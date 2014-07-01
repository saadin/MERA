import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import mera.DatabaseManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser fileChooser;
	private JFileChooser folderChooser;
	private JLabel fileChooseLabel;
	
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
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseManager dbm = DatabaseManager.getInstance();
		dbm.initdb();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/mera/assets/logo64.png")));
		setTitle("بی نشان ساز پرونده های پزشکی");
		setFont(new Font("B Davat", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(MainWindow.class.getResource("/mera/assets/logo64.png")));
		lblNewLabel.setFont(new Font("B Mitra", Font.PLAIN, 16));
		lblNewLabel.setText("برای شروع پرونده(ها) را انتخاب نمایید");
		
		panel.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("درباره", null, panel_1, null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainWindow.class.getResource("/mera/assets/logo128.png")));
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("MERA");
		panel_2.add(lblNewLabel_2);
		
		JLabel lblS = new JLabel("مِرا - بی‌نشان‌ساز پرونده‌های پزشکی");
		panel_2.add(lblS);
		
		JLabel label_2 = new JLabel("نسخه ۰.۱");
		panel_2.add(label_2);
		
		JLabel label_1 = new JLabel("طراح: صادق صدرزاده");
		panel_2.add(label_1);
		
		JPanel fileTabbedPanel = new JPanel();
		tabbedPane.addTab("بی‌نشان‌سازی", null, fileTabbedPanel, null);
		SpringLayout sl_fileTabbedPanel = new SpringLayout();
		fileTabbedPanel.setLayout(sl_fileTabbedPanel);
		
		fileChooseLabel = new JLabel("نام فایل");
		sl_fileTabbedPanel.putConstraint(SpringLayout.NORTH, fileChooseLabel, 0, SpringLayout.NORTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, fileChooseLabel, 0, SpringLayout.WEST, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.SOUTH, fileChooseLabel, 38, SpringLayout.NORTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, fileChooseLabel, -148, SpringLayout.EAST, fileTabbedPanel);
		fileTabbedPanel.add(fileChooseLabel);
		
		JButton button = new JButton("انتخاب پرونده");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(MainWindow.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					String names = "";
					for(File i : fileChooser.getSelectedFiles())
						names+=i.getName()+"|";
					fileChooseLabel.setText(names);
					fileChooseLabel.setToolTipText(fileChooser.getSelectedFile().getParent());
				}
				else{
					fileChooseLabel.setText("پرونده را انتخاب کنید");
				}
			}
		});
		sl_fileTabbedPanel.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, button, 6, SpringLayout.EAST, fileChooseLabel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.SOUTH, button, 38, SpringLayout.NORTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, fileTabbedPanel);
		fileTabbedPanel.add(button);
		
		JLabel label = new JLabel("نوع بی‌نشان‌سازی");
		sl_fileTabbedPanel.putConstraint(SpringLayout.NORTH, label, 6, SpringLayout.SOUTH, button);
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, label, 368, SpringLayout.WEST, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.SOUTH, label, 82, SpringLayout.NORTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, fileTabbedPanel);
		fileTabbedPanel.add(label);
		
		JRadioButton rdbAutomatic = new JRadioButton("خودکار");
		rdbAutomatic.setSelected(true);
		sl_fileTabbedPanel.putConstraint(SpringLayout.NORTH, rdbAutomatic, 10, SpringLayout.NORTH, label);
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, rdbAutomatic, 271, SpringLayout.WEST, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, rdbAutomatic, -6, SpringLayout.WEST, label);
		fileTabbedPanel.add(rdbAutomatic);
		
		JRadioButton rdbManual = new JRadioButton("دستی");
		rdbManual.setEnabled(false);
		sl_fileTabbedPanel.putConstraint(SpringLayout.NORTH, rdbManual, 0, SpringLayout.NORTH, rdbAutomatic);
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, rdbManual, 190, SpringLayout.WEST, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, rdbManual, -15, SpringLayout.WEST, rdbAutomatic);
		fileTabbedPanel.add(rdbManual);
		
		JButton btnAnonymize = new JButton("شروع");
		btnAnonymize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(File i : fileChooser.getSelectedFiles())
				{
					Anonymizer an = new Anonymizer(i.getPath());
					an.anonymizeAndSave();
				}
				fileChooseLabel.setText("عملیات انجام شد");
			}
		});
		sl_fileTabbedPanel.putConstraint(SpringLayout.WEST, btnAnonymize, 128, SpringLayout.WEST, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.SOUTH, btnAnonymize, -10, SpringLayout.SOUTH, fileTabbedPanel);
		sl_fileTabbedPanel.putConstraint(SpringLayout.EAST, btnAnonymize, -101, SpringLayout.EAST, fileTabbedPanel);
		fileTabbedPanel.add(btnAnonymize);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Document files", "doc","docx"));
		fileChooser.setMultiSelectionEnabled(true);
		folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	void fileChanged()
	{
		fileChooseLabel.setText("");
	}
	protected JLabel getFileChooseLabel() {
		return fileChooseLabel;
	}
}
