package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.IoUtils;
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

public final class MappingSetUtils {
   private static final Map<MappingSet, MappingSet> field1 = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<MappingSet, Map<String, String>> field2 = Collections.synchronizedMap(new WeakHashMap<>());
   public static boolean field3 = false;

   public static MappingSet method1(MappingSet mappingset0) {
      try {
         if (mappingset0 == null) {
            return null;
         }

         MappingSet mappingset1 = field1.computeIfAbsent(mappingset0, MappingSet::reverse);
         field1.put(mappingset1, mappingset0);
         return mappingset1;
      } catch (Throwable exception2) {
         throw exception2;
      }
   }

   public static String method2(MappingSet mappingset0, String text1) {
      return method3(mappingset0, text1, text1);
   }

   @Nullable
   public static String method3(MappingSet mappingset0, String text1, String text2) {
      if (mappingset0 == null) {
         throw new IllegalStateException("Mappings are null");
      }

      Map map3 = field2.computeIfAbsent(mappingset0, arg0x -> {
         HashMap map1x = new HashMap();
         ArrayList list2x = new ArrayList(arg0x.getTopLevelClassMappings());

         while (!list2x.isEmpty()) {
            ArrayList list3x = list2x;
            list2x = new ArrayList();

            for (ClassMapping classmapping5x : list3x) {
               String text6x = classmapping5x.getFullObfuscatedName().intern();
               String text7x = classmapping5x.getFullDeobfuscatedName().intern();
               if (!text6x.equals(text7x)) {
                  map1x.put(text7x, text6x);
               }

               list2x.addAll(classmapping5x.getInnerClassMappings());
            }
         }

         return map1x;
      });
      if (field3) {
         String text4 = (String)map3.getOrDefault(text1, null);
         if (text4 == null) {
            int index5 = text1.lastIndexOf(36);
            if (index5 != -1) {
               String text6 = text1.substring(0, index5);
               String text7 = text1.substring(index5 + 1);
               String text8 = method3(mappingset0, text6, text6);
               if (text8 != null) {
                  text4 = text8 + "$" + text7;
               }
            }
         }

         if (text4 == null) {
            text4 = text2;
         }

         return text4;
      } else {
         return map3.getOrDefault(text1, text2);
      }
   }

   public static FieldType method4(MappingSet mappingset0, FieldType fieldtype1) {
      if (fieldtype1 instanceof ArrayType arraytype2) {
         FieldType fieldtype4 = method4(mappingset0, arraytype2.getComponent());
         return fieldtype4 == arraytype2.getComponent() ? arraytype2 : new ArrayType(arraytype2.getDimCount(), fieldtype4);
      } else {
         return (FieldType)(fieldtype1 instanceof ObjectType objecttype3 ? new ObjectType(method2(mappingset0, objecttype3.getClassName())) : fieldtype1);
      }
   }

   public static MethodDescriptor method5(MappingSet mappingset0, MethodDescriptor methoddescriptor1) {
      Object obj2 = methoddescriptor1.getReturnType() instanceof FieldType fieldtype3 ? method4(mappingset0, fieldtype3) : methoddescriptor1.getReturnType();
      return new MethodDescriptor(methoddescriptor1.getParamTypes().stream().map(arg1x -> method4(mappingset0, arg1x)).collect(Collectors.toList()), (Type)obj2);
   }

