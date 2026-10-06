package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityRenderStateBridge;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ColoredFireApolloHandler extends ApolloModuleHandler {
   private final Object2IntMap<UUID> field4 = new Object2IntOpenHashMap();

   public ColoredFireApolloHandler() {
      super("colored_fire", "Colored Fire");
      this.field4.defaultReturnValue(-1);
      this.handle(EventPlayerRemove.class, arg1 -> {
         if (!(Boolean)this.getOptions().get(ColoredFireModule.PERSIST_COLORS_ON_UNLOAD)) {
            this.field4.removeInt(arg1.method1().bridge$getUniqueID());
         }
      });
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideColoredFireMessage.class, ResetColoredFireMessage.class, ResetColoredFiresMessage.class);
   }

   protected void onEnable() {
      this.method8();
   }

   protected void onDisable() {
      this.method8();
   }

   public Collection<Option<?, ?, ?>> method1() {
      return List.of(ColoredFireModule.PERSIST_COLORS_ON_UNLOAD);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideColoredFireMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasPlayerUuid()) {
            UUID uuid2 = NetworkTypes.fromProtobuf(arg1x.getPlayerUuid());
            int number3 = arg1x.getColor().getColor();
            this.field4.put(uuid2, number3);
         }
      });
      highlightimpl_31.unpack(ResetColoredFireMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasPlayerUuid()) {
            UUID uuid2 = NetworkTypes.fromProtobuf(arg1x.getPlayerUuid());
            this.field4.removeInt(uuid2);
         }
      });
      highlightimpl_31.unpack(ResetColoredFiresMessage.class).ifPresent(arg1x -> this.method8());
   }

   public boolean method4(EntityRenderStateBridge bridge_611) {
      if (Ref.MC_VERSION <= 25) {
         return this.method7(((BridgeExtension)bridge_611).bridge$getUniqueID()) != -1;
      } else {
         return bridge_611 instanceof EntityPlayerBridge bridgeextension2222 ? this.method7(bridgeextension2222.bridge$getUniqueID()) != -1 : false;
      }
   }

   public int method5(EntityRenderStateBridge bridge_611) {
      if (Ref.MC_VERSION <= 25) {
         return this.method7(((BridgeExtension)bridge_611).bridge$getUniqueID());
      } else {
         return bridge_611 instanceof EntityPlayerBridge bridgeextension2222 ? this.method7(bridgeextension2222.bridge$getUniqueID()) : -1;
      }
   }

   public boolean method6(UUID uuid1) {
      return this.method7(uuid1) != -1;
   }

   public int method7(UUID uuid1) {
      return this.field4.getInt(uuid1);
   }

   private void method8() {
      this.field4.clear();
   }
}
