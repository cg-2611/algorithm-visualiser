package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;

public class ControlPanel extends JPanel {

    private JPanel cardPanel;

    private Boolean algorithmRunning = false;
    private int arrayLength;

    public ControlPanel() {
        initialise();
    }

    public Boolean getAlgorithmRunning() {
        return algorithmRunning;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void setAlgorithmRunning(Boolean algorithmRunning) {
        this.algorithmRunning = algorithmRunning;
    }

    private void initialise() {
        setLayout(new BorderLayout());

        JPanel selectPanel = createSelectPanel();
        add(selectPanel, BorderLayout.WEST);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.EAST);

        JPanel optionPanel = createOptionsPanel();
        add(optionPanel, BorderLayout.CENTER);
    }

    private JPanel createSelectPanel() {
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new FlowLayout());

        JButton typeSwitch = new JButton("Searching");
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

        selectPanel.add(typeSwitch);

        return selectPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton runButton = new JButton("Run");
        runButton.addActionListener((e) -> {
            algorithmRunning = true;
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            // reset visualisation
        });

        buttonPanel.add(runButton);
        buttonPanel.add(resetButton);

        return buttonPanel;
    }

    private JPanel createOptionsPanel() {
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        createCardPanel();
        optionPanel.add(cardPanel);

        JPanel arrayOptionPanel = createArrayOptionPanel();
        optionPanel.add(arrayOptionPanel);

        return optionPanel;
    }

    private void createCardPanel() {
        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());

        JPanel sortingOptionsPanel = createSortingOptionsPanel();
        cardPanel.add(sortingOptionsPanel, "sorting");

        JPanel searchingOptionsPanel = createSearchingOptionsPanel();
        cardPanel.add(searchingOptionsPanel, "searching");
    }

    private JPanel createSortingOptionsPanel() {
        JPanel sortingOptionsPanel = new JPanel();
        sortingOptionsPanel.setLayout(new FlowLayout());

        JLabel sortingOptionsLabel = new JLabel("Sorting Algorithm:");
        sortingOptionsPanel.add(sortingOptionsLabel);

        JComboBox<String> sortingOptionsDropdown = new JComboBox<String>();
        sortingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(new String[] {"Bubble Sort"}));
        sortingOptionsPanel.add(sortingOptionsDropdown);

        return sortingOptionsPanel;
    }

    private JPanel createSearchingOptionsPanel() {
        JPanel searchingOptionsPanel = new JPanel();
        searchingOptionsPanel.setLayout(new FlowLayout());

        JLabel searchingOptionsLabel = new JLabel("Searching Algorithm:");
        searchingOptionsPanel.add(searchingOptionsLabel);

        JComboBox<String> searchingOptionsDropdown = new JComboBox<String>();
        searchingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(new String[] {"Linear Search"}));
        searchingOptionsPanel.add(searchingOptionsDropdown);

        JLabel searchingTargetOptionLabel = new JLabel("Target:");
        searchingOptionsPanel.add(searchingTargetOptionLabel);

        JSpinner searchingTargetOptionsSpinner = new JSpinner();
        searchingOptionsPanel.add(searchingTargetOptionsSpinner);

        return searchingOptionsPanel;
    }

    private JPanel createArrayOptionPanel() {
        JPanel arrayOptionPanel = new JPanel();
        arrayOptionPanel.setLayout(new FlowLayout());

        JLabel arrayOptionLabel = new JLabel("Array Length:");
        arrayOptionPanel.add(arrayOptionLabel);

        JLabel arrayLengthValue = new JLabel("10");
        arrayOptionPanel.add(arrayLengthValue);

        JSlider arrayLengthSlider = new JSlider();
        arrayLengthSlider.addChangeListener((e) -> {
            arrayLength = arrayLengthSlider.getValue();
            arrayLengthValue.setText(String.valueOf(arrayLength));
        });
        arrayOptionPanel.add(arrayLengthSlider);

        return arrayOptionPanel;
    }

}
