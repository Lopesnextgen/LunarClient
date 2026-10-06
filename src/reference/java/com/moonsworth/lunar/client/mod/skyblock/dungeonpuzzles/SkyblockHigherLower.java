package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.entity.EntitySpawnEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class SkyblockHigherLower extends AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final Pattern field9 = Pattern.compile("^\\[Lv15] (?<mobType>[^ ] )?Blaze ([0-9,]+)/([0-9,]+)");
   private final TreeSet<SkyblockHigherLower.Data> field10 = new TreeSet<>();
   private long field11;

   public SkyblockHigherLower(SkyblockDungeonPuzzles var1, ToggleOption var2) {
      super(true);
      this.method8(Framework.field16, Framework4.method4(false, var1));
      this.method8(Framework.field6, ModEnabledState.method7(var2));
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::method13);
      this.handle(EventEntityRemoval.class, this::method3);
      this.handle(Rewindhandlers$Data10.class, var1x -> this.method13());
      this.handle(EntitySpawnEvent.class, this::method5);
      this.handle(HudRenderLegacyEvent.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, this::method7);
   }

   @Override
   public String getId() {
      return "SKYBLOCK_HIGHER_LOWER";
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

   private void method3(EventEntityRemoval var1) {
      BridgeExtension var2 = var1.method1();
      boolean var3 = this.field10.removeIf(var1x -> var1x.method3().equals(var2));
      if (this.field10.isEmpty() && var3) {
         Holograms2_5 var4 = this.field8.method5().orElse(null);
         if (var4 == null) {
            return;
         }

         Holograms4Iterator var5 = var4.method29().method28();
         if (var5 == null) {
            return;
         }

         HologramsType8 var6 = var5.method25();
         if (var6 != HologramsType8.BLAZE_UPPER && var6 != HologramsType8.BLAZE_LOWER) {
            return;
         }

         var5.method10(HologramsType2.CLEARED);
         Bridge5Extension_5 var7 = ThreadModuleDump63.method7();
         if (var7 != null && ThreadModuleDump63.method3().bridge$getSystemTime() - this.field11 > 300L) {
            Fishing_3.method1("/pc Blaze Finished!");
         }
      }
   }

   private void method13() {
      this.field10.clear();
   }

   private void method5(EntitySpawnEvent var1) {
      BridgeExtension var2 = var1.field1;
      if (var2 instanceof ArmorStandBridge) {
         Component var3 = var2.bridge$getCustomName();
         if (var3 != null) {
            Matcher var4 = this.field9.matcher(AdventureTextBridge.getTextContent(var3));
            if (var4.find()) {
               String var5 = var4.group(2).replace(",", "");
               int var6 = Integer.parseInt(var5);
               Itemcounter6Extension var7 = ThreadModuleDump63.method8();
               if (var7 == null) {
                  return;
               }

               SkyblockHigherLower.Data var8 = new SkyblockHigherLower.Data(var6, var2);
               this.field10.add(var8);
            }
         }
      }
   }

   private void method6(HudRenderLegacyEvent var1) {
      SkyblockDungeonPuzzles var2 = ((Framework4)this.method7(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null) {
         HologramsType8 var4 = var3.method25();
         if (var4 == HologramsType8.BLAZE_UPPER || var4 == HologramsType8.BLAZE_LOWER) {
            Iterator var5 = var4 == HologramsType8.BLAZE_UPPER ? this.field10.descendingIterator() : this.field10.iterator();
            if (var5.hasNext()) {
               BridgeExtension var6 = ((SkyblockHigherLower.Data)var5.next()).field2;
               AxisAlignedBBBridge var7 = AxisAlignedBBBridge.method2(
                  var6.bridge$getPosX() - 0.5,
                  var6.bridge$getPosY() - 2.25,
                  var6.bridge$getPosZ() - 0.5,
                  var6.bridge$getPosX() + 0.5,
                  var6.bridge$getPosY() + 0.05F,
                  var6.bridge$getPosZ() + 0.5
               );
               var6 = var5.hasNext() ? ((SkyblockHigherLower.Data)var5.next()).field2 : null;
               AxisAlignedBBBridge var8 = var6 != null
                  ? AxisAlignedBBBridge.method2(
                     var6.bridge$getPosX() - 0.5,
                     var6.bridge$getPosY() - 2.25,
                     var6.bridge$getPosZ() - 0.5,
                     var6.bridge$getPosX() + 0.5,
                     var6.bridge$getPosY() + 0.05F,
                     var6.bridge$getPosZ() + 0.5
                  )
                  : null;
               AbstractRenderContext var9 = var1.method3();
               Bridge2_43 var10 = ThreadModuleDump63.method13();
               var9.push();
               var9.translate(-var10.bridge$renderPosX(), -var10.bridge$renderPosY(), -var10.bridge$renderPosZ());
               Bridge2_32 var11 = var9.method10(LunarRenderTypes.field15);
               var11.method1();
               Click.drawBoxFilled(
                  var11,
                  var7.bridge$getMinX(),
                  var7.bridge$getMinY(),
                  var7.bridge$getMinZ(),
                  var7.bridge$getMaxX(),
                  var7.bridge$getMaxY(),
                  var7.bridge$getMaxZ(),
                  570490624
               );
               if (var8 != null) {
                  Click.drawBoxFilled(
                     var11,
                     var8.bridge$getMinX(),
                     var8.bridge$getMinY(),
                     var8.bridge$getMinZ(),
                     var8.bridge$getMaxX(),
                     var8.bridge$getMaxY(),
                     var8.bridge$getMaxZ(),
                     587202368
                  );
               }

               var11.method17(BufferBuildMode.BATCHED);
               Bridge_28 var12 = var9.method11(var2.method44().get());
               Click.drawBoxWires(
                  var12,
                  var7.bridge$getMinX(),
                  var7.bridge$getMinY(),
                  var7.bridge$getMinZ(),
                  var7.bridge$getMaxX(),
                  var7.bridge$getMaxY(),
                  var7.bridge$getMaxZ(),
                  -16711936
               );
               if (var8 != null) {
                  Click.drawBoxWires(
                     var12,
                     var8.bridge$getMinX(),
                     var8.bridge$getMinY(),
                     var8.bridge$getMinZ(),
                     var8.bridge$getMaxX(),
                     var8.bridge$getMaxY(),
                     var8.bridge$getMaxZ(),
                     -65536
                  );
                  var12.method3(
                     (var7.bridge$getMinX() + var7.bridge$getMaxX()) / 2.0,
                     (var7.bridge$getMinY() + var7.bridge$getMaxY()) / 2.0,
                     (var7.bridge$getMinZ() + var7.bridge$getMaxZ()) / 2.0,
                     (var8.bridge$getMinX() + var8.bridge$getMaxX()) / 2.0,
                     (var8.bridge$getMinY() + var8.bridge$getMaxY()) / 2.0,
                     (var8.bridge$getMinZ() + var8.bridge$getMaxZ()) / 2.0
                  );
               }

               var12.end();
               var9.pop();
            }
         }
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      if (var2.startsWith("PUZZLE FAIL!") && var2.contains("killed a Blaze in the wrong order!")) {
         this.field11 = ThreadModuleDump63.method3().bridge$getSystemTime();
      }
   }

   private static class Data implements Comparable<SkyblockHigherLower.Data> {
      private final int field1;
      private final BridgeExtension field2;

      public int method1(@NotNull SkyblockHigherLower.Data var1) {
         return this.field1 - var1.field1;
      }

      @Generated
      public int method2() {
         return this.field1;
      }

      @Generated
      public BridgeExtension method3() {
         return this.field2;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof SkyblockHigherLower.Data var2)) {
            return false;
         } else {
            if (!var2.canEqual(this)) {
               return false;
            }

            if (this.method2() != var2.method2()) {
               return false;
            }

            BridgeExtension var3 = this.method3();
            BridgeExtension var4 = var2.method3();
            return var3 == null ? var4 == null : var3.equals(var4);
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof SkyblockHigherLower.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + this.method2();
         BridgeExtension var3 = this.method3();
         return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      }

      @Generated
      public Data(int var1, BridgeExtension var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
