import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class UserInterface {// thiết lập form
  JFrame mainWindow = new JFrame("Khủng Long Cute Chạy Thể Dục");
  
  public static int WIDTH = 800;
  public static int HEIGHT = 500;
  
  public void createAndShowGUI() {
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Container container = mainWindow.getContentPane();
    
    GamePanel gamePanel = new GamePanel();//gọi file GamePanel
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);
    
    container.setLayout(new BorderLayout());
    
    container.add(gamePanel, BorderLayout.CENTER);
    
    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(false);
    mainWindow.setVisible(true);
  }
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}