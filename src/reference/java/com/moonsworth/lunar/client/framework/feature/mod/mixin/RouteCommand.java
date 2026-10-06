package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.GreedyStringArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.command.CommandSuggestionBuilder;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class RouteCommand extends ClientCommand {
   private static final RouteHelpSubcommand field2 = new RouteHelpSubcommand();
   private static final Map<String, RouteSubcommand> field3 = new HashMap<>();

   public RouteCommand() {
      super(
         LiteralCommandNode.method1("route")
            .method3(arg0 -> method1(new String[0]))
            .method2(
               ArgumentCommandNode.method1("args", GreedyStringArgumentParser.field1)
                  .method2(RouteCommand::method2)
                  .method8(arg0 -> method1(arg0.getString("args").split(" ")))
            )
      );
   }

   @Override
   public boolean isEnabled() {
      return IslandUtils.isOnIsland() && IslandUtils.getIsland() == SkyblockIsland.DUNGEON && SkyblockDungeonRoutes.method13().isEnabled();
   }

   private static void method1(String[] items0) {
      String text1 = items0.length == 0 ? "help" : items0[0].toLowerCase(Locale.ROOT);
      RouteSubcommand mixinhelper22 = field3.getOrDefault(text1, field2);
      mixinhelper22.method1(items0);
   }

   private static void method2(com.moonsworth.lunar.client.command.CommandArguments mixinhelper20, CommandSuggestionBuilder nameplate3_21) {
      String text2 = nameplate3_21.method1();
      String[] items3 = text2.split(" ", -1);
      String text4 = items3[items3.length - 1].toLowerCase(Locale.ROOT);
      int number5 = text2.lastIndexOf(32) + 1;
      CommandSuggestionBuilder nameplate3_26 = nameplate3_21.method3(nameplate3_21.getStart() + number5);
      Stream stream7;
      if (items3.length <= 1) {
         stream7 = field3.keySet().stream();
      } else {
         String text8 = items3[0].toLowerCase(Locale.ROOT);
         RouteSubcommand mixinhelper29 = field3.getOrDefault(text8, field2);
         stream7 = mixinhelper29.method2(items3);
      }

      stream7.filter(arg1x -> arg1x.toLowerCase(Locale.ROOT).startsWith(text4)).forEach(nameplate3_26::method2);
   }

   static {
      field3.put("help", field2);
      field3.put("save", new RouteSaveSubcommand());
      field3.put("start", new RouteStartSubcommand());
      field3.put("clone", new RouteCloneSubcommand());
      field3.put("cancel", new RouteCancelSubcommand());
      field3.put("reset", new RouteResetSubcommand());
      field3.put("info", new RouteInfoSubcommand());
      field3.put("show", new RouteShowSubcommand());
      field3.put("delete", new RouteDeleteSubcommand());
      field3.put("select", new RouteSelectSubcommand());
      field3.put("deselect", new RouteDeselectSubcommand());
      field3.put("section", new RouteSectionSubcommand());
      field3.put("list", new RouteListSubcommand());
      field3.put("meta", new RouteMetaSubcommand());
      field3.put("sectionmeta", new RouteSectionMetaSubcommand());
      field3.put("whitelist", new RouteWhitelistSubcommand());
      field3.put("blacklist", new RouteBlacklistSubcommand());
   }
}
