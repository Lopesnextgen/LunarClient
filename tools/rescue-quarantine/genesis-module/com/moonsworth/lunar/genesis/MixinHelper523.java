package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;

@Immutable
final class MixinHelper523 extends MixinHelper52 implements Serializable {
   private final MessageDigest field1;
   private final int field2;
   private final boolean field3;
   private final String field4;

   MixinHelper523(String var1, String var2) {
      this.field1 = getMessageDigest(var1);
      this.field2 = this.field1.getDigestLength();
      this.field4 = Preconditions.checkNotNull(var2);
      this.field3 = supportsClone(this.field1);
   }

   MixinHelper523(String var1, int var2, String var3) {
      this.field4 = Preconditions.checkNotNull(var3);
      this.field1 = getMessageDigest(var1);
      int var4 = this.field1.getDigestLength();
      Preconditions.checkArgument(var2 >= 4 && var2 <= var4, "bytes (%s) must be >= 4 and < %s", var2, var4);
      this.field2 = var2;
      this.field3 = supportsClone(this.field1);
   }

   private static boolean supportsClone(MessageDigest var0) {
      try {
         var0.clone();
         return true;
      } catch (CloneNotSupportedException var2) {
         return false;
      }
   }

   @Override
   public int bits() {
      return this.field2 * 8;
   }

   @Override
   public String toString() {
      return this.field4;
   }

   private static MessageDigest getMessageDigest(String var0) {
      try {
         return MessageDigest.getInstance(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new AssertionError(var2);
      }
   }

   @Override
   public MixinHelper42_2 method1() {
      if (this.field3) {
         try {
            return new MixinHelper523.Data2((MessageDigest)this.field1.clone(), this.field2);
         } catch (CloneNotSupportedException var2) {
         }
      }

      return new MixinHelper523.Data2(getMessageDigest(this.field1.getAlgorithm()), this.field2);
   }

   Object writeReplace() {
      return new MixinHelper523.Data(this.field1.getAlgorithm(), this.field2, this.field4);
   }

   private static final class Data implements Serializable {
      private final String field1;
      private final int field2;
      private final String field3;
      private static final long field4 = 0L;

      private Data(String var1, int var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      private Object readResolve() {
         return new MixinHelper523(this.field1, this.field2, this.field3);
      }
   }

   private static final class Data2 extends MixinHelper4223 {
      private final MessageDigest field2;
      private final int field3;
      private boolean done;

      private Data2(MessageDigest var1, int var2) {
         this.field2 = var1;
         this.field3 = var2;
      }

      @Override
      protected void update(byte var1) {
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
         this.field2.update(var1);
      }

      private void checkNotDone() {
         Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
      }

      @Override
      public HashCode method15() {
         this.checkNotDone();
         this.done = true;
         return this.field3 == this.field2.getDigestLength()
            ? HashCode.method5(this.field2.digest())
            : HashCode.method5(Arrays.copyOf(this.field2.digest(), this.field3));
      }
   }
}
