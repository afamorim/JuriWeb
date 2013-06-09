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
		<title>JuriWeb - Demo</title>
		<link href="<c:url value="/nucleo/style/juriweb-site.css"/>" rel="stylesheet" type="text/css" />
	</head>

	<body>
	<div id="Conteudo">
	<h2>Demo</h2>
	<div id="Login">
<fieldset>
				<legend>Autenticação:</legend>
						<label for="login">Login:</label><input name="login" id="login" type="text" size="8" maxlength="24" value="adm"/><br />
						<label for="senha">Senha:</label><input name="senha" id="senha" type="password" size="8" maxlength="24" value="adm"/>
						<a class="botao" href="<c:url value="/admin/index.jsp"/>" onclick="">Enviar</a><br />
				<br />
			  </fieldset>
			</div>
	</div>
	</body>
</html>
</f:view>