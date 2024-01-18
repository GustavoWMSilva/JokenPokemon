package poo.modelo;

public class Pokemon extends Card{
    
    private int imgCount = 0;
    private Tipo tipo = null;
    private int evolucao = 0;
   // private int imageId = 0;
    private int maxEvo = 0;

    public Pokemon(String anId, String anImageId, int val, Tipo tipo, int evolucao){
        super(anId, anImageId, val);
        this.tipo = tipo; //escolhe(val);
        this.evolucao = evolucao;
        this.imgCount = val;
    }

    
    public Tipo getTipo(){
        return this.tipo;
    }

    public void setTipo(Tipo t){
        this.tipo = t;
    }

    public int getEvolucao(){
        return this.evolucao;
    }

    public void setEvolucao(int newEvo){
        this.evolucao = newEvo;
    }

    public void evoluir(){
        
        if(getEvolucao() < 2){
            setEvolucao(getEvolucao()+1);
            setValue(getValue() + 1);
            setImageId( "img" + getValue());
        }
       // if(maxEvo < 2){
       //     this.evolucao++;
       //     this.maxEvo++;
       //     this.imageId++;
       //     this.setImageId(this.imageId);
       // }
    }
    public void involuir(){
        // setValue(getValue() - 1);
       //this.evolucao--;
       setEvolucao(getEvolucao()-1);
       // this.maxEvo--;
       // this.imageId--;
       // this.setImageId(this.imageId);
       // if(this.evolucao < 0){
       //     this.descartar();
       // }
      setValue((getValue()-1));
            setImageId( "img" + getValue());
        
    }
    public boolean descartar(){
        if(evolucao < 0)
        {
            return true;
        }
        return false;
    }

    
    public Pokemon getCard(){
        return this;
    }

    public void setCard(Pokemon p){
        this.setId(p.getId());
        this.setImageId(p.getImageId());
        this.setValue(p.getValue());
        this.setTipo(p.getTipo());
        this.setEvolucao(p.getEvolucao());
    }
    
    public void batalha(Pokemon pokemon){
        Tipo tipop = pokemon.getTipo();
        int evolucaop = pokemon.getEvolucao();

        // SE O POKEMON FOR DE FOGO
        if(this.tipo == Tipo.FOGO){
            if(tipop == Tipo.PLANTA){ // VENCE PARA QUALQUER PLANTA
                this.evoluir();
                pokemon.involuir();
            }else if(tipop == Tipo.AGUA){  // PERDE PARA QUALQUER AGUA
                pokemon.evoluir();
                this.involuir();
            }else if (tipop == Tipo.FOGO){ 
                if(this.evolucao > evolucaop){ // VENCE COM DO MESMO TIPO E EVOLUCAO MENOR
                    this.evoluir();
                    pokemon.involuir();
                }else if(this.evolucao < evolucaop){                         // PERDE COM DO MESMO TIPO E EVOLUCAO MAIOR
                    pokemon.evoluir();
                    this.involuir();
                }
            }
        }

        // SE O POKEMON FOR DE AGUA
        else if(this.tipo == Tipo.AGUA){
            if(tipop == Tipo.FOGO){     // VENCE PARA QUALQUER FOGO
                this.evoluir();
                pokemon.involuir();
            }else if(tipop == Tipo.PLANTA){  // PERDE PARA QUALQUER PLANTA
                pokemon.evoluir();
                this.involuir();
            }else if(tipop == Tipo.AGUA){ 
                if(this.evolucao > evolucaop){ // VENCE COM DO MESMO TIPO E EVOLUCAO MENOR
                    this.evoluir();
                    pokemon.involuir();
                }else if(this.evolucao < evolucaop){                         // PERDE COM DO MESMO TIPO E EVOLUCAO MAIOR
                    pokemon.evoluir();
                    this.involuir();
                }
            }
        }

        // SE O POKEMON FOR DE PLANTA
        else if(this.tipo == Tipo.PLANTA){
            if(tipop == Tipo.AGUA){     // VENCE PARA QUALQUER FOGO
                this.evoluir();
                pokemon.involuir();
            }else if(tipop == Tipo.FOGO){  // PERDE PARA QUALQUER PLANTA
                pokemon.evoluir();
                this.involuir();
            }else if(tipop == Tipo.PLANTA){ 
                if(this.evolucao > evolucaop){ // VENCE COM DO MESMO TIPO E EVOLUCAO MENOR
                    this.evoluir();
                    pokemon.involuir();
                }else if(this.evolucao < evolucaop){                         // PERDE COM DO MESMO TIPO E EVOLUCAO MAIOR
                    pokemon.evoluir();
                    this.involuir();
                }
            }
        }
    }
}