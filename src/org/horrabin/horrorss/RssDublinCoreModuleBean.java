/**
 * RssDublinCoreModule.java 
 *
 * HORRORss Package, Version 1.4
 * Simple RSS parser fully GNU-Classpath compatible.
 *
 * February 21, 2009
 *
 * Copyright (C) 2009 Fernando Fornieles
 * e-mail: nandofm@gmail.com
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
* The RssDublinCoreModuleBean object represents the <i>Dublin Core</i> module in a RSS feed. Valid for RDF specifications.
* 
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
   * Create a new RssDublinCoreModuleBean object
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
   * Sets the title of the module
   * @param title Module title
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
   * Sets the subject of the module
   * @param subject Subject of the module
   */  
  public void setSubject(String subject){
	 this.subject = subject;
  }

  /**
   * Sets the description of the module
   * @param description Description of the module
   */  
  public void setDescription(String description){
	 this.description = description;
  }

  /**
   * Sets the publisher
   * @param publisher The publisher
   */  
  public void setPublisher(String publisher){
	 this.publisher = publisher;
  }

  /**
   * Sets the contributor
   * @param contributor The contributor
   */  
  public void setContributor(String contributor){
	 this.contributor = contributor;
  }

  /**
   * Sets the date
   * @param date The date
   */  
  public void setDate(String date){
	 this.date = date;
  }

  /**
   * Sets the type of the module
   * @param type type of the module
   */  
  public void setType(String type){
	 this.type = type;
  }

  /**
   * Sets the format of the module
   * @param format Format of the module
   */  
  public void setFormat(String format){
	 this.format = format;
  }

  /**
   * Sets the identifier of the module
   * @param identifier Identifier of the module
   */  
  public void setIdentifier(String identifier){
	 this.identifier = identifier;
  }

  /**
   * Sets the source
   * @param source The source
   */  
  public void setSource(String source){
	 this.source = source;
  }

  /**
   * Sets the language
   * @param language The language
   */  
  public void setLanguage(String language){
	 this.language = language;
  }

  /**
   * Sets the relation
   * @param relation Relation
   */  
  public void setRelation(String relation){
	 this.relation = relation;
  }

  /**
   * Sets the coverage
   * @param coverage The coverage
   */  
  public void setCoverage(String coverage){
	 this.coverage = coverage;
  }

  /**
   * Sets the rights
   * @param rights The rights
   */  
  public void setRights(String rights){
	 this.rights = rights;
  }

  /**
   * Returns the title of the module
   * @return Title of the module
   */  
  public String getTitle(){
	 return title;
  }
  
  /**
   * Returns the creator of the module
   * @return Creator of the module
   */  
  public String getCreator(){
	 return creator;
  }

  /**
   * Returns the subject of the module
   * @return Subject of the module
   */    
  public String getSubject(){
	 return subject;
  }

  /**
   * Returns the description of the module
   * @return Description of the module
   */    
  public String getDescription(){
	 return description;
  }

  /**
   * Returns the publisher
   * @return The publisher
   */    
  public String getPublisher(){
	 return publisher;
  }

  /**
   * Returns the contributor
   * @return The contributor
   */    
  public String getContributor(){
	 return contributor;
  }

  /**
   * Returns the date
   * @return The date
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
   * Returns the type of the module
   * @return Type of the module
   */    
  public String getFormat(){
	 return format;
  }

  /**
   * Returns the identifier
   * @return Tthe identifier
   */    
  public String getIdentifier(){
	 return identifier;
  }

  /**
   * Returns the source
   * @return The source
   */    
  public String getSource(){
	 return source;
  }

  /**
   * Returns the language
   * @return The language
   */    
  public String getLanguage(){
	 return language;
  }

  /**
   * Returns the relation
   * @return The relation
   */    
  public String getRelation(){
	 return relation;
  }

  /**
   * Returns the coverage
   * @return The coverage
   */    
  public String getCoverage(){
	 return coverage;
  }

  /**
   * Returns the rights
   * @return The rights
   */    
  public String getRights(){
	 return rights;
  }
}