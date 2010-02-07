/**
 * RssImageBean.java 
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
* POJO que representa un elemento <i>image</i> de un fichero RSS. Valido para las especificaciones 
* RSS 2.0 y RDF 
* @author Fernando Fornieles
*/ 
public class RssImageBean {
  private String title;
  private String url;
  private String link;

  /**
   * Crea un nuevo RssImageBean
   */
  public RssImageBean(){
     title = "";
     url = "";
     link = "";
  }

  /**
   * Crea un nuevo RssImageBean
   * @param title Titulo de la imagen
   * @param url URL donde se halla la imagen
   * @param link Enlace hacia donde debe dirigir la imagen
   */
  public RssImageBean(String title, String url, String link){
	 this.title = title;
	 this.url = url;
	 this.link = link;
  }

  /**
   * Asigna el titulo de la imagen
   * @param title Titulo de la imagen
   */
  public void setTitle(String title){
	 this.title = title;
  }

  /**
   * Asigna la URL de la imagen
   * @param url URL de la imagen
   */  
  public void setUrl(String url){
	 this.url = url;
  }

  /**
   * Asigna el enlace de la imagen
   * @param link Enlace de la imagen
   */  
  public void setLink(String link){
	 this.link = link;
  }

  /**
   * Retorna el titulo de la imagen
   * @return Titulo de la imagen
   */  
  public String getTitle(){
	 return title;
  }

  /**
   * Retorna la URL de la imagen
   * @return URL de la imagen
   */    
  public String getUrl(){
	 return url;
  }

  /**
   * Retorna el enlace de la imagen
   * @return Enlace de la imagen
   */    
  public String getLink(){
	 return link;
  }
}