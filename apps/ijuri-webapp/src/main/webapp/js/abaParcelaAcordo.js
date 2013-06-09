function Aba(menu,imgDir,conteudo){
	this.menu = menu;
	this.imgDir = imgDir;
	this.conteudo = conteudo;
}

var abasParcela = new Array();
	abasParcela[0] = new Aba('acordoMenu','acordoImg','divAcordo');
	abasParcela[1] = new Aba('parcelasMenu','parcelasImg','divParcelas');

function openAba(menu,imgDir,conteudo){
	for(i=0;i<abasParcela.length;i++){
		m = document.getElementById(abasParcela[i].menu);
		m.className = 'corpoAbaInativa';
		
		img = document.getElementById(abasParcela[i].imgDir);
		img.src = urlImageAbaInativa;

		c = document.getElementById(abasParcela[i].conteudo);
		c.style.display = 'none';
	}

	m = document.getElementById(menu);
	m.className = 'corpoAbaAtiva';

	img = document.getElementById(imgDir);
	img.src = urlImageAbaAtiva;

	c = document.getElementById(conteudo);
	c.style.display = 'block';
}