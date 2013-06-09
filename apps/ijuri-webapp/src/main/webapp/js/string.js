/**
 * Adiciona m?todo lpad() ? classe String.
 * Preenche a String ? esquerda com o caractere fornecido,
 * at? que ela atinja o tamanho especificado.
 */
String.prototype.lpad = function(pSize, pCharPad)
{
	var str = this;
	var dif = pSize - str.length;
	var ch = String(pCharPad).charAt(0);
	for (; dif>0; dif--) str = ch + str;
	return (str);
} //String.lpad

/**
 * Adiciona m?todo trim() ? classe String.
 * Elimina brancos no in?cio e fim da String.
 */
String.prototype.trim = function()
{
	return this.replace(/^\s*/, "").replace(/\s*$/, "");
} //String
