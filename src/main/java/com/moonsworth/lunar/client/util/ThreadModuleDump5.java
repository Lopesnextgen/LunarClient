package com.moonsworth.lunar.client.util;

import java.util.Objects;
import lombok.Generated;

public class ThreadModuleDump5<T> {
   private final T field1;
   private final T field2;

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof ThreadModuleDump5 var2)
         ? false
         : this.field1.equals(var2.field1) && this.field2.equals(var2.field2) || this.field1.equals(var2.field2) && this.field2.equals(var2.field1);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.field2);
   }

   @Generated
   public T method1() {
      return this.field1;
   }

   @Generated
   public T method2() {
      return this.field2;
   }

   @Generated
   public ThreadModuleDump5(T var1, T var2) {
      this.field1 = (T)var1;
      this.field2 = (T)var2;
   }
}
