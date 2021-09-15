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

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 1200;

    private final int DELAY = 140;

    private int simonGreen_x;
    private int simonGreen_y;

    private int simonYellow_x;
    private int simonYellow_y;

    private int simonBlue_x;
    private int simonBlue_y;

    private int simonRed_x;
    private int simonRed_y;


    private int lightGreen_x;
    private int lightGreen_y;

    private int lightYellow_x;
    private int lightYellow_y;

    private int lightBlue_x;
    private int lightBlue_y;

    private int lightRed_x;
    private int lightRed_y;


    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image yellow;
    private Image blue;
    private Image red;
    private Image green;

    private Image lightYellow;
    private Image lightBlue;
    private Image lightRed;
    private Image lightGreen;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon r = new ImageIcon("src/main/resources/SimonRed.png");
        red = r.getImage();

        ImageIcon g = new ImageIcon("src/main/resources/SimonGreen.png");
        green= g.getImage();

        ImageIcon y = new ImageIcon("src/main/resources/SimonYellow.png");
        yellow = y.getImage();

        ImageIcon b = new ImageIcon("src/main/resources/SimonBlue.png");
        blue = b.getImage();

        ImageIcon lr = new ImageIcon("src/main/resources/redLighted.png");
        lightRed = lr.getImage();

        ImageIcon lg = new ImageIcon("src/main/resources/greenLighted.png");
        lightGreen= lg.getImage();

        ImageIcon ly = new ImageIcon("src/main/resources/yellowLighted.png");
        lightYellow = ly.getImage();

        ImageIcon lb = new ImageIcon("src/main/resources/blueLighted.png");
        lightBlue = lb.getImage();
    }

    private void initGame() {

        initColor();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initColor() {
        simonGreen_x = 350;
        simonGreen_y = 200;

        simonYellow_x=350;
        simonYellow_y=450;

        simonBlue_x=600;
        simonBlue_y=450;

        simonRed_x=600;
        simonRed_y=200;
        
        //=====Selected/Lighted================
        lightGreen_x = 350;
        lightGreen_y = 200;

        lightYellow_x=350;
        lightYellow_y=450;

        lightBlue_x=600;
        lightBlue_y=450;

        lightRed_x=600;
        lightRed_y=200;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(green,  simonGreen_x,  simonGreen_y, this);
            g.drawImage(yellow,  simonYellow_x,  simonYellow_y, this);
            g.drawImage(blue,  simonBlue_x,  simonBlue_y, this);
            g.drawImage(red,  simonRed_x,  simonRed_y, this);

            move(g);
            
//            Toolkit.getDefaultToolkit().sync();

        } else {
        	
           // gameOver(g);
        }
    }


    private void move(Graphics g) {

        if (inGame) {


            if (leftDirection) {
                g.drawImage(lightGreen,  lightGreen_x,  lightGreen_y, this);
            }

            if (rightDirection) {
                g.drawImage(lightRed,  lightRed_x,  lightRed_y, this);
            }

            if (upDirection) {
                g.drawImage(lightBlue,  lightBlue_x,  lightBlue_y, this);
            }

            if (downDirection) {
                g.drawImage(lightYellow,  lightYellow_x,  lightYellow_y, this);
            }



//            Toolkit.getDefaultToolkit().sync();

        }


    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

        	

        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                rightDirection = false;
            }

            if (key == KeyEvent.VK_RIGHT)  {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                leftDirection = false;
            }

            if (key == KeyEvent.VK_UP) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_DOWN)  {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                upDirection = false;
            }
        }
    }
}