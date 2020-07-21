package LoadGame;


import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ImageComponent2D;
import javax.swing.JPanel;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.image.TextureLoader;



public class Displays extends Applet {
	
	Background background = new Background();
	
	   
	   public Displays() {
		   
	   }
	   
	   public void initBg(Image img)
	    {  
		 //background.setColor(0.75f, 0.69f, 0.680f); //  background màu 
		   
		  	/*  background image */
		    
		   
		      BoundingSphere backgroundBounds = new BoundingSphere(new Point3d(0,0,0), 1000);
		      TextureLoader myLoader = new  TextureLoader(img,this);
		      ImageComponent2D myImage = myLoader.getImage( );
		      background.setImage(myImage);
		      background.setApplicationBounds(backgroundBounds); // dùng cho bg là ảnh
		      
		   // background.setApplicationBounds(bounds); // dùng cho bg màu
	    }  

}
