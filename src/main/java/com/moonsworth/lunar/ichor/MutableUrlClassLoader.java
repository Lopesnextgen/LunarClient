package com.moonsworth.lunar.ichor;

import java.net.URL;
import java.net.URLClassLoader;

class MutableUrlClassLoader extends URLClassLoader {
   private MutableUrlClassLoader(URL[] items1, ClassLoader type) {
      super(items1, type);
   }

   @Override
   public void addURL(URL url1) {
      super.addURL(url1);
   }
}
