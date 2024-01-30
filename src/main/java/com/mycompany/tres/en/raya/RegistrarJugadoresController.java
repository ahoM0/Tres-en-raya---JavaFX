/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tres.en.raya;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moha2
 */
public class RegistrarJugadoresController implements Initializable {

    @FXML
    private TextField nombreJugador1;
    @FXML
    private TextField nombreJugador2;
    @FXML
    private Label avisoRellenar;
    @FXML
    private Label campoSoloLetras;

    @FXML
    private Label campoRegistroCorrecto;

    @FXML
    private Button botonRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        avisoRellenar.setVisible(false);
        campoSoloLetras.setVisible(false);
        campoRegistroCorrecto.setVisible(false);
    }

    public ArrayList<Jugador> registroJugadores() throws IOException {
        ArrayList<Jugador> players = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/com/mycompany/tres/en/raya/Ficheros/jugadores.txt");

        if (inputStream != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String contenido;
            while ((contenido = br.readLine()) != null) {
                if (contenido.length() > 0) {
                    String[] content = contenido.split(";");
                    String nombre = content[0];
                    String vic = content[1];

                    int victorias = Integer.parseInt(vic);

                    Jugador jug = new Jugador(nombre, victorias);
                    players.add(jug);
                }

            }

            br.close();
        } else {
            System.out.println("Recurso no encontrado.");
        }

        return players;
    }

    public boolean comprobarJugador(ArrayList<Jugador> players, String nom) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getNombre().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    public void registrarJugadores(String jug1, String jug2) throws IOException {
        ArrayList<Jugador> copiaJugadores = registroJugadores();

        if (!comprobarJugador(copiaJugadores, jug1)) {
            String rutaArchivo = "src/main/resources/com/mycompany/tres/en/raya/Ficheros/jugadores.txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true));
            String victorias = "0";
            String jugador1 = jug1 + ";" + victorias;

            writer.newLine();
            writer.write(jugador1);
            writer.close();
        }

        if (!comprobarJugador(copiaJugadores, jug2)) {
            String rutaArchivo2 = "src/main/resources/com/mycompany/tres/en/raya/Ficheros/jugadores.txt";

            BufferedWriter writer2 = new BufferedWriter(new FileWriter(rutaArchivo2, true));
            String victorias2 = "0";
            String jugador2 = jug2 + ";" + victorias2;

            writer2.newLine();
            writer2.write(jugador2);
            writer2.close();
        }

        campoRegistroCorrecto.setVisible(true);
    }

    @FXML
    public void empezarJuego(ActionEvent event) throws IOException {

        if (nombreJugador1.getText().isEmpty() || nombreJugador2.getText().isEmpty()) {
            avisoRellenar.setVisible(true);
            PauseTransition pause = new PauseTransition(javafx.util.Duration.millis(2000));
            pause.setOnFinished(e -> avisoRellenar.setVisible(false));
            pause.play();

        } else {
            String texto1 = nombreJugador1.getText();
            String texto2 = nombreJugador2.getText();
            boolean contieneSoloLetras1 = texto1.matches("[a-zA-Z]+");
            boolean contieneSoloLetras2 = texto2.matches("[a-zA-Z]+");
            if (contieneSoloLetras1 && contieneSoloLetras2) {
                String jugador1 = texto1;
                String jugador2 = texto2;
                registrarJugadores(jugador1, jugador2);
            } else {
                campoSoloLetras.setVisible(true);
                PauseTransition pause = new PauseTransition(javafx.util.Duration.millis(2000));
                pause.setOnFinished(e -> campoSoloLetras.setVisible(false));
                pause.play();
            }
        }

        if (campoRegistroCorrecto.isVisible()) {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("Juego.fxml"));
            Parent root = cargador.load();
            JuegoController controlador = cargador.getController();

            controlador.setRegistrarJugadoresController(this);
            controlador.actualizarNombresJugadores(nombreJugador1.getText(), nombreJugador2.getText());

            Scene scene = new Scene(root);
            Stage juego = new Stage();
            juego.setScene(scene);
            juego.setTitle("Partida");
            juego.setResizable(false);
            juego.show();

            Stage ventanaActual = (Stage) botonRegistro.getScene().getWindow();
            ventanaActual.close();
        }

    }

    @FXML
    public void cancelarRegistro(ActionEvent event) throws IOException {
        Stage ventanaActual = (Stage) botonRegistro.getScene().getWindow();
        ventanaActual.close();
    }

}
