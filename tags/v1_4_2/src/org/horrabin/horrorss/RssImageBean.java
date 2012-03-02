/**
 * RssImageBean.java 
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
* The RssImageBean object represents the element <i>image</i> in an RSS feed. 
* Valid for the RSS 2.0 and RDF specifications 
* @author Fernando Fornieles
*/ 
public class RssImageBean {
  private String title;
  private String url;
  private String link;

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
   * @param link URL where the image links
   */
  public RssImageBean(String title, String url, String link){
	 this.title = title;
	 this.url = url;
	 this.link = link;
  }

  /**
   * Sets the title of the image
   * @param title Title of the image
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
   * Sets the URL where the image links
   * @param link URL where the image links
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