package poo.modelo;

public abstract class Curinga extends Pokemon {
    
    //private final Tipo tipo = Tipo.CURINGA;

    public Curinga(String anId, String anImageId, int val, Tipo tipo, int evolucao){
        super(anId, anImageId, val, tipo, evolucao);
    }
    
    public void useHabilidade(){
            habilidade();
    }

    public abstract void habilidade();
}
