package cacacodigo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class Janela extends JFrame implements ActionListener{
    
    private final int TAMANHO = 15;
    private JButton[][] btLetra = new JButton[TAMANHO][TAMANHO];
    private CacaPalavras cp;

    private JTextArea taPerguntas;

    private JLabel lbPontos;
    private JLabel lbPontuacao;

    private Escolhas escolhas;

    private Pontuacao pontuacao;

    private Cronometro cronometro;
    private static JLabel lbTempo;
    
    public Janela(String usuario){
        super("Caça-Código");

        escolhas = new Escolhas();

        // Gerando o Caça Palavras **********************************

        cp = new CacaPalavras(TAMANHO);
        cp.setGrid();

        //String palavras[] = {"INT", "FLOAT", "CHAR", "STRING", "COMPILADOR"};
        //String perguntas[] = {"Como se declara um tipo inteiro?", "Como se declara um tipo decimal de menor precissão?", "Como declara um caracter?", "Como declara um texto?", "Nome do programa responsavel em transformar o codigo em linguagem de maquina"};
        
        String perguntas[] = new String[5];
        String respostas[] = new String[5];

        Banco bd = new Banco(ConnFactory.conectar());

        String perguntasRespostas[][] = bd.pegarPerguntas();

        ConnFactory.desconectar(bd.getConn());

        for (int i = 0; i < 5; i++){
            perguntas[i] = perguntasRespostas[0][i];
            respostas[i] = perguntasRespostas[1][i];
        }

        for (int i = 0; i < respostas.length; i++){
            cp.addPalavra(respostas[i]);
        }

        // **********************************************************
        // Layout da janela *****************************************

        Container caixa = getContentPane();
        caixa.setLayout(null);

        // deixei o plano de fundo preto
        caixa.setBackground(Color.BLACK);

        JPanel grid = new JPanel(new GridLayout(TAMANHO, TAMANHO));

        // Gerando os botões com as letras **************************

        for (int i = 0; i < TAMANHO; i++){
            for (int j = 0; j < TAMANHO; j++){
                btLetra[i][j] = new JButton("" + cp.getGrid(i, j));
                btLetra[i][j].addActionListener(this);
                grid.add(btLetra[i][j]);
                // coloquei uma corzinha ne fi kk *******************
                btLetra[i][j].setFont(new java.awt.Font("Arial Black", 0, 12));
                btLetra[i][j].setForeground(new Color(255, 255, 255));
                btLetra[i][j].setBackground(new Color(27, 30, 35));
            }
        }
        
        // Gerando a parte das perguntas ****************************

        taPerguntas = new JTextArea(10,50);
        taPerguntas.setBounds(730, 100, 600, 600);
        //coloquei uma borda branca chique ********
        taPerguntas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        // fundo preto ne pra fica chique ***********
        taPerguntas.setBackground(new java.awt.Color(27, 30, 35));
        // fonte branca ***********
        taPerguntas.setForeground(new java.awt.Color(255, 255, 255));

        Font fonte = new Font("Arial", Font.BOLD, 22);
        taPerguntas.setFont(fonte);
        taPerguntas.setLineWrap(true);
        taPerguntas.setWrapStyleWord(true);

        for (int i = 0; i < perguntas.length; i++){
            taPerguntas.setText(taPerguntas.getText() + "\n\n" + (i+1) + ") " + perguntas[i]);         
        }

        taPerguntas.setEditable(false);

        caixa.add(taPerguntas);

        // Criando a parte da Pontuação *****************************

        pontuacao = new Pontuacao(usuario);

        Font fontePontos = new Font("Arial", Font.BOLD, 34);
        lbPontos = new JLabel(pontuacao.getPontosString());
        lbPontos.setFont(fontePontos);
        // cor dos ponto branca ************
        lbPontos.setForeground(new java.awt.Color(255, 255, 255));

        lbPontuacao = new JLabel("Pontuação: ");

        lbPontuacao.setBounds(730, 40, 70, 30);
        lbPontos.setBounds(810, 40, 100, 30);
        // branco tbm ********
        lbPontuacao.setForeground(new java.awt.Color(255, 255, 255));

        caixa.add(lbPontuacao);
        caixa.add(lbPontos);

        // Cronometro *********************************************

        cronometro = new Cronometro();

        lbTempo = new JLabel("00:00");

        lbTempo.setFont(fonte);
        lbTempo.setBounds(1250, 40, 100, 30);
        caixa.add(lbTempo);
        lbTempo.setForeground(new java.awt.Color(255, 255, 255));

        cronometro.iniciar();

        // **********************************************************
        
        grid.setBounds(10, 10, 700, 700);
        caixa.add(grid);

        // Opções da janela *****************************************

        setSize(1366,768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // **********************************************************

    }

    // Evento dos botões ********************************************

    public void actionPerformed(ActionEvent evento){
        // i é o Y. j é o X.
        for (int i = 0; i < TAMANHO; i++){
            for (int j = 0; j < TAMANHO; j++){
                if (evento.getSource() == btLetra[i][j]){
                    // Se for o primeiro botao a ser clicado **********
                    if (escolhas.getQuantidade() == 0){
                        escolhas.adicionarPos(j, i);
                        btLetra[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                    // Se já houver outros botoes clicados ***************
                    else{
                        // Se tiver clicado, desclica ********************
                        if (escolhas.checarPos(j, i)){
                            escolhas.removerPos(j, i);
                            btLetra[i][j].setBackground(new Color(27, 30, 35));
                        // ********************************************
                        }
                        // Se não tiver clicado, clica ********************
                        else{
                            escolhas.adicionarPos(j, i);
                            btLetra[i][j].setBackground(Color.LIGHT_GRAY);
                        }
                        // ***********************************************

                    }
                    // se acertou, pinta os botoes de verde **************
                    for (int m = 1; m <= 5; m++){
                        if (verificarResposta(escolhas.getPosicoes(), m)){
                            ArrayList<Cursor> acertou = escolhas.getPosicoes();
                            for (int k = acertou.size() - 1; k > -1; k--){
                                btLetra[acertou.get(k).getY()][acertou.get(k).getX()].setEnabled(false);
                                btLetra[acertou.get(k).getY()][acertou.get(k).getX()].setBackground(Color.GREEN);
                            }
                            escolhas.resetar();
                            pontuacao.aumentarPontos();
                            atualizarPontuacao();
                            escolhas.incrementarAcertos();
                        }
                    }
                    // Se achou todas as palavras, calcula o bonus e mostra o placar ***********
                    if (escolhas.getAcertos() == 5){
                        cronometro.parar();
                        int segundos = cronometro.segundosTotal();
                        int bonus = CalcularBonus.gerarBonus(segundos);
                        pontuacao.somarBonus(bonus);
                        atualizarPontuacao();
                        // Salva pontuacao no banco de dados
                        Banco bd = new Banco(ConnFactory.conectar());
                        if (pontuacao.getPontos() > bd.pegarPontuacao(pontuacao.getUsuario())){
                            bd.atualizarPontuacao(pontuacao.getUsuario(), pontuacao.getPontos(), cronometro.getTempo());
                        }
                        String dadosPlacar[][] = bd.gerarPlacar();
                        ConnFactory.desconectar(bd.getConn());
                        // MOSTRA PLACAR *******************************************************
                        dispose();
                        TelaScore placar = new TelaScore(dadosPlacar, pontuacao.getUsuario());
                    }
                }
            }
        }
    }

    // ********************************************************************

    // Verifica se a resposta está correta. ****************************
    public boolean verificarResposta(ArrayList<Cursor> escolhas, int j){
        ArrayList<Cursor> resposta1 = cp.pegarResosta(j);
        if (escolhas.size() != resposta1.size()){
            return false;
        }
        for (int i = 0; i < resposta1.size(); i++){
            if (resposta1.get(i).equals(escolhas.get(i)) == false){
                return false;
            }
        }
        return true;
    }

    // *********************************************************************

    public void atualizarPontuacao(){
        lbPontos.setText(pontuacao.getPontosString());
    }
    
    // *********************************************************************

    public static void AtualizarCronometroLB(String tempo){
        lbTempo.setText(tempo);
    }

    // *********************************************************************



}
