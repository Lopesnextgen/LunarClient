package com.moonsworth.lunar.client.ui.external;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.lang.reflect.Constructor;
import javax.annotation.Nullable;

public class ModMenuCompat {
   public static final String field1 = "com.terraformersmc.modmenu.gui.ModsScreen";
   @Nullable
   public static Class<?> field2;
   @Nullable
   public static Constructor<?> field3;

   public ModMenuCompat() {
   }

   public static boolean method1() {
      if (field3 == null) {
         return false;
      }

      try {
         Object obj0 = field3.newInstance(Ref.method3().bridge$getCurrentScreen());
         Ref.method3().bridge$displayScreen((GuiScreenBridge)obj0);
         return true;
      } catch (Throwable exception1) {
         exception1.printStackTrace();
         return false;
      }
   }

   public static boolean method2() {
      return field2 != null;
   }

   static {
      if (Ref.MC_VERSION >= 6) {
         try {
            Class clazz0;
            try {
               clazz0 = Class.forName("net.minecraft.client.gui.screens.Screen");
            } catch (Throwable exception2) {
               clazz0 = Class.forName("net.minecraft.class_437");
            }

            field2 = Class.forName("com.terraformersmc.modmenu.gui.ModsScreen");
            field3 = field2.getConstructor(clazz0);
         } catch (Throwable exception3) {
         }
      }
   }
}
