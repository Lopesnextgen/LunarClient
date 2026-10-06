package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.moonsworth.lunar.client.render.texture.FramebufferCapture;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.screenshot.Screenshot2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class Highlight3 {
   private final File field1;
   private final Map<UUID, TreeMap<Integer, String>> field2 = new HashMap<>();
   private final Queue<Highlight2_2> field3 = new LinkedList<>();

   public void method1(UUID var1) {
      File var2 = new File(this.field1, "thumbnails/" + var1);
      if (var2.isDirectory()) {
         TreeMap var3 = new TreeMap();

         for (File var7 : Objects.requireNonNull(var2.listFiles())) {
            if (var7.getName().endsWith(".png")) {
               try {
                  int var8 = Integer.parseInt(var7.getName().substring(0, var7.getName().length() - ".png".length()));
                  var3.put(var8, null);
               } catch (Exception var9) {
                  var9.printStackTrace();
               }
            }
         }

         this.field2.put(var1, var3);
      }
   }

   public String method2(UUID var1, int var2) {
      TreeMap var3 = this.field2.computeIfAbsent(var1, var0 -> new TreeMap<>());
      var3.put(var2, null);
      return this.method3(var1, var2);
   }

   public String method3(UUID var1, int var2) {
      TreeMap var3 = this.field2.get(var1);
      if (var3 == null) {
         return null;
      }

      if (!var3.containsKey(var2)) {
         return null;
      }

      String var4 = (String)var3.get(var2);
      if (var4 == null) {
         File var5 = new File(this.field1, "thumbnails/" + var1 + "/" + var2 + ".png");
         var4 = Gui5.method2(var5, Gui.field10);
         var3.put(var2, var4);
      }

      return var4;
   }

   public void method4() {
      this.field2.clear();
   }

   public void method5(Highlight2_2 var1) {
      this.field3.add(var1);
   }

   public void method6(RewindHandlers var1, int var2, FramebufferCapture var3) {
      Iterator var4 = this.field3.iterator();

      while (var4.hasNext()) {
         try {
            Highlight2_2 var5 = (Highlight2_2)var4.next();
            int var6 = var5.method4();
            String var7 = this.method3(var5.method3(), var6);
            if (var7 != null) {
               var4.remove();
               var5.method1().method14(var7);
               var1.method27();
            } else if (var2 >= (Integer)var5.method2().getMinimum() && var2 < (Integer)var5.method2().getMaximum()) {
               var4.remove();
               File var8 = new File(this.field1, "thumbnails/" + var5.method3() + "/" + var6 + ".png");
               Screenshot2 var9 = new Screenshot2();
               int var10 = var3.method11().bridge$framebufferTextureWidth();
               int var11 = var3.method11().bridge$framebufferTextureHeight();
               var9.method5(
                  null,
                  var8,
                  -1,
                  -1,
                  var3.method11(),
                  false,
                  var3x -> this.method8(var3x, var10, var11, 64),
                  () -> ThreadModuleDump63.method3().bridge$submit(() -> {
                     try {
                        var5.method1().method14(this.method2(var5.method3(), var6));
                        var1.method27();
                     } catch (IOException var5x) {
                        var5x.printStackTrace();
                     }
                  })
               );
            }
         } catch (IOException var12) {
            var12.printStackTrace();
         }
      }
   }

   public void method7(FramebufferCapture var1) {
      File var2 = new File(this.field1, "thumbnails/project.png");
      int var3 = var1.method11().bridge$framebufferTextureWidth();
      int var4 = var1.method11().bridge$framebufferTextureHeight();
      Screenshot2 var5 = new Screenshot2();
      var5.method5(null, var2, -1, -1, var1.method11(), false, var3x -> this.method8(var3x, var3, var4, 256), () -> {});
   }

   @NotNull
   private BufferedImage method8(int[] var1, int var2, int var3, int var4) {
      BufferedImage var5 = new BufferedImage(var2, var3, 1);
      var5.setRGB(0, 0, var2, var3, var1, 0, var2);
      if (var2 > var3) {
         var3 = var4 * var3 / var2;
         var2 = var4;
      } else {
         var2 = var4 * var2 / var3;
         var3 = var4;
      }

      BufferedImage var6 = new BufferedImage(var2, var3, 1);
      var6.getGraphics().drawImage(var5.getScaledInstance(var2, var3, 4), 0, 0, null);
      return var6;
   }

   @Generated
   public Highlight3(File var1) {
      this.field1 = var1;
   }
}
