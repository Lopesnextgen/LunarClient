package com.moonsworth.lunar.client.mod.skyblock.bosstimer;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent.Data;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent.SlayerBossSpawnEvent;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TimeFormatting.Type;

public class SkyblockBossTimer extends AbstractFeature {
   private long field8 = -1L;
   private String field9 = "";

   public SkyblockBossTimer(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SlayerBossSpawnEvent.class, this::method1);
      this.handle(Data.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_BOSS_TIMER";
   }

   private void method1(SlayerBossSpawnEvent data31) {
      this.field8 = data31.getTimestamp();
      this.field9 = data31.method1();
   }

   private void method2(Data data1) {
      if (this.field9.equals(data1.method1())) {
         String text2 = Type.STOPWATCH.format(data1.getTimestamp() - this.field8);
         SkyBlockChat.method1("Slayer boss took " + text2 + " to kill.");
      }
   }
}
