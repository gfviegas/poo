package com.gfviegas.library.lms;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Classe principal que manipula as views de usuário autenticado (template Main.fxml)
 */
public class MainView extends BaseView {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane pageContentPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private VBox box;
    @FXML
    private HamburgerBackArrowBasicTransition burgerTask;

    /**
     * Incializador da tela. Carrega o menu lateral, configura a sua animação e eventos e carrega a tela inicial (Welcome)
     */
    @FXML
    public void initialize() {
        try {
            box = FXMLLoader.load(getClass().getResource("templates/DrawerMenu.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Node node: box.getChildren()) {
            if (node.getId() != null) bindMenuEvent(node);
        }

        burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);

        changeContentPage("templates/Welcome.fxml");
    }

    /**
     * A partir de um caminho de um template FXML, tenta o carregar
     * @param filePath - URL de um arquivo FXML
     * @return Um AnchorPane com o conteúdo da tela buscada para ser exibida no painel principal
     */
    @FXML
    private AnchorPane getPageScene(URL filePath) {
        try {
            FXMLLoader loader = new FXMLLoader(filePath);
            AnchorPane contentPane = loader.load();
            return contentPane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Muda o conteúdo da pagina principal, buscando um arquivo a partir da string fornecida
     * @param file - String do caminho até o arquivo de template desejado
     */
    @FXML
    private void changeContentPage(String file) {
        URL filePath = getClass().getResource(file);
        AnchorPane contentScene = getPageScene(filePath);

        pageContentPane.getChildren().clear();
        pageContentPane.getChildren().setAll(contentScene);
    }

    /**
     * Redireciona o usuário para a página de Login
     */
    @FXML
    private void logoutView() {
        URL filePath = getClass().getResource("templates/Login.fxml");

        try {
            FXMLLoader loader = new FXMLLoader(filePath);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) anchorPane.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vincula um evento de click para um nó do menu lateral, de forma que mude o conteúdo da página ao ser clicado
     * @param node - Nó do menu lateral para ter o evento vinculado
     */
    @FXML
    private void bindMenuEvent(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            toggleDrawer();
            switch (node.getId()) {
                case "btn0":
                    changeContentPage("templates/Welcome.fxml");
                    break;
                case "btn1":
                    changeContentPage("templates/Books.fxml");
                    break;
                case "btn2":
                    changeContentPage("templates/Loans.fxml");
                    break;
                case "btn3":
                    changeContentPage("templates/Profile.fxml");
                    break;
                case "btn4":
                    authService.logout();
                    logoutView();
                    break;
            }
        });
    }

    /**
     * Alterna a visibilidade do menu lateral e animação do "hamburger"
     */
    @FXML
    private void toggleDrawer() {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();

        if (drawer.isOpened()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    /**
     * Vincula o evento de click ao hamburger para alternar a visibilidade do menu lateral
     */
    @FXML
    public void hamburgerClicked() {
        toggleDrawer();
    }
}
