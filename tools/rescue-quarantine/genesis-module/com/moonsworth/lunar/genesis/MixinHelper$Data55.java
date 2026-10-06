package com.moonsworth.lunar.genesis;

import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath$ResourceInfo;

@Annotation4
final class MixinHelper$Data55 extends MixinHelper$Data58 {
   private final MixinHelper132_2<ClassLoader, String> field2 = MixinHelper43.method1().method6().method2();

   ImmutableSet<ClassPath$ResourceInfo> method1() {
      ImmutableSet.Data2 var1 = ImmutableSet.method18();

      for (Entry var3 : this.field2.entries()) {
         var1.method2(ClassPath$ResourceInfo.method1((String)var3.getValue(), (ClassLoader)var3.getKey()));
      }

      return var1.method7();
   }

   @Override
   protected void scanJarFile(ClassLoader var1, JarFile var2) {
      Enumeration var3 = var2.entries();

      while (var3.hasMoreElements()) {
         JarEntry var4 = (JarEntry)var3.nextElement();
         if (!var4.isDirectory() && !var4.getName().equals("META-INF/MANIFEST.MF")) {
            this.field2.get(var1).add(var4.getName());
         }
      }
   }

   @Override
   protected void scanDirectory(ClassLoader var1, File var2) {
      HashSet var3 = new HashSet();
      var3.add(var2.getCanonicalFile());
      this.scanDirectory(var2, var1, "", var3);
   }

   private void scanDirectory(File var1, ClassLoader var2, String var3, Set<File> var4) {
      File[] var5 = var1.listFiles();
      if (var5 == null) {
         ClassPath.access$100().warning("Cannot read directory " + var1);
      } else {
         for (File var9 : var5) {
            String var10 = var9.getName();
            if (var9.isDirectory()) {
               File var11 = var9.getCanonicalFile();
               if (var4.add(var11)) {
                  this.scanDirectory(var11, var2, var3 + var10 + "/", var4);
                  var4.remove(var11);
               }
            } else {
               String var12 = var3 + var10;
               if (!var12.equals("META-INF/MANIFEST.MF")) {
                  this.field2.get(var2).add(var12);
               }
            }
         }
      }
   }
}
