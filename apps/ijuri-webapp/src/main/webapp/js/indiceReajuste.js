//VARIAVEIS GLOBAIS
var xmlHttp = null;

var selIndice = null;

var codigoIndice = null;
//VARIAVEIS GLOBAIS

function atualizaIndice(objIndice, url, codigo){
	codigoIndice = codigo;
	selIndice = objIndice;
	selIndice.options[0] = null;
	selIndice.options[0] = new Option("Carregando...", "", false, false);
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}else{
		alert("Seu browser n?o da suporte a esta pagina.");
		return null;
	}
	xmlHttp.open("GET", url, true);
	xmlHttp.onreadystatechange=carregaIndice;
	xmlHttp.send(null);
}

function limparIndice(){
	for (i = 0; i < selIndice.options.length; i++){
		selIndice.options[i] = null;
	}
	selIndice.options[0] = new Option('Selecione', '', false, false);
}

function carregaIndice(){
	selComponent = window.document.forms[0].elements["indiceReajusteCodigo"];
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			response = xmlHttp.responseXML;
			data = response.getElementsByTagName("componente");
			limparIndice();
			for (i = 0; i < data.length; i++){
				var item = new Option(data[i].getElementsByTagName("nome").item(0).firstChild.data,data[i].getElementsByTagName("codigo").item(0).firstChild.data, false, false);
				selComponent.options[selComponent.length] = item;							
			}
			for (i = 0; i < selComponent.options.length; i++){
				if (selComponent.options[i].value == codigoIndice){
					selComponent.options[i].selected = true;
				}
			}
		}
	}
}

function openIndiceReajuste(url){
	window.open(url, "indiceReajuste", "width=600,height=400");
}