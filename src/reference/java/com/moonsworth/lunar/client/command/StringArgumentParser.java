package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import org.jetbrains.annotations.Nullable;

public class StringArgumentParser extends MixinCore<String> {
   public static final StringArgumentParser field1 = new StringArgumentParser();

   @Nullable
   public String method2(ThreadModuleDump27 var1) {
      if (var1.method2() && var1.peek() != ' ') {
         int var2 = var1.getCursor();

         while (var1.method2() && var1.peek() != ' ') {
            var1.skip();
         }

         return var1.getString().substring(var2, var1.getCursor());
      } else {
         return null;
      }
   }
}
