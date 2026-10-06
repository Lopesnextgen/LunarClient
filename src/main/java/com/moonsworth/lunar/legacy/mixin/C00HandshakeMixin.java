package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.network.handshake.client.C00Handshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@MixinCondition(absent = "forge")
@Mixin(C00Handshake.class)
public class C00HandshakeMixin {
   public C00HandshakeMixin() {
   }

   @ModifyConstant(method = "readPacketData", constant = @Constant(intValue = 255))
   private int lunar$modifyHostnameMaxLength(int value) {
      return 32767;
   }
}
