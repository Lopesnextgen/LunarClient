package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.EntityHitResult.Data;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump64;
import com.moonsworth.lunar.client.util.ThreadModuleDump49.Type;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import com.moonsworth.lunar.client.util.chest.SExtension.Data2;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler3;
import lombok.Generated;
import org.joml.Vector3d;

public class RewindHandlers3Impl2 extends RewindHandlers3 {
   private BridgeExtension field9 = null;
   private boolean field10 = false;

   public RewindHandlers3Impl2(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
   }

   public int getHighlightColor() {
      int var1 = (int)(Math.abs(Math.sin(System.currentTimeMillis() % 4000L / 4000.0 * Math.PI * 2.0)) * 50.0);
      return ThreadModuleDump23.method10(Math.min(255, 26 + var1), Math.min(255, 189 + var1), Math.min(255, 226 + var1), 255);
   }

   public boolean method1(int var1) {
      if (this.field9 != null && var1 == 2) {
         this.field10 = !this.field10;
         Coordinates.refreshEntityContextMenu();
         return true;
      } else if (this.field10) {
         this.field10 = false;
         Coordinates.refreshEntityContextMenu();
         return true;
      } else {
         return false;
      }
   }

   public void method2(RewindHandlers3Impl8 var1, RewindHandlers3Handler var2, float var3) {
      RewindHandlers var4 = ((Nameplate4)this.field8.get()).method6();
      Highlight_3 var5 = var4.method40().method37();
      if (var5 == null || var4.method57().method25() || var4.method40().method31() || var4.method62() || var1.method26() == var4.method58()) {
         this.field9 = null;
         if (this.field10) {
            this.field10 = false;
            Coordinates.refreshEntityContextMenu();
         }
      } else if (!this.field10) {
         double var6 = var2.method15();
         double var8 = var2.method16();
         double var10 = var5.method13().getWidth();
         double var12 = var5.method13().getHeight();
         if (!var4.method44()) {
            var6 = Bridge.method20().getX();
            if (!Bridge.getMinecraftVersion().method19()) {
               var8 = var1.method27() - Bridge.method20().getY();
            } else {
               var8 = Bridge.method20().getY();
            }

            var10 = var4.method58();
            var12 = var4.method59();
         }

         if (!(var6 < 0.0) && !(var8 < 0.0) && !(var6 > var10) && !(var8 > var12) && ThreadModuleDump49.method4() != Type.HAND) {
            Itemcounter6Extension var14 = ThreadModuleDump63.method8();
            if (var14 != null) {
               Data2 var15 = SExtension.builder(SImpl.ENTITY).method1(new ChestHandler3(false, 0.0F, var3)).method15(var0 -> true);
               double var16 = 0.0;
               BridgeExtension var18 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
               if (ThreadModuleDump63.MC_VERSION == 0 && var18 != null && var18 != ThreadModuleDump63.method7()) {
                  var16 = 1.8 - var18.bridge$getEyeHeight();
               }

               Vector3d var19 = ThreadModuleDump64.method4(
                  var6 / var10 * ThreadModuleDump64.getScaledWidth(), var8 / var12 * ThreadModuleDump64.getScaledHeight()
               );
               Vector3d var20 = ThreadModuleDump64.method5();
               double var21 = 0.5;
               double var23 = 50.0;
               var15.method2(
                  Vec3Bridge.method2(var20.x() + var19.x() * var21, var20.y() + var19.y() * var21 + var16, var20.z() + var19.z() * var21),
                  Vec3Bridge.method2(var20.x() + var19.x() * var23, var20.y() + var19.y() * var23 + var16, var20.z() + var19.z() * var23)
               );
               SExtension var25 = var15.method18();
               if (var25.trace(var14) instanceof Data var27) {
                  this.field9 = var27.method5();
               } else {
                  this.field9 = null;
               }
            } else {
               this.field9 = null;
            }
         } else {
            this.field9 = null;
         }
      }
   }

   @Generated
   public BridgeExtension method14() {
      return this.field9;
   }

   @Generated
   public boolean method15() {
      return this.field10;
   }

   @Generated
   public void method5(boolean var1) {
      this.field10 = var1;
   }
}
