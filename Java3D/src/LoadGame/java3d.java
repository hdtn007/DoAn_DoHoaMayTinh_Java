package LoadGame;

import java.awt.image.BufferStrategy;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.Random;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import javafx.scene.layout.BackgroundImage;
import jdk.nashorn.internal.ir.WithNode;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.loaders.Scene;

import com.sun.j3d.loaders.objectfile.ObjectFile;

import java.io.*;

import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class java3d extends Applet implements KeyListener,ActionListener {
	
	private static int Y = 3; // vị trí y mặt định của ma trận 
	private static int X = 3; // vị trí x mặt định của ma trận 
	
	private SimpleUniverse universe = null;
	 private Canvas3D canvas = null;
	 private TransformGroup viewtrans = null;

	 private TransformGroup tg = null;
	 private Transform3D t3d = null;
	 private Transform3D t3dstep = new Transform3D();
	 private Matrix4d matrix = new Matrix4d();
	 
	 panel p = new panel();  // gọi class panel
	 static Displays dis = new Displays();
	 

	 
	 private static Image Pic;
	 private static Image img;
	 
/* -------------------------------- Start hàm khởi tạo ------------------------------------------ */
	 public java3d() {
		 
		 ImageIcon obj = new ImageIcon("model/dice.png");  // ảnh icon
		 img = Toolkit.getDefaultToolkit().createImage("model/matban.jpg"); // ảnh background
		 Pic = obj.getImage();
		 
	
		 
		 
		 
		// this.add(p.chan_bnt);
		// this.add(p.le_bnt);
		 
		 this.add(p.lbnut);
		 this.add(p.nut);
		 
		 //this.add(p.start);
		// this.add(p.restart);
		 		 
			 
	  setLayout(new BorderLayout());
	  GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

	  canvas = new Canvas3D(config);
	  add("Center", canvas);
	  universe = new SimpleUniverse(canvas);

	  BranchGroup scene = createSceneGraph();
	  universe.getViewingPlatform().setNominalViewingTransform();

	  universe.getViewer().getView().setBackClipDistance(100.0);

	  canvas.addKeyListener(this);

	  universe.addBranchGraph(scene);
	  
	 }
	 
/* -------------------------------- End hàm khởi tạo ------------------------------------------ */
	 
/* -------------------------------- hàm khởi tạo ------------------------------------------ */

	 private BranchGroup createSceneGraph() {
	  BranchGroup objRoot = new BranchGroup();

	  BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

	  viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

	  KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
	  keyNavBeh.setSchedulingBounds(bounds);
	  PlatformGeometry platformGeom = new PlatformGeometry();
	  platformGeom.addChild(keyNavBeh);
	  universe.getViewingPlatform().setPlatformGeometry(platformGeom);

	  objRoot.addChild(TaoXiNgau());  // thêm xí ngầu
	  
	  
	  Background background = new Background();
	  background = dis.background;
	  
	  background.setImageScaleMode(2); // tỷ lệ ảnh
	  dis.initBg(img); // gọi hàm background từ class display
	 
	  
	  
	  objRoot.addChild(background);  // thêm background


	  return objRoot;
	 }
	 


	 private BranchGroup TaoXiNgau() {

	  BranchGroup objRoot = new BranchGroup();
	  tg = new TransformGroup();
	  t3d = new Transform3D();

	  tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	  t3d.setTranslation(new Vector3d(-0.15, -1.3, -10.0)); // (tọa độ  X, tọa độ Y ,Độ to nhỏ của đối tượng 3d)
	  t3d.setRotation(new AxisAngle4f(0.0f, 0.0f, 0.0f, 0.0f));
	  t3d.setScale(1.0); // Tỷ lệ 1:1 - Tỷ lệ của đối tượng

	  tg.setTransform(t3d);

	  ObjectFile loader = new ObjectFile(ObjectFile.RESIZE);
	  Scene s = null;

	  File file = new java.io.File("model/dice_obj/dice.obj");

	  try {
	   s = loader.load(file.toURI().toURL());
	  } catch (Exception e) {
	   System.err.println(e);
	   System.exit(1);
	  }

	  tg.addChild(s.getSceneGroup());

	  objRoot.addChild(tg);
	  objRoot.addChild(createLight());

	  objRoot.compile();
	 

	  return objRoot;

	 }
	 


	 private Light createLight() { // tạo ánh sáng
	  DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f,
	    1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));

	  light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

	  return light;
	 }
	 
/* -------------------------------- Start Main Function ------------------------------------------ */
	 public static void main(String[] args) {
		 	 
	  java3d applet = new java3d();
	  Frame frame = new MainFrame(applet, 800, 600);
	  
	  frame.add(dis);
	  
	  frame.setTitle("lắc xí ngầu");
	  
	  
	  frame.setIconImage(Pic);
	  

	  applet.setVisible(true);
	 }
/* -------------------------------- End Main Function ------------------------------------------ */
	 
