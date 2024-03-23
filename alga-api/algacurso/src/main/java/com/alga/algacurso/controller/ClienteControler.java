package com.alga.algacurso.controller;

import com.alga.algacurso.domain.exception.NegException;
import com.alga.algacurso.domain.model.Cliente;
import com.alga.algacurso.domain.repository.ClienteRepository;
import com.alga.algacurso.domain.services.CadastroClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteControler {

    @PersistenceContext
    private EntityManager manager;


    //injeção através de contrutor
//    public ClienteControler(ClienteRepository clienteRepository) {
//        this.clienteRepository = clienteRepository;
//    }


    private final CadastroClienteService cadastroClienteService;
    //injeção com lombok gera construtor automatico
    //@Autowired//tipo de njeção de dependencia com anotações
    private final ClienteRepository clienteRepository;//instancia lombok


    //Pesquisa todos do banco de dados, usando persistencia no controller(não indicado)
     @GetMapping
    public List<Cliente> listar() {

            return clienteRepository.findAll();//busca no banco de dados
            //return manager.createQuery("from Cliente", Cliente.class).getResultList(); feito sem usar a interface repository
     }

     //busca por nome
    @GetMapping("/clientesN")
    public List<Cliente> listarN() {

        return clienteRepository.findByNome("zé");//busca no banco de dados
        //return manager.createQuery("from Cliente", Cliente.class).getResultList(); feito sem usar a interface repository
    }

    //busca por nome
    @GetMapping("/clientesC")
    public List<Cliente> listarC() {

        return clienteRepository.findByNomeContaining("zé");//busca no banco de dados um nome que contem zé
        //return manager.createQuery("from Cliente", Cliente.class).getResultList(); feito sem usar a interface repository
    }



        //retorna um cliente por id
      @GetMapping("/{clienteId}")
        public ResponseEntity<Cliente> buscar(@PathVariable long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

            if(cliente.isPresent()){
                return ResponseEntity.ok(cliente.get());

             }
            return ResponseEntity.notFound().build();
        }
    //adição do banco de dados com o verbo POST, segue Postman para adicionar
    @ResponseStatus(HttpStatus.CREATED)
        @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){

         return cadastroClienteService.salvar(cliente);
       // return clienteRepository.save(cliente); usando repositorios
    }


    //atualizando banco de dados
@PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente){
        if( !clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        //cliente = clienteRepository.save(cliente);
        cliente = cadastroClienteService.salvar(cliente);

        return  ResponseEntity.ok(cliente);
    }
@DeleteMapping("/{clienteId}")
    public  ResponseEntity<Void> remover(@PathVariable Long clienteId){


        if( !clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        //clienteRepository.deleteById(clienteId); usando regras de negocio com services

        cadastroClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
     }
    //Mudando para classe ApiExceptionHandler boas práticas
//    @ExceptionHandler(NegException.class)
//    public ResponseEntity<String> capturar(NegException e){
//        return ResponseEntity.badRequest()
//                .body(e.getMessage());
//    }
//
//
//

//        var cliente01 = new Cliente();
//        cliente01.setId(1);
//        cliente01.setNome("aaaa   nnnn");
//        cliente01.setTelefone("9999999");
//        cliente01.setEmail("AAAA@AAAAAAA");
//
//        var cliente02 = new Cliente();
//        cliente02.setId(2);
//        cliente02.setNome("aakjaazzzzzv   zzzz");
//        cliente02.setTelefone("99kkk99999");
//        cliente02.setEmail("AAAAhhhh@AAAAAAA");
//
//        var cliente03 = new Cliente();
//        cliente03.setId(3);
//        cliente03.setNome("aahhuopoaa zzzzz");
//        cliente03.setTelefone("999jjljl9999");
//        cliente03.setEmail("AAAA@AAAAjjljljljAAA");
//
//        return Arrays.asList(cliente01, cliente02, cliente03);


}
