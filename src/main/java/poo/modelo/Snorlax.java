package poo.modelo;

public class Snorlax extends Curinga{
    private boolean dormindo;
    Game game = Game.getGame();

    public Snorlax(String anId, String anImageId, int val, Tipo tipo, int evolucao){
        super(anId, anImageId, val, tipo, evolucao);
        dormindo = true;
    }

    public boolean getdormindo(){
        return dormindo;
    }

    public void habilidade(){
        dormindo = !dormindo;
    }

    public Snorlax getThis(){
		return this;
	}

    public void batalha(Pokemon pokemon){
        if(this.getdormindo()){
            pokemon.evoluir();
            this.habilidade();
        }else{
            pokemon.involuir();
            this.involuir();
            this.involuir();
            this.descartar();
        }
    }
}


