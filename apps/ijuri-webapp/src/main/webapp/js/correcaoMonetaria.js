//VARIAVEIS GLOBAIS

var xmlHttp = null;

//VARIAVEIS GLOBAIS

function validaCalculo(){
	if (document.forms[0].elements['cliente'].value != ""){
		if (document.forms[0].elements['devedor'].value != ""){
			if (document.forms[0].elements['valorHistorico'].value != ""){
				if (document.forms[0].elements['indiceReajusteCodigo'].value != ""){
					if (document.forms[0].elements['dataInicial'].value != ""){
						if (document.forms[0].elements['dataFinal'].value != ""){
							if (document.forms[0].elements['multa'].value != ""){
								if (document.forms[0].elements['juros'].value != ""){
									if (document.forms[0].elements['honorarios'].value != ""){
										return true;
									}else{
										alert("O campo honorarios não pode ser vazio para o calculo da correção monetaria.");
										return false;
									}
								}else{
									alert("O campo juros não pode ser vazio para o calculo da correção monetaria.");
									return false;
								}
							}else{
								alert("O campo multa não pode ser vazio para o calculo da correção monetaria.");
								return false;
							}
						}else{
							alert("O campo data final não pode ser vazio para o calculo da correção monetaria.");
							return false;
						}
					}else{
						alert("O campo data inicial não pode ser vazio para o calculo da correção monetaria.");
						return false;
					}
				}else{
					alert("O campo indice Reajuste não pode ser vazio para o calculo da correção monetaria.");
					return false;
				}
			}else{
				alert("O campo devedor não pode ser vazio para o calculo da correção monetaria.");
				return false;
			}
		}else{
			alert("O campo cliente não pode ser vazio para o calculo da correção monetaria.");
			return false;
		}
	}else{
	}
}

function carregaVariaveis(){
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}else{
		alert("Seu browser não da suporte a esta pagina.");
		return null;
	}
}

function excluirCorrecao(url, codigo){
	
	carregaVariaveis();
	
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.onreadystatechange=montaCorrecao;
	arrayCorrecao = convertBase64ToReqAjax(document.forms[0].elements['arrayCorrecao'].value);
	xmlHttp.send("codigo=" + codigo + "&arrayCorrecao=" + arrayCorrecao);
}

function calculaCorrecao(url){
	if (validaCalculo()){
		carregaVariaveis();
		
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xmlHttp.onreadystatechange=montaCorrecao;
		
		cliente = document.forms[0].elements['cliente'].value;
		devedor = document.forms[0].elements['devedor'].value;
	
		valorHistorico = document.forms[0].elements['valorHistorico'].value;
		dataInicial = document.forms[0].elements['dataInicial'].value;
		dataFinal = document.forms[0].elements['dataFinal'].value;
		juros = document.forms[0].elements['juros'].value;
		honorarios = document.forms[0].elements['honorarios'].value;
		multa = document.forms[0].elements['multa'].value;
		indiceReajusteCodigo = document.forms[0].elements['indiceReajusteCodigo'].value;
		arrayCorrecao = (document.forms[0].elements['arrayCorrecao'] != null) ? convertBase64ToReqAjax(document.forms[0].elements['arrayCorrecao'].value) : "";
		//alert("arrayCorrecao= " + arrayCorrecao);
		//alert("dataInicial=" + dataInicial + "&dataFinal=" + dataFinal + "&juros=" + juros + "&honorarios=" + honorarios + "&multa=" + multa + "&indiceReajusteCodigo=" + indiceReajusteCodigo + "&valorHistorico="  + valorHistorico + "&arrayCorrecao= " + arrayCorrecao + "&cliente=" + escape(cliente) + "&devedor=" + escape(devedor));
		xmlHttp.send("dataInicial=" + dataInicial + "&dataFinal=" + dataFinal + "&juros=" + juros + "&honorarios=" + honorarios + "&multa=" + multa + "&indiceReajusteCodigo=" + indiceReajusteCodigo + "&valorHistorico="  + valorHistorico + "&arrayCorrecao= " + arrayCorrecao + "&cliente=" + cliente + "&devedor=" + devedor);
		document.forms[0].dataInicial.focus();
	}
}

function montaCorrecao(){
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			response = xmlHttp.responseText;
			div = window.document.getElementById("listaCorrecao");
			div.innerHTML = response;
		}
	}
}

function limpaCorrecao(){
	document.forms[0].elements['cliente'].value = "";
	document.forms[0].elements['devedor'].value = "";
	document.forms[0].elements['valorHistorico'].value = "";
	document.forms[0].elements['indiceReajusteCodigo'].value = "";
	document.forms[0].elements['dataInicial'].value = "";
	document.forms[0].elements['dataFinal'].value = "";
	document.forms[0].elements['multa'].value = "";
	document.forms[0].elements['juros'].value = "";
	document.forms[0].elements['honorarios'].value = "20";
}