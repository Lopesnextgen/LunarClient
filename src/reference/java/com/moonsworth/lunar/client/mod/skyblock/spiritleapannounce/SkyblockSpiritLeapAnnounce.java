package com.moonsworth.lunar.client.mod.skyblock.spiritleapannounce;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSpiritLeapAnnounce extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^You have teleported to (?<username>\\w+)!$");
   private final DungeonMapListener field9 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final TextOption field10 = (TextOption)((Data)OptionFactory.method12("leapMessage")
         .method2("Leaped to {username}!"))
      .method3(250)
      .method31();

   public SkyblockSpiritLeapAnnounce(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Matcher matcher2 = field8.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (matcher2.matches()) {
         DungeonStateTracker holograms2_53 = (DungeonStateTracker)this.field9.method5().orElse(null);
         if (holograms2_53 != null && this.field9.method12()) {
            String text4 = matcher2.group("username");
            String text5 = ((String)this.field10.get()).replace("{username}", text4);
            DungeonPlayerTracker holograms4updater6 = (DungeonPlayerTracker)holograms2_53.method31(text4).orElse(null);
            if (holograms4updater6 != null) {
               DungeonClass hologramstype2_27 = holograms4updater6.method37();
               if (hologramstype2_27 != null) {
                  text5 = text5.replace("{class}", hologramstype2_27.getChatDisplayName()).replace("{classletter}", hologramstype2_27.getFirstLetter() + "");
               }
            }

            ChatMessageQueue.method1("/pc " + text5);
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field10}));
   }

   public String getId() {
      return "SKYBLOCK_SPIRIT_LEAP_ANNOUNCE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
