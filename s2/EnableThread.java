package s2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EnableThread extends Thread {
	private BufferedReader in;
	protected ArrayList<SquareButton> sq = new ArrayList<SquareButton>();

	protected int num;
	protected String player=null;
	protected static String[][] matrixStrings;
	protected JTable table;
	static Object matrizObjetos[][];
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected ResultSet rs2;
	private static  int filaUsuario=0;
	private static DefaultTableModel model;
	private String [] titulos= new String[] {"Posición","Usuario", "Victorias", "Derrotas", "Empates"};
	


	@SuppressWarnings("serial")
	public EnableThread(JTable table) {
		

		model = new DefaultTableModel(matrizObjetos, titulos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Todas las celdas no son editables
			}
		};
		this.table=table;
		
	}

	public static DefaultTableModel getModel() {
		return model;
	}
	

	public static int getFilaUsuario() {
		return filaUsuario;
	}

	@Override
	public void run() {			
		
		Aws.connect(false);
		
		while (true) {
			
			try {
				
				String select= "select count(*) from Usuarios;";
				ps = Aws.getConnection().prepareStatement(select);
				rs = ps.executeQuery(); 
     

				if(rs.next()) {
					 
					
					matrixStrings = new String[rs.getInt(1)][5];

					select= "select Usuario, Victorias, Derrotas, Empates from Usuarios order by Victorias desc, Derrotas asc;";
					ps = Aws.getConnection().prepareStatement(select);
					rs2 = ps.executeQuery();
					
					int index=0;
					model.setRowCount(0);
					while (rs2.next()) {
						
						matrixStrings[index][0] = String.valueOf(index+1);
						matrixStrings[index][1] = rs2.getString(1);
						if (rs2.getString(1).equals(Login.getUsuario())) {
							filaUsuario=index;
						}
						matrixStrings[index][2] = rs2.getString(2);
						matrixStrings[index][3] = rs2.getString(3);
						matrixStrings[index][4] = rs2.getString(4);
						model.addRow(matrixStrings[index]);
						index++;
						
					}

					matrizObjetos= new Object[matrixStrings.length][matrixStrings[0].length];
					for (int i = 0; i < matrixStrings.length; i++) {
						for (int j = 0; j < matrixStrings[i].length; j++) {
							matrizObjetos[i][j] = matrixStrings[i][j];
						}
					}}
				table.setModel(model);
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}

