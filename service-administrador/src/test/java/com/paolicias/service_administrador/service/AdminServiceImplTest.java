package com.paolicias.service_administrador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paolicias.service_administrador.model.Administrador;
import com.paolicias.service_administrador.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    
    @Mock
    private AdminRepository adminRepository;
    
    @InjectMocks 
    private AdminServiceImpl adminService;

    private Administrador adminMock;

    @BeforeEach 
    void setUp(){
        adminMock = new Administrador();
        adminMock.setIdAdmin(1L);
        adminMock.setNombreCompleto("Juan Pérez");
        adminMock.setCargo("Jefe de Banquetería");
        adminMock.setUsuarioId(10L);
    }

    @Test 
    void crearUsuarioSinAdmin(){
        when(adminRepository.existsByUsuarioId(10L)).thenReturn(false);
        when(adminRepository.save(any(Administrador.class))).thenReturn(adminMock);

        Administrador adminCreado = adminService.crear(adminMock);
        assertNotNull(adminCreado);
        assertEquals("Juan Pérez", adminCreado.getNombreCompleto());
        verify(adminRepository, times(1)).save(adminMock);
    }
    @Test 
    void crearUsuarioAdminExiste(){
        when(adminRepository.existsByUsuarioId(10L)).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->{
            adminService.crear(adminMock);
        });
        assertTrue(ex.getMessage().contains("Ya existe un perfil de administrador"));
        verify(adminRepository, never()).save(any());
    }
    @Test 
    void obtenerIdUsuarioRetornaAdmin(){
        when(adminRepository.findByUsuarioId(10L)).thenReturn(Optional.of(adminMock));
        Optional<Administrador> resultado = adminService.obtenerPorUsuarioId(10L);
        assertTrue(resultado.isPresent());
        assertEquals("Jefe de Banquetería", resultado.get().getCargo());
    }
    @Test 
    void retornaListaAdmin(){
        when(adminRepository.findAll()).thenReturn(List.of(adminMock));
        List<Administrador> lista = adminService.listarTodos();
        assertEquals(1, lista.size());
        verify(adminRepository, times(1)).findAll();
    }
}
