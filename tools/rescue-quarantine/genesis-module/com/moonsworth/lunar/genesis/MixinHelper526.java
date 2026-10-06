package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;

@Immutable
final class MixinHelper526 extends MixinHelper52 implements Serializable {
   static final MixinHelper5_8 field1 = new MixinHelper526(2, 4, 506097522914230528L, 1084818905618843912L);
   private final int field2;
   private final int field3;
   private final long field4;
   private final long field5;
   private static final long field6 = 0L;

   MixinHelper526(int var1, int var2, long var3, long var5) {
      Preconditions.checkArgument(var1 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", var1);
      Preconditions.checkArgument(var2 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", var2);
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var5;
   }

   @Override
   public int bits() {
      return 64;
   }

   @Override
   public MixinHelper42_2 method1() {
      return new MixinHelper526.Data(this.field2, this.field3, this.field4, this.field5);
   }

   @Override
   public String toString() {
      return "Hashing.sipHash" + this.field2 + "" + this.field3 + "(" + this.field4 + ", " + this.field5 + ")";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper526)) {
         return false;
      }

      MixinHelper526 var2 = (MixinHelper526)var1;
      return this.field2 == var2.field2 && this.field3 == var2.field3 && this.field4 == var2.field4 && this.field5 == var2.field5;
   }

   @Override
   public int hashCode() {
      return (int)(this.getClass().hashCode() ^ this.field2 ^ this.field3 ^ this.field4 ^ this.field5);
   }

   private static final class Data extends MixinHelper4222 {
      private static final int field4 = 8;
      private final int field5;
      private final int field6;
      private long v0 = 8317987319222330741L;
      private long v1 = 7237128888997146477L;
      private long v2 = 7816392313619706465L;
      private long v3 = 8387220255154660723L;
      private long b = 0L;
      private long finalM = 0L;

      Data(int var1, int var2, long var3, long var5) {
         super(8);
         this.field5 = var1;
         this.field6 = var2;
         this.v0 ^= var3;
         this.v1 ^= var5;
         this.v2 ^= var3;
         this.v3 ^= var5;
      }

      @Override
      protected void process(ByteBuffer var1) {
         this.b += 8L;
         this.processM(var1.getLong());
      }

      @Override
      protected void processRemaining(ByteBuffer var1) {
         this.b = this.b + var1.remaining();

         for (byte var2 = 0; var1.hasRemaining(); var2 += 8) {
            this.finalM = this.finalM ^ (var1.get() & 255L) << var2;
         }
      }

      @Override
      protected HashCode method10() {
         this.finalM = this.finalM ^ this.b << 56;
         this.processM(this.finalM);
         this.v2 ^= 255L;
         this.sipRound(this.field6);
         return HashCode.method3(this.v0 ^ this.v1 ^ this.v2 ^ this.v3);
      }

      private void processM(long var1) {
         this.v3 ^= var1;
         this.sipRound(this.field5);
         this.v0 ^= var1;
      }

      private void sipRound(int var1) {
         for (int var2 = 0; var2 < var1; var2++) {
            this.v0 = this.v0 + this.v1;
            this.v2 = this.v2 + this.v3;
            this.v1 = Long.rotateLeft(this.v1, 13);
            this.v3 = Long.rotateLeft(this.v3, 16);
            this.v1 = this.v1 ^ this.v0;
            this.v3 = this.v3 ^ this.v2;
            this.v0 = Long.rotateLeft(this.v0, 32);
            this.v2 = this.v2 + this.v1;
            this.v0 = this.v0 + this.v3;
            this.v1 = Long.rotateLeft(this.v1, 17);
            this.v3 = Long.rotateLeft(this.v3, 21);
            this.v1 = this.v1 ^ this.v2;
            this.v3 = this.v3 ^ this.v0;
            this.v2 = Long.rotateLeft(this.v2, 32);
         }
      }
   }
}
