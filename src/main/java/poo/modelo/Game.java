package poo.modelo;

import java.util.LinkedList;
import java.util.List;

import poo.gui.GameWindow;

//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class Game {
	private static Game game = new Game();
	private int ptsJ1, ptsJ2;
	private CardDeck deckJ1, deckJ2;
	private CardDeck mesaJ1, mesaJ2;
	private int player;
	private int jogadas;
	private List<GameListener> observers;
	private Pokemon saveJ1;
	private Pokemon saveJ2;
	private boolean seleciona;
	private boolean umaVez;
	
	public static Game getGame() {
		return game;
	}

	private Game() {
		saveJ1 = new Pokemon(null, null, player, null, jogadas);
		saveJ2 = new Pokemon(null, null, player, null, jogadas);
		ptsJ1 = 0;
		ptsJ2 = 0;
		deckJ1 = new CardDeck();
		deckJ2 = new CardDeck();
		mesaJ1 = new CardDeck();
		mesaJ2 = new CardDeck();
		player = 1;
		seleciona = false;
		umaVez = false;
		jogadas = CardDeck.NCARDS;
		observers = new LinkedList<>();
		mesaJ1.clear();
		mesaJ2.clear();
	}

	private void nextPlayer() {
		
		player++;
		if (player == 4) {
			player = 1;
			
		}
	}

	public int getPlayer(){
		return player;
	}

	public Pokemon getCarta(int player)
	{
		if(player == 1)
			return saveJ1;
		else if (player == 2)
			return saveJ2;
		return null;
	}

	public CardDeck getDeck(){
		if(player == 1)
			return deckJ1;
		else if (player == 2)
			return deckJ2;
		return null;
	}
	
	public void getRetorne(){
	// retornar a carta do campo
		if(umaVez == false){
		getDeck().addCard(getCarta(player));
		umaVez = true;
	}
	}

	public void nextBotao(boolean botao){
		if(botao == false){

		}
	}
	public CardDeck getDeckAdv() {
		if(player == 1){
			return deckJ1;
		}
		return deckJ2;
	}

	public CardDeck getDeckEu() {
		if(player == 1){
			return deckJ2;
		}
		return deckJ1;
	}

	public int getPtsJ1() {
		return deckJ1.getNumberOfCards();
	}

	public int getPtsJ2() {
		return deckJ2.getNumberOfCards();
	}

	public CardDeck getDeckJ1() {
		return deckJ1;
	}

	public CardDeck getDeckJ2() {
		return deckJ2;
	}

	public CardDeck getMesaJ1() {
		return mesaJ1;
	}

	public CardDeck getMesaJ2() {
		return mesaJ2;
	}

	public void selecionado(boolean m){	
		this.seleciona = m;
		if(seleciona == true){
			seleciona = false;
			getCarta(player).flip();
			getDeck().setSelectedCard(getCarta(player));
			// Proximo jogador
			nextPlayer();}
	}

	public void play(CardDeck deckAcionado){
		GameEvent gameEvent = null;
		if (player == 3) {
			gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.MUSTCLEAN, "");
			for (var observer : observers) {
				observer.notify(gameEvent);
			}
			return;
		}
		if (deckAcionado == deckJ1){
			if (player != 1) {	
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				for (var observer : observers) {
					observer.notify(gameEvent);
				}
			} else{
				deckJ1.getSelectedCard();
				this.saveJ1 = deckJ1.getSelectedCard();	
		}
		} else if (deckAcionado == deckJ2) {
			if (player != 2) {
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				for (var observer : observers) {
					observer.notify(gameEvent);
				}
			}else{
				deckJ2.getSelectedCard();
				this.saveJ2 = deckJ2.getSelectedCard();
			}
			}
		}
	

	// Acionada pelo botao de limpar
	public void removeSelected() {
		mesaJ1.clear();
		mesaJ2.clear();
		GameEvent gameEvent = null;
		
			deckJ1.getNumberOfCards();
			deckJ2.getNumberOfCards();
			
		
		
		for (var observer : observers) {
			observer.notify(gameEvent);
		}

		if (player != 3) {
			return;
		}
	
		if (deckJ2.getNumberOfCards() == 0 || deckJ1.getNumberOfCards() == 0) {
            gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.ENDGAME, "");
            for (var observer : observers) {
                observer.notify(gameEvent);
            }
		}

		//public Card getDescarted(){
		//	return cardeck.getDescarte()
		//}
		
		
		mesaJ1.addCard( deckJ1.getSelectedCard() );
		mesaJ1.getPokemon().flip();
		this.saveJ1 = deckJ1.getSelectedCard();
		//deckJ1.removeSel();
		
		mesaJ2.addCard( deckJ2.getSelectedCard() );
		mesaJ2.getPokemon().flip();
		this.saveJ2 = deckJ2.getSelectedCard(); // salvar a carta na mesa
		//deckJ2.removeSel();
		
		nextPlayer();
	}

	public void novoTurno(){
		mesaJ1.clear();
		mesaJ2.clear();
		
		if(deckJ2.getSelectedCard().getTipo() == Tipo.CURINGA){
			deckJ2.getSelectedCard().batalha(deckJ1.getSelectedCard());
		}else{
			deckJ1.getSelectedCard().batalha(deckJ2.getSelectedCard());
		}

		if(deckJ1.getSelectedCard().descartar()){
			deckJ1.removeSel();
		}
		if(deckJ2.getSelectedCard().descartar()){
			deckJ2.removeSel();
		}
	
		GameWindow.atualizaMao1();
		GameWindow.atualizaMao2();

		deckJ1.getCardAt(deckJ1.getIndexOfCard(deckJ1.getSelectedCard())).setCard(this.saveJ1);
		deckJ2.getCardAt(deckJ2.getIndexOfCard(deckJ2.getSelectedCard())).setCard(this.saveJ2);
	
		mesaJ1.addCard( deckJ1.getSelectedCard() );
		//mesaJ1.getPokemon().flip();
		this.saveJ1 = deckJ1.getSelectedCard();

		mesaJ2.addCard( deckJ2.getSelectedCard() );
		//mesaJ2.getPokemon().flip();
		this.saveJ2 = deckJ2.getSelectedCard();
		
		nextBotao(false);
	}
	
	public void addGameListener(GameListener listener) {
		observers.add(listener);
	}
}
