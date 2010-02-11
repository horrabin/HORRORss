/**
 * RssSlashModule.java 
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

/**
* The RssSlashModuleBean object represents an <i>Slash</i> module of an RSS feed. 
* Valid for RDF specification. 
* @author Fernando Fornieles
*/ 
public class RssSlashModuleBean {
  private String section;
  private String department;
  private int comments;
  private String hit_parade;

  /**
   * Create a new Slash module
   */
  public RssSlashModuleBean(){
     section = "";
     department = "";
     comments = 0;
     hit_parade = "";
  }

  /**
   * Create a new Slash module
   * @param section Sets the section
   * @param department Sets the department
   * @param comments Sets the number of comments
   * @param hit_parade Sets the hit parade 
   */
  public RssSlashModuleBean(String section, String department, int comments, String hit_parade){
	 this.section = section;
	 this.department = department;
	 this.comments = comments;
	 this.hit_parade = hit_parade;
  }

  /**
   * Sets the section of the item
   * @param section The section of the item
   */
  public void setSection(String section){
	 this.section = section;
  }

  /**
   * Sets the department of the item
   * @param department The department of the item
   */  
  public void setDepartment(String department){
	 this.department = department;
  }

  /**
   * Sets the number of comments of the item
   * @param comments Number of comments of the item
   */  
  public void setComments(int comments){
	 this.comments = comments;
  }

  /**
   * Sets the hit parade of the item
   * @param hit_parade Hit Parade of the item
   */  
  public void setHitParade(String hit_parade){
	 this.hit_parade = hit_parade;
  }

  /**
   * Returns the section of the item
   * @return Section of the item
   */  
  public String getSection(){
	 return section;
  }

  /**
   * Returns the department of the item
   * @return Department of the item
   */    
  public String getDepartment(){
	 return department;
  }

  /**
   * Returns the number of comments of the item
   * @return Number of comments of the item
   */    
  public int getComments(){
	 return comments;
  }

  /**
   * Returns the  hit parade of the item
   * @return Hit parade of the item
   */    
  public String getHitParade(){
	 return hit_parade;
  }
}