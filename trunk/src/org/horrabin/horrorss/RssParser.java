/**
 * RssParser.java
 *
 * HORRORss Package, Version 2.2.0
 * Simple RSS parser
 *
 * October 16, 2012
 *
 * Copyright (C) 2012 Fernando Fornieles
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.horrabin.horrorss.util.DateParser;
import org.horrabin.horrorss.util.DefaultDateParser;

import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.Parser;

/**
* RSS file parser compatible with all RSS, RDF & Atom specifications.
* @author Fernando Fornieles
*/
public class RssParser {
	public static final int TYPE_RDF = 0;
	public static final int TYPE_RSS = 1;
	public static final int TYPE_ATOM = 2;
	
	private Document doc;
	private String filename;
	private String xPath;
	private int rssType;
	private String charset = "utf8";
	private String cacheDir;
	private long cacheLifeTime = 0;
	
	private DateParser dateParser = new DefaultDateParser();
	private Map<String, RssModuleParser> moduleParsers;
	  

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
  
  public void setDateParser(DateParser dateParser){
	  this.dateParser = dateParser;
  }

  /**
   * Add a custom RssModuleParser, identified by the parameter name, to parse and obtain
   * additional info from the RSS file.
   * 
   * @param name String to identify the parser
   * @param moduleParser An implementation of RssModuleParser
   */
  public void addRssModuleParser(String name, RssModuleParser moduleParser){
	  if (this.moduleParsers == null) this.moduleParsers = new HashMap<String, RssModuleParser>();
	  this.moduleParsers.put(name, moduleParser);
  }
  
  /**
   * Parses the RSS source defined in the constructor and load its content into an RssFeed object
   * @return RSS mapped into an RssFeed object
   * @throws Exception
   */
  public RssFeed load() throws Exception {
      if ((this.filename.startsWith("http://")) || 
    	   (this.filename.startsWith("https://"))) this.parseFromURL();
         else this.parseFromReader(this.getReaderFromFile(this.filename));
       
      return this.getRssFeed();
  }

  /**
   * Parses an RSS string and load its content into an RssFeed object
   * @param xml An XML string 
   * @return RSS mapped into an RssFeed object
   * @throws Exception
   */
  public RssFeed loadString(String xml) throws Exception{
	    try {
	        doc = Parser.parse(xml);
	    }catch(Exception e){
	        doc = new Document();
	        throw new Exception("Error reading the file " + filename, e);	        
	    }
	    this.setChannelXPath();
	    
	    return this.getRssFeed();
  }
  
  /**
   * Parses an RSS source and load its content into an RssFeed object
   * @param filename It can be a filename or an URL
   * @return RSS mapped into an RssFeed object
   * @throws Exception
   */
  public RssFeed load(String filename) throws Exception{
	  this.filename = filename;
	  this.load();
	  return this.getRssFeed();
  }   
  
  /**
   * Parses an RSS file and load its content into an RssFeed object
   * @param file An RSS file 
   * @return RSS mapped into an RssFeed object
   * @throws Exception
   */
  public RssFeed load(File file) throws Exception{
	  InputStream input = new FileInputStream(file);
	  this.parseFromReader(this.getReaderFromInputStream(input));
	  return this.getRssFeed();
  }  

  /**
   * Parses an RSS from an input stream
   * @param input InputStream an input stream
   * @return RSS mapped into an RssFeed object
   * @throws Exception
   */
  public RssFeed load(InputStream input) throws Exception{
	  this.parseFromReader(this.getReaderFromInputStream(input));
	  return this.getRssFeed();
  }

  /**
   * Tells the parser what charset must use. Default UTF-8.
   * @param charset Charset name (iso-8859-1, utf-8,...).
   */
  public void setCharset(String charset){
	  this.charset = charset;
  }

  /**
   * Enables the caching system.
   * @param cacheDir The directory where the contents will be stored by the cache system
   * @param cacheLifeTime long Cache lifetime in milliseconds. If cacheLifeTime=0 it will be disabled.
   */
  public void enableCache(String cacheDir, long cacheLifeTime){
	  this.cacheDir = cacheDir;
	  this.cacheLifeTime = cacheLifeTime;
  }
  
  /**
   * Get the Document object containing all the RSS elements in the file
   * @return The Document object
   */  
  public Document getDocument(){
	  return this.doc;
  }

  private RssChannelBean getChannel() throws Exception {
  	 if (rssType!=TYPE_ATOM) return this.getChannelRss();
  	 	else return this.getChannelAtom();
  }

