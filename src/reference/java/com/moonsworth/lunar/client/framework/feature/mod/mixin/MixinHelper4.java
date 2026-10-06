package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.command.MixinNameplateIterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class MixinHelper4 extends MixinNameplate2 {
   private static final List<String> field2 = List.of("all", "party", "guild", "officer", "coop");

   public MixinHelper4() {
      super(
         MixinNameplateImpl.method1("sendcoords")
            .method3(var0 -> method1(null))
            .method2(MixinNameplateIterator.method1("mode", StringArgumentParser.field1).method5(field2).method8(var0 -> method1(var0.getString("mode"))))
      );
   }

   @Override
   public boolean isEnabled() {
      return Click3.hasIsland();
   }

   private static void method1(@Nullable String var0) {
      String var10000;
      if (var0 == null) {
         var10000 = "";
      } else {
         switch (var0) {
            case "all":
               var10000 = "/ac ";
               break;
            case "party":
               var10000 = "/pc ";
               break;
            case "guild":
               var10000 = "/gc ";
               break;
            case "officer":
               var10000 = "/oc ";
               break;
            case "coop":
               var10000 = "/cc ";
               break;
            default:
               var10000 = "";
         }
      }

      String var1 = var10000;
      ThreadModuleDump63.method7()
         .bridge$sendChatMessage(
            var1
               + "x: "
               + ThreadModuleDump63.method7().bridge$getBlockX()
               + ", y: "
               + ThreadModuleDump63.method7().bridge$getBlockY()
               + ", z: "
               + ThreadModuleDump63.method7().bridge$getBlockZ()
         );
   }
}
