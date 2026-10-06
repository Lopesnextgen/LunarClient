package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import com.moonsworth.lunar.client.event.options.ApolloOptionUpdateEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.combat.tntcountdown.TntCountdown;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import com.moonsworth.lunar.client.framework.Client;

public class TntCountdownApolloHandler extends ApolloModuleHandler {
   public TntCountdownApolloHandler() {
      super("tnt_countdown", "TNT Countdown");
      this.handle(ApolloOptionUpdateEvent.class, this::method4);
   }

   public Collection<Option<?, ?, ?>> method1() {
      return List.of(TntCountdownModule.TNT_TICKS);
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(SetTntCountdownMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      TntCountdown tntcountdown2 = Client.method109().method40().method38();
      highlightimpl_31.unpack(SetTntCountdownMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasEntityId()) {
            ApolloEntity apolloentity2x = NetworkTypes.fromProtobuf(arg1x.getEntityId());
            int number3 = arg1x.getDurationTicks();
            if (Ref.MC_VERSION >= 5) {
               tntcountdown2.method8(apolloentity2x.getEntityUuid(), number3);
            } else {
               tntcountdown2.method9(apolloentity2x.getEntityId(), number3);
            }
         }
      });
   }

   private void method4(ApolloOptionUpdateEvent highlightimpl2_21) {
      TntCountdown tntcountdown2 = Client.method109().method40().method38();
      if (highlightimpl2_21.getOption().equals(TntCountdownModule.TNT_TICKS)) {
         tntcountdown2.method7(((Number)highlightimpl2_21.getValue()).intValue());
      }
   }
}
