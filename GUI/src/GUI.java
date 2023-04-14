import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class GUI {
    static int n = 1;
    static double k = 1.0;

    static class Plot extends JPanel {

        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graph = (Graphics2D)graphics;
            int width = getWidth();
            int height = getHeight();
            int OY = width / 2;
            int OX = height / 2;
            double scaleX = OY * 0.3;
            double scaleY = -OX * 0.9;
            graph.draw(new Line2D.Double(OY, 0, OY, height));
            graph.draw(new Line2D.Double(0, height-OX, width, height-OX));

            graph.setPaint(Color.RED);
            double kHelp = ((scaleX / 10.0) * k * Math.PI);
            double x1 = OY - kHelp ;
            double y1 = OX;
            graph.draw(new Line2D.Double(x1, y1 - OX, x1, y1 + OX));
            x1 = OY + kHelp;
            graph.draw(new Line2D.Double(x1, y1 - OX, x1, y1 + OX));

            Map<Double, Double> points = new HashMap<>();
            graph.setPaint(Color.BLUE);

            double maxY = 0.0;
            double i = 0.1;
            while (-kHelp + i < kHelp) {
                x1 = -kHelp + i;
                double x0 = ((-kHelp + i) / (OY * 0.3));
                y1 = 0;
                int n0 = 1;
                while (n0 <= n) {
                    y1 += ((-2 * Math.pow(-1, n0)) / n0) * Math.sin(n0 * x0);
                    n0++;
                }
                if (y1 > maxY) maxY = y1;
                points.put(x1,y1);
                i += 0.1;
            }


            for (Double key : points.keySet()) {
                double x = OY + key;
                double y = OX + ((points.get(key) / maxY) * scaleY);
                graph.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            }
        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        JFrame frame = new JFrame("Task 9");
        frame.setSize( 500, 200 );
        frame.setLayout(new BorderLayout());
        Plot plot = new Plot();
        JPanel menu = new JPanel(new GridLayout(2, 0));
        JPanel labels = new JPanel(new GridLayout(0,2));
        JLabel labelN = new JLabel("N (1-25):  " + n);
        JLabel labelK = new JLabel("K (1.0-10.0):  " + k);
        JPanel changers = new JPanel(new GridLayout(0, 2));
        JTextField textField = new JTextField(valueOf(k));
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 25, 1);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int newValue = source.getValue();
                n = newValue;
                labelN.setText("N (1-25):  " + newValue);
                plot.revalidate();
                plot.repaint();
            }
        });
        textField.addActionListener(e -> {
            String text = textField.getText();
            try {
                double number = Double.parseDouble(text);
                if (number >= 1.0 && number <= 10.0) {
                    labelK.setText("K (1.0-10.0): " + number);
                    k = number;
                    plot.revalidate();
                    plot.repaint();
                } else
                    labelK.setText("Type a valid number in range 1.0 to 10.0");
            } catch (Exception ignored) {
                labelK.setText("Type a valid number in range 1.0 to 10.0");
            }
        });
        changers.add(textField);
        changers.add(slider);
        labels.add(labelK);
        labels.add(labelN);
        menu.add(labels);
        menu.add(changers);
        frame.add(plot, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.SOUTH);
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}