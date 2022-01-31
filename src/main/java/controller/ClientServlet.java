
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//LOGOUT-----------------------------------
		//creation de session
		HttpSession session = request.getSession();	
		String logoutClient =  request.getParameter("LogoutClient");
		if(logoutClient != null) {			
		session.setAttribute("client", null);
		
		response.sendRedirect("index.jsp");
		}
		
		//AFFICHAGE INFOS CLIENT----------------------------------
		else {
			//Affichage infos clients detaille			
			  String action = request.getParameter("action"); 
			  if(action!= null) {	
				  System.out.println("action: "+action);
				  if(action.equals("affichageC")){ 
					  System.out.println("connection client");						  
						
						  request.getRequestDispatcher("client/affichageClient.jsp").forward(request,
						  response);
						 
					  //response.sendRedirect("client/affich")
				  }else {
					  System.out.println("vous n'etes pas connecte ");
					  request.getRequestDispatcher("client/homeClient.jsp").forward(request, response);
				  }
			  }					
		}//fin else ln 47:affichage detaille
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);		
	
		//Recuperation de la valeur de bouton de name="btn"
		String btnForm=request.getParameter("btn");
		ClientsImp cltImp=new ClientsImp();
		
		//INSCRIPTION /AJOUT CONTACT-------------------------------
		//verificaiton si la valeur du bouton de form = inscription ou bien connexion
		if(btnForm.equals("Inscription")) 
		{
			System.out.println("espace inscription");
			//intance de la classe Users
			Users clt = new Users();
			
			//setter attributs avec l'instance clt
			clt.setAdresse(request.getParameter("adresse"));
			clt.setNom(request.getParameter("nom"));
			clt.setPrenom(request.getParameter("prenom"));
			clt.setMdp(request.getParameter("pwd"));
			clt.setEmail(request.getParameter("email"));
			clt.setTel(request.getParameter("tel"));
			System.out.println(clt.getNom());						
			
			//verifier si l'email existe => rediriger			
			  String emailV=request.getParameter("email");
			  ClientsImp userV= new ClientsImp(); 
			  
			  if(emailV!=null) {
				  List<Users> ListV = userV.RechercheEmail(emailV, request.getParameter("tel"));
				  int result = ListV.size();
				  System.out.println(result);
				  if(result!=0) { //si l'email existe deja
					  
					  // on verifier si le tel par rappport a la valeur de la colonne tel de la table
					  String tel = ListV.get(0).getTel();
					  String emailT = ListV.get(0).getEmail();
					  System.out.println(tel);
					  if (tel.equals(request.getParameter("tel"))&& !emailT.equals(request.getParameter("email")) ) {
						  String msgErreurTel= "telephone "+request.getParameter("tel") +" existe deja";
						  request.setAttribute("msgErreurTel", msgErreurTel);
					  }else if(emailT.equals(request.getParameter("email"))&& !tel.equals(request.getParameter("tel"))){						  
						  System.out.println("email  existe deja"); 
						  String msg= "email "+request.getParameter("email")+" existe deja";
						  request.setAttribute("msg",msg);
					  }	else if( tel.equals(request.getParameter("tel")) && emailT.equals(request.getParameter("email") ) )	{
						  System.out.println("email/tel  existe deja"); 
						  String msg= "email "+request.getParameter("email")+" tel "+request.getParameter("tel") + " existe deja";
						  request.setAttribute("msg",msg);
					  }
					  request.getRequestDispatcher("admin/erreurPage.jsp").forward(request, response);					  
					  
				  }else {// l'email n'existe pas
						cltImp.Add(clt);
						response.sendRedirect("index.jsp");
				  }
				  
			  }		
		}
		
		//CONNEXION----------------------
		else if(btnForm.equals("Connexion")){
			System.out.println("espace connexion");		
			
			String email = request.getParameter("email"); 
			String pwd = request.getParameter("pwd");		
			ClientsImp userDao = new ClientsImp();	         
	        List<Users> user =  userDao.Authentification(email, pwd);	
	        int nb= user.size(); //size pour compter le nombre des enregistrements retourné par la fonction appelé
	        System.out.println(nb);
	        
	        if(nb==0) {//non connecte
	        	System.out.println("vous n'etes pas membre");
	        	request.setAttribute("e", email);
				request.setAttribute("m	", pwd);
				String msg = "email/password incorrect";
				request.setAttribute("msg", msg);
				System.out.println(msg);			
				request.getRequestDispatcher("admin/erreurPage.jsp").forward(request, response);	
	        	
	        }else {//connecté	      
	        	System.out.println("vous etes membre");	        	
	        	//recuperer l'enregistrement retourné par la fonction d'authentification
	        	Users user2 = cltImp.Authentification(email, pwd).get(0);
	     	      
	        		//creation de session
					HttpSession sessionAdmin = request.getSession();		
					sessionAdmin.setAttribute("user", user);
					sessionAdmin.setAttribute("idClient", user2.getId());
					sessionAdmin.setAttribute("email", email);	
					sessionAdmin.setAttribute("role", "admin");	
					sessionAdmin.setAttribute("client", user2.getNom()+" "+user2.getPrenom());				
					
					//si admin => dashboard admin
					if(email.contains("admin")){
						System.out.println("vous etes admin");
						request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request, response);	
					//sinon client= > page dashboard client
					}else {
						System.out.println("vous etes client");														  
						request.getRequestDispatcher("client/homeClient.jsp").forward(request, response);						  
					}
					  
				} 
		}
	        
					
		//MODIFIER-------------------------------
		else if (btnForm.equals("modifier")) {				
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
		
		
	



