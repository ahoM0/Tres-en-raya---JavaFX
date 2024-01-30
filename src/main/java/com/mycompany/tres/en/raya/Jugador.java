/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tres.en.raya;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dam1t
 */
public class Jugador {

    private final StringProperty Nombre = new SimpleStringProperty();
    private final IntegerProperty Victorias = new SimpleIntegerProperty(0);

    public Jugador(String nombre, int victorias) {
        Nombre.setValue(nombre);
        Victorias.setValue(victorias);
    }

    public final StringProperty NombreProperty() {
        return this.Nombre;
    }

    public final java.lang.String getNombre() {
        return this.NombreProperty().get();
    }

    public final void setNombre(final java.lang.String Nombre) {
        this.NombreProperty().set(Nombre);
    }

    public final IntegerProperty VictoriasProperty() {
        return this.Victorias;
    }

    public final java.lang.Integer getVictorias() {
        return this.VictoriasProperty().get();
    }

    public final void setVictorias(final java.lang.Integer Victorias) {
        this.VictoriasProperty().set(Victorias);
    }
}
