package dao;

import java.util.List;

import model.Produits;

public interface ProduitsInterface {
	
	
	//creation de la fonction AddClient
	public void Add(Produits produit);
	
	//fonction d'affichage= pour afficher les enregistrement de la table
	//la fonction va nous afficher une liste des clients
	public List<Produits> getProduit();
	
	//fonction de modificaiton 
	public void  Modifier(Produits produit);
	
	//fonction supprimer
	public void Supprimer(int idClient); //idClient = nom du parametre
	
	//fonction de recherche= return une liste de produit
	public List<Produits> Recherche(String rechProduit);
			
	
	

}
