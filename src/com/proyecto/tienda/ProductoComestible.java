package com.proyecto.tienda;

import java.time.LocalDate;

public interface ProductoComestible {

    // Método para obtener la fecha de vencimiento
    LocalDate getFechaVencimiento();

    // Método para obtener las calorias
    int getCalorias();

    // Método para setear la fecha de vencimiento
    void setFechaVencimiento(LocalDate fechaVencimiento);

    // Método para setear las calorias
    void setCalorias(int calorias);

}
