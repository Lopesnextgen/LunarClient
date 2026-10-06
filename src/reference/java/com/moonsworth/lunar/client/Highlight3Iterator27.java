package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator27 extends ApolloModuleHandler {
   private final Object2IntMap<UUID> field4 = new Object2IntOpenHashMap();

   public Highlight3Iterator27() {
      super("colored_fire", "Colored Fire");
      this.field4.defaultReturnValue(-1);
      this.handle(EventPlayerRemoval.class, var1 -> {
         if (!(Boolean)this.getOptions().get(ColoredFireModule.PERSIST_COLORS_ON_UNLOAD)) {
            this.field4.removeInt(var1.method1().bridge$getUniqueID());
         }
      });
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideColoredFireMessage.class, ResetColoredFireMessage.class, ResetColoredFiresMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method8();
   }

   @Override
   protected void onDisable() {
      this.method8();
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(ColoredFireModule.PERSIST_COLORS_ON_UNLOAD);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideColoredFireMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            int var3 = var1x.getColor().getColor();
            this.field4.put(var2, var3);
         }
      });
      var1.unpack(ResetColoredFireMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            this.field4.removeInt(var2);
         }
      });
      var1.unpack(ResetColoredFiresMessage.class).ifPresent(var1x -> this.method8());
   }

   public boolean method4(Bridge_61 var1) {
      if (ThreadModuleDump63.MC_VERSION <= 25) {
         return this.method7(((BridgeExtension)var1).bridge$getUniqueID()) != -1;
      } else {
         return var1 instanceof EntityPlayerBridge var2 ? this.method7(var2.bridge$getUniqueID()) != -1 : false;
      }
   }

   public int method5(Bridge_61 var1) {
      if (ThreadModuleDump63.MC_VERSION <= 25) {
         return this.method7(((BridgeExtension)var1).bridge$getUniqueID());
      } else {
         return var1 instanceof EntityPlayerBridge var2 ? this.method7(var2.bridge$getUniqueID()) : -1;
      }
   }

   public boolean method6(UUID var1) {
      return this.method7(var1) != -1;
   }

   public int method7(UUID var1) {
      return this.field4.getInt(var1);
   }

   private void method8() {
      this.field4.clear();
   }
}
