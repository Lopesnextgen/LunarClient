package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.gui.HostWorldScreen.Type4;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.framework.Ref;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.minecraft.server.integrated.IntegratedPlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IntegratedPlayerList.class)
public abstract class IntegratedPlayerListMixin {
   public IntegratedPlayerListMixin() {
   }

   @Inject(method = "allowUserToConnect", at = @At("HEAD"), cancellable = true)
   private void lunar$allowUserToConnect(SocketAddress socketaddress1, GameProfile gameprofile2, CallbackInfoReturnable<String> callbackinforeturnable3) {
      FogIterator fogiterator4 = Ref.method4().method81();
      boolean flag5 = false;
      if (socketaddress1 instanceof InetSocketAddress inetsocketaddress6) {
         flag5 = !inetsocketaddress6.getAddress().isLoopbackAddress();
      }

      if (flag5 && fogiterator4.method20() && fogiterator4.method28().method1().method6() != Type4.LAN) {
         callbackinforeturnable3.setReturnValue("This world does not accept LAN connections.");
      }
   }
}
