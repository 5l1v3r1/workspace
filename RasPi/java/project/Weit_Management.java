import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Weit_Management extends JFrame implements ActionListener{
    
    // Variable of items 
    private JPanel contentPane;
    private JLabel name;
    private JLabel subject;
    private JLabel classname;
    private JLabel state;
    
    // Variable of menu items
    private JMenuItem regit_Stu;
    private JMenuItem regit_Inst;
    private JMenuItem del;
    
    // Variable of TCP communication
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
    
    class ClosingHandler extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            System.out.println("Exit");
            System.exit(0);
        }
    }
    /**
     * Create the frame.
     */
    public Weit_Management() {
        // Create TCP_Accept instance, start thread
        accept = new TCP_Accept(this);
        Thread th = new Thread(accept);
        th.start();
        
        // Setting about this frame
        setTitle("WE-IT Academy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 290, 266);
        setResizable(false);
        addWindowListener(new ClosingHandler());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Add Regiter Menu
        JMenu mnRegister = new JMenu("등록");
        mnRegister.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnRegister);
        regit_Stu = new JMenuItem("수강생");
        mnRegister.add(regit_Stu);
        regit_Stu.setHorizontalAlignment(SwingConstants.CENTER);
        regit_Inst = new JMenuItem("강사");
        mnRegister.add(regit_Inst);
        regit_Inst.setHorizontalAlignment(SwingConstants.CENTER);
        regit_Inst.addActionListener(this);
        regit_Stu.addActionListener(this);
        
        // Add Delete Menu
        JMenu mnDelete = new JMenu("삭제");
        mnDelete.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnDelete);
        del = new JMenuItem("삭제");
        del.setHorizontalAlignment(SwingConstants.CENTER);
        del.addActionListener(this);
        mnDelete.add(del);
        
        // Setting about main panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblWeitManagement = new JLabel("우리IT 출결프로그램");
        lblWeitManagement.setFont(new Font("돋움", Font.BOLD, 16));
        lblWeitManagement.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeitManagement.setBounds(12, 10, 250, 35);
        contentPane.add(lblWeitManagement);
        
        // Setting about items
        JLabel lblName = new JLabel("이름");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(12, 55, 68, 23);
        contentPane.add(lblName);
        
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
        Object event = e.getSource();
        if(event == regit_Stu){
            new Weit_Register_Student(accept);
        }
        else if(event == regit_Inst){
            new Weit_Register_Instructor(accept);
        }
        else if(event == del){
            accept.State_Delete();
            JOptionPane.showMessageDialog(null,"삭제할 카드를 리더기에 체크하십시오.","Check Card",JOptionPane.INFORMATION_MESSAGE);
            this.setValue("", "", "", "삭제할 카드를 리더기에 체크하십시오.");
        }
    }
    
    public void setValue(String name, String subject, String classname, String state){
        this.name.setText(name);
        this.subject.setText(subject);
        this.classname.setText(classname);
        this.state.setText(state);
    }
}
