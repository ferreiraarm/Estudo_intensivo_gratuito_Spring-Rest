package com.alga.algacurso.controller;


import com.alga.algacurso.assembler.ParcelamentoAssembler;
import com.alga.algacurso.domain.exception.NegException;
import com.alga.algacurso.domain.model.Parcelamento;
import com.alga.algacurso.domain.repository.ParcelamentoRepository;

import com.alga.algacurso.domain.services.ParcelamentoService;
import com.alga.algacurso.model.ParcelamentoModel;
import com.alga.algacurso.model.input.ParcelamentoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;

    private final ParcelamentoService parcelamentoService;

    private final ParcelamentoAssembler parcelamentoAssembler;

    //private final ModelMapper modelMapper; passou para assembler/Parcelamento Assembler

    @GetMapping
    public List<ParcelamentoModel> listar(){

        return parcelamentoAssembler.toCollecttionModel(parcelamentoRepository.findAll());
    }

    //usandp Represention model, com código borderplate
    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {

        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

//          Usado quando instaciado nessa classe o ModelWapper
//        return parcelamentoRepository.findById(parcelamentoId)
//                .map(parcelamento -> modelMapper.map(parcelamento, ParcelamentoModel.class))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

    }

                    //desta forma não precisa fazer os set abaixo
                    //var parcelamentoModel = modelMapper.map(parcelamento, ParcelamentoModel.class);


                    //codigo repetitivo borderplate
//                    .map(parcelamento -> {
//                    var parcelamentoModel = new ParcelamentoModel();
//                    parcelamentoModel.setId(parcelamento.getId());
//                    parcelamentoModel.setDescricao(parcelamento.getDescricao());
//                    parcelamentoModel.setParcelas(parcelamento.getQuantidadeParcelas());
//                    parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
//                    parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
//                    parcelamentoModel.setDataCriacao(parcelamento.getDataCriacao());



        //.map(p-> ResponseEntity.ok(p).orelse(ResponseEntity   mesmo efeito que acima
        // .notFound().build());
    //}


    //sem o uso de DTO
//    @GetMapping("/{parcelamentoId}")
//    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId){
//
//
//        return parcelamentoRepository.findById(parcelamentoId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//
//        //.map(p-> ResponseEntity.ok(p).orelse(ResponseEntity   mesmo efeito que acima
//        // .notFound().build());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInput parcelamentoInput){

        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);

        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(
                novoParcelamento);

        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
    }

    //POST direto
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ParcelamentoModel cadastrar(@Valid @RequestBody Parcelamento parcelamento){
//        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(parcelamento);
//
//        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
//    }



    //
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento){
//
//
//           return parcelamentoService.cadastrar(parcelamento);
//    }


//Mudando para classe ApiExceptionHandler boas práticas
//    @ExceptionHandler(NegException.class)
//    public ResponseEntity<String> capturar(NegException e){
//        return ResponseEntity.badRequest()
//                .body(e.getMessage());
//    }

}
