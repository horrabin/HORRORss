/**
 * RssItemBean.java 
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

import org.horrabin.horrorss.RssDublinCoreModuleBean;
import org.horrabin.horrorss.RssSlashModuleBean;

/**
* POJO que representa un elemento <i>item</i> de un fichero RSS. Valido para las especificaciones 
* RSS 2.0, RDF y ATOM 0.3 
* @author Fernando Fornieles
*/ 
public class RssItemBean {
  private RssDublinCoreModuleBean dcBean;
  private RssSlashModuleBean slashBean;  

  //Para RDF, RSS 2.0 y ATOM
  private String title;
  private String link;
  private String description;  
  private String author;
  private String pubDate;  

  //Solo para RSS 2.0
  private String category;
  private String comments;
  private RssEnclosureBean enclosure;
  private String guid;
  private String source;

  //Control de tipo
  private final int TYPE_RDF = 0;
  private final int TYPE_RSS = 1;
  private final int TYPE_ATOM = 2;
  private int rssType;

   /** 
   * Crea un nuevo RssItemBean.<br>
   * <li>Si rssType=0 indicamos que se trata de un RDF</li>
   * <li>Si rssType=1 indicamos que se trata de un RSS 2.0 </li>
   * <li>Si rssType=2 indicamos que se trata de un ATOM </li>
   * @param rssType indica el tipo de especificación (RSS 2.0, RDF o ATOM)
   */   
  public RssItemBean(int rssType){
     title = "";
     link = "";
     description = "";
     this.rssType = rssType;
     author = "";
     category = "";
     pubDate = "";
     comments = "";
     enclosure = new RssEnclosureBean();
     guid = "";
     source = "";
     dcBean = new RssDublinCoreModuleBean();
     slashBean = new RssSlashModuleBean();
  }

   /** 
   * Crea un nuevo RssItemBean.<br>
   * <li>Si rssType=0 indicamos que se trata de un RDF</li>
   * <li>Si rssType=1 indicamos que se trata de un RSS 2.0 </li>
   * <li>Si rssType=2 indicamos que se trata de un ATOM </li>
   * @param title Titulo del canal
   * @param link Enlace a la pagina web del canal
   * @param description Descripcion del canal
   * @param rssType indica el tipo de especificación (RSS 2.0, RDF o ATOM)
   */   
  public RssItemBean(String title, String link, String description, int rssType){
     this.title = title;
     this.link = link;
     this.description = description;
     this.rssType = rssType;
     author = "";
     category = "";
     pubDate = "";
     comments = "";
     enclosure = new RssEnclosureBean();
     guid = "";
     source = "";
     dcBean = new RssDublinCoreModuleBean();
     slashBean = new RssSlashModuleBean();
  }

  /**
   * Asigna el titulo del item
   * @param title Titulo del item
   */
  public void setTitle(String title){
	 this.title = title;
  }

  /**
   * Asigna el enlace del item
   * @param link Enlace del item
   */  
  public void setLink(String link){
	 this.link = link;
  }

  /**
   * Asigna la descripcion del item
   * @param description Descripcion del item
   */  
  public void setDescription(String description){
	 this.description = description;
  }

  /**
   * Asigna un modulo <i>Dublin Core</i> al item
   * @see RssDublinCoreModuleBean
   * @param dcBean Modulo Dublin Core
   */  
  public void setDublinCoreModule(RssDublinCoreModuleBean dcBean){
	 this.dcBean = dcBean;
  }

  /**
   * Asigna un modulo <i>Slash</i> al item
   * @see RssSlashModuleBean
   * @param slashBean Modulo Slash
   */    
  public void setSlashModule(RssSlashModuleBean slashBean){
	 this.slashBean = slashBean;
  }

  /**
   * Asigna el autor del item
   * @param author Autor del item
   */    
  public void setAuthor(String author){
  	 switch (rssType){
  	 	case TYPE_RSS: this.author = author;break;
  	 	case TYPE_RDF: dcBean.setCreator(author);break;
  	 	case TYPE_ATOM: this.author = author;break;
  	 }	  
  }

  /**
   * Asigna la fecha de publicacion del item
   * @param pubDate fecha de publicacion del item
   */      
  public void setPubDate(String pubDate){
  	 switch (rssType){
  	 	case TYPE_RSS: this.pubDate = pubDate;break;
  	 	case TYPE_RDF: dcBean.setDate(pubDate);
  	 	case TYPE_ATOM: this.pubDate = pubDate;break;
  	 }
  }

  /**
   * Asigna la categoria del item
   * @param category Categoria del item
   */      
  public void setCategory(String category){
	 this.category = category;
  }

  /**
   * Asigna los comentarios del item
   * @param comments Comentarios del item
   */      
  public void setComments(String comments){
	 this.comments = comments;
  }

  /**
   * Asigna el recurso multimedia adjunto del item
   * @param enclosure Recurso multimedia adjunto del item
   */      
  public void setEnclosure(RssEnclosureBean enclosure){
	 this.enclosure = enclosure;
  }

  /**
   * Asigna el Guid del item
   * @param guid Guid del item
   */      
  public void setGuid(String guid){
	 this.guid = guid;
  }

  /**
   * Asigna la fuetne del item
   * @param source Fuente del item
   */      
  public void setSource(String source){
	 this.source = source;
  }

  /**
   * Retorna el titulo del item
   * @return Titulo del item
   */      
  public String getTitle(){
	 return title;
  }

  /**
   * Retorna el enlace del item
   * @return Enlace del item
   */        
  public String getLink(){
	 return link;
  }

  /**
   * Retorna la descripcion del item
   * @return Descripcion del item
   */        
  public String getDescription(){
	 return description;
  }

  /**
   * Retorna el modulo Dublin Core del item
   * @see RssDublinCoreModuleBean
   * @return Modulo Dublin Core
   */        
  public RssDublinCoreModuleBean getDublinCoreModule(){
	 return dcBean;
  }

  /**
   * Retorna el modulo Slash del item
   * @see RssSlashModuleBean
   * @return ModuloSlash
   */          
  public RssSlashModuleBean getSlashModule(){
	 return slashBean;
  }

  /**
   * Retorna el autor del item
   * @return Autor del item
   */          
  public String getAuthor(){
  	 String res = "";
  	 switch (rssType){
  	 	case TYPE_RSS: res = this.author;break;
  	 	case TYPE_RDF: res = dcBean.getCreator();break;
  	 	case TYPE_ATOM: res = this.author;break;
  	 }
  	 return res;
  }

  /**
   * Retorna la fecha de publicacion del item
   * @return Fecha de publicacion del item
   */
  public String getPubDate(){
  	 String res = "";
  	 switch (rssType){
  	 	case TYPE_RSS: res = this.pubDate;break;
  	 	case TYPE_RDF: res = dcBean.getDate();;break;
  	 	case TYPE_ATOM: res = this.pubDate;break;
  	 }
  	 return res;
  }

  /**
   * Retorna la categoria del item
   * @return Categoria del item
   */            
  public String getCategory(){
	 return category;
  }

  /**
   * Retorna los comentarios del item
   * @return Comentarios del item
   */            
  public String getComments(){
	 return comments;
  }

  /**
   * Retorna el recurso multimedia adjunto del item
   * @return Recurso multimedia adjunto del item
   */            
  public RssEnclosureBean getEnclosure(){
	 return enclosure;
  }

  /**
   * Retorna el Guid del item
   * @return Guid del item
   */            
  public String getGuid(){
	 return guid;
  }

  /**
   * Retorna la fuente del item
   * @return Fuente del item
   */            
  public String getSource(){
	 return source;
  }
}