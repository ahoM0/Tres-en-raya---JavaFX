/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tres.en.raya;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moha2
 */
public class JuegoController implements Initializable {

    @FXML
    private Button boton00;
    @FXML
    private Button boton01;
    @FXML
    private Button boton02;
    @FXML
    private Button boton10;
    @FXML
    private Button boton11;
    @FXML
    private Button boton12;
    @FXML
    private Button boton20;
    @FXML
    private Button boton21;
    @FXML
    private Button boton22;

    @FXML
    private Line linea0horizontal;
    @FXML
    private Line linea1horizontal;
    @FXML
    private Line linea2horizontal;
    @FXML
    private Line linea0vertical;
    @FXML
    private Line linea1vertical;
    @FXML
    private Line linea2vertical;
    @FXML
    private Line line0a2diagonal;
    @FXML
    private Line line2a0diagonal;

    @FXML
    private Button botonSalir;
    @FXML
    private Button botonLimpiar;
    @FXML
    private Button botonJugar;
    @FXML
    private Label nombreJug1;
    @FXML
    private Label nombreJug2;

    private RegistrarJugadoresController registrarJugadoresController;
    @FXML
    private Label turnoJugador1;
    @FXML
    private Label turnoJugador2;
    @FXML
    private TextField campoVictoriasJug1;
    @FXML
    private TextField campoVictoriasJug2;

    public String Jugador1;
    public String Jugador2;

    public int ganador = 0;
    public int contador = 0;
    @FXML
    private Label ganajug1;
    @FXML
    private Label ganajug2;
    @FXML
    private GridPane panel;
    @FXML
    private Button guardar;
    @FXML
    private Label guardadlabel;
    @FXML
    private Button revertirRoles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        guardadlabel.setVisible(false);
        panel.setDisable(true);
        linea0horizontal.setVisible(false);
        linea1horizontal.setVisible(false);
        linea2horizontal.setVisible(false);
        linea0vertical.setVisible(false);

        linea1vertical.setVisible(false);
        linea2vertical.setVisible(false);
        line0a2diagonal.setVisible(false);
        line2a0diagonal.setVisible(false);
        turnoJugador1.setVisible(false);
        turnoJugador2.setVisible(false);
        ganajug1.setVisible(false);
        ganajug2.setVisible(false);
        campoVictoriasJug1.setEditable(false);
        campoVictoriasJug2.setEditable(false);

