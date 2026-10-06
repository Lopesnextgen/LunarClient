package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockSpiritLeapDebug;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Bridge7Impl extends DualMarkerScreenLegacy {
   private final Bridge5Extension_3 field6;
   @Nullable
   private final SkyblockSpiritLeapDebug field7;

   public Bridge7Impl(DriverRouteRegistryLegacy var1, @Nullable Bridge5Extension_3 var2) {
      this(var1, var2, null);
   }

   public Bridge7Impl(DriverRouteRegistryLegacy var1, @Nullable Bridge5Extension_3 var2, @Nullable SkyblockSpiritLeapDebug var3) {
      super(var1, new DriverContextLegacy());
      this.field6 = var2;
      this.field7 = var3;
   }

   public boolean method1() {
      return this.field7 != null;
   }

   @Generated
   public Bridge5Extension_3 method4() {
      return this.field6;
   }

   @Nullable
   @Generated
   public SkyblockSpiritLeapDebug method5() {
      return this.field7;
   }
}
