package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public final class DungeonRoomRegistry {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");
   private static final String[][] field2 = new String[][]{
      {"CHAMBERS", "BLOOD", "SLIME", "SLIME", "SLIME", "ICEFILL"},
      {"CHAMBERS", "CHAMBERS", "HOL", "TTT", "FAIRY", "DIAGONAL"},
      {"PIPES", "MINIBOSS", "STAIRCASE", "MIRROR", "CHAINS", "DIAGONAL"},
      {"PIPES", "PLATES", "PLATES", "PAINTING", "BASEMENT", "DIAGONAL"},
      {"PIPES", "SKULLS", "SUPERTALL", "SUPERTALL", "SPIDER", "SPAWN"},
      {"PIPES", "TRAP", "SUPERTALL", "SUPERTALL", "SPIDER", "SPIDER"}
   };
   private static final Map<String, MapRoomType> field3 = Map.of(
      "SPAWN",
      MapRoomType.SPAWN,
      "BLOOD",
      MapRoomType.BLOOD,
      "FAIRY",
      MapRoomType.FAIRY,
      "TRAP",
      MapRoomType.TRAP,
      "MINIBOSS",
      MapRoomType.MINIBOSS,
      "HOL",
      MapRoomType.PUZZLE,
      "TTT",
      MapRoomType.PUZZLE,
      "ICEFILL",
      MapRoomType.PUZZLE
   );
   private static final Map<String, DungeonRoomPreset> field4 = Map.ofEntries(
      Map.entry(
         "CHAMBERS",
         new DungeonRoomPreset("Chambers", 5, 5, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "BLOOD", new DungeonRoomPreset(null, 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "SLIME", new DungeonRoomPreset("Slime", 5, 5, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "ICEFILL",
         new DungeonRoomPreset("Ice Fill", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "HOL",
         new DungeonRoomPreset("Higher Or Lower", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "TTT", new DungeonRoomPreset("Tic Tac Toe", 1, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.FAILED)
      ),
      Map.entry(
         "FAIRY", new DungeonRoomPreset("Fairy", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "DIAGONAL",
         new DungeonRoomPreset("Diagonal", 4, 2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED)
      ),
      Map.entry(
         "PIPES", new DungeonRoomPreset("Pipes", 7, 7, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "MINIBOSS",
         new DungeonRoomPreset("Miniboss", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "STAIRCASE",
         new DungeonRoomPreset("Staircase", 3, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED)
      ),
      Map.entry(
         "MIRROR", new DungeonRoomPreset("Mirror", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "CHAINS",
         new DungeonRoomPreset("Overgrown Chains", 2, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED)
      ),
      Map.entry(
         "PLATES",
         new DungeonRoomPreset("Pressure Plates", 6, 6, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "PAINTING",
         new DungeonRoomPreset("Painting", 2, 2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "BASEMENT",
         new DungeonRoomPreset("Basement", 1, 1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "SKULLS",
         new DungeonRoomPreset("Blue Skulls", 1, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED)
      ),
      Map.entry(
         "SUPERTALL",
         new DungeonRoomPreset("Supertall", 6, 6, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "SPIDER", new DungeonRoomPreset("Spider", 9, 3, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED)
      ),
      Map.entry(
         "TRAP", new DungeonRoomPreset("Old Trap", 4, 4, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      ),
      Map.entry(
         "SPAWN", new DungeonRoomPreset("Entrance", 0, 0, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED)
      )
   );
   private static final int[][] field5 = new int[][]{{3, 2}, {5, 4}, {1, 2}, {2, 4}};
   private static final UUID field6 = UUID.fromString("e23e44f6-05f2-4f23-b65e-51b449dd0c0d");
   private static final UUID field7 = UUID.fromString("dc8c3964-7b29-4e03-ae9e-d13ebd65dd29");
   private static final UUID field8 = UUID.fromString("63401220-16bb-4432-bb9a-089198355a2a");
   private static final UUID field9 = UUID.fromString("1f8e65d2-a194-4632-bff4-b4f6b02d9567");

   public static DungeonStateTracker method1(boolean flag0) {
      TestDungeonStateTracker holograms2$data21 = new TestDungeonStateTracker(flag0);
      HashMap map2 = new HashMap();

      for (int index3 = 0; index3 < field2.length; index3++) {
         for (int index4 = 0; index4 < field2[index3].length; index4++) {
            map2.computeIfAbsent(field2[index3][index4], arg0x -> new ArrayList<>()).add(new int[]{index4, index3});
         }
      }

      for (Entry entry11 : map2.entrySet()) {
         MapRoomType hologramstype55 = field3.getOrDefault(entry11.getKey(), MapRoomType.CLEAR);
         DungeonRoomTracker holograms4iterator6 = new DungeonRoomTracker(holograms2$data21, hologramstype55);

         for (int[] items8 : (List)entry11.getValue()) {
            holograms4iterator6.method5(WorldPosition.method1(items8[0], items8[1], holograms2$data21));
         }

         DungeonRoomPreset holograms2$data14 = field4.get(entry11.getKey());
         if (holograms2$data14.name() != null) {
            holograms4iterator6.method30().setDisplayName(holograms2$data14.name());
         }

         holograms4iterator6.method30().method17(holograms2$data14.method1());
         holograms4iterator6.method30().method10(holograms2$data14.method2());
         holograms4iterator6.method30().method11(holograms2$data14.method3());
      }

      method2(holograms2$data21, MapRoomType.BLOOD, 1, 0, 2, 0);
      method2(holograms2$data21, MapRoomType.CLEAR, 4, 0, 4, 1);
      method2(holograms2$data21, MapRoomType.PUZZLE, 5, 0, 5, 1);
      method2(holograms2$data21, MapRoomType.PUZZLE, 3, 1, 4, 1);
      method2(holograms2$data21, MapRoomType.FAIRY, 4, 1, 5, 1);
      method2(holograms2$data21, MapRoomType.CLEAR, 0, 1, 0, 2);
      method2(holograms2$data21, MapRoomType.MINIBOSS, 0, 2, 1, 2);
      method2(holograms2$data21, MapRoomType.PUZZLE, 2, 1, 2, 2);
      method2(holograms2$data21, MapRoomType.CLEAR, 2, 2, 3, 2);
      method2(holograms2$data21, MapRoomType.CLEAR, 3, 2, 4, 2);
      method2(holograms2$data21, MapRoomType.CLEAR, 4, 2, 5, 2);
      method2(holograms2$data21, MapRoomType.CLEAR, 0, 3, 1, 3);
      method2(holograms2$data21, MapRoomType.CLEAR, 2, 3, 3, 3);
      method2(holograms2$data21, MapRoomType.CLEAR, 4, 3, 5, 3);
      method2(holograms2$data21, MapRoomType.CLEAR, 3, 3, 3, 4);
      method2(holograms2$data21, MapRoomType.CLEAR, 4, 3, 4, 4);
      method2(holograms2$data21, MapRoomType.CLEAR, 1, 4, 2, 4);
      method2(holograms2$data21, MapRoomType.TRAP, 1, 4, 1, 5);
      method2(holograms2$data21, MapRoomType.CLEAR, 4, 4, 5, 4);
      method2(holograms2$data21, MapRoomType.CLEAR, 3, 5, 4, 5);
      Collection list10 = holograms2$data21.getPlayers();
      method3(holograms2$data21, list10, "Dulkir", field6, DungeonClass.HEALER, field5[0]);
      method3(holograms2$data21, list10, "Soopyboo32", field7, DungeonClass.MAGE, field5[1]);
      method3(holograms2$data21, list10, "NoticeMehSenpai", field8, DungeonClass.TANK, field5[2]);
      DungeonPlayerTracker holograms4updater12 = method3(holograms2$data21, list10, "mlgboi", field9, DungeonClass.BERSERK, field5[3]);
      holograms4updater12.method9("mlgboi", "DEAD", null);
      DungeonPlayerTracker holograms4updater13 = new DungeonPlayerTracker(null, holograms2$data21, null, field1);
      holograms4updater13.method9("Player", DungeonClass.ARCHER.getChatDisplayName(), "L");
      method4(holograms4updater13, 2, 3);
      holograms4updater13.method5(35.0, 0L);
      list10.add(holograms4updater13);
      return holograms2$data21;
   }

   private static void method2(DungeonStateTracker holograms2_50, MapRoomType hologramstype51, int number2, int number3, int number4, int number5) {
      WorldPosition nameplate46 = WorldPosition.method1(number2, number3, holograms2_50);
      WorldPosition nameplate47 = WorldPosition.method1(number4, number5, holograms2_50);
      holograms2_50.method22(new RoomStateHistory(holograms2_50, hologramstype51, nameplate46, nameplate47));
   }

   private static DungeonPlayerTracker method3(DungeonStateTracker holograms2_50, Collection<DungeonPlayerTracker> list1, String text2, UUID uuid3, DungeonClass hologramstype2_24, int[] items5) {
      DungeonPlayerTracker holograms4updater6 = new DungeonPlayerTracker(null, holograms2_50, uuid3, field1);
      holograms4updater6.method9(text2, hologramstype2_24.getChatDisplayName(), "L");
      method4(holograms4updater6, items5[0], items5[1]);
      list1.add(holograms4updater6);
      return holograms4updater6;
   }

   private static void method4(DungeonPlayerTracker holograms4updater0, int number1, int number2) {
      double value3 = (number1 + 0.5) * 32.0 + DungeonStateTracker.field7.x();
      double value5 = (number2 + 0.5) * 32.0 + DungeonStateTracker.field7.y();
      holograms4updater0.method30().method4(value3);
      holograms4updater0.method30().method5(value5);
   }

   private DungeonRoomRegistry() {
   }
}
