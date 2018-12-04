package com.gfviegas.library.user;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Domain Access Object para Usuários.
 * Classe responsável por ler o arquivo JSON com dados do usuário e servir a lista de usuários como singleton a diversas classes.
 */
public class UserDAO {
    private static UserDAO ourInstance = new UserDAO();
    private ArrayList<User> users;

    /**
     * Construtor privado da Singleton, que impede a criação de uma nova instância da classe.
     */
    private UserDAO() {
        this.users = new ArrayList<User>();
        readDataFromSource();
    }

    /**
     * Obtêm a instância do DAO de Usuários
     * @return Instância Singleton do DAO.
     */
    public static UserDAO getInstance() {
        return ourInstance;
    }

    /**
     * Lê os dados do arquivo de JSON e cria as instâncias de usuário para cada usuário na lista.
     * Popula a lista de usuários para ser utilizada através do sistema.
     */
    private void readDataFromSource() {
        try {
            InputStream stream = UserDAO.class.getResourceAsStream("users.json");

            Gson gson = new Gson();
            JsonParser p = new JsonParser();
            JsonArray users = p.parse(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject().getAsJsonArray("users");

            for (JsonElement u: users) {
                JsonObject userData = u.getAsJsonObject();
                int id = userData.get("id").getAsInt();
                String name = userData.get("name").getAsString();
                String password = userData.get("password").getAsString();

                JsonObject address = userData.get("address").getAsJsonObject();
                String addressStreet = address.get("street").getAsString();
                String addressCity = address.get("city").getAsString();
                int addressNumber = address.get("number").getAsInt();
                int addressZip = address.get("zip").getAsInt();

                this.users.add(new User(id, name, password, addressStreet, addressCity, addressNumber, addressZip));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Serve a lista de usuários
     * @return ArrayList de usuários lidos pelo arquivo JSON anteriormente
     */
    ArrayList<User> getUsers() {
        return (ArrayList<User>) users;
    }
}
