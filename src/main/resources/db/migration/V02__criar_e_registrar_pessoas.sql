CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(30),
    numero VARCHAR(10),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(10),
    cidade VARCHAR(30),
    estado VARCHAR(2),
    ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Matheus Pereira da Silva', 'Rua São Pedro', '436', null, 'Girilandia', '62.940-000', 'Morada Nova', 'CE', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Elyka Oliveira Saboia', 'Sítio Mutamba II', '56', 'Quixelô', 'Boa Água', '62.940-000', 'Morada Nova', 'CE', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Pedro José Saboia', 'Sítio Mutamba II', '56', 'Quixelô', 'Boa Água', '62.940-000', 'Morada Nova', 'CE', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Esau Batista de Oliveira', 'Rua Cipriano Maia', '15', 'Centro', null, '62.940-000', 'Morada Nova', 'CE', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Ana Brenna Girão de Oliveira', 'Rua Cipriano Maia', '15', 'Centro', null, '62.940-000', 'Morada Nova', 'CE', true);