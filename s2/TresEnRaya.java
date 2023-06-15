package s2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
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

import javax.swing.BoxLayout;
import javax.swing.FocusManager;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TresEnRaya extends JFrame {

	private JPanel contentPane;
	private JPanel panel_Game;
	protected ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected static String player;
	private final int PORT = 5006;
	private PrintStream output;
	private BufferedReader input;
	private Socket client;
	private  ServerSocket server;
	protected Socket socket;
	protected String user;
	protected JTextArea textArea_chat;
	protected BoxThread b;
	protected EnableThread e;
	protected int statePC;
	private static JButton btnNuevaPartida;
	private static JButton btnRendirse;
	private static JLabel lblPersonaje=null;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static String select;
	protected static String usuarioEnemigo;
	private static JLabel lblVictoriasDelEnemigo = new JLabel("error");
	private static JLabel lblTusVictorias = new JLabel("error");
	
	
	
	
	
	

	public static void setLblVictoriasDelEnemigo() {
		int victoriasEnemigo=0;
		try {
			select = "select Victorias from Usuarios where Usuario = ?";
			ps =  Aws.getConnection().prepareStatement(select);
			ps.setString(1, usuarioEnemigo);
			rs = ps.executeQuery();
			rs.next();
			victoriasEnemigo=rs.getInt(1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		lblVictoriasDelEnemigo.setText("Victorias de "+usuarioEnemigo+" : "+victoriasEnemigo);
	}
	
	public static void setLblTusVictorias() {
		int tusVictorias=0;
		try {
		select = "select Victorias from Usuarios where Usuario = ?";
		ps =  Aws.getConnection().prepareStatement(select);
		ps.setString(1, Login.getUsuario());
		rs = ps.executeQuery();
		rs.next();
		tusVictorias=rs.getInt(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		lblTusVictorias.setText("Tus victorias : "+tusVictorias);
	}

	public static JButton getBtnNuevaPartida() {
		return btnNuevaPartida;
	}

	public static JButton getBtnRendirse() {
		return btnRendirse;
	}

	public static void setPlayer(String player) {
		TresEnRaya.player = player;
		lblPersonaje.setText("Tu personaje es: "+player);
	}

	public void setOutput(PrintStream output) {
		this.output = output;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}
	

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new TresEnRaya();
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
	public TresEnRaya() {
//		addWindowStateListener(new WindowStateListener() {
//			public void windowStateChanged(WindowEvent e) {
//				panel_Game.repaint();
//			}
	//	});
		setResizable(false);
//		if(Bienvenido.primerEstado()) {
//			for (SquareButton squareButton : sqA) {
//				squareButton.setEnabled(true);
//				squareButton.setText("");
//			}
//		}
		Aws.connect(false);
		setTitle("Tres En Raya");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1081, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel_SerCli = new JPanel();
		panel_SerCli.setBackground(new Color(252, 233, 79));
		contentPane.add(panel_SerCli);
		panel_SerCli.setLayout(null);
		
		panel_Game = new JPanel();
		panel_Game.setBounds(327, 74, 402, 352);
		panel_SerCli.add(panel_Game);
		panel_Game.setLayout(new GridLayout(3, 3, 0, 0));
		SquareButton sq1= new SquareButton();
		SquareButton sq2= new SquareButton();
		SquareButton sq3= new SquareButton();
		SquareButton sq4= new SquareButton();
		SquareButton sq5= new SquareButton();
		SquareButton sq6= new SquareButton();
		SquareButton sq7= new SquareButton();
		SquareButton sq8= new SquareButton();
		SquareButton sq9= new SquareButton();
		
		sqA.add(sq1);
		sqA.add(sq2);
		sqA.add(sq3);
		sqA.add(sq4);
		sqA.add(sq5);
		sqA.add(sq6);
		sqA.add(sq7);
		sqA.add(sq8);
		sqA.add(sq9);
		
		for (SquareButton squareButton : sqA) {
			squareButton.setFont(new Font("Latin Modern Math", Font.BOLD, 40));
		}
		
		panel_Game.add(sq1);
		panel_Game.add(sq2);
		panel_Game.add(sq3);
		panel_Game.add(sq4);
		panel_Game.add(sq5);
		panel_Game.add(sq6);
		panel_Game.add(sq7);
		panel_Game.add(sq8);
		panel_Game.add(sq9);
		
		
		
		sq1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 0);
				
			}
		});
		
		sq2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 1);
			}
		});
		
		sq3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 2);
			}
		});
		
		sq4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 3);
			}
		});
		
		sq5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 4);
			}
		});
		
		sq6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 5);
			}
		});
		
		sq7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 6);
			}
		});
		
		sq8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 7);
			}
		});
		
		sq9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(sqA, 8);
			}
		});
		
		lblPersonaje = new JLabel();
		lblPersonaje.setBounds(12, 198, 261, 17);
		panel_SerCli.add(lblPersonaje);
		
		btnRendirse = new JButton("Rendirse");
		btnRendirse.setForeground(new Color(0, 0, 0));
		btnRendirse.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnRendirse.setBackground(new Color(255, 0, 0));
		btnRendirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.println("merindo");
				for (SquareButton squareButton : sqA) {
					squareButton.setEnabled(false);
				}
				btnRendirse.setEnabled(false);
				JOptionPane.showMessageDialog(contentPane, "Te has rendido", "Derrota", JOptionPane.OK_OPTION);
				btnNuevaPartida.setVisible(true);
				try {
					String update = "UPDATE Usuarios SET Derrotas = Derrotas+1 WHERE Usuario=?";
					ps = Aws.getConnection().prepareStatement(update);
					Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
					ps.execute();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				setLblTusVictorias();
				setLblVictoriasDelEnemigo();
			}
		});
		btnRendirse.setBounds(55, 231, 105, 27);
		panel_SerCli.add(btnRendirse);
		

		lblVictoriasDelEnemigo.setFont(new Font("Latin Modern Math", Font.BOLD, 20));
		lblVictoriasDelEnemigo.setBounds(12, 138, 297, 48);
		panel_SerCli.add(lblVictoriasDelEnemigo);
		
		
		lblTusVictorias.setFont(new Font("Latin Modern Math", Font.BOLD, 20));
		lblTusVictorias.setBounds(12, 78, 297, 48);
		panel_SerCli.add(lblTusVictorias);
		
		btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNuevaPartida.setForeground(new Color(0, 0, 0));
		btnNuevaPartida.setBackground(new Color(0, 204, 51));
		btnNuevaPartida.setVisible(false);
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LocalOrOnline.getBi().setVisible(true);
				Bienvenido.getBtnRecibirConexion().setVisible(false);
				Bienvenido.getBtnConectarse().setVisible(false);
				Bienvenido.getBtnO().setEnabled(false);
				Bienvenido.getBtnX().setEnabled(false);
				if (player.equals("X")) {
					Bienvenido.setxLeHaDadoANuevaPartida(true);
					output.println("xNuevaPartida");
				}
				else if (player.equals("O")) {
					Bienvenido.setoLeHaDadoANuevaPartida(true);
					output.println("oNuevaPartida");
				}
				if (Bienvenido.xLeHaDadoANuevaPartida&&Bienvenido.oLeHaDadoANuevaPartida) {
					Bienvenido.getBtnO().setEnabled(true);
					Bienvenido.getBtnX().setEnabled(true);
				}
				Bienvenido.getBtnO().repaint();
				Bienvenido.getBtnX().repaint();
