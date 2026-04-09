package TP1.example.Audit;


import TP1.example.Audit.Repository.OrganizacoesRepository;
import TP1.example.Aventura.OrganizacaoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizacaoValidatorImpl implements OrganizacaoValidator {

    private final OrganizacoesRepository repository;

    @Override
    public boolean existe(Long organizacaoId) {
        return repository.existsById(organizacaoId);
    }
}
