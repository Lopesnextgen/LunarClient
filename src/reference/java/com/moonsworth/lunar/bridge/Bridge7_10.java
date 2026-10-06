package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;

public interface Bridge7_10 {
   static Bridge7_10 method1(Bridge3_24 bridge3_240, ResourceLocationBridge horsestats141) {
      return Bridge.method8().method59(bridge3_240, horsestats141);
   }

   List<ShaderBridge> bridge$listShaders();

   String bridge$getShaderGroupName();

   void bridge$process(Bridge3_24 bridge3_241, float value2);

   void bridge$close();

   @VersionGate(min = 26)
   void bridge$setID$v1_21_2(ResourceLocationBridge horsestats141);
}
