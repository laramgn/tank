package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    List<Bullet> bulletList = new ArrayList();

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setBackground(Color.BLUE);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        //解决屏幕闪烁问题
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLUE);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bulletList.size(), 10, 60);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

    }

    public class MyKeyListener extends KeyAdapter {
        boolean BL = false;
        boolean BR = false;
        boolean BU = false;
        boolean BD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    BL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = true;
                    break;
                case KeyEvent.VK_UP:
                    BU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();

        }

        private void setMainTankDir() {
            if (!BL && !BR && !BU && !BD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (BL) myTank.setDir(Dir.LEFT);
                if (BR) myTank.setDir(Dir.RIGHT);
                if (BU) myTank.setDir(Dir.UP);
                if (BD) myTank.setDir(Dir.DOWN);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    BL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = false;
                    break;
                case KeyEvent.VK_UP:
                    BU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }
            setMainTankDir();

        }
    }


}
