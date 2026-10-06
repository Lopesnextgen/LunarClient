package com.moonsworth.lunar.client.framework.feature.cooldowns;

import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.Type;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import lombok.Generated;

public abstract class Cooldown {
   protected static final int field1 = 20;
   private static final int field2 = 1;
   private static final int field3 = 42;
   private static final int field4 = 4;
   private static final String field5 = "23h59m";
   private final String field6;
   private final long length;
   private final long field7;
   private final CooldownStyle field8;

   protected Cooldown(String text, long value, CooldownStyle cooldownstyle4) {
      this.field6 = text;
      this.length = value;
      this.field8 = cooldownstyle4;
      this.field7 = Ref.method3().bridge$getSystemTime();
   }

   public static FloatFloatPair method1(CooldownTextPosition cooldownTextPosition) {
      Bridge10_2 bridge10_21 = Ref.method10();
      float value2 = bridge10_21.method19();
      float value3 = bridge10_21.bridge$getStringWidth("23h59m");

      return switch (cooldownTextPosition) {
         case LEFT, RIGHT -> FloatFloatPair.of(46.0F + value3, 42.0F);
         case ABOVE, BELOW -> FloatFloatPair.of(Math.max(42.0F, value3), 46.0F + value2);
      };
   }

   public void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, int value, int value4, int number6, int number7, CooldownTextPosition cooldownTextPosition) {
      Bridge10_2 bridge10_29 = Ref.method10();
      int number10 = bridge10_29.method19();
      float value11 = value2 + 1.0F;
      float value12 = value3 + 1.0F;
      switch (cooldownTextPosition) {
         case LEFT:
            float value13 = bridge10_29.bridge$getStringWidth("23h59m");
            value11 = value2 + value13 + 4.0F + 1.0F;
            break;
         case ABOVE:
            value12 = value3 + number10 + 4.0F + 1.0F;
      }

      this.method3(mixinhelper_41, value11, value12);
      double value15 = this.length - (Ref.method3().bridge$getSystemTime() - this.field7);
      if (!(value15 <= 0.0)) {
         this.method4(mixinhelper_41, value11, value12, value15, value, value4, number6);
         this.method5(mixinhelper_41, bridge10_29, value2, value3, value11, value12, value15, number10, number7, cooldownTextPosition);
      }
   }

   public abstract void method3(MixinHelper_4 mixinhelper_41, float value2, float value3);

   private void method4(MixinHelper_4 mixinhelper_41, float value2, float value3, double value4, int number6, int number7, int value) {
      double value9 = value2 + 20.0F;
      double value11 = value3 + 20.0F;
      double value13 = 20.0;
      double value15 = 17.0;
      byte number17 = 32;
      LcuiScreen.method63(mixinhelper_41, value9, value11, 20.0, 0.0, (float)this.length / 3.95F, (int)this.length, value4, 872415231);
      LcuiScreen.method63(mixinhelper_41, value9, value11, 20.0, 0.0, (float)this.length / 3.95F, (int)this.length, value4, 855638016);
      LcuiScreen.method61(mixinhelper_41, value9, value11, value13 + 0.3, value15 - 0.3, 0.0, 1.0, 0.0, number7, number17);
      LcuiScreen.method63(mixinhelper_41, value9, value11, value13 + 0.3, value15 - 0.3, (float)this.length / 3.95F, (int)this.length, value4, number6);
      LcuiScreen.method61(mixinhelper_41, value9, value11, value13 + 1.0, value13, 0.0, 1.0, 0.0, value, number17);
      LcuiScreen.method61(mixinhelper_41, value9, value11, value15, value15 - 1.0, 0.0, 1.0, 0.0, value, number17);
   }

   private void method5(
      MixinHelper_4 mixinhelper_41, Bridge10_2 bridge10_22, float value3, float value4, float value, float value2, double value5, int value6, int number10, CooldownTextPosition cooldownTextPosition
   ) {
      String text12 = Type.COMPACT_DYNAMIC.format((long)value5);
      float value13 = bridge10_22.bridge$getStringWidth(text12);
      float value14 = value + 20.0F;
      float value15 = value2 + 20.0F;
      float value16 = value6 / 2.0F;
      float value17;
      float value18;
      switch (cooldownTextPosition) {
         case LEFT:
            value17 = value3;
            value18 = value15 - value16;
            break;
         case RIGHT:
            value17 = value3 + 42.0F + 4.0F;
            value18 = value15 - value16;
            break;
         case ABOVE:
            value17 = value14 - value13 / 2.0F;
            value18 = value4;
            break;
         case BELOW:
            value17 = value14 - value13 / 2.0F;
            value18 = value4 + 42.0F + 4.0F;
            break;
         default:
            value17 = value3 + 42.0F + 4.0F;
            value18 = value15 - value16;
      }

      mixinhelper_41.method19(bridge10_22, text12, value17, value18, number10, true);
   }

   public boolean method6() {
      return this.field7 < Ref.method3().bridge$getSystemTime() - this.length;
   }

   @Generated
   public String getName() {
      return this.field6;
   }

   @Generated
   public CooldownStyle method7() {
      return this.field8;
   }
}
