package com.gfviegas.library.lms;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


/**
 * View referente a tela de login (template Login.fxml)
 */
public class LoginView extends BaseView {
    @FXML
    private JFXTextField id;
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Pane spinnerPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorMsg;

    /**
     * Mostra o pane com o spinner de carregando e oculta o de erro se estiver visível
     */
    @FXML
    private void showLoadingPane() {
        overlayPane.setVisible(true);
        errorPane.setVisible(false);
        spinnerPane.setVisible(true);
    }

    /**
     * Esconde o pane de erro e remove o texto a ele vinculado
     */
    @FXML
    private void hideErrorPane() {
        errorMsg.setText("");
        overlayPane.setVisible(false);
        errorPane.setVisible(false);
        spinnerPane.setVisible(false);
    }

    /**
     * Mostra o pane de erro e atribui a mensagem do parâmetro como a sua mensagem a ser exibida
     * Após 3 segundos, o pane se esconde automaticamente.
     * @param message - String de mensagem a ser exibida ao usuário
     */
    @FXML
    private void showErrorPane(String message) {
        errorMsg.setText(message);
        overlayPane.setVisible(true);
        errorPane.setVisible(true);
        spinnerPane.setVisible(false);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> hideErrorPane());
        delay.play();
    }

    /**
     * Vincula o evento de botão pressionado no formulário. Ao receber a tecla enter, a autenticação é realizada.
     * @param e - Evento de pressionamento de um botão, automaticamente vinculado pelo JavaFX
     */
    @FXML
    private void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) submitForm();
    }

    /**
     * Valida os campos de matrícula e senha para verificar se são validos e preenchidos corretamente.
     * @return Valor booleano representando se os campos estão digitados com valores válidos (true) ou não (false)
     */
    @FXML
    private boolean validateFields() {
        String id = this.id.getText();
        String password = this.password.getText();

        if (id.isEmpty() || ! id.matches("\\d+")) {
            showErrorPane("O campo matrícula é inválido. \n O campo é obrigatório e aceita apenas números.");
            return false;
        }

        if (password.isEmpty()) {
            showErrorPane("O campo senha é inválido. \n O campo é obrigatório.");
            return false;
        }

        return true;
    }

    /**
     * Tenta realizar a autenticação. Se as credenciais estiver corretas, o usuário é redirecionado para outra tela. Caso contrário, uma mensagem de erro é exibida
     */
    @FXML
    private void submitForm() {
        showLoadingPane();
        if (!validateFields()) return;

        if (authService.authenticate(Integer.valueOf(id.getText()), password.getText())) {

            // Delay para melhorar transição.
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> loadMainView());
            delay.play();

            return;
        }

        showErrorPane("Credenciais inválidas!");
    }

    /**
     * Vincula o evento de click no link para continuar sem autenticar. O usuário é redirecionado para a tela principal sem ter um usuário autenticado vinculado ao sistema
     */
    @FXML
    private void continueAsGuest() {
        showLoadingPane();

        // Delay para melhorar transição.
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> loadMainView());
        delay.play();
    }

    /**
     * Redireciona o usuário para a tela principal, que gerencia as telas autenticadas do sistema. Por default, o sistema carregará a home como pane principal
     */
    @FXML
    private void loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templates/Main.fxml"));
            Parent root = loader.load();
            MainView controller = loader.<MainView>getController();
            controller.setAuthenticatedUser(authService.getAuthenticatedUser());

            Scene scene = new Scene(root);
            Stage stage = (Stage) overlayPane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
