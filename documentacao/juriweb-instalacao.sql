--SEGURANCA--
CREATE SEQUENCE SEQ_FUNCIONALIDADE;
CREATE TABLE FUNCIONALIDADE (
  CODIGO INTEGER NOT NULL,
  NOME VARCHAR(50) NULL,
  DESCRICAO VARCHAR(100) NULL,
  PRIMARY KEY(CODIGO)
);

CREATE SEQUENCE SEQ_LINK_FUNCAO;
CREATE TABLE LINK_FUNCAO (
  CODIGO INTEGER NOT NULL,
  URL VARCHAR(100) NULL UNIQUE,
  DESCRICAO VARCHAR(100) NULL,
  TIPO CHAR(1) NULL,
  PRIMARY KEY(CODIGO)
);

CREATE SEQUENCE SEQ_FUNCAO;
CREATE TABLE FUNCAO (
  CODIGO INTEGER NOT NULL,
  LINK_CODIGO INTEGER NOT NULL,
  NOME VARCHAR(50) NULL UNIQUE,
  DESCRICAO VARCHAR(100) NULL,
  PRIMARY KEY(CODIGO),
  FOREIGN KEY(LINK_CODIGO)
    REFERENCES LINK_FUNCAO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE SEQUENCE SEQ_PERFIL;
CREATE TABLE PERFIL (
  CODIGO INTEGER NOT NULL,
  NOME VARCHAR(50) NULL UNIQUE,
  DESCRICAO VARCHAR(100) NULL,
  PRIMARY KEY(CODIGO)
);

CREATE SEQUENCE SEQ_USUARIO;
CREATE TABLE USUARIO (
  CODIGO INTEGER NOT NULL,
  PERFIL_CODIGO INTEGER NOT NULL,
  NOME VARCHAR(100) NULL,
  LOGIN VARCHAR(100) NULL UNIQUE,
  SENHA VARCHAR(100) NULL,
  ATIVO BOOL NULL,
  PRIMARY KEY(CODIGO),
  FOREIGN KEY(PERFIL_CODIGO)
    REFERENCES PERFIL(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE FUNCAO_FUNCIONALIDADE (
  FUNCAO_CODIGO INTEGER NOT NULL,
  FUNCIONALIDADE_CODIGO INTEGER NOT NULL,
  PRIMARY KEY(FUNCAO_CODIGO, FUNCIONALIDADE_CODIGO),
  FOREIGN KEY(FUNCAO_CODIGO)
    REFERENCES FUNCAO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(FUNCIONALIDADE_CODIGO)
    REFERENCES FUNCIONALIDADE(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE PERFIL_FUNCAO (
  PERFIL_CODIGO INTEGER NOT NULL,
  FUNCAO_CODIGO INTEGER NOT NULL,
  PRIMARY KEY(PERFIL_CODIGO, FUNCAO_CODIGO),
  FOREIGN KEY(FUNCAO_CODIGO)
    REFERENCES FUNCAO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(PERFIL_CODIGO)
    REFERENCES PERFIL(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE PERFIL_FUNCIONALIDADE (
  FUNCIONALIDADE_CODIGO INTEGER NOT NULL,
  PERFIL_CODIGO INTEGER NOT NULL,
  PRIMARY KEY(FUNCIONALIDADE_CODIGO, PERFIL_CODIGO),
  FOREIGN KEY(PERFIL_CODIGO)
    REFERENCES PERFIL(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(FUNCIONALIDADE_CODIGO)
    REFERENCES FUNCIONALIDADE(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE USUARIO_FUNCAO (
  USUARIO_CODIGO INTEGER NOT NULL,
  FUNCAO_CODIGO INTEGER NOT NULL,
  PRIMARY KEY(USUARIO_CODIGO, FUNCAO_CODIGO),
  FOREIGN KEY(FUNCAO_CODIGO)
    REFERENCES FUNCAO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(USUARIO_CODIGO)
    REFERENCES USUARIO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE USUARIO_FUNCIONALIDADE (
  USUARIO_CODIGO INTEGER NOT NULL,
  FUNCIONALIDADE_CODIGO INTEGER NOT NULL,
  PRIMARY KEY(USUARIO_CODIGO, FUNCIONALIDADE_CODIGO),
  FOREIGN KEY(USUARIO_CODIGO)
    REFERENCES USUARIO(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(FUNCIONALIDADE_CODIGO)
    REFERENCES FUNCIONALIDADE(CODIGO)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

--SEGURANCA--

--CRIACAO DE TABELAS

CREATE TABLE acordo (
    acordo_codigo integer NOT NULL,
    processo_codigo integer NOT NULL,
    val_acordo double precision NOT NULL,
    qtd_parcelas integer NOT NULL,
    dt_vencimento date NOT NULL,
    str_observacao character varying(255),
    vl_indice_multa double precision,
    vl_juros_mes double precision,
    vl_indice_clasula_penal_mes double precision,
    indice_reajuste_codigo integer
);


CREATE TABLE andamento (
    andamento_codigo integer NOT NULL,
    tipo_andamento_codigo integer NOT NULL,
    processo_codigo integer NOT NULL,
    des_andamento character varying(1000),
    dt_lancamento date NOT NULL,
    sts_interno integer,
    dth_prazo timestamp without time zone
);

CREATE TABLE banco (
    num_banco integer NOT NULL,
    nom_banco character varying(150) NOT NULL
);

CREATE TABLE cheque (
    cheque_codigo integer NOT NULL,
    num_banco integer NOT NULL,
    num_cheque character varying(20) NOT NULL,
    num_agencia character varying(20) NOT NULL,
    dt_recebimento date,
    dt_compensacao date
);

CREATE TABLE classe_processo (
    classe_processo_codigo integer NOT NULL,
    des_classe_processo character varying
);

CREATE TABLE comarca (
    comarca_codigo integer NOT NULL,
    municipio_codigo integer NOT NULL
);

CREATE TABLE diretorio (
    diretorio_codigo integer NOT NULL,
    nom_diretorio character varying(255),
    diretorio_pai_codigo integer,
    str_sort_key character varying(4000) NOT NULL
);

CREATE TABLE documento (
    documento_codigo integer NOT NULL,
    diretorio_codigo integer NOT NULL,
    nom_documento character varying(1000) NOT NULL,
    blb_documento text
);

CREATE TABLE estado (
    estado_codigo integer NOT NULL,
    nom_estado character varying(100) NOT NULL,
    sig_estado character varying(10) NOT NULL
);

CREATE TABLE forma_pagamento (
    forma_pagamento_codigo integer NOT NULL,
    des_tipo_pagamento character varying(150) NOT NULL
);

CREATE TABLE indice_reajuste (
    indice_reajuste_codigo integer NOT NULL,
    des_indice_reajuste character varying(50),
    str_nome character varying(10) NOT NULL
);

CREATE TABLE juizo (
    juizo_codigo integer NOT NULL,
    des_juizo character varying(150) NOT NULL
);

CREATE TABLE jurisprudencia (
    jurisprudencia_codigo integer NOT NULL,
    str_titulo character varying(3000) NOT NULL,
    documento_codigo integer NOT NULL,
    area_atuacao_codigo integer NOT NULL
);


CREATE TABLE link (
    link_codigo integer NOT NULL,
    nom_link character varying(100) NOT NULL,
    str_endereco character varying(400) NOT NULL
);

CREATE TABLE municipio (
    municipio_codigo integer NOT NULL,
    estado_codigo integer NOT NULL,
    nom_municipio character varying(100) NOT NULL
);

CREATE TABLE orgao_judiciario (
    orgao_judiciario_codigo integer NOT NULL,
    juizo_codigo integer NOT NULL,
    des_orgao_judiciario character varying(150) NOT NULL
);

CREATE TABLE pagamento (
    pagamento_codigo integer NOT NULL,
    cheque_codigo integer,
    vl_vencimento double precision NOT NULL,
    sts_pagamento integer NOT NULL,
    vl_multa double precision,
    vl_juros double precision,
    dt_vencimento date NOT NULL,
    forma_pagamento_codigo integer,
    vl_pago double precision,
    dt_pagamento date,
    vl_correcao double precision
);

CREATE TABLE parceiro (
    pessoa_codigo integer NOT NULL,
    des_servico text
);

CREATE TABLE parcela_acordo (
    parcela_acordo_codigo integer NOT NULL,
    acordo_codigo integer NOT NULL,
    pagamento_codigo integer,
    num_parcela integer NOT NULL,
    dt_repasse date,
    vl_repasse double precision,
    vl_honorario double precision,
    str_observacao character varying(400),
    vl_clausula_penal double precision
);

CREATE TABLE parte (
    processo_codigo integer NOT NULL,
    pessoa_codigo integer NOT NULL,
    tipo_parte integer NOT NULL,
    sts_cliente integer NOT NULL
);

CREATE TABLE periodo_indice (
    periodo_indice_codigo integer NOT NULL,
    indice_reajuste_codigo integer NOT NULL,
    mes integer,
    ano integer,
    valor double precision
);

CREATE TABLE pessoa (
    pessoa_codigo integer NOT NULL,
    usuario_codigo INTEGER NULL,
    nome character varying(150) NOT NULL,
    cpf_cnpj character varying(30),
    endereco character varying(150),
    apto integer,
    bloco character varying(25),
    tipo_pessoa integer NOT NULL,
    observacao text,
    contato character varying(100),
    cep character varying(20),
    telefone character varying(25),
    str_telefone text,
    str_email character varying(200),
    FOREIGN KEY(usuario_codigo)
    REFERENCES usuario(codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


CREATE TABLE processo (
    processo_codigo integer NOT NULL,
    classe_processo_codigo integer NOT NULL,
    orgao_judiciario_codigo integer NOT NULL,
    comarca_codigo integer NOT NULL,
    str_numero_processo character varying(100) NOT NULL,
    sts_processo integer,
    vl_causa double precision NOT NULL,
    dt_distribuicao date,
    percentagem_honorario double precision,
    turno_codigo integer,
    dt_autuacao date,
    str_observacao text,
    apto integer,
    bloco character varying(5)
);

CREATE TABLE configuracao(
	sigla		VARCHAR(50) 	NOT NULL,
	valor		VARCHAR(50)		NOT NULL,
	descricao	VARCHAR(100)	NOT NULL,
	PRIMARY KEY(sigla)
);

--CRIACAO DE TABELAS

CREATE SEQUENCE seq_acordo
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_acordo', 3, true);

CREATE SEQUENCE seq_andamento
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT setval('seq_andamento', 3, true);

CREATE SEQUENCE seq_banco
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_banco', 1, false);

CREATE SEQUENCE seq_cheque
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_cheque', 1, false);

CREATE SEQUENCE seq_classe_processo
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_classe_processo', 1, false);

CREATE SEQUENCE seq_comarca
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_comarca', 1, false);

CREATE SEQUENCE seq_diretorio
    START WITH 2
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_diretorio', 2, false);

CREATE SEQUENCE seq_documento
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT setval('seq_documento', 1, false);

CREATE SEQUENCE seq_estado
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_estado', 1, false);

CREATE SEQUENCE seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_forma_pagamento', 1, false);

CREATE SEQUENCE seq_indice_reajuste
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT setval('seq_indice_reajuste', 1, false);

CREATE SEQUENCE seq_juizo
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_juizo', 1, false);

CREATE SEQUENCE seq_jurisprudencia
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT setval('seq_jurisprudencia', 1, false);

CREATE SEQUENCE seq_municipio
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_municipio', 1, false);


CREATE SEQUENCE seq_orgao_judiciario
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_orgao_judiciario', 1, false);


CREATE SEQUENCE seq_pagamento
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_pagamento', 30, true);

CREATE SEQUENCE seq_parceiro
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('seq_parceiro', 1, false);

CREATE SEQUENCE seq_parte
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('seq_parte', 1, false);


CREATE SEQUENCE seq_periodo_indice
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('seq_periodo_indice', 1, false);

CREATE SEQUENCE seq_pessoa
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_pessoa', 2, true);

CREATE SEQUENCE seq_processo
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_processo', 4, true);

CREATE SEQUENCE seq_taxa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('seq_taxa', 1, false);


CREATE SEQUENCE seq_tipo_andamento
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('seq_tipo_andamento', 1, false);


CREATE SEQUENCE seq_tipo_orgao_judiciario
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('seq_tipo_orgao_judiciario', 1, false);

--CREATE SEQUENCE seq_usuario
--    START WITH 1
--    INCREMENT BY 1
--    NO MAXVALUE
--    NO MINVALUE
--    CACHE 1;

--SELECT pg_catalog.setval('seq_usuario', 1, false);

CREATE TABLE taxa (
    taxa_codigo integer NOT NULL,
    tipo_taxa integer NOT NULL,
    num_mes integer NOT NULL,
    num_ano integer NOT NULL,
    processo_codigo integer
);


CREATE TABLE tipo_andamento (
    tipo_andamento_codigo integer NOT NULL,
    des_tipo_andamento character varying(150) NOT NULL
);



CREATE TABLE tipo_andamento_juizo (
    tipo_andamento_codigo integer NOT NULL,
    juizo_codigo integer NOT NULL
);

CREATE VIEW vw_adv AS
    SELECT parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco FROM (parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))) WHERE (parte.tipo_parte = 3);



CREATE VIEW vw_autor AS
    SELECT parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco FROM (parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))) WHERE (parte.tipo_parte = 2);


CREATE VIEW vw_reu AS
    SELECT parte.processo_codigo, parte.pessoa_codigo, parte.tipo_parte, parte.sts_cliente, pessoa.nome, pessoa.apto, pessoa.bloco FROM (parte JOIN pessoa ON ((parte.pessoa_codigo = pessoa.pessoa_codigo))) WHERE (parte.tipo_parte = 1);


INSERT INTO banco (num_banco, nom_banco) VALUES (654, 'BANCO A.J. RENNER S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (246, 'BANCO ABC BRASIL S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (275, 'BANCO ABN AMRO REAL S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (356, 'BANCO ABN AMRO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (25, 'BANCO ALFA S/A ');
INSERT INTO banco (num_banco, nom_banco) VALUES (641, 'BANCO ALVORADA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (213, 'BANCO ARBI S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (29, 'BANCO BANERJ S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (38, 'BANCO BANESTADO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (719, 'BANCO BANIF PRIMUS S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (107, 'BANCO BBM S.A          ');
INSERT INTO banco (num_banco, nom_banco) VALUES (291, 'BANCO BCN S.A.         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (34, 'BANCO BEA S.A.         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (31, 'BANCO BEG S.A.         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (48, 'BANCO BEMGE S.A.       ');
INSERT INTO banco (num_banco, nom_banco) VALUES (739, 'BANCO BGN S.A.         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (96, 'BANCO BM&F DE SERV. DE LIQUIDA��O E CUSTODIA S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (394, 'BANCO BMC S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (318, 'BANCO BMG S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (116, 'BANCO BNL DO BRASIL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (752, 'BANCO BNP PARIBAS BRASIL S.A ');
INSERT INTO banco (num_banco, nom_banco) VALUES (231, 'BANCO BOAVISTA INTERATLANTICO S.A-EM ABSORCAO  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (218, 'BANCO BONSUCESSO S.A.     ');
INSERT INTO banco (num_banco, nom_banco) VALUES (237, 'BANCO BRADESCO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (225, 'BANCO BRASCAN S.A.   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (44, 'BANCO BVA SA         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (263, 'BANCO CACIQUE S.A.   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (222, 'BANCO CALYON BRASIL S/A ');
INSERT INTO banco (num_banco, nom_banco) VALUES (412, 'BANCO CAPITAL S.A.      ');
INSERT INTO banco (num_banco, nom_banco) VALUES (266, 'BANCO CEDULA S.A.       ');
INSERT INTO banco (num_banco, nom_banco) VALUES (244, 'BANCO CIDADE S.A.       ');
INSERT INTO banco (num_banco, nom_banco) VALUES (745, 'BANCO CITIBANK S.A.     ');
INSERT INTO banco (num_banco, nom_banco) VALUES (215, 'BANCO COMERCIAL E DE INVESTIMENTO SUDAMERIS S.A ');
INSERT INTO banco (num_banco, nom_banco) VALUES (753, 'BANCO COMERCIAL URUGUAI S.A.       ');
INSERT INTO banco (num_banco, nom_banco) VALUES (756, 'BANCO COOPERATIVO DO BRASIL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (748, 'BANCO COOPERATIVO SICREDI S.A. - BANSICREDI ');
INSERT INTO banco (num_banco, nom_banco) VALUES (721, 'BANCO CREDIBEL S.A.                         ');
INSERT INTO banco (num_banco, nom_banco) VALUES (505, 'BANCO CREDIT SUISSE FIRST BOSTON S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (229, 'BANCO CRUZEIRO DO SUL S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (3, 'BANCO DA AMAZONIA S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (707, 'BANCO DAYCOVAL S.A.   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (495, 'BANCO DE LA PROVINCIA DE BUENOS AIRES   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (494, 'BANCO DE LA REPUBLICA ORIENTAL DEL URUGUAY ');
INSERT INTO banco (num_banco, nom_banco) VALUES (24, 'BANCO DE PERNAMBUCO S.A.-BANDEPE   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (456, 'BANCO DE TOKYO MITSUBISHI BRASIL S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (214, 'BANCO DIBENS S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (1, 'BANCO DO BRASIL S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (27, 'BANCO DO ESTADO DE SANTA CATARINA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (33, 'BANCO DO ESTADO DE SAO PAULO S.A. - BANESPA   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (47, 'BANCO DO ESTADO DE SERGIPE S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (35, 'BANCO DO ESTADO DO CEARA S.A. BEC  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (36, 'BANCO DO ESTADO DO MARANHAO S.A.-BEM ');
INSERT INTO banco (num_banco, nom_banco) VALUES (37, 'BANCO DO ESTADO DO PARA S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (39, 'BANCO DO ESTADO DO PIAUI S.A.-BEP  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (41, 'BANCO DO ESTADO DO RIO GRANDE DO SUL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (4, 'BANCO DO NORDESTE DO BRASIL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (743, 'BANCO EMBLEMA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (265, 'BANCO FATOR S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (224, 'BANCO FIBRA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (626, 'BANCO FICSA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (175, 'BANCO FINASA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (252, 'BANCO FININVEST S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (233, 'BANCO GE CAPITAL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (734, 'BANCO GERDAU S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (612, 'BANCO GUANABARA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (63, 'BANCO IBI S.A - BANCO MULTIPLO ');
INSERT INTO banco (num_banco, nom_banco) VALUES (604, 'BANCO INDUSTRIAL DO BRASIL S. A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (320, 'BANCO INDUSTRIAL E COMERCIAL S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (653, 'BANCO INDUSVAL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (630, 'BANCO INTERCAP S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (249, 'BANCO INVESTCRED UNIBANCO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (341, 'BANCO ITAU S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (74, 'BANCO J. SAFRA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (217, 'BANCO JOHN DEERE S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (600, 'BANCO LUSO BRASILEIRO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (212, 'BANCO MATONE S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (243, 'BANCO MAXIMA S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (392, 'BANCO MERCANTIL DE SAO PAULO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (389, 'BANCO MERCANTIL DO BRASIL S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (746, 'BANCO MODAL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (738, 'BANCO MORADA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (151, 'BANCO NOSSA CAIXA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (45, 'BANCO OPPORTUNITY S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (208, 'BANCO PACTUAL S.A.    ');
INSERT INTO banco (num_banco, nom_banco) VALUES (623, 'BANCO PANAMERICANO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (611, 'BANCO PAULISTA S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (650, 'BANCO PEBB S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (613, 'BANCO PECUNIA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (643, 'BANCO PINE S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (735, 'BANCO POTTENCIAL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (638, 'BANCO PROSPER S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (747, 'BANCO RABOBANK INTERNATIONAL BRASIL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (216, 'BANCO REGIONAL MALCON S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (633, 'BANCO RENDIMENTO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (741, 'BANCO RIBEIRAO PRETO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (453, 'BANCO RURAL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (422, 'BANCO SAFRA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (353, 'BANCO SANTANDER BRASIL S.A.');
INSERT INTO banco (num_banco, nom_banco) VALUES (8, 'BANCO SANTANDER MERIDIONAL S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (351, 'BANCO SANTANDER S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (250, 'BANCO SCHAHIN S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (366, 'BANCO SOCIETE GENERALE BRASIL S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (637, 'BANCO SOFISA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (347, 'BANCO SUDAMERIS DO BRASIL S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (464, 'BANCO SUMITOMO MITSUI BRASILEIRO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (634, 'BANCO TRIANGULO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (247, 'BANCO UBS S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (655, 'BANCO VOTORANTIM S.A.   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (610, 'BANCO VR S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (370, 'BANCO WESTLB DO BRASIL  S.A.   ');
INSERT INTO banco (num_banco, nom_banco) VALUES (219, 'BANCO ZOGBI S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (62, 'BANCO1.NET S.A  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (28, 'BANEB-EM ABSORCAO ');
INSERT INTO banco (num_banco, nom_banco) VALUES (21, 'BANESTES S.A BANCO DO ESTADO DO ESPIRITO SANTO ');
INSERT INTO banco (num_banco, nom_banco) VALUES (479, 'BANKBOSTON BANCO MULTIPLO S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (73, 'BB BANCO POPULAR DO BRASIL  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (749, 'BR BANCO MERCANTIL S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (70, 'BRB - BANCO DE BRASILIA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (104, 'CAIXA ECONOMICA FEDERAL  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (487, 'DEUTSCHE BANK S. A. - BANCO ALEM�O  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (210, 'DRESDNER BANK LATEINAMERIKA AKTIENGESELLSCHAFT  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (399, 'HSBC BANK BRASIL S.A.-BANCO M�LTIPLO ');
INSERT INTO banco (num_banco, nom_banco) VALUES (65, 'LEMON BANK BANCO MULTIPLO S.A ');
INSERT INTO banco (num_banco, nom_banco) VALUES (30, 'PARAIBAN-BANCO DA PARAIBA S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (254, 'PARANA BANCO S.A.  ');
INSERT INTO banco (num_banco, nom_banco) VALUES (409, 'UNIBANCO - UNIAO DE BANCOS BRASILEIROS S.A. ');
INSERT INTO banco (num_banco, nom_banco) VALUES (230, 'UNICARD BANCO MULTIPLO S.A  ');




INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (1, 'COBRAN�A');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (3, 'INDENIZATORIA');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (4, 'BUSCA E APREENS�O');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (5, 'RECLAMA��O TRABALHISTA');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (6, 'EXECU��O');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (7, 'MANDADO DE SEGURAN�A');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (8, 'SUSTA��O DE PROTESTO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (9, 'DECLARATORIA');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (10, 'EMBARGOS');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (11, 'SEPARA��O CONSENSUAL');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (12, 'DIVORCIO CONSENSUAL');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (13, 'INVENTARIO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (14, 'AGRAVO DE INSTRUMENTO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (15, 'DIVORCIO DIRETO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (16, 'DEFESA ADMINISTRATIVA');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (17, 'CONSENSUAL DE DISSOLU��O DE UNI�O ESTAVEL ');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (24, 'ARROLAMENTO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (19, 'ALVAR� JUDICIAL');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (20, 'IMPUGNA��O AO VALOR DA CAUSA');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (21, 'PRESTA��O DE CONTAS');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (18, 'REGULA��O DE VISITAS');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (23, 'ATENDIMENTO');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (2, 'REPETI��O DE INDEBITO - PULSOS');
INSERT INTO classe_processo (classe_processo_codigo, des_classe_processo) VALUES (25, 'REPETI��O DE INDEBITO - ASSINATURA');



INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (1, 1);
INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (2, 2);
INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (3, 3);
INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (4, 8);
INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (5, 10);
INSERT INTO comarca (comarca_codigo, municipio_codigo) VALUES (6, 11);




INSERT INTO diretorio (diretorio_codigo, nom_diretorio, diretorio_pai_codigo, str_sort_key) VALUES (1, 'Sistema Jur�dico WEB', NULL, '/00');



INSERT INTO estado (estado_codigo, nom_estado, sig_estado) VALUES (7000, 'Bahia', 'BA');


INSERT INTO indice_reajuste (indice_reajuste_codigo, des_indice_reajuste, str_nome) VALUES (1, '�ndice Nacional de Pre�os ao Consumidor', 'INPC');
INSERT INTO indice_reajuste (indice_reajuste_codigo, des_indice_reajuste, str_nome) VALUES (3, '�ndice de Pre�os ao Consumidor Ampliado', 'IPCA');
INSERT INTO indice_reajuste (indice_reajuste_codigo, des_indice_reajuste, str_nome) VALUES (2, '�ndice Geral de Pre�os do Mercado', 'IGP-M');


INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (5, 'ADMINISTRATIVO');
INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (6, 'EXTRA JUDICIAL');
INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (1, 'COMUM');
INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (3, 'JUIZADO');
INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (4, 'FEDERAL');
INSERT INTO juizo (juizo_codigo, des_juizo) VALUES (2, 'TRABALHISTA');


INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (478, 'Superior Tribunal de Justi�a', 'www.stj.gov.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (464, 'Correios', 'www.correios.com.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (458, 'Justi�a Federal - 1� Regi�o', 'www.trf1.gov.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (457, 'Tribunal de Justi�a do Estado da Bahia', 'www.tj.ba.gov.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (459, 'Justi�a do trabalho - 5� Regi�o', 'www.trt05.gov.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (479, 'Supremo Tribunal Federal', 'www.stf.gov.br');
INSERT INTO link (link_codigo, nom_link, str_endereco) VALUES (485, 'Presidencia da Republica', 'www.presidenciadarepublica.gov.br/');



INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (1, 7000, 'Salvador');
INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (2, 7000, 'Lauro de Freitas');
INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (3, 7000, 'Cama�ari');
INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (10, 7000, 'Senhor do Bonfim');
INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (11, 7000, 'Vitoria da Conquista');
INSERT INTO municipio (municipio_codigo, estado_codigo, nom_municipio) VALUES (8, 7000, 'Itaparica');


INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (1, 3, 'JEAIG');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (2, 3, '3� JEC - FTC');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (3, 3, '5� JEC - JORGE AMADO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (4, 3, '1� JDC - BARRIS');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (5, 3, '2� JDC - BROTAS');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (6, 3, '1� JEC - PIAT�');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (7, 3, '2� JEC - LIBERDADE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (8, 3, '4� JEC - BONFIM');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (9, 3, 'JMEFE - FEDERA��O');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (10, 3, '1� JECRIM - FORUM');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (11, 3, '2� JECRIM - ITAPOAN');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (12, 3, 'JEABA - SHOP. BARRA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (13, 3, 'JEABR - BOCA DO RIO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (95, 3, 'JEC - SR. DO BONFIM');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (15, 1, '2� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (14, 1, '1� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (17, 1, '10� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (18, 2, '1� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (19, 2, '2� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (20, 2, '3� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (21, 2, '4� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (22, 2, '5� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (23, 2, '6� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (24, 2, '7� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (25, 2, '8� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (26, 2, '9� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (27, 2, '10� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (28, 2, '11� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (29, 2, '12� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (30, 2, '13� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (31, 2, '14� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (32, 2, '15� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (33, 2, '16� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (34, 2, '17� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (35, 2, '18� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (36, 2, '19� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (37, 2, '20� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (38, 2, '21� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (39, 2, '22� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (40, 2, '23� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (41, 2, '24� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (42, 2, '25� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (43, 2, '26� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (44, 5, 'INSS - AGENCIA ITAPOAN');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (45, 1, '3� VARA C�VEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (46, 1, '4� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (47, 1, '5� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (48, 1, '6� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (49, 1, '7� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (50, 1, '9� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (51, 1, '11� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (52, 1, '12� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (53, 1, '13� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (54, 1, '14� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (55, 1, '15� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (56, 1, '16� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (57, 1, '17� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (58, 1, '18� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (59, 1, '19� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (60, 1, '20� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (61, 1, '21� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (62, 1, '22� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (63, 1, '24� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (64, 1, '25� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (65, 1, '1� VARA ESPECIALIZADA DEFESA DO CONSUMIDOR');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (66, 1, '2� VARA ESPECIALIZADA DEFESA DO CONSUMIDOR');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (67, 1, 'VARA DE REGISTROS PUBLICOS E ACIDENTES DE TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (68, 1, '1� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (69, 1, '2� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (70, 1, '3� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (71, 1, '4� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (72, 1, '5� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (80, 5, 'DELEGACIA REGIONAL DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (74, 1, '6� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (75, 1, '7� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (76, 1, '8� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (77, 1, '9� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (78, 1, '10� VARA DE FAZENDA PUBLICA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (79, 4, 'JUIZADO ESPECIAL FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (81, 3, 'NAJ');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (82, 6, 'EXTRA- JUDICIAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (83, 2, '37� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (84, 2, '27� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (85, 2, '28� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (86, 2, '29� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (87, 2, '30� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (88, 2, '31� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (89, 2, '32� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (90, 2, '33� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (91, 2, '34� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (92, 2, '35� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (93, 2, '36� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (94, 3, 'SAC PERIPERI');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (96, 3, 'DETRAN 1� JUIZADO DE TRANSITO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (97, 2, '39� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (98, 2, '37� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (99, 2, '38� VARA DO TRABALHO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (100, 4, '1� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (101, 4, '2� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (102, 4, '3� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (103, 4, '4� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (104, 4, '5� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (105, 4, '6� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (106, 4, '7� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (107, 4, '8� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (108, 4, '9� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (109, 4, '10� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (110, 4, '11� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (111, 4, '12� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (112, 4, '13� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (113, 4, '14� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (114, 4, '15� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (115, 4, '16� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (116, 4, '17� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (117, 4, '18� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (118, 4, '19� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (119, 4, '20� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (120, 4, '21� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (121, 4, '22� VARA FEDERAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (122, 1, '1� CAMARA C�VEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (123, 1, '2� CAMARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (124, 1, '3� CAMARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (125, 3, '1� TURMA RECURSAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (126, 3, '2� TURMA RECURSAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (127, 3, '3� TURMA RECURSAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (128, 3, '4� TURMA RECURSAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (129, 1, '5� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (130, 1, '4� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (131, 1, '3� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (132, 1, '2� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (133, 1, '1� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (134, 1, '6� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (135, 1, '7� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (16, 1, '8� VARA CIVEL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (136, 1, '8� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (137, 1, '9� VARA DE FAMILIA, SUCES., ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (138, 1, '10� VARA DE FAMILIA, SUCES, ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (139, 1, '11� VARA DE FAMILIA, SUCES, ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (140, 1, '12� VARA DE FAMILIA, SUCES, ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (141, 1, '13� VARA DE FAMILIA, SUCES, ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (142, 1, '14� VARA DE FAMILIA, SUCES, ORFAOS, INTERD. E AUSE');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (143, 5, 'DETRAN - DEPARTAMENTO ESTADUAL DE TRANSITO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (144, 3, '5� TURMA RECURSAL');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (145, 5, 'PROGRAMA ANUAL DE UNIVERSALIZA��O');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (146, 5, 'CINCOP- COMISS�O INTERSINDIC DE CONCILIA��O PR�VIA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (147, 5, 'SET - SECRETARIA DE ENGENHARIA DE TRAFEGO');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (148, 2, '1� VARA DO TRABALHO CAMA�ARI');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (149, 2, '2� VARA DO TRABALHO CAMA�ARI');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (150, 2, '3� VARA DO TRABALHO CAMA�ARI');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (151, 2, '4� VARA DO TRABALHO CAMA�ARI');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (152, 3, 'JEC - VITORIA DA CONQUISTA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (153, 3, 'SAC - VITORIA DA CONQUISTA');
INSERT INTO orgao_judiciario (orgao_judiciario_codigo, juizo_codigo, des_orgao_judiciario) VALUES (154, 6, 'ATENDIMENTO');




INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (1, 2, 1, 2005, 0.39000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (2, 2, 2, 2005, 0.29999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (3, 2, 3, 2005, 0.84999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (4, 2, 4, 2005, 0.85999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (5, 2, 5, 2005, -0.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (6, 2, 6, 2005, -0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (7, 2, 7, 2005, -0.34000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (8, 2, 8, 2005, -0.65000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (9, 2, 9, 2005, -0.53000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (10, 2, 1, 2004, 0.88);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (11, 2, 2, 2004, 0.68999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (12, 2, 3, 2004, 1.1299999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (13, 2, 4, 2004, 1.21);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (14, 2, 5, 2004, 1.3100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (15, 2, 6, 2004, 1.3799999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (16, 2, 7, 2004, 1.3100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (17, 2, 8, 2004, 1.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (18, 2, 9, 2004, 0.68999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (19, 2, 10, 2004, 0.39000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (20, 2, 11, 2004, 0.81999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (21, 2, 12, 2004, 0.73999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (22, 2, 1, 2003, 2.3300000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (23, 2, 2, 2003, 2.2799999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (24, 2, 3, 2003, 1.53);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (25, 2, 4, 2003, 0.92000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (27, 2, 6, 2003, -1);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (28, 2, 7, 2003, -0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (29, 2, 8, 2003, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (30, 2, 9, 2003, 1.1799999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (31, 2, 10, 2003, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (32, 2, 11, 2003, 0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (33, 2, 12, 2003, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (34, 2, 1, 2002, 0.35999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (35, 2, 2, 2002, 0.059999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (36, 2, 3, 2002, 0.089999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (37, 2, 4, 2002, 0.56000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (38, 2, 5, 2002, 0.82999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (39, 2, 6, 2002, 1.54);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (40, 2, 7, 2002, 1.95);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (41, 2, 8, 2002, 2.3199999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (42, 2, 9, 2002, 2.3999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (43, 2, 10, 2002, 3.8700000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (44, 2, 11, 2002, 5.1900000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (45, 2, 12, 2002, 3.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (46, 2, 1, 2001, 0.62);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (47, 2, 2, 2001, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (48, 2, 3, 2001, 0.56000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (49, 2, 4, 2001, 1);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (50, 2, 5, 2001, 0.85999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (51, 2, 6, 2001, 0.97999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (52, 2, 7, 2001, 1.48);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (53, 2, 8, 2001, 1.3799999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (54, 2, 9, 2001, 0.31);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (55, 2, 10, 2001, 1.1799999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (56, 2, 11, 2001, 1.1000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (57, 2, 1, 2000, 1.24);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (58, 2, 2, 2000, 0.34999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (59, 2, 3, 2000, 0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (60, 2, 4, 2000, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (61, 2, 5, 2000, 0.31);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (62, 2, 6, 2000, 0.84999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (63, 2, 7, 2000, 1.5700000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (64, 2, 8, 2000, 2.3900000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (65, 2, 9, 2000, 1.1599999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (66, 2, 10, 2000, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (67, 2, 11, 2000, 0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (68, 2, 12, 2000, 0.63);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (69, 2, 1, 1999, 0.83999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (70, 2, 2, 1999, 3.6099999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (71, 2, 3, 1999, 2.8300000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (72, 2, 4, 1999, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (73, 2, 5, 1999, -0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (74, 2, 6, 1999, 0.35999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (75, 2, 7, 1999, 1.55);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (76, 2, 8, 1999, 1.5600000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (77, 2, 9, 1999, 1.45);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (78, 2, 10, 1999, 1.7);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (79, 2, 11, 1999, 2.3900000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (80, 2, 12, 1999, 1.8100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (81, 2, 1, 1998, 0.95999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (82, 2, 2, 1998, 0.17999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (83, 2, 3, 1998, 0.19);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (84, 2, 4, 1998, 0.13);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (85, 2, 5, 1998, 0.14000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (86, 2, 6, 1998, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (87, 2, 7, 1998, -0.17000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (88, 2, 8, 1998, -0.16);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (89, 2, 9, 1998, -0.080000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (90, 2, 10, 1998, 0.080000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (91, 2, 11, 1998, -0.32000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (92, 2, 12, 1998, 0.45000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (93, 2, 1, 1997, 1.77);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (94, 2, 2, 1997, 0.42999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (95, 2, 3, 1997, 1.1499999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (96, 2, 4, 1997, 0.68000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (97, 2, 5, 1997, 0.20999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (98, 2, 6, 1997, 0.73999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (99, 2, 7, 1997, 0.089999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (100, 2, 8, 1997, 0.089999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (101, 2, 9, 1997, 0.47999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (102, 2, 10, 1997, 0.37);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (103, 2, 11, 1997, 0.64000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (104, 2, 12, 1997, 0.83999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (105, 2, 1, 1996, 1.73);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (106, 2, 2, 1996, 0.96999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (107, 2, 3, 1996, 0.40000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (108, 2, 4, 1996, 0.32000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (109, 2, 5, 1996, 1.55);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (110, 2, 6, 1996, 1.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (111, 2, 7, 1996, 1.3500000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (112, 2, 8, 1996, 0.28000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (113, 2, 9, 1996, 0.10000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (114, 2, 10, 1996, 0.19);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (115, 2, 11, 1996, 0.20000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (116, 2, 12, 1996, 0.72999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (117, 2, 1, 1995, 0.92000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (118, 2, 2, 1995, 1.3899999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (119, 2, 3, 1995, 1.1200000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (120, 2, 4, 1995, 2.1000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (121, 2, 5, 1995, 0.57999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (122, 2, 6, 1995, 2.46);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (123, 2, 7, 1995, 1.8200000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (124, 2, 8, 1995, 2.2000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (125, 2, 9, 1995, -0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (126, 2, 10, 1995, 0.52000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (127, 2, 11, 1995, 1.2);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (128, 2, 12, 1995, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (129, 2, 1, 1994, 39.07);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (130, 2, 2, 1994, 40.780000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (131, 2, 3, 1994, 45.710000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (132, 2, 4, 1994, 40.909999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (133, 2, 5, 1994, 42.579999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (134, 2, 6, 1994, 45.210000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (135, 2, 7, 1994, 40);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (136, 2, 8, 1994, 7.5599999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (137, 2, 9, 1994, 1.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (138, 2, 10, 1994, 1.8200000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (139, 2, 11, 1994, 2.8500000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (140, 2, 12, 1994, 0.83999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (141, 2, 1, 1993, 25.829999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (142, 2, 2, 1993, 28.41);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (143, 2, 3, 1993, 26.25);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (144, 2, 4, 1993, 28.829999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (145, 2, 5, 1993, 29.699999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (146, 2, 6, 1993, 31.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (147, 2, 7, 1993, 31.25);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (148, 2, 8, 1993, 31.789999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (149, 2, 9, 1993, 35.280000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (150, 2, 10, 1993, 35.039999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (151, 2, 11, 1993, 36.149999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (152, 2, 12, 1993, 38.32);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (153, 2, 1, 1992, 23.559999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (154, 2, 2, 1992, 27.859999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (155, 2, 3, 1992, 21.390000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (156, 2, 4, 1992, 19.940000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (157, 2, 5, 1992, 20.43);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (158, 2, 6, 1992, 23.609999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (159, 2, 7, 1992, 21.84);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (160, 2, 8, 1992, 24.629999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (161, 2, 9, 1992, 25.27);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (162, 2, 10, 1992, 26.760000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (163, 2, 11, 1992, 23.43);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (164, 2, 12, 1992, 25.079999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (165, 2, 1, 1991, 17.699999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (166, 2, 2, 1991, 21.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (167, 2, 3, 1991, 9.1899999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (168, 2, 4, 1991, 7.8099999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (169, 2, 5, 1991, 7.4800000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (170, 2, 6, 1991, 8.4800000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (171, 2, 7, 1991, 13.220000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (172, 2, 8, 1991, 15.25);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (173, 2, 9, 1991, 14.93);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (174, 2, 10, 1991, 22.629999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (175, 2, 11, 1991, 25.620000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (176, 2, 12, 1991, 23.629999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (177, 3, 1, 2005, 0.57999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (178, 3, 2, 2005, 0.58999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (179, 3, 3, 2005, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (180, 3, 4, 2005, 0.87);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (181, 3, 5, 2005, 0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (182, 3, 6, 2005, -0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (183, 3, 7, 2005, 0.25);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (184, 3, 8, 2005, 0.17000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (185, 3, 9, 2005, 0.34999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (186, 3, 1, 2004, 0.76000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (187, 3, 2, 2004, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (188, 3, 3, 2004, 0.46999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (189, 3, 4, 2004, 0.37);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (190, 3, 5, 2004, 0.51000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (191, 3, 6, 2004, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (192, 3, 7, 2004, 0.91000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (193, 3, 8, 2004, 0.68999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (194, 3, 9, 2004, 0.33000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (195, 3, 10, 2004, 0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (196, 3, 11, 2004, 0.68999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (197, 3, 12, 2004, 0.85999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (198, 3, 1, 2003, 2.25);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (199, 3, 2, 2003, 1.5700000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (200, 3, 3, 2003, 1.23);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (201, 3, 4, 2003, 0.96999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (202, 3, 5, 2003, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (203, 3, 6, 2003, -0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (204, 3, 7, 2003, 0.20000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (205, 3, 8, 2003, 0.34000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (206, 3, 9, 2003, 0.78000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (207, 3, 10, 2003, 0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (208, 3, 11, 2003, 0.34000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (209, 3, 12, 2003, 0.52000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (210, 3, 1, 2002, 0.52000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (211, 3, 2, 2002, 0.35999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (212, 3, 3, 2002, 0.59999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (213, 3, 4, 2002, 0.80000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (214, 3, 5, 2002, 0.20999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (215, 3, 6, 2002, 0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (216, 3, 7, 2002, 1.1899999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (217, 3, 8, 2002, 0.65000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (218, 3, 9, 2002, 0.71999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (219, 3, 10, 2002, 1.3100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (220, 3, 11, 2002, 3.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (221, 3, 12, 2002, 2.1000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (222, 3, 1, 2001, 0.56999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (223, 3, 2, 2001, 0.46000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (224, 3, 3, 2001, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (225, 3, 4, 2001, 0.57999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (226, 3, 5, 2001, 0.40999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (227, 3, 6, 2001, 0.52000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (228, 3, 7, 2001, 1.3300000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (229, 3, 8, 2001, 0.69999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (230, 3, 9, 2001, 0.28000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (231, 3, 10, 2001, 0.82999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (232, 3, 11, 2001, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (233, 3, 12, 2001, 0.65000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (234, 3, 1, 2000, 0.62);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (235, 3, 2, 2000, 0.13);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (236, 3, 3, 2000, 0.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (237, 3, 4, 2000, 0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (238, 3, 5, 2000, 0.01);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (239, 3, 6, 2000, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (240, 3, 7, 2000, 1.6100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (241, 3, 8, 2000, 1.3100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (242, 3, 9, 2000, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (243, 3, 10, 2000, 0.14000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (244, 3, 11, 2000, 0.32000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (245, 3, 12, 2000, 0.58999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (246, 3, 1, 1999, 0.69999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (247, 3, 2, 1999, 1.05);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (248, 3, 3, 1999, 1.1000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (249, 3, 4, 1999, 0.56000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (250, 3, 5, 1999, 0.29999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (251, 3, 6, 1999, 0.19);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (252, 3, 7, 1999, 1.0900000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (253, 3, 8, 1999, 0.56000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (254, 3, 9, 1999, 0.31);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (255, 3, 10, 1999, 1.1899999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (256, 3, 11, 1999, 0.94999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (257, 3, 12, 1999, 0.59999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (258, 3, 1, 1998, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (259, 3, 2, 1998, 0.46000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (260, 3, 3, 1998, 0.34000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (261, 3, 4, 1998, 0.23999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (262, 3, 5, 1998, 0.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (263, 3, 6, 1998, 0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (264, 3, 7, 1998, -0.12);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (265, 3, 8, 1998, -0.51000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (266, 3, 9, 1998, -0.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (267, 3, 10, 1998, 0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (268, 3, 11, 1998, -0.12);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (269, 3, 12, 1998, 0.33000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (270, 3, 1, 1997, 1.1799999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (271, 3, 2, 1997, 0.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (272, 3, 3, 1997, 0.51000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (273, 3, 4, 1997, 0.88);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (274, 3, 5, 1997, 0.40999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (275, 3, 6, 1997, 0.54000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (276, 3, 7, 1997, 0.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (277, 3, 8, 1997, -0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (278, 3, 9, 1997, 0.059999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (279, 3, 10, 1997, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (280, 3, 11, 1997, 0.17000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (281, 3, 12, 1997, 0.42999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (282, 3, 1, 1996, 1.3400000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (283, 3, 2, 1996, 1.03);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (284, 3, 3, 1996, 0.34999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (285, 3, 4, 1996, 1.26);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (286, 3, 5, 1996, 1.22);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (287, 3, 6, 1996, 1.1899999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (288, 3, 7, 1996, 1.1100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (289, 3, 8, 1996, 0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (290, 3, 9, 1996, 0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (291, 3, 10, 1996, 0.29999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (292, 3, 11, 1996, 0.32000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (293, 3, 12, 1996, 0.46999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (294, 3, 1, 1995, 1.7);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (295, 3, 2, 1995, 1.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (296, 3, 3, 1995, 1.55);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (297, 3, 4, 1995, 2.4300000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (298, 3, 5, 1995, 2.6699999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (299, 3, 6, 1995, 2.2599999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (300, 3, 7, 1995, 2.3599999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (301, 3, 8, 1995, 0.98999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (302, 3, 9, 1995, 0.98999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (303, 3, 10, 1995, 1.4099999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (304, 3, 11, 1995, 1.47);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (305, 3, 12, 1995, 1.5600000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (306, 3, 1, 1994, 41.310000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (307, 3, 2, 1994, 40.270000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (308, 3, 3, 1994, 42.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (309, 3, 4, 1994, 42.68);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (310, 3, 5, 1994, 44.030000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (311, 3, 6, 1994, 47.43);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (312, 3, 7, 1994, 6.8399999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (313, 3, 8, 1994, 1.8600000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (314, 3, 9, 1994, 1.53);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (315, 3, 10, 1994, 2.6200000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (316, 3, 11, 1994, 2.8100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (317, 3, 12, 1994, 1.71);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (318, 3, 1, 1993, 30.350000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (319, 3, 2, 1993, 24.98);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (320, 3, 3, 1993, 27.260000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (321, 3, 4, 1993, 27.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (322, 3, 5, 1993, 27.690000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (323, 3, 6, 1993, 30.07);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (324, 3, 7, 1993, 30.719999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (325, 3, 8, 1993, 32.960000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (326, 3, 9, 1993, 35.689999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (327, 3, 10, 1993, 33.920000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (328, 3, 11, 1993, 35.560000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (329, 3, 12, 1993, 36.840000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (330, 3, 1, 1992, 25.940000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (331, 3, 2, 1992, 24.32);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (332, 3, 3, 1992, 21.399999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (333, 3, 4, 1992, 19.93);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (334, 3, 5, 1992, 24.859999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (335, 3, 6, 1992, 20.210000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (336, 3, 7, 1992, 21.829999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (337, 3, 8, 1992, 22.140000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (338, 3, 9, 1992, 24.629999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (339, 3, 10, 1992, 25.239999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (340, 3, 11, 1992, 22.489999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (341, 3, 12, 1992, 25.239999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (342, 3, 1, 1991, 20.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (343, 3, 2, 1991, 20.719999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (344, 3, 3, 1991, 11.92);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (345, 3, 4, 1991, 4.9900000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (346, 3, 5, 1991, 7.4299999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (347, 3, 6, 1991, 11.19);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (348, 3, 7, 1991, 12.41);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (349, 3, 8, 1991, 15.630000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (350, 3, 9, 1991, 15.630000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (351, 3, 10, 1991, 20.23);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (352, 3, 11, 1991, 25.210000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (353, 3, 12, 1991, 23.710000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (354, 1, 1, 2005, 0.56999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (355, 1, 2, 2005, 0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (356, 1, 3, 2005, 0.72999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (357, 1, 4, 2005, 0.91000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (358, 1, 5, 2005, 0.69999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (360, 1, 7, 2005, 0.029999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (361, 1, 8, 2005, 0);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (362, 1, 9, 2005, 0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (363, 1, 10, 2005, 0.57999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (364, 1, 1, 2004, 0.82999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (365, 1, 2, 2004, 0.39000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (366, 1, 3, 2004, 0.56999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (367, 1, 4, 2004, 0.40999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (368, 1, 5, 2004, 0.40000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (369, 1, 6, 2004, 0.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (370, 1, 7, 2004, 0.72999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (371, 1, 8, 2004, 0.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (372, 1, 9, 2004, 0.17000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (373, 1, 10, 2004, 0.17000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (374, 1, 11, 2004, 0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (375, 1, 12, 2004, 0.85999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (376, 1, 1, 2002, 1.0700000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (377, 1, 2, 2002, 0.31);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (378, 1, 3, 2002, 0.62);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (379, 1, 4, 2002, 0.68000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (380, 1, 5, 2002, 0.089999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (381, 1, 6, 2002, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (382, 1, 7, 2002, 1.1499999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (383, 1, 8, 2002, 0.85999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (384, 1, 9, 2002, 0.82999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (385, 1, 10, 2002, 1.5700000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (386, 1, 11, 2002, 3.3900000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (387, 1, 12, 2002, 2.7000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (388, 1, 1, 2001, 0.77000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (389, 1, 2, 2001, 0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (390, 1, 3, 2001, 0.47999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (391, 1, 4, 2001, 0.83999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (392, 1, 5, 2001, 0.56999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (393, 1, 6, 2001, 0.59999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (394, 1, 7, 2001, 1.1100000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (395, 1, 8, 2001, 0.79000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (396, 1, 9, 2001, 0.44);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (397, 1, 10, 2001, 0.93999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (398, 1, 11, 2001, 1.29);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (399, 1, 12, 2001, 0.73999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (400, 1, 1, 2000, 0.60999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (401, 1, 2, 2000, 0.050000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (402, 1, 3, 2000, 0.13);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (403, 1, 4, 2000, 0.089999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (404, 1, 5, 2000, -0.050000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (405, 1, 6, 2000, 0.29999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (406, 1, 7, 2000, 1.3899999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (407, 1, 8, 2000, 1.21);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (408, 1, 9, 2000, 0.42999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (409, 1, 10, 2000, 0.16);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (410, 1, 11, 2000, 0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (411, 1, 12, 2000, 0.55000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (412, 1, 1, 1999, 0.65000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (413, 1, 2, 1999, 1.29);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (414, 1, 3, 1999, 1.28);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (415, 1, 4, 1999, 0.46999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (416, 1, 5, 1999, 0.050000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (417, 1, 6, 1999, 0.070000000000000007);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (418, 1, 7, 1999, 0.73999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (419, 1, 8, 1999, 0.55000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (420, 1, 9, 1999, 0.39000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (421, 1, 10, 1999, 0.95999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (422, 1, 11, 1999, 0.93999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (423, 1, 12, 1999, 0.73999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (424, 1, 1, 1998, 0.84999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (425, 1, 2, 1998, 0.54000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (426, 1, 3, 1998, 0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (427, 1, 4, 1998, 0.45000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (428, 1, 5, 1998, 0.71999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (429, 1, 6, 1998, 0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (430, 1, 7, 1998, -0.28000000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (431, 1, 8, 1998, -0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (432, 1, 9, 1998, -0.31);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (433, 1, 10, 1998, 0.11);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (434, 1, 11, 1998, -0.17999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (435, 1, 12, 1998, 0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (436, 1, 1, 1997, 0.81000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (437, 1, 2, 1997, 0.45000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (438, 1, 3, 1997, 0.68000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (439, 1, 4, 1997, 0.59999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (440, 1, 5, 1997, 0.11);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (441, 1, 6, 1997, 0.34999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (442, 1, 7, 1997, 0.17999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (443, 1, 8, 1997, -0.029999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (444, 1, 9, 1997, 0.10000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (445, 1, 10, 1997, 0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (446, 1, 11, 1997, 0.14999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (447, 1, 12, 1997, 0.56999999999999995);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (448, 1, 1, 1996, 1.46);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (449, 1, 2, 1996, 0.70999999999999996);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (450, 1, 3, 1996, 0.28999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (451, 1, 4, 1996, 0.93000000000000005);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (452, 1, 5, 1996, 1.28);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (453, 1, 6, 1996, 1.3300000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (454, 1, 7, 1996, 1.2);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (455, 1, 8, 1996, 0.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (456, 1, 9, 1996, 0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (457, 1, 10, 1996, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (458, 1, 11, 1996, 0.34000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (459, 1, 12, 1996, 0.33000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (460, 1, 1, 1995, 1.4399999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (461, 1, 2, 1995, 1.01);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (462, 1, 3, 1995, 1.6200000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (463, 1, 4, 1995, 2.4900000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (464, 1, 5, 1995, 2.1000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (465, 1, 6, 1995, 2.1800000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (466, 1, 7, 1995, 2.46);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (467, 1, 8, 1995, 1.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (468, 1, 9, 1995, 1.1699999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (469, 1, 10, 1995, 1.3999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (470, 1, 11, 1995, 1.51);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (471, 1, 12, 1995, 1.6499999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (472, 1, 1, 1994, 41.32);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (473, 1, 2, 1994, 40.57);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (474, 1, 3, 1994, 43.079999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (475, 1, 4, 1994, 42.859999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (476, 1, 5, 1994, 42.729999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (477, 1, 6, 1994, 48.240000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (478, 1, 7, 1994, 7.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (479, 1, 8, 1994, 1.8500000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (480, 1, 9, 1994, 1.3999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (481, 1, 10, 1994, 2.8199999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (482, 1, 11, 1994, 2.96);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (483, 1, 12, 1994, 1.7);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (484, 1, 1, 1993, 28.77);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (485, 1, 2, 1993, 24.789999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (486, 1, 3, 1993, 27.579999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (487, 1, 4, 1993, 28.370000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (488, 1, 5, 1993, 26.780000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (489, 1, 6, 1993, 30.370000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (490, 1, 7, 1993, 31.010000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (491, 1, 8, 1993, 33.340000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (492, 1, 9, 1993, 35.630000000000003);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (493, 1, 10, 1993, 34.119999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (494, 1, 11, 1993, 36);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (495, 1, 12, 1993, 37.729999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (496, 1, 1, 1992, 25.920000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (497, 1, 2, 1992, 24.48);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (498, 1, 3, 1992, 21.620000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (499, 1, 4, 1992, 20.84);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (500, 1, 5, 1992, 24.5);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (501, 1, 6, 1992, 20.850000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (502, 1, 7, 1992, 22.079999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (503, 1, 8, 1992, 22.379999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (504, 1, 9, 1992, 23.98);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (505, 1, 10, 1992, 26.07);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (506, 1, 11, 1992, 22.890000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (507, 1, 12, 1992, 25.579999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (508, 1, 1, 1991, 20.949999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (509, 1, 2, 1991, 20.199999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (510, 1, 3, 1991, 11.789999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (511, 1, 4, 1991, 5.0099999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (512, 1, 5, 1991, 6.6799999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (513, 1, 6, 1991, 10.83);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (514, 1, 7, 1991, 12.140000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (515, 1, 8, 1991, 15.619999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (516, 1, 9, 1991, 15.619999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (517, 1, 10, 1991, 21.079999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (518, 1, 11, 1991, 26.48);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (519, 1, 12, 1991, 24.149999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (359, 1, 6, 2005, 0);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (26, 2, 5, 2003, 0);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (520, 1, 11, 2005, 0.54000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (521, 1, 12, 2005, 0.40000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (522, 1, 1, 2006, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (523, 1, 2, 2006, 0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (524, 2, 10, 2005, 0.59999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (525, 2, 11, 2005, 0.40000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (526, 2, 12, 2005, -0.01);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (527, 2, 1, 2006, 0.92000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (528, 2, 2, 2006, 0.01);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (529, 1, 3, 2006, 0.27000000000000002);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (530, 1, 4, 2006, 0.12);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (531, 2, 3, 2006, -0.23000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (532, 3, 10, 2005, 0.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (533, 3, 11, 2005, 0.55000000000000004);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (534, 3, 12, 2005, 0.35999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (535, 3, 1, 2006, 0.58999999999999997);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (536, 3, 2, 2006, 0.40999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (537, 3, 3, 2006, 0.42999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (544, 1, 5, 2006, 0.13);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (545, 1, 6, 2006, -0.070000000000000007);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (540, 2, 4, 2006, -0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (541, 2, 5, 2006, 0.38);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (542, 2, 6, 2006, 0.75);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (546, 3, 4, 2006, 0.20999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (547, 3, 5, 2006, 0.10000000000000001);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (548, 3, 6, 2006, -0.20999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (549, 1, 7, 2006, 0.11);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (550, 1, 8, 2006, -0.02);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (551, 1, 9, 2006, 0.16);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (552, 1, 10, 2006, 0.42999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (553, 1, 11, 2006, 0.41999999999999998);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (554, 1, 12, 2006, 0.62);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (555, 1, 1, 2007, 0.48999999999999999);
INSERT INTO periodo_indice (periodo_indice_codigo, indice_reajuste_codigo, mes, ano, valor) VALUES (556, 1, 2, 2007, 0.41999999999999998);




INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (99, 'AUTOS CONCLUSOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (2, 'REDESIGNADA AUDIENCIA DE CONCILIA�AO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (3, 'DESIGNADA AUDIENCIA DE INSTRU��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (4, 'AUTOS CONCLUSOS PARA SENTEN�A');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (5, 'AUTOS CONCLUSOS PARA DESPACHO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (6, 'REU N�O COMPARECEU. REQUERIDA REVELIA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (7, 'REQUERIDA SUSPENS�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (8, 'PROCESSO DISTRIBUIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (9, 'REQUERIDA EXECU��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (10, 'EXPEDIDO MPA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (11, 'REALIZADA PENHORA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (12, 'REALIZADO ACORDO JUDICIAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (13, 'REDESIGNADA AUDIENCIA DE INSTRU��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (14, 'APRESENTADO RECURSO ADMINISTRATIVO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (15, 'REQUERIDA A DESIST�NCIA DO PROCESSO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (17, 'ELABORAR CONTESTA��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (114, 'AUTOS ENVIADOS A TURMA RECURSAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (19, 'REQUERIDO PRAZO PARA PAGAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (20, 'JUNTAMOS COMPROVANTE DE PAGAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (21, 'INTIMADOS A APRESENTAR DEFESA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (22, 'AUTOR INTIMADO A SE MANIFESTAR NOS AUTOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (23, 'CITADOS A NOMEAR BENS A PENHORA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (24, 'INDICAMOS BENS A PENHORA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (25, 'AGUARDANDO DESIGNAR AUDI�NCIA DE INSTRU��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (26, 'JUNTADA DE SUBSTABELECIMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (27, 'MANDADO DISTRIBUIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (28, 'ARQUIVAMENTO COM BAIXA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (29, 'AUSENCIA JUSTIFICADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (30, 'REQUERIDA NOVA AUDIENCIA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (77, 'DESIGNADO JULGAMENTO DE RECURSO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (32, 'SENTEN�A PROCEDENTE EM PARTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (33, 'LIMINAR INDEFERIDA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (34, 'DOCUMENTO JUNTADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (35, 'REQUERIMENTO DE LEVANTAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (36, 'REQUERIDO PRAZO PARA INFORMAR ENDERE�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (37, 'ACORDO HOMOLOGADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (38, 'LIMINAR DEFERIDA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (39, 'APRECIAR� LIMINAR AP�S CITA��O DA (O) R� (U)');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (40, 'EXPEDIDO MANDADO DE BUSCA E APREENS�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (86, 'SENTEN�A DE REVELIA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (120, 'IMPUGNAMOS CONTESTACAO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (43, 'CARTA ENVIADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (44, 'CARTA ENVIADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (45, 'REUNI�O MARCADA PARA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (46, 'REQUERIDA CITA��O POR EDITAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (47, 'DILIGENCIA PROCESSUAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (48, 'INTIMADOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (31, 'APRESENTADA DEFESA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (107, 'AGUARDANDO DESIGNAR LEILAO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (50, 'RECURSO IMPROVIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (51, 'ACORDO CUMPRIDO INTEGRALMENTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (52, 'PROCESSO ARQUIVADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (54, 'REDESIGNADO PRACA E LAILAO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (55, 'DESPACHO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (56, 'PROCESSO ARQUIVADO. CONDENACAO EM CUSTAS.');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (57, 'REALIZADO ACORDO EXTRAJUDICIAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (58, 'ACORDO HOMOLOGADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (101, 'OFERECEMOS EMBARGOS DE DECLA��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (60, 'RECURSO PROVIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (61, 'APRESENTADOS RECIBOS DE PAGAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (62, 'PROCESSO EXTINTO - AUTOR NAO COMPARECE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (63, 'REQUERIDA EXPEDI��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (64, 'REPLICA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (67, 'ACORDO DESCUMPRIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (68, 'ACORDO RENEGOCIADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (65, 'REQUERIDO DESARQUIVAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (69, 'REU QUITOU INTEGRALMENTE D�BITO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (70, 'SENTEN�A PUBLICADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (71, 'PETICIONAMOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (75, 'REQUERIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (108, 'MANDADO CUMPRIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (73, 'DILIGENCIA - DR. SANDRO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (74, 'DILIGENCIA - DR. SYLVIO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (76, 'OFERECEMOS CONTRA-RAZ�ES');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (1, 'DESIGNADA AUDIENCIA CONCILIA��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (78, 'COBRAR HONORARIOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (79, 'EMBARGOS � EXECU��O OPOSTOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (80, 'DESISTIMOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (81, 'HONOR�RIOS PAGOS PELO CONDOM�NIO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (82, 'JULGAMENTO MARCADO TURMA RECURSAL');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (83, 'DECIS�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (85, 'REDESIGNADO JULGAMENTO DE RECURSO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (84, 'AGUARDANDO REDESIGNAR AUDI�NCIA DE CONCILIA��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (41, 'SENTEN�A PROCEDENTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (89, 'PUBLICA��O PREPARADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (87, 'RECURSO INOMINADO INTERPOSTO - AUTOR');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (109, 'CALCULOS ORDENADOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (92, 'BUSCA E APREENS�O DO VE�CULO EFETUADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (91, 'RECURSO PROVIDO EM PARTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (93, 'PRAZO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (94, 'CITA��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (53, 'DESIGNADA PRACA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (95, 'DESIGNADO LEIL�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (96, 'RETORNO CONTATO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (98, 'DILIGENCIA CUMPRIDA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (97, 'ACORDO EM ANDAMENTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (100, 'SENTEN�A IMPROCEDENTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (59, 'DEVEDOR FEZ CONTATO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (49, 'APRESENTADO RECURSO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (102, 'RECURSO ORDINARIO INTERPOSTO - RECLAMANTE');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (103, 'RECURSO ORDINARIO INTERPOSTO - RECLAMADO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (88, 'RECURSO INOMINADO INTERPOSTO - REU');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (104, 'AGUARDANDO REDESIGNAR AUDI�NCIA DE INSTRU��O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (105, 'JUNTADA DE DOCUMENTOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (106, 'REQUERIDA DESIGNA��O DE AUDI�NCIA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (110, 'CALCULOS EFETUADOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (18, 'INTIMADOS A COMPLEMENTAR CUSTAS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (111, 'AUTOS EM CARGA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (112, 'DEVOLU�A� DOS AUTOS � VARA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (113, 'AGUARDANDO RETORNO DE OFICIO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (115, 'CERTID�O');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (116, 'DESPACHO SANEADOR');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (117, 'LIGAR PARA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (118, 'APELACAO INTERPOSTA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (119, 'AGRAVO INTERPOSTO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (42, 'CARTA REENVIADA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (121, 'RENUNCIAMOS');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (122, 'OFERECEMOS CONTRA-MINUTA');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (123, 'RECURSO ESPECIAL INTERPOSTO - REU');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (124, 'GUIA/ALVARA DE RETIRADA EXPEDIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (125, 'MANDADO EXPEDIDO');
INSERT INTO tipo_andamento (tipo_andamento_codigo, des_tipo_andamento) VALUES (16, 'PAGA PARCELA');



INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (100, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (100, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (100, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (100, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (2, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (2, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (2, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (2, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (3, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (3, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (3, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (3, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (4, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (4, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (4, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (4, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (5, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (5, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (5, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (5, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (6, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (6, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (6, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (6, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (7, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (7, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (7, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (7, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (8, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (8, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (9, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (9, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (9, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (9, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (10, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (10, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (10, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (10, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (11, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (11, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (11, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (11, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (12, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (12, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (12, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (12, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (13, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (13, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (13, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (13, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (14, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (15, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (15, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (15, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (15, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (15, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (17, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (17, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (17, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (17, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (114, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (114, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (115, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (19, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (19, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (19, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (19, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (20, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (20, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (20, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (20, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (20, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (21, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (21, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (21, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (21, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (21, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (22, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (22, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (22, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (22, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (23, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (23, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (23, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (23, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (24, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (24, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (24, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (24, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (25, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (25, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (25, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (25, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (26, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (26, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (26, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (26, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (27, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (27, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (27, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (27, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (28, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (28, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (28, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (28, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (28, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (29, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (29, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (29, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (29, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (30, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (30, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (30, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (30, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (56, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (56, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (56, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (56, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (93, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (32, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (32, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (32, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (32, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (33, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (33, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (33, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (33, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (34, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (34, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (34, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (34, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (35, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (35, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (35, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (35, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (35, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (36, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (36, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (36, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (36, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (36, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (37, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (37, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (37, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (37, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (37, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (38, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (38, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (38, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (38, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (38, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (39, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (39, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (39, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (39, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (40, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (40, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (40, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (40, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (93, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (91, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (93, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (87, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (115, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (93, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (43, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (44, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (45, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (46, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (46, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (46, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (46, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (47, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (48, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (48, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (48, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (48, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (48, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (31, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (31, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (31, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (31, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (31, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (108, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (108, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (108, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (108, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (18, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (50, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (50, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (50, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (50, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (50, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (51, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (52, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (52, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (52, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (52, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (52, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (77, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (54, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (55, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (55, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (55, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (55, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (55, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (57, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (58, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (101, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (60, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (60, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (60, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (60, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (60, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (61, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (62, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (62, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (63, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (63, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (63, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (63, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (63, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (64, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (64, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (67, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (67, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (65, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (67, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (67, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (68, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (68, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (68, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (68, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (69, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (69, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (70, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (70, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (70, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (70, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (71, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (71, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (71, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (71, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (71, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (18, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (73, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (74, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (75, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (76, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (76, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (76, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (76, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (1, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (1, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (1, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (1, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (77, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (77, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (77, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (78, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (78, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (78, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (78, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (79, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (79, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (79, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (79, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (80, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (80, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (80, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (80, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (81, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (81, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (82, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (83, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (83, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (83, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (83, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (85, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (85, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (85, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (84, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (84, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (84, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (85, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (41, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (41, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (41, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (41, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (41, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (86, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (86, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (86, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (86, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (89, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (89, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (89, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (89, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (92, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (93, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (94, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (94, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (94, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (94, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (94, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (53, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (53, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (53, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (53, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (95, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (95, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (95, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (95, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (1, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (99, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (96, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (98, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (97, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (99, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (99, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (99, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (99, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (59, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (59, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (101, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (101, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (101, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (49, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (102, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (103, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (88, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (104, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (104, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (104, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (104, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (105, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (105, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (105, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (105, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (105, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (106, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (107, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (109, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (109, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (109, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (109, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (110, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (110, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (110, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (110, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (18, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (111, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (111, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (111, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (111, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (111, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (112, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (112, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (112, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (112, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (112, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (113, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (113, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (113, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (113, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (113, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (115, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (115, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (115, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (116, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (117, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (118, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (118, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (119, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (42, 6);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (120, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (120, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (120, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (120, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (121, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (121, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (121, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (121, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (121, 5);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (122, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (122, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (122, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (123, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (123, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (123, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (123, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (124, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (124, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (124, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (124, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (125, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (125, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (125, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (125, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (16, 1);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (16, 2);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (16, 3);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (16, 4);
INSERT INTO tipo_andamento_juizo (tipo_andamento_codigo, juizo_codigo) VALUES (16, 6);

ALTER TABLE ONLY parceiro
    ADD CONSTRAINT "PARCEIRO_PK" PRIMARY KEY (pessoa_codigo);

ALTER TABLE ONLY parte
    ADD CONSTRAINT "PARTE_PRIMARY_KEY" PRIMARY KEY (processo_codigo, pessoa_codigo);

ALTER TABLE ONLY acordo
    ADD CONSTRAINT acordo_pkey PRIMARY KEY (acordo_codigo);

ALTER TABLE ONLY andamento
    ADD CONSTRAINT andamento_pkey PRIMARY KEY (andamento_codigo);

ALTER TABLE ONLY banco
    ADD CONSTRAINT banco_pkey PRIMARY KEY (num_banco);

ALTER TABLE ONLY cheque
    ADD CONSTRAINT cheque_pkey PRIMARY KEY (cheque_codigo);

ALTER TABLE ONLY classe_processo
    ADD CONSTRAINT classe_processo_pkey PRIMARY KEY (classe_processo_codigo);

ALTER TABLE ONLY comarca
    ADD CONSTRAINT comarca_pkey PRIMARY KEY (comarca_codigo);

ALTER TABLE ONLY diretorio
    ADD CONSTRAINT diretorio_pkey PRIMARY KEY (diretorio_codigo);

ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_pkey PRIMARY KEY (documento_codigo);

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (estado_codigo);

ALTER TABLE ONLY forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (forma_pagamento_codigo);

ALTER TABLE ONLY indice_reajuste
    ADD CONSTRAINT indice_reajuste_pkey PRIMARY KEY (indice_reajuste_codigo);

ALTER TABLE ONLY juizo
    ADD CONSTRAINT juizo_pkey PRIMARY KEY (juizo_codigo);

ALTER TABLE ONLY jurisprudencia
    ADD CONSTRAINT jurisprudencia_pkey PRIMARY KEY (jurisprudencia_codigo);

ALTER TABLE ONLY link
    ADD CONSTRAINT link_pkey PRIMARY KEY (link_codigo);


ALTER TABLE ONLY municipio
    ADD CONSTRAINT municipio_pkey PRIMARY KEY (municipio_codigo);


ALTER TABLE ONLY orgao_judiciario
    ADD CONSTRAINT orgao_judiciario_pkey PRIMARY KEY (orgao_judiciario_codigo);


ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pagamento_codigo);


ALTER TABLE ONLY parcela_acordo
    ADD CONSTRAINT parcela_acordo_pkey PRIMARY KEY (parcela_acordo_codigo);



ALTER TABLE ONLY periodo_indice
    ADD CONSTRAINT periodo_indice_pkey PRIMARY KEY (periodo_indice_codigo);



ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (pessoa_codigo);



ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_num_unique UNIQUE (str_numero_processo);


ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_pkey PRIMARY KEY (processo_codigo);


ALTER TABLE ONLY taxa
    ADD CONSTRAINT taxa_pkey PRIMARY KEY (taxa_codigo);


ALTER TABLE ONLY tipo_andamento_juizo
    ADD CONSTRAINT tipo_andamento_juizo_pkey PRIMARY KEY (tipo_andamento_codigo, juizo_codigo);


ALTER TABLE ONLY tipo_andamento
    ADD CONSTRAINT tipo_andamento_pkey PRIMARY KEY (tipo_andamento_codigo);


ALTER TABLE ONLY parceiro
    ADD CONSTRAINT "PESSOA_PARCEIRO" FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(pessoa_codigo);


ALTER TABLE ONLY acordo
    ADD CONSTRAINT acordo_processo_codigo_fkey FOREIGN KEY (processo_codigo) REFERENCES processo(processo_codigo);

ALTER TABLE ONLY andamento
    ADD CONSTRAINT andamento_processo_codigo_fkey FOREIGN KEY (processo_codigo) REFERENCES processo(processo_codigo);

ALTER TABLE ONLY andamento
    ADD CONSTRAINT andamento_tipo_andamento_codigo_fkey FOREIGN KEY (tipo_andamento_codigo) REFERENCES tipo_andamento(tipo_andamento_codigo);


ALTER TABLE ONLY cheque
    ADD CONSTRAINT cheque_fkey FOREIGN KEY (num_banco) REFERENCES banco(num_banco);


ALTER TABLE ONLY comarca
    ADD CONSTRAINT comarca_municipio_codigo_fkey FOREIGN KEY (municipio_codigo) REFERENCES municipio(municipio_codigo);

ALTER TABLE ONLY diretorio
    ADD CONSTRAINT diretorio_diretorio_pai_codigo_fkey FOREIGN KEY (diretorio_pai_codigo) REFERENCES diretorio(diretorio_codigo);


ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_diretorio_codigo_fkey FOREIGN KEY (diretorio_codigo) REFERENCES diretorio(diretorio_codigo);

ALTER TABLE ONLY acordo
    ADD CONSTRAINT indice_reajuste_codigo_fkey FOREIGN KEY (indice_reajuste_codigo) REFERENCES indice_reajuste(indice_reajuste_codigo);


ALTER TABLE ONLY jurisprudencia
    ADD CONSTRAINT jurisprudencia_documento_codigo_fkey FOREIGN KEY (documento_codigo) REFERENCES documento(documento_codigo);


ALTER TABLE ONLY municipio
    ADD CONSTRAINT municipio_estado_codigo_fkey FOREIGN KEY (estado_codigo) REFERENCES estado(estado_codigo);

ALTER TABLE ONLY orgao_judiciario
    ADD CONSTRAINT orgao_judiciario_juizo_codigo_fkey FOREIGN KEY (juizo_codigo) REFERENCES juizo(juizo_codigo);


ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_cheque_codigo_fkey FOREIGN KEY (cheque_codigo) REFERENCES cheque(cheque_codigo);


ALTER TABLE ONLY parcela_acordo
    ADD CONSTRAINT parcela_acordo_acordo_codigo_fkey FOREIGN KEY (acordo_codigo) REFERENCES acordo(acordo_codigo);


ALTER TABLE ONLY parcela_acordo
    ADD CONSTRAINT parcela_acordo_pagamento_codigo_fkey FOREIGN KEY (pagamento_codigo) REFERENCES pagamento(pagamento_codigo) ON DELETE CASCADE;


ALTER TABLE ONLY parte
    ADD CONSTRAINT parte_pessoa_codigo_fkey FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(pessoa_codigo);


ALTER TABLE ONLY parte
    ADD CONSTRAINT parte_processo_codigo_fkey FOREIGN KEY (processo_codigo) REFERENCES processo(processo_codigo);


ALTER TABLE ONLY periodo_indice
    ADD CONSTRAINT periodo_indice_indice_reajuste_codigo_fkey FOREIGN KEY (indice_reajuste_codigo) REFERENCES indice_reajuste(indice_reajuste_codigo);

ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_classe_processo_codigo_fkey FOREIGN KEY (classe_processo_codigo) REFERENCES classe_processo(classe_processo_codigo);


ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_comarca_codigo_fkey FOREIGN KEY (comarca_codigo) REFERENCES comarca(comarca_codigo);


ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_orgao_judiciario_codigo_fkey FOREIGN KEY (orgao_judiciario_codigo) REFERENCES orgao_judiciario(orgao_judiciario_codigo);



ALTER TABLE ONLY taxa
    ADD CONSTRAINT taxa_proc_codigo_fkey FOREIGN KEY (processo_codigo) REFERENCES processo(processo_codigo);



ALTER TABLE ONLY tipo_andamento_juizo
    ADD CONSTRAINT tipo_andamento_juizo_juizo_codigo_fkey FOREIGN KEY (juizo_codigo) REFERENCES juizo(juizo_codigo);


ALTER TABLE ONLY tipo_andamento_juizo
    ADD CONSTRAINT tipo_andamento_juizo_tipo_andamento_codigo_fkey FOREIGN KEY (tipo_andamento_codigo) REFERENCES tipo_andamento(tipo_andamento_codigo);
    
    
select setval('seq_tipo_andamento', 125);

select setval('seq_periodo_indice', 556);

select setval('seq_orgao_judiciario', 154);

select setval('seq_classe_processo', 25);

select setval('seq_juizo', 6);