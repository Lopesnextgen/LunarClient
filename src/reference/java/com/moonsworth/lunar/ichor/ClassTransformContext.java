package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.ClassEntry;
import org.objectweb.asm.tree.ClassNode;

public class ClassTransformContext {
   private final IchorStage field1;
   private final String field2;
   private final ClassEntry field3;
   private final URLClassLoader field4;

   public ClassTransformContext(IchorStage ichor41, String text, ClassEntry fatalichorerror83, URLClassLoader type) {
      this.field1 = ichor41;
      this.field2 = text;
      this.field3 = fatalichorerror83;
      this.field4 = type;
   }

   public byte[] method1(Class<?> clazz1) {
      if (this.field3.method1()) {
         throw new IllegalStateException("Trying to get class bytes at a not-early stage: " + this.field1 + " -> " + clazz1.getSimpleName());
      } else {
         return this.field3.getClassBytes();
      }
   }

   public void method2(byte[] items1) {
      if (this.field3.method1()) {
         throw new IllegalStateException("Trying to set class name at a not-early stage: " + this.field1);
      }

      this.field3.method3(items1, this.field3.getClassName());
   }

   public ClassNode method3(Class<?> clazz1) {
      if (this.field3.method1()) {
         return this.field3.getClassNode();
      } else {
         throw new IllegalStateException("Trying to get ClassNode at an early stage: " + this.field1 + " -> " + clazz1.getSimpleName());
      }
   }

   public IchorStage method4() {
      return this.field1;
   }

   public String className() {
      return this.field2;
   }

   public ClassEntry method5() {
      return this.field3;
   }

   public URLClassLoader method6() {
      return this.field4;
   }
}
