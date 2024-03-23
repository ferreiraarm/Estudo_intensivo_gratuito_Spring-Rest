package com.alga.algacurso.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ParcelamentoModel {
    //não precisa ser exatamente igual a entidade

    private Long id;

    //private String nomeCliente;
    private ClienteResumeModel cliente; //paramentro para aparecer o id e o nome do cliente na class ClienteResumeModel
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;

    private OffsetDateTime dataCriacao;
}
