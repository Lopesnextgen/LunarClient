package com.moonsworth.lunar.genesis;
import com.google.common.eventbus.Subscribe;

@Subscribe
class Ordering$IncomparableValueException extends ClassCastException {
   final Object field1;
   private static final long field2 = 0L;

   Ordering$IncomparableValueException(Object obj1) {
      super("Cannot compare value: " + obj1);
      this.field1 = obj1;
   }
}
