import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TaskManagerGUI {
    private JButton button1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JFormattedTextField dateFormattedTextField;
    private JTextField taskTextField;
    private JButton clickToAddNewButton;
    private JTextField dateTextField;
    private JButton searchDateButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TaskManagerGUI");

        frame.setContentPane(new TaskManagerGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize( new Dimension(700,400));
        frame.setLocationRelativeTo(null);
        frame.setTitle("Task Manager User Interface");
        frame.pack();
        frame.setVisible(true);
    }

    public TaskManagerGUI() {

        TaskDatabase database = new TaskDatabase();
        database.createNewTableIfNoExists();

        button1.setText("Click to display all DB content");
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        dateFormattedTextField.setEnabled(true);
        dateFormattedTextField.setEditable(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {



                String allDBTexts = database.displayAllGUI();

                textArea1.setText(allDBTexts);
            }
        });
        clickToAddNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int taskID = database.checkNextId();

                Task task1 = new Task ( taskID  , LocalDate.parse(dateFormattedTextField.getText()), taskTextField.getText());
                database.insertTask(task1);
            }
        });
        searchDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                String rowsByDate = database.displayByDate( dateTextField.getText() );

                textArea1.setText(rowsByDate);
            }
        });
    }

}
