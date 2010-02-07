/**
 * RssSlashModule.java 
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
* POJO que representa un modulo <i>Slash</i> de un fichero RSS. Valido para las especificaciones 
* RDF 
* @author Fernando Fornieles
*/ 
public class RssSlashModuleBean {
  private String section;
  private String department;
  private int comments;
  private String hit_parade;

  /**
   * Crea un nuevo modulo Slash
   */
  public RssSlashModuleBean(){
     section = "";
     department = "";
     comments = 0;
     hit_parade = "";
  }

  /**
   * Crea un nuevo modulo Slash
   * @param section Seccion
   * @param department Departamento
   * @param comments Numero de comentarios
   * @param hit_parade Hit Parade 
   */
  public RssSlashModuleBean(String section, String department, int comments, String hit_parade){
	 this.section = section;
	 this.department = department;
	 this.comments = comments;
	 this.hit_parade = hit_parade;
  }

  /**
   * Asigna la seccion del modulo
   * @param section Seccion del modulo
   */
  public void setSection(String section){
	 this.section = section;
  }

  /**
   * Asigna el departamento del modulo
   * @param department Departamento del modulo
   */  
  public void setDepartment(String department){
	 this.department = department;
  }

  /**
   * Asigna el numero de comentarios del modulo
   * @param comments Numero de comentarios del modulo
   */  
  public void setComments(int comments){
	 this.comments = comments;
  }

  /**
   * Asigna el Hit Parade del modulo
   * @param hit_parade Hit Parade del modulo
   */  
  public void setHitParade(String hit_parade){
	 this.hit_parade = hit_parade;
  }

  /**
   * Retorna la seccion del modulo
   * @return Seccion del modulo
   */  
  public String getSection(){
	 return section;
  }

  /**
   * Retorna el departamento del modulo
   * @return Departmento del modulo
   */    
  public String getDepartment(){
	 return department;
  }

  /**
   * Retorna el numero de comentarios del modulo
   * @return Numero de comentarios del modulo
   */    
  public int getComments(){
	 return comments;
  }

  /**
   * Retorna el Hit Parade del modulo
   * @return Hit Parade del modulo
   */    
  public String getHitParade(){
	 return hit_parade;
  }
}