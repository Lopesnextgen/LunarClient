package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.client.command.GreedyStringArgumentParser;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.command.MixinNameplateIterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.command.Nameplate3_2;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class MixinHelper5 extends MixinNameplate2 {
   private static final MixinHelper107 field2 = new MixinHelper107();
   private static final Map<String, MixinHelper2> field3 = new HashMap<>();

   public MixinHelper5() {
      super(
         MixinNameplateImpl.method1("route")
            .method3(var0 -> method1(new String[0]))
            .method2(
               MixinNameplateIterator.method1("args", GreedyStringArgumentParser.field1)
                  .method2(MixinHelper5::method2)
                  .method8(var0 -> method1(var0.getString("args").split(" ")))
            )
      );
   }

   @Override
   public boolean isEnabled() {
      return Click3.hasIsland() && Click3.getIsland() == Gui2Extension3.DUNGEON && SkyblockDungeonRoutes.method13().isEnabled();
   }

   private static void method1(String[] var0) {
      String var1 = var0.length == 0 ? "help" : var0[0].toLowerCase(Locale.ROOT);
      MixinHelper2 var2 = field3.getOrDefault(var1, field2);
      var2.method1(var0);
   }

   private static void method2(com.moonsworth.lunar.client.command.MixinHelper2 var0, Nameplate3_2 var1) {
      String var2 = var1.method1();
      String[] var3 = var2.split(" ", -1);
      String var4 = var3[var3.length - 1].toLowerCase(Locale.ROOT);
      int var5 = var2.lastIndexOf(32) + 1;
      Nameplate3_2 var6 = var1.method3(var1.getStart() + var5);
      Stream var7;
      if (var3.length <= 1) {
         var7 = field3.keySet().stream();
      } else {
         String var8 = var3[0].toLowerCase(Locale.ROOT);
         MixinHelper2 var9 = field3.getOrDefault(var8, field2);
         var7 = var9.method2(var3);
      }

      var7.filter(var1x -> var1x.toLowerCase(Locale.ROOT).startsWith(var4)).forEach(var6::method2);
   }

   static {
      field3.put("help", field2);
      field3.put("save", new MixinHelper106());
      field3.put("start", new MixinHelper105());
      field3.put("clone", new MixinHelper10());
      field3.put("cancel", new MixinHelper7());
      field3.put("reset", new MixinHelper6());
      field3.put("info", new MixinHelper109());
      field3.put("show", new MixinHelper104());
      field3.put("delete", new MixinHelper103());
      field3.put("select", new MixinHelper1012());
      field3.put("deselect", new MixinHelper1011());
      field3.put("section", new MixinHelper108());
      field3.put("list", new MixinHelper1010());
      field3.put("meta", new MixinHelper102());
      field3.put("sectionmeta", new MixinHelper8());
      field3.put("whitelist", new MixinHelper1013());
      field3.put("blacklist", new MixinHelper9());
   }
}
