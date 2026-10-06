package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.ArmorstatusType;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump86;
import lombok.Generated;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class Nameplate {
   public static final int field1 = 16;
   private static final int field2 = 2;
   private final Armorstatus field3;
   private final ArmorstatusType field4;
   @Nullable
   private final ItemStackBridge field5;
   private final boolean field6;
   private String name = "";
   private String field7 = "";
   private ColorOption field8;
   private int field9;
   private int field10;
   private int width;
   private int height;
   private int field11;
   private int field12;
   private int field13;
   private int field14;
   private int field15;
   private int field16;

   public Nameplate(Armorstatus var1, ArmorstatusType var2, @Nullable ItemStackBridge var3, boolean var4) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field6 = !var2.isHeld();
      if (var4) {
         this.init();
      }
   }

   private void init() {
      Bridge10_2 var1 = ThreadModuleDump63.method10();
      this.name = this.field5 != null && this.field3.field8.get() ? this.field5.bridge$getDisplayName() : "";
      this.field9 = (int)var1.bridge$getStringWidth(ThreadModuleDump86.getTextWithoutFormattingCodes(this.name));
      Pair var2 = this.method1((Boolean)this.field3.field16.get());
      this.field7 = (String)var2.getKey();
      this.field8 = (ColorOption)var2.getValue();
      this.field10 = (int)var1.bridge$getStringWidth(ThreadModuleDump86.getTextWithoutFormattingCodes(this.field7));
      this.method2((Gui2Extension2)this.field3.field18.get());
   }

   protected Pair<String, ColorOption> method1(boolean var1) {
      boolean var2 = this.field6 ? (Boolean)this.field3.field15.get() : (Boolean)this.field3.field14.get();
      if (var2
         && this.field5 != null
         && this.field5.bridge$isItemStackDamageableNoUnbr()
         && (this.field5.bridge$isItemStackDamageable() || !(Boolean)this.field3.field17.get())) {
         int var3 = this.field5.bridge$getMaxDamage();
         int var4 = var3 - this.field5.bridge$getItemDamage();
         int var5 = var4 * 100 / var3;

         String var6 = switch ((Gui2Extension4)this.field3.field20.get()) {
            case VALUE -> var4 + (var1 ? "/" + var3 : "");
            case PERCENT -> var5 + "%";
            case NONE -> "";
         };
         return Pair.of(var6, this.field3.method6(var5));
      } else {
         return Pair.of("", this.field3.method6(100));
      }
   }

   public void method2(Gui2Extension2 var1) {
      if (this.field5 == null) {
         this.width = 0;
         this.height = 0;
      } else {
         if (var1 != Gui2Extension2.TOP && var1 != Gui2Extension2.BOTTOM) {
            this.method3(var1 == Gui2Extension2.LEFT);
         } else {
            this.method4(var1 == Gui2Extension2.TOP);
         }
      }
   }

   private void method3(boolean var1) {
      int var2 = ThreadModuleDump63.method10().method19();
      int var3 = Math.max(this.field9, this.field10);
      int var4 = var2 * ((this.name.isEmpty() ? 0 : 1) + (this.field7.isEmpty() ? 0 : 1));
      this.width = 16 + (var3 == 0 ? 0 : 2 + var3);
      this.height = Math.max(16, var4);
      if (var1) {
         this.field11 = this.width - 16;
         this.field13 = var3 - this.field9;
         this.field15 = var3 - this.field10;
      } else {
         this.field11 = 0;
         this.field13 = this.width - var3;
         this.field15 = this.width - var3;
      }

      this.field12 = 0;
      this.field14 = this.method5(this.height, var4);
      this.field16 = this.field14 + (this.name.isEmpty() ? 0 : var2);
   }

   private void method4(boolean var1) {
      int var2 = ThreadModuleDump63.method10().method19();
      int var3 = 16 + (this.field9 == 0 ? 0 : 2 + this.field9);
      int var4 = this.field7.isEmpty() ? 0 : 2 + var2;
      this.width = Math.max(var3, this.field10);
      this.height = 16 + var4;
      this.field11 = this.method5(this.width, var3);
      this.field12 = var1 ? var4 : 0;
      this.field13 = this.field11 + 16 + 2;
      this.field14 = this.field12 + this.method5(16, var2);
      this.field15 = 0;
      this.field16 = var1 ? 3 : 16;
   }

   private int method5(int var1, int var2) {
      return Math.round((var1 - var2) / 2.0F);
   }

   public void method6(MixinHelper_4 var1, Bridge5_19 var2, float var3, float var4) {
      if (this.field5 != null) {
         this.method7(var1, var2, var3 + this.field11, var4 + this.field12);
         this.method8(var1, var3 + this.field13, var4 + this.field14);
         this.method9(var1, var3 + this.field15, var4 + this.field16);
      }
   }

   private void method7(MixinHelper_4 var1, Bridge5_19 var2, float var3, float var4) {
      var1.method44(var0 -> {
         var0.method29().method22();
         Bridge.method14().method2();
      });
      var1.method37(this.field5, var3, var4, ThreadModuleDump63.method3());
      Nameplate3.method1(
         var1, ThreadModuleDump63.method10(), this.field5, (int)var3, (int)var4, (Boolean)this.field3.field13.get(), (Boolean)this.field3.field9.get()
      );
      var1.method44(var0 -> {
         Bridge.method14().method3();
         var0.method29().method23();
         var0.method29().method15();
      });
   }

   private void method8(MixinHelper_4 var1, float var2, float var3) {
      if (!this.name.isEmpty()) {
         this.field3.field26.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, this.name, var2, var3, (Boolean)this.field3.field12.get());
      }
   }

   private void method9(MixinHelper_4 var1, float var2, float var3) {
      if (this.field8 != null && !this.field7.isEmpty()) {
         this.field8.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, this.field7, var2, var3, (Boolean)this.field3.field12.get());
      }
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   @Generated
   public ArmorstatusType method12() {
      return this.field4;
   }

   @Nullable
   @Generated
   public ItemStackBridge method13() {
      return this.field5;
   }
}
