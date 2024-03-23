package com.alga.algacurso.domain.model;

import com.alga.algacurso.domain.validation.ValidationsGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.*;

import java.io.Serializable;

@Entity
//@Table(name = "ccclinte")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Cliente implements Serializable {

    //@NotNull(groups = Default.class)padrão erm not null not blank e outros
    @NotNull(groups = ValidationsGroups.ClientedId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

   // @Column(name = "nome") tooso os atributos virarão colunas
    @Size(max=50)//determina o tamanho máximo
    @NotBlank//não pode ser null sem vazia em espaços
    private String nome;

    @Size(max=200)//determina o tamanho máximo
    @NotBlank//não pode ser null sem vazia em espaços
    @Email
    private String email;


    @Size(max=60)//determina o tamanho máximo
    @NotBlank//não pode ser null sem vazia em espaços
    @Column (name = "fone")
    private String telefone;
}
