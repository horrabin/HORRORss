/**
 * RssDublinCoreModule.java 
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
* POJO que representa un modulo <i>Dublin Core</i> de un fichero RSS. Valido para las especificiones
* RDF.
* @author Fernando Fornieles
*/ 
public class RssDublinCoreModuleBean {
  private String title;
  private String creator;
  private String subject;
  private String description;
  private String publisher;
  private String contributor;
  private String date;
  private String type;
  private String format;
  private String identifier;
  private String source;
  private String language;
  private String relation;
  private String coverage;
  private String rights;

  /**
   * Crea un nuevo RssDublinCoreModuleBean
   */
  public RssDublinCoreModuleBean(){
    title = "";
    creator = "";
    subject = "";
    description = "";
    publisher = "";
    contributor = "";
    date = "";
    type = "";
    format = "";
    identifier = "";
    source = "";
    language = "";
    relation = "";
    coverage = "";
    rights = "";  
  }

  /**
   * Asigna el título del modulo
   * @param title Titulo del modulo
   */
  public void setTitle(String title){
	 this.title = title;
  }

  /**
   * Asigna el creador del modulo
   * @param creator Creador del modulo
   */  
  public void setCreator(String creator){
	 this.creator = creator;
  }

  /**
   * Asigna el asunto del modulo
   * @param subject Asunto del modulo
   */  
  public void setSubject(String subject){
	 this.subject = subject;
  }

  /**
   * Asigna la descripcion del modulo
   * @param description Descripcion del modulo
   */  
  public void setDescription(String description){
	 this.description = description;
  }

  /**
   * Asigna el editor del modulo
   * @param publisher Editor del modulo
   */  
  public void setPublisher(String publisher){
	 this.publisher = publisher;
  }

  /**
   * Asigna el participante? del modulo
   * @param contributor Participante? del modulo
   */  
  public void setContributor(String contributor){
	 this.contributor = contributor;
  }

  /**
   * Asigna la fecha del modulo
   * @param date FEcha del modulo
   */  
  public void setDate(String date){
	 this.date = date;
  }

  /**
   * Asigna el tipo del modulo
   * @param type Tipo del modulo
   */  
  public void setType(String type){
	 this.type = type;
  }

  /**
   * Asigna el formato del modulo
   * @param format Formato del modulo
   */  
  public void setFormat(String format){
	 this.format = format;
  }

  /**
   * Asigna el identificador del modulo
   * @param identifier Identificador del modulo
   */  
  public void setIdentifier(String identifier){
	 this.identifier = identifier;
  }

  /**
   * Asigna la fuente del modulo
   * @param source Fuente del modulo
   */  
  public void setSource(String source){
	 this.source = source;
  }

  /**
   * Asigna el idioma del modulo
   * @param language Idioma del modulo
   */  
  public void setLanguage(String language){
	 this.language = language;
  }

  /**
   * Asigna la relacion del modulo
   * @param relation Relacion del modulo
   */  
  public void setRelation(String relation){
	 this.relation = relation;
  }

  /**
   * Asigna el "coverage" del modulo
   * @param coverage "Coverage" del modulo
   */  
  public void setCoverage(String coverage){
	 this.coverage = coverage;
  }

  /**
   * Asigna los derechos del modulo
   * @param rights Rights del modulo
   */  
  public void setRights(String rights){
	 this.rights = rights;
  }

  /**
   * Retorna el título del modulo
   * @return Titulo del modulo
   */  
  public String getTitle(){
	 return title;
  }
  
  /**
   * Retorna el creador del modulo
   * @return Creador del modulo
   */  
  public String getCreator(){
	 return creator;
  }

  /**
   * Retorna el asunto del modulo
   * @return Asunto del modulo
   */    
  public String getSubject(){
	 return subject;
  }

  /**
   * Retorna la descripcion del modulo
   * @return Descripcion del modulo
   */    
  public String getDescription(){
	 return description;
  }

  /**
   * Retorna el editor del modulo
   * @return Editor del modulo
   */    
  public String getPublisher(){
	 return publisher;
  }

  /**
   * Retorna el participante? del modulo
   * @return Participante? del modulo
   */    
  public String getContributor(){
	 return contributor;
  }

  /**
   * Retorna la fecha del modulo
   * @return Fecha del modulo
   */    
  public String getDate(){
	 return date;
  }

  /**
   * Retorna el tipo del modulo
   * @return Tipo del modulo
   */    
  public String getType(){
	 return type;
  }

  /**
   * Retorna el formato del modulo
   * @return Formato del modulo
   */    
  public String getFormat(){
	 return format;
  }

  /**
   * Retorna el identificador del modulo
   * @return Identificador del modulo
   */    
  public String getIdentifier(){
	 return identifier;
  }

  /**
   * Retorna la fuente del modulo
   * @return Fuente del modulo
   */    
  public String getSource(){
	 return source;
  }

  /**
   * Retorna el idioma del modulo
   * @return Idioma del modulo
   */    
  public String getLanguage(){
	 return language;
  }

  /**
   * Retorna la relacion del modulo
   * @return Relacion del modulo
   */    
  public String getRelation(){
	 return relation;
  }

  /**
   * Retorna el "coverage" del modulo
   * @return "Coverage" del modulo
   */    
  public String getCoverage(){
	 return coverage;
  }

  /**
   * Retorna los derehos del modulo
   * @return Derechos del modulo
   */    
  public String getRights(){
	 return rights;
  }
}