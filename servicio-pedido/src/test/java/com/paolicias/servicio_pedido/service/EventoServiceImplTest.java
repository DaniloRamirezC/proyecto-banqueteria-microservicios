package com.paolicias.servicio_pedido.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paolicias.servicio_pedido.model.DetalleEvento;
import com.paolicias.servicio_pedido.model.Evento;
import com.paolicias.servicio_pedido.repository.EventoRepository;

@ExtendWith(MockitoExtension.class)
class EventoServiceImplTest {

    @Mock 
    private EventoRepository eventoRepository;

    @InjectMocks 
    private EventoServiceImpl eventoService;

    private Evento eventoMock;

    @BeforeEach 
    void setUp(){
        eventoMock = new Evento();
        eventoMock.setIdEvento(1L);
        eventoMock.setTipoEvento("Matrimonio");
        eventoMock.setFecha(LocalDate.of(2026,12,18));
        eventoMock.setHorario("Noche");
        eventoMock.setCantiPersonas(100);
        eventoMock.setObservaciones("Decoración lujosa");
        eventoMock.setClienteId(1L);

        DetalleEvento detalle = new DetalleEvento();
        detalle.setIdDetalleEvento(1L);
        detalle.setNombreItem("Menú Cóctel");
        detalle.setPrecioUnitario(18000);
        detalle.setMenuId(2L);

        eventoMock.setDetalles(List.of(detalle));
    }

    @Test 
    void crearEventoConDetalle(){
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoMock);

        Evento resultado = eventoService.crearEvento(eventoMock);
        assertNotNull(resultado);
        assertEquals("Matrimonio", resultado.getTipoEvento());
        assertEquals(1, resultado.getDetalles().size());
        assertEquals(eventoMock, resultado.getDetalles().get(0).getEvento());
        verify(eventoRepository, times(1)).save(eventoMock);
    }
    @Test 
    void obtenerPorIdExistenteRetornaEvento(){
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(eventoMock));
        Optional<Evento> resultado = eventoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(100, resultado.get().getCantiPersonas());
        verify(eventoRepository, times(1)).findById(1L);
    }
    @Test 
    void obtenerPorClienteRetornaLista(){
        when(eventoRepository.findByClienteId(1L)).thenReturn(List.of(eventoMock));
        List<Evento> lista = eventoService.obtenerPorCliente(1L);
        assertFalse(lista.isEmpty());
        assertEquals(1L, lista.get(0).getClienteId());
        verify(eventoRepository, times(1)).findByClienteId(1L);
    }
}
