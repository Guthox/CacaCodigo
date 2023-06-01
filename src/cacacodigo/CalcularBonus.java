package cacacodigo;

public class CalcularBonus {
    
    private final static int PONTUACAO_MAXIMA = 1000000; // 1 milhoes

    public static int gerarBonus(int tempoEmSeg){
        if (tempoEmSeg > 0){
            return PONTUACAO_MAXIMA / tempoEmSeg;
        }
        else{
            return PONTUACAO_MAXIMA;
        }
    }

    

}
