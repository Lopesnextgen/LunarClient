package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.CursorManager;

public class ThreadModuleDump49 {
   private static ThreadModuleDump49.Type field1 = null;
   private static Object field2 = null;
   private static ThreadModuleDump49.Type field3 = null;
   private static final Map<ThreadModuleDump49.Type, Object> field4 = new HashMap<>();

   private static BufferedImage method1(String var0) {
      return ThreadModuleDump59.method4(ResourceLocationBridge.create("lunar", "cursors/" + var0 + ".png"));
   }

   public static void method2(ThreadModuleDump49.Type var0) {
      if (field3 == null || field3 != var0 && var0 == null || var0.ordinal() < field3.ordinal()) {
         field3 = var0;
      }
   }

   public static void tick() {
      if (Client.method109().method41().method6().method90().get()) {
         if (field3 != null) {
            method3(field3);
         }

         Object var0 = field3 == null ? null : field4.get(field3);
         if (var0 != field2) {
            field2 = var0;
            field1 = field3;
            if (var0 == null) {
               Bridge.method20().method5();
            } else {
               Bridge.method20().method4(var0);
            }
         }

         field3 = null;
      }
   }

   private static void method3(ThreadModuleDump49.Type var0) {
      if (!field4.containsKey(var0)) {
         try {
            field4.put(var0, Bridge.method20().method3(var0.ordinal(), var0.supplier, var0.hotspotX, var0.hotspotY));
         } catch (Exception var2) {
            Slayer.method8("CursorManager", "Failed to create cursor %s.", new Object[]{var0.name()});
            field4.put(var0, null);
         }
      }
   }

   @Generated
   public static ThreadModuleDump49.Type method4() {
      return field1;
   }

   public enum Type {
      HIDDEN(() -> new BufferedImage(1, 1, 2), 0, 0),
      CROSSHAIR(() -> ThreadModuleDump49.method1("win_move"), 12, 12),
      IBEAM(() -> ThreadModuleDump49.method1("win_ibeam"), 3, 8),
      HAND(() -> ThreadModuleDump49.method1("win_link_select"), 8, 22),
      HRESIZE(() -> ThreadModuleDump49.method1("win_hresize"), 11, 4),
      VRESIZE(() -> ThreadModuleDump49.method1("win_vresize"), 4, 11);

      private final Supplier<BufferedImage> supplier;
      private final int hotspotX;
      private final int hotspotY;

      @Generated
      Type(Supplier<BufferedImage> supplier2, int value, int value2) {
         this.supplier = supplier2;
         this.hotspotX = value;
         this.hotspotY = value2;
      }
   }
}
