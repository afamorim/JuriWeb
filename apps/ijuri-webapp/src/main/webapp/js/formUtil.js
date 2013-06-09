//Uso global entre as funções 

var isNS4 = (navigator.appName=="Netscape")?1:0;
var TECLAS_CONTROLE = 0;
var TECLA_BACKSPACE = 8;
var TECLA_ENTER = 13;

function retornoKeyPress(e){
    if(!isNS4){//Internet explorer
        event.returnValue = false;
    }
    else{ //Netscape - Mozilla
        return false;
    }
}


function limparCampos(textBox,hidden){
	document.forms[0].elements[textBox].value = '';
	document.forms[0].elements[hidden].value = '';	
}

function openPopUpDefault(url,nomeTela){
	winprops = 'resizable=yes,menubar=no,scrollbars=yes,toolbar=no,left=80,top=40,';
	winprops += 'width=640,height=300';
	window.open(url, nomeTela, winprops);
}

function maxWindow(win)
{
	win.moveTo(0,0);

	if (document.all)
	{
	  win.top.window.resizeTo(screen.availWidth,screen.availHeight);
	}
	
}

function openPopUpMaximizado(url,nomeTela){
	winprops = 'resizable=yes,menubar=no,scrollbars=yes,toolbar=no,left=80,top=40';
	maxWindow(window.open(url, nomeTela, winprops));
}

function openPopUp(url,nomeTela,width,height){
	winprops = 'resizable=yes,menubar=no,scrollbars=yes,toolbar=no,left=80,top=40,';
	winprops += 'width='+width+',height='+height;
	window.open(url, nomeTela, winprops);

}

function buildQueryString(theForm) {
	  var qs = '';
	  if (typeof theForm != "undefined"){
		  for (e=0;e<theForm.elements.length;e++) {
		    if (theForm.elements[e].name!='') {
		      qs+=(qs=='')?'?':'&';
		      qs+=theForm.elements[e].name+'='+escape(theForm.elements[e].value);
		    }
		  }
	  }
	  return qs;
}	  

function excluirRegistros(nomeCampo,url){
	checkBoxs = document.forms[0].elements[nomeCampo];
	//se existe algum checkbox
	if (typeof checkBoxs != "undefined" ){
		//se existe um ?nico checkbox
		if (typeof checkBoxs.length == "undefined" ){
			if (checkBoxs.checked){
					if (confirm("Deseja excluir definitivamente o(s) registro(s)?")){
						document.forms[0].action = url;
						document.forms[0].submit();
						return true; 
					}else{
						return false;
					}
			}
		}else{
			//se existe muitos checkbox
			for(i=0;i<checkBoxs.length;i++){
				if (checkBoxs[i].checked){
					if (confirm("Deseja excluir definitivamente o(s) registro(s)?")){
						document.forms[0].action = url;
						document.forms[0].submit();
						return true; 
					}else{
						return false;
					}
				}	
			}
		}
	}
	alert('Selecione pelo menos um registro.');
	return false;
}

function limpar(){
		document.forms[0].acao.value = 'L';
}

function fecharPopUp(janela,msg){
	janela.close();
	alert(msg);
}

function formataData(campo,teclaPres){ 
  
   var tecla;
   if(!isNS4){//Internet explorer
        tecla = teclaPres.keyCode;
    }
   else{ //Netscape - Mozilla
        tecla = teclaPres.which;
   }
    
   separador = '/'; 
   conjunto1 = 2; 
   conjunto2 = 5; 
    
   if (tecla==TECLAS_CONTROLE || tecla==TECLA_BACKSPACE || tecla==TECLA_ENTER)
     return true;
    
   if (tecla >= 48 && tecla <= 57){ 
     if (campo.value.length == conjunto1){ 
       campo.value = campo.value + separador; 
     } 
     if (campo.value.length == conjunto2){ 
       campo.value = campo.value + separador; 
     } 
   }else{ 
      alert("Por favor, digite número.");
      return retornoKeyPress(teclaPres);
  } 
}

