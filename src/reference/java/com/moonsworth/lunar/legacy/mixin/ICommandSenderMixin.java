package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.CommandSenderBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ICommandSender.class)
public interface ICommandSenderMixin extends CommandSenderBridge {
   @Shadow
   void addChatMessage(IChatComponent text1);

   @Shadow
   void sendMessage$v1_12(IChatComponent text1);

   default void bridge$addChatMessage(Bridge2_42 bridge2_421) {
      if (Ref.MC_VERSION >= 5) {
         this.sendMessage$v1_12((IChatComponent)bridge2_421);
      } else {
         this.addChatMessage((IChatComponent)bridge2_421);
      }
   }
}
