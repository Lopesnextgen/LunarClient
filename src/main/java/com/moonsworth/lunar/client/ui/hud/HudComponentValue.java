package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class HudComponentValue<T> {
   private final boolean field1;
   private final boolean field2;
   private boolean field3 = false;
   private T field4;
   private Supplier<T> supplier = null;

   public HudComponentValue(T value1, boolean flag2) {
      this((T)value1, flag2, false);
   }

   public HudComponentValue(T value1, boolean flag2, boolean flag) {
      this.field1 = flag2;
      this.field4 = (T)value1;
      this.field2 = flag;
   }

   public HudComponentValue<T> method1(T value1) {
      if (!this.field1 && value1 == null) {
         throw new NullPointerException();
      }

      this.supplier = null;
      this.field4 = (T)value1;
      return this;
   }

   public HudComponentValue<T> method2(Supplier<T> supplier1) {
      if (!this.field1 && supplier1 == null) {
         throw new NullPointerException();
      }

      this.supplier = supplier1;
      return this;
   }

   public T get() {
      if (this.supplier != null) {
         if (this.field2 && this.field3) {
            return this.field4;
         }

         Object obj1 = this.supplier.get();
         if (obj1 == null && !this.field1) {
            throw new NullPointerException();
         }

         if (this.field2) {
            this.field4 = (T)obj1;
            this.field3 = true;
         }

         return (T)obj1;
      } else {
         return this.field4;
      }
   }

   public void clearCache() {
      if (!this.field2) {
         throw new IllegalStateException("Not a caching HudComponentValue!");
      }

      if (this.supplier != null) {
         this.field3 = false;
         this.field4 = null;
      }
   }
}
