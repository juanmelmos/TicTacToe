package s2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class WThreadFacil extends Thread {
	protected static ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected Random r = new Random();
	protected boolean turnoPlayer=LocalFacil.isTurnoPlayer();
	protected static String player=LocalFacil.getPlayer();
	protected PreparedStatement ps;
	protected static String playerMaquina;

	public WThreadFacil(ArrayList<SquareButton> sqA) {
		WThreadFacil.sqA=sqA;
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
		Aws.connect(true);
		while (true) {
			try {
				this.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			turnoPlayer=LocalFacil.isTurnoPlayer();
			if (!turnoPlayer) {
				//SABER SI GANA EL JUGADOR
				if ((sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player))||(sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player))||(sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player))||(sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player))) {
					for (SquareButton squareButton : sqA) {
						squareButton.setEnabled(false);
					}
					LocalFacil.setTurnoPlayer(true);
					LocalFacil.btnJugarDeNuevo.setVisible(true);
					JOptionPane.showMessageDialog(LocalFacil.contentPane, "Has ganado", "Victoria!", JOptionPane.INFORMATION_MESSAGE);
					try {
						String update = "UPDATE Usuarios SET VictoriasLocalFacil = VictoriasLocalFacil+1 WHERE Usuario=?";
						ps = Aws.getConnection().prepareStatement(update);
						Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
						ps.execute();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
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
					LocalFacil.btnJugarDeNuevo.setVisible(true);
					LocalFacil.setTurnoPlayer(true);
					LocalFacil.actualizarEstado();
					JOptionPane.showMessageDialog(LocalFacil.contentPane, "Has empatado", "Tablas!", JOptionPane.INFORMATION_MESSAGE);
					try {
						String update = "UPDATE Usuarios SET EmpatesLocalFacil = EmpatesLocalFacil+1 WHERE Usuario=?";
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

						LocalFacil.setTurnoPlayer(true);
						LocalFacil.actualizarEstado();
						if ((sqA.get(0).getText().equals(playerMaquina)&&sqA.get(1).getText().equals(playerMaquina)&&sqA.get(2).getText().equals(playerMaquina))||(sqA.get(3).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina))||(sqA.get(6).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(0).getText().equals(playerMaquina)&&sqA.get(3).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina))||(sqA.get(1).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina))||(sqA.get(2).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(0).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina))||(sqA.get(2).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina))) {
							for (SquareButton squareButton : sqA) {
								squareButton.setEnabled(false);
							}
							LocalFacil.setTurnoPlayer(true);
							LocalFacil.btnJugarDeNuevo.setVisible(true);
							JOptionPane.showMessageDialog(LocalFacil.contentPane, "Has perdido", "Derrota!", JOptionPane.ERROR_MESSAGE);
							try {
								String update = "UPDATE Usuarios SET DerrotasLocalFacil = DerrotasLocalFacil+1 WHERE Usuario=?";
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
	}

}
