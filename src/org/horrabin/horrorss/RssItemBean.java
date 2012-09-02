/**
 * RssItemBean.java 
 *
 * HORRORss Package, Version 2.1.0
 * Simple RSS parser
 *
 * August 30, 2012
 *
 * Copyright (C) 2012 Fernando Fornieles
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* The RssItemBean object maps the element <i>item</i> of an RSS feed. 
* @author Fernando Fornieles
*/ 
public class RssItemBean {
  //For all specifications
  private String title;
  private String link;
  private String description;  
  private String author;
  private Date pubDate;  

  //Only for RSS 2.0
  private String category;
  
  private Map<String, Object> additionalInfo;

   /** 
   * Create a new RssItemBean.
   */   
  public RssItemBean(){
     title = "";
     link = "";
     description = "";
     author = "";
     category = "";
     pubDate = new Date();
  }

   /** 
   * Create a new RssItemBean.
   * @param title Title of the item
   * @param link The link of the item
   * @param description Description of the item
   */   
  public RssItemBean(String title, String link, String description){
     this.title = title;
     this.link = link;
     this.description = description;
     author = "";
     category = "";
     pubDate = new Date();
  }
  
  /** 
   * Adds an object with additional info identified by the string name
   * 
   * @param name The string identifying the object
   * @param object The object to add
   */
  public void putAdditionalInfo(String name, Object object){
	  if (this.additionalInfo==null) this.additionalInfo = new HashMap<String,Object>();
	  this.additionalInfo.put(name, object);
  }
  
  /**
   * Get an object with additional info identified by the string name
   * 
   * @param name The string identifying the object
   * @return An object with additional info
   */
  public Object getAdditionalInfo(String name){
	  return this.additionalInfo.get(name);
  }

  /**
   * Sets the title of the item
   * @param title Title of the item
   */
  public void setTitle(String title){
	 this.title = title;
  }

  /**
   * Sets the link of the item
   * @param link Link of the item
   */  
  public void setLink(String link){
	 this.link = link;
  }

  /**
   * Sets the description of the item
   * @param description Description of the item
   */  
  public void setDescription(String description){
	 this.description = description;
  }

  /**
   * Sets the author of the item
   * @param author Author of the item
   */    
  public void setAuthor(String author){
	  this.author = author;
  }

  /**
   * Sets the publication date of the item
   * @param pubDate Publication date of the item
   */      
  public void setPubDate(Date pubDate){  	 
	  this.pubDate = pubDate;
  }

  /**
   * Sets the category of the item
   * @param category Category of the item
   */      
  public void setCategory(String category){
	 this.category = category;
  }

  /**
   * Returns the title of the item
   * @return Title of the item
   */      
  public String getTitle(){
	 return title;
  }

  /**
   * Returns the link of the item
   * @return Link of the item
   */        
  public String getLink(){
	 return link;
  }

  /**
   * Returns the description of the item
   * @return Description of the item
   */        
  public String getDescription(){
	 return description;
  }

  /**
   * Returns the author of the item
   * @return Author of the item
   */          
  public String getAuthor(){
  	 return this.author;
  }

  /**
   * Returns the publication date of the item
   * @return Publication date of the item
   */
  public Date getPubDate(){
  	 return this.pubDate;
  }

  /**
   * Returns the category of the item
   * @return Category of the item
   */            
  public String getCategory(){
	 return category;
  }

}