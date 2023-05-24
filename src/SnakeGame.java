import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {

    private int score = 0;

    private Timer timer;
    private int delay = 100;
    private int squareSize = 10;
    private int borderWidth = 30;
    private int boardWidth = 400;
    private int boardHeight = 400;
    private int[] x = new int[100];
    private int[] y = new int[100];
    private int snakeLength = 3;
    private int foodX;
    private int foodY;
    private boolean gameOver = false;
    private boolean paused = false;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    public SnakeGame() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(new ArrowKeysListener());

        startGame();
    }

    public void startGame() {
        x[0] = 50;
        y[0] = 50;
        x[1] = 40;
        y[1] = 50;
        x[2] = 30;
        y[2] = 50;

        generateFood();

        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            g.setColor(Color.red);
            g.fillRect(foodX, foodY, squareSize, squareSize);

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.yellow);
                }
                g.fillRect(x[i], y[i], squareSize, squareSize);
            }
        } else {
            g.setColor(Color.white);
            g.drawString("Game Over!", boardWidth / 2 - 30, boardHeight / 2);
        }

        g.setColor(Color.white);
        g.drawRect(borderWidth - 1, borderWidth - 1, boardWidth - borderWidth * 2 + 2, boardHeight - borderWidth * 2 + 2);
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, 20); // Ghi điểm lên màn hình
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !paused) {
            moveSnake();
            checkCollisions();
        }
        repaint();
    }

    public void moveSnake() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (left) {
            x[0] -= squareSize;
        }

        if (right) {
            x[0] += squareSize;
        }

        if (up) {
            y[0] -= squareSize;
        }

        if (down) {
            y[0] += squareSize;
        }
    }

    public void generateFood() {
        int randX = (int) (Math.random() * (boardWidth - squareSize - borderWidth * 2) / squareSize) * squareSize + borderWidth;
        int randY = (int) (Math.random() * (boardHeight - squareSize - borderWidth * 2) / squareSize) * squareSize + borderWidth;
        foodX = randX;
        foodY = randY;
    }

    public void checkCollisions() {
        if (x[0] < borderWidth || x[0] > boardWidth - borderWidth - squareSize || y[0] < borderWidth || y[0] > boardHeight - borderWidth - squareSize) {
            gameOver = true;
        }

        for (int i = 1; i < snakeLength; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                gameOver = true;
            }
        }

        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            generateFood();
        }
        if (x[0] == foodX && y[0] == foodY) {
            score += 10; // Tăng điểm lên 10
            snakeLength++;
            generateFood();
        }
    }

    private class ArrowKeysListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }

            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }

            if (key == KeyEvent.VK_SPACE) {
                paused = !paused;
            }

            if (key == KeyEvent.VK_ENTER && gameOver) {
                startGame();
                gameOver = false;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SnakeGame());
        frame.pack();
        frame.setVisible(true);
    }
}