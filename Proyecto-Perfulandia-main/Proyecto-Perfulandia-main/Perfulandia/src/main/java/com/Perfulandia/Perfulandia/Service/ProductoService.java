package com.Perfulandia.Perfulandia.Service;

import com.Perfulandia.Perfulandia.Model.Producto;
import com.Perfulandia.Perfulandia.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.obtenerProductos();
    }

    public Producto obtenerPorId(long id) {
        return productoRepository.buscarPorId(id);
    }

    public List<Producto> obtenerPorNombre(String nombre) {
        return productoRepository.buscarPorNombre(nombre);
    }

    public Producto obtenerPorIsbn(int isbn) {
        return productoRepository.buscarPorIsbn(isbn);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.guardar(producto);
    }

    public Producto actualizarProducto(Producto producto) {
        return productoRepository.actualizar(producto);
    }

    public void eliminarProducto(long id) {
        productoRepository.eliminar(id);
    }
}
