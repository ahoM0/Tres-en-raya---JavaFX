/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tres.en.raya;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dam1t
 */
public class MenuController implements Initializable {

    @FXML
    private Button botonStart;
    @FXML
    private Button botonPuntuacion;
    @FXML
    private Button butonSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        botonPuntuacion.setVisible(true);
    }

    @FXML
    public void empezarPartida(ActionEvent event) throws IOException {
        Stage juego = new Stage();
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("RegistrarJugadores.fxml"));
        Parent root = cargador.load();
        RegistrarJugadoresController controlador = cargador.getController();
        Scene scene = new Scene(root);

        juego.setScene(scene);
        juego.setTitle("Registro Jugadores");
        juego.setResizable(false);
        juego.show();

    }

    @FXML
    public void verPuntuacion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreBoard.fxml"));

        Stage ventanaEmergente = new Stage();
        ventanaEmergente.initModality(Modality.APPLICATION_MODAL);
        ventanaEmergente.initOwner(((Node) event.getSource()).getScene().getWindow());
        ventanaEmergente.setTitle("Ver Puntuaciones de los Jugadores");
        ventanaEmergente.setResizable(false);

        Scene scene = new Scene(loader.load());
        ventanaEmergente.setScene(scene);
        ventanaEmergente.showAndWait();
    }

    @FXML
    public void cerrarVentana(ActionEvent event) {
        Stage ventanaActual = (Stage) butonSalir.getScene().getWindow();
        ventanaActual.close();
    }

}
