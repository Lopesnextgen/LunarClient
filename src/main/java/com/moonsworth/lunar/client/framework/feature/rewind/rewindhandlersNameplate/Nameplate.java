package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import java.nio.ByteBuffer;

public class Nameplate {
   private final ByteBuffer field1;
   private final int field2;
   private final int field3;

   public Nameplate(ByteBuffer byteBuffer, int value, int value2) {
      this.field1 = byteBuffer;
      this.field2 = value;
      this.field3 = value2;
   }

   public ByteBuffer buffer() {
      return this.field1;
   }

   public int method1() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }
}
