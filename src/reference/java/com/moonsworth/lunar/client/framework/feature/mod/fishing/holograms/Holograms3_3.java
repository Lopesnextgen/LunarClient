package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemDataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagDataComponent;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
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

public class Holograms3_3 {
   public static final File field1 = new File(ThreadModuleDump48.field25, "Dungeon-Routes");
   private final SkyblockDungeonRoutes field2;
   private final Holograms5_3 field3 = new Holograms5_3(this);
   private final Holograms9 field4 = new Holograms9(this);
   private final List<int[]> field5 = new ArrayList<>();
   private final Holograms8 field6;
   private Map<String, List<Holograms2>> field7 = new HashMap<>();
   private Map<String, List<Holograms2>> field8 = new HashMap<>();
   @Nullable
   private Holograms7 field9 = null;
   private Holograms6_2 field10 = Holograms6_2.NONE;
   public Holograms_8 field11;
   private boolean field12 = false;
   private Holograms2 field13 = null;
   private long field14;

   public Holograms3_3(SkyblockDungeonRoutes var1) {
      this.field6 = new Holograms8();
      this.field2 = var1;
      this.field11 = new Holograms_8(this.field2.method14());
      new Thread(() -> {
         this.field7 = this.field6.method2();
         this.field8 = this.field6.method1();
      }).start();
   }

