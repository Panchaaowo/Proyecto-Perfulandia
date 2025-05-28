package com.Perfulandia.Perfulandia.Repository;

import com.Perfulandia.Perfulandia.Model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {
    // Arreglo que guarde todos los productos
    private List<Producto> listaProductos = new ArrayList<>();

    // Campo para generar IDs automáticos
    private long nextId = 1;

    // Método que retorne todos los productos
    public List<Producto> obtenerProductos() {
        return listaProductos;
    }

    // Buscar producto por su id
    public Producto buscarPorId(long id) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // Buscar Producto por su nombre
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto producto : listaProductos) {
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(producto);
            }
        }
        return encontrados;
    }

    // Buscar Producto por su isbn
    public Producto buscarPorIsbn(int isbn) {
        for (Producto producto : listaProductos) {
            if (producto.getIsbn() == isbn) {
                return producto;
            }
        }
        return null;
    }

    // Guardar producto y asignar ID automáticamente
    public Producto guardar(Producto pro) {
        pro.setId(nextId++);
        listaProductos.add(pro);
        return pro;
    }

    // Actualizar producto
    public Producto actualizar(Producto pro) {
        int idPosicion = -1;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == pro.getId()) {
                idPosicion = i;
                break;
            }
        }
        if (idPosicion != -1) {
            listaProductos.set(idPosicion, pro);
            return pro;
        }
        return null;
    }

    // Eliminar producto por id
    public void eliminar(long id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            listaProductos.remove(producto);
        }
    }
}

