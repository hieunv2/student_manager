/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import ConnectService.sqlConnect;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import subComponent.MenuItemPane;

/**
 *
 * @author 84969
 */
public class MainComponent extends JPanel {

    private static final long serialVersionUID = 1L;

    private ArrayList<MenuItem> menuItems;
    private JPanel cards;
    private JPanel menuPane;

    private UserPanel userPanel;
    private StudentPanel studentPanel;
    private SubjectPanel subjectPanel;
    private ScorePanel scorePanel;

    public UserPanel getUserPanel() {
        return this.userPanel;
    }

    public StudentPanel getStudentPanel() {
        return this.studentPanel;
    }

    public SubjectPanel getSubjectPanel() {
        return this.subjectPanel;
    }

    public ScorePanel getScorePanel() {
        return this.scorePanel;
    }

    public MainComponent() {
        this.setLayout(new BorderLayout(10, 10));
        menuItems = new ArrayList<>();

        final ImageIcon userIcon = new ImageIcon(MainComponent.class.getResource("/image/user.png"));
        final ImageIcon studentIcon = new ImageIcon(MainComponent.class.getResource("/image/student.png"));
        final ImageIcon subjectIcon = new ImageIcon(MainComponent.class.getResource("/image/subject.png"));

        cards = new JPanel();
        cards.setLayout(new CardLayout());
        cards.setBorder(new EmptyBorder(20, 0, 10, 20));
        this.add(cards, BorderLayout.CENTER);

        if (sqlConnect.user.getRole().equals("User")) {
            scorePanel = new ScorePanel();
            cards.add(scorePanel);
        } else {
            menuPane = new JPanel();
            menuPane.setBorder(new EmptyBorder(20, 0, 0, 0));
            menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.Y_AXIS));
            menuPane.setBackground(new Color(48, 65, 86));
            this.add(menuPane, BorderLayout.WEST);

            menuPane.add(Box.createRigidArea(new Dimension(0, 20)));

            MenuItemPane payPane = new MenuItemPane();
            payPane.setLayout(new GridLayout());

            if(sqlConnect.user.getRole().equals("Manager")){
                   userPanel = new UserPanel();
            cards.add(userPanel, "user");
            new MenuItem("Tài khoản", "user", userIcon, userPanel);
            }

            studentPanel = new StudentPanel();
            cards.add(studentPanel, "student");
            new MenuItem("Sinh Viên", "student", studentIcon, studentPanel);

            subjectPanel = new SubjectPanel();
            cards.add(subjectPanel, "subject");
            new MenuItem("Môn học", "subject", subjectIcon, subjectPanel);
        }

    }

    private class MenuItem extends JPanel {

        private static final long serialVersionUID = 1L;

        private boolean isOpen;
        private String key;
        private MenuItemPane panel;

        public MenuItem(String text, String key, ImageIcon icon, MenuItemPane panel) {

            this.panel = panel;
            this.isOpen = false;
            this.key = key;

            setPreferredSize(new Dimension(250, 70));
            setMaximumSize(new Dimension(250, 70));
            setMinimumSize(new Dimension(250, 70));

            setBackground(new Color(48, 65, 86));
            setLayout(new BorderLayout());
            JLabel label = new JLabel(text);
            label.setIcon(icon);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBorder(new EmptyBorder(0, 10, 0, 0));
            label.setFont(new Font("Calibri", Font.PLAIN, 35));
            label.setForeground(new Color(195, 210, 221));
            this.add(label, BorderLayout.CENTER);

            menuPane.add(this);
            menuItems.add(this);

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    setOpenned();
                }
            });
        }

        public void setOpenned() {

            for (MenuItem menuItem : menuItems) {
                if (menuItem.isOpen) {
                    menuItem.setUnOpenned();
                    break;
                }
            }

            this.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(114, 137, 218)));
            this.setBackground(new Color(38, 52, 69));
            this.isOpen = true;

            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, key);
            panel.setOpenned();
        }

        public void setUnOpenned() {
            this.setBorder(null);
            this.setBackground(new Color(48, 65, 86));
            this.isOpen = false;
        }
    }
}
