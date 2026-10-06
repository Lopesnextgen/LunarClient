package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.TextComponent;
import com.moonsworth.lunar.client.event.LunarEvent;

public abstract class EventRenderHologram extends LunarEvent {
   protected final MixinHelper_4 field1;
   protected final GuiContainerBridge field2;
   protected final SlotBridge field3;

   @Generated
   public MixinHelper_4 method1() {
      return this.field1;
   }

   @Generated
   public GuiContainerBridge method2() {
      return this.field2;
   }

   @Generated
   public SlotBridge method3() {
      return this.field3;
   }

   @Generated
   public EventRenderHologram(MixinHelper_4 mixinhelper_41, GuiContainerBridge bridge5extension_32, SlotBridge bridge3_183) {
      this.field1 = mixinhelper_41;
      this.field2 = bridge5extension_32;
      this.field3 = bridge3_183;
   }

   public static class EventRenderHologramItem extends EventRenderHologram {
      private ItemStackBridge field4;

      public EventRenderHologramItem(MixinHelper_4 mixinhelper_41, GuiContainerBridge bridge5extension_32, SlotBridge bridge3_183) {
         super(mixinhelper_41, bridge5extension_32, bridge3_183);
      }

      public void method1(int number1) {
         this.IOOOHRROHOIIOCIIOROOCRIICIIICI.method44(arg0 -> {
            arg0.method29().method11();
            arg0.method29().method33();
         });
         if (Ref.MC_VERSION >= 6 && Ref.MC_VERSION <= 15) {
            this.IOOOHRROHOIIOCIIOROOCRIICIIICI
               .method39(-this.ICOCOHOROIROHRCHIIOIRRROCCRIIR.bridge$getGuiLeft(), -this.ICOCOHOROIROHRCHIIOIRRROCCRIIR.bridge$getGuiTop());
         }

         LcuiScreen.method127(this.IOOOHRROHOIIOCIIOROOCRIICIIICI, this.ICOCOHOROIROHRCHIIOIRRROCCRIIR, this.IRCOHIRORCHIROOORCIRCRCOCIIRHH, number1);
      }

      @Generated
      public void method2(ItemStackBridge bridgeextension_41) {
         this.field4 = bridgeextension_41;
      }

      @Generated
      public ItemStackBridge method4() {
         return this.field4;
      }
   }

   public static class EventRenderHologramText extends EventRenderHologram {
      public EventRenderHologramText(MixinHelper_4 mixinhelper_41, GuiContainerBridge bridge5extension_32, SlotBridge bridge3_183) {
         super(mixinhelper_41, bridge5extension_32, bridge3_183);
      }

      public void method1(TextComponent text1) {
         LcuiScreen.method128(this.IOOOHRROHOIIOCIIOROOCRIICIIICI, this.ICOCOHOROIROHRCHIIOIRRROCCRIIR, this.IRCOHIRORCHIROOORCIRCRCOCIIRHH, text1);
      }

      public void method2(TextComponent text1) {
         LcuiScreen.method129(this.IOOOHRROHOIIOCIIOROOCRIICIIICI, this.ICOCOHOROIROHRCHIIOIRRROCCRIIR, this.IRCOHIRORCHIROOORCIRCRCOCIIRHH, text1);
      }

      public void method3(TextComponent text1, int number2, int number3) {
         LcuiScreen.method130(
            this.IOOOHRROHOIIOCIIOROOCRIICIIICI, this.ICOCOHOROIROHRCHIIOIRRROCCRIIR, this.IRCOHIRORCHIROOORCIRCRCOCIIRHH, text1, number2, number3
         );
      }

      public void method4(TextComponent text1, int number2, int number3, int number4) {
         LcuiScreen.method131(
            this.IOOOHRROHOIIOCIIOROOCRIICIIICI, this.ICOCOHOROIROHRCHIIOIRRROCCRIIR, this.IRCOHIRORCHIROOORCIRCRCOCIIRHH, text1, number2, number3, number4
         );
      }
   }
}
