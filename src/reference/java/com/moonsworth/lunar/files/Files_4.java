package com.moonsworth.lunar.files;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.InnerClassMapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.cadixdev.lorenz.model.MethodParameterMapping;
import org.cadixdev.lorenz.model.TopLevelClassMapping;

public final class Files_4 {
   private static final List<String> field1 = List.of(
      "./",
      "//",
      "net/",
      "net/minecraft/",
      "net/minecraft/src",
      "net/minecraft/client/",
      "net/minecraft/client/main/",
      "net/minecraft/realms/",
      "net/minecraft/server/"
   );

   public static MappingSet method1(MappingSet var0) {
      MappingSet var1 = MappingSet.create();

      for (TopLevelClassMapping var3 : var0.getTopLevelClassMappings()) {
         method2(var1, var3);
      }

      return var1;
   }

   private static void method2(MappingSet var0, ClassMapping<?, ?> var1) {
      ClassMapping var2 = (ClassMapping)var0.getOrCreateClassMapping(var1.getFullObfuscatedName()).setDeobfuscatedName(var1.getFullDeobfuscatedName());

      for (FieldMapping var4 : var1.getFieldMappings()) {
         var2.createFieldMapping(new FieldSignature(var4.getObfuscatedName())).setDeobfuscatedName(var4.getDeobfuscatedName());
      }

      for (MethodMapping var7 : var1.getMethodMappings()) {
         var2.createMethodMapping(var7.getSignature(), var7.getDeobfuscatedName());
      }

      for (InnerClassMapping var8 : var1.getInnerClassMappings()) {
         method2(var0, var8);
      }
   }

   public static MappingSet method3(MappingSet var0, ClassLoader var1) {
      try {
         MappingSet var2 = MappingSet.create();
         InheritanceProvider var3 = method8(var1);

         for (TopLevelClassMapping var5 : var0.getTopLevelClassMappings()) {
            method4(var0, var2, var3, var5);
            Collection var6 = var5.getInnerClassMappings();

            while (var6 != null && var6.size() > 0) {
               ArrayList var7 = new ArrayList();

               for (InnerClassMapping var9 : var6) {
                  method4(var0, var2, var3, var9);
                  var7.addAll(var9.getInnerClassMappings());
               }

               var6 = var7;
            }
         }

         return var2;
      } catch (Throwable var10) {
         throw var10;
      }
   }

   private static void method4(MappingSet var0, MappingSet var1, org.cadixdev.bombe.analysis.InheritanceProvider var2, ClassMapping<?, ?> var3) {
      try {
         ClassMapping var4 = (ClassMapping)var1.getOrCreateClassMapping(var3.getFullObfuscatedName()).setDeobfuscatedName(var3.getDeobfuscatedName());
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         ArrayList var7 = new ArrayList();
         List var8 = List.of(var3.getFullObfuscatedName());

         while (!var8.isEmpty()) {
            ArrayList var9 = new ArrayList();

            for (String var11 : var8) {
               try {
                  var2.provide(var11).ifPresent(var7x -> {
                     for (ClassInfo var9x : var7x.provideParents(var2)) {
                        if (!var7.contains(var9x.getName())) {
                           var7.add(var9x.getName());
                           var9.add(var9x.getName());
                           var0.getClassMapping(var9x.getName()).ifPresent(var3xx -> {
                              for (FieldMapping var5xx : var3.getFieldMappings()) {
                                 Optional var6xx = var3xx.computeFieldMapping(var5xx.getSignature());
                                 var6xx.ifPresent(var1xxx -> var5.add(var1xxx.getSignature()));
                              }

                              for (MethodMapping var8x : var3.getMethodMappings()) {
                                 if (!var8x.getDeobfuscatedName().startsWith("<")) {
                                    Optional var9xx = var3xx.getMethodMapping(var8x.getSignature());
                                    var9xx.ifPresent(var1xxx -> var6.add(var1xxx.getSignature()));
                                 }
                              }
                           });
                        }
                     }
                  });
               } catch (Throwable var13) {
                  System.out.println("Skipping class " + var3.getFullDeobfuscatedName());
               }

               var8 = var9;
            }
         }

         for (FieldMapping var17 : var3.getFieldMappings()) {
            if (!var5.contains(var17.getSignature())) {
               var4.createFieldMapping(var17.getSignature(), var17.getDeobfuscatedName());
            }
         }

         for (MethodMapping var18 : var3.getMethodMappings()) {
            if (!var6.contains(var18.getSignature())) {
               var4.createMethodMapping(var18.getSignature(), var18.getDeobfuscatedName());
            }
         }
      } catch (Throwable var14) {
         throw var14;
      }
   }

