/**
 * RssChannelBean.java 
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
import org.horrabin.horrorss.RssSyndicationModuleBean;

/**
* POJO que representa un elemento <i>channel</i> de un fichero RSS. Valido para las especificaciones
* RSS 2.0, RDF y ATOM 0.3 
* @author Fernando Fornieles 
*/
public class RssChannelBean {
  //Para RSS 2.0, RDF y ATOM
  private String title;
  private String link;
  private String description;
  private String language;
  private String pubDate;
  
 //Para RSS 2.0 y RDF
  private String copyright;
  private String managingEditor;
  private String webMaster;
  
  //Solo para RSS 2.0
  private String lastBuildDate;
  private String category;
  private String generator;
  private String docs;
  private String cloud;
  private String ttl;
  private String rating;
  
  //Control de tipo
  private final int TYPE_RDF = 0;
  private final int TYPE_RSS = 1;
  private final int TYPE_ATOM = 2;
  private int rssType;

  private RssDublinCoreModuleBean dcBean;
  private RssSyndicationModuleBean syBean;

   /** 
   * Crea un nuevo RssChannelBean.<br>
   * <li>Si rssType=0 indicamos que se trata de un RDF</li>
   * <li>Si rssType=1 indicamos que se trata de un RSS 2.0 </li>
   * <li>Si rssType=2 indicamos que se trata de un ATOM 0.3 </li>
   * @param rssType indica el tipo de especificación (RSS 2.0, RDF o ATOM)
   */
  public RssChannelBean(int rssType){
     title = "";
     link = "";
     description = "";
     language = "";
     copyright = "";
     managingEditor = "";
     webMaster = "";
     pubDate = "";
     lastBuildDate = "";
     category = "";
     generator = "";
     docs = "";
     cloud = "";
     ttl = "";
     rating = "";
     this.rssType = rssType;
     dcBean = new RssDublinCoreModuleBean();
     syBean = new RssSyndicationModuleBean();
  }

   /** 
   * Crea un nuevo RssChannelBean.<br>
   * <li>Si rssType=0 indicamos que se trata de un RDF</li>
   * <li>Si rssType=1 indicamos que se trata de un RSS 2.0 </li>
   * <li>Si rssType=2 indicamos que se trata de un ATOM 0.3 </li>
   * @param title Titulo del canal
   * @param link Enlace a la pagina web del canal
   * @param description Descripcion del canal
   * @param rssType indica el tipo de especificación (RSS 2.0, RDF o ATOM)
   */ 
  public RssChannelBean(String title, String link, String description, int rssType){
     this.title = title;
     this.link = link;
     this.description = description;
     language = "";
     copyright = "";
     managingEditor = "";
     webMaster = "";
     pubDate = "";
     lastBuildDate = "";
     category = "";
     generator = "";
     docs = "";
     cloud = "";
     ttl = "";
     rating = "";
     this.rssType = rssType;
     dcBean = new RssDublinCoreModuleBean();
     syBean = new RssSyndicationModuleBean();
  }

  /**   
  * Asigna el titulo del canal 
  * @param title Titulo del canal
  */
  public void setTitle(String title){
     this.title = title;
  }

  /** 
  * Asigna la descripcion del canal 
  * @param description Descripcion del canal
  */
  public void setDescription(String description){
     this.description = description;
  }

  /** 
  * Asigna la URL del canal 
  * @param link URL del canal
  */
  public void setLink(String link){
     this.link = link;
  }

  /** 
   * Asigna un modulo Dublin Core
   * @see RssDublinCoreModuleBean
   * @param dcBean Modulo Dublin Core 
   */
  public void setDublinCoreModule(RssDublinCoreModuleBean dcBean){
     this.dcBean = dcBean;
  }

  /** 
   * Asigna un modulo Syndication
   * @see RssSyndicationModuleBean
   * @param syBean Modulo Syndication
   */  
  public void setSyndicationModule(RssSyndicationModuleBean syBean){
     this.syBean = syBean;
  }

  /** 
   * Asigna el idioma del canal   
   * @param language Idioma del canal
   */    
  public void setLanguage(String language){
  	 switch(rssType){
  	 	case TYPE_RSS: this.language = language;break;
  	 	case TYPE_RDF: dcBean.setLanguage(language);break;
  	 	case TYPE_ATOM: this.language = language;break;
  	 }     
  }

  /** 
   * Asigna el propietario del copyright del canal
   * @param copyright Propietario del copyright del canal
   */    
  public void setCopyright(String copyright){
     if(rssType==TYPE_RSS) this.copyright = copyright;
       else dcBean.setRights(copyright);     
  }

  /** 
   * Asigna el editor del canal
   * @param managingEditor Editor del canal
   */    
  public void setPublisher(String managingEditor){
     if(rssType==TYPE_RSS) this.managingEditor = managingEditor;
       else dcBean.setPublisher(managingEditor);          
  }

  /** 
   * Asigna el creador del canal
   * @param webMaster creador del canal
   */    
  public void setCreator(String webMaster){
     if(rssType==TYPE_RSS) this.webMaster = webMaster;
       else dcBean.setCreator(webMaster);               
  }

