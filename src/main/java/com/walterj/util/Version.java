package com.walterj.util;/*
 * Copyright (C) 2000-2003, MBI Solutions.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of MBI Solutions. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with MBI Solutions.
 *
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 * Container class to represent versioning information for a project.
 *
 * @author Ryan O. Stouffer, Scott Gray
 * @version $Id: $Id
 */
public class Version
    implements Serializable {

   private static final long serialVersionUID = 1L;

   private static final Logger LOG = Logger.getLogger(Version.class.getName());

   private static final String VERSION = "%d.%d.%d (Build %s)";
   private static final String COPYRIGHT = "Copyright © 2011-%d, MBI Solutions";

   private final String name;
   private final int major;
   private final int minor;
   private final int patchLevel;
   private final String releaseDate;
   private String copyright = null;
   private String version = null;

   /**
    * The object that represents  product versions
    *
    * @see VersionTool
    * *
    * @param name The name of the product
    * @param major the major version number
    * @param minor the minor version number
    * @param patchLevel the patch level
    * @param releaseDate the date of the release
    */
   public Version(String name, int major, int minor, int patchLevel, 
       String releaseDate) {
      
      this.name = name;
      this.major = major;
      this.minor = minor;
      this.patchLevel = patchLevel;
      this.releaseDate = releaseDate;
   }

   /**
    * Returns the full name of a product.
    *
    * @return the full name of a product, or null if
    *   the product does not exist.
    */
   public String getName() {

      return this.name;
   }

   /**
    * Returns the major version number for a given product.
    *
    * @return the major version number for a given product, or -1 if
    *   the product does not exist.
    */
   public int getMajor() {

      return this.major;
   }

   /**
    * Returns the minor version number for a given product.
    *
    * @return the minor version number for a given product, or -1 if
    *   the product does not exist.
    */
   public int getMinor() {

      return this.minor;
   }

   /**
    * Returns the patch level for a given product.
    *
    * @return the patch level for a given product, or -1 if
    *   the product does not exist.
    */
   public int getPatchLevel() {

      return this.patchLevel;
   }

   /**
    * Returns the release date for the product. This will be
    * a string of the format YYMMDD.
    *
    * @return a {@link String} object.
    */
   public String getReleaseDate() {

      return this.releaseDate;
   }

   /**
    * Returns a standard copyright string of the format:
    *
    * <pre>
    *    <i>Product</i> net.mbisolutions.util.Version <i>major.minor.patch-level</i>
    *    (<i>release-date</i>) Copyright (C) 2000-2003,
    *    MBI Solutions.
    * </pre>
    *
    * @return a {@link String} object.
    */
   public String getCopyright() {

      if (copyright == null) {
         
         Calendar cal = GregorianCalendar.getInstance();
         copyright = String.format(COPYRIGHT, cal.get(Calendar.YEAR));
      }

      return copyright;
      
   }
   
   /**
    *   <pre>
    *     <i>Product</i> net.mbisolutions.util.Version <i>major.minor.patch-level</i>
    *     (<i>release-date</i>)
    *   </pre>
    *
    * @return A string
    */
   public String getVersionString() {
   
      if (version == null) {
         version = String.format(VERSION, major, minor, patchLevel,
             releaseDate);
      }
   
      return version;
   }


   /**
    * Returns a standard copyright string of the format:
    *
    * <pre>
    *    <i>Product</i> net.mbisolutions.util.Version <i>major.minor.patch-level</i>
    *    (<i>release-date</i>) Copyright (C) 2000-2003,
    *    MBI Solutions.
    * </pre>
    *
    * @return a {@link String} object.
    */
   public String toString() {

      return getCopyright();
   }
}
