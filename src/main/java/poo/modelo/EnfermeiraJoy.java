package poo.modelo;

public class EnfermeiraJoy extends Treinador{
    private Game game = Game.getGame();
    public EnfermeiraJoy(String anId, String anImageId, int val) {
        super(anId, anImageId, val);
    }

    public void habilidade(){
       // game.getDeckJ1().addCard(game.getDescarte().getDescarted());
    }
}
