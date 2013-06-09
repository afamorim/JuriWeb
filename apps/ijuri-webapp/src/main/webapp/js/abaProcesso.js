function Aba(menu,imgDir,conteudo){
	this.menu = menu;
	this.imgDir = imgDir;
	this.conteudo = conteudo;
}

var abas = new Array();
	abas[0] = new Aba('andamentoMenu','andamentoImg','divAndamento');
	abas[1] = new Aba('partesMenu','partesImg','divPartes');
	abas[2] = new Aba('taxasMenu','taxasImg','divTaxas');

function openAba(menu,imgDir,conteudo){
	for(i=0;i<abas.length;i++){
		m = document.getElementById(abas[i].menu);
		m.className = 'corpoAbaInativa';
		
		//img = document.getElementById(abas[i].imgDir);
		//img.src = '../../img/aba/aba-right-inativa.gif';

		c = document.getElementById(abas[i].conteudo);
		c.style.display = 'none';
	}

	m = document.getElementById(menu);
	m.className = 'corpoAbaAtiva';

	//img = document.getElementById(imgDir);
	//img.src = '../../img/aba/aba-right-ativa.gif';

	c = document.getElementById(conteudo);
	c.style.display = 'block';
}