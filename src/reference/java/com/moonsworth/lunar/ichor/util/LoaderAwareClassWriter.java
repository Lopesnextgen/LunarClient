package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.URLClassLoader;
import java.util.Optional;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;

public class LoaderAwareClassWriter extends org.objectweb.asm.ClassWriter {
   private final ClassLoader classLoader;
   private static final String field1 = "java/lang/Object";

   public LoaderAwareClassWriter(ClassLoader type, int value) {
      super(value);
      this.classLoader = type;
   }

   protected ClassLoader getClassLoader() {
      return this.classLoader;
   }

   protected String getCommonSuperClass(String text, String text2) {
      if (text.equals(text2)) {
         return text;
      }

      if (this.classLoader instanceof URLClassLoader urlclassloader3) {
         org.cadixdev.bombe.analysis.InheritanceProvider inheritanceprovider11 = urlclassloader3.method17();

         Optional optional5;
         Optional optional6;
         try {
            optional5 = inheritanceprovider11.provide(text);
            optional6 = inheritanceprovider11.provide(text2);
         } catch (ClassCircularityError classcircularityerror10) {
            System.err.println("CIRCLE?!?!?! " + text + " ^ " + text2);
            throw classcircularityerror10;
         }

         if (optional5.isPresent() && optional6.isPresent()) {
            ClassInfo classinfo7 = (ClassInfo)optional5.get();
            ClassInfo classinfo8 = (ClassInfo)optional6.get();
            if (classinfo8.hasParent(classinfo7, inheritanceprovider11)) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + text + " ^ " + text2 + " => " + classinfo7.getName());
               return classinfo7.getName();
            }

            if (classinfo7.hasParent(classinfo8, inheritanceprovider11)) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + text + " ^ " + text2 + " => " + classinfo8.getName());
               return classinfo8.getName();
            }

            if (classinfo7.isInterface() || classinfo8.isInterface()) {
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + text + " ^ " + text2 + " => obj, they're interfaces");
               return "java/lang/Object";
            }

            String text9 = classinfo7.getName();

            do {
               text9 = inheritanceprovider11.provide(text9).<String>map(ClassInfo::getSuperName).filter(arg0 -> !arg0.isEmpty()).orElse(null);
               System.out.println("LoaderAwareClassWriter.getCommonSuperClass: " + text9);
            } while (text9 != null && !classinfo8.hasParent(text9, inheritanceprovider11));

            if (text9 != null) {
               return text9;
            }
         }
      }

      return super.getCommonSuperClass(text, text2);
   }
}
