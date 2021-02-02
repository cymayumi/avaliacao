package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public ResponseEntity gravar(CursoDTO cursoDTO){

        CursoEntity entity = new CursoEntity();

        entity.setNomeCurso(cursoDTO.getNome());
        entity.setNrCargaHoraria(cursoDTO.getCargaHoraria());

        cursoRepository.save(entity);

        ResultData resultData = new ResultData(HttpStatus.CREATED.value(), "Curso cadastrado com sucesso",entity.getIdCurso());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
    }

    public List<CursoEntity> listar(){
        List<CursoEntity> lista = cursoRepository.findAll();
        return lista;
    }

    public List<CursoEntity> consultarPorNome(String nome) {
        return cursoRepository.findByNomeCurso(nome);
    }
}
