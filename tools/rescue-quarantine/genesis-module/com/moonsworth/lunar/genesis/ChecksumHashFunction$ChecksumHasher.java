package com.moonsworth.lunar.genesis;

import java.util.zip.Checksum;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class ChecksumHashFunction$ChecksumHasher extends AbstractByteHasher {
   private final Checksum field2;

   private ChecksumHashFunction$ChecksumHasher(MixinHelper522 mixinhelper5221, Checksum checksum2) {
      this.field3 = mixinhelper5221;
      this.field2 = (Checksum)Preconditions.checkNotNull(checksum2);
   }

   protected void update(byte number1) {
      this.field2.update(number1);
   }

   protected void update(byte[] items1, int number2, int number3) {
      this.field2.update(items1, number2, number3);
   }

   public HashCode method15() {
      long number1 = this.field2.getValue();
      return MixinHelper522.method2(this.field3) == 32 ? HashCode.method2((int)number1) : HashCode.method3(number1);
   }
}
