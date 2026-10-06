package com.moonsworth.lunar.ichor;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public final class ClasspathHierarchyResolver implements ClassHierarchyFactory {
   private final Map<String, ClassHierarchyNode> field1;
   private final URLClassLoader field2;
   private final IchorTransformer field3;

   public ClasspathHierarchyResolver(IchorTransformer autocloseableiterator21, URLClassLoader urlclassloader2) {
      this.field2 = urlclassloader2;
      this.field3 = autocloseableiterator21;
      this.field1 = new ConcurrentHashMap<>();
   }

   public ClassHierarchyNode provide(String text1) {
      if (text1 == null) {
         return null;
      }

      if (text1.equals("java/lang/Object")) {
         return ClassHierarchyNode.field3;
      }

      ClassHierarchyNode mixinlegacy2 = this.field1.get(text1);
      if (mixinlegacy2 != null) {
         return mixinlegacy2;
      }

      if (text1.startsWith("java.") || text1.startsWith("java/")) {
         try {
            Class clazz26 = Class.forName(text1.replace("/", "."), false, this.getClass().getClassLoader());
            ClassHierarchyNode mixinlegacy28 = clazz26.getSuperclass() == null ? null : this.provide(clazz26.getSuperclass().getName().replace(".", "/"));
            List list29 = Arrays.stream(clazz26.getInterfaces()).map(arg1x -> this.provide(arg1x.getName().replace(".", "/"))).toList();
            mixinlegacy2 = new ClassHierarchyNode(text1.replace(".", "/"), mixinlegacy28, list29);
            this.field1.put(text1.replace(".", "/"), mixinlegacy2);
            return mixinlegacy2;
         } catch (ClassNotFoundException classnotfoundexception23) {
            classnotfoundexception23.printStackTrace();
         }
      }

      IchorStage ichor43 = this.field2.method18();
      String text4 = text1.replace('.', '/') + ".class";
      Set set5 = this.field3.method13(ichor43, text1);
      String text6 = this.field3.method15(ichor43, text1);

      try {
         for (String text8 : set5) {
            text4 = text8.replace('.', '/') + ".class";
            InputStream input9 = this.field2.getResourceAsStream(text4);
            if (input9 != null) {
               try {
                  ClassReader classreader10 = new ClassReader(input9);
                  ClassNode node11 = new ClassNode();
                  classreader10.accept(node11, 7);
                  input9.close();
                  String text12 = node11.superName;
                  String text13 = null;
                  if (text12 != null) {
                     text13 = this.field3.method15(ichor43, text12);
                  }

                  ClassHierarchyNode mixinlegacy14 = this.provide(text13);
                  List list15 = node11.interfaces.stream().map(arg2x -> this.field3.method15(ichor43, arg2x)).map(this::provide).filter(Objects::nonNull).toList();
                  mixinlegacy2 = new ClassHierarchyNode(text6, mixinlegacy14, list15);
                  this.field1.put(text6, mixinlegacy2);
                  return mixinlegacy2;
               } finally {
                  input9.close();
               }
            }
         }
      } catch (Exception exception22) {
      }

      return null;
   }
}
