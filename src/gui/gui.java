package gui;

import classes.Course;
import classes.Major;
import helpers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class gui {
    private JTextArea ta;
    public gui() {

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        m11.addActionListener(e -> {
            try {
                openDirectory(e, m11);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        ta = new JTextArea();
        ta.append("Hello! Welcome to the Predictive Model program written by Brady Ash \n");
        ta.append("Please hit \"file, open\" and select the directory with the degree plans!");

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
    public void appendText(String s) {
     ta.append(s);
     ta.append("\n");
    }
    public void openDirectory(ActionEvent e, Component c) throws IOException {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //Handle open button action.
        if (e.getSource() == c) {
            int returnVal = fc.showOpenDialog(c);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File f = fc.getSelectedFile();
                appendText("File accepted!");

                appendText("Collecting degree plans...");
                ArrayList<File> allFiles = CollectFiles.collectFiles(f.getAbsolutePath());

                appendText("Mapping Majors...");
                HashMap<String, Major> majors = ReadEnrolled.readCsv();

                appendText("Reading degree plans...");
                for (File file : allFiles) {
                    ArrayList<Course> courses1 = ReadPlans.readPlan(majors, file);
                }

                appendText("Aggregating class totals..");
                HashMap<String,Integer> totals = AggregateClassTotals.AggregateClassTotals(majors);

                appendText("Outputting to .CSV...");
                SendToCSV.writeToCsv("", totals);

                appendText("Program Finished!");

                //appendText("\nWould you like to view the .csv file??");
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
            } else {

            }
        }
    }
}
