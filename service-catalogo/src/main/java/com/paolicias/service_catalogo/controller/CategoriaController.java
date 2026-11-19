package com.paolicias.service_catalogo.controller;
import java.util.List; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
import com.paolicias.service_catalogo.model.Categoria; import com.paolicias.service_catalogo.repository.CategoriaRepository;
@RestController @RequestMapping("/api/categorias") @CrossOrigin(origins="*")
public class CategoriaController { private final CategoriaRepository repo; public CategoriaController(CategoriaRepository repo){this.repo=repo;}
 @GetMapping public ResponseEntity<List<Categoria>> listar(){return ResponseEntity.ok(repo.findAll());}
 @PostMapping public ResponseEntity<Categoria> crear(@RequestBody Categoria c){return ResponseEntity.ok(repo.save(c));}
}
