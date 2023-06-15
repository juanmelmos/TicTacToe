package s2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class WThreadImposible extends Thread {
	protected static ArrayList<SquareButton> sqA = new ArrayList<SquareButton>();
	protected Random r = new Random();
	protected boolean turnoPlayer=LocalImposible.isTurnoPlayer();
	protected static String player=LocalImposible.getPlayer();
	protected static int casillasMarcadas;
	protected static String playerMaquina;
	protected static boolean empiezaMedio;
	protected static boolean empiezaEsquina;
	protected static boolean empiezaLado;
	protected static PreparedStatement ps;

	public WThreadImposible(ArrayList<SquareButton> sqA) {
		WThreadImposible.sqA=sqA;
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
			turnoPlayer=LocalImposible.isTurnoPlayer();
			if (!turnoPlayer) {
				//SABER SI GANA EL JUGADOR
				if ((sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player))||(sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player))||(sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player))||(sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player))) {
					for (SquareButton squareButton : sqA) {
						squareButton.setEnabled(false);
					}
					LocalImposible.setTurnoPlayer(true);
					LocalImposible.btnJugarDeNuevo.setVisible(true);
					empiezaMedio=false;
					empiezaEsquina=false;
					empiezaLado=false;
					JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has ganado", "Victoria!", JOptionPane.INFORMATION_MESSAGE);
					try {
						String update = "UPDATE Usuarios SET VictoriasLocalImposible = VictoriasLocalImposible+1 WHERE Usuario=?";
						ps = Aws.getConnection().prepareStatement(update);
						Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
						ps.execute();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
				if (casillasMarcadas!=1) {
				if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(1).getText().equals(playerMaquina)&&sqA.get(2).getText().equals("")) {

					responder(2);
					ganaMaquina();
					

				}
				else if (sqA.get(1).getText().equals(playerMaquina)&&sqA.get(2).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")) {
					responder(0);
					ganaMaquina();
				}
				else if (sqA.get(3).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(5).getText().equals("")) {
					responder(5);
					ganaMaquina();

				}
				else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina)&&sqA.get(3).getText().equals("")) {
					responder(3);
					ganaMaquina();
				}
				else if (sqA.get(6).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina)&&sqA.get(8).getText().equals("")) {
					responder(8);
					ganaMaquina();
				}
				else if (sqA.get(7).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(6).getText().equals("")) {
					responder(6);
					ganaMaquina();

				}
				else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(3).getText().equals(playerMaquina)&&sqA.get(6).getText().equals("")) {
					responder(6);
					ganaMaquina();
				}
				else if (sqA.get(3).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")) {
					responder(0);
					ganaMaquina();
				}
				else if (sqA.get(1).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(7).getText().equals("")) {
					responder(7);
					ganaMaquina();
				}
				else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina)&&sqA.get(1).getText().equals("")) {
					responder(1);
					ganaMaquina();
				}
				else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina)&&sqA.get(8).getText().equals("")) {
					responder(8);
					ganaMaquina();
				}
				else if (sqA.get(5).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(2).getText().equals("")) {
					responder(2);
					ganaMaquina();
				}
				else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(8).getText().equals("")) {
					responder(8);
					ganaMaquina();

				}
				else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")) {
					responder(0);
					ganaMaquina();
				}
				else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(4).getText().equals(playerMaquina)&&sqA.get(6).getText().equals("")) {
					responder(6);
					ganaMaquina();
				}
				else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina)&&sqA.get(2).getText().equals("")) {
					responder(2);
					ganaMaquina();
				}
				else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(2).getText().equals(playerMaquina)&&sqA.get(1).getText().equals("")) {
					responder(1);
					ganaMaquina();

				}
				else if (sqA.get(3).getText().equals(playerMaquina)&&sqA.get(5).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")) {
					responder(4);
					ganaMaquina();
				}
				else if (sqA.get(6).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(7).getText().equals("")) {
					responder(7);
					ganaMaquina();
				}
				else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina)&&sqA.get(3).getText().equals("")) {

					responder(3);
					ganaMaquina();
				}
				else if (sqA.get(1).getText().equals(playerMaquina)&&sqA.get(7).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")) {
					responder(4);
					ganaMaquina();
				}
				else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(5).getText().equals("")) {
					responder(5);
					ganaMaquina();

				}
				else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")) {
					responder(4);
					ganaMaquina();

				}
				else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")) {
					responder(4);
					ganaMaquina();

				}


				}
				if (casillasMarcadas==1) {
					if (sqA.get(4).getText().equals(player)) {
						empiezaMedio=true;
						int random = r.nextInt(3);
						if (random==0) {
							responder(0);
						}
						else if (random==1) {
							responder(2);
						}
						else if (random==2) {
							responder(6);
						}
						else if (random==3) {
							responder(8);
						}

					}
					else if (sqA.get(0).getText().equals(player)||sqA.get(2).getText().equals(player)||sqA.get(6).getText().equals(player)||sqA.get(8).getText().equals(player)) {
						empiezaEsquina=true;
						responder(4);
					}
					else if (sqA.get(1).getText().equals(player)||sqA.get(3).getText().equals(player)||sqA.get(5).getText().equals(player)||sqA.get(7).getText().equals(player)) {
						empiezaLado=true;
						responder(4);
					}
				}
				else if (empiezaMedio) {
					int random = r.nextInt(1); 
					if (defenderseSinAleatorio()){}//COMPROBAR SI RESPONDE
					else if (((sqA.get(0).getText().equals(playerMaquina)&&sqA.get(8).getText().equals(player))||(sqA.get(8).getText().equals(playerMaquina)&&sqA.get(0).getText().equals(player)))) {
						if (random==0&&sqA.get(2).getText().equals("")) {
							responder(2);
						}
						else if (random==1&&sqA.get(6).getText().equals("")) {
							responder(6);
						}
						else {
							defenderse();
						}
					}							//EN UNA ESQUINA CONTRARIA
					else if ((sqA.get(2).getText().equals(playerMaquina)&&sqA.get(6).getText().equals(player))||(sqA.get(6).getText().equals(playerMaquina)&&sqA.get(2).getText().equals(player))) {
						if (random==0&&sqA.get(0).getText().equals("")) {
							responder(0);
						}
						else if (random==1&&sqA.get(8).getText().equals("")) {
							responder(8);
						}
						else {
							defenderse();
						}
					}
					else { //DEFENDERSE
						defenderse();
					}
				}
				else if (empiezaEsquina) {
					int random = r.nextInt(1);
					if(defenderseSinAleatorio()) {}
					//COMPROBAR SI RESPONDE //EN UNA ESQUINA CONTRARIA
					else if ((sqA.get(0).getText().equals(player)&&sqA.get(8).getText().equals(player))||(sqA.get(2).getText().equals(player)&&sqA.get(6).getText().equals(player))) {
						if (random==0&&sqA.get(1).getText().equals("")) {
							responder(1);
						}
						else if (random==1&&sqA.get(7).getText().equals("")) {
							responder(7);
						}
						else {
							defenderse();
						}
					}							
					else { //DEFENDERSE
						defenderse();
					}
				}
				else if (empiezaLado) {
					if (sqA.get(1).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(0).getText().equals("")) {
						responder(0);
					}
					else if (sqA.get(1).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(2).getText().equals("")) {
						responder(2);
					}
					else if (sqA.get(3).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(6).getText().equals("")) {
						responder(6);
					}
					else if (sqA.get(7).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals("")) {
						responder(8);
					}
					else {
						defenderse();
					}
				}


			}
		}
		
	}
	public void responder(int num){
	if (sqA.get(num).getText().equals("")) {
		if (player.equals("X")) {
			sqA.get(num).setText("O");
		}
		else {
			sqA.get(num).setText("X");
		}
		LocalImposible.setTurnoPlayer(true);
		LocalImposible.actualizarEstado();
	}
	}
	
	public void ganaMaquina() {
		LocalImposible.setTurnoPlayer(true);
		for (SquareButton squareButton : sqA) {
			squareButton.setEnabled(false);
		}
		LocalImposible.btnJugarDeNuevo.setVisible(true);
		empiezaMedio=false;
		empiezaEsquina=false;
		empiezaLado=false;
		JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has perdido", "Derrota!", JOptionPane.ERROR_MESSAGE);
		try {
			String update = "UPDATE Usuarios SET DerrotasLocalImposible = DerrotasLocalImposible+1 WHERE Usuario=?";
			ps = Aws.getConnection().prepareStatement(update);
			Aws.lanzar(ps, 1, Login.getUsuario(), Aws.STRING);
			ps.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

	public void defenderse() {
		if (sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals("")) {

			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(5).setText("O");
			}
			else {
				sqA.get(5).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(3).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(3).setText("O");
			}
			else {
				sqA.get(3).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(7).setText("O");
			}
			else {
				sqA.get(7).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(1).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(1).setText("O");
			}
			else {
				sqA.get(1).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(2).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(2).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(1).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(1).setText("O");
			}
			else {
				sqA.get(1).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(6).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(7).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(7).setText("O");
			}
			else {
				sqA.get(7).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(3).getText().equals("")) {

			if (player.equals("X")) {
				sqA.get(3).setText("O");
			}
			else {
				sqA.get(3).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(5).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(5).setText("O");
			}
			else {
				sqA.get(5).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();

		}
		//preparar ataque
		else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(1).getText().equals("")&&sqA.get(2).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(1);
			}
			else if (random==1) {
				responder(2);
			}
		}
		else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(0).getText().equals(playerMaquina)&&sqA.get(3).getText().equals("")&&sqA.get(6).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(3);
			}
			else if (random==1) {
				responder(6);
			}
		}
		else if (sqA.get(1).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")&&sqA.get(2).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(0);
			}
			else if (random==1) {
				responder(2);
			}
		}
		else if (sqA.get(1).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(7).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(7);
			}
		}
		else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(1).getText().equals("")&&sqA.get(0).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(1);
			}
			else if (random==1) {
				responder(0);
			}
		}
		else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(5).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(5);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(2).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(6).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(6);
			}
		}
		else if (sqA.get(3).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")&&sqA.get(6).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(0);
			}
			else if (random==1) {
				responder(6);
			}
		}
		else if (sqA.get(3).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(5).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(5);
			}
		}
		else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(0);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(1).getText().equals("")&&sqA.get(7).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(1);
			}
			else if (random==1) {
				responder(7);
			}
		}
		else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(2).getText().equals("")&&sqA.get(6).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(2);
			}
			else if (random==1) {
				responder(6);
			}
		}
		else if (sqA.get(4).getText().equals(playerMaquina)&&sqA.get(3).getText().equals("")&&sqA.get(5).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(3);
			}
			else if (random==1) {
				responder(5);
			}
		}
		else if (sqA.get(5).getText().equals(playerMaquina)&&sqA.get(3).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(3);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(5).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(3).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(3);
			}
		}
		else if (sqA.get(6).getText().equals(playerMaquina)&&sqA.get(0).getText().equals("")&&sqA.get(3).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(0);
			}
			else if (random==1) {
				responder(3);
			}
		}
		else if (sqA.get(6).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(2).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(2);
			}
		}
		else if (sqA.get(6).getText().equals(playerMaquina)&&sqA.get(7).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(7);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(7).getText().equals(playerMaquina)&&sqA.get(6).getText().equals("")&&sqA.get(8).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(6);
			}
			else if (random==1) {
				responder(8);
			}
		}
		else if (sqA.get(7).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(1).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(1);
			}
			else if (random==1) {
				responder(4);
			}
		}
		else if (sqA.get(8).getText().equals(playerMaquina)&&sqA.get(4).getText().equals("")&&sqA.get(0).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(4);
			}
			else if (random==1) {
				responder(0);
			}
		}
		else if (sqA.get(8).getText().equals(playerMaquina)&&sqA.get(5).getText().equals("")&&sqA.get(2).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(5);
			}
			else if (random==1) {
				responder(2);
			}
		}
		else if (sqA.get(8).getText().equals(playerMaquina)&&sqA.get(7).getText().equals("")&&sqA.get(6).getText().equals("")) {
			int random = r.nextInt(1);
			if (random==0) {
				responder(7);
			}
			else if (random==1) {
				responder(6);
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
				LocalImposible.btnJugarDeNuevo.setVisible(true);
				LocalImposible.setTurnoPlayer(true);
				LocalImposible.actualizarEstado();
				empiezaMedio=false;
				empiezaEsquina=false;
				empiezaLado=false;
				JOptionPane.showMessageDialog(LocalMedio.contentPane, "Has empatado", "Empate!", JOptionPane.INFORMATION_MESSAGE);
				try {
					String update = "UPDATE Usuarios SET EmpatesLocalImposible = EmpatesLocalImposible+1 WHERE Usuario=?";
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

					LocalImposible.setTurnoPlayer(true);
					LocalImposible.actualizarEstado();
				}
			}
		}
	}
	public boolean defenderseSinAleatorio() {
		if (sqA.get(0).getText().equals(player)&&sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals("")) {

			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(5).setText("O");
			}
			else {
				sqA.get(5).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(3).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(3).setText("O");
			}
			else {
				sqA.get(3).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(6).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(7).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(7).setText("O");
			}
			else {
				sqA.get(7).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(1).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(1).setText("O");
			}
			else {
				sqA.get(1).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(5).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(2).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(8).setText("O");
			}
			else {
				sqA.get(8).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(0).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(0).setText("O");
			}
			else {
				sqA.get(0).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(6).setText("O");
			}
			else {
				sqA.get(6).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(4).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(2).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(2).setText("O");
			}
			else {
				sqA.get(2).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(2).getText().equals(player)&&sqA.get(1).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(1).setText("O");
			}
			else {
				sqA.get(1).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(3).getText().equals(player)&&sqA.get(5).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(6).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(7).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(7).setText("O");
			}
			else {
				sqA.get(7).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(3).getText().equals("")) {

			if (player.equals("X")) {
				sqA.get(3).setText("O");
			}
			else {
				sqA.get(3).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(1).getText().equals(player)&&sqA.get(7).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;
		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(5).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(5).setText("O");
			}
			else {
				sqA.get(5).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(0).getText().equals(player)&&sqA.get(8).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else if (sqA.get(2).getText().equals(player)&&sqA.get(6).getText().equals(player)&&sqA.get(4).getText().equals("")) {
			if (player.equals("X")) {
				sqA.get(4).setText("O");
			}
			else {
				sqA.get(4).setText("X");
			}
			LocalImposible.setTurnoPlayer(true);
			LocalImposible.actualizarEstado();
			return true;

		}
		else {
			return false;
		}
}

}

