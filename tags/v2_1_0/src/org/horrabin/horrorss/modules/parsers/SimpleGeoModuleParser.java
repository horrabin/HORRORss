/**
 * SimpleGeoModuleParser.java 
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

package org.horrabin.horrorss.modules.parsers;

import org.horrabin.horrorss.RssModuleParser;
import org.horrabin.horrorss.modules.objects.SimpleGeoRssItemBean;

import com.hp.hpl.sparta.Document;

public class SimpleGeoModuleParser implements RssModuleParser {

	@Override
	public Object parseChannel(int rssType, Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parseImage(int rssType, Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parseItem(int rssType, Document doc, int index) throws Exception {
		// TODO Auto-generated method stub
		SimpleGeoRssItemBean geo = new SimpleGeoRssItemBean();
		
		try {
			geo.setLatitude(new Double(doc.xpathSelectString("rss/channel/item[" + index + "]/geo:lat/text()")));
			geo.setLongitude(new Double(doc.xpathSelectString("rss/channel/item[" + index + "]/geo:long/text()")));
		}catch(Exception e){
			throw new Exception("Error GeoRSS Module item at index " + index, e);
		}
		return geo;
	}

}
