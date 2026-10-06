package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click16;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.event.mixin.highlight.EntitiesRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints.DungeonWaypoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Dungeonwaypoints2 {
   private final DungeonWaypoints field1;

   public void method1(EntitiesRenderEvent var1) {
      this.method3(var1.method1(), var1.getX(), var1.getY(), var1.getZ(), false);
   }

   public void method2(HudRenderLegacyEventAlt var1) {
      Bridge2_43 var2 = ThreadModuleDump63.method13();
      this.method3(var1.method3(), var2.bridge$renderPosX(), var2.bridge$renderPosY(), var2.bridge$renderPosZ(), true);
   }

   private void method3(AbstractRenderContext var1, double var2, double var4, double var6, boolean var8) {
      List var9 = this.method6();
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var10 = this.method7();
      if (var10 != null) {
         Vector3iBridge var11 = var10.method7();
         if (var9.removeIf(var1x -> var1x.method2().method1(var11))) {
            var10 = null;
         }
      }

      var9.removeIf(var1x -> var1x.method1().method8().method13() != var8);
      if (var10 != null && var10.method8().method13() != var8) {
         var10 = null;
      }

      if (!var9.isEmpty() || var10 != null) {
         var1.push();
         var1.translate(-var2, -var4, -var6);
         double[] var13 = null;
         if (var10 != null) {
            HologramsType_3 var12 = this.field1.method17() ? null : this.field1.method15().map(Holograms3::method28).orElse(null);
            var13 = Holograms3_4.method1(var10.method7(), var10.method8(), var12);
         }

         this.method4(var1, var9, var10, var13, var8);
         this.method5(var1, var9, var10, var13, var8);
         var1.pop();
      }
   }

   private void method4(AbstractRenderContext var1, List<Dungeonwaypoints2.Data> var2, @Nullable Dungeonwaypoints var3, double @Nullable [] var4, boolean var5) {
      Bridge2_32 var6 = var1.method10(var5 ? LunarRenderTypes.field52 : LunarRenderTypes.field17);
      var6.method1();
      ArrayList var7 = new ArrayList();

      for (Dungeonwaypoints2.Data var9 : var2) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 var10 = var9.method1().method8();
         if (var10.method9() != Gui2Extension2.WIREFRAME) {
            var7.add(Click16.Data2.method1(var9.method3(), var10.method11()));
         }
      }

      for (Click16.Data4 var12 : Click16.mergeFaces(var7)) {
         var12.method1(var6);
      }

      if (var3 != null && var3.method8().method9() != Gui2Extension2.WIREFRAME) {
         Click.drawBoxSolid(var6, var4[0], var4[1], var4[2], var4[3], var4[4], var4[5], ThreadModuleDump23.method18(var3.method8().method11(), 0.5F));
      }

      var6.method17(BufferBuildMode.BATCHED);
   }

   private void method5(AbstractRenderContext var1, List<Dungeonwaypoints2.Data> var2, @Nullable Dungeonwaypoints var3, double @Nullable [] var4, boolean var5) {
      Bridge_28 var6 = var1.method12(((Framework4)this.field1.method7(Framework.field16)).<Skyblock>method1().method19().get(), var5);
      ArrayList var7 = new ArrayList();

      for (Dungeonwaypoints2.Data var9 : var2) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 var10 = var9.method1().method8();
         if (var10.method9() != Gui2Extension2.FILL) {
            var7.add(Click16.Data2.method1(var9.method3(), var10.method12()));
         }
      }

      for (Click16.Data var12 : Click16.mergeEdges(var7)) {
         var6.method1(var12.method7());
         var6.method3(var12.method1(), var12.method2(), var12.method3(), var12.method4(), var12.method5(), var12.method6());
      }

      if (var3 != null && var3.method8().method9() != Gui2Extension2.FILL) {
         Click.drawBoxWires(var6, var4[0], var4[1], var4[2], var4[3], var4[4], var4[5], var3.method8().method12());
      }

      var6.end();
   }

   private List<Dungeonwaypoints2.Data> method6() {
      ArrayList var1 = new ArrayList();
      Dungeonwaypoints3 var2 = this.field1.method49();
      if (this.field1.method17()) {
         if (!this.field1.method50().method7() && !this.field1.method48().get()) {
            return var1;
         }

         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var11 : var2.method15(
            this.field1.method19()
         )) {
            var1.add(new Dungeonwaypoints2.Data(var11, var11.method7(), Holograms3_4.method1(var11.method7(), var11.method8(), null)));
         }

         return var1;
      } else {
         Holograms3 var3 = this.field1.method15().orElse(null);
         Holograms4Iterator var4 = this.field1.method16();
         if (var3 != null && var4 != null) {
            boolean var5 = this.field1.method50().method7() || this.field1.method37().get();
            HologramsType2 var6 = var4.method30().method2();

            for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var8 : var2.method2(
               var3.method26().getBlcID(), var3.method26().communityName()
            )) {
               if (var5 || var8.method8().method10().rendersAt(var6)) {
                  Vector3iBridge var9 = var3.method1(var8.method7());
                  var1.add(new Dungeonwaypoints2.Data(var8, var9, Holograms3_4.method1(var9, var8.method8(), var3.method28())));
               }
            }

            return var1;
         } else {
            return var1;
         }
      }
   }

   @Nullable
   private Dungeonwaypoints method7() {
      if (!this.field1.method50().method7()) {
         return null;
      } else if (!this.field1.method17() && this.field1.method15().isEmpty()) {
         return null;
      } else {
         Vector3iBridge var1 = this.field1.method50().method6();
         if (!this.field1.method50().method4(var1) && var1 != null) {
            Holograms4Iterator var2 = this.field1.method16();
            return this.field1.method17() || var2 != null && var2.contains(var1.bridge$getX(), var1.bridge$getZ())
               ? new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
                  var1, this.field1.method15(var1)
               )
               : null;
         } else {
            return null;
         }
      }
   }

   @Generated
   public Dungeonwaypoints2(DungeonWaypoints var1) {
      this.field1 = var1;
   }

   public class Data {
      private final com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints field1;
      private final Vector3iBridge field2;
      private final double[] field3;

      public Data(
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var1, Vector3iBridge var2, double[] var3
      ) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints method1() {
         return this.field1;
      }

      public Vector3iBridge method2() {
         return this.field2;
      }

      public double[] method3() {
         return this.field3;
      }
   }
}
