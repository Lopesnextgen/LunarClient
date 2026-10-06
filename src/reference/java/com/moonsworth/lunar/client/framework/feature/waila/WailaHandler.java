package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class WailaHandler implements WailaComponent {
   private static final int field1 = 5;
   private final Component field2;
   private boolean field3 = true;
   private ColorOption field4 = null;

   public WailaHandler(String text1) {
      this(TextBridge.asAdventure(text1));
   }

   public WailaHandler(Bridge2_42 bridge2_421) {
      this(TextBridge.asAdventure(bridge2_421));
   }

   public WailaHandler(Component component1) {
      this.field2 = component1;
   }

   @Override
   public int getWidth() {
      return (int)Ref.method10().bridge$getStringWidth(this.field2);
   }

   @Override
   public int getHeight() {
      return Ref.method10().method19();
   }

   public WailaHandler method1() {
      this.field3 = false;
      return this;
   }

   public WailaHandler method2(ColorOption lightingextension42221) {
      this.field4 = lightingextension42221;
      return this;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int number3, int number4) {
      ColorOption lightingextension42225 = this.field4 == null ? waila2.method16() : this.field4;
      Component component6 = this.field2;
      if (Ref.MC_VERSION >= 6) {
         component6 = component6.color(TextColor.color(lightingextension42225.method14(number3 + number4)));
      }

      lightingextension42225.method11(mixinhelper_41, TextBridge.asBridge(component6), number3 + 1, (float)number4 + (this.field3 ? 5 : 1), (Boolean)waila2.method17().get());
   }
}
