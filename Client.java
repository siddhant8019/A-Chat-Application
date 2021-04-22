import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client extends JFrame implements ActionListener{

	JTextField tf1;
	JPanel p1;
	JTextField t1;
	JButton b1;
	static JPanel a1;
	static JFrame f1 = new JFrame();
	
	static Socket s;
	static DataInputStream din;
    static DataOutputStream dout;
    
	static Box vertical = Box.createVerticalBox();
	Boolean typing;
	
	public Client(){
	f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
	p1 = new JPanel();
	p1.setLayout(null);
	p1.setBackground(new Color(7, 94, 84));
	p1.setBounds(0, 0, 450, 70);
	p1.setVisible(true);
	f1.add(p1);
	
	ImageIcon i1 = new ImageIcon(getClass().getResource("/res/back.png"));
	Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon i3 = new ImageIcon(i2);

    JLabel l1 = new JLabel(i3);
    l1.setVisible(true);
    l1.setBounds(5, 17, 30, 30);
    p1.add(l1);
    
    l1.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
            System.exit(0);
        }
    });
    
    JLabel statusLabel = new JLabel();
    statusLabel.setBounds(100, 150, 240, 30);
    statusLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 28));
    statusLabel.setBackground(new Color(7, 94, 84));
	statusLabel.setForeground(Color.BLACK);
    f1.add(statusLabel);
    
    ImageIcon i4 = new ImageIcon(getClass().getResource("/res/2.png"));
    Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
    Image i0 = i4.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    ImageIcon i6 = new ImageIcon(i5);
    ImageIcon i10 = new ImageIcon(i0);

    JLabel l2 = new JLabel(i6);
    l2.setBounds(40, 5, 60, 60);
    p1.add(l2);
    
    ImageIcon i7 = new ImageIcon(getClass().getResource("/res/video.png"));
    Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon i9 = new ImageIcon(i8);
    JLabel l5 = new JLabel(i9);
    l5.setBounds(290, 20, 30, 30);
    p1.add(l5);
    l5.setVisible(true);
    
    l5.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
        	statusLabel.setVisible(true);
        	statusLabel.setText("Couldn't connect.");
        }
    });
    
    ImageIcon i11 = new ImageIcon(getClass().getResource("/res/phone.png"));
    Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH);
    ImageIcon i13 = new ImageIcon(i12);
    JLabel l6 = new JLabel(i13);
    l6.setBounds(350, 20, 35, 30);
    p1.add(l6);
    l6.setVisible(true);
    
    l6.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
        	statusLabel.setVisible(true);
        	statusLabel.setText("Couldn't connect.");
        }
    });
    
    ImageIcon i14 = new ImageIcon(getClass().getResource("/res/3icon.png"));
    Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_SMOOTH);
    ImageIcon i16 = new ImageIcon(i15);
    JLabel l7 = new JLabel(i16);
    l7.setBounds(410, 20, 13, 25);
    p1.add(l7);
    l7.setVisible(true);
    
    l7.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
        	statusLabel.setVisible(true);
        	statusLabel.setText("Setting.");
        	statusLabel.setSize(240, 32);
        }
    });
    
    ImageIcon i17 = new ImageIcon(getClass().getResource("/res/background.png"));
    Image i18 = i17.getImage().getScaledInstance(450, 750, Image.SCALE_SMOOTH);
    ImageIcon i19 = new ImageIcon(i18);
    JLabel l8 = new JLabel(i19);
    l8.setBounds(0, 0, 450, 750);
    f1.add(l8);
    l8.setVisible(true);
	
	JLabel l3 = new JLabel("Viraj");
	l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
	l3.setForeground(Color.WHITE);
	l3.setBounds(110, 15, 100, 18);
	p1.add(l3);   
	        
	JLabel l4 = new JLabel("Online");
	l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
	l4.setForeground(Color.WHITE);
	l4.setBounds(110, 35, 100, 20);
	p1.add(l4);   
	
	Timer t = new Timer(1, new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			if(!typing){
				l4.setText("Online");
				t1.setText(null);
	            	}
	     	}	
	  });
	    
	 t.setInitialDelay(2000);
	
	 a1 = new JPanel();
	 a1.setBounds(5, 75, 440, 570);
	 a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	 f1.add(a1);
	       
	 t1 = new JTextField();
	 t1.setBounds(5, 655, 310, 40);
	 t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	 f1.add(t1);
	 
	 t1.addKeyListener(new KeyAdapter(){
		 public void keyPressed(KeyEvent ke){
			 l4.setText("typing...");   
			 statusLabel.setText(null);
	         t.stop();
	         typing = true;
	     }
	     
		 public void keyReleased(KeyEvent ke){
			 typing = false;
	            	if(!t.isRunning()){
	            		t.start();
	            	}	
	        	}
	 });
	    
	 b1 = new JButton("Send");
	 b1.setBounds(320, 655, 123, 40);
	 b1.setBackground(new Color(7, 94, 84));
	 b1.setForeground(Color.BLACK);
	 b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	 b1.addActionListener(this);
	 f1.add(b1);
	 
	 f1.getContentPane().setBackground(Color.BLACK);
	 f1.setLayout(null);
	 f1.setSize(450, 700);
	 f1.setLocation(100, 20); 
	 f1.setUndecorated(true);;
	 f1.setVisible(true);
	 
	}
		
	public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
      
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			String out = t1.getText();
			JPanel p2 = formatLabel(out);
			a1.setLayout(new BorderLayout());
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2, BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));	
			a1.add(vertical, BorderLayout.PAGE_START);				
			//a1.add(p2);
			dout.writeUTF(out);
			t1.setText("");
		}catch(Exception e){
			System.out.println("Done");
		}
	}
	public static void main(String[] args) {
		new Client().f1.setVisible(true);
try{
            s = new Socket("127.0.0.1", 8080);
            din  = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            String msginput = "";
            
	    while(true){
                a1.setLayout(new BorderLayout());
	        msginput = din.readUTF();
            	JPanel p2 = formatLabel(msginput);
                JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);
                
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f1.validate();
            }
            
        }catch(Exception e){}
	}

}
