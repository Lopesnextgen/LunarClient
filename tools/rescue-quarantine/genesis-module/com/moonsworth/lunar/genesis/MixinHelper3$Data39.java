package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import com.google.common.base.Preconditions;

class MixinHelper3$Data39 extends OutputStream {
   final MixinHelper4_3 field1;

   MixinHelper3$Data39(MixinHelper4_3 var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public void write(int var1) {
      this.field1.method1((byte)var1);
   }

   @Override
   public void write(byte[] var1) {
      this.field1.method2(var1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this.field1.method3(var1, var2, var3);
   }

   @Override
   public String toString() {
      return "Funnels.asOutputStream(" + this.field1 + ")";
   }
}
