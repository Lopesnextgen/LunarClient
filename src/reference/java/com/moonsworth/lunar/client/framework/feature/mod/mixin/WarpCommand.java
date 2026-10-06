package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.GreedyStringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Locale;

public class WarpCommand extends ClientCommand {
   private static boolean field2;

   public WarpCommand() {
      super(
         LiteralCommandNode.method1("warp")
            .method3(arg0 -> method1(""))
            .method2(ArgumentCommandNode.method1("destination", GreedyStringArgumentParser.field1).method2((arg0, arg1) -> {
               List list2 = Ref.method4().method40().method82().method15().method28();
               if (list2 != null) {
                  String text3 = arg1.method1().toLowerCase(Locale.ROOT);

                  for (String text5 : list2) {
                     if (text5.startsWith(text3)) {
                        arg1.method2(text5);
                     }
                  }
               }
            }).method8(arg0 -> method1(arg0.getString("destination"))))
      );
   }

   private static void method1(String text0) {
      field2 = true;
      String text1 = text0.isEmpty() ? "/warp" : "/warp " + text0;

      try {
         Ref.method7().bridge$sendChatMessage(text1);
      } finally {
         field2 = false;
      }
   }

   @Override
   public boolean isEnabled() {
      return (Boolean)Ref.method4().method40().method82().method36().get() && !field2 && IslandUtils.isOnIsland();
   }
}
