/**
 * RssModuleParser.java 
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

package org.horrabin.horrorss;

import com.hp.hpl.sparta.Document;

/**
 * Implement this interface if you want to obtain elements from the RSS file that are not
 * parsed by default
 *  
 * @author Fernando Fornieles
 *
 */
public interface RssModuleParser {

	/**
	 * Obtain additional items from the channel element 
	 * @param rssType The type of the RSS file (RSS, RDF or ATOM)
	 * @param doc The Document object
	 * @return A custom object mapping your data
	 * @throws Exception
	 */
	public Object parseChannel(int rssType, Document doc) throws Exception;
	
	/**
	 * Obtain additional items from the image element 
	 * @param rssType The type of the RSS file (RSS, RDF or ATOM)
	 * @param doc The Document object
	 * @return A custom object mapping your data
	 * @throws Exception
	 */
	public Object parseImage(int rssType, Document doc) throws Exception;
	
	/**
	 * Obtain additional items from the item element 
	 * @param rssType The type of the RSS file (RSS, RDF or ATOM)
	 * @param doc The Document object
	 * @param index The index of the current item
	 * @return A custom object mapping your data
	 * @throws Exception
	 */
	public Object parseItem(int rssType, Document doc, int index) throws Exception;
}