function formataMesAno(campo,teclapres) {
  var tecla;
  
  if(!isNS4){//Internet explorer
        tecla = teclapres.keyCode;
    }
    else{ //Netscape - Mozilla
        tecla = teclapres.which;
    }

	vr = campo.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length + 1;

   

  if ((tecla<48 || tecla>57) && (tecla!=TECLAS_CONTROLE && tecla!=TECLA_BACKSPACE)){
     alert("Por favor, digite número.");
     return retornoKeyPress(teclapres);
  }

	if ( tecla != 9 && tecla != 8 ){
		if ( tam > 2 && tam < 7 )
			campo.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, tam ); }
} 

function validarNumero(e,decimal,texto){
    if(!isNS4){//Internet explorer
        digito = e.keyCode;
    }
    else{ //Netscape - Mozilla
        digito = e.which;
    }
  
  TECLA_VIRGULA = 44;
  TECLA_PONTO = 46;
  TECLA_BARRA_DE_ESPACO = 32;
  
  /*
  * número inteiro
  */
  
	if(!decimal){        
    if((isNaN(String.fromCharCode(digito)) || digito==TECLA_BARRA_DE_ESPACO) 
      && (digito!=TECLAS_CONTROLE && digito!=TECLA_BACKSPACE)){
			alert("Por favor, digite número."); 
            return retornoKeyPress(e);
    }  
	}else{        
         /*
         * Se ? número flutuante.
         */
         if (digito==TECLA_PONTO){
             alert("Utilize v?rgula para separar casas decimais.");
             return retornoKeyPress(e);
         }
         /*
         *  A tecla virgula n?o pode ser escrita mais 
         *  de um vez.
         */
         else if((digito == TECLA_VIRGULA) && texto.indexOf(",")!=-1){
            alert("Por favor, digite número.");
            return retornoKeyPress(e);
         }
         /*
         * Fora número s? pode ser digitado a v?rgula.
         */
         else if (isNaN(String.fromCharCode(digito)) && 
           (digito!=TECLAS_CONTROLE && digito!=TECLA_BACKSPACE && digito != TECLA_VIRGULA) 
              || digito==TECLA_BARRA_DE_ESPACO){
            alert("Por favor, digite número.");
            return retornoKeyPress(e);      
         /*
         * Deve se digitar pelo menos um n?mero antes da v?gula.  
         */
         }else if (digito == TECLA_VIRGULA && texto.length==0){
            alert("Deve se digitar pelo menos um número antes da vírgula.");
            return retornoKeyPress(e);            
         }
         
	}
}

function formataHora(objeto){
	campo = eval (objeto); 
	separador = ':'; 
	conjunto1 = 2;
	if (window.event.keyCode >= 48 && window.event.keyCode <= 57){ 
		if (campo.value.length == conjunto1){
			campo.value = campo.value + separador; 
		}
		if (campo.value.length == 5){ 
				window.event.keyCode = 0;
		} 
	}else{
	window.event.keyCode = 0;
	}
 
}

function formataDataHora(teclapres) {
	var tecla = teclapres.keyCode;
	vr = event.srcElement.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ":", "" );
	vr = vr.replace( " ", "" );
	tam = vr.length + 1;
	
		
	if ( tecla != 9 && tecla != 8 ){
	  if (tecla >= 48 && tecla <= 57){ 
		if ( tam > 2 && tam < 5 )
		{
			event.srcElement.value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		}
		if ( tam >= 5 && tam <= 8 )
		{
			event.srcElement.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 );
		}
		if ( tam >= 10 && tam <= 11 )
		{
		  	event.srcElement.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 )+
		  		" "+vr.substr( 8, tam-9 );	
		}
		if (tam>=12 && tam<=13){
			event.srcElement.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 )+
				   " "+vr.substr( 8, 2 ) + ':'+ vr.substr( 10, 2 );		
		}
	  }
	}
}


