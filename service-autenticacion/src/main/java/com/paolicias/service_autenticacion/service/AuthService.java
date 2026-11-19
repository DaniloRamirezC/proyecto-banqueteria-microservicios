package com.paolicias.service_autenticacion.service;

import com.paolicias.service_autenticacion.dto.AuthResponse;
import com.paolicias.service_autenticacion.dto.LoginRequest;
import com.paolicias.service_autenticacion.dto.RegistroRequest;

public interface AuthService {
    AuthResponse registrar(RegistroRequest request);
    AuthResponse login(LoginRequest request);
}
