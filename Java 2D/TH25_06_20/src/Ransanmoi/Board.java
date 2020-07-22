package Ransanmoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    //Thiết lập kích thước các đối tượng có trong game
    private final int B_WIDTH = 500;//kích thước của cửa sổ
    private final int B_HEIGHT = 500;
    private final int DOT_SIZE = 10;//khoảng cách giữa các đốt
    private final int ALL_DOTS = 900;//số lượng chấm của Rắn (900 = (300*300)/(10*10))
    private final int RAND_POS = 40;//khoảng cách giữa rắn và táo
    private final int DELAY = 100;//tốc độ rắn chạy giảm = nhanh

    private final int x[] = new int[ALL_DOTS];// lưu trữ tọa độ phần thân của con rắn
    private final int y[] = new int[ALL_DOTS];

    private int dots;//tạo biến chứa phần thân
    private int apple_x;//biến vị trí xuất hiện táo
    private int apple_y;

    //Tạo các biến để điều khiển: trên, dưới, phải ,trái
    private boolean trai = false;
    private boolean phai = false;
    private boolean tren = false;
    private boolean duoi = false;
    private boolean inGame = true;

    private Timer timer;// thoigian
    private Image than;// phần thân
    private Image tao;//táo
    private Image dau;//đầu

    public Board() {
        
        initBoard();
    }
    // thiết lập màn hình chơi
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    //load hình ảnh
    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        than = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        tao = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        dau = iih.getImage();
    }

    private void initGame() {
    //Tạo ra con rắn với 3 chấm.
        dots = 4;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    //thiết lập thời gian xuất hiện quả táo
        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(tao, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(dau, x[z], y[z], this);
                } else {
                    g.drawImage(than, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Snake Die";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
/*Nếu rắn ăn được quả táo, sẽ tăng số lượng chấm thân của con rắn.
gọi phương thức locationApple () để định vị vị trí ngẫu nhiên của quả apple mới.*/
    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();
        }
    }
/* Thuật toán chính của trò chơi. Có thể điều khiển các khớp của con rắn cùng di chuyển đồng bộ, nối tiếp nhau*/
    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (trai) {
            x[0] -= DOT_SIZE;
        }

        if (phai) {
            x[0] += DOT_SIZE;
        }

        if (tren) {
            y[0] -= DOT_SIZE;
        }

        if (duoi) {
            y[0] += DOT_SIZE;
        }
    }
    //Kiểm tra va chạm
    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }
    //Định vị vị trí xuất hiện táo
    private void locateApple() {//lấy số ngẫu nhiên

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }
// thiết lập các phím chức năng
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!phai)) {
                trai = true;
                tren = false;
                duoi = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!trai)) {
                phai= true;
                tren = false;
                duoi = false;
            }

            if ((key == KeyEvent.VK_UP) && (!duoi)) {
                tren = true;
                phai = false;
                trai = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!tren)) {
                duoi = true;
                phai = false;
                trai = false;
            }
        }
    }
}