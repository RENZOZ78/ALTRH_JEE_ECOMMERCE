package controller;

import java.io.IOException;
import java.sql.SQLException;

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
		
		//Message erreur methode2
		
		//LOGOUT
		//creation obj hhtp session deja ouverte dans le post
		//verification si on a clique sur les parametres de logout
		//redirection
		
		/*METHODE1
		 * HttpSession session = req.getSession(); if (req.getParameter("logout")
		 * !=null) { if (req.getParameter("logout").equals("OK")) {
		 * session.setAttribute("role", null);
		 * req.getRequestDispatcher("admin/login.jsp").forward(req, resp); } }else { if
		 * (session.getAttribute("role")!=null){
		 * req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp); } else {
		 * req.getRequestDispatcher("admin/login.jsp").forward(req, resp); } }
		 */
		
		/*METHODE2*/
		 	String email = req.getParameter("email");
	        String pwd = req.getParameter("pwd");
	         
	        ClientsImp userDao = new ClientsImp();
	         
	        Users user = (Users) userDao.Authentification(email, pwd);
			String destPage = "login.jsp";
			 
			if (user != null) {
			    HttpSession session = req.getSession();
			    session.setAttribute("user", user);
			    //destPage = "admin/login.jsp";
			} else {
			    String message = "Invalid email/password";
			    req.setAttribute("message", message);
			}
			 
			req.getRequestDispatcher("admin/login.jsp").forward(req, resp);
			/*
			 * RequestDispatcher dispatcher = req.getRequestDispatcher(destPage);
			 * dispatcher.forward(req, resp);
			 */
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		//traitement des formulaires qui ont des methodes post
					
			//TRAITEMENT AUTHENTIFICATION
			//recuperation des valeurs des champs "email" et "mdp" de formualire ou d'un parametre
	
			String email = req.getParameter("email"); 
			String pwd = req.getParameter("mdp");
			
			//verification si l'email ="admin@gmail.com" et le pwds="admin2022"
			//on a 2 attribut de session
			if (email.equals("admin@gmail.com") && pwd.equals("admin2022")) {			
				HttpSession sessionAdmin = req.getSession();		
				sessionAdmin.setAttribute("role", "superAdmin");
				sessionAdmin.setAttribute("email", email);
			}//message d'erreur ="email/pasword incorrect"
			else {
				req.setAttribute("e", email);
				req.setAttribute("m	", pwd);
				String msg = "email/password incorrect";
				req.setAttribute("msg", msg);
				System.out.println(msg);
				//Methode1
				//req.getRequestDispatcher("admin/erreurPage.jsp").forward(req, resp);					
				
				//Methode2
				//req.setAttribute("msgErreur", msg);
				
				//Methode3: methode jsp
				req.getRequestDispatcher("admin/login.jsp").forward(req, resp);	
				
			}
								
			req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp); //n'affiche pas le nom du fichier, on le protege// on peut recupere les setter


					
	}
	

	
	

}
