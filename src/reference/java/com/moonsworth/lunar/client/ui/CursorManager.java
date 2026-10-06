package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Generated;
import com.moonsworth.lunar.client.util.io.ImageUtils;

public class CursorManager {
   private static CursorManager.Type field1 = null;
   private static Object field2 = null;
   private static CursorManager.Type field3 = null;
   private static final Map<CursorManager.Type, Object> field4 = new HashMap<>();

   public CursorManager() {
   }

   private static BufferedImage method1(String text) {
      return ImageUtils.method4(ResourceLocationBridge.create("lunar", "cursors/" + text + ".png"));
   }

   public static void method2(CursorManager.Type type0) {
      if (field3 == null || field3 != type0 && type0 == null || type0.ordinal() < field3.ordinal()) {
         field3 = type0;
      }
   }

   public static void tick() {
      if ((Boolean)Client.method109().method41().method6().method90().get()) {
         if (field3 != null) {
            method3(field3);
         }

         Object obj0 = field3 == null ? null : field4.get(field3);
         if (obj0 != field2) {
            field2 = obj0;
            field1 = field3;
            if (obj0 == null) {
               Bridge.method20().method5();
            } else {
               Bridge.method20().method4(obj0);
            }
         }

         field3 = null;
      }
   }

   private static void method3(CursorManager.Type type0) {
      if (!field4.containsKey(type0)) {
         try {
            field4.put(type0, Bridge.method20().method3(type0.ordinal(), type0.supplier, type0.hotspotX, type0.hotspotY));
         } catch (Exception exception2) {
            LunarLogger.method8("CursorManager", "Failed to create cursor %s.", new Object[]{type0.name()});
            field4.put(type0, null);
         }
      }
   }

   @Generated
   public static CursorManager.Type method4() {
      return field1;
   }

   public enum Type {
      HIDDEN(() -> new BufferedImage(1, 1, 2), 0, 0),
      CROSSHAIR(() -> CursorManager.method1("win_move"), 12, 12),
      IBEAM(() -> CursorManager.method1("win_ibeam"), 3, 8),
      HAND(() -> CursorManager.method1("win_link_select"), 8, 22),
      HRESIZE(() -> CursorManager.method1("win_hresize"), 11, 4),
      VRESIZE(() -> CursorManager.method1("win_vresize"), 4, 11);

      private final Supplier<BufferedImage> supplier;
      private final int hotspotX;
      private final int hotspotY;

      @Generated
      Type(Supplier<BufferedImage> supplier3, int value, int value2) {
         this.supplier = supplier3;
         this.hotspotX = value;
         this.hotspotY = value2;
      }
   }
}
