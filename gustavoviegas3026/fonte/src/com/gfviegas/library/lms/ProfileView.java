package com.gfviegas.library.lms;

import com.gfviegas.library.Address;
import com.gfviegas.library.user.AuthService;
import com.gfviegas.library.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Classe de View referente a tela de Perfil (template Profile.fxml)
 */
public class ProfileView extends BaseView {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private Label cityLabel;

    /**
     * Inicializador da tela. Carrega os dados do usuário autenticado e o vincula ao seu label respectivo
     */
    @FXML
    public void initialize() {
        Address userAddress = authenticatedUser.getAddress();
        idLabel.setText(String.valueOf(authenticatedUser.getId()));
        nameLabel.setText(authenticatedUser.getName());
        streetLabel.setText(userAddress.getStreet());
        numberLabel.setText(String.valueOf(userAddress.getNumber()));
        zipCodeLabel.setText(String.valueOf(userAddress.getZipCode()));
        cityLabel.setText(userAddress.getCity());
    }
}
