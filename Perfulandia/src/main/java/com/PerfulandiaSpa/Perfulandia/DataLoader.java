package com.PerfulandiaSpa.Perfulandia;

import com.PerfulandiaSpa.Perfulandia.Model.*;
import com.PerfulandiaSpa.Perfulandia.Repository.*;
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

        productoRepository.deleteAll();
        usuarioRepository.deleteAll();
        pedidoRepository.deleteAll();
        envioRepository.deleteAll();


        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price(10.0, 100.0)));
            productoRepository.save(producto);
        }


        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().fullName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setContraseña(faker.internet().password());
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();


        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido();
            pedido.setUsuarioId(usuarios.get(random.nextInt(usuarios.size())).getId());
            pedido.setEstado(faker.options().option("Pendiente", "Procesando", "Enviado", "Entregado", "Cancelado"));
            pedido.setTotal(Double.parseDouble(faker.commerce().price(50.0, 1000.0)));
            pedidoRepository.save(pedido);
        }


        for (int i = 0; i < 10; i++) {
            Envio envio = new Envio();
            envio.setDireccion(faker.address().fullAddress());
            envio.setNombre(faker.name().fullName());
            envio.setEstado(faker.options().option("Preparando", "En tránsito", "Entregado", "Devuelto"));
            envioRepository.save(envio);
        }
    }
}
