package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.chat.ChatModule;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge3_13;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.network.apollo.ChatApolloHandler;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.ichor.Annotation2;
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
public abstract class GuiNewChatMixin2 {
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

   @Shadow
   public abstract boolean getChatOpen();

   @Shadow
   public abstract int getLineCount();

   @Shadow
   public abstract float getChatScale();

   @Shadow
   public abstract int getChatWidth();

   @Unique
   private Bridge2_42 lunar$handleChatMessage(Bridge2_42 var1, int var2, boolean var3, LocalRef<EventChatMessageLegacy> var4) {
      if (var3) {
         return var1;
      }

      EventChatMessageLegacy var5 = (EventChatMessageLegacy)ClientEventBus.method29().method12(Data.class, () -> new Data(AdventureTextBridge.asAdventure(var1), var2));
      if (var5 != null) {
         var4.set(var5);
         if (var5.isCancelled()) {
            return null;
         } else {
            return !var5.isChanged() ? var1 : AdventureTextBridge.asBridge(var5.method2());
         }
      } else {
         return var1;
      }
   }

   @Unique
   private void lunar$removeLastMessage() {
      List var1 = ThreadModuleDump63.MC_VERSION >= 1 ? this.chatLines : this.chatLines$v1_7;
      List var2 = ThreadModuleDump63.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;
      if (!var1.isEmpty() && !var2.isEmpty()) {
         int var3 = ((Bridge3_13)var1.get(0)).bridge$getLunarID();
         var1.remove(0);

         while (!var2.isEmpty() && ((Bridge3_13)var2.get(0)).bridge$getLunarID() == var3) {
            var2.remove(0);
         }
      }
   }

   @Unique
   private void lunar$handleCancellation(CallbackInfo var1, LocalRef<EventChatMessageLegacy> var2) {
      EventChatMessageLegacy var3 = (EventChatMessageLegacy)var2.get();
      if (var3 != null) {
         if (var3.isCancelled()) {
            var1.cancel();
         } else {
            lunar$percentTranslated = 0.0F;
         }

         if (var3.method8()) {
            this.lunar$removeLastMessage();
         }
      }
   }

   @Inject(method = {"setChatLine$v1_7", "setChatLine$v1_8"}, at = @At("TAIL"))
   private void lunar$incrementIdCounter(CallbackInfo var1) {
      this.impl$idCounter++;
   }

