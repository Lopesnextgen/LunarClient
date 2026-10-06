package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class SendCoordsCommand extends ClientCommand {
   private static final List<String> field2 = List.of("all", "party", "guild", "officer", "coop");

   public SendCoordsCommand() {
      super(
         LiteralCommandNode.method1("sendcoords")
            .method3(arg0 -> method1(null))
            .method2(ArgumentCommandNode.method1("mode", StringArgumentParser.field1).method5(field2).method8(arg0 -> method1(arg0.getString("mode"))))
      );
   }

   @Override
   public boolean isEnabled() {
      return IslandUtils.isOnIsland();
   }

   private static void method1(@Nullable String text0) {
      String text10000;
      if (text0 == null) {
         text10000 = "";
      } else {
         switch (text0) {
            case "all":
               text10000 = "/ac ";
               break;
            case "party":
               text10000 = "/pc ";
               break;
            case "guild":
               text10000 = "/gc ";
               break;
            case "officer":
               text10000 = "/oc ";
               break;
            case "coop":
               text10000 = "/cc ";
               break;
            default:
               text10000 = "";
         }
      }

      String text1 = text10000;
      Ref.method7()
         .bridge$sendChatMessage(
            text1
               + "x: "
               + Ref.method7().bridge$getBlockX()
               + ", y: "
               + Ref.method7().bridge$getBlockY()
               + ", z: "
               + Ref.method7().bridge$getBlockZ()
         );
   }
}
