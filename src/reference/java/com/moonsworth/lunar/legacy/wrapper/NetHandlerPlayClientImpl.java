package com.moonsworth.lunar.legacy.wrapper;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;

public class NetHandlerPlayClientImpl extends NetHandlerPlayClient {
   private static final UUID field1 = new UUID(0L, 0L);

   @Annotation2(5)
   public NetHandlerPlayClientImpl(Minecraft var1) {
      super(var1, var1.currentScreen, (NetworkManager_v1_12)DummyNetworkManagerProvider.method1(), new GameProfile(field1, var1.getSession().getProfile().getName()));
   }

   @Annotation2(1)
   public NetHandlerPlayClientImpl(Minecraft var1, int var2) {
      super(var1, var1.currentScreen, (NetworkManager)DummyNetworkManagerProvider.method1(), new GameProfile(field1, var1.getSession().getProfile().getName()));
   }

   @Annotation2(max = 0)
   public NetHandlerPlayClientImpl(Minecraft var1, boolean var2) {
      super(var1, var1.currentScreen, (NetworkManager_v1_7)DummyNetworkManagerProvider.method1());
   }

   public static NetHandlerPlayClientImpl method1(Minecraft var0) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return new NetHandlerPlayClientImpl(var0);
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? new NetHandlerPlayClientImpl(var0, 0) : new NetHandlerPlayClientImpl(var0, false);
      }
   }
}