  private RssChannelBean getChannelAtom() throws Exception {
   	 RssChannelBean res = new RssChannelBean();
   	 String pubDate;
   	 
	 try{
		 res.setTitle(doc.xpathSelectString("/feed/title/text()"));		 
		 res.setDescription(doc.xpathSelectString("/feed/tagline/text()"));
		 res.setLink(doc.xpathSelectString("/feed/link/@href"));		 
		 pubDate =  doc.xpathSelectString("/feed/modified/text()");
		 if (pubDate!=null) res.setPubDate(this.getDate(pubDate, TYPE_ATOM));
		 this.parseAdditionalChannelInfo(res);
	 }catch(Exception e){
		   throw new Exception("Error obteniendo elemento canal de " + filename, e);
	 }
   	 return res;
  }

  private RssChannelBean getChannelRss() throws Exception {
	 RssChannelBean res = new RssChannelBean();
	 String datePublish;
	 
	 try{	   
	   if (this.rssType==TYPE_RDF){		   		   
		   res.setTitle(doc.xpathSelectString(this.xPath + "/channel/title/text()"));
		   res.setLink(doc.xpathSelectString(this.xPath + "/channel/link/text()"));
		   res.setDescription(doc.xpathSelectString(this.xPath + "/channel/description/text()"));	
		   datePublish = doc.xpathSelectString(this.xPath + "/channel/dc:date/text()");
	   }else {
		   res.setTitle(doc.xpathSelectString(this.xPath + "/title/text()"));
		   res.setLink(doc.xpathSelectString(this.xPath + "/link/text()"));
		   res.setDescription(doc.xpathSelectString(this.xPath + "/description/text()"));		   
		   datePublish = doc.xpathSelectString(this.xPath + "/pubDate/text()");
	   }
	   
	   if (datePublish!=null) res.setPubDate(this.getDate(datePublish, this.rssType));
	   
	   this.parseAdditionalChannelInfo(res);
	 }
	 catch (Exception e){
	   throw new Exception("Error reading element channel from " + filename, e);
	 }

	 return res;
  }

  private RssImageBean getImage() throws Exception {
   	 if (rssType!=TYPE_ATOM) return this.getImageRss();
   	 	else return new RssImageBean();
  }

  private RssImageBean getImageRss() throws Exception {
     RssImageBean res = new RssImageBean();
     String title;
     try{
       title = doc.xpathSelectString(this.xPath + "/image/title/text()");
       if (title!=null){
    	   res.setTitle(title);
    	   res.setLink(doc.xpathSelectString(this.xPath + "/image/link/text()"));
    	   res.setUrl(doc.xpathSelectString(this.xPath + "/image/url/text()"));
       }else{
    	   title = doc.xpathSelectString(this.xPath + "/channel/image/title/text()");
    	   if (title!=null){
    		   res.setTitle(title);
    		   res.setLink(doc.xpathSelectString(this.xPath + "/channel/image/link/text()"));
    		   res.setUrl(doc.xpathSelectString(this.xPath + "/channel/image/url/text()"));
    	   }
       }
       
       this.parseAdditionalImageInfo(res);
     }catch (Exception e){
       throw new Exception("Error obteniendo elemento imagen de " + filename, e);
     }

     return res;
  }

  private List<RssItemBean> getItems() throws Exception {
   	  if (rssType!=TYPE_ATOM) return this.getItemsRss();
   	  	else return this.getItemsAtom();
  }

