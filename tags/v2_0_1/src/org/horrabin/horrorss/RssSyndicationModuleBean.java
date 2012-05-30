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
 * This file is part of HORRORss
 *
 * HORRORss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HORRORss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.horrabin.horrorss;

/**
* The RssSyndicationModuleBean object represents the <i>Syndication</i> module of an RSS feed. 
* Valid for RDF specification.  
* @author Fernando Fornieles
*/ 
public class RssSyndicationModuleBean {
  private String updatePeriod;
  private int updateFrequency;
  private String updateBase;

  /**
   * Create a new Syndication module
   */
  public RssSyndicationModuleBean(){
     updatePeriod = "";
     updateFrequency = -1;
     updateBase = "";
  }

  /**
   * Create a new Syndication module
   * @param updatePeriod Sets the update period
   * @param updateFrequency Sets the update frequency
   * @param updateBase Sets the update base
   */  
  public RssSyndicationModuleBean(String updatePeriod, int updateFrequency, String updateBase){
	 this.updatePeriod = updatePeriod;
	 this.updateFrequency = updateFrequency;
	 this.updateBase = updateBase;
  }

  /**
   * Sets the update period
   * @param updatePeriod Update period
   */
  public void setPeriod(String updatePeriod){
	 this.updatePeriod = updatePeriod;
  }

  /**
   * Sets the update frequency
   * @param updateFrequency Update frequency
   */  
  public void setFrequency(int updateFrequency){
	 this.updateFrequency = updateFrequency;
  }

  /**
   * Sets the update base
   * @param updateBase Update base
   */  
  public void setBase(String updateBase){
	 this.updateBase = updateBase;
  }

  /**
   * Returns the update period
   * @return Update period
   */  
  public String getPeriod(){
	 return updatePeriod;
  }

  /**
   * Returns the update frequency
   * @return Update frequency
   */    
  public int getFrequency(){
	 return updateFrequency;
  }

  /**
   * Returns the update base
   * @return Update base
   */    
  public String getBase(){
	 return updateBase;
  }
}