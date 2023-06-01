package cacacodigo;

import java.util.ArrayList;

public class Escolhas {

    private ArrayList<Cursor> posicoes;
    private int quantidade;

    private int acertos;
    
    public Escolhas(){
        posicoes = new ArrayList<>();
        quantidade = 0;
        setAcertos(0);
    }

    // Adiciona posições no ArrayList ***********************
    public void adicionarPos(int x, int y){
        Cursor c = new Cursor(x, y);
        posicoes.add(c);
        atualizarQuantidade();
    }

    public void adicionarPos(Cursor cursor){
        posicoes.add(cursor);
        atualizarQuantidade();
    }

    public void adicionarPosSafe(int x, int y){
        if (checarPos(x, y) == false){
            Cursor c = new Cursor(x, y);
            posicoes.add(c);
            atualizarQuantidade();
        }

    }
    // *****************************************************

    // Checa se existe essa posicao no ArrayList **********
    public boolean checarPos(int x, int y){
        Cursor c = new Cursor(x, y);
        return posicoes.contains(c); 
    }

    public boolean checarPos(Cursor c){
        return posicoes.contains(c); 
    }

    // ****************************************************

    // Remove item do ArrayList
    public void removerPos(int x, int y){
        int index = acharPos(x, y);
        if (index > -1){
            posicoes.remove(index);
        }
        atualizarQuantidade();
    }
    // *****************************************************

    // Acha a posição do item no ArrayList. Retorna -1 se não achar
    public int acharPos(int x, int y){
        Cursor c = new Cursor(x, y);
        for (int i = 0; i < posicoes.size(); i++){
            if (posicoes.get(i).equals(c)){
                return i;
            }
        }
        return -1;
    }

    public int acharPos(Cursor cursor){
        for (int i = 0; i < posicoes.size(); i++){
            if (posicoes.get(i).equals(cursor)){
                return i;
            }
        }
        return -1;
    }

    // *****************************************************

    // Retorna o Cursor escolhido **************************
    public Cursor retornarCursor(int x, int y){
        int index = acharPos(x, y);
        if (index > -1){
            return posicoes.get(index);
        }
        else{
            return null;
        }
    }

    public Cursor retornarCursor(Cursor cursor){
        int index = acharPos(cursor);
        if (index > -1){
            return posicoes.get(index);
        }
        else{
            return null;
        }
    }

    // *****************************************************

    // Atualiza a quantidade *******************************

    public void atualizarQuantidade(){
        setQuantidade(posicoes.size());
    }

    // *****************************************************

    // Retorna o ArrayList *********************************
    public ArrayList<Cursor> getPosicoes(){
        return posicoes;
    }

    // GET e SET *******************************************
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getAcertos() {
        return acertos;
    }
    // ******************************************************
    // Limpa o ArrayList ************************************
    public void resetar(){
        posicoes = new ArrayList<>();
        atualizarQuantidade();
    }

    // incrementa os acertos quando achar uma palavra *******
    public void incrementarAcertos(){
        setAcertos(getAcertos() +1 );
    }
    // ********************************************************

}
