package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data9;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdateNotify;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;

public class SkyblockWaterRoom extends AbstractFeature {
   private final Map<Holograms6.Type2, Vector3i> field8 = new HashMap<>();
   private final Map<Holograms6.Type, Vector3i> field9 = new HashMap<>();
   private final List<SkyblockWaterRoom.Data> field10 = new ArrayList<>();
   private final List<Vec3D> field11 = new ArrayList<>();
   private final Map<Vector3f, Component> field12 = new HashMap<>();
   private int field13 = -1;
   private int field14 = -1;
   private Map<Holograms6.Type, Set<Holograms6.Data2>> field15;

   public SkyblockWaterRoom(SkyblockDungeonPuzzles var1, ToggleOption var2) {
      super(true);
      this.method13(Framework.field16, Framework4.method4(false, var1));
      this.method13(Framework.field6, ModEnabledState.method7(var2));
      this.method3(this::reset);
      this.handle(Rewindhandlers$Data10.class, var1x -> this.reset());
      this.handle(Rewindhandlers$Data9.class, var1x -> this.reset());
      this.handle(EventClientTick.class, var1x -> this.method13());
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method5);
      this.handle(HudRenderLegacyEventAlt.class, this::method6);
      this.handle(EventBlockUpdateNotify.Data.class, this::method2);
   }

   @Override
   public String getId() {
      return "SKYBLOCK_WATER_ROOM";
   }

   @Override
   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
         var2.method13();
      }
   }

   private void method2(EventBlockUpdateNotify.Data var1) {
      SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.WATER_BOARD && this.field13 != -1) {
         if (!this.field10.isEmpty()) {
            Vector3iBridge var4 = var1.method1();
            Vector3i var5 = this.field8.get(this.field10.get(0).field2);
            if (var5 != null) {
               if (var4.bridge$getX() == var5.x && var4.bridge$getY() == var5.y && var4.bridge$getZ() == var5.z) {
                  this.field10.remove(0);
               }
            }
         }
      }
   }

   public void method3(Holograms4Iterator var1) {
      this.reset();
      Holograms3 var2 = var1.method23().orElse(null);
      if (var2 != null) {
         ArrayList var3 = new ArrayList();
         var3.add(var2.method4(new Vector3i(14, 77, 26)));
         var3.add(var2.method4(new Vector3i(14, 77, 27)));
         var3.add(var2.method4(new Vector3i(14, 78, 26)));
         var3.add(var2.method4(new Vector3i(14, 78, 27)));
         ArrayList var4 = new ArrayList();
         var4.add(var2.method4(new Vector3i(16, 77, 26)));
         var4.add(var2.method4(new Vector3i(16, 77, 27)));
         var4.add(var2.method4(new Vector3i(16, 78, 26)));
         var4.add(var2.method4(new Vector3i(16, 78, 27)));
         Holograms6.Type2 var5 = this.method4(var4);
         Holograms6.Type2 var6 = this.method4(var3);
         if (var5 == Holograms6.Type2.GOLD && var6 == Holograms6.Type2.TERRACOTTA) {
            this.field13 = 1;
         } else if (var5 == Holograms6.Type2.EMERALD && var6 == Holograms6.Type2.QUARTZ) {
            this.field13 = 2;
         } else if (var5 == Holograms6.Type2.QUARTZ && var6 == Holograms6.Type2.DIAMOND) {
            this.field13 = 3;
         } else {
            if (var5 != Holograms6.Type2.GOLD || var6 != Holograms6.Type2.QUARTZ) {
               this.field13 = 0;
               Fishing2_2.method1("Couldn't find water room layout.");
               return;
            }

            this.field13 = 4;
         }

         SkyblockDungeonPuzzles var7 = ((Framework4)this.method7(Framework.field16)).method1();
         if (var7.method46().get()) {
            Fishing2_2.method1("Waterboard Puzzle Layout: " + this.field13);
         }

         for (Holograms6.Type var11 : Holograms6.Type.values()) {
            this.field9.put(var11, var2.method4(var11.getRelativePos()));
         }

         for (Holograms6.Type2 var16 : Holograms6.Type2.values()) {
            this.field8.put(var16, var2.method4(var16.getRelativePos()));
         }

         Holograms6 var13 = ThreadModuleDump63.method4().method40().method82().method15().method17();
         if (var13 != null) {
            this.field15 = var13.field2.get(this.field13 + "");
         }
      }
   }

   private Holograms6.Type2 method4(List<Vector3i> var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 == null) {
         return null;
      }

      for (Vector3i var4 : var1) {
         Bridge3_23 var5 = var2.bridge$getBlockAt(var4.x(), var4.y(), var4.z());

         for (Holograms6.Type2 var9 : Holograms6.Type2.values()) {
            if (var9 != Holograms6.Type2.WATER && var5 == var9.getBlock()) {
               return var9;
            }
         }
      }

      return null;
   }

   private void reset() {
      this.field13 = -1;
      this.field14 = -1;
      this.field12.clear();
      this.field11.clear();
      this.field9.clear();
      this.field8.clear();
      this.field10.clear();
   }

   private void method5(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent var1) {
      AbstractRenderContext var2 = var1.method3();

      for (Entry var4 : this.field12.entrySet()) {
         Vector3f var5 = (Vector3f)var4.getKey();
         Bridge2_43 var6 = ThreadModuleDump63.method13();
         var2.push();
         var2.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());
         Click.drawComponentCentered(var2, (Component)var4.getValue(), var5.x, var5.y, var5.z, true);
         var2.pop();
      }
   }

   private void method6(HudRenderLegacyEventAlt var1) {
      SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.WATER_BOARD && this.field13 != -1) {
         AbstractRenderContext var4 = var1.method3();
         if (!this.field11.isEmpty()) {
            float var5 = ThreadModuleDump63.method4().method40().method82().method19().get();
            Click.drawLookLine(this.field11.get(0), var4, -16711681, var5);
            if (this.field11.size() >= 2) {
               Bridge2_43 var6 = ThreadModuleDump63.method13();
               var4.push();
               var4.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());
               Click.drawLine(var4, new Line3D(this.field11.get(0), this.field11.get(1)), -16711681, var5, true);
               var4.pop();
            }
         }
      }
   }

   private void method13() {
      SkyblockDungeonPuzzles var1 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var2 = var1.method47();
      if (var2 != null && var2.method25() == HologramsType8.WATER_BOARD && this.field13 != -1) {
         if (var1.method45().get()) {
            this.method14();
         } else {
            this.method15();
         }
      }
   }

   private void method14() {
      if (this.field14 == -1) {
         this.method16();
         if (this.field14 == -1) {
            this.method15();
            return;
         }
      }

      if (this.field10.isEmpty()) {
         for (Holograms6.Type var14 : Holograms6.Type.values()) {
            if (!ThreadModuleDump63.method8().method5((Vector3ic)this.field9.get(var14)).bridge$isAir()) {
               this.method15();
               return;
            }
         }

         this.field12.clear();
         this.field11.clear();
      } else {
         SkyblockWaterRoom.Data var1 = this.field10.get(0);
         if (var1.field1.isPaused()) {
            Vector3i var2 = this.field8.get(Holograms6.Type2.WATER);
            Bridge3_23 var3 = ThreadModuleDump63.method8().method5(var2);
            if (var3.bridge$isFlippedLever(var2.x, var2.y, var2.z)) {
               this.field10.forEach(var0 -> var0.field1.method2());
            }
         }

         this.field10.removeIf(var0 -> var0.field1.get() < -1000L);
         this.field11.clear();
         this.field12.clear();
         HashMap var10 = new HashMap();

         for (SkyblockWaterRoom.Data var4 : this.field10) {
            Holograms6.Type2 var5 = var4.method2();
            Vector3i var6 = this.field8.get(var5);
            if (this.field11.size() < 2) {
               Vec3D var7 = new Vec3D(var6.x + 0.5F, var6.y + 0.5F, var6.z + 0.5F);
               this.field11.add(var7);
            }

            int var15 = var10.compute(var5, (var0, var1x) -> var1x == null ? 0 : var1x + 1);
            Vector3f var8 = new Vector3f(var6.x + 0.5F, var6.y + 1.5F + var15 / 2.0F, var6.z + 0.5F);
            this.field12.put(var8, this.method10(var4.field1));
         }
      }
   }

   private void method15() {
      if (this.field15 != null) {
         Itemcounter6Extension var1 = ThreadModuleDump63.method8();
         if (var1 != null) {
            this.field11.clear();
            this.field12.clear();
            HashMap var2 = new HashMap();

            for (Entry var4 : this.field15.entrySet()) {
               if (!this.method13(var1, (Holograms6.Type)var4.getKey())) {
                  for (Holograms6.Data2 var6 : (Set)var4.getValue()) {
                     Holograms6.Type2 var7 = var6.method2();
                     Vector3i var8 = this.field8.get(var7);
                     boolean var9 = var1.method5(var8).bridge$isFlippedLever(var8.x, var8.y, var8.z);
                     if ((var6.method3() != Holograms6.Data2.Type.UP || var9) && (var6.method3() != Holograms6.Data2.Type.DOWN || !var9)) {
                        int var10 = var2.compute(var7, (var0, var1x) -> var1x == null ? 0 : var1x + 1);
                        Vector3f var11 = new Vector3f(var8.x + 0.5F, var8.y + 1.5F + var10 / 2.0F, var8.z + 0.5F);
                        this.field12.put(var11, ((Holograms6.Type)var4.getKey()).getPretty());
                     }
                  }
               }
            }
         }
      }
   }

   private Component method10(ThreadModuleDump45 var1) {
      long var3 = var1.get();
      if (var3 <= 0L) {
         return Component.text("Click!").color(NamedTextColor.GREEN);
      }

      NamedTextColor var2;
      if (var3 > 5000L) {
         var2 = NamedTextColor.RED;
      } else if (var3 > 1000L) {
         var2 = NamedTextColor.GOLD;
      } else {
         var2 = NamedTextColor.YELLOW;
      }

      return Component.text(var1.method1()).color(var2);
   }

   private void method16() {
      if (this.field13 != -1) {
         Itemcounter6Extension var1 = ThreadModuleDump63.method8();
         if (var1 != null) {
            int var2 = 0;

            for (Holograms6.Type var6 : Holograms6.Type.values()) {
               if (!this.method13(var1, var6)) {
                  var2 += (int)Math.pow(2.0, var6.ordinal());
               }
            }

            if (var2 != 0) {
               this.field14 = var2;
               String var10 = this.field13 + "-" + this.field14;
               SkyblockDungeonPuzzles var11 = ((Framework4)this.method7(Framework.field16)).method1();
               if (var11.method46().get()) {
                  Fishing2_2.method1("WaterBoard Fast Layout: " + var10);
               }

               Holograms6 var12 = ThreadModuleDump63.method4().method40().method82().method15().method17();
               if (var12 != null) {
                  List var13 = var12.field1.get(var10);
                  if (var13 != null) {
                     this.field10.clear();
                     this.field10.addAll(this.method12(var13, var1));
                     boolean var7 = true;

                     for (Holograms6.Data2 var9 : var13) {
                        if (!var7 || var9.method2() == Holograms6.Type2.WATER) {
                           var7 = false;
                           this.field10
                              .add(
                                 new SkyblockWaterRoom.Data(
                                    ThreadModuleDump45.Data.method1().method2().method4().method5(var9.method1()).method7(), var9.method2(), false
                                 )
                              );
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private List<SkyblockWaterRoom.Data> method12(List<Holograms6.Data2> var1, Itemcounter6 var2) {
      HashSet var3 = new HashSet();

      for (Holograms6.Data2 var5 : var1) {
         if (var5.method2() == Holograms6.Type2.WATER) {
            break;
         }

         var3.add(var5.method2());
      }

      ArrayList var11 = new ArrayList();

      for (Holograms6.Type2 var8 : Holograms6.Type2.values()) {
         Vector3i var9 = this.field8.get(var8);
         Bridge3_23 var10 = var2.method5(var9);
         if (var10.bridge$isFlippedLever(var9.x, var9.y, var9.z) != var3.contains(var8)) {
            var11.add(new SkyblockWaterRoom.Data(ThreadModuleDump45.Data.method1().method2().method4().method5(0L).method7(), var8, true));
         }
      }

      return var11;
   }

   private boolean method13(Itemcounter6 var1, Holograms6.Type var2) {
      return var1.method5((Vector3ic)this.field9.get(var2)).bridge$isAir();
   }

   @Generated
   public Map<Holograms6.Type2, Vector3i> method17() {
      return this.field8;
   }

   @Generated
   public int method19() {
      return this.field13;
   }

   @Generated
   public int method21() {
      return this.field14;
   }

   private class Data {
      private final ThreadModuleDump45 field1;
      private final Holograms6.Type2 field2;
      private final boolean field3;

      private Data(ThreadModuleDump45 var1, Holograms6.Type2 var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public ThreadModuleDump45 method1() {
         return this.field1;
      }

      public Holograms6.Type2 method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }
   }
}
