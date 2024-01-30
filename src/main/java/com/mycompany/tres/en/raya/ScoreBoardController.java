/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tres.en.raya;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moha2
 */
public class ScoreBoardController implements Initializable {

    @FXML
    private TableView<Jugador> vistaTabla;
    @FXML
    private TableColumn<?, ?> columnaNombre;
    @FXML
    private TableColumn<?, ?> columnaVictorias;
    @FXML
    private Button salirboton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Tengo que darle el mismo nombre que el de la columna de la tabla
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaVictorias.setCellValueFactory(new PropertyValueFactory<>("Victorias"));

        try {
            guardarJugadores();
        } catch (IOException e) {
        }

    }

    public void guardarJugadores() throws FileNotFoundException, IOException {
        InputStream fic = getClass().getResourceAsStream("/com/mycompany/tres/en/raya/Ficheros/jugadores.txt");
        BufferedReader BR = new BufferedReader(new InputStreamReader(fic));
        String contenido;
        String nombre;
        String vic;
        ArrayList<Jugador> jugadores = new ArrayList<>();

        while ((contenido = BR.readLine()) != null) {
            if (contenido.length() > 0) {
                String[] division = contenido.split(";");
                nombre = division[0];
                vic = division[1];
                int victorias = Integer.parseInt(vic);
                Jugador jug1 = new Jugador(nombre, victorias);
                jugadores.add(jug1);
            }

        }

        ObservableList<Jugador> listaJugadores = FXCollections.observableArrayList(jugadores);
        vistaTabla.setItems(listaJugadores);
    }

    @FXML
    public void salir(ActionEvent event) {
        Stage stage = (Stage) salirboton.getScene().getWindow();
        stage.close();
    }

}
