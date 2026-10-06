package com.moonsworth.lunar.files;

public class Files6_2<A, B> {
   public final A field1;
   public final B field2;

   public Files6_2(A var1, B b) {
      this.field1 = (A)var1;
      this.field2 = (B)b;
   }

   public static <A, B> Files6_2<A, B> method1(A a, B var1) {
      return new Files6_2<>((A)a, (B)var1);
   }
}
