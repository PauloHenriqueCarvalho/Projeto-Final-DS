-- Criação do banco de dados
drop DATABASE projeto_final_db;
CREATE DATABASE projeto_final_db;
USE projeto_final_db;

-- Tabela de usuários
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(70) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    telefone VARCHAR(14),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    acesso varchar(30) DEFAULT 'cliente'
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

CREATE TABLE endereco (
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(100),
    rua VARCHAR(100),
	cidade VARCHAR(100),
	pais VARCHAR(100),
	estado VARCHAR(100),
    bairro VARCHAR(100),
    complemento VARCHAR(100)
);


INSERT INTO usuario (nome, email, senha, telefone, cpf, acesso) 
VALUES ('Administrador', 'administrador@gmail.com', 'adm', '5551999999999', '12345678901', 'administrador');


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
INSERT INTO produto (nome, valor, id_categoria, descricao) VALUES ('batata', '12', '1','asdada');

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
-- Tabela de pedidos
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_funcionario INT NOT NULL,
    data_pedido DATETIME NOT NULL,
    status ENUM('pendente', 'processando', 'enviado', 'entregue', 'cancelado') NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY  (id_cliente) REFERENCES usuario(id_usuario),
	FOREIGN KEY  (id_funcionario) REFERENCES funcionario(id_funcionario)
);

-- Tabela de formas de pagamento
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(100)
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


-- Tabela de pedidos de pagamento
CREATE TABLE pedido_pagamento (
    id_pedido_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_forma_pagamento INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id_forma_pagamento)
);

-- Tabela de carrinho
CREATE TABLE carrinho (
    id_carrinho INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela de produtos no carrinho
CREATE TABLE carrinho_produto (
    id_carrinho_produto INT AUTO_INCREMENT PRIMARY KEY,
    id_carrinho INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_carrinho) REFERENCES carrinho(id_carrinho),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
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


ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';

