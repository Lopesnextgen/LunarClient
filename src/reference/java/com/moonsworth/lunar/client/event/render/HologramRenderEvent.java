package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.kyori.adventure.text.TextComponent;
import com.moonsworth.lunar.client.highlight.Highlight;

public abstract class HologramRenderEvent extends Highlight {
   protected final MixinHelper_4 field1;
   protected final Bridge5Extension_3 field2;
   protected final Bridge3_18 field3;

   @Generated
   public MixinHelper_4 method1() {
      return this.field1;
   }

   @Generated
   public Bridge5Extension_3 method2() {
      return this.field2;
   }

   @Generated
   public Bridge3_18 method3() {
      return this.field3;
   }

   @Generated
   public HologramRenderEvent(MixinHelper_4 var1, Bridge5Extension_3 var2, Bridge3_18 var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public static class HologramItemRenderEvent extends HologramRenderEvent {
      private ItemStackBridge field4;

      public HologramItemRenderEvent(MixinHelper_4 var1, Bridge5Extension_3 var2, Bridge3_18 var3) {
         super(var1, var2, var3);
      }

      public void method1(int var1) {
         this.field1.method44(var0 -> {
            var0.method29().method11();
            var0.method29().method33();
         });
         if (ThreadModuleDump63.MC_VERSION >= 6 && ThreadModuleDump63.MC_VERSION <= 15) {
            this.field1
               .method39(-this.field2.bridge$getGuiLeft(), -this.field2.bridge$getGuiTop());
         }

         LcuiScreen.method127(this.field1, this.field2, this.field3, var1);
      }

      @Generated
      public void method2(ItemStackBridge var1) {
         this.field4 = var1;
      }

      @Generated
      public ItemStackBridge method4() {
         return this.field4;
      }
   }

   public static class HologramTextRenderEvent extends HologramRenderEvent {
      public HologramTextRenderEvent(MixinHelper_4 var1, Bridge5Extension_3 var2, Bridge3_18 var3) {
         super(var1, var2, var3);
      }

      public void method1(TextComponent var1) {
         LcuiScreen.method128(this.field1, this.field2, this.field3, var1);
      }

      public void method2(TextComponent var1) {
         LcuiScreen.method129(this.field1, this.field2, this.field3, var1);
      }

      public void method3(TextComponent var1, int var2, int var3) {
         LcuiScreen.method130(
            this.field1, this.field2, this.field3, var1, var2, var3
         );
      }

      public void method4(TextComponent var1, int var2, int var3, int value) {
         LcuiScreen.method131(
            this.field1, this.field2, this.field3, var1, var2, var3, value
         );
      }
   }
}
