package wormgame.game;

import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WormGame extends Timer implements ActionListener {
    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random r = new Random();

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);

        setAppleCoordinates();

        addActionListener(this);
        setInitialDelay(2000);
    }

    public Worm getWorm() {
        return worm;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void setAppleCoordinates() {
        int wormX = width / 2;
        int wormY = height / 2;
        while (true) {
            int appleWidth = r.nextInt(this.width), appleHeight = r.nextInt(this.width);
            if (appleWidth == wormX && appleHeight == wormY) {
            } else {
                this.apple = new Apple(appleWidth, appleHeight);
                break;
            }
        }
    }

    private boolean hitsBorder() {
        if (worm.getHeadX() >= width || worm.getHeadY() >= height) {
            return true;
        } else if (worm.getHeadX() < 0 || worm.getHeadY() < 0) {
            return true;
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        worm.move();

        if (worm.runsInto(apple)) {
            setAppleCoordinates();
            worm.grow();
        }

        if (hitsBorder()) {
            continues = false;
        }

        if (worm.runsIntoItself()) {
            continues = false;
        }

        if (!continues) {
            return;
        }

        updatable.update();

        setDelay(1000 / worm.getLength());


    }

}
