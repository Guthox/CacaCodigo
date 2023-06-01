package cacacodigo;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
 
    private int minutos;
    private int segundos;
    private Timer timer;

    public Cronometro(){
        setMinutos(0);
        setSegundos(0);
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    // inicia o Cronometro *********************************************
    public void iniciar() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new AtualizarCronometro(), 0, 1000);
    }

    // Aumenta os segundos e eventualmente os minutos do cronometro ***
    public class AtualizarCronometro extends TimerTask {
        public void run() {
            segundos++;

            if (segundos == 60) {
                segundos = 0;
                minutos++;
            }
            Janela.AtualizarCronometroLB(getTempo());
        }
    }
    // *****************************************************************

    // Pega o tempo em String *******************************************
    public String getMinutosString(){
        int minutos = getMinutos();
        if (minutos < 10){
            return "0" + minutos;
        }
        else{
            return "" + minutos;
        }
    }

    public String getSegundoString(){
        int segundos = getSegundos();
        if (segundos < 10){
            return "0" + segundos;
        }
        else{
            return "" + segundos;
        }
    }

    public String getTempo(){
        return getMinutosString() + ":" + getSegundoString();
    }

    // Retorna tempo em segundos para poder calcular bonus

    public int segundosTotal(){
        return ((getMinutos() * 60) + getSegundos());
    }

    // para o cronometro ************************************

    public void parar(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
