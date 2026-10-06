package com.moonsworth.lunar.client.mod.misc;

import java.time.LocalDate;
import java.time.Month;

public class EventChest {
   public static final boolean field1;
   public static final boolean field2;

   static {
      LocalDate var0 = LocalDate.now();
      field1 = var0.getMonth() == Month.APRIL && var0.getDayOfMonth() == 1;
      field2 = var0.getMonth() == Month.APRIL && var0.getDayOfMonth() == 6;
   }
}
