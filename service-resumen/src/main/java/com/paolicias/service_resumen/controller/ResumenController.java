package com.paolicias.service_resumen.controller;
import com.paolicias.service_resumen.model.Resumen;
import com.paolicias.service_resumen.service.ResumenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumenes")
public class ResumenController {

    private final ResumenService resumenService;

    public ResumenController(ResumenService resumenService) {
        this.resumenService = resumenService;
    }

    @GetMapping
    public List<Resumen> listar() {
        return resumenService.listarResumenes();
    }

    @GetMapping("/{id}")
    public Resumen obtenerPorId(@PathVariable Long id) {
        return resumenService.obtenerPorId(id);
    }

    @GetMapping("/evento/{idEvento}")
    public List<Resumen> listarPorEvento(@PathVariable Long idEvento) {
        return resumenService.listarPorEvento(idEvento);
    }

    @PostMapping
    public Resumen crear(@RequestBody Resumen resumen) {
        return resumenService.guardarResumen(resumen);
    }
}