        //Doy formato a las X y O de los botones
        boton00.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton01.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton02.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton10.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton11.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton12.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton20.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton21.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");
        boton22.setStyle("-fx-font-size: 38px; -fx-text-fill: darkred;");

    }

    public void actualizarNombresJugadores(String jugador1, String jugador2) {
        nombreJug1.setText(jugador1);
        nombreJug2.setText(jugador2);
        this.Jugador1 = jugador1;
        this.Jugador2 = jugador2;
    }

    public void setRegistrarJugadoresController(RegistrarJugadoresController controller) {
        this.registrarJugadoresController = controller;
    }

    @FXML
    public void salir(ActionEvent event) throws IOException, FileNotFoundException, URISyntaxException {
        Stage ventanaActual = (Stage) botonSalir.getScene().getWindow();
        ventanaActual.close();
    }

    @FXML
    public void Limpiar(ActionEvent event) throws IOException, FileNotFoundException, URISyntaxException {
        panel.setDisable(false);

        boton00.setText("");
        boton01.setText("");
        boton02.setText("");
        boton10.setText("");
        boton11.setText("");
        boton12.setText("");
        boton20.setText("");
        boton21.setText("");
        boton22.setText("");


        ganajug1.setVisible(false);
        ganajug2.setVisible(false);
        linea0horizontal.setVisible(false);
        linea1horizontal.setVisible(false);
        linea2horizontal.setVisible(false);
        linea0vertical.setVisible(false);
        linea1vertical.setVisible(false);
        linea2vertical.setVisible(false);
        line0a2diagonal.setVisible(false);
        line2a0diagonal.setVisible(false);

        this.ganador = 0;
        this.contador = 0;

        botonJugar.setDisable(false);

    }

    @FXML
    public void Jugar(ActionEvent event) throws IOException, URISyntaxException {
        turnoJugador1.setText(Jugador1);
        turnoJugador2.setText(Jugador2);
        turnoJugador1.setVisible(true);
        panel.setDisable(false);

    }

    public void determinarGanador(int contador) throws IOException {
        if (contador <= 9) {

            //Comprobar si hay linea ariiba
            if ("X".equals(boton00.getText()) && "X".equals(boton01.getText()) && "X".equals(boton02.getText())) {
                linea0horizontal.setVisible(true);
                this.ganador = 1;
                //Comprueba si hay linea en Medio
            } else if ("X".equals(boton10.getText()) && "X".equals(boton11.getText()) && "X".equals(boton12.getText())) {
                linea1horizontal.setVisible(true);
                this.ganador = 1;
                //Comprueba si hay linea abajo
            } else if ("X".equals(boton20.getText()) && "X".equals(boton21.getText()) && "X".equals(boton22.getText())) {
                linea2horizontal.setVisible(true);
                this.ganador = 1;
                //Copmprueba si hay linea vertical izquierda 
            } else if ("X".equals(boton00.getText()) && "X".equals(boton10.getText()) && "X".equals(boton20.getText())) {
                linea0vertical.setVisible(true);
                this.ganador = 1;
                //Copmprueba si hay linea vertical medio
            } else if ("X".equals(boton01.getText()) && "X".equals(boton11.getText()) && "X".equals(boton21.getText())) {
                linea1vertical.setVisible(true);
                this.ganador = 1;
                //Copmprueba si hay linea vertical derecha
            } else if ("X".equals(boton02.getText()) && "X".equals(boton12.getText()) && "X".equals(boton22.getText())) {
                linea2vertical.setVisible(true);
                this.ganador = 1;
                //Comprueba si hay linea diagonal 0a2
            } else if ("X".equals(boton00.getText()) && "X".equals(boton11.getText()) && "X".equals(boton22.getText())) {
                line0a2diagonal.setVisible(true);
                this.ganador = 1;
                //Compruebo ultima diagonal
            } else if ("X".equals(boton02.getText()) && "X".equals(boton11.getText()) && "X".equals(boton20.getText())) {
                line2a0diagonal.setVisible(true);
                this.ganador = 1;
            }

            if ("O".equals(boton00.getText()) && "O".equals(boton01.getText()) && "O".equals(boton02.getText())) {
                linea0horizontal.setVisible(true);
                this.ganador = 2;
                //Comprueba si hay linea en Medio
            } else if ("O".equals(boton10.getText()) && "O".equals(boton11.getText()) && "O".equals(boton12.getText())) {
                linea1horizontal.setVisible(true);
                this.ganador = 2;
                //Comprueba si hay linea abajo
            } else if ("O".equals(boton20.getText()) && "O".equals(boton21.getText()) && "O".equals(boton22.getText())) {
                linea2horizontal.setVisible(true);
                this.ganador = 2;
                //Copmprueba si hay linea vertical izquierda 
            } else if ("O".equals(boton00.getText()) && "O".equals(boton10.getText()) && "O".equals(boton20.getText())) {
                linea0vertical.setVisible(true);
                this.ganador = 2;
                //Copmprueba si hay linea vertical medio
            } else if ("O".equals(boton01.getText()) && "O".equals(boton11.getText()) && "O".equals(boton21.getText())) {
                linea1vertical.setVisible(true);
                this.ganador = 2;
                //Copmprueba si hay linea vertical derecha
            } else if ("O".equals(boton02.getText()) && "O".equals(boton12.getText()) && "O".equals(boton22.getText())) {
                linea2vertical.setVisible(true);
                this.ganador = 2;
                //Comprueba si hay linea diagonal 0a2
            } else if ("O".equals(boton00.getText()) && "O".equals(boton11.getText()) && "O".equals(boton22.getText())) {
                line0a2diagonal.setVisible(true);
                this.ganador = 2;
                //Compruebo ultima diagonal
            } else if ("O".equals(boton02.getText()) && "O".equals(boton11.getText()) && "O".equals(boton20.getText())) {
                line2a0diagonal.setVisible(true);
                this.ganador = 2;
            }

            if (this.ganador == 1) {

                ganajug1.setVisible(true);
                String vic = campoVictoriasJug1.getText();
                int n = Integer.parseInt(vic);
                n = n + 1;
                String num = String.valueOf(n);
                campoVictoriasJug1.setText(num);
                panel.setDisable(true);
                botonJugar.setDisable(true);
                turnoJugador1.setVisible(false);
                 turnoJugador2.setVisible(false);
            } else if (this.ganador == 2) {

                ganajug2.setVisible(true);
                String vic = campoVictoriasJug2.getText();
                int n = Integer.parseInt(vic);
                n = n + 1;
                String num = String.valueOf(n);
                campoVictoriasJug2.setText(num);
                panel.setDisable(true);
                botonJugar.setDisable(true);
                turnoJugador2.setVisible(false);
                 turnoJugador1.setVisible(false);
            }

        }

    }

    public void actualizarVictorias(String jug1, String jug2) throws IOException, URISyntaxException {
        ArrayList<Jugador> players = new ArrayList<>();

        FileReader fic = new FileReader("src/main/resources/com/mycompany/tres/en/raya/Ficheros/jugadores.txt");
        BufferedReader br = new BufferedReader(fic);
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

        String vjug1 = campoVictoriasJug1.getText();
        int vic1 = Integer.parseInt(vjug1);
        String vjug2 = campoVictoriasJug2.getText();
        int vic2 = Integer.parseInt(vjug2);

        // Actualiza los datos en el ArrayList "players"
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getNombre().equals(jug1)) {
                int v = players.get(i).getVictorias();
                v += vic1;
                players.get(i).setVictorias(v);
            }
            if (players.get(i).getNombre().equals(jug2)) {
                int v = players.get(i).getVictorias();
                v += vic2;
                players.get(i).setVictorias(v);
            }
        }

        String rutaArchivo = "src/main/resources/com/mycompany/tres/en/raya/Ficheros/jugadores.txt";
        String rutaTemporal = "src/main/resources/com/mycompany/tres/en/raya/Ficheros/jugadores.txt.tmp";

        FileWriter fichero = new FileWriter(rutaTemporal);
        for (int i = 0; i < players.size(); i++) {
            String jugador = players.get(i).getNombre() + ";" + players.get(i).getVictorias();
            fichero.write(jugador + System.lineSeparator());
        }
        fichero.close();

        Files.deleteIfExists(Paths.get(rutaArchivo));
        Files.move(Paths.get(rutaTemporal), Paths.get(rutaArchivo));

    }

    @FXML
    public void boton00(ActionEvent event) throws IOException {

        if ("".equals(boton00.getText())) {
            if (turnoJugador1.isVisible()) {
                boton00.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton00.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton01(ActionEvent event) throws IOException {
        if ("".equals(boton01.getText())) {
            if (turnoJugador1.isVisible()) {
                boton01.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton01.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton02(ActionEvent event) throws IOException {
        if ("".equals(boton02.getText())) {
            if (turnoJugador1.isVisible()) {
                boton02.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton02.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton10(ActionEvent event) throws IOException {
        if ("".equals(boton10.getText())) {
            if (turnoJugador1.isVisible()) {
                boton10.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton10.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton11(ActionEvent event) throws IOException {
        if ("".equals(boton11.getText())) {
            if (turnoJugador1.isVisible()) {
                boton11.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton11.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton12(ActionEvent event) throws IOException {
        if ("".equals(boton12.getText())) {
            if (turnoJugador1.isVisible()) {
                boton12.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton12.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton20(ActionEvent event) throws IOException {
        if ("".equals(boton20.getText())) {
            if (turnoJugador1.isVisible()) {
                boton20.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton20.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton21(ActionEvent event) throws IOException {
        if ("".equals(boton21.getText())) {
            if (turnoJugador1.isVisible()) {
                boton21.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton21.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void boton22(ActionEvent event) throws IOException {
        if ("".equals(boton22.getText())) {
            if (turnoJugador1.isVisible()) {
                boton22.setText("X");
                contador++;
                determinarGanador(contador);
                turnoJugador1.setVisible(false);
                turnoJugador2.setVisible(true);
            } else if (turnoJugador2.isVisible()) {
                boton22.setText("O");
                contador++;
                determinarGanador(contador);
                turnoJugador2.setVisible(false);
                turnoJugador1.setVisible(true);
            }
        }

    }

    @FXML
    public void guardado(ActionEvent event) throws IOException, URISyntaxException {
        actualizarVictorias(this.Jugador1, this.Jugador2);
        campoVictoriasJug1.setText("0");
        campoVictoriasJug2.setText("0");
        guardadlabel.setVisible(true);
        PauseTransition pause = new PauseTransition(javafx.util.Duration.millis(2000));
        pause.setOnFinished(e -> guardadlabel.setVisible(false));
        pause.play();
    }

   

}
