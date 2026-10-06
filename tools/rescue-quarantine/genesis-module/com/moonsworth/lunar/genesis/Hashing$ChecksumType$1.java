package com.moonsworth.lunar.genesis;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

enum Hashing$ChecksumType$1 {
   ;
   Hashing$ChecksumType$1(String text3) {
   }

   public Checksum get() {
      return new CRC32();
   }
}
