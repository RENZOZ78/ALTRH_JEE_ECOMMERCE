package dao;

import java.util.List;

import model.Panier;

public interface PanierInterface {
	
		//creation de la fonction AddPanier
		public void Add(Paniers panier);
		
		//fonction d'affichage= pour afficher les enregistrement de la table
		//la fonction va nous afficher une liste des clients
		public List<Panier> getPanier();
		
		//fonction de modificaiton 
		public void  Modifier(Users client);
		
		//fonction supprimer
		public void Supprimer(int idClient); //idClient = nom du parametre
		
		//fonction de recherche= return une liste de client
		public List<Users> Recherche(String rech);
			
		//fonction rechercher 
			public List<Users> RechercheEmail( String email, String tel);
		
}
