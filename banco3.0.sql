-- Criação do banco de dados
DROP DATABASE IF EXISTS projeto_final_db;
CREATE DATABASE projeto_final_db;
USE projeto_final_db;

-- Tabela de usuários
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(70) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(14),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    acesso VARCHAR(30) DEFAULT 'cliente',
    INDEX (email),
    INDEX (cpf)
);

CREATE TABLE funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    salario DECIMAL(10,2),
    cargo VARCHAR(100),
    foto longblob,
    data_contratacao DATE,
    data_demissao DATE,
    id_usuario int,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela de endereços
CREATE TABLE endereco (
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(10),
    rua VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(100),
    pais VARCHAR(100),
    bairro VARCHAR(100),
    complemento VARCHAR(100),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    INDEX (id_usuario)
);




-- Tabela de categorias
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);




-- Tabela de produtos
CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor DECIMAL(10,2) NOT NULL,
    imagem LONGBLOB,
    id_categoria INT NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Tabela de subcategorias
INSERT INTO categoria (nome) VALUES ('Bolos'), ('Salgados'), ('Doces'), ('Camisetas');

-- Tabela de estoque
CREATE TABLE estoque (
    id_estoque INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    preco_custo DECIMAL(10,2) NOT NULL,
    data_comprado DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_produto INT NOT NULL, 
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);
SELECT p.*, e.quantidade, e.preco_custo FROM produto p INNER JOIN estoque e ON p.id_Produto = e.id_produto;
-- Tabela de formas de pagamento
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(100)
);

-- Tabela de pedidos
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_forma_pagamento int,
    id_cliente INT NOT NULL,
    data_pedido DATETIME NOT NULL,
    status ENUM('pendente', 'processando', 'enviado', 'entregue', 'cancelado') NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY  (id_cliente) REFERENCES usuario(id_usuario),
    FOREIGN KEY  (id_forma_pagamento) REFERENCES forma_pagamento(id_forma_pagamento)
);

-- Inserir pagamento via Pix
INSERT INTO forma_pagamento (nome, descricao) 
VALUES ('Pix', 'Pagamento realizado através da plataforma Pix.');

-- Inserir pagamento com cartão de crédito
INSERT INTO forma_pagamento (nome, descricao) 
VALUES ('Cartão de Crédito', 'Pagamento realizado através de cartão de crédito.');

-- Inserir pagamento com cartão de débito
INSERT INTO forma_pagamento (nome, descricao) 
VALUES ('Cartão de Débito', 'Pagamento realizado através de cartão de débito.');
    
CREATE TABLE sabor (
    id_sabor INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT,
    nome VARCHAR(60),
	descricao varchar(255),
    valorAdicional DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'disponivel',
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

INSERT INTO sabor (id_categoria, nome, valorAdicional) VALUES (2, 'Frango com Catupiry', 3.00);
INSERT INTO sabor (id_categoria, nome, valorAdicional) VALUES (2, 'Bolinha de Queijo', 2.00);
INSERT INTO sabor (id_categoria, nome, valorAdicional) VALUES (3, 'Cereja', 1.00);

CREATE TABLE cobertura (
    id_cobertura INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60),
    valorAdicional DECIMAL(10,2) NOT NULL,
	descricao varchar(255)
);

INSERT INTO cobertura (nome, valorAdicional) VALUES ('Ganache de Chocolate', 3.00);
INSERT INTO cobertura (nome, valorAdicional) VALUES ('Brigadeiro', 4.00);
INSERT INTO cobertura (nome, valorAdicional) VALUES ('Chantilly', 5.00);

CREATE TABLE topper (
    id_topper INT AUTO_INCREMENT PRIMARY KEY,
    valorAdicional DECIMAL(10,2) NOT NULL,
    nome VARCHAR(60),
    descricao varchar(255)
);

INSERT INTO topper (nome, valorAdicional) VALUES ('Flores comestíveis', 4.00);
INSERT INTO topper (nome, valorAdicional) VALUES ('Bandeirinhas', 3.00);
INSERT INTO topper (nome, valorAdicional) VALUES ('Personagens de papel', 2.00);

CREATE TABLE recheio (
    id_recheio INT AUTO_INCREMENT PRIMARY KEY,
    valorAdicional DECIMAL(10,2) NOT NULL,
    nome VARCHAR(60),
     descricao varchar(255)
);

INSERT INTO recheio (nome, valorAdicional) VALUES ('Doce de leite', 4.00);
INSERT INTO recheio (nome, valorAdicional) VALUES ('Coco com ameixa', 3.00);
INSERT INTO recheio (nome, valorAdicional) VALUES ('Nutella', 2.00);

