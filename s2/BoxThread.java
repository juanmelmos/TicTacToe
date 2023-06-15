package s2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BoxThread extends Thread {
	
	private BufferedReader in;
	protected ArrayList<SquareButton> sq = new ArrayList<SquareButton>();
	
	protected int num;
	protected String player=null;
	private JButton btnO;
	private JButton btnX;
	private JPanel contentPane;
	private static JButton btnNuevaPartida;
	private static TresEnRaya frameTresEnRaya;
	protected PreparedStatement ps;
	
	public BoxThread(ArrayList<SquareButton> sq,BufferedReader in, String player) {
		this.sq=sq;
		this.in=in;
		this.player=player;
	}
	
	
	
	public static void setFrameTresEnRaya(TresEnRaya frameTresEnRaya) {
		BoxThread.frameTresEnRaya = frameTresEnRaya;
	}


	public static void setBtnNuevaPartida(JButton btnNuevaPartida) {
		BoxThread.btnNuevaPartida = btnNuevaPartida;
	}


	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public void setPlayer(String player) {
		this.player=player;
	}
	
	public void setBtnO(JButton btnO) {
		this.btnO = btnO;
	}

	public void setBtnX(JButton btnX) {
		this.btnX = btnX;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String str = in.readLine();
				if (str != null) {

					if (str.equals("xNuevaPartida")) {
						Bienvenido.setxLeHaDadoANuevaPartida(true);
						Bienvenido.getBtnO().setEnabled(true);
						Bienvenido.getBtnX().setEnabled(true);
					}
					
					else if (str.equals("oNuevaPartida")) {
						Bienvenido.setoLeHaDadoANuevaPartida(true);
						Bienvenido.getBtnO().setEnabled(true);
						Bienvenido.getBtnX().setEnabled(true);
					}
					
					
					else if (str.equals("soyunax")) {
						btnX.setEnabled(false);
						Bienvenido.setxReady(true);
						for (SquareButton squareButton : sq) {
							squareButton.setEnabled(true);
							squareButton.setText("");
						}
					}
					else if (str.equals("soyunao")) {
						btnO.setEnabled(false);
						Bienvenido.setoReady(true);
						for (SquareButton squareButton : sq) {
							squareButton.setEnabled(true);
							squareButton.setText("");
						}
					}

					else if (str.equals("heganado")) {
						for (SquareButton squareButton : sq) {
							squareButton.setEnabled(false);
						}
						TresEnRaya.getBtnRendirse().setEnabled(false);
						JOptionPane.showMessageDialog(contentPane, "Has perdido", "Derrota", JOptionPane.OK_OPTION);
						btnNuevaPartida.setVisible(true);
						btnO.setEnabled(true);
						btnX.setEnabled(true);
						TresEnRaya.getBtnRendirse().setVisible(false);
						try {
							String update = "UPDATE Usuarios SET Derrotas = Derrotas+1 WHERE Usuario=?";
							ps = Aws.getConnection().prepareStatement(update);
							Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
							ps.execute();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						TresEnRaya.setLblTusVictorias();
						TresEnRaya.setLblVictoriasDelEnemigo();
					}

					else if (str.equals("tablas")) {
						TresEnRaya.getBtnRendirse().setEnabled(false);
						JOptionPane.showMessageDialog(contentPane, "Empate", "Tablas", JOptionPane.OK_OPTION);
						btnNuevaPartida.setVisible(true);
						btnO.setEnabled(true);
						btnX.setEnabled(true);
						TresEnRaya.getBtnRendirse().setVisible(false);
						try {
							String update = "UPDATE Usuarios SET Empates = Empates+1 WHERE Usuario=?";
							ps = Aws.getConnection().prepareStatement(update);
							Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
							ps.execute();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						TresEnRaya.setLblTusVictorias();
						TresEnRaya.setLblVictoriasDelEnemigo();
					}
					else if (str.equals("merindo")) {
						for (SquareButton squareButton : sq) {
							squareButton.setEnabled(false);
						}
						TresEnRaya.getBtnRendirse().setEnabled(false);
						JOptionPane.showMessageDialog(contentPane, "¡Enhorabuena, el rival se ha rendido!", "Victoria", JOptionPane.OK_OPTION);
						btnNuevaPartida.setVisible(true);
						TresEnRaya.getBtnRendirse().setEnabled(false);
						try {
							String update = "UPDATE Usuarios SET Victorias = Victorias+1 WHERE Usuario=?";
							ps = Aws.getConnection().prepareStatement(update);
							Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
							ps.execute();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						TresEnRaya.setLblTusVictorias();
						TresEnRaya.setLblVictoriasDelEnemigo();
					}
					else if (str.equals("Im ready")) {
						TresEnRaya.getBtnRendirse().setVisible(true);
						TresEnRaya.getBtnNuevaPartida().setVisible(false);
						TresEnRaya.getBtnRendirse().setEnabled(true);
					}
					else if (str.indexOf('&')==0) {
						TresEnRaya.usuarioEnemigo=str.substring(1,str.length());
					}
					else if (str.equals("inactivo")) {
						for (SquareButton squareButton : sq) {
							squareButton.setEnabled(false);
						}
						JOptionPane.showMessageDialog(contentPane, "¡Enhorabuena, el rival se ha rendido!", "Victoria", JOptionPane.OK_OPTION);
					}
					else {
						num = Integer.parseInt(str);
						if (player.equals("X")) {
							sq.get(num).setText("O");
						}
						else if (player.equals("O")) {
							sq.get(num).setText("X");
						}
						sq.get(num).setEnabled(false);
						for (SquareButton squareButton : sq) {
							if (squareButton.getText().equals("")) {
								squareButton.setEnabled(true);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	

	
	
	
}
