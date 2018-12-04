package com.gfviegas.library.lms;

import com.gfviegas.library.user.AuthService;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

/**
 * Classe de View referente a tela do menu lateral (template DrawerMenu.fxml)
 */
public class DrawerMenu extends BaseView {
    @FXML
    private JFXButton btn0;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn4;

    /**
     * Inicializador da tela. Configura os botões de Meu Perfil e Empréstimos como invisíveis caso o usuário esteja desconectado.
     */
    @FXML
    public void initialize() {
        if (!authService.isUserAuthenticated()) {
            btn2.setVisible(false);
            btn3.setVisible(false);
        }
    }
}
