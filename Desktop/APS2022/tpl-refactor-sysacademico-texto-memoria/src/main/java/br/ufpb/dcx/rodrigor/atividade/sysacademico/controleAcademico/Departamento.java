package br.ufpb.dcx.rodrigor.atividade.sysacademico.controleAcademico;

import java.util.*;

public class Departamento {

    private String nome;
    private Map<String,Disciplina> disciplinas;

    public Departamento(String nome) {
        this.nome = nome;
        disciplinas = new LinkedHashMap<>();
    }

    public Disciplina cadastrarDisciplina(Disciplina novaDisciplina) {
//        if(disciplinas.containsKey(codigo)) {
//            throw new DepartamentoException("Já existe uma disciplina com o código '"+codigo+"'");
//        }
        disciplinas.put(novaDisciplina.getCodigo(),novaDisciplina);
        return novaDisciplina;
    }

    public void removerDisciplina(String codigo){
        if (disciplinas.containsKey(codigo)){
            disciplinas.remove(codigo);
        }
    }

    public String getNome() {
        return this.nome;
    }

    public Disciplina getDisciplina(String codigo) {
        return this.disciplinas.get(codigo);
    }

    public Collection<Disciplina> getDisciplinas() {
        return this.disciplinas.values();
    }

    public Set<String> getCodigos(){
        return this.disciplinas.keySet();
    }

}
