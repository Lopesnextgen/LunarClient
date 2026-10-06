package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.moonsworth.lunar.client.network.apollo.PacketEnrichmentApolloHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.gui.GuiChat;
import net.optifine.gui.GuiChatOF;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(GuiChat.class)
public class GuiChatPacketEnrichmentMixin {
   public GuiChatPacketEnrichmentMixin() {
   }

   @Inject(method = "initGui", at = @At("TAIL"))
   private void apollo$handleChatOpen(CallbackInfo callback1) {
      this.apollo$sendChatPacket(true);
   }

   @Inject(method = "onGuiClosed", at = @At("TAIL"))
   private void apollo$handleChatClose(CallbackInfo callback1) {
      this.apollo$sendChatPacket(false);
   }

   @Unique
   private void apollo$sendChatPacket(boolean flag) {
      if (Ref.method3().bridge$getCurrentScreen() instanceof GuiChatOF) {
         Ref.method4()
            .method84()
            .method3(PacketEnrichmentModule.class)
            .filter(
               arg1x -> (Boolean)arg1x.getOptions()
                  .get(flag ? PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET : PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET)
            )
            .ifPresent(arg1x -> ((PacketEnrichmentApolloHandler)arg1x).method6(flag));
      }
   }
}
