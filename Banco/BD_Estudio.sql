
CREATE DATABASE bd_estudio;

USE bd_estudio;

#------------------------------------------------------------------------------------------------------------
#Tabela Login
CREATE TABLE tbl_login (
  id_login INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  CPF CHAR(11) NOT NULL,
  login VARCHAR(100) NOT NULL,
  senha VARCHAR(16) NOT NULL,
  admin BOOLEAN DEFAULT '0', # 0 = Comum, 1 = Administrador
  ativo BOOLEAN DEFAULT '0', # 0 = Ativo, 1 = Inativo
  PRIMARY KEY (id_login),
  UNIQUE INDEX login (login ASC), #Permite que o campo Login não se repita
  UNIQUE INDEX cpf (cpf ASC)); #Permite que o campo CPF não se repita
#-----------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Cliente
CREATE TABLE tbl_cliente (
  id_cli INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  CPF CHAR(11) NOT NULL,
  sexo BOOLEAN DEFAULT 0, # 0 = Masculino, 1 = Feminino
  data_nasc DATE NOT NULL,
  tel VARCHAR(20) NOT NULL,
  estado VARCHAR(30),
  cidade VARCHAR(30),
  bairro VARCHAR(30),
  fk_id_login INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (id_cli),
  UNIQUE INDEX cpf (cpf ASC), #Permite que o campo CPF não se repita

  INDEX idx_fk_cliente_login (fk_id_login ASC),

  CONSTRAINT fk_tbl_cliente_login
    FOREIGN KEY (fk_id_login) #Criação da chave estrangeira
    REFERENCES tbl_login (id_login)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION); #Permite que as chaves sejam excluidas e atualizadas
#------------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Representante
CREATE TABLE tbl_representante (
  id_representante INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  CPF CHAR(11) NOT NULL,
  sexo BOOLEAN DEFAULT 0, # 0 = masculino, 1 = feminino
  data_nasc DATE NOT NULL,
  tel VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_representante),
  UNIQUE INDEX cpf (cpf ASC)); #Permite que o campo CPF não se repita
#------------------------------------------------------------------------------------------------------------



#------------------------------------------------------------------------------------------------------------
#Tabela Grau de Parentesco
CREATE TABLE tbl_parentesco(
id_parentesco INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
tipo_parentesco VARCHAR(20) NOT NULL,
PRIMARY KEY(id_parentesco));
#------------------------------------------------------------------------------------------------------------



