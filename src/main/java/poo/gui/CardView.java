package poo.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
import poo.modelo.Pokemon;
import poo.modelo.ImageFactory;

public class CardView extends Button implements PropertyChangeListener{
	private Pokemon Pokemon;
	private CardView thisCardView;
	private CardViewListener observer;

	public CardView(Pokemon aCard) {
		super("", ImageFactory.getInstance().createImage("imgBck"));

		if (aCard.isFacedUp())
		   this.setGraphic(ImageFactory.getInstance().createImage(aCard.getImageId()));
		
		Pokemon = aCard;
		Pokemon.addPropertyChangeListener(this);
		thisCardView = this;

		this.setOnAction(e -> {
			if (observer != null) {
				observer.handle(new CardViewEvent(thisCardView));
			}
		});
	}

	public void setCardViewObserver(CardViewListener obs) {
		observer = obs;
	}

	public Pokemon getCard() {
		return Pokemon;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (Pokemon.isFacedUp()) {
			this.setGraphic(ImageFactory.getInstance().createImage(Pokemon.getImageId()));
		} else {
			this.setGraphic(ImageFactory.getInstance().createImage("imgBck"));
		}		
	}
}
