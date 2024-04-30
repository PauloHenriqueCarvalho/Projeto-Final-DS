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
    acesso ENUM('cliente', 'administrador') DEFAULT 'cliente'
);


INSERT INTO usuario (nome, email, senha, telefone, cpf, acesso) 
VALUES ('Administrador', 'administrador@gmail.com', 'adm', '5551999999999', '12345678901', 'administrador');


-- Tabela de categorias
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela de subcategorias
CREATE TABLE subcategoria (
    id_subcategoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

INSERT INTO categoria (nome) VALUES ('Eletrônicos'), ('Roupas'), ('Livros'), ('Móveis');

-- Inserir dados na tabela de subcategoria
INSERT INTO subcategoria (nome, id_categoria) VALUES 
('Smartphones', 1),
('Laptops', 1),
('Camisetas', 2),
('Calças', 2),
('Ficção', 3),
('Não Ficção', 3),
('Sofás', 4),
('Mesas', 4);
-- Tabela de produtos
CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor DECIMAL(10,2) NOT NULL,
    valorFinal DECIMAL(10,2) NOT NULL,
    desconto DECIMAL(10,2),
    tamanho_camisa VARCHAR(10),
    unidade_medida VARCHAR(20),
    peso DECIMAL(10, 2),
    imagem MEDIUMBLOB,
    id_categoria INT NOT NULL,
    id_subcategoria INT NOT NULL, 
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_subcategoria) REFERENCES subcategoria(id_subcategoria),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Tabela de estoque
CREATE TABLE estoque (
    id_estoque INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    preco_custo DECIMAL(10,2) NOT NULL,
    data_comprado DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_produto INT NOT NULL, 
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-- Tabela de pedidos
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    data_pedido DATETIME NOT NULL,
    status ENUM('pendente', 'processando', 'enviado', 'entregue', 'cancelado') NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id_usuario)
);

-- Tabela de formas de pagamento
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(100)
);

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

create table empresa(
id_empresa int auto_increment primary key,
precoVendido decimal(10,2),
custoVendido decimal(10,2),
dataVendido DATETIME DEFAULT CURRENT_TIMESTAMP,
produto int,
foreign key (produto) references produto(id_produto)
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

