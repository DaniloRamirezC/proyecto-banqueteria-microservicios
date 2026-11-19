package com.paolicias.service_catalogo.service;

import com.paolicias.service_catalogo.model.Menu;
import com.paolicias.service_catalogo.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repo;

    public MenuServiceImpl(MenuRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Menu> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Menu> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Menu> porCategoria(Long idCategoria) {
        return repo.findByIdCategoria(idCategoria);
    }

    @Override
    @Transactional
    public Menu crear(Menu menu) {
        return repo.save(menu);
    }

    @Override
    @Transactional
    public Menu actualizar(Long id, Menu datos) {
        return repo.findById(id).map(menu -> {
            menu.setNombre(datos.getNombre());
            menu.setDetalle(datos.getDetalle());
            menu.setPrecio(datos.getPrecio());
            menu.setIdCategoria(datos.getIdCategoria());
            return repo.save(menu);
        }).orElseThrow(() ->
                new IllegalArgumentException("Menú no encontrado: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Menú no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
