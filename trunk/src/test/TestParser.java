package test;

import junit.framework.TestCase;
import org.horrabin.horrorss.*;
import java.util.Vector;

public class TestParser extends TestCase {

	private RssChannelBean channel;
	private Vector items;

	public void fileParse(String filename){
		this.fileParse(filename, null);
	}

	public void fileParse(String filename, String charset){
		RssParser rss = new RssParser(filename);
		if (charset!=null) rss.setCharset(charset);
		try {
			rss.parse();
			this.channel = rss.getChannel(); //Obtenemos el element channel
			this.items = rss.getItems(); //Obtenemos un Vector de elementos item (RssItemBean)
		}catch(Exception e){
			System.out.println("ERROR parsing RSS");
		}				
	}

	public void testUTF8FileParse(){
		this.fileParse("java/test/data/rss-utf8.xml", "utf-8");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testUTF8URLParse(){
		this.fileParse("http://localhost/horrorsstest/rss-utf8.xml", "utf-8");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testISO88591FileParse(){
		this.fileParse("java/test/data/rss-iso-8859-1.xml", "iso-8859-1");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testISO88591URLParse(){
		this.fileParse("http://localhost/horrorsstest/rss-iso-8859-1.xml", "iso-8859-1");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testAtomParse(){
		this.fileParse("java/test/data/rss-atom.xml");
		assertEquals(21,this.items.size());

		assertEquals("El blog horroroso de Horrabin", this.channel.getTitle());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testRDFParse(){
		this.fileParse("java/test/data/rss-rdf.xml");
		assertEquals(10,this.items.size());

		assertEquals("Noticias javaHispano.org", this.channel.getTitle());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("10 increibles aplicaciones Java", item.getTitle());

		item = (RssItemBean)this.items.elementAt(9);
		assertEquals("Versión alpha del framework de la JavaCup 2009", item.getTitle());
	}

	public void testFileParse(){
		this.fileParse("java/test/data/rss-utf8.xml");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}

	public void testURLParse(){
		this.fileParse("http://localhost/horrorsstest/rss-utf8.xml");
		assertEquals(21,this.items.size());

		RssItemBean item = (RssItemBean)this.items.elementAt(0);
		assertEquals("La niñez de Luke Skywalker", item.getTitle());

		item = (RssItemBean)this.items.elementAt(20);
		assertEquals("Y con este sencillo acto...", item.getTitle());
	}
}
