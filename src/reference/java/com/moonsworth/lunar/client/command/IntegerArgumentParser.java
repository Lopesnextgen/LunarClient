package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import org.jetbrains.annotations.Nullable;

public final class IntegerArgumentParser extends MixinCore<Integer> {
   public static final IntegerArgumentParser field1 = new IntegerArgumentParser();

   @Nullable
   public Integer method2(ThreadModuleDump27 var1) {
      if (!var1.method2()) {
         return null;
      }

      int var2 = var1.getCursor();
      if (var1.peek() == '-') {
         var1.skip();
      }

      if (var1.method2() && Character.isDigit(var1.peek())) {
         while (var1.method2() && Character.isDigit(var1.peek())) {
            var1.skip();
         }

         if (var1.method2() && var1.peek() != ' ') {
            var1.setCursor(var2);
            return null;
         }

         try {
            return Integer.parseInt(var1.getString().substring(var2, var1.getCursor()));
         } catch (NumberFormatException var4) {
            var1.setCursor(var2);
            return null;
         }
      } else {
         var1.setCursor(var2);
         return null;
      }
   }
}
