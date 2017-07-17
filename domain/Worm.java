package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private List<Piece> wormPieces;
    private int headX, headY;
    private Direction direction;
    private boolean wormGrows;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        headX = originalX;
        headY = originalY;
        Piece piece = new Piece(originalX, originalY);
        this.direction = originalDirection;
        this.wormPieces = new ArrayList<>();
        wormPieces.add(piece);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getLength() {
        return wormPieces.size();
    }

    public List<Piece> getPieces() {
        return wormPieces;
    }

    public void move() {
        if (wormPieces.size() < 3) {
            grow();
        }
            moveWorm();

        if (!wormGrows) {
            wormPieces.remove(0);
        } else {
            wormGrows = false;
        }
    }

    public void grow() {
    wormGrows = true;
    }

    public boolean runsInto(Piece piece) {
        for (Piece worm : wormPieces) {
            if (piece.getX() == worm.getX() && piece.getY() == worm.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean runsIntoItself() {
        List<Piece> testPieces = new ArrayList<>();

        for (int i = 0; i < wormPieces.size() - 1; i++) {
            testPieces.add(wormPieces.get(i));
        }

        try {
            for (Piece worm : testPieces) {
                for (Piece W : testPieces) {
                    if (W == worm) {
                        continue;
                    }
                    if (worm.getX() == headX && worm.getY() == headY) {
                        return true;
                    }

                    if (W.getX() == worm.getX() && W.getY() == worm.getY()) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getXDirection() {
        if (direction == Direction.RIGHT) {
            return ++headX;
        } else if (direction == Direction.LEFT) {
            return --headX;
        } else if (direction == Direction.UP || direction == Direction.DOWN) {
            return headX;
        }

        return -1;
    }

    private int getYDirection() {
        if (direction == Direction.RIGHT || direction == Direction.LEFT) {
            return headY;
        } else if (direction == Direction.UP) {
            return --headY;
        } else if (direction == Direction.DOWN) {
            return ++headY;
        }
        return -1;
    }

    private void moveWorm() {
        wormPieces.add(new Piece(getXDirection(), getYDirection()));
    }

    public int getHeadX() {
        return headX;
    }

    public int getHeadY() {
        return headY;
    }

}
