package cacacodigo;

import  java.sql.Connection;
import  java.sql.PreparedStatement;
import  java.sql.ResultSet;
import  java.sql.Statement;
import  java.util.ArrayList;

public class Banco {
    
    private Statement stm;
    private Connection conn;

    public Banco(Connection conn){
        setConn(conn);
    }

    // Busca 5 perguntas aleatorias no banco de dados *****************************************
    public String[][] pegarPerguntas(){
        String comando = "SELECT * FROM Perguntas ORDER BY RAND() LIMIT 5";
        
        String resultado[][] = new String[2][5];
    

        try{
            stm = getConn().createStatement();
            ResultSet result = stm.executeQuery(comando);
            int i = 0;
            while (result.next()){
                resultado[0][i] = result.getString(2); // Perguntas
                resultado[1][i] = result.getString(3); // Respostas
                i++;
            }
            return resultado;
        }
        catch (Exception erro){
            return null;
        }

    }


    // *****************************************************************************************

    // Inserir Pergunta e resposta no banco de dados ******************************************
    public boolean inserirPergunta(String pergunta, String resposta){
        String sqlInsert = "INSERT INTO Perguntas (Pergunta, Resposta) VALUES (?, ?)";
        try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
            stm.setString(1, pergunta);
            stm.setString(2, resposta);
            stm.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    // Cadastrar Usuario ***********************************************************************
    
    // Inserir usuario no banco de dados ******************************************
    public boolean cadastrarUsuario(String usuario, String senha){
        String sqlInsert = "INSERT INTO Usuarios (Usuario, Senha) VALUES (?, SHA2(?, 256))";
        try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
            stm.setString(1, usuario);
            stm.setString(2, senha);
            stm.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    // *****************************************************************************************

    // Login do usuario. Verifica se o usuario e senha digitados estao no banco de dados
    public boolean logarUsuario(String usuario, String senha){
    String comando = "SELECT COUNT(*) FROM Usuarios WHERE Usuario = ? AND Senha = SHA2(?, 256)";
    
       try {
            PreparedStatement stmt = getConn().prepareStatement(comando); 
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retorna true se houver uma correspondência de usuário e senha
                }
            }
        } catch (Exception e) {
           return false;
        }
       return false;
    }
    // *****************************************************************************************
    
    // Verificar nome de usuario ***************************************************************
    
    public boolean verificarUsuario(String usuario){
        String comando = "SELECT COUNT(*) FROM Usuarios WHERE Usuario = ?";
    
       try {
            PreparedStatement stmt = getConn().prepareStatement(comando); 
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retorna true se houver uma correspondência de usuário
                }
            }
        } catch (Exception e) {
           return false;
        }
       return false;
        
    }
    
    // *****************************************************************************************
    
    // Pega a maior pontuacao do usuario para poder comparar com a atual **********************
    public int pegarPontuacao(String usuario){
    
    String comando = "SELECT Pontuacao FROM Pontuacoes WHERE Usuario = ?";
    
       try {
            PreparedStatement stmt = getConn().prepareStatement(comando); 
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int pontos = rs.getInt(1);
                    return pontos; // Retorna true se houver uma correspondência de usuário
                }
            }
        } catch (Exception e) {
           return 0;
        }
       return 0;
    }
       
    // ****************************************************************************************
    
    // Inserir pontuacao do usuario. Melhor assim pq nao precissa conferir se usuario tem placar ou n, sempre vai ter e usa sempre update no banco
    
    public boolean cadastrarPontuacao(String usuario, int pontos, String tempo){
        String sqlInsert = "INSERT INTO Pontuacoes (Usuario, Pontuacao, Tempo) VALUES (?, ?, ?)";
        try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
            stm.setString(1, usuario);
            stm.setInt(2, pontos);
            stm.setString(3, tempo);
            stm.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    // *******************************************************************************
    
    
    // Update da pontuacao no banco de dados ****************************************
    
    public boolean atualizarPontuacao(String usuario, int pontos, String tempo){
        String sqlInsert = "UPDATE Pontuacoes SET Pontuacao = ?, Tempo = ? WHERE Usuario = ?";
        try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
            stm.setInt(1, pontos);
            stm.setString(2, tempo);
            stm.setString(3, usuario);
            stm.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    // *******************************************************************************
    
    // Gera a string para ser usada no placar ****************************************
    
    public String[][] gerarPlacar(){
        String comando = "SELECT Usuario, Pontuacao, Tempo FROM Pontuacoes ORDER BY Pontuacao DESC LIMIT 100";
        String placar[][] = new String[100][4];
        
        try {
            PreparedStatement stmt = getConn().prepareStatement(comando); 

            try (ResultSet rs = stmt.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    placar[i][0] = "#" + (i+1);
                    placar[i][1] = rs.getString(1);
                    placar[i][2] = "" + rs.getInt(2);
                    placar[i][3] = rs.getString(3);
                    i++;
                }
            }
        } catch (Exception e) {
           return null;
        }
       return placar;
        
        
    }
    
    // *******************************************************************************
    
    public String[][] pegarPerguntasTodas(){
        String comando = "SELECT * FROM Perguntas";
        
        ArrayList<String> perguntas = new ArrayList<>();
        ArrayList<String> respostas = new ArrayList<>();
    
        try{
            stm = getConn().createStatement();
            ResultSet result = stm.executeQuery(comando);
            while (result.next()){
                perguntas.add(result.getString(2)); // Perguntas
                respostas.add(result.getString(3)); // Respostas
            }
            String resultado[][] = new String[2][perguntas.size()];
            for (int i = 0; i < perguntas.size(); i++){
                resultado[0][i] = perguntas.get(i);
                resultado[1][i] = respostas.get(i);
            }
            return resultado;
        }
        catch (Exception erro){
            return null;
        }

    }
    
    // *******************************************************************************
        
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

}
