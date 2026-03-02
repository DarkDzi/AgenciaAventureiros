package TP1.example.Service;

import TP1.example.Domain.Cliente;
import TP1.example.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public List<Cliente> ListarTodos() {
        return repository.findAll();
    }
    public Cliente BuscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
    public Cliente Salvar(Cliente cliente) {
        return repository.save(cliente);
    }
    public void Deletar(Long id) {
        repository.deleteById(id);
    }

}
