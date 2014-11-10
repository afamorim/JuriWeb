package br.com.vortice.ijuri.core.abstracao.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * This class is converts a java.util.Date to a String
 * and a String to a java.util.Date. It is used by
 * BeanUtils when copying properties.  Registered
 * for use in BaseManager.
 * 
 * <p>
 * <a href="DateConverter.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.1 $ $Date: 2008/01/02 02:41:49 $
 */
public class DateTimeConverter implements Converter {
     
    public DateTimeConverter(){
        super();
    }
    
    
    /**
     * Convert a String to a Date and a Date to a String
     *
     * @param type the class type to output
     * @param value the object to convert
     * @return object the converted object (Date or String)
     */
    public Object convert(Class type, Object value) {
        // for a null value, return null
        if (value == null) {
            return null;
        } else {
            try {
                if (value instanceof String) {
                        if (value.toString().trim().length()==0) {
                            return null;
                        }
                        return DateUtil.getInstance().convertStringToDateTime(value.toString());
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
