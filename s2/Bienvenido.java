package s2;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.StateEditable;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class Bienvenido extends JFrame {

	private JPanel contentPane;
	protected ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected static String player=null;
	private final static int PORT = 5006;
	protected PrintStream output;
	protected BufferedReader input;
	protected Socket client;
	protected ServerSocket server;
	protected Socket socket;
	protected String user;
	protected JTextArea textArea_chat;
	protected BoxThread b;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected Object obj;
	protected final int WITH_CONNECTION=1;
	protected final int WITHOUT_CONNECTION=0;
	protected int stateConnection =WITHOUT_CONNECTION;
	protected final int pcServer=0;
	protected final int pcClient=1;
	protected int statePC;
	//buttons
	private JButton btnAtras;
	private static JButton btnRecibirConexion;
	private static JButton btnConectarse;
	private static JButton btnO;
	private static JButton btnX;
	private TresEnRaya ter = new TresEnRaya();
	private static Bienvenido frame;
	private static boolean xReady=false;
	private static boolean oReady=false;
	private static JLabel lblBienvenidonombre;
	private static JLabel lblEligeFicha;
	private static JTable table = new JTable();
	private static String[][] matrixStrings;
	protected static boolean xLeHaDadoANuevaPartida=false;
	protected static boolean oLeHaDadoANuevaPartida=false;
	protected static DefaultTableModel model;
	private EnableThread et = new EnableThread(table);;
	
//	private static String Usuario;
	
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Bienvenido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	



	public static Bienvenido getFrame() {
		return frame;
	}



	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		Bienvenido.table = table;
	}

	public static DefaultTableModel getModel() {
		return model;
	}

	public static void setxLeHaDadoANuevaPartida(boolean xLeHaDadoANuevaPartida) {
		Bienvenido.xLeHaDadoANuevaPartida = xLeHaDadoANuevaPartida;
	}

	public static void setoLeHaDadoANuevaPartida(boolean oLeHaDadoANuevaPartida) {
		Bienvenido.oLeHaDadoANuevaPartida = oLeHaDadoANuevaPartida;
	}

	public static String[][] getMatrixStrings() {
		return matrixStrings;
	}

	public static JLabel getLblBienvenidonombre() {
		return lblBienvenidonombre;
	}

	public static JButton getBtnRecibirConexion() {
		return btnRecibirConexion;
	}

	public static JButton getBtnConectarse() {
		return btnConectarse;
	}

	public static JButton getBtnO() {
		return btnO;
	}

	public static JButton getBtnX() {
		return btnX;
	}
	
	public static boolean primerEstado() {
		if (xReady&&oReady) {
			return true;
		}
		else
			return false;
	}
	
	
	
	

	

	public static boolean isxReady() {
		return xReady;
	}

	public static boolean isoReady() {
		return oReady;
	}

	public static void setxReady(boolean xReady) {
		Bienvenido.xReady = xReady;
	}

	public static void setoReady(boolean oReady) {
		Bienvenido.oReady = oReady;
	}

	/**
	 * Create the frame.
	 */
	public Bienvenido() {
		
		et.start();
		for (double i = 0; i < 999999999; i++) {
		}
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1187, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.setBackground(new Color(252, 233, 79));
		
		lblBienvenidonombre = new JLabel("Bienvenido ");
		lblBienvenidonombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidonombre.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblBienvenidonombre.setBounds(12, 8, 535, 55);
		contentPane.add(lblBienvenidonombre);
		
		btnRecibirConexion = new JButton("Recibir conexión");
		btnRecibirConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					server = new ServerSocket(PORT);   
					//Client Socket 
					client = server.accept();
					//setSoLinger closes the socket giving 10mS to receive the remaining data
					client.setSoLinger (true, 10);
					//an input reader to read from the socket
					input = new BufferedReader(new InputStreamReader(client.getInputStream()));
					//to print data out                
					output = new PrintStream(client.getOutputStream());
					stateConnection=WITH_CONNECTION;
					ter.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ter.setInput(input);
					ter.setOutput(output);
					
					ter.executeBoxThread();
//					ter.executeEnableThread();
					ter.b.setBtnX(btnX);
					ter.b.setBtnO(btnO);
					btnRecibirConexion.setVisible(false);
					btnConectarse.setVisible(false);
					lblEligeFicha.setVisible(true);
					btnO.setVisible(true);
					btnX.setVisible(true);
					output.println("&"+Login.getUsuario());
				//	enableEdition();
				} catch (IOException ex) {
					System.err.println(ex.getMessage());
				}
			}
		});
		btnRecibirConexion.setBounds(96, 177, 133, 27);
		contentPane.add(btnRecibirConexion);
		
		btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String ip = null;
			        boolean ipValid = true;
			        while (ipValid) {
			        	ip = JOptionPane.showInputDialog(contentPane, "Ingresa una dirección IP(*.*.*.*):", "0.0.0.0");
			            if (ip.matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) {
			            	ipValid = false;
			            } else {
			                JOptionPane.showMessageDialog(contentPane, "La dirección IP no es válida, inténtalo de nuevo.");
			            }
			        }
			   //     JOptionPane.showMessageDialog(contentPane, "La dirección IP ingresada es: " + ip);
			    
					socket = new Socket(ip, PORT);//open socket                
					//To read from the server      
					input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
					//to write to the server
					output = new PrintStream(socket.getOutputStream());
					//To read from the user (keyboard)           
					stateConnection=WITH_CONNECTION;
					ter=new TresEnRaya();
					ter.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ter.setInput(input);
					ter.setOutput(output);
					
					ter.executeBoxThread();
