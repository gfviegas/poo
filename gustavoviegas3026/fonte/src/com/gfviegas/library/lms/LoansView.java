package com.gfviegas.library.lms;


import com.gfviegas.library.book.Book;
import com.gfviegas.library.loan.Loan;
import com.gfviegas.library.loan.LoanController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe de View referente a tela de Empréstimos (template Loans.fxml)
 */
public class LoansView extends BaseView {
    @FXML
    private JFXTextField searchInput;
    @FXML
    private TableView<Loan> loanTableView;
    @FXML
    private TableColumn<Loan, String> loanedCol;
    @FXML
    private TableColumn<Loan, Long> ISBNCol;
    @FXML
    private TableColumn<Loan, String> titleCol;
    @FXML
    private TableColumn<Loan, String> authorsCol;
    @FXML
    private TableColumn<Loan, Calendar> createdAtCol;
    @FXML
    private TableColumn<Loan, Calendar> dueAtCol;

    @FXML
    private Label activeLoanAmountLabel;
    @FXML
    private AnchorPane selectedBookPane;
    @FXML
    private Label selectedBookName;
    @FXML
    private StackPane rootStackPane;

    private LoanController loanController = new LoanController();

    /**
     * Inicializador da tela. Configura os valores a serem exibidos na tabela além de verificar se o usuário está autenticado ou não para vincular
     * o evento de mostrar o painel para manipular os empréstimos
     */
    @FXML
    public void initialize() {
        ISBNCol.setCellValueFactory(data -> {
            Book book = data.getValue().getLoanedBook();
            return new SimpleObjectProperty<>(book.getISBN());
        });
        titleCol.setCellValueFactory(data -> {
            Book book = data.getValue().getLoanedBook();
            return new SimpleObjectProperty<>(book.getTitle());
        });
        authorsCol.setCellValueFactory(data -> {
            Book book = data.getValue().getLoanedBook();
            return new SimpleObjectProperty<>(book.getAuthors());
        });

        loanedCol.setCellValueFactory(data -> {
            Loan loan = data.getValue();
            String message = (loan.isLoaned()) ? "Ativo" : "Encerrado";
            return new ReadOnlyStringWrapper(message);
        });
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        dueAtCol.setCellValueFactory(new PropertyValueFactory<>("dueAt"));

        ArrayList<Loan> loansList = authenticatedUser.getBookLoans();
        if (!loansList.isEmpty()) loanTableView.setItems(FXCollections.observableArrayList(loansList));

        if (authService.isUserAuthenticated()) {
            loanTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null && newSelection.getLoanedBook() != null) {
                    selectedBookPane.setVisible(true);
                    selectedBookName.setText(newSelection.getLoanedBook().getTitle());
                }
            });
        }

        setActiveLoans();
        selectedBookPane.setVisible(false);
    }

    /**
     * Vincula o valor de empréstimos vigentes ao seu label na tela
     */
    @FXML
    private void setActiveLoans() {
        long activeLoans = authenticatedUser.getBookLoans().stream().filter(Loan::isLoaned).count();
        activeLoanAmountLabel.setText(String.valueOf(activeLoans));
    }

    /**
     * Vincula o evento de click do botão de devolver um livro. O evento abre um dialog confirmando a ação do usuário e prossegue caso confirme
     */
    @FXML
    private void openLoanReturnDialog() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        Text headingTitle = new Text("Confirmação de Devolução");
        headingTitle.setFont(Font.font("SansSerif"));
        headingTitle.setStyle("-fx-font-weight: bold");

        JFXButton confirmBtn = getDialogButton("Confirmar", "darkgreen");
        JFXButton cancelBtn = getDialogButton("Cancelar", "darkred");

        dialogLayout.setHeading(headingTitle);
        dialogLayout.setBody(new Text("Você deseja realmente devolver o livro \"" + selectedBookName.getText() + "\"?"));
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
                Loan selectedLoan = loanTableView.getSelectionModel().getSelectedItem();
                dialogLayout.setBody(new Text(loanReturn(selectedLoan)));
                confirmBtn.setVisible(false);
                cancelBtn.setText("OK");
            }
        });

        dialog.show(rootStackPane);
    }

    /**
     * Chama as validações necessárias para se entregar um livro e o entrega, caso tudo esteja válido
     * @param selectedLoan - Empréstimo selecionado do usuário autenticado
     * @return String representando uma mensagem a ser exibida pro usuário, de sucesso, ou de erro.
     */
    private String loanReturn(Loan selectedLoan) {
        if (loanController.checkLoanInactive(selectedLoan)) return "O empréstimo não está ativo e portanto não pode ser devolvido.";
        if (!loanController.finishLoan(selectedLoan)) return "Erro inesperado ao finalizar o empréstimo. Tente novamente mais tarde.";
        loanTableView.refresh();
        setActiveLoans();

        return "Livro devolvido com sucesso! O nosso drone o receberá em seu endereço em breve.";
    }

    /**
     * Vincula o evento de click do botão de renovar o empréstimo de um livro. O evento abre um dialog confirmando a ação do usuário e prossegue caso confirme
     */
    @FXML
    private void openLoanExtendDialog() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        Text headingTitle = new Text("Confirmação de Renovação");
        headingTitle.setFont(Font.font("SansSerif"));
        headingTitle.setStyle("-fx-font-weight: bold");

        JFXButton confirmBtn = getDialogButton("Confirmar", "darkgreen");
        JFXButton cancelBtn = getDialogButton("Cancelar", "darkred");

        dialogLayout.setHeading(headingTitle);
        dialogLayout.setBody(new Text("Você deseja realmente renovar o empréstimo do livro \"" + selectedBookName.getText() + "\"?"));
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
                Loan selectedLoan = loanTableView.getSelectionModel().getSelectedItem();
                dialogLayout.setBody(new Text(loanExtend(selectedLoan)));
                confirmBtn.setVisible(false);
                cancelBtn.setText("OK");
            }
        });

        dialog.show(rootStackPane);

    }

    /**
     * Chama as validações necessárias para se renovar o empréstimo de um livro e o faz, caso tudo esteja válido
     * @param selectedLoan - Empréstimo selecionado do usuário autenticado
     * @return String representando uma mensagem a ser exibida pro usuário, de sucesso, ou de erro.
     */
    @FXML
    private String loanExtend(Loan selectedLoan) {
        if (loanController.checkLoanInactive(selectedLoan)) return "O empréstimo não está ativo e portanto não pode ser renovado.";
        if (loanController.isLoanDue(selectedLoan)) return "Este empréstimo está vencido e portanto não pode ser renovado.";
        if (!loanController.requestLoanExtend(selectedLoan)) return "Erro inesperado ao solicitar a renovação. Tente novamente mais tarde.";

        loanTableView.refresh();

        return "Empréstimo renovado com sucesso! Você pode ficar mais 10 dias com o livro.";
    }
}
