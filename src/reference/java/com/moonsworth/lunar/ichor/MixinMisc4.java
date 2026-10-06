package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.bombe.type.ArrayType;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.ObjectType;
import org.cadixdev.bombe.type.Type;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.InnerClassMapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.cadixdev.lorenz.model.TopLevelClassMapping;

public final class MixinMisc4 {
   private static final Map<MappingSet, MappingSet> field1 = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<MappingSet, Map<String, String>> field2 = Collections.synchronizedMap(new WeakHashMap<>());
   public static boolean field3 = false;

   public static MappingSet method1(MappingSet var0) {
      try {
         if (var0 == null) {
            return null;
         }

         MappingSet var1 = field1.computeIfAbsent(var0, MappingSet::reverse);
         field1.put(var1, var0);
         return var1;
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public static String method2(MappingSet var0, String var1) {
      return method3(var0, var1, var1);
   }

   @Nullable
   public static String method3(MappingSet var0, String var1, String var2) {
      if (var0 == null) {
         throw new IllegalStateException("Mappings are null");
      }

      Map var3 = field2.computeIfAbsent(var0, var0x -> {
         HashMap var1x = new HashMap();
         ArrayList var2x = new ArrayList(var0x.getTopLevelClassMappings());

         while (!var2x.isEmpty()) {
            ArrayList var3x = var2x;
            var2x = new ArrayList();

            for (ClassMapping var5x : var3x) {
               String var6x = var5x.getFullObfuscatedName().intern();
               String var7x = var5x.getFullDeobfuscatedName().intern();
               if (!var6x.equals(var7x)) {
                  var1x.put(var7x, var6x);
               }

               var2x.addAll(var5x.getInnerClassMappings());
            }
         }

         return var1x;
      });
      if (field3) {
         String var4 = (String)var3.getOrDefault(var1, null);
         if (var4 == null) {
            int var5 = var1.lastIndexOf(36);
            if (var5 != -1) {
               String var6 = var1.substring(0, var5);
               String var7 = var1.substring(var5 + 1);
               String var8 = method3(var0, var6, var6);
               if (var8 != null) {
                  var4 = var8 + "$" + var7;
               }
            }
         }

         if (var4 == null) {
            var4 = var2;
         }

         return var4;
      } else {
         return var3.getOrDefault(var1, var2);
      }
   }

   public static FieldType method4(MappingSet var0, FieldType var1) {
      if (var1 instanceof ArrayType var2) {
         FieldType var4 = method4(var0, var2.getComponent());
         return var4 == var2.getComponent() ? var2 : new ArrayType(var2.getDimCount(), var4);
      } else {
         return (FieldType)(var1 instanceof ObjectType var3 ? new ObjectType(method2(var0, var3.getClassName())) : var1);
      }
   }

   public static MethodDescriptor method5(MappingSet var0, MethodDescriptor var1) {
      Object var2 = var1.getReturnType() instanceof FieldType var3 ? method4(var0, var3) : var1.getReturnType();
      return new MethodDescriptor(var1.getParamTypes().stream().map(var1x -> method4(var0, var1x)).collect(Collectors.toList()), (Type)var2);
   }

   public static void method6(String var0, String var1, MappingSet var2, MappingSet var3, Function<String, Collection<String>> var4) {
      var2.getClassMapping(var0)
         .map(var0x -> (Object)var0x)
         .or(() -> var2.getClassMapping(method2(var2, var0)))
         .map(var0x -> (ClassMapping)var0x)
         .ifPresent(var2x -> {
            ClassMapping var3x = var3.getOrCreateClassMapping(var1);

            for (FieldMapping var5x : var2x.getFieldMappings()) {
               var3x.getOrCreateFieldMapping(var5x.getObfuscatedName()).setDeobfuscatedName(var5x.getDeobfuscatedName());
            }

            for (MethodMapping var7 : var2x.getMethodMappings()) {
               var3x.getOrCreateMethodMapping(var7.getSignature()).setDeobfuscatedName(var7.getDeobfuscatedName());
            }
         });
      Collection var5 = (Collection)var4.apply(var0);
      if (var5 != null) {
         var5.forEach(var4x -> method6(var4x, var1, var2, var3, var4));
      }
   }

   public static void method7(MappingSet var0, MappingSet var1) {
      for (TopLevelClassMapping var3 : var0.getTopLevelClassMappings()) {
         method8(var0, var1, var3);
      }
   }

   public static void method8(MappingSet var0, MappingSet var1, ClassMapping<?, ?> var2) {
      ClassMapping var3 = (ClassMapping)var1.getOrCreateClassMapping(var2.getDeobfuscatedName()).setDeobfuscatedName(var2.getDeobfuscatedName());
      method9(var0, var1, var2, var3);
   }

   public static void method9(MappingSet var0, MappingSet var1, ClassMapping<?, ?> var2, ClassMapping<?, ?> var3) {
      for (FieldMapping var5 : var2.getFieldMappings()) {
         var3.getOrCreateFieldMapping(var5.getObfuscatedName()).setDeobfuscatedName(var5.getDeobfuscatedName());
      }

      for (MethodMapping var9 : var2.getMethodMappings()) {
         var3.getOrCreateMethodMapping(var9.getObfuscatedName(), var9.getDeobfuscatedDescriptor()).setDeobfuscatedName(var9.getDeobfuscatedName());
      }

      for (InnerClassMapping var10 : var2.getInnerClassMappings()) {
         InnerClassMapping var6 = var3.getOrCreateInnerClassMapping(var10.getDeobfuscatedName());
         method9(var0, var1, var10, var6);
      }
   }

   public static void method10(Path var0, Path var1, boolean var2, BiFunction<String, byte[], byte[]> var3, Function<String, String> var4) {
      JarFile var5 = new JarFile(var0.toFile());
      JarOutputStream var6 = new JarOutputStream(new FileOutputStream(var1.toFile()));
      Enumeration var7 = var5.entries();

      while (var7.hasMoreElements()) {
         JarEntry var8 = (JarEntry)var7.nextElement();
         String var9 = var8.getName();
         InputStream var10 = var5.getInputStream(var8);
         byte[] var11 = FatalIchorError7.toByteArray(var10);
         var10.close();
         boolean var12 = false;
         if (var9.endsWith(".class")) {
            var12 = true;
            String var13 = var9.substring(0, var9.length() - ".class".length());
            String var14 = var13.replace('/', '.');
            var11 = (byte[])var3.apply(var14, var11);
            var9 = (String)var4.apply(var13) + ".class";
         }

         if (var2 || var12) {
            var6.putNextEntry(new ZipEntry(var9));
            var6.write(var11);
            var6.closeEntry();
         }
      }

      var5.close();
      var6.close();
   }

   public static URL method11(Path var0) {
      try {
         return var0.toUri().toURL();
      } catch (Throwable var2) {
         throw var2;
      }
   }

   @Generated
   private MixinMisc4() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
