import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setFont(new Font("B Davat", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnChooseFile = new JButton("انتخاب پرونده...");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnChooseFile, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnChooseFile, -10, SpringLayout.EAST, contentPane);
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnChooseFile);
		
		JButton btnChooseFolder = new JButton("انتخاب پوشه...");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnChooseFolder, 6, SpringLayout.SOUTH, btnChooseFile);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnChooseFolder, 0, SpringLayout.WEST, btnChooseFile);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnChooseFolder, 0, SpringLayout.EAST, btnChooseFile);
		contentPane.add(btnChooseFolder);
	}
}
