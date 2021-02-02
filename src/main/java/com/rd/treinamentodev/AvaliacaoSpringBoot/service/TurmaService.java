package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.InstrutorDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.TurmaDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.InstrutorEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.TurmaEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public List<TurmaDTO> listar(){

        List<TurmaEntity> listEntity = turmaRepository.findAll();
        List<TurmaDTO> listDTO = new ArrayList<>();

        for(TurmaEntity turmaEntity : listEntity) {
            TurmaDTO t = new TurmaDTO();

            Date dtInicio = turmaEntity.getDtInicio();
            String formatadaInicio = SDF.format(dtInicio);

            t.setDtInicio(formatadaInicio);

            Date dtFim = turmaEntity.getDtFinal();
            String formatadaFim = SDF.format(dtFim);
            t.setDtFim(formatadaFim);

            CursoEntity cursoEntity = turmaEntity.getCurso();
            CursoDTO curso = new CursoDTO();
            curso.setNome(cursoEntity.getNomeCurso());
            curso.setCargaHoraria(cursoEntity.getNrCargaHoraria());
            t.setCurso(curso);

            List<InstrutorDTO> listDTOInstrutor = new ArrayList<>();
            List<InstrutorEntity> instEntity = turmaEntity.getInstrutores();
            for(InstrutorEntity entityInst : instEntity){
                InstrutorDTO instrutor = new InstrutorDTO();
                instrutor.setNome(entityInst.getNomeInstrutor());
                instrutor.setValorHora(entityInst.getValorHora());

                listDTOInstrutor.add(instrutor);
            }
            t.setInstrutores(listDTOInstrutor);


            List<AlunoDTO> listDTOAluno = new ArrayList<>();
            List<AlunoEntity> alunoEntity = turmaEntity.getAlunos();
            for(AlunoEntity entityAluno : alunoEntity){
                AlunoDTO aluno = new AlunoDTO();
                aluno.setNome(entityAluno.getNomeAluno());
                aluno.setCpf(entityAluno.getCpf());

                listDTOAluno.add(aluno);
            }
            t.setAlunos(listDTOAluno);

            listDTO.add(t);
        }

        return listDTO;
    }
}
