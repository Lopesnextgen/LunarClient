package com.moonsworth.lunar.v1_8.mixin;

import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerListEntryNormal.class)
public class ServerListEntryNormalMixin {
   public ServerListEntryNormalMixin() {
   }

   @Redirect(
      method = "drawEntry(IIIIIIIZ)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/FMLClientHandler;fixDescription(Ljava/lang/String;)Ljava/lang/String;")
   )
   public String ichor$drawEntry(FMLClientHandler fmlclienthandler1, String text) {
      return text;
   }

   @Redirect(
      method = "drawEntry(IIIIIIIZ)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/client/FMLClientHandler;enhanceServerListEntry(Lnet/minecraft/client/gui/ServerListEntryNormal;Lnet/minecraft/client/multiplayer/ServerData;IIIII)Ljava/lang/String;"
      )
   )
   public String ichor$enhanceServerList(FMLClientHandler fmlclienthandler1, ServerListEntryNormal serverlistentrynormal2, ServerData data, int value, int value2, int value3, int value4, int value5) {
      return null;
   }
}
