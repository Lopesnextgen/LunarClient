package com.moonsworth.lunar.v1_12.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.legacy.MixinCore3;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiUtils.class)
public class GuiUtilsMixin {
   public GuiUtilsMixin() {
   }

   @WrapMethod(method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V")
   private static void lunar$wrapTooltipRender(
      ItemStack stack0,
      List<String> list1,
      int number2,
      int number3,
      int number4,
      int number5,
      int number6,
      FontRenderer font7,
      Operation<Void> operation8,
      @Share("context") LocalRef<AbstractRenderContext> localref9,
      @Share("guiContext") LocalRef<MixinHelper_4> localref10,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref11
   ) {
      if (!MixinCore3.method1(list1, number2, number3, localref9, localref10, localref11)) {
         ((AbstractRenderContext)localref9.get()).push();
         operation8.call(new Object[]{stack0, list1, number2, number3, number4, number5, number6, font7});
         ((AbstractRenderContext)localref9.get()).pop();
      }
   }

   @ModifyExpressionValue(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At(value = "INVOKE", target = "Ljava/util/List;size()I")
   )
   private static int lunar$cancelLoopIter(int number0, @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref1) {
      return localref1.get() != null && ((EventRenderTooltipPre)localref1.get()).isModified() ? 0 : number0;
   }

   @Inject(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At(value = "CONSTANT", args = "intValue=12", ordinal = 0)
   )
   private static void lunar$updateWidth(
      ItemStack stack0,
      List<String> list1,
      int number2,
      int number3,
      int number4,
      int number5,
      int number6,
      FontRenderer font7,
      CallbackInfo callback8,
      @Local(ordinal = 5) LocalIntRef localintref9,
      @Share("actualW") LocalIntRef localintref10,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref11
   ) {
      MixinCore3.method2(localintref9, localintref10, localref11);
   }

   @Inject(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 2)
   )
   private static void lunar$updateHeight(
      ItemStack stack0,
      List<String> list1,
      int number2,
      int number3,
      int number4,
      int number5,
      int number6,
      FontRenderer font7,
      CallbackInfo callback8,
      @Local(ordinal = 9) LocalIntRef localintref9,
      @Share("actualH") LocalIntRef localintref10,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref11
   ) {
      MixinCore3.method3(localintref9, localintref10, localref11);
   }

   @ModifyVariable(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At("LOAD"),
      ordinal = 0
   )
   private static boolean lunar$cancelWrap(boolean flag0, @Share("event") LocalRef<EventRenderTooltipPre> localref1) {
      return localref1.get() != null && ((EventRenderTooltipPre)localref1.get()).isModified() ? false : flag0;
   }

   @Inject(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/event/RenderTooltipEvent$Color;getBorderEnd()I", shift = Shift.AFTER)
   )
   private static void lunar$modifyPosition(
      ItemStack stack0,
      List<String> list1,
      int number2,
      int number3,
      int number4,
      int number5,
      int number6,
      FontRenderer font7,
      CallbackInfo callback8,
      @Local(ordinal = 5) LocalIntRef localintref9,
      @Local(ordinal = 9) LocalIntRef localintref10,
      @Local(ordinal = 7) LocalIntRef localintref11,
      @Local(ordinal = 8) LocalIntRef localintref12,
      @Share("actualW") LocalIntRef localintref13,
      @Share("actualH") LocalIntRef localintref14,
      @Share("context") LocalRef<AbstractRenderContext> localref15,
      @Share("guiContext") LocalRef<MixinHelper_4> localref16
   ) {
      MixinCore3.method7(localintref9, localintref10, localintref13, localintref14, localintref11, localintref12, localref15, localref16);
   }

   @Inject(
      method = "drawHoveringText(Lnet/minecraft/item/ItemStack;Ljava/util/List;IIIIILnet/minecraft/client/gui/FontRenderer;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;enableLighting()V")
   )
   private static void lunar$renderComponents(
      ItemStack stack0,
      List<String> list1,
      int number2,
      int number3,
      int number4,
      int number5,
      int number6,
      FontRenderer font7,
      CallbackInfo callback8,
      @Local(ordinal = 7) int number9,
      @Local(ordinal = 8) int number10,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref11,
      @Share("context") LocalRef<AbstractRenderContext> localref12,
      @Share("guiContext") LocalRef<MixinHelper_4> localref13
   ) {
      MixinCore3.method9(number9, number10, localref11, localref12, localref13);
   }
}
