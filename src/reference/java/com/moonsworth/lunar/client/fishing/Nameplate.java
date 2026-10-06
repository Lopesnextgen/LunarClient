package com.moonsworth.lunar.client.fishing;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.lang.reflect.Constructor;
import javax.annotation.Nullable;

public class Nameplate {
   public static final String field1 = "com.terraformersmc.modmenu.gui.ModsScreen";
   @Nullable
   public static Class<?> field2;
   @Nullable
   public static Constructor<?> field3;

   public static boolean method1() {
      if (field3 == null) {
         return false;
      }

      try {
         Object var0 = field3.newInstance(ThreadModuleDump63.method3().bridge$getCurrentScreen());
         ThreadModuleDump63.method3().bridge$displayScreen((Bridge5Extension6)var0);
         return true;
      } catch (Throwable var1) {
         var1.printStackTrace();
         return false;
      }
   }

   public static boolean method2() {
      return field2 != null;
   }

   static {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         try {
            Class var0;
            try {
               var0 = Class.forName("net.minecraft.client.gui.screens.Screen");
            } catch (Throwable var2) {
               var0 = Class.forName("net.minecraft.class_437");
            }

            field2 = Class.forName("com.terraformersmc.modmenu.gui.ModsScreen");
            field3 = field2.getConstructor(var0);
         } catch (Throwable var3) {
         }
      }
   }
}
