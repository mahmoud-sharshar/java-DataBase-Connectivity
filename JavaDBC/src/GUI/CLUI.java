package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Label;
import java.awt.RenderingHints.Key;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;

public class CLUI extends JFrame {

	private JPanel contentPane;
	private String dataBaseName;
	private Connection connector;
	private String currentStatement;
	private Statement statement;
	private JFrame mainFram;
	private JTextArea console;
	private ResultSet result;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CLUI(Connection connect, String name, JFrame mainframe) {
		this.mainFram = mainframe;
		try {
			this.statement = connect.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		this.connector = connect;
		this.dataBaseName = name;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton("show logs");
		panel_2.add(btnNewButton);

		Label label = new Label("DataBase Name : " + dataBaseName);
		label.setAlignment(Label.CENTER);
		panel_2.add(label);

		JButton closeButton = new JButton("Close connection");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterFrame();
				try {
					connector.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_2.add(closeButton);

		console = new JTextArea();
		// textArea.setHighlighter(null);
		console.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int end = console.getDocument().getLength();
					console.setDragEnabled(false);
					int start;
					try {
						start = Utilities.getRowStart(console, end);
						// System.out.println(start);
						while (start == end) {
							end--;
							start = Utilities.getRowStart(console, end);
						}
						currentStatement = console.getText(start, end - start);
						// System.out.println(currentStatement);
						excuteStatement();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		JScrollPane s = new JScrollPane(console);
		console.setBorder(new TitledBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)), "Command Line",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(s, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setVisible(false);
		s.setRowHeaderView(panel);
	}

	private void excuteStatement() {
		try {
			
			if (currentStatement.toLowerCase().contains("select")) {
				result = statement.executeQuery(currentStatement);
				int columns = result.getMetaData().getColumnCount();
				String[] names = new String[columns];
				for (int i = 1; i <= columns; i++) {
					names[i - 1] = result.getMetaData().getColumnLabel(i);
				}
				int rows = 0;
				while (result.next()) rows++;
				System.out.println(rows);
					
				result.beforeFirst();
				Object[][] content = new Object[rows][columns];
				rows = 0;
				while (result.next()) {
					for (int i = 1; i <= columns; i++) {
						content[rows][i - 1] =  result.getObject(i);
					}
					rows++;
				}
				ArrayList<Integer> max = new ArrayList<>(names.length);
				for(int i=0;i<names.length;i++) {
					max.add(i,names[i].length());
					for(int j=0;j<content.length;j++) {
						if(max.get(i)<content[j][i].toString().length()) {
							max.add(i,content[j][i].toString().length());
						}
					}
				}
				for (int i = 0; i < names.length; i++) {
					if (i == 0)
						console.append("\n");
					console.append( names[i]);
					for(int j =0;j<max.get(i);j++) {
						console.append(" ");
					}
				}
				for (int i = 0; i < content.length; i++) {
					console.append("\n");
					for (int j = 0; j < content[0].length; j++) {
						console.append( content[i][j].toString());
						/*for(int c =0;c<max.get(i);c++) {
							console.append(" ");
						}*/
					}
				}

			} else {
				int rows = statement.executeUpdate(currentStatement);
				console.append("\n Query excuted successfully ," + rows + " rows are edited");
			}
		} catch (SQLException e) {
			console.append("\nError occured , check your query");

		}
	}

	private void alterFrame() {
		this.setVisible(false);
		mainFram.setVisible(true);
	}

}
