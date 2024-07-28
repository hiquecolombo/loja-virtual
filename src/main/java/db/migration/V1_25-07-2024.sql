SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE 
	WHERE TABLE_NAME = 'usuario_acesso' AND COLUMN_NAME = 'acesso_id' 
	AND CONSTRAINT_NAME <> 'unique_acesso_user';
	
ALTER TABLE usuario_acesso drop CONSTRAINT "ukfhwpg5wu1u5p306q8gycxn9ky";

ALTER TABLE venda_compra_loja_virtual drop CONSTRAINT "ukc30agr0qfwruqaoymp4chk2h1";

ALTER TABLE nota_fiscal_venda drop CONSTRAINT "uklwo38kb0aaxboalphfjlan8qr";
