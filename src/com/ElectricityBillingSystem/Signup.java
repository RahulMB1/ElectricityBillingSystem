package com.ElectricityBillingSystem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*; //contains background colors etc
import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

//To capture click event on the JLabel buttons we are using (on-click event)
//implements ActionListener 
public class Signup extends JFrame implements ActionListener{
	//Globally declaring variables
	JButton create,back;
	Choice accounttype;
	JTextField meter, lblusernamee,lblpasswordd,lblnamee;
	Signup() {
		setSize(700,400);
		setLocation(450,150); //or we can use setBounds(450,150,700,400); But make sure it is not getting called somewhere else
		getContentPane().setBackground(Color.white);
		setLayout(null); 
		
		//Using Panel. Panel is just like frame divided into multiple parts 
		JPanel panel = new JPanel();
		panel.setBounds(30,30,650,300);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172,216,230)));
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.setForeground(new Color(34,139,34));
		add(panel);
		
		JLabel heading = new JLabel("Create Account as");
		heading.setBounds(100,50,140,20);
		heading.setForeground(Color.gray);
		heading.setFont(new Font("Tahoma",Font.BOLD, 14));
		panel.add(heading);
		
		JLabel meterr = new JLabel("Meter Number");
		meterr.setBounds(100,90,140,20);
		meterr.setForeground(Color.gray);
		meterr.setFont(new Font("Tahoma",Font.BOLD, 14));
		meterr.setVisible(false);
		panel.add(meterr);
		
		meter = new JTextField();
		meter.setBounds(260,90,150,20);
		meter.setVisible(false);
		panel.add(meter);
		
		
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(100,130,140,20);
		lblusername.setForeground(Color.gray);
		lblusername.setFont(new Font("Tahoma",Font.BOLD, 14));
		panel.add(lblusername);
		
		lblusernamee = new JTextField();
		lblusernamee.setBounds(260,130,150,20);
		panel.add(lblusernamee);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(100,170,140,20);
		lblname.setForeground(Color.gray);
		lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
		panel.add(lblname);
		
		lblnamee = new JTextField();
		lblnamee.setBounds(260,170,150,20);
		panel.add(lblnamee);
		
		meter.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {}
			
			@Override
			public void focusLost(FocusEvent fe) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
					while(rs.next()) {
						lblnamee.setText(rs.getString("name"));
						
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(100,210,140,20);
		lblpassword.setForeground(Color.gray);
		lblpassword.setFont(new Font("Tahoma",Font.BOLD, 14));
		panel.add(lblpassword);
		
		lblpasswordd = new JTextField();
		lblpasswordd.setBounds(260,210,150,20);
		panel.add(lblpasswordd);
		
		
		
		create = new JButton("Create");
		create.setBackground(Color.black);
		create.setForeground(Color.white);
		create.setBounds(140,260,120,25);
		create.addActionListener(this);
		panel.add(create);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(300,260,120,25);
		back.addActionListener(this);
		panel.add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
		Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(410,30,250,250);
		panel.add(image);
		
		accounttype = new Choice();
		accounttype.add("Admin");
		accounttype.add("Customer");
		accounttype.setBounds(260,50,150,20);
		panel.add(accounttype);
		
		accounttype.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent ae) {
		        String user = accounttype.getSelectedItem();
		        if (user.equals("Customer")) {
		            meterr.setVisible(true);
		            meter.setVisible(true);
		            lblnamee.setEditable(false);
		        } else {
		            meterr.setVisible(false);
		            meter.setVisible(false);
		            lblnamee.setEditable(true);
		        }
		    }
		});
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == create) {
			String atype = accounttype.getSelectedItem(); //Use this function to get accountype from the Choice accountype which returns a String
			String susername = lblusernamee.getText();
			String sname = lblnamee.getText();
			String spassword = lblpasswordd.getText();
			String smeter = meter.getText();
			//We need to create a hit in MySql so we use try and catch. 
			//Since mysql is an external entity there are chances of getting exceptions
		try {
			//Making conection with database
			Conn c = new Conn(); //Creating a variable by the name of Conn() class. we made Conn class to establish connection b/w code and database
			//Creating a query
			
			//Insering all 5 parameters into this query as VARIABLES
			String query = null; 
			if(atype.equals("Admin")) {
				query = "insert into login values('" + smeter +"','" + susername+"','"+ sname +"', '"+ spassword +"', '"+ atype +"' )";
			//varchar takes input in the form of Strings
			} else {
				query = "update login set username = '"+susername+"', password ='"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
			}
				c.s.executeUpdate(query); //Update of query
			
			JOptionPane.showMessageDialog(null, "Account created successfully ");
			
			setVisible(false);
			new Login(); //login frame is called
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		} else if(ae.getSource() == back) {
			setVisible(false);
			
			new Login();
		}
	}
	
	public static void main(String[] args) {
		new Signup();
	}
}
