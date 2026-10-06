package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.google.protobuf.Any;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import lombok.Generated;

public class Nameplate2Impl2 extends Nameplate2 {
   private byte[] data;

   @Override
   public void method1(ByteBufLoader var1) {
      this.data = var1.readByteArray();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method2(this.data);
   }

   @Override
   public void method3(Nameplate4 var1) {
      EntityRenderer4 var2 = Client.method109().method35();
      ApolloModuleManager var3 = Client.method109().method84();
      if (var2 != null) {
         Any var4 = Any.parseFrom(this.data);

         try {
            var2.method110(true);
            if (!var4.is(OverrideConfigurableSettingsMessage.class) && !var4.is(ConfigurableSettings.class)) {
               var2.method2(var4);
            } else {
               var3.method18(var4);
            }
         } finally {
            var2.method110(false);
         }
      }
   }

   @Generated
   public Nameplate2Impl2(byte[] var1) {
      this.data = var1;
   }

   @Generated
   public Nameplate2Impl2() {
   }
}
