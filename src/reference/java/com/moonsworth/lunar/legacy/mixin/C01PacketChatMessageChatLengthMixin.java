package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(C01PacketChatMessage.class)
public class C01PacketChatMessageChatLengthMixin {
   public C01PacketChatMessageChatLengthMixin() {
   }

   @Redirect(method = "<init>(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(II)Ljava/lang/String;"))
   private String lunar$modifyChatLengthLimit(String text, int value, int value2) {
      Chat chat4 = Ref.method4().method40().method47();
      int number5 = chat4.method24(Minecraft.getMinecraft().isSingleplayer(), value2);
      int index6 = Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH))
         .map(arg0 -> ((Number)arg0.getOptions().get(ServerRuleModule.MAX_CHAT_LENGTH)).intValue())
         .orElse(number5);
      if (text.length() > index6) {
         text = text.substring(0, index6);
      }

      return text;
   }
}
