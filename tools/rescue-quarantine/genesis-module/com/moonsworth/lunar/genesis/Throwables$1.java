package com.moonsworth.lunar.genesis;

import java.util.AbstractList;

final class Throwables$1 extends AbstractList<StackTraceElement> {
   Throwables$1(Throwable exception1) {
      this.field1 = exception1;
   }

   public StackTraceElement get(int number1) {
      return (StackTraceElement)MixinHelper13_2.access$200(MixinHelper13_2.access$000(), MixinHelper13_2.access$100(), new Object[]{this.field1, number1});
   }

   @Override
   public int size() {
      return (Integer)MixinHelper13_2.access$200(MixinHelper13_2.access$300(), MixinHelper13_2.access$100(), new Object[]{this.field1});
   }
}
