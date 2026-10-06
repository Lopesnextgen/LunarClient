package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator6 extends ApolloModuleHandler {
   private final Map<String, Beam> field4 = new HashMap<>();

   public Highlight3Iterator6() {
      super("beam", "Beam");
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
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayBeaconBeamMessage.class, RemoveBeaconBeamMessage.class, ResetBeaconBeamsMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(DisplayBeaconBeamMessage.class)
         .ifPresent(
            var1x -> {
               if (var1x.hasLocation()) {
                  String var2 = var1x.getId();
                  this.field4
                     .put(
                        var2,
                        Beam.builder().color(NetworkTypes.fromProtobuf(var1x.getColor())).location(NetworkTypes.fromProtobuf(var1x.getLocation())).build()
                     );
               }
            }
         );
      highlightImpl_3.unpack(RemoveBeaconBeamMessage.class).ifPresent(var1x -> this.field4.remove(var1x.getId()));
      highlightImpl_3.unpack(ResetBeaconBeamsMessage.class).ifPresent(var1x -> this.method3());
   }

   private void method3() {
      this.field4.clear();
   }

   @Generated
   public Map<String, Beam> method4() {
      return this.field4;
   }
}
