package servlets_http;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		CustomerCRUD crud = new CustomerCRUD();
		
		try {
			String passwordDB;
			 passwordDB = crud.getPassword(email);
			 
			 if(passwordDB != null) {
				 if(passwordDB.equals(password)) {
					 
					 resp.sendRedirect("https://www.amazon.in/");
				 }else {
					 RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
					 dispatcher.forward(req, resp);
				 }
			 }else {
				 RequestDispatcher dispatcher = req.getRequestDispatcher("signup.html");
				 dispatcher.forward(req, resp);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
