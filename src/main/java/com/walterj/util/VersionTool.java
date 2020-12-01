package com.walterj.util;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * The VersionTool is used to manage product version
 * numbers by embedding them in property files that are contained
 * in the jars that make up the application.  The tool works by
 * looking for the file <b>app-version.properties</b> located in
 * the root of the classpath. This property file should be of
 * the format:
 *
 * <pre>
 *    products           = <i>prod1</i>,<i>prod2</i>
 *    <i>prod1</i>.name         = My product
 *    <i>prod1</i>.major        = 3
 *    <i>prod1</i>.minor        = 1
 *    <i>prod1</i>.patch-level  = 1
 *    <i>prod1</i>.release      = SNAPSHOT
 *    <i>prod2</i>.name         = Your product
 *    <i>prod2</i>.major        = 3
 *    <i>prod2</i>.minor        = 3
 *    <i>prod2</i>.patch-level  = 2
 *    <i>prod2</i>.release      = BETA
 * </pre>
 *
 * The VersionTool will locate and process all such files that it can find
 * in its classpath.
 *
 * @author Walter Jordan
 * @version $Id: $Id
 */

import java.io.IOException;
import java.util.*;

public class VersionTool {

   private static final Logger LOG
       = Logger.getLogger(VersionTool.class.getName());

   private final ClassLoader loader;

   private static final String VERSION_FILENAME = "app-version.properties";
   private static final String PRODUCTS     = "products";
   private static final String NAME         = "name";
   private static final String MAJOR        = "major";
   private static final String MINOR        = "minor";
   private static final String PATCH_LEVEL  = "patch-level";
   private static final String RELEASE_DATE = "release";
   
   /**
    * This special property is used in lieu of MAJOR/MINOR/PATCH_LEVEL
    * properties to specify a fully qualified version string. The string
    * expected is of the form of:<br>
    * <br>
    * &nbsp;&nbsp;<i>major</i>.<i>minor</i>.<i>patch</i>[-<i>release</i>]
    * <br>
    * 
    * <p>More specifically, this property was designed to allow us to
    * automagically use a Maven version string (${project.version})
    * to specify the version number rather than using the old-style
    * explicitly stated version number.
    */
   private static final String VERSION = "version";
   
   /**
    * Hash table to map from product name to version info.
    */
   private final Map<String,Version> productHash = new HashMap<>();

   /**
    * Primary constructor. This will utilize the system classloader
    * to determine product version numbers.
    */
   public VersionTool() {

      loader = getClass().getClassLoader();
      findInfo();
   }
   
   /**
    * Creates a version tool that determines product versions using
    * only the classloader provided.
    *
    * @param loader The {@link ClassLoader} to search for version descriptors
    */
   public VersionTool(ClassLoader loader) {

      this.loader = loader;
      findInfo();
   }
   
   /**
    * Returns the set of product string names that were
    *    located by the version tool.
    *
    * @return a {@link List} object.
    */
   public List<String> getProductNames() {

      List<String> names = new ArrayList<>();
      names.addAll(productHash.keySet());
      return names;
   }
   
   /**
    * Returns the Version class associated with the product
    *
    * @param product the name of the product
    * @return a util.Version object or null if none found with product name*
    */
   public Version getProductVersion(String product) {
      
      return productHash.get(product);
   }

   /**
    * Does the actual legwork of iterating through the version files
    * that are available and parsing the contents.
    */
   private void findInfo() {
      
      try {

         Enumeration<URL> urls = loader.getResources(VERSION_FILENAME);
         
         while (urls.hasMoreElements()) {
   
            Properties props = new Properties();
            URL url = urls.nextElement();
            String products;
            StringTokenizer tokenizer;
            
            props.load(url.openStream());
            products = props.getProperty(PRODUCTS);

            if (products == null || products.equals("")) {

               System.err.println("No Version info found for " + url.toString());
            }
            else {

               tokenizer = new StringTokenizer(products, ",", false);

               while (tokenizer.hasMoreTokens()) {

                  String product = tokenizer.nextToken().trim();
                  String name = props.getProperty(product + '.' + NAME);
                  Version v;

                  if (name == null) {

                     System.err.println("Name for product " + product + " not found");
                     continue;
                  }
                  
                  /*
                   * First check to see if there is a fully qualified version
                   * string available. If there is, then we can skip the
                   * individual property checks.
                   */
                  String version = props.getProperty(product + "." + VERSION);
                  
                  if (version != null) {
                     
                     v = parseVersion(name, product, version);
                     
                  }
                  else {
                     
                     v = buildVersion(name, product, props);
                  }
                  
                  if (v != null) {
                     
                     productHash.put(product, v);
                  }
               }
            }
         }
      }
      catch (IOException e) {

         System.err.println("Cannot get version information because exception " 
            + e.getMessage());
      }
   }
   
