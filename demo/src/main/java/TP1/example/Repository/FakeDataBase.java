package TP1.example.Repository;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.StatusAventureiro;

import java.util.ArrayList;
import java.util.List;

public class FakeDataBase {
    public static List<Aventureiro> aventureiros = new ArrayList<>();
    public static Long sequence = 1L;

    static {
         for (int i = 0 ; i < 100 ; i++) {
             Aventureiro a = new Aventureiro(
                     null,
                     StatusAventureiro.ATIVO,
                     1,
                     Classe.Mago,
                     "Aventureiro " + (i + 1),
                     sequence++
             );
             aventureiros.add(a);
         }
    }

}
