import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Weit_Management extends JFrame implements ActionListener{

	public static final int STATE_REGIT = 1011;
	public static final int STATE_READ = 2022;

	private JPanel contentPane;
	private JButton register;
	private JLabel name;
	private JLabel subject;
	private JLabel classname;
	private JLabel state;
	
	private TCP_Accept accept;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Weit_Management frame = new Weit_Management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Weit_Management() {
		accept = new TCP_Accept(this);
		
		setTitle("WE-IT Academy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWeitManagement = new JLabel("우리IT 출결프로그램");
		lblWeitManagement.setFont(new Font("돋움", Font.BOLD, 16));
		lblWeitManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeitManagement.setBounds(12, 10, 250, 35);
		contentPane.add(lblWeitManagement);
		
		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(12, 55, 68, 23);
		contentPane.add(lblName);
		
		register = new JButton("등록");
		register.setBounds(165, 187, 97, 33);
		contentPane.add(register);
		
		JLabel lblSubject = new JLabel("과목");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setBounds(12, 88, 68, 23);
		contentPane.add(lblSubject);
		
		JLabel lblClass = new JLabel("반");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setBounds(12, 121, 68, 23);
		contentPane.add(lblClass);
		
		name = new JLabel("");
		name.setBounds(92, 57, 170, 19);
		contentPane.add(name);
		
		subject = new JLabel("");
		subject.setBounds(92, 90, 170, 19);
		contentPane.add(subject);
		
		classname = new JLabel("");
		classname.setBounds(92, 123, 170, 19);
		contentPane.add(classname);
		
		state = new JLabel("");
		state.setBounds(22, 158, 240, 19);
		contentPane.add(state);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == register){
			new Weit_Register(accept);
		}
	}
	
	public void setValue(String name, String subject, String classname, String state){
		this.name.setText(name);
		this.subject.setText(subject);
		this.classname.setText(classname);
		this.state.setText(state);
	}
}
