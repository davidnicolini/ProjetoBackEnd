-- Exemplos de comandos SQL para um CRUD
-- CREATE, READ, UPDATE AND DELETE

-- Adicionando produto na tabela
INSERT INTO public.produto(
	"nome ", quantidade, valor, observacao)
	VALUES ('Sonho doce de Chocolate', 50, 2.50, 'Sonho Frito');

-- Consultando todos os produtos
SELECT * FROM public.produto

-- Consultando produto por id
SELECT * FROM public.produto WHERE id = 1;

-- Atualiando produto na tabela
UPDATE public.produto
SET observacao = 'Sonho frito com acucar',
	quantidade = 20
WHERE id = 2;

-- Deletando produto por id
DELETE FROM public.produto
WHERE id = 1;