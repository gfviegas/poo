package com.gfviegas.library.lms;

import com.gfviegas.library.book.Book;
import com.gfviegas.library.book.BookController;
import com.gfviegas.library.book.BookDAO;
import com.gfviegas.library.loan.LoanController;
import com.gfviegas.library.stock.StockDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de View referente a tela de Livros (template Books.fxml)
 */
public class BooksView extends BaseView {
    @FXML
    private JFXTextField searchInput;
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> ISBNCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorsCol;
    @FXML
    private TableColumn<Book, Integer> editionCol;
    @FXML
    private TableColumn<Book, String> dimensionsCol;
    @FXML
    private TableColumn<Book, String> weightCol;
    @FXML
    private TableColumn<Book, Integer> yearCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private TableColumn<Book, Integer> stockCol;

    @FXML
    private AnchorPane selectedBookPane;
    @FXML
    private Label selectedBookName;
    @FXML
    private StackPane rootStackPane;

    private ArrayList<Book> booksList;
    private BookDAO bookDAO = BookDAO.getInstance();
    private StockDAO stockDAO = StockDAO.getInstance();
    private BookController bookController = new BookController();
    private LoanController loanController = new LoanController();

    /**
     * Inicializador da tela. Configura e carrega os valores a serem exibidos na tabela além de verificar se o usuário
     * está autenticado ou não para vincular o evento de mostrar o painel para solicitar os empréstimos
     */
    @FXML
    public void initialize() {
        ISBNCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        dimensionsCol.setCellValueFactory(data -> {
            Book book = data.getValue();
            return new ReadOnlyStringWrapper(book.getDimensionFormatted());
        });
        weightCol.setCellValueFactory(data -> {
            Book book = data.getValue();
            return new ReadOnlyStringWrapper(book.getWeightFormatted());
        });

        stockCol.setCellValueFactory(data -> {
            Book book = data.getValue();
            Integer stock = stockDAO.getBookStock(book);
            return new ReadOnlyObjectWrapper<>(stock);
        });

        booksList = bookDAO.getBooks();
        bookTableView.setItems(FXCollections.observableArrayList(booksList));

        if (authService.isUserAuthenticated()) {
            bookTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    selectedBookPane.setVisible(true);
                    selectedBookName.setText(newSelection.getTitle());
                }
            });
        }

        selectedBookPane.setVisible(false);
    }

    /**
     * Vincula o evento de botão pressionado no formulário de busca. Ao receber a tecla enter, a busca é realizada.
     * @param e - Evento de pressionamento de um botão, automaticamente vinculado pelo JavaFX
     */
    @FXML
    private void searchInputKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) searchBook();
    }

    /**
     * Chama o método para buscar um livro pelo texto digitado e atualiza lista de livros com os dados que casam a busca
     */
    @FXML
    private void searchBook() {
        String query = searchInput.getText();
        bookTableView.setItems(FXCollections.observableArrayList(bookController.searchBooks(booksList, query)));
    }

    /**
     * Vincula o evento de click do botão de solicitar o empréstimo um livro. O evento abre um dialog confirmando a ação do usuário e prossegue caso confirme
     */
    @FXML
    private void openLoanDialog() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        Text headingTitle = new Text("Confirmação de Empréstimo");
        headingTitle.setFont(Font.font("SansSerif"));
        headingTitle.setStyle("-fx-font-weight: bold");

        JFXButton confirmBtn = getDialogButton("Confirmar", "darkgreen");
        JFXButton cancelBtn = getDialogButton("Cancelar", "darkred");

        dialogLayout.setHeading(headingTitle);
        dialogLayout.setBody(new Text("Você deseja realmente solicitar o empréstimo do livro \"" + selectedBookName.getText() + "\"?"));
        dialogLayout.setActions(List.of(confirmBtn, cancelBtn));

        JFXDialog dialog = new JFXDialog(rootStackPane, dialogLayout, JFXDialog.DialogTransition.BOTTOM);

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
                dialogLayout.setBody(new Text(loanBook(selectedBook)));
                confirmBtn.setVisible(false);
                cancelBtn.setText("OK");
            }
        });


        dialog.show(rootStackPane);
    }

    /**
     * Chama as validações necessárias para se emprestar um livro e o empresta, caso tudo esteja válido
     * @param selectedBook - Livro selecionado para a criação de um empréstimo para o usuário autenticado
     * @return String representando uma mensagem a ser exibida pro usuário, de sucesso, ou de erro.
     */
    @FXML
    private String loanBook(Book selectedBook) {
        if (!loanController.checkLoanLimit()) return "Você já possui 3 livros emprestados e não pode solicitar um novo empréstimo.";
        if (!loanController.checkBookStock(selectedBook)) return "Este livro não possui estoque disponível e não pode ser emprestado.";
        if (!loanController.requestBookLoan(selectedBook)) return "Erro inesperado ao solicitar o empréstimo. Tente novamente mais tarde.";

        bookTableView.refresh();

        return "Empréstimo solicitado com sucesso! O nosso drone entregará em seu endereço em breve.";
    }
}