   public static void method6(String text0, String text1, MappingSet mappingset2, MappingSet mappingset3, Function<String, Collection<String>> function4) {
      mappingset2.getClassMapping(text0)
         .map(arg0x -> (Object)arg0x)
         .or(() -> mappingset2.getClassMapping(method2(mappingset2, text0)))
         .map(arg0x -> (ClassMapping)arg0x)
         .ifPresent(arg2x -> {
            ClassMapping classmapping3x = mappingset3.getOrCreateClassMapping(text1);

            for (FieldMapping fieldmapping5x : arg2x.getFieldMappings()) {
               classmapping3x.getOrCreateFieldMapping(fieldmapping5x.getObfuscatedName()).setDeobfuscatedName(fieldmapping5x.getDeobfuscatedName());
            }

            for (MethodMapping methodmapping7 : arg2x.getMethodMappings()) {
               classmapping3x.getOrCreateMethodMapping(methodmapping7.getSignature()).setDeobfuscatedName(methodmapping7.getDeobfuscatedName());
            }
         });
      Collection list5 = (Collection)function4.apply(text0);
      if (list5 != null) {
         list5.forEach(arg4x -> method6(arg4x, text1, mappingset2, mappingset3, function4));
      }
   }

   public static void method7(MappingSet mappingset0, MappingSet mappingset1) {
      for (TopLevelClassMapping toplevelclassmapping3 : mappingset0.getTopLevelClassMappings()) {
         method8(mappingset0, mappingset1, toplevelclassmapping3);
      }
   }

   public static void method8(MappingSet mappingset0, MappingSet mappingset1, ClassMapping<?, ?> classmapping2) {
      ClassMapping classmapping3 = (ClassMapping)mappingset1.getOrCreateClassMapping(classmapping2.getDeobfuscatedName()).setDeobfuscatedName(classmapping2.getDeobfuscatedName());
      method9(mappingset0, mappingset1, classmapping2, classmapping3);
   }

   public static void method9(MappingSet mappingset0, MappingSet mappingset1, ClassMapping<?, ?> classmapping2, ClassMapping<?, ?> classmapping3) {
      for (FieldMapping fieldmapping5 : classmapping2.getFieldMappings()) {
         classmapping3.getOrCreateFieldMapping(fieldmapping5.getObfuscatedName()).setDeobfuscatedName(fieldmapping5.getDeobfuscatedName());
      }

      for (MethodMapping methodmapping9 : classmapping2.getMethodMappings()) {
         classmapping3.getOrCreateMethodMapping(methodmapping9.getObfuscatedName(), methodmapping9.getDeobfuscatedDescriptor()).setDeobfuscatedName(methodmapping9.getDeobfuscatedName());
      }

      for (InnerClassMapping innerclassmapping10 : classmapping2.getInnerClassMappings()) {
         InnerClassMapping innerclassmapping6 = classmapping3.getOrCreateInnerClassMapping(innerclassmapping10.getDeobfuscatedName());
         method9(mappingset0, mappingset1, innerclassmapping10, innerclassmapping6);
      }
   }

   public static void method10(Path path0, Path path1, boolean flag2, BiFunction<String, byte[], byte[]> function3, Function<String, String> function4) {
      JarFile jarfile5 = new JarFile(path0.toFile());
      JarOutputStream jaroutputstream6 = new JarOutputStream(new FileOutputStream(path1.toFile()));
      Enumeration enumeration7 = jarfile5.entries();

      while (enumeration7.hasMoreElements()) {
         JarEntry jarentry8 = (JarEntry)enumeration7.nextElement();
         String text9 = jarentry8.getName();
         InputStream input10 = jarfile5.getInputStream(jarentry8);
         byte[] items11 = IoUtils.toByteArray(input10);
         input10.close();
         boolean flag12 = false;
         if (text9.endsWith(".class")) {
            flag12 = true;
            String text13 = text9.substring(0, text9.length() - ".class".length());
            String text14 = text13.replace('/', '.');
            items11 = (byte[])function3.apply(text14, items11);
            text9 = (String)function4.apply(text13) + ".class";
         }

         if (flag2 || flag12) {
            jaroutputstream6.putNextEntry(new ZipEntry(text9));
            jaroutputstream6.write(items11);
            jaroutputstream6.closeEntry();
         }
      }

      jarfile5.close();
      jaroutputstream6.close();
   }

   public static URL method11(Path path0) {
      try {
         return path0.toUri().toURL();
      } catch (Throwable exception2) {
         throw exception2;
      }
   }

   @Generated
   private MappingSetUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
