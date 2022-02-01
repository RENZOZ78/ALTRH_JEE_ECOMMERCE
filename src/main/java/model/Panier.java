package model;

public class Panier {

	 int id;
	 int idClient;
	 int idProduit;
	 int quantite;
	 int etatCommande;
	 
	 
	public int getEtatCommande() {
		return etatCommande;
	}
	public void setEtatCommande(int etatCommande) {
		this.etatCommande = etatCommande;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	 
}
