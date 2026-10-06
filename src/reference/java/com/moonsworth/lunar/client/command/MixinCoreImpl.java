package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import org.jetbrains.annotations.Nullable;

public final class MixinCoreImpl extends MixinCore<Double> {
   public static final MixinCoreImpl field1 = new MixinCoreImpl();

   @Nullable
   public Double method2(ThreadModuleDump27 var1) {
      if (!var1.method2()) {
         return null;
      }

      int var2 = var1.getCursor();
      if (var1.peek() == '-') {
         var1.skip();
      }

      boolean var3 = false;

      while (var1.method2() && Character.isDigit(var1.peek())) {
         var3 = true;
         var1.skip();
      }

      if (var1.method2() && var1.peek() == '.') {
         var1.skip();

         while (var1.method2() && Character.isDigit(var1.peek())) {
            var3 = true;
            var1.skip();
         }
      }

      if (!var3) {
         var1.setCursor(var2);
         return null;
      }

      if (var1.method2() && var1.peek() != ' ') {
         var1.setCursor(var2);
         return null;
      }

      try {
         return Double.parseDouble(var1.getString().substring(var2, var1.getCursor()));
      } catch (NumberFormatException var5) {
         var1.setCursor(var2);
         return null;
      }
   }
}
