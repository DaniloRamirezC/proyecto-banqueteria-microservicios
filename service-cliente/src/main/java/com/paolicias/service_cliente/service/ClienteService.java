package com.paolicias.service_cliente.service;
import java.util.List; import java.util.Optional; import com.paolicias.service_cliente.model.Cliente;
public interface ClienteService { List<Cliente> listar(); Optional<Cliente> obtener(Long id); Cliente crear(Cliente c); Cliente actualizar(Long id,Cliente c); void eliminar(Long id); }
