package com.gfviegas.library.book;

import com.gfviegas.library.Author;

import java.awt.Dimension;

public class Book {
    private final int ISBN;
    private String title;
    private String publisher;
    private Author[] authors;
    private final int edition;
    private Dimension dimension;

    public Book(int ISBN, String title, String publisher, Author[] authors, int edition, int width, int height) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.edition = edition;
        this.dimension = new Dimension(width, height);
    }
}