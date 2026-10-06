package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import org.jetbrains.annotations.Nullable;

public final class GreedyStringArgumentParser extends MixinCore<String> {
   public static final GreedyStringArgumentParser field1 = new GreedyStringArgumentParser();

   @Nullable
   public String method2(ThreadModuleDump27 var1) {
      if (!var1.method2()) {
         return null;
      }

      String var2 = var1.method5();
      var1.setCursor(var1.getString().length());
      return var2;
   }

   @Override
   public boolean method2() {
      return true;
   }
}
