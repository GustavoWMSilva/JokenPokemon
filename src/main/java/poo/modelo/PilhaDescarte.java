package poo.modelo;

import java.util.Stack;

public class PilhaDescarte{
    private Stack<Card> pilhaD;

    public PilhaDescarte(){
        this.pilhaD = new Stack<Card>();
    }

    public Card getDescarted(){
        return this.pilhaD.pop();
    }

    public void descartar(Card descarted){
        this.pilhaD.push(descarted);
    }
}
