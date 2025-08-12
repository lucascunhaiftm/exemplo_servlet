package br.edu.iftm.model;

public class User {

    /**
     * Valida as credenciais do usuário.
     * Em um aplicativo real, isso se conectaria ao banco de dados para verificar o usuário.
    */
    public boolean validar(String username, String password) {
        if ("aluno".equals(username) && "iftm".equals(password)) {
            return true;
        }
        return false;
    }
}