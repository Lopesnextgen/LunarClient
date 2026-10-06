package com.moonsworth.lunar.client.network.apollo;

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

public class BeamApolloHandler extends ApolloModuleHandler {
   private final Map<String, Beam> field4 = new HashMap<>();

   public BeamApolloHandler() {
      super("beam", "Beam");
   }

   protected void onEnable() {
      this.method3();
   }

   protected void onDisable() {
      this.method3();
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayBeaconBeamMessage.class, RemoveBeaconBeamMessage.class, ResetBeaconBeamsMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(DisplayBeaconBeamMessage.class)
         .ifPresent(
            arg1x -> {
               if (arg1x.hasLocation()) {
                  String text2 = arg1x.getId();
                  this.field4
                     .put(
                        text2,
                        Beam.builder().color(NetworkTypes.fromProtobuf(arg1x.getColor())).location(NetworkTypes.fromProtobuf(arg1x.getLocation())).build()
                     );
               }
            }
         );
      highlightimpl_31.unpack(RemoveBeaconBeamMessage.class).ifPresent(arg1x -> this.field4.remove(arg1x.getId()));
      highlightimpl_31.unpack(ResetBeaconBeamsMessage.class).ifPresent(arg1x -> this.method3());
   }

   private void method3() {
      this.field4.clear();
   }

   @Generated
   public Map<String, Beam> method4() {
      return this.field4;
   }
}
