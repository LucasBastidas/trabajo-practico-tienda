import com.proyecto.tienda.*;

import java.time.LocalDate;

import java.util.Scanner;


public class ApplicacionTienda {

    private static ProductoBebida cocaCola;
    private static ProductoBebida cerveza;
    private static ProductoBebida wiski;
    private static ProductoBebida agua;
    private static ProductoEnvasado arroz;
    private  static ProductoEnvasado cafe;
    private static ProductoEnvasado fideos;
    private static ProductoEnvasado yerba;
    private static ProductoDeLimpieza lavandina;
    private static ProductoDeLimpieza javonEnPolvo;
    private static ProductoDeLimpieza desinfectanteDePiso;
    private static ProductoDeLimpieza antigrasa;



    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


        //BEBIDAS

        cocaCola = new ProductoBebida("AC234","Coca-Cola","Coca-cola de 2L",10,500,450,false,
                0.0,false);
        cocaCola.setCalorias(50);
        cocaCola.setFechaVencimiento(LocalDate.of(2023,12,31));

         cerveza = new ProductoBebida("AC312","Cerveza","Cerveza IPA", 5,300,260,true,
                2.5,false);
        cerveza.setCalorias(30);
        cerveza.setFechaVencimiento(LocalDate.of(2023,11,29));

         wiski = new ProductoBebida("AC323","Wiski","Wiski importado",8,800,670,true,
                10.5,true);
        wiski.setCalorias(30);
        wiski.setFechaVencimiento(LocalDate.of(2024,11,21));

         agua = new ProductoBebida("AC333","Agua mineral","Agua mineral de río",15,143.50,120,false,
                0.0,false);
        agua.setCalorias(0);
        agua.setFechaVencimiento(LocalDate.of(2024,1,31));

        //ENVASADOS

         arroz = new ProductoEnvasado("AB231","Arroz integral","Arroz gallo light",20,360,300,
                TipoEnvasado.PLASTICO,false);
        arroz.setCalorias(300);
        arroz.setFechaVencimiento(LocalDate.of(2024,11,25));

         cafe = new ProductoEnvasado("AB341","Café","Café de Brasil premium",15,600,550,
                TipoEnvasado.VIDRIO,true);
        cafe.setCalorias(10);
        cafe.setFechaVencimiento(LocalDate.of(2023,11,25));

         fideos = new ProductoEnvasado("AB433","Fideos","Fideos moñito",8,300,280.50,
                TipoEnvasado.PLASTICO,false);
        cafe.setCalorias(280);
        cafe.setFechaVencimiento(LocalDate.of(2023,12,25));

        yerba = new ProductoEnvasado("AB344","Yerba","Yerba sin palo",5,700,600,
                TipoEnvasado.PLASTICO, false);
        yerba.setCalorias(40);
        yerba.setFechaVencimiento(LocalDate.of(2023,11,25));

        //PRODUCTOS DE LIMPIEZA

         lavandina = new ProductoDeLimpieza("AZ223","lavandina","Lavandina premium",10, 300.50, 275,
                TipoAplicacion.MULTIUSO);

         javonEnPolvo = new ProductoDeLimpieza("AZ456","Jabón en polvo","Jabón en polvo barato",5,140,120,
                TipoAplicacion.ROPA);

         desinfectanteDePiso = new ProductoDeLimpieza("AZ331","Desinfectante de piso","Desinfectante de piso fragancia lavanda",8,
                180,150,TipoAplicacion.PISOS);

         antigrasa = new ProductoDeLimpieza("AZ512","Antigrasa Cocina","Antigrasa extra fuerte",13,100,90,
                TipoAplicacion.COCINA);

        System.out.println("LOS SIGUIENTES SON TODOS LOS CASOS POSIBLES.. ");



        System.out.println("1- Tienda sin espacio suficiente para agregar todos los productos");
        System.out.println("2- Tienda sin fondos suficientes para agregar todos los productos");
        System.out.println("3- Agregando producto excediendo el porcentaje de ganancia permitido");
        System.out.println("4- Producto no disponible");
        System.out.println("5- Se solicita comprar una cantidad mas alta que el stock disponible.");
        System.out.println("6- Se solicita comprar mas de 10 unidades");
        System.out.println("7- Se intenta comprar un producto que no esta cargado en la Tienda.");
        System.out.println("8- Se aplica un descuento  en bebidas");
        System.out.println("9- Se aplica un descuento  en los envasados");
        System.out.println("10- Se aplica un descuento en los productos de limpieza");
        System.out.println("11- Caso de venta 1");
        System.out.println("12- Caso de venta 2");
        System.out.println("13- Caso de venta 3");
        System.out.println("14- Método obtenerComestiblesConMenorDescuento(porcentaje_descuento)");
        System.out.println("15- Método listarProductosConUtilidadesInferiores(porcentaje)");

