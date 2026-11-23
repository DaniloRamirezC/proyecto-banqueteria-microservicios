package com.paolicias.service_autenticacion.service;

import com.paolicias.service_autenticacion.dto.AuthResponse;
import com.paolicias.service_autenticacion.dto.LoginRequest;
import com.paolicias.service_autenticacion.dto.RegistroRequest;
import com.paolicias.service_autenticacion.model.Rol;
import com.paolicias.service_autenticacion.model.Usuario;
import com.paolicias.service_autenticacion.repository.RolRepository;
import com.paolicias.service_autenticacion.repository.UsuarioRepository;
import com.paolicias.service_autenticacion.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           RolRepository rolRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostConstruct
    @Transactional
    public void inicializarRoles() {
        if (rolRepository.findByNombreRol("administrador").isEmpty()) {
            Rol admin = new Rol();
            admin.setNombreRol("administrador");
            rolRepository.save(admin);
        }
        if (rolRepository.findByNombreRol("cliente").isEmpty()) {
            Rol cliente = new Rol();
            cliente.setNombreRol("cliente");
            rolRepository.save(cliente);
        }
    }

    @Override
    @Transactional
    public AuthResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado.");
        }

        String rolEntrada = (request.getNombreRol() != null && !request.getNombreRol().isBlank())
                ? request.getNombreRol().trim().toLowerCase()
                : "cliente";
        
        final String nombreRolSolicitado = "admin".equals(rolEntrada)
            ? "administrador"
            : rolEntrada;

        Rol rol = rolRepository.findByNombreRol(nombreRolSolicitado)
                .orElseThrow(() -> new IllegalArgumentException("Rol inválido: " + nombreRolSolicitado));

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);
        String token = jwtUtil.generarToken(guardado);

        return new AuthResponse(
                token,
                guardado.getIdUsuario(),
                guardado.getNombreUsuario(),
                guardado.getCorreo(),
                guardado.getRol().getNombreRol()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas."));

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new IllegalArgumentException("Credenciales inválidas.");
        }

        String token = jwtUtil.generarToken(usuario);

        return new AuthResponse(
                token,
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol()
        );
    }
}
