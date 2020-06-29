package dhmt;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class ve {
    public void main(String[]args){
        cuaso m =new cuaso("Chương Trình Vẽ");
        m.setSize(550,500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class cuaso extends JFrame implements ActionListener
{
    JLabel da=new JLabel("Điểm A:");
    JLabel db=new JLabel("Điểm B:");
    JLabel dc=new JLabel("Điểm C:");
    JLabel dd=new JLabel("Điểm D:");
    JTextField txta=new JTextField(5);
    JTextField txtb=new JTextField(5);
    JTextField txtc=new JTextField(5);
    JTextField txtd=new JTextField(5);
    JButton btnVe=new JButton("Vẽ");
    public cuaso(String tt)
{
    super(tt);
    this.setLayout(null);
    btnVe.setBounds(5,5,50,20);this.add(btnVe);btnVe.addActionListener(this);
    btnVe.setToolTipText("Nhấp vào để vẽ");
    
    da.setBounds(60,5,45,20);this.add(da);
    txta.setBounds(105,5,65,20);this.add(txta);
    db.setBounds(175, 5, 45, 20);this.add(db);
    txtb.setBounds(220, 5, 65, 20);this.add(txtb);
    dc.setBounds(290, 5, 65, 20);this.add(dc);
    txtc.setBounds(335, 5, 65, 20);this.add(txtc);
    dd.setBounds(405, 5, 65, 20);this.add(dd);
    txtd.setBounds(450, 5, 65, 20);this.add(txtd);
}
    public void Ve(int xa,int ya,int xb,int yb)
    {
        BasicStroke viet=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND);
        Graphics gg= getGraphics();
        Graphics2D g= (Graphics2D)gg;
        g.setStroke(viet);
        g.setColor(Color.blue);
        g.drawLine(xa,ya,xb,yb);
    }
    public void actionPerformed(ActionEvent e)
    {
        int xa, ya, xb, yb, xc, yc, xd, yd;
        String diemA=txta.getText().toString();
        String[]toadoA=diemA.split("\\,");
        
        String diemB=txtb.getText().toString();
        String[]toadoB=diemB.split("\\,");
        
        String diemC=txtc.getText().toString();
        String[]toadoC=diemC.split("\\,");
        
        String diemD=txtd.getText().toString();
        String[]toadoD=diemD.split("\\,");
        
        xa=Integer.parseInt(toadoA[0]);
        ya=Integer.parseInt(toadoA[1]);
        
        xb=Integer.parseInt(toadoB[0]);
        yb=Integer.parseInt(toadoB[1]);
        
        xc=Integer.parseInt(toadoC[0]);
        yc=Integer.parseInt(toadoC[1]);
        
        xd=Integer.parseInt(toadoD[0]);
        yd=Integer.parseInt(toadoD[1]);
        
        Ve(xa,ya,xb,yc);
        Ve(xb,yb,xc,yc);
        Ve(xc,yc,xd,yd);
        Ve(xd,yd,xa,ya);
    }
}