#------------------------------------------------------------------------------------------------------------
#Tabela responsável por relacionar a tabela Cliente, Responsável e Parentesco
CREATE TABLE tbl_rel (
  id_rel INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_id_cli INT(10) UNSIGNED NOT NULL, #Campo que recebe a FK da tabela cliente
  fk_id_parentesco INT(10) UNSIGNED NOT NULL, #Campo que recebe a FK da tabela Parentesco
  fk_id_representante INT(10) UNSIGNED NOT NULL, #Campo que recebe a FK da tabela Responsável
  PRIMARY KEY(id_rel),

  INDEX idx_fk_rel_cliente (fk_id_cli ASC),
  INDEX idx_fk_rel_parentesco (fk_id_parentesco ASC),
  INDEX idx_fk_rel_representante (fk_id_representante ASC),
  
 CONSTRAINT fk_tbl_rel_cliente
    FOREIGN KEY (fk_id_cli) #Criação da chave estrangeira para a tabela Cliente
    REFERENCES bd_estudio.tbl_cliente (id_cli)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION, #Permite que as chaves sejam excluidas e atualizadas
 
  CONSTRAINT fk_tbl_rel_parentesco
    FOREIGN KEY (fk_id_parentesco) #Criação da chave estrangeira da tabela Parentesco
    REFERENCES bd_estudio.tbl_parentesco (id_parentesco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

  CONSTRAINT fk_tbl_rel_representante
    FOREIGN KEY (fk_id_representante) #Criação da chave estrangeira da tabela Responsável
    REFERENCES bd_estudio.tbl_representante (id_representante)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
#------------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Orçamento
CREATE TABLE tbl_orcamento (
  cod_orc INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  criado_em DATETIME NULL DEFAULT CURRENT_TIMESTAMP, #Busca a hora atual do sistema
  tipo_pagamento VARCHAR(30) NOT NULL, #Cartão, dinheiro ou Não especificado
  valor_total DECIMAL(10,2) NOT NULL,
  qntd_sessao INT(10) NOT NULL,
  fk_id_cli_orc INT(10) UNSIGNED NOT NULL, #Criação do campo da chave estrangeira da tabela Cliente
  PRIMARY KEY (cod_orc),

  INDEX idx_fk_orcamento_cliente (fk_id_cli_orc ASC),

  CONSTRAINT fk_tbl_orcamento_cliente
    FOREIGN KEY (fk_id_cli_orc) #Criação da chave estrangeira
    REFERENCES tbl_cliente (id_cli)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION); #Permite que as chaves sejam excluidas e atualizadas
#------------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Sessão
CREATE TABLE tbl_sessao (
  id_sessao INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  concluida boolean NULL DEFAULT 0, #0 não concluida, 1 concluida
  valor_sessao DECIMAL(10,2) NOT NULL,
  tipo_pagamento VARCHAR(30) NOT NULL, #Cartão, dinheiro ou Não especificado
  data_agendada DATE NOT NULL,
  hora_agendada TIME NOT NULL,
  desconto DECIMAL(10,2) NULL DEFAULT '0.00',
  fk_cod_orc INT(10) UNSIGNED NOT NULL, #Chave estrangeira tabela Orçamento
  PRIMARY KEY (id_sessao),

  INDEX idx_fk_sessao_orcamento (fk_cod_orc ASC),
  CONSTRAINT fk_tbl_sessao_orcamento
    FOREIGN KEY (fk_cod_orc) #Criando a Chave Estrangeira
    REFERENCES tbl_orcamento (cod_orc)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION); #Permite que as chaves sejam excluidas e atualizadas
#-----------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Orçamento Concluidos
CREATE TABLE tbl_orccon (
  cod_orccon INT(10) NOT NULL,
  criado_em DATETIME NULL DEFAULT CURRENT_TIMESTAMP, #Busca a hora atual do sistema
  tipo_pagamento VARCHAR(30) NOT NULL, #Cartão, dinheiro ou Não especificado
  valor_total DECIMAL(10,2) NOT NULL,
  qntd_sessao INT(10) NOT NULL,
  dt_ins TIMESTAMP DEFAULT NOW(),
  fk_id_cli_orccon INT(10) UNSIGNED NOT NULL, #Criação do campo da chave estrangeira da tabela Cliente
  PRIMARY KEY (cod_orccon),

  INDEX idx_fk_orccon_cliente (fk_id_cli_orccon ASC),

  CONSTRAINT fk_tbl_orccon_cliente
    FOREIGN KEY (fk_id_cli_orccon) #Criação da chave estrangeira
    REFERENCES tbl_cliente (id_cli)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION); #Permite que as chaves sejam excluidas e atualizadas
#------------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------
#Tabela Sessões Concluídas
CREATE TABLE tbl_sescon (
  id_sescon INT(10) NOT NULL,
  concluida BOOLEAN NULL DEFAULT 0, #0 não concluida, 1 concluida
  valor_sessao DECIMAL(10,2) NOT NULL,
  tipo_pagamento VARCHAR(30) NOT NULL, #Cartão, dinheiro ou Não especificado
  data_agendada DATE NOT NULL,
  hora_agendada TIME NOT NULL,
  desconto DECIMAL(10,2) NULL DEFAULT '0.00',
  dt_ins TIMESTAMP DEFAULT NOW(),
  fk_cod_orccon INT(10), #Chave estrangeira tabela Orçamento
  PRIMARY KEY (id_sescon),

  INDEX idx_fk_sescon_orccon (fk_cod_orccon ASC),

  CONSTRAINT fk_tbl_sescon_orccon
    FOREIGN KEY (fk_cod_orccon) #Criando a Chave Estrangeira
    REFERENCES tbl_orccon (cod_orccon)
    ON DELETE NO ACTION #Permite que as chaves sejam excluidas e atualizadas
    ON UPDATE NO ACTION); #Permite que as chaves sejam excluidas e atualizadas
#-----------------------------------------------------------------------------------------------------------

