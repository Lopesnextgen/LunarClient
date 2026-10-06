package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Set;

public interface Bridge3_11 {
   void bridge$playSound(ResourceLocationBridge var1, boolean var2, float var3);

   default void method1(ResourceLocationBridge var1) {
      this.bridge$playSound(var1, false, "lunar".equals(var1.bridge$getDomain()) ? 1.0F : 0.25F);
   }

   void bridge$play(
      ResourceLocationBridge var1, BridgeType2_7 var2, float var3, float var4, boolean var5, int var6, SoundAttenuationType var7, double var8, double var10, double var12
   );

   void bridge$playLunarMusic(ResourceLocationBridge var1);

   void bridge$stopPlayingLunarMusic();

   void bridge$setLunarMusicVolume(float var1);

   default Object method2(String var1, float var2, boolean var3) {
      return this.bridge$playMp3FromURL(var1, var2, var3, true);
   }

   Object bridge$playMp3FromURL(String var1, float var2, boolean var3, boolean var4);

   boolean bridge$isSoundPlaying(Object var1);

   void bridge$destroySound(Object var1);

   void bridge$setVolume(Object var1, float var2);

   void bridge$setSoundLocation(Object var1, double var2, double var4, double var6);

   Set<ResourceLocationBridge> bridge$getAllRegisteredSounds();

   Bridge2_47 bridge$getSoundEngine();

   void bridge$reload();

   boolean bridge$shouldIgnoreVolumeSettings();

   int bridge$getPlayingSoundCount();

   String bridge$getDebugString();
}
