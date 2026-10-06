package com.moonsworth.lunar.legacy;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public enum DesktopPlatform {
   LINUX("linux"),
   SOLARIS("solaris"),
   WINDOWS("WINDOWS", 2, "windows"),
   OSX("OSX", 3, "mac"),
   UNKNOWN("unknown");

   public final String telemetryName;

   DesktopPlatform(String text3) {
      this.telemetryName = text3;
   }

   public void openUrl(URL url1) {
      try {
         Process process2 = Runtime.getRuntime().exec(this.getOpenUrlArguments(url1));
         process2.getInputStream().close();
         process2.getErrorStream().close();
         process2.getOutputStream().close();
      } catch (IOException exception3) {
         exception3.printStackTrace();
      }
   }

   public void openUri(URI uri1) {
      try {
         this.openUrl(uri1.toURL());
      } catch (MalformedURLException malformedurlexception3) {
         malformedurlexception3.printStackTrace();
      }
   }

   public void openFile(File file1) {
      try {
         this.openUrl(file1.toURI().toURL());
      } catch (MalformedURLException malformedurlexception3) {
         malformedurlexception3.printStackTrace();
      }
   }

   public String[] getOpenUrlArguments(URL url1) {
      String text2 = url1.toString();
      if ("file".equals(url1.getProtocol())) {
         text2 = text2.replace("file:", "file://");
      }

      return new String[]{"xdg-open", text2};
   }

   public void openUri(String text1) {
      try {
         this.openUrl(new URI(text1).toURL());
      } catch (IllegalArgumentException | URISyntaxException | MalformedURLException illegalargumentexception3) {
         illegalargumentexception3.printStackTrace();
      }
   }

   public String telemetryName() {
      return this.telemetryName;
   }
}
