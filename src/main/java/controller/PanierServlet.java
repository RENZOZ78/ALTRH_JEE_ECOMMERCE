package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PanierImp;
import dao.ProduitsImp;
import model.Panier;
import model.Produits;

/**
 * Servlet implementation class PanierServlet
 */
//@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//instance de la classe Panier
		PanierImp panImp=new PanierImp();
		Panier panier = new Panier();
		String action =request.getParameter("action");
			if(action!=null) {
				
				//AFFICHAGE PANIER---------------
				System.out.println("voici votre panier");
				if(action.equals("affichagePanier")) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}						
				//suppression panier-------------				
				else if(action.equals("supprimerPanier")) {
					System.out.println("votre panier est supprimé");
					int id= Integer.parseInt(request.getParameter("id"));
							panImp.Supprimer(id);
							response.sendRedirect("index.jsp");
				}
				else if(action.equals("paiement")) {
					int IdClient = Integer.parseInt(request.getSession().getAttribute("idClient").toString());
					panImp.Commande(IdClient);
					response.sendRedirect("index.jsp");
				}
			}
							
			else {
				request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PanierImp panierImp=new PanierImp();		
		String action= request.getParameter("btn");
		Panier p = new Panier();
		if(action!=null) {
			
			//AJOUT DE PANIER--------------------------------	
			if(action.equals("ajout")) {				
			System.out.println("vous avez ajouté le produit:" + p.getIdProduit());			
		
			String idProduit= request.getParameter("idProduit");
			String idClient =request.getParameter("idClient");
			Integer quantite= Integer.parseInt(request.getParameter("quantite"));			
					
			p.setIdProduit(Integer.parseInt(idProduit));
			p.setIdClient(Integer.parseInt(idClient));
			p.setQuantite(quantite);				
			
			//fonction de recherhe	
			//List<Panier> reqPanier= panierImp.Recherche(Integer.parseInt(request.getParameter("quantite")));
			List<Panier> reqPanier= panierImp.RecherchePanier2(Integer.parseInt(request.getParameter("idClient")), Integer.parseInt(idProduit));
			
			//compter le nb enregistrements
			int nbEnreg = reqPanier.size();
			
				//s'il n y a pas d'enregistrement dans la table
				if(nbEnreg==0) {	
					System.out.println("il n'ya pas d'enregistrement dans la table");
					//ajout de produit
					p.setEtatCommande(0);
					panierImp.ajouter(p);
					response.sendRedirect("index.jsp");
				
				//s'il ya des enregistrements dans la table
				}else if(nbEnreg>0) {
					System.out.println("il ya des enregsitrement existant dans la table");
					int idProduitPanier= reqPanier.get(0).getIdProduit();
					int sumQte = reqPanier.get(0).getQuantite()+ quantite;
					p.setQuantite(sumQte);
					p.setId(reqPanier.get(0).getId());
					panierImp.Modifier(p);
					
					response.sendRedirect("index.jsp");				
				}						
			}
			
			//MODIFIER DE PÄNIER----------------------------
			else if(action.equals("modifierPanier")) {
				System.out.println("votre panier est modifié");							
				int quantite = Integer.parseInt(request.getParameter("quantite"));
				
				p.setId(Integer.parseInt(request.getParameter("id")));
				p.setQuantite(quantite);	
											
				panierImp.Modifier(p);
				response.sendRedirect("index.jsp");
				}
			else {
				System.out.println("vous n'avez rien modifier");
			}
		}		
	}
}
