package pjak.psmelter.util;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import pjak.psmelter.PSmelter;
import pjak.psmelter.enums.Bars;
import pjak.psmelter.enums.Locations;


public class PSmelterGUI extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					PSmelterGUI frame = new PSmelterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PSmelterGUI() {
		setTitle("pSmelter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 226, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPsmelter = new JLabel("pSmelter");
		lblPsmelter.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblPsmelter.setBounds(63, 11, 83, 55);
		contentPane.add(lblPsmelter);

		final JComboBox barCombo = new JComboBox();
		barCombo.setModel(new DefaultComboBoxModel(
				new String[] { "Bronze", "Iron", "Steel", "Silver", "Gold",
						"Mithril", "Adamant", "Rune" }));
		barCombo.setBounds(10, 58, 188, 20);
		contentPane.add(barCombo);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(72, 89, 46, 14);
		contentPane.add(lblLocation);

		final JComboBox locationCombo = new JComboBox();
		locationCombo.setModel(new DefaultComboBoxModel(new String[] {
				"Edgeville", "Al Kharid" }));
		locationCombo.setBounds(10, 114, 188, 20);
		contentPane.add(locationCombo);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (barCombo.getSelectedItem().toString().equals("Steel")) {
					PSmelter.bar = Bars.STEEL;
				} else if (barCombo.getSelectedItem().toString()
						.equals("Bronze")) {
					PSmelter.bar = Bars.BRONZE;
				} else if (barCombo.getSelectedItem().toString().equals("Iron")) {
					PSmelter.bar = Bars.IRON;
				} else if (barCombo.getSelectedItem().toString()
						.equals("Silver")) {
					PSmelter.bar = Bars.SILVER;
				} else if (barCombo.getSelectedItem().toString().equals("Gold")) {
					PSmelter.bar = Bars.GOLD;
				} else if (barCombo.getSelectedItem().toString()
						.equals("Mithril")) {
					PSmelter.bar = Bars.MITHRIL;
				} else if (barCombo.getSelectedItem().toString()
						.equals("Adamant")) {
					PSmelter.bar = Bars.ADAMANT;
				} else if (barCombo.getSelectedItem().toString().equals("Rune")) {
					PSmelter.bar = Bars.RUNITE;
				}

				if (locationCombo.getSelectedItem().toString()
						.equals("Edgeville")) {
					PSmelter.location = Locations.EDGEVILLE;
				} else if (locationCombo.getSelectedItem().toString()
						.equals("Al Kharid")) {
					PSmelter.location = Locations.AL_KHARID;
				}

				PSmelter.guiWait = false;
				destroy();
			}
		});
		btnStart.setBounds(63, 162, 89, 23);
		contentPane.add(btnStart);

	}

	public void destroy() {
		this.dispose();
	}
}
