/**
 * RssChannelBean.java 
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
import org.horrabin.horrorss.RssSyndicationModuleBean;

/**
* The RssChannelBean object represents the element <i>channel</i> in a RSS feed. 
* Compatible with RSS 2.0, RDF and ATOM 0.3
*  
* @author Fernando Fornieles 
*/
public class RssChannelBean {
  //For RSS 2.0, RDF y ATOM
  private String title;
  private String link;
  private String description;
  private String language;
  private String pubDate;
  
 //For RSS 2.0 y RDF
  private String copyright;
  private String managingEditor;
  private String webMaster;
  
  //Only for RSS 2.0
  private String lastBuildDate;
  private String category;
  private String generator;
  private String docs;
  private String cloud;
  private String ttl;
  private String rating;
  
  //Type control
  private final int TYPE_RDF = 0;
  private final int TYPE_RSS = 1;
  private final int TYPE_ATOM = 2;
  private int rssType;

  private RssDublinCoreModuleBean dcBean;
  private RssSyndicationModuleBean syBean;

   /** 
   * Create a new RssChannelBean.The rssType parameter tells to the parser the specification of the feed<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0 </li>
   * <li>If rssType=2 the feed is ATOM 0.3 </li>
   * </ul>
   * 
   * @param rssType tells the parser which specification must use (RSS 2.0, RDF o ATOM)
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
   * Create a new RssChannelBean. The rssType parameter tells to the parser the specification of the feed<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0</li>
   * <li>If rssType=2 the feed is ATOM</li>
   * </ul>
   * 
   * @param title the title of the channel
   * @param link the link channel's web page
   * @param description the description of the channel 
   * @param rssType the type of the feed.
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
  * Sets the channel title to the specified string
  * @param title the title of the channel
  */
  public void setTitle(String title){
     this.title = title;
  }

  /** 
  * Sets the channel description 
  * @param description Channel description
  */
  public void setDescription(String description){
     this.description = description;
  }

  /** 
  * Sets the URL of the feed
  * @param link Channel URL
  */
  public void setLink(String link){
     this.link = link;
  }

  /** 
   * Sets the Dublin Core module
   * @see RssDublinCoreModuleBean
   * @param dcBean Dublin Core Module 
   */
  public void setDublinCoreModule(RssDublinCoreModuleBean dcBean){
     this.dcBean = dcBean;
  }

  /** 
   * Sets de Syndication module
   * @see RssSyndicationModuleBean
   * @param syBean Syndication Module
   */  
  public void setSyndicationModule(RssSyndicationModuleBean syBean){
     this.syBean = syBean;
  }

  /** 
   * Sets the language of the feed   
   * @param language the language of the feed
   */    
  public void setLanguage(String language){
  	 switch(rssType){
  	 	case TYPE_RSS: this.language = language;break;
  	 	case TYPE_RDF: dcBean.setLanguage(language);break;
  	 	case TYPE_ATOM: this.language = language;break;
  	 }     
  }

  /** 
   * Sets the copyright owner of the feed
   * @param copyright the copyright owner of the feed
   */    
  public void setCopyright(String copyright){
     if(rssType==TYPE_RSS) this.copyright = copyright;
       else dcBean.setRights(copyright);     
  }

  /** 
   * Sets the publisher of the feed
   * @param managingEditor the publisher of the feed
   */    
  public void setPublisher(String managingEditor){
     if(rssType==TYPE_RSS) this.managingEditor = managingEditor;
       else dcBean.setPublisher(managingEditor);          
  }

  /** 
   * Sets the creator of the feed
   * @param webMaster the creator of the feed
   */    
  public void setCreator(String webMaster){
     if(rssType==TYPE_RSS) this.webMaster = webMaster;
       else dcBean.setCreator(webMaster);               
  }

  /** 
   * Sets the publication date of the feed
   * @param pubDate the publication date of the feed
   */      
  public void setPubDate(String pubDate){
  	 switch(rssType){
  	 	case TYPE_RSS: this.pubDate = pubDate;break;
  	 	case TYPE_RDF: dcBean.setDate(pubDate);break;
  	 	case TYPE_ATOM: this.pubDate = pubDate;break;
  	 }
  }

  /** 
   * Sets the last build date of the feed
   * @param lastBuildDate last build date of the feed
   */    
  public void setLastBuildDate(String lastBuildDate){
     this.lastBuildDate = lastBuildDate;
  }

