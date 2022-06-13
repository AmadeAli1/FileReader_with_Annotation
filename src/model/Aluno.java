package model;

import anotation.Campo;
import anotation.DataType;
import anotation.LerFicheiro;

@LerFicheiro(filename = "src\\file.txt", separador = "/")
public class Aluno {
    @Campo(min = 5, max = 20)
    private String nome;

    @Campo(min = 5, max = 20)
    private String curso;

    @Campo(type = DataType.BYTE, min = 16, max = 30)
    private Byte idade;

    @Campo(type = DataType.BOOLEAN, min = 4, max = 5)
    private Boolean devendo;

    public Aluno(String nome, String curso, Byte idade, Boolean devendo) {
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
        this.devendo = devendo;
    }

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public Byte getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                ", idade=" + idade +
                ", devendo=" + devendo +
                '}';
    }

}
