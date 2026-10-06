package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.v1.Location;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;
import java.util.Set;
import net.kyori.adventure.text.Component;

public class HologramApolloHandler extends ApolloModuleHandler {
   public HologramApolloHandler() {
      super("hologram", "Hologram");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayHologramMessage.class, RemoveHologramMessage.class, ResetHologramsMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayHologramMessage.class)
         .ifPresent(
            var0 -> {
               if (var0.hasLocation()) {
                  String var1x = var0.getId();
                  Component[] var2 = var0.getAdventureJsonLinesList().stream().map(Rewindhandlers3::method4).filter(Objects::nonNull).toArray(Component[]::new);
                  Location var3 = var0.getLocation();
                  Serverholograms var4 = new Serverholograms(
                     var1x, var2, var3.getX(), var3.getY(), var3.getZ(), var0.getShowThroughWalls(), var0.getShowShadow(), var0.getShowBackground(), true
                  );
                  ThreadModuleDump63.method4().method57().method3().put(var1x, var4);
               }
            }
         );
      var1.unpack(RemoveHologramMessage.class).ifPresent(var0 -> ThreadModuleDump63.method4().method57().method3().remove(var0.getId()));
      var1.unpack(ResetHologramsMessage.class).ifPresent(var1x -> this.method3());
   }

   private void method3() {
      ThreadModuleDump63.method4().method57().method3().entrySet().removeIf(var0 -> ((Serverholograms)var0.getValue()).method3());
   }
}
