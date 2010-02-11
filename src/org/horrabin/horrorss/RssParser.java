/**
 * RssParser.java
 *
 * HORRORss Package, Version 1.4
 * Simple RSS parser fully GNU-Classpath compatible.
 *
 * February 21, 2009
 *
 * Copyright (C) 2009 Fernando Fornieles
 * e-mail: nandofm@gmail.com
 *
 * HORRORss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HORRORss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.horrabin.horrorss;

import com.hp.hpl.sparta.*;
import java.util.Enumeration;
import java.util.Vector;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.horrabin.horrorss.RssChannelBean;
import org.horrabin.horrorss.RssImageBean;
import org.horrabin.horrorss.RssDublinCoreModuleBean;
import org.horrabin.horrorss.RssSlashModuleBean;
import org.horrabin.horrorss.RssSyndicationModuleBean;
import org.horrabin.horrorss.RssEnclosureBean;

/**
* RSS file parser compatible with all RSS, RDF & Atom specifications.
* @author Fernando Fornieles
*/
public class RssParser {
  private Document doc;
  private String filename;
  private String xPath;
  private String channelXPath;
  private final int TYPE_RDF = 0;
  private final int TYPE_RSS = 1;
  private final int TYPE_ATOM = 2;
  private int rssType;
  private String charset = "utf8";
  private String cacheDir;
  private long cacheLifeTime = 0;


 /**
  * Create a new RSS parser.
  */
  public RssParser(){
       this.filename = "";
  }

 /**
  * Create a new RSS file parser.
  * @param filename String it can be a filename or an URL
  */
  public RssParser(String filename){
       this.filename = filename;
  }

  /**
   * Parses the RSS file
   */
  public void parse() throws Exception {
       if (this.filename.startsWith("http://")) this.parseFromURL();
         else this.parseFromReader(this.getReaderFromFile(this.filename));
  }

  /**
   * Parses the RSS file
   * @param filename String it can be a filename or an URL
   */
  public void parse(String filename) throws Exception{
	   this.filename = filename;
       this.parse();
  }

  /**
   * Tells the parser what charset must use. Default UTF-8.
   * @param charset String the charset (iso-8859-1, utf-8,...).
   */
  public void setCharset(String charset){
	  this.charset = charset;
  }

  /**
   * Enables the caching system.
   * @param cacheDir String the directory the cache will use to store the contents.
   * @param cacheLifeTime long Cache lifetime in milliseconds. If cacheLifeTime=0 it will be disabled.
   */
  public void enableCache(String cacheDir, long cacheLifeTime){
	  this.cacheDir = cacheDir;
	  this.cacheLifeTime = cacheLifeTime;
  }

  /**
   * Returns a RssChannelBean which represents the element <i>channel</i> in a <i>feed</i>
   * @see RssChannelBean
   * @return RssChannelBean
   */
  public RssChannelBean getChannel() throws Exception {
  	 if (rssType!=TYPE_ATOM) return this.getChannelRss();
  	 	else return this.getChannelAtom();
  }

  /**
   * Returns a RssChannelBean which represents the element <i>channel</i> in an Atom feed
   * @see RssChannelBean
   * @return RssChannelBean
   */
   private RssChannelBean getChannelAtom() throws Exception {
   	 RssChannelBean res = new RssChannelBean(rssType);
	 try{
		   Enumeration list = doc.xpathSelectElements("feed/title");
		   Element e;
		   if (list.hasMoreElements()){
		   	 e = (Element)list.nextElement();
		   	 res.setTitle(e.toString());
		   }
		   list = doc.xpathSelectElements("feed/tagline");
		   if (list.hasMoreElements()){
		   	 e = (Element)list.nextElement();
		   	 res.setDescription(e.toString());
		   }
		   list = doc.xpathSelectElements("feed/link");
		   while (list.hasMoreElements()){
		     e = (Element)list.nextElement();
		     if (e.getAttribute("type").equals("text/html")) res.setLink(e.getAttribute("href"));
		   }
		   list = doc.xpathSelectElements("feed/modified");
		   if (list.hasMoreElements()){
		   	 e = (Element)list.nextElement();
		   	 res.setPubDate(e.toString());
		   }
		   list = doc.xpathSelectElements("feed");
		   if (list.hasMoreElements()){
		   	 e = (Element)list.nextElement();
		   	 res.setLanguage(e.getAttribute("xml:lang"));
		   }

	 }catch(Exception e){
		   throw new Exception("Error obteniendo elemento canal de " + filename, e);
	 }
   	 return res;
   }

