package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
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

public final class AudioStreamLoader {
   private static final Map<String, AudioInputStream> field1 = new ConcurrentHashMap<>();
   private static final Set<String> field2 = new HashSet<>();

   @Nullable
   public static AudioInputStream method1(URL var0) {
      String var1 = var0.toString();
      AudioInputStream var2 = null;
      if (field2.contains(var1)) {
         var2 = field1.remove(var1);
         if (var2 == null) {
            field2.remove(var1);
         }
      }

      return var2;
   }

   public static void method2(URL var0, Runnable var1) {
      String var2 = var0.toString();
      if (field2.contains(var2)) {
         var1.run();
      } else {
         ThreadModuleDump37.method4(() -> {
            field2.add(var2);
            field1.put(var2, method4(var0));
            var1.run();
         });
      }
   }

   public static void method3() {
      field1.clear();
      field2.clear();
   }

   private static AudioInputStream method4(URL var0) {
      try {
         AudioInputStream var1 = AudioSystem.getAudioInputStream(new BufferedInputStream(var0.openStream()));
         int var2 = ClampUtils.clamp(var1.getFormat().getChannels(), 1, 2);
         int var3 = (int)var1.getFormat().getSampleRate();
         int var4 = var1.getFormat().getSampleSizeInBits();
         var4 = var4 != -1 ? var4 : 16;
         AudioFormat var5 = new AudioFormat(Encoding.PCM_SIGNED, var3, var4, var2, var2 * 2, var3, false);
         return AudioSystem.getAudioInputStream(var5, var1);
      } catch (Exception var6) {
         Slayer.method7("Could not load MP3 audio stream!", new Object[]{var6.getMessage()});
         throw new RuntimeException(var6);
      }
   }

   @Generated
   private AudioStreamLoader() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
