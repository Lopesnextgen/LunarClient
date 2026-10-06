package com.moonsworth.lunar.v1_8.mixin;

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
import net.minecraftforge.fml.client.config.GuiUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiUtils.class)
public class GuiUtilsMixin {
   public GuiUtilsMixin() {
   }

   @WrapMethod(method = "drawHoveringText")
   private static void lunar$wrapTooltipRender(
      List<String> list0,
      int number1,
      int number2,
      int number3,
      int number4,
      int number5,
      FontRenderer font6,
      Operation<Void> operation7,
      @Share("context") LocalRef<AbstractRenderContext> localref8,
      @Share("guiContext") LocalRef<MixinHelper_4> localref9,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref10
   ) {
      if (!MixinCore3.method1(list0, number1, number2, localref8, localref9, localref10)) {
         ((AbstractRenderContext)localref8.get()).push();
         operation7.call(new Object[]{list0, number1, number2, number3, number4, number5, font6});
         ((AbstractRenderContext)localref8.get()).pop();
      }
   }

   @ModifyExpressionValue(method = "drawHoveringText", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"))
   private static int lunar$cancelLoopIter(int number0, @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref1) {
      return localref1.get() != null && ((EventRenderTooltipPre)localref1.get()).isModified() ? 0 : number0;
   }

   @Inject(method = "drawHoveringText", at = @At(value = "CONSTANT", args = "intValue=12", ordinal = 0))
   private static void lunar$updateWidth(
      List<String> list0,
      int number1,
      int number2,
      int number3,
      int number4,
      int number5,
      FontRenderer font6,
      CallbackInfo callback7,
      @Local(ordinal = 5) LocalIntRef localintref8,
      @Share("actualW") LocalIntRef localintref9,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref10
   ) {
      MixinCore3.method2(localintref8, localintref9, localref10);
   }

   @Inject(method = "drawHoveringText", at = @At(value = "CONSTANT", args = "intValue=6", ordinal = 0))
   private static void lunar$updateHeight(
      List<String> list0,
      int number1,
      int number2,
      int number3,
      int number4,
      int number5,
      FontRenderer font6,
      CallbackInfo callback7,
      @Local(ordinal = 9) LocalIntRef localintref8,
      @Share("actualH") LocalIntRef localintref9,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref10
   ) {
      MixinCore3.method3(localintref8, localintref9, localref10);
   }

   @ModifyVariable(method = "drawHoveringText", at = @At("LOAD"), ordinal = 0)
   private static boolean lunar$cancelWrap(boolean flag0, @Share("event") LocalRef<EventRenderTooltipPre> localref1) {
      return localref1.get() != null && ((EventRenderTooltipPre)localref1.get()).isModified() ? false : flag0;
   }

   @Inject(method = "drawHoveringText", at = @At(value = "CONSTANT", args = "intValue=-267386864", ordinal = 0))
   private static void lunar$modifyPosition(
      List<String> list0,
      int number1,
      int number2,
      int number3,
      int number4,
      int number5,
      FontRenderer font6,
      CallbackInfo callback7,
      @Local(ordinal = 5) LocalIntRef localintref8,
      @Local(ordinal = 9) LocalIntRef localintref9,
      @Local(ordinal = 7) LocalIntRef localintref10,
      @Local(ordinal = 8) LocalIntRef localintref11,
      @Share("actualW") LocalIntRef localintref12,
      @Share("actualH") LocalIntRef localintref13,
      @Share("context") LocalRef<AbstractRenderContext> localref14,
      @Share("guiContext") LocalRef<MixinHelper_4> localref15
   ) {
      MixinCore3.method7(localintref8, localintref9, localintref12, localintref13, localintref10, localintref11, localref14, localref15);
   }

   @Inject(method = "drawHoveringText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;enableLighting()V"))
   private static void lunar$renderComponents(
      List<String> list0,
      int number1,
      int number2,
      int number3,
      int number4,
      int number5,
      FontRenderer font6,
      CallbackInfo callback7,
      @Local(ordinal = 7) int number8,
      @Local(ordinal = 8) int number9,
      @Share("modifyComponents") LocalRef<EventRenderTooltipPre> localref10,
      @Share("context") LocalRef<AbstractRenderContext> localref11,
      @Share("guiContext") LocalRef<MixinHelper_4> localref12
   ) {
      MixinCore3.method9(number8, number9, localref10, localref11, localref12);
   }
}
