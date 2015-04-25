#-----------------------------------------------------------------------------------
#Views do Sistema

#Mostra os clientes que são menores de dado
CREATE VIEW cliente_menor AS
    SELECT 
        REL.id_rel AS 'ID da tabela relação',
        CLI.nome AS 'Nome do cliente',
		CLI.CPF AS 'CPF do cliente',
		PAR.tipo_parentesco AS 'Tipo do parentesco',
		REP.nome AS 'Nome do Representante',
		REP.CPF AS 'CPF do representante'
    FROM
        tbl_cliente CLI,
        tbl_parentesco PAR,
		tbl_representante REP,
		tbl_rel REL
    WHERE REL.fk_id_cli = CLI.id_cli 
	AND REL.fk_id_representante = REP.id_representante 
	AND REL.fk_id_parentesco = PAR.id_parentesco;

SELECT * FROM cliente_menor;