package Component;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javafx.scene.layout.Border;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class LoginComponent extends JPanel{
        private final JTextField userField;
	private final JPasswordField passField;
	private final JButton loginButton;
	private final JButton forgotButton;
	
	public JTextField getUserField() {
		return this.userField;
	}
        public JPasswordField getPassField() {
		return this.passField;
	}
	
	public JButton getLoginButton() {
		return this.loginButton;
	}
	
	public JButton getForgotButton() {
		return this.forgotButton;
	}

    public LoginComponent() {
        setLayout(null);
        setBackground(Color.WHITE);
        JLabel lb1 = new JLabel("CHÀO MỪNG BẠN!");
        lb1.setForeground(Color.BLACK);
        lb1.setFont(new Font("Calibri", Font.PLAIN, 35));
	lb1.setBounds(70, 28, 600, 44);
	lb1.setHorizontalAlignment(SwingConstants.CENTER);
        add(lb1);
        
        JLabel lb2 = new JLabel("Đăng nhập ngay");
        lb2.setForeground(new Color(240, 138, 29));
        lb2.setFont(new Font("Calibri", Font.PLAIN, 20));
	lb2.setBounds(125, 74, 524, 26);
	lb2.setHorizontalAlignment(SwingConstants.CENTER);
        add(lb2);
        
        JLabel user_Label = new JLabel("TÊN ĐĂNG NHẬP");
	user_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
	user_Label.setForeground(Color.BLACK);
	user_Label.setBounds(125, 155, 200, 19);
        add(user_Label);
		
	JLabel pass_Label = new JLabel("MẬT KHẨU");
	pass_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
	pass_Label.setForeground(Color.BLACK);
	pass_Label.setBounds(125, 255, 200, 19);
	add(pass_Label);
        
        
        JPanel userPanel = new JPanel();
		userPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		userPanel.setBounds(125, 183, 449, 44);
		add(userPanel);
		
		userPanel.setLayout(new BorderLayout(0, 0));
	userField = new JTextField();
	userField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				userPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(114, 137, 218)));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				userPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
		});
	userField.setFont(new Font("Calibri", Font.BOLD, 20));
	userField.setBorder(new EmptyBorder(0, 15, 0, 0));
	userField.setCaretColor(new Color(114, 137, 218));
	userField.setForeground(Color.WHITE);
	userField.setBackground(new Color(48, 51, 57));
	userPanel.add(userField);
	userField.setColumns(10);
		
	JPanel passPanel = new JPanel();
	passPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
	passPanel.setBounds(125, 287, 449, 44);
	passPanel.setLayout(new BorderLayout(0, 0));
	add(passPanel);
		
	passField = new JPasswordField();
	passField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				passPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(114, 137, 218)));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				passPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
		});
	passField.setFont(new Font("Calibri", Font.BOLD, 20));
	passField.setBorder(new EmptyBorder(0, 15, 0, 0));
	passField.setCaretColor(new Color(114, 137, 218));
	passField.setForeground(Color.WHITE);
	passField.setBackground(new Color(48, 51, 57));
	passPanel.add(passField);
        loginButton = new JButton("ĐĂNG NHẬP");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        loginButton.setBounds(255, 403, 200, 49);
        add(loginButton);
        
        forgotButton = new JButton("Quên mật khẩu?");
	forgotButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	forgotButton.setBorderPainted(false);
	forgotButton.setBorder(new EmptyBorder(0, 0, 0, 0));
	forgotButton.setContentAreaFilled(false);
	forgotButton.setOpaque(false);
	forgotButton.setForeground(new Color(115, 107, 218));
	forgotButton.setBounds(275, 350, 163, 21);
	add(forgotButton);
    }      

    }

