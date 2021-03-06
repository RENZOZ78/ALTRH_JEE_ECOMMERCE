package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.ConnexionBD;
import model.Panier;
import model.Produits;
import model.Panier;

public class PanierImp implements PanierInterface {
	
	ConnexionBD conn;

	@Override
	public void ajouter(Panier panier) {
		// TODO Auto-generated method stub
		String sql= "insert into panier(idClient,idProduit,quantite,etatCommande) values('"+panier.getIdClient()+"','"+panier.getIdProduit()+"','"+panier.getQuantite()+"','"+panier.getEtatCommande()+"')";
		System.out.println(sql);
		//Execution de requete
		//Preparation de requete SQL
						
		try {
			//prepration de requete sql
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			
			//execution de resultat de preparation	
			int res=ps.executeUpdate();
			
			//test de l'execution de requete
			if(res==0) System.out.println("Ajout Echou?");
			else System.out.println("Ajout Effectu?");			
		}
		catch (Exception e) {
			e.printStackTrace();
		}			
	}
		
	

	@Override
	public List<Panier> getPanier(int idClient) {
		// TODO Auto-generated method stub
		String sql= " select * from panier  where etatCommande!=1 && idClient="+idClient;
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
		 String sql="update panier set quantite='"+panier.getQuantite()+"' "
				 +"where id= "+panier.getId();
		 System.out.println(sql);
		 try {
				PreparedStatement ps=conn.getConnection().prepareStatement(sql);							
				int res = ps.executeUpdate();
				if(res==0) {
					System.out.println("modification echou?e");
				}
				else {
					System.out.println("modification effectu?e");
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
				System.out.println("suppression echou?e");
			}
			else {
				System.out.println("suppression effectu?e");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public List<Panier> Recherche(int idClient) {
		// TODO Auto-generated method stub
				String sql= " select * from panier where idClient= "+idClient;
				System.out.println(sql);
				//Execution de requete
				//Preparation de requete SQL
				List<Panier> ListPanier=new ArrayList<Panier>();
								
				try {
					//prepration de requete sql
					PreparedStatement ps=conn.getConnection().prepareStatement(sql);
					
					ResultSet resultat=ps.executeQuery(); //de type Resultset
					
					//parcourir le resultat de type Resultset
					while(resultat.next()) {
						Panier panier = new Panier();
						panier.setId(resultat.getInt("id"));
						panier.setIdProduit(resultat.getInt("idProduit"));
						panier.setIdClient(resultat.getInt("idClient"));
						panier.setQuantite(resultat.getInt("Quantite"));
						
						//Enregistrer l'enregistrementd "produit" de la table de "User" dans un array de type List<Users>
						//ajouter ou stoke dans cette liste l'objet de type users
						ListPanier.add(panier);		
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}	
				return ListPanier;
	}


	@Override
	public List<Panier> RecherchePanier2(int idClient, int IdProduit) {
		// TODO Auto-generated method stub
		String sql= " select * from panier where idClient= '"+idClient+"' && idProduit='"+ IdProduit+"' && etatCommande!=1" ;
		System.out.println(sql);
		//Execution de requete
		//Preparation de requete SQL
		List<Panier> ListPanier=new ArrayList<Panier>();
						
		try {
			//prepration de requete sql
			PreparedStatement ps=conn.getConnection().prepareStatement(sql);
			
			ResultSet resultat=ps.executeQuery(); //de type Resultset
			
			//parcourir le resultat de type Resultset
			while(resultat.next()) {
				Panier panier = new Panier();
				panier.setId(resultat.getInt("id"));
				panier.setIdProduit(resultat.getInt("idProduit"));
				panier.setIdClient(resultat.getInt("idClient"));
				panier.setQuantite(resultat.getInt("Quantite"));
				
				//Enregistrer l'enregistrementd "produit" de la table de "User" dans un array de type List<Users>
				//ajouter ou stoke dans cette liste l'objet de type users
				ListPanier.add(panier);		
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return ListPanier;
	}



	@Override
	public void Commande(int idClient) {
		// TODO Auto-generated method stub
		 String sql="update panier set etatCommande=1 where idClient= "+idClient;
		 System.out.println(sql);
		 try {
				PreparedStatement ps=conn.getConnection().prepareStatement(sql);							
				int res = ps.executeUpdate();
				if(res==0) {
					System.out.println("modification echou?e");
				}
				else {
					System.out.println("modification effectu?e");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		
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
