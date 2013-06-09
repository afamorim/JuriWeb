package br.com.vortice.ijuri.core.monetario;

import java.util.Calendar;
import java.util.Date;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class PeriodoIndiceFiltroAssembler extends ValueObject {
    private Date periodoIndiceInicio;
    private Date periodoIndiceFim; 
    
    private IndiceReajusteVO indiceReajuste;
    
    public Integer getMesPeriodoIndiceInicio(){
        if (periodoIndiceInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoIndiceInicio);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getMesPeriodoIndiceFim(){
        if (periodoIndiceFim!=null){ 
            Calendar c = Calendar.getInstance();
            c.setTime(periodoIndiceFim);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getAnoPeriodoIndiceInicio(){
        if (periodoIndiceInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoIndiceInicio);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }
    
    public Integer getAnoPeriodoIndiceFim(){
        if (periodoIndiceFim!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoIndiceFim);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }
    

    public IndiceReajusteVO getIndiceReajuste() {
        return indiceReajuste;
    }


    public void setIndiceReajuste(IndiceReajusteVO indiceReajuste) {
        this.indiceReajuste = indiceReajuste;
    }
    

    public Date getPeriodoIndiceFim() {
        return periodoIndiceFim;
    }
    

    public void setPeriodoIndiceFim(Date periodoIndiceFim) {
        this.periodoIndiceFim = periodoIndiceFim;
    }
    

    public Date getPeriodoIndiceInicio() {
        return periodoIndiceInicio;
    }

    
    public void setPeriodoIndiceInicio(Date periodoIndiceInicio) {
        this.periodoIndiceInicio = periodoIndiceInicio;
    }
    
   }
