package com.moonsworth.lunar.client.mod.hud.horsestats;

import com.moonsworth.lunar.bridge.GuiScreenHorseInventoryBridge;
import com.moonsworth.lunar.bridge.AbstractHorseBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;

public class HorseStats extends AbstractFeature {
   private final ColorOption textColor = (ColorOption)((Data)OptionFactory.method8("textColor").method4(-12566464))
      .method31();
   private final ColorOption speedColor = (ColorOption)((Data)OptionFactory.method8("speedColor").method4(-13801265))
      .method31();
   private final ColorOption jumpColor = (ColorOption)((Data)OptionFactory.method8("jumpColor").method4(-15552755))
      .method31();
   private final ColorOption healthColor = (ColorOption)((Data)OptionFactory.method8("healthColor").method4(-3199699))
      .method31();

   public HorseStats() {
      super(true);
      this.handle(EventRenderContainerSlotPost.class, this::onContainerSlotRender);
   }

   public void onContainerSlotRender(EventRenderContainerSlotPost data31) {
      if (data31.method3() instanceof GuiScreenHorseInventoryBridge bridge3_22) {
         MixinHelper_4 mixinhelper_418 = data31.method5();
         AbstractHorseBridge bridge4_234 = bridge3_22.bridge$getHorse();
         int number5 = data31.method3().bridge$getWidth();
         int number6 = data31.method3().bridge$getHeight();
         int number7 = number5 / 2 - 8;
         int number8 = number6 / 2 - 65;
         byte number9 = 88;
         MutableInt mutableint10 = new MutableInt(0);
         float value11 = 0.0F;
         mixinhelper_418.method44(arg0 -> arg0.method29().method11());
         value11 += this.registerOptions(
            mixinhelper_418,
            2,
            this.onContainerSlotRender("health", new Object[0]),
            ((EntityLivingBridge)bridge4_234).bridge$getMaxHealth() / 2.0F,
            ((EntityLivingBridge)bridge4_234).bridge$getMaxHealth(),
            15.0,
            30.0,
            number5,
            number6,
            this.healthColor,
            bridge4_234.bridge$hasChest(),
            mutableint10,
            ResourceLocationBridge.create("lunar", "horse_stats/health_boost.png")
         );
         value11 += this.registerOptions(
            mixinhelper_418,
            1,
            this.onContainerSlotRender("jump", new Object[0]),
            bridge4_234.bridge$getJumpHeight(),
            bridge4_234.bridge$getJumpHeightRaw(),
            0.4,
            1.0,
            number5,
            number6,
            this.jumpColor,
            bridge4_234.bridge$hasChest(),
            mutableint10,
            ResourceLocationBridge.create("lunar", "horse_stats/jump_boost.png")
         );
         value11 += this.registerOptions(
            mixinhelper_418,
            0,
            this.onContainerSlotRender("speed", new Object[0]),
            bridge4_234.bridge$getSpeed(),
            bridge4_234.bridge$getSpeedRaw(),
            0.1125,
            0.3375,
            number5,
            number6,
            this.speedColor,
            bridge4_234.bridge$hasChest(),
            mutableint10,
            ResourceLocationBridge.create("lunar", "horse_stats/speed.png")
         );
         float value12 = value11 / 3.0F * 100.0F;
         ColorOption lightingextension422213 = this.textColor;
         String text14 = String.format("%.1f%%", value12);
         if (!bridge4_234.bridge$hasChest()) {
            text14 = "Score: " + text14;
         }

         int number15 = (int)this.mc.bridge$getFontRenderer().bridge$getStringWidth(text14);
         float value16 = number7 + number9 / 2.0F - number15 / 2.0F;
         float value17 = number8 - 11.5F;
         if (bridge4_234.bridge$hasChest()) {
            value16 = number7 + number9 - mutableint10.getValue() - number15;
         }

         mixinhelper_418.method19(Ref.method10(), text14, value16, value17, lightingextension422213.method14(0.0F), false);
      }
   }

   private float registerOptions(
      MixinHelper_4 mixinhelper_41,
      int number2,
      String text3,
      float value4,
      double value5,
      double value7,
      double value9,
      int number11,
      int number12,
      ColorOption lightingextension422213,
      boolean flag14,
      MutableInt mutableint15,
      @NotNull ResourceLocationBridge horsestats1416
   ) {
      int number17 = number11 / 2 - 8;
      int number18 = number12 / 2 - 65;
      float value19 = Math.max(Math.min((float)((value5 - value7) / (value9 - value7)), 1.0F), 0.0F);
      if (flag14) {
         String text20 = String.format("%.1f", value4);
         int index21 = (int)(this.mc.bridge$getFontRenderer().bridge$getStringWidth(text20) + 8.0F + 4.0F);
         byte number22 = 88;
         int number23 = number18 - 13;
         int number24 = number17 + number22 - mutableint15.getValue() - index21;
         mixinhelper_41.method1(number24, number23 + 10, number24 + index21, number23 + 11, -12566464);
         mixinhelper_41.method1(number24, number23 + 10, number24 + (int)(index21 * value19), number23 + 11, lightingextension422213.method14(0.0F));
         mixinhelper_41.method18(Ref.method10(), text20, number24, number23, this.textColor.method14(0.0F), false);
         mixinhelper_41.method24(horsestats1416, number24, number23 - 2, 12, 12, -1);
         mutableint15.add(index21 + 3);
      } else {
         byte number25 = 88;
         int number26 = number18 + 2 + 17 * number2;
         mixinhelper_41.method1(number17 + 2, number26 + 10, number17 + number25 - 2, number26 + 12, -1340071904);
         mixinhelper_41.method1(number17 + 2, number26 + 10, number17 + 2 + (int)((number25 - 4) * value19), number26 + 10 + 2, lightingextension422213.method14(0.0F));
         String text27 = String.format(text3, value4);
         mixinhelper_41.method18(Ref.method10(), text27, number17 + 2, number26, this.textColor.method14(0.0F), false);
      }

      return value19;
   }

   public String getId() {
      return "HORSE_STATS";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.textColor});
      lightingextension231.method1(
         "barsColor", arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.speedColor, this.jumpColor, this.healthColor})
      );
   }
}
