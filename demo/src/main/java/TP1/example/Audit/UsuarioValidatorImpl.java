package TP1.example.Audit;

import TP1.example.Audit.Repository.UsuarioRepository;
import TP1.example.Aventura.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioValidatorImpl implements UsuarioValidator {
    private final UsuarioRepository usuarioRepository;
    @Override
    public boolean existe(Long usuarioId) {
        return usuarioRepository.existsById(usuarioId);
    }
}
