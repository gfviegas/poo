package com.gfviegas.library.lms;

import com.gfviegas.library.user.AuthService;
import com.gfviegas.library.user.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.paint.Paint;


/**
 * Classe base para as classes tipo View do sistema.
 * Implementa funcões básicas e reutilizadas nas intefaces.
 */
public class BaseView implements ViewInterface {
    User authenticatedUser;
    AuthService authService = AuthService.getInstance();


    /**
     * Construtor da classe base da View. Seta o valor do usuário autenticado para ser facilmente acessada em qualquer tela
     */
    BaseView() {
        authenticatedUser = authService.getAuthenticatedUser();
    }

    /**
     * Método auxiliar para buscar o usuário autenticado
     * @return Instância do usuário autenticado
     */
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }


    /**
     * Método auxiliar para atualizar o valor do usuário autenticado
     * @param authenticatedUser - Instância do usuário autenticado
     */
    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        authService.setAuthenticatedUser(this.authenticatedUser);
    }

    /**
     * Função atômica para criar um botão para ser utilizado em alguma caixa de diálogo (modal)
     * @param text - String de texto a ser inserido no botão
     * @param bgColor - String representando a cor do background do botão
     * @return Instância do botão estilizada para um dialog
     */
    @FXML
    JFXButton getDialogButton(String text, String bgColor) {
        JFXButton button = new JFXButton(text);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: " + bgColor);
        button.setTextFill(Paint.valueOf("#eeeeee"));
        return button;
    }
}