  /** 
   * Sets the category of the feed
   * @param category the category of the feed
   */    
  public void setCategory(String category){
     this.category = category;
  }

  /** 
   * Sets the generator of the feed
   * @param generator the generator of the feed
   */    
  public void setGenerator(String generator){
     this.generator = generator;
  }

  /** 
   * Sets the documents of the feed
   * @param docs the documents of the feed
   */    
  public void setDocs(String docs){
     this.docs = docs;
  }

  /** 
   * Sets the cloud ??? 
   * <strong>Anybody know what's this?</strong> ... :-(
   * @param cloud cloud of the feed 
   */    
  public void setCloud(String cloud){
     this.cloud = cloud;
  }

  /** 
   * Sets the TTL of the feed
   * @param ttl the TTL of the feed
   */    
  public void setTtl(String ttl){
     this.ttl = ttl;
  }

  /** 
   * Sets the rating of the feed
   * @param rating the rating of the feed
   */    
  public void setRating(String rating){
     this.rating = rating;
  }

  /** 
   * Sets the RSS specification of the feed.<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0 </li>
   * <li>If rssType=2 the feed is ATOM 0.3 </li>
   * </ul>
   * 
   * @param rssType the RSS type of the feed
   */    
  public void setRssType(int rssType){
     this.rssType = rssType;
  }

  /** 
   * Returns the title of the feed
   * @return Title of the feed
   */    
  public String getTitle(){
     return title;
  }

  /** 
   * Returns the description of the feed
   * @return Description of the feed
   */    
  public String getDescription(){
     return description;
  }

  /** 
   * Returns the URL of the feed
   * @return URL of the feed
   */   
  public String getLink(){
     return link;
  }

  /** 
   * Returns the Dublin Core Module of the feed
   * @see RssDublinCoreModuleBean
   * @return Dublin Core Module of the feed
   */   
  public RssDublinCoreModuleBean getDublinCoreModule(){
     return dcBean;
  }

  /** 
   * Returns the Syndication module of the feed
   * @see RssSyndicationModuleBean
   * @return Syndication module of the feed
   */   
  public RssSyndicationModuleBean getSyndicationModule(){
     return syBean;
  }

  /** 
   * Returns the language of the feed
   * @return Language of the feed
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
   * Returns the copyright owner of the feed
   * @return The copyright owner of the feed
   */     
  public String getCopyright(){
     if(rssType==TYPE_RSS) return copyright;
       else return dcBean.getRights();
  }

  /** 
   * Returns the publisher of the feed
   * @return Publisher of the feed
   */     
  public String getPublisher(){
     if(rssType==TYPE_RSS) return managingEditor;
       else return dcBean.getPublisher();     
  }

  /** 
   * Returns the creator of the feed
   * @return Creator of the feed
   */     
  public String getCreator(){
     if(rssType==TYPE_RSS) return webMaster;
       else return dcBean.getCreator();          
  }

  /** 
   * Returns the publication date of the feed
   * @return Publication date of the feed
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
   * Returns the last build date of the feed
   * @return Last build date of the feed
   */     
  public String getLastBuildDate(){
     return lastBuildDate;
  }

  /** 
   * Returns the category of the feed
   * @return Category of the feed
   */     
  public String getCategory(){
     return category;
  }

  /** 
   * Returns the generator of the feed
   * @return Generator of the feed
   */     
  public String getGenerator(){
     return generator;
  }

  /** 
   * Returns the documents of the feed
   * @return Documents of the feed
   */     
  public String getDocs(){
     return docs;
  }

  /** 
   * Returns the cloud of the feed... <strong>What's this</strong> :-(
   * @return Cloud of the feed
   */     
  public String getCloud(){
     return cloud;
  }

  /** 
   * Returns the TTL of the feed
   * @return TTL of the feed
   */     
  public String getTtl(){
     return ttl;
  }

  /** 
   * Returns the rating of the feed
   * @return Rating of the feed
   */     
  public String getRating(){
     return rating;
  }

  /** 
   * Returns the RSS feed specification<br>
   * <ul>
   * <li>If rssType=0 the feed is RDF</li>
   * <li>If rssType=1 the feed is RSS 2.0 </li>
   * <li>If rssType=2 the feed is ATOM 0.3 </li>
   * </ul>
   * 
   * @return RSS feed specification
   */     
  public int getRssType(){
     return rssType;
  }

}