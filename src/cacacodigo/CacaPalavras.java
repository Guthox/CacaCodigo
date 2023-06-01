package cacacodigo;

import java.util.ArrayList;

public class CacaPalavras {
    
    private char grid[][];
    private int tamanho;
    private boolean ocupado[][];
    private String palavrasAdicionadas[];
    private int qtdPalavras;

    private Respostas respostas;
    
    public CacaPalavras(int tamanho){
        setTamanho(tamanho);
        setGrid();
        setOcupado();
        setPalavrasAdicioanadas(5);
        setQtdPalavras(0);
        
        respostas = new Respostas();
    }

    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    public int getTamanho(){
        return tamanho;
    }

    // Cria o tabuleiro com letras aleatorias
    public void setGrid(){
        int size = getTamanho();
        grid = new char[size][size];
        char alfabeto[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                grid[i][j] = alfabeto[(int)(Math.random() * 26)];
            }
        }
    }
    // **************************************


    public void setGrid(int x, int y, char caracter){
        grid[x][y] = caracter;
        setOcupado(x, y, true);
    }
    public char[][] getGrid(){
        return grid;
    }
    public char getGrid(int i, int j){
        return grid[i][j];
    }

    // Printa o tabuleiro inteiro, usado apenas para debug *******
    public void showGrid(){
        String gridCompleto = "";
        for (int i = 0; i < getTamanho(); i++){
            for (int j = 0; j < getTamanho(); j++){
                gridCompleto += getGrid(i, j) + " ";
            }
            gridCompleto += "\n";
        }
        System.out.print(gridCompleto);
    }
    // **************************************************************

    public void setOcupado(){
        int size = getTamanho();
        ocupado = new boolean[size][size];
        for (int i = 0; i < getTamanho(); i++){
            for (int j = 0; j < getTamanho(); j++){
                ocupado[i][j] = false;
            }
        }
    }
    public void setOcupado(int i, int j, boolean ocupado) {
        this.ocupado[i][j] = ocupado;
    }
    public boolean[][] getOcupado(){
        return ocupado;
    }
    public boolean getOcupado(int i, int j){
        return ocupado[i][j];
    }

    // Adiciona uma palavra na horizontal ou vertical *********************
    public void addPalavra(String palavra){
        int tamanhoPalavra = palavra.length();
        boolean adicionado = false;
        int tentativas = 0;
        // enquanto nao conseguir adicionar, tenta novamente até 100 vezes
        while (adicionado == false && tentativas < 100){
            tentativas++;
            int x = (int) (Math.random() * getTamanho()); // Pos inicial de x
            int y = (int) (Math.random() * getTamanho()); // pos inicial de y
            double orientacao = Math.random(); // Se vai ser horizontal ou vertical
            boolean vazio = false;
            // se horizontal, verifica se cabe a palavra e se não existem outras palavras no local *********
            if (orientacao <= 0.5 && palavra.length() <= getTamanho() && x + palavra.length() < getTamanho()){
                vazio = true;
                for (int i = 0; i < tamanhoPalavra; i++){
                    if (getOcupado(x+i, y) == true){
                        vazio = false;
                        break;
                    }
                }
            }
            // ************************************************
            // se Vertical, verifica se cabe a palavra e se não existem outras palavras no local *********
            else{
                if (orientacao > 0.5 && palavra.length() <= getTamanho() && y + palavra.length() < getTamanho()){
                    vazio = true;
                    for (int i = 0; i < tamanhoPalavra; i++){
                        if (getOcupado(x, y+i) == true){
                            vazio = false;
                            break;
                        }
                    }
                }
            }
            // ************************************************************
            // se a palavra cabe, adiciona a palavra *********************
            if (vazio == true){ // horizontal
                if (orientacao <= 0.5){
                    for (int i = 0; i < palavra.length(); i++){
                        setGrid(x+i, y, palavra.charAt(i));
                        respostas.adicionarPos(y, x+i);
                    }
                    respostas.incrementarIndex();
                    adicionado = true;

                }else{ // vertical 
                    if (orientacao > 0.5){
                        for (int i = 0; i < palavra.length(); i++){
                            setGrid(x, y+i, palavra.charAt(i));
                            respostas.adicionarPos(y+i, x);
                        }
                        respostas.incrementarIndex();
                        adicionado = true;
                    }
                }
                setPalavrasAdicioanadas(palavra);
            }
        }
    }
    // ******************************************************************

    public void setPalavrasAdicioanadas(int quantas){
        palavrasAdicionadas = new String[quantas];
    }
    public void setPalavrasAdicioanadas(String palavras){
        palavrasAdicionadas[getQtdPalavras()] = palavras;
        incrementaQtdPalavras();
    }

    public String getPalavrasAdicionadas(){
        String palavras = "";
        for (int i = 0; i < getQtdPalavras(); i++){
            palavras += palavrasAdicionadas[i] + " ";
        }
        return palavras;
    }

        
    public void setQtdPalavras(int qtd){
        qtdPalavras = qtd;
    }
    public int getQtdPalavras(){
        return qtdPalavras;
    }
    public void incrementaQtdPalavras(){
        setQtdPalavras(getQtdPalavras() + 1);
    }

    public String getOcupadoString(){
        String res = "";
        for (int i = 0; i <  getTamanho(); i++){
            for (int j = 0; j < getTamanho(); j++){
                res += getOcupado(i, j) + "\t";
            }
            res += "\n";
        }
        return res;
    }

    // Pega as respostas ********************

    public ArrayList<Cursor> pegarResosta(int n){
        return respostas.getArrayResposta(n);
    }

    // Retorna as Perguntas ******************
    public String[] retornarPerguntas(){
        return respostas.retornarPerguntas();
    }


}
