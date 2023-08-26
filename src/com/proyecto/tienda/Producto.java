package com.proyecto.tienda;

public abstract class Producto implements ProductoConDescuento {
    private String id;
    private String titulo;
    private String descripcion;
    private int stock;
    private double precioPorUnidad; //precio al que se vende
    private double costoPorUnidad; //pecio al que se compra el producto
    private boolean disponible; //Disponibilidad para la venta

    private double porcentajeDescuento;
    private double precioVentaConDescuento;





    protected String getId(){
        return this.id;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public int getStock(){
        return this.stock;
    }
    public double getPrecioPorUnidad(){
        return this.precioPorUnidad;
    }
    public double getCostoPorUnidad(){
        return this.costoPorUnidad;
    }
    public boolean getDisponible(){
        return this.disponible;
    }
    protected void setId(String id){
        this.id = id;
    }
    protected void setTitulo(String titulo){
        this.titulo = titulo;
    }
    protected void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    protected void setStock(int stock){
        this.stock = stock;
    }
    protected void setPrecioPorUnidad(double precio){
        this.precioPorUnidad = precio;
    }
    protected void setCostoPorUnidad(double costo){
        this.costoPorUnidad = costo;
    }
    public void setDisponible(boolean disponible){this.disponible = disponible;}


    // Implementación de métodos de la interfaz ProductoConDescuento
    @Override
    public void setPorcentajeDescuento(double porcentaje) {

        //ESTABLECIDO EL MAXIMO DE DESCUENTO PARA CADA TIPO DE PRODUCTO
        if (this instanceof ProductoDeLimpieza){
            if (porcentaje > 25){
                System.out.println("El porcentaje de descuento este producto no puede ser mayor a 25");
            }else {
                this.porcentajeDescuento = porcentaje;
            }
        }
        if (this instanceof ProductoEnvasado){
            if (porcentaje > 20){
                System.out.println("El porcentaje de descuento de este producto no puede ser mayor a 20");
            }else {
                this.porcentajeDescuento = porcentaje;
            }
        }
        if (this instanceof ProductoBebida){
            if (porcentaje > 15){
                System.out.println("El porcentaje de descuento de este producto no puede ser mayor a 15");
            }else{
                this.porcentajeDescuento = porcentaje;
            }

        }
    }

    @Override
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public double getPrecioVentaConDescuento() {
        // Calcula y devuelve el precio de venta con descuento
        double precioSinDescuento = this.getPrecioPorUnidad();
        double descuento = precioSinDescuento * (porcentajeDescuento / 100);
        return precioSinDescuento - descuento;
    }
}
