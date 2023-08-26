package com.proyecto.tienda;

import java.time.LocalDate;


public class ProductoEnvasado extends Producto implements ProductoComestible {

    //Es el formato para el ID del PRODUCTO ENVASADO
    private static final String ID_FORMAT = "AB\\d{3}";
    private TipoEnvasado tipoEnvasado; //Define el tipo de envasado.
    private boolean importado;//Define si es importado o no.
    private LocalDate fechaVencimiento;//Define la fecha de vencimiento.
    private int calorias;//Define las calorias.


    public ProductoEnvasado(String id,String titulo, String descripcion, int stock, double precioUnidad, double costoUnidad, TipoEnvasado tipoEnvasado, boolean importado) {
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
        this.importado = importado;
        this.tipoEnvasado = tipoEnvasado;


    }

    public TipoEnvasado getTipoEnvasado(){
        return this.tipoEnvasado;
    }

    public boolean getImportado(){
        return this.importado;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    @Override
    public int getCalorias() {
        return this.calorias;
    }

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
}