  /**
  * Returns a RssChannelBean which represents the element <i>channel</i> in an RSS feed
  * @see RssChannelBean
  * @return RssChannelBean
  */
  private RssChannelBean getChannelRss() throws Exception {
	 RssChannelBean res = new RssChannelBean(rssType);
	 RssDublinCoreModuleBean dcBean = new RssDublinCoreModuleBean();
	 RssSyndicationModuleBean syBean = new RssSyndicationModuleBean();
	 try{
	   Enumeration list = doc.xpathSelectElements(channelXPath);
	   while (list.hasMoreElements()){
	     Element e = (Element)list.nextElement();
		 if(e.getTagName().equals("title")) res.setTitle(e.toString());
		   else if(e.getTagName().equals("link")) res.setLink(e.toString());
		   else if(e.getTagName().equals("description")) res.setDescription(e.toString());
		   else if (e.getTagName().indexOf("dc:")>=0) dcBean = setDublinCoreElement(e, dcBean);
		   else if (e.getTagName().indexOf("syn:")>=0) syBean = setSyndicationElement(e, syBean);
		   else if (e.getTagName().indexOf("language")>=0) res.setLanguage(e.toString());
		   else if (e.getTagName().indexOf("copyright")>=0) res.setCopyright(e.toString());
		   else if (e.getTagName().indexOf("managingEditor")>=0) res.setPublisher(e.toString());
		   else if (e.getTagName().indexOf("webMaster")>=0) res.setCreator(e.toString());
		   else if (e.getTagName().indexOf("pubDate")>=0) res.setPubDate(e.toString());
		   else if (e.getTagName().indexOf("lastBuildDate")>=0) res.setLastBuildDate(e.toString());
		   else if (e.getTagName().indexOf("category")>=0) res.setCategory(e.toString());
		   else if (e.getTagName().indexOf("generator")>=0) res.setGenerator(e.toString());
		   else if (e.getTagName().indexOf("docs")>=0) res.setDocs(e.toString());
		   else if (e.getTagName().indexOf("cloud")>=0) res.setCloud(e.toString());
		   else if (e.getTagName().indexOf("ttl")>=0) res.setTtl(e.toString());
		   else if (e.getTagName().indexOf("rating")>=0) res.setRating(e.toString());
	   }
	 }
	 catch (Exception e){
	   throw new Exception("Error obteniendo elemento canal de " + filename, e);
	 }
	 res.setDublinCoreModule(dcBean);
	 res.setSyndicationModule(syBean);
	 return res;
  }

  /**
   * Returns a RssImageBean which represents the element <i>image</i> in a feed
   * @see RssImageBean
   * @return RssImageBean
   */
   public RssImageBean getImage() throws Exception {
   	 if (rssType!=TYPE_ATOM) return this.getImageRss();
   	 	else return new RssImageBean();
   }

  /**
  * Returns a RssImageBean which represents the element <i>image</i> in a RSS feed
  * @see RssImageBean
  * @return RssImageBean
  */
  private RssImageBean getImageRss() throws Exception {
     RssImageBean res = new RssImageBean();
     try{
       Enumeration list = doc.xpathSelectElements(xPath + "/image/*");
       if (!list.hasMoreElements()) list = doc.xpathSelectElements(xPath + "/channel/image/*");
       while (list.hasMoreElements()){
         Element e = (Element)list.nextElement();
         if(e.getTagName().equals("title")) res.setTitle(e.toString());
           else if(e.getTagName().equals("url")) res.setUrl(e.toString());
           else if(e.getTagName().equals("link")) res.setLink(e.toString());
       }
     }catch (Exception e){
       throw new Exception("Error obteniendo elemento imagen de " + filename, e);
     }

     return res;
  }

  /**
   * Returns a Vector of RssItemBean with all the entries of a <i>feed</i>
   * @see RssItemBean
   * @return java.util.Vector
   */
   public Vector getItems() throws Exception {
   	  if (rssType!=TYPE_ATOM) return this.getItemsRss();
   	  	else return this.getItemsAtom();
   }

