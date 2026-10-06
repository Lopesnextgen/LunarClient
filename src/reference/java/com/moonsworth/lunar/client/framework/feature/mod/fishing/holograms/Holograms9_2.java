package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import com.moonsworth.lunar.client.util.ThreadModuleDump45.Data;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Holograms9_2 {
   public static final int field1 = 144;
   private ThreadModuleDump45 field2 = Data.method1().method2().method4().method5(5000L).method7().method2();
   private ArmorStandBridge field3 = null;
   private ArmorStandBridge field4 = null;
   private TextComponent field5 = null;
   private TextComponent field6 = null;

   public boolean method1() {
      return this.field3 == null
         || this.field4 == null
         || this.field3.method15(this.field4.bridge$getPosX(), this.field4.bridge$getPosY(), this.field4.bridge$getPosZ()) > 144.0;
   }

   public TextComponent method2(HologramsType4 var1, boolean var2) {
      long var3 = this.field2.get();
      String var5 = this.field2.method1();
      NamedTextColor var6;
      if (var3 < 1000L) {
         var6 = NamedTextColor.GREEN;
      } else if (var3 < 3000L) {
         var6 = NamedTextColor.YELLOW;
      } else {
         var6 = NamedTextColor.RED;
      }

      return Click12.builder().method2(var1.getName()).method4(var5).method5(var1.getTextColor()).method7(var6).method12(var2).build();
   }

   public void method3(String var1, HologramsType4 var2) {
      this.method12((TextComponent)Component.text(var1).color(var2.getTextColor()));
      this.method13(Click12.builder().method2(var2.getName()).method4(var1).method5(var2.getTextColor()).method7(NamedTextColor.GRAY).build());
   }

   @Generated
   public ThreadModuleDump45 method4() {
      return this.field2;
   }

   @Generated
   public ArmorStandBridge method5() {
      return this.field3;
   }

   @Generated
   public ArmorStandBridge method6() {
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
   public void method9(ThreadModuleDump45 var1) {
      this.field2 = var1;
   }

   @Generated
   public void method10(ArmorStandBridge var1) {
      this.field3 = var1;
   }

   @Generated
   public void method11(ArmorStandBridge var1) {
      this.field4 = var1;
   }

   @Generated
   public void method12(TextComponent var1) {
      this.field5 = var1;
   }

   @Generated
   public void method13(TextComponent var1) {
      this.field6 = var1;
   }
}
