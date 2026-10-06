package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.NameTagRenderEvent;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.moonsworth.lunar.client.framework.Client;

public class NametagApolloHandler extends ApolloModuleHandler {
   private final ConcurrentHashMap<UUID, Boolean> field4 = new ConcurrentHashMap<>();

   public NametagApolloHandler() {
      super("nametag", "Nametag");
      this.handle(NameTagRenderEvent.class, this::method3);
      this.handle(ServerChangeEvent.class, this::method4);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideNametagMessage.class, ResetNametagMessage.class, ResetNametagsMessage.class);
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
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideNametagMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            List var3 = var1x.getAdventureJsonLinesList().stream().map(Rewindhandlers3::method4).filter(Objects::nonNull).toList();
            Client.method109().method58().method3().put(var2, var3);
            switch (var1x.getVisibilityOverride()) {
               case NAMETAG_VISIBILITY_OVERRIDE_SHOWN:
                  this.field4.put(var2, true);
                  break;
               case NAMETAG_VISIBILITY_OVERRIDE_HIDDEN:
                  this.field4.put(var2, false);
                  break;
               default:
                  this.field4.remove(var2);
            }
         }
      });
      var1.unpack(ResetNametagMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            Client.method109().method58().method3().remove(var2);
            this.field4.remove(var2);
         }
      });
      var1.unpack(ResetNametagsMessage.class).ifPresent(var1x -> this.method8());
   }

   private void method3(NameTagRenderEvent var1) {
      if (!var1.isCancelled()) {
         if (ThreadModuleDump63.method4().method40().method51().isEnabled()) {
            if (var1.method2() instanceof EntityPlayerBridge var2) {
               List var4 = (List)ThreadModuleDump63.method4().method58().method3().get(var2.bridge$getUniqueID());
               if (var4 != null) {
                  var1.getLines().clear();
                  var1.getLines().addAll(var4);
               }
            }
         }
      }
   }

   private void method4(ServerChangeEvent var1) {
      this.field4.clear();
   }

   public boolean method5(UUID var1) {
      return this.field4.getOrDefault(var1, false);
   }

   public boolean method6(UUID var1) {
      return !this.field4.getOrDefault(var1, true);
   }

   private void method8() {
      Client.method109().method58().clear();
      this.field4.clear();
   }
}
