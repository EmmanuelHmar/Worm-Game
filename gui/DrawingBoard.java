package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.WormGame;
import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable{
    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength) {
        super.setBackground(Color.GRAY);
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (Piece x : wormGame.getWorm().getPieces()) {
            g.fill3DRect(x.getX()*pieceLength,x.getY()*pieceLength,pieceLength,pieceLength,false);
        }

        g.setColor(Color.RED);
        g.fillOval(wormGame.getApple().getX()*pieceLength, wormGame.getApple().getY()*pieceLength, pieceLength,pieceLength);

    }

    @Override
    public void update() {
        super.repaint();
    }
}
