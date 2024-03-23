package com.alga.algacurso.domain.repository;

import org.aspectj.apache.bcel.generic.InstructionConstants;
import com.alga.algacurso.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //pode-se criar metodos aqui
    public List<Cliente> findByNome(String nome);
    public List<Cliente> findByNomeContaining(String nome);

    Optional<Cliente> findByEmail(String email);
}