function formataCpf(teclapres) {
	var tecla = teclapres.keyCode;
	vr = event.srcElement.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;

	if (tam < 11 && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		event.srcElement.value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		event.srcElement.value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		event.srcElement.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		event.srcElement.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		event.srcElement.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		event.srcElement.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}

function formataCgc(teclapres) {
	var tecla = teclapres.keyCode;
	vr = event.srcElement.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;

	if (tam < 14 && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		event.srcElement.value = vr ; }
	 	if ( (tam > 2) && (tam <= 6) ){
	 		event.srcElement.value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 7) && (tam <= 9) ){
	 		event.srcElement.value = vr.substr( 0, tam - 6 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 10) && (tam <= 12) ){
	 		event.srcElement.value = vr.substr( 0, tam - 9 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 13) && (tam <= 14) ){
	 		event.srcElement.value = vr.substr( 0, tam - 12 ) + '.' + vr.substr( tam - 12, 3 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		event.srcElement.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}

function FiltraCampo(campo){
	var s = "";
	var cp = "";
	vr = document.forms[0].elements[campo].value;
	tam = vr.length;
	for (i = 0; i < tam ; i++) {  
		if (vr.substring(i,i + 1) != "/" && vr.substring(i,i + 1) != "-" && vr.substring(i,i + 1) != "."  && vr.substring(i,i + 1) != "," ){
		 	s = s + vr.substring(i,i + 1);}
	}
	return s;
}

function formataValor(campo,tammax) {
	var tecla = event.keyCode;
	vr = campo.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	tam = vr.length;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		campo.value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		campo.value = vr.substr( 0, tam - 2 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		campo.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		campo.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		campo.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		campo.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ;}
	}

}

function maskFormatPeriodo(component){
    var date = component.value;
    var size = date.length;
    if(  size > 2 ){
        var element = date.substring(2,3);
        if( element != "/" ){
            date = date.substring(0,2) + "/" + date.substring(2);
            component.value = date.replace('//','/');
            return; 
        }
    } 
 }
 	
 function validarMesAno(campo){
    valorCampo = campo.value;
 	if (valorCampo.trim()=="" || valorCampo=="mm/aaaa")
 		return false;
 	else{ 
 		if (valorCampo.trim().length!=7) 
 			return false;
 		return validarData("01/"+valorCampo); 
 	}	
 }
 
 function validarMesAnoNaoRequerido(campo){
    valorCampo = campo.value;
 	if (valorCampo.trim().length!=7) 
 		return false;
 	return validarData("01/"+valorCampo); 
 }
 
 function validarAno(campo){
    valorCampo = campo.value;
 	if (valorCampo.trim()=="" || valorCampo=="aaaa")
 		return false;
 	else{ 
 		if (valorCampo.trim().length!=4 || isNaN(valorCampo.trim())) 
 			return false;
 		else
 			return true;	
 	}	
 }
 
 function validarData(dataStr){
 	try{
	 	partes = dataStr.split("/");
	 	if (partes.length==3){
	 	    mes = partes[1]-1;
	 	    dt = new Date(partes[2],mes,partes[0]);
	 	 	if (parseInt(partes[2])!= dt.getFullYear() || 
		 			mes != (dt.getMonth()) || 
		 			  parseInt(partes[0])!= dt.getDate()){
		 		return false;				
		 	}
		 	
		    return (dt.toString() != 'NaN');	
	    }else{
	    	return false;
	    }
    }catch(Exception){
    	return false;
    }
 }
 
 function createDate(dataStr){
	 	partes = dataStr.split("/");
 	    mes = partes[1]-1;
 	    dt = new Date(partes[2],mes,partes[0]);
 	 	return dt;
 }
 
 function setarLabel(campo,valor){
 	if (campo.value.trim()=='')
 		campo.value=valor;	
 }
 
 function limparLabel(campo,valor){
 	if (campo.value.trim()==valor){
 		campo.value = '';
 		campo.focus();
 	}
 }
 
 function soNumero(campo,teclapres) {
  var tecla;
  
  if(!isNS4){//Internet explorer
        tecla = teclapres.keyCode;
    }
    else{ //Netscape - Mozilla
        tecla = teclapres.which;
    }

	vr = campo.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length + 1;

   

  if ((tecla<48 || tecla>57) && (tecla!=TECLAS_CONTROLE && tecla!=TECLA_BACKSPACE)){
     alert("Por favor, digite número.");
     return retornoKeyPress(teclapres);
  }
}			 