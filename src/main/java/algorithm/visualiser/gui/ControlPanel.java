package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPanel extends JPanel {

    private JPanel selectPanel;
    private JButton typeSwitch;

    private JPanel buttonPanel;
    private JButton runButton;
    private JButton resetButton;

    private JPanel optionPanel;

    private JPanel arrayLengthOptions;
    private JLabel arrayLengthLabel;
    private JSlider arrayLengthSlider;

    private JPanel cardPanel;
    private JPanel sortingOptionsPanel;
    private JPanel searchingOptionsPanel;

    public ControlPanel() {
        initialise();
    }

    private void initialise() {
        setLayout(new BorderLayout());

        typeSwitch = new JButton("Searching");
        typeSwitch.addActionListener((e) -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            if (typeSwitch.getText().equals("Sorting")) {
                cl.show(cardPanel, typeSwitch.getText().toLowerCase());
                typeSwitch.setText("Searching");
            } else if (typeSwitch.getText().equals("Searching")) {
                cl.show(cardPanel, typeSwitch.getText().toLowerCase());
                typeSwitch.setText("Sorting");
            }
        });

        selectPanel = new JPanel();
        selectPanel.setLayout(new FlowLayout());
        selectPanel.add(typeSwitch);
        add(selectPanel, BorderLayout.WEST);

        runButton = new JButton("Run");
        runButton.addActionListener((e) -> {
            // run algorithm
        });

        resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            // reset visualisation
        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(runButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.EAST);

        optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());
        optionPanel.add(cardPanel);

        sortingOptionsPanel = new JPanel();
        sortingOptionsPanel.add(new JLabel("Sorting"));
        cardPanel.add(sortingOptionsPanel, "sorting");

        searchingOptionsPanel = new JPanel();
        searchingOptionsPanel.add(new JLabel("Searching"));
        cardPanel.add(searchingOptionsPanel, "searching");

        arrayLengthOptions = new JPanel();
        arrayLengthOptions.setLayout(new FlowLayout());

        arrayLengthLabel = new JLabel("Array Length:");
        arrayLengthOptions.add(arrayLengthLabel);

        arrayLengthSlider = new JSlider();
        arrayLengthOptions.add(arrayLengthSlider);
    }
}
