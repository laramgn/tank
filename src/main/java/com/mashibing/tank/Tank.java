package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

//tank 类
public class Tank {
    private int x = 200;
    private final int SPEED = 2;
    private int y = 200;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private TankFrame tf;
    public static int TANK_WIDTH = ResourceMngr.tankD.getWidth();
    public static int TANK_HEIGHT = ResourceMngr.tankD.getHeight() + 4;
    private boolean live = true;
    private Random random = new Random();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //敌方坦克还是我方坦克
    private Group group = Group.Bad;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        if (!live) tf.tanks.remove(this);
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMngr.tankR, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMngr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMngr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMngr.tankD, x, y, null);
                break;

        }
        move();

    }

    private void move() {
        if (!moving) return;
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
        if (random.nextInt(10) > 8) this.fire();

    }

    public void fire() {
        tf.bulletList.add(new Bullet(this.x + TANK_WIDTH / 2 - Bullet.WIDTH / 2, this.y + TANK_HEIGHT / 2 - Bullet.HEIGHT / 2, dir, this.group, tf));
    }

    public void die() {
        this.live = false;
    }
}
