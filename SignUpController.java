package servlets_http;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpController extends HttpServlet{
	
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setPhone(phone);
		customer.setEmail(email);
		customer.setPassword(password);
		
		CustomerCRUD crud = new CustomerCRUD();
		try {
			int count = crud.saveCustomer(customer);
			
			if(count != 0)
			{
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
				dispatcher.forward(req, res);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		
		}

}
