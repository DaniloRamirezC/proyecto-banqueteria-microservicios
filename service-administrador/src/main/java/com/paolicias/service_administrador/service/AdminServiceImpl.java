package com.paolicias.service_administrador.service;

import com.paolicias.service_administrador.repository.ValidacionPagoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paolicias.service_administrador.dto.EstimacionOperativaDTO;
import com.paolicias.service_administrador.model.Administrador;
import com.paolicias.service_administrador.model.ValidacionPago;
import com.paolicias.service_administrador.repository.AdminRepository;

@Service 
public class AdminServiceImpl implements AdminService{
    private final ValidacionPagoRepository validacionPagoRepository;
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository, ValidacionPagoRepository validacionPagoRepository){
        this.adminRepository = adminRepository;
        this.validacionPagoRepository = validacionPagoRepository;
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
    @Override 
    @Transactional(readOnly = true)
    public EstimacionOperativaDTO calcularEstimacion(int cantiAsistentes, String tipoEvento){
        if(cantiAsistentes<=10 || cantiAsistentes>100){
            throw new IllegalArgumentException("La cantidad de asistentes debe ser entre 10 y 100 personas");
        }

        boolean esPasteleria = tipoEvento != null && tipoEvento.equalsIgnoreCase("Pastelería");

        int garzones = esPasteleria ? 0: Math.max(2,(int) Math.ceil(cantiAsistentes /15.0));
        int ayudanteCocina = Math.max(1,(int) Math.ceil(cantiAsistentes/25.0));
        int totalStaff = garzones + ayudanteCocina;

        String tramo;
        String detalleInsumos;

        if(cantiAsistentes<=20){
            tramo = "Tramo pequeño (hasta 20 personas)";
            detalleInsumos = esPasteleria
                ? "Cajas de transporte térmico, bases de torta y bandejas descartables para 20 porciones."
                : "1 set compacto de vajilla/cristalería (20 asistentes), 2 manteles principales y una estación de café";
        }else if(cantiAsistentes<=50){
            tramo = "Tramo mediano (21 a 50 personas)";
            detalleInsumos = esPasteleria
                ? "Embalaje reforzado de pastelería y 50 kits de platos/cubiertos de postre."
                : "3 sets de vajilla/cristalería (50 asistentes), 5 manteles de salón, 2 chafer buffeteras y estación doble de bar/café";
        }else{
            tramo = "Tramo evento completo (51 a 100 personas)";
            detalleInsumos = esPasteleria
            ? "Logística refrigerada de pastelería y 100 kits de servicio."
            : "6 sets completos de vajilla/cristalería (100 asistentes), 10 manteles, 4 chafer buffeteras y estación doble de bar/café";
        }

        return new EstimacionOperativaDTO(
            tipoEvento !=null ? tipoEvento : "Banquetería",
            cantiAsistentes,
            garzones,
            ayudanteCocina,
            totalStaff,
            tramo,
            detalleInsumos
        );
    }

    @Override 
    @Transactional 
    public ValidacionPago registraValidacionPago(Long idAdmin, ValidacionPago validacion){
        if(!adminRepository.existsById(idAdmin)){
            throw new IllegalArgumentException("No existe un administrador con ID: "+idAdmin);
        }
        if(validacion.getEstadoValidacion() ==  null){
            throw new IllegalArgumentException("El estado de validación debe ser válido");
        }
        validacion.setIdAdmin(idAdmin);
        return validacionPagoRepository.save(validacion);
    }
    @Override 
    @Transactional(readOnly = true)
    public List<ValidacionPago> listarValidacionPorAdmin(Long idAdmin){
        return validacionPagoRepository.findByIdAdminOrderByFechaRevisionDesc(idAdmin);
    }
}
