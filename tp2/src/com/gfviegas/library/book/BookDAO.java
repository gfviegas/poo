package com.gfviegas.library.book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.parser.JSONObject;

public class BookDAO {
    private static final String filePath = "./books.json";
    private ArrayList<Book> books;

    public BookDAO() {
        this.books = new ArrayList<Book>();
    }

    private boolean readDataFromSource() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(this.filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }


}
