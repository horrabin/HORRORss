/**
 * RssFeed.java 
 *
 * HORRORss Package, Version 2.1.0
 * Simple RSS parser
 *
 * March 3, 2012
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

import java.util.List;

/**
* The RssFeed class maps the elements of an RSS feed 
* (channel, image and a list of items) 
*  
* @author Fernando Fornieles 
*/
public class RssFeed {
	
	private RssChannelBean channel;
	private RssImageBean image;
	private List<RssItemBean> items;
	
	/**
	 * Returns the feed channel element
	 * @return the feed channel element
	 */
	public RssChannelBean getChannel() {
		return channel;
	}
	
	/**
	 * Sets the feed channel element
	 * @param channel The feed channel element
	 */
	public void setChannel(RssChannelBean channel) {
		this.channel = channel;
	}
	
	/**
	 * Returns the feed image element
	 * @return the feed image element
	 */	
	public RssImageBean getImage() {
		return image;
	}
	
	/**
	 * Sets the feed image element
	 * @param image The feed image element
	 */
	public void setImage(RssImageBean image) {
		this.image = image;
	}
	
	/**
	 * Returns the feed items list
	 * @return List of RssItemBean
	 */	
	public List<RssItemBean> getItems() {
		return items;
	}
	
	/**
	 * Sets the feed items list
	 * @param items The feed item list
	 */
	public void setItems(List<RssItemBean> items) {
		this.items = items;
	}
	
}
