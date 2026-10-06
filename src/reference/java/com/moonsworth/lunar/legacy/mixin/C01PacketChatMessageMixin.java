package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(C01PacketChatMessage.class)
public abstract class C01PacketChatMessageMixin {
   public C01PacketChatMessageMixin() {
   }

   @ModifyConstant(method = {"<init>(Ljava/lang/String;)V", "readPacketData"}, constant = {@Constant(intValue = 100), @Constant(intValue = 256)})
   public int apollo$setMaxLength(int value) {
      return Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH))
         .map(arg0 -> ((Number)arg0.getOptions().get(ServerRuleModule.BRIGHTNESS)).intValue())
         .orElse(value);
   }
}
