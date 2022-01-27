package dao;

import java.util.List;

import model.Users;

public interface ClientsInterface {
	
	//creation de la fonction AddClient
	public void Add(Users client);
	
	//fonction d'affichage= pour afficher les enregistrement de la table
	//la fonction va nous afficher une liste des clients
	public List<Users> getClient();
	
	//fonction de modificaiton 
	public void  Modifier(Users client);
	
	//fonction supprimer
	public void Supprimer(int idClient); //idClient = nom du parametre
	
	//fonction authentification Client = return une list de client
	public List<Users> Authentification(String email, String mdp);
	
	//fonction de recherche= return une liste de client
	public List<Users> Recherche(String rech);
	
	//fonction de valider  email
		//public boolean isValidateEmail  (String email);
		
	//fonction rechercher 
		public List<Users> RechercheEmail( String email, String tel);
	
	
}
