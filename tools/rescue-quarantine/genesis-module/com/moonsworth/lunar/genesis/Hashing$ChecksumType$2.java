package com.moonsworth.lunar.genesis;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

enum Hashing$ChecksumType$2 {
   ;
   Hashing$ChecksumType$2(String text3) {
   }

   public Checksum get() {
      return new Adler32();
   }
}
