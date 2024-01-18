package poo.modelo;

public class Ditto extends Curinga{
    
    public Ditto(String anId, String anImageId, int val, Tipo tipo, int evolucao){
        super(anId, anImageId, val, tipo, evolucao);
    }

    public void habilidade(){
    }

    
    public void batalha(Pokemon p){
        this.setImageId(p.getImageId());
        this.setValue(p.getValue());
        this.setTipo(p.getTipo());
        this.setEvolucao(p.getEvolucao());
    }
}
