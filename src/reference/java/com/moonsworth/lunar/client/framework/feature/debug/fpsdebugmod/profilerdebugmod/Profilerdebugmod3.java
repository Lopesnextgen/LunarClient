package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.util.Annotation2;

@Annotation2
public class Profilerdebugmod3 {
   private final String branch;
   private final String hash;
   private final String mcVersion;
   private final String uuid;

   public Profilerdebugmod3(String text, String text2, String text3, String text4) {
      this.branch = text;
      this.hash = text2;
      this.mcVersion = text3;
      this.uuid = text4;
   }

   public static Profilerdebugmod3 create() {
      return new Profilerdebugmod3(
         LunarBuildData.field1, LunarBuildData.field3, Bridge.getMinecraftVersion().getDisplayName(), ThreadModuleDump63.method4().method31().method10().toString()
      );
   }
}
