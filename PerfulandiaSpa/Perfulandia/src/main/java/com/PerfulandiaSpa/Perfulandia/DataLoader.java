package com.PerfulandiaSpa.Perfulandia;
import com.PerfulandiaSpa.Perfulandia.Model.*;
import com.PerfulandiaSpa.Perfulandia.Repository.*;
import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar productos (de 1 a 20)
        int cantidadProductos = random.nextInt(20) + 1;
        for (int i = 0; i < cantidadProductos; i++) {
            Producto producto = new Producto(1L, "Chanel", "Aroma floral", 19990.0);
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price(10.0, 100.0)));
            productoRepository.save(producto);
        }

        // Generar usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().fullName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setContraseña(faker.internet().password());
            usuarioRepository.save(usuario);
        }

        List<Producto> productos = productoRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Generar pedidos
        for (int i = 0; i < 30; i++) {
            Pedido pedido = new Pedido();
            pedido.setUsuarioId(usuarios.get(random.nextInt(usuarios.size())).getId());
            pedido.setEstado(faker.options().option("Pendiente", "Procesando", "Enviado", "Entregado", "Cancelado"));
            pedido.setTotal(Double.parseDouble(faker.commerce().price(50.0, 1000.0)));
            pedidoRepository.save(pedido);
        }

        List<Pedido> pedidos = pedidoRepository.findAll();

        // Generar envíos
        for (int i = 0; i < 20; i++) {
            Envio envio = new Envio();
            envio.setDireccion(faker.address().fullAddress());
            envio.setNombre(faker.name().fullName());
            envio.setEstado(faker.options().option("Preparando", "En tránsito", "Entregado", "Devuelto"));
            envioRepository.save(envio);
        }
    }
}