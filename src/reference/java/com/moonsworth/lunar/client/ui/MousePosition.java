package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data6;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;

public final class MousePosition {
   public static Data6 method1() {
      int number0;
      if (!Bridge.getMinecraftVersion().method19()) {
         number0 = Ref.method3().bridge$logicalHeight() - Bridge.method20().getY();
      } else {
         number0 = Bridge.method20().getY();
      }

      return new Data6(Bridge.method20().getX(), number0);
   }

   @Generated
   private MousePosition() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
