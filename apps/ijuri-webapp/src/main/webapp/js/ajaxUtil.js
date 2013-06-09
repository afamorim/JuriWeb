	/**
	* Objeto javascript que representa uma requisição HTTP com base em XML (XMLHTTPRequest) e 
	* a relação de sincronia com HTML. 
	* ***Obs: Quando a tela fazer mais de uma XMLHTTPRequest ao mesmo tempo dê um setTimeout(300)
	* 		  para cada criação do objeto.
	* @author Antonio Amadeu
	* 
	*/
	
	function AjaxRequest(url,idDiv,imgLoading,form,chamadaAssincrona){
		//Atributos
		this.url = url;
		this.imgLoading = imgLoading;
		if (imgLoading == null || typeof(imgLoading) == "undefined")
			this.semPainel = true;
		this.idDiv = idDiv;
		this.form = form;
		this.chamadaAssincrona = true;
		this.painelTemplate = "<table width=\'100%\' align=\'center\' height=\'100%\' cellspacing=\'0\' cellpadding=\'0\' "+
	    				"border=\'0\' class=\'AvisoCallback\'> 	<tr> 		<td align=\'center\'> 	"+
	    				"<table cellspacing=\'0\' cellpadding=\'0\' border=\'0\'> 				<tr> 	"+
	    				" <td style=\'font-size:9px;\'>Carregando...&nbsp;</td> 	"+
	    				" <td><img src=\'"+imgLoading+"\' width=\'16\' height=\'16\' border=\'0\'></td> 	"+
	    				"</tr> 			</table>  		</td> 	</tr> </table>";

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
	
	AjaxRequest.prototype.atualizarDivViaGet = function(){
	   
	    //Obtém o objeto HTML
	    objetoHTML=document.getElementById(this.idDiv);
	    
   	    if (this.semPainel != true){
		    //Exibe "Carregando..."
		    objetoHTML.innerHTML = this.painelTemplate;
	    }

	    var request = this.httpRequest;
	    if (this.chamadaAssincrona == true){
			request.open("GET", this.getUrlNoCache(),true);
		}else{
			//default
			request.open("GET", this.getUrlNoCache(),false);
		}

	    var semPainel = this.semPainel;
	    request.onreadystatechange = function(){
	    	//Quando a requisição HTTP é concluída
			if (request.readyState==4) {
				//status ok
				if (request.status == 200){
					processResponse(request,objetoHTML,semPainel);					
				}else{
			        alert("Houve um problema ao obter os dados:\n" + request.statusText);
			    } 
	  		}
	    }
	 	
	 	request.send(null);
		
	};
	
	AjaxRequest.prototype.buildQueryString = function() {
	  var qs = '';
	  var theForm = this.form;
	  if (typeof theForm != "undefined"){
	  	  for (e=0;e<theForm.elements.length;e++) {
		    if (theForm.elements[e].name!='') {
		      qs+=(qs=='')?'':'&';
		      qs+=theForm.elements[e].name+'='+escape(theForm.elements[e].value);
		    }
		  }
	  }
	  return qs;
	};
	
	AjaxRequest.prototype.atualizarDivViaPost = function(params){
		    
	    //Obtém o objeto HTML
	    objetoHTML=document.getElementById(this.idDiv);
	
	    //Exibe "Carregando..."
	    objetoHTML.innerHTML = this.painelTemplate;
		
		var reqLocal = this.httpRequest;
		if (this.chamadaAssincrona == true){
			reqLocal.open("POST", this.getUrlNoCache(),true);
		}else{
			//default
			reqLocal.open("POST", this.getUrlNoCache(),false);
		}

		reqLocal.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=iso-8859-1');
		
	 	var semPainel = this.semPainel;
	    reqLocal.onreadystatechange = function(){
	    	//Quando a requisição HTTP é concluída
			if (reqLocal.readyState==4) {
				//status ok
				if (reqLocal.status == 200){
					processResponse(reqLocal,objetoHTML,semPainel);					
				}else{
			        alert("Houve um problema ao obter os dados:\n" + reqLocal.statusText);
			    } 
	  		}
	    }
	 	
	 	// Adiciona os campos do form no corpo da requisição HTTP
		if (typeof params != "undefined"){
			parametros = params;
		}else{
			parametros = this.buildQueryString();
		}
		
		reqLocal.send(parametros); 	
		
	};

	AjaxRequest.prototype.abrirPopUpViaPost = function(width,height,semPainel){
	    
	    //Obtém o objeto HTML
	    objetoHTML=document.getElementById(this.idDiv);
	
	    //Exibe "Carregando..."
	    objetoHTML.innerHTML = this.painelTemplate;
		
		var reqLocal = this.httpRequest;
		if (this.chamadaAssincrona == true){
			reqLocal.open("POST", this.url,true);
		}else{
			//default
			reqLocal.open("POST", this.url,false);
		}

		reqLocal.setRequestHeader('Content-Type','application/x-www-form-urlencoded;');
		
	    reqLocal.onreadystatechange = function(){	
	    			//Quando a requisição HTTP é concluída
	    			if (reqLocal.readyState==4) {
	    				//status ok
	    				if (reqLocal.status == 200){
		    				//setTimeout("objetoHTML.innerHTML = reqLocal.responseText;",1000);
							 newWindow=window.open('',new Date()+'x','toolbar=no,scrollbars=no,width='+width+',height='+height);
							 newWindow.document.open("text/html", "replace");
							 newWindow.document.write(reqLocal.responseText);
							 newWindow.document.close();
							 setTimeout("objetoHTML.style.display = 'none';",1000);
	    				}else{
					        alert("Houve um problema ao obter os dados:\n" + reqLocal.statusText);
					    } 
			  		}
	 	}
		
	 	// Adiciona os campos do form no corpo da requisição HTTP
		parametros = this.buildQueryString();
		reqLocal.send(parametros); 	
		
	};

	AjaxRequest.prototype.abrirPopUpViaGet = function(width,height,semPainel){
	    
	    //Obtém o objeto HTML
	    objetoHTML=document.getElementById(this.idDiv);
	
	    //Exibe "Carregando..."
	    objetoHTML.innerHTML = this.painelTemplate;
		
		var reqLocal = this.httpRequest;
		if (this.chamadaAssincrona == true){
			reqLocal.open("GET", this.url,true);
		}else{
			//default
			reqLocal.open("GET", this.url,false);
		}

		reqLocal.onreadystatechange = function(){	
	    			//Quando a requisição HTTP é concluída
	    			if (reqLocal.readyState==4) {
	    				//status ok
	    				if (reqLocal.status == 200){
		    				 dataAtual = new Date();
							 newWindow=window.open('','win'+dataAtual.getMilliseconds(),'toolbar=no,width='+width+',height='+height);
							 newWindow.document.open("text/html", "replace");
							 newWindow.document.write(reqLocal.responseText);
							 newWindow.document.close();

							 setTimeout("objetoHTML.style.display = 'none'; ",2000);
	    				}else{
					        alert("Houve um problema ao obter os dados:\n" + reqLocal.statusText);
					    } 
			  		}
	 	}
		
	 	reqLocal.send(null); 	
		
	};
	
	AjaxRequest.prototype.getUrlNoCache = function(){
		data = new Date();
		noCache = 'noCache='+data.getMilliseconds();
		if (this.url.indexOf('?')>0){
			this.url += '&'+noCache;
		}else{
			this.url += '?'+noCache;		
		}
		
		return this.url;
	};
	
	function processResponse(request,objetoHTML,semPainel){
		//Regular Expression pra achar tags de script no html
		scriptMatch = '(?:<script.*?>)((\n|.)*?)(?:<\/script>)';
	    exp = new RegExp(scriptMatch,'img');
	  	
        //extrai HTML sem scripts (javascript)
        responseText = request.responseText.replace(exp,'');
		//seta o HTML no div
		if (semPainel != true)
			setTimeout("objetoHTML.innerHTML = responseText;",300);  	
		else
			objetoHTML.innerHTML = responseText;
		
		//Obtêm o vetor de bloco de scripts do HTML
		scripts = request.responseText.match(exp);
        if (scripts != null){
            match = new RegExp(scriptMatch, 'im');
            for (i=0;i<scripts.length;i++){
                    //Executa script
                    eval(scripts[i].match(match)[1]);
            }
        }
	}
	
	
	function convertBase64ToReqAjax(text){
		return escape(text).replace(new RegExp('\\+', 'g'), '%2b');
	}
	
	
	
	