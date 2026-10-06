package com.moonsworth.lunar.client.mod.skyblock.hidelotusfishnametag;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;

public class SkyblockHideLotusfishNametag extends AbstractFeature {
   private final IntegerOption field8 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "hideFarEntitiesDistance"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(0, 20))
      .method31();

   public SkyblockHideLotusfishNametag(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.LOTUS_ATOLL));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.LOTUS_ATOLL));
      this.handle(EventRenderNameTag.class, this::method1);
   }

   private void method1(EventRenderNameTag highlightimpl111) {
      if (!highlightimpl111.isCancelled()) {
         if (!(highlightimpl111.method2() instanceof EntityPlayerBridge)) {
            Component component2 = highlightimpl111.method3();
            if (component2 != null) {
               String text3 = TextBridge.getTextContent(component2);
               if (text3.endsWith("Lotusfish")) {
                  int number4 = (Integer)this.field8.get();
                  if (number4 > 0) {
                     EntityLivingStateBridge bridgeextension2_25 = highlightimpl111.method2();
                     double value6 = Ref.method7()
                        .ORICHRORRORHORHOIHCRHOORCRRHOI(bridgeextension2_25.bridge$getPosX(), bridgeextension2_25.bridge$getPosY(), bridgeextension2_25.bridge$getPosZ());
                     if (value6 <= number4 * number4) {
                        return;
                     }
                  }

                  highlightimpl111.cancel();
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_LOTUSFISH_NAMETAG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
