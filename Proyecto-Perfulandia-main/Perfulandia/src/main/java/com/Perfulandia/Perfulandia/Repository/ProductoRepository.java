package com.Perfulandia.Perfulandia.Repository;

import com.Perfulandia.Perfulandia.Model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {
    // Arreglo que guarde todos los productos
    private List<Producto> productos = new ArrayList<>();

    // Metodo que retorne todos los prodctos


    public List<Producto> obtenerProductos() {
        return productos;
    }

    //BUscar producto por su id


    public List<Producto> buscarPorId(long id  ) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return productos;
            }
        };
        return null;
    }

    //Buscar Producto por su nombre
    public List<Producto> buscarPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return productos;
            }
        }
        return null;
    }

    //BUscar Producto por su isbn
    public List<Producto> buscarPorIsbn(int isbn) {
        for (Producto producto : productos) {
            if (producto.getIsbn() == isbn) {
                return productos;
            }
        }
        return null;
    }



}