   /**
    * Builds a {@link Version} object based upon properties provided for a
    * specific project.
    * 
    * @param name
    *           Long descriptive name of product
    * @param product
    *           Short internal product identified
    * @param props
    *           Properties that contain version information.
    * 
    * @return The resulting version.
    */
   private Version buildVersion(String name, String product, Properties props) {
      
      String release = null; 
      int major = -1; 
      int minor = -1; 
      int patch = -1; 
      
      String temp;

      release = props.getProperty(product + '.' + RELEASE_DATE);

      if (release == null) {

         System.err.println("VersionTool: Release not set for product " + product);
         return null;
      }

      temp = props.getProperty(product + '.' + MAJOR);

      if (temp == null) {

         System.err.println("VersionTool: The major version id was not found for "
            + product);
         return null;
      }
      else {

         try {

            major = Integer.parseInt(temp);
         }
         catch (NumberFormatException e) {

            System.err.println("VersionTool: The major version id ("
               + temp + ") is not numeric for " + product);
            return null;
         }
      }

      temp = props.getProperty(product + '.' + MINOR);

      if (temp == null) {

         System.err.println("VersionTool: The minor version id was not found for "
            + product);

         return null;
      }
      else {

         try {

            minor = Integer.parseInt(temp);
         }
         catch (NumberFormatException e) {

            System.err.println("VersionTool: The minor version id ("
               + temp + ") is not numeric for "
               + product);
            

            return null;
         }
      }

      temp = props.getProperty(product + '.' + PATCH_LEVEL);

      if (temp == null) {

         System.err.println("VersionTool: The patch-level id was not found for "
            + product);
         
         return null;
      }
      else {

         try {

            patch = Integer.parseInt(temp);
         }
         catch (NumberFormatException e) {

            System.err.println("VersionTool: The patch-level id ("
               + temp + ") is not numeric for "
               + product);
            
            return null;
         }
      }
      
      return new Version(name, major, minor, patch, release);
   }
   

   /**
    * Used to parse the contents of the {@link #VERSION} property.
    * 
    * @param name    Long descriptive name of product
    * @param product Short internal product identified
    * @param version The version being parsed.
    * 
    * @return The resulting version or null if something goes wrong.
    */
   private Version parseVersion(String name, String product,  String version) {
      
      String release = null; 
      int major = -1; 
      int minor = -1; 
      int patch = -1; 
      
      /*
       * Split the version and patch level apart.
       */
      String []versionRelease = version.split("-");
      boolean ok = true;
      
      if (versionRelease.length < 1 || versionRelease.length > 2) {
         
         ok = false;
      }
      else {
      
	      /*
	       * A bit of a hack. Since I know this is being used to parse a 
	       * Maven version identifier and maven leaves the -SNAPSHOT off
	       * during an actual release, I'll call it -RELEASE automagically.
	       */
	      release = (versionRelease.length == 2) 
            ? versionRelease[1] : "GA";
	      
	      String []mmp = versionRelease[0].split("\\.");
	      if (mmp.length != 3) {
	         
            ok = false;
	      }
         
         if (ok) {
            
            
            try {
               
               major = Integer.parseInt(mmp[0]);
               minor = Integer.parseInt(mmp[1]);
               patch = Integer.parseInt(mmp[2]);
            }
            catch (NumberFormatException e) {
               
               ok = false;
            }
         }
      }
      
      if (!ok) {
         
	     System.err.println("VersionTool: Expected a version number of the form "
	        + "MAJOR.MINOR.PATCH[-NAME] for product "  + product + ", but got "
           + version);
        
        return null;
      }
      
      return new Version(name, major, minor, patch, release);
   }
   
   
   /**
    * Outputs all product information located in default classLoader
    *
    * @param args
    *           an array of product names to display if none specified then all
    *           found are displayed
    */
   public static void main (String[] args) {

      try {

         VersionTool vt = new VersionTool();
         Iterator iter;

         if (args.length > 0) {

            iter = Arrays.asList(args).iterator();
         }
         else {

            iter = vt.getProductNames().iterator();
         }

         while (iter.hasNext()) {

            String product = (String) iter.next();
            System.err.println(product + " = " + vt.getProductVersion(product));
         }
      }
      catch (Throwable e) {

         System.err.println("Error getting version info: " + e.getMessage());
      }
   }
}