//				for (SquareButton squareButton : sqA) {
//					squareButton.setEnabled(true);
//					squareButton.setText("");
//				}
			}
		});
		btnNuevaPartida.setBounds(55, 304, 105, 27);
		panel_SerCli.add(btnNuevaPartida);
		
		JLabel lblNewLabel = new JLabel("TIC TAC TOE");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 12, 1069, 57);
		panel_SerCli.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TresEnRaya.class.getResource("../img/anuncio.jpeg")));
		lblNewLabel_1.setBounds(747, 74, 297, 352);
		panel_SerCli.add(lblNewLabel_1);
		
		JLabel tunombre = new JLabel(Login.getUsuario());
		tunombre.setHorizontalTextPosition(SwingConstants.CENTER);
		tunombre.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		tunombre.setHorizontalAlignment(SwingConstants.CENTER);
		tunombre.setBounds(12, 12, 148, 48);
		panel_SerCli.add(tunombre);
		
		
	}
	
	
	public void send(ArrayList<SquareButton> sqA, int num) {
		Bienvenido.setoReady(false);
		Bienvenido.setxReady(false);
		Bienvenido.setxLeHaDadoANuevaPartida(false);
		Bienvenido.setoLeHaDadoANuevaPartida(false);
		output.println(num);
		sqA.get(num).setText(player);;
		sqA.get(num).setEnabled(false);
		for (SquareButton squareButton : sqA) {
			squareButton.setEnabled(false);
		}
		
		if ((sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player))||(sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player))||(sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player))||(sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player))) {
			output.println("heganado");
			TresEnRaya.getBtnRendirse().setEnabled(false);
			JOptionPane.showMessageDialog(contentPane, "¡Enhorabuena, has ganado!", "Victoria", JOptionPane.OK_OPTION);
			btnNuevaPartida.setVisible(true);
			TresEnRaya.getBtnRendirse().setVisible(false);
			try {
			String update = "UPDATE Usuarios SET Victorias = Victorias+1 WHERE Usuario=?";
			ps = Aws.getConnection().prepareStatement(update);
			Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
			ps.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			setLblTusVictorias();
			setLblVictoriasDelEnemigo();
		}
		else if (!sqA.get(0).getText().equals("")&&!sqA.get(1).getText().equals("")&&!sqA.get(2).getText().equals("")&&!sqA.get(3).getText().equals("")&&!sqA.get(4).getText().equals("")&&!sqA.get(5).getText().equals("")&&!sqA.get(6).getText().equals("")&&!sqA.get(7).getText().equals("")&&!sqA.get(8).getText().equals("")) {
			output.println("tablas");
			btnRendirse.setEnabled(false);
			JOptionPane.showMessageDialog(contentPane, "Empate", "Tablas", JOptionPane.OK_OPTION);
			btnRendirse.setVisible(false);
			btnNuevaPartida.setVisible(true);
			try {
				String update = "UPDATE Usuarios SET Empates = Empates+1 WHERE Usuario=?";
				ps = Aws.getConnection().prepareStatement(update);
				Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
				ps.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			setLblTusVictorias();
			setLblVictoriasDelEnemigo();
		}
	}
	
	public JPanel getPanel_Game() {
		return panel_Game;
	}

	public void executeBoxThread() {
		b=new BoxThread(sqA,input, player);
		b.start();
		b.setContentPane(contentPane);
		b.setBtnNuevaPartida(btnNuevaPartida);
	}
}
