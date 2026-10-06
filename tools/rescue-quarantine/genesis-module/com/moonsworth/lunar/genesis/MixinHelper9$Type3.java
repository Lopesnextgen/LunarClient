package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.util.zip.Checksum;
import com.google.common.hash.HashFunction;

@Immutable
enum MixinHelper9$Type3 implements ImmutableSupplier<Checksum> {
   CRC_32("CRC_32", 0, "Hashing.crc32()"),
   ADLER_32("ADLER_32", 1, "Hashing.adler32()");

   public final HashFunction hashFunction;

   MixinHelper9$Type3(String text3) {
      this.hashFunction = new MixinHelper522(this, 32, text3);
   }
}
