package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat.Encoding;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class AudioStreamCache {
   private static final Map<String, AudioInputStream> field1 = new ConcurrentHashMap<>();
   private static final Set<String> field2 = new HashSet<>();

   @Nullable
   public static AudioInputStream method1(URL url0) {
      String text1 = url0.toString();
      AudioInputStream audioinputstream2 = null;
      if (field2.contains(text1)) {
         audioinputstream2 = field1.remove(text1);
         if (audioinputstream2 == null) {
            field2.remove(text1);
         }
      }

      return audioinputstream2;
   }

   public static void method2(URL url0, Runnable runnable1) {
      String text2 = url0.toString();
      if (field2.contains(text2)) {
         runnable1.run();
      } else {
         BackgroundExecutor.method4(() -> {
            field2.add(text2);
            field1.put(text2, method4(url0));
            runnable1.run();
         });
      }
   }

   public static void method3() {
      field1.clear();
      field2.clear();
   }

   private static AudioInputStream method4(URL url0) {
      try {
         AudioInputStream audioinputstream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(url0.openStream()));
         int number2 = ClampUtils.clamp(audioinputstream1.getFormat().getChannels(), 1, 2);
         int number3 = (int)audioinputstream1.getFormat().getSampleRate();
         int number4 = audioinputstream1.getFormat().getSampleSizeInBits();
         number4 = number4 != -1 ? number4 : 16;
         AudioFormat audioformat5 = new AudioFormat(Encoding.PCM_SIGNED, number3, number4, number2, number2 * 2, number3, false);
         return AudioSystem.getAudioInputStream(audioformat5, audioinputstream1);
      } catch (Exception exception6) {
         LunarLogger.method7("Could not load MP3 audio stream!", new Object[]{exception6.getMessage()});
         throw new RuntimeException(exception6);
      }
   }

   @Generated
   private AudioStreamCache() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
