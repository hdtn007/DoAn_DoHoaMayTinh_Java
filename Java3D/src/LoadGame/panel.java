package LoadGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.Background;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import LoadGame.java3d; // file java3d
import javafx.scene.layout.BackgroundImage;

public class panel implements ActionListener {
	
	 public static JButton chan_bnt = new JButton("Chẳn");
	 public static JButton le_bnt = new JButton("Lẻ");
	 public static JLabel lbnut = new JLabel("Số Nút");
	 public static JTextField nut = new JTextField(5);
	 public static JButton start = new JButton("Lắc");
	 public static JButton restart = new JButton("Restart");
	 public static Background bg ;
	 
	 
	 public static int[][] matran = new int[][] { // ma trận vị trí số nút
		 {1,1,1,1,1,1,1},
		 {5,4,2,3,5,4,2},
		 {6,6,6,6,6,6,6},
		 {5,3,2,4,5,3,2},
		 {1,1,1,1,1,1,1},
		 {5,4,2,3,5,4,2},
		 {6,6,6,6,6,6,6}
	 };
	
	public panel() {
	     chan_bnt.setBounds(250, 30, 100, 50);
	     //chan_bnt.enable(false);
	     
	     le_bnt.setBounds(350, 30, 100, 50);
	    // le_bnt.enable(false);
	     
	     restart.setBounds(550, 30, 100, 50);
	     restart.addActionListener(this);
	     
	     lbnut.setBounds(10, 11, 50, 30);
	     lbnut.setForeground(Color.RED);
	     nut.setBounds(60, 10, 50, 32);
	     nut.enable(false);
	     nut.setForeground(Color.RED);
	
	    // nut.textCol
	    // nut.enable(false);
	     
	     start.setBounds(10,50,100,50);
	    // start.enable(false);
	     

	}
	
	public int matran(int[][] matran, int i, int j) {
		int sonut;
		sonut = matran[i][j];
		return sonut;
	}
	
	public void xukienlacxingau(ActionEvent e) {
		actionPerformed(e);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}


}
