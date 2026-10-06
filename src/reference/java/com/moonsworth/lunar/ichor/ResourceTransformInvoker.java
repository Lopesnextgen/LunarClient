package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;

public final class ResourceTransformInvoker extends MixinHelper<TransformResource> {
   public ResourceTransformInvoker(TransformResource annotation101, AutoCloseableIterator autocloseableiterator2, Method method3_) {
      super(annotation101, autocloseableiterator2, method3_);
   }

   public boolean method1(ClassTransformContext autocloseableiterator2$data31) {
      throw new IllegalStateException("Tried to handle resource as class, shouldn't happen.");
   }

   public void method2(ResourcePayload mixinshared1, URLClassLoader urlclassloader2) {
      this.method3(((TransformResource)this.field1).value(), mixinshared1.getResourcePath(), mixinshared1);
   }

   private void method3(String text1, String text2, ResourcePayload mixinshared3) {
      if (mixinshared3 != null && this.field3 != null && AutoCloseableIterator.method5(text1).matcher(text2).matches()) {
         try {
            this.field3.invoke(this.field2.method6(), mixinshared3);
         } catch (Exception exception5) {
            exception5.printStackTrace();
         }
      }
   }
}
