package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.bridge.Bridge2_38;
import com.moonsworth.lunar.bridge.CommandSenderBridge;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientCommandHandler.class)
public class ClientCommandHandlerMixin {
   public ClientCommandHandlerMixin() {
   }

   @Redirect(
      method = "executeCommand",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/command/ICommand;processCommand(Lnet/minecraft/command/ICommandSender;[Ljava/lang/String;)V")
   )
   private void impl$processCommand(ICommand icommand1, ICommandSender icommandsender2, String[] items3) {
      if (((Bridge2_38)icommand1).method1((CommandSenderBridge)icommandsender2)) {
         icommand1.processCommand(icommandsender2, items3);
      }
   }
}
