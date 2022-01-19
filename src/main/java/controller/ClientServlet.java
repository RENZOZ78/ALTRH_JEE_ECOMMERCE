package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientsImp;
import model.Users;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/Client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//FORMULAIREINSCRIPTION /CONNEXION
		//Recupoeration de la valeur de bouton de name="btn"
		String btnForm=request.getParameter("btn");
		
		//verificaiton si la valeur du bouton de form = inscription ou bien connexion
		if(btnForm.equals("Inscription")) 
		{
			System.out.println("espace inscription");
			//intance de la classe Clients
			Users clt = new Users();
			clt.setAdresse(request.getParameter("adresse"));
			clt.setNom(request.getParameter("nom"));
			clt.setPrenom(request.getParameter("prenom"));
			clt.setMdp(request.getParameter("pwd"));
			clt.setEmail(request.getParameter("email"));
			clt.setTel(request.getParameter("tel"));
			System.out.println(clt.getNom());
			// on va appeler la fonction add de class ClientsImp
			//instance de la classe ClientImp
			ClientsImp cltImp=new ClientsImp();
			cltImp.Add(clt);

		}
		else if(btnForm.equals("Connexion")){
			System.out.println("espace connexion");
		}		
		
		
		
	}

}
