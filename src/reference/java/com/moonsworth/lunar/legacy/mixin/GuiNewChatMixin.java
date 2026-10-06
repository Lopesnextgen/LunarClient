package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge3_13;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import net.kyori.adventure.text.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin extends Gui implements Bridge5Extension4 {
   @Final
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public List<ChatLine> drawnChatLines;
   @Final
   @Shadow
   public List chatLines$v1_7;
   @Final
   @Shadow
   public List<ChatLine> chatLines;
   @Final
   @Shadow
   public List field_146253_i$v1_7;
   @Unique
   private int lunar$messageId = -1;
   @Unique
   private boolean lunar$msgAddedByApollo = false;

   @Shadow
   public abstract boolean getChatOpen();

   @Shadow
   public abstract void printChatMessage(IChatComponent var1);

   @Shadow
   public abstract void printChatMessage(IChatComponent var1);

   @Shadow
   public abstract void printChatMessageWithOptionalDeletion(IChatComponent var1, int var2);

   @Shadow
   public abstract void printChatMessageWithOptionalDeletion(IChatComponent var1, int var2);

   @Shadow
   public abstract void clearChatMessages();

   @Shadow
   public abstract void clearChatMessages(boolean var1);

   @Shadow
   public abstract void refreshChat();

   @Override
   public void bridge$addMessage(Bridge2_42 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.printChatMessage((IChatComponent)var1);
      } else {
         this.printChatMessage((IChatComponent)var1);
      }
   }

   @Override
   public void bridge$addMessageWithLunarId(Bridge2_42 var1, int var2, boolean var3) {
      this.lunar$messageId = var2;
      this.lunar$msgAddedByApollo = var3;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.printChatMessage((IChatComponent)var1);
      } else {
         this.printChatMessage((IChatComponent)var1);
      }

      this.lunar$msgAddedByApollo = false;
      this.lunar$messageId = -1;
   }

   @Override
   public int bridge$addMessageWithLunarId(Bridge2_42 var1) {
      int var2;
      do {
         var2 = ThreadLocalRandom.current().nextInt();
      } while (var2 == 0 || this.bridge$getMessageContentByLunarId(var2) != null);

      this.bridge$addMessageWithLunarId(var1, var2, false);
      return var2;
   }

   @Override
   public void bridge$deleteMessageByLunarId(int var1, boolean var2) {
      Predicate var3 = var2x -> {
         Bridge3_13 var3x = (Bridge3_13)var2x;
         return var3x.bridge$getLunarID() == var1 && var3x.bridge$isAddedByApollo() == var2;
      };
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.chatLines.removeIf(var3);
         this.drawnChatLines.removeIf(var3);
      } else {
         this.chatLines$v1_7.removeIf(var3);
         this.field_146253_i$v1_7.removeIf(var3);
      }
   }

   @Nullable
   @Override
   public Component bridge$getMessageContentByLunarId(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         for (ChatLine var3 : this.chatLines) {
            if (((Bridge3_13)var3).bridge$getLunarID() == var1) {
               return AdventureTextBridge.asAdventure((Bridge2_42)var3.getChatComponent());
            }
         }
      } else {
         for (ChatLine var5 : this.chatLines$v1_7) {
            if (((Bridge3_13)var5).bridge$getLunarID() == var1) {
               return AdventureTextBridge.asAdventure((Bridge2_42)var5.getChatComponent());
            }
         }
      }

      return null;
   }

   @Override
   public int bridge$getLunarId() {
      return this.lunar$messageId;
   }

   @Override
   public void bridge$clearChatMessages() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.clearChatMessages(true);
      } else {
         this.clearChatMessages();
      }
   }

   @Override
   public boolean bridge$messageAddedByApollo() {
      return this.lunar$msgAddedByApollo;
   }

   @Override
   public boolean bridge$getChatOpen() {
      return this.getChatOpen();
   }

   @Override
   public void bridge$rescaleChat() {
      this.refreshChat();
   }
}
