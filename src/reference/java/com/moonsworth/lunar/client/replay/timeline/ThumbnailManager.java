package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.render.texture.FramebufferCapture;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotCapture;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.TreeMap;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ThumbnailManager {
   private final File field1;
   private final Map<UUID, TreeMap<Integer, String>> field2 = new HashMap<>();
   private final Queue<ThumbnailRequest> field3 = new LinkedList<>();

   public void method1(UUID uuid1) {
      File file2 = new File(this.field1, "thumbnails/" + uuid1);
      if (file2.isDirectory()) {
         TreeMap map3 = new TreeMap();

         for (File file7 : Objects.requireNonNull(file2.listFiles())) {
            if (file7.getName().endsWith(".png")) {
               try {
                  int number8 = Integer.parseInt(file7.getName().substring(0, file7.getName().length() - ".png".length()));
                  map3.put(number8, null);
               } catch (Exception exception9) {
                  exception9.printStackTrace();
               }
            }
         }

         this.field2.put(uuid1, map3);
      }
   }

   public String method2(UUID uuid1, int number2) {
      TreeMap map3 = this.field2.computeIfAbsent(uuid1, arg0 -> new TreeMap<>());
      map3.put(number2, null);
      return this.method3(uuid1, number2);
   }

   public String method3(UUID uuid1, int index2) {
      TreeMap map3 = this.field2.get(uuid1);
      if (map3 == null) {
         return null;
      }

      if (!map3.containsKey(index2)) {
         return null;
      }

      String text4 = (String)map3.get(index2);
      if (text4 == null) {
         File file5 = new File(this.field1, "thumbnails/" + uuid1 + "/" + index2 + ".png");
         text4 = PathUtils.method2(file5, RewindPaths.field10);
         map3.put(index2, text4);
      }

      return text4;
   }

   public void method4() {
      this.field2.clear();
   }

   public void method5(ThumbnailRequest highlight2_21) {
      this.field3.add(highlight2_21);
   }

   public void method6(RewindHandlers rewindhandlers1, int number2, FramebufferCapture alert3) {
      Iterator iterator4 = this.field3.iterator();

      while (iterator4.hasNext()) {
         try {
            ThumbnailRequest highlight2_25 = (ThumbnailRequest)iterator4.next();
            int number6 = highlight2_25.method4();
            String text7 = this.method3(highlight2_25.method3(), number6);
            if (text7 != null) {
               iterator4.remove();
               highlight2_25.method1().method14(text7);
               rewindhandlers1.method27();
            } else if (number2 >= (Integer)highlight2_25.method2().getMinimum() && number2 < (Integer)highlight2_25.method2().getMaximum()) {
               iterator4.remove();
               File file8 = new File(this.field1, "thumbnails/" + highlight2_25.method3() + "/" + number6 + ".png");
               ScreenshotCapture screenshot29 = new ScreenshotCapture();
               int number10 = alert3.method11().bridge$framebufferTextureWidth();
               int number11 = alert3.method11().bridge$framebufferTextureHeight();
               screenshot29.method5(
                  null,
                  file8,
                  -1,
                  -1,
                  alert3.method11(),
                  false,
                  arg3x -> this.method8(arg3x, number10, number11, 64),
                  () -> Ref.method3().bridge$submit(() -> {
                     try {
                        highlight2_25.method1().method14(this.method2(highlight2_25.method3(), number6));
                        rewindhandlers1.method27();
                     } catch (IOException exception5x) {
                        exception5x.printStackTrace();
                     }
                  })
               );
            }
         } catch (IOException exception12) {
            exception12.printStackTrace();
         }
      }
   }

   public void method7(FramebufferCapture alert1) {
      File file2 = new File(this.field1, "thumbnails/project.png");
      int number3 = alert1.method11().bridge$framebufferTextureWidth();
      int number4 = alert1.method11().bridge$framebufferTextureHeight();
      ScreenshotCapture screenshot25 = new ScreenshotCapture();
      screenshot25.method5(null, file2, -1, -1, alert1.method11(), false, arg3x -> this.method8(arg3x, number3, number4, 256), () -> {});
   }

   @NotNull
   private BufferedImage method8(int[] items1, int number2, int number3, int number4) {
      BufferedImage bufferedimage5 = new BufferedImage(number2, number3, 1);
      bufferedimage5.setRGB(0, 0, number2, number3, items1, 0, number2);
      if (number2 > number3) {
         number3 = number4 * number3 / number2;
         number2 = number4;
      } else {
         number2 = number4 * number2 / number3;
         number3 = number4;
      }

      BufferedImage bufferedimage6 = new BufferedImage(number2, number3, 1);
      bufferedimage6.getGraphics().drawImage(bufferedimage5.getScaledInstance(number2, number3, 4), 0, 0, null);
      return bufferedimage6;
   }

   @Generated
   public ThumbnailManager(File file1) {
      this.field1 = file1;
   }
}
