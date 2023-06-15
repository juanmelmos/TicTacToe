package s2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	protected PreparedStatement ps;
	protected static LocalOrOnline lol;
	protected ResultSet rs;
	protected static Login frame;
	private static String Usuario;
	private JFormattedTextField formattedTextField;
	protected EnableThread et;
	protected JButton btnRegister;
	protected JButton btnLogin;
	
	

	public static String getUsuario() {
		return Usuario;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(252, 233, 79));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(176, 203, 117, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(176, 274, 105, 17);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select= "select Usuario from Usuarios where Usuario = ?";
				try {
					if (comprobarSiExiste()) {
						
						String psw = Aws.encriptarContraseña(passwordField.getText());
						select = "select Contrasena from Usuarios where Usuario = ?";
						ps =  Aws.getConnection().prepareStatement(select);
						ps.setString(1, formattedTextField.getText());
						rs = ps.executeQuery();
						rs.next();
							if (psw.equals(rs.getString(1))) {
								Usuario=formattedTextField.getText();
								lol=new LocalOrOnline();
								lol.setVisible(true);
								lol.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
								frame.dispose();
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Contraseña incorrecta.", "Error de contraseña", JOptionPane.ERROR_MESSAGE);
							}
						
	
				}	
					else {
						JOptionPane.showMessageDialog(contentPane, "Usuario inexistente, es necesario registrarse previamente.", "Error de usuario", JOptionPane.ERROR_MESSAGE);
					} 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		passwordField.setBounds(176, 291, 114, 21);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select= "select Usuario from Usuarios where Usuario = ?";
				try {
					if (comprobarSiExiste()) {
						String psw = Aws.encriptarContraseña(passwordField.getText());
						select = "select Contrasena from Usuarios where Usuario = ?";
						ps =  Aws.getConnection().prepareStatement(select);
						ps.setString(1, formattedTextField.getText());
						rs = ps.executeQuery();
						rs.next();
							if (psw.equals(rs.getString(1))) {
								Usuario=formattedTextField.getText();
								lol=new LocalOrOnline();
								lol.setVisible(true);
								lol.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
								frame.dispose();
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Contraseña incorrecta.", "Error de contraseña", JOptionPane.ERROR_MESSAGE);
							}
						
	
				}	
					else {
						JOptionPane.showMessageDialog(contentPane, "Usuario inexistente, es necesario registrarse previamente.", "Error de usuario", JOptionPane.ERROR_MESSAGE);
					} 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(73, 378, 105, 27);
		contentPane.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String insert= "insert into Usuarios (Usuario, Contrasena, Victorias, Derrotas, Empates, VictoriasLocalFacil, DerrotasLocalFacil, EmpatesLocalFacil, VictoriasLocalMedio, DerrotasLocalMedio, EmpatesLocalMedio, VictoriasLocalDificil, DerrotasLocalDificil, EmpatesLocalDificil, VictoriasLocalImposible, DerrotasLocalImposible, EmpatesLocalImposible) values (?, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
				if (comprobarSiExiste()) {
					JOptionPane.showMessageDialog(contentPane, "El usuario "+formattedTextField.getText() +" ya está registrado, inicie sesión.", "Usuario existente", JOptionPane.ERROR_MESSAGE);
				}
				else if (passwordField.getText().length()<8||passwordField.getText().length()>16) {
					JOptionPane.showMessageDialog(contentPane, "La contraseña debe tener ente 8 y 16 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						String psw = Aws.encriptarContraseña(passwordField.getText());
						ps = Aws.getConnection().prepareStatement(insert);
						Aws.lanzar(ps, 1, formattedTextField.getText(), Aws.STRING);
						Aws.lanzar(ps, 2, psw, Aws.STRING);
						ps.execute();
						JOptionPane.showMessageDialog(contentPane, "¡Te has registrado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					} catch(SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnRegister.setBounds(292, 378, 105, 27);
		contentPane.add(btnRegister);
		
		
		///////////////////////////////////////formattedTextField////////////////////////////////////////////////////////
		DocumentFilter filter = new DocumentFilter() {
			@Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                text = text.toLowerCase().replaceAll("\\s", ""); // Convertir a minúscula y eliminar espacios
                super.insertString(fb, offset, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                text = text.toLowerCase().replaceAll("\\s", ""); // Convertir a minúscula y eliminar espacios
                super.replace(fb, offset, length, text, attrs);
            }
        };


        formattedTextField = new JFormattedTextField();
        formattedTextField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String select= "select Usuario from Usuarios where Usuario = ?";
				try {
					if (comprobarSiExiste()) {
						
						String psw = Aws.encriptarContraseña(passwordField.getText());
						select = "select Contrasena from Usuarios where Usuario = ?";
						ps =  Aws.getConnection().prepareStatement(select);
						ps.setString(1, formattedTextField.getText());
						rs = ps.executeQuery();
						rs.next();
							if (psw.equals(rs.getString(1))) {
								Usuario=formattedTextField.getText();
								lol=new LocalOrOnline();
								lol.setVisible(true);
								lol.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
								frame.dispose();
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Contraseña incorrecta.", "Error de contraseña", JOptionPane.ERROR_MESSAGE);
							}
						
	
				}	
					else {
						JOptionPane.showMessageDialog(contentPane, "Usuario inexistente, es necesario registrarse previamente.", "Error de usuario", JOptionPane.ERROR_MESSAGE);
					} 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        ((AbstractDocument) formattedTextField.getDocument()).setDocumentFilter(filter);
       
        formattedTextField.setBounds(176, 219, 114, 21);
		contentPane.add(formattedTextField);
		
		JPanel panelTTT = new JPanel();
		panelTTT.setBackground(Color.BLUE);
		panelTTT.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelTTT.setBounds(0, 0, 483, 128);
		
		contentPane.add(panelTTT);
		panelTTT.setLayout(null);
		
		JLabel lblTicTacToe = new JLabel("Tic Tac Toe");
		lblTicTacToe.setBackground(Color.WHITE);
		lblTicTacToe.setBounds(0, 0, 483, 128);
		panelTTT.add(lblTicTacToe);
		lblTicTacToe.setFont(new Font("Latin Modern Math", Font.BOLD, 60));
		lblTicTacToe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTicTacToe.setForeground(new Color(252, 233, 79));
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	public boolean comprobarSiExiste() {
		String select= "select Usuario from Usuarios where Usuario = ?";
		try {
			Aws.connect(false);
			ps = Aws.getConnection().prepareStatement(select);
			ps.setString(1, formattedTextField.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(formattedTextField.getText())) {
					return true;
				}
			}
			return false;
		}  catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	
			
	}
}
