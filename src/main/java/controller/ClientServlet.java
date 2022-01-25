
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		ClientsImp cltImp=new ClientsImp();
		
		//INSCRIPTION
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
			
			cltImp.Add(clt);
		}
		//CONNEXION
		else if(btnForm.equals("Connexion")){
			System.out.println("espace connexion");			
			String email = request.getParameter("email"); 
			String pwd = request.getParameter("pwd");		
			ClientsImp userDao = new ClientsImp();	         
	        List<Users> user =  userDao.Authentification(email, pwd);	
	        int nb= user.size(); //size pour compter le nombre des enregistrements retourné par la fonction appelé
	        System.out.println(nb);
	        
	        if(nb==0) {
	        	System.out.println("vous n'etes pas membre");
	        	request.setAttribute("e", email);
				request.setAttribute("m	", pwd);
				String msg = "email/password incorrect";
				request.setAttribute("msg", msg);
				System.out.println(msg);			
				request.getRequestDispatcher("admin/erreurPage.jsp").forward(request, response);	
	        	
	        }else {
	        	System.out.println("vous etes membre");
	        	
	        	if (email !=null && pwd !=null && email.contains("admin")  ) {			
					HttpSession sessionAdmin = request.getSession();		
					sessionAdmin.setAttribute("user", user);
					sessionAdmin.setAttribute("email", email);	
					sessionAdmin.setAttribute("role", "admin");	
					request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request, response);	
					
				}else if (email !=null && pwd !=null) {	
					HttpSession sessionAdmin = request.getSession();
					sessionAdmin.setAttribute("user", user);
					sessionAdmin.setAttribute("email", email);
					sessionAdmin.setAttribute("role", "client");				
					request.getRequestDispatcher("client/homeClient.jsp").forward(request, response);	
					//System.out.println("espace connexion "+ user.getNom());
	        	
	        }
	        
			
			//verification si l'email 
			//on a 2 attribut de session
			/*if (email !=null && pwd !=null && email.contains("admin")  ) {			
				HttpSession sessionAdmin = request.getSession();		
				sessionAdmin.setAttribute("user", user);
				sessionAdmin.setAttribute("email", email);	
				sessionAdmin.setAttribute("role", "admin");	
				request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request, response);	
				
			}*/
	        //else if (email !=null && pwd !=null) {	
				//HttpSession sessionAdmin = request.getSession();
				//sessionAdmin.setAttribute("user", user);
				//sessionAdmin.setAttribute("email", email);
				//sessionAdmin.setAttribute("role", "client");				
				//request.getRequestDispatcher("client/homeClient.jsp").forward(request, response);	
				//System.out.println("espace connexion "+ user.getNom());
				
				//AFFICHAGE INFOS CLIENT
				/*
				 * String action=request.getParameter("action"); if(action.equals("affichageC"))
				 * { System.out.println("conneciton client");
				 * request.getRequestDispatcher("client/affichageClient.jsp").forward(request,
				 * response); }
				 */
			//}			
			
			//message d'erreur ="email/pasword incorrect"
			/*else  {
				request.setAttribute("e", email);
				request.setAttribute("m	", pwd);
				String msg = "email/password incorrect";
				request.setAttribute("msg", msg);
				System.out.println(msg);
				//Methode1
				request.getRequestDispatcher("admin/erreurPage.jsp").forward(request, response);	
			}*/
		}
		}
		//MODIFIER
		else if(btnForm.equals("modifier")) {		
			
			Users clt = new Users();
			clt.setAdresse(request.getParameter("adresse"));
			clt.setNom(request.getParameter("nom"));
			clt.setPrenom(request.getParameter("prenom"));
			clt.setMdp(request.getParameter("pwd"));
			clt.setEmail(request.getParameter("email"));
			clt.setTel(request.getParameter("tel"));			
			clt.setId(Integer.parseInt(request.getParameter("id")));
			System.out.println(clt.getNom());
			// on va appeler la fonction add de class ClientsImp
			//instance de la classe ClientImp
			
			cltImp.Modifier(clt);
			response.sendRedirect("admin?action=affichage");
		}
		
		
	}
}
		
		
	



