CREATE OR REPLACE VIEW VW_AUTOR
AS
SELECT  parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, 
        parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco 
FROM parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))
WHERE parte.tipo_parte = 2
;


CREATE OR REPLACE VIEW VW_REU
AS
SELECT  parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, 
        parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco 
FROM parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))
WHERE parte.tipo_parte = 1;

CREATE OR REPLACE VIEW VW_ADV
AS
SELECT parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, 
       parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco 
FROM (parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))) 
WHERE (parte.tipo_parte = 3);

