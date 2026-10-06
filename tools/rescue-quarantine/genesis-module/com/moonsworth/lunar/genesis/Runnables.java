package com.moonsworth.lunar.genesis;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public final class Runnables {
   private static final Runnable field1 = new Runnables$1();

   public static Runnable doNothing() {
      return field1;
   }

   private Runnables() {
   }
}
