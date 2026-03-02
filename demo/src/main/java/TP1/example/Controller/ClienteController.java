package TP1.example.Controller;

import TP1.example.Domain.Cliente;
import TP1.example.Service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }
    @GetMapping
    public List<Cliente> listar() {
        return  service.ListarTodos();
    }
    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        return service.BuscarPorId(id);
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return service.Salvar(cliente);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.Deletar(id);
    }
}
