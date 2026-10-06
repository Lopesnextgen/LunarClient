package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@Immutable
final class MixinHelper522 extends MixinHelper52 implements Serializable {
   private final SupplierExtension_2<? extends Checksum> field1;
   private final int field2;
   private final String field3;
   private static final long field4 = 0L;

   MixinHelper522(SupplierExtension_2<? extends Checksum> var1, int var2, String var3) {
      this.field1 = Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(var2 == 32 || var2 == 64, "bits (%s) must be either 32 or 64", var2);
      this.field2 = var2;
      this.field3 = Preconditions.checkNotNull(var3);
   }

   @Override
   public int bits() {
      return this.field2;
   }

   @Override
   public MixinHelper42_2 method1() {
      return new MixinHelper522.Data(this.field1.get());
   }

   @Override
   public String toString() {
      return this.field3;
   }

   private final class Data extends MixinHelper4223 {
      private final Checksum field2;

      private Data(Checksum var2) {
         this.field2 = Preconditions.checkNotNull(var2);
      }

      @Override
      protected void update(byte var1) {
         this.field2.update(var1);
      }

      @Override
      protected void update(byte[] var1, int var2, int var3) {
         this.field2.update(var1, var2, var3);
      }

      @Override
      public HashCode method15() {
         long var1 = this.field2.getValue();
         return MixinHelper522.this.field2 == 32 ? HashCode.method2((int)var1) : HashCode.method3(var1);
      }
   }
}
