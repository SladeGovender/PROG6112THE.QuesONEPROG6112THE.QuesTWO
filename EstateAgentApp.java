/**
 *
 * @author sladegovender
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class EstateAgentApp extends JFrame {
    private JComboBox<String> locationComboBox;
    private JTextField agentNameTextField;
    private JTextField propertyPriceTextField;
    private JTextField commissionTextField;
    private JTextArea reportTextArea;

    public EstateAgentApp() {
        // Initialize components
        locationComboBox = new JComboBox<>(new String[]{"Cape Town", "Durban", "Pretoria"});
        agentNameTextField = new JTextField();
        propertyPriceTextField = new JTextField();
        commissionTextField = new JTextField();
        reportTextArea = new JTextArea();

        // Create menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        // Create menu items
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem processReportMenuItem = new JMenuItem("Process Report");
        JMenuItem saveReportMenuItem = new JMenuItem("Save Report");
        JMenuItem clearMenuItem = new JMenuItem("Clear");

        // Add action listeners to menu items
        exitMenuItem.addActionListener(e -> exitApplication());
        processReportMenuItem.addActionListener(e -> processReport());
        saveReportMenuItem.addActionListener(e -> saveReport());
        clearMenuItem.addActionListener(e -> clearForm());

        // Add menu items to menus
        fileMenu.add(exitMenuItem);
        toolsMenu.add(processReportMenuItem);
        toolsMenu.add(saveReportMenuItem);
        toolsMenu.add(clearMenuItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Set layout and add components to the frame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Agent Location:"));
        add(locationComboBox);
        add(new JLabel("Agent Name:"));
        add(agentNameTextField);
        add(new JLabel("Property Price:"));
        add(propertyPriceTextField);
        add(new JLabel("Commission Percentage:"));
        add(commissionTextField);
        add(new JLabel("Report:"));
        add(new JScrollPane(reportTextArea));

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void exitApplication() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void processReport() {
        // Validate data
        if (EstateAgent.validateData(
                locationComboBox.getSelectedItem().toString(),
                agentNameTextField.getText(),
                propertyPriceTextField.getText(),
                commissionTextField.getText())) {

            // Calculate commission
            double commission = EstateAgent.calculateCommission(
                    propertyPriceTextField.getText(),
                    commissionTextField.getText());

            // Display report
            String report = String.format("Agent Location: %s\nAgent Name: %s\nProperty Price: %s\nCommission: %.2f",
                    locationComboBox.getSelectedItem(),
                    agentNameTextField.getText(),
                    propertyPriceTextField.getText(),
                    commission);

            JOptionPane.showMessageDialog(this, report, "Estate Agent Report", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid data. Please check the input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveReport() {
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write(reportTextArea.getText());
            JOptionPane.showMessageDialog(this, "Report saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving report.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void clearForm() {
        locationComboBox.setSelectedIndex(0);
        agentNameTextField.setText("");
        propertyPriceTextField.setText("");
        commissionTextField.setText("");
        reportTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstateAgentApp());
    }
}

class EstateAgent {
    public static double calculateCommission(String propertyPrice, String agentCommission) {
        double price = Double.parseDouble(propertyPrice);
        double commissionPercentage = Double.parseDouble(agentCommission);
        return price * (commissionPercentage / 100);
    }

    public static boolean validateData(String location, String agentName, String propertyPrice, String agentCommission) {
        return !location.isEmpty() && !agentName.isEmpty() &&
               Double.parseDouble(propertyPrice) > 0 && Double.parseDouble(agentCommission) > 0;
    }
}
