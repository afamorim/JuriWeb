
	function AjaxRequestCombo(combo,url,textoPrimReg){
		this.url = url;
		
		this.combo = combo;
		
		this.textoPrimReg = textoPrimReg;
		
		this.codigoSel = combo.value;
		
		//Criar o objeto da Requisição
	    var req = null;
	    if (window.ActiveXObject)
		{
		    // Microsoft
		    try{
		    	req = new ActiveXObject("Microsoft.XMLHTTP");
		    }catch(Exception){
		    	//caso for IE bem antigo
		    	req = new ActiveXObject("Msxml2.XMLHTTP"); 
		    }
		}
		else if (window.XMLHttpRequest)
		{
		    // Outros
		    req = new XMLHttpRequest();
		}
		
		this.httpRequest = req;
	}

	var request = null;
	
	AjaxRequestCombo.prototype.atualizar = function(){
		   	var comboBox = this.combo;
		   	comboBox.options[0] = null;
		   	comboBox.options[0] = new Option("Carregando...", "", false, false);
		    
		    request = this.httpRequest;
		    request.open("GET", this.getUrlNoCache(),true);
			
			var codigoSelecionado = this.codigoSel;
			var textoPrimRegCmb = this.textoPrimReg;
			request.onreadystatechange = function(){
		    	//Quando a requisição HTTP é concluída
				if (request.readyState==4) {
					//status ok
					if (request.status == 200){
						processResponse(request,comboBox,codigoSelecionado,textoPrimRegCmb);
					}else{
				        alert("Houve um problema ao obter os dados:\n" + request.statusText);
				    } 
		  		}
		    }
		 	
		 	//request.send(null);
		 	setTimeout("request.send(null);",600);
			
		};
		
	AjaxRequestCombo.prototype.getUrlNoCache = function(){
		data = new Date();
		noCache = 'noCache='+data.getMilliseconds();
		if (this.url.indexOf('?')>0){
			this.url += '&'+noCache;
		}else{
			this.url += '?'+noCache;		
		}
		
		return this.url;
	};
	
	function processResponse(request,comboBox,codigoSel,textoPrimReg){
		var response = request.responseXML;
		var dados = response.getElementsByTagName("componente");
		
		limparCombo(comboBox,textoPrimReg);
		
		for (i = 0; i < dados.length; i++){
			//Código do registro
			var codigo = dados[i].getElementsByTagName("codigo").item(0).firstChild.data;
			
			//Texto do Registro a ser exibido
			var texto = dados[i].getElementsByTagName("nome").item(0).firstChild.data;
			
			//Criar um item no ComboBox
			var item = new Option(texto,codigo, false, false);
			comboBox.options[i+1] = item;
		}
	}
	
	function limparCombo(combo,textoPrimReg){
		for (i = 0; i < combo.options.length; i++){
			combo.options[i] = null;
		}

		if (typeof(textoPrimReg) != "undefined"){
			combo.options[0] = new Option(textoPrimReg, '', false, false);
		}else{
			combo.options[0] = new Option('-- SELECIONE --', '', false, false);
		}
	}
