package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

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
import javax.swing.SpinnerNumberModel;

import algorithm.visualiser.algorithms.Algorithm;
import algorithm.visualiser.algorithms.SearchingAlgorithm;
import algorithm.visualiser.algorithms.SortingAlgorithm;
import algorithm.visualiser.algorithms.searching.BinarySearch;
import algorithm.visualiser.algorithms.searching.InterpolationSearch;
import algorithm.visualiser.algorithms.searching.LinearSearch;
import algorithm.visualiser.algorithms.sorting.BubbleSort;
import algorithm.visualiser.algorithms.sorting.InsertionSort;
import algorithm.visualiser.algorithms.sorting.OptimisedBubbleSort;

public class ControlPanel extends JPanel {

    private static final SortingAlgorithm[] SORTING_ALGORITHMS = {
        new BubbleSort(), new OptimisedBubbleSort(), new InsertionSort()
    };

    private static final SearchingAlgorithm[] SEARCHING_ALGORITHMS = {
        new LinearSearch(), new BinarySearch(), new InterpolationSearch()
    };

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
    private boolean algorithmRunning;
    private int arrayLength;

    private SortingAlgorithm sortingAlgorithm;
    private SearchingAlgorithm searchingAlgorithm;

    public ControlPanel(RenderCanvas renderCanvas) {
        this.renderCanvas = renderCanvas;

        algorithmTypeSelection = "Sorting";
        algorithmRunning = false;
        arrayLength = 10;

        initialise();
    }

    public String getAlgorithmTypeSelection() {
        return algorithmTypeSelection;
    }

    public boolean isAlgorithmRunning() {
        return algorithmRunning;
    }

    public SortingAlgorithm getSortingAlgorithm() {
        return sortingAlgorithm;
    }

    public SearchingAlgorithm getSearchingAlgorithm() {
        return searchingAlgorithm;
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
            updatePanel();
        });

        searchingButton = new JRadioButton("Searching");
        searchingButton.addActionListener((e) -> {
            switchAlgorithmOptions(e);
            updatePanel();
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
            resetSearchTargetFound();
        });
        buttonPanel.add(resetButton);

        runButton = new JButton("Run");
        runButton.addActionListener((e) -> {
            resetSearchTargetFound();

            if (algorithmTypeSelection.equals("Sorting")) {
                sortingAlgorithm = SORTING_ALGORITHMS[sortingOptionsDropdown.getSelectedIndex()];
                sortingAlgorithm.setArray(renderCanvas.getArray());
                sortingAlgorithm.setDelay(250);

                runAlgorithm(sortingAlgorithm);
            } else if (algorithmTypeSelection.equals("Searching")) {
                searchingAlgorithm = SEARCHING_ALGORITHMS[searchingOptionsDropdown.getSelectedIndex()];

                searchingAlgorithm.setArray(renderCanvas.getArray());
                searchingAlgorithm.setDelay(1000);
                searchingAlgorithm.setTarget((int)searchingTargetOptionsSpinner.getValue());

                runAlgorithm(searchingAlgorithm);
            }
        });
        buttonPanel.add(runButton);

        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener((e) -> {
            renderCanvas.getArray().shuffle();
            resetSearchTargetFound();
        });
        buttonPanel.add(shuffleButton);

        reverseButton = new JButton("Reverse");
        reverseButton.addActionListener((e) -> {
            renderCanvas.getArray().reverse();
            resetSearchTargetFound();
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
        sortingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(getAlgorithmNames(SORTING_ALGORITHMS)));
        sortingOptionsPanel.add(sortingOptionsDropdown);
        algorithmOptionsPanel.add(sortingOptionsPanel, "Sorting");

        searchingOptionsPanel = new JPanel();
        searchingOptionsPanel.setLayout(new FlowLayout());

        searchingOptionsLabel = new JLabel("Searching Algorithm:");
        searchingOptionsPanel.add(searchingOptionsLabel);

        searchingOptionsDropdown = new JComboBox<String>();
        searchingOptionsDropdown.setModel(new DefaultComboBoxModel<String>(getAlgorithmNames(SEARCHING_ALGORITHMS)));
        searchingOptionsPanel.add(searchingOptionsDropdown);

        searchingTargetOptionLabel = new JLabel("Target:");
        searchingOptionsPanel.add(searchingTargetOptionLabel);

        searchingTargetOptionsSpinner = new JSpinner();
        searchingTargetOptionsSpinner.setModel(getSpinnerNumberModel());
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

    private void runAlgorithm(Algorithm algorithm) {
        enableComponents(false);
        algorithmRunning = true;

        try {
            algorithm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        algorithmRunning = false;
        enableComponents(true);
    }

    private void enableComponents(boolean enabled) {
        sortingButton.setEnabled(enabled);
        searchingButton.setEnabled(enabled);

        sortingOptionsDropdown.setEnabled(enabled);

        searchingOptionsDropdown.setEnabled(enabled);
        searchingTargetOptionsSpinner.setEnabled(enabled);

        arrayLengthSlider.setEnabled(enabled);

        resetButton.setEnabled(enabled);
        runButton.setEnabled(enabled);
        shuffleButton.setEnabled(enabled);
        reverseButton.setEnabled(enabled);
    }

    private SpinnerNumberModel getSpinnerNumberModel() {
        int randomValue = new Random().nextInt(arrayLength - 1) + 1;
        return new SpinnerNumberModel(randomValue, 1, arrayLength, 1);
    }

    private void switchAlgorithmOptions(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();
        algorithmTypeSelection = source.getText();

        CardLayout cl = (CardLayout) algorithmOptionsPanel.getLayout();
        cl.show(algorithmOptionsPanel, algorithmTypeSelection);

        resetSearchTargetFound();
    }

    private void resetSearchTargetFound() {
        if (algorithmTypeSelection.equals("Searching") && searchingAlgorithm != null) {
            searchingAlgorithm.resetTargetIndex();
        }
    }

    private void changeArrayLength(int length) {
        arrayLength = length;
        arrayLengthValue.setText(String.valueOf(arrayLength));
        renderCanvas.updateArray(arrayLength);
        searchingTargetOptionsSpinner.setModel(getSpinnerNumberModel());
    }

    private static String[] getAlgorithmNames(Algorithm[] algorithms) {
        String[] names = new String[algorithms.length];

        for (int i = 0; i < algorithms.length; i++) {
            names[i] = algorithms[i].getName();
        }

        return names;
    }

    private void updatePanel() {
        if (algorithmTypeSelection.equals("Sorting")) {
            shuffleButton.setEnabled(true);
            reverseButton.setEnabled(true);
        } else if (algorithmTypeSelection.equals("Searching")) {
            shuffleButton.setEnabled(false);
            reverseButton.setEnabled(false);
        }
    }

}
