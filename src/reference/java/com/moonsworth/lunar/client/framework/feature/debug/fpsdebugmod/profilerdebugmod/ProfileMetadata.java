package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.util.KeepName;

@KeepName
public class ProfileMetadata {
   private final String branch;
   private final String hash;
   private final String mcVersion;
   private final String uuid;

   public ProfileMetadata(String text, String text2, String text3, String text4) {
      this.branch = text;
      this.hash = text2;
      this.mcVersion = text3;
      this.uuid = text4;
   }

   public static ProfileMetadata create() {
      return new ProfileMetadata(
         LunarBuildData.field1, LunarBuildData.field3, Bridge.getMinecraftVersion().getDisplayName(), Ref.method4().method31().method10().toString()
      );
   }
}
