package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairEditorWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints5;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption.Data;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Bridge7Iterator extends com.moonsworth.lunar.client.ui.LcuiScreen {
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

   public static void method1(DungeonWaypoints var0) {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      Bridge7Iterator var2 = new Bridge7Iterator(var1, var0);
      if (ThreadModuleDump63.method8() == null) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new MainMenuButton(var2)));
      } else {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var2));
      }
   }

   public Bridge7Iterator(Bridge5Extension6 var1, DungeonWaypoints var2) {
      this.field31 = var2;
      Holograms[] var3 = ThreadModuleDump63.method4().method40().method82().method15().method12();
      if (var3 != null) {
         for (Holograms var7 : var3) {
            String var8 = var7.communityName() != null ? var7.communityName() : var7.getBlcID();
            this.field32.put(var7.getBlcID(), var8);
            if (var7.communityName() != null) {
               this.field33.put(var7.communityName(), var7.getBlcID());
            }
         }
      }

      for (Dungeonwaypoints3 var11 : var2.method49().method13()) {
         this.field32.putIfAbsent(this.method2(var11), var11.communityName() != null ? var11.communityName() : var11.method3());
      }

      for (int var10 = 1; var10 <= 7; var10++) {
         this.field32.put("boss-floor-" + var10, this.method2("bossFloor", new Object[]{var10}));
      }

      this.field22 = (MultiSelectOption)((Data)OptionFactory.method27("dungeonWaypointRoomSelect")
            .method3(this.field32.keySet())
            .method2(new LinkedHashSet<>(this.field32.keySet())))
         .method6(var1x -> this.field32.getOrDefault(var1x, var1x))
         .method31();
      this.field21
         .method14(
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
      this.field24.method4((var1x, var2x) -> {
         this.field22.method1(new LinkedHashSet<>(this.field32.keySet()));
         return true;
      });
      this.field25.method4((var1x, var2x) -> {
         this.field22.method1(new LinkedHashSet());
         return true;
      });
      this.field26.method4((var1x, var2x) -> {
         try {
            ThreadModuleDump68.setClipboardString(Dungeonwaypoints5.method1(this.method5(), this.method6()));
            this.method7("dungeonWaypointCopied");
         } catch (Exception var4) {
            Slayer.error("Failed to export dungeon waypoints to string", var4);
            this.method7("dungeonWaypointExportFailed");
         }

         return true;
      });
      this.field27.method4((var1x, var2x) -> {
         this.method6(Dungeonwaypoints5.method2(ThreadModuleDump68.getClipboardString()));
         return true;
      });
      this.field28
         .method4(
            (var1x, var2x) -> {
               File var3x = Gui4.method9(
                  this.method2("exportZip", new Object[0]),
                  new File(
                     com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints3.field2, "dungeon-waypoints.zip"
                  ),
                  "Zip archive",
                  "zip"
               );
               if (var3x == null) {
                  return true;
               }

               try {
                  Dungeonwaypoints5.method4(var3x, this.method5(), this.method6(), ThreadModuleDump63.method4().method61().method14().getName());
                  this.method7("dungeonWaypointExportedZip");
               } catch (Exception var5) {
                  Slayer.error("Failed to export dungeon waypoints zip", var5);
                  this.method7("dungeonWaypointExportFailed");
               }

               return true;
            }
         );
      this.field29
         .method4(
            (var1x, var2x) -> {
               File var3x = Gui4.method7(
                  this.method2("importZip", new Object[0]),
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints3.field2,
                  "Zip archive",
                  "zip"
               );
               if (var3x == null) {
                  return true;
               }

               this.method6(Dungeonwaypoints5.method5(var3x));
               return true;
            }
         );
      this.field30.method4((var1x, var2x) -> {
         ThreadModuleDump63.method3().bridge$displayScreen(var1);
         return true;
      });
   }

   private String method2(Dungeonwaypoints3 var1) {
      if (var1.method3() != null) {
         return var1.method3();
      }

      String var2 = this.field33.get(var1.communityName());
      return var2 != null ? var2 : "~" + var1.communityName();
   }

   private boolean method3(String var1) {
      return !this.field32.containsKey(var1) || this.field22.contains(var1);
   }

   private List<Dungeonwaypoints3> method5() {
      ArrayList var1 = new ArrayList();

      for (Dungeonwaypoints3 var3 : this.field31.method49().method13()) {
         if (this.method3(this.method2(var3))) {
            var1.add(var3);
         }
      }

      return var1;
   }

   private List<Dungeonwaypoints2> method6() {
      ArrayList var1 = new ArrayList();

      for (Dungeonwaypoints2 var3 : this.field31.method49().method19()) {
         if (this.method3("boss-floor-" + var3.method3())) {
            var1.add(var3);
         }
      }

      return var1;
   }

   private void method6(Dungeonwaypoints5.Data var1) {
      if (var1 == null) {
         this.method7("dungeonWaypointImportFailed");
      } else {
         int var2 = 0;

         for (Dungeonwaypoints3 var4 : var1.method1()) {
            if (this.method3(this.method2(var4))) {
               this.field31.method49().method10(var4);
               var2++;
            }
         }

         for (Dungeonwaypoints2 var6 : var1.method2()) {
            if (HighlightType.isValidBossFloor(var6.method3()) && this.method3("boss-floor-" + var6.method3())) {
               this.field31.method49().method18(var6);
               var2++;
            }
         }

         ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("dungeonWaypointImported", new Object[]{var2}));
      }
   }

   private void method7(String var1) {
      ThreadModuleDump63.method4().method69().method3(NotificationManager.method15(var1, new Object[0]));
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field21 = new TitledWidget(null, this.method2("shareTitle", new Object[0])) {
         public void method3(float var1, float var2, float var3, float var4) {
            float var5 = 12.0F;
            float var6 = Bridge7Iterator.this.field25.method6().method4(Bridge7Iterator.this.field25.method5()) + var5;
            float var7 = Bridge7Iterator.this.field24.method6().method4(Bridge7Iterator.this.field24.method5()) + var5;
            Bridge7Iterator.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 8.0F - var6, var2 + 27.0F, var6, 13.0F);
            Bridge7Iterator.this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 12.0F - var6 - var7, var2 + 27.0F, var7, 13.0F);
            Bridge7Iterator.this.field23.method2(var1 + 8.0F, var2 + 43.0F, var3 - 16.0F, 109.0F);
            float var8 = (var3 - 24.0F) / 2.0F;
            float var9 = 20.0F;
            float var10 = var1 + 8.0F;
            float var11 = var1 + 16.0F + var8;
            float var12 = var2 + 160.0F;
            float var13 = var12 + var9 + 6.0F;
            Bridge7Iterator.this.field26.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10, var12, var8, var9);
            Bridge7Iterator.this.field27.RIIICIRHRCIHOOOORHOICRIICCCRHR(var11, var12, var8, var9);
            Bridge7Iterator.this.field28.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10, var13, var8, var9);
            Bridge7Iterator.this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(var11, var13, var8, var9);
            Bridge7Iterator.this.field30.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 6.0F, var2 + 240.0F - 24.0F, 18.0F, 18.0F);
         }
      });
   }

   public void init() {
      float var1 = 340.0F;
      this.field21.method2(this.method22() / 2.0F - var1 / 2.0F, this.method23() / 2.0F - 120.0F, var1, 240.0F);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
   }

   public void method11(Data2 var1, int var2) {
   }

   public void method12(Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public void close() {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".dungeonWaypointShare";
   }
}
