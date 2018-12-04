package com.gfviegas.library.stock;

import com.gfviegas.library.book.Book;
import com.gfviegas.library.book.BookDAO;
import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Domain Access Object para o Estoque de Livros.
 * Classe responsável por ler o arquivo JSON com dados do estoque e servir a lista de dados como singleton a diversas classes.
 */
public class StockDAO {
    private static StockDAO ourInstance = new StockDAO();
    private HashMap<Book, Integer> booksStock = new HashMap<>();
    private BookDAO bookDAO = BookDAO.getInstance();

    /**
     * Construtor privado da Singleton, que impede a criação de uma nova instância da classe.
     */
    private StockDAO() {
        ArrayList<Book> books = bookDAO.getBooks();
        readDataFromSource();
    }

    /**
     * Obtêm a instância do DAO de Estoque
     * @return Instância Singleton do DAO.
     */
    public static StockDAO getInstance() {
        return ourInstance;
    }

    /**
     * Lê os dados do arquivo de JSON e popula o HashMap de estoque com os dados do livro encontrado.
     * Popula a HashMap de livros/estoque para ser utilizada através do sistema.
     */
    private void readDataFromSource() {
        try {
            InputStream stream = StockDAO.class.getResourceAsStream("stock.json");

            Gson gson = new Gson();
            JsonParser p = new JsonParser();
            JsonArray bookStock = p.parse(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject().getAsJsonArray("stock");

            for (JsonElement b: bookStock) {
                JsonObject bookStockData = b.getAsJsonObject();
                long ISBN = bookStockData.get("ISBN").getAsLong();
                int stock = bookStockData.get("stock").getAsInt();

                Book matchedBook = bookDAO.findBook(ISBN);
                if (matchedBook != null) this.booksStock.put(matchedBook, stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Serve a quantidade de unidades em estoque de um Livro. Caso o Livro não exista no estoque, o retorno será 0.
     * @return Inteiro representando a quantidade de livros em estoque.
     */
    public Integer getBookStock(Book book) {
        if (!booksStock.containsKey(book)) return 0;
        return booksStock.get(book);
    }

    /**
     * Reduz em uma unidade a quantidade em estoque de um dado Livro
     * @param book - Livro que terá sua quantidade em estoque deduzida.
     */
    public void decrementBookStock(Book book) {
        if (!booksStock.containsKey(book)) return;

        int currentAmount = booksStock.get(book);
        booksStock.put(book, currentAmount - 1);
    }

    /**
     * Aumenta em uma unidade a quantidade em estoque de um dado Livro
     * @param book - Livro que terá sua quantidade em estoque incrementada.
     */
    public void incrementBookStock(Book book) {
        if (!booksStock.containsKey(book)) return;

        int currentAmount = booksStock.get(book);
        booksStock.put(book, currentAmount + 1);
    }

    @Override
    public String toString() {
        return "StockDAO{" +
                "booksStock=" + booksStock +
                '}';
    }
}
