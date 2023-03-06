package gui;

import classes.Course;
import classes.Major;
import helpers.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//found in thread
public class gui {
    private JTextArea ta;
    private File output;
    private ArrayList<String> errors;
    private  HashMap<String, Major> majors;
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
                openDirectory(e, m11, "file");
                openDirectory(e, m11, "directory");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        send.addActionListener(e -> {
            try {
                openFile(e, send, tf);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        ta = new JTextArea(16, 8);
        ta.append("Hello! Welcome to the Predictive Model program written by Brady Ash \n");
        ta.append("Please hit \"file, open\" and select the file with the \nfreshmen enrolled count by major!\n");

        //add scroll functionality to text area
        JScrollPane scroll = new JScrollPane(ta);
        scroll.setSize( 100, 100 );

        //always sets scrollbar to the furthest possible down
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            public void adjustmentValueChanged(AdjustmentEvent e){
                ta.select(ta.getHeight()+1000,0);
            }});

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.setVisible(true);
    }
    public void appendText(String s) {
     ta.append(s);
     ta.append("\n");
    }
    public void setFile(File file) {
        output = file;
    }
    public File getFile() {
        return output;
    }

    public void setMajors( HashMap<String, Major> m) {
        majors = m;
    }
    public  HashMap<String, Major> getMajors() {
        return majors;
    }

    public void openEnrolled(ActionEvent e, Component c) {

    }

    //opens the output file
    public void openFile(ActionEvent e, Component c, JTextField j) throws BadLocationException, IOException {
        if (e.getSource() == c) {
            String input = j.getText();
            ta.append("--> " + input);
            if(input.toLowerCase().equals("yes")) {
                File file = getFile();
                Desktop.getDesktop().open(file);
                ta.append("\nThank you for running the program!");
            }
                ta.append("\nProgram Finished, hit the 'X' button to close!");
        }
    }

    public void openDirectory(ActionEvent e, Component c, String s) throws IOException {
        final JFileChooser fc = new JFileChooser();
        if(s.equals("file")) {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        else {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        //Handle open button action.
        if (e.getSource() == c) {
            int returnVal = fc.showOpenDialog(c);
            if (s.equals("file")) {
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    setMajors(ReadEnrolled.readCsv(f));
                    appendText("Majors filed!");
                    appendText("Select the directory containing the degree plans!");
                    return;
                }
            }

            //if the file is a valid directory
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File f = fc.getSelectedFile();
                appendText("File directory accepted!");

                appendText("Collecting degree plans...");
                ArrayList<File> allFiles = CollectFiles.collectFiles(f.getAbsolutePath());

                appendText("Reading degree plans...");

                //outputs errors to the text area
                for (File file : allFiles) {
                    String error = ReadPlans.readPlan(getMajors(), file);
                    if (!error.equals("") && !error.equals(null)) {
                        appendText(error);
                    }
                }

                appendText("Aggregating class totals..");
                HashMap<String,Integer> totals = AggregateClassTotals.AggregateClassTotals(getMajors());

                appendText("Outputting to .CSV...");
                setFile(SendToCSV.writeToCsv(totals));

                appendText("Program Finished!");

                //prints out output file, then asks if they would like to open it.
                File file = new File(System.getProperty("user.dir"));
                File output = new File(file.getAbsolutePath() + "\\out.csv");
                if (output.isFile()) {
                    appendText("\nout.csv can be accessed using the following filepath:\n");
                    appendText(output.getPath());
                    setFile(output);
                }
                appendText("\nWould you like to view the .csv file?\nEnter yes/no in bottom text box");
                //This is where a real application would open the file.
            } else {

            }
        }
    }
}
