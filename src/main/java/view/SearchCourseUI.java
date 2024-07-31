package view;

import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCourseViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCourseUI {
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;
    private SearchCourseController controller;
    private SearchCourseViewModel viewModel;

    public SearchCourseUI(SearchCourseController controller, SearchCourseViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Search Course");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblEnterCourseId = new JLabel("Enter Course ID or Name:");
        lblEnterCourseId.setBounds(10, 11, 200, 14);
        frame.getContentPane().add(lblEnterCourseId);

        searchField = new JTextField();
        searchField.setBounds(10, 36, 200, 20);
        frame.getContentPane().add(searchField);
        searchField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(220, 35, 89, 23);
        frame.getContentPane().add(btnSearch);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 67, 414, 183);
        frame.getContentPane().add(resultArea);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                controller.handleInput(query);
                resultArea.setText(viewModel.getMessage());
                if (viewModel.getCourse() != null) {
                    resultArea.append("\n" + viewModel.getCourse().toString());
                }
            }
        });
    }

    public void display() {
        frame.setVisible(true);
    }
}