import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class MainForm extends JFrame {
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    Font bigFont = new Font("Courier New", Font.BOLD, 24);
    Font mediumFont = new Font("Courier New", Font.BOLD, 18);
    Font smallFont = new Font("Courier New", Font.BOLD, 16);
    Font textFont = new Font("Courier New", Font.PLAIN, 16);

    public MainForm() {

        JPanel mainPanel = new JPanel();
        JLabel title = new JLabel();

        setTitle("Planar Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.decode("#2B2D30"));

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.setLayout(layout);

        title.setText("Planar Graphs");
        title.setFont(bigFont);
        title.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(title, c);

        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont(mediumFont);
        difficultyLabel.setForeground(Color.white);

        c.gridy = 1;
        mainPanel.add(difficultyLabel, c);

        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"Trivial", "Easy", "Medium", "Hard","Impossible"});
        difficultyComboBox.setSelectedIndex(1);
        difficultyComboBox.setFont(smallFont);
        difficultyComboBox.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        difficultyComboBox.setBackground(Color.decode("#2B2D30"));
        difficultyComboBox.setForeground(Color.WHITE);

        c.gridy = 2;
        mainPanel.add(difficultyComboBox, c);

        JButton goButton = new Button1("Go");
        goButton.setFont(smallFont);
        goButton.setBackground(Color.decode("#2B2D30"));
        goButton.setForeground(Color.WHITE);

        c.gridy = 3;
        mainPanel.add(goButton, c);

        // Processing applet that draws the graph
        PlanarGraph s = new PlanarGraph();
        PApplet.runSketch(new String[]{"AppletSample"}, s);
        PSurfaceAWT surface = (PSurfaceAWT) s.getSurface();
        surface.setVisible(false);
        Canvas canvas = (Canvas) surface.getNative();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(800, 600);
        panel.setBackground(Color.decode("#2B2D30"));
        panel.add(canvas, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 30, 30), new ButtonBorder1(1, Color.WHITE)));// delete later?

        c.gridx = 1;
        c.gridheight = 3;
        mainPanel.add(panel, c);

        JLabel instructionsLabel = new JLabel("Click and drag vertices until no edges intersect.");
        instructionsLabel.setFont(textFont);
        instructionsLabel.setForeground(Color.white);
        c.gridy = 2;
        mainPanel.add(instructionsLabel, c);

        goButton.addActionListener(e -> {//add vertices
            if(s.getVertices().isEmpty()){
                goButton.setText("Reset");
                String option = (String) difficultyComboBox.getSelectedItem();
                assert option != null;

                int[] vertexBounds = new int[2];
                switch (option) {
                    case "Easy" -> {
                        vertexBounds[0] = 5;
                        vertexBounds[1] = 6;
                    }
                    case "Medium" -> {
                        vertexBounds[0] = 7;
                        vertexBounds[1] = 10;
                    }
                    case "Hard" -> {
                        vertexBounds[0] = 11;
                        vertexBounds[1] = 15;
                    }
                    case "Impossible" -> {
                        vertexBounds[0] = 16;
                        vertexBounds[1] = 21;
                    }
                    default -> {
                        // Default to trivial settings
                        vertexBounds[0] = 2;
                        vertexBounds[1] = 4;
                    }
                }

                s.addRandomVertices(vertexBounds, panel);
            }else{
                goButton.setText("Go");
                vertices.clear();
                edges.clear();
                s.reset();
            }
        });
        pack();
        setVisible(true);
    }
}
