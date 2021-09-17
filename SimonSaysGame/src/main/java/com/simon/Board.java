package com.simon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;


public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 1400;
    private final int B_HEIGHT = 1200;

    private final int DELAY = 150;

    private int simonGreen_x;
    private int simonGreen_y;

    private int simonPurple_x;
    private int simonPurple_y;

    private int simonBlue_x;
    private int simonBlue_y;

    private int simonRed_x;
    private int simonRed_y;


    private int lightGreen_x;
    private int lightGreen_y;

    private int lightPurple_x;
    private int lightPurple_y;

    private int lightBlue_x;
    private int lightBlue_y;

    private int lightRed_x;
    private int lightRed_y;

    private int instructions_x;
    private int instructions_y;


    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    private boolean inGame = true;
    private boolean creatingSequence = true;
    
    private int seconds; 
    private int sequenceIndex;
    private int countDown;
    int rand;
    
    private Timer timer;
    private Image purple;
    private Image blue;
    private Image red;
    private Image green;

    private Image lightPurple;
    private Image lightBlue;
    private Image lightRed;
    private Image lightGreen;
    private Image instruct;
    
    
    // 0 = green, 1 = red, 2 = purple, 3 = blue
    ArrayList<Integer> gameList = new ArrayList<Integer> ();
    ArrayList<Integer> playerList = new ArrayList<Integer> ();
    

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new KeyStrokes());
        setBackground(Color.white);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        //startSequence(gameList);
        
        
        initGame();
    }

    private void loadImages() {

        ImageIcon r = new ImageIcon("src/main/resources/SimonRed.png");
        red = r.getImage();

        ImageIcon g = new ImageIcon("src/main/resources/SimonGreen.png");
        green= g.getImage();

        ImageIcon y = new ImageIcon("src/main/resources/SimonPurple.png");
        purple = y.getImage();

        ImageIcon b = new ImageIcon("src/main/resources/SimonBlue.png");
        blue = b.getImage();

        ImageIcon lr = new ImageIcon("src/main/resources/redLighted.png");
        lightRed = lr.getImage();

        ImageIcon lg = new ImageIcon("src/main/resources/greenLighted.png");
        lightGreen= lg.getImage();

        ImageIcon ly = new ImageIcon("src/main/resources/purpleLighted.png");
        lightPurple = ly.getImage();

        ImageIcon lb = new ImageIcon("src/main/resources/blueLighted.png");
        lightBlue = lb.getImage();

        ImageIcon im = new ImageIcon("src/main/resources/instructions.png");
        instruct = im.getImage();
    }

    private void initGame() {

        initColor();
        seconds = 0;
        sequenceIndex = 0;
        countDown = 2;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initColor() {
        simonGreen_x = 350;
        simonGreen_y = 200;

        simonPurple_x=350;
        simonPurple_y=450;

        simonBlue_x=600;
        simonBlue_y=450;

        simonRed_x=600;
        simonRed_y=200;
        
        //=====Selected/Lighted================
        lightGreen_x = 350;
        lightGreen_y = 200;

        lightPurple_x=350;
        lightPurple_y=450;

        lightBlue_x=600;
        lightBlue_y=450;

        lightRed_x=600;
        lightRed_y=200;

        instructions_x=900;
        instructions_y=0;
    }
    
    //create sequence to challenge user
    private void startSequence (ArrayList<Integer> list) {
    
    	//this creates 7 numbers btwn 0-3 and put's it into the list
	    for(int i = 0; i < 7; i++) {
	    	Double rand = Math.random() * 4;
	    	list.add(rand.intValue());   	
	    }
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
        	
            origColor(g);
            colorChange(g);
            

        } else {
        	
           gameOver(g);
        }
    }

    private void origColor(Graphics g){
        g.drawImage(green,  simonGreen_x,  simonGreen_y, this);
        g.drawImage(purple,  simonPurple_x,  simonPurple_y, this);
        g.drawImage(blue,  simonBlue_x,  simonBlue_y, this);
        g.drawImage(red,  simonRed_x,  simonRed_y, this);
        g.drawImage(instruct,  instructions_x,  instructions_y, this);

    }

    private void autoChange(int num) {
    	// 0 = green, 1 = red, 2 = purple, 3 = blue
    	switch (num) {
		case 0:
			leftDirection=true;		
			System.out.println(num);
			break;
		case 1:
			rightDirection=true;
			System.out.println(num);
			break;
		case 2:
			downDirection=true;
			System.out.println(num);
			break;
		case 3:
			upDirection=true;
			System.out.println(num);
			break;
		default:
			System.out.println("broken");
			break;
		}
    }

    private void colorChange(Graphics g) {

        if (inGame) {

            if (leftDirection) {
                g.drawImage(lightGreen,  lightGreen_x,  lightGreen_y, this);
                leftDirection=false;
            }

            if (rightDirection) {
                g.drawImage(lightRed,  lightRed_x,  lightRed_y, this);
                rightDirection=false;
            }

            if (upDirection) {
                g.drawImage(lightBlue,  lightBlue_x,  lightBlue_y, this);
                upDirection=false;
            }

            if (downDirection) {
                g.drawImage(lightPurple,  lightPurple_x,  lightPurple_y, this);
                downDirection=false;
            }

        }

    }
    
    private void checkLists(ArrayList<Integer> gList, int index) {
    	// 0 = green, 1 = red, 2 = purple, 3 = blue
    	//	  left,		right,	   down,	  up
    	//pass in index as sequenceIndex
    	int listIndex = gList.get(index);

    	switch (listIndex) {
		case 0:
			if (leftDirection) {
				sequenceIndex++;
			} else {
				inGame = false;
			}
			break;
		case 1:
			if (rightDirection) {
				sequenceIndex++;
			} else {
				inGame = false;
			}
			break;
		case 2:
			if (downDirection) {
				sequenceIndex++;
			} else {
				inGame = false;
			}
			break;
		case 3:
			if (upDirection) {
				sequenceIndex++;
			} else {
				inGame = false;
			}
			break;
		default:
			inGame = false;
			System.out.println("Game over");
			break;
		}
    	if (gList.get(sequenceIndex) == 0) {
    		
    	}
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        
        setBackground(Color.black);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
        	
        	seconds++;
        	if (seconds % 5 == 0) {
        		leftDirection = false;
                upDirection = false;
                downDirection = false;
                rightDirection = false;
                
                if (countDown >= 0) {
                	countDown --;
                }
        	}
        	
        	if (creatingSequence) {
        		if (countDown <= 0) { //slow down the blinking
        			if(sequenceIndex >= gameList.size()) {
		        		rand = (int)(Math.random() * 4);
		        		
		        		autoChange(rand);
		        		gameList.add(rand);
		        		sequenceIndex = 0;
		        		creatingSequence = false;
        			} else {
        				autoChange(gameList.get(sequenceIndex));
        				sequenceIndex++;
        			}
	        		countDown = 1;
	        		
        		}
        	} else if (sequenceIndex == gameList.size()){
        		creatingSequence = true;
        		sequenceIndex = 0;
        		countDown = 1;
        	}
        	
        	//checkLists(gameList,playerList);

        }

        repaint();
    }

    private class KeyStrokes extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if (!creatingSequence) {
	            if (key == KeyEvent.VK_LEFT) {
	                leftDirection = true;
	                upDirection = false;
	                downDirection = false;
	                rightDirection = false;
	                //playerList.add(0);
	                seconds = 1;
	            }
	
	            if (key == KeyEvent.VK_RIGHT)  {
	                rightDirection = true;
	                upDirection = false;
	                downDirection = false;
	                leftDirection = false;
	                //playerList.add(1);
	                seconds = 1;
	            }
	
	            if (key == KeyEvent.VK_UP) {
	                upDirection = true;
	                rightDirection = false;
	                leftDirection = false;
	                downDirection = false;
	                //playerList.add(3);
	                seconds = 1;
	            }
	
	            if (key == KeyEvent.VK_DOWN)  {
	                downDirection = true;
	                rightDirection = false;
	                leftDirection = false;
	                upDirection = false;
	                //playerList.add(2);
	                seconds = 1;
	            }
	            
	            checkLists(gameList, sequenceIndex);
	            
            }   
        }
    }
}