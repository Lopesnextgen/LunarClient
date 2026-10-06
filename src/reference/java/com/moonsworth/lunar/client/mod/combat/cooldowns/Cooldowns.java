package com.moonsworth.lunar.client.mod.combat.cooldowns;

import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode;
import com.moonsworth.lunar.client.framework.feature.cooldowns.ItemCooldown;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.game.ItemTypeLookup;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class Cooldowns extends AbstractFeature {
   private static final Map<String, com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown> field8 = new HashMap<>();
   private final Map<String, com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown> field9 = new HashMap<>();
   private final EnumOption<ArmorStatusListMode> field10 = (EnumOption<ArmorStatusListMode>)OptionFactory.method10("mode", ArmorStatusListMode.VERTICAL)
      .method31();
   private final EnumOption<com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition> field11 = (EnumOption<com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition>)OptionFactory.method10(
         "cooldownTextPosition", com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition.RIGHT
      )
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "cooldownCircleStartColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1722197671))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "cooldownCircleEndColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1710619))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "cooldownEdgeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12566464))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();

   public Cooldowns() {
      super(true);
      this.method4(ModTraits.field1, new Cooldowns.Data());
      this.handle(EventTick.class, this::method1);
   }

   public String getId() {
      return "COOLDOWNS";
   }

   private void method1(EventTick highlightimpl21) {
      if (!field8.isEmpty()) {
         this.method2(field8);
      }

      if (!this.field9.isEmpty()) {
         this.method2(this.field9);
      }
   }

   private void method2(Map<String, com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown> map1) {
      Iterator iterator2 = map1.entrySet().iterator();

      while (iterator2.hasNext()) {
         Entry entry3 = (Entry)iterator2.next();
         com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown cooldowns4 = (com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown)entry3.getValue();
         if (cooldowns4.method6()) {
            iterator2.remove();
         }
      }
   }

   public static void method3(String text0, long number1, int number3) {
      ItemStackBridge bridgeextension_44 = ItemTypeLookup.method2(number3);
      if (bridgeextension_44 != null) {
         field8.put(text0, new ItemCooldown(text0, number1, bridgeextension_44));
      }
   }

   public static void method4(com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown cooldowns0) {
      field8.put(cooldowns0.getName(), cooldowns0);
   }

   public static void method5(String text0) {
      field8.remove(text0);
   }

   public static void method13() {
      field8.clear();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field10, this.field11})
      );
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   @Generated
   public Map<String, com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown> method14() {
      return this.field9;
   }

   @Generated
   public EnumOption<ArmorStatusListMode> method15() {
      return this.field10;
   }

   @Generated
   public EnumOption<com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition> method16() {
      return this.field11;
   }

   @Generated
   public ColorOption method17() {
      return this.field12;
   }

   @Generated
   public ColorOption method19() {
      return this.field13;
   }

   @Generated
   public ColorOption method21() {
      return this.field14;
   }

   @Generated
   public ColorOption method22() {
      return this.field15;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_CENTER_L);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (!flag4 && !Cooldowns.field8.isEmpty()) {
            this.method2(highlightimpl1.method2(), Cooldowns.field8, value2, value3);
         } else {
            if (Cooldowns.this.field9.isEmpty()) {
               Cooldowns.this.field9.put("CombatTag", new ItemCooldown("CombatTag", 30000L, ItemTypeLookup.method1("golden_sword")));
               Cooldowns.this.field9.put("EnderPearl", new ItemCooldown("EnderPearl", 12000L, ItemTypeLookup.method1("ender_pearl")));
            }

            this.method2(highlightimpl1.method2(), Cooldowns.this.field9, value2, value3);
         }
      }

      private void method2(MixinHelper_4 mixinhelper_41, Map<String, com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown> map2, float value3, float value4) {
         boolean flag5 = Cooldowns.this.field10.get() == ArmorStatusListMode.VERTICAL;
         com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition gui2extension6 = (com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition)Cooldowns.this.field11
            .get();
         FloatFloatPair floatfloatpair7 = com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown.method1(gui2extension6);
         int number8 = (int)Math.ceil(floatfloatpair7.leftFloat());
         int number9 = (int)Math.ceil(floatfloatpair7.rightFloat());
         int number10 = map2.size();
         byte number11 = 4;
         int number12;
         int number13;
         if (flag5) {
            number12 = number8;
            number13 = number10 * number9 + (number10 - 1) * number11;
         } else {
            number12 = number10 * number8 + (number10 - 1) * number11;
            number13 = number9;
         }

         this.method16(number12, number13);
         int number14 = Cooldowns.this.field12.method14(0.0F);
         int number15 = Cooldowns.this.field13.method14(0.0F);
         int number16 = Cooldowns.this.field14.method14(0.0F);
         int number17 = Cooldowns.this.field15.method14(0.0F);
         int index18 = 0;

         for (Entry entry20 : map2.entrySet()) {
            com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown cooldowns21 = (com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown)entry20.getValue();
            float value22;
            float value23;
            if (flag5) {
               value22 = value3;
               value23 = value4 + index18 * (number9 + number11);
            } else {
               value22 = value3 + index18 * (number8 + number11);
               value23 = value4;
            }

            CooldownStyle cooldownstyle24 = cooldowns21.method7();
            if (cooldownstyle24 != null) {
               cooldowns21.method2(
                  mixinhelper_41,
                  value22,
                  value23,
                  cooldownstyle24.hasCircleStartColor() ? cooldownstyle24.getCircleStartColor().getColor() : number14,
                  cooldownstyle24.hasCircleEndColor() ? cooldownstyle24.getCircleEndColor().getColor() : number15,
                  cooldownstyle24.hasCircleEdgeColor() ? cooldownstyle24.getCircleEdgeColor().getColor() : number16,
                  cooldownstyle24.hasTextColor() ? cooldownstyle24.getTextColor().getColor() : number17,
                  gui2extension6
               );
            } else {
               cooldowns21.method2(mixinhelper_41, value22, value23, number14, number15, number16, number17, gui2extension6);
            }

            index18++;
         }
      }

      public boolean method4(boolean flag1) {
         return flag1 ? true : !Cooldowns.field8.isEmpty();
      }
   }
}
