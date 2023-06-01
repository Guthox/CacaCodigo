package cacacodigo;

import java.util.ArrayList;

public class Respostas {
    
    // Cada ArrayList guarda as Posições das letras nas respostas 
    private ArrayList<Cursor> resposta1;
    private ArrayList<Cursor> resposta2;
    private ArrayList<Cursor> resposta3;
    private ArrayList<Cursor> resposta4;
    private ArrayList<Cursor> resposta5;

    private String perguntas[];
    private int index;

    public Respostas(){
        resposta1 = new ArrayList<>();
        resposta2 = new ArrayList<>();
        resposta3 = new ArrayList<>();
        resposta4 = new ArrayList<>();
        resposta5 = new ArrayList<>();
        setIndex(1);

        perguntas = new String[5];
    }

    // Adiciona pos de uma letra da resposta ****************
    public void adicionarPos(int x, int y){
        Cursor c = new Cursor(x, y);
        switch (getIndex()){
            case 1: 
                resposta1.add(c);
                break;
            case 2:
                resposta2.add(c);
                break;
            case 3:
                resposta3.add(c);
                break;
            case 4:
                resposta4.add(c);
                break;
            case 5:
                resposta5.add(c);
                break;
        }
        
    }

    // ******************************************************

    // Retorna o ArrayList selecionado ***********************
    public ArrayList<Cursor> getArrayResposta(int n){
        switch(n){
            case 1:
                return resposta1;
            case 2:
                return resposta2;
            case 3:
                return resposta3;
            case 4:
                return resposta4;
            case 5:
                return resposta5;
            default:
                return null;
        }
    }
    // ******************************************************

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public void incrementarIndex(){
        setIndex(getIndex() + 1);
    }

    // adiciona uma pergunta *******************************
    public void adicionaPergunta(String pergunta){
        if ((getIndex()-1) >= 0 && (getIndex()-1) <=4){
            perguntas[getIndex() - 1] = pergunta; 
        }
    }

    // Retorna as perguntas ***********************************
    public String[] retornarPerguntas(){
        return perguntas;
    }

}
