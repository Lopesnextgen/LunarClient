package com.moonsworth.lunar.client.network.apollo;

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

public class GlowApolloHandler extends ApolloModuleHandler {
   public static final int field4 = Integer.MIN_VALUE;
   private final Object2IntMap<UUID> field5 = new Object2IntOpenHashMap();

   public GlowApolloHandler() {
      super("glow", "Glow");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideGlowEffectMessage.class, ResetGlowEffectMessage.class, ResetGlowEffectsMessage.class);
   }

   protected void onEnable() {
      this.method6();
   }

   protected void onDisable() {
      this.method6();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideGlowEffectMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasPlayerUuid()) {
            UUID uuid2 = NetworkTypes.fromProtobuf(arg1x.getPlayerUuid());
            if (arg1x.hasColor()) {
               this.field5.put(uuid2, arg1x.getColor().getColor());
            } else {
               this.field5.put(uuid2, Integer.MIN_VALUE);
            }
         }
      });
      highlightimpl_31.unpack(ResetGlowEffectMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasPlayerUuid()) {
            UUID uuid2 = NetworkTypes.fromProtobuf(arg1x.getPlayerUuid());
            this.field5.removeInt(uuid2);
         }
      });
      highlightimpl_31.unpack(ResetGlowEffectsMessage.class).ifPresent(arg1x -> this.method6());
   }

   public boolean method3(BridgeExtension bridgeextension1) {
      return this.field5.containsKey(bridgeextension1.bridge$getUniqueID());
   }

   public int method4(BridgeExtension bridgeextension1) {
      return this.method5(bridgeextension1.bridge$getUniqueID());
   }

   public int method5(UUID uuid1) {
      return this.field5.getInt(uuid1);
   }

   private void method6() {
      this.field5.clear();
   }
}
