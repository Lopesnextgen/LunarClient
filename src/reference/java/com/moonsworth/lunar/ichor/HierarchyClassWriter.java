package com.moonsworth.lunar.ichor;

public final class HierarchyClassWriter extends org.objectweb.asm.ClassWriter {
   private final IchorTransformer field1;
   private final URLClassLoader field2;
   private final ClassHierarchyFactory field3;

   public HierarchyClassWriter(int value, IchorTransformer ichorTransformer, URLClassLoader type) {
      super(value);
      this.field1 = ichorTransformer;
      this.field2 = type;
      this.field3 = new ClasspathHierarchyResolver(ichorTransformer, type);
   }

   protected String getCommonSuperClass(String text, String text2) {
      ClassHierarchyNode mixinlegacy3 = this.field3.provide(text);
      ClassHierarchyNode mixinlegacy4 = this.field3.provide(text2);
      if (mixinlegacy3 == null || mixinlegacy4 == null) {
         return "java/lang/Object";
      }

      if (mixinlegacy3.method1(mixinlegacy4)) {
         return mixinlegacy3.name();
      }

      if (mixinlegacy4.method1(mixinlegacy3)) {
         return mixinlegacy4.name();
      }

      if (mixinlegacy3.method3() != null && mixinlegacy4.method3() != null) {
         do {
            mixinlegacy3 = mixinlegacy3.method3();
         } while (!mixinlegacy3.method1(mixinlegacy4));

         return mixinlegacy3.name();
      } else {
         return "java/lang/Object";
      }
   }
}
