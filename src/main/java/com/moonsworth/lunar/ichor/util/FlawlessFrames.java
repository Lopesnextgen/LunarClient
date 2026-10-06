package com.moonsworth.lunar.ichor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@IchorService
public class FlawlessFrames {
   private static final List<Consumer<Boolean>> consumers = new ArrayList<>();
   private static boolean enabled;

   public FlawlessFrames() {
   }

   public static void register(Function<String, Consumer<Boolean>> function0) {
      consumers.add((Consumer<Boolean>)function0.apply("lunar"));
   }

   public static void set(boolean flag) {
      if (enabled != flag) {
         enabled = flag;

         for (Consumer consumer2 : consumers) {
            consumer2.accept(flag);
         }
      }
   }

   public static boolean get() {
      return enabled;
   }
}
