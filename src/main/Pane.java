package main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pane extends JPanel {

    public static final long RUNNING_TIME = 2000;

    private BufferedImage inImage;

    private float alpha = 0f;
    private long startTime = -1;

    public Pane() throws IOException {
    		Timer timer = new Timer();
    		alpha = 0f;
    		inImage = ImageIO.read(new File("/img/logo.jpg"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        int x = (getWidth() - inImage.getWidth()) / 2;
        int y = (getHeight() - inImage.getHeight()) / 2;
        g2d.drawImage(inImage, x, y, this);
        g2d.dispose();
    }

}