package com.paolicias.service_resumen.controller;
import com.paolicias.service_resumen.model.Comprobante;
import com.paolicias.service_resumen.service.ComprobanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    public ComprobanteController(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    @GetMapping
    public List<Comprobante> listar() {
        return comprobanteService.listarComprobantes();
    }

    @GetMapping("/{id}")
    public Comprobante obtenerPorId(@PathVariable Long id) {
        return comprobanteService.obtenerPorId(id);
    }

    @PostMapping
    public Comprobante registrar(@RequestBody Comprobante comprobante) {
        return comprobanteService.registrarComprobante(comprobante);
    }

    @PatchMapping("/{id}/estado")
    public Comprobante actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return comprobanteService.actualizarEstadoAbono(id, body.get("estadoAbono"));
    }
}