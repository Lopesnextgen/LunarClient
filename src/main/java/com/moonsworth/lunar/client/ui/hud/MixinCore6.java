package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class MixinCore6<T> {
   private final boolean field1;
   private final boolean field2;
   private boolean field3 = false;
   private T field4;
   private Supplier<T> supplier = null;

   public MixinCore6(T var1, boolean var2) {
      this((T)var1, var2, false);
   }

   public MixinCore6(T var1, boolean var2, boolean flag) {
      this.field1 = var2;
      this.field4 = (T)var1;
      this.field2 = flag;
   }

   public MixinCore6<T> method1(T var1) {
      if (!this.field1 && var1 == null) {
         throw new NullPointerException();
      }

      this.supplier = null;
      this.field4 = (T)var1;
      return this;
   }

   public MixinCore6<T> method2(Supplier<T> var1) {
      if (!this.field1 && var1 == null) {
         throw new NullPointerException();
      }

      this.supplier = var1;
      return this;
   }

   public T get() {
      if (this.supplier != null) {
         if (this.field2 && this.field3) {
            return this.field4;
         }

         Object var1 = this.supplier.get();
         if (var1 == null && !this.field1) {
            throw new NullPointerException();
         }

         if (this.field2) {
            this.field4 = (T)var1;
            this.field3 = true;
         }

         return (T)var1;
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
