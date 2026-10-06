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

public final class MappingNormalizer {
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

   public MappingNormalizer() {
   }

   public static MappingSet method1(MappingSet mappingset0) {
      MappingSet mappingset1 = MappingSet.create();

      for (TopLevelClassMapping toplevelclassmapping3 : mappingset0.getTopLevelClassMappings()) {
         method2(mappingset1, toplevelclassmapping3);
      }

      return mappingset1;
   }

   private static void method2(MappingSet mappingset0, ClassMapping<?, ?> classmapping1) {
      ClassMapping classmapping2 = (ClassMapping)mappingset0.getOrCreateClassMapping(classmapping1.getFullObfuscatedName()).setDeobfuscatedName(classmapping1.getFullDeobfuscatedName());

      for (FieldMapping fieldmapping4 : classmapping1.getFieldMappings()) {
         classmapping2.createFieldMapping(new FieldSignature(fieldmapping4.getObfuscatedName())).setDeobfuscatedName(fieldmapping4.getDeobfuscatedName());
      }

      for (MethodMapping methodmapping7 : classmapping1.getMethodMappings()) {
         classmapping2.createMethodMapping(methodmapping7.getSignature(), methodmapping7.getDeobfuscatedName());
      }

      for (InnerClassMapping innerclassmapping8 : classmapping1.getInnerClassMappings()) {
         method2(mappingset0, innerclassmapping8);
      }
   }

   public static MappingSet method3(MappingSet mappingset0, ClassLoader classloader1) {
      try {
         MappingSet mappingset2 = MappingSet.create();
         InheritanceProvider inheritanceprovider3 = method8(classloader1);

         for (TopLevelClassMapping toplevelclassmapping5 : mappingset0.getTopLevelClassMappings()) {
            method4(mappingset0, mappingset2, inheritanceprovider3, toplevelclassmapping5);
            Collection list6 = toplevelclassmapping5.getInnerClassMappings();

            while (list6 != null && list6.size() > 0) {
               ArrayList list7 = new ArrayList();

               for (InnerClassMapping innerclassmapping9 : list6) {
                  method4(mappingset0, mappingset2, inheritanceprovider3, innerclassmapping9);
                  list7.addAll(innerclassmapping9.getInnerClassMappings());
               }

               list6 = list7;
            }
         }

         return mappingset2;
      } catch (Throwable exception10) {
         throw exception10;
      }
   }

   private static void method4(MappingSet mappingset0, MappingSet mappingset1, org.cadixdev.bombe.analysis.InheritanceProvider inheritanceprovider2, ClassMapping<?, ?> classmapping3) {
      try {
         ClassMapping classmapping4 = (ClassMapping)mappingset1.getOrCreateClassMapping(classmapping3.getFullObfuscatedName()).setDeobfuscatedName(classmapping3.getDeobfuscatedName());
         ArrayList list5 = new ArrayList();
         ArrayList list6 = new ArrayList();
         ArrayList list7 = new ArrayList();
         List list8 = List.of(classmapping3.getFullObfuscatedName());

         while (!list8.isEmpty()) {
            ArrayList list9 = new ArrayList();

            for (String text11 : list8) {
               try {
                  inheritanceprovider2.provide(text11).ifPresent(arg7x -> {
                     for (ClassInfo classinfo9x : arg7x.provideParents(inheritanceprovider2)) {
                        if (!list7.contains(classinfo9x.getName())) {
                           list7.add(classinfo9x.getName());
                           list9.add(classinfo9x.getName());
                           mappingset0.getClassMapping(classinfo9x.getName()).ifPresent(arg3xx -> {
                              for (FieldMapping fieldmapping5xx : classmapping3.getFieldMappings()) {
                                 Optional optional6xx = arg3xx.computeFieldMapping(fieldmapping5xx.getSignature());
                                 optional6xx.ifPresent(arg1xxx -> list5.add(arg1xxx.getSignature()));
                              }

                              for (MethodMapping methodmapping8x : classmapping3.getMethodMappings()) {
                                 if (!methodmapping8x.getDeobfuscatedName().startsWith("<")) {
                                    Optional optional9xx = arg3xx.getMethodMapping(methodmapping8x.getSignature());
                                    optional9xx.ifPresent(arg1xxx -> list6.add(arg1xxx.getSignature()));
                                 }
                              }
                           });
                        }
                     }
                  });
               } catch (Throwable exception13) {
                  System.out.println("Skipping class " + classmapping3.getFullDeobfuscatedName());
               }

               list8 = list9;
            }
         }

         for (FieldMapping fieldmapping17 : classmapping3.getFieldMappings()) {
            if (!list5.contains(fieldmapping17.getSignature())) {
               classmapping4.createFieldMapping(fieldmapping17.getSignature(), fieldmapping17.getDeobfuscatedName());
            }
         }

         for (MethodMapping methodmapping18 : classmapping3.getMethodMappings()) {
            if (!list6.contains(methodmapping18.getSignature())) {
               classmapping4.createMethodMapping(methodmapping18.getSignature(), methodmapping18.getDeobfuscatedName());
            }
         }
      } catch (Throwable exception14) {
         throw exception14;
      }
   }

