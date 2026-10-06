package com.moonsworth.lunar.client.replay.recording;

import com.google.protobuf.Any;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import java.util.ArrayList;
import lombok.NonNull;

public class ApolloSettingsCapture implements RecorderCapture {
   public ApolloSettingsCapture() {
   }

   @Override
   public void method1(@NonNull RewindRecorder rewindhandlers51, @NonNull Bridge5Extension_5 bridge5extension_52, @NonNull Itemcounter6 itemcounter63) {
      if (rewindhandlers51 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (bridge5extension_52 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (itemcounter63 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      ApolloModuleManager foghandler24 = Client.method109().method84();
      ArrayList list5 = new ArrayList();

      for (ApolloModuleHandler highlight3iterator_37 : foghandler24.method2().values()) {
         list5.add(foghandler24.method19(highlight3iterator_37));
      }

      OverrideConfigurableSettingsMessage overrideconfigurablesettingsmessage8 = OverrideConfigurableSettingsMessage.newBuilder().addAllConfigurableSettings(list5).build();
      rewindhandlers51.method1(Any.pack(overrideconfigurablesettingsmessage8));
   }
}