   /**
    * Returns a Vector of RssItemBean with all the entries of an ATOM <i>feed</i>
    * @see RssItemBean
    * @return java.util.Vector
    */
    private Vector getItemsAtom() throws Exception {
  	 Vector res = new Vector();
  	 try{
  	   Enumeration list = doc.xpathSelectElements("feed/entry");
  	   int i=1;
  	   while (list.hasMoreElements()){
  	     list.nextElement();
  		 res.add(getItemAtom(i));
  		 i++;
  	   }
  	 }
  	 catch (Exception e){
  	   throw new Exception("Error el Vector de elementos entry de " + filename ,e);
  	 }
  	 return res;
    }


  /**
  * Returns a Vector of RssItemBean with all the entries of a RSS feed
  * @see RssItemBean
  * @return java.util.Vector
  */
  private Vector getItemsRss() throws Exception {
	 Vector res = new Vector();
	 try{
	   Enumeration list = doc.xpathSelectElements(xPath + "/item");
	   int i=1;
	   while (list.hasMoreElements()){
	     list.nextElement();
		 res.add(getItemRss(i));
		 i++;
	   }
	 }
	 catch (Exception e){
	   throw new Exception("Error el Vector de elementos item de " + filename, e);
	 }
	 return res;
  }

  /**
   * Returns a RssItemBean which represents the element <i>entry</i> in an ATOM feed.
   * 
   * @param index Index of the element to be returned.
   * @see RssItemBean
   * @return RssItemBean
   */
   private RssItemBean getItemAtom(int index) throws Exception{
 	 RssItemBean res = new RssItemBean(rssType);

 	 try{
 	   Element e;
 	   //Gets the title
 	   Enumeration list = doc.xpathSelectElements("feed/entry[" + index + "]/title");
 	   if (list.hasMoreElements()){
 	     e = (Element)list.nextElement();
 	     res.setTitle(e.toString());
 	   }

 	   //Gets the link
 	   list = doc.xpathSelectElements("feed/entry[" + index + "]/link");
 	   while (list.hasMoreElements()){
 	     e = (Element)list.nextElement();
 	     if (e.getAttribute("type").equals("text/html")) res.setLink(e.getAttribute("href"));
 	   }

 	   //Gets the description/content
 	   list = doc.xpathSelectElements("feed/entry[" + index + "]/content");
 	   if (list.hasMoreElements()){
	     e = (Element)list.nextElement();
	     res.setDescription(e.toString());
	   }

 	   //Gets the author
 	   list = doc.xpathSelectElements("feed/entry[" + index + "]/author/name");
 	   if (list.hasMoreElements()){
	     e = (Element)list.nextElement();
	     res.setAuthor(e.toString());
	   }

 	   //Gets the creation date
 	   list = doc.xpathSelectElements("feed/entry[" + index + "]/created");
       String datePublish = "";
 	   if (list.hasMoreElements()){
	     e = (Element)list.nextElement();
         datePublish = e.toString();
	     res.setPubDate(datePublish);
	   }

       //If it is Atom 1.0 the creation date is in "published"
 	   if ((datePublish==null) || (datePublish.equals(""))){
           list = doc.xpathSelectElements("feed/entry[" + index + "]/published");
           if (list.hasMoreElements()){
             e = (Element)list.nextElement();
             datePublish = e.toString();
             res.setPubDate(datePublish);
           }
       }
 	 }
 	 catch (Exception e){
 	   throw new Exception("Error obtaining the entry at position " + index + " of " + filename, e);
 	 }

 	 return res;
   }

