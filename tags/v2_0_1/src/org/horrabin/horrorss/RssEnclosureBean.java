/**
 * RssEnclosureBean.java 
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
* The RssEnclosureBean object represents the <i>item's</i> <i>enclosure</i> sub-element  of an RSS feed. 
* Valid for RSS 2.0 specification
* 
* @author Fernando Fornieles
*/
public class RssEnclosureBean {
  private String url;
  private String length;
  private String type;

   /** 
   * Create a new RssEnclosureBean.
   */
  public RssEnclosureBean(){
     this.url = "";
     this.length = "";
     this.type = "";
  }

   /** 
   * Create a new RssEnclosureBean.
   * @param url URL of the attached media
   * @param length Lenght of the attached media
   * @param type MIME/Type of the attached media
   */ 
  public RssEnclosureBean(String url, String length, String type){
     this.url = url;
     this.length = length;
     this.type = type;
  }

  /**   
  * Sets the URL of the attached media 
  * @param url URL del recurso multimedia adjunto
  */
  public void setURL(String url){
     this.url = url;
  }

  /** 
  * Sets the length of the attached media
  * @param length Length of the attached media
  */
  public void setLength(String length){
     this.length = length;
  }

  /** 
  * Sets the MIME/Type of the attached media
  * @param type MIME/Type of the attached media
  */
  public void setType(String type){
     this.type = type;
  }

  /** 
   * Returns the URL of the attached media
   * @return URL of the attached media
   */    
  public String getURL(){
     return url;
  }

  /** 
   * Returns the length of the attached media
   * @return Length of the attached media
   */    
  public String getLength(){
     return length;
  }

  /** 
   * Returns the MIME/Type of the attached media
   * @return MIME/Type of the attached media
   */   
  public String getType(){
     return type;
  }
}