//					ter.executeEnableThread();
					ter.b.setBtnX(btnX);
					ter.b.setBtnO(btnO);
					btnConectarse.setVisible(false);
					btnRecibirConexion.setVisible(false);
					lblEligeFicha.setVisible(true);
					btnO.setVisible(true);
					btnX.setVisible(true);
					output.println("&"+Login.getUsuario());
					
					
				//	enableEdition();
				} catch (IOException ex) {        
					System.err.println("Client -> " + ex.getMessage());   
				}
			}
		});
			
		btnConectarse.setBounds(332, 177, 105, 27);
		contentPane.add(btnConectarse);
		

		
		btnX = new JButton("X");
		btnX.setVisible(false);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TresEnRaya.setLblVictoriasDelEnemigo();
				TresEnRaya.setLblTusVictorias();
				xReady=true;
				player="X";
				output.println("soyunax");
				TresEnRaya.setPlayer(player);
				ter.b.setPlayer(player);
				ter.setVisible(true);
				setVisible(false);
				ter.getPanel_Game().repaint();
				if (xReady&&!oReady) {
					for (SquareButton squareButton : ter.sqA) {
						squareButton.setEnabled(false);
						squareButton.setText("");
					}
				}
				else if (oReady&&xReady) {
					for (SquareButton squareButton : ter.sqA) {
						squareButton.setEnabled(true);
						squareButton.setText("");
						TresEnRaya.getBtnRendirse().setVisible(true);
						TresEnRaya.getBtnRendirse().setEnabled(true);
						TresEnRaya.getBtnNuevaPartida().setVisible(false);
					}
					output.println("Im ready");
				}
				
			}
		});
		btnX.setBounds(228, 177, 105, 27);
		contentPane.add(btnX);
		
		lblEligeFicha = new JLabel("Elige ficha");
		lblEligeFicha.setFont(new Font("Latin Modern Math", Font.BOLD, 20));
		lblEligeFicha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeFicha.setBounds(12, 75, 535, 64);
		lblEligeFicha.setVisible(false);
		contentPane.add(lblEligeFicha);
		
		
		
		
		
		btnO = new JButton("O");
		btnO.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TresEnRaya.setLblVictoriasDelEnemigo();
				TresEnRaya.setLblTusVictorias();
				oReady=true;
				player="O";
				TresEnRaya.setPlayer(player);
				output.println("soyunao");
				ter.b.setPlayer(player);
				ter.setVisible(true);
				setVisible(false);
				ter.getPanel_Game().repaint();
				if (oReady&&!xReady) {
					for (SquareButton squareButton : ter.sqA) {
						squareButton.setEnabled(false);
						squareButton.setText("");
					}
				}
				else if (oReady&&xReady) {
					for (SquareButton squareButton : ter.sqA) {
						squareButton.setEnabled(true);
						squareButton.setText("");
						TresEnRaya.getBtnRendirse().setVisible(true);
						TresEnRaya.getBtnRendirse().setEnabled(true);
						TresEnRaya.getBtnNuevaPartida().setVisible(false);
					}
					output.println("Im ready");
				}
			}
		});
		btnO.setBounds(228, 240, 105, 27);
		contentPane.add(btnO);
		
		JLabel lblRanking = new JLabel("Ranking");
		lblRanking.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(547, 8, 620, 55);
		contentPane.add(lblRanking);
		
		
		

		

		table.setShowGrid(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.setRowHeight(40);
		table.setBackground(new Color(238,238,238));
		table.setFont(new Font("FreeSans", Font.BOLD, 17));
		    
        
        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer() {
            // Sobrescribir el método getTableCellRendererComponent
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Llamar al método de la superclase para obtener el componente de celda predeterminado
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verificar si la fila actual es la que deseas cambiar el fondo
                if (row == EnableThread.getFilaUsuario()) {
                    cell.setBackground(Color.ORANGE);
                } else {
                    // Establecer el fondo predeterminado para las demás filas
                    cell.setBackground(table.getBackground());
                }
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

                return cell;
            }
        };
        
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Establecer la fuente y el tipo de letra
                setFont(new Font("FreeSans", Font.BOLD, 19));
                
                // Establecer alineacion
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                
                // Establecer el color de fondo
                setBackground(new Color(238,238,238));
                
                // Establecer el color de la línea
                setForeground(Color.BLACK);
                
                // Establecer el borde inferior
                Border border = new MatteBorder(0, 0, 3, 0, Color.BLACK);
                setBorder(border);
                
                return this;
            }
        };
        
		

        // Establecer el centrado para todas las columnas
        for (int i = 0; i < table.getColumnCount(); i++) {
            
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            table.getColumnModel().getColumn(i).setResizable(false);
            table.getColumnModel().getColumn(i).setCellRenderer(renderizador);
            
        }
		
		
		JScrollPane panelRanking = new JScrollPane(table);
		panelRanking.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelRanking.setBounds(547, 63, 620, 366);
		contentPane.add(panelRanking);
		
		JPanel panelPosicion = new JPanel();
		panelPosicion.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelPosicion.setBackground(Color.ORANGE);
		panelPosicion.setBounds(547, 428, 620, 35);
		contentPane.add(panelPosicion);
		panelPosicion.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" " + Login.getUsuario() + " estas en la posicion: " + (EnableThread.getFilaUsuario()+1));
		lblNewLabel.setBounds(0, 0, 620, 33);
		panelPosicion.add(lblNewLabel);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setFont(new Font("FreeSans", Font.BOLD, 20));
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login.lol.setVisible(true);
			}
		});
		btnAtras.setBounds(31, 434, 89, 23);
		contentPane.add(btnAtras);
		
		
		
	}
}