   @Annotation2(max = 0)
   @ModifyArgs(method = "setChatLine$v1_7", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
   private void lunar$assignMessageId$v1_7(Args var1) {
      if (var1.get(1) instanceof Bridge3_13 var3) {
         this.lunar$setMessageId(var3);
      }
   }

   @Annotation2(min = 1)
   @ModifyArgs(method = "setChatLine$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
   private void lunar$assignMessageId$v1_8(Args var1) {
      if (var1.get(1) instanceof Bridge3_13 var3) {
         this.lunar$setMessageId(var3);
      }
   }

   @Unique
   private void lunar$setMessageId(Bridge3_13 var1) {
      Bridge5Extension4 var2 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI();
      int var3 = var2.bridge$getLunarId();
      if (var3 != -1) {
         var1.bridge$setAddedByApollo(var2.bridge$messageAddedByApollo());
         var1.bridge$setLunarID(var3);
      } else {
         var1.bridge$setLunarID(this.impl$idCounter);
      }
   }

   @Annotation2(min = 1)
   @ModifyVariable(method = "setChatLine$v1_8", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private IChatComponent lunar$modifyText$v1_8(
      IChatComponent var1, IChatComponent var2, int var3, int var4, @Local(argsOnly = true) boolean var5, @Share("event") LocalRef<EventChatMessageLegacy> var6
   ) {
      return (IChatComponent)this.lunar$handleChatMessage((Bridge2_42)var1, this.impl$idCounter, var5, var6);
   }

   @Annotation2(max = 0)
   @ModifyVariable(method = "setChatLine$v1_7", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private IChatComponent lunar$modifyText$v1_7(
      IChatComponent var1, IChatComponent var2, int var3, int var4, @Local(argsOnly = true) boolean var5, @Share("event") LocalRef<EventChatMessageLegacy> var6
   ) {
      return (IChatComponent)this.lunar$handleChatMessage((Bridge2_42)var1, this.impl$idCounter, var5, var6);
   }

   @Inject(method = {"setChatLine$v1_8", "setChatLine$v1_7"}, at = @At("HEAD"), cancellable = true)
   private void lunar$cancelAdd(CallbackInfo var1, @Share("event") LocalRef<EventChatMessageLegacy> var2) {
      this.lunar$handleCancellation(var1, var2);
   }

   @ModifyConstant(method = {"setChatLine$v1_8", "setChatLine$v1_7"}, constant = @Constant(intValue = 100))
   private int lunar$setChatLine$maxLines(int var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      return var2.isEnabled() && var2.method27().get() ? 1000 : var1;
   }

   @Unique
   private static void lunar$updateChatTranslation(long var0) {
      if (lunar$percentTranslated < 1.0F) {
         lunar$percentTranslated = lunar$percentTranslated + ThreadModuleDump63.method4().method40().method47().method17() * (float)var0;
      }

      lunar$percentTranslated = ThreadModuleDump67.method3(lunar$percentTranslated, 0.0F, 1.0F);
   }

   @Inject(method = "drawChat", at = @At("HEAD"), cancellable = true)
   private void lunar$drawChat(int var1, CallbackInfo var2) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method15())) {
         var2.cancel();
      } else if (ThreadModuleDump63.method4().method40().method47().method21()) {
         var2.cancel();
      } else if (FramebufferCaptureTask.method6() && FramebufferCaptureTask.method2()) {
         var2.cancel();
      } else if (lunar$lastUpdate == -1L) {
         lunar$lastUpdate = ThreadModuleDump63.method3().bridge$getSystemTime();
         var2.cancel();
      } else {
         long var3 = ThreadModuleDump63.method3().bridge$getSystemTime();
         long var5 = var3 - lunar$lastUpdate;
         lunar$lastUpdate = var3;
         lunar$updateChatTranslation(var5);
         float var7 = lunar$percentTranslated;
         this.lunar$easedPercent = (float)(1.0 - --var7 * Math.pow(var7, 3.0));
         this.lunar$easedPercent = ThreadModuleDump67.method3(this.lunar$easedPercent, 0.0F, 1.0F);
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 0))
   private void lunar$translateChat_v1_7(float var1, float var2, float var3) {
      GL11.glTranslatef(var1, var2, var3);
      if (ThreadModuleDump63.method4().method40().method47().method16() && !this.isScrolled) {
         GL11.glTranslatef(0.0F, (9.0F - 9.0F * lunar$percentTranslated) * this.getChatScale(), 0.0F);
      }
   }

   @Annotation2(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 0))
   private void lunar$translateChat_v1_8(float var1, float var2, float var3) {
      GlStateManager.translate(var1, var2, var3);
      if (ThreadModuleDump63.method4().method40().method47().method16() && !this.isScrolled) {
         GlStateManager.translate(0.0F, (9.0F - 9.0F * lunar$percentTranslated) * this.getChatScale(), 0.0F);
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;III)I"))
   private int lunar$drawChat$drawStringWithShadow$v1_7(FontRenderer var1, String var2, int var3, int var4, int var5) {
      return this.lunar$drawChat$drawStringWithShadow(var1, var2, var3, var4, var5);
   }

   @Annotation2(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
   private int lunar$drawChat$drawStringWithShadow$v1_8(FontRenderer var1, String var2, float var3, float var4, int var5) {
      return this.lunar$drawChat$drawStringWithShadow(var1, var2, var3, var4, var5);
   }

   @Unique
   private int lunar$drawChat$drawStringWithShadow(FontRenderer var1, String var2, float var3, float var4, int var5) {
      float var6 = (var4 + 8.0F) / -9.0F;
      MixinHelper_4 var7 = AbstractRenderContext.method32().method42();
      Chat var8 = ThreadModuleDump63.method4().method40().method47();
      if (var8.method16() && var6 <= lunar$newLines && !this.isScrolled) {
         int var9 = var5 >>> 24;
         var5 = 16777215 + ((int)(var9 * this.lunar$easedPercent) << 24);
      }

      boolean var16 = this.lunar$chatLifted();
      boolean var10 = !var8.isEnabled() || var8.method38().get();
      if (var8.method24()) {
         int var11 = Math.round(var6) + this.scrollPos;
         ResourceLocationBridge var12 = this.lunar$chatHeadSkin(var11);
         if (var12 != null) {
            if (this.lunar$chatHeadTopLine(var11)) {
               int var13 = 16777215 | var5 & 0xFF000000;
               float var14 = var4 - 1.0F - (var16 ? 12 : 0);
               if (var10) {
                  int var15 = ThreadModuleDump23.method32(var5);
                  LcuiScreen.method49(var7, var12, var3 + 1.0F, var14 + 1.0F, var15);
               }

               LcuiScreen.method49(var7, var12, var3, var14, var13);
            }

            var3 += 12.0F;
         }
      }

      Bridge10_2 var17 = (Bridge10_2)var1;
      var7.method19(var17, var2, var3, var4 - (var16 ? 12 : 0), var5, var10);
      return (int)var17.bridge$getStringWidth(var2);
   }

   @Unique
   private boolean lunar$chatLifted() {
      Chat var1 = ThreadModuleDump63.method4().method40().method47();
      return var1.isEnabled() && var1.method29().get()
         ? true
         : ThreadModuleDump63.method4().method84().method3(ChatModule.class).map(var0 -> ((ChatApolloHandler)var0).method8()).orElse(false);
   }

   @Unique
   private List<ChatLine> lunar$drawnChatLines() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;
   }

   @Unique
   @Nullable
   private ResourceLocationBridge lunar$chatHeadSkin(int var1) {
      List var2 = this.lunar$drawnChatLines();
      if (var1 >= 0 && var1 < var2.size()) {
         int var3 = ((Bridge3_13)var2.get(var1)).bridge$getLunarID();
         return ThreadModuleDump63.method4().method40().method47().method83().method5(var3);
      } else {
         return null;
      }
   }

   @Unique
   private boolean lunar$chatHeadTopLine(int var1) {
      List var2 = this.lunar$drawnChatLines();
      return var1 + 1 >= var2.size() ? true : ((Bridge3_13)var2.get(var1 + 1)).bridge$getLunarID() != ((Bridge3_13)var2.get(var1)).bridge$getLunarID();
   }

   @Unique
   private boolean lunar$chatHeadsForCurrentMessage() {
      Chat var1 = ThreadModuleDump63.method4().method40().method47();
      if (!var1.method24()) {
         return false;
      }

      int var2 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getLunarId();
      int var3 = var2 != -1 ? var2 : this.impl$idCounter;
      return var1.method83().method5(var3) != null;
   }

   @Annotation2(max = 0)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"))
   private void lunar$drawChat$getChatOpen_v1_7(float var1, float var2, float var3, int var4) {
      GL11.glScalef(var1, var2, var3);
      this.lunar$drawChat$getChatOpen(var4);
   }

   @Annotation2(min = 1)
   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;scale(FFF)V"))
   private void lunar$drawChat$getChatOpen_v1_8(float var1, float var2, float var3, int var4) {
      GlStateManager.scale(var1, var2, var3);
      this.lunar$drawChat$getChatOpen(var4);
   }

   @Unique
   private void lunar$drawChat$getChatOpen(int var1) {
      int var2 = this.getLineCount();
      boolean var3 = this.getChatOpen();
      float var4 = this.getChatScale();
      float var5 = this.getChatWidth() / var4;
      int var6 = ThreadModuleDump63.MC_VERSION >= 5 ? MathHelper.ceil$v1_12(var5) : MathHelper.ceiling_float_int(var5);
      Chat var7 = ThreadModuleDump63.method4().method40().method47();
      float var8 = var7.isEnabled() ? var7.method37().get() : 1.0F;
      boolean var9 = this.lunar$chatLifted();
      if (var8 > 0.0F) {
         int var10 = 0;
         List var11 = ThreadModuleDump63.MC_VERSION >= 1 ? this.drawnChatLines : this.field_146253_i$v1_7;

         for (int var12 = 0; var12 + this.scrollPos < var11.size() && var12 < var2; var12++) {
            ChatLine var13 = (ChatLine)var11.get(var12 + this.scrollPos);
            if (var13 != null) {
               int var14 = var1 - var13.getUpdatedCounter();
               double var15 = var14 / 200.0;
               var15 = 1.0 - var15;
               var15 *= 10.0;
               var15 = ThreadModuleDump63.MC_VERSION >= 5 ? MathHelper.clamp$v1_12(var15, 0.0, 1.0) : MathHelper.clamp_double(var15, 0.0, 1.0);
               var15 *= var15;
               int var17 = (int)(255.0 * var15);
               if (var17 >= 254 || var3) {
                  var10 -= this.mc.fontRendererObj.FONT_HEIGHT;
               }
            }
         }

         Gui.drawRect(0, var9 ? -12 : 0, var6 + 4, var10 + (var9 ? -12 : 0), (int)(127.0F * var8) << 24);
      }
   }

   @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
   private void lunar$drawChat$drawRect(int var1, int var2, int var3, int var4, int var5) {
      Chat var6 = ThreadModuleDump63.method4().method40().method47();
      boolean var7 = this.lunar$chatLifted();
      float var8 = var6.isEnabled() ? var6.method37().get() : 1.0F;
      if (var8 > 0.0F) {
         int var9 = (byte)(var5 >> 24) & 255;
         if (var9 < 127) {
            Gui.drawRect(var1, var2 + (var7 ? -12 : 0), var3, var4 + (var7 ? -12 : 0), (int)(var8 * var9) << 24);
         }
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "setChatLine$v1_8", at = @At("HEAD"))
   private void lunar$setChatLine(IChatComponent var1, int var2, int var3, boolean var4, CallbackInfo var5) {
      float var6 = this.getChatWidth() / this.getChatScale();
      int var7 = ThreadModuleDump63.MC_VERSION >= 5 ? MathHelper.floor$v1_12(var6) : MathHelper.floor_float(var6);
      List var8 = GuiUtilRenderComponents.splitText(var1, var7, this.mc.fontRendererObj, false, false);
      lunar$newLines = var8.size() - 1;
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor$v1_12(F)I", ordinal = 1),
      require = 1
   )
   private int lunar$getChatComponent$yPos$v1_12(float var1) {
      return MathHelper.floor$v1_12(var1) - (this.lunar$chatLifted() ? 12 : 0);
   }

   @Annotation2(1)
   @Redirect(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor_float$v1_7(F)I", ordinal = 1),
      require = 1
   )
   private int lunar$getChatComponent$yPos$v1_8(float var1) {
      return MathHelper.floor_float(var1) - (this.lunar$chatLifted() ? 12 : 0);
   }

   @Annotation2(min = 1)
   @Redirect(
      method = "setChatLine$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiUtilRenderComponents;splitText(Lnet/minecraft/util/text/ITextComponent;ILnet/minecraft/client/gui/FontRenderer;ZZ)Ljava/util/List;"
      )
   )
   private List<IChatComponent> lunar$splitChatLines(IChatComponent var1, int var2, FontRenderer var3, boolean var4, boolean var5) {
      if (this.lunar$chatHeadsForCurrentMessage()) {
         var2 -= 12;
      }

      return GuiUtilRenderComponents.splitText(var1, var2, var3, var4, var5);
   }

   @Annotation2(max = 0)
   @ModifyExpressionValue(
      method = "setChatLine$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor_float$v1_7(F)I", ordinal = 0)
   )
   private int lunar$chatHeadsWrapWidth$v1_7(int var1) {
      return this.lunar$chatHeadsForCurrentMessage() ? var1 - 12 : var1;
   }

