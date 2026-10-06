package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.chat.ChatModule;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.ChatLineBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiNewChatBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.network.apollo.ChatApolloHandler;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMessageMixin {
   @Shadow
   public int scrollPos;
   @Shadow
   public boolean isScrolled;
   @Final
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public List<ChatLine> chatLines;
   @Final
   @Shadow
   public List chatLines$v1_7;
   @Final
   @Shadow
   public List<ChatLine> drawnChatLines;
   @Final
   @Shadow
   public List field_146253_i$v1_7;
   @Unique
   private static float lunar$percentTranslated = 0.0F;
   @Unique
   private static long lunar$lastUpdate = -1L;
   @Unique
   private float lunar$easedPercent;
   @Unique
   private static int lunar$newLines;
   @Unique
   private boolean lunar$hoveredLineHasHead;
   @Unique
   private boolean lunar$hoverPendingFirstWidth;
   @Unique
   private int impl$idCounter = 1;

   public GuiNewChatMessageMixin() {
   }

   @Shadow
   public abstract boolean getChatOpen();

   @Shadow
   public abstract int getLineCount();

   @Shadow
   public abstract float getChatScale();

   @Shadow
   public abstract int getChatWidth();

   @Unique
   private Bridge2_42 lunar$handleChatMessage(Bridge2_42 bridge2_421, int number2, boolean flag3, LocalRef<EventChatMessage> localref4) {
      if (flag3) {
         return bridge2_421;
      }

      EventChatMessage highlightimpl5 = (EventChatMessage)LunarEventBus.method29().method12(TypedChatMessage.class, () -> new TypedChatMessage(TextBridge.asAdventure(bridge2_421), number2));
      if (highlightimpl5 != null) {
         localref4.set(highlightimpl5);
         if (highlightimpl5.isCancelled()) {
            return null;
         } else {
            return !highlightimpl5.isChanged() ? bridge2_421 : TextBridge.asBridge(highlightimpl5.method2());
         }
      } else {
         return bridge2_421;
      }
   }

   @Unique
   private void lunar$removeLastMessage() {
      List list1 = Ref.MC_VERSION >= 1 ? this.chatLines : this.chatLines$v1_7;
      List list2 = Ref.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;
      if (!list1.isEmpty() && !list2.isEmpty()) {
         int number3 = ((ChatLineBridge)list1.get(0)).bridge$getLunarID();
         list1.remove(0);

         while (!list2.isEmpty() && ((ChatLineBridge)list2.get(0)).bridge$getLunarID() == number3) {
            list2.remove(0);
         }
      }
   }

   @Unique
   private void lunar$handleCancellation(CallbackInfo callback1, LocalRef<EventChatMessage> localref2) {
      EventChatMessage highlightimpl3 = (EventChatMessage)localref2.get();
      if (highlightimpl3 != null) {
         if (highlightimpl3.isCancelled()) {
            callback1.cancel();
         } else {
            lunar$percentTranslated = 0.0F;
         }

         if (highlightimpl3.method8()) {
            this.lunar$removeLastMessage();
         }
      }
   }

   @Inject(method = {"setChatLine$v1_7", "setChatLine$v1_8"}, at = @At("TAIL"))
   private void lunar$incrementIdCounter(CallbackInfo callback1) {
      this.impl$idCounter++;
   }

   @VersionGate(max = 0)
   @ModifyArgs(method = "setChatLine$v1_7", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
   private void lunar$assignMessageId$v1_7(Args args1) {
      if (args1.get(1) instanceof ChatLineBridge bridge3_133) {
         this.lunar$setMessageId(bridge3_133);
      }
   }

   @VersionGate(min = 1)
   @ModifyArgs(method = "setChatLine$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
   private void lunar$assignMessageId$v1_8(Args args1) {
      if (args1.get(1) instanceof ChatLineBridge bridge3_133) {
         this.lunar$setMessageId(bridge3_133);
      }
   }

   @Unique
   private void lunar$setMessageId(ChatLineBridge bridge3_131) {
      GuiNewChatBridge bridge5extension42 = Ref.method3().bridge$getGuiIngame().bridge$getChatGUI();
      int number3 = bridge5extension42.bridge$getLunarId();
      if (number3 != -1) {
         bridge3_131.bridge$setAddedByApollo(bridge5extension42.bridge$messageAddedByApollo());
         bridge3_131.bridge$setLunarID(number3);
      } else {
         bridge3_131.bridge$setLunarID(this.impl$idCounter);
      }
   }

   @VersionGate(min = 1)
   @ModifyVariable(method = "setChatLine$v1_8", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private IChatComponent lunar$modifyText$v1_8(
      IChatComponent text1, IChatComponent text2, int number3, int number4, @Local(argsOnly = true) boolean flag5, @Share("event") LocalRef<EventChatMessage> localref6
   ) {
      return (IChatComponent)this.lunar$handleChatMessage((Bridge2_42)text1, this.impl$idCounter, flag5, localref6);
   }

   @VersionGate(max = 0)
   @ModifyVariable(method = "setChatLine$v1_7", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private IChatComponent lunar$modifyText$v1_7(
      IChatComponent text1, IChatComponent text2, int number3, int number4, @Local(argsOnly = true) boolean flag5, @Share("event") LocalRef<EventChatMessage> localref6
   ) {
      return (IChatComponent)this.lunar$handleChatMessage((Bridge2_42)text1, this.impl$idCounter, flag5, localref6);
   }

   @Inject(method = {"setChatLine$v1_8", "setChatLine$v1_7"}, at = @At("HEAD"), cancellable = true)
   private void lunar$cancelAdd(CallbackInfo callback1, @Share("event") LocalRef<EventChatMessage> localref2) {
      this.lunar$handleCancellation(callback1, localref2);
   }

   @ModifyConstant(method = {"setChatLine$v1_8", "setChatLine$v1_7"}, constant = @Constant(intValue = 100))
   private int lunar$setChatLine$maxLines(int number1) {
      Chat chat2 = Ref.method4().method40().method47();
      return chat2.isEnabled() && chat2.method27().get() ? 1000 : number1;
   }

   @Unique
   private static void lunar$updateChatTranslation(long number0) {
      if (lunar$percentTranslated < 1.0F) {
         lunar$percentTranslated = lunar$percentTranslated + Ref.method4().method40().method47().method17() * (float)number0;
      }

      lunar$percentTranslated = MathUtils.method3(lunar$percentTranslated, 0.0F, 1.0F);
   }

   @Inject(method = "drawChat", at = @At("HEAD"), cancellable = true)
   private void lunar$drawChat(int number1, CallbackInfo callback2) {
      if (Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method15())) {
         callback2.cancel();
      } else if (Ref.method4().method40().method47().method21()) {
         callback2.cancel();
      } else if (FramebufferCaptureTask.method6() && FramebufferCaptureTask.method2()) {
         callback2.cancel();
      } else if (lunar$lastUpdate == -1L) {
         lunar$lastUpdate = Ref.method3().bridge$getSystemTime();
         callback2.cancel();
      } else {
         long number3 = Ref.method3().bridge$getSystemTime();
         long number5 = number3 - lunar$lastUpdate;
         lunar$lastUpdate = number3;
         lunar$updateChatTranslation(number5);
         float value7 = lunar$percentTranslated;
         this.lunar$easedPercent = (float)(1.0 - --value7 * Math.pow(value7, 3.0));
         this.lunar$easedPercent = MathUtils.method3(this.lunar$easedPercent, 0.0F, 1.0F);
      }
   }

   @VersionGate(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 0))
   private void lunar$translateChat_v1_7(float value1, float value2, float value3) {
      GL11.glTranslatef(value1, value2, value3);
      if (Ref.method4().method40().method47().method16() && !this.isScrolled) {
         GL11.glTranslatef(0.0F, (9.0F - 9.0F * lunar$percentTranslated) * this.getChatScale(), 0.0F);
      }
   }

   @VersionGate(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 0))
   private void lunar$translateChat_v1_8(float value1, float value2, float value3) {
      GlStateManager.translate(value1, value2, value3);
      if (Ref.method4().method40().method47().method16() && !this.isScrolled) {
         GlStateManager.translate(0.0F, (9.0F - 9.0F * lunar$percentTranslated) * this.getChatScale(), 0.0F);
      }
   }

   @VersionGate(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;III)I"))
   private int lunar$drawChat$drawStringWithShadow$v1_7(FontRenderer font1, String text2, int number3, int number4, int number5) {
      return this.lunar$drawChat$drawStringWithShadow(font1, text2, number3, number4, number5);
   }

   @VersionGate(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
   private int lunar$drawChat$drawStringWithShadow$v1_8(FontRenderer font1, String text2, float value3, float value4, int number5) {
      return this.lunar$drawChat$drawStringWithShadow(font1, text2, value3, value4, number5);
   }

   @Unique
   private int lunar$drawChat$drawStringWithShadow(FontRenderer font1, String text2, float value3, float value4, int number5) {
      float value6 = (value4 + 8.0F) / -9.0F;
      MixinHelper_4 mixinhelper_47 = AbstractRenderContext.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO();
      Chat chat8 = Ref.method4().method40().method47();
      if (chat8.method16() && value6 <= lunar$newLines && !this.isScrolled) {
         int number9 = number5 >>> 24;
         number5 = 16777215 + ((int)(number9 * this.lunar$easedPercent) << 24);
      }

      boolean flag16 = this.lunar$chatLifted();
      boolean flag10 = !chat8.isEnabled() || (Boolean)chat8.method38().get();
      if (chat8.method24()) {
         int number11 = Math.round(value6) + this.scrollPos;
         ResourceLocationBridge horsestats1412 = this.lunar$chatHeadSkin(number11);
         if (horsestats1412 != null) {
            if (this.lunar$chatHeadTopLine(number11)) {
               int number13 = 16777215 | number5 & 0xFF000000;
               float value14 = value4 - 1.0F - (flag16 ? 12 : 0);
               if (flag10) {
                  int number15 = ColorUtils.method32(number5);
                  LcuiScreen.method49(mixinhelper_47, horsestats1412, value3 + 1.0F, value14 + 1.0F, number15);
               }

               LcuiScreen.method49(mixinhelper_47, horsestats1412, value3, value14, number13);
            }

            value3 += 12.0F;
         }
      }

      Bridge10_2 bridge10_217 = (Bridge10_2)font1;
      mixinhelper_47.method19(bridge10_217, text2, value3, value4 - (flag16 ? 12 : 0), number5, flag10);
      return (int)bridge10_217.bridge$getStringWidth(text2);
   }

   @Unique
   private boolean lunar$chatLifted() {
      Chat chat1 = Ref.method4().method40().method47();
      return chat1.isEnabled() && chat1.method29().get()
         ? true
         : Ref.method4().method84().method3(ChatModule.class).map(arg0 -> ((ChatApolloHandler)arg0).method8()).orElse(false);
   }

   @Unique
   private List<ChatLine> lunar$drawnChatLines() {
      return Ref.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;
   }

   @Unique
   @Nullable
   private ResourceLocationBridge lunar$chatHeadSkin(int index1) {
      List list2 = this.lunar$drawnChatLines();
      if (index1 >= 0 && index1 < list2.size()) {
         int number3 = ((ChatLineBridge)list2.get(index1)).bridge$getLunarID();
         return Ref.method4().method40().method47().method83().method5(number3);
      } else {
         return null;
      }
   }

   @Unique
   private boolean lunar$chatHeadTopLine(int index1) {
      List list2 = this.lunar$drawnChatLines();
      return index1 + 1 >= list2.size() ? true : ((ChatLineBridge)list2.get(index1 + 1)).bridge$getLunarID() != ((ChatLineBridge)list2.get(index1)).bridge$getLunarID();
   }

   @Unique
   private boolean lunar$chatHeadsForCurrentMessage() {
      Chat chat1 = Ref.method4().method40().method47();
      if (!chat1.method24()) {
         return false;
      }

      int number2 = Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getLunarId();
      int number3 = number2 != -1 ? number2 : this.impl$idCounter;
      return chat1.method83().method5(number3) != null;
   }

   @VersionGate(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"))
   private void lunar$drawChat$getChatOpen_v1_7(float value1, float value2, float value3, int number4) {
      GL11.glScalef(value1, value2, value3);
      this.lunar$drawChat$getChatOpen(number4);
   }

   @VersionGate(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;scale(FFF)V"))
   private void lunar$drawChat$getChatOpen_v1_8(float value1, float value2, float value3, int number4) {
      GlStateManager.scale(value1, value2, value3);
      this.lunar$drawChat$getChatOpen(number4);
   }

   @Unique
   private void lunar$drawChat$getChatOpen(int number1) {
      int number2 = this.getLineCount();
      boolean flag3 = this.getChatOpen();
      float value4 = this.getChatScale();
      float value5 = this.getChatWidth() / value4;
      int number6 = Ref.MC_VERSION >= 5 ? MathHelper.ceil$v1_12(value5) : MathHelper.ceiling_float_int(value5);
      Chat chat7 = Ref.method4().method40().method47();
      float value8 = chat7.isEnabled() ? (Float)chat7.method37().get() : 1.0F;
      boolean flag9 = this.lunar$chatLifted();
      if (value8 > 0.0F) {
         int number10 = 0;
         List list11 = Ref.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;

         for (int index12 = 0; index12 + this.scrollPos < list11.size() && index12 < number2; index12++) {
            ChatLine chatline13 = (ChatLine)list11.get(index12 + this.scrollPos);
            if (chatline13 != null) {
               int number14 = number1 - chatline13.getUpdatedCounter();
               double value15 = number14 / 200.0;
               value15 = 1.0 - value15;
               value15 *= 10.0;
               value15 = Ref.MC_VERSION >= 5 ? MathHelper.clamp$v1_12(value15, 0.0, 1.0) : MathHelper.clamp_double(value15, 0.0, 1.0);
               value15 *= value15;
               int number17 = (int)(255.0 * value15);
               if (number17 >= 254 || flag3) {
                  number10 -= this.mc.fontRendererObj.FONT_HEIGHT;
               }
            }
         }

         Gui.drawRect(0, flag9 ? -12 : 0, number6 + 4, number10 + (flag9 ? -12 : 0), (int)(127.0F * value8) << 24);
      }
   }

   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
   private void lunar$drawChat$drawRect(int number1, int number2, int number3, int number4, int number5) {
      Chat chat6 = Ref.method4().method40().method47();
      boolean flag7 = this.lunar$chatLifted();
      float value8 = chat6.isEnabled() ? (Float)chat6.method37().get() : 1.0F;
      if (value8 > 0.0F) {
         int number9 = (byte)(number5 >> 24) & 255;
         if (number9 < 127) {
            Gui.drawRect(number1, number2 + (flag7 ? -12 : 0), number3, number4 + (flag7 ? -12 : 0), (int)(value8 * number9) << 24);
         }
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "setChatLine$v1_8", at = @At("HEAD"))
   private void lunar$setChatLine(IChatComponent text1, int number2, int number3, boolean flag4, CallbackInfo callback5) {
      float value6 = this.getChatWidth() / this.getChatScale();
      int number7 = Ref.MC_VERSION >= 5 ? MathHelper.floor$v1_12(value6) : MathHelper.floor_float(value6);
      List list8 = GuiUtilRenderComponents.splitText(text1, number7, this.mc.fontRendererObj, false, false);
      lunar$newLines = list8.size() - 1;
   }

   @VersionGate(min = 5)
   @Redirect(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor$v1_12(F)I", ordinal = 1),
      require = 1
   )
   private int lunar$getChatComponent$yPos$v1_12(float value1) {
      return MathHelper.floor$v1_12(value1) - (this.lunar$chatLifted() ? 12 : 0);
   }

   @VersionGate(1)
   @Redirect(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor_float$v1_7(F)I", ordinal = 1),
      require = 1
   )
   private int lunar$getChatComponent$yPos$v1_8(float value1) {
      return MathHelper.floor_float(value1) - (this.lunar$chatLifted() ? 12 : 0);
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "setChatLine$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiUtilRenderComponents;splitText(Lnet/minecraft/util/text/ITextComponent;ILnet/minecraft/client/gui/FontRenderer;ZZ)Ljava/util/List;"
      )
   )
   private List<IChatComponent> lunar$splitChatLines(IChatComponent text1, int number2, FontRenderer font3, boolean flag4, boolean flag5) {
      if (this.lunar$chatHeadsForCurrentMessage()) {
         number2 -= 12;
      }

      return GuiUtilRenderComponents.splitText(text1, number2, font3, flag4, flag5);
   }

   @VersionGate(max = 0)
   @ModifyExpressionValue(
      method = "setChatLine$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor_float$v1_7(F)I", ordinal = 0)
   )
   private int lunar$chatHeadsWrapWidth$v1_7(int number1) {
      return this.lunar$chatHeadsForCurrentMessage() ? number1 - 12 : number1;
   }

   @Redirect(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;"))
   private Object lunar$chatHeadsHoverLine(List<?> list1, int index2) {
      this.lunar$hoveredLineHasHead = Ref.method4().method40().method47().method24() && this.lunar$chatHeadSkin(index2) != null;
      this.lunar$hoverPendingFirstWidth = true;
      return list1.get(index2);
   }

   @Redirect(
      method = {"getChatComponent$v1_7", "getChatComponent$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;getStringWidth(Ljava/lang/String;)I")
   )
   private int lunar$chatHeadsHoverWidth(FontRenderer font1, String text2) {
      int number3 = font1.getStringWidth(text2);
      if (this.lunar$hoverPendingFirstWidth) {
         this.lunar$hoverPendingFirstWidth = false;
         if (this.lunar$hoveredLineHasHead) {
            number3 += 12;
         }
      }

      return number3;
   }

   @VersionGate(min = 1)
   @Inject(method = "getChatComponent$v1_8", at = @At("HEAD"))
   private void lunar$resetLastHovered$v1_8(int number1, int number2, CallbackInfoReturnable<IChatComponent> callbackinforeturnable3) {
      Chat.method83(-1);
   }

   @VersionGate(max = 0)
   @Inject(method = "getChatComponent$v1_7", at = @At("HEAD"))
   private void lunar$resetLastHovered$v1_7(int number1, int number2, CallbackInfoReturnable<IChatComponent> callbackinforeturnable3) {
      Chat.method83(-1);
   }

   @VersionGate(min = 1)
   @Inject(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ChatLine;getChatComponent$v1_8()Lnet/minecraft/util/text/ITextComponent;")
   )
   private void lunar$getLastChatLineHovered$v1_8(int number1, int number2, CallbackInfoReturnable<IChatComponent> callbackinforeturnable3, @Local ChatLine chatline4) {
      Chat.method83(((ChatLineBridge)chatline4).bridge$getLunarID());
   }

   @VersionGate(max = 0)
   @Inject(
      method = "getChatComponent$v1_7",
      at = @At(value = "INVOKE", target = "net/minecraft/client/gui/ChatLine.getChatComponent()Lnet/minecraft/util/IChatComponent;")
   )
   private void lunar$getLastChatLineHovered$v1_7(int number1, int number2, CallbackInfoReturnable<IChatComponent> callbackinforeturnable3, @Local ChatLine chatline4) {
      Chat.method83(((ChatLineBridge)chatline4).bridge$getLunarID());
   }

   @VersionGate(min = 5)
   @Inject(method = "clearChatMessages$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$preventChatClear(boolean flag1, CallbackInfo callback2) {
      if (flag1 && (Boolean)Ref.method4().method40().method47().method40().get()) {
         callback2.cancel();
      }
   }

   @Inject(method = "getChatOpen", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindIsChatFocused(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (Ref.method4().method40().method85().method17(arg0 -> ((ReplayContext)arg0.method42().get()).method5())) {
         callbackinforeturnable1.setReturnValue(true);
      }
   }

   @Inject(method = "getChatOpen", at = @At("HEAD"), cancellable = true)
   private void lunar$chatPeekGetChatOpen(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (Ref.method4().method40().method47().method22()) {
         callbackinforeturnable1.setReturnValue(true);
      }
   }

   @ModifyVariable(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private int lunar$getRewindMouseX(int number1) {
      RewindHandlers rewindhandlers2 = Ref.method4().method40().method85().method35();
      if (rewindhandlers2 != null) {
         GuiResolution threadmoduledump713 = LcuiScreen.method151();
         return ((ReplayContext)rewindhandlers2.method42().get()).method8().method3(Ref.method3().bridge$getTimer().method1(), threadmoduledump713.getScaledWidth())
            * threadmoduledump713.method3();
      } else {
         return number1;
      }
   }

   @ModifyVariable(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At("HEAD"), ordinal = 1, argsOnly = true)
   private int lunar$getRewindMouseY(int number1) {
      RewindHandlers rewindhandlers2 = Ref.method4().method40().method85().method35();
      if (rewindhandlers2 != null) {
         GuiResolution threadmoduledump713 = LcuiScreen.method151();
         return (
               threadmoduledump713.getScaledHeight()
                  - ((ReplayContext)rewindhandlers2.method42().get()).method8().method4(Ref.method3().bridge$getTimer().method1(), threadmoduledump713.getScaledHeight())
            )
            * threadmoduledump713.method3();
      } else {
         return number1;
      }
   }
}
