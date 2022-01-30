package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.ConnexionBD;
import model.Panier;
import model.Panier;

public class PanierImp implements PanierInterface {
	
	ConnexionBD conn;

	@Override
	public void ajouter(Panier panier) {
		// TODO Auto-generated method stub
		String sql= "insert into panier(idClient,idProduit,quantite) values('"+panier.getIdClient()+"','"+panier.getIdProduit()+"','"+panier.getQuantite()+"')";
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
	public List<Panier> getPanier(int idClient) {
		// TODO Auto-generated method stub
		String sql= " select * from panier  where idClient="+idClient;
		System.out.println(sql);
		//Execution de requete
		//Preparation de requete SQL
		List<Panier> ListePanier=new ArrayList<Panier>();
						
		try {
			//prepration de requete sql
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			
			ResultSet resultat=ps.executeQuery(); //de type Resultset
			
			//parcourir le resultat de type Resultset
			while(resultat.next()) {
				Panier panier = new Panier();
				panier.setId(resultat.getInt("id"));
				panier.setIdClient(resultat.getInt("idClient"));
				panier.setIdProduit(resultat.getInt("idProduit"));
				panier.setQuantite(resultat.getInt("quantite"));						
				
				//Enregistrer l'enregistrementd "panier" de la table de "User" dans un array de type List<Users>
				//ajouter ou stoke dans cette liste l'objet de type users
				ListePanier.add(panier);		
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return ListePanier;
		
	}

	@Override
	public void Modifier(Panier panier) {
		// TODO Auto-generated method stub
		 String sql="update panier set idClient='"+panier.getIdClient()+"',idProduit='"+panier.getIdProduit()+"',quantite='"+panier.getQuantite()+"'  ";
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
	public void Supprimer(int idPanier) {
		// TODO Auto-generated method stub
		String sql="delete from panier where id="+idPanier;
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
	public List<Panier> Recherche(String rech) {
		// TODO Auto-generated method stub
		
		return null;
	}
}

/*
 * @Override public int savePanier(Panier panier) { // TODO Auto-generated
 * method stub int save=0; String
 * sql="insert into panier(idClient,idProduit,quantite)values(?,?,?,)"; try {
 * PreparedStatement ps=conn.getConnection().prepareStatement(sql);
 * ps.setInt(1,panier.getId()); ps.setInt(2,panier.getIdClient());
 * ps.setInt(3,panier.getIdProduit()); ps.setInt(4,panier.getQuantite());
 * save=ps.executeUpdate();
 * 
 * } catch (Exception e) { e.printStackTrace();
 * 
 * }return save; }
 */
