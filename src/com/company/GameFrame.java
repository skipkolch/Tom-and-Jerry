package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame {

    private static JButton button;
    private static JFrame frame;
    private static JPanel panel;

    private static Random rand;

    private static Timer timer;

    private static Cat Tom;
    private static Animal Jerry;

    private static AnimalsImage animalImage;


    public static void CreateGameFrame()
    {
        frame = new JFrame("Tom and Jerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));

        rand = new Random();

        animalImage = new AnimalsImage("D:\\Jave Projects\\Tom and Jerry\\src\\Tom1.jpg",
                                    "D:\\Jave Projects\\Tom and Jerry\\src\\jer1.jpg",
                                    new Point(200,200),
                                    new Point(10,10));

        frame.add(animalImage);

        frame.addMouseListener(new ComplexMouseListener());

        frame.pack();
        frame.setVisible(true);

        Tom = new Cat("Tom",animalImage.getTomPposition().x,animalImage.getTomPposition().y,animalImage.GetHeightTom(),animalImage.GetWidthTom());
        Jerry = new Mouse("Jerry",animalImage.getJerryPosition().x,animalImage.getJerryPosition().y,animalImage.GetHeightJerry(),animalImage.GetWidthJerry());

        timer = new Timer(100, e -> {

            if(!Tom.isCatchMouse())
            {
                Tom.CatchMouse(Jerry.getPosition());

                animalImage.setTomPposition(Tom.getPosition());
                animalImage.setJerryPosition(Jerry.getPosition());

            }
            else{
                button.setText("GameOver!");
                button.setEnabled(true);
                Tom.resetCatch();
                timer.stop();
            }
        });


        button = new JButton("Start");
        frame.add(button, BorderLayout.SOUTH);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                button.setEnabled(false);

                Tom.setPosition(new Point(rand.nextInt(frame.getWidth()), rand.nextInt(frame.getHeight())));
                timer.start();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(GameFrame::CreateGameFrame);

    }

    static class ComplexMouseListener implements MouseListener, MouseMotionListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            Jerry.setPosition(e.getPoint());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Jerry.setPosition(e.getPoint());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Jerry.setPosition(e.getPoint());
        }
    }
}

class AnimalsImage extends JComponent{

    private Image tomImage;
    private Image jerryImage;

    private Point tomPosition;
    private Point jerryPosition;

    public int GetHeightTom()
    {
        return tomImage.getHeight(null);
    }

    public int GetWidthTom()
    {
        return tomImage.getWidth(null);
    }

    public int GetHeightJerry()
    {
        return jerryImage.getHeight(null);
    }

    public int GetWidthJerry()
    {
        return jerryImage.getWidth(null);
    }

    public Point getTomPposition() {
        return tomPosition;
    }

    public void setTomPposition(Point tomPposition) {
        this.tomPosition = tomPposition;
    }

    public Point getJerryPosition() {
        return jerryPosition;
    }

    public void setJerryPosition(Point jerryPosition) {
        this.jerryPosition = jerryPosition;
    }

    public AnimalsImage(String TomJPG, String JerryJPG , Point startPositionTom, Point startPositionJerry)
    {
        try {
            tomImage = ImageIO.read(new File(TomJPG));
            tomPosition = startPositionTom;

            jerryImage = ImageIO.read(new File(JerryJPG));
            jerryPosition = startPositionJerry;

        } catch (IOException ex) {
            return;
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d=(Graphics2D)g;


        g2d.drawImage(jerryImage,jerryPosition.x - jerryImage.getWidth(null),jerryPosition.y - jerryImage.getHeight(null),this);
        g2d.drawImage(tomImage,tomPosition.x - tomImage.getWidth(null),tomPosition.y - tomImage.getHeight(null),this);
        super.repaint();
    }
}


