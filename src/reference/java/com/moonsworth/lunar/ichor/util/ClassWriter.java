package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.URLClassLoader;
import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;

public class ClassWriter extends org.objectweb.asm.ClassWriter {
   private final ClassLoader classLoader;
   private static final String field1 = "java/lang/Object";

   public ClassWriter(ClassLoader var1, int var2) {
      super(var2);
      this.classLoader = var1;
   }

   protected ClassLoader getClassLoader() {
      return this.classLoader;
   }

   protected String getCommonSuperClass(String var1, String var2) {
      if (var1.equals(var2)) {
         return var1;
      }

      if (this.classLoader instanceof URLClassLoader var3) {
         org.cadixdev.bombe.analysis.InheritanceProvider var11 = var3.method17();

         Optional var5;
         Optional var6;
         try {
            var5 = var11.provide(var1);
            var6 = var11.provide(var2);
         } catch (ClassCircularityError var10) {
            System.err.println("CIRCLE?!?!?! " + var1 + " ^ " + var2);
            throw var10;
         }

         if (var5.isPresent() && var6.isPresent()) {
            ClassInfo var7 = (ClassInfo)var5.get();
            ClassInfo var8 = (ClassInfo)var6.get();
            if (var8.hasParent(var7, var11)) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + var1 + " ^ " + var2 + " => " + var7.getName());
               return var7.getName();
            }

            if (var7.hasParent(var8, var11)) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + var1 + " ^ " + var2 + " => " + var8.getName());
               return var8.getName();
            }

            if (var7.isInterface() || var8.isInterface()) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + var1 + " ^ " + var2 + " => obj, they're interfaces");
               return "java/lang/Object";
            }

            String var9 = var7.getName();

            do {
               var9 = var11.provide(var9).<String>map(ClassInfo::getSuperName).filter(var0 -> !var0.isEmpty()).orElse(null);
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + var9);
            } while (var9 != null && !var8.hasParent(var9, var11));

            if (var9 != null) {
               return var9;
            }
         }
      }

      return super.getCommonSuperClass(var1, var2);
   }
}
