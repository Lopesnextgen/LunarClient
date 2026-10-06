package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface LongAddable {
   void increment();

   void add(long number1);

   long sum();
}
