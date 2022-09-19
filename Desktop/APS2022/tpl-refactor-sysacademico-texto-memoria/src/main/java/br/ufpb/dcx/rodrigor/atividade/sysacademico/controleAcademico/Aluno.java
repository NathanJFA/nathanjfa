package br.ufpb.dcx.rodrigor.atividade.sysacademico.controleAcademico;

public class Aluno {


    private String nome;
    private String matricula;

    public Aluno(String matricula, String nome){
        this.nome = nome;
        this.matricula = matricula;
    }

    public String toString(){
        return this.matricula+"\t"+ this.nome;
    }


    public String getMatricula() {
        return this.matricula;
    }


}
