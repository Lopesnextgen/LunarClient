package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.chat.v1.ChatButton;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.util.Slayer;

public class ChatApolloHandler extends ApolloModuleHandler {
   public static final int field4 = 320;
   public static final int field5 = 20;
   private static final int field6 = 2;
   private static final int field7 = 16;
   private static final int field8 = 25;
   private static final int field9 = 100;
   private final Map<String, ApolloButtonRenderer> field10 = new LinkedHashMap<>();

   public ChatApolloHandler() {
      super("chat", "Chat");
      this.handle(ContainerSlotRenderEvent.ContainerSlotPreEvent.class, this::method4);
      this.handle(ContainerSlotRenderEvent.ContainerSlotPostEvent.class, this::method5);
      this.handle(MarkerInputEvent.class, this::method6);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         DisplayLiveChatMessageMessage.class,
         RemoveLiveChatMessageMessage.class,
         DisplayChatButtonsMessage.class,
         RemoveChatButtonMessage.class,
         ResetChatButtonsMessage.class,
         UpdateChatButtonMessage.class
      );
   }

   @Override
   protected void onDisable() {
      this.field10.clear();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayLiveChatMessageMessage.class).ifPresent(var0 -> {
         Component var1x = Rewindhandlers3.method4(var0.getAdventureJsonLines());
         if (var1x != null) {
            Bridge5Extension4 var2 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI();
            int var3 = var0.getMessageId();
            var2.bridge$deleteMessageByLunarId(var3, true);
            var2.bridge$addMessageWithLunarId(AdventureTextBridge.asBridge(var1x), var3, true);
         }
      });
      var1.unpack(RemoveLiveChatMessageMessage.class)
         .ifPresent(var0 -> ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$deleteMessageByLunarId(var0.getMessageId(), true));
      if (this.isEnabled()) {
         var1.unpack(DisplayChatButtonsMessage.class).ifPresent(this::method3);
         var1.unpack(RemoveChatButtonMessage.class).ifPresent(var1x -> this.field10.remove(var1x.getId()));
         var1.unpack(ResetChatButtonsMessage.class).ifPresent(var1x -> this.field10.clear());
         var1.unpack(UpdateChatButtonMessage.class).ifPresent(var1x -> {
            ApolloButtonRenderer var2 = this.field10.get(var1x.getId());
            if (var2 != null) {
               var2.method2(var1x.getUpdate());
            }
         });
      }
   }

   private void method3(DisplayChatButtonsMessage var1) {
      int var2 = 0;
      int var3 = 0;

      for (ChatButton var5 : var1.getChatButtonsList()) {
         if (!var5.hasButton()) {
            var2++;
         } else {
            String var6 = var5.getButton().getId();
            int var7 = this.field10.size() - (this.field10.containsKey(var6) ? 1 : 0);
            if (var7 < 25 && var3 < 100) {
               var3++;
               ApolloButtonRenderer var8 = ApolloButtonRenderer.method1(var5.getButton(), 320.0F, 20.0F);
               if (var8 == null) {
                  var2++;
               } else {
                  this.field10.put(var8.getId(), var8);
               }
            } else {
               var2++;
            }
         }
      }

      if (var2 > 0) {
         Slayer.method4("Apollo", "Dropped " + var2 + " invalid or over-cap chat buttons");
      }
   }

   private void method4(ContainerSlotRenderEvent.ContainerSlotPreEvent var1) {
      Bridge5Extension612 var2 = this.method8(var1.method3());
      if (var2 != null) {
         boolean var3 = var2.bridge$isSuggestionOverlayVisible();
         ApolloDebugMod var4 = ThreadModuleDump63.method4().method40().method80();
         boolean var5 = var4 != null && var4.isEnabled();
         boolean var6 = var5 && var4.method15();
         if (!this.field10.isEmpty()) {
            float var7 = 2.0F;
            float var8 = var2.bridge$getHeight() - 16 - 20;
            if (var6) {
               this.method9(var1.method5(), var7, var8);
            }

            double var9 = var1.method1().HHHCHORHIHRCOHIOICICICHCRRICCI();
            double var11 = var1.method1().IHRCCHHROHIRCOOOHRRIHOORRHIOHO();

            for (ApolloButtonRenderer var14 : this.field10.values()) {
               boolean var15 = !var3 && var14.method12(var7, var8, var9, var11);
               var14.method4(var1.method5(), var7, var8, var15);
            }

            for (ApolloButtonRenderer var17 : this.field10.values()) {
               var17.method5(var1.method5(), var7, var8);
            }
         }
      }
   }

   private void method5(ContainerSlotRenderEvent.ContainerSlotPostEvent var1) {
      Bridge5Extension612 var2 = this.method8(var1.method3());
      if (var2 != null && !this.field10.isEmpty()) {
         if (!var2.bridge$isSuggestionOverlayVisible()) {
            float var3 = 2.0F;
            float var4 = var2.bridge$getHeight() - 16 - 20;
            double var5 = var1.method1().HHHCHORHIHRCOHIOICICICHCRRICCI();
            double var7 = var1.method1().IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
            ApolloButtonRenderer var9 = null;

            for (ApolloButtonRenderer var11 : this.field10.values()) {
               if (var11.method12(var3, var4, var5, var7)) {
                  var9 = var11;
               }
            }

            if (var9 != null) {
               var9.method11(var1.method5(), (int)var5, (int)var7);
            }
         }
      }
   }

   private void method6(MarkerInputEvent var1) {
      if (var1.method4() == MouseInputTypeLegacy.CLICK) {
         Bridge5Extension612 var2 = this.method8(var1.method1());
         if (var2 != null && !this.field10.isEmpty()) {
            if (!var2.bridge$isSuggestionOverlayVisible()) {
               float var3 = 2.0F;
               float var4 = var2.bridge$getHeight() - 16 - 20;
               double var5 = var1.method2().HHHCHORHIHRCOHIOICICICHCRRICCI();
               double var7 = var1.method2().IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
               ArrayList var9 = new ArrayList<>(this.field10.values());

               for (int var10 = var9.size() - 1; var10 >= 0; var10--) {
                  ApolloButtonRenderer var11 = (ApolloButtonRenderer)var9.get(var10);
                  if (var11.method12(var3, var4, var5, var7)) {
                     var1.setCancelled(true);
                     if (var1.method3() != 0) {
                        return;
                     }

                     if (var11.getRunCommand() != null) {
                        LcuiScreen.method15();
                        Rewindhandlers2.method3(this.getId(), var11.getRunCommand());
                     } else if (var11.getOpenUrl() != null) {
                        LcuiScreen.method15();
                        Rewindhandlers2.method4(this.getId(), var11.getOpenUrl());
                     } else if (var11.getClientAction() != null) {
                        LcuiScreen.method15();
                        Rewindhandlers2.method5(this.getId(), var11.getClientAction());
                     }

                     return;
                  }
               }
            }
         }
      }
   }

   public boolean method8() {
      return this.isEnabled() && !this.field10.isEmpty() && this.method8(ThreadModuleDump63.method3().bridge$getCurrentScreen()) != null;
   }

   @Nullable
   private Bridge5Extension612 method8(Bridge5Extension6 var1) {
      if (!ThreadModuleDump63.method4().method41().method6().method62().get()) {
         return null;
      } else {
         return var1 instanceof Bridge5Extension612 var2 && !var2.bridge$isBedChat() ? var2 : null;
      }
   }

   private void method9(MixinHelper_4 var1, float var2, float var3) {
      int var4 = Math.round(var2);
      int var5 = Math.round(var3);
      int var6 = var4 + 320;
      int var7 = var5 + 20;
      int var8 = -43691;
      var1.method1(var4, var5, var6, var5 + 1, var8);
      var1.method1(var4, var7 - 1, var6, var7, var8);
      var1.method1(var4, var5 + 1, var4 + 1, var7 - 1, var8);
      var1.method1(var6 - 1, var5 + 1, var6, var7 - 1, var8);
   }
}
