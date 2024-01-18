package poo.modelo;

public abstract class Treinador extends Card{
    private int usos = 0;

    public Treinador(String anId, String anImageId, int val) {
        super(anId, anImageId, val);
        this.usos = 1;
    }

    public void useHabilidade(){
        if(usos > 0){
            habilidade();
            this.usos--;
        }
    }

    public void setUsos(int novoUsos){
        this.usos = novoUsos;
    }

    public abstract void habilidade();
}
