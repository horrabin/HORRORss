/**
 * RssImageBean.java 
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

import java.util.HashMap;
import java.util.Map;

/**
* The RssImageBean object maps the element <i>image</i> of an RSS feed. 
* @author Fernando Fornieles
*/ 
public class RssImageBean {
  private String title;
  private String url;
  private String link;
  
  private Map<String, Object> additionalInfo;

  /**
   * Create a new RssImageBean
   */
  public RssImageBean(){
     title = "";
     url = "";
     link = "";
  }

  /**
   * Create a new RssImageBean
   * @param title Title of the image
   * @param url URL of the image
   * @param link The URL to the feed web page
   */
  public RssImageBean(String title, String url, String link){
	 this.title = title;
	 this.url = url;
	 this.link = link;
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
   * Sets the title of the image element
   * @param title Title of the image element
   */
  public void setTitle(String title){
	 this.title = title;
  }

  /**
   * Sets the URL of the image 
   * @param url URL of the image
   */  
  public void setUrl(String url){
	 this.url = url;
  }

  /**
   * Sets the URL to the feed web page
   * @param link the URL to the feed web page
   */  
  public void setLink(String link){
	 this.link = link;
  }

  /**
   * Returns the title of the image
   * @return Title of the image
   */  
  public String getTitle(){
	 return title;
  }

  /**
   * Returns the URL of the image
   * @return URL of the image
   */    
  public String getUrl(){
	 return url;
  }

  /**
   * Returns the URL where the image links
   * @return URL where the image links
   */    
  public String getLink(){
	 return link;
  }
}