//VARIAVEIS GLOBAIS
var xmlHttp = null;

//VARIAVEIS GLOBAIS

function atualizaPeriodo(url, codigo){
	if (codigo != ""){
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}else{
			alert("Seu browser n?o da suporte a esta pagina.");
			return null;
		}
		url = url + "?indiceCodigo=" + codigo;
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange=carregaPeriodo;
		xmlHttp.send(null);
	}
}


function carregaPeriodo(){
	var mes = "";
	var ano = "";
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			response = xmlHttp.responseXML;
			data = response.getElementsByTagName("periodo");
			for (i = 0; i < data.length; i++){
				mes = parseInt(data[i].getElementsByTagName("mes").item(0).firstChild.data);
				ano = parseInt(data[i].getElementsByTagName("ano").item(0).firstChild.data);
				if (mes == 12){
					mes = 1;
					ano = ano + 1;
				}else{
					mes = mes + 1;
				}
				document.forms[0].elements['mes'].value = mes;
				document.forms[0].elements['ano'].value = ano;
			}
		}
	}
	if (mes == ""){
		document.forms[0].elements['mes'].value = "";
		document.forms[0].elements['ano'].value = "";
	}
}