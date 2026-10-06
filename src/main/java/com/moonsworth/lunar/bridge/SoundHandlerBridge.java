package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Set;

public interface SoundHandlerBridge {
   void bridge$playSound(ResourceLocationBridge horsestats141, boolean flag2, float value3);

   default void method1(ResourceLocationBridge horsestats141) {
      this.bridge$playSound(horsestats141, false, "lunar".equals(horsestats141.bridge$getDomain()) ? 1.0F : 0.25F);
   }

   void bridge$play(
      ResourceLocationBridge horsestats141, SoundSource bridgetype2_72, float value3, float value4, boolean flag5, int number6, AttenuationTypeBridge bridgetype_37, double value8, double value10, double value12
   );

   void bridge$playLunarMusic(ResourceLocationBridge horsestats141);

   void bridge$stopPlayingLunarMusic();

   void bridge$setLunarMusicVolume(float value1);

   default Object method2(String text1, float value2, boolean flag3) {
      return this.bridge$playMp3FromURL(text1, value2, flag3, true);
   }

   Object bridge$playMp3FromURL(String text1, float value2, boolean flag3, boolean flag4);

   boolean bridge$isSoundPlaying(Object obj1);

   void bridge$destroySound(Object obj1);

   void bridge$setVolume(Object obj1, float value2);

   void bridge$setSoundLocation(Object obj1, double value2, double value4, double value6);

   Set<ResourceLocationBridge> bridge$getAllRegisteredSounds();

   SoundManagerBridge bridge$getSoundEngine();

   void bridge$reload();

   boolean bridge$shouldIgnoreVolumeSettings();

   int bridge$getPlayingSoundCount();

   String bridge$getDebugString();
}
