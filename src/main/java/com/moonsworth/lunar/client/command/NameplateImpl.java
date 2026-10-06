package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.MixinHelper2;
import java.time.Duration;

final class NameplateImpl implements MixinHelper2 {
   static final NameplateImpl field1 = new NameplateImpl();

   @Override
   public String getString(String var1) {
      throw method1(var1);
   }

   @Override
   public int getInteger(String var1) {
      throw method1(var1);
   }

   @Override
   public double getDouble(String var1) {
      throw method1(var1);
   }

   @Override
   public Duration getDuration(String var1) {
      throw method1(var1);
   }

   private static UnsupportedOperationException method1(String text) {
      return new UnsupportedOperationException(
         "Suggester tried to read argument '" + text + "', but no argument values are parsed during tab-complete suggestion gathering"
      );
   }
}
