package com.paolicias.service_administrador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paolicias.service_administrador.model.Administrador;
import com.paolicias.service_administrador.repository.AdminRepository;

@Service 
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override 
    @Transactional(readOnly = true)
    public List<Administrador> listarTodos(){
        return adminRepository.findAll();
    }
    @Override 
    @Transactional(readOnly = true)
    public Optional<Administrador> obtenerPorId(Long id){
        return adminRepository.findById(id);
    }
    @Override 
    @Transactional(readOnly = true)
    public Optional<Administrador> obtenerPorUsuarioId(Long usuarioId){
        return adminRepository.findByUsuarioId(usuarioId);
    }
    @Override
    @Transactional
    public Administrador crear(Administrador administrador){
        if(adminRepository.existsByUsuarioId(administrador.getUsuarioId())){
            throw new IllegalArgumentException("Ya existe un perfil de administrador asociado al ID: "+administrador.getUsuarioId());
        }
        return adminRepository.save(administrador);
    }
    @Override 
    @Transactional
    public Administrador actualizar(Long id, Administrador detalles){
        return adminRepository.findById(id).map(adminExiste ->{
            adminExiste.setNombreCompleto(detalles.getNombreCompleto());
            adminExiste.setCargo(detalles.getCargo());
            adminExiste.setUsuarioId(detalles.getUsuarioId());
            return adminRepository.save(adminExiste);
        }).orElseThrow(()-> new RuntimeException("Administrador no encontrado con ID "+id));
    }
    @Override 
    @Transactional
    public void eliminar(Long id){
        adminRepository.deleteById(id);
    }
}
