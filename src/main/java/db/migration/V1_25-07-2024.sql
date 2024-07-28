SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE 
	WHERE TABLE_NAME = 'usuario_acesso' AND COLUMN_NAME = 'acesso_id' 
	AND CONSTRAINT_NAME <> 'unique_acesso_user';
	
------------------------------------
	
ALTER TABLE nota_fiscal_venda drop CONSTRAINT "uklwo38kb0aaxboalphfjlan8qr";

ALTER TABLE pessoa_fisica drop CONSTRAINT "uk6iugygxa8vvgtu94fsb0j7fs2";

ALTER TABLE pessoa_juridica drop CONSTRAINT "uk6iugygxa8vvgtu94fsb0j7fs2";

ALTER TABLE usuario drop CONSTRAINT "ukr3paqktjbbb5iuok1mvu0s8xg";

ALTER TABLE usuario_acesso drop CONSTRAINT "ukfhwpg5wu1u5p306q8gycxn9ky";

ALTER TABLE venda_compra_loja_virtual drop CONSTRAINT "ukc30agr0qfwruqaoymp4chk2h1";


------------------------------------

CREATE OR REPLACE FUNCTION validaChavePessoa()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
AS $$
DECLARE EXISTE INTEGER;

BEGIN
		EXISTE = (SELECT COUNT(1) FROM PESSOA_FISICA WHERE ID = NEW.PESSOA_ID);
			IF (EXISTE <= 0) THEN
		EXISTE = (SELECT COUNT(1) FROM PESSOA_JURIDICA WHERE ID = NEW.PESSOA_ID);
			IF(EXISTE <= 0) THEN
		RAISE EXCEPTION 'Não foi encontrado o ID ou PK da pessoa para realizar associação.';
			END IF;
   		END IF;
	RETURN NEW;
END;
$$
;

--------------------------


CREATE OR REPLACE FUNCTION validaChavePessoaForn()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
AS $$
DECLARE EXISTE INTEGER;

BEGIN
		EXISTE = (SELECT COUNT(1) FROM PESSOA_FISICA WHERE ID = NEW.PESSOA_FORNECEDOR_ID);
			IF (EXISTE <= 0) THEN
		EXISTE = (SELECT COUNT(1) FROM PESSOA_JURIDICA WHERE ID = NEW.PESSOA_FORNECEDOR_ID);
			IF(EXISTE <= 0) THEN
		RAISE EXCEPTION 'Não foi encontrado o ID ou PK da pessoa para realizar associação.';
			END IF;
   		END IF;
	RETURN NEW;
END;
$$
;

-----------------------------------------

CREATE TRIGGER validaChavePessoaAvaliacaoProduto
BEFORE UPDATE OR INSERT ON AVALIACAO_PRODUTO
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChavePessoaContaPagar
BEFORE UPDATE OR INSERT ON CONTA_PAGAR
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChavePessoaFornecedorContaPagar
BEFORE UPDATE OR INSERT ON CONTA_PAGAR
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoaForn();

CREATE TRIGGER validaChavePessoaContaReceber
BEFORE UPDATE OR INSERT ON CONTA_RECEBER
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChavePessoaEndereco
BEFORE UPDATE OR INSERT ON ENDERECO
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChavePessoaNotaFiscal
BEFORE UPDATE OR INSERT ON NOTA_FISCAL_COMPRA
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChaveUsuario
BEFORE UPDATE OR INSERT ON USUARIO
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();

CREATE TRIGGER validaChavePessoaVendaCompraLojaVirtual
BEFORE UPDATE OR INSERT ON venda_compra_loja_virtual
FOR EACH ROW EXECUTE PROCEDURE PUBLIC.validaChavePessoa();





