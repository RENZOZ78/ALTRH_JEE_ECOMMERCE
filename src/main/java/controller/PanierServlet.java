package controller;

import java.io.IOException;
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
		
		//instance de la classe Pnaier
		PanierImp panImp=new PanierImp();
		Panier panier = new Panier();
		String action =request.getParameter("action");
			if(action!=null) {
				
				//AFFICHAGE PANIER---------------
				System.out.println("voivi votre panier");
				if(action.equals("affichagePanier")) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}						
				//suppression panier-------------
				
				else if(action.equals("supprimerPanier")) {
					System.out.println("votre panier est supprimé");
					int id= Integer.parseInt(request.getParameter("id"));
							panImp.Supprimer(id);
							response.sendRedirect("panier?action=affichagePanier");
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
						
			panierImp.ajouter(p);
			response.sendRedirect("index.jsp");
			}
			
			else if(action.equals("modifier")) {
				System.out.println("votre panier est modifié");
				//MODIFIER DE PÄNIER----------------------------
				System.out.println("vous avez modifié un panier:");
				String idProduit= request.getParameter("idProduit");
				String idClient =request.getParameter("idClient");
				int quantite = Integer.parseInt(request.getParameter("quantite"));
				p.setIdProduit(Integer.parseInt(idProduit));
				p.setIdClient(Integer.parseInt(idClient));
				p.setQuantite(quantite);		
								
				panierImp.Modifier(p);
				response.sendRedirect("produits?action=affichageP");
				}
		}
		
		
		
	}

}
