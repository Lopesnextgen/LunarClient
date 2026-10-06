package com.moonsworth.lunar.client.mod.skyblock.chestlooted;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockChestLooted extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ {2}(WOOD|GOLD|DIAMOND|EMERALD|OBSIDIAN|BEDROCK) CHEST REWARDS$");
   private static final Pattern field9 = Pattern.compile("^Crimson Essence x\\d+$");
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("announceDungeonChest").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("announceKuudraChest").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("sendInPartyChat").method4(true))
      .method31();
   private boolean field13;

   public SkyblockChestLooted(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.CHAT));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(TypedChatMessage.class, this::method1);
      this.handle(EventEntitySpawn.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
   }

   private void onDisable() {
      this.field13 = false;
   }

   private void method1(TypedChatMessage data1) {
      if ((Boolean)this.field10.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
            String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
            if (field8.matcher(text2).matches()) {
               this.method13();
            }
         }
      }
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field11.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            if (!this.field13) {
               if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
                  Component component5 = bridgeextension_22.bridge$getCustomName();
                  if (component5 != null) {
                     String text4 = TextBridge.getTextContent(component5);
                     if (field9.matcher(text4).matches()) {
                        this.method13();
                        this.field13 = true;
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(EventWorldChange data31) {
      this.field13 = false;
   }

   private void method13() {
      if ((Boolean)this.field12.get()) {
         ChatMessageQueue.method1("/pc Chest looted!");
      } else {
         ChatMessageQueue.method1("/ac Chest looted!");
      }
   }

   public String getId() {
      return "SKYBLOCK_CHEST_LOOTED";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11, this.field12});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
