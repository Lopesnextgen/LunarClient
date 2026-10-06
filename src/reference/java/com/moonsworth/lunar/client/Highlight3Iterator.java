package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import com.moonsworth.lunar.client.event.options.OptionUpdateEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.combat.tntcountdown.TntCountdown;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.Client;

public class Highlight3Iterator extends ApolloModuleHandler {
   public Highlight3Iterator() {
      super("tnt_countdown", "TNT Countdown");
      this.handle(OptionUpdateEvent.class, this::method4);
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(TntCountdownModule.TNT_TICKS);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(SetTntCountdownMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      TntCountdown var2 = Client.method109().method40().method38();
      var1.unpack(SetTntCountdownMessage.class).ifPresent(var1x -> {
         if (var1x.hasEntityId()) {
            ApolloEntity var2x = NetworkTypes.fromProtobuf(var1x.getEntityId());
            int var3 = var1x.getDurationTicks();
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               var2.method8(var2x.getEntityUuid(), var3);
            } else {
               var2.method9(var2x.getEntityId(), var3);
            }
         }
      });
   }

   private void method4(OptionUpdateEvent var1) {
      TntCountdown var2 = Client.method109().method40().method38();
      if (var1.getOption().equals(TntCountdownModule.TNT_TICKS)) {
         var2.method7(((Number)var1.getValue()).intValue());
      }
   }
}
