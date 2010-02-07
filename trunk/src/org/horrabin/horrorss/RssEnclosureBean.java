/**
 * RssEnclosureBean.java 
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
* POJO que representa un sub-elemento <i>enclosure</i> de <i>item</i> de un fichero RSS. 
* Valido para la especificación RSS 2.0
* @author Fernando Fornieles
*/
public class RssEnclosureBean {
  private String url;
  private String length;
  private String type;

   /** 
   * Crea un nuevo RssEnclosureBean.
   */
  public RssEnclosureBean(){
     this.url = "";
     this.length = "";
     this.type = "";
  }

   /** 
   * Crea un nuevo RssEnclosureBean.<br>
   * @param url URL del recurso multimedia adjunto
   * @param length Tamaño del recurso multimedia adjunto
   * @param type MIME/Type del recurso multimedia adjunto
   */ 
  public RssEnclosureBean(String url, String length, String type){
     this.url = url;
     this.length = length;
     this.type = type;
  }

  /**   
  * Asigna la URL del recurso multimedia adjunto 
  * @param url URL del recurso multimedia adjunto
  */
  public void setURL(String url){
     this.url = url;
  }

  /** 
  * Asigna el tamaño del recurso multimedia adjunto
  * @param length Tamaño del recurso multimedia adjunto
  */
  public void setLength(String length){
     this.length = length;
  }

  /** 
  * Asigna el MIME/Type del recurso multimedia adjunto
  * @param type MIME/Type del recurso multimedia adjunto
  */
  public void setType(String type){
     this.type = type;
  }

  /** 
   * Retorna la URL del recurso multimedia adjunto
   * @return URL del recurso multimedia adjunto
   */    
  public String getURL(){
     return url;
  }

  /** 
   * Retorna el tamaño del recurso multimedia adjunto
   * @return Tamaño del recurso multimedia adjunto
   */    
  public String getLength(){
     return length;
  }

  /** 
   * Retorna el MIME/Type del recurso multimedia adjunto
   * @return MIME/Type del recurso multimedia adjunto
   */   
  public String getType(){
     return type;
  }
}