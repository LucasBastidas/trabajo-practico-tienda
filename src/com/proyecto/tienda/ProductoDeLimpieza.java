package com.proyecto.tienda;

public class ProductoDeLimpieza extends Producto {
    private static final String ID_FORMAT = "AZ\\d{3}";

    private TipoAplicacion tipoAplicacion;

    public ProductoDeLimpieza(String id,String titulo, String descripcion, int stock, double precioUnidad, double costoUnidad, TipoAplicacion tipoAplicacion) {
        if (!id.matches(ID_FORMAT)){
            throw new IllegalArgumentException("El id no cumple con el formato requerido");
        }
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setDisponible(stock>0);
        this.setStock(stock);
        this.setPrecioPorUnidad(precioUnidad);
        this.setCostoPorUnidad(costoUnidad);
        this.setTipoAplicacion(tipoAplicacion);

    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }
}
