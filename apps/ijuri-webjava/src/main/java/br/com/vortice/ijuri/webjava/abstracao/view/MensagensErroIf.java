package br.com.vortice.ijuri.webjava.abstracao.view;


/**
 * As mensagens devem ser colocadas no ApplicationResources.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Antonio Amadeu
 * @version 1.0
 */

public interface MensagensErroIf {
  String VAZIO = "error.validar.VAZIO";
  String NUMERO_INTEIRO = "error.validar.NUMERO_INTEIRO";
  String NUMERO_FLOAT = "error.validar.NUMERO_FLOAT";
  String NUMERICO = "error.validar.NUMERICO";
  String TAMANHO_MAX = "error.validar.TAMANHO_MAX";
  String NUMERO_POSITIVO = "error.validar.NUMERO_POSITIVO";
  String LETRA_OBRIGATORIA = "error.validar.LETRA_OBRIGATORIA";
  String MAIOR_QUE_ZERO = "error.validar.MAIOR_QUE_ZERO";

  String CHAVE_DUPLICADA = "error.aplicacao.CHAVE_DUPLICADA";
  String CRIACAO_ENTIDADE = "error.aplicacao.CRIACAO_ENTIDADE";
  String INESPERADO = "error.ambiente.INESPERADO";
  String DATA_INVALIDA = "error.validar.DATA";
  String DATA_FUTURA_INVALIDA = "error.validar.DATA_FUTURA_INVALIDA";
  String REGISTRO_NAO_ENCONTRADO = "error.aplicacao.REGISTRO_NAO_ENCONTRADO";
  String CAMPO_INVALIDO = "error.aplicacao.CAMPO_INVALIDO";
  String REGISTRO_EM_USO = "error.aplicacao.REGISTRO_EM_USO";

  String DATA_DEVE_SER_MAIOR =  "error.aplicacao.DATA_DEVE_SER_MAIOR";
  String DATA_DEVE_SER_MENOR = "error.aplicacao.DATA_DEVE_SER_MENOR";
  String DATA_DEVE_SER_MAIOR_IGUAL ="error.aplicacao.DATA_DEVE_SER_MAIOR_IGUAL";
  String DATA_DEVE_SER_MENOR_IGUAL ="error.aplicacao.DATA_DEVE_SER_MENOR_IGUAL";
  String DATA_DEVE_SER_IGUAL = "error.aplicacao.DATA_DEVE_SER_IGUAL";
  
  String USUARIO_INVALIDO = "error.aplicacao.USUARIO_INVALIDO";
  String PARTE_EXISTENTE = "error.aplicacao.PARTE_EXISTENTE";
  String UPDATE_PARCELA_QUITADA = "error.aplicacao.UPDATE_PARCELA_QUITADA";
  String NOME_DOCUMENTO_INVALIDO = "error.aplicacao.NOME_DOCUMENTO_INVALIDO";
  String FORMA_PAGAMENTO_INVALIDA = "error.aplicacao.FORMA_PAGAMENTO_INVALIDA";
  
  
}
