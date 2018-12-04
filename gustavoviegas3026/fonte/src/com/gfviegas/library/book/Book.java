package com.gfviegas.library.book;

import java.awt.*;

/**
 * Entidade representando um Livro
 */
public class Book {
    private final long ISBN;
    private final String title;
    private final String publisher;
    private final String authors;
    private final int edition;
    private final Dimension dimension;
    private final double weight;
    private final int year;

    Book(long ISBN, String title, String publisher, String authors, int edition, int width, int height, double weight, int year) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.edition = edition;
        this.dimension = new Dimension(width, height);
        this.weight = weight;
        this.year = year;
    }

    // GETTERS
    public long getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public int getEdition() {
        return edition;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public double getWeight() {
        return weight;
    }

    public int getYear() {
        return year;
    }

    /**
     * Formata as dimensões do livro representando seu valor em centímetros. Ex: 23x30 cm
     * @return String formatada das dimensões do livro
     */
    public String getDimensionFormatted() {
        return dimension.width + "x" + dimension.height + " cm";
    }

    /**
     * Formata o peso do livro representando seu valor em gramas. Ex: 234 g
     * @return String formatada do peso do livro
     */
    public String getWeightFormatted() {
        return weight + " g";
    }
}
