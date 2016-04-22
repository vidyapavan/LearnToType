/****************************************************************************************************************
 
        Learn to Type App
 
        @author - Vidya Pavan
 
 ****************************************************************************************************************/

import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;


public class LearnToType{
    
JFrame f;
static int CELL_COUNTER = 0;
static final int NUM_OF_CELLS = 9;
static char letter[] = new char[NUM_OF_CELLS];
static JButton [] buttons = new JButton[NUM_OF_CELLS];
    static int ALPHABET_DISPLAY_COUNT = 0;
    
LearnToType(){
    f=new JFrame();
    
    generateFrameComponents();
}

    public void generateFrameComponents() {
        System.out.println("Generating new frame components");
        
        if (ALPHABET_DISPLAY_COUNT < 2) {
            for (int i=0; i<NUM_OF_CELLS; i++) {
                letter[i] = generateRandomAlphabet();
                System.out.println(letter[i]);
            }
            ALPHABET_DISPLAY_COUNT = ALPHABET_DISPLAY_COUNT + 1;
        }
        else {
            for (int i=0; i<NUM_OF_CELLS; i++) {
                letter[i] = generateRandomNumber();
                System.out.println(letter[i]);
            }
            ALPHABET_DISPLAY_COUNT = 0;
        }
        
        for (int i=0; i<NUM_OF_CELLS; i++) {
            buttons[i] = new JButton(Character.toString(letter[i]));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 80));
            buttons[i].setForeground(Color.GRAY);
            buttons[i].addKeyListener(new MyKeyListener());
        }
        
        
        for (int i=0; i<NUM_OF_CELLS; i++) {
            f.add(buttons[i]);
        }
        
        f.setLayout(new GridLayout(3,3));//creating grid layout of 3 row and 3 columns
        f.setSize(800,800);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Initial focus
        buttons[0].requestFocus();
    }
    
public static void main(String[] args) {
    new LearnToType();
}
    
    private char generateRandomAlphabet() {
        String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rd = new Random();

        return(alphabetString.charAt(rd.nextInt(26)));
    }
    
    private char generateRandomNumber() {
        String numberString = "0123456789";
        Random rd = new Random();
        
        return(numberString.charAt(rd.nextInt(10)));
    }
    
    public class MyKeyListener implements KeyListener{
        
        public void keyTyped(KeyEvent e) {
        
        }
        
        public void keyReleased(KeyEvent e) {
            
        }
        
        public void keyPressed(KeyEvent e) {
            
                //Handle end of frame
                if (CELL_COUNTER >= 9) {
                    System.out.println("All Done");
                    CELL_COUNTER = 0;
                    
                    clearFrameComponents();
                    
                    //Generate new frame
                    generateFrameComponents();
                    //return;
                }
            
                System.out.println("Key pressed: " + e.getKeyChar());
                System.out.println("Cell  "+CELL_COUNTER);
                System.out.println("Input letter "+Character.toUpperCase(e.getKeyChar()));
                System.out.println("Cell letter "+letter[CELL_COUNTER]);
            
                boolean flag = compareInputKeyWithCell(Character.toUpperCase(e.getKeyChar()), letter[CELL_COUNTER]);
            
                if (flag) {
                    buttons[CELL_COUNTER].setForeground(Color.BLUE);
                
                    // TBD Play sound
                    //playAlphabetSound();
                    
                    CELL_COUNTER = CELL_COUNTER + 1;
                    if (CELL_COUNTER < 9) {
                        buttons[CELL_COUNTER].requestFocus();
                    }
                }
                else {
                
                }
        }
        
    }
    
    public void clearFrameComponents() {
        //Close old frame
        System.out.println("Clearing buttons from old frame");
        //f.dispose();
        for (int i=0; i<NUM_OF_CELLS; i++) {
            f.remove(buttons[i]);
        }
    }
    
    /*public void playAlphabetSound() {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("A.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }*/
    
    public boolean compareInputKeyWithCell(char inputLetter, char cellLetter) {
        boolean isMatching = false;
        if (inputLetter == cellLetter) {
            isMatching = true;
        }
        System.out.println("isMatching flag "+isMatching);
        return isMatching;
    }

}