package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateLifecycleBridge;
import lombok.Generated;

public class Bridge19Task implements RenderStateLifecycleBridge {
   private final Runnable field1;
   private final Runnable field2;

   public void bridge$setupState() {
      this.field1.run();
   }

   public void bridge$clearState() {
      this.field2.run();
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Bridge19Task var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else {
         Runnable var3 = this.field1;
         Runnable var4 = var2.field1;
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Runnable var5 = this.field2;
            Runnable var6 = var2.field2;
            return var5 == null ? var6 == null : var5.equals(var6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Bridge19Task;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      Runnable var3 = this.field1;
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Runnable var4 = this.field2;
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   @Generated
   public Bridge19Task(Runnable var1, Runnable var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
