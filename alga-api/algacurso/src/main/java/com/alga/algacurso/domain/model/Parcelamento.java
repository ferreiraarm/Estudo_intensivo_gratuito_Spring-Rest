package com.alga.algacurso.domain.model;

import com.alga.algacurso.domain.validation.ValidationsGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@JoinColumn(name = "cliente_Id") isso é gerado automatico por causa do nome
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationsGroups.ClientedId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Max(12)
    private Integer quantidadeParcelas;



    //private LocalDateTime dataCriacao; padroniza como horario que não armazena off set

    private OffsetDateTime dataCriacao;//cria data hr e armazena o off set time
}
