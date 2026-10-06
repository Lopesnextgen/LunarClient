package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.hud.HudTimer.Data;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class KingRelicTracker {
   public static final int field1 = 144;
   private HudTimer field2 = Data.method1().method2().method4().method5(5000L).method7().method2();
   private EntityArmorStandBridge field3 = null;
   private EntityArmorStandBridge field4 = null;
   private TextComponent field5 = null;
   private TextComponent field6 = null;

   public KingRelicTracker() {
   }

   public boolean method1() {
      return this.field3 == null
         || this.field4 == null
         || this.field3.ORICHRORRORHORHOIHCRHOORCRRHOI(this.field4.bridge$getPosX(), this.field4.bridge$getPosY(), this.field4.bridge$getPosZ()) > 144.0;
   }

   public TextComponent method2(KingRelic hologramstype41, boolean flag2) {
      long number3 = this.field2.get();
      String text5 = this.field2.method1();
      NamedTextColor namedtextcolor6;
      if (number3 < 1000L) {
         namedtextcolor6 = NamedTextColor.GREEN;
      } else if (number3 < 3000L) {
         namedtextcolor6 = NamedTextColor.YELLOW;
      } else {
         namedtextcolor6 = NamedTextColor.RED;
      }

      return TextComponentFactory.builder().method2(hologramstype41.getName()).method4(text5).method5(hologramstype41.getTextColor()).method7(namedtextcolor6).method12(flag2).build();
   }

   public void method3(String text1, KingRelic hologramstype42) {
      this.method12((TextComponent)Component.text(text1).color(hologramstype42.getTextColor()));
      this.method13(TextComponentFactory.builder().method2(hologramstype42.getName()).method4(text1).method5(hologramstype42.getTextColor()).method7(NamedTextColor.GRAY).build());
   }

   @Generated
   public HudTimer method4() {
      return this.field2;
   }

   @Generated
   public EntityArmorStandBridge method5() {
      return this.field3;
   }

   @Generated
   public EntityArmorStandBridge method6() {
      return this.field4;
   }

   @Generated
   public TextComponent method7() {
      return this.field5;
   }

   @Generated
   public TextComponent method8() {
      return this.field6;
   }

   @Generated
   public void method9(HudTimer threadmoduledump451) {
      this.field2 = threadmoduledump451;
   }

   @Generated
   public void method10(EntityArmorStandBridge bridgeextension_21) {
      this.field3 = bridgeextension_21;
   }

   @Generated
   public void method11(EntityArmorStandBridge bridgeextension_21) {
      this.field4 = bridgeextension_21;
   }

   @Generated
   public void method12(TextComponent text1) {
      this.field5 = text1;
   }

   @Generated
   public void method13(TextComponent text1) {
      this.field6 = text1;
   }
}
