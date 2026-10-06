package com.moonsworth.lunar.files;

public final class BinaryMappingsFormat {
   public static final int MAGIC = 99151942;
   public static final byte VERSION_ONE = 1;
   public static final String STANDARD_EXTENSION = "kin";

   public static String toHexString(int value) {
      return "0x" + Integer.toHexString(value);
   }

   private BinaryMappingsFormat() {
   }
}