  /**
  * Returns a RssItemBean which represents an element <i>item</i> in a RSS feed. 
  * 
  * @param index Index of the element to be returned
  * @see RssItemBean
  * @return RssItemBean
  */
  private RssItemBean getItemRss(int index) throws Exception{
	 RssItemBean res = new RssItemBean(rssType);
	 RssDublinCoreModuleBean dcBean = new RssDublinCoreModuleBean();
	 RssSlashModuleBean slashBean = new RssSlashModuleBean();
     RssEnclosureBean enclosureBean = new RssEnclosureBean();
	 try{
	   Enumeration list = doc.xpathSelectElements(xPath + "/item[" + index + "]/*");
	   while (list.hasMoreElements()){
	     Element e = (Element)list.nextElement();
		 if(e.getTagName().equals("title")) res.setTitle(e.toString());
		   else if(e.getTagName().equals("link")) res.setLink(e.toString());
		   else if(e.getTagName().equals("description")) res.setDescription(e.toString());
		   else if (e.getTagName().indexOf("dc:")>=0) dcBean = setDublinCoreElement(e, dcBean);
		   else if (e.getTagName().indexOf("slash:")>=0) slashBean = setSlashElement(e, slashBean);
		   else if (e.getTagName().indexOf("author")>=0) res.setAuthor(e.toString());
		   else if (e.getTagName().indexOf("pubDate")>=0) res.setPubDate(e.toString());
		   else if (e.getTagName().indexOf("category")>=0) res.setCategory(e.toString());
		   else if (e.getTagName().indexOf("comments")>=0) res.setComments(e.toString());
		   else if (e.getTagName().indexOf("enclosure")>=0) enclosureBean = setEnclosure(e);
		   else if (e.getTagName().indexOf("guid")>=0) res.setGuid(e.toString());
		   else if (e.getTagName().indexOf("source")>=0) res.setSource(e.toString());
	   }
	 }
	 catch (Exception e){
	   throw new Exception("Error obtaining the entry at position " + index + " of " + filename, e);
	 }
     res.setEnclosure(enclosureBean);
	 res.setDublinCoreModule(dcBean);
	 res.setSlashModule(slashBean);
	 return res;
  }

  private RssDublinCoreModuleBean setDublinCoreElement(Element e, RssDublinCoreModuleBean dcBean){
      if(e.getTagName().equals("dc:title")) dcBean.setTitle(e.toString());
        else if(e.getTagName().equals("dc:creator")) dcBean.setCreator(e.toString());
        else if(e.getTagName().equals("dc:subject")) dcBean.setSubject(e.toString());
        else if(e.getTagName().equals("dc:description")) dcBean.setDescription(e.toString());
        else if(e.getTagName().equals("dc:publisher")) dcBean.setPublisher(e.toString());
        else if(e.getTagName().equals("dc:contributor")) dcBean.setContributor(e.toString());
        else if(e.getTagName().equals("dc:date")) dcBean.setDate(e.toString());
        else if(e.getTagName().equals("dc:type")) dcBean.setType(e.toString());
        else if(e.getTagName().equals("dc:format")) dcBean.setFormat(e.toString());
        else if(e.getTagName().equals("dc:indentifier")) dcBean.setIdentifier(e.toString());
        else if(e.getTagName().equals("dc:source")) dcBean.setSource(e.toString());
        else if(e.getTagName().equals("dc:language")) dcBean.setLanguage(e.toString());
        else if(e.getTagName().equals("dc:relation")) dcBean.setRelation(e.toString());
        else if(e.getTagName().equals("dc:coverage")) dcBean.setCoverage(e.toString());
        else if(e.getTagName().equals("dc:rights")) dcBean.setRights(e.toString());
      return dcBean;
  }

  private RssSlashModuleBean setSlashElement(Element e, RssSlashModuleBean slashBean){
      if(e.getTagName().equals("slash:section")) slashBean.setSection(e.toString());
        else if(e.getTagName().equals("slash:department")) slashBean.setDepartment(e.toString());
        else if(e.getTagName().equals("slash:comments")) slashBean.setComments(new Integer(e.toString()).intValue());
        else if(e.getTagName().equals("slash:hit_parade")) slashBean.setHitParade(e.toString());
      return slashBean;
  }

  private RssSyndicationModuleBean setSyndicationElement(Element e, RssSyndicationModuleBean syBean){
      if(e.getTagName().equals("syn:updatePeriod")) syBean.setPeriod(e.toString());
        else if(e.getTagName().equals("syn:updateFrequency")) syBean.setFrequency(new Integer(e.toString()).intValue());
        else if(e.getTagName().equals("syn:updateBase")) syBean.setBase(e.toString());
      return syBean;
  }

  private RssEnclosureBean setEnclosure(Element e){
      RssEnclosureBean enclosure = new RssEnclosureBean();
      enclosure.setURL(e.getAttribute("url"));
      enclosure.setLength(e.getAttribute("length"));
      enclosure.setType(e.getAttribute("type"));
      return enclosure;
  }

