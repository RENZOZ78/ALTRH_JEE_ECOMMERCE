package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connexion.ConnexionBD;
import dao.ClientsImp;
import model.Users;

public class AdminServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		
		
//		Instance de la classe ClientsImp
		ClientsImp cltImp=new ClientsImp();	
		 HttpSession session = req.getSession(); 
		 String logout = req.getParameter("logout");
		 
		 	//LOGOUT
			if (logout != null) {
				session.setAttribute("role", null);	
				//pour ne pas afficher la page de l'url
				resp.sendRedirect("admin");
			}			
			else if (session.getAttribute("role")!= null ) {			 
			 System.out.println("admin connecte");
			 String action=req.getParameter("action"); 
			 
			 
			 if(action!= null) {									
					//AFFICHAGE---------------
				    if(action.equals("affichage")) {				 
					req.getRequestDispatcher("admin/affichageUser.jsp").forward(req, resp);
				    }				    
				    //SUPPRESSION CLIENT------------------------
				    else if(action.equals("supprimer")){				    	
				    	int idClient = Integer.parseInt(req.getParameter("idClient"));
				    	cltImp.Supprimer(idClient);				    	
				    	//req.getRequestDispatcher("admin/affichageUser.jsp").forward(req, resp);				    	
				    	resp.sendRedirect("admin?action=affichage");
				    }		    				    						 
				}	//fin action!= null ln	34 
			 else {					
					req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp);
				}			 
		
			}//fin ligne 29
		 else {
			 System.out.println("admin deconnecte");
			 req.getRequestDispatcher("admin/login.jsp").forward(req, resp);
		 }
	} //fin de doGet
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		//traitement des formulaires qui ont des methodes post
					
			//TRAITEMENT AUTHENTIFICATION-------------
			//recuperation des valeurs des champs "email" et "mdp" de formualire ou d'un parametre	
			/*
			 * String email = req.getParameter("email"); String pwd =
			 * req.getParameter("pwd");
			 * 
			 * //verification si l'email ="admin@gmail.com" et le pwds="admin2022" //on a 2
			 * attribut de session if (email.equals("admin@gmail.com") &&
			 * pwd.equals("admin2022")) { HttpSession sessionAdmin = req.getSession();
			 * sessionAdmin.setAttribute("role", "superAdmin");
			 * sessionAdmin.setAttribute("email", email); }//message d'erreur
			 * ="email/pasword incorrect" else { req.setAttribute("e", email);
			 * req.setAttribute("m	", pwd); String msg = "email/password incorrect";
			 * req.setAttribute("msg", msg); System.out.println(msg); //Methode1
			 * //req.getRequestDispatcher("admin/erreurPage.jsp").forward(req, resp);
			 * 
			 * //Methode2 //req.setAttribute("msgErreur", msg);
			 * 
			 * //Methode3: methode jsp
			 * req.getRequestDispatcher("admin/login.jsp").forward(req, resp);
			 * 
			 * }
			 */
								
			/* req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp); */ //n'affiche pas le nom du fichier, on le protege// on peut recupere les setter


					
	}
	

	
	

}
