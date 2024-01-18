package poo.modelo;

import java.util.Random;

public class EquipeRocket extends Treinador{
    private Game game = Game.getGame();
    public EquipeRocket(String anId, String anImageId, int val) {
        super(anId, anImageId, val);
    }

    @Override
    public void habilidade(){
        Random rand = new Random();
        CardDeck maoAdv = game.getDeckAdv();
        CardDeck maoEu = game.getDeckEu();
        int pos = rand.nextInt(maoAdv.getNumberOfCards());
        Pokemon roubada = maoAdv.getCardAt(pos);
        maoAdv.removeCardAt(pos);
        maoEu.addDeck(roubada);
    }
}
