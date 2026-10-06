package com.moonsworth.lunar.client.mod.skyblock.calculatorinsigns;

import com.moonsworth.lunar.bridge.GuiEditSignBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CalculatorParser;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.gui.EventSignUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCalculatorInSigns extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("calculatorAllLines").method31();
   private final ColorOption field9 = (ColorOption)((Data)OptionFactory.method8("answerColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();

   public SkyblockCalculatorInSigns(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderContainerSlotPost.class, this::method1);
      this.handle(EventSignUpdate.class, this::method2);
   }

   private void method1(EventRenderContainerSlotPost data31) {
      GuiScreenBridge bridge5extension62 = data31.method3();
      if (bridge5extension62 != null && bridge5extension62.method1(GuiEditSignBridge.class)) {
         GuiEditSignBridge bridge5extension3_23 = (GuiEditSignBridge)bridge5extension62;
         int number4 = this.field8.get() ? 3 : 0;

         for (int index5 = 0; index5 <= number4; index5++) {
            String text6 = CalculatorParser.formatResult(bridge5extension3_23.bridge$getLine(index5), true);
            if (text6 != null && (!(Boolean)this.field8.get() || index5 == bridge5extension3_23.bridge$getEditLine())) {
               data31.method5()
                  .method27(Ref.method10(), Component.text(text6), bridge5extension62.bridge$getWidth() / 2, 55, this.field9.method14(0.0F), true);
            }
         }
      }
   }

   private void method2(EventSignUpdate highlightimpl171) {
      String[] items2 = highlightimpl171.method1();
      int number3 = this.field8.get() ? 3 : 0;

      for (int index4 = 0; index4 <= number3; index4++) {
         String text5 = CalculatorParser.formatResult(items2[index4], true);
         if (text5 != null) {
            items2[index4] = text5.replace(",", "");
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field9}));
   }

   public String getId() {
      return "SKYBLOCK_CALCULATOR_IN_SIGNS";
   }
}
