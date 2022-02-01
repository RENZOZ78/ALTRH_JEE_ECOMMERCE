package dao;

import java.util.List;

import model.Panier;

public interface PanierInterface {
	
		//creation de la fonction AddPanier
		public void ajouter(Panier panier);
		
		//fonction d'affichage= pour afficher les enregistrement de la table		
		public List<Panier> getPanier(int idClient);
		
		//fonction de modificaiton 
		public void  Modifier(Panier panier);
		
		//fonction supprimer
		public void Supprimer(int idPanier); //idClient = nom du parametre
		
		//fonction de recherche= return une liste de client
		public List<Panier> Recherche(int idClient);
		
		public List<Panier> RecherchePanier2(int idClient, int IdProduit);
			

		
}
