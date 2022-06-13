package tools;

import model.Aluno;

import java.util.List;

public class Execute {
    public static void main(String[] args) {
        Config<Aluno> config = new Config<>(Aluno.class, a -> a.forEach(System.out::println));
        config.save(List.of(
                new Aluno("Amade", "Informatica", (byte) 22, false),
                new Aluno("Rafae", "Direito", (byte) 26, false),
                new Aluno("Acacio", "Gestao", (byte) 19, false)
        ));
    }
}
