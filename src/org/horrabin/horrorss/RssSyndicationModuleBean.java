/**
 * RssSyndicationModule.java 
 *
 * HORRORss Package, Version 1.4
 * Simple RSS parser fully GNU-Classpath compatible.
 *
 * February 21, 2009
 *
 * Copyright (C) 2009 Fernando Fornieles
 * e-mail: horrabin@usuarios.javahispano.net
 *
 * This file is part og HORRORss
 *
 * HORRORss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HORRORss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.horrabin.horrorss;

/**
* POJO que representa un modulo <i>Syndication</i> de un fichero RSS. Valido para las especificaciones 
* RDF 
* @author Fernando Fornieles
*/ 
public class RssSyndicationModuleBean {
  private String updatePeriod;
  private int updateFrequency;
  private String updateBase;

  /**
   * Crea un nuevo modulo Syndication
   */
  public RssSyndicationModuleBean(){
     updatePeriod = "";
     updateFrequency = -1;
     updateBase = "";
  }

  /**
   * Crea un nuevo modulo Syndication
   * @param updatePeriod Periodo de actualizacion
   * @param updateFrequency Frecuencia de actualizacion
   * @param updateBase Base de actualizacion
   */  
  public RssSyndicationModuleBean(String updatePeriod, int updateFrequency, String updateBase){
	 this.updatePeriod = updatePeriod;
	 this.updateFrequency = updateFrequency;
	 this.updateBase = updateBase;
  }

  /**
   * Asigna el periodo de actualizacion
   * @param updatePeriod Periodo de actualizacion
   */
  public void setPeriod(String updatePeriod){
	 this.updatePeriod = updatePeriod;
  }

  /**
   * Asigna la frecuencia de actualizacion
   * @param updateFrequency Frecuencia de actualizacion
   */  
  public void setFrequency(int updateFrequency){
	 this.updateFrequency = updateFrequency;
  }

  /**
   * Asigna la base de actualizacion
   * @param updateBase Base de actualizacion
   */  
  public void setBase(String updateBase){
	 this.updateBase = updateBase;
  }

  /**
   * Retorna el periodo de actualizacion
   * @return Periodo de actualizacion
   */  
  public String getPeriod(){
	 return updatePeriod;
  }

  /**
   * Retorna la frecuencia de actualizacion
   * @return Frecuencia de actualizacion
   */    
  public int getFrequency(){
	 return updateFrequency;
  }

  /**
   * Retorna la base de actualizacion
   * @return Base de actualizacion
   */    
  public String getBase(){
	 return updateBase;
  }
}