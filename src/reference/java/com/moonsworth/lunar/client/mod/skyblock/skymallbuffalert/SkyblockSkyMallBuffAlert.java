package com.moonsworth.lunar.client.mod.skyblock.skymallbuffalert;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSkyMallBuffAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final TextComponent field9 = Component.text("SkyMall buff has changed!", NamedTextColor.RED);

   public SkyblockSkyMallBuffAlert(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isMiningIsland));
      this.handle(TypedChatMessage.class, this::method1);
   }

   private void method1(TypedChatMessage data1) {
      if (data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC().equals("New day! Your Sky Mall buff changed!")) {
         Ref.method7().method1("random.pop", 1.0F, 1.0F);
         this.field8.method2(ComparableImpl.method2().method1("SKYMALL_BUFF_ALERT").method2(field9).method3(1000L).method6());
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SKY_MALL_BUFF_ALERT";
   }
}
