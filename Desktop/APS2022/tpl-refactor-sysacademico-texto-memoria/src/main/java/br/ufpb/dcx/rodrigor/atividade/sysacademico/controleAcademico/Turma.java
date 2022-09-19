package br.ufpb.dcx.rodrigor.atividade.sysacademico.controleAcademico;

import java.util.LinkedList;
import java.util.List;

public class Turma {

    private int numeroTurma;
    private Disciplina disciplina;

    private List<Aluno> alunos;
    public Turma(Disciplina disciplina, int numeroTurma) {
        this.disciplina = disciplina;
        this.numeroTurma = numeroTurma;
        alunos = new LinkedList<>();
    }

    public int getNumeroTurma(){
        return this.numeroTurma;
    }

    public Disciplina getDisciplina(){
        return this.disciplina;
    }

    public String toString(){
        return this.disciplina.getNome()+": Turma "+this.numeroTurma;
    }

    public void matricularAluno(Aluno novoAluno) {
        this.alunos.add(novoAluno);
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }
}
