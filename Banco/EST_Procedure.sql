#A procedure será chamada quando todas as sessões forem concluidas
#Assim ele cria uma cópia dos dados para as respectivas tablas de orçamento e sessão concluidas

DELIMITER $
CREATE PROCEDURE orcamento_concluido(IN X INT(10))

	BEGIN

		INSERT INTO tbl_orccon(cod_orccon, criado_em, tipo_pagamento, valor_total, qntd_sessao, fk_id_cli_orccon)
		SELECT cod_orc, criado_em, tipo_pagamento, valor_total, qntd_sessao, fk_id_cli_orc
		FROM tbl_orcamento
		WHERE cod_orc = X;

		INSERT INTO tbl_sescon(id_sescon, concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orccon)
		SELECT id_sessao, concluida, valor_sessao, tipo_pagamento, data_agendada, hora_agendada, desconto, fk_cod_orc
		FROM tbl_sessao	
		WHERE fk_cod_orc = X
		#GROUP BY fk_cod_orc
		;

		DELETE FROM tbl_sessao WHERE fk_cod_orc = 1;
		DELETE FROM tbl_orcamento WHERE cod_orc = 1;
	
	END $

Delimiter ;

SELECT * FROM tbl_orcamento;
SELECT * FROM tbl_orccon;
SELECT * FROM tbl_sessao;
SELECT * FROM tbl_sescon;

CALL orcamento_concluido(1);