package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.ConnexionBD;
import model.Produits;
import model.Users;

public class ProduitsImp implements ProduitsInterface {

	
	ConnexionBD conn; 
	
	//AJOUTER
	@Override
	public void Add(Produits produit) {
		// TODO Auto-generated method stub
		String sql= "insert into produit(id,reference,designation,prix,poids,QteStock) values('"+produit.getId()+"','"+produit.getReference()+"','"+produit.getDesignation()+"','"+produit.getPrix()+"','"+produit.getPoids()+"','"+produit.getStock()+"')";
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
	public List<Produits> getProduit() {
		// TODO Auto-generated method stub
		
		String sql= " select * from produit ";
		System.out.println(sql);
		//Execution de requete
		//Preparation de requete SQL
		List<Produits> ListesProduits=new ArrayList<Produits>();
						
		try {
			//prepration de requete sql
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			
			ResultSet resultat=ps.executeQuery(); //de type Resultset
			
			//parcourir le resultat de type Resultset
			while(resultat.next()) {
				Produits produit = new Produits();
				produit.setId(resultat.getInt("id"));
				produit.setReference(resultat.getString("reference"));
				produit.setDesignation(resultat.getString("designation"));
				produit.setPrix(resultat.getDouble("prix"));
				produit.setPoids(resultat.getDouble("poids"));
				produit.setStock(resultat.getInt("QteStock"));				
				
				//Enregistrer l'enregistrementd "produit" de la table de "User" dans un array de type List<Users>
				//ajouter ou stoke dans cette liste l'objet de type users
				ListesProduits.add(produit);		
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return ListesProduits;
	}

	@Override
	public void Modifier(Produits produit) {
		// TODO Auto-generated method stub
		
		//METHODE2
		 String sql="update produit set reference='"+produit.getReference()+"',designation='"+produit.getDesignation()+"',prix='"+produit.getPrix()+"',poids='"+produit.getPoids()+"',QteStock='"+produit.getStock()+"' WHERE  id='"+produit.getId()+"'   ";
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

	
	@Override
	public void Supprimer(int id) {
		// TODO Auto-generated method stub
		//String sql="delete from Users where id='"+id"'";
		String sql="delete from produit where id="+id;
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

	@Override
	public List<Produits> Recherche(String rechProduit) {
		// TODO Auto-generated method stub
		return null;
	}
}
