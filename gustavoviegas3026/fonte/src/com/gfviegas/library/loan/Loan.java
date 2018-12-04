package com.gfviegas.library.loan;

import com.gfviegas.library.book.Book;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Entidade representando o Empréstimo de um Livro
 */
public class Loan {
    private LocalDate createdAt;
    private LocalDate dueAt;
    private Book loanedBook;
    private Boolean loaned;

    Loan(Book loanedBook) {
        this.loanedBook = loanedBook;

        // Configura o vencimento do empréstimo para 10 dias.
        createdAt = LocalDate.now();
        dueAt = createdAt.plus(10, ChronoUnit.DAYS);
        loaned = true;
    }

    // GETTERS
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueAt() {
        return dueAt;
    }

    public Boolean isLoaned() {
        return loaned;
    }

    public Book getLoanedBook() {
        return loanedBook;
    }

    // SETTERS
    void setLoaned(Boolean loaned) {
        this.loaned = loaned;
    }


    /**
     * Prorroga o prazo de entrega do livro em 10 dias.
     */
    void extendDueDate() {
        this.dueAt = dueAt.plus(10, ChronoUnit.DAYS);
    }
}
