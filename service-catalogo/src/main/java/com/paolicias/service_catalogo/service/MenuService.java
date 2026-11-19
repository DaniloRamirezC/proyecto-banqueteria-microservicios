package com.paolicias.service_catalogo.service;
import java.util.List; import java.util.Optional;
import com.paolicias.service_catalogo.model.Menu;
public interface MenuService { List<Menu> listar(); Optional<Menu> obtener(Long id); List<Menu> porCategoria(Long idCategoria); Menu crear(Menu menu); Menu actualizar(Long id,Menu menu); void eliminar(Long id); }
