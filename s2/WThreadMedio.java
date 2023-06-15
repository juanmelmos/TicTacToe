package s2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class WThreadMedio extends Thread {
	protected static ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected Random r = new Random();
	protected boolean turnoPlayer=LocalMedio.isTurnoPlayer();
	protected static String player=LocalMedio.getPlayer();
	protected PreparedStatement ps;
	protected static String playerMaquina;

	public WThreadMedio(ArrayList<SquareButton> sqA) {
		WThreadMedio.sqA=sqA;
	}

	public static void setPlayerMaquina(String player) {
		if (player.equals("X")) {
			playerMaquina="O";
		}
		else if (player.equals("O")) {
			playerMaquina="X";
		}
	}
	
	
	public void run() {
		Aws.connect(false);
		while (true) {
			try {
				this.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			turnoPlayer=LocalMedio.isTurnoPlayer();
			if (!turnoPlayer) {
				//SABER SI GANA EL JUGADOR
				if ((sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player))||(sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player))||(sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player))||(sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player))) {
					for (SquareButton squareButton : sqA) {
						squareButton.setEnabled(false);
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.btnJugarDeNuevo.setVisible(true);
					JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has ganado", "Victoria!", JOptionPane.INFORMATION_MESSAGE);
					try {
						String update = "UPDATE Usuarios SET VictoriasLocalMedio = VictoriasLocalMedio+1 WHERE Usuario=?";
						ps = Aws.getConnection().prepareStatement(update);
						Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
						ps.execute();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals("")) {

					if (player.equals("X")) {
						sqA.get(2).setText("O");
					}
					else {
						sqA.get(2).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(0).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(0).setText("O");
					}
					else {
						sqA.get(0).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(5).setText("O");
					}
					else {
						sqA.get(5).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(3).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(3).setText("O");
					}
					else {
						sqA.get(3).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(8).setText("O");
					}
					else {
						sqA.get(8).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(6).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(6).setText("O");
					}
					else {
						sqA.get(6).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(6).setText("O");
					}
					else {
						sqA.get(6).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(0).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(0).setText("O");
					}
					else {
						sqA.get(0).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(7).setText("O");
					}
					else {
						sqA.get(7).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(1).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(1).setText("O");
					}
					else {
						sqA.get(1).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(8).setText("O");
					}
					else {
						sqA.get(8).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(2).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(2).setText("O");
					}
					else {
						sqA.get(2).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(8).setText("O");
					}
					else {
						sqA.get(8).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(0).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(0).setText("O");
					}
					else {
						sqA.get(0).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(6).setText("O");
					}
					else {
						sqA.get(6).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(2).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(2).setText("O");
					}
					else {
						sqA.get(2).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(1).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(1).setText("O");
					}
					else {
						sqA.get(1).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(3).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(4).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(4).setText("O");
					}
					else {
						sqA.get(4).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(6).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(7).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(7).setText("O");
					}
					else {
						sqA.get(7).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(3).getText().equals("")) {

					if (player.equals("X")) {
						sqA.get(3).setText("O");
					}
					else {
						sqA.get(3).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(1).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(4).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(4).setText("O");
					}
					else {
						sqA.get(4).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();
				}
				else if (sqA.get(2).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(5).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(5).setText("O");
					}
					else {
						sqA.get(5).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(0).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(4).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(4).setText("O");
					}
					else {
						sqA.get(4).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}
				else if (sqA.get(2).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(4).getText().equals("")) {
					if (player.equals("X")) {
						sqA.get(4).setText("O");
					}
					else {
						sqA.get(4).setText("X");
					}
					LocalMedio.setTurnoPlayer(true);
					LocalMedio.actualizarEstado();

				}

				else {
					int num=r.nextInt(9);
					int count=0;
					for (SquareButton squareButton : sqA) {
						if(squareButton.getText().equals("")) {
							count++;
						}
					}
					if (count==0) {
						LocalMedio.btnJugarDeNuevo.setVisible(true);
						LocalMedio.setTurnoPlayer(true);
						LocalMedio.actualizarEstado();
						JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has empatado", "Tablas!", JOptionPane.INFORMATION_MESSAGE);
						try {
							String update = "UPDATE Usuarios SET EmpatesLocalMedio = EmpatesLocalMedio+1 WHERE Usuario=?";
							ps = Aws.getConnection().prepareStatement(update);
							Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
							ps.execute();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else {
						if (sqA.get(num).getText().equals("")) {


							if (player.equals("X")) {
								sqA.get(num).setText("O");
							}
							else {
								sqA.get(num).setText("X");
							}

							LocalMedio.setTurnoPlayer(true);
							LocalMedio.actualizarEstado();

						}
					}
					if ((sqA.get(0).getText().equals(playerMaquina)&&sqA.get(1).getText().equals(playerMaquina)&&sqA.get(2).getText().equals(playerMaquina))||(sqA.get(3).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina))||(sqA.get(6).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(0).getText().equals(playerMaquina)&&sqA.get(3).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina))||(sqA.get(1).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina))||(sqA.get(2).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(0).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(2).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina))) {
						for (SquareButton squareButton : sqA) {
							squareButton.setEnabled(false);
						}
						LocalMedio.setTurnoPlayer(true);
						LocalMedio.btnJugarDeNuevo.setVisible(true);
						JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has perdido", "Derrot!", JOptionPane.ERROR_MESSAGE);
						try {
							String update = "UPDATE Usuarios SET DerrotasLocalMedio = DerrotasLocalMedio+1 WHERE Usuario=?";
							ps = Aws.getConnection().prepareStatement(update);
							Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
							ps.execute();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

}
