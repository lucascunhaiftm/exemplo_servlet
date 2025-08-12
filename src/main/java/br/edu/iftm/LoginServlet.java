package br.edu.iftm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.iftm.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String usuarioParam = request.getParameter("usuario");
        String senhaParam = request.getParameter("senha");

        User userDAO = new User();

        if (userDAO.validar(usuarioParam, senhaParam)) {
            // Se as credenciais são válidas, cria uma sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuarioParam);
            response.sendRedirect("fii");
        } else {
             response.sendRedirect("login.html");
        }
    }
}