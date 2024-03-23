package com.alga.algacurso.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ParcelamentoInput {

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorToTal;

    @NotNull
    @Positive
    @Max(12)
    private  Integer quantidadeParcelas;

    @Valid
    @NotNull
    private ClienteIdInput cliente;
}
