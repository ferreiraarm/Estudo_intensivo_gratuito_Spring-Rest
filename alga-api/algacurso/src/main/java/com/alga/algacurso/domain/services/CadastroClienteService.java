package com.alga.algacurso.domain.services;

import com.alga.algacurso.domain.exception.NegException;
import com.alga.algacurso.domain.model.Cliente;
import com.alga.algacurso.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegException("Cliente não encontrado"));
    }
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c->!c.equals(cliente))
                .isPresent() ;
        if(emailEmUso){
            throw new NegException("Email duplicado");
        }

        return clienteRepository.save(cliente);

    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);

    }

}
