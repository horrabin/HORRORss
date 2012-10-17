/**
 * DefaultDateParser.java 
 *
 * HORRORss Package, Version 2.2.0
 * Simple RSS parser
 *
 * October 16, 2012
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
package org.horrabin.horrorss.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.horrabin.horrorss.RssParser;

public class DefaultDateParser implements DateParser {

	public Date getDate(String date, int rssType) throws Exception {
		  Date res = null;
		  String pattern = null;
			
		  switch (rssType){
		  	case RssParser.TYPE_RDF: {
		  		if (date.indexOf("+")>=0)
		  			pattern = "yyyy-MM-dd'T'HH:mm:ss+HH:mm";
		  		else pattern = "yyyy-MM-dd'T'HH:mm:ss-HH:mm";
		  		break;
		  	}
			case RssParser.TYPE_RSS: {
				pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
				break;
			}
			case RssParser.TYPE_ATOM: {
				pattern = "yyyy-MM-dd'T'HH:mm:ss";
				break;				
			}
		  }
		  
		  try {
			  SimpleDateFormat sd = new SimpleDateFormat(pattern, Locale.ENGLISH);
			  res = sd.parse(date);
		  } catch (Exception e) {
			  System.out.println("Error parsing date: " + date + " [Type: " + rssType + "] --" + e.toString());
			  //throw e;
		  }	  
		  
		  return res;
	}

}
