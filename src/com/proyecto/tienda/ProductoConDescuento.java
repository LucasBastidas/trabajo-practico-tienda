package com.proyecto.tienda;

public interface ProductoConDescuento {

    // Método para establecer el porcentaje de descuento
    void setPorcentajeDescuento(double porcentaje);

    // Método para obtener el porcentaje de descuento
    double getPorcentajeDescuento();

    // Método para obtener el precio de venta con descuento
    double getPrecioVentaConDescuento();
}
