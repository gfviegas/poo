package com.gfviegas.library.loan;

import com.gfviegas.library.book.Book;
import com.gfviegas.library.stock.StockDAO;
import com.gfviegas.library.user.AuthService;
import com.gfviegas.library.user.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller de Empréstimos, responsável por fazer validações, tratar dados e alterar os dados referente a Empréstimos e Estoque de Livros
 */
public class LoanController {
    private StockDAO stockDAO = StockDAO.getInstance();
    private AuthService authService = AuthService.getInstance();

    /**
     * Verifica se um empréstimo está inativo no momento ou não
     * @param loan - Empréstimo a ser verificado
     * @return Valor booleano representando se o livro está inativo (true) ou não (false)
     */
    public boolean checkLoanInactive(Loan loan) {
        return !loan.isLoaned();
    }


    /**
     * Verifica se o usuário autenticado possui no máximo 2 livros emprestados, e pode solicitar um empréstimo novo.
     * @return Valor booleano representando se o usuário pode solicitar um novo empréstimo (true) ou não (false)
     */
    public boolean checkLoanLimit() {
        User user = authService.getAuthenticatedUser();
        if (user == null) return false;

        return user.getBookLoans().stream().filter(Loan::isLoaned).count() < 3;
    }

    /**
     * Verifica se um certo livro está em estoque e pode ter o seu empréstimo solicitado.
     * @param book - Livro a ser verificado
     * @return Valor booleano representando se o livro possui estoque e pode ser emprestado (true) ou não (false).
     */
    public boolean checkBookStock(Book book) {
        User user = authService.getAuthenticatedUser();
        return (user != null && stockDAO.getBookStock(book) != 0);
    }

    /**
     * Cria um novo empréstimo de um livro para o usuário autenticado
     * @param book - Livro a ser emprestado ao usuário
     * @return Valor booleano representando se o empréstimo foi criado (true) ou não (false).
     */
    public boolean requestBookLoan(Book book) {
        User user = authService.getAuthenticatedUser();
        if (user == null) return false;
        if (stockDAO.getBookStock(book) == 0) return false;

        Loan loan = new Loan(book);
        ArrayList<Loan> userLoans = user.getBookLoans();
        userLoans.add(loan);
        user.setBookLoans(userLoans);
        stockDAO.decrementBookStock(book);

        return true;
    }

    /**
     * Verifica se um empréstimo está vencido
     * @param loan - Empréstimo a ser verificado
     * @return Valor booleano representando se o empréstimo está vencido (true) ou não (false)
     */
    public boolean isLoanDue(Loan loan) {
        return loan.getDueAt().isBefore(LocalDate.now());
    }

    /**
     * Encerra um empréstimo, devolvendo uma unidade ao estoque do livro e atualizando o status do empréstimo
     * @param loan - Empréstimo a ser manipulado
     * @return Valor booleano representando se o empréstimo do usuário foi finalizado (true) ou não (false)
     */
    public boolean finishLoan(Loan loan) {
        User user = authService.getAuthenticatedUser();
        if (user == null) return false;

        loan.setLoaned(false);
        stockDAO.incrementBookStock(loan.getLoanedBook());
        return true;
    }

    /**
     * Renova o empréstimo de um livro do usuário
     * @param loan - Empréstimo a ser manipulado
     * @return Valor booleano representando se o empréstimo foi renovado (true) ou não (false)
     */
    public boolean requestLoanExtend(Loan loan) {
        if (!loan.isLoaned()) return false;
        if (isLoanDue(loan)) return false;
        loan.extendDueDate();
        return true;
    }
}
