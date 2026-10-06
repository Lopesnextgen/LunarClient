package com.moonsworth.lunar.client.cosmetics.skin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

public class SkinLayerCache {
   private final SkinDataCache<com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3> field1 = new SkinDataCache<>(64, 64);
   private final SkinDataCache<com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[]> field2 = new SkinDataCache<>(64, 64);
   private final WeakHashMap<BlockEntityBridge, GameProfile> field3 = new WeakHashMap<>();
   private final WeakHashMap<ItemStackRenderStateBridge, GameProfile> field4 = new WeakHashMap<>();

   public SkinLayerCache() {
   }

   void method1(EventServerJoin event) {
      ResourceLocationBridge horsestats142 = Ref.method3().bridge$getPlayer().bridge$getLocationSkin();
      this.field1.method5(horsestats142);
      this.field2.method5(horsestats142);
   }

   void method2(EventPlayerRemove event) {
      if (event.method1() instanceof Bridge5_11 bridge5_112) {
         this.field1.method6(bridge5_112);
         this.field2.method6(bridge5_112);
      }
   }

   @Nullable
   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method3(EntityPlayerBridge bridgeextension2221) {
      return this.field1.method1(bridgeextension2221, SkinLayerFactory::method3).orElse(null);
   }

   @Nullable
   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] method4(EntityPlayerBridge bridgeextension2221, boolean flag) {
      return this.field2.method1(bridgeextension2221, arg1x -> SkinLayerFactory.method2(arg1x, flag)).orElse(null);
   }

   public void method5(BlockEntityBridge entity, GameProfile gameprofile2) {
      this.field3.put(entity, gameprofile2);
   }

   public void method6(ItemStackRenderStateBridge mixinhelper_141, GameProfile gameprofile2) {
      this.field4.put(mixinhelper_141, gameprofile2);
   }

   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method7(@Nullable GameProfile gameprofile1) {
      return gameprofile1 == null ? null : this.field1.method2(gameprofile1, SkinLayerFactory::method3).orElse(null);
   }
}
