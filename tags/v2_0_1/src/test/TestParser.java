package test;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.horrabin.horrorss.RssChannelBean;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssImageBean;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;

public class TestParser extends TestCase {

	private RssChannelBean channel;
	private RssImageBean image;
	private List<RssItemBean> items;

	public void fileParse(String filename){
		this.fileParse(filename, null);
	}

	public void fileParse(String filename, String charset){
		RssParser rss = new RssParser(filename);
		if (charset!=null) rss.setCharset(charset);
		try {
			RssFeed obj = rss.load();
			this.channel = obj.getChannel(); //Gets the channel element
			this.items = obj.getItems(); //Gets a List of RssItemBean
			this.image = obj.getImage(); //Gets the image element
		}catch(Exception e){
			e.printStackTrace();
		}				
	}
	
	public void testCache(){
		RssParser rss = new RssParser();
		rss.enableCache("java/test/data/", 1000);
		
		File file = new File("java/test/data/https%3A%2F%2Fhorrorss.googlecode.com%2Fsvn%2Ftrunk%2Fsrc%2Ftest%2Fdata%2Frss-utf8.xml.cache");
		assertEquals(false, file.exists());
		
		try {
			rss.load("https://horrorss.googlecode.com/svn/trunk/src/test/data/rss-utf8.xml");
			assertEquals(true, file.exists());
			file.delete();
		}catch(Exception e){
			System.out.println("ERROR testing the cache system");
		}
	}

	public void testUTF8FileParse(){
		this.fileParse("java/test/data/rss-utf8.xml", "utf-8");
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
		assertEquals("Sun Jul 27 13:50:00 CEST 2008", item.getPubDate().toString());	
	}

	public void testUTF8URLParse(){
		this.fileParse("https://horrorss.googlecode.com/svn/trunk/src/test/data/rss-utf8.xml", "utf-8");
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
		assertEquals("Sun Jul 27 13:50:00 CEST 2008", item.getPubDate().toString());
	}

	public void testISO88591FileParse(){
		this.fileParse("java/test/data/rss-iso-8859-1.xml", "iso-8859-1");
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
		assertEquals("Sun Jul 27 13:50:00 CEST 2008", item.getPubDate().toString());		
	}

	public void testISO88591URLParse(){
		this.fileParse("http://horrorss.googlecode.com/svn/trunk/src/test/data/rss-iso-8859-1.xml", "iso-8859-1");
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
		assertEquals("Sun Jul 27 13:50:00 CEST 2008", item.getPubDate().toString());		
	}

	public void testAtomParse(){
		this.fileParse("java/test/data/rss-atom.xml");
		assertEquals(21,this.items.size());

		assertEquals("El blog horroroso de Horrabin", this.channel.getTitle());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
		assertEquals("Sun Jul 27 13:50:00 CEST 2008", item.getPubDate().toString());
		
		assertEquals("", this.image.getUrl());
	}
	
	public void testRSSParse(){
		this.fileParse("java/test/data/rss20.xml");
		assertEquals(12,this.items.size());

		assertEquals("JRMora, humor gráfico", this.channel.getTitle());

		RssItemBean item = this.items.get(0);
		assertEquals("Activismo de clic", item.getTitle());

		item = this.items.get(11);
		assertEquals("Emprendedores", item.getTitle());
		assertEquals("Thu Jan 12 15:45:45 CET 2012", item.getPubDate().toString());
		
		assertEquals("http://www.jrmora.com/jr_bocata.jpg", this.image.getUrl());
	}	

	public void testRDFParse(){
		this.fileParse("java/test/data/rss-rdf.xml");
		assertEquals(10,this.items.size());

		assertEquals("Barrapunto", this.channel.getTitle());

		RssItemBean item = this.items.get(0);
		assertEquals("Bloomberg abre su API de datos", item.getTitle());

		item = this.items.get(9);
		assertEquals("Los mejores inicios de novela", item.getTitle());
		assertEquals("Tue Jan 31 00:00:00 CET 2012", item.getPubDate().toString());
		
		assertEquals("http://barrapunto.com/images/topics/topicbarrapunto.png", this.image.getUrl());
	}

	public void testFileParse(){
		this.fileParse("java/test/data/rss-utf8.xml");
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}
			
	public void testURLParse(){
		this.fileParse("http://horrorss.googlecode.com/svn/trunk/src/test/data/rss-utf8.xml");		
		
		assertEquals(21,this.items.size());

		RssItemBean item = this.items.get(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = this.items.get(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}
}
