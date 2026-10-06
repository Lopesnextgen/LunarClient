package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInDoorAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInRoomAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomDetectionAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.EarlyInUnknownAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class RoomTemplateDetector {
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

   public RoomTemplateDetector() {
   }

   public static void method1(DungeonRoomTracker holograms4iterator0, boolean flag1) {
      RoomRotation hologramstype_32 = RoomRotation.EAST;
      Object obj3 = null;
      boolean flag4 = false;
      Bridge3_23 bridge3_235 = Bridge.method34().method44();
      WorldBridgeExtension itemcounter6extension6 = Ref.method8();
      if (itemcounter6extension6 != null) {
         WorldPosition nameplate47 = holograms4iterator0.method28().get(0);
         int number8 = method5(itemcounter6extension6, (int)nameplate47.method2(), (int)nameplate47.method3());
         DungeonRoomTracker holograms4iterator9 = holograms4iterator0.method27().method42();
         if (holograms4iterator0.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT
            || holograms4iterator9 == null
            || method2(holograms4iterator0, holograms4iterator9.method28().get(0), number8)) {
            label64:
            for (WorldPosition nameplate411 : holograms4iterator0.method28()) {
               if (!nameplate411.isLoaded()) {
                  return;
               }

               Horsestats20Extension2 horsestats20extension212 = Bridge.method8().method6(nameplate411.method2(), number8, nameplate411.method3());
               RoomRotation[] items13 = RoomRotation.VALUES;
               int number14 = items13.length;
               int index15 = 0;

               while (true) {
                  if (index15 < number14) {
                     RoomRotation hologramstype_316 = items13[index15];
                     Vector3i vector3i17 = new Vector3i(hologramstype_316.getX(), 0, hologramstype_316.getZ());
                     Vec3iBridge horsestats2018 = horsestats20extension212.bridge$add(vector3i17);
                     Bridge3_23 bridge3_2319 = itemcounter6extension6.method4(horsestats2018);
                     if (bridge3_2319 != bridge3_235) {
                        index15++;
                        continue;
                     }

                     hologramstype_32 = hologramstype_316;
                     obj3 = hologramstype_316.getCorner(horsestats2018);
                     flag4 = true;
                  }

                  if (flag4) {
                     break label64;
                  }
                  break;
               }
            }

            if (!flag4) {
               obj3 = Bridge.method8().method6(nameplate47.method2(), number8, nameplate47.method3());
            }

            String text20 = method3(itemcounter6extension6, (Vec3iBridge)obj3, hologramstype_32, number8);
            com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom holograms21 = method4(text20);
            if (holograms21 == null && !flag1) {
               Ref.method4().method69().method3(NotificationManager.method15("skyblockUnknownDungeonRoom", new Object[0]));
            }

            holograms4iterator0.method1(new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance(holograms21, text20, (Vec3iBridge)obj3, hologramstype_32));
            if (holograms21 != null) {
               holograms4iterator0.method27().method45().method2(new RoomDetectionAction(nameplate47.method15(), text20, obj3.bridge$toJoml(), hologramstype_32));
            }

            if (holograms21 != null) {
               holograms4iterator0.method16(holograms21.roomType());
            }

            if (!flag1) {
               method14(holograms4iterator0);
            }
         }
      }
   }

   private static boolean method2(DungeonRoomTracker holograms4iterator0, WorldPosition nameplate41, int number2) {
      List list3 = method15(Ref.method8(), holograms4iterator0.method28().get(0), nameplate41, number2, holograms4iterator0.method27());
      if (list3 == null) {
         return false;
      }

      boolean flag4 = false;

      for (WorldPosition nameplate46 : list3) {
         if (!holograms4iterator0.method24(nameplate46)) {
            holograms4iterator0.method5(nameplate46);
            flag4 = true;
         }
      }

      if (flag4) {
         holograms4iterator0.method27().method45().method2(new EarlyInRoomAction(list3.stream().map(WorldPosition::method15).toArray(Vector2i[]::new), holograms4iterator0.method30().method6()));
      }

      return true;
   }

   private static String method3(WorldBridgeExtension itemcounter6extension0, Vec3iBridge horsestats201, RoomRotation hologramstype_32, int number3) {
      StringBuilder builder4 = new StringBuilder();

      for (int index5 = 1; index5 <= 2; index5++) {
         for (int index6 = 1; index6 <= 2; index6++) {
            Vec3iBridge horsestats207 = method7(horsestats201, Bridge.method8().method4(index5 * 10, 0, index6 * 10), hologramstype_32);
            int number8 = horsestats207.bridge$getX();
            int number9 = horsestats207.bridge$getZ();

            for (int index10 = number3; index10 >= 0; index10--) {
               Bridge3_23 bridge3_2311 = itemcounter6extension0.bridge$getBlockAt(number8, index10, number9);
               int index12 = bridge3_2311.bridge$getPreFlatteningID();
               if (!field3.contains(index12)) {
                  if (!field4.containsKey(index12)) {
                     builder4.append(index12);
                  } else {
                     builder4.append(field4.get(index12));
                  }
               } else {
                  builder4.append(0);
               }
            }
         }
      }

      int number13 = builder4.toString().hashCode();
      return number13 + "-" + number3;
   }

   public static com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom method4(String text0) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom[] items1 = Ref.method4()
         .method40()
         .method82()
         .method15()
         .method12();
      if (items1 == null) {
         return null;
      }

      for (com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom holograms5 : items1) {
         String[] items6 = holograms5.hashes();

         for (String text10 : items6) {
            if (text10.equals(text0)) {
               return holograms5;
            }
         }
      }

      return null;
   }

   private static int method5(WorldBridgeExtension itemcounter6extension0, int number1, int number2) {
      Bridge3_23 bridge3_233 = Bridge.method34().method3();

      for (int index4 = 254; index4 >= 83; index4--) {
         if (itemcounter6extension0.bridge$getBlockAt(number1, index4, number2) != bridge3_233) {
            return index4;
         }
      }

      return -1;
   }

   public static Vector3i method6(Vector3i vector3i0, Vector3i vector3i1, RoomRotation hologramstype_32) {
      int number3 = vector3i0.x();
      int number4 = vector3i0.z();
      int number5 = vector3i1.x();
      int number6 = vector3i1.z();
      switch (hologramstype_32) {
         case EAST:
            number3 += number5;
            number4 += number6;
            break;
         case SOUTH:
            number3 -= number6;
            number4 += number5;
            break;
         case WEST:
            number3 -= number5;
            number4 -= number6;
            break;
         case NORTH:
            number3 += number6;
            number4 -= number5;
      }

      return new Vector3i(number3, vector3i1.y(), number4);
   }

   public static Vec3iBridge method7(Vec3iBridge horsestats200, Vec3iBridge horsestats201, RoomRotation hologramstype_32) {
      int number3 = horsestats200.bridge$getX();
      int number4 = horsestats200.bridge$getZ();
      int number5 = horsestats201.bridge$getX();
      int number6 = horsestats201.bridge$getZ();
      switch (hologramstype_32) {
         case EAST:
            number3 += number5;
            number4 += number6;
            break;
         case SOUTH:
            number3 -= number6;
            number4 += number5;
            break;
         case WEST:
            number3 -= number5;
            number4 -= number6;
            break;
         case NORTH:
            number3 += number6;
            number4 -= number5;
      }

      return Bridge.method8().method4(number3, horsestats201.bridge$getY(), number4);
   }

   public static int[] method8(Vec3iBridge horsestats200, int[] items1, RoomRotation hologramstype_32) {
      int number3 = horsestats200.bridge$getX();
      int number4 = horsestats200.bridge$getZ();
      int number5 = items1[0];
      int number6 = items1[2];
      switch (hologramstype_32) {
         case EAST:
            number3 += number5;
            number4 += number6;
            break;
         case SOUTH:
            number3 -= number6;
            number4 += number5;
            break;
         case WEST:
            number3 -= number5;
            number4 -= number6;
            break;
         case NORTH:
            number3 += number6;
            number4 -= number5;
      }

      return new int[]{number3, items1[1], number4};
   }

   public static double[] method9(Vec3iBridge horsestats200, double value1, double value3, double value5, RoomRotation hologramstype_37) {
      double value8 = horsestats200.bridge$getX();
      double value10 = horsestats200.bridge$getZ();
      switch (hologramstype_37) {
         case EAST:
            value8 += value1;
            value10 += value5;
            break;
         case SOUTH:
            value8 -= value5;
            value10 += value1;
            break;
         case WEST:
            value8 -= value1;
            value10 -= value5;
            break;
         case NORTH:
            value8 += value5;
            value10 -= value1;
      }

      return new double[]{value8, value3, value10};
   }

   public static Vec3iBridge method10(Vec3iBridge horsestats200, Vec3iBridge horsestats201, RoomRotation hologramstype_32) {
      int number3 = horsestats200.bridge$getX();
      int number4 = horsestats200.bridge$getZ();
      int number5 = horsestats201.bridge$getX();
      int number6 = horsestats201.bridge$getZ();
      int number7 = 0;
      int number8 = 0;
      switch (hologramstype_32) {
         case EAST:
            number7 = number5 - number3;
            number8 = number6 - number4;
            break;
         case SOUTH:
            number7 = number6 - number4;
            number8 = number3 - number5;
            break;
         case WEST:
            number7 = number3 - number5;
            number8 = number4 - number6;
            break;
         case NORTH:
            number7 = number4 - number6;
            number8 = number5 - number3;
      }

      return Bridge.method8().method4(number7, horsestats201.bridge$getY(), number8);
   }

   public static Vector3i method11(Vector3i vector3i0, Vector3i vector3i1, RoomRotation hologramstype_32) {
      int number3 = vector3i0.x();
      int number4 = vector3i0.z();
      int number5 = vector3i1.x();
      int number6 = vector3i1.z();
      int number7 = 0;
      int number8 = 0;
      switch (hologramstype_32) {
         case EAST:
            number7 = number5 - number3;
            number8 = number6 - number4;
            break;
         case SOUTH:
            number7 = number6 - number4;
            number8 = number3 - number5;
            break;
         case WEST:
            number7 = number3 - number5;
            number8 = number4 - number6;
            break;
         case NORTH:
            number7 = number4 - number6;
            number8 = number5 - number3;
      }

      return new Vector3i(number7, vector3i1.y(), number8);
   }

   public static int[] method12(Vec3iBridge horsestats200, int number1, int number2, int number3, RoomRotation hologramstype_34) {
      int number5 = horsestats200.bridge$getX();
      int number6 = horsestats200.bridge$getZ();
      int number7 = 0;
      int number8 = 0;
      switch (hologramstype_34) {
         case EAST:
            number7 = number1 - number5;
            number8 = number3 - number6;
            break;
         case SOUTH:
            number7 = number3 - number6;
            number8 = number5 - number1;
            break;
         case WEST:
            number7 = number5 - number1;
            number8 = number6 - number3;
            break;
         case NORTH:
            number7 = number6 - number3;
            number8 = number1 - number5;
      }

      return new int[]{number7, number2, number8};
   }

   public static void method13(DungeonStateTracker holograms2_50, WorldPosition nameplate41) {
      DungeonRoomTracker holograms4iterator2 = new DungeonRoomTracker(holograms2_50, MapRoomType.SPAWN);
      holograms4iterator2.method3(nameplate41);
      method1(holograms4iterator2, true);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms33 = holograms4iterator2.method23().orElse(null);
      if (holograms33 != null && holograms33.method26() != null && holograms33.method26().roomType() == MapRoomType.SPAWN) {
         holograms4iterator2.method4();
         holograms4iterator2.method5(nameplate41);
         method14(holograms4iterator2);
      }
   }

   private static void method14(DungeonRoomTracker holograms4iterator0) {
      BlocksBridge bridge_561 = Bridge.method34();
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      DungeonStateTracker holograms2_53 = holograms4iterator0.method27();
      method16(holograms4iterator0).forEach(arg4 -> {
         Bridge3_23 bridge3_235 = itemcounter6extension2.method4(arg4);
         WorldPosition nameplate46 = new WorldPosition(arg4.bridge$getX(), arg4.bridge$getZ(), holograms2_53);
         WorldPosition nameplate47 = null;

         for (RoomRotation hologramstype_311 : RoomRotation.VALUES) {
            HorsestatsType_2 horsestatstype_212 = hologramstype_311.asDirectionBridge();
            nameplate47 = new WorldPosition(arg4.bridge$getX() + horsestatstype_212.getOffsetX() * 5, arg4.bridge$getZ() + horsestatstype_212.getOffsetZ() * 5, holograms2_53);
            if (nameplate46.method8() != nameplate47.method8() || nameplate46.method9() != nameplate47.method9()) {
               break;
            }
         }

         if (nameplate47 != null && holograms2_53.method16(nameplate46, nameplate47) == null) {
            nameplate46.method14();
            nameplate47.method14();
            RoomStateHistory rewindhandlers14;
            if (bridge3_235 == bridge_561.method51()) {
               rewindhandlers14 = new RoomStateHistory(holograms2_53, MapRoomType.WITHER_DOOR, nameplate46, nameplate47);
            } else if (bridge3_235 == bridge_561.method45()) {
               rewindhandlers14 = new RoomStateHistory(holograms2_53, MapRoomType.BLOOD, nameplate46, nameplate47);
            } else if (bridge3_235 == bridge_561.method3() && holograms4iterator0.method30().method6() != MapRoomType.SPAWN) {
               Bridge3_23 bridge3_2315 = itemcounter6extension2.method4(arg4.bridge$offset(0, -1, 0));
               Bridge3_23 bridge3_2318 = itemcounter6extension2.method4(arg4.bridge$offset(0, -2, 0));
               Bridge3_23 bridge3_2319 = itemcounter6extension2.method4(arg4.bridge$offset(0, 1, 0));
               Bridge3_23 bridge3_2320 = itemcounter6extension2.method4(arg4.bridge$offset(0, 2, 0));
               Bridge3_23 bridge3_2313 = itemcounter6extension2.method4(arg4.bridge$offset(0, 3, 0));
               if (bridge3_2315 != bridge_561.method3() || bridge3_2318 == bridge_561.method3() || bridge3_2319 != bridge_561.method3() || bridge3_2320 != bridge_561.method3() || bridge3_2313 == bridge_561.method3()) {
                  return;
               }

               rewindhandlers14 = new RoomStateHistory(holograms2_53, MapRoomType.UNKNOWN, nameplate46, nameplate47);
            } else {
               if (bridge3_235 != bridge_561.method69()) {
                  return;
               }

               rewindhandlers14 = new RoomStateHistory(holograms2_53, MapRoomType.UNKNOWN, nameplate46, nameplate47);
            }

            if (holograms2_53.method14(nameplate46) == null) {
               DungeonRoomTracker holograms4iterator16 = new DungeonRoomTracker(holograms2_53, MapRoomType.UNKNOWN);
               holograms4iterator16.method5(nameplate46);
               holograms4iterator16.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT);
               holograms2_53.method45().method2(new EarlyInUnknownAction(nameplate46.method15()));
            } else if (holograms2_53.method14(nameplate47) == null) {
               DungeonRoomTracker holograms4iterator17 = new DungeonRoomTracker(holograms2_53, MapRoomType.UNKNOWN);
               holograms4iterator17.method5(nameplate47);
               holograms4iterator17.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT);
               holograms2_53.method45().method2(new EarlyInUnknownAction(nameplate47.method15()));
            }

            holograms4iterator0.method27().method22(rewindhandlers14);
            holograms2_53.method45().method2(new EarlyInDoorAction(rewindhandlers14.method8(), rewindhandlers14.method6().method15(), rewindhandlers14.method7().method15()));
         }
      });
   }

   @Nullable
   private static List<WorldPosition> method15(WorldBridgeExtension itemcounter6extension0, WorldPosition nameplate41, WorldPosition nameplate42, int number3, DungeonStateTracker holograms2_54) {
      ArrayList list5 = new ArrayList();
      list5.add(nameplate41);
      if (nameplate41.equals(nameplate42)) {
         return list5;
      }

      LinkedList list6 = new LinkedList();
      list6.add(nameplate41);

      while (!list6.isEmpty()) {
         WorldPosition nameplate47 = (WorldPosition)list6.remove();
         Horsestats20Extension2 horsestats20extension28 = Bridge.method8().method6(nameplate47.method2(), number3, nameplate47.method3());

         for (MapRotation holograms$type12 : MapRotation.values()) {
            Vector3i vector3i13 = new Vector3i(holograms$type12.getX(), 0, holograms$type12.getZ());
            Vec3iBridge horsestats2014 = horsestats20extension28.bridge$add(vector3i13);
            int number15 = (int)Math.floor(horsestats2014.bridge$getX() / 16.0);
            int number16 = (int)Math.floor(horsestats2014.bridge$getZ() / 16.0);
            if (!itemcounter6extension0.bridge$isChunkLoaded(number15, number16)) {
               return null;
            }

            if (itemcounter6extension0.method4(horsestats2014) != Bridge.method34().method3()) {
               Vec3iBridge horsestats2017 = horsestats2014.bridge$add(vector3i13);
               WorldPosition nameplate418 = new WorldPosition(horsestats2017.bridge$getX(), horsestats2017.bridge$getZ(), holograms2_54);
               nameplate418.method14();
               if (!nameplate418.equals(nameplate42) && !list5.contains(nameplate418)) {
                  if (!nameplate418.isLoaded()) {
                     return null;
                  }

                  list5.add(nameplate418);
                  list6.add(nameplate418);
               }
            }
         }
      }

      return list5;
   }

   private static ArrayList<Vec3iBridge> method16(DungeonRoomTracker holograms4iterator0) {
      ArrayList list1 = new ArrayList();
      BridgeImplementation bridge22 = Bridge.method8();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms33 = holograms4iterator0.method23().orElse(null);
      if (holograms33 == null) {
         return list1;
      }

      switch (holograms4iterator0.method12()) {
         case ONE_BY_ONE:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(31, 70, 15)));
            break;
         case ONE_BY_TWO:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(63, 70, 15)));
            break;
         case ONE_BY_THREE:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(79, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(79, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(95, 70, 15)));
            break;
         case ONE_BY_FOUR:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(79, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(79, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(111, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(111, 70, 31)));
            list1.add(holograms33.method1(bridge22.method4(127, 70, 15)));
            break;
         case TWO_BY_TWO:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(63, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(63, 70, 47)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 63)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 63)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 47)));
            break;
         case L_SHAPE:
            list1.add(holograms33.method1(bridge22.method4(15, 70, -1)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(31, 70, 15)));
            list1.add(holograms33.method1(bridge22.method4(63, 70, 47)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 63)));
            list1.add(holograms33.method1(bridge22.method4(15, 70, 63)));
            list1.add(holograms33.method1(bridge22.method4(-1, 70, 47)));
            list1.add(holograms33.method1(bridge22.method4(47, 70, 31)));
      }

      return list1;
   }
}
