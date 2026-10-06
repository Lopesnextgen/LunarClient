package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public final class ThreadModuleDump85 {
   public static MarkerModel.Data6 get() {
      int var0;
      if (!Bridge.getMinecraftVersion().method19()) {
         var0 = ThreadModuleDump63.method3().bridge$logicalHeight() - Bridge.method20().getY();
      } else {
         var0 = Bridge.method20().getY();
      }

      return new MarkerModel.Data6(Bridge.method20().getX(), var0);
   }

   @Generated
   private ThreadModuleDump85() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
