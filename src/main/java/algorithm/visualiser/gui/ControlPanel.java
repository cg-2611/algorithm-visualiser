package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import algorithm.visualiser.algorithms.Algorithm;
import algorithm.visualiser.algorithms.BubbleSort;

public class ControlPanel extends JPanel {

    private static final Algorithm[] SORTING_ALGORITHMS = {new BubbleSort()};
    private static final String[] SORTING_ALGORITHM_NAMES = {"Bubble Sort"};
    private static final int[] ARRAY_LENGTHS = {5, 10, 25, 50, 100, 250, 500};

    private Window window;

    private JPanel switchPanel;
    private JPanel buttonPanel;
    private JPanel optionsPanel;
    private JPanel algorithmOptionsPanel;
    private JPanel sortingOptionsPanel;
    private JPanel searchingOptionsPanel;
    private JPanel arrayOptionsPanel;

    private JButton typeSwitch;

    private JButton runButton;
    private JButton shuffleButton;

    private JLabel sortingOptionsLabel;
    private JComboBox<String> sortingOptionsDropdown;

    private JLabel searchingOptionsLabel;
    private JComboBox<String> searchingOptionsDropdown;
    private JLabel searchingTargetOptionLabel;
    private JSpinner searchingTargetOptionsSpinner;

    private JLabel arrayOptionLabel;
    private JLabel arrayLengthValue;
    private JSlider arrayLengthSlider;

    private int typeSelection;
    private int arrayLength;
    private Algorithm algorithmSelection;
    private boolean algorithmRunning;

    public ControlPanel(Window window) {
        this.window = window;

        typeSelection = 0;
        algorithmRunning = false;

        initialise();
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public boolean getAlgorithmRunning() {
        return algorithmRunning;
    }

    public Algorithm getAlgorithmSelection() {
        return algorithmSelection;
    }

    public void setAlgorithmRunning(boolean algorithmRunning) {
        this.algorithmRunning = algorithmRunning;
    }

    public void updateCardPanel() {
        switchCardPanel(typeSwitch.getText().toLowerCase());

        if (typeSelection == 0) {
            typeSwitch.setText("Searching");
        } else if (typeSelection == 1) {
            typeSwitch.setText("Sorting");
        }
    }

    public void switchCardPanel(String cardName) {
        CardLayout cl = (CardLayout) algorithmOptionsPanel.getLayout();
        cl.show(algorithmOptionsPanel, cardName);
    }

    public void enableComponents(boolean enabled) {
        runButton.setEnabled(enabled);
        shuffleButton.setEnabled(enabled);

        sortingOptionsDropdown.setEnabled(enabled);
        searchingOptionsDropdown.setEnabled(enabled);
        searchingTargetOptionsSpinner.setEnabled(enabled);

        arrayLengthSlider.setEnabled(enabled);
    }

    private void updateArrayLength(int value) {
        arrayLength = value;
        window.getRenderCanvas().updateArray(arrayLength);
    }

    private void initialise() {
        setLayout(new BorderLayout());

        initialiseSwitchPanel();
        add(switchPanel, BorderLayout.WEST);

        initialiseButtonPanel();
        add(buttonPanel, BorderLayout.EAST);

        initialiseOptionsPanel();
        add(optionsPanel, BorderLayout.CENTER);
    }

    private void initialiseSwitchPanel() {
        switchPanel = new JPanel();
        switchPanel.setLayout(new FlowLayout());

        typeSwitch = new JButton("Searching");
        typeSwitch.addActionListener((e) -> {
            if (typeSwitch.getText().equals("Sorting")) {
                typeSelection = 0;
            } else if (typeSwitch.getText().equals("Searching")) {
                typeSelection = 1;
            }

            updateCardPanel();
        });

        switchPanel.add(typeSwitch);
    }

    private void initialiseButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 5));

        runButton = new JButton("Run");
        runButton.addActionListener((e) -> {
            setAlgorithmRunning(true);
            algorithmSelection = SORTING_ALGORITHMS[sortingOptionsDropdown.getSelectedIndex()];
            algorithmSelection.setArray(window.getRenderCanvas().getArray().getArray());
            algorithmSelection.run();
            enableComponents(false);
        });

        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener((e) -> {
            window.getRenderCanvas().getArray().shuffle();
        });

        buttonPanel.add(runButton);
        buttonPanel.add(shuffleButton);
    }

    private void initialiseOptionsPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        initialiseAlgorithmOptionsPanel();
        optionsPanel.add(algorithmOptionsPanel);

        initialiseArrayOptionsPanel();
        optionsPanel.add(arrayOptionsPanel);
    }

    private void initialiseAlgorithmOptionsPanel() {
        algorithmOptionsPanel = new JPanel();
        algorithmOptionsPanel.setLayout(new CardLayout());

        initialiseSortingOptionsPanel();
        algorithmOptionsPanel.add(sortingOptionsPanel, "sorting");

        initialiseSearchingOptionsPanel();
        algorithmOptionsPanel.add(searchingOptionsPanel, "searching");
    }

    private void initialiseSortingOptionsPanel() {
        sortingOptionsPanel = new JPanel();
        sortingOptionsPanel.setLayout(new FlowLayout());

        sortingOptionsLabel = new JLabel("Sorting Algorithm:");
        sortingOptionsPanel.add(sortingOptionsLabel);

        sortingOptionsDropdown = new JComboBox<String>();
        sortingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(SORTING_ALGORITHM_NAMES));
        sortingOptionsPanel.add(sortingOptionsDropdown);
    }

    private void initialiseSearchingOptionsPanel() {
        searchingOptionsPanel = new JPanel();
        searchingOptionsPanel.setLayout(new FlowLayout());

        searchingOptionsLabel = new JLabel("Searching Algorithm:");
        searchingOptionsPanel.add(searchingOptionsLabel);

        searchingOptionsDropdown = new JComboBox<String>();
        searchingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(new String[] {"Linear Search"}));
        searchingOptionsPanel.add(searchingOptionsDropdown);

        searchingTargetOptionLabel = new JLabel("Target:");
        searchingOptionsPanel.add(searchingTargetOptionLabel);

        searchingTargetOptionsSpinner = new JSpinner();
        searchingOptionsPanel.add(searchingTargetOptionsSpinner);
    }

    private void initialiseArrayOptionsPanel() {
        arrayOptionsPanel = new JPanel();
        arrayOptionsPanel.setLayout(new FlowLayout());

        arrayOptionLabel = new JLabel("Array Length:");
        arrayOptionsPanel.add(arrayOptionLabel);

        arrayLengthValue = new JLabel("10");
        arrayOptionsPanel.add(arrayLengthValue);

        arrayLengthSlider = new JSlider();
        arrayLengthSlider.setModel(new DefaultBoundedRangeModel(2, 0, 0, ARRAY_LENGTHS.length - 1));
        arrayLengthSlider.setSnapToTicks(true);
        arrayLengthSlider.addChangeListener((e) -> {
            updateArrayLength(ARRAY_LENGTHS[arrayLengthSlider.getValue()]);
            arrayLengthValue.setText(String.valueOf(arrayLength));
        });
        arrayOptionsPanel.add(arrayLengthSlider);
    }


}
