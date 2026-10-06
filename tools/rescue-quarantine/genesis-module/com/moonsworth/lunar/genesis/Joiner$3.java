package com.moonsworth.lunar.genesis;

import java.util.AbstractList;

final class Joiner$3 extends AbstractList<Object> {
   Joiner$3(Object[] items1, Object obj2, Object obj3) {
      this.field1 = items1;
      this.field2 = obj2;
      this.field3 = obj3;
   }

   @Override
   public int size() {
      return this.field1.length + 2;
   }

   @Override
   public Object get(int index1) {
      switch (index1) {
         case 0:
            return this.field2;
         case 1:
            return this.field3;
         default:
            return this.field1[index1 - 2];
      }
   }
}
