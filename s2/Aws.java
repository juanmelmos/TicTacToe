package s2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Aws {
	protected static Connection connection;
	protected static Statement sentence;
	protected static String url="jdbc:mysql://first-database.cna36iqi35xu.us-east-1.rds.amazonaws.com:3306/TresEnRaya";
	protected static String user="admin";
	protected static String password="yn9GNqf8d69J9Hr";
	protected static int status;
	protected final static int INT=1;
	protected final static int STRING=2;
	protected final static int DOUBLE=3;
	protected final static int DATE=4;
	
	


	public static Connection getConnection() {
		return connection;
	}

	public static void select(String query) {
		try {
			ResultSet rs = sentence.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString(1)+ " "+ rs.getString(2)+ " "+ rs.getString(3)+ " "+ rs.getString(4)+ " "+ rs.getString(5)+ " "+ rs.getString(6)+ " "+ rs.getString(7));
			}
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}

	public static void lanzar(PreparedStatement ps, int position, Object value, int type) {
		try {
			if (type==1) {
				int nvalue=(int)value;
				ps.setInt(position, nvalue);
			}
			else if (type==2) {
				String nvalue=(String)value;
				ps.setString(position, nvalue);
			}
			else if(type==3){
				double nvalue=(double)value;
				ps.setDouble(position, nvalue);
			}
			else if(type==4){
				Date nvalue=(Date)value;
				ps.setDate(position, nvalue);
			}
			else {
				System.out.println("Eres imbécil");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	public static void connect(boolean confirmation) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
			if (confirmation) {
				System.out.println("Connected");
			}
			sentence=connection.createStatement();
		} catch (Exception e) { 
			System.out.println("Cannot connect to the database");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static String encriptarContraseña(String contraseña) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(contraseña.getBytes());
	        StringBuilder sb = new StringBuilder();
	        
	        for (byte b : hash) {
	            sb.append(String.format("%02x", b));
	        }
	        
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	public static void main(String[] args) {
		boolean returnConfirmation=true;
		connect(returnConfirmation);
		try {
		String insert= "insert into Usuarios (Usuario, Contrasena, Victorias, Derrotas, Empates) values (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		lanzar(ps, 1, "Hugo", STRING);
		lanzar(ps, 2, encriptarContraseña("Patata"), STRING);
		lanzar(ps, 3, 0, INT);
		lanzar(ps, 4, 0, INT);
		lanzar(ps, 5, 0, INT);
		
		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	



	}

}

