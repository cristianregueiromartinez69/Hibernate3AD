import models.Pokedex;
import service.Crud;
import service.MetodosPokedex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MetodosPokedex metodosPokedex = new MetodosPokedex();
        Crud crud = new Crud();
        List<Pokedex> listaPokedex = metodosPokedex.getPokemonsList();

        /*
         Con objetos, excepto readPokemons() y deletePokedex(), que no encontré metodos en la clase para hacerlo
         */



        crud.insertPokemons(listaPokedex);
        crud.readPokemons();
        crud.updatePokemons(1, "nidoking", BigDecimal.valueOf(62.0), "cornudo");
        crud.updatePokemons(2, "vaporeon", BigDecimal.valueOf(29.0), "sireno");
        crud.readPokemons();
        crud.deletePokedex();


        /*
          Con querys
         */

        crud.insertPokemons(listaPokedex);
        crud.readPokemons();
        crud.updatePokemonsCOnQuery(1,"pikachu", BigDecimal.valueOf(6.0), "rata muy hepatitica");
        crud.updatePokemonsCOnQuery(2,"gengar", BigDecimal.valueOf(40.5), "risitas jejeje");
        crud.readPokemons();
        crud.deletePokedex();



    }


}
