package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

public final class MixinHelper11$Data18 {
   private final double field1;
   private final double field2;

   private MixinHelper11$Data18(double var1, double var3) {
      this.field1 = var1;
      this.field2 = var3;
   }

   public MixinHelper11_4 method1(double var1, double var3) {
      Preconditions.checkArgument(MixinHelper5_11.isFinite(var1) && MixinHelper5_11.isFinite(var3));
      if (var1 == this.field1) {
         Preconditions.checkArgument(var3 != this.field2);
         return new MixinHelper11$Data20(this.field1);
      } else {
         return this.method2((var3 - this.field2) / (var1 - this.field1));
      }
   }

   public MixinHelper11_4 method2(double var1) {
      Preconditions.checkArgument(!Double.isNaN(var1));
      if (MixinHelper5_11.isFinite(var1)) {
         double var3 = this.field2 - this.field1 * var1;
         return new MixinHelper11$Data19(var1, var3);
      } else {
         return new MixinHelper11$Data20(this.field1);
      }
   }
}
