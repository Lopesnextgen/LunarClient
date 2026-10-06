package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge_56;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType22;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType24;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType25;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType26;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class Holograms_4 {
   public static final int field1 = 254;
   public static final int field2 = 83;
   private static final IntList field3 = new IntArrayList(new int[]{5, 35, 54, 68, 77, 144, 146});
   private static final Int2IntArrayMap field4 = new Int2IntArrayMap() {
      {
         this.put(8, 9);
         this.put(10, 11);
         this.put(43, 44);
         this.put(62, 61);
         this.put(125, 126);
         this.put(181, 182);
      }
   };

   public static void method1(Holograms4Iterator var0, boolean var1) {
      HologramsType_3 var2 = HologramsType_3.EAST;
      Object var3 = null;
      boolean var4 = false;
      Bridge3_23 var5 = Bridge.method34().method44();
      Itemcounter6Extension var6 = ThreadModuleDump63.method8();
      if (var6 != null) {
         Nameplate4 var7 = var0.method28().get(0);
         int var8 = method5(var6, (int)var7.method2(), (int)var7.method3());
         Holograms4Iterator var9 = var0.method27().method42();
         if (var0.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT
            || var9 == null
            || method2(var0, var9.method28().get(0), var8)) {
            label64:
            for (Nameplate4 var11 : var0.method28()) {
               if (!var11.isLoaded()) {
                  return;
               }

               Horsestats20Extension2 var12 = Bridge.method8().method6(var11.method2(), var8, var11.method3());
               HologramsType_3[] var13 = HologramsType_3.VALUES;
               int var14 = var13.length;
               int var15 = 0;

               while (true) {
                  if (var15 < var14) {
                     HologramsType_3 var16 = var13[var15];
                     Vector3i var17 = new Vector3i(var16.getX(), 0, var16.getZ());
                     Vector3iBridge var18 = var12.bridge$add(var17);
                     Bridge3_23 var19 = var6.method4(var18);
                     if (var19 != var5) {
                        var15++;
                        continue;
                     }

                     var2 = var16;
                     var3 = var16.getCorner(var18);
                     var4 = true;
                  }

                  if (var4) {
                     break label64;
                  }
                  break;
               }
            }

            if (!var4) {
               var3 = Bridge.method8().method6(var7.method2(), var8, var7.method3());
            }

            String var20 = method3(var6, (Vector3iBridge)var3, var2, var8);
            com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms var21 = method4(var20);
            if (var21 == null && !var1) {
               ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skyblockUnknownDungeonRoom", new Object[0]));
            }

            var0.method1(new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3(var21, var20, (Vector3iBridge)var3, var2));
            if (var21 != null) {
               var0.method27().method45().method2(new HologramsType25(var7.getProvider(), var20, var3.bridge$toJoml(), var2));
            }

            if (var21 != null) {
               var0.method16(var21.roomType());
            }

            if (!var1) {
               method14(var0);
            }
         }
      }
   }

   private static boolean method2(Holograms4Iterator var0, Nameplate4 var1, int var2) {
      List var3 = method15(ThreadModuleDump63.method8(), var0.method28().get(0), var1, var2, var0.method27());
      if (var3 == null) {
         return false;
      }

      boolean var4 = false;

      for (Nameplate4 var6 : var3) {
         if (!var0.method24(var6)) {
            var0.method5(var6);
            var4 = true;
         }
      }

      if (var4) {
         var0.method27().method45().method2(new HologramsType24(var3.stream().map(Nameplate4::getProvider).toArray(Vector2i[]::new), var0.method30().method6()));
      }

      return true;
   }

   private static String method3(Itemcounter6Extension var0, Vector3iBridge var1, HologramsType_3 var2, int var3) {
      StringBuilder var4 = new StringBuilder();

      for (int var5 = 1; var5 <= 2; var5++) {
         for (int var6 = 1; var6 <= 2; var6++) {
            Vector3iBridge var7 = method7(var1, Bridge.method8().method4(var5 * 10, 0, var6 * 10), var2);
            int var8 = var7.bridge$getX();
            int var9 = var7.bridge$getZ();

            for (int var10 = var3; var10 >= 0; var10--) {
               Bridge3_23 var11 = var0.bridge$getBlockAt(var8, var10, var9);
               int var12 = var11.bridge$getPreFlatteningID();
               if (!field3.contains(var12)) {
                  if (!field4.containsKey(var12)) {
                     var4.append(var12);
                  } else {
                     var4.append(field4.get(var12));
                  }
               } else {
                  var4.append(0);
               }
            }
         }
      }

      int var13 = var4.toString().hashCode();
      return var13 + "-" + var3;
   }

   public static com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms method4(String var0) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms[] var1 = ThreadModuleDump63.method4()
         .method40()
         .method82()
         .method15()
         .method12();
      if (var1 == null) {
         return null;
      }

      for (com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms var5 : var1) {
         String[] var6 = var5.hashes();

         for (String var10 : var6) {
            if (var10.equals(var0)) {
               return var5;
            }
         }
      }

      return null;
   }

   private static int method5(Itemcounter6Extension var0, int var1, int var2) {
      Bridge3_23 var3 = Bridge.method34().method3();

      for (int var4 = 254; var4 >= 83; var4--) {
         if (var0.bridge$getBlockAt(var1, var4, var2) != var3) {
            return var4;
         }
      }

      return -1;
   }

   public static Vector3i method6(Vector3i var0, Vector3i var1, HologramsType_3 var2) {
      int var3 = var0.x();
      int var4 = var0.z();
      int var5 = var1.x();
      int var6 = var1.z();
      switch (var2) {
         case EAST:
            var3 += var5;
            var4 += var6;
            break;
         case SOUTH:
            var3 -= var6;
            var4 += var5;
            break;
         case WEST:
            var3 -= var5;
            var4 -= var6;
            break;
         case NORTH:
            var3 += var6;
            var4 -= var5;
      }

      return new Vector3i(var3, var1.y(), var4);
   }

   public static Vector3iBridge method7(Vector3iBridge var0, Vector3iBridge var1, HologramsType_3 var2) {
      int var3 = var0.bridge$getX();
      int var4 = var0.bridge$getZ();
      int var5 = var1.bridge$getX();
      int var6 = var1.bridge$getZ();
      switch (var2) {
         case EAST:
            var3 += var5;
            var4 += var6;
            break;
         case SOUTH:
            var3 -= var6;
            var4 += var5;
            break;
         case WEST:
            var3 -= var5;
            var4 -= var6;
            break;
         case NORTH:
            var3 += var6;
            var4 -= var5;
      }

      return Bridge.method8().method4(var3, var1.bridge$getY(), var4);
   }

   public static int[] method8(Vector3iBridge var0, int[] var1, HologramsType_3 var2) {
      int var3 = var0.bridge$getX();
      int var4 = var0.bridge$getZ();
      int var5 = var1[0];
      int var6 = var1[2];
      switch (var2) {
         case EAST:
            var3 += var5;
            var4 += var6;
            break;
         case SOUTH:
            var3 -= var6;
            var4 += var5;
            break;
         case WEST:
            var3 -= var5;
            var4 -= var6;
            break;
         case NORTH:
            var3 += var6;
            var4 -= var5;
      }

      return new int[]{var3, var1[1], var4};
   }

   public static double[] method9(Vector3iBridge var0, double var1, double var3, double var5, HologramsType_3 var7) {
      double var8 = var0.bridge$getX();
      double var10 = var0.bridge$getZ();
      switch (var7) {
         case EAST:
            var8 += var1;
            var10 += var5;
            break;
         case SOUTH:
            var8 -= var5;
            var10 += var1;
            break;
         case WEST:
            var8 -= var1;
            var10 -= var5;
            break;
         case NORTH:
            var8 += var5;
            var10 -= var1;
      }

      return new double[]{var8, var3, var10};
   }

   public static Vector3iBridge method10(Vector3iBridge var0, Vector3iBridge var1, HologramsType_3 var2) {
      int var3 = var0.bridge$getX();
      int var4 = var0.bridge$getZ();
      int var5 = var1.bridge$getX();
      int var6 = var1.bridge$getZ();
      int var7 = 0;
      int var8 = 0;
      switch (var2) {
         case EAST:
            var7 = var5 - var3;
            var8 = var6 - var4;
            break;
         case SOUTH:
            var7 = var6 - var4;
            var8 = var3 - var5;
            break;
         case WEST:
            var7 = var3 - var5;
            var8 = var4 - var6;
            break;
         case NORTH:
            var7 = var4 - var6;
            var8 = var5 - var3;
      }

      return Bridge.method8().method4(var7, var1.bridge$getY(), var8);
   }

   public static Vector3i method11(Vector3i var0, Vector3i var1, HologramsType_3 var2) {
      int var3 = var0.x();
      int var4 = var0.z();
      int var5 = var1.x();
      int var6 = var1.z();
      int var7 = 0;
      int var8 = 0;
      switch (var2) {
         case EAST:
            var7 = var5 - var3;
            var8 = var6 - var4;
            break;
         case SOUTH:
            var7 = var6 - var4;
            var8 = var3 - var5;
            break;
         case WEST:
            var7 = var3 - var5;
            var8 = var4 - var6;
            break;
         case NORTH:
            var7 = var4 - var6;
            var8 = var5 - var3;
      }

      return new Vector3i(var7, var1.y(), var8);
   }

   public static int[] method12(Vector3iBridge var0, int var1, int var2, int var3, HologramsType_3 var4) {
      int var5 = var0.bridge$getX();
      int var6 = var0.bridge$getZ();
      int var7 = 0;
      int var8 = 0;
      switch (var4) {
         case EAST:
            var7 = var1 - var5;
            var8 = var3 - var6;
            break;
         case SOUTH:
            var7 = var3 - var6;
            var8 = var5 - var1;
            break;
         case WEST:
            var7 = var5 - var1;
            var8 = var6 - var3;
            break;
         case NORTH:
            var7 = var6 - var3;
            var8 = var1 - var5;
      }

      return new int[]{var7, var2, var8};
   }

   public static void method13(Holograms2_5 var0, Nameplate4 var1) {
      Holograms4Iterator var2 = new Holograms4Iterator(var0, HologramsType5.SPAWN);
      var2.method3(var1);
      method1(var2, true);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var3 = var2.method23().orElse(null);
      if (var3 != null && var3.method26() != null && var3.method26().roomType() == HologramsType5.SPAWN) {
         var2.method4();
         var2.method5(var1);
         method14(var2);
      }
   }

   private static void method14(Holograms4Iterator var0) {
      Bridge_56 var1 = Bridge.method34();
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      Holograms2_5 var3 = var0.method27();
      method16(var0).forEach(var4 -> {
         Bridge3_23 var5 = var2.method4(var4);
         Nameplate4 var6 = new Nameplate4(var4.bridge$getX(), var4.bridge$getZ(), var3);
         Nameplate4 var7 = null;

         for (HologramsType_3 var11 : HologramsType_3.VALUES) {
            HorsestatsType_2 var12 = var11.asDirectionBridge();
            var7 = new Nameplate4(var4.bridge$getX() + var12.getOffsetX() * 5, var4.bridge$getZ() + var12.getOffsetZ() * 5, var3);
            if (var6.method8() != var7.method8() || var6.method9() != var7.method9()) {
               break;
            }
         }

         if (var7 != null && var3.method16(var6, var7) == null) {
            var6.method14();
            var7.method14();
            Rewindhandlers var14;
            if (var5 == var1.method51()) {
               var14 = new Rewindhandlers(var3, HologramsType5.WITHER_DOOR, var6, var7);
            } else if (var5 == var1.method45()) {
               var14 = new Rewindhandlers(var3, HologramsType5.BLOOD, var6, var7);
            } else if (var5 == var1.method3() && var0.method30().method6() != HologramsType5.SPAWN) {
               Bridge3_23 var15 = var2.method4(var4.bridge$offset(0, -1, 0));
               Bridge3_23 var18 = var2.method4(var4.bridge$offset(0, -2, 0));
               Bridge3_23 var19 = var2.method4(var4.bridge$offset(0, 1, 0));
               Bridge3_23 var20 = var2.method4(var4.bridge$offset(0, 2, 0));
               Bridge3_23 var13 = var2.method4(var4.bridge$offset(0, 3, 0));
               if (var15 != var1.method3() || var18 == var1.method3() || var19 != var1.method3() || var20 != var1.method3() || var13 == var1.method3()) {
                  return;
               }

               var14 = new Rewindhandlers(var3, HologramsType5.UNKNOWN, var6, var7);
            } else {
               if (var5 != var1.method69()) {
                  return;
               }

               var14 = new Rewindhandlers(var3, HologramsType5.UNKNOWN, var6, var7);
            }

            if (var3.method14(var6) == null) {
               Holograms4Iterator var16 = new Holograms4Iterator(var3, HologramsType5.UNKNOWN);
               var16.method5(var6);
               var16.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT);
               var3.method45().method2(new HologramsType26(var6.getProvider()));
            } else if (var3.method14(var7) == null) {
               Holograms4Iterator var17 = new Holograms4Iterator(var3, HologramsType5.UNKNOWN);
               var17.method5(var7);
               var17.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT);
               var3.method45().method2(new HologramsType26(var7.getProvider()));
            }

            var0.method27().method22(var14);
            var3.method45().method2(new HologramsType22(var14.method8(), var14.method6().method15(), var14.method7().method15()));
         }
      });
   }

   @Nullable
   private static List<Nameplate4> method15(Itemcounter6Extension var0, Nameplate4 var1, Nameplate4 var2, int var3, Holograms2_5 var4) {
      ArrayList var5 = new ArrayList();
      var5.add(var1);
      if (var1.equals(var2)) {
         return var5;
      }

      LinkedList var6 = new LinkedList();
      var6.add(var1);

      while (!var6.isEmpty()) {
         Nameplate4 var7 = (Nameplate4)var6.remove();
         Horsestats20Extension2 var8 = Bridge.method8().method6(var7.method2(), var3, var7.method3());

         for (Holograms$Type var12 : Holograms$Type.values()) {
            Vector3i var13 = new Vector3i(var12.getX(), 0, var12.getZ());
            Vector3iBridge var14 = var8.bridge$add(var13);
            int var15 = (int)Math.floor(var14.bridge$getX() / 16.0);
            int var16 = (int)Math.floor(var14.bridge$getZ() / 16.0);
            if (!var0.bridge$isChunkLoaded(var15, var16)) {
               return null;
            }

            if (var0.method4(var14) != Bridge.method34().method3()) {
               Vector3iBridge var17 = var14.bridge$add(var13);
               Nameplate4 var18 = new Nameplate4(var17.bridge$getX(), var17.bridge$getZ(), var4);
               var18.method14();
               if (!var18.equals(var2) && !var5.contains(var18)) {
                  if (!var18.isLoaded()) {
                     return null;
                  }

                  var5.add(var18);
                  var6.add(var18);
               }
            }
         }
      }

      return var5;
   }

   private static ArrayList<Vector3iBridge> method16(Holograms4Iterator var0) {
      ArrayList var1 = new ArrayList();
      BridgeImplementation var2 = Bridge.method8();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var3 = var0.method23().orElse(null);
      if (var3 == null) {
         return var1;
      }

      switch (var0.method12()) {
         case ONE_BY_ONE:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(15, 70, 31)));
            var1.add(var3.method1(var2.method4(31, 70, 15)));
            break;
         case ONE_BY_TWO:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(15, 70, 31)));
            var1.add(var3.method1(var2.method4(47, 70, -1)));
            var1.add(var3.method1(var2.method4(47, 70, 31)));
            var1.add(var3.method1(var2.method4(63, 70, 15)));
            break;
         case ONE_BY_THREE:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(15, 70, 31)));
            var1.add(var3.method1(var2.method4(47, 70, -1)));
            var1.add(var3.method1(var2.method4(47, 70, 31)));
            var1.add(var3.method1(var2.method4(79, 70, -1)));
            var1.add(var3.method1(var2.method4(79, 70, 31)));
            var1.add(var3.method1(var2.method4(95, 70, 15)));
            break;
         case ONE_BY_FOUR:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(15, 70, 31)));
            var1.add(var3.method1(var2.method4(47, 70, -1)));
            var1.add(var3.method1(var2.method4(47, 70, 31)));
            var1.add(var3.method1(var2.method4(79, 70, -1)));
            var1.add(var3.method1(var2.method4(79, 70, 31)));
            var1.add(var3.method1(var2.method4(111, 70, -1)));
            var1.add(var3.method1(var2.method4(111, 70, 31)));
            var1.add(var3.method1(var2.method4(127, 70, 15)));
            break;
         case TWO_BY_TWO:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(47, 70, -1)));
            var1.add(var3.method1(var2.method4(63, 70, 15)));
            var1.add(var3.method1(var2.method4(63, 70, 47)));
            var1.add(var3.method1(var2.method4(47, 70, 63)));
            var1.add(var3.method1(var2.method4(15, 70, 63)));
            var1.add(var3.method1(var2.method4(-1, 70, 47)));
            break;
         case L_SHAPE:
            var1.add(var3.method1(var2.method4(15, 70, -1)));
            var1.add(var3.method1(var2.method4(-1, 70, 15)));
            var1.add(var3.method1(var2.method4(31, 70, 15)));
            var1.add(var3.method1(var2.method4(63, 70, 47)));
            var1.add(var3.method1(var2.method4(47, 70, 63)));
            var1.add(var3.method1(var2.method4(15, 70, 63)));
            var1.add(var3.method1(var2.method4(-1, 70, 47)));
            var1.add(var3.method1(var2.method4(47, 70, 31)));
      }

      return var1;
   }
}
