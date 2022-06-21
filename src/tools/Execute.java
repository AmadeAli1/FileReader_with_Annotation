package tools;

import model.Pessoa;

import java.util.List;

public class Execute {
    public static void main(String[] args) {
        Config<Pessoa> config = new Config<>(Pessoa.class, Execute::reader);
        config.save(List.of(new Pessoa("Archiel","Santos",(byte)18,"playboy")));
    }

    public static void reader(List<Pessoa> alunos) {
        alunos.forEach(System.out::println);
    }

}
