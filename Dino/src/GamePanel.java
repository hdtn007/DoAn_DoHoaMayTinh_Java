import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import components.Ground;
import components.Dino;
import components.Obstacles;

class GamePanel extends JPanel implements KeyListener, Runnable {//tạo cửa sổ
  
  public static int WIDTH;
  public static int HEIGHT;
  private Thread animator;
  
  private boolean running = false;
  private boolean gameOver = false;
  
  Ground ground;
  Dino dino;//Gọi file Dino 
  Obstacles obstacles;

  private int score;//khai báo điểm
  
  public GamePanel() {
    WIDTH = UserInterface.WIDTH;
    HEIGHT = UserInterface.HEIGHT;
    
    ground = new Ground(HEIGHT);
    dino = new Dino();
    obstacles = new Obstacles((int)(WIDTH * 1.5));

    score = 0;// thiết lập điểm =0
    
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }
  
  public void paint(Graphics g) {// vẽ các đối tượng
    super.paint(g);
    g.setFont(new Font("Courier New", Font.BOLD, 25));
    g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);// hiển thị score lên màn hình
    ground.create(g);
    dino.create(g);
    obstacles.create(g);
  }
  
  public void run() {//hàm run
    running = true;

    while(running) {
      updateGame();
      repaint();      
      try {
        Thread.sleep(50);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void updateGame() {//hàm cập nhật game
    score += 1;

    ground.update();
    // dino.update();
    obstacles.update();

    if(obstacles.hasCollided()) {//hàm va chạm
      dino.die();
      repaint();
      running = false;
      gameOver = true;
      System.out.println("Va chạm");
    }
    // game complete condition
  }

  public void reset() {//reset điểm
    score = 0;
    System.out.println("Reset điểm");
    obstacles.resume();
    gameOver = false;
  }
  
  public void keyTyped(KeyEvent e) {// phát hiện phím "cách" và play
    // System.out.println(e);
    if(e.getKeyChar() == ' ') {    
      if(gameOver) reset();
      if (animator == null || !running) {
        System.out.println("Game Bắt Đầu");        
        animator = new Thread(this);
        animator.start();     
        dino.startRunning();   
      } else {
        dino.jump();
      }
    }
  }
  
  public void keyPressed(KeyEvent e) {
    
  }
  
  public void keyReleased(KeyEvent e) {
    
  }
}