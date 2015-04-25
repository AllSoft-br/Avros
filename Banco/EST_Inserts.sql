#INSERTS DAS TABELAS

#---------------------------------------------------------------------------------------------------------------
#INSERTS DA TABELA LOGIN
INSERT INTO tbl_login (nome, CPF, login, senha, admin)
VALUES ('Wallace Abreu de Oliveira', '53141191913', 'admin', '123', 1);

INSERT INTO tbl_login (nome, CPF, login, senha)
VALUES ('Eduardo de Souza', '12398765436', 'Edu', '321');

SELECT * FROM tbl_login;
#---------------------------------------------------------------------------------------------------------------


#---------------------------------------------------------------------------------------------------------------
#INSERTS DA TABELA CLIENTE
INSERT INTO tbl_cliente (nome, CPF, data_nasc, tel, fk_id_login)
VALUES ('Douglas Lambertinny', '64837748274', '1994/04/20', '9876-5432', 1);

INSERT INTO tbl_cliente (nome, CPF, sexo, data_nasc, tel, fk_id_login)
VALUES ('Luana Ferreira Nascimento', '37485574758', 1, '1997-07-14', '1234-5678', 1);

SELECT * FROM tbl_cliente;
#------------------------------------------------------------------------------------------------------------


#------------------------------------------------------------------------------------------------------------
#TABELA REPRESENTANTE
INSERT INTO tbl_representante (nome, CPF, sexo,  data_nasc, tel)
VALUES ('Eucrimaria Luiza Ferreira Nascimento', '37474456283', 1, '1960/12/10', '6253-7474'); 

SELECT * FROM tbl_representante;
#------------------------------------------------------------------------------------------------------------


#------------------------------------------------------------------------------------------------------------
#TABELA PARENTESCO
INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('AVÔ');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('AVÓ');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('MADRASTA');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('TIA');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('MÃE');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('PADRASTO');

INSERT INTO tbl_parentesco (tipo_parentesco)
VALUES ('PAI');

SELECT * FROM tbl_parentesco;
#------------------------------------------------------------------------------------------------------------


#------------------------------------------------------------------------------------------------------------
#TABELA RELAÇÃO
INSERT INTO tbl_rel (fk_id_cli, fk_id_parentesco, fk_id_representante)
VALUES (2, 2, 1);

SELECT * FROM tbl_rel;
#------------------------------------------------------------------------------------------------------------


#------------------------------------------------------------------------------------------------------------
#TABELA ORCAMENTO
insert into tbl_orcamento(tipo_pagamento, valor_total, qntd_sessao, fk_id_cli_orc)
values ('cartão', 1090.98, 6, 1);

select * from tbl_orcamento;
#------------------------------------------------------------------------------------------------------------


#------------------------------------------------------------------------------------------------------------
#TABELA SESSÃO
INSERT INTO tbl_sessao(concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orc)
VALUES ('0', 100.00, 'cartão', '2015-06-15', '15:00', 0, 1);

INSERT INTO tbl_sessao(concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orc)
VALUES ('0', 100.00, 'cartão', '2015-06-25', '13:00', 0, 1);

INSERT INTO tbl_sessao(concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orc)
VALUES ('0', 100.00, 'cartão', '2015-07-10', '15:00', 0, 1);

INSERT INTO tbl_sessao(concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orc)
VALUES ('1', 100.00, 'cartão', '2015-07-30', '15:00', 0, 1);


SELECT * FROM tbl_sessao;
#------------------------------------------------------------------------------------------------------------