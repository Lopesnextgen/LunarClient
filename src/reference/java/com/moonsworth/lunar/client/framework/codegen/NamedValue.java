package com.moonsworth.lunar.client.framework.codegen;

import lombok.Generated;

public class NamedValue<T> implements SourceEmitter {
   private final String field1;
   private final T field2;

   @Override
   public String method1(int value) {
      return JavaFileWriter.method7(value) + (this.field2 instanceof SourceEmitter mixinhelper22 ? mixinhelper22.method1(value) : this.field2.toString()) + " " + this.field1;
   }

   @Generated
   public String method2() {
      return this.field1;
   }

   @Generated
   public T method3() {
      return this.field2;
   }

   @Generated
   private NamedValue(String text, T t) {
      this.field1 = text;
      this.field2 = (T)t;
   }

   @Generated
   public static <T> NamedValue<T> method4(String text, T t) {
      return new NamedValue<>(text, (T)t);
   }
}
