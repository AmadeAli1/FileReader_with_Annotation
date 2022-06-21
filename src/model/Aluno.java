package model;

import anotation.Campo;
import anotation.DataType;
import anotation.DataType.*;
import anotation.LerFicheiro;

import java.util.StringJoiner;

import static anotation.DataType.BOOLEAN;
import static anotation.DataType.BYTE;

@LerFicheiro(filename = "src\\file.txt", separador = "|")
public class Aluno {
    @Campo(min = 5, max = 20)
    private String nome;

    @Campo(min = 5, max = 20, valid = {"gestao", "medicina", "informatica"})
    private String curso;

    @Campo(type = BYTE, min = 16, max = 30)
    private Byte idade;

    @Campo(type = BOOLEAN, min = 4, max = 5)
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
        return new StringJoiner(", ", Aluno.class.getSimpleName() + "[", "]")
                .add("nome='" + nome + "'")
                .add("curso='" + curso + "'")
                .add("idade=" + idade)
                .add("devendo=" + devendo)
                .toString();
    }

}
