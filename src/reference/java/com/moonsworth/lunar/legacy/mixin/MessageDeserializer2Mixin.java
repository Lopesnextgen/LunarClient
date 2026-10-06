package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.MessageDeserializer2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MessageDeserializer2.class)
public class MessageDeserializer2Mixin {
   public MessageDeserializer2Mixin() {
   }

   @VersionGate(max = 0)
   @Redirect(method = "decode$v1_7", at = @At(value = "INVOKE", target = "Lio/netty/buffer/ByteBuf;readBytes(I)Lio/netty/buffer/ByteBuf;"))
   private ByteBuf lunar$monitorBandwidth$v1_7(ByteBuf buffer1, int number2) {
      if (Ref.method4().method40().method95().isEnabled()) {
         int number3 = F3Display.field12.method6(number2);
         F3Display.field12.method5(number3 + number2);
      }

      return buffer1.readBytes(number2);
   }

   @VersionGate(min = 1)
   @Redirect(method = "decode$v1_8", at = @At(value = "INVOKE", target = "Lio/netty/buffer/ByteBuf;readBytes(I)Lio/netty/buffer/ByteBuf;"))
   private ByteBuf lunar$monitorBandwidth$v1_8(ByteBuf buffer1, int number2) {
      if (Ref.method4().method40().method95().isEnabled()) {
         int number3 = F3Display.field12.method6(number2);
         F3Display.field12.method5(number3 + number2);
      }

      return buffer1.readBytes(number2);
   }
}
