package com.moonsworth.lunar.ichor;

import java.time.Duration;

public class Ichor {
   public static long field1;

   public Ichor() {
   }

   public static void log() {
      double value0 = Duration.ofSeconds(1L).toNanos();
      System.out.printf("PROTECTION DOMAIN %.2f seconds", field1 / value0);
   }
}
