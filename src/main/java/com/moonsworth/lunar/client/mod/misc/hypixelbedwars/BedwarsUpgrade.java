package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import java.util.Objects;

class BedwarsUpgrade {
   private final String field1;
   private final int field2;
   private final String field3;
   private final int[] field4;
   private final int[] field5;

   private BedwarsUpgrade(String text, int value, String text2, int[] items4, int[] items5) {
      this.field1 = text;
      this.field2 = value;
      this.field3 = text2;
      this.field4 = items4;
      this.field5 = items5;
   }

   public boolean method1() {
      return "Forge".equals(this.field1);
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return object != null && this.getClass() == object.getClass()
            ? Objects.equals(this.field1, ((BedwarsUpgrade)object).field1)
            : false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1);
   }

   public String method2() {
      return this.field1;
   }

   public int method3() {
      return this.field2;
   }

   public String method4() {
      return this.field3;
   }

   public int[] method5() {
      return this.field4;
   }

   public int[] method6() {
      return this.field5;
   }
}
