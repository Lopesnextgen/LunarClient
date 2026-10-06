package com.moonsworth.lunar.v1_8.mixin;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiScreen.class)
public class GuiScreenMixin {
   public GuiScreenMixin() {
   }

   @Redirect(
      method = "sendChatMessage(Ljava/lang/String;Z)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/client/ClientCommandHandler;executeCommand(Lnet/minecraft/command/ICommandSender;Ljava/lang/String;)I"
      )
   )
   private int fixInvalidCommands(ClientCommandHandler clientcommandhandler1, ICommandSender icommandsender2, String text3) {
      return text3.startsWith("/") ? clientcommandhandler1.executeCommand(icommandsender2, text3) : 0;
   }
}
