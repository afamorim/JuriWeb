package br.com.vortice.ijuri.core.abstracao.relatorio;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;

import org.apache.commons.beanutils.PropertyUtils;

public class JavaBeanReportDataSource implements JRRewindableDataSource {
   
	  private Collection dados = null;
	  private Iterator iterator = null;
	  private Object currentBean = null;
	
	  public JavaBeanReportDataSource(Collection beans){
          this.dados = beans;

          if (this.dados != null)
          {
                  this.iterator = this.dados.iterator();
          }
	  }


      /**
       * Método chamado pela abstração do Jasper Reports para preencher a próxima
       * linha de um determinado grupo.
       */
      public boolean next() throws JRException{
              boolean hasNext = false;

              if (this.iterator != null)
              {
                      hasNext = this.iterator.hasNext();

                      if (hasNext)
                      {
                              this.currentBean = this.iterator.next();
                      }
              }

              return hasNext;
      }

      /**
       * Método chamado pela abstração do jasper reports responsível por recuperar
       * o valor de um determinado atributo do
       * objeto que representa um registro do relatório.
       * É necessário que o nome do campo para agregação seja colocado o '_' e não
       * o ponto como no JSTL. Isto ocorre pois o jasper reports gera o os objetos
       * do tipo JRField da seguinte forma: field_nome_do_objeto
       * @return Object
       * @param JRField - Classe que representa um campo do relatório
       */

      public Object getFieldValue(JRField jrField) throws JRException{
              Object value = null;

              if (currentBean != null)
              {
                      try
                      {  String metodoToSring = "toString";
                         if (jrField.getName().equals(metodoToSring)){
                             Method metodo = currentBean.getClass().getMethod(metodoToSring,null);
                             value = metodo.invoke(currentBean,null);
                         }else{
                             value = PropertyUtils.getProperty(currentBean,jrField.getName());
                         }
                      }
                      catch (java.lang.IllegalAccessException e)
                      {
                              throw new JRException("Erro ao recuperar o valor do campo no bean : " + jrField.getName(), e);
                      }
                      catch (java.lang.reflect.InvocationTargetException e)
                      {
                              throw new JRException("Erro ao recuperar o valor do campo no bean : " + jrField.getName(), e);
                      }
                      catch (java.lang.NoSuchMethodException e)
                      {
                              throw new JRException("Erro ao recuperar o valor do campo no bean : " + jrField.getName(), e);
                      }
              }

              return value;
      }

      /**
       *
       */
      public void moveFirst() throws JRException
      {

              if (this.dados != null)
              {
                      this.iterator = this.dados.iterator();
              }
      }
}