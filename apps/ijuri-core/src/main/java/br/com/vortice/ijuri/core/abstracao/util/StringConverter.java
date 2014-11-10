package br.com.vortice.ijuri.core.abstracao.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;


/**
 * This class is converts a java.util.Date to a String
 * and a String to a java.util.Date. It is used by
 * BeanUtils when copying properties.  
 * 
 * @author amadeu
 */
public class StringConverter implements Converter {
     
    protected final DecimalFormat formatter;
    
    public StringConverter(Locale locale){
        super();
        formatter  = new DecimalFormat("#,##0.00",new DecimalFormatSymbols(locale));
    }
    
    
    /**
     * Convert a String to a Date and a Date to a String
     *
     * @param type the class type to output
     * @param value the object to convert
     * @return object the converted object (Date or String)
     */
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else {
            try {
                if (value instanceof String) {
                        if (value.toString().trim().length()==0) {
                            return null;
                        }
                        return value.toString();
                } else if (value instanceof Timestamp) {
                    return DateUtil.getInstance().convertDateTimeToString((Timestamp) value);    
                } else if (value instanceof Date) {
                    return DateUtil.getInstance().convertDateToString((Date) value);
                } else if (value instanceof Float){
                    Float valueFloat = (Float)value;
                    return formatter.format(valueFloat.doubleValue());
                } else if (value instanceof Double){
                    Double valueDouble = (Double)value;
                    return formatter.format(valueDouble.doubleValue());
                } else if (value instanceof Integer || value instanceof Long){
                    if (((Number)value).intValue()==0)
                        return null; 
                    else
                        return value.toString();
                } else if (value instanceof Double){
                    Double valueDouble = (Double)value;
                    return formatter.format(valueDouble.doubleValue());
                }
            } catch (Exception pe) {
                pe.printStackTrace();
            }
        }

        throw new ConversionException("Não pode converter "
                                      + value.getClass().getName() + " para "
                                      + type.getName() + "!");
    }
}
