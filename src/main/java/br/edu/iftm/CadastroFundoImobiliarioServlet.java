package br.edu.iftm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/fii")
public class CadastroFundoImobiliarioServlet extends HttpServlet {

    /**
     * Verifica se o usuário tem uma sessão ativa. Se não tiver, redireciona para a página de login.
     */
    private boolean verificarAutenticacao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); 

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!verificarAutenticacao(request, response)) {
            return;
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT id, nome, setor, preco, data_ipo FROM fundos_imobiliarios");
            ResultSet rs = pstmt.executeQuery();

            out.println("<html><head><title>Lista de Fundos Imobiliários</title></head><body>");
            out.println("<h3>Lista de Fundos Imobiliários</h3>");
            out.println(
                    "<table border='1'><tr><th>ID</th><th>Nome</th><th>Setor</th><th>Preço</th><th>Data IPO</th><th>Ações</th></tr>");

            while (rs.next()) {
               //TODO: busca os campos da table do banco para monta a tabela no html na lista dos fii

                /*out.println("<tr><td>" + id + "</td><td>" + nome + "</td><td>" + setor + "</td><td>" + preco
                        + "</td><td>" + dataFormatada + "</td>");*/
                out.println("<td><form method='post' action='fii'>");
                out.println("<input type='hidden' name='acao' value='excluir'>");
                //out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<input type='submit' value='Excluir'>");
                out.println("</form></td></tr>");
            }

            out.println("</table>");
            out.println("<a href='formulario.html'>Cadastrar</a>");
            out.println("</body></html>");
        } catch (SQLException | ClassNotFoundException e) {
            out.println(
                    "<div class='error-message'>Erro ao listar os fundos imobiliários: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!verificarAutenticacao(request, response)) {
            return;
        }
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String acao = request.getParameter("acao");
        if ("excluir".equals(acao)) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                /*try {
                     //TODO: Pega o id vindo na requisição e exclui no banco de dados. 
                   
                } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                    out.println("<div class='error-message'>Erro ao excluir fundo: " + e.getMessage() + "</div>");
                    e.printStackTrace();
                }*/
            }

        } else if("cadastrar".equals(acao)) {

            String nome = request.getParameter("nome");
            //TODO: buscar os demais campos vindos do formulário de cadastro. 
            /*
            if (nome == null || nome.trim().isEmpty() || setor == null || setor.trim().isEmpty() ||
                    precoStr == null || precoStr.trim().isEmpty() || dataIpoStr == null
                    || dataIpoStr.trim().isEmpty()) {
                out.println("<div class='error-message'>Todos os campos são obrigatórios.</div>");
                return;
            }

            double preco;
            try {
                preco = Double.parseDouble(precoStr);
            } catch (NumberFormatException e) {
                out.println("<div class='error-message'>Preço inválido.</div>");
                return;
            }

            Date dataIpo;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataIpo = sdf.parse(dataIpoStr);
            } catch (ParseException e) {
                out.println("<div class='error-message'>Data de IPO inválida. Use o formato AAAA-MM-DD.</div>");
                return;
            }

            try {
                //TODO: Conecta com o banco, monta a query de insert e executa. 
                response.sendRedirect("fii");
            } catch (SQLException | ClassNotFoundException e) {
                out.println("<div class='error-message'>Erro ao cadastrar fundo: " + e.getMessage() + "</div>");
                e.printStackTrace();
            }*/
        }
    }
}