   public static Files6_2<MappingSet, MappingSet> method5(MappingSet var0, String var1) {
      MappingSet var2 = var0.copy();
      MappingSet var3 = MappingSet.create();

      for (TopLevelClassMapping var5 : var2.getTopLevelClassMappings()) {
         String var6 = var5.getFullDeobfuscatedName();
         if (!var6.contains(var1)) {
            int var7 = -1;

            for (String var9 : List.of("net/minecraft/", "com/mojang/")) {
               if (var6.startsWith(var9)) {
                  var7 = var9.length();
                  break;
               }
            }

            if (var7 != -1) {
               String var10 = var6.substring(0, var7) + var1 + "/" + var6.substring(var7);
               var5.setDeobfuscatedName(var10);
               var3.getOrCreateClassMapping(var6).setDeobfuscatedName(var10);
            }
         }
      }

      return Files6_2.method1(var2, var3);
   }

   public static MappingSet method6(MappingSet var0, File var1, List<Path> var2, boolean var3, boolean var4) {
      try {
         URLClassLoader var5 = method7(var1, var2);
         InheritanceProvider var6 = method8(var5);
         MappingSet var7 = method9(var0, var5);
         if (var3) {
            for (TopLevelClassMapping var9 : var7.getTopLevelClassMappings()) {
               var9.complete(var6);
            }
         }

         if (var4) {
            var7 = method3(var7, var5);
         }

         return var7;
      } catch (Throwable var10) {
         throw var10;
      }
   }

   private static URLClassLoader method7(File var0, List<Path> var1) {
      ArrayList var2 = new ArrayList();
      if (!var0.exists()) {
         throw new IllegalStateException("Failed to find jar " + var0 + " to normalize mappings with.");
      }

      var2.add(var0);
      var1.stream().map(Path::toFile).forEach(var2::add);
      URL[] var3 = var2.stream().map(var0x -> {
         try {
            return var0x.toURI().toURL();
         } catch (MalformedURLException var2x) {
            throw new RuntimeException("Failed to convert file to URL", var2x);
         }
      }).toArray(URL[]::new);
      return new URLClassLoader(var3);
   }

   private static InheritanceProvider method8(ClassLoader var0) {
      return new InheritanceProvider(589824, ClassProvider.of(var0));
   }

   public static MappingSet method9(MappingSet var0, URLClassLoader var1) {
      try {
         MappingSet var2 = MappingSet.create();
         int var3 = 0;

         for (ClassMapping var5 : var0.getTopLevelClassMappings()) {
            if (!field1.contains(var5.getObfuscatedName())) {
               var3 += method10(var2, var1, var5);
            }
         }

         if (var3 > 200) {
            throw new IllegalStateException("Missing " + var3 + " fields when attempting to normalize mapping set");
         }

         if (var3 > 0) {
            System.out.println("[MappingUtil.normalize] missingFields = " + var3);
         }

         return var2;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   private static int method10(MappingSet var0, URLClassLoader var1, ClassMapping<?, ?> var2) {
      ClassMapping var3 = var0.getOrCreateClassMapping(var2.getFullObfuscatedName());
      if (var2.getObfuscatedName().matches("[0-9]+")) {
         var3.setDeobfuscatedName(var2.getObfuscatedName());
      } else {
         var3.setDeobfuscatedName(var2.getDeobfuscatedName());
      }

      int var4 = 0;
      String var5 = var2.getFullObfuscatedName().replace('/', '.');

      try {
         Class var6 = var1.loadClass(var5);

         for (FieldMapping var8 : var2.getFieldMappings()) {
            try {
               String var9 = var8.getObfuscatedName();
               Field var19 = var6.getDeclaredField(var9);
               FieldSignature var11 = new FieldSignature(var9, FieldType.of(var19.getType()));
               var3.createFieldMapping(var11, var8.getDeobfuscatedName());
            } catch (NoSuchFieldException var12) {
               String var10 = var12.getMessage();
            }
         }

         for (MethodMapping var17 : var2.getMethodMappings()) {
            MethodMapping var18 = var3.createMethodMapping(var17.getSignature(), var17.getDeobfuscatedName());

            for (MethodParameterMapping var21 : var17.getParameterMappings()) {
               var18.createParameterMapping(var21.getIndex(), var21.getDeobfuscatedName());
            }
         }
      } catch (ClassNotFoundException | ClassFormatError var13) {
         if (!var13.getMessage().contains("illegal modifiers") && !var13.getMessage().equals(var5)) {
            throw new IllegalStateException("Failed to find class when filling in field types for " + var5 + ". You're probably missing a dependency.", var13);
         }
      }

      for (InnerClassMapping var16 : var2.getInnerClassMappings()) {
         var4 += method10(var0, var1, var16);
      }

      return var4;
   }
}
