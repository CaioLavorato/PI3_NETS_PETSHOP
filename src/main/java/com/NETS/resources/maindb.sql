
CREATE DATABASE IF NOT EXISTS PetShop_Nets;

USE pet_shop_nets;

CREATE TABLE Categoria (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL,
    descricao VARCHAR (255)
);

CREATE TABLE Produto (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_categoria INTEGER NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria (id),
    nomeProduto VARCHAR (100) NOT NULL,
    valor VARCHAR (20) NOT NULL,
    descricao VARCHAR (255),
    removido BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Endereco (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    rua VARCHAR (100) NOT NULL,
    bairro VARCHAR (100) NOT NULL,
    estado VARCHAR (100) NOT NULL,
    cidade VARCHAR (100) NOT NULL,
    numero VARCHAR (100) NOT NULL,
    cep VARCHAR (100) NOT NULL
);


CREATE TABLE Filial (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome_fantasia VARCHAR (100) NOT NULL,
    cnpj VARCHAR (20) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    id_endereco INTEGER NOT NULL,
    removido BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id)
);

CREATE TABLE Produto_Filial (
    PRIMARY KEY(id_filial, id_produto),
    estoque INTEGER NOT NULL DEFAULT 0,
    id_filial INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto (id),
    FOREIGN KEY (id_filial) REFERENCES Filial (id)
);

CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL,
    sobrenome VARCHAR (100) NOT NULL,
    sexo VARCHAR (100) NOT NULL,
    funcao VARCHAR (100) NOT NULL,
    dt_admissao DATE NOT NULL,
    removido BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Cliente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_endereco INTEGER NOT NULL,
    nome VARCHAR (100) NOT NULL,
    sobrenome VARCHAR (100) NOT NULL,
    sexo VARCHAR (100) NOT NULL,
    cpf VARCHAR (20) NOT NULL,
    dt_nascimento DATE NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id),
    removido BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Venda (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_usuario INTEGER NOT NULL,
    id_filial INTEGER NOT NULL,
    id_cliente INTEGER,
    data_venda DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario (id),
    FOREIGN KEY (id_filial) REFERENCES Filial (id),
    FOREIGN KEY (id_cliente) REFERENCES Cliente (id)
);

CREATE TABLE Item_Venda(
    item_venda_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    valor_unitario FLOAT NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES Produto (id),
    FOREIGN KEY (id_venda) REFERENCES Venda (id)
    );



DELIMITER //

CREATE TRIGGER insert_produto_filial_after_produto_insert
AFTER INSERT
   ON Produto FOR EACH ROW
BEGIN
   -- Insert record into audit table
   INSERT INTO Produto_Filial
   ( id_filial,
     id_produto)
   SELECT id, NEW.id from filial;
END; //

DELIMITER ;

DELIMITER //

CREATE TRIGGER insert_produto_filial_after_filial_insert
AFTER INSERT
   ON filial FOR EACH ROW
BEGIN
   -- Insert record into audit table
   INSERT INTO Produto_Filial
   ( id_filial,
     id_produto)
   SELECT NEW.id, id from produto;
END; //

DELIMITER ;