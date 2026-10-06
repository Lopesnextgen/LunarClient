package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.DriverContext;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockSpiritLeapDebug;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SpiritLeapGuiContainer extends DualMarkerScreenLegacy {
   private final GuiContainerBridge field6;
   @Nullable
   private final SkyblockSpiritLeapDebug field7;

   public SpiritLeapGuiContainer(DriverRouteRegistry markers2handler21, @Nullable GuiContainerBridge bridge5extension_32) {
      this(markers2handler21, bridge5extension_32, null);
   }

   public SpiritLeapGuiContainer(DriverRouteRegistry markers2handler21, @Nullable GuiContainerBridge bridge5extension_32, @Nullable SkyblockSpiritLeapDebug skyblockspiritleapdebug3) {
      super(markers2handler21, new DriverContext());
      this.field6 = bridge5extension_32;
      this.field7 = skyblockspiritleapdebug3;
   }

   public boolean method1() {
      return this.field7 != null;
   }

   @Generated
   public GuiContainerBridge method4() {
      return this.field6;
   }

   @Nullable
   @Generated
   public SkyblockSpiritLeapDebug method5() {
      return this.field7;
   }
}