/* -------------------------------- Start setup kiểu xoay ------------------------------------------ */
	 
	public void ThuNho() {
		t3dstep.set(new Vector3d(0.0, 0.0, -0.1));
		tg.getTransform(t3d);
		t3d.mul(t3dstep);
		tg.setTransform(t3d);
	}
	
	public void DiLen() {
		   
		   for(int i=0; i<=3;i++) { // xoay lên 1 mặt  
				  try {
					Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   t3dstep.set(new Vector3d(0.0, 0.1, 0.0));
				   tg.getTransform(t3d);
				   t3d.mul(t3dstep);
				   tg.setTransform(t3d);
			  }
	}
	
	public void DiXuong() {
		
		   for(int i=0; i<=3;i++) { // xoay lên 1 mặt  
				  try {
					Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   t3dstep.set(new Vector3d(0.0, -0.1, 0.0));
				   tg.getTransform(t3d);
				   t3d.mul(t3dstep);
				   tg.setTransform(t3d);
			  }
	}
	
	public void XoayPhai() {
		
				for(int i=0; i<=15;i++) { 
					  try {
						Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
		
					   t3dstep.rotY(Math.PI / 32);
					   tg.getTransform(t3d);
					   t3d.get(matrix);
					   t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
					   t3d.mul(t3dstep);
					   t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
					   tg.setTransform(t3d);
				  }
				if((Y==1)||(Y==5)) {
					X = X + 1;
					  if(X>6) {
						  X=3;
					  }
				}
				else if ((Y==0)||(Y==2)||(Y==4)||(Y==6)) {
					X=X+0;
				}
				else {
					X = X - 1;
					  if(X<0) {
						  X=3;
					  }	
				}
		
	}
	
	public void XoayTrai() {
		
				for(int i=0; i<=15;i++) { 
					  try {
						Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
					   t3dstep.rotY(-Math.PI / 32);
					   tg.getTransform(t3d);
					   t3d.get(matrix);
					   t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
					   t3d.mul(t3dstep);
					   t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
					   tg.setTransform(t3d);
				  }
				if((Y==1)||(Y==5)) {
					X = X - 1;
					  if(X<0) {
						  X=3;
					  }
				}
				else if ((Y==0)||(Y==2)||(Y==4)||(Y==6)) {
					X=X+0;
				}
				else {
					X = X + 1;
					  if(X>6) {
						  X=3;
					  }	
				}
		
	}
	
	public void XoayXuong() {
		for(int i=0; i<=15;i++) { // xoay xuống 1 mặt  
			  try {
				Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   t3dstep.rotX(Math.PI / 32);
			   tg.getTransform(t3d);
			   t3d.get(matrix);
			   t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			   t3d.mul(t3dstep);
			   t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			   tg.setTransform(t3d);
	  }
		if((X==1)||(X==5)) {
			Y = Y + 1;
			  if(Y>6) {
				  Y=3;
			  }
		}
		else if ((X==0)||(X==2)||(X==4)||(X==6)) {
			Y=Y+0;
		}
		else {
			Y = Y - 1;
			  if(Y<0) {
				  Y=3;
			  }
		}
	}
	
	
	public void XoayLen() {
		  for(int i=0; i<=15;i++) { // xoay lên 1 mặt  
			  try {
				Thread.sleep(20);  // tốc độ xoay nhanh chậm của xí ngầu
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   t3dstep.rotX(-Math.PI / 32);
			   tg.getTransform(t3d);
			   t3d.get(matrix);
			   t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
			   t3d.mul(t3dstep);
			   t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
			   tg.setTransform(t3d);
		  }
		  
		  if((X==1)||(X==5)) {
			  Y = Y - 1;
			  if(Y<0) {
				  Y=3;
			  }
		  }
		  else if ((X==0)||(X==2)||(X==4)||(X==6)) {
				Y=Y+0;
			}
		  else {
			  Y = Y + 1;
			  if(Y>6) {
				  Y=3;
			  }
		  }
	}
/* -------------------------------- End setup kiểu xoay ------------------------------------------ */	 

	 
/* -------------------------------- Start setup event keyboard ------------------------------------------ */

	 public void keyTyped(KeyEvent e) {
	 char key = e.getKeyChar();

	  if (key == 'e') {  // thu nhỏ xí ngầu  
		  ThuNho();
	  }

	  if (key == 'd') {// xoay ngang qua phải 1 mặt
		  XoayPhai();
	  }

	  if (key == 'a') { // xoay ngang qua trái 1 mặt
		  XoayTrai();
	  }

	  if (key == 's') {
		  XoayXuong();
	  }

	  if (key == 'w') {
		  XoayLen();
	  }

	  if (key == '8') { // di chuyển lên trên
		  DiLen();
	  }

	  if (key == '2') { // di chuyển xuống dưới
		  DiXuong();
	  }
	 }
	 
/* -------------------------------- End setup event keyboard ------------------------------------------ */

	 public void keyReleased(KeyEvent e) {
		 
		 char key = e.getKeyChar();
		 

		 
		if (key == ' ') { // nút lắc xí ngầu

			 
			  for(int i = 0; i<=SoNut();i++) {
				  XoayLen();}
			  for(int i = 0; i<=SoNut();i++) {
				  XoayTrai();}
			  for(int i = 0; i<=SoNut();i++) {
				  XoayXuong();}
			  for(int i = 0; i<=SoNut();i++) {
				  XoayPhai();}
		}
		
		p.nut.setText(String.valueOf(p.matran(p.matran, Y, X)/* + ",Y="+Y+",X ="+X*/)); // Xuất số nút ra màn hình
		 
		 
	 }

	 public void keyPressed(KeyEvent e) {
		 
	 }
	 
	 /* random */
	 
	 private Random rdRandom = new Random(); //  ngẩu nhiên số lần xoay
	 
	 private int SoNut() {
		 int num = rdRandom.nextInt(3); // ngẩu nhiên từ 0..3
		 return num;
	 }


	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
	 
	 

}
