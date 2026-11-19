package com.paolicias.servicio_pedido.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paolicias.servicio_pedido.model.Evento;
import com.paolicias.servicio_pedido.service.EventoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity <List<Evento>> listar() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(@PathVariable Long id){
        return eventoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Evento>> obtenerPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(eventoService.obtenerPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<Evento> crear(@RequestBody Evento evento){
        Evento nuevoEvento = eventoService.crearEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEvento);
    }
}
