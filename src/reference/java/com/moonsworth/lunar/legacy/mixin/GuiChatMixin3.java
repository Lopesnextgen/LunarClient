package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.GuiChat;
import net.optifine.gui.GuiChatOF;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(GuiChat.class)
public class GuiChatMixin3 {
   @Inject(method = "initGui", at = @At("TAIL"))
   private void apollo$handleChatOpen(CallbackInfo var1) {
      this.apollo$sendChatPacket(true);
   }

   @Inject(method = "onGuiClosed", at = @At("TAIL"))
   private void apollo$handleChatClose(CallbackInfo var1) {
      this.apollo$sendChatPacket(false);
   }

   @Unique
   private void apollo$sendChatPacket(boolean var1) {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof GuiChatOF) {
         ThreadModuleDump63.method4()
            .method84()
            .method3(PacketEnrichmentModule.class)
            .filter(
               var1x -> (Boolean)var1x.getOptions()
                  .get(var1 ? PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET : PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET)
            )
            .ifPresent(var1x -> ((Highlight3Iterator31)var1x).method6(var1));
      }
   }
}
