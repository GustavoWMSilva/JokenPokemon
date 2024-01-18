package poo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class CardDeck {
	public static final int NCARDS = 5;
	private List<Pokemon> cartas;
	private Pokemon selected;
	private List<GameListener> observers;
	public Treinador treinador; 

	
	
	
	Pokemon bulbasaur = new Pokemon("C" + 10, "img"+10, 10, Tipo.PLANTA,0);
	Pokemon charmander = new Pokemon("C" + 20, "img"+20, 20, Tipo.FOGO,0);
	Pokemon squirtle = new Pokemon("C" + 30, "img"+30, 30, Tipo.AGUA,0);
	Curinga snorlax = new Snorlax("C" + 50, "img"+50, 50, Tipo.CURINGA,0);
	Curinga ditto = new Ditto("C" +40, "img"+40,40, Tipo.CURINGA, 0);

	public CardDeck() {
		cartas = new ArrayList<>(6);
		selected = null;
		observers = new LinkedList<>();
		selected = null;
		
		cartas.add(bulbasaur);
		cartas.add(charmander);
		cartas.add(squirtle);
		cartas.add(ditto);

		observers = new LinkedList<>();
	}



	public List<Pokemon> getCards() {
		return Collections.unmodifiableList(cartas);
	}

	public void addDeck(Pokemon pokemon){
		cartas.add(pokemon);
	}

	public int getNumberOfCards() {
		return cartas.size();
	}

	public void removeSel() {
		if (selected == null) {
			return;
		}
		cartas.remove(selected);
		selected = null;
		GameEvent gameEvent = new GameEvent(this, GameEvent.Target.DECK, GameEvent.Action.REMOVESEL, "");
		for (var observer : observers) {
			observer.notify(gameEvent);
		}
	}

	public void addCard(Pokemon Pokemon) {
		System.out.println("add: "+ Pokemon);
		cartas.add(Pokemon);
		GameEvent gameEvent = new GameEvent(this, GameEvent.Target.DECK, GameEvent.Action.SHOWTABLE, "");
		for (var observer : observers) {
			observer.notify(gameEvent);
		}
	}

	public void setSelectedCard(Pokemon pokemon) {
		selected = pokemon;
	}

	public Pokemon getSelectedCard() {
		return selected;
	}

	public Pokemon getPokemon(){
		return cartas.get(0);
	}

	public void addGameListener(GameListener listener) {
		observers.add(listener);
	}

	public void clear(){
		cartas.clear();
	}

	public Pokemon getCardAt(int i){
		return cartas.get(i);
	}

	public int getIndexOfCard(Card c){
		for(int i = 0; i < this.getNumberOfCards(); i++){
			if(this.getCardAt(i).getId() == c.getId()){
				return i;
			}
		}
		return -1;
	}

	public void removeCardAt(int i){
		cartas.remove(i);
	}



	public Treinador getTreinador(){
		return treinador;
	}

	public void setTreinador(Treinador trainer){
		this.treinador = trainer;
	}

	public void removeFainted(){
		for(Card p : cartas){
			Pokemon pokemon = (Pokemon) p;
			if(p instanceof Pokemon){
				if(pokemon.getEvolucao() < 0){
					p = null;
				}
			}
		}
	}
}
