package com.Perfulandia.Perfulandia.Controller;

import com.Perfulandia.Perfulandia.Model.Producto;
import com.Perfulandia.Perfulandia.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // Obtener producto por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable long id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener productos por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> obtenerPorNombre(@PathVariable String nombre) {
        List<Producto> productos = productoService.obtenerPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    // Obtener producto por isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Producto> obtenerPorIsbn(@PathVariable int isbn) {
        Producto producto = productoService.obtenerPorIsbn(isbn);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo producto sin necesidad de enviar id
    @PostMapping("/")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        producto.setId(0); // Asegurarse que id sea 0 o no esté seteado para que se genere automáticamente
        Producto creado = productoService.guardarProducto(producto);
        return ResponseEntity.ok(creado);
    }


    // Actualizar producto por id
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable long id, @RequestBody Producto producto) {
        // Validar que el id en la URL y en el cuerpo coincidan
        if (producto.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Producto actualizado = productoService.actualizarProducto(producto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar producto por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable long id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto != null) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

