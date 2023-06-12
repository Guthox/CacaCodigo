CREATE DATABASE bdCacaCodigo;

USE bdCacaCodigo;

CREATE TABLE Perguntas(
idPergunta INT AUTO_INCREMENT PRIMARY KEY,
Pergunta VARCHAR(150) NOT NULL,
Resposta VARCHAR(15) NOT NULL
);

INSERT INTO Perguntas (Pergunta, Resposta) VALUES 
("Como se declara uma variavel com valor inteiro?", "INT"),
("Qual o tipo de uma variavel que possua um texto?", "STRING"),
("Qual o tipo de uma variavel que possua um único caractere?", "CHAR"),
("Como se declara uma variavel que possua um valor decimal com menos precisão?", "FLOAT"),
("Como se declara uma variavel que possua um valor decimal com mais precisão?", "DOUBLE"),
("Qual é a excessão de uma estrutura de decisão?", "ELSE"),
("Qual é o sistema de numeração composto por apenas dois símbolos, utilizado na computação?", "BINARIO"),
("Qual a palavra que se usa quando se quer representar uma herança em Java?", "EXTENDS"),
("Na programação, qual o nome dado para estruturas de repetição?", "LOOP"),
("Qual o nome da restrição que impede repetições em banco de dados?", "UNIQUE"),
("Qual é o método utilizado para executar uma consulta em um banco de dados SQL?", "SELECT"),
("Qual é o operador lógico utilizado para verificar se duas condições são verdadeiras ao mesmo tempo?", "AND"),
("Qual é o nome dado a uma variável que referencia uma instância de uma classe em programação orientada a objetos?", "OBJETO"),
("Qual é o tipo de dado utilizado para armazenar verdadeiro ou falso?", "BOOLEAN"),
("O que significa o operador !=?", "DIFERENTE"),
("Qual é o termo utilizado para descrever o processo de ocultar a implementação interna de um objeto?", "ENCAPSULAMENTO"),
("Qual é o tipo de dado utilizado para armazenar sequências de caracteres em um banco de dados SQL?", "VARCHAR"),
("Qual é o comando usado para criar uma tabela em um banco de dados SQL?", "CREATE"),
("Qual é o comando usado para excluir registros em um banco de dados SQL?", "DELETE"),
("Qual é o comando usado para inserir registros em um banco de dados SQL?", "INSERT"),
("Qual é o nome do comando usado para retornar um valor de uma função em Java?", "RETURN");

SELECT * FROM Perguntas;

CREATE TABLE Usuarios(
Usuario VARCHAR(20) UNIQUE PRIMARY KEY,
Senha VARCHAR(64) NOT NULL,
Adm BOOl NOT NULL
);

CREATE TABLE Pontuacoes(
idPontuacao INT AUTO_INCREMENT PRIMARY KEY,
Usuario VARCHAR(20) UNIQUE NOT NULL,
Pontuacao INT NOT NULL,
Tempo CHAR(5) NOT NULL,
CONSTRAINT FK_Usuario FOREIGN KEY (Usuario) REFERENCES Usuarios (Usuario)
);

SELECT * FROM Usuarios;

SELECT * FROM Pontuacoes;

-- UPDATE Pontuacoes SET Pontuacao = 2000, Tempo = "10:00" WHERE Usuario = usuario;
-- UPDATE Pontuacoes SET Pontuacao = 1000 WHERE Usuario = "aaaaa"; 

-- SELECT Usuario, Pontuacao, Tempo FROM Pontuacoes ORDER BY Pontuacao DESC LIMIT 100;

-- SET SQL_SAFE_UPDATES = 0;
-- DELETE FROM Usuarios;
-- DELETE FROM Pontuacoes;
-- delete from perguntas;

-- drop table usuarios;
-- drop table pontuacoes;

-- select * from Usuarios;
-- SELECT Adm FROM Usuarios WHERE Usuario = "usuario";

-- SELECT COUNT(*) FROM Usuarios WHERE Usuario = "usuario" AND Senha = SHA2("senha", 256);

-- drop database bdCacaCodigo;