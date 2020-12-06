package com.mashibing.tank;

import java.awt.*;

//子弹类
public class Bullet {
    private int x;
    private final int SPEED = 5;
    private int y;
    private Dir dir;
    public static int WIDTH = ResourceMngr.bulletD.getWidth();
    public static int HEIGHT = ResourceMngr.bulletD.getHeight();
    private boolean live = true;
    private Group group = Group.Bad;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bulletList.remove(this);
        }
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMngr.bulletR, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMngr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMngr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMngr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;

    }

    public void collideWith(Tank tank) {
        if (this.group ==  tank.getGroup()) return;
        Rectangle rec1 = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
        Rectangle rec2 = new Rectangle(tank.getX(), tank.getY(), tank.TANK_WIDTH, tank.TANK_HEIGHT);
        if (rec1.intersects(rec2)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
