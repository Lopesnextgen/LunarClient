package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public final class Holograms2_2 {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");
   private static final String[][] field2 = new String[][]{
      {"CHAMBERS", "BLOOD", "SLIME", "SLIME", "SLIME", "ICEFILL"},
      {"CHAMBERS", "CHAMBERS", "HOL", "TTT", "FAIRY", "DIAGONAL"},
      {"PIPES", "MINIBOSS", "STAIRCASE", "MIRROR", "CHAINS", "DIAGONAL"},
      {"PIPES", "PLATES", "PLATES", "PAINTING", "BASEMENT", "DIAGONAL"},
      {"PIPES", "SKULLS", "SUPERTALL", "SUPERTALL", "SPIDER", "SPAWN"},
      {"PIPES", "TRAP", "SUPERTALL", "SUPERTALL", "SPIDER", "SPIDER"}
   };
   private static final Map<String, HologramsType5> field3 = Map.of(
      "SPAWN",
      HologramsType5.SPAWN,
      "BLOOD",
      HologramsType5.BLOOD,
      "FAIRY",
      HologramsType5.FAIRY,
      "TRAP",
      HologramsType5.TRAP,
      "MINIBOSS",
      HologramsType5.MINIBOSS,
      "HOL",
      HologramsType5.PUZZLE,
      "TTT",
      HologramsType5.PUZZLE,
      "ICEFILL",
      HologramsType5.PUZZLE
   );
   private static final Map<String, Holograms2$Data> field4 = Map.ofEntries(
      Map.entry(
         "CHAMBERS",
         new Holograms2$Data("Chambers", 5, 5, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "BLOOD", new Holograms2$Data(null, 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "SLIME", new Holograms2$Data("Slime", 5, 5, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "ICEFILL",
         new Holograms2$Data("Ice Fill", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "HOL",
         new Holograms2$Data("Higher Or Lower", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "TTT", new Holograms2$Data("Tic Tac Toe", 1, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.FAILED)
      ),
      Map.entry(
         "FAIRY", new Holograms2$Data("Fairy", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "DIAGONAL",
         new Holograms2$Data("Diagonal", 4, 2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED)
      ),
      Map.entry(
         "PIPES", new Holograms2$Data("Pipes", 7, 7, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "MINIBOSS",
         new Holograms2$Data("Miniboss", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "STAIRCASE",
         new Holograms2$Data("Staircase", 3, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED)
      ),
      Map.entry(
         "MIRROR", new Holograms2$Data("Mirror", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "CHAINS",
         new Holograms2$Data("Overgrown Chains", 2, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED)
      ),
      Map.entry(
         "PLATES",
         new Holograms2$Data("Pressure Plates", 6, 6, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "PAINTING",
         new Holograms2$Data("Painting", 2, 2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "BASEMENT",
         new Holograms2$Data("Basement", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "SKULLS",
         new Holograms2$Data("Blue Skulls", 1, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED)
      ),
      Map.entry(
         "SUPERTALL",
         new Holograms2$Data("Supertall", 6, 6, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "SPIDER", new Holograms2$Data("Spider", 9, 3, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED)
      ),
      Map.entry(
         "TRAP", new Holograms2$Data("Old Trap", 4, 4, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      ),
      Map.entry(
         "SPAWN", new Holograms2$Data("Entrance", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED)
      )
   );
   private static final int[][] field5 = new int[][]{{3, 2}, {5, 4}, {1, 2}, {2, 4}};
   private static final UUID field6 = UUID.fromString("e23e44f6-05f2-4f23-b65e-51b449dd0c0d");
   private static final UUID field7 = UUID.fromString("dc8c3964-7b29-4e03-ae9e-d13ebd65dd29");
   private static final UUID field8 = UUID.fromString("63401220-16bb-4432-bb9a-089198355a2a");
   private static final UUID field9 = UUID.fromString("1f8e65d2-a194-4632-bff4-b4f6b02d9567");

   public static Holograms2_5 method1(boolean var0) {
      Holograms2$Data2 var1 = new Holograms2$Data2(var0);
      HashMap var2 = new HashMap();

      for (int var3 = 0; var3 < field2.length; var3++) {
         for (int var4 = 0; var4 < field2[var3].length; var4++) {
            var2.computeIfAbsent(field2[var3][var4], var0x -> new ArrayList<>()).add(new int[]{var4, var3});
         }
      }

      for (Entry var11 : var2.entrySet()) {
         HologramsType5 var5 = field3.getOrDefault(var11.getKey(), HologramsType5.CLEAR);
         Holograms4Iterator var6 = new Holograms4Iterator(var1, var5);

         for (int[] var8 : (List)var11.getValue()) {
            var6.method5(Nameplate4.method1(var8[0], var8[1], var1));
         }

         Holograms2$Data var14 = field4.get(var11.getKey());
         if (var14.name() != null) {
            var6.method30().setDisplayName(var14.name());
         }

         var6.method30().method17(var14.method1());
         var6.method30().method10(var14.method2());
         var6.method30().method11(var14.method3());
      }

      method2(var1, HologramsType5.BLOOD, 1, 0, 2, 0);
      method2(var1, HologramsType5.CLEAR, 4, 0, 4, 1);
      method2(var1, HologramsType5.PUZZLE, 5, 0, 5, 1);
      method2(var1, HologramsType5.PUZZLE, 3, 1, 4, 1);
      method2(var1, HologramsType5.FAIRY, 4, 1, 5, 1);
      method2(var1, HologramsType5.CLEAR, 0, 1, 0, 2);
      method2(var1, HologramsType5.MINIBOSS, 0, 2, 1, 2);
      method2(var1, HologramsType5.PUZZLE, 2, 1, 2, 2);
      method2(var1, HologramsType5.CLEAR, 2, 2, 3, 2);
      method2(var1, HologramsType5.CLEAR, 3, 2, 4, 2);
      method2(var1, HologramsType5.CLEAR, 4, 2, 5, 2);
      method2(var1, HologramsType5.CLEAR, 0, 3, 1, 3);
      method2(var1, HologramsType5.CLEAR, 2, 3, 3, 3);
      method2(var1, HologramsType5.CLEAR, 4, 3, 5, 3);
      method2(var1, HologramsType5.CLEAR, 3, 3, 3, 4);
      method2(var1, HologramsType5.CLEAR, 4, 3, 4, 4);
      method2(var1, HologramsType5.CLEAR, 1, 4, 2, 4);
      method2(var1, HologramsType5.TRAP, 1, 4, 1, 5);
      method2(var1, HologramsType5.CLEAR, 4, 4, 5, 4);
      method2(var1, HologramsType5.CLEAR, 3, 5, 4, 5);
      Collection var10 = var1.getPlayers();
      method3(var1, var10, "Dulkir", field6, HologramsType2_2.HEALER, field5[0]);
      method3(var1, var10, "Soopyboo32", field7, HologramsType2_2.MAGE, field5[1]);
      method3(var1, var10, "NoticeMehSenpai", field8, HologramsType2_2.TANK, field5[2]);
      Holograms4Updater var12 = method3(var1, var10, "mlgboi", field9, HologramsType2_2.BERSERK, field5[3]);
      var12.method9("mlgboi", "DEAD", null);
      Holograms4Updater var13 = new Holograms4Updater(null, var1, null, field1);
      var13.method9("Player", HologramsType2_2.ARCHER.getChatDisplayName(), "L");
      method4(var13, 2, 3);
      var13.method5(35.0, 0L);
      var10.add(var13);
      return var1;
   }

   private static void method2(Holograms2_5 var0, HologramsType5 var1, int var2, int var3, int var4, int var5) {
      Nameplate4 var6 = Nameplate4.method1(var2, var3, var0);
      Nameplate4 var7 = Nameplate4.method1(var4, var5, var0);
      var0.method22(new Rewindhandlers(var0, var1, var6, var7));
   }

   private static Holograms4Updater method3(Holograms2_5 var0, Collection<Holograms4Updater> var1, String var2, UUID var3, HologramsType2_2 var4, int[] var5) {
      Holograms4Updater var6 = new Holograms4Updater(null, var0, var3, field1);
      var6.method9(var2, var4.getChatDisplayName(), "L");
      method4(var6, var5[0], var5[1]);
      var1.add(var6);
      return var6;
   }

   private static void method4(Holograms4Updater var0, int var1, int var2) {
      double var3 = (var1 + 0.5) * 32.0 + Holograms2_5.field7.x();
      double var5 = (var2 + 0.5) * 32.0 + Holograms2_5.field7.y();
      var0.method30().method4(var3);
      var0.method30().method5(var5);
   }

   private Holograms2_2() {
   }
}
