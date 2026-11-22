package com.paolicias.service_catalogo.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.paolicias.service_catalogo.model.Categoria;
public interface CategoriaRepository extends JpaRepository<Categoria,Long> { 
    Optional<Categoria> findByNombreCategoriaIgnoreCase(String nombreCategoria); 
}
