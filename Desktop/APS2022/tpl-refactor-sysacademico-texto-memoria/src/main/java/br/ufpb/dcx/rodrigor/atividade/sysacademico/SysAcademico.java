package br.ufpb.dcx.rodrigor.atividade.sysacademico;

import br.ufpb.dcx.rodrigor.atividade.sysacademico.controleAcademico.*;
import br.ufpb.dcx.rodrigor.util.Texto;

public class SysAcademico {

    private int tamLinha;
    private int margem = 5;
    private Departamento departamento;
    private Curso curso;

    public SysAcademico(String nomeDepartamento) {

        this.tamLinha = nomeDepartamento.length() + 10;
        this.departamento = new Departamento(nomeDepartamento);
        this.curso = new Curso("001","SI / LCC");
    }

    public static void main(String[] args) {
        SysAcademico sys = new SysAcademico("Departamento de Ciências Exatas");
        sys.run();
    }

    public void run(){
        printMenuPrincipal();
    }

    private void printMenuPrincipal() {
        boolean continua = true;
        while(continua) {
            String[] opcoes = {"1", "2", "x"};
            String[] itens = {
                    "Gerenciar Disciplinas",
                    "Gerenciar Alunos",
                    "Sair"
            };
            String opcao = printMenu(departamento.getNome(), opcoes,itens);
            switch (opcao){
                case "1":
                    printMenuDisciplina();
                    break;
                case "2":
                    printMenuAluno();
                    break;
                case "x":
                    Texto.printMargem(margem,"Até logo!");
                    continua = false;
            }
        }
    }


    public String printMenu(String titulo, String[] opcoes,String[] itens){
        if(opcoes.length != itens.length) {
            throw new IllegalArgumentException("O número de opções deve ser igual ao número de itens do menu");
        }

        Texto.printCabecalho(titulo, tamLinha);
        for (int i = 0; i < opcoes.length; i++) {
            Texto.printMargem(margem,opcoes[i]+"\t"+itens[i]);
        }
        Texto.printLine(tamLinha);
        return Texto.selecionarOpcao(opcoes);

    }

    private void printMenuDisciplina(){
        boolean continua = true;
        while(continua) {
            String[] opcoes = {"1", "2", "3", "4", "x"};
            String[] itens = {
                    "Cadastrar Disciplina",
                    "Listar Disciplinas",
                    "Remover Disciplina",
                    "Criar Turma",
                    "Voltar"
            };
            String opcao = printMenu("Gerenciar Disciplinas", opcoes,itens);
            switch (opcao){
                case "1":
                    cadastrarDisciplina();
                    break;
                case "2":
                    listarDisciplinas();
                    break;
                case "3":
                    removerDisciplina();
                    break;
                case "4":
                    criarTurma();
                    break;
                case "x":
                    continua = false;
            }
        }

    }

    private void printMenuAluno(){
        boolean continua = true;
        while(continua) {
            String[] opcoes = {"1", "2", "3", "4", "x"};
            String[] itens = {
                    "Matricular Aluno",
                    "Listar Alunos",
                    "Cadastrar Aluno em um curso",
                    "Remover Aluno",
                    "Voltar"
            };
            String opcao = printMenu("Gerenciar Alunos", opcoes,itens);
            switch (opcao){
                case "1":
                    matricularAluno();
                    break;
                case "2":
                    listarAlunos();
                    break;
                case "3":
                    cadastrarAluno();
                    break;
                case "4":
                    removerAluno();
                    break;
                case "x":
                    continua = false;
            }
        }

    }

    private void removerAluno() {
        if(curso.getAlunosMatriculados().isEmpty()){
            Texto.printMargem(margem, "Não tem alunoa cadastrados para remover");
            return;
        }
        Texto.printMargem(margem, " Remoção de aluno ");
        String matAluno = Texto.readString("Digite a matrícula do aluno:");
        if(curso.getAlunosMatriculados().containsKey(matAluno)){
            curso.getAlunosMatriculados().remove(matAluno, curso.getAlunosMatriculados().get(matAluno));
            for(Disciplina d: departamento.getDisciplinas()) {
                for (Turma t : d.getTurmas()) {
                    for (Aluno a : t.getAlunos()) {
                        if (a.getMatricula() == matAluno) {
                            t.getAlunos().remove(a);
                            Texto.printMargem(margem, " Aluno removido com sucesso!!");
                            return;
                        }
                    }
                }
            }
        }else {
            Texto.printMargem(margem, " Matrícula informada não foi encontrada!! ");
        }
    }

