function Aba(menu,imgDir,conteudo){
	this.menu = menu;
	this.imgDir = imgDir;
	this.conteudo = conteudo;
}

var abasAcordo = new Array();
	abasAcordo[0] = new Aba('acordoMenu','acordoImg','divAcordo');
	abasAcordo[1] = new Aba('processoMenu','processoImg','divProcesso');

function openAba(menu,imgDir,conteudo){
	for(i=0;i<abasAcordo.length;i++){
		m = document.getElementById(abasAcordo[i].menu);
		m.className = 'corpoAbaInativa';
		
		img = document.getElementById(abasAcordo[i].imgDir);
		img.src = urlImageAbaInativa;
	
		c = document.getElementById(abasAcordo[i].conteudo);
		c.style.display = 'none';
	}

	m = document.getElementById(menu);
	m.className = 'corpoAbaAtiva';

	img = document.getElementById(imgDir);
	img.src = urlImageAbaAtiva;
	
	c = document.getElementById(conteudo);
	c.style.display = 'block';
}