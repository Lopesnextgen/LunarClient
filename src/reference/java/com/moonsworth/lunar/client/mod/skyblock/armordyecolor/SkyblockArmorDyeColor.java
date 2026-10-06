package com.moonsworth.lunar.client.mod.skyblock.armordyecolor;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.DyedColorComponentBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public class SkyblockArmorDyeColor extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("textColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("matchColorOfArmor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("hexColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();

   public SkyblockArmorDyeColor(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.method1().orElse(null);
      if (bridgeextension_42 != null) {
         DyedColorComponentBridge mixinhelper3_83 = (DyedColorComponentBridge)bridgeextension_42.bridge$getDataComponent(DataComponentTypes.field26);
         if (mixinhelper3_83 != null) {
            int number4 = mixinhelper3_83.bridge$getRgb();
            int number5 = this.field9.get() ? number4 : this.field10.method14(0.0F);
            TextComponent text6 = Component.text(ColorUtils.method48(number4, false), TextColor.color(number5));
            TextComponent text7 = (TextComponent)Component.text(
                  this.method14("color", new Object[0]) + ": ", TextColor.color(this.field8.method14(0.0F))
               )
               .append(text6);
            List list8 = data21.method3();
            list8.add(1, (ClickableText)Bridge.method8().method89(text7));
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_ARMOR_DYE_COLOR";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field8, this.field9});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10}).method3(this.field9::get);
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
