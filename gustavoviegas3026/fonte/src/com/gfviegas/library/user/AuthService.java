package com.gfviegas.library.user;

/**
 * Classe Singleton para gerenciar autenticações e servir dados do usuário autenticado.
 */
public class AuthService {
    private static AuthService ourInstance = new AuthService();
    private UserDAO userDAO = UserDAO.getInstance();
    private User authenticatedUser = null;

    /**
     * Obtêm a instância do serviço de autenticação
     * @return Instância Singleton do serviço.
     */
    public static AuthService getInstance() {
        return ourInstance;
    }

    // GETTERS
    public User getAuthenticatedUser() {
        if (!isUserAuthenticated()) return null;
        return authenticatedUser;
    }

    // SETTERS
    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }


    /**
     * Verifica se o sistema possui um usuário autenticado
     * @return Valor booleano se o usuário está autenticado ou não
     */
    public boolean isUserAuthenticated() {
        return (authenticatedUser != null);
    }

    /**
     * A partir de uma matrícula e senha, verifica os dados e atribui o usuário autenticado ao
     * @param id - Matrícula de um usuário
     * @param password - Senha (não cripotgrafada) de um usuário
     * @return Valor booleano se o usuário foi encontrado e autenticado, ou não
     */
    public boolean authenticate(int id, String password) {
        for (User u: userDAO.getUsers()) {
            if (u.getId() == id && u.getPassword().equals(password)) {
                authenticatedUser = u;
                return true;
            }
        }
        return false;
    }

    /**
     * Desconecta o usuário autenticado, removendo a instância de usuário a ele vinculada
     */
    public void logout() {
        if (!isUserAuthenticated()) return;
        authenticatedUser = null;
    }

    /**
     * Retorna o nome do usuário autenticado ou o nome de "Visitante"
     * @return Nome do usuário autenticado/Visitante
     */
    public String getAuthUserName() {
        return (isUserAuthenticated()) ? authenticatedUser.getName() : "Visitante";
    }
}
