import models.Pokedex;
import service.Crud;
import service.MetodosPokedex;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MetodosPokedex metodosPokedex = new MetodosPokedex();
        Crud crud = new Crud();
        List<Pokedex> listaPokedex = metodosPokedex.getPokemonsList();

        /*
         Con objetos, excepto readPokemons(), que no encontré metodos en la clase para hacerlo
         */

        //crud.insertPokemons(listaPokedex);
        crud.readPokemons();


    }


}
