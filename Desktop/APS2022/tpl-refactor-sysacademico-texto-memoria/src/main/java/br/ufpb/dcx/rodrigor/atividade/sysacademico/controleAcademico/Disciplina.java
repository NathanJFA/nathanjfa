package br.ufpb.dcx.rodrigor.atividade.sysacademico.controleAcademico;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {


    private Curso curso;
    private String codigo;
    private String nome;
    private List<Turma> turmas;

    public Disciplina(String codigo, String nome, Curso curso){
        this.codigo = codigo;
        this.nome = nome;
        this.turmas = new ArrayList<>();
        this.curso = curso;
    }


    public Turma criarTurma(){
        Turma novaTurma = new Turma(this,this.turmas.size()+1);
        this.turmas.add(novaTurma);
        return novaTurma;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String toString(){
        StringBuffer disciplinaStr = new StringBuffer(this.codigo+" - "+this.nome);
        if(!this.turmas.isEmpty()){
            disciplinaStr.append(" - turma(s): ");
            for(Turma turma: turmas)
                disciplinaStr.append(turma.getNumeroTurma()+" ");
        }
        return disciplinaStr.toString() ;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public List<Turma> getTurmas() {
        return this.turmas;
    }

    public Turma getTurma(int turma) {
        return this.turmas.get(turma-1);
    }
}
