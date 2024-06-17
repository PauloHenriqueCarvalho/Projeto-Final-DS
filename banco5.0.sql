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
    numero INT,
    id_usuario INT,
    endereco_padrao BOOL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    INDEX (id_usuario)
);

-- Tabela de categorias
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    status bool default true
);

-- Tabela de produtos
CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor DECIMAL(10,2) NOT NULL,
    id_categoria INT NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    preco_custo DECIMAL(10,2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    status VARCHAR(200) DEFAULT 'disponivel',
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE
);

-- Tabela de imagens dos produtos
CREATE TABLE produto_imagem (
    id_produto_imagem INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    imagemPadrao BOOL DEFAULT FALSE,
    imagem LONGBLOB,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);

-- Tabela de formas de pagamento
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(100)
);

-- Tabela de pedidos
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_forma_pagamento INT,
    id_cliente INT NOT NULL,
    frete DECIMAL(10,2),
    data_entrega DATETIME NOT NULL,
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pendente', 'processando', 'enviado', 'entregue', 'cancelado') NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_endereco int,
    FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id_forma_pagamento)
);

-- Tabela de sabores dos produtos
CREATE TABLE sabor (
    id_sabor INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    nome VARCHAR(60),
    idPai INT DEFAULT NULL,
    descricao VARCHAR(255),
    valorAdicional DECIMAL(10,2),
    status VARCHAR(50) DEFAULT 'disponivel',
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);

-- Tabela de produtos no carrinho
CREATE TABLE produto_carrinho (
    id_produto_carrinho INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_produto INT NOT NULL,
    valorAdicional DECIMAL(10,2) NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabela de sabores no carrinho
CREATE TABLE produto_carrinho_sabores (
    id_produto_carrinho_sabores INT AUTO_INCREMENT PRIMARY KEY,
    id_produto_carrinho INT NOT NULL,
    id_sabor INT NOT NULL,
    FOREIGN KEY (id_produto_carrinho) REFERENCES produto_carrinho(id_produto_carrinho) ON DELETE CASCADE,
    FOREIGN KEY (id_sabor) REFERENCES sabor(id_sabor) ON DELETE CASCADE
);

-- Tabela de produtos em pedidos
CREATE TABLE produto_pedido (
    id_produto_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    valorAdicional DECIMAL(10,2) NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);

-- Tabela de avisos
CREATE TABLE avisos (
    id_avisos INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    titulo VARCHAR(100),
    data_aviso DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de despesas da empresa
CREATE TABLE despesasEmpresa (
    id_despesas INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    valor DECIMAL(10,2),
    data DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de produtos na wishlist
CREATE TABLE wishlist_produto (
    id_wishlist_produto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_produto INT NOT NULL,
    data_adicionado DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);

-- Tabela de auditoria
CREATE TABLE auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    tabela_alterada VARCHAR(100),
    operacao VARCHAR(10),
    dados_antigos TEXT,
    dados_novos TEXT,
    usuario_id INT,
    data_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Triggers
DELIMITER //
CREATE TRIGGER before_usuario_update
BEFORE UPDATE ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (tabela_alterada, operacao, dados_antigos, dados_novos, usuario_id)
    VALUES ('usuario', 'UPDATE', OLD.email, NEW.email, NEW.id_usuario);
END;
//
DELIMITER //

DELIMITER //
CREATE TRIGGER before_produto_insert
BEFORE INSERT ON produto
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (tabela_alterada, operacao, dados_antigos, dados_novos, usuario_id)
    VALUES (
        'produto', 
        'INSERT', 
        NULL, 
        CONCAT('nome: ', NEW.nome, ', descricao: ', NEW.descricao, ', valor: ', NEW.valor, ', id_categoria: ', NEW.id_categoria, ', preco_custo: ', NEW.preco_custo, ', quantidadeEstoque: ', NEW.quantidade_estoque), 
        NEW.id_produto
    );
END;
//
DELIMITER //

-- Inserts de exemplo

INSERT INTO categoria (nome) VALUES ('Bolos'), ('Salgados'), ('Doces');

INSERT INTO usuario (nome, email, senha, telefone, cpf, acesso) 
VALUES ('Administrador', 'administrador@gmail.com', 'adm', '5551999999999', '12345678901', 'administrador');
INSERT INTO usuario (nome, email, senha, telefone, cpf) 
VALUES ('a', 'a@gmail.com', 'a', '5551999199999', '12345678301');

-- Inserir formas de pagamento
INSERT INTO forma_pagamento (nome, descricao) 
VALUES ('Pix', 'Pagamento realizado através da plataforma Pix.'),
       ('Cartão de Crédito', 'Pagamento realizado através de cartão de crédito.'),
       ('Cartão de Débito', 'Pagamento realizado através de cartão de débito.');

-- Produto de exemplo
INSERT INTO produto (nome, descricao, valor, id_categoria, preco_custo, quantidade_estoque)
VALUES ('Brigadeiro', 'Doce de chocolate tradicional brasileiro', 5.99, 3, 2.50, 100);


