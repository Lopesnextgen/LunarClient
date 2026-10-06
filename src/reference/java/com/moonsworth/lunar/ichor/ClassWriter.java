package com.moonsworth.lunar.ichor;

public final class ClassWriter extends org.objectweb.asm.ClassWriter {
   private final IchorTransformer field1;
   private final URLClassLoader field2;
   private final MixinLegacy2 field3;

   public ClassWriter(int var1, IchorTransformer var2, URLClassLoader var3) {
      super(var1);
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = new MixinLegacy2Iterator(var2, var3);
   }

   protected String getCommonSuperClass(String var1, String var2) {
      MixinLegacy var3 = this.field3.provide(var1);
      MixinLegacy var4 = this.field3.provide(var2);
      if (var3 == null || var4 == null) {
         return "java/lang/Object";
      }

      if (var3.method1(var4)) {
         return var3.name();
      }

      if (var4.method1(var3)) {
         return var4.name();
      }

      if (var3.method3() != null && var4.method3() != null) {
         do {
            var3 = var3.method3();
         } while (!var3.method1(var4));

         return var3.name();
      } else {
         return "java/lang/Object";
      }
   }
}
