package one.digitalinnovation.lab_padroes_projeto_spring.service.impl;

import one.digitalinnovation.lab_padroes_projeto_spring.model.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring.model.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.model.Endereco;
import one.digitalinnovation.lab_padroes_projeto_spring.model.EnderecoRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.service.ClienteService;
import one.digitalinnovation.lab_padroes_projeto_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.CaretListener;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    //SingletonÇ Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos do Spring com @Autowired.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);

    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }



}
