package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import connexion.ConnexionBD;
import model.Users;

public class ClientsImp implements ClientsInterface {	
	
	ConnexionBD conn; //variable globale de type conneciont
	
	//AJOUTER
	@Override
	public void Add(Users client) {
		// TODO Auto-generated method stub
		String sql= "insert into client(nom,prenom,tel,email,mdp,adresse) values('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getTel()+"','"+client.getEmail()+"','"+client.getMdp()+"','"+client.getAdresse()+"')";
		System.out.println(sql);
		//Execution de requete
		//Preparation de requete SQL
						
		try {
			//prepration de requete sql
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			
			//execution de resultat de preparation	
			int res=ps.executeUpdate();
			
			//test de l'execution de requete
			if(res==0) System.out.println("Ajout Echoué");
			else System.out.println("Ajout Effectué");			
		}
		catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	//AFFICHER
	@Override
	public List<Users> getClient() {
		// TODO Auto-generated method stub
		String sql= "select * from client";
		
		//on cree un objet de type arrayList sert a remplir chaque enregistrement, ou chaque "pers"
		//on va rajouter dans lea liste les enregistrements remplis
		List<Users> ListesClients=new ArrayList<Users>();
		
		//prepared de la requete sql
		try {
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			//execution de resultat de perpapration
			ResultSet resultat=ps.executeQuery(); //de type Resultset
			
			//parcourir le resultat de type Resultset
			while(resultat.next()) {
				Users pers = new Users();
				
				pers.setNom(resultat.getString("nom"));
				pers.setPrenom(resultat.getString("prenom"));
				pers.setAdresse(resultat.getString("adresse"));
				pers.setId(resultat.getInt("id"));
				pers.setMdp(resultat.getString("mdp"));
				pers.setTel(resultat.getString("tel"));
				pers.setEmail(resultat.getString("email"));
				
				//Enregistrer l'enregistrementd "pers" de la table de "User" dans un array de type List<Users>
				//ajouter ou stoke dans cette liste l'objet de type users
				ListesClients.add(pers);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListesClients;
	}
	

	//MODIFIER----------------
	@Override
	public void Modifier(Users client) {
		// TODO Auto-generated method stub
		
		//METHODE2
		 String sql="update client set nom='"+client.getNom()+"',prenom='"+client.getPrenom()+"',adresse='"+client.getAdresse()+"',id='"+client.getId()+"',mdp='"+client.getMdp()+"',tel='"+client.getTel()+"',email='"+client.getEmail()+"' where id='"+client.getId()+"' ";
		 try {
				PreparedStatement ps=conn.getConnection().prepareStatement(sql);						
				
				int res = ps.executeUpdate();
				if(res==0) {
					System.out.println("modification echouée");
				}
				else {
					System.out.println("modification effectuée");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	//SUPPRIMER------------------
	@Override
	public void Supprimer(int idClient) {
		// TODO Auto-generated method stub
		//String sql="delete from Users where id='"+idClient"'";
		String sql="delete from client where id="+idClient;
		try {
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			int res = ps.executeUpdate();
			if(res==0) {
				System.out.println("suppression echouée");
			}
			else {
				System.out.println("suppression effectuée");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	//AUTHENTIFIER--------------
	@Override
	public List<Users> Authentification(String email, String mdp) {
		// TODO Auto-generated method stub
		String sql= " select * from client where email= '"+email +"'&& mdp='"+mdp+"'  ";
		System.out.println(sql);
		List<Users> infos= new ArrayList<Users>();
		try {
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
			Users user = new Users();
			user.setNom(resultat.getString("nom"));
			user.setId(resultat.getInt("id"));
			user.setPrenom(resultat.getString("prenom"));
			user.setEmail(resultat.getString("email"));
			infos.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return infos;		
	}
	

	//RECHERCHER---------------
	@Override
	public List<Users> Recherche(String rech) {
		// TODO Auto-generated method stub		
		return null;
	}
	
	
	//RECHERCHE EMAIL& TEL-------------------
	@Override
	public List<Users> RechercheEmail(String email, String tel) {
		// TODO Auto-generated method stub
		boolean result = true;
		String sql= " select * from client where email= '"+email +"'|| tel='"+tel+"' ";
		System.out.println(sql);
		List<Users> emailR= new ArrayList<Users>();
		try {
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
			Users user = new Users();			
			user.setEmail(resultat.getString("email"));
			user.setTel(resultat.getString("tel"));
			emailR.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		
		}return emailR;

	}
}
