#-------------------------------------------------------------------------------------
#DROPS DO BANCO ESTUDIO
DROP DATABASE bd_estudio;

DROP TABLE tbl_login;

DROP TABLE tbl_cliente;

DROP TABLE tbl_representante;

DROP TABLE tbl_parentesco;

DROP TABLE tbl_rel;

DROP TABLE tbl_orcamento;

DROP TABLE tbl_sessao;

DROP TABLE tbl_orccon;

DROP TABLE tbl_sescon;
#-------------------------------------------------------------------------------------


#-------------------------------------------------------------------------------------
#DELETS DO BANCO ESTUDIO
DELETE FROM tbl_login WHERE id_login = 1;
DELETE FROM tbl_login WHERE id_login <> 0;


DELETE FROM tbl_cliente WHERE id_cli = 1;
DELETE FROM tbl_cliente WHERE id_cli <> 0;


DELETE FROM tbl_representante WHERE id_representante = 1;
DELETE FROM tbl_representante WHERE id_representante <> 0;


DELETE FROM tbl_parentesco WHERE id_parentesco = 1;
DELETE FROM tbl_parentesco WHERE id_parentesco <> 0;


DELETE FROM tbl_rel WHERE id_rel = 1;
DELETE FROM tbl_rel WHERE id_rel <> 0;


DELETE FROM tbl_orcamento WHERE cod_orc = 1;
DELETE FROM tbl_orcamento WHERE cod_orc <> 0;


DELETE FROM tbl_orccon WHERE cod_orccon = 1;
DELETE FROM tbl_orccon WHERE cod_orccon <> 0;


DELETE FROM tbl_sessao WHERE fk_cod_orc = 1;
DELETE FROM tbl_sessao WHERE id_sessao <> 0;


DELETE FROM tbl_sescon WHERE id_sescon = 1;
DELETE FROM tbl_sescon WHERE id_sescon <> 0;
#--------------------------------------------------------------------------------------