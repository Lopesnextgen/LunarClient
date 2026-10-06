package com.moonsworth.lunar.genesis;

import java.nio.ByteBuffer;
import javax.crypto.Mac;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class MacHashFunction$MacHasher extends AbstractByteHasher {
   private final Mac field2;
   private boolean done;

   private MacHashFunction$MacHasher(Mac mac1) {
      this.field2 = mac1;
   }

   protected void update(byte number1) {
      this.checkNotDone();
      this.field2.update(number1);
   }

   protected void update(byte[] items1) {
      this.checkNotDone();
      this.field2.update(items1);
   }

   protected void update(byte[] items1, int number2, int number3) {
      this.checkNotDone();
      this.field2.update(items1, number2, number3);
   }

   protected void update(ByteBuffer buffer1) {
      this.checkNotDone();
      Preconditions.checkNotNull(buffer1);
      this.field2.update(buffer1);
   }

   private void checkNotDone() {
      Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
   }

   public HashCode method15() {
      this.checkNotDone();
      this.done = true;
      return HashCode.method5(this.field2.doFinal());
   }
}
