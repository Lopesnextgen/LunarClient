package com.moonsworth.lunar.genesis;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Splitter;
import com.google.common.reflect.ClassPath;
import com.google.common.collect.Maps;
import com.google.common.base.StandardSystemProperty;

abstract class MixinHelper$Data58 {
   private final Set<File> field1 = Sets.newHashSet();

   public final void method1(ClassLoader var1) {
      MixinHelperIterator3 var2 = method4(var1).method12().method1();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         this.method2((File)var3.getKey(), (ClassLoader)var3.getValue());
      }
   }

   @Annotation4
   final void method2(File var1, ClassLoader var2) {
      if (this.field1.add(var1.getCanonicalFile())) {
         this.scanFrom(var1, var2);
      }
   }

   protected abstract void scanDirectory(ClassLoader var1, File var2);

   protected abstract void scanJarFile(ClassLoader var1, JarFile var2);

   private void scanFrom(File var1, ClassLoader var2) {
      try {
         if (!var1.exists()) {
            return;
         }
      } catch (SecurityException var4) {
         ClassPath.access$100().warning("Cannot access " + var1 + ": " + var4);
         return;
      }

      if (var1.isDirectory()) {
         this.scanDirectory(var2, var1);
      } else {
         this.scanJar(var1, var2);
      }
   }

   private void scanJar(File var1, ClassLoader var2) {
      JarFile var3;
      try {
         var3 = new JarFile(var1);
      } catch (IOException var13) {
         return;
      }

      try {
         MixinHelperIterator3 var4 = method3(var1, var3.getManifest()).method1();

         while (var4.hasNext()) {
            File var5 = (File)var4.next();
            this.method2(var5, var2);
         }

         this.scanJarFile(var2, var3);
      } finally {
         try {
            var3.close();
         } catch (IOException var12) {
         }
      }
   }

   @Annotation4
   static ImmutableSet<File> method3(File var0, @Nullable Manifest var1) {
      if (var1 == null) {
         return ImmutableSet.method3();
      }

      ImmutableSet.Data2 var2 = ImmutableSet.method18();
      String var3 = var1.getMainAttributes().getValue(Name.CLASS_PATH.toString());
      if (var3 != null) {
         for (String var5 : ClassPath.method7().split(var3)) {
            URL var6;
            try {
               var6 = getClassPathEntry(var0, var5);
            } catch (MalformedURLException var8) {
               ClassPath.access$100().warning("Invalid Class-Path entry: " + var5);
               continue;
            }

            if (var6.getProtocol().equals("file")) {
               var2.method2(ClassPath.toFile(var6));
            }
         }
      }

      return var2.method7();
   }

   @Annotation4
   static ImmutableMap<File, ClassLoader> method4(ClassLoader var0) {
      LinkedHashMap var1 = Maps.newLinkedHashMap();
      ClassLoader var2 = var0.getParent();
      if (var2 != null) {
         var1.putAll(method4(var2));
      }

      MixinHelperIterator3 var3 = method5(var0).method1();

      while (var3.hasNext()) {
         URL var4 = (URL)var3.next();
         if (var4.getProtocol().equals("file")) {
            File var5 = ClassPath.toFile(var4);
            if (!var1.containsKey(var5)) {
               var1.put(var5, var0);
            }
         }
      }

      return ImmutableMap.method9(var1);
   }

   private static ImmutableList<URL> method5(ClassLoader var0) {
      if (var0 instanceof java.net.URLClassLoader) {
         return ImmutableList.method17(((java.net.URLClassLoader)var0).getURLs());
      } else {
         return var0.equals(ClassLoader.getSystemClassLoader()) ? method6() : ImmutableList.method3();
      }
   }

   @Annotation4
   static ImmutableList<URL> method6() {
      ImmutableList.Data2 var0 = ImmutableList.method30();

      for (String var2 : Splitter.method3(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
         try {
            try {
               var0.method2(new File(var2).toURI().toURL());
            } catch (SecurityException var4) {
               var0.method2(new URL("file", null, new File(var2).getAbsolutePath()));
            }
         } catch (MalformedURLException var5) {
            ClassPath.access$100().log(Level.WARNING, "malformed classpath entry: " + var2, var5);
         }
      }

      return var0.method6();
   }

   @Annotation4
   static URL getClassPathEntry(File var0, String var1) {
      return new URL(var0.toURI().toURL(), var1);
   }
}
