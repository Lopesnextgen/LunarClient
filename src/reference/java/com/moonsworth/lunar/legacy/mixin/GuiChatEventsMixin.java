package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventComponentMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatSendLegacy;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import com.moonsworth.lunar.legacy.MixinMisc;
import java.io.File;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.event.ClickEvent.Action;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChat.class)
public abstract class GuiChatEventsMixin extends GuiScreen {
   @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiChat;drawRect(IIIII)V"))
   private void lunar$applyChatAlpha(int var1, int var2, int var3, int var4, int var5) {
      Chat var6 = ThreadModuleDump63.method4().method40().method47();
      drawRect(var1, var2, var3, var4, ThreadModuleDump23.method18(var5, var6.method19()));
   }

   @Annotation2(min = 5)
   @ModifyExpressionValue(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;getChatComponent$v1_8(II)Lnet/minecraft/util/text/ITextComponent;")
   )
   private IChatComponent lunar$onComponentHover$v1_12(IChatComponent var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      if (var2.isEnabled() && var2.method66().get() && var1 != null) {
         ChatStyle var3 = var1.getStyle$v1_12();
         if (var3 != null) {
            ClickEvent var4 = var3.getClickEvent$v1_12();
            if (var4 != null && var4.getAction() == Action.OPEN_URL) {
               if (var2.method86().method6(var4.getValue())) {
                  return null;
               }

               return var1;
            }
         }

         return var2.method86().method6(null) ? null : var1;
      } else {
         var2.method86().method9();
         return var1;
      }
   }

   @Annotation2(min = 1, max = 1)
   @ModifyExpressionValue(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;getChatComponent$v1_8(II)Lnet/minecraft/util/text/ITextComponent;")
   )
   private IChatComponent lunar$onComponentHover$v1_8(IChatComponent var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      if (var2.isEnabled() && var2.method66().get() && var1 != null) {
         ChatStyle var3 = var1.getChatStyle();
         if (var3 != null) {
            ClickEvent var4 = var3.getChatClickEvent();
            if (var4 != null && var4.getAction() == Action.OPEN_URL) {
               if (var2.method86().method6(var4.getValue())) {
                  return null;
               }

               return var1;
            }
         }

         return var2.method86().method6(null) ? null : var1;
      } else {
         var2.method86().method9();
         return var1;
      }
   }

   @Annotation2(max = 0)
   @ModifyExpressionValue(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;getChatComponent$v1_7(II)Lnet/minecraft/util/IChatComponent;")
   )
   private IChatComponent lunar$onComponentHover(IChatComponent var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      if (var2.isEnabled() && var2.method66().get() && var1 != null) {
         ChatStyle var3 = var1.getChatStyle();
         if (var3 != null) {
            ClickEvent var4 = var3.getChatClickEvent();
            if (var4 != null && var4.getAction() == Action.OPEN_URL) {
               if (var2.method86().method6(var4.getValue())) {
                  return null;
               }

               return var1;
            }
         }

         return var2.method86().method6(null) ? null : var1;
      } else {
         var2.method86().method9();
         return var1;
      }
   }

   @Annotation2(max = 0)
   @WrapMethod(method = "submitChatMessage$v1_7")
   private void lunar$wrapChatMessage(String var1, Operation<Void> var2) {
      EventChatSendLegacy var3 = ClientEventBus.method29().method12(EventChatSendLegacy.class, () -> new EventChatSendLegacy(var1));
      boolean var4 = var3 != null && var3.isCancelled();
      if (!var4) {
         String var5 = var3 == null ? var1 : var3.getMessage();
         Chat.method85(var5);
         var2.call(new Object[]{var5});
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "mouseClicked(III)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/IChatComponent;getChatStyle()Lnet/minecraft/util/ChatStyle;")
   )
   private ChatStyle lunar$mouseClick(IChatComponent var1) {
      EventComponentMessageLegacy var2 = ClientEventBus.method29()
         .method12(EventComponentMessageLegacy.class, () -> new EventComponentMessageLegacy(AdventureTextBridge.asAdventure((Bridge2_42)var1), var1.getUnformattedTextForChat()));
      return var2 != null && var2.isCancelled() ? new ChatStyle() : var1.getChatStyle();
   }

