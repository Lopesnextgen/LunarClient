package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge7_10;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class F3display {
   public static final F3display_2 field1 = new F3display_2();
   private static List<ProfilerResultBridge> field2 = List.of();
   private static long field3 = 0L;
   private static long field4 = -1L;
   private static long field5 = 0L;

   public static void method1(F3display_3 var0, @Nullable F3DisplayModule var1) {
      String var2 = Bridge.getMinecraftVersion().method45();
      var0.method2("Minecraft ", var2);
      if (var1 == null || (Boolean)var1.field15.get()) {
         String var3 = Client.getClientBrand();
         var0.method1(var3);
      }
   }

   public static String method2() {
      Bridge5_12 var0 = ThreadModuleDump63.method3();
      return var0.bridge$getDebugFPS() + "";
   }

   public static String method3() {
      return method30(var0 -> var0.method6().bridge$getBiomeName());
   }

   public static String method4() {
      return method30(var0 -> {
         double var1 = var0.bridge$getPosX();
         double var3 = var0.bridge$getPosY();
         double var5 = var0.bridge$getPosZ();
         return method33(var1, 3) + "  " + method33(var3, 5) + "  " + method33(var5, 3);
      });
   }

   public static String method5() {
      return method30(var0 -> {
         int var1 = var0.bridge$getBlockX();
         int var2 = var0.bridge$getBlockY();
         int var3 = var0.bridge$getBlockZ();
         return var1 + " " + var2 + " " + var3;
      });
   }

   public static String method6() {
      return method30(var0 -> {
         Horsestats20Extension2 var1 = var0.bridge$getBlockPos();
         return (var1.bridge$getX() >> 4) + " " + (var1.bridge$getY() >> 4) + " " + (var1.bridge$getZ() >> 4);
      });
   }

   public static String method7() {
      return method30(var0 -> {
         Horsestats20Extension2 var1 = var0.bridge$getBlockPos();
         int var2 = var1.bridge$getX() >> 4;
         int var3 = var1.bridge$getZ() >> 4;
         int var4 = var2 & 31;
         int var5 = var3 & 31;
         int var6 = var2 >> 5;
         int var7 = var3 >> 5;
         return "[" + var4 + " " + var5 + " in r." + var6 + "." + var7 + ".mca]";
      });
   }

   public static String method8() {
      return method30(var0 -> {
         Horsestats20Extension2 var1 = var0.bridge$getBlockPos();
         return (var1.bridge$getX() & 15) + " " + (var1.bridge$getY() & 15) + " " + (var1.bridge$getZ() & 15);
      });
   }

   public static void method9(F3display_3 var0, F3DisplayModule var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && var2.bridge$getWorld() != null) {
         if (var0.method7()) {
            String var6 = ThreadModuleDump63.MC_VERSION <= 5 ? "Player" : "minecraft:player";
            var0.method2("Target Entity: ", var6);
         } else {
            Optional var3 = ThreadModuleDump63.method3().bridge$getPointedEntity();
            if (!var3.isEmpty()) {
               BridgeExtension var4 = (BridgeExtension)var3.get();
               String var5 = var4.bridge$getEntityString();
               if (ThreadModuleDump63.MC_VERSION <= 5 && var4 instanceof Bridge6_10) {
                  var5 = "Player";
               }

               var0.method2("Target Entity: ", var5);
            }
         }
      }
   }

   public static String method10() {
      return method30(var0 -> {
         float var1 = (float)var0.bridge$getRotationYaw();
         float var2 = (float)var0.bridge$getRotationPitch();
         String var3 = method12(var1);
         String var4 = method13(var1);
         var1 = MathHelperBridge.method6(var1);
         var2 = MathHelperBridge.method6(var2);
         return var3 + " " + var4 + " (" + method33(var1, 1) + " / " + method33(var2, 1) + ")";
      });
   }

   public static String method11() {
      return method30(var0 -> var0.bridge$getWorld().bridge$getDimensionKey());
   }

   public static String method12(float var0) {
      String[] var1 = new String[]{"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest"};
      double var2 = ThreadModuleDump67.method13(var0) + 180.0;
      var2 += 22.5;
      var2 %= 360.0;
      var2 /= 45.0;
      return var1[ThreadModuleDump67.method9(var2)];
   }

   public static String method13(float var0) {
      String[] var1 = new String[]{"-Z", "+X -Z", "+X", "+X +Z", "+Z", "-X +Z", "-X", "-X -Z"};
      double var2 = ThreadModuleDump67.method13(var0) + 180.0;
      var2 += 22.5;
      var2 %= 360.0;
      var2 /= 45.0;
      return var1[ThreadModuleDump67.method9(var2)];
   }

   public static String method14() {
      Bridge5_12 var0 = ThreadModuleDump63.method3();
      float var1 = var0.bridge$getGpuUtilization();
      return var1 > 100.0F ? "100%" : Math.round(var1) + "%";
   }

   public static void method15(F3display_3 var0, @Nullable F3DisplayModule var1) {
      Runtime var2 = Runtime.getRuntime();
      long var3 = var2.totalMemory() - var2.freeMemory();
      long var5 = var2.maxMemory();
      String var7 = var3 / 1048576L + "/" + var5 / 1048576L + " MB";
      int var8 = (int)(100L * var3 / var5);
      var0.method2("RAM Usage: ", var8 + "%");
      var0.method1(var7);
      if (var1 == null || (Boolean)var1.field25.get()) {
         var0.method2("Allocation Rate: ", method16(var3) / 1048576L + " MB/s");
      }
   }

   private static long method16(long var0) {
      long var2 = System.currentTimeMillis();
      long var4 = var2 - field3;
      if (var4 >= 500L) {
         if (field4 >= 0L && var0 > field4) {
            field5 = (var0 - field4) * 1000L / var4;
         }

         field3 = var2;
         field4 = var0;
      }

      return field5;
   }

   public static void method17(F3display_3 var0, F3DisplayModule var1) {
      var0.method2("Java ", System.getProperty("java.version"));
      var0.method2("CPU: ", Bridge.method22().method9());
      Bridge5_12 var2 = ThreadModuleDump63.method3();
      if (ThreadModuleDump63.MC_VERSION >= 29) {
         Bridge7_2 var3 = Bridge.method42().method85();
         String var4 = var2.bridge$displayWidth() + "x" + var2.bridge$displayHeight() + " (" + var3.field1 + ")";
         var0.method2("Display: ", var4);
         var0.method1(var3.field3);
         var0.method1(var3.field2 + " " + var3.version);
      } else {
         String var5 = var2.bridge$displayWidth() + "x" + var2.bridge$displayHeight();
         var0.method2("Display: ", var5);
      }
   }

   public static String method18() {
      return method30(var0 -> {
         Itemcounter6 var1 = var0.bridge$getWorld();
         int var2 = var1.bridge$getPackedLight(var0.bridge$getBlockPos());
         int var3 = var2 >> 4 & 15;
         int var4 = var2 >> 20 & 15;
         return Math.max(var3, var4) + " (" + var4 + " sky, " + var3 + " block)";
      });
   }

   public static String method19() {
      return method30(var0 -> var0.bridge$getWorld().bridge$getWorldTime() / 24000L + "");
   }

   public static String method20() {
      return method31(var0 -> {
         Bridge14_3 var1 = ThreadModuleDump63.method3().bridge$getLevelRenderer();
         if (var1 == null) {
            return null;
         }

         int var2 = var1.bridge$getRenderedEntityCount();
         int var3 = var0.bridge$getEntities().size();
         return var2 + "/" + var3;
      });
   }

   public static String method21() {
      return method32(var0 -> var0.bridge$getUnculledRenderCount() + "");
   }

   public static String method22() {
      return ThreadModuleDump63.method3().bridge$getEffectRenderer().bridge$countParticles() + "";
   }

   public static void method23(F3display_3 var0, @Nullable F3DisplayModule var1) {
      GameOptionsBridge var2 = ThreadModuleDump63.method3().bridge$getGameSettings();
      var0.method2("Render Distance: ", var2.bridge$getRenderDistance() + "");
      if (var1 == null || (Boolean)var1.field23.get()) {
         var0.method2("Simulation Distance: ", var2.bridge$getSimulationDistance() + "");
      }
   }

   public static void method24(F3display_3 var0, @Nullable F3DisplayModule var1) {
      String var2 = ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$getDebugString();
      var0.method2("Sounds: ", var2);
      if (var1 == null || (Boolean)var1.field24.get()) {
         Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
         if (var3 != null) {
            var0.method2("Mood: ", Math.round(var3.bridge$getCurrentMood() * 100.0F) + "%");
         }
      }
   }

   public static String method25() {
      ClientPacketListenerBridge var0 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var0 == null) {
         return null;
      }

      Glintcolorizer var1 = ThreadModuleDump63.method3().bridge$getIntegratedServer();
      if (var1 != null) {
         return "Integrated Server";
      }

      String var2 = var0.bridge$getServerBrand();
      return var2 != null && !var2.isEmpty() ? var2 : null;
   }

   public static String method26() {
      Bridge7_10 var0 = ThreadModuleDump63.method3().bridge$getGameRenderer().bridge$getShaderGroup();
      return var0 == null ? null : var0.bridge$getShaderGroupName();
   }

   public static String method27() {
      int var0 = F3Display.bandwidthChartDisplay.RHHRCRRIHHICHROHHRIORRORIHICCR();
      return F3Display.bandwidthChartDisplay.method11(var0);
   }

   public static List<ProfilerResultBridge> method28() {
      List var0 = ThreadModuleDump63.method3().bridge$getPieChartResults();
      if (var0 != null && !var0.isEmpty()) {
         field2 = var0;
      }

      return field2;
   }

   public static void method29() {
      if (!field2.isEmpty()) {
         field2 = List.of();
      }
   }

   private static String method30(Function<Bridge5Extension_5, String> var0) {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      return var1 == null ? null : (String)var0.apply(var1);
   }

   private static String method31(Function<Itemcounter6, String> var0) {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      return var1 == null ? null : (String)var0.apply(var1);
   }

   private static String method32(Function<Bridge14_3, String> var0) {
      if (ThreadModuleDump63.method8() == null) {
         return null;
      }

      Bridge14_3 var1 = ThreadModuleDump63.method3().bridge$getLevelRenderer();
      return var1 == null ? null : (String)var0.apply(var1);
   }

   public static String method33(double var0, int var2) {
      if (var2 <= 0) {
         return Long.toString((long)var0);
      }

      boolean var3 = var0 < 0.0;
      if (var3) {
         var0 = -var0;
      }
      long var4 = switch (var2) {
         case 1 -> 10L;
         case 2 -> 100L;
         case 3 -> 1000L;
         case 4 -> 10000L;
         case 5 -> 100000L;
         default -> {
            long var6 = 1L;

            for (int var8 = 0; var8 < var2; var8++) {
               var6 *= 10L;
            }

            yield var6;
         }
      };
      long var15 = (long)(var0 * var4);
      long var16 = var15 / var4;
      long var10 = var15 % var4;
      StringBuilder var12 = new StringBuilder(32);
      if (var3 && (var16 != 0L || var10 != 0L)) {
         var12.append('-');
      }

      var12.append(var16).append(".");

      for (long var13 = var4 / 10L; var13 > 0L && var10 < var13; var13 /= 10L) {
         var12.append('0');
      }

      if (var10 > 0L) {
         var12.append(var10);
      }

      return var12.toString();
   }

   @Generated
   private F3display() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
