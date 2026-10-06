package com.moonsworth.lunar.client.network.mumble;

import lombok.Generated;

public final class MumbleLink {
   private static boolean MUMBLELINK_LOADED = false;

   static synchronized void loadNativeLibrary() {
      if (!MUMBLELINK_LOADED) {
         try {
            System.loadLibrary("MumbleLink");
         } catch (UnsatisfiedLinkError unsatisfiedlinkerror1) {
            unsatisfiedlinkerror1.printStackTrace();
         }

         MUMBLELINK_LOADED = true;
      }
   }

   public native int init();

   public native void update(LinkData linkdata1);

   public boolean isLoaded() {
      return MUMBLELINK_LOADED;
   }

   @Generated
   public MumbleLink() {
   }

   static {
      loadNativeLibrary();
   }
}
