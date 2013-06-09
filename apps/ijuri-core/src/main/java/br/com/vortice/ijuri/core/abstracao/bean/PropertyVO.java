package br.com.vortice.ijuri.core.abstracao.bean;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class PropertyVO extends ValueObject{
    private Class type;
    private Object value;
    private String name;
    
    public PropertyVO() {}
    
    public PropertyVO(Class type, Object value, String name) {
        this.type = type;
        this.value = value;
        this.name = name;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the type.
     */
    public Class getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(Class type) {
        this.type = type;
    }
    /**
     * @return Returns the value.
     */
    public Object getValue() {
        return value;
    }
    /**
     * @param value The value to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }
    
    public String toString(){
        return name+"->"+value;
    }
}
