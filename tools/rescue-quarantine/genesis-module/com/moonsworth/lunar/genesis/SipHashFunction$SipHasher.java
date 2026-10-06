package com.moonsworth.lunar.genesis;

import java.nio.ByteBuffer;
import com.google.common.hash.HashCode;

final class SipHashFunction$SipHasher extends AbstractStreamingHasher {
   private static final int field4 = 8;
   private final int field5;
   private final int field6;
   private long v0 = 8317987319222330741L;
   private long v1 = 7237128888997146477L;
   private long v2 = 7816392313619706465L;
   private long v3 = 8387220255154660723L;
   private long b = 0L;
   private long finalM = 0L;

   SipHashFunction$SipHasher(int number1, int number2, long number3, long number5) {
      super(8);
      this.field5 = number1;
      this.field6 = number2;
      this.v0 ^= number3;
      this.v1 ^= number5;
      this.v2 ^= number3;
      this.v3 ^= number5;
   }

   protected void process(ByteBuffer buffer1) {
      this.b += 8L;
      this.processM(buffer1.getLong());
   }

   protected void processRemaining(ByteBuffer buffer1) {
      this.b = this.b + buffer1.remaining();

      for (byte index2 = 0; buffer1.hasRemaining(); index2 += 8) {
         this.finalM = this.finalM ^ (buffer1.get() & 255L) << index2;
      }
   }

   protected HashCode method10() {
      this.finalM = this.finalM ^ this.b << 56;
      this.processM(this.finalM);
      this.v2 ^= 255L;
      this.sipRound(this.field6);
      return HashCode.method3(this.v0 ^ this.v1 ^ this.v2 ^ this.v3);
   }

   private void processM(long number1) {
      this.v3 ^= number1;
      this.sipRound(this.field5);
      this.v0 ^= number1;
   }

   private void sipRound(int number1) {
      for (int index2 = 0; index2 < number1; index2++) {
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
