package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import algorithm.visualiser.algorithms.Algorithm;
import algorithm.visualiser.algorithms.BubbleSort;
import algorithm.visualiser.algorithms.LinearSearch;

public class ControlPanel extends JPanel {

    private static final Algorithm[] SORTING_ALGORITHMS = {new BubbleSort(250)};
    private static final Algorithm[] SEARCHING_ALGORITHMS = {new LinearSearch(250)};
    private static final String[] SORTING_ALGORITHM_NAMES = {"Bubble Sort"};
    private static final int[] ARRAY_LENGTHS = {5, 10, 25, 50, 100, 250, 500};

    private RenderCanvas renderCanvas;

    private JPanel selectionPanel;
    private ButtonGroup buttonGroup;
    private JRadioButton sortingButton;
    private JRadioButton searchingButton;

    private JPanel buttonPanel;
    private JButton resetButton;
    private JButton runButton;
    private JButton shuffleButton;
    private JButton reverseButton;

    private JPanel optionsPanel;
    private JPanel algorithmOptionsPanel;
    private JPanel arrayOptionsPanel;

    private JPanel sortingOptionsPanel;
    private JLabel sortingOptionsLabel;
    private JComboBox<String> sortingOptionsDropdown;

    private JPanel searchingOptionsPanel;
    private JLabel searchingOptionsLabel;
    private JComboBox<String> searchingOptionsDropdown;
    private JLabel searchingTargetOptionLabel;
    private JSpinner searchingTargetOptionsSpinner;

    private JLabel arrayOptionLabel;
    private JLabel arrayLengthValue;
    private JSlider arrayLengthSlider;

    private String algorithmTypeSelection;
    private Algorithm algorithmSelection;
    private boolean algorithmRunning;
    private int arrayLength;

    public ControlPanel(RenderCanvas renderCanvas) {
        this.renderCanvas = renderCanvas;

        algorithmTypeSelection = "Sorting";
        algorithmRunning = false;
        arrayLength = 10;

        initialise();
    }

    public Algorithm getAlgorithmSelection() {
        return algorithmSelection;
    }

    public boolean isAlgorithmRunning() {
        return algorithmRunning;
    }

    private void initialise() {
        setLayout(new BorderLayout());

        selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        add(selectionPanel, BorderLayout.WEST);

        buttonGroup = new ButtonGroup();

        sortingButton = new JRadioButton("Sorting");
        sortingButton.setSelected(true);
        sortingButton.addActionListener((e) -> {
            switchAlgorithmOptions(e);
        });

        searchingButton = new JRadioButton("Searching");
        searchingButton.addActionListener((e) -> {
            switchAlgorithmOptions(e);
        });

        buttonGroup.add(sortingButton);
        buttonGroup.add(searchingButton);

        selectionPanel.add(sortingButton);
        selectionPanel.add(searchingButton);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        add(buttonPanel, BorderLayout.EAST);

        resetButton = new JButton("Reset");
        resetButton.addActionListener((e) -> {
            renderCanvas.getArray().reset();
        });
        buttonPanel.add(resetButton);

        runButton = new JButton("Run");
        runButton.addActionListener((e) -> {
            if (algorithmTypeSelection.equals("Sorting")) {
                algorithmSelection = SORTING_ALGORITHMS[sortingOptionsDropdown.getSelectedIndex()];
                algorithmSelection.setArray(renderCanvas.getArray());

                algorithmRunning = true;
                algorithmSelection.run();
                algorithmRunning = false;
            } else if (algorithmTypeSelection.equals("Searching")) {
                algorithmSelection = SEARCHING_ALGORITHMS[searchingOptionsDropdown.getSelectedIndex()];
                algorithmSelection.setArray(renderCanvas.getArray());

                algorithmRunning = true;
                algorithmSelection.run();
                algorithmRunning = false;
            }
        });
        buttonPanel.add(runButton);

        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener((e) -> {
            renderCanvas.getArray().shuffle();
        });
        buttonPanel.add(shuffleButton);

        reverseButton = new JButton("Reverse");
        reverseButton.addActionListener((e) -> {
            renderCanvas.getArray().reverse();
        });
        buttonPanel.add(reverseButton);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        add(optionsPanel, BorderLayout.CENTER);

        algorithmOptionsPanel = new JPanel();
        algorithmOptionsPanel.setLayout(new CardLayout());
        optionsPanel.add(algorithmOptionsPanel);

        sortingOptionsPanel = new JPanel();
        sortingOptionsPanel.setLayout(new FlowLayout());

        sortingOptionsLabel = new JLabel("Sorting Algorithm:");
        sortingOptionsPanel.add(sortingOptionsLabel);

        sortingOptionsDropdown = new JComboBox<String>();
        sortingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(SORTING_ALGORITHM_NAMES));
        sortingOptionsPanel.add(sortingOptionsDropdown);
        algorithmOptionsPanel.add(sortingOptionsPanel, "Sorting");

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
        algorithmOptionsPanel.add(searchingOptionsPanel, "Searching");

        arrayOptionsPanel = new JPanel();
        arrayOptionsPanel.setLayout(new FlowLayout());

        arrayOptionLabel = new JLabel("Array Length:");
        arrayOptionsPanel.add(arrayOptionLabel);

        arrayLengthValue = new JLabel(String.valueOf(arrayLength));
        arrayOptionsPanel.add(arrayLengthValue);

        arrayLengthSlider = new JSlider();
        arrayLengthSlider.setModel(new DefaultBoundedRangeModel(2, 0, 0, ARRAY_LENGTHS.length - 1));
        arrayLengthSlider.setSnapToTicks(true);
        arrayLengthSlider.addChangeListener((e) -> {
            changeArrayLength(ARRAY_LENGTHS[arrayLengthSlider.getValue()]);
        });
        arrayOptionsPanel.add(arrayLengthSlider);
        optionsPanel.add(arrayOptionsPanel);
    }

    private void switchAlgorithmOptions(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();
        algorithmTypeSelection = source.getText();

        CardLayout cl = (CardLayout) algorithmOptionsPanel.getLayout();
        cl.show(algorithmOptionsPanel, algorithmTypeSelection);
    }

    private void changeArrayLength(int length) {
        arrayLength = length;
        arrayLengthValue.setText(String.valueOf(arrayLength));
        renderCanvas.updateArray(arrayLength);
    }

}
