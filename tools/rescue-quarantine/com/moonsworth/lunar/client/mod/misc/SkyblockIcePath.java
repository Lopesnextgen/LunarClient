package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension52;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Bridge_56;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl12;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl6_2;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightImpl2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.joml.Vector3i;

public class SkyblockIcePath extends Framework7Extension2 {
   private final Set<Vector3i> vertices = new HashSet<>();
   private final HashMap<Vector3i, SkyblockIcePath.Data> field8 = new HashMap<>();
   private final List<Vector3i> field9 = new ArrayList<>();
   private Vector3i field10;
   private BridgeExtension field11;

   public SkyblockIcePath(SkyblockDungeonPuzzles var1, LightingExtension443 var2) {
      super(true);
      this.method12(Framework.field16, Framework4.method4(false, var1));
      this.method12(Framework.field6, Framework3.method7(var2));
      this.method3(this::method13);
      this.handle(Data10.class, var1x -> this.method13());
      this.handle(HighlightImpl6_2.class, this::method6);
      this.handle(HighlightImpl2.class, var1x -> this.method14());
      this.handle(com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2.class, this::method16);
      this.handle(HighlightImpl12.class, this::method4);
   }

   public String getId() {
      return "SKYBLOCK_ICE_PATH";
   }

   protected void method1(boolean var1) {
   }

   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var2.method13();
      }
   }

   public void method3(Holograms4Iterator var1) {
      this.field11 = null;
      this.field10 = ((Holograms3)var1.method23().get()).method4(new Vector3i(14, 67, 28));
      this.method9(var1);
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         for (BridgeExtension var5 : var2.bridge$getEntities()) {
            if (this.method7(var5)) {
               Vector3i var6 = new Vector3i(
                  (int)Math.floor(this.field11.bridge$getPosX()),
                  (int)Math.floor(this.field11.bridge$getPosY()),
                  (int)Math.floor(this.field11.bridge$getPosZ())
               );
               SkyblockIcePath.Data var7 = new SkyblockIcePath.Data(var6);
               this.method12(var2, var7);
               this.method10(var6);
               break;
            }
         }
      }
   }

   private void method4(HighlightImpl12 var1) {
      if (var1.method1().equals(this.field11)) {
         this.field9.clear();
         this.field11 = null;
      }
   }

   private void method13() {
      this.vertices.clear();
      this.field9.clear();
      this.field10 = null;
      this.field11 = null;
   }

   private void method6(HighlightImpl6_2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.ICE_PATH) {
         Itemcounter6Extension var4 = ThreadModuleDump63.method8();
         if (var4 != null) {
            if (this.method7(var1.field1)) {
               Vector3i var5 = new Vector3i(
                  (int)Math.floor(this.field11.bridge$getPosX()),
                  (int)Math.floor(this.field11.bridge$getPosY()),
                  (int)Math.floor(this.field11.bridge$getPosZ())
               );
               SkyblockIcePath.Data var6 = new SkyblockIcePath.Data(var5);
               this.method12(var4, var6);
               this.method10(var5);
            }
         }
      }
   }

   private boolean method7(BridgeExtension var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 == null || var3.method25() != HologramsType8.ICE_PATH) {
         return false;
      }

      if (!var1.bridge$isRiding()) {
         return false;
      }

      if (var1 instanceof BridgeExtension52 var4) {
         if (var4.bridge$getItemStack().bridge$getItem() != Bridge.method28().method34()) {
            return false;
         }

         if (!var3.contains(var1.bridge$getPosX(), var1.bridge$getPosZ())) {
            return false;
         }

         this.field11 = var1;
         return true;
      } else {
         return false;
      }
   }

   private void method14() {
      SkyblockDungeonPuzzles var1 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var2 = var1.method47();
      if (var2 != null && var2.method25() == HologramsType8.ICE_PATH) {
         if (this.field11 != null) {
            if (this.field11.bridge$getMotionX() == 0.0 && this.field11.bridge$getMotionZ() == 0.0) {
               if (this.field11.bridge$lastTickZ() != this.field11.bridge$getPosZ() || this.field11.bridge$lastTickX() != this.field11.bridge$getPosX()) {
                  Vector3i var3 = new Vector3i(
                     (int)Math.floor(this.field11.bridge$getPosX()),
                     (int)Math.floor(this.field11.bridge$getPosY()),
                     (int)Math.floor(this.field11.bridge$getPosZ())
                  );
                  this.method10(var3);
               }
            }
         }
      }
   }

   private void method9(Holograms4Iterator var1) {
      this.vertices.clear();
      this.field8.clear();
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         Bridge_56 var3 = Bridge.method34();
         Holograms3 var4 = (Holograms3)var1.method23().get();

         for (int var5 = 6; var5 <= 24; var5++) {
            for (int var6 = 7; var6 <= 28; var6++) {
               Horsestats20 var7 = var4.method1(Bridge.method8().method4(var5, 67, var6));
               Bridge3_23 var8 = var2.method4(var7);
               if (var8 == var3.method40()) {
                  this.method13(var7.bridge$getX(), 67, var7.bridge$getZ());
               }
            }
         }

         for (Vector3i var10 : this.vertices) {
            SkyblockIcePath.Data var11 = new SkyblockIcePath.Data(var10);
            this.method12(var2, var11);
         }
      }
   }

   private void method10(Vector3i var1) {
      HashSet var2 = new HashSet();
      LinkedList var3 = new LinkedList();
      HashMap var4 = new HashMap();
      var3.add(var1);

      while (!var3.isEmpty()) {
         Vector3i var5 = (Vector3i)var3.poll();
         var2.add(var5);
         SkyblockIcePath.Data var6 = this.field8.get(var5);
         if (var6 != null) {
            for (Vector3i var8 : var6.field2) {
               if (!var2.contains(var8)) {
                  if (!var4.containsKey(var8)) {
                     var4.put(var8, var5);
                  }

                  if (var8.equals(this.field10)) {
                     this.method11(var8, var4, var1);
                     return;
                  }

                  var3.add(var8);
               }
            }
         }
      }
   }

   private void method11(Vector3i var1, HashMap<Vector3i, Vector3i> var2, Vector3i var3) {
      this.field9.clear();
      Vector3i var4 = var1;

      do {
         this.field9.add(var4);
         var4 = (Vector3i)var2.get(var4);
      } while (var4 != var3);

      this.field9.add(var4);
   }

   private void method12(Itemcounter6 var1, SkyblockIcePath.Data var2) {
      Vector3i var3 = var2.field1;

      for (Vector3i var5 : this.vertices) {
         if (this.method14(var1, var3, var5)) {
            MutableBoolean var6 = new MutableBoolean(false);
            var2.field2.removeIf(var4 -> {
               if (this.method15(var3, var5) == this.method15(var3, var4)) {
                  if (var3.distanceSquared(var4) > var3.distanceSquared(var5)) {
                     return true;
                  }

                  var6.setTrue();
               }

               return false;
            });
            if (var6.isFalse()) {
               var2.field2.add(var5);
            }
         }
      }

      this.field8.put(var3, var2);
   }

   private void method13(int var1, int var2, int var3) {
      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      if (var4 != null) {
         for (ThreadModuleDumpType var8 : ThreadModuleDumpType.values()) {
            Bridge3_23 var9 = var4.bridge$getBlockAt(var1 + var8.offsetX(), var2, var3 + var8.offsetY());
            if (var9.bridge$isAir()) {
               this.vertices.add(new Vector3i(var1 + var8.offsetX(), var2, var3 + var8.offsetY()));
            }
         }
      }
   }

   private boolean method14(Itemcounter6 var1, Vector3i var2, Vector3i var3) {
      boolean var4 = var2.x() == var3.x();
      boolean var5 = var2.z() == var3.z();
      if (var4 == var5) {
         return false;
      } else if (var4) {
         int var7 = var2.z() > var3.z() ? -1 : 1;
         return !var1.bridge$getBlockAt(var3.x(), var3.y(), var3.z() + var7).bridge$isAir()
            && var1.bridge$getBlockAt(var2.x(), var2.y(), var2.z() + var7).bridge$isAir();
      } else {
         int var6 = var2.x() > var3.x() ? -1 : 1;
         return !var1.bridge$getBlockAt(var3.x() + var6, var3.y(), var3.z()).bridge$isAir()
            && var1.bridge$getBlockAt(var2.x() + var6, var2.y(), var2.z()).bridge$isAir();
      }
   }

   private HorsestatsType_2 method15(Vector3i var1, Vector3i var2) {
      if (var1.x() == var2.x()) {
         return var1.z() > var2.z() ? HorsestatsType_2.WEST : HorsestatsType_2.EAST;
      } else {
         return var1.x() > var2.x() ? HorsestatsType_2.NORTH : HorsestatsType_2.SOUTH;
      }
   }

   private void method16(com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.ICE_PATH) {
         if (!this.field9.isEmpty()) {
            BridgeExtension_9 var4 = var1.method3();
            Bridge2_43 var5 = ThreadModuleDump63.method13();
            var4.push();
            var4.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
            Bridge_28 var6 = var4.method11((Float)var2.method42().get());
            var6.method1(-65536);

            for (int var7 = 1; var7 < this.field9.size(); var7++) {
               Vector3i var8 = this.field9.get(var7 - 1);
               Vector3i var9 = this.field9.get(var7);
               var6.method3(var8.x() + 0.5, var8.y() + 0.5, var8.z() + 0.5, var9.x() + 0.5, var9.y() + 0.5, var9.z() + 0.5);
            }

            var6.end();
            var4.pop();
         }
      }
   }

   private static class Data {
      private final Vector3i field1;
      private final Set<Vector3i> field2 = new HashSet<>();

      private Data(Vector3i var1) {
         this.field1 = var1;
      }
   }
}
