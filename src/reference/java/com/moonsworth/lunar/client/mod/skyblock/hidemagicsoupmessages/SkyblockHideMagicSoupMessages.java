package com.moonsworth.lunar.client.mod.skyblock.hidemagicsoupmessages;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.Set;

public class SkyblockHideMagicSoupMessages extends AbstractFeature {
   private static final Set<String> field8 = Sets.newHashSet(
      new String[]{
         "I feel like I can fly!",
         "What was in that soup?",
         "Hmm… tasty!",
         "Hmm... tasty!",
         "You can now fly for 2 minutes.",
         "Your flight has been extended for 2 extra minutes.",
         "You can now fly for 200 minutes.",
         "Your flight has been extended for 200 extra minutes."
      }
   );

   public SkyblockHideMagicSoupMessages(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.CHAT));
      this.method45(
         ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.ISLAND || IslandUtils.getIsland() == SkyblockIsland.GARDEN)
      );
      this.handle(TypedChatMessage.class, this::method1);
   }

   private void method1(TypedChatMessage data1) {
      if (field8.contains(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH())) {
         data1.setCancelled(true);
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_MAGIC_SOUP_MESSAGES";
   }
}