  private void parseFromReader(BufferedReader buffer) throws Exception{
	    //BufferedReader buffer = this.getReader(filename);
	    String line;
	    String xml = "";
	    try {
	        while ((line = buffer.readLine()) != null) xml = xml + line;
	        doc = Parser.parse(xml);
	    }catch(Exception e){
	        doc = new Document();
	        throw new Exception("Error reading the file " + filename, e);	        
	    }
	    this.setChannelXPath();
  }

  private void parseFromFile() throws Exception{
       try{
         File xmlFile = new File(filename);
         doc = Parser.parse( xmlFile );
       }catch (Exception e){
         System.out.println("Error reading the file " + filename);
         System.out.println("RssParser:parseFromFile() ERROR: " + e.getMessage() );
         doc = new Document();
       }
       this.setChannelXPath();
  }

  private void parseFromURL() throws Exception{
	if (this.cacheLifeTime==0){
	    this.parseFromReader(this.getReaderFromUrl(this.filename));
	}else {
		this.parseFromCache();
	}
  }

  private void parseFromCache() throws Exception{
	  String encFilename = this.encode(this.filename);
	  if (this.cacheExpired()){
		  this.saveURL(this.filename, this.cacheDir, encFilename);
	  }
	  this.filename = this.cacheDir + "/" + encFilename;
	  this.parseFromFile();
  }

  private boolean cacheExpired(){
	  boolean res = false;
	  String filename = this.encode(this.filename);
	  File file = new File(this.cacheDir + "/" + filename);
	  boolean exists = file.exists();
	  if (exists){
		  long actualTime = System.currentTimeMillis();
		  long fileTime = file.lastModified();
		  if ((actualTime-fileTime)>this.cacheLifeTime) res = true;
	  }else res = true;
	  return res;
  }

  private boolean saveURL(String url, String path, String filename) throws IOException{
     boolean res = false;
     this.checkPath(path);
     BufferedReader buffer = getReaderFromUrl(url);
     if (buffer!=null){
         try{
          FileOutputStream f = new FileOutputStream(path + "/" + filename);
          OutputStreamWriter writer = new OutputStreamWriter(f);
          String line;
          while ((line = buffer.readLine()) != null) {
             writer.write(line + "\n",0,line.length() + 1);
          }
          writer.close();
          res = true;
        }catch (IOException e){
          throw new IOException("Error writing the file " + path + "/" + filename);
        }
     }
     return res;
  }

  private void checkPath(String dir){
	  File f = new File(dir);
	  if (!f.exists()) f.mkdirs();
  }

  private BufferedReader getReaderFromFile(String file) throws IOException {
	    BufferedReader buffer = null;
	    try{
	      FileInputStream fis = new FileInputStream(file);
	      InputStreamReader input = new InputStreamReader(fis,this.charset);
		  buffer = new BufferedReader(input);
		  return buffer;
		}
		catch (IOException e){
		  throw new IOException("Error obtaining the reader from " + filename);
		}

  }

  private BufferedReader getReaderFromUrl(String url) throws IOException{
    BufferedReader buffer = null;
    try{
 	  URL urlConn= new URL(url);
	  URLConnection conn = urlConn.openConnection();
	  InputStreamReader input = new InputStreamReader(conn.getInputStream(),this.charset);
	  buffer = new BufferedReader(input);
	  return buffer;
	}
	catch (IOException e){
	  throw new IOException("Error obtaining the reader from " + url);
	}
  }

  private String getRootXPath() throws Exception {
	   String xPath = null;
	   try{
	     Enumeration list = doc.xpathSelectElements("rdf:RDF");
	     if (list.hasMoreElements()) xPath = "rdf:RDF";
	       else {
 	       	 list = doc.xpathSelectElements("rss/channel");
	       	 if (list.hasMoreElements()) xPath = "rss/channel";
	       		else xPath = "atom";
	       }
	   }catch (Exception e){
	     throw new Exception("Error obtaining the file XPath root [" + filename + "]", e);
	   }
	   return xPath;
  }

  private void setChannelXPath() throws Exception {
       xPath = getRootXPath();
       if (xPath.equals("rdf:RDF")) {
             channelXPath = xPath + "/channel/*";
             rssType = TYPE_RDF;
       }
       else if (xPath.equals("rss/channel")){
             channelXPath = xPath + "/*";
             rssType = TYPE_RSS;
       }else {
       		 rssType = TYPE_ATOM;
       }
  }

  private String encode(String filename){
	  String res =  URLEncoder.encode(filename) + ".cache";
	  return res;
  }
}