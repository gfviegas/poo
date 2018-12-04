package com.gfviegas.library.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller de Livros, responsável por buscar dados referente a Livros
 */
public class BookController {

    /**
     * Dado uma string, busca na lista de livros a ocorrência de um livro que possui um título parecido.
     * @param booksList - Lista de livros a ser buscada
     * @param titleQuery - String representando o texto de busca do título de um livro
     * @return ArrayList de Livros com todas as ocorrências encontradas
     */
    public ArrayList<Book> searchBooks(ArrayList<Book> booksList, String titleQuery) {
        List list = booksList.stream().filter(p -> p.getTitle().matches("(.*)(?i:" + titleQuery + ")(.*)")).collect(Collectors.toList());
        return new ArrayList<Book>(list);
    }
}
