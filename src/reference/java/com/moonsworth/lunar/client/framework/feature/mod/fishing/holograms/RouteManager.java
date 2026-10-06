package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class RouteManager {
   public static final File field1 = new File(LunarConstants.field25, "Dungeon-Routes");
   private final SkyblockDungeonRoutes field2;
   private final RouteRecorder field3 = new RouteRecorder(this);
   private final RouteRenderer field4 = new RouteRenderer(this);
   private final List<int[]> field5 = new ArrayList<>();
   private final RouteFileLoader field6;
   private Map<String, List<DungeonRoute>> field7 = new HashMap<>();
   private Map<String, List<DungeonRoute>> field8 = new HashMap<>();
   @Nullable
   private RouteSection field9 = null;
   private RouteConditions field10 = RouteConditions.NONE;
   public RouteRequirements field11;
   private boolean field12 = false;
   private DungeonRoute field13 = null;
   private long field14;

   public RouteManager(SkyblockDungeonRoutes skyblockdungeonroutes1) {
      this.field6 = new RouteFileLoader();
      this.field2 = skyblockdungeonroutes1;
      this.field11 = new RouteRequirements(this.field2.method14());
      new Thread(() -> {
         this.field7 = this.field6.method2();
         this.field8 = this.field6.method1();
      }).start();
   }

   public void method1() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         boolean flag2 = false;
         boolean flag3 = false;
         RouteRequirements holograms_84 = new RouteRequirements(this.field2.method14());

         for (ItemStackBridge bridgeextension_46 : bridge5extension_51.bridge$getInventory().bridge$getMainInventory()) {
            String text7 = SkyblockItemUtil.method2(bridgeextension_46);
            if (text7 != null && !text7.isEmpty()) {
               if (!flag2 && SkyblockItemUtil.method21(text7)) {
                  flag2 = true;
               }

               CompoundTagComponent mixinhelper_108 = (CompoundTagComponent)bridgeextension_46.bridge$getDataComponent(DataComponentTypes.field1);
               if (!flag3 && SkyblockItemUtil.method20(mixinhelper_108)) {
                  flag3 = true;
               }

               holograms_84.method1(bridgeextension_46);
            }
         }

         boolean flag9 = (Boolean)SkyblockDungeonRoutes.method13().method24().get();
         this.field10 = new RouteConditions(flag2, flag3, flag9);
         this.field11 = holograms_84;
      }
   }

   public Stream<DungeonRoute> method2() {
      if (!this.method3()) {
         return Stream.empty();
      }

      List list1 = this.method4()
         .filter(arg0 -> !arg0.method19().hidden)
         .filter(arg1x -> arg1x.method1(this.field10))
         .filter(arg1x -> this.field11.method2(arg1x))
         .toList();
      RouteConditions holograms6_22 = list1.stream().map(DungeonRoute::method17).reduce(RouteConditions.NONE, RouteConditions::max);
      return list1.stream().filter(arg1x -> arg1x.method17().equals(holograms6_22));
   }

   private boolean method3() {
      SkyblockDungeonRoutes skyblockdungeonroutes1 = SkyblockDungeonRoutes.method13();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom holograms2 = this.method18().orElse(null);
      if (holograms2 == null) {
         return false;
      } else {
         return !skyblockdungeonroutes1.method17().isEmpty() && !skyblockdungeonroutes1.method17().contains(holograms2.getBlcID())
            ? false
            : skyblockdungeonroutes1.method19().isEmpty() || !skyblockdungeonroutes1.method19().contains(holograms2.getBlcID());
      }
   }

   public Stream<DungeonRoute> method4() {
      return this.method5(false);
   }

   public Stream<DungeonRoute> method5(boolean flag1) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom holograms2 = this.method18().orElse(null);
      if (holograms2 == null) {
         return Stream.empty();
      }

      List list3 = this.field7.get(holograms2.getBlcID());
      if (list3 != null && flag1) {
         List list4 = this.field8.get(holograms2.getBlcID());
         if (list4 != null) {
            list3 = new ArrayList(list3);
            list3.addAll(list4);
         }
      } else if ((list3 == null || list3.isEmpty()) && this.method22()) {
         list3 = this.field8.get(holograms2.getBlcID());
      }

      return list3 == null ? Stream.empty() : list3.stream();
   }

   public Stream<DungeonRoute> method6() {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom holograms1 = this.method18().orElse(null);
      if (holograms1 == null) {
         return Stream.empty();
      }

      List list2 = this.field7.get(holograms1.getBlcID());
      return list2 == null ? Stream.empty() : list2.stream();
   }

   public Stream<RouteSection> method7() {
      SkyblockDungeonRoutes skyblockdungeonroutes1 = SkyblockDungeonRoutes.method13();
      if (!skyblockdungeonroutes1.isEnabled()) {
         return Stream.empty();
      }

      if (this.field12) {
         return this.method4().map(DungeonRoute::method3);
      }

      if (this.method27().method9().isPresent()) {
         return Stream.of(this.method27().method9().get().method2());
      }

      if (this.field9 == null) {
         return this.method16().map(DungeonRoomTracker::method30).map(TrackedRoom::method2).orElse(null)
               == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED
            ? Stream.empty()
            : this.method2().map(DungeonRoute::method3);
      }

      if (this.field9 == this.field9.method7().method2()) {
         List list2 = this.field9.method7().method19().getSwapOnComplete();
         if (list2 != null) {
            Optional optional3 = this.method8(list2);
            if (optional3.isPresent()) {
               return Stream.of(this.field9, (RouteSection)optional3.get());
            }
         }
      }

      return Stream.of(this.field9);
   }

   public Optional<RouteSection> method8(List<RouteLink> list1) {
      return list1.stream()
         .map(arg1x -> arg1x.method3(this))
         .filter(Optional::isPresent)
         .map(Optional::get)
         .filter(arg1x -> arg1x.method7().method1(this.field10))
         .filter(arg1x -> this.field11.method3(arg1x.method7(), arg1x))
         .findFirst();
   }

   public boolean method9() {
      return this.field12;
   }

   public void method10(DungeonRoute holograms21, String text2, String text3) {
      holograms21.setName(text3);
      List list4 = this.field7.computeIfAbsent(text2, arg0 -> new ArrayList<>());
      if (!list4.contains(holograms21)) {
         list4.add(holograms21);
      }

      this.field9 = null;
      RouteDataCodec holograms4_35 = new RouteDataCodec(holograms21);
      String text6 = LunarConstants.field22.toJson(holograms4_35);
      File file7 = new File(field1, text2);
      file7.mkdirs();
      File file8 = new File(file7, text3 + ".lcroute");

      for (int index9 = 1; !file8.createNewFile(); file8 = new File(file7, text3 + "-(" + index9 + ").lcroute")) {
         holograms21.setName(text3 + "-(" + ++index9 + ")");
      }

      FileWriter filewriter10 = new FileWriter(file8);
      filewriter10.write(text6);
      filewriter10.close();
   }

   public void method11(int[] items1) {
      this.method12(items1, false);
   }

   public void method12(int[] items1, boolean flag2) {
      if (!this.method27().method10()) {
         RouteSection holograms73;
         do {
            holograms73 = this.field9;
            this.method13(items1, flag2);
         } while (this.field9 != holograms73);
      }
   }

   private void method13(int[] items1, boolean flag2) {
      this.field5.add(items1);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms33 = this.method17().orElse(null);
      if (holograms33 != null) {
         int[] items4 = holograms33.method20(items1);
         if (this.field9 == null) {
            this.method2().forEach(arg2x -> {
               List list3x = arg2x.method3().method9();
               if (arg2x.getSections().size() > 1 && !list3x.isEmpty()) {
                  for (ValuePair files6_25x : list3x) {
                     int[] items6x = (int[])files6_25x.field2;
                     if (items6x[0] == items4[0] && items6x[1] == items4[1] && items6x[2] == items4[2]) {
                        boolean flag7x = list3x.indexOf(files6_25x) == list3x.size() - 1;
                        this.field9 = arg2x.getSections().get(flag7x ? 1 : 0);
                        break;
                     }
                  }
               }
            });
         } else {
            RouteSection holograms75 = this.field9;
            if (holograms75 == holograms75.method7().method2()) {
               List list6 = holograms75.method7().method19().getSwapOnComplete();
               if (list6 != null) {
                  Optional optional7 = this.method8(list6);
                  if (optional7.isPresent()) {
                     holograms75 = (RouteSection)optional7.get();
                  }
               }
            }

            List list13 = holograms75.method9();
            int index14 = holograms75.getIndex();
            if (index14 < holograms75.method7().getSections().size() - 1 && !list13.isEmpty()) {
               int[] items8 = (int[])((ValuePair)list13.get(list13.size() - 1)).field2;
               if (items8[0] == items4[0] && items8[1] == items4[1] && items8[2] == items4[2]) {
                  this.field9 = holograms75.method7().getSections().get(index14 + 1);
                  this.field14 = Ref.method3().bridge$getSystemTime();
               } else if (flag2) {
                  int[] items9 = holograms33.method8(items8);
                  boolean flag10 = false;

                  for (int[] items12 : this.method29()) {
                     if (items9[0] == items12[0] && items9[1] == items12[1] && items9[2] == items12[2]) {
                        flag10 = true;
                        break;
                     }
                  }

                  Bridge5Extension_5 bridge5extension_515 = Ref.method7();
                  if (flag10 && bridge5extension_515 != null && bridge5extension_515.method15(items9[0], items9[1], items9[2]) < 49.0) {
                     this.field9 = holograms75.method7().getSections().get(index14 + 1);
                     this.field14 = Ref.method3().bridge$getSystemTime();
                  }
               }
            }
         }
      }
   }

   public void method14() {
      if (this.field9 != null && Ref.method3().bridge$getSystemTime() - this.field14 <= 1000L) {
         List list1 = this.field9.method6().getSwapOnLocked();
         if (list1 != null) {
            Optional optional2 = this.method8(list1);
            if (!optional2.isEmpty()) {
               this.field9 = (RouteSection)optional2.get();
            }
         }
      }
   }

   public void method15() {
      this.field5.clear();
      this.field9 = null;
   }

   public Optional<DungeonRoomTracker> method16() {
      return this.field2.method14().method5().flatMap(arg0 -> Optional.ofNullable(arg0.method29().method7()));
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance> method17() {
      return this.method16().flatMap(DungeonRoomTracker::method23);
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom> method18() {
      return this.method16().flatMap(DungeonRoomTracker::method23).flatMap(arg0 -> Optional.ofNullable(arg0.method26()));
   }

   public boolean method19() {
      this.field12 = !this.field12;
      return this.field12;
   }

   public boolean method20(String text1, DungeonRoute holograms22) {
      File file3 = new File(field1, text1);
      File file4 = new File(file3, holograms22.method22() + ".lcroute");
      if (!file4.exists()) {
         return false;
      }

      List list5 = this.field7.computeIfAbsent(text1, arg0 -> new ArrayList<>());
      list5.remove(holograms22);
      return file4.delete();
   }

   public boolean method21(String text1, String text2) {
      File file3 = new File(field1, text1);
      File file4 = new File(file3, text2 + ".lcroute");
      if (!file4.exists()) {
         return false;
      }

      List list5 = this.field7.computeIfAbsent(text1, arg0 -> new ArrayList<>());
      list5.removeIf(arg1x -> arg1x.method22().equals(text2));
      return file4.delete();
   }

   private boolean method22() {
      return (Boolean)SkyblockDungeonRoutes.method13().method21().get();
   }

   public Optional<DungeonRoute> method23(String text1) {
      return this.method5(true).filter(arg1x -> arg1x.method22().equals(text1)).findFirst();
   }

   public Optional<DungeonRoute> method24() {
      return Optional.ofNullable(this.field13);
   }

   public Optional<RouteSection> method25() {
      return Optional.ofNullable(this.field9);
   }

   public void method26(RouteSection holograms71) {
      this.field9 = holograms71;
   }

   @Generated
   public RouteRecorder method27() {
      return this.field3;
   }

   @Generated
   public RouteRenderer method28() {
      return this.field4;
   }

   @Generated
   public List<int[]> method29() {
      return this.field5;
   }

   @Generated
   public void method30(DungeonRoute holograms21) {
      this.field13 = holograms21;
   }
}
