package com.moonsworth.lunar.client.framework.feature.pkg;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

public class Pkg4 {
   private final Pkg2<com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3> field1 = new Pkg2<>(64, 64);
   private final Pkg2<com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[]> field2 = new Pkg2<>(64, 64);
   private final WeakHashMap<HitcolorExtension, GameProfile> field3 = new WeakHashMap<>();
   private final WeakHashMap<ItemStackRenderStateBridge, GameProfile> field4 = new WeakHashMap<>();

   void method1(ServerJoinEvent var1) {
      ResourceLocationBridge var2 = ThreadModuleDump63.method3().bridge$getPlayer().bridge$getLocationSkin();
      this.field1.method5(var2);
      this.field2.method5(var2);
   }

   void method2(EventPlayerRemoval var1) {
      if (var1.method1() instanceof Bridge5_11 var2) {
         this.field1.method6(var2);
         this.field2.method6(var2);
      }
   }

   @Nullable
   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method3(EntityPlayerBridge var1) {
      return this.field1.method1(var1, Pkg5::method3).orElse(null);
   }

   @Nullable
   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] method4(EntityPlayerBridge var1, boolean var2) {
      return this.field2.method1(var1, var1x -> Pkg5.method2(var1x, var2)).orElse(null);
   }

   public void method5(HitcolorExtension var1, GameProfile var2) {
      this.field3.put(var1, var2);
   }

   public void method6(ItemStackRenderStateBridge var1, GameProfile var2) {
      this.field4.put(var1, var2);
   }

   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method7(@Nullable GameProfile var1) {
      return var1 == null ? null : this.field1.method2(var1, Pkg5::method3).orElse(null);
   }
}
