package com.paolicias.service_cliente.repository;
import java.util.Optional; import org.springframework.data.jpa.repository.JpaRepository; import com.paolicias.service_cliente.model.Cliente;
public interface ClienteRepository extends JpaRepository<Cliente,Long> { Optional<Cliente> findByRut(String rut); boolean existsByRut(String rut); boolean existsByEmail(String email); }
