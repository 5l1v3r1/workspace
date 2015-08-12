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

public class Weit_Register_Student extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JButton register;
    private JTextField name;
    private JTextField subject;
    private JTextField classname;
    private JTextField phone;
    private JTextField parent;
    private TCP_Accept accept;

    /**
     * Create the frame.
     */
    public Weit_Register_Student(TCP_Accept accept) {
        this.accept = accept;
        
        setTitle("WE-IT Academy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 290, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblWeitManagement = new JLabel("������ ���");
        lblWeitManagement.setFont(new Font("����", Font.BOLD, 16));
        lblWeitManagement.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeitManagement.setBounds(12, 10, 250, 35);
        contentPane.add(lblWeitManagement);
        
        JLabel lblName = new JLabel("�̸�");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(12, 55, 82, 23);
        contentPane.add(lblName);
        
        register = new JButton("���");
        register.setBounds(171, 218, 97, 33);
        register.addActionListener(this);
        contentPane.add(register);
        
        JLabel lblSubject = new JLabel("����");
        lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubject.setBounds(12, 88, 82, 23);
        contentPane.add(lblSubject);
        
        JLabel lblClass = new JLabel("���ǽ�");
        lblClass.setHorizontalAlignment(SwingConstants.CENTER);
        lblClass.setBounds(12, 121, 82, 23);
        contentPane.add(lblClass);
        
        JLabel lblPhone = new JLabel("���ο���ó");
        lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhone.setBounds(12, 154, 82, 23);
        contentPane.add(lblPhone);
        
        JLabel lblParent = new JLabel("��ȣ�ڿ���ó");
        lblParent.setHorizontalAlignment(SwingConstants.CENTER);
        lblParent.setBounds(12, 187, 82, 23);
        contentPane.add(lblParent);
        
        name = new JTextField("");
        name.setBounds(102, 57, 160, 19);
        contentPane.add(name);
        
        subject = new JTextField("");
        subject.setBounds(102, 90, 160, 19);
        contentPane.add(subject);
        
        classname = new JTextField("");
        classname.setBounds(102, 123, 160, 19);
        contentPane.add(classname);
        
        phone = new JTextField("");
        phone.setBounds(102, 156, 160, 19);
        contentPane.add(phone);
        
        parent = new JTextField("");
        parent.setBounds(102, 189, 160, 19);
        contentPane.add(parent);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == register){
            if(!(name.getText().equals("")||subject.getText().equals("")||classname.getText().equals("")||phone.getText().equals("")||parent.getText().equals(""))){
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
        Person stu = new Person(name.getText(), subject.getText(), classname.getText(), phone.getText(), parent.getText());
        if(accept.Student_Register(stu)){
            JOptionPane.showMessageDialog(null, "������ �Ǿ����ϴ�.","��� ����", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }
}
