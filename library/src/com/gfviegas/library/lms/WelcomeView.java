package com.gfviegas.library.lms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Classe de View referente a tela de Bem-Vindo (template Welcome.fxml)
 */
public class WelcomeView extends BaseView {
    @FXML
    private Label auxiliarMsg;
    @FXML
    private Label welcomeTitle;

    /**
     * Incializador da tela. Configura o título da página para dar boas vindas ao nome do usuário ou ao visitante
     */
    @FXML
    public void initialize() {
        this.welcomeTitle.setText("Bem vindo, " + this.authService.getAuthUserName() + "!");
    }
}
