package com.paolicias.service_catalogo.repository;

import com.paolicias.service_catalogo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByIdCategoria(Long idCategoria);
}