        System.out.println("Selecciona la opcion (n°) que desees:");
        int opcion = scan.nextInt();


        switch (opcion) {
            case 1 -> casoTiendaSinEspacioSuficiente();
            case 2 -> casoTiendaSinSaldoSuficiente();
            case 3 -> casoAgregandoProductoConExcesoDePorcentajeDeGanancia();
            case 4 -> casoProductoNoDisponible();
            case 5 -> casoProductoSinStockSuficiente();
            case 6 -> casoMasDeDiezUnidades();
            case 7 -> casoProductoNoExisteEnLaTienda();
            case 8 -> casoExcesoDescuentoBebida();
            case 9 -> casoExcesoDescuentoEnvasado();
            case 10 -> casoExcesoDescuentoLimpieza();
            case 11 -> casoVenta1();
            case 12 -> casoVenta2();
            case 13 -> casoVenta3();
            case 14 -> obtenerComestiblesConMenorDescuento();
            case 15 -> listarProductosConUtilidadesInferiores();
            default -> System.out.println("Opción inválida");
        }



        scan.close(); //Cerrar el scaner al final para liberar memoria



    }
    static void casoTiendaSinEspacioSuficiente(){
        Tienda tienda = new Tienda("Tienda",117,1000000);

        int stock = cocaCola.getStock();
        stock = stock + cerveza.getStock() + wiski.getStock() + agua.getStock() + arroz.getStock() +
                cafe.getStock()+fideos.getStock()+yerba.getStock()+lavandina.getStock()+javonEnPolvo.getStock()+
                desinfectanteDePiso.getStock()+antigrasa.getStock();
        System.out.println("En este caso la cantidad de productos es mayor al maximo permitido por la tienda, por lo tanto nos avisará con un throw.");
        System.out.println("----");
        System.out.println("CANTIDAD TOTAL DE PRODUCTOS: "+stock);
        System.out.println("CANTIDAD MAXIMA DE PRODUCTOS EN STOCK DE LA TIENDA: "+tienda.getMaxProductosEnStock());

        tienda.agregarProducto(cocaCola);
        tienda.agregarProducto(cerveza);
        tienda.agregarProducto(wiski);
        tienda.agregarProducto(agua);
        tienda.agregarProducto(arroz);
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(fideos);
        tienda.agregarProducto(yerba);
        tienda.agregarProducto(lavandina);
        tienda.agregarProducto(javonEnPolvo);
        tienda.agregarProducto(desinfectanteDePiso);
        tienda.agregarProducto(antigrasa);
    }

    static void casoTiendaSinSaldoSuficiente(){
        Tienda tienda = new Tienda("Tienda",200,500);

        double costo = cocaCola.getCostoPorUnidad();
        costo = costo + cerveza.getCostoPorUnidad() + wiski.getCostoPorUnidad() + agua.getCostoPorUnidad() + arroz.getCostoPorUnidad() +
                cafe.getCostoPorUnidad()+fideos.getCostoPorUnidad()+yerba.getCostoPorUnidad()+lavandina.getCostoPorUnidad()+javonEnPolvo.getCostoPorUnidad()+
                desinfectanteDePiso.getCostoPorUnidad()+antigrasa.getCostoPorUnidad();
        System.out.println("En este caso la suma total de todos los productos es mas alta que el dinero en la caja de la tienda, por lo tanto nos avisará con un throw.");
        System.out.println("----");
        System.out.println("COSTO TOTAL DE PRODUCTOS: $"+costo);
        System.out.println("SALDO EN LA CAJA DE LA TIENDA: "+tienda.getSaldoCaja());

        tienda.agregarProducto(cocaCola);
        tienda.agregarProducto(cerveza);
        tienda.agregarProducto(wiski);
        tienda.agregarProducto(agua);
        tienda.agregarProducto(arroz);
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(fideos);
        tienda.agregarProducto(yerba);
        tienda.agregarProducto(lavandina);
        tienda.agregarProducto(javonEnPolvo);
        tienda.agregarProducto(desinfectanteDePiso);
        tienda.agregarProducto(antigrasa);
    }

    static void casoAgregandoProductoConExcesoDePorcentajeDeGanancia(){
           Tienda tienda = new Tienda("Tienda",200,1000);
           System.out.println("En este caso se intenta agregar un producto con exceso de porcentaje de ganancia, por lo tanto nos avisará con un throw.");
           System.out.println("----");

           ProductoEnvasado galletitas = new ProductoEnvasado("AB655","Galletitas","Galletitas surtidas",10,50,10,
                   TipoEnvasado.PLASTICO,false);
           tienda.agregarProducto(galletitas);
       }

    static void casoProductoNoDisponible(){
        Tienda tienda = new Tienda("Tienda",200,1000);
        System.out.println("En este caso se intenta comprar un producto no disponible y nos avisará con un throw.");
        System.out.println("----");
        tienda.agregarProducto(agua);
        agua.setDisponible(false);
        Carrito carrito = new Carrito();
        carrito.agregarProductoAlCarrito(agua,1);
        tienda.venderProductos(carrito);
    }

    static void casoProductoSinStockSuficiente(){
        Tienda tienda = new Tienda("Tienda",200,1000);
        System.out.println("En este caso se intenta comprar un producto con stock no suficiente al solicitado.");
        System.out.println("----");

        tienda.agregarProducto(cerveza);

        System.out.println("STOCK DISPONIBLE DEL PRODUCTO: "+ cerveza.getStock());
        System.out.println("CANTIDAD SOLICITADA: "+ 7);

        Carrito carrito = new Carrito();
        carrito.agregarProductoAlCarrito(cerveza, 7);
        tienda.venderProductos(carrito);
    }

    static void casoMasDeDiezUnidades(){
        Tienda tienda = new Tienda("Tienda",200,1000);
        System.out.println("En este caso se intenta comprar mas de 10 unidades de un producto.");
        System.out.println("Directamente no nos dejará agregar esa cantidad al carrito.");
        System.out.println("----");

        tienda.agregarProducto(agua);
        Carrito carrito = new Carrito();
        carrito.agregarProductoAlCarrito(agua,12);
    }

    static void casoProductoNoExisteEnLaTienda(){
        Tienda tienda = new Tienda("Tienda",200,1000);
        System.out.println("En este caso se intenta comprar un producto que no esta cargado en la tienda y nos avisa con un throw");

        Carrito carrito = new Carrito();
        carrito.agregarProductoAlCarrito(agua,3);
        tienda.venderProductos(carrito);
    }

    static void casoExcesoDescuentoBebida(){
        Scanner scan = new Scanner(System.in);
        System.out.println("En este caso se intenta aplicar un descuento a un producto tipo: Bebida (El maximo es 15)");
        System.out.println("Ingresa un porcentaje de descuento: ");
        double porcentaje = scan.nextDouble();
        agua.setPorcentajeDescuento(porcentaje);
        if (porcentaje <= 15){
            System.out.println("El porcentaje se aplicó correctamente");
        }
    }
    static void casoExcesoDescuentoEnvasado(){
        Scanner scan = new Scanner(System.in);
        System.out.println("En este caso se intenta aplicar un descuento a un producto tipo: Envasado (El maximo es 20)");
        System.out.println("Ingresa un porcentaje de descuento: ");
        double porcentaje = scan.nextDouble();
        yerba.setPorcentajeDescuento(porcentaje);
        if (porcentaje <= 20){
            System.out.println("El porcentaje se aplicó correctamente");
        }
    }

    static void casoExcesoDescuentoLimpieza(){
        Scanner scan = new Scanner(System.in);
        System.out.println("En este caso se intenta aplicar un descuento a un producto tipo: Limpieza (El maximo es 25)");
        System.out.println("Ingresa un porcentaje de descuento: ");
        double porcentaje = scan.nextDouble();
        lavandina.setPorcentajeDescuento(porcentaje);
        if (porcentaje <= 25){
            System.out.println("El porcentaje se aplicó correctamente");
        }
    }
    static void casoVenta1(){
        System.out.println("En este caso se venderan 3 productos distintos:");
        System.out.println("AGUA x 3 con %15 de descuento");
        System.out.println("YERBA x 1 con %0 de descuento");
        System.out.println("LAVANDINA x 3 con %0 de descuento");
        System.out.println("--------");
        Tienda tienda = new Tienda("Tienda",200,10000);

        agua.setPorcentajeDescuento(15);

        tienda.agregarProducto(agua);
        tienda.agregarProducto(yerba);
        tienda.agregarProducto(lavandina);

        Carrito carrito = new Carrito();

        carrito.agregarProductoAlCarrito(agua,3);
        carrito.agregarProductoAlCarrito(yerba,1);
        carrito.agregarProductoAlCarrito(lavandina,3);

        tienda.venderProductos(carrito);
    }

    static void casoVenta2(){
        System.out.println("En este caso se venderan 3 productos distintos:");
        System.out.println("COCACOLA x 6 con %0 de descuento");
        System.out.println("CAFE x 2 con %15 de descuento (El café al ser importado tiene un 10% de impuesto)");
        System.out.println("ANTIGRASA x 3 con %25 de descuento (En este caso no se aplica el descuento porque el precio de venta queda menor que el costo del producto");
        System.out.println("--------");
        Tienda tienda = new Tienda("Tienda",200,15000);

        cafe.setPorcentajeDescuento(15);
        antigrasa.setPorcentajeDescuento(25);

        tienda.agregarProducto(cocaCola);
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(antigrasa);

        Carrito carrito = new Carrito();

        carrito.agregarProductoAlCarrito(cocaCola,6);
        carrito.agregarProductoAlCarrito(cafe,2);
        carrito.agregarProductoAlCarrito(antigrasa,3);

        tienda.venderProductos(carrito);
    }

    static void casoVenta3(){
        System.out.println("En este caso se venderan 2 productos distintos:");
        System.out.println("Cerveza sin descuento y Desinfectante sin descuento");

        Tienda tienda = new Tienda("Tienda",200,10000);

        tienda.agregarProducto(cerveza);
        tienda.agregarProducto(desinfectanteDePiso);

        Carrito carrito = new Carrito();

        carrito.agregarProductoAlCarrito(cerveza,4);
        carrito.agregarProductoAlCarrito(desinfectanteDePiso,3);

        tienda.venderProductos(carrito);
    }

    static void obtenerComestiblesConMenorDescuento(){
        Tienda tienda = new Tienda("Tienda",200,50000);
        tienda.agregarProducto(cocaCola);
        tienda.agregarProducto(cerveza);
        tienda.agregarProducto(wiski);
        tienda.agregarProducto(agua);
        tienda.agregarProducto(arroz);
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(fideos);
        tienda.agregarProducto(yerba);
        tienda.agregarProducto(lavandina);
        tienda.agregarProducto(javonEnPolvo);
        tienda.agregarProducto(desinfectanteDePiso);
        tienda.agregarProducto(antigrasa);

        arroz.setPorcentajeDescuento(20);
        yerba.setPorcentajeDescuento(12);
        agua.setPorcentajeDescuento(3);
        cocaCola.setPorcentajeDescuento(12);

        System.out.println("ESTO DEVOLVERÁ TODOS LOS PRODUCTOS COMESTIBLES NO IMPORTADOS CON DESCUENTO MENOR AL PORCENTAJE" +
                " QUE INGRESES A CONTINUACIÓN INCLUYENDO 0% ORDENADOS DE MENOR A MAYOR SEGUN EL PRECIO DE VENTA");
        System.out.println("-------");

        Scanner scan = new Scanner(System.in);

        System.out.println("Ingresa un porcentaje de descuento:");
        double porcentaje = scan.nextDouble();

        tienda.obtenerComestiblesConMenorDescuento(porcentaje);
    }

    static void listarProductosConUtilidadesInferiores(){
        Tienda tienda = new Tienda("Tienda",200,50000);
        tienda.agregarProducto(cocaCola);
        tienda.agregarProducto(cerveza);
        tienda.agregarProducto(wiski);
        tienda.agregarProducto(agua);
        tienda.agregarProducto(arroz);
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(fideos);
        tienda.agregarProducto(yerba);
        tienda.agregarProducto(lavandina);
        tienda.agregarProducto(javonEnPolvo);
        tienda.agregarProducto(desinfectanteDePiso);
        tienda.agregarProducto(antigrasa);

        System.out.println("ESTO DEVOLVERÁ TODOS LOS PRODUCTOS DE CUALQUIER TIPO QUE ESTEN GENERANDO GANANCIAS INFERIORES AL " +
                "PORCENTAJE QUE INGRESES A CONTINUACIÓN  ");
        System.out.println("-------");

        Scanner scan = new Scanner(System.in);

        System.out.println("Ingresa un porcentaje: ");
        double porcentaje = scan.nextDouble();

        tienda.listarProductosConUtilidadesInferiores(porcentaje);


    }



}