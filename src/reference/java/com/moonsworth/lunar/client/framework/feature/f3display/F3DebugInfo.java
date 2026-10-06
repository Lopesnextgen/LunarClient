package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge7_10;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.bridge.server.IntegratedServerBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class F3DebugInfo {
   public static final TargetInfoRegistry field1 = new TargetInfoRegistry();
   private static List<ProfilerResultBridge> field2 = List.of();
   private static long field3 = 0L;
   private static long field4 = -1L;
   private static long field5 = 0L;

   public static void method1(F3DebugWriter f3display_30, @Nullable F3DisplayModule f3modulechildmod1) {
      String text2 = Bridge.getMinecraftVersion().method45();
      f3display_30.method2("Minecraft ", text2);
      if (f3modulechildmod1 == null || (Boolean)f3modulechildmod1.field15.get()) {
         String text3 = Client.getClientBrand();
         f3display_30.method1(text3);
      }
   }

   public static String method2() {
      MinecraftBridge bridge5_120 = Ref.method3();
      return bridge5_120.bridge$getDebugFPS() + "";
   }

   public static String method3() {
      return method30(arg0 -> arg0.method6().bridge$getBiomeName());
   }

   public static String method4() {
      return method30(arg0 -> {
         double value1 = arg0.bridge$getPosX();
         double value3 = arg0.bridge$getPosY();
         double value5 = arg0.bridge$getPosZ();
         return method33(value1, 3) + "  " + method33(value3, 5) + "  " + method33(value5, 3);
      });
   }

   public static String method5() {
      return method30(arg0 -> {
         int number1 = arg0.bridge$getBlockX();
         int number2 = arg0.bridge$getBlockY();
         int number3 = arg0.bridge$getBlockZ();
         return number1 + " " + number2 + " " + number3;
      });
   }

   public static String method6() {
      return method30(arg0 -> {
         Horsestats20Extension2 horsestats20extension21 = arg0.bridge$getBlockPos();
         return (horsestats20extension21.bridge$getX() >> 4) + " " + (horsestats20extension21.bridge$getY() >> 4) + " " + (horsestats20extension21.bridge$getZ() >> 4);
      });
   }

   public static String method7() {
      return method30(arg0 -> {
         Horsestats20Extension2 horsestats20extension21 = arg0.bridge$getBlockPos();
         int number2 = horsestats20extension21.bridge$getX() >> 4;
         int number3 = horsestats20extension21.bridge$getZ() >> 4;
         int number4 = number2 & 31;
         int number5 = number3 & 31;
         int number6 = number2 >> 5;
         int number7 = number3 >> 5;
         return "[" + number4 + " " + number5 + " in r." + number6 + "." + number7 + ".mca]";
      });
   }

   public static String method8() {
      return method30(arg0 -> {
         Horsestats20Extension2 horsestats20extension21 = arg0.bridge$getBlockPos();
         return (horsestats20extension21.bridge$getX() & 15) + " " + (horsestats20extension21.bridge$getY() & 15) + " " + (horsestats20extension21.bridge$getZ() & 15);
      });
   }

   public static void method9(F3DebugWriter f3display_30, F3DisplayModule f3modulechildmod1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && bridge5extension_52.bridge$getWorld() != null) {
         if (f3display_30.method7()) {
            String text6 = Ref.MC_VERSION <= 5 ? "Player" : "minecraft:player";
            f3display_30.method2("Target Entity: ", text6);
         } else {
            Optional optional3 = Ref.method3().bridge$getPointedEntity();
            if (!optional3.isEmpty()) {
               BridgeExtension bridgeextension4 = (BridgeExtension)optional3.get();
               String text5 = bridgeextension4.bridge$getEntityString();
               if (Ref.MC_VERSION <= 5 && bridgeextension4 instanceof Bridge6_10) {
                  text5 = "Player";
               }

               f3display_30.method2("Target Entity: ", text5);
            }
         }
      }
   }

   public static String method10() {
      return method30(arg0 -> {
         float value1 = (float)arg0.bridge$getRotationYaw();
         float value2 = (float)arg0.bridge$getRotationPitch();
         String text3 = method12(value1);
         String text4 = method13(value1);
         value1 = MathHelperBridge.method6(value1);
         value2 = MathHelperBridge.method6(value2);
         return text3 + " " + text4 + " (" + method33(value1, 1) + " / " + method33(value2, 1) + ")";
      });
   }

   public static String method11() {
      return method30(arg0 -> arg0.bridge$getWorld().bridge$getDimensionKey());
   }

   public static String method12(float value0) {
      String[] items1 = new String[]{"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest"};
      double value2 = MathUtils.method13(value0) + 180.0;
      value2 += 22.5;
      value2 %= 360.0;
      value2 /= 45.0;
      return items1[MathUtils.method9(value2)];
   }

   public static String method13(float value0) {
      String[] items1 = new String[]{"-Z", "+X -Z", "+X", "+X +Z", "+Z", "-X +Z", "-X", "-X -Z"};
      double value2 = MathUtils.method13(value0) + 180.0;
      value2 += 22.5;
      value2 %= 360.0;
      value2 /= 45.0;
      return items1[MathUtils.method9(value2)];
   }

   public static String method14() {
      MinecraftBridge bridge5_120 = Ref.method3();
      float value1 = bridge5_120.bridge$getGpuUtilization();
      return value1 > 100.0F ? "100%" : Math.round(value1) + "%";
   }

   public static void method15(F3DebugWriter f3display_30, @Nullable F3DisplayModule f3modulechildmod1) {
      Runtime runtime2 = Runtime.getRuntime();
      long number3 = runtime2.totalMemory() - runtime2.freeMemory();
      long number5 = runtime2.maxMemory();
      String text7 = number3 / 1048576L + "/" + number5 / 1048576L + " MB";
      int number8 = (int)(100L * number3 / number5);
      f3display_30.method2("RAM Usage: ", number8 + "%");
      f3display_30.method1(text7);
      if (f3modulechildmod1 == null || (Boolean)f3modulechildmod1.showAllocationRate.get()) {
         f3display_30.method2("Allocation Rate: ", method16(number3) / 1048576L + " MB/s");
      }
   }

   private static long method16(long number0) {
      long number2 = System.currentTimeMillis();
      long number4 = number2 - field3;
      if (number4 >= 500L) {
         if (field4 >= 0L && number0 > field4) {
            field5 = (number0 - field4) * 1000L / number4;
         }

         field3 = number2;
         field4 = number0;
      }

      return field5;
   }

   public static void method17(F3DebugWriter f3display_30, F3DisplayModule f3modulechildmod1) {
      f3display_30.method2("Java ", System.getProperty("java.version"));
      f3display_30.method2("CPU: ", Bridge.method22().method9());
      MinecraftBridge bridge5_122 = Ref.method3();
      if (Ref.MC_VERSION >= 29) {
         Bridge7_2 bridge7_23 = Bridge.method42().method85();
         String text4 = bridge5_122.bridge$displayWidth() + "x" + bridge5_122.bridge$displayHeight() + " (" + bridge7_23.field1 + ")";
         f3display_30.method2("Display: ", text4);
         f3display_30.method1(bridge7_23.field3);
         f3display_30.method1(bridge7_23.field2 + " " + bridge7_23.version);
      } else {
         String text5 = bridge5_122.bridge$displayWidth() + "x" + bridge5_122.bridge$displayHeight();
         f3display_30.method2("Display: ", text5);
      }
   }

   public static String method18() {
      return method30(arg0 -> {
         Itemcounter6 itemcounter61 = arg0.bridge$getWorld();
         int number2 = itemcounter61.bridge$getPackedLight(arg0.bridge$getBlockPos());
         int number3 = number2 >> 4 & 15;
         int number4 = number2 >> 20 & 15;
         return Math.max(number3, number4) + " (" + number4 + " sky, " + number3 + " block)";
      });
   }

   public static String method19() {
      return method30(arg0 -> arg0.bridge$getWorld().bridge$getWorldTime() / 24000L + "");
   }

   public static String method20() {
      return method31(arg0 -> {
         Bridge14_3 bridge14_31 = Ref.method3().bridge$getLevelRenderer();
         if (bridge14_31 == null) {
            return null;
         }

         int number2 = bridge14_31.bridge$getRenderedEntityCount();
         int number3 = arg0.bridge$getEntities().size();
         return number2 + "/" + number3;
      });
   }

   public static String method21() {
      return method32(arg0 -> arg0.bridge$getUnculledRenderCount() + "");
   }

   public static String method22() {
      return Ref.method3().bridge$getEffectRenderer().bridge$countParticles() + "";
   }

   public static void method23(F3DebugWriter f3display_30, @Nullable F3DisplayModule f3modulechildmod1) {
      GameOptionsBridge mixinhelper2_82 = Ref.method3().bridge$getGameSettings();
      f3display_30.method2("Render Distance: ", mixinhelper2_82.bridge$getRenderDistance() + "");
      if (f3modulechildmod1 == null || (Boolean)f3modulechildmod1.showSimulationDistance.get()) {
         f3display_30.method2("Simulation Distance: ", mixinhelper2_82.bridge$getSimulationDistance() + "");
      }
   }

   public static void method24(F3DebugWriter f3display_30, @Nullable F3DisplayModule f3modulechildmod1) {
      String text2 = Ref.method3().bridge$getSoundHandler().bridge$getDebugString();
      f3display_30.method2("Sounds: ", text2);
      if (f3modulechildmod1 == null || (Boolean)f3modulechildmod1.showMood.get()) {
         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridge5extension_53 != null) {
            f3display_30.method2("Mood: ", Math.round(bridge5extension_53.bridge$getCurrentMood() * 100.0F) + "%");
         }
      }
   }

   public static String method25() {
      NetHandlerPlayClientBridge bridgeextension_70 = Ref.method3().bridge$getClientPacketListener();
      if (bridgeextension_70 == null) {
         return null;
      }

      IntegratedServerBridge glintcolorizer1 = Ref.method3().bridge$getIntegratedServer();
      if (glintcolorizer1 != null) {
         return "Integrated Server";
      }

      String text2 = bridgeextension_70.bridge$getServerBrand();
      return text2 != null && !text2.isEmpty() ? text2 : null;
   }

   public static String method26() {
      Bridge7_10 bridge7_100 = Ref.method3().bridge$getGameRenderer().bridge$getShaderGroup();
      return bridge7_100 == null ? null : bridge7_100.bridge$getShaderGroupName();
   }

   public static String method27() {
      int number0 = F3Display.bandwidthChartDisplay.RHHRCRRIHHICHROHHRIORRORIHICCR();
      return F3Display.bandwidthChartDisplay.method11(number0);
   }

   public static List<ProfilerResultBridge> method28() {
      List list0 = Ref.method3().bridge$getPieChartResults();
      if (list0 != null && !list0.isEmpty()) {
         field2 = list0;
      }

      return field2;
   }

   public static void method29() {
      if (!field2.isEmpty()) {
         field2 = List.of();
      }
   }

   private static String method30(Function<Bridge5Extension_5, String> function0) {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      return bridge5extension_51 == null ? null : (String)function0.apply(bridge5extension_51);
   }

   private static String method31(Function<Itemcounter6, String> function0) {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      return itemcounter6extension1 == null ? null : (String)function0.apply(itemcounter6extension1);
   }

   private static String method32(Function<Bridge14_3, String> function0) {
      if (Ref.method8() == null) {
         return null;
      }

      Bridge14_3 bridge14_31 = Ref.method3().bridge$getLevelRenderer();
      return bridge14_31 == null ? null : (String)function0.apply(bridge14_31);
   }

   public static String method33(double value0, int number2) {
      if (number2 <= 0) {
         return Long.toString((long)value0);
      }

      boolean flag3 = value0 < 0.0;
      if (flag3) {
         value0 = -value0;
      }
      long number4 = switch (number2) {
         case 1 -> 10L;
         case 2 -> 100L;
         case 3 -> 1000L;
         case 4 -> 10000L;
         case 5 -> 100000L;
         default -> {
            long number6 = 1L;

            for (int index8 = 0; index8 < number2; index8++) {
               number6 *= 10L;
            }

            yield yield6;
         }
      };
      long number15 = (long)(value0 * number4);
      long number16 = number15 / number4;
      long number10 = number15 % number4;
      StringBuilder builder12 = new StringBuilder(32);
      if (flag3 && (number16 != 0L || number10 != 0L)) {
         builder12.append('-');
      }

      builder12.append(number16).append(".");

      for (long index13 = number4 / 10L; index13 > 0L && number10 < index13; index13 /= 10L) {
         builder12.append('0');
      }

      if (number10 > 0L) {
         builder12.append(number10);
      }

      return builder12.toString();
   }

   @Generated
   private F3DebugInfo() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
