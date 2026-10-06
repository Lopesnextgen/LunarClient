package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.GreedyStringArgumentParser;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.command.MixinNameplateIterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Locale;

public class MixinHelper3 extends MixinNameplate2 {
   private static boolean field2;

   public MixinHelper3() {
      super(
         MixinNameplateImpl.method1("warp")
            .method3(var0 -> method1(""))
            .method2(MixinNameplateIterator.method1("destination", GreedyStringArgumentParser.field1).method2((var0, var1) -> {
               List var2 = ThreadModuleDump63.method4().method40().method82().method15().method28();
               if (var2 != null) {
                  String var3 = var1.method1().toLowerCase(Locale.ROOT);

                  for (String var5 : var2) {
                     if (var5.startsWith(var3)) {
                        var1.method2(var5);
                     }
                  }
               }
            }).method8(var0 -> method1(var0.getString("destination"))))
      );
   }

   private static void method1(String var0) {
      field2 = true;
      String var1 = var0.isEmpty() ? "/warp" : "/warp " + var0;

      try {
         ThreadModuleDump63.method7().bridge$sendChatMessage(var1);
      } finally {
         field2 = false;
      }
   }

   @Override
   public boolean isEnabled() {
      return (Boolean)ThreadModuleDump63.method4().method40().method82().method36().get() && !field2 && Click3.hasIsland();
   }
}
