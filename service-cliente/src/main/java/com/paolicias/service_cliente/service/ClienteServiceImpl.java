package com.paolicias.service_cliente.service;
import java.util.List; import java.util.Optional; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import com.paolicias.service_cliente.model.Cliente; import com.paolicias.service_cliente.repository.ClienteRepository;
@Service public class ClienteServiceImpl implements ClienteService {
 private final ClienteRepository repo; public ClienteServiceImpl(ClienteRepository repo){this.repo=repo;}
 @Override @Transactional(readOnly=true) public List<Cliente> listar(){return repo.findAll();}
 @Override @Transactional(readOnly=true) public Optional<Cliente> obtener(Long id){return repo.findById(id);}
 @Override @Transactional public Cliente crear(Cliente c){if(repo.existsByRut(c.getRut()))throw new IllegalArgumentException("Ya existe un cliente con ese RUT");if(repo.existsByEmail(c.getEmail()))throw new IllegalArgumentException("Ya existe un cliente con ese correo");return repo.save(c);}
 @Override @Transactional public Cliente actualizar(Long id,Cliente datos){return repo.findById(id).map(c->{c.setNombre(datos.getNombre());c.setRut(datos.getRut());c.setEmpresa(datos.getEmpresa());c.setTelefono(datos.getTelefono());c.setEmail(datos.getEmail());return repo.save(c);}).orElseThrow(()->new IllegalArgumentException("Cliente no encontrado: "+id));}
 @Override @Transactional public void eliminar(Long id){repo.deleteById(id);}
}
