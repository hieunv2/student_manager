/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Component.ScorePanel;
import Data.ScoreData;
import Model.Student;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import subComponent.ButtonTable;

/**
 *
 * @author 84969
 */
public class ScoreController {

    private ScorePanel scorePanel;
    private ScoreData scoreData;
    private StudentTable studentTable;

    private Student studentSelected;

    public ScoreController(ScorePanel scorePanel, ScoreData scoreData) {
        this.scoreData = scoreData;
        this.scorePanel = scorePanel;
    }

    public Student getStudentSelected() {
        return this.studentSelected;
    }

    public void setViewAndEvent() {

        this.studentTable = new StudentTable();
        scorePanel.getListStudentPanel().add(new JScrollPane(studentTable), BorderLayout.CENTER);

        updateMainPane();
        setAction();
    }

    public void updateMainPane() {

        studentTable.deleteAllRow();
        ArrayList<Student> listStudents = scoreData.getListStudents();
        for (Student student : listStudents) {
            String name = student.getName();
            int age = student.getAge();
            String email = student.getEmail();
            String phone = student.getPhone();
            String mssv = student.getMssv();
            String sex = student.getSex();
            String address = student.getAddress();

            Object[] row = {studentTable.getRowCount() + 1, student, mssv, age, sex, email};
            studentTable.addRow(row);
        }
    }

    private void setAction() {
        scorePanel.getSearchButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = scorePanel.getNameField().getText();
                String email = scorePanel.getEmailField().getText();
                String mssv = scorePanel.getMssvField().getText();
                String sex = "";
                if (scorePanel.getMaleButton().isSelected()) {
                    sex = "Nam";
                } else if (scorePanel.getFemaleButton().isSelected()) {
                    sex = "Nữ";
                } else {
                    sex = "";
                }
                scoreData.getListStudentByQuery(name, email, mssv, sex);
                updateMainPane();
            }

        });

        scorePanel.getResetButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                scorePanel.getNameField().setText("");
                scorePanel.getEmailField().setText("");
                scorePanel.getMssvField().setText("");
                scorePanel.getSexGroup().clearSelection();
            }

        });

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if(selectedRow != -1){
                   studentSelected = (Student) studentTable.getModel().getValueAt(selectedRow, 1);
                }
                if (studentSelected != null) {
                    scorePanel.getNameText().setText(studentSelected.getName());
                    scorePanel.getAgeText().setText(String.valueOf(studentSelected.getAge()));
                    scorePanel.getEmailText().setText(studentSelected.getEmail());
                    scorePanel.getPhoneText().setText(studentSelected.getPhone());
                    scorePanel.getSexText().setText(studentSelected.getSex());
                    scorePanel.getMssvText().setText(studentSelected.getMssv());
                    scorePanel.getAddressText().setText(studentSelected.getAddress());
                }
            }
        });
    }

    private class StudentTable extends JTable {

        private static final long serialVersionUID = 1L;

        private DefaultTableModel defaultTableModel;

        public StudentTable() {

            final String[] titles = {"STT", "Tên sinh viên", "Mã sinh viên", "Tuổi", "Giới Tính", "Email"};

            defaultTableModel = new DefaultTableModel(null, titles) {

                private static final long serialVersionUID = 1L;

                @Override
                public Class<?> getColumnClass(int column) {
                    for (int row = 0; row < getRowCount(); row++) {
                        if (getValueAt(row, column) != null) {
                            return getValueAt(row, column).getClass();
                        }
                    }
                    return String.class;
                }
            };

            this.setModel(defaultTableModel);
            this.setFont(new Font("Calibri", Font.PLAIN, 20));
            this.setRowHeight(40);
            this.setAutoCreateRowSorter(true);

            JTableHeader header = this.getTableHeader();
            header.setReorderingAllowed(false);
            TableCellRenderer rendererFromHeader = header.getDefaultRenderer();

            JLabel headerLabel = (JLabel) rendererFromHeader;
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            header.setFont(new Font("Calibri", Font.BOLD, 20));

            int[] positions = {0, 2, 3, 4, 5};
            int[] widths = {50, 120, 50, 100, 230};
            setWidth(positions, widths);
        }

        public void deleteAllRow() {
            for (int index = defaultTableModel.getRowCount() - 1; index >= 0; index--) {
                defaultTableModel.removeRow(index);
            }
        }

//        private void addButtonToTable(List<String> strings, List<Action> actions, int position) {
//
//            ButtonTable buttonTable = new ButtonTable(strings, actions);
//            TableColumn column = columnModel.getColumn(position);
//            column.setCellRenderer(buttonTable.getButtonsRenderer());
//            column.setCellEditor(buttonTable.getButtonEditor(this));
//        }
        private void setWidth(int[] positions, int[] widths) {

            TableColumn column;
            for (int index = 0; index < positions.length; index++) {
                column = columnModel.getColumn(positions[index]);
                column.setMinWidth(widths[index]);
                column.setMaxWidth(widths[index]);
                column.setPreferredWidth(widths[index]);
            }
        }

        public void addRow(Object[] rowData) {
            defaultTableModel.addRow(rowData);
        }

//        @Override
//        public boolean isCellEditable(int row, int column) {
//            if (column == 5 || column == 6) {
//                return true;
//            }
//            return false;
//        }
//
//        @Override
//        public Component prepareRenderer(TableCellRenderer renderer, int row,
//                int col) {
//            Component comp = super.prepareRenderer(renderer, row, col);
//            if (col != 8 && col != 9) {
//                if (col != 1 && col != 2 && col != 3 && col != 4) {
//                    ((JLabel) comp).setHorizontalAlignment(JLabel.CENTER);
//                } else {
//                    ((JLabel) comp).setHorizontalAlignment(JLabel.LEFT);
//                    ((JLabel) comp).setBorder(new EmptyBorder(0, 10, 0, 0));
//                }
//            }
//            return comp;
//      /*  }
    }

}