  /** 
   * Asigna la fecha de publicacion
   * @param pubDate fecha de publicación
   */      
  public void setPubDate(String pubDate){
  	 switch(rssType){
  	 	case TYPE_RSS: this.pubDate = pubDate;break;
  	 	case TYPE_RDF: dcBean.setDate(pubDate);break;
  	 	case TYPE_ATOM: this.pubDate = pubDate;break;
  	 }
  }

  /** 
   * Asigna la fecha de última publicacion
   * @param lastBuildDate la fecha de ultima publicacion
   */    
  public void setLastBuildDate(String lastBuildDate){
     this.lastBuildDate = lastBuildDate;
  }

  /** 
   * Asigna la categoria del canal
   * @param category Categoria del canal
   */    
  public void setCategory(String category){
     this.category = category;
  }

  /** 
   * Asigna el generador del canal
   * @param generator Generador del canal
   */    
  public void setGenerator(String generator){
     this.generator = generator;
  }

  /** 
   * Asigna documentos del canal
   * @param docs Documentos del canal
   */    
  public void setDocs(String docs){
     this.docs = docs;
  }

  /** 
   * Asigna el "cloud" al canal ¿¿¿??? 
   * Quien sepa que es esto que me lo explique... :-(
   * @param cloud "Cloud del canal" ¿¿?? 
   */    
  public void setCloud(String cloud){
     this.cloud = cloud;
  }

  /** 
   * Asigna el TTL al canal
   * @param ttl TTL del canal
   */    
  public void setTtl(String ttl){
     this.ttl = ttl;
  }

  /** 
   * Asigna putuacion al canal
   * @param rating Puntuacion del canal
   */    
  public void setRating(String rating){
     this.rating = rating;
  }

  /** 
   * Asigna el tipo de RSS del canal
   * @param rssType Tipo de RSS
   */    
  public void setRssType(int rssType){
     this.rssType = rssType;
  }

  /** 
   * Retorna el titulo del canal
   * @return Titulo del canal
   */    
  public String getTitle(){
     return title;
  }

  /** 
   * Retorna la descripcion del canal
   * @return Descripcion del canal
   */    
  public String getDescription(){
     return description;
  }

  /** 
   * Retorna la URL del canal
   * @return URL del canal
   */   
  public String getLink(){
     return link;
  }

  /** 
   * Retorna el modulo Dublin Core del canal
   * @see RssDublinCoreModuleBean
   * @return RssDublinCoreModuleBean
   */   
  public RssDublinCoreModuleBean getDublinCoreModule(){
     return dcBean;
  }

  /** 
   * Retorna el modulo Syndication del canal
   * @see RssSyndicationModuleBean
   * @return RssSyndicationModuleBean
   */   
  public RssSyndicationModuleBean getSyndicationModule(){
     return syBean;
  }

  /** 
   * Retorna el idioma del canal
   * @return Idioma del canal
   */   
  public String getLanguage(){
  	 String res = null;
  	 switch(rssType){
  	 	case TYPE_RSS: res = this.language;break;
  	 	case TYPE_RDF: res = dcBean.getLanguage();break;
  	 	case TYPE_ATOM: res = this.language;break;
  	 }
  	 return res;
  }

  /** 
   * Retorna el propietario del copyright del canal
   * @return Propietario del copyright del canal
   */     
  public String getCopyright(){
     if(rssType==TYPE_RSS) return copyright;
       else return dcBean.getRights();
  }

  /** 
   * Retorna el editor del canal
   * @return Editor del canal
   */     
  public String getPublisher(){
     if(rssType==TYPE_RSS) return managingEditor;
       else return dcBean.getPublisher();     
  }

  /** 
   * Retorna el creador del canal
   * @return Creador del canal
   */     
  public String getCreator(){
     if(rssType==TYPE_RSS) return webMaster;
       else return dcBean.getCreator();          
  }

  /** 
   * Retorna la fecha de publicacion
   * @return Fecha de publicacion
   */     
  public String getPubDate(){
  	 String res = null;
  	 switch(rssType){
  	 	case TYPE_RSS: res = pubDate;break;
  	 	case TYPE_RDF: res = dcBean.getDate();break;
  	 	case TYPE_ATOM: res = pubDate;break;
  	 }
  	 return res;
  }

  /** 
   * Retorna la ultima fecha de creacion
   * @return La ultima fecha de creacion
   */     
  public String getLastBuildDate(){
     return lastBuildDate;
  }

  /** 
   * Retorna la categoria del canal
   * @return Categoria del canal
   */     
  public String getCategory(){
     return category;
  }

  /** 
   * Retorna el generador del canal
   * @return Generador del canal
   */     
  public String getGenerator(){
     return generator;
  }

  /** 
   * Retorna documentos del canal
   * @return Documentos del canal
   */     
  public String getDocs(){
     return docs;
  }

  /** 
   * Retorna el "cloud" del canal
   * @return "cloud" del canal :-(
   */     
  public String getCloud(){
     return cloud;
  }

  /** 
   * Retorna el TTL del canal
   * @return TTL del canal
   */     
  public String getTtl(){
     return ttl;
  }

  /** 
   * Retorna la puntuación del canal
   * @return Puntuacion del canal
   */     
  public String getRating(){
     return rating;
  }

  /** 
   * Retorna el tipo RSS del canal
   * @return Tipo de RSS
   */     
  public int getRssType(){
     return rssType;
  }

}