<%@ taglib uri="/tags/c" prefix="c"%>
<%@ taglib uri="/tags/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:view>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>JuriWeb - Contato</title>
		<link href="<c:url value="/nucleo/style/juriweb-site.css"/>" rel="stylesheet" type="text/css" />
		<style type="text/css">
			<!--
			.style1 {font-size: 12px; font-family: "Lucida Bright";}
			-->
        </style>
  
	</head>

	<body>
		<h:form>
			<div id="Conteudo">
				<h2>Contato : <span class="style1">+55 71 3497-0085 | +55 71 8802-0529</span></h2>
				<fieldset>
				<legend>Preencha o formul&aacute;rio:</legend><br />
					<label for="nome">Nome:</label><h:inputText title="NOME" size="30" styleClass="CadForm" id="nome" value="#{ContatoBean.contato.nome}" /><br />
					<label for="email">E-mail:</label><h:inputText title="E-MAIL" size="30" styleClass="CadForm" id="email" value="#{ContatoBean.contato.email}" /><br />
					<label for="estado">Estado:</label><h:inputText title="ESTADO" size="30" styleClass="CadForm" id="estado" value="#{ContatoBean.contato.estado}" /><br />
					<label for="cidade">Cidade:</label><h:inputText title="CIDADE" size="30" styleClass="CadForm" id="cidade" value="#{ContatoBean.contato.cidade}" /><br />
					<label for="telefone">Telefone:</label><h:inputText title="ESTADO" size="30" styleClass="CadForm" id="estado" value="#{ContatoBean.contato.telefone}" /><br />
					<label for="comentario">Coment&aacute;rio:</label><textarea cols="30" name="txt_comentario" title="COMENTÁRIO" id="comentario" rows="5" class="CadFormTextarea"></textarea><br />
					<label>&nbsp;</label><a class="botao" href="#">Enviar</a><br /><br />
				</fieldset>
			</div>
		</h:form>
	</body>
</html>
</f:view>