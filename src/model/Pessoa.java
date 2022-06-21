package model;

import anotation.Campo;
import anotation.DataType;
import anotation.LerFicheiro;

import java.util.StringJoiner;

@LerFicheiro(filename = "src\\pessoa.txt", separador = "|")
public class Pessoa {
    @Campo(min = 3)
    private String firstname;

    @Campo(min = 2)
    private String lastname;

    @Campo(type = DataType.BYTE)
    private Byte idade;

    @Campo(valid = {"solteiro", "casado","playboy"})
    private String estado_civil;

    public Pessoa(String firstname, String lastname, Byte idade, String estado_civil) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.idade = idade;
        this.estado_civil = estado_civil;
    }

    public Pessoa() {}

    @Override
    public String toString() {
        return new StringJoiner(", ", Pessoa.class.getSimpleName() + "[", "]")
                .add("firstname='" + firstname + "'")
                .add("lastname='" + lastname + "'")
                .add("idade=" + idade)
                .add("estado_civil='" + estado_civil + "'")
                .toString();
    }

}
