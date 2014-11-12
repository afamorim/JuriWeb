var strOptPerfil = "";
var janelaPerfil = null;

function carregaPerfis(){
	PerfilRN.findAll(carregaOptPerfil);
}

function carregaOptPerfil(perfis){
	alert(perfis);
	var strOptPerfil = "";
	DWRUtil.addOptions("selPerfil", perfis, "codigo", "nome");
}

function createWindowPerfil(pessoaCodigo)
{
	var newWindowModel = new DHTMLSuite.windowModel({windowsTheme:true,id:'newWindow',title:'Perfis',xPos:200,yPos:200,minWidth:100,minHeight:100 } );
	newWindowModel.addTab(
	{ 
	id:'myTab',htmlElementId:'myTab',tabTitle:'Sele��o de Perfil',
	textContent:'<label>Perfil:</label> <select id="selPerfil">'+strOptPerfil+'</select><br/>' +
		'<input type="button" name="bt_perfil" value="Selecione" ' + 
		'onclick="document.forms[0].pessoaCodigo.value='+pessoaCodigo+';'+
			'document.forms[0].perfilCodigo.value=document.getElementById(\'selPerfil\').value;janelaPerfil.close();' + 
			'criarUsuario();" ' + 
		'class="login_botao_sist"/>' 
	} );
	janelaPerfil = new DHTMLSuite.windowWidget(newWindowModel);
	janelaPerfil.init();
	carregaPerfis();
}

function criarUsuario(){
	pessoaCodigo = document.forms[0].pessoaCodigo.value;
	perfilCodigo = document.forms[0].perfilCodigo.value;
	if (document.forms[0].perfilCodigo.value != "" && document.forms[0].pessoaCodigo.value != ""){
		var perfil = {codigo: perfilCodigo};
		var pessoa = {codigo: pessoaCodigo};
		try{
			PessoaRN.criarUsuario(pessoa, perfil, userCriadoSucesso);
		}catch(e){
			alert(e);
		}
	}else{
		alert("Selecione uma Pessoa e Perfil v�lidos.");
	}
}

function userCriadoSucesso(){
	alert("Usu�rio criado com sucesso.");
	document.getElementById("acaoUsuario").innerHTML = "" + 
	"<a href='#' onclick='ativarUsuario("+ document.forms[0].pessoaCodigo.value +");' title='Ativar Usu�rio'> " +
	"	<img src='"+ IMG_USER_DESATIVADO +"' border='0' title='Ativar Usu�rio'/>" +
	"</a>";
}

function desativarUsuario(codigo){
	if (confirm("Deseja desativar o usu�rio?")){
		pessoa = {codigo: codigo};
		document.forms[0].pessoaCodigo.value = codigo;
		try{
			PessoaRN.desativarUsuario(pessoa, userDesativado);
		}catch(e){
			alert(e);
		}
	}
}

function userDesativado(){
	alert("Usu�rio desativado com sucesso.");
	document.getElementById("acaoUsuario").innerHTML = "" + 
	"<a href='#' onclick='ativarUsuario("+ document.forms[0].pessoaCodigo.value +");' title='Ativar Usu�rio'> " +
	"	<img src='"+ IMG_USER_DESATIVADO +"' border='0' title='Ativar Usu�rio'/>" +
	"</a>";
}

function ativarUsuario(codigo){
	if (confirm("Deseja ativar o usu�rio?")){
		pessoa = {codigo: codigo};
		document.forms[0].pessoaCodigo.value = codigo;
		try{
			PessoaRN.ativarUsuario(pessoa, userAtivado);
		}catch(e){
			alert(e);
		}
	}
}

function userAtivado(){
	alert("Usu�rio ativado com sucesso.");
	document.getElementById("acaoUsuario").innerHTML = "" + 
	"<a href='#' onclick='desativarUsuario("+ document.forms[0].pessoaCodigo.value +");' title='Desativar Usu�rio'> " +
	"	<img src='"+ IMG_USER_ATIVADO +"' border='0' title='Desativar Usu�rio'/>" +
	"</a>";
}