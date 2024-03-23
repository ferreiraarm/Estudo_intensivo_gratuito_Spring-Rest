package com.alga.algacurso.domain.services;


import com.alga.algacurso.domain.exception.NegException;
import com.alga.algacurso.domain.model.Cliente;
import com.alga.algacurso.domain.model.Parcelamento;
import com.alga.algacurso.domain.repository.ClienteRepository;
import com.alga.algacurso.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ParcelamentoService {

private final ParcelamentoRepository parcelamentoRepository;
private final CadastroClienteService cadastroClienteService;

@Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {
        if(novoParcelamento.getId() != null){
            throw new NegException("Parcelamento a ser criado não devve possuir um código");
        }

    Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());


        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(OffsetDateTime.now());

        return  parcelamentoRepository.save(novoParcelamento);
    }
}
