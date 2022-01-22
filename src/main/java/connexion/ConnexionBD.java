package connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBD {
	
	//variable static de type Connection
	private static Connection cnx; //connexion c'est le type de cnx	
	
	static {
		
		try {
			//prepration de requete sql
			Class.forName("com.mysql.jdbc.Driver");
			
			cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercejee","root","");
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//creation de la fonction qui return la variable cnx
	public static Connection getConnection() {
		return cnx;
	}
	

}