   public void method1() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null) {
         boolean var2 = false;
         boolean var3 = false;
         Holograms_8 var4 = new Holograms_8(this.field2.method14());

         for (ItemStackBridge var6 : var1.bridge$getInventory().bridge$getMainInventory()) {
            String var7 = Gui3.method2(var6);
            if (var7 != null && !var7.isEmpty()) {
               if (!var2 && Gui3.method21(var7)) {
                  var2 = true;
               }

               CompoundTagDataComponent var8 = (CompoundTagDataComponent)var6.bridge$getDataComponent(ItemDataComponentTypes.field1);
               if (!var3 && Gui3.method20(var8)) {
                  var3 = true;
               }

               var4.method1(var6);
            }
         }

         boolean var9 = (Boolean)SkyblockDungeonRoutes.method13().method24().get();
         this.field10 = new Holograms6_2(var2, var3, var9);
         this.field11 = var4;
      }
   }

   public Stream<Holograms2> method2() {
      if (!this.method3()) {
         return Stream.empty();
      }

      List var1 = this.method4()
         .filter(var0 -> !var0.method19().hidden)
         .filter(var1x -> var1x.method1(this.field10))
         .filter(var1x -> this.field11.method2(var1x))
         .toList();
      Holograms6_2 var2 = var1.stream().map(Holograms2::method17).reduce(Holograms6_2.NONE, Holograms6_2::max);
      return var1.stream().filter(var1x -> var1x.method17().equals(var2));
   }

   private boolean method3() {
      SkyblockDungeonRoutes var1 = SkyblockDungeonRoutes.method13();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms var2 = this.method18().orElse(null);
      if (var2 == null) {
         return false;
      } else {
         return !var1.method17().isEmpty() && !var1.method17().contains(var2.getBlcID())
            ? false
            : var1.method19().isEmpty() || !var1.method19().contains(var2.getBlcID());
      }
   }

   public Stream<Holograms2> method4() {
      return this.method5(false);
   }

   public Stream<Holograms2> method5(boolean var1) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms var2 = this.method18().orElse(null);
      if (var2 == null) {
         return Stream.empty();
      }

      List var3 = this.field7.get(var2.getBlcID());
      if (var3 != null && var1) {
         List var4 = this.field8.get(var2.getBlcID());
         if (var4 != null) {
            var3 = new ArrayList(var3);
            var3.addAll(var4);
         }
      } else if ((var3 == null || var3.isEmpty()) && this.method22()) {
         var3 = this.field8.get(var2.getBlcID());
      }

      return var3 == null ? Stream.empty() : var3.stream();
   }

   public Stream<Holograms2> method6() {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms var1 = this.method18().orElse(null);
      if (var1 == null) {
         return Stream.empty();
      }

      List var2 = this.field7.get(var1.getBlcID());
      return var2 == null ? Stream.empty() : var2.stream();
   }

   public Stream<Holograms7> method7() {
      SkyblockDungeonRoutes var1 = SkyblockDungeonRoutes.method13();
      if (!var1.isEnabled()) {
         return Stream.empty();
      }

      if (this.field12) {
         return this.method4().map(Holograms2::method3);
      }

      if (this.method27().method9().isPresent()) {
         return Stream.of(this.method27().method9().get().method2());
      }

      if (this.field9 == null) {
         return this.method16().map(Holograms4Iterator::method30).map(Holograms2_3::method2).orElse(null)
               == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED
            ? Stream.empty()
            : this.method2().map(Holograms2::method3);
      }

      if (this.field9 == this.field9.method7().method2()) {
         List var2 = this.field9.method7().method19().getSwapOnComplete();
         if (var2 != null) {
            Optional var3 = this.method8(var2);
            if (var3.isPresent()) {
               return Stream.of(this.field9, (Holograms7)var3.get());
            }
         }
      }

      return Stream.of(this.field9);
   }

   public Optional<Holograms7> method8(List<Holograms12> var1) {
      return var1.stream()
         .map(var1x -> var1x.method3(this))
         .filter(Optional::isPresent)
         .map(Optional::get)
         .filter(var1x -> var1x.method7().method1(this.field10))
         .filter(var1x -> this.field11.method3(var1x.method7(), var1x))
         .findFirst();
   }

   public boolean method9() {
      return this.field12;
   }

   public void method10(Holograms2 var1, String var2, String var3) {
      var1.setName(var3);
      List var4 = this.field7.computeIfAbsent(var2, var0 -> new ArrayList<>());
      if (!var4.contains(var1)) {
         var4.add(var1);
      }

      this.field9 = null;
      Holograms4_3 var5 = new Holograms4_3(var1);
      String var6 = ThreadModuleDump48.field22.toJson(var5);
      File var7 = new File(field1, var2);
      var7.mkdirs();
      File var8 = new File(var7, var3 + ".lcroute");

      for (int var9 = 1; !var8.createNewFile(); var8 = new File(var7, var3 + "-(" + var9 + ").lcroute")) {
         var1.setName(var3 + "-(" + ++var9 + ")");
      }

      FileWriter var10 = new FileWriter(var8);
      var10.write(var6);
      var10.close();
   }

   public void method11(int[] var1) {
      this.method12(var1, false);
   }

   public void method12(int[] var1, boolean var2) {
      if (!this.method27().method10()) {
         Holograms7 var3;
         do {
            var3 = this.field9;
            this.method13(var1, var2);
         } while (this.field9 != var3);
      }
   }

   private void method13(int[] var1, boolean var2) {
      this.field5.add(var1);
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var3 = this.method17().orElse(null);
      if (var3 != null) {
         int[] var4 = var3.method20(var1);
         if (this.field9 == null) {
            this.method2().forEach(var2x -> {
               List var3x = var2x.method3().method9();
               if (var2x.getSections().size() > 1 && !var3x.isEmpty()) {
                  for (Files6_2 var5x : var3x) {
                     int[] var6x = (int[])var5x.field2;
                     if (var6x[0] == var4[0] && var6x[1] == var4[1] && var6x[2] == var4[2]) {
                        boolean var7x = var3x.indexOf(var5x) == var3x.size() - 1;
                        this.field9 = var2x.getSections().get(var7x ? 1 : 0);
                        break;
                     }
                  }
               }
            });
         } else {
            Holograms7 var5 = this.field9;
            if (var5 == var5.method7().method2()) {
               List var6 = var5.method7().method19().getSwapOnComplete();
               if (var6 != null) {
                  Optional var7 = this.method8(var6);
                  if (var7.isPresent()) {
                     var5 = (Holograms7)var7.get();
                  }
               }
            }

            List var13 = var5.method9();
            int var14 = var5.getIndex();
            if (var14 < var5.method7().getSections().size() - 1 && !var13.isEmpty()) {
               int[] var8 = (int[])((Files6_2)var13.get(var13.size() - 1)).field2;
               if (var8[0] == var4[0] && var8[1] == var4[1] && var8[2] == var4[2]) {
                  this.field9 = var5.method7().getSections().get(var14 + 1);
                  this.field14 = ThreadModuleDump63.method3().bridge$getSystemTime();
               } else if (var2) {
                  int[] var9 = var3.method8(var8);
                  boolean var10 = false;

                  for (int[] var12 : this.method29()) {
                     if (var9[0] == var12[0] && var9[1] == var12[1] && var9[2] == var12[2]) {
                        var10 = true;
                        break;
                     }
                  }

                  Bridge5Extension_5 var15 = ThreadModuleDump63.method7();
                  if (var10 && var15 != null && var15.method15(var9[0], var9[1], var9[2]) < 49.0) {
                     this.field9 = var5.method7().getSections().get(var14 + 1);
                     this.field14 = ThreadModuleDump63.method3().bridge$getSystemTime();
                  }
               }
            }
         }
      }
   }

   public void method14() {
      if (this.field9 != null && ThreadModuleDump63.method3().bridge$getSystemTime() - this.field14 <= 1000L) {
         List var1 = this.field9.method6().getSwapOnLocked();
         if (var1 != null) {
            Optional var2 = this.method8(var1);
            if (!var2.isEmpty()) {
               this.field9 = (Holograms7)var2.get();
            }
         }
      }
   }

   public void method15() {
      this.field5.clear();
      this.field9 = null;
   }

   public Optional<Holograms4Iterator> method16() {
      return this.field2.method14().method5().flatMap(var0 -> Optional.ofNullable(var0.method29().method7()));
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3> method17() {
      return this.method16().flatMap(Holograms4Iterator::method23);
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms> method18() {
      return this.method16().flatMap(Holograms4Iterator::method23).flatMap(var0 -> Optional.ofNullable(var0.method26()));
   }

   public boolean method19() {
      this.field12 = !this.field12;
      return this.field12;
   }

   public boolean method20(String var1, Holograms2 var2) {
      File var3 = new File(field1, var1);
      File var4 = new File(var3, var2.method22() + ".lcroute");
      if (!var4.exists()) {
         return false;
      }

      List var5 = this.field7.computeIfAbsent(var1, var0 -> new ArrayList<>());
      var5.remove(var2);
      return var4.delete();
   }

   public boolean method21(String var1, String var2) {
      File var3 = new File(field1, var1);
      File var4 = new File(var3, var2 + ".lcroute");
      if (!var4.exists()) {
         return false;
      }

      List var5 = this.field7.computeIfAbsent(var1, var0 -> new ArrayList<>());
      var5.removeIf(var1x -> var1x.method22().equals(var2));
      return var4.delete();
   }

   private boolean method22() {
      return (Boolean)SkyblockDungeonRoutes.method13().method21().get();
   }

   public Optional<Holograms2> method23(String var1) {
      return this.method5(true).filter(var1x -> var1x.method22().equals(var1)).findFirst();
   }

   public Optional<Holograms2> method24() {
      return Optional.ofNullable(this.field13);
   }

   public Optional<Holograms7> method25() {
      return Optional.ofNullable(this.field9);
   }

   public void method26(Holograms7 var1) {
      this.field9 = var1;
   }

   @Generated
   public Holograms5_3 method27() {
      return this.field3;
   }

   @Generated
   public Holograms9 method28() {
      return this.field4;
   }

   @Generated
   public List<int[]> method29() {
      return this.field5;
   }

   @Generated
   public void method30(Holograms2 var1) {
      this.field13 = var1;
   }
}
