package com.proyecto.tienda;

import java.time.LocalDate;

public class ProductoBebida extends Producto implements ProductoComestible {
    private static final String ID_FORMAT = "AC\\d{3}";

    private boolean alcoholica;
    private double porcentajeAlcohol;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoBebida(String id,String titulo, String descripcion, int stock, double precioUnidad, double costoUnidad, boolean alcoholica,double porcentajeAlcohol, boolean importado){
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
        this.alcoholica = alcoholica;
        this.porcentajeAlcohol = porcentajeAlcohol;
        this.importado = importado;

    }

    /**
     * Cuando la bebida no es alcoholica no hay que ingresar "alcoholica" ni "porcentajeAlcohol"
     */
    public ProductoBebida(String id,String titulo, String descripcion, int stock, double precioUnidad, double costoUnidad, boolean importado) {
        this(id,titulo, descripcion, stock, precioUnidad, costoUnidad, false, 0.0, importado);
    }


    public boolean getAlcoholica(){
        return this.alcoholica;
    }
    public double getPorcentajeAlcohol(){
        return this.porcentajeAlcohol;
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