  private List<RssItemBean> getItemsAtom() throws Exception {
     List<RssItemBean> res = new ArrayList<RssItemBean>();
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

  private List<RssItemBean> getItemsRss() throws Exception {
	 List<RssItemBean> res = new ArrayList<RssItemBean>();
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

  private RssItemBean getItemAtom(int index) throws Exception{
 	 RssItemBean res = new RssItemBean();
 	 String description;
 	 String datePublish;
 	 String link;
 	 
 	 try{
	   res.setTitle(doc.xpathSelectString("feed/entry[" + index + "]/title/text()"));
	   link = doc.xpathSelectString("feed/entry[" + index + "]/link/text()");
	   
	   if (link == null){
		   link = doc.xpathSelectString("feed/entry[" + index + "]/link[@rel=\"alternate\"]/@href");
	   }
	   res.setLink(link);
	   
	   description = doc.xpathSelectString("feed/entry[" + index + "]/content/text()");
	   if (description == null){
		   description = doc.xpathSelectString("feed/entry[" + index + "]/summary/text()");
	   }
	   res.setDescription(description);
	   res.setAuthor(doc.xpathSelectString("feed/entry[" + index + "]/author/name/text()"));

	   datePublish = doc.xpathSelectString("feed/entry[" + index + "]/created/text()");
       //If it is Atom 1.0 the creation date is in "published"
 	   if ((datePublish==null) || (datePublish.equals(""))){
 		   datePublish = doc.xpathSelectString("feed/entry[" + index + "]/published/text()");
       }
 	   
 	   res.setPubDate(this.getDate(datePublish, TYPE_ATOM));
 	   
 	   this.parseAdditionalItemInfo(res, index);
 	 }
 	 catch (Exception e){
 	   throw new Exception("Error obtaining the entry at position " + index + " of " + filename, e);
 	 }

 	 return res;
  }

  private RssItemBean getItemRss(int index) throws Exception{
	 RssItemBean res = new RssItemBean();
	 String datePublish;
	 String author;
 
	 try{
	   res.setTitle(doc.xpathSelectString(xPath + "/item[" + index + "]/title/text()"));
	   res.setLink(doc.xpathSelectString(xPath + "/item[" + index + "]/link/text()"));
	   res.setDescription(doc.xpathSelectString(xPath + "/item[" + index + "]/description/text()"));
	   if (this.rssType==TYPE_RDF){
		   author = doc.xpathSelectString(xPath + "/item[" + index + "]/dc:creator/text()");
		   datePublish = doc.xpathSelectString(xPath + "/item[" + index + "]/dc:date/text()");		   
	   }else{
		   author = doc.xpathSelectString(xPath + "/item[" + index + "]/author/text()");
		   if (author==null) author = doc.xpathSelectString(xPath + "/item[" + index + "]/dc:creator/text()");
		   datePublish = doc.xpathSelectString(xPath + "/item[" + index + "]/pubDate/text()");
	   }
	   res.setAuthor(author);
	   res.setPubDate(this.getDate(datePublish, this.rssType));
	   	  
	   this.parseAdditionalItemInfo(res, index);
	 }
	 catch (Exception e){
	   throw new Exception("Error obtaining the entry at position " + index + " of " + filename, e);
	 }

	 return res;
  }
  
  private void parseAdditionalChannelInfo(RssChannelBean item) throws Exception{
	  if (this.moduleParsers!=null){
		  Iterator<String> keys = this.moduleParsers.keySet().iterator();
		  while (keys.hasNext()){
			  String keyName = keys.next();
			  RssModuleParser moduleParser = this.moduleParsers.get(keyName);
			  item.putAdditionalInfo(keyName, moduleParser.parseChannel(this.rssType, this.doc));
		  }
	  }
  }  
  
  private void parseAdditionalImageInfo(RssImageBean item) throws Exception{
	  if (this.moduleParsers!=null){
		  Iterator<String> keys = this.moduleParsers.keySet().iterator();
		  while (keys.hasNext()){
			  String keyName = keys.next();
			  RssModuleParser moduleParser = this.moduleParsers.get(keyName);
			  item.putAdditionalInfo(keyName, moduleParser.parseImage(this.rssType, this.doc));
		  }
	  }
  }  
  
  private void parseAdditionalItemInfo(RssItemBean item, int index) throws Exception{
	  if (this.moduleParsers!=null){
		  Iterator<String> keys = this.moduleParsers.keySet().iterator();
		  while (keys.hasNext()){
			  String keyName = keys.next();
			  RssModuleParser moduleParser = this.moduleParsers.get(keyName);
			  item.putAdditionalInfo(keyName, moduleParser.parseItem(this.rssType, this.doc, index));
		  }
	  }
  }
  
  private void parseFromReader(BufferedReader buffer) throws Exception{
	    String line;
	    StringBuffer xml = new StringBuffer();
	    try {
	        while ((line = buffer.readLine()) != null) {
	        	xml.append(line);
	        }
	        
	        if (xml.charAt(0)=='\uFEFF') xml.setCharAt(0, '\n');
	        
	        doc = Parser.parse(xml.toString());
	    }catch(Exception e){
	        doc = new Document();
	        throw new Exception("Error reading the file " + filename, e);	        
	    }finally{
	    	buffer.close();
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

  private boolean cacheExpired() throws Exception {
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
  
  private BufferedReader getReaderFromInputStream(InputStream inputStream) throws IOException {
	  BufferedReader buffer = null;
	  InputStreamReader input = new InputStreamReader(inputStream, this.charset);
	  buffer = new BufferedReader(input);
	  return buffer;
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
	       		else xPath = "";
	       }
	   }catch (Exception e){
	     throw new Exception("Error obtaining the file XPath root [" + filename + "]", e);
	   }
	   return xPath;
  }

  private void setChannelXPath() throws Exception {
       xPath = getRootXPath();
       if (xPath.equals("rdf:RDF")) {
             rssType = TYPE_RDF;
       }
       else if (xPath.equals("rss/channel")){
             rssType = TYPE_RSS;
       }else {
       		 rssType = TYPE_ATOM;
       }
  }

  private String encode(String filename) throws Exception{
	  String res =  URLEncoder.encode(filename, this.charset) + ".cache";
	  return res;
  }
  
  private RssFeed getRssFeed() throws Exception{
      RssFeed rss = new RssFeed();
      rss.setChannel(this.getChannel());
      rss.setImage(this.getImage());
      rss.setItems(this.getItems());
      return rss;	  
  }
  
  private Date getDate(String date, int rssType) throws Exception {
	  return this.dateParser.getDate(date, rssType);
  }
}