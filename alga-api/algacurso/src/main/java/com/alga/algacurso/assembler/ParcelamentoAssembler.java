package com.alga.algacurso.assembler;

import com.alga.algacurso.domain.model.Parcelamento;
import com.alga.algacurso.model.ParcelamentoModel;
import com.alga.algacurso.model.input.ParcelamentoInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {
    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento){

        return modelMapper.map(parcelamento, ParcelamentoModel.class);

    }


    //transforma uma lista de parcelamento em uma lista de parcelamento model
    public List<ParcelamentoModel> toCollecttionModel(List<Parcelamento> parcelamentos) {
        return parcelamentos.stream()
                .map(this::toModel)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput){
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
