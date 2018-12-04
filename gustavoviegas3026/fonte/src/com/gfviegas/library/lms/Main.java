package com.gfviegas.library.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * Classe Principal, que inicia a aplicação JavaFX
 */
public class Main extends Application {

    /**
     * Carrega a pagina inicial (login).
     * @param primaryStage Stage do JavaFX, auto-chamado
     * @throws Exception Exceção de arquivo não encontrado
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = null;
        Parent root = null;
        try  {
            String sceneFile = "templates/Login.fxml";
            url = getClass().getResource(sceneFile);
            root = FXMLLoader.load(url);
            System.out.println( "  fxmlResource = " + sceneFile);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("LMS - Login");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon.png")));
            primaryStage.centerOnScreen();

            primaryStage.show();
        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }
    }


    /**
     * Inicializa a aplicação
     */
    public static void main(String[] args) {
        launch(args);
    }
}
