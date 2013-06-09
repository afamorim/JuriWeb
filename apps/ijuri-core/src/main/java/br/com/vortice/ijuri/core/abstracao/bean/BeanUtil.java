package br.com.vortice.ijuri.core.abstracao.bean;

import java.beans.PropertyDescriptor;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.PropertyUtils;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Amadeu
 */

public class BeanUtil {
    public static PropertyVO executePropertyGet(String propertyName,Object objeto) throws AmbienteException{
        try {
            PropertyDescriptor propertyInfo = null;
            Object objectTemp = null;
            String propertSimples = null;
            Object objectCurrent = objeto;
            
            if (propertyName.indexOf(".")<0){
                propertyInfo = PropertyUtils.getPropertyDescriptor(objeto,propertyName);
            }else{
                StringTokenizer strTokenizer = new StringTokenizer(propertyName, ".");
                for (int j = 0; j < strTokenizer.countTokens(); j++) {
                    propertSimples = strTokenizer.nextElement().toString();
                    objectTemp = objectCurrent;
                    propertyInfo = PropertyUtils.getPropertyDescriptor(objectTemp,
                            propertSimples);
                    objectCurrent = propertyInfo.getReadMethod().invoke(objectTemp,
                            null);
                    if (objectCurrent == null) {
                        objectCurrent = propertyInfo.getPropertyType().newInstance();
                        propertyInfo.getWriteMethod().invoke(objectTemp,
                                new Object[] { objectCurrent });
                    }
                    
                }
                propertSimples = strTokenizer.nextElement().toString();
                propertyInfo = PropertyUtils.getPropertyDescriptor(objectCurrent,
                        propertSimples);
            }
            PropertyVO propertyVO = new PropertyVO(propertyInfo.getPropertyType(),null,propertyInfo.getName());
            propertyVO.setValue(propertyInfo.getReadMethod().invoke(objectCurrent, null));
            return propertyVO;
        } catch (Exception e) {
            throw new AmbienteException("Erro ao tentar executar o GET da property: --- "+propertyName+" ---",e);
        }
    }
    
    public static Object executePropertySet(String propertyName,Object objeto,Object valor) throws AmbienteException{
        try {
            
            PropertyDescriptor propertyInfo = null;
            Object objectTemp = null;
            String propertSimples = null;
            Object objectCurrent = objeto;
            
            if (propertyName.indexOf(".")<0){
                propertyInfo = PropertyUtils.getPropertyDescriptor(objeto,propertSimples);
            }else{
                StringTokenizer strTokenizer = new StringTokenizer(propertyName, ".");
                for (int j = 0; j < strTokenizer.countTokens(); j++) {
                    propertSimples = strTokenizer.nextElement().toString();
                    objectTemp = objectCurrent;
                    propertyInfo = PropertyUtils.getPropertyDescriptor(objectTemp,
                            propertSimples);
                    objectCurrent = propertyInfo.getReadMethod().invoke(objectTemp,
                            null);
                    if (objectCurrent == null) {
                        objectCurrent = propertyInfo.getPropertyType().newInstance();
                        propertyInfo.getWriteMethod().invoke(objectTemp,
                                new Object[] { objectCurrent });
                    }
                    
                }
                propertSimples = strTokenizer.nextElement().toString();
                propertyInfo = PropertyUtils.getPropertyDescriptor(objectCurrent,
                        propertSimples);
            }
            return propertyInfo.getWriteMethod().invoke(objectCurrent, new Object[]{valor});
        } catch (Exception e) {
            throw new AmbienteException("Erro ao tentar executar o SET da property: --- "+propertyName+" ---",e);
        }
    }
    
    
    
}
