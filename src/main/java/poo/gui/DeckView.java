package poo.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import poo.modelo.Pokemon;
import poo.modelo.CardDeck;
import poo.modelo.Game;
import poo.modelo.GameEvent;
import poo.modelo.GameListener;
//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class DeckView extends HBox implements CardViewListener, GameListener {
	private int jogador;
	private CardDeck cDeck;
	private Pokemon selectedCard;

	public DeckView(int nroJog) {
		super(4);
		this.setAlignment(Pos.CENTER);

		jogador = nroJog;
		selectedCard = null;
		
		int cont = 0;

		cDeck = null;
		if (jogador == 1) {
			cDeck = Game.getGame().getDeckJ1();cont = 3;
		} else if (jogador == 2) {
			cDeck = Game.getGame().getDeckJ2(); cont=3;
		} else if (jogador == -1) {
			cDeck = Game.getGame().getMesaJ1(); cont = 1;
	    } else if (jogador == -2) {
			cDeck = Game.getGame().getMesaJ2(); cont = 1;
		}

		cDeck.addGameListener(this);
		//for(int i = 0; i <= cont; i++){
		//	CardView cv = new CardView(cDeck.getCards().get(i));
		//	cv.setCardViewObserver(this);
		//	this.getChildren().add(cv);
		//}

		for (Pokemon Pokemon : cDeck.getCards()) {
			CardView cv = new CardView(Pokemon);
			cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		}
	}

	private void removeSel() {
		ObservableList<Node> cards = getChildren();
		for (int i = 0; i < cards.size(); i++) {
			CardView cv = (CardView) cards.get(i);
			if (cv.getCard() == selectedCard) {
				getChildren().remove(cv);
				selectedCard = null;
			}
		}
	}

	private void showDeck() {
		//ObservableList<Node> cards = getChildren();
		//cDeck.addGameListener(this);

		this.getChildren().clear();

		System.out.println("m1.len>" + cDeck.getNumberOfCards());
		for (Pokemon Pokemon : cDeck.getCards()) {
			System.out.println("show>" + Pokemon);
			CardView cv = new CardView(Pokemon);
			//cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		}
	}

	@Override
	public void notify(GameEvent event) {
		System.out.println("ev: "+ event);
		if (event.getTarget() != GameEvent.Target.DECK) {
			return;
		}
		if (event.getAction() == GameEvent.Action.REMOVESEL) {
			removeSel();
		}
		if (event.getAction() == GameEvent.Action.SHOWTABLE) {
			showDeck();
		}
	}

	@Override
	public void handle(CardViewEvent event) {
		CardView cv = event.getCardView();
		selectedCard = cv.getCard();
		cDeck.setSelectedCard(selectedCard);
		Game.getGame().play(cDeck);
	}
}
