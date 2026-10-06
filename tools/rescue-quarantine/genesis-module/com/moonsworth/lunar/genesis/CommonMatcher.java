package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonMatcher {
   CommonMatcher() {
   }

   public abstract boolean matches();

   public abstract boolean find();

   public abstract boolean find(int number1);

   public abstract String replaceAll(String text1);

   public abstract int end();

   public abstract int start();
}
