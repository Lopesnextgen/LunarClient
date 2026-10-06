package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.MissResult.BlockPositionHitResult;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Updater;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.render.particle.AngleMath;
import com.moonsworth.lunar.client.event.input.EventMouseDelta;
import com.moonsworth.lunar.client.event.mixin.fishing.EventReplayFrame;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.joml.Vector3d;

public class RewindhandlersImpl extends Rewindhandlers {
   private static final double field2 = 0.1;
   private static final double field3 = 256.0;
   private final Vector3d field4 = new Vector3d();
   private double distance;
   private boolean field5;
   protected BridgeExtension2_5 field6 = null;
   private float field7 = 0.0F;
   private float field8 = 0.0F;
   private float field9 = 0.0F;
   private double field10 = 0.0;
   private double field11 = 0.0;
   private double field12 = 0.0;

   public RewindhandlersImpl(RewindHandlers3Updater var1) {
      super(var1);
   }

   @Override
   public void method1(float var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null && (this.field6 == null || this.field6.bridge$getWorld() != var2)) {
         this.field6 = Bridge.method8().method78(var2);
      }

      if (ThreadModuleDump63.method3().bridge$getRenderViewEntity() != this.field6) {
         this.apply();
         ThreadModuleDump63.method3().bridge$setRenderViewEntity(this.field6);
      }

      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setThirdPersonView(0);
   }

   @Override
   public void method3(EventMouseDelta var1) {
      if (Bridge.method20().method1(2)) {
         if (!this.field5) {
            float var2 = ThreadModuleDump63.method3().bridge$getTimer().method1();
            Optional var3 = ((MissResult)SExtension.builder(SImpl.BLOCK_AT)
                  .method8(this.field6, 128.0, var2)
                  .method14((var0, var1x) -> !var1x.bridge$isAir())
                  .method18()
                  .method8(ThreadModuleDump63.method8()))
               .method3();
            if (var3.isPresent()) {
               BlockPositionHitResult var4 = (BlockPositionHitResult)var3.get();
               this.field4.set(var4.IHCORIOIOOHRCOOIOHIHHCOCIHRRHC().method6().sub(0.0, this.field6.bridge$getEyeHeight(), 0.0));
               this.distance = this.field4.distance(new Vector3d(this.field6.bridge$getPosX(), this.field6.bridge$getPosY(), this.field6.bridge$getPosZ()));
               this.field5 = true;
            } else {
               this.field4.set(0.0);
               this.distance = 0.0;
               this.field5 = false;
            }
         }
      } else {
         this.field4.set(0.0);
         this.distance = 0.0;
         this.field5 = false;
      }

      if (this.field5) {
         this.method8(this.field6, var1.method2(), var1.method1());
      } else {
         RewindHandlers var13 = ((Nameplate4)this.field1.method13().get()).method6();
         if (var13.method26()) {
            this.field6.bridge$turn(var1.method1(), var1.method2());
            ArrayList var14 = new ArrayList((Collection)this.field1.method16().get());
            var14.set(3, AngleMath.wrapDegrees(this.field6.bridge$getRotationYaw()));
            var14.set(4, AngleMath.wrapDegrees(this.field6.bridge$getRotationPitch()));
            if (this.field1.method15() == Gui2Extension2.FOLLOW) {
               BridgeExtension var15 = this.field1.method17();
               if (var15 != null) {
                  double var5 = ThreadModuleDump63.method3().bridge$getTimer().method1();
                  double var7 = var15.bridge$getPreviousRotationYaw() + (var15.bridge$getRotationYaw() - var15.bridge$getPreviousRotationYaw()) * var5;
                  double var9 = var15 instanceof BridgeExtension2_5 var11
                     ? var11.bridge$getPrevRotationYawHead() + (var11.bridge$getRotationYawHead() - var11.bridge$getPrevRotationYawHead()) * var5
                     : var7;
                  double var16 = var15.bridge$getPreviousRotationPitch() + (var15.bridge$getRotationPitch() - var15.bridge$getPreviousRotationPitch()) * var5;
                  if (this.field1.method24().get() == Gui2Extension.POS_ROT_HEAD) {
                     var14.set(3, (Double)var14.get(3) - var9);
                     var14.set(4, (Double)var14.get(4) - var16);
                  } else if (this.field1.method24().get() == Gui2Extension.POS_ROT_BODY) {
                     var14.set(3, (Double)var14.get(3) - var7);
                  }
               }
            }

            this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var14);
         }
      }
   }

   @Override
   public void method2(EventMouseScrollLegacy var1) {
      if (this.field5) {
         boolean var2 = var1.method1() < 0.0;
         if (!var2 && this.distance > 0.1 || var2 && this.distance < 256.0) {
            double var3 = Math.min(this.distance / 16.0, 8.0);
            this.distance += var2 ? var3 : var3 * -1.0;
            if (this.distance < 0.1) {
               this.distance = 0.1;
            } else if (this.distance > 256.0) {
               this.distance = 256.0;
            }
         }
      } else {
         float var5 = (float)Math.min(Math.max(-1.0, var1.method1()), 1.0);
         LoadingStageImpl var6 = ThreadModuleDump63.method4().method90();
         float var4 = (Float)var6.method21().get();
         var4 += var5 * var4 * 0.1F;
         var4 = Math.max(0.01F, Math.min(10.0F, var4));
         var6.method21().method1(var4);
      }
   }

   @Override
   public void method4(EventReplayFrame var1) {
      if (this.field6 != null) {
         BridgeExtension var2 = this.field1.method17();
         double var3 = ThreadModuleDump63.method3().bridge$getTimer().method1();
         if (var2 != null && var2 != ThreadModuleDump63.method7() && var3 < this.field12 && var2 instanceof BridgeExtension2_5 var5) {
            this.field11 = this.field10;
            this.field10 = Gui2.method2(var5.bridge$getRotationYawHead());
            var5.bridge$setPrevRotationYawHead((float)this.field11);
            var5.bridge$setRotationYawHead((float)this.field10);
            var5.bridge$setPrevRotationYawHead((float)Gui2.method1(this.field11, this.field10));
         }

         this.field12 = var3;
         float var11 = 0.5F;
         GameOptionsBridge var6 = ThreadModuleDump63.method3().bridge$getGameSettings();
         if (var6.bridge$keyBindForward().bridge$isKeyDown()) {
            this.field7++;
         }

         if (var6.bridge$keyBindBack().bridge$isKeyDown()) {
            this.field7--;
         }

         if (var6.bridge$keyBindLeft().bridge$isKeyDown()) {
            this.field8++;
         }

         if (var6.bridge$keyBindRight().bridge$isKeyDown()) {
            this.field8--;
         }

         if (var6.bridge$keyBindSprint().bridge$isKeyDown()) {
            var11 = 1.0F;
         }

         if (var6.bridge$keyBindJump().bridge$isKeyDown()) {
            this.field9 += 0.5F;
         }

         if (var6.bridge$keyBindSneak().bridge$isKeyDown()) {
            this.field9 -= 0.5F;
            var11 *= 0.5F;
         }

         this.field7 = Math.min(1.0F, Math.max(-1.0F, this.field7));
         this.field8 = Math.min(1.0F, Math.max(-1.0F, this.field8));
         this.field9 = Math.min(0.5F, Math.max(-0.5F, this.field9));
         float var7 = var1.method1();
         RewindHandlers var8 = ((Nameplate4)this.field1.method13().get()).method6();
         if (var8.method62() && var8.method26()) {
            LoadingStageImpl var9 = ThreadModuleDump63.method4().method90();
            float var10 = (Float)var9.method21().get();
            this.method5(this.field6, this.field7, this.field8, this.field9, var11 * var10, var7);
         }

         this.apply();
         float var12 = 1.0F - 0.5F * var7;
         this.field7 *= var12;
         this.field8 *= var12;
         this.field9 *= var12;
      }
   }

   private void apply() {
      double var1 = ThreadModuleDump63.method3().bridge$getTimer().method1();
      Nameplate3 var3 = ((Nameplate4)this.field1.method13().get()).method7();
      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      if (var4 != null) {
         var4.bridge$setRotationYaw(var3.method2((float)var1));
         var4.bridge$setRotationPitch(var3.method3((float)var1));
         var4.bridge$setPreviousRotationYaw(var4.bridge$getRotationYaw());
         var4.bridge$setPreviousRotationPitch(var4.bridge$getRotationPitch());
      }

      if (ThreadModuleDump63.MC_VERSION >= 7) {
         Bridge2_19 var5 = ThreadModuleDump63.method3().bridge$getGameRenderer().bridge$getCamera();
         var5.bridge$setEyeHeight(1.62F);
      }

      List var41 = (List)this.field1.method16().get();
      Double var6 = (Double)var41.get(0);
      Double var7 = (Double)var41.get(1);
      Double var8 = (Double)var41.get(2);
      Double var9 = (Double)var41.get(3);
      Double var10 = (Double)var41.get(4);
      if (this.field1.method15() == Gui2Extension2.FOLLOW) {
         BridgeExtension var11 = this.field1.method17();
         if (var11 != null) {
            double var12 = var11.method3() + (var11.bridge$getPosX() - var11.method3()) * var1;
            double var14 = var11.method4() + (var11.bridge$getPosY() - var11.method4()) * var1;
            double var16 = var11.method5() + (var11.bridge$getPosZ() - var11.method5()) * var1;
            double var18 = var11.bridge$getPreviousRotationYaw() + (var11.bridge$getRotationYaw() - var11.bridge$getPreviousRotationYaw()) * var1;
            double var20 = var11 instanceof BridgeExtension2_5 var22
               ? var22.bridge$getPrevRotationYawHead() + (var22.bridge$getRotationYawHead() - var22.bridge$getPrevRotationYawHead()) * var1
               : var18;
            double var44 = var11.bridge$getPreviousRotationPitch() + (var11.bridge$getRotationPitch() - var11.bridge$getPreviousRotationPitch()) * var1;
            if (this.field1.method24().get() == Gui2Extension.POSITION) {
               var6 = var12 + var6;
               var7 = var14 + var7;
               var8 = var16 + var8;
            } else {
               boolean var24 = this.field1.method24().get() == Gui2Extension.POS_ROT_HEAD;
               double var25 = Math.toRadians(var24 ? var20 : var18);
               double var27 = Math.toRadians(var24 ? var44 : 0.0);
               double var29 = var6;
               double var31 = var7 * Math.cos(var27) - var8 * Math.sin(var27);
               double var33 = var7 * Math.sin(var27) + var8 * Math.cos(var27);
               double var35 = var29 * Math.cos(var25) - var33 * Math.sin(var25);
               double var37 = var31;
               double var39 = var29 * Math.sin(var25) + var33 * Math.cos(var25);
               var6 = var12 + var35;
               var7 = var14 + var37;
               var8 = var16 + var39;
               if (var24) {
                  var9 = var20 + var9;
                  var10 = var44 + var10;
               } else {
                  var9 = var18 + var9;
               }
            }
         }
      }

      this.field6.bridge$setPosX(var6);
      this.field6.bridge$setPosY(var7);
      this.field6.bridge$setPosZ(var8);
      this.field6.bridge$setPreviousPosX(this.field6.bridge$getPosX());
      this.field6.bridge$setPreviousPosY(this.field6.bridge$getPosY());
      this.field6.bridge$setPreviousPosZ(this.field6.bridge$getPosZ());
      this.field6.bridge$setLastTickX(this.field6.bridge$getPosX());
      this.field6.bridge$setLastTickY(this.field6.bridge$getPosY());
      this.field6.bridge$setLastTickZ(this.field6.bridge$getPosZ());
      if (ThreadModuleDump48.field24.nextInt(10) == 0) {
         var9 = var9 + ThreadModuleDump48.field24.nextDouble() * 1.0E-5;
         var10 = var10 + ThreadModuleDump48.field24.nextDouble() * 1.0E-5;
      }

      this.field6.bridge$setRotationYawHead(var9.floatValue());
      this.field6.bridge$setRotationYaw(var9.floatValue());
      this.field6.bridge$setRotationPitch(var10.floatValue());
      this.field6.bridge$setPrevRotationYawHead(var9.floatValue());
      this.field6.bridge$setPreviousRotationYaw(var9.floatValue());
      this.field6.bridge$setPreviousRotationPitch(var10.floatValue());
      int var42 = (int)Math.floor(var6) >> 4;
      int var43 = (int)Math.floor(var8) >> 4;
      this.field6.bridge$setChunkX(var42);
      this.field6.bridge$setChunkY(var7.intValue());
      this.field6.bridge$setChunkZ(var43);
      this.field6.bridge$setPreviousRotationYaw((float)this.field6.bridge$getRotationYaw());
      this.field6.bridge$setPrevRotationYawHead((float)this.field6.bridge$getRotationYaw());
      this.field6.bridge$setRotationYawHead((float)this.field6.bridge$getPreviousRotationYaw());
   }

   protected void method5(BridgeExtension var1, float var2, float var3, float var4, float var5, float var6) {
      ArrayList var7 = new ArrayList((Collection)this.field1.method16().get());
      boolean var8 = false;
      float var9 = var3 * var3 + var2 * var2;
      if (var9 >= 1.0E-4F) {
         var9 = (float)Math.max(Math.sqrt(var9), 1.0);
         if (var9 < 1.0F) {
            var9 = 1.0F;
         }

         var9 = var5 / var9;
         var3 *= var9;
         var2 *= var9;
         double var10 = var1.bridge$getRotationYaw();
         if (this.field1.method15() == Gui2Extension2.FOLLOW) {
            BridgeExtension var12 = this.field1.method17();
            if (var12 != null) {
               double var13 = ThreadModuleDump63.method3().bridge$getTimer().method1();
               double var15 = var12.bridge$getPreviousRotationYaw() + (var12.bridge$getRotationYaw() - var12.bridge$getPreviousRotationYaw()) * var13;
               if (this.field1.method24().get() == Gui2Extension.POS_ROT_HEAD) {
                  var10 -= var12 instanceof BridgeExtension2_5 var17
                     ? var17.bridge$getPrevRotationYawHead() + (var17.bridge$getRotationYawHead() - var17.bridge$getPrevRotationYawHead()) * var13
                     : var15;
               } else if (this.field1.method24().get() == Gui2Extension.POS_ROT_BODY) {
                  var10 -= var15;
               }
            }
         }

         float var22 = (float)Math.sin(var10 * (float) Math.PI / 180.0);
         float var23 = (float)Math.cos(var10 * (float) Math.PI / 180.0);
         var7.set(0, (Double)var7.get(0) + (double)(var3 * var23 - var2 * var22) * var6);
         var7.set(2, (Double)var7.get(2) + (double)(var2 * var23 + var3 * var22) * var6);
         var8 = true;
      }

      if (var4 * var4 >= 1.0E-4F) {
         var7.set(1, (Double)var7.get(1) + var4 * var5 * var6);
         var8 = true;
      }

      if (var8) {
         this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var7);
      }
   }

   public void method6() {
      ((Nameplate4)this.field1.method13().get()).method6().method16();
      Highlight_3 var1 = ((Nameplate4)this.field1.method13().get()).method4();
      var1.setPaused(true);
      BridgeExtension var2 = this.field1.method17();
      Nameplate3 var3 = ((Nameplate4)this.field1.method13().get()).method7();
      ArrayList var4 = new ArrayList((Collection)this.field1.method16().get());
      if (this.field1.method15() == Gui2Extension2.FOLLOW) {
         var4.set(0, 0.0);
         var4.set(1, 0.0);
         var4.set(2, -2.0);
         this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var4);
         if (this.field1.method24().get() == Gui2Extension.POSITION) {
            if (var2 == null) {
               this.method7(var3.getYaw(), var3.getPitch());
            } else {
               this.method7(var2.bridge$getRotationYaw(), var2.bridge$getRotationPitch());
            }
         } else {
            this.method7(0.0, 0.0);
         }
      } else if (var2 == null) {
         var4.set(0, var3.getX());
         var4.set(1, var3.getY());
         var4.set(2, var3.getZ());
         this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var4);
         this.method7(var3.getYaw(), var3.getPitch());
      } else {
         float var5 = 0.0F;
         if (ThreadModuleDump63.MC_VERSION <= 0) {
            var5 = this.field6.bridge$getHeight() - this.field6.bridge$getEyeHeight();
         }

         var4.set(0, var2.bridge$getPosX());
         var4.set(1, var2.bridge$getPosY() - var5);
         var4.set(2, var2.bridge$getPosZ());
         this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var4);
         this.method7(var2.bridge$getRotationYaw(), var2.bridge$getRotationPitch());
      }
   }

   protected void method7(double var1, double var3) {
      ArrayList var5 = new ArrayList((Collection)this.field1.method16().get());
      var5.set(3, AngleMath.wrapDegrees(var1));
      var5.set(4, AngleMath.wrapDegrees(var3));
      this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var5);
   }

   private void method8(BridgeExtension var1, float var2, float var3) {
      if (this.field1.method15() != Gui2Extension2.FOLLOW) {
         double var4 = AngleMath.wrapDegrees(var1.bridge$getRotationPitch() + var2);
         double var6 = AngleMath.wrapDegrees(var1.bridge$getRotationYaw() + var3);
         this.method7(var6, var4);
         Vector3d var8 = this.method9(var4, var6);
         ArrayList var9 = new ArrayList((Collection)this.field1.method16().get());
         var9.set(0, this.field4.x + var8.x * this.distance);
         var9.set(1, this.field4.y + var8.y * this.distance);
         var9.set(2, this.field4.z + var8.z * this.distance);
         this.field1.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(var9);
      }
   }

   private Vector3d method9(double var1, double var3) {
      double var5 = Math.toRadians(var1);
      double var7 = Math.toRadians(-var3);
      double var9 = Math.cos(var7);
      double var11 = Math.sin(var7);
      double var13 = Math.cos(var5) * -1.0;
      double var15 = Math.sin(var5);
      return new Vector3d(var11 * var13, var15, var9 * var13);
   }
}
