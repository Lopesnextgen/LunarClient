package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.driver.DriverViewContext;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.framework.Ref;

public class ShaderCloakEditorContext implements DriverViewContext {
   public ShaderCloakEditorContext() {
   }

   public void method1(AbstractRenderContext bridgeextension_91, Data5 data52) {
      ShaderDebugMod shaderdebugmod3 = Ref.method4().method40().method74();
      if (shaderdebugmod3.isValid()) {
         Shaderdebugmod shaderdebugmod4 = shaderdebugmod3.method26();
         if (shaderdebugmod4 != null) {
            shaderdebugmod4.method34(bridgeextension_91, data52.HHHCHORHIHRCOHIOICICICHCRRICCI(), data52.IHRCCHHROHIRCOOOHRRIHOORRHIOHO());
         }
      }
   }
}