    private void matricularAluno() {
        if(curso.getAlunosMatriculados().isEmpty()){
            Texto.printMargem(margem, "Não existem alunos cadastrados, cadastre o aluno para poder matricular! ");
            return;
        }
        if(departamento.getDisciplinas().isEmpty()) {
            Texto.printMargem(margem, "Não existem disciplinas cadastradas!");
            return;
        }
        listarDisciplinas();
        String codDisciplina = Texto.readString("Digite o código da disciplina:");
        if(!departamento.getCodigos().contains(codDisciplina)){
            Texto.printMargem(margem,"Disciplina inexistente '"+codDisciplina+"'");
            return;
        }
        Disciplina disciplina = departamento.getDisciplina(codDisciplina);
        if(disciplina.getTurmas().isEmpty()){
            Texto.printMargem(margem,"Esta disciplina não tem turmas cadastradas!");
            return;
        }
        int numTurma = Texto.readInt("Digite o número da turma:");
        Turma turma = disciplina.getTurma(numTurma);
        if(turma == null){
            Texto.printMargem(margem,"Turma Inválida: "+turma);
            return;
        }

        String matricula = Texto.readString("Matrícula do aluno a cacdastrar:");
        Aluno aluno = curso.getAluno(matricula);
        if(aluno != null){
            turma.matricularAluno(aluno);
        }else{
            Texto.printMargem(margem,"Não foi encontrado nenhum aluno com a matrícula informada! ");
        }


    }


    private void criarTurma() {
        if(departamento.getDisciplinas().isEmpty()) {
            Texto.printMargem(margem, "Não existem disciplinas cadastradas");
            return;
        }
        listarDisciplinas();
        String codDisciplina = Texto.readString("Digite o código da disciplina: ");
        if(!departamento.getCodigos().contains(codDisciplina)){
            Texto.printMargem(margem,"Disciplina inexistente '"+codDisciplina+"'");
            return;
        }
        Turma novaTurma = departamento.getDisciplina(codDisciplina).criarTurma();
        Texto.printMargem(margem,"Turma criada: "+novaTurma.getNumeroTurma());
    }

    private void listarDisciplinas() {
        if(departamento.getDisciplinas().isEmpty()){
            Texto.printMargem(margem, "Não existem disciplinas cadastradas! ");
            return;
        }
        Texto.printCabecalho("Disciplinas cadastradas", tamLinha);
        for(Disciplina disciplina: departamento.getDisciplinas()){
            Texto.printMargem(margem,disciplina.toString());
        }
        Texto.printLine(tamLinha);
    }

    private void listarAlunos() {
        if (curso.getAlunosMatriculados().isEmpty()){
            Texto.printMargem(margem, "Não há alunos cadastrados!! ");
            return;
        }
        Texto.printCabecalho("Alunos matriculados:", tamLinha);
        for(Aluno a: curso.getAlunosMatriculados().values()){
            Texto.printMargem(margem, "Aluno: " + a.toString());
        }
        Texto.printLinhasBranco(1);
        Texto.printCabecalho("Alunos matriculados, por disciplina", tamLinha);
        int espaco = 4;
        for(Disciplina disciplina:departamento.getDisciplinas()){
            Texto.printMargem(margem,disciplina.toString());
            Texto.printLine(tamLinha);
            for(Turma turma: disciplina.getTurmas()){
                Texto.printMargem(margem+espaco,"Turma "+turma.getNumeroTurma());
                Texto.printLine(tamLinha);
                for(Aluno aluno: turma.getAlunos()){
                    Texto.printMargem(margem+espaco+espaco,aluno.toString());
                }
            }
        }
    }


    private void cadastrarDisciplina() {
        Texto.printLine(tamLinha);
        String codigo = Texto.readString("Código da disciplina:");
        String nome = Texto.readString("Nome da disciplina:");
        if(departamento.getDisciplina(codigo) != null){
            Texto.printMargem(margem,"Já existe uma disciplina com o código '"+codigo+"'");
        }else {
            Disciplina disciplina = new Disciplina(codigo,nome,curso);
            departamento.cadastrarDisciplina(disciplina);
        }
    }

    private void removerDisciplina() {
        if(departamento.getDisciplinas().isEmpty()){
            Texto.printMargem(margem, "Não existem disciplinas cadastradas! ");
            return;
        }
        listarDisciplinas();
        Texto.printLine(tamLinha);
        String codigo = Texto.readString("Código da disciplina que deseja remover:");
        if(departamento.getDisciplina(codigo) == null){
            Texto.printMargem(margem,"Não existe uma disciplina com o código '"+codigo+"'");
        }else {
            departamento.removerDisciplina(codigo);
            for(Disciplina disciplina:departamento.getDisciplinas()){
                Texto.printMargem(margem,disciplina.toString());
                Texto.printLine(tamLinha);
                for(Turma turma: disciplina.getTurmas()){
                    Texto.printMargem(margem+4,"Turma "+turma.getNumeroTurma());
                    Texto.printLine(tamLinha);
                    for(Aluno aluno: turma.getAlunos()){
                        Texto.printMargem(margem+4+4,aluno.toString());
                    }
                }
            }
        }
    }

    private void cadastrarAluno() {
        String matricula = Texto.readString("Matrícula:");

        for (Disciplina d: departamento.getDisciplinas()){
            for (Turma t: d.getTurmas()){
                for (Aluno a: t.getAlunos()){
                    if (a.getMatricula().equals(matricula)){
                        curso.cadastrarAluno(a);
                        Texto.printMargem(margem,"Aluno cadastrado com sucesso!");
                    }else{
                        Texto.printMargem(margem,"Matrícula não encontrada!");
                    }
                }
            }
        }
    }


}