   @Redirect(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;"))
   private Object lunar$chatHeadsHoverLine(List<?> var1, int var2) {
      this.lunar$hoveredLineHasHead = ThreadModuleDump63.method4().method40().method47().method24() && this.lunar$chatHeadSkin(var2) != null;
      this.lunar$hoverPendingFirstWidth = true;
      return var1.get(var2);
   }

   @Redirect(
      method = {"getChatComponent$v1_7", "getChatComponent$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;getStringWidth(Ljava/lang/String;)I")
   )
   private int lunar$chatHeadsHoverWidth(FontRenderer var1, String var2) {
      int var3 = var1.getStringWidth(var2);
      if (this.lunar$hoverPendingFirstWidth) {
         this.lunar$hoverPendingFirstWidth = false;
         if (this.lunar$hoveredLineHasHead) {
            var3 += 12;
         }
      }

      return var3;
   }

   @Annotation2(min = 1)
   @Inject(method = "getChatComponent$v1_8", at = @At("HEAD"))
   private void lunar$resetLastHovered$v1_8(int var1, int var2, CallbackInfoReturnable<IChatComponent> var3) {
      Chat.method83(-1);
   }

   @Annotation2(max = 0)
   @Inject(method = "getChatComponent$v1_7", at = @At("HEAD"))
   private void lunar$resetLastHovered$v1_7(int var1, int var2, CallbackInfoReturnable<IChatComponent> var3) {
      Chat.method83(-1);
   }

   @Annotation2(min = 1)
   @Inject(
      method = "getChatComponent$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ChatLine;getChatComponent$v1_8()Lnet/minecraft/util/text/ITextComponent;")
   )
   private void lunar$getLastChatLineHovered$v1_8(int var1, int var2, CallbackInfoReturnable<IChatComponent> var3, @Local ChatLine var4) {
      Chat.method83(((Bridge3_13)var4).bridge$getLunarID());
   }

