package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProduitsImp;
import dao.ProduitsImp;
import model.Produits;

/**
 * Servlet implementation class ProduitsServlet
 */
@WebServlet("/ProduitsServlet")
public class ProduitsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action =req.getParameter("action");			
		
		//Instance de la classe ProduitsImp
		ProduitsImp pdtImp=new ProduitsImp();		
		Produits produit= new Produits();
			 if(action!= null) {	
				 
					//AFFICHAGE page produit---------------------------
				 	System.out.println("vous avez ajouté:"+produit.getDesignation());
				    if(action.equals("affichageP")) {				 
					req.getRequestDispatcher("admin/affichageProduits.jsp").forward(req, resp);
				    }	
				    
				    //SUPPRESSION PRODUIT-----------------------------
				    else if(action.equals("supprimerP")){		
				    	System.out.println("vous avez supprimer: "+produit.getDesignation());
				    	int id = Integer.parseInt(req.getParameter("id"));
				    	pdtImp.Supprimer(id);				    	
				    	//req.getRequestDispatcher("admin/affichageUser.jsp").forward(req, resp);				    	
				    	resp.sendRedirect("produits?action=affichageP");
				    }		    				    						 
				}	 
			 else  {					
					req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp);							 
		
			}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//instance de produitsImp
		ProduitsImp produitsImp=new ProduitsImp();		
		String action= request.getParameter("btn");
		
		if(action!=null) {
			
			//AJOUT DE PRODUIT--------------------------------	
			if(action.equals("ajout")) {				
			System.out.println("vous avez ajouté un produit:");			
			//recuperation des champs formulaires ajout
			String reference= request.getParameter("ref");
			String designation =request.getParameter("designation");
			Integer prix= Integer.parseInt(request.getParameter("prix"));
			Integer poids= Integer.parseInt(request.getParameter("poids"));
			Integer stock= Integer.parseInt(request.getParameter("stock"));
			
			//instance d eproduit et setter attribut
			Produits p = new Produits();
			
			//setter avec instance p de produit
			p.setReference(reference);
			p.setDesignation(designation);
			p.setPrix(prix);
			p.setPoids(poids);
			p.setStock(stock);			
			
			//appelle de la methode ajouter avec l'instance p
			produitsImp.Add(p);
			response.sendRedirect("produits?action=affichageP");
			}
			
			else if(action.equals("modifier")) {
				
				//MODIFIER DE PRODUIT----------------------------
				System.out.println("vous avez modifié un produit:");					
				//champs formulaires modifier
				String reference= request.getParameter("ref");
				String designation =request.getParameter("designation");
				double prix= Double.parseDouble(request.getParameter("prix"));
				double poids= Double.parseDouble(request.getParameter("poids"));
				int stock= Integer.parseInt(request.getParameter("stock"));
				Integer id= Integer.parseInt(request.getParameter("id"));
				
				//instance p de Produits 
				Produits p = new Produits();
				
				//nouveaux setter avec instance p
				p.setReference(reference);
				p.setDesignation(designation);
				p.setPrix(prix);
				p.setPoids(poids);
				p.setStock(stock);	
				p.setId(id);	
				
				//appelle de methode modifier de produitsImp avec en parametre l'instance p
				produitsImp.Modifier(p);
				response.sendRedirect("produits?action=affichageP");
				}
		}
						
				
	}

}