   public static ValuePair<MappingSet, MappingSet> method5(MappingSet mappingset0, String text1) {
      MappingSet mappingset2 = mappingset0.copy();
      MappingSet mappingset3 = MappingSet.create();

      for (TopLevelClassMapping toplevelclassmapping5 : mappingset2.getTopLevelClassMappings()) {
         String text6 = toplevelclassmapping5.getFullDeobfuscatedName();
         if (!text6.contains(text1)) {
            int index7 = -1;

            for (String text9 : List.of("net/minecraft/", "com/mojang/")) {
               if (text6.startsWith(text9)) {
                  index7 = text9.length();
                  break;
               }
            }

            if (index7 != -1) {
               String text10 = text6.substring(0, index7) + text1 + "/" + text6.substring(index7);
               toplevelclassmapping5.setDeobfuscatedName(text10);
               mappingset3.getOrCreateClassMapping(text6).setDeobfuscatedName(text10);
            }
         }
      }

      return ValuePair.method1(mappingset2, mappingset3);
   }

   public static MappingSet method6(MappingSet mappingset0, File file1, List<Path> list2, boolean flag3, boolean flag4) {
      try {
         URLClassLoader urlclassloader5 = method7(file1, list2);
         InheritanceProvider inheritanceprovider6 = method8(urlclassloader5);
         MappingSet mappingset7 = method9(mappingset0, urlclassloader5);
         if (flag3) {
            for (TopLevelClassMapping toplevelclassmapping9 : mappingset7.getTopLevelClassMappings()) {
               toplevelclassmapping9.complete(inheritanceprovider6);
            }
         }

         if (flag4) {
            mappingset7 = method3(mappingset7, urlclassloader5);
         }

         return mappingset7;
      } catch (Throwable exception10) {
         throw exception10;
      }
   }

   private static URLClassLoader method7(File file0, List<Path> list1) {
      ArrayList list2 = new ArrayList();
      if (!file0.exists()) {
         throw new IllegalStateException("Failed to find jar " + file0 + " to normalize mappings with.");
      }

      list2.add(file0);
      list1.stream().map(Path::toFile).forEach(list2::add);
      URL[] items3 = list2.stream().map(arg0x -> {
         try {
            return arg0x.toURI().toURL();
         } catch (MalformedURLException malformedurlexception2x) {
            throw new RuntimeException("Failed to convert file to URL", malformedurlexception2x);
         }
      }).toArray(URL[]::new);
      return new URLClassLoader(items3);
   }

   private static InheritanceProvider method8(ClassLoader classloader0) {
      return new InheritanceProvider(589824, ClassProvider.of(classloader0));
   }

   public static MappingSet method9(MappingSet mappingset0, URLClassLoader urlclassloader1) {
      try {
         MappingSet mappingset2 = MappingSet.create();
         int number3 = 0;

         for (ClassMapping classmapping5 : mappingset0.getTopLevelClassMappings()) {
            if (!field1.contains(classmapping5.getObfuscatedName())) {
               number3 += method10(mappingset2, urlclassloader1, classmapping5);
            }
         }

         if (number3 > 200) {
            throw new IllegalStateException("Missing " + number3 + " fields when attempting to normalize mapping set");
         }

         if (number3 > 0) {
            System.out.println("[MappingUtil.normalize] missingFields = " + number3);
         }

         return mappingset2;
      } catch (Throwable exception6) {
         throw exception6;
      }
   }

   private static int method10(MappingSet mappingset0, URLClassLoader urlclassloader1, ClassMapping<?, ?> classmapping2) {
      ClassMapping classmapping3 = mappingset0.getOrCreateClassMapping(classmapping2.getFullObfuscatedName());
      if (classmapping2.getObfuscatedName().matches("[0-9]+")) {
         classmapping3.setDeobfuscatedName(classmapping2.getObfuscatedName());
      } else {
         classmapping3.setDeobfuscatedName(classmapping2.getDeobfuscatedName());
      }

      int number4 = 0;
      String text5 = classmapping2.getFullObfuscatedName().replace('/', '.');

      try {
         Class clazz6 = urlclassloader1.loadClass(text5);

         for (FieldMapping fieldmapping8 : classmapping2.getFieldMappings()) {
            try {
               String text9 = fieldmapping8.getObfuscatedName();
               Field field19 = clazz6.getDeclaredField(text9);
               FieldSignature fieldsignature11 = new FieldSignature(text9, FieldType.of(field19.getType()));
               classmapping3.createFieldMapping(fieldsignature11, fieldmapping8.getDeobfuscatedName());
            } catch (NoSuchFieldException nosuchfieldexception12) {
               String text10 = nosuchfieldexception12.getMessage();
            }
         }

         for (MethodMapping methodmapping17 : classmapping2.getMethodMappings()) {
            MethodMapping methodmapping18 = classmapping3.createMethodMapping(methodmapping17.getSignature(), methodmapping17.getDeobfuscatedName());

            for (MethodParameterMapping methodparametermapping21 : methodmapping17.getParameterMappings()) {
               methodmapping18.createParameterMapping(methodparametermapping21.getIndex(), methodparametermapping21.getDeobfuscatedName());
            }
         }
      } catch (ClassNotFoundException | ClassFormatError classnotfoundexception13) {
         if (!classnotfoundexception13.getMessage().contains("illegal modifiers") && !classnotfoundexception13.getMessage().equals(text5)) {
            throw new IllegalStateException("Failed to find class when filling in field types for " + text5 + ". You're probably missing a dependency.", classnotfoundexception13);
         }
      }

      for (InnerClassMapping innerclassmapping16 : classmapping2.getInnerClassMappings()) {
         number4 += method10(mappingset0, urlclassloader1, innerclassmapping16);
      }

      return number4;
   }
}
