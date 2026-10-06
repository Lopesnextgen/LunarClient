package com.moonsworth.lunar.genesis;

import java.io.Serializable;

final class MessageDigestHashFunction$SerializedForm implements Serializable {
   private final String field1;
   private final int field2;
   private final String field3;
   private static final long field4 = 0L;

   private MessageDigestHashFunction$SerializedForm(String text1, int number2, String text3) {
      this.field1 = text1;
      this.field2 = number2;
      this.field3 = text3;
   }

   private Object readResolve() {
      return new MixinHelper523(this.field1, this.field2, this.field3);
   }
}
