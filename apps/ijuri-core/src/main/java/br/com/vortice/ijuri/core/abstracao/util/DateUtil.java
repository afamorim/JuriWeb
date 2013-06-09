package br.com.vortice.ijuri.core.abstracao.util;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;

public class DateUtil {

    private static DateUtil converter;
    
    private Locale locale;
    
    public DateUtil(Locale locale){
        this.locale = locale;
    }
    
    private DateUtil(){}
    
    public static DateUtil getInstance(Locale locale){
        if (converter==null){
            converter = new DateUtil(locale); 
        }
        return converter;
    }
    
    public static DateUtil getInstance(){
        if (converter==null){
            converter = new DateUtil(); 
        }
        return converter;
    }
    
    public Date convertStringToDate(String valor) throws AmbienteException{
        try{
          SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy",new DateFormatSymbols(locale));
          return dateFormatter.parse(valor);
        }catch (Exception e) {
          throw new AmbienteException(e);
        }
     }
    
    public Timestamp convertStringToDateTime(String valor) throws AmbienteException{
        try{
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm",new DateFormatSymbols(locale));
            return new Timestamp(dateFormatter.parse(valor).getTime());
        }catch (Exception e) {
          throw new AmbienteException(e);
        }
     }
    
    public String convertDateTimeToString(Timestamp valor) throws AmbienteException{
        try{
          SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm",new DateFormatSymbols(locale));
          return dateFormatter.format(new Date(valor.getTime()));
        }catch (Exception e) {
          throw new AmbienteException(e);
        }
     }
    
    public String convertDateToString(Date valor) throws AmbienteException{
        try{
          SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy",new DateFormatSymbols(locale));
          return dateFormatter.format(valor);
        }catch (Exception e) {
          throw new AmbienteException(e);
        }
     }
    
    public void setLocale(Locale locale){
        this.locale = locale;
    } 
    
    
    /**
     * Retorna uma nova data somada de um mês.
     * @param data
     * @return
     */
    public Date calcularProximoMes(Date data)
    {
         Calendar dataAtual = Calendar.getInstance();
         
         dataAtual.setTime(data);
         
         int indiceMes = dataAtual.get(Calendar.MONTH)+1;
         
         dataAtual.set(Calendar.MONTH,indiceMes);
        
         return dataAtual.getTime();
   }
    
   public String formatarMesAnoNumero(Integer mes,Integer ano){ 
       Date date = convertToDate(null,mes,ano);
       return new SimpleDateFormat("MM/yyyy",this.locale).format(date);
   }
   
   public String formatarMesAnoDescricao(Integer mes,Integer ano){ 
       Date date = convertToDate(null,mes,ano);
       return new SimpleDateFormat("MMM/yyyy",this.locale).format(date);
   }
   
   public Date convertToDate(Integer dia, Integer mes,Integer ano){
       Calendar c = Calendar.getInstance();
       
       if (dia==null)
           c.set(Calendar.DAY_OF_MONTH,1);
       else
           c.set(Calendar.DAY_OF_MONTH,dia.intValue());
       
       c.set(Calendar.MONTH,mes.intValue()-1);
       
       c.set(Calendar.YEAR,ano.intValue());
       
       return c.getTime(); 
   }
   
   public int subtractDays(Date dataInicial,Date dataFinal){
       
       long res = dataFinal.getTime()-dataInicial.getTime();
       
       Calendar c = Calendar.getInstance();
       c.setTimeInMillis(res);
       
       return c.get(Calendar.DAY_OF_YEAR)+1;
       
   }
}
