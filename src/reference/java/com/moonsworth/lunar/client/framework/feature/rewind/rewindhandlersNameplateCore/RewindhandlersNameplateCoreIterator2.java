package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.google.protobuf.Any;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import java.util.ArrayList;
import lombok.NonNull;

public class RewindhandlersNameplateCoreIterator2 implements RewindhandlersNameplateCore_2 {
   @Override
   public void method1(@NonNull RewindHandlers5 var1, @NonNull Bridge5Extension_5 var2, @NonNull Itemcounter6 var3) {
      if (var1 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var3 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      ApolloModuleManager var4 = Client.method109().method84();
      ArrayList var5 = new ArrayList();

      for (ApolloModuleHandler var7 : var4.method3().values()) {
         var5.add(var4.method19(var7));
      }

      OverrideConfigurableSettingsMessage var8 = OverrideConfigurableSettingsMessage.newBuilder().addAllConfigurableSettings(var5).build();
      var1.method1(Any.pack(var8));
   }
}
