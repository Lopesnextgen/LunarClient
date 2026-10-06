package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderPackHelper {
   public ShaderPackHelper() {
   }

   public static void method1() {
      if (!Fov3.method27()) {
         boolean flag0 = false;
         boolean flag1 = false;
         if (method2()) {
            ShadersBridge slayer32 = ((OptifineBridge)Bridge.method5().get()).getShaders();
            if (slayer32.isRenderingDfb()) {
               if (Bridge.getMinecraftVersion().method19()) {
                  Ref.method3().bridge$overrideMainRenderTarget(null, false, true);
               }

               slayer32.bindTargetDfb();
               flag1 = true;
               if (!Fov3.method28()) {
                  slayer32.restoreViewport();
                  flag0 = true;
               }
            }
         }

         if (!flag1) {
            Ref.method3().bridge$overrideMainRenderTarget(null, false, true);
         }

         if (!flag0) {
            Fov3.restoreViewport();
         }
      }
   }

   public static boolean method2() {
      return Bridge.method5().map(arg0 -> {
         ShadersBridge slayer31 = arg0.getShaders();
         String text2 = slayer31.getShaderPack();
         return text2 != null && !slayer31.getPackNone().equals(text2);
      }).orElse(false);
   }

   public static String method3(String text0, int number1) {
      StringBuilder builder2 = new StringBuilder();
      Pattern pattern3 = Pattern.compile("(\\d+):(\\d+):");
      Pattern pattern4 = Pattern.compile("(\\d+)\\((\\d+)\\)");
      String[] items5 = text0.split("\n");

      for (String text9 : items5) {
         Matcher matcher10 = pattern3.matcher(text9);
         if (matcher10.find()) {
            String text11 = matcher10.group(1);
            int number12 = Integer.parseInt(matcher10.group(2)) - number1;
            text9 = matcher10.replaceFirst(text11 + ":" + number12 + ":");
         }

         Matcher matcher14 = pattern4.matcher(text9);
         if (matcher14.find()) {
            String text15 = matcher14.group(1);
            int number13 = Integer.parseInt(matcher14.group(2)) - number1;
            text9 = matcher14.replaceFirst(text15 + "(" + number13 + ")");
         }

         builder2.append(text9).append("\n");
      }

      return builder2.toString();
   }
}
