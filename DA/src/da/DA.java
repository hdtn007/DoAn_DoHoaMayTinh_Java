
package da;

import javax.swing.JButton;

class Heart extends javax.swing.JPanel {
 
   public void paintComponent(java.awt.Graphics g)
   {
      super.paintComponents(g);
 
      java.awt.Graphics2D g2d = (java.awt.Graphics2D)g;
 
      g2d.fillOval(50, 50, 100, 100);
 
      //Fill the rest in
      //...
 
      g2d.setColor(java.awt.Color.black);
   }
}
public class DA { 
    public static void main(String[] args) {
      javax.swing.JFrame frame = new javax.swing.JFrame();
 
      frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
      
      int w = 600;
      int h = 300;
 
      frame.setBounds(500,500,w,h);
      frame.setTitle("Đồ Họa Máy Tính 2D");
      frame.setVisible(true);
      frame.getContentPane().setLayout(null);
 
      JButton b1 = new JButton();
      b1.setBounds(5,20,60,30);
      b1.setVisible(true);
      b1.setText("Phải");
      b1.action(click, h);
      frame.add(b1);

      
      JButton b2 = new JButton();
      b2.setBounds(80,20,60,30);
      b2.setVisible(true);
      b2.setText("Trái");
      frame.add(b2);
      
      Heart heart = new Heart();
      heart.setBounds(5,50,w,h);
 
      frame.getContentPane().add(heart);
      
      
    }
}