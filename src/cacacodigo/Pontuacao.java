package cacacodigo;

public class Pontuacao {
 
    private String usuario;
    private int pontos;

    public Pontuacao(String usuario){
        setPontos(0);
        setUsuario(usuario);
    }

    // GET E SET **************************
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public String getPontosString(){
        return "" + getPontos();
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
    
    
    // Aumenta a pontuação em 200 ****************
    public void aumentarPontos(){
        setPontos(getPontos() + 200);
    }

    public void somarBonus(int bonus){
        setPontos(getPontos() + bonus);
    }

   
    
}