CREATE TABLE massa (
    id_massa INT AUTO_INCREMENT PRIMARY KEY,
    valorAdicional DECIMAL(10,2) NOT NULL,
    nome VARCHAR(60),
     descricao varchar(255)
);

INSERT INTO massa (nome, valorAdicional) VALUES ('Pão de ló', 4.00);
INSERT INTO massa (nome, valorAdicional) VALUES ('Chiffon', 3.00);
INSERT INTO massa (nome, valorAdicional) VALUES ('Red Velvet', 2.00);

    CREATE TABLE produto_carrinho(
		id_produto_carrinho INT AUTO_INCREMENT PRIMARY KEY,
        id_usuario int not null,
        id_produto INT NOT NULL,
        id_massa INT,
        id_recheio INT,
        id_topper INT,
        id_cobertura INT,
        valorAdicional DECIMAL(10,2) NOT NULL,
        quantidade DECIMAL(10,2)  NOT NULL,
        FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
        FOREIGN KEY (id_massa) REFERENCES massa(id_massa),
        FOREIGN KEY (id_recheio) REFERENCES recheio(id_recheio),
        FOREIGN KEY (id_topper) REFERENCES topper(id_topper),
        FOREIGN KEY (id_cobertura) REFERENCES cobertura(id_cobertura)
    );
    
    CREATE TABLE produto_carrinho_sabores(
		id_produto_carrinho_sabores INT AUTO_INCREMENT PRIMARY KEY,
        id_produto_carrinho INT NOT NULL,
        id_sabor INT NOT NULL,
        FOREIGN KEY (id_produto_carrinho) REFERENCES produto_carrinho(id_produto_carrinho),
        FOREIGN KEY (id_sabor) REFERENCES sabor(id_sabor)
    );
    
      -- Tabela de carrinho
    CREATE TABLE carrinho (
        id_carrinho INT AUTO_INCREMENT PRIMARY KEY,
        id_usuario INT NOT NULL,
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    );



-- Tabela de pedidos de pagamento
CREATE TABLE pedido_pagamento (
    id_pedido_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_forma_pagamento INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id_forma_pagamento)
);

-- Tabela de produtos em pedidos
CREATE TABLE produto_pedido (
    id_produto_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE avisos(
	id_avisos INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    titulo varchar(100),
	data_aviso DATETIME DEFAULT CURRENT_TIMESTAMP
);

create table empresa(
	id_empresa int auto_increment primary key,
	precoVendido decimal(10,2),
	custoVendido decimal(10,2),
	dataVendido DATETIME DEFAULT CURRENT_TIMESTAMP,
	produto int,
	foreign key (produto) references produto(id_produto)
);

CREATE TABLE despesasEmpresa(
	id_despesas INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
	valor DECIMAL (10,2),
    data DATETIME DEFAULT CURRENT_TIMESTAMP
);


-- Criação da tabela de produtos na wishlist
CREATE TABLE wishlist_produto (
    id_wishlist_produto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_produto INT NOT NULL,
    data_adicionado DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);


CREATE TABLE auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    tabela_alterada VARCHAR(100),
    operacao VARCHAR(10),
    dados_antigos TEXT,
    dados_novos TEXT,
    usuario_id INT,
    data_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

    
    

DELIMITER //
CREATE TRIGGER before_usuario_update
BEFORE UPDATE ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (tabela_alterada, operacao, dados_antigos, dados_novos, usuario_id)
    VALUES ('usuario', 'UPDATE', OLD.email, NEW.email, NEW.id_usuario);
END;
//
DELIMITER ;


-- Trigger para inserção de produto no estoque
DELIMITER //
CREATE TRIGGER after_produto_insert AFTER INSERT ON produto FOR EACH ROW
BEGIN
    INSERT INTO estoque (quantidade, preco_custo, id_produto)
    VALUES (0, 0, NEW.id_produto);
END;
//
DELIMITER ;

-- Trigger para inserção de carrinho de acordo com o usuario
DELIMITER //

CREATE TRIGGER after_usuario_insert
AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO carrinho (id_usuario) VALUES (NEW.id_usuario);
END;
//

DELIMITER ;



INSERT INTO usuario (nome, email, senha, telefone, cpf, acesso) 
VALUES ('Administrador', 'administrador@gmail.com', 'adm', '5551999999999', '12345678901', 'administrador');
INSERT INTO usuario (nome, email, senha, telefone, cpf) 
VALUES ('a', 'a@gmail.com', 'a', '5551999199999', '12345678301');

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';

