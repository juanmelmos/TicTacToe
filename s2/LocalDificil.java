package s2;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.FocusManager;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

public class LocalDificil extends JFrame {

	private JPanel contentPane;
	private JPanel panel_Game;
	protected static ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected static String player="X";
	protected JTextArea textArea_chat;
	protected static JButton btnNuevaPartida;
	protected static JButton btnJugarDeNuevo;
	private static JLabel lblPersonaje=null;
	protected PreparedStatement ps;
	private static boolean turnoPlayer=true;
	protected WThreadDificil thread = new WThreadDificil(sqA);
	
	
	
	

	

	public static void setTurnoPlayer(boolean turnoPlayer) {
		LocalDificil.turnoPlayer = turnoPlayer;
	}

	public static String getPlayer() {
		return player;
	}

	public static boolean isTurnoPlayer() {
		return turnoPlayer;
	}

	public static JButton getBtnNuevaPartida() {
		return btnNuevaPartida;
	}

	public static JButton getBtnRendirse() {
		return btnJugarDeNuevo;
	}

	public static void setPlayer(String player) {
		LocalDificil.player = player;
		lblPersonaje.setText("Tu personaje es: "+player);
	}

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new LocalDificil();
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
	public LocalDificil() {

		setResizable(false);

		
		WThreadDificil.setPlayerMaquina(player);
		
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
		SquareButton sq1= new SquareButton("");
		SquareButton sq2= new SquareButton("");
		SquareButton sq3= new SquareButton("");
		SquareButton sq4= new SquareButton("");
		SquareButton sq5= new SquareButton("");
		SquareButton sq6= new SquareButton("");
		SquareButton sq7= new SquareButton("");
		SquareButton sq8= new SquareButton("");
		SquareButton sq9= new SquareButton("");
		
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
		lblPersonaje.setBounds(391, 28, 261, 17);
		panel_SerCli.add(lblPersonaje);
		
		btnJugarDeNuevo = new JButton("Jugar de nuevo");
		btnJugarDeNuevo.setVisible(false);
		btnJugarDeNuevo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnJugarDeNuevo.setForeground(new Color(0, 0, 0));
		btnJugarDeNuevo.setBackground(new Color(0, 204, 51));
		btnJugarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (SquareButton squareButton : sqA) {
					squareButton.setText("");
					squareButton.setEnabled(true);
				}
				LocalDificil.setTurnoPlayer(true);
				LocalDificil.actualizarEstado();
				btnJugarDeNuevo.setVisible(false);
			}
		});
		btnJugarDeNuevo.setBounds(55, 231, 105, 27);
		panel_SerCli.add(btnJugarDeNuevo);
		
		JButton btnVolver = new JButton("Volver atras");
		btnVolver.setForeground(new Color(0, 0, 0));
		btnVolver.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnVolver.setBackground(new Color(255, 0, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login.lol.setVisible(true);
			}
		});
		btnVolver.setBounds(55, 269, 105, 27);
		panel_SerCli.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("TIC TAC TOE");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Latin Modern Math", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 12, 1069, 57);
		panel_SerCli.add(lblNewLabel);
		

		thread.start();
	}
	public void send(ArrayList<SquareButton> sqA, int num) {
		sqA.get(num).setText(player);;
		sqA.get(num).setEnabled(false);
		turnoPlayer=false;
		actualizarEstado();
		int count=0;
		for (SquareButton squareButton : sqA) {
			if(!squareButton.getText().equals("")) {
				count++;
			}
		}
		WThreadDificil.casillasMarcadas=count;
	}
	public static void actualizarEstado() {
		if (turnoPlayer) {
			for (SquareButton squareButton : sqA) {
				if (squareButton.getText().equals("")) {
					squareButton.setEnabled(true);
				}

			}
		}
		else
			for (SquareButton squareButton : sqA) {
				squareButton.setEnabled(false);
			}
	}
}