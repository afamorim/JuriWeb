package br.com.vortice.ijuri.business.monetario.rn;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.core.monetario.CorrecaoMonetariaVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class CorrecaoMonetariaRN extends RegraNegocio {
	
	private IndiceReajusteRN indiceReajusteRN;
	
	public CorrecaoMonetariaRN(){
	}
	
	public CorrecaoMonetariaVO calcularCorrecao(CorrecaoMonetariaVO vo) throws AmbienteException, AplicacaoException{
		PeriodoIndiceFiltroAssembler periodo = new PeriodoIndiceFiltroAssembler();
		periodo.setIndiceReajuste(vo.getIndiceReajuste());
		periodo.setPeriodoIndiceInicio(vo.getDataInicial());
		periodo.setPeriodoIndiceFim(vo.getDataFinal());
		Float valor = indiceReajusteRN.findValorAcumladoByPeriodo(periodo);
		Float valorAcumlado = null;//new Float(((valor != null) ? valor : 0) * vo.getValorHistorico().floatValue());
		if (valor != null) valorAcumlado = new Float(valor.floatValue() * vo.getValorHistorico().floatValue());
		else valorAcumlado = new Float(0);
		CorrecaoMonetariaVO correcao = new CorrecaoMonetariaVO();
		correcao.setDataInicial(vo.getDataInicial());
		correcao.setDataFinal(vo.getDataFinal());
		correcao.setIndiceReajusteValor(new Double(valor.doubleValue()));
		correcao.setValorHistorico(new Double(valorAcumlado.doubleValue()));
		long  dias = (vo.getDataFinal().getTime() - vo.getDataInicial().getTime())/(24*60*60*1000);
		double juros = (dias * (vo.getJuros().doubleValue() * 0.01)/30) * valorAcumlado.doubleValue();
		correcao.setJuros(new Double(juros));
		double multa = valorAcumlado.doubleValue() * vo.getMulta().doubleValue() * 0.01;
		correcao.setMulta(new Double(multa));
		double honorario = (valorAcumlado.doubleValue()+juros+multa) * vo.getHonorarios().doubleValue() * 0.01;
		correcao.setHonorarios(new Double(honorario));
		return correcao;
	}

	public void setIndiceReajusteRN(IndiceReajusteRN indiceReajusteRN) {
		this.indiceReajusteRN = indiceReajusteRN;
	}
	
}
