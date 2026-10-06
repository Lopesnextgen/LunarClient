package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.ice.Ice;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdateNotify.Data;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class SkyblockBoulder extends AbstractFeature {
   private final Ice field8;
   private final List<Vector3i> field9 = new ArrayList<>();
   private final int field10 = 7;
   private final int field11 = 5;
   private int field12 = 0;

   public SkyblockBoulder(SkyblockDungeonPuzzles var1, ToggleOption var2) {
      super(true);
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field16, Framework4.method4(false, var1));
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field6, ModEnabledState.method7(var2));
      this.method3(this::method13);
      this.field8 = new Ice();
      this.handle(Rewindhandlers$Data10.class, var1x -> this.method13());
      this.handle(HudRenderLegacyEvent.class, this::method6);
      this.handle(Data.class, this::method5);
   }

   @Override
   public String getId() {
      return "SKYBLOCK_BOULDER";
   }

   @Override
   protected void method1(boolean var1) {
   }

   @Override
   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
         var2.method13();
      }
   }

   public void method3(Holograms4Iterator var1) {
      this.field12 = 0;
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         byte var3 = 24;
         byte var4 = 21;
         boolean[][] var5 = new boolean[7][5];

         for (int var6 = 0; var6 < 7; var6++) {
            for (int var7 = 0; var7 < 5; var7++) {
               Horsestats20Extension2 var8 = Bridge.method8().method4(var3 - var6 * 3, 65, var4 - var7 * 3);
               var5[var6][var7] = !var2.method4(var1.method23().get().method1(var8)).bridge$isAir();
            }
         }

         int var14 = Arrays.deepHashCode(var5);
         Map var15 = this.field8.method1();
         if (var15.containsKey(var14)) {
            Ice.Data var16 = (Ice.Data)var15.get(var14);
            this.field9.clear();
            SkyblockDungeonPuzzles var9 = ((Framework4)this.method7(Framework.field16)).method1();

            for (Vector2i var12 : var9.method37().get() ? var16.method2() : var16.method1()) {
               Vector3i var13 = new Vector3i(var3 - var12.x(), 65, var4 - var12.y());
               this.field9.add(var1.method23().get().method4(var13));
            }
         }
      }
   }

   private void method13() {
      this.field9.clear();
      this.field12 = 0;
   }

   private void method5(Data var1) {
      SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.BOULDER) {
         if (!this.field9.isEmpty() && this.field12 < this.field9.size()) {
            Vector3i var4 = var1.IIRHOIHIOOOCIIICROHOROCIOHHORC().bridge$toJoml();
            if (var4.equals(this.field9.get(this.field12))) {
               this.field12++;
            }
         }
      }
   }

   private void method6(HudRenderLegacyEvent var1) {
      SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.BOULDER) {
         if (!this.field9.isEmpty() && this.field12 < this.field9.size()) {
            AbstractRenderContext var4 = var1.method3();
            Bridge2_43 var5 = ThreadModuleDump63.method13();
            var4.push();
            var4.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
            Vector3i var6 = this.field9.get(this.field12);
            Click.drawBlockOutline(var4, var6, 587137024);
            Click.drawStringCentered(var1.method3(), this.field12 + 1 + "", var6.x() + 0.5F, var6.y() + 1.5F, var6.z() + 0.5F, -16711936, true);
            var4.pop();
         }
      }
   }
}
