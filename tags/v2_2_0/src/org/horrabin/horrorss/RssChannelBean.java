/**
 * RssChannelBean.java 
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
* The RssChannelBean object maps the element <i>channel</i> of an RSS feed. 
* Compatible with RSS 2.0, RDF and ATOM 0.3
*  
* @author Fernando Fornieles 
*/
public class RssChannelBean {
  //For RSS 2.0, RDF y ATOM
  private String title;
  private String link;
  private String description;
  private Date pubDate;
  
  private Map<String, Object> additionalInfo;
  
   /** 
   * Create a new RssChannelBean.
   */
  public RssChannelBean(){
     this.title = "";
     this.link = "";
     this.description = "";
  }

   /** 
   * Create a new RssChannelBean.
   * 
   * @param title the title of the feed
   * @param link the URL to the feed web page
   * @param description the description of the feed 
   */ 
  public RssChannelBean(String title, String link, String description){
     this.title = title;
     this.link = link;
     this.description = description;
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
  * Sets the title of the feed 
  * @param title the title of the feed
  */
  public void setTitle(String title){
     this.title = title;
  }

  /** 
  * Sets the description of the feed 
  * @param description the description of the feed
  */
  public void setDescription(String description){
     this.description = description;
  }

  /** 
  * Sets the URL to the feed's web page
  * @param link The URL to the feed's web page 
  */
  public void setLink(String link){
     this.link = link;
  }

  /** 
   * Sets the publication date of the feed
   * @param pubDate the publication date of the feed
   */      
  public void setPubDate(Date pubDate){
	  this.pubDate = pubDate;
  }

  /** 
   * Returns the title of the feed
   * @return Title of the feed
   */    
  public String getTitle(){
     return this.title;
  }

  /** 
   * Returns the description of the feed
   * @return Description of the feed
   */    
  public String getDescription(){
     return this.description;
  }

  /** 
   * Returns the URL of the feed's web page
   * @return URL of the feed's web page
   */   
  public String getLink(){
     return this.link;
  }

  /** 
   * Returns the last modification date of the feed
   * @return The last modification date of the feed
   */     
  public Date getPubDate(){
  	 return this.pubDate;
  }

}