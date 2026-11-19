package com.paolicias.service_administrador.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paolicias.service_administrador.model.Administrador;
import com.paolicias.service_administrador.service.AdminService;

@RestController 
@RequestMapping("/api/administrador")
public class AdminController {
    private final AdminService adminService;
    
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listar(){
        return ResponseEntity.ok(adminService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerPorId(@PathVariable Long id){
        return adminService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Administrador> obtenerPorUsuarioId(@PathVariable Long usuarioId){
        return adminService.obtenerPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping 
    public ResponseEntity<?> crear(@RequestBody Administrador administrador){
        try{
            Administrador adminNuevo = adminService.crear(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(adminNuevo);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(@PathVariable Long id, @RequestBody Administrador administrador){
        try{
            Administrador adminActualizado = adminService.actualizar(id, administrador);
            return ResponseEntity.ok(adminActualizado);
        }catch(RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        adminService.eliminar(id);
        return ResponseEntity.notFound().build();
    }
}
