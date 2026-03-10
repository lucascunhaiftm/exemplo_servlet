package br.edu.iftm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            // cria uma sessão, atribui um valor 
            response.sendRedirect("fii");
        } else {
             response.sendRedirect("login.html");
        }
    }
}