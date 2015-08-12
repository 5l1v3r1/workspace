import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Weit_Register_Instructor extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JButton register;
    private JTextField name;
    private JTextField department;
    private JTextField phone;
    private TCP_Accept accept;

    /**
     * Create the frame.
     */
    public Weit_Register_Instructor(TCP_Accept accept) {
        this.accept = accept;
        
        setTitle("WE-IT Academy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 290, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblWeitManagement = new JLabel("강사 등록");
        lblWeitManagement.setFont(new Font("돋움", Font.BOLD, 16));
        lblWeitManagement.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeitManagement.setBounds(12, 10, 250, 35);
        contentPane.add(lblWeitManagement);
        
        JLabel lblName = new JLabel("이름");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(12, 55, 82, 23);
        contentPane.add(lblName);
        
        register = new JButton("등록");
        register.setBounds(171, 218, 97, 33);
        register.addActionListener(this);
        contentPane.add(register);
        
        JLabel lblDepartment = new JLabel("소속");
        lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartment.setBounds(12, 88, 82, 23);
        contentPane.add(lblDepartment);
        
        JLabel lblPhone = new JLabel("본인연락처");
        lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhone.setBounds(12, 121, 82, 23);
        contentPane.add(lblPhone);
        
        name = new JTextField("");
        name.setBounds(102, 57, 160, 19);
        contentPane.add(name);
        
        department = new JTextField("");
        department.setBounds(102, 90, 160, 19);
        contentPane.add(department);
        
        phone = new JTextField("");
        phone.setBounds(102, 123, 160, 19);
        contentPane.add(phone);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == register){
            if(!(name.getText().equals("")||department.getText().equals("")||phone.getText().equals(""))){
                Register();
            }
        }
    }
    
    public void Register(){
        accept.State_Register();
        while(accept.Current_State()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        Person instructor = new Person(name.getText(), department.getText(), phone.getText());
        if(accept.Instructor_Register(instructor)){
            JOptionPane.showMessageDialog(null, "정상등록 되었습니다.","등록 성공", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }
}
