package com.paolicias.service_cliente.controller;
import java.util.List; 
import org.springframework.http.*; 
import org.springframework.web.bind.annotation.*; 
import jakarta.validation.Valid;
import com.paolicias.service_cliente.model.Cliente; 
import com.paolicias.service_cliente.service.ClienteService;
@RestController 
@RequestMapping("/api/clientes") 
public class ClienteController {
    private final ClienteService service; 
    public ClienteController(ClienteService service){
        this.service=service;
    }
    @GetMapping 
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}") 
    public ResponseEntity<Cliente> obtener(@PathVariable Long id){
        return service.obtener(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        }
    @PostMapping 
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente c){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(c));
            }catch(IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    @PutMapping("/{id}") 
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody Cliente c){
        try{return ResponseEntity.ok(service.actualizar(id,c));

        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
