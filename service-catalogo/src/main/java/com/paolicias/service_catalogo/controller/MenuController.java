package com.paolicias.service_catalogo.controller;
import java.util.List; 
import org.springframework.http.*; 
import org.springframework.web.bind.annotation.*; 
import jakarta.validation.Valid;
import com.paolicias.service_catalogo.model.Menu; 
import com.paolicias.service_catalogo.service.MenuService;

@RestController 
@RequestMapping("/api/catalogo") 

public class MenuController {
    private final MenuService service; 
    
    public MenuController(MenuService service){
        this.service=service;
    }
    @GetMapping public ResponseEntity<List<Menu>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}") 
    public ResponseEntity<Menu> obtener(@PathVariable Long id){
        return service.obtener(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/categoria/{idCategoria}") 
    public ResponseEntity<List<Menu>> porCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok(service.porCategoria(idCategoria));
    }
    @PostMapping public ResponseEntity<Menu> crear(@Valid @RequestBody Menu menu){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(menu));
    }
    @PutMapping("/{id}") 

    public ResponseEntity<Menu> actualizar(@PathVariable Long id,@Valid @RequestBody Menu menu){
        try{
            return ResponseEntity.ok(service.actualizar(id,menu));
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
