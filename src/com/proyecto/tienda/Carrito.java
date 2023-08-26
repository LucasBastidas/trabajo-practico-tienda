package com.proyecto.tienda;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Producto,Integer> productosEnElcarrito;

    public Carrito(){
        this.productosEnElcarrito = new HashMap<>();
    }

    public Map<Producto, Integer> getProductosEnElcarrito() {
        return productosEnElcarrito;
    }

    public void agregarProductoAlCarrito(Producto producto,int cantidad){
        Map<Producto,Integer> productosEnElcarrito = this.getProductosEnElcarrito();
        if (!(productosEnElcarrito.size() >= 3)){
            if (cantidad > 10){
                throw new IllegalArgumentException("No se puede agregar mas de 10 unidades por producto al carrito");
            }
            productosEnElcarrito.put(producto,cantidad);

        }else{
            throw new IllegalArgumentException("No se permiten mas de 3 productos en el carrito");
        }
    }

}
