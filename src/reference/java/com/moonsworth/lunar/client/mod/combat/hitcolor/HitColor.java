package com.moonsworth.lunar.client.mod.combat.hitcolor;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HitColor extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((Data)OptionFactory.method8("hitArmorColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1721303040))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("shouldColorArmor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public HitColor() {
      super(false);
      this.method2(EventRenderItemGlint.class, this::method1, 130);
   }

   private void method1(EventRenderItemGlint highlightimpl31) {
      if (highlightimpl31.method2() == GlintTarget.EQUIPPED_ARMOR && highlightimpl31.method5() != null && (Boolean)this.method15().get()) {
         EntityLivingBridge bridgeextension2_52 = (EntityLivingBridge)highlightimpl31.method5();
         if (bridgeextension2_52.bridge$getHurtTime() > 0 || bridgeextension2_52.bridge$getDeathTime() > 0.0F) {
            highlightimpl31.setCancelled(true);
         }
      }
   }

   public String getId() {
      return "HIT_COLOR";
   }

   public static int method2(int number0) {
      Client client1 = Ref.method4();
      if (client1 == null) {
         return number0;
      }

      ModsSettings fogloader32 = client1.method40();
      if (fogloader32 == null) {
         return number0;
      }

      if (fogloader32.method84().method35()) {
         return -16777216;
      }

      HitColor hitcolor3 = fogloader32.method14();
      return hitcolor3 != null && hitcolor3.isEnabled() ? hitcolor3.method13() : number0;
   }

   private int method13() {
      int number1;
      if (this.isEnabled()) {
         number1 = this.field8.method14(0.0F);
      } else {
         number1 = ColorUtils.method10(255, 0, 0, 77);
      }

      int number2 = ColorUtils.method22(number1, 255 - ColorUtils.method4(number1));
      return ColorUtils.method17(number2);
   }

   public float method4(float value1) {
      if (Ref.method4().method40().method84().method35()) {
         return 1.0F;
      } else {
         return !this.isEnabled() ? value1 : ColorUtils.method9(this.field8.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F));
      }
   }

   public float method5(float value1) {
      if (Ref.method4().method40().method84().method35()) {
         return 1.0F;
      } else {
         return !this.isEnabled() ? value1 : ColorUtils.method9(this.field8.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F));
      }
   }

   public float method6(float value1) {
      if (Ref.method4().method40().method84().method35()) {
         return 1.0F;
      } else {
         return !this.isEnabled() ? value1 : ColorUtils.method9(this.field8.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F));
      }
   }

   public float method7(float value1) {
      if (Ref.method4().method40().method84().method35()) {
         return 0.0F;
      } else {
         return !this.isEnabled() ? value1 : ColorUtils.method9(this.field8.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F));
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9});
   }

   @Generated
   public ColorOption method14() {
      return this.field8;
   }

   @Generated
   public ToggleOption method15() {
      return this.field9;
   }
}
