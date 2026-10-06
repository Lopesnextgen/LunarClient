package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(C01PacketChatMessage.class)
public class C01PacketChatMessageMixin2 {
   @Redirect(method = "<init>(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(II)Ljava/lang/String;"))
   private String lunar$modifyChatLengthLimit(String text, int value, int value2) {
      Chat var4 = ThreadModuleDump63.method4().method40().method47();
      int var5 = var4.method24(Minecraft.getMinecraft().isSingleplayer(), value2);
      int var6 = ThreadModuleDump63.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH))
         .map(var0 -> ((Number)var0.getOptions().get(ServerRuleModule.BRIGHTNESS)).intValue())
         .orElse(var5);
      if (text.length() > var6) {
         text = text.substring(0, var6);
      }

      return text;
   }
}
