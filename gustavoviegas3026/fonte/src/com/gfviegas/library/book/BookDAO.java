package com.gfviegas.library.book;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Domain Access Object para Livros.
 * Classe responsável por ler o arquivo JSON com dados dos livros e servir a lista de livros como singleton a diversas classes.
 */
public class BookDAO {
    private static BookDAO ourInstance = new BookDAO();
    private ArrayList<Book> books;

    /**
     * Construtor privado da Singleton, que impede a criação de uma nova instância da classe.
     */
    private BookDAO() {
        this.books = new ArrayList<Book>();
        readDataFromSource();
    }

    /**
     * Obtêm a instância do DAO de Livros
     * @return Instância Singleton do DAO.
     */
    public static BookDAO getInstance() {
        return ourInstance;
    }

    /**
     * Lê os dados do arquivo de JSON e cria as instâncias de livro para cada livro na lista.
     * Popula a lista de livros para ser utilizada através do sistema.
     */
    private void readDataFromSource() {
        try {
            InputStream stream = BookDAO.class.getResourceAsStream("books.json");

            Gson gson = new Gson();
            JsonParser p = new JsonParser();
            JsonArray books = p.parse(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject().getAsJsonArray("books");

            for (JsonElement b: books) {
                JsonObject bookData = b.getAsJsonObject();
                long ISBN = bookData.get("ISBN").getAsLong();
                String title = bookData.get("name").getAsString();
                String authors = bookData.get("authors").getAsString();
                int year = bookData.get("year").getAsInt();
                double weight = bookData.get("weight").getAsDouble();

                // Em cm
                JsonObject dimensions = bookData.get("dimensions").getAsJsonObject();
                int width = dimensions.get("width").getAsInt();
                int height = dimensions.get("height").getAsInt();

                int edition = bookData.get("edition").getAsInt();
                String publisher = bookData.get("publisher").getAsString();

                this.books.add(new Book(ISBN, title, publisher, authors, edition, width, height, weight, year));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Serve a lista de livros
     * @return ArrayList de livros lidos pelo arquivo JSON anteriormente
     */
    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) books;
    }


    /**
     * Busca na lista por um livro cujo ISBN é dado
     * @param ISBN - Valor do código de barras do livro a ser encontrado
     * @return Livro encontrado que possui o ISBN buscado ou null, caso não encontre tal livro
     */
    public Book findBook(long ISBN) {
        return books.stream().filter(b -> b.getISBN() == ISBN).findFirst().orElse(null);
    }
}
