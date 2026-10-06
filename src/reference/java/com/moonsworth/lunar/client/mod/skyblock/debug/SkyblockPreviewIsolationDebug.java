package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.ContainerOverlayPreviewMode;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockPreviewIsolationDebug extends AbstractFeature {
   private static final String field8 = "previewIsolationSnapshot.json";
   private static final String field9 = "SKYBLOCK_TAB_WIDGET_";
   private static final Set<String> field10 = Set.of(
      "SKYBLOCK_CHOCOLATE_SHOP_HELPER",
      "SKYBLOCK_CHOCOLATE_STATS",
      "SKYBLOCK_RABBIT_COLLECTION_OVERLAY",
      "BETTERMAP_PRIMARY",
      "BETTERMAP_SECONDARY",
      "SKYBLOCK_DRAGON_FEATURES",
      "SKYBLOCK_DUNGEON_BLESSING_HUD",
      "SKYBLOCK_DUNGEON_MILESTONE_HUD",
      "SKYBLOCK_TICK_TIMER_HUD",
      "SKYBLOCK_FLOOR_FOUR",
      "SKYBLOCK_INVINCIBILITY_HUD",
      "SKYBLOCK_SKELETON_HELMET_HUD",
      "SKYBLOCK_STORM_HUD",
      "SKYBLOCK_DUNGEON_TIMER",
      "SKYBLOCK_COMPOSTER_HUD",
      "SKYBLOCK_CROP_TRACKER_HUD",
      "SKYBLOCK_FARMING_HUD",
      "SKYBLOCK_GARDEN_PESTS",
      "SKYBLOCK_HARVEST_FEAST_TRACKER_HUD",
      "SKYBLOCK_VISITOR_SHOPPING_LIST_HUD",
      "SKYBLOCK_VISITOR_TRACKER_HUD",
      "SKYBLOCK_VISITOR_HUD",
      "SKYBLOCK_FISHING_BAIT_HUD",
      "SKYBLOCK_FISHING_INFO_HUD",
      "SKYBLOCK_FISHING_REEL_TIMER_HUD",
      "SKYBLOCK_SPIDER_DEN_RAIN_HUD",
      "SKYBLOCK_TROPHY_FISH_EXCHANGE_RATE",
      "SKYBLOCK_WHISPER_TRACKER_HUD",
      "SKYBLOCK_CHEST_PROFIT",
      "SKYBLOCK_ARROW_POISON_HUD",
      "SKYBLOCK_BITS_SHOP_HELPER",
      "SKYBLOCK_ENHANCED_MINION_MENU",
      "SKYBLOCK_QUIVER_HUD",
      "SKYBLOCK_SKYMART_HELPER",
      "SKYBLOCK_CRYSTAL_HOLLOWS_MAP",
      "SKYBLOCK_POWDER_TRACKER_HUD",
      "SKYBLOCK_SCATHA_TRACKER_HUD",
      "SKYBLOCK_NETHER_BOSS_HUD",
      "SKYBLOCK_RAFFLE_TASKS_HUD",
      "SKYBLOCK_SKILL_PROGRESS_HUD",
      "SKYBLOCK_SKILL_XP_TRACKER_HUD",
      "SKYBLOCK_BLAZE_SLAYER",
      "SKYBLOCK_ENDERMAN_SLAYER",
      "SKYBLOCK_SPEED_HUD"
   );

   public SkyblockPreviewIsolationDebug(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method7(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_PREVIEW_ISOLATION_DEBUG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("isolatePreviews").method4(this::method13)});
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("restoreSnapshot").method4(this::method14)});
   }

   private boolean method2(Framework7Extension framework7extension1) {
      String text2 = framework7extension1.getId();
      return field10.contains(text2) || text2.startsWith("SKYBLOCK_TAB_WIDGET_");
   }

   private List<Framework7Extension> method3(Skyblock skyblock1) {
      ArrayList list2 = new ArrayList();
      list2.add(skyblock1);
      skyblock1.method3(ModTraits.field5).ifPresent(arg1x -> arg1x.HROOOICICRCOCIROHIRICCCOCCIORH(arg1xx -> list2.add(arg1xx)));
      return list2;
   }

   private void method4(Framework7Extension framework7extension1, boolean flag2) {
      framework7extension1.method3(ModTraits.field6).ifPresent(arg1x -> arg1x.setEnabled(flag2));
   }

   private void method13() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      List list2 = this.method3(skyblock1);
      SkyblockPreviewIsolationDebug.Data data3 = new SkyblockPreviewIsolationDebug.Data();

      for (Framework7Extension framework7extension5 : list2) {
         framework7extension5.method3(ModTraits.field6).ifPresent(arg2x -> data3.field1.put(framework7extension5.getId(), arg2x.isEnabled()));
      }

      data3.field2 = (ContainerOverlayPreviewMode)skyblock1.method34().get();
      skyblock1.method15().method5("previewIsolationSnapshot.json", data3);
      skyblock1.method34().OIRHOOIICOCIOOHICRRRICORIHHIHC(ContainerOverlayPreviewMode.ALWAYS);
      HashSet set9 = new HashSet();
      set9.add(skyblock1);

      for (Framework7Extension framework7extension6 : list2) {
         if (this.method2(framework7extension6)) {
            Framework7Extension framework7extension7 = framework7extension6;

            while (framework7extension7 != null) {
               set9.add(framework7extension7);
               ChildModBinding framework48 = (ChildModBinding)framework7extension7.method1(ModTraits.field16);
               framework7extension7 = framework48 == null ? null : framework48.method1();
            }
         }
      }

      int index11 = 0;
      int index12 = 0;

      for (Framework7Extension framework7extension14 : list2) {
         if (set9.contains(framework7extension14)) {
            this.method4(framework7extension14, true);
            index11++;
         } else if (framework7extension14.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field1)) {
            this.method4(framework7extension14, false);
            index12++;
         }
      }

      Ref.method4().method69().method2("Previews isolated", index11 + " kept on, " + index12 + " HUDs off. Snapshot saved.");
   }

   private void method14() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      SkyblockPreviewIsolationDebug.Data data2 = (SkyblockPreviewIsolationDebug.Data)skyblock1.method15()
         .method4("previewIsolationSnapshot.json", SkyblockPreviewIsolationDebug.Data.class);
      if (data2 != null && data2.field1 != null) {
         int index3 = 0;

         for (Framework7Extension framework7extension5 : this.method3(skyblock1)) {
            Boolean flag6 = data2.field1.get(framework7extension5.getId());
            if (flag6 != null) {
               this.method4(framework7extension5, flag6);
               index3++;
            }
         }

         if (data2.field2 != null) {
            skyblock1.method34().OIRHOOIICOCIOOHICRRRICORIHHIHC(data2.field2);
         }

         Ref.method4().method69().method2("Snapshot restored", index3 + " features returned to their saved state.");
      } else {
         Ref.method4().method69().method2("Nothing to restore", "Run \"Isolate previews\" first to save a snapshot.");
      }
   }

   private static final class Data {
      Map<String, Boolean> field1 = new LinkedHashMap<>();
      ContainerOverlayPreviewMode field2;

      private Data() {
      }
   }
}
