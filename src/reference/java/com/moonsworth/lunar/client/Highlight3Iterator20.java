package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.glow.v1.OverrideGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Set;
import java.util.UUID;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator20 extends ApolloModuleHandler {
   public static final int field4 = Integer.MIN_VALUE;
   private final Object2IntMap<UUID> field5 = new Object2IntOpenHashMap();

   public Highlight3Iterator20() {
      super("glow", "Glow");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideGlowEffectMessage.class, ResetGlowEffectMessage.class, ResetGlowEffectsMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method6();
   }

   @Override
   protected void onDisable() {
      this.method6();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideGlowEffectMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            if (var1x.hasColor()) {
               this.field5.put(var2, var1x.getColor().getColor());
            } else {
               this.field5.put(var2, Integer.MIN_VALUE);
            }
         }
      });
      var1.unpack(ResetGlowEffectMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            this.field5.removeInt(var2);
         }
      });
      var1.unpack(ResetGlowEffectsMessage.class).ifPresent(var1x -> this.method6());
   }

   public boolean method3(BridgeExtension var1) {
      return this.field5.containsKey(var1.bridge$getUniqueID());
   }

   public int method4(BridgeExtension var1) {
      return this.method5(var1.bridge$getUniqueID());
   }

   public int method5(UUID var1) {
      return this.field5.getInt(var1);
   }

   private void method6() {
      this.field5.clear();
   }
}