   @Annotation2(max = 0)
   @Inject(
      method = "getChatComponent$v1_7",
      at = @At(value = "INVOKE", target = "net/minecraft/client/gui/ChatLine.getChatComponent()Lnet/minecraft/util/IChatComponent;")
   )
   private void lunar$getLastChatLineHovered$v1_7(int var1, int var2, CallbackInfoReturnable<IChatComponent> var3, @Local ChatLine var4) {
      Chat.method83(((Bridge3_13)var4).bridge$getLunarID());
   }

   @Annotation2(min = 5)
   @Inject(method = "clearChatMessages$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$preventChatClear(boolean var1, CallbackInfo var2) {
      if (var1 && ThreadModuleDump63.method4().method40().method47().method40().get()) {
         var2.cancel();
      }
   }

   @Inject(method = "getChatOpen", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindIsChatFocused(CallbackInfoReturnable<Boolean> var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method42().get().method5())) {
         var1.setReturnValue(true);
      }
   }

   @Inject(method = "getChatOpen", at = @At("HEAD"), cancellable = true)
   private void lunar$chatPeekGetChatOpen(CallbackInfoReturnable<Boolean> var1) {
      if (ThreadModuleDump63.method4().method40().method47().method22()) {
         var1.setReturnValue(true);
      }
   }

   @ModifyVariable(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private int lunar$getRewindMouseX(int var1) {
      RewindHandlers var2 = ThreadModuleDump63.method4().method40().method85().method35();
      if (var2 != null) {
         ThreadModuleDump71 var3 = LcuiScreen.method151();
         return var2.method42().get().method8().method3(ThreadModuleDump63.method3().bridge$getTimer().method1(), var3.getScaledWidth()) * var3.getScaleFactor();
      } else {
         return var1;
      }
   }

   @ModifyVariable(method = {"getChatComponent$v1_7", "getChatComponent$v1_8"}, at = @At("HEAD"), ordinal = 1, argsOnly = true)
   private int lunar$getRewindMouseY(int var1) {
      RewindHandlers var2 = ThreadModuleDump63.method4().method40().method85().method35();
      if (var2 != null) {
         ThreadModuleDump71 var3 = LcuiScreen.method151();
         return (
               var3.getScaledHeight()
                  - var2.method42().get().method8().method4(ThreadModuleDump63.method3().bridge$getTimer().method1(), var3.getScaledHeight())
            )
            * var3.getScaleFactor();
      } else {
         return var1;
      }
   }
}
