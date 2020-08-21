/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Component.MainComponent;
import Data.ScoreData;
import Data.StudentData;
import Data.SubjectData;
import Data.UserData;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 84969
 */
public class MainController {

    private MainComponent mainPanel;
    private JFrame frame;
    private JPanel contentPanel;

    public MainController(MainComponent mainPanel, JFrame frame, JPanel contentPanel) {
        this.mainPanel = mainPanel;
        this.frame = frame;
        this.contentPanel = contentPanel;
    }

    public void setEvent() {
        UserData userData = new UserData();
        userData.getData();

        UserController userController = new UserController(mainPanel.getUserPanel(), userData);
        if (mainPanel.getUserPanel() != null) {
            userController.setViewAndEvent();
        }

        StudentData studentData = new StudentData();
        studentData.getData();

        StudentController studentController = new StudentController(mainPanel.getStudentPanel(), studentData);
        if (mainPanel.getStudentPanel() != null) {
            studentController.setViewAndEvent();
        }
        
          SubjectData subjectData = new SubjectData();
        subjectData.getData();

        SubjectController subjectController = new SubjectController(mainPanel.getSubjectPanel(), subjectData);
        if (mainPanel.getSubjectPanel() != null) {
            subjectController.setViewAndEvent();
        }
        
        ScoreData scoreData = new ScoreData();
        scoreData.getData();
        
          ScoreController scoreController = new ScoreController(mainPanel.getScorePanel(), scoreData);
        if (mainPanel.getScorePanel() != null) {
            scoreController.setViewAndEvent();
        }   
    }
}
