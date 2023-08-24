package com.ElectricityBillingSystem;
import javax.swing.*;
import java.sql.*;
import java.awt.*; // Contains Color.WHITE 
import java.awt.event.*;


//To capture click event on the JLabel buttons we are using (on-click event)
//implements ActionListener 
public class Login extends JFrame implements ActionListener{
	
	//Login() is a constructor
	JButton log,cancel,signup; //Globally declaring variables
	JTextField username,password;
	Choice login;
	Login() {
		super("Login Page"); //Title for the App. Super must be the first line inside the constructor
		getContentPane().setBackground(Color.WHITE); //getContentPane function gives access of frame
		setLayout(null); //manages the layour design of frame without using swing
		
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(300,20,100,20); // 300,20 are location parameters of lblusername wrt frame. 100 and 20 are height and width of text
		add(lblusername);
		
		//Input field
		username = new JTextField();
		username.setBounds(400,20,150,20);
		add(username);
		
		JLabel lblpass = new JLabel("Password");
		lblpass.setBounds(300,60,100,20); // 300,20 are location parameters of lblpass wrt frame. 100 and 20 are height and width of text
		add(lblpass);

		//Input field for password
		 password = new JTextField();
		password.setBounds(400,60,150,20);
		add(password);
		
		JLabel loginas = new JLabel("Log in as");
		loginas.setBounds(300,100,100,20); // 300,20 are location parameters of lblpass wrt frame. 100 and 20 are height and width of text
		add(loginas);
		
		/* Drop down using 2 classes either by choice or JCom Box
		Choice class comes under jwt package. JCom is in swing*/
		 login = new Choice();
		// To add choices, we need to use add function
		login.add("Admin");
		login.add("Customer");
		login.setBounds(400,100,150,20);
		add(login);
		
		ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
		
		//Adding buttons using JFrame
		Image image2 = image1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
		//We cannot directly pass Image image2, we create a class with name:ImageIcon()
		log = new JButton("Login", new ImageIcon(image2));
		log.setBounds(330,160,100,20);
		log.addActionListener(this);
		add(log);
		
		ImageIcon image3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
		Image image4 = image3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
		//We cannot directly pass Image image2, we create a class with name:ImageIcon()
		cancel = new JButton("Cancel", new ImageIcon(image4));
		cancel.setBounds(450,160,100,20);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon image5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
		Image image6 = image5.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
		//We cannot directly pass Image image2, we create a class with name:ImageIcon()
		signup = new JButton("Signup", new ImageIcon(image6));
		signup.setBounds(380,200,100,20);
		signup.addActionListener(this);
		add(signup);		
		
		ImageIcon image7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
		Image image8 = image7.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
		ImageIcon image9 = new ImageIcon(image8);
		JLabel man = new JLabel(image9);
		man.setBounds(0,0,250,250);
		add(man);
		
		setSize(640,300); //Setting the Size of the frame
		setLocation(400,200); // Setting the height and width of the frame
		setVisible(true); // Making sure the frame is visible
	}
	//Override and on click event 
	public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == log) {
				String susername = username.getText();
				String spassword = password.getText();
				String suser = login.getSelectedItem();
				
				try {
					Conn c = new Conn();
					String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and  user = '"+suser+"'";
					
					ResultSet rs = c.s.executeQuery(query); //get data from above command
					
					if(rs.next()) {
						String meter = rs.getString("meter_no");
						setVisible(false);
						new Project(suser, meter);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Login");
						username.setText("");
						password.setText("");
					
					}
					
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if(ae.getSource() == cancel) {
				setVisible(false);
			} else if(ae.getSource() == signup) {
				setVisible(false);
				
				new Signup();
			}
	}
	
	
	public static void main(String args[]) {
		new Login();
	}
}
