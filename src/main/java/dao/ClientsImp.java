package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sound.midi.Soundbank;

import connexion.ConnexionBD;
import model.Users;

public class ClientsImp implements ClientsInterface {	
	
	ConnexionBD conn; //variable globale de type conneciont
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
	

	@Override
	public List<Users> getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Modifier(Users client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Supprimer(int idClient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Users> Authentification(String email, String mdp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> Recherche(String rech) {
		// TODO Auto-generated method stub
		return null;
	}

}
