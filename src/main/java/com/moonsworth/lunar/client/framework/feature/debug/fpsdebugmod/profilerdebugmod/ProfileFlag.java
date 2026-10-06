package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.ichor.util.KeepName;

@KeepName
public class ProfileFlag {
   private final boolean shouldProfile;

   public ProfileFlag(boolean flag) {
      this.shouldProfile = flag;
   }
}
