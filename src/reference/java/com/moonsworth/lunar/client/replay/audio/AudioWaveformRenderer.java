package com.moonsworth.lunar.client.replay.audio;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.AudioSegment;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class AudioWaveformRenderer {
   private final File field1;
   private final Map<String, List<Float>> field2 = new HashMap<>();
   private final Executor field3 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-waveform-thread", true));
   private final Map<UUID, String> field4 = new HashMap<>();
   private final Set<String> field5 = new HashSet<>();
   private static final float field6 = -48.0F;

   public List<Float> method1(String text1, com.moonsworth.lunar.client.replay.audio.AudioStream<?> rewindhandlers22) {
      if (rewindhandlers22.getKey() != null) {
         text1 = text1 + "$" + rewindhandlers22.getKey();
      }

      if (this.field2.containsKey(text1)) {
         return this.field2.get(text1);
      }

      File file3 = new File(this.field1, "audio_cache");
      file3.mkdirs();
      File file4 = new File(file3, "waveform_" + text1);
      if (file4.exists()) {
         ArrayList list15 = new ArrayList();
         this.field2.put(text1, list15);

         try (FileInputStream stream16 = new FileInputStream(file4)) {
            byte[] items17 = new byte[4096];

            while (stream16.read(items17) != -1) {
               for (byte index18 = 0; index18 < items17.length; index18 += 4) {
                  int number19 = (items17[index18] & 255) << 24 | (items17[index18 + 1] & 255) << 16 | (items17[index18 + 2] & 255) << 8 | items17[index18 + 3] & 255;
                  float value20 = Float.intBitsToFloat(number19);
                  list15.add(value20);
               }
            }
         }

         return list15;
      } else {
         List list5 = rewindhandlers22.method2();
         this.field2.put(text1, list5);

         try (FileOutputStream stream6 = new FileOutputStream(file4)) {
            for (short index7 = 0; index7 < list5.size(); index7 += 1024) {
               byte[] items8 = new byte[4096];

               for (int index9 = 0; index9 < 1024 && index7 + index9 < list5.size(); index9++) {
                  int number10 = Float.floatToIntBits((Float)list5.get(index7 + index9));
                  items8[index9 * 4] = (byte)(number10 >> 24);
                  items8[index9 * 4 + 1] = (byte)(number10 >> 16);
                  items8[index9 * 4 + 2] = (byte)(number10 >> 8);
                  items8[index9 * 4 + 3] = (byte)number10;
               }

               stream6.write(items8);
            }
         }

         return list5;
      }
   }

   private float method2(float value1) {
      if (value1 <= 0.0F) {
         return 0.0F;
      }

      float value2 = (float)(20.0 * Math.log10(value1));
      return value2 < -48.0F ? 0.0F : (value2 - -48.0F) / 48.0F;
   }

   public String method3(ReplayTimeline highlight_31, Range<Integer> range2, AudioSegment rewinditerator223, float value4, float value5, long number6, Runnable runnable8) {
      com.moonsworth.lunar.client.replay.audio.AudioStream rewindhandlers29 = rewinditerator223.method9();
      List list10 = rewindhandlers29.method20();
      if (list10 == null) {
         return "";
      }

      int number11 = (Integer)range2.getMaximum() - (Integer)range2.getMinimum();
      File file12 = new File(this.field1, "audio_cache");
      File file13 = new File(file12, "renders_" + rewindhandlers29.getHash());
      File file14 = new File(
         file13,
         rewinditerator223.getId()
            + "-"
            + (rewindhandlers29.getKey() == null ? "" : rewindhandlers29.getKey() + "-")
            + rewinditerator223.OOIOROCCOHHIHOHRICCORHRCROCCIH()
            + "-"
            + number11
            + "@"
            + value4
            + "@"
            + value5
            + "-"
            + number6
            + ".png"
      );
      file14.deleteOnExit();
      if (this.field5.contains(file14.getName())) {
         return this.field4.getOrDefault(rewinditerator223.getId(), "");
      }

      if (file14.exists()) {
         return PathUtils.method2(file14, RewindPaths.field10);
      }

      this.field5.add(file14.getName());
      PropertyGroup fishing2iterator15 = rewinditerator223.HRCRIHHCHRHIOHHOCHIROOCORCICOI().get("audio");
      KeyframeProperty fishing2loader16 = fishing2iterator15.method12().get("volume");
      int number17 = rewindhandlers29.method20().size();
      double value18 = highlight_31.method9() / 1000.0;
      double value20 = number11 * value18;
      byte number22 = 40;
      int number23 = (int)(value20 * value4);
      if (number23 <= 0) {
         return "";
      }

      if (number23 > 100000) {
         return "";
      }

      float value24 = (float)(rewindhandlers29.method10() * rewindhandlers29.method19() * value20) / number23;
      double value25 = rewindhandlers29.method10() * (rewinditerator223.OOIOROCCOHHIHOHRICCORHRCROCCIH() * value18);
      float value27 = 2.0F;
      BufferedImage bufferedimage28 = new BufferedImage(number23, number22, 2);
      Graphics2D graphics2d29 = bufferedimage28.createGraphics();
      graphics2d29.setColor(Color.white);

      for (float value30 = 0.0F; value30 < number23 && value25 + value30 * value24 < number17; value30 += value27) {
         float value31 = (Float)fishing2loader16.getAt((int)(value30 / number23 * number11)) / 100.0F;
         float value32 = 0.0F;
         if (value25 + value30 * value24 + value27 < number17) {
            float value33 = 0.0F;

            for (int index34 = 0; index34 < value27; index34++) {
               value33 = Math.max(value33, rewindhandlers29.method20().get((int)(value25 + value30 * value24 + index34)));
            }

            value32 = value33;
         }

         int number37 = number22 / 2;
         float value38 = value32 * value31;
         float value35 = this.method2(value38);
         float value36 = value35 * number37;
         graphics2d29.drawLine((int)value30, number37 - (int)value36, (int)value30, number37 + (int)value36);
      }

      graphics2d29.dispose();
      this.field3.execute(() -> {
         try {
            file14.getParentFile().mkdirs();
            ImageIO.write(bufferedimage28, "PNG", file14);
         } catch (IOException exception6x) {
            exception6x.printStackTrace();
         }

         this.field4.put(rewinditerator223.getId(), PathUtils.method2(file14, RewindPaths.field10));
         this.field5.remove(file14.getName());
         runnable8.run();
      });
      return this.field4.getOrDefault(rewinditerator223.getId(), "");
   }

   @Generated
   public AudioWaveformRenderer(File file1) {
      this.field1 = file1;
   }
}
