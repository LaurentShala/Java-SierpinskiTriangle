// Laurent Shala
// Sierpinski's Triangle

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Driver extends JFrame implements ChangeListener{
	
	private JSlider slider = new JSlider(JSlider.HORIZONTAL);
	private SierTriangle triangle = null;
	
	public Driver() {
		triangle = new SierTriangle();
		triangle.setBackground(Color.WHITE);
		this.getContentPane().add(triangle, BorderLayout.CENTER);
		
		JPanel orderPanel = new JPanel();
		orderPanel.add(new JLabel("Enter a Level: "));
		orderPanel.add(slider);
		orderPanel.setBackground(Color.WHITE);

		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setValue(0);
		
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(0, new JLabel("0"));
        labels.put(2, new JLabel("2"));
        labels.put(4, new JLabel("4"));
        labels.put(6, new JLabel("6"));
        labels.put(8, new JLabel("8"));
        labels.put(10, new JLabel("10"));
        
        slider.setMajorTickSpacing(1);
        slider.setLabelTable(labels);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
		
		this.getContentPane().add(orderPanel, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(750,750));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		triangle.setLevel(slider.getValue());
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {	
		Driver fr = new Driver();	
	}
	
	private class SierTriangle extends JPanel {
		private int level = 0;
		
		public void setLevel(int v) { 
			level = v; 
			repaint();
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Point p1 = new Point(this.getWidth() / 2, 10);
			Point p2 = new Point(10, this.getHeight() - 10);
			Point p3 = new Point(this.getWidth() - 10, this.getHeight() - 10);
			displayTriangle(g, level, p1, p2, p3);
		}
		
		public Point midpoint (Point a, Point b){
			Point mid = new Point();
			mid.x = (a.x + b.x) / 2;
			mid.y = (a.y + b.y) / 2;
			return mid;
		}
		
		public void displayTriangle(Graphics g, int level, Point p1, Point p2, Point p3) {
			if (level == 0){
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
				g.drawLine(p1.x, p1.y, p3.x, p3.y);
				g.drawLine(p2.x, p2.y, p3.x, p3.y);
			}else{
				Point p1_2 = midpoint(p1, p2);
				Point p2_3 = midpoint(p2, p3);
				Point p3_1 = midpoint(p3, p1);

				displayTriangle(g, level - 1, p1, p1_2, p3_1);
				displayTriangle(g, level - 1, p1_2, p2, p2_3);
				displayTriangle(g, level - 1, p3_1, p2_3, p3);
			}
		}
	}
}
