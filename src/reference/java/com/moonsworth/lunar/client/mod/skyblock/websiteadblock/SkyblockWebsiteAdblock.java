package com.moonsworth.lunar.client.mod.skyblock.websiteadblock;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.List;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockWebsiteAdblock extends AbstractFeature {
   public SkyblockWebsiteAdblock(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.CHAT));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(TypedChatMessage.class, this::method1);
   }

   private void method1(TypedChatMessage data1) {
      List list2 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method33();
      if (list2 != null && !list2.isEmpty()) {
         String text3 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();

         for (String text5 : list2) {
            if (text3.contains(text5)) {
               data1.setCancelled(true);
               return;
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_WEBSITE_ADBLOCK";
   }
}