   @Annotation2(min = 1)
   public boolean handleComponentClick(IChatComponent var1) {
      if (var1 == null) {
         return super.handleComponentClick(null);
      }

      EventComponentMessageLegacy var2 = ClientEventBus.method29().method12(EventComponentMessageLegacy.class, () -> {
         String var1x = ThreadModuleDump63.MC_VERSION >= 5 ? var1.getUnformattedComponentText$v1_12() : var1.getUnformattedTextForChat();
         return new EventComponentMessageLegacy(AdventureTextBridge.asAdventure((Bridge2_42)var1), var1x);
      });
      return var2 != null && var2.isCancelled() ? false : super.handleComponentClick(var1);
   }

   @Annotation2(max = 0)
   @Inject(method = "openLink$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$openFileWithDefaultApp$v1_7(URI var1, CallbackInfo var2) {
      if ("file".equals(var1.getScheme())) {
         MixinMisc.method1().openFile(new File(var1));
         var2.cancel();
      }
   }

   @WrapWithCondition(
      method = "keyTyped",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   private boolean lunar$dontOverrideCustomScreen(Minecraft var1, GuiScreen var2) {
      return var2 != null || !(var1.currentScreen instanceof Bridge5Extension62);
   }

   @ModifyArg(method = "initGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiTextField;setMaxStringLength(I)V"))
   private int apollo$setMaxStringLength(int var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      var1 = var2.method24(Minecraft.getMinecraft().isSingleplayer(), var1);
      return ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(ServerRuleModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH))
         .map(var0 -> ((Number)var0.getOptions().get(ServerRuleModule.BRIGHTNESS)).intValue())
         .orElse(var1);
   }

   @Inject(method = "initGui", at = @At("TAIL"))
   @Annotation_2(absent = "optifine", available = @Annotation2(min = 1))
   private void apollo$handleChatOpen(CallbackInfo var1) {
      this.apollo$sendChatPacket(true);
   }

   @Inject(method = "onGuiClosed", at = @At("TAIL"))
   @Annotation_2(absent = "optifine", available = @Annotation2(min = 1))
   private void apollo$handleChatClose(CallbackInfo var1) {
      this.apollo$sendChatPacket(false);
   }

   @Unique
   private void apollo$sendChatPacket(boolean var1) {
      ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(PacketEnrichmentModule.class)
         .filter(
            var1x -> (Boolean)var1x.getOptions().get(var1 ? PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET : PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET)
         )
         .ifPresent(var1x -> ((Highlight3Iterator31)var1x).method6(var1));
   }

   @Annotation2(max = 0)
   @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
   private void lunar$preventRewindSystemClickEvent$v1_7(int var1, int var2, int var3, CallbackInfo var4) {
      if (var3 == 0 && this.mc.gameSettings.chatLinks && ThreadModuleDump63.method4().method40().method85().method19() && !isShiftKeyDown()) {
         IChatComponent var5 = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
         if (var5 != null) {
            ClickEvent var6 = var5.getChatStyle().getChatClickEvent();
            if (var6 != null && (var6.getAction() == Action.OPEN_FILE || var6.getAction() == Action.OPEN_URL)) {
               var4.cancel();
            }
         }
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "mouseClicked",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;chatLinksPrompt:Z", opcode = 180, shift = Shift.BEFORE),
      cancellable = true
   )
   private void lunar$maliciousUrlWarning$v1_7(int var1, int var2, int var3, CallbackInfo var4) {
      URI var5 = this.lunar$getClickEventValue();
      if (ThreadModuleDump63.method4().method93().method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var5)) {
         var4.cancel();
      }
   }

   @Annotation2(max = 0)
   @Unique
   private URI lunar$getClickEventValue() {
      IChatComponent var1 = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
      if (var1 == null) {
         return null;
      }

      ChatStyle var2 = var1.getChatStyle();
      if (var2 == null) {
         return null;
      }

      ClickEvent var3 = var2.getChatClickEvent();
      if (var3 != null && var3.getAction() == Action.OPEN_URL) {
         try {
            return new URI(var3.getValue());
         } catch (Exception var5) {
         }
      }

      return null;
   }
}
