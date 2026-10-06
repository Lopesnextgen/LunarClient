package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairEditorWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.DungeonWaypointCodec;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption.Data;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class DungeonWaypointShareScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private static final String field19 = "boss-floor-";
   private static final String field20 = "~";
   private TitledWidget field21;
   private final MultiSelectOption field22;
   private final CrosshairEditorWidget field23;
   private final TextLabelWidget field24;
   private final TextLabelWidget field25;
   private final TextLabelWidget field26;
   private final TextLabelWidget field27;
   private final TextLabelWidget field28;
   private final TextLabelWidget field29;
   private final ProgressBarWidget field30;
   private final DungeonWaypoints field31;
   private final Map<String, String> field32 = new LinkedHashMap<>();
   private final Map<String, String> field33 = new HashMap<>();
   private final float field34 = 240.0F;

   public static void method1(DungeonWaypoints dungeonwaypoints0) {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreen();
      DungeonWaypointShareScreen bridge7iterator2 = new DungeonWaypointShareScreen(bridge5extension61, dungeonwaypoints0);
      if (Ref.method8() == null) {
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new MainMenuButton(bridge7iterator2)));
      } else {
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7iterator2));
      }
   }

   public DungeonWaypointShareScreen(GuiScreenBridge bridge5extension61, DungeonWaypoints dungeonwaypoints2) {
      this.field31 = dungeonwaypoints2;
      DungeonRoom[] items3 = Ref.method4().method40().method82().method15().method12();
      if (items3 != null) {
         for (DungeonRoom holograms7 : items3) {
            String text8 = holograms7.communityName() != null ? holograms7.communityName() : holograms7.getBlcID();
            this.field32.put(holograms7.getBlcID(), text8);
            if (holograms7.communityName() != null) {
               this.field33.put(holograms7.communityName(), holograms7.getBlcID());
            }
         }
      }

      for (DungeonRoomWaypoints dungeonwaypoints311 : dungeonwaypoints2.method49().method13()) {
         this.field32.putIfAbsent(this.method2(dungeonwaypoints311), dungeonwaypoints311.communityName() != null ? dungeonwaypoints311.communityName() : dungeonwaypoints311.method3());
      }

      for (int index10 = 1; index10 <= 7; index10++) {
         this.field32.put("boss-floor-" + index10, this.method2("bossFloor", new Object[]{index10}));
      }

      this.field22 = (MultiSelectOption)((Data)OptionFactory.method27("dungeonWaypointRoomSelect")
            .method3(this.field32.keySet())
            .HIIIOHRRROCICIOIORRRIRCRCHHIII(new LinkedHashSet<>(this.field32.keySet())))
         .method6(arg1x -> this.field32.getOrDefault(arg1x, arg1x))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field21
         .RCRHIIIOHIRHRCORCCCIRRRICHIHRC(
            ImmutableList.of(
               this.field23 = new CrosshairEditorWidget(this.field22, this.field21),
               this.field24 = new TextLabelWidget(this.field21, this.method2("selectAll", new Object[0])),
               this.field25 = new TextLabelWidget(this.field21, this.method2("deselectAll", new Object[0])),
               this.field26 = new TextLabelWidget(this.field21, this.method2("exportString", new Object[0])),
               this.field27 = new TextLabelWidget(this.field21, this.method2("importString", new Object[0])),
               this.field28 = new TextLabelWidget(this.field21, this.method2("exportZip", new Object[0])),
               this.field29 = new TextLabelWidget(this.field21, this.method2("importZip", new Object[0])),
               this.field30 = new ProgressBarWidget(this.field21, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png"))
            )
         );
      this.field24.method4((arg1x, arg2x) -> {
         this.field22.method1(new LinkedHashSet<>(this.field32.keySet()));
         return true;
      });
      this.field25.method4((arg1x, arg2x) -> {
         this.field22.method1(new LinkedHashSet());
         return true;
      });
      this.field26.method4((arg1x, arg2x) -> {
         try {
            ClipboardUtils.method2(DungeonWaypointCodec.method1(this.method5(), this.method6()));
            this.method7("dungeonWaypointCopied");
         } catch (Exception exception4) {
            LunarLogger.error("Failed to export dungeon waypoints to string", exception4);
            this.method7("dungeonWaypointExportFailed");
         }

         return true;
      });
      this.field27.method4((arg1x, arg2x) -> {
         this.method6(DungeonWaypointCodec.method2(ClipboardUtils.method1()));
         return true;
      });
      this.field28
         .method4(
            (arg1x, arg2x) -> {
               File file3x = Gui4.method9(
                  this.method2("exportZip", new Object[0]),
                  new File(
                     com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.DungeonWaypointManager.field2, "dungeon-waypoints.zip"
                  ),
                  "Zip archive",
                  "zip"
               );
               if (file3x == null) {
                  return true;
               }

               try {
                  DungeonWaypointCodec.method4(file3x, this.method5(), this.method6(), Ref.method4().method61().method14().getName());
                  this.method7("dungeonWaypointExportedZip");
               } catch (Exception exception5) {
                  LunarLogger.error("Failed to export dungeon waypoints zip", exception5);
                  this.method7("dungeonWaypointExportFailed");
               }

               return true;
            }
         );
      this.field29
         .method4(
            (arg1x, arg2x) -> {
               File file3x = Gui4.method7(
                  this.method2("importZip", new Object[0]),
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.DungeonWaypointManager.field2,
                  "Zip archive",
                  "zip"
               );
               if (file3x == null) {
                  return true;
               }

               this.method6(DungeonWaypointCodec.method5(file3x));
               return true;
            }
         );
      this.field30.method4((arg1x, arg2x) -> {
         Ref.method3().bridge$displayScreen(bridge5extension61);
         return true;
      });
   }

   private String method2(DungeonRoomWaypoints dungeonwaypoints31) {
      if (dungeonwaypoints31.method3() != null) {
         return dungeonwaypoints31.method3();
      }

      String text2 = this.field33.get(dungeonwaypoints31.communityName());
      return text2 != null ? text2 : "~" + dungeonwaypoints31.communityName();
   }

   private boolean method3(String text1) {
      return !this.field32.containsKey(text1) || this.field22.contains(text1);
   }

   private List<DungeonRoomWaypoints> method5() {
      ArrayList list1 = new ArrayList();

      for (DungeonRoomWaypoints dungeonwaypoints33 : this.field31.method49().method13()) {
         if (this.method3(this.method2(dungeonwaypoints33))) {
            list1.add(dungeonwaypoints33);
         }
      }

      return list1;
   }

   private List<DungeonFloorWaypoints> method6() {
      ArrayList list1 = new ArrayList();

      for (DungeonFloorWaypoints dungeonwaypoints23 : this.field31.method49().method19()) {
         if (this.method3("boss-floor-" + dungeonwaypoints23.method3())) {
            list1.add(dungeonwaypoints23);
         }
      }

      return list1;
   }

   private void method6(DungeonWaypointCodec.Data data1) {
      if (data1 == null) {
         this.method7("dungeonWaypointImportFailed");
      } else {
         int index2 = 0;

         for (DungeonRoomWaypoints dungeonwaypoints34 : data1.method1()) {
            if (this.method3(this.method2(dungeonwaypoints34))) {
               this.field31.method49().method10(dungeonwaypoints34);
               index2++;
            }
         }

         for (DungeonFloorWaypoints dungeonwaypoints26 : data1.method2()) {
            if (DungeonFloor.isValidBossFloor(dungeonwaypoints26.method3()) && this.method3("boss-floor-" + dungeonwaypoints26.method3())) {
               this.field31.method49().method18(dungeonwaypoints26);
               index2++;
            }
         }

         Ref.method4().method69().method3(NotificationManager.method15("dungeonWaypointImported", new Object[]{index2}));
      }
   }

   private void method7(String text1) {
      Ref.method4().method69().method3(NotificationManager.method15(text1, new Object[0]));
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field21 = new TitledWidget(null, this.method2("shareTitle", new Object[0])) {
         public void method3(float value1, float value2, float value3, float value4) {
            float value5 = 12.0F;
            float value6 = DungeonWaypointShareScreen.this.field25.method6().method4(DungeonWaypointShareScreen.this.field25.method5()) + value5;
            float value7 = DungeonWaypointShareScreen.this.field24.method6().method4(DungeonWaypointShareScreen.this.field24.method5()) + value5;
            DungeonWaypointShareScreen.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + value3 - 8.0F - value6, value2 + 27.0F, value6, 13.0F);
            DungeonWaypointShareScreen.this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + value3 - 12.0F - value6 - value7, value2 + 27.0F, value7, 13.0F);
            DungeonWaypointShareScreen.this.field23.method2(value1 + 8.0F, value2 + 43.0F, value3 - 16.0F, 109.0F);
            float value8 = (value3 - 24.0F) / 2.0F;
            float value9 = 20.0F;
            float value10 = value1 + 8.0F;
            float value11 = value1 + 16.0F + value8;
            float value12 = value2 + 160.0F;
            float value13 = value12 + value9 + 6.0F;
            DungeonWaypointShareScreen.this.field26.RIIICIRHRCIHOOOORHOICRIICCCRHR(value10, value12, value8, value9);
            DungeonWaypointShareScreen.this.field27.RIIICIRHRCIHOOOORHOICRIICCCRHR(value11, value12, value8, value9);
            DungeonWaypointShareScreen.this.field28.RIIICIRHRCIHOOOORHOICRIICCCRHR(value10, value13, value8, value9);
            DungeonWaypointShareScreen.this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(value11, value13, value8, value9);
            DungeonWaypointShareScreen.this.field30.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 6.0F, value2 + 240.0F - 24.0F, 18.0F, 18.0F);
         }
      });
   }

   public void init() {
      float value1 = 340.0F;
      this.field21.method2(this.method22() / 2.0F - value1 / 2.0F, this.method23() / 2.0F - 120.0F, value1, 240.0F);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
   }

   public void method11(Data2 data21, int number2) {
   }

   public void method12(Data2 data21, int number2) {
   }

   public void method14(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".dungeonWaypointShare";
   }
}
