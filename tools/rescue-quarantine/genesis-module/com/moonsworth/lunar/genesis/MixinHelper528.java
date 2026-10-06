package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@Immutable
final class MixinHelper528 extends MixinHelper52 {
   private final Mac field1;
   private final Key field2;
   private final String field3;
   private final int field4;
   private final boolean field5;

   MixinHelper528(String var1, Key var2, String var3) {
      this.field1 = getMac(var1, var2);
      this.field2 = Preconditions.checkNotNull(var2);
      this.field3 = Preconditions.checkNotNull(var3);
      this.field4 = this.field1.getMacLength() * 8;
      this.field5 = supportsClone(this.field1);
   }

   @Override
   public int bits() {
      return this.field4;
   }

   private static boolean supportsClone(Mac var0) {
      try {
         var0.clone();
         return true;
      } catch (CloneNotSupportedException var2) {
         return false;
      }
   }

   private static Mac getMac(String var0, Key var1) {
      try {
         Mac var2 = Mac.getInstance(var0);
         var2.init(var1);
         return var2;
      } catch (NoSuchAlgorithmException var3) {
         throw new IllegalStateException(var3);
      } catch (InvalidKeyException var4) {
         throw new IllegalArgumentException(var4);
      }
   }

   @Override
   public MixinHelper42_2 method1() {
      if (this.field5) {
         try {
            return new MixinHelper528.Data((Mac)this.field1.clone());
         } catch (CloneNotSupportedException var2) {
         }
      }

      return new MixinHelper528.Data(getMac(this.field1.getAlgorithm(), this.field2));
   }

   @Override
   public String toString() {
      return this.field3;
   }

   private static final class Data extends MixinHelper4223 {
      private final Mac field2;
      private boolean done;

      private Data(Mac var1) {
         this.field2 = var1;
      }

      @Override
      protected void update(byte var1) {
         this.checkNotDone();
         this.field2.update(var1);
      }

      @Override
      protected void update(byte[] var1) {
         this.checkNotDone();
         this.field2.update(var1);
      }

      @Override
      protected void update(byte[] var1, int var2, int var3) {
         this.checkNotDone();
         this.field2.update(var1, var2, var3);
      }

      @Override
      protected void update(ByteBuffer var1) {
         this.checkNotDone();
         Preconditions.checkNotNull(var1);
         this.field2.update(var1);
      }

      private void checkNotDone() {
         Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
      }

      @Override
      public HashCode method15() {
         this.checkNotDone();
         this.done = true;
         return HashCode.method5(this.field2.doFinal());
      }
   }
}
