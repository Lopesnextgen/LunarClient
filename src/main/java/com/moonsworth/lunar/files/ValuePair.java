package com.moonsworth.lunar.files;

public class ValuePair<A, B> {
   public final A field1;
   public final B field2;

   public ValuePair(A value1, B b) {
      this.field1 = (A)value1;
      this.field2 = (B)b;
   }

   public static <A, B> ValuePair<A, B> method1(A a, B value1) {
      return new ValuePair<>((A)a, (B)value1);
   }
}
