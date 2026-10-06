package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.cooldowns.mixin.CooldownsImpl;
import com.moonsworth.lunar.client.framework.feature.cooldowns.mixin.CooldownsImpl2;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.combat.cooldowns.Cooldowns;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator25 extends ApolloModuleHandler {
   public Highlight3Iterator25() {
      super("cooldown", "Cooldown");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayCooldownMessage.class, RemoveCooldownMessage.class, ResetCooldownsMessage.class);
   }

   @Override
   protected void onEnable() {
      Cooldowns.method13();
   }

   @Override
   protected void onDisable() {
      Cooldowns.method13();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayCooldownMessage.class).ifPresent(var1x -> {
         if (var1x.hasIcon()) {
            String var2 = var1x.getName();
            long var3 = NetworkTypes.fromProtobuf(var1x.getDuration()).toMillis();
            Icon var5 = NetworkTypes.fromProtobuf(var1x.getIcon());
            this.method3(var2, var3, var5, var1x.hasStyle() ? var1x.getStyle() : null);
         }
      });
      var1.unpack(RemoveCooldownMessage.class).ifPresent(var0 -> Cooldowns.method5(var0.getName()));
      var1.unpack(ResetCooldownsMessage.class).ifPresent(var0 -> Cooldowns.method13());
   }

   private void method3(String var1, long var2, Icon var4, CooldownStyle var5) {
      if (var4 instanceof ItemStackIcon var11) {
         ItemStackBridge var12 = Rewindhandlers3.method2(var11);
         if (var12 != null) {
            Cooldowns.method4(new CooldownsImpl(var1, var2, var12, var5));
         }
      } else {
         String var7 = null;
         if (var4 instanceof AdvancedResourceLocationIcon var8) {
            var7 = var8.getResourceLocation();
         } else if (var4 instanceof SimpleResourceLocationIcon var9) {
            var7 = var9.getResourceLocation();
         }

         if (var7 == null) {
            Rewindhandlers3.method3(null, var7);
         } else {
            ResourceLocationBridge var6;
            try {
               var6 = ResourceLocationBridge.create(var7);
            } catch (Exception var10) {
               Rewindhandlers3.method3(var10, var7);
               return;
            }

            Cooldowns.method4(new CooldownsImpl2(var1, var2, var6, var4, var5));
         }
      }
   }
}
