package com.proyecto.tienda;

import java.util.*;


public class Tienda {
    private String nombre;
    private  int productosEnStock;
    private int maxProductosEnStock;
    private double saldoCaja;
    private Map<TipoProducto, List<Producto>> productosPorTipo;

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja){
        this.nombre = nombre;
        this.productosEnStock = 0;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.productosPorTipo = new HashMap<>();

        for (TipoProducto tipo: TipoProducto.values()) {
            productosPorTipo.put(tipo, new ArrayList<>());
        }
    }

    private TipoProducto obtenerTipoProducto(Producto producto){
            if(producto instanceof ProductoBebida){
                return TipoProducto.BEBIDA;
            }
            if(producto instanceof ProductoEnvasado ){
                return TipoProducto.ENVASADO;
            }
            if(producto instanceof ProductoDeLimpieza){
                return TipoProducto.LIMPIEZA;
            }else{
                throw new IllegalArgumentException("Tipo de producto desconocido");
            }
    }



    public void agregarProducto(Producto producto){
        TipoProducto tipoProducto = obtenerTipoProducto(producto);
        int stockProducto = producto.getStock();
        double precioProducto = producto.getPrecioPorUnidad();
        double costoProducto = producto.getCostoPorUnidad();
        double costoTotal = costoProducto * stockProducto;
        if(this.productosEnStock + stockProducto > this.maxProductosEnStock){
            throw new IllegalArgumentException("Capacidad de stock excedida");
        }
        if(this.saldoCaja < costoProducto){
            throw new IllegalArgumentException("El saldo en caja no es suficiente");
        }
        double porcentajeGanancia  = this.calcularPorcentajeGanancia(producto);
        boolean porcentajeGananciaValido = this.getPorcentajeDeGananciaValido(porcentajeGanancia,producto);

        //VALIDA QUE EL PORCENTAJE SEA EL ADECUADO PARA CADA TIPO DE PRODUCTO
        if (!porcentajeGananciaValido){
            throw new IllegalArgumentException("El porcentaje de ganancia no es válido");
        }

        //SI T0DO ESTA BIEN SE RESTA EL COSTO DEL O LOS PRODUCTOS Y SE AGREGA EL STOCK
        this.setSaldoCaja(this.saldoCaja - costoTotal);
        this.setProductosEnStock(this.productosEnStock + stockProducto);

        //FINALMENTE SE AGREGA EL PRODUCTO EN EL TIPO CORRESPONDIENTE
        productosPorTipo.get(tipoProducto).add(producto);
    }

    //CALCULA EL PORCENTAJE DE GANANCIA DE UN PRODUCTO
    private double calcularPorcentajeGanancia(Producto producto){
        double precioVentaProducto = producto.getPrecioPorUnidad();
        double costoProducto = producto.getCostoPorUnidad();
        double porcentajeGanancia = ((precioVentaProducto - costoProducto) / costoProducto)* 100;
        return porcentajeGanancia;
    }

    //CHEQUEA QUE EL PORCENTAJE DE GANANCIA SE VALIDO
    private boolean getPorcentajeDeGananciaValido(double porcentaje, Producto producto){
        TipoProducto tipoProducto = getTipoProductoById(producto.getId());
        if (tipoProducto == TipoProducto.ENVASADO || tipoProducto == TipoProducto.BEBIDA){
            return porcentaje <= 20;
        }
        if (tipoProducto == TipoProducto.LIMPIEZA){
            if (((ProductoDeLimpieza)producto).getTipoAplicacion() == TipoAplicacion.COCINA || ((ProductoDeLimpieza)producto).getTipoAplicacion() == TipoAplicacion.PISOS){
                return 10 <= porcentaje &&  porcentaje <= 25;
            }
            if (((ProductoDeLimpieza)producto).getTipoAplicacion() == TipoAplicacion.ROPA || ((ProductoDeLimpieza)producto).getTipoAplicacion() == TipoAplicacion.MULTIUSO){
                return true;
            }
        }
        return false;
    }



    TipoProducto getTipoProductoById(String id){

        if (id.matches("AC\\d{3}")){
            return TipoProducto.BEBIDA;
        }
        if(id.matches("AZ\\d{3}")){
            return TipoProducto.LIMPIEZA;
        }
        if (id.matches("AB\\d{3}")){
            return TipoProducto.ENVASADO;
        }
        return null;

    }

    //SE INGRESA EL ID DEL PRODUCTO Y CHEQUEA SI EXISTE EN LA TIENDA
    public Producto getProductoPorId(String id){
        TipoProducto tipoProducto = getTipoProductoById(id);
        if (tipoProducto == null){
            throw new IllegalArgumentException("El id no corresponde a ningún producto");
        }
        //Se busca el producto en la categoria correspondiente.
        List<Producto> productos = getProductosPorTipo().get(tipoProducto);
            for (Producto producto: productos) {
                if(producto.getId().equals(id)){
                    return producto;
                }else {
                    return null;
                }
            }

            return null;
    }


    //TODO SEGUIR CON LA VENTA DE PRODUCTOS Y SEPARAR LOGICA
    public void venderProductos(Carrito carrito){

        double totalVenta = 0.0;
        StringBuilder ticket = new StringBuilder();
        Map<Producto, Integer> productosEnElCarrito = carrito.getProductosEnElcarrito();
        double saldoAntesDeLaCompra = this.getSaldoCaja();

        //RECORRE EL MAP DE PRODUCTOS DENTRO DEL CARRITO
        for (Map.Entry<Producto, Integer> productoEntry : productosEnElCarrito.entrySet()) {

            Producto producto = productoEntry.getKey();
            double precioProducto = producto.getPrecioPorUnidad();
            double costoProducto = producto.getCostoPorUnidad();
            double descuentoProducto = 0;
            String idProducto = producto.getId();
            String tituloProducto = producto.getTitulo();
            int stockProducto = producto.getStock();
            int cantidad = productoEntry.getValue();
            TipoProducto tipoProducto = getTipoProductoById(idProducto);

            //Se asegura que el producto exista dentro de la tienda
            if (getProductoPorId(idProducto) == null){
                throw new IllegalArgumentException("EL PRODUCTO NO EXISTE EN LA TIENDA");
            }

            //SI EL PRODUCTO NO ESTA DISPONIBLE SE NOTIFICARÁ
            if (!producto.getDisponible()) {
                throw new IllegalArgumentException("El producto: "+tituloProducto+" no esta disponible para la venta");
            }

            //Si el stock del producto no llega a la cantidad solicitada, sale este msj
            if (stockProducto< cantidad){
                throw new IllegalArgumentException("El stock disponible de: "+ tituloProducto+ " es menor a la cantidad solicitada");
            }
            //CHEQUEA SI LOS PRODUCTOS SON BEBIDAS O ENVASADOS Y LUEGO SI SON IMPORTADOS PARA APLICAR EL IMPUESTO.
            if (tipoProducto == TipoProducto.BEBIDA){
                if (((ProductoBebida) producto).getImportado()){
                    producto.setPrecioPorUnidad(producto.getPrecioPorUnidad()*1.10);}
            }
            if (tipoProducto == TipoProducto.ENVASADO){
                if (((ProductoEnvasado) producto).getImportado()){
                    producto.setPrecioPorUnidad(producto.getPrecioPorUnidad()*1.10);
                }
            }
            //CHEQUEA SI HAY DESCUENTOS
            if(producto.getPorcentajeDescuento() > 0){
                double precioConDescuento = producto.getPrecioVentaConDescuento();
                //SI HAY DESCUENTO: CHEQUEA QUE EL PRECIO CON DESCUENTO NO SEA MENOR AL COSTO DEL PRODUCTO
                if (precioConDescuento < costoProducto){
                    System.out.println("El descuento para el producto "+ idProducto + " " + tituloProducto + " no se pudo aplicar.");
                }else{
                    System.out.println("El descuento a " +tituloProducto +" fue aplicado con exito: Precio original: "+precioProducto+ " | " + " Precio con descuento: "+precioConDescuento);
                    descuentoProducto = producto.getPorcentajeDescuento();
                    precioProducto = precioConDescuento;
                }
            }
            //SE ACTUALIZA EL TICKET
            ticket.append(idProducto).append(" ").append(tituloProducto).append(" x").append(cantidad).append("  $").append(precioProducto).append(" DESCUENTO: ").append(descuentoProducto).append(" ").append("%").append("\n");

            // SE ACTUALIZA EL STOCK DEL PRODUCTO
            producto.setStock(stockProducto - cantidad);
            //SE ACTUALIZA EL PRECIO TOTAL
            totalVenta = totalVenta + (producto.getPrecioPorUnidad() * cantidad);
        }
        setSaldoCaja(this.saldoCaja + totalVenta);
        System.out.println("\n"+"---RESUMEN---"+"\n"+"\n"+ ticket + "\n" + "TOTAL VENTA: "+ "$ " +totalVenta + "\n" + "\n" + "Saldo en la caja antes de la compra: " + "$ "+ saldoAntesDeLaCompra + "\n" +
                "Saldo actual de la caja: $" + this.getSaldoCaja());
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxProductosEnStock() {
        return maxProductosEnStock;
    }

    public int getProductosEnStock() {
        return productosEnStock;
    }

    public void setProductosEnStock(int productosEnStock) {
        this.productosEnStock = productosEnStock;
    }

    public void setMaxProductosEnStock(int maxProductosEnStock) {
        this.maxProductosEnStock = maxProductosEnStock;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public Map<TipoProducto, List<Producto>> getProductosPorTipo() {
        return productosPorTipo;
    }
    
    public void obtenerComestiblesConMenorDescuento(double porcentaje_descuento){
            List<Producto> bebidasMenorDescuento = productosPorTipo.get(TipoProducto.BEBIDA).stream()
                    .filter(producto -> producto instanceof ProductoBebida && !((ProductoBebida)producto).getImportado())
                    .filter(producto -> producto.getPorcentajeDescuento() < porcentaje_descuento)
                    .toList();

            List<Producto> envasadosMenorDescuento = productosPorTipo.get(TipoProducto.ENVASADO).stream()
                    .filter(producto -> producto instanceof ProductoEnvasado && !((ProductoEnvasado)producto).getImportado())
                    .filter(producto -> producto.getPorcentajeDescuento() < porcentaje_descuento)
                    .toList();

            List<Producto> comestiblesNoImportados = new ArrayList<>();

            //Junto las listas de bebidas y envasados no importados
            comestiblesNoImportados.addAll(bebidasMenorDescuento);
            comestiblesNoImportados.addAll(envasadosMenorDescuento);

            List<String> comestiblesMenorDescuento = comestiblesNoImportados.stream()
                            .filter(producto -> producto.getPorcentajeDescuento() < porcentaje_descuento)
                                    .sorted(Comparator.comparing(Producto::getPrecioPorUnidad))
                                            .map(producto -> producto.getTitulo().toUpperCase())
                                                    .toList();

            if (comestiblesMenorDescuento.isEmpty()){
                System.out.println("No se encontraron productos");
            }else {
                System.out.println("Comestibles no importados con descuento menor al %"+porcentaje_descuento+": "+comestiblesMenorDescuento);
            }
    }

    public void listarProductosConUtilidadesInferiores(double porcentaje_utilidad){
    List<Producto> todosLosProductos = getProductosPorTipo().values().stream()
            .flatMap(List::stream)
            .filter(producto -> this.calcularPorcentajeGanancia(producto) < porcentaje_utilidad)
            .toList();

            if (todosLosProductos.isEmpty()) {
                System.out.println("No se encontraron productos con ganancias inferiores al: %"+porcentaje_utilidad);
            }else{
                System.out.println("Productos con ganancias inferiores al: %"+porcentaje_utilidad + "\n");
                todosLosProductos.forEach(producto -> {
                    String titulo = "Titulo del producto: " + producto.getTitulo();
                    String stock = "Stock disponible: " + producto.getStock();
                    System.out.println(producto.getId() + " - " + titulo + " - " + stock);
                });
            }




    }

}
