package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private List<Piece> wormPieces = new ArrayList<>();
    private Piece growthPiece;
    private int headX, headY;
    private int matureLength = 1;
    private Direction direction;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        headX = originalX;
        headY = originalY;
        Piece piece = new Piece(originalX, originalY);
        this.direction = originalDirection;
        wormPieces.add(piece);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        //Worm starts to move in the new direction when move is called again
        this.direction = dir;
    }

    public int getLength() {
        return getPieces().size();
    }

    public List<Piece> getPieces() {
        return wormPieces;
    }

    public void move() {

        if (growthPiece != null && !wormPieces.contains(growthPiece)) {
            wormPieces.add(growthPiece);
            return;
        }

        if (matureLength < 3) {
            wormPieces.add(new Piece(getXDirection(), getYDirection()));
//            if (direction == Direction.RIGHT) {
//                wormPieces.add(new Piece(++headX, headY));
//            } else if (direction == Direction.LEFT) {
//                wormPieces.add(new Piece(--headX, headY));
//            } else if (direction == Direction.UP) {
//                wormPieces.add(new Piece(headX, --headY));
//            } else if (direction == Direction.DOWN) {
//                wormPieces.add(new Piece(getXDirection(), getYDirection()));
//            }
//
            matureLength++;
        } else {
            if (direction == Direction.RIGHT) {
                moveWormRight();
            } else if (direction == Direction.LEFT) {
                moveWormLeft();
            } else if (direction == Direction.UP || direction == Direction.DOWN) {
                moveWormUpOrDown();
            }
        }
    }

    public void grow() {

        growthPiece = new Piece(getXDirection(), getYDirection());

    }

    public boolean runsInto(Piece piece) {
        return headX == piece.getX() && headY == piece.getY();
    }

    public boolean runsIntoItself() {
        int length = 0;
        for (Piece x : wormPieces) {
            if (length == wormPieces.size() - 1) {
                break;
            }

            if (this.headX == x.getX() && headY == x.getY()) {
                return true;
            }
            length++;
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

    private void moveWormRight() {
        wormPieces.remove(0);
        wormPieces.add(new Piece(getXDirection(), getYDirection()));
    }

    private void moveWormLeft() {
        wormPieces.remove(0);
        wormPieces.add(new Piece(getXDirection(), getYDirection()));
    }

    private void moveWormUpOrDown() {
        wormPieces.remove(0);
        wormPieces.add(new Piece(getXDirection(), getYDirection()));
    }
}
