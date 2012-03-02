/**
 * RssItemBean.java 
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

import org.horrabin.horrorss.RssDublinCoreModuleBean;
import org.horrabin.horrorss.RssSlashModuleBean;

/**
* The RssItemBean object represents the element <i>item</i> in an RSS feed. 
* Valid for the RSS 2.0, RDF and ATOM specifications 
* @author Fernando Fornieles
*/ 
public class RssItemBean {
  private RssDublinCoreModuleBean dcBean;
  private RssSlashModuleBean slashBean;  

  //For all specifications
  private String title;
  private String link;
  private String description;  
  private String author;
  private String pubDate;  

  //Only for RSS 2.0
  private String category;
  private String comments;
  private RssEnclosureBean enclosure;
  private String guid;
  private String source;

  //Type control
  private final int TYPE_RDF = 0;
  private final int TYPE_RSS = 1;
  private final int TYPE_ATOM = 2;
  private int rssType;

   /** 
   * Create a new RssItemBean.<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0 </li>
   * <li>If rssType=2 the feed is ATOM </li>
   * </ul>
   * @param rssType Sets the specification type (RSS 2.0, RDF or ATOM)
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
   * Create a new RssItemBean.<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0 </li>
   * <li>If rssType=2 the feed is ATOM </li>
   * </ul>
   * @param title Title of the item
   * @param link The link of the item
   * @param description Description of the item
   * @param rssType Sets the specification type (RSS 2.0, RDF or ATOM)
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
   * Sets the <i>Dublin Core</i> module of the item
   * @see RssDublinCoreModuleBean
   * @param dcBean Dublin Core module of the item
   */  
  public void setDublinCoreModule(RssDublinCoreModuleBean dcBean){
	 this.dcBean = dcBean;
  }

  /**
   * Sets the <i>Slash</i> module of the item
   * @see RssSlashModuleBean
   * @param slashBean Slash module of the item
   */    
  public void setSlashModule(RssSlashModuleBean slashBean){
	 this.slashBean = slashBean;
  }

  /**
   * Sets the author of the item
   * @param author Author of the item
   */    
  public void setAuthor(String author){
  	 switch (rssType){
  	 	case TYPE_RSS: this.author = author;break;
  	 	case TYPE_RDF: dcBean.setCreator(author);break;
  	 	case TYPE_ATOM: this.author = author;break;
  	 }	  
  }

  /**
   * Sets the publication date of the item
   * @param pubDate Publication date of the item
   */      
  public void setPubDate(String pubDate){
  	 switch (rssType){
  	 	case TYPE_RSS: this.pubDate = pubDate;break;
  	 	case TYPE_RDF: dcBean.setDate(pubDate);
  	 	case TYPE_ATOM: this.pubDate = pubDate;break;
  	 }
  }

  /**
   * Sets the category of the item
   * @param category Category of the item
   */      
  public void setCategory(String category){
	 this.category = category;
  }

  /**
   * Sets the comments of the item
   * @param comments Comments of the item
   */      
  public void setComments(String comments){
	 this.comments = comments;
  }

  /**
   * Sets the attached resources of the item
   * @param enclosure Attached resources of the item
   */      
  public void setEnclosure(RssEnclosureBean enclosure){
	 this.enclosure = enclosure;
  }

  /**
   * Sets the Guid of the item
   * @param guid Guid of the item
   */      
  public void setGuid(String guid){
	 this.guid = guid;
  }

  /**
   * Sets the source of the item
   * @param source Source of the item
   */      
  public void setSource(String source){
	 this.source = source;
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
   * @return Source Link of the item
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
   * Returns the Dublin Core module of the item
   * @see RssDublinCoreModuleBean
   * @return Dublin Core module of the item
   */        
  public RssDublinCoreModuleBean getDublinCoreModule(){
	 return dcBean;
  }

  /**
   * Returns the Slash module of the item
   * @see RssSlashModuleBean
   * @return Slash module of the item
   */          
  public RssSlashModuleBean getSlashModule(){
	 return slashBean;
  }

  /**
   * Returns the author of the item
   * @return Author of the item
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
   * Returns the publication date of the item
   * @return Publication date of the item
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
   * Returns the category of the item
   * @return Category of the item
   */            
  public String getCategory(){
	 return category;
  }

  /**
   * Returns the comments of the item
   * @return Comments of the item
   */            
  public String getComments(){
	 return comments;
  }

  /**
   * Returns the attached resources of the item
   * @return Attached resources of the item
   */            
  public RssEnclosureBean getEnclosure(){
	 return enclosure;
  }

  /**
   * Returns the Guid of the item
   * @return Guid of the item
   */            
  public String getGuid(){
	 return guid;
  }

  /**
   * Returns the source of the item
   * @return Source of the item
   */            
  public String getSource(){
	 return source;
  }
}