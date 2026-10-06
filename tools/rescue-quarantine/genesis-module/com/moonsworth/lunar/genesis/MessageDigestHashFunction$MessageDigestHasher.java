package com.moonsworth.lunar.genesis;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class MessageDigestHashFunction$MessageDigestHasher extends AbstractByteHasher {
   private final MessageDigest field2;
   private final int field3;
   private boolean done;

   private MessageDigestHashFunction$MessageDigestHasher(MessageDigest messagedigest1, int number2) {
      this.field2 = messagedigest1;
      this.field3 = number2;
   }

   protected void update(byte number1) {
      this.checkNotDone();
      this.field2.update(number1);
   }

   protected void update(byte[] items1, int number2, int number3) {
      this.checkNotDone();
      this.field2.update(items1, number2, number3);
   }

   protected void update(ByteBuffer buffer1) {
      this.checkNotDone();
      this.field2.update(buffer1);
   }

   private void checkNotDone() {
      Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
   }

   public HashCode method15() {
      this.checkNotDone();
      this.done = true;
      return this.field3 == this.field2.getDigestLength()
         ? HashCode.method5(this.field2.digest())
         : HashCode.method5(Arrays.copyOf(this.field2.digest(), this.field3));
   }
}
