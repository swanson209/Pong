package pong;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Pong extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    JFrame frame;
    static boolean gameRunning = false;
    public final int WIDTH = 600;
    public final int HEIGHT = WIDTH / 16 * 9;
    public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public final String TITLE = "Pong InDev";

    @Override
    public void run() {

        while (gameRunning) {
            tick();
            render();
        }
    }
    
    public synchronized void start() {
        gameRunning = true;
        new Thread(this).start();
    }
    
    public static void stop() {
        gameRunning = false;
        System.exit(0);
    }

    public void tick() {

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getWidth());
        g.dispose();
        bs.show();
    }

    public Pong() {
        frame = new JFrame();

        setMinimumSize(gameSize);
        setPreferredSize(gameSize);
        setMaximumSize(gameSize);

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(TITLE);
        frame.setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
        Pong pong = new Pong();
        pong.start();
    }
}
