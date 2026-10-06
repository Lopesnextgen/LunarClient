package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.MissResult.BlockPositionHitResult;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.replay.timeline.AngleUtils;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.render.CameraUpdateHandler;
import com.moonsworth.lunar.client.replay.render.CameraFollowMode;
import com.moonsworth.lunar.client.replay.render.CameraMode;
import com.moonsworth.lunar.client.render.particle.AngleMath;
import com.moonsworth.lunar.client.event.input.EventMouseMove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindFrame;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.joml.Vector3d;

public class RewindhandlersImpl extends RewindCameraController {
   private static final double field2 = 0.1;
   private static final double field3 = 256.0;
   private final Vector3d field4 = new Vector3d();
   private double distance;
   private boolean field5;
   protected EntityLivingBridge field6 = null;
   private float field7 = 0.0F;
   private float field8 = 0.0F;
   private float field9 = 0.0F;
   private double field10 = 0.0;
   private double field11 = 0.0;
   private double field12 = 0.0;

   public RewindhandlersImpl(CameraUpdateHandler rewindhandlers3updater1) {
      super(rewindhandlers3updater1);
   }

   @Override
   public void method1(float value1) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null && (this.field6 == null || this.field6.bridge$getWorld() != itemcounter6extension2)) {
         this.field6 = Bridge.method8().method78(itemcounter6extension2);
      }

      if (Ref.method3().bridge$getRenderViewEntity() != this.field6) {
         this.apply();
         Ref.method3().bridge$setRenderViewEntity(this.field6);
      }

      Ref.method3().bridge$getGameSettings().bridge$setThirdPersonView(0);
   }

   @Override
   public void method3(EventMouseMove highlightimpl141) {
      if (Bridge.method20().method1(2)) {
         if (!this.field5) {
            float value2 = Ref.method3().bridge$getTimer().method1();
            Optional optional3 = ((MissResult)Ray.method9(Raycaster.field3)
                  .method8(this.field6, 128.0, value2)
                  .method14((arg0, arg1x) -> !arg1x.bridge$isAir())
                  .method18()
                  .method8(Ref.method8()))
               .IIRRICRIRHCOIOCIIOHOORIIHCIOHC();
            if (optional3.isPresent()) {
               BlockPositionHitResult data4 = (BlockPositionHitResult)optional3.get();
               this.field4.set(data4.IHCORIOIOOHRCOOIOHIHHCOCIHRRHC().method6().sub(0.0, this.field6.bridge$getEyeHeight(), 0.0));
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
         this.method8(this.field6, highlightimpl141.method2(), highlightimpl141.method1());
      } else {
         RewindHandlers rewindhandlers13 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method6();
         if (rewindhandlers13.method26()) {
            this.field6.bridge$turn(highlightimpl141.method1(), highlightimpl141.method2());
            ArrayList list14 = new ArrayList((Collection)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get());
            list14.set(3, AngleMath.wrapDegrees(this.field6.bridge$getRotationYaw()));
            list14.set(4, AngleMath.wrapDegrees(this.field6.bridge$getRotationPitch()));
            if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() == CameraMode.FOLLOW) {
               BridgeExtension bridgeextension15 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
               if (bridgeextension15 != null) {
                  double value5 = Ref.method3().bridge$getTimer().method1();
                  double value7 = bridgeextension15.bridge$getPreviousRotationYaw() + (bridgeextension15.bridge$getRotationYaw() - bridgeextension15.bridge$getPreviousRotationYaw()) * value5;
                  double value9 = bridgeextension15 instanceof EntityLivingBridge bridgeextension2_511
                     ? bridgeextension2_511.bridge$getPrevRotationYawHead() + (bridgeextension2_511.bridge$getRotationYawHead() - bridgeextension2_511.bridge$getPrevRotationYawHead()) * value5
                     : value7;
                  double value16 = bridgeextension15.bridge$getPreviousRotationPitch() + (bridgeextension15.bridge$getRotationPitch() - bridgeextension15.bridge$getPreviousRotationPitch()) * value5;
                  if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POS_ROT_HEAD) {
                     list14.set(3, (Double)list14.get(3) - value9);
                     list14.set(4, (Double)list14.get(4) - value16);
                  } else if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POS_ROT_BODY) {
                     list14.set(3, (Double)list14.get(3) - value7);
                  }
               }
            }

            this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list14);
         }
      }
   }

   @Override
   public void method2(EventMouseScroll highlightimpl41) {
      if (this.field5) {
         boolean flag2 = highlightimpl41.method1() < 0.0;
         if (!flag2 && this.distance > 0.1 || flag2 && this.distance < 256.0) {
            double value3 = Math.min(this.distance / 16.0, 8.0);
            this.distance += flag2 ? value3 : value3 * -1.0;
            if (this.distance < 0.1) {
               this.distance = 0.1;
            } else if (this.distance > 256.0) {
               this.distance = 256.0;
            }
         }
      } else {
         float value5 = (float)Math.min(Math.max(-1.0, highlightimpl41.method1()), 1.0);
         LoadingStageImpl fogloader26 = Ref.method4().method90();
         float value4 = (Float)fogloader26.method21().get();
         value4 += value5 * value4 * 0.1F;
         value4 = Math.max(0.01F, Math.min(10.0F, value4));
         fogloader26.method21().method1(value4);
      }
   }

   @Override
   public void method4(EventRewindFrame highlightimpl111) {
      if (this.field6 != null) {
         BridgeExtension bridgeextension2 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
         double value3 = Ref.method3().bridge$getTimer().method1();
         if (bridgeextension2 != null && bridgeextension2 != Ref.method7() && value3 < this.field12 && bridgeextension2 instanceof EntityLivingBridge bridgeextension2_55) {
            this.field11 = this.field10;
            this.field10 = AngleUtils.method2(bridgeextension2_55.bridge$getRotationYawHead());
            bridgeextension2_55.bridge$setPrevRotationYawHead((float)this.field11);
            bridgeextension2_55.bridge$setRotationYawHead((float)this.field10);
            bridgeextension2_55.bridge$setPrevRotationYawHead((float)AngleUtils.method1(this.field11, this.field10));
         }

         this.field12 = value3;
         float value11 = 0.5F;
         GameOptionsBridge mixinhelper2_86 = Ref.method3().bridge$getGameSettings();
         if (mixinhelper2_86.bridge$keyBindForward().bridge$isKeyDown()) {
            this.field7++;
         }

         if (mixinhelper2_86.bridge$keyBindBack().bridge$isKeyDown()) {
            this.field7--;
         }

         if (mixinhelper2_86.bridge$keyBindLeft().bridge$isKeyDown()) {
            this.field8++;
         }

         if (mixinhelper2_86.bridge$keyBindRight().bridge$isKeyDown()) {
            this.field8--;
         }

         if (mixinhelper2_86.bridge$keyBindSprint().bridge$isKeyDown()) {
            value11 = 1.0F;
         }

         if (mixinhelper2_86.bridge$keyBindJump().bridge$isKeyDown()) {
            this.field9 += 0.5F;
         }

         if (mixinhelper2_86.bridge$keyBindSneak().bridge$isKeyDown()) {
            this.field9 -= 0.5F;
            value11 *= 0.5F;
         }

         this.field7 = Math.min(1.0F, Math.max(-1.0F, this.field7));
         this.field8 = Math.min(1.0F, Math.max(-1.0F, this.field8));
         this.field9 = Math.min(0.5F, Math.max(-0.5F, this.field9));
         float value7 = highlightimpl111.method1();
         RewindHandlers rewindhandlers8 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method6();
         if (rewindhandlers8.method62() && rewindhandlers8.method26()) {
            LoadingStageImpl fogloader29 = Ref.method4().method90();
            float value10 = (Float)fogloader29.method21().get();
            this.method5(this.field6, this.field7, this.field8, this.field9, value11 * value10, value7);
         }

         this.apply();
         float value12 = 1.0F - 0.5F * value7;
         this.field7 *= value12;
         this.field8 *= value12;
         this.field9 *= value12;
      }
   }

   private void apply() {
      double value1 = Ref.method3().bridge$getTimer().method1();
      LocalPlayerContext nameplate33 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method7();
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (bridge5extension_54 != null) {
         bridge5extension_54.bridge$setRotationYaw(nameplate33.method2((float)value1));
         bridge5extension_54.bridge$setRotationPitch(nameplate33.method3((float)value1));
         bridge5extension_54.bridge$setPreviousRotationYaw(bridge5extension_54.bridge$getRotationYaw());
         bridge5extension_54.bridge$setPreviousRotationPitch(bridge5extension_54.bridge$getRotationPitch());
      }

      if (Ref.MC_VERSION >= 7) {
         CameraBridge bridge2_195 = Ref.method3().bridge$getGameRenderer().bridge$getCamera();
         bridge2_195.bridge$setEyeHeight(1.62F);
      }

      List list41 = (List)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get();
      Double value6 = (Double)list41.get(0);
      Double value7 = (Double)list41.get(1);
      Double value8 = (Double)list41.get(2);
      Double value9 = (Double)list41.get(3);
      Double value10 = (Double)list41.get(4);
      if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() == CameraMode.FOLLOW) {
         BridgeExtension bridgeextension11 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
         if (bridgeextension11 != null) {
            double value12 = bridgeextension11.method3() + (bridgeextension11.bridge$getPosX() - bridgeextension11.method3()) * value1;
            double value14 = bridgeextension11.method4() + (bridgeextension11.bridge$getPosY() - bridgeextension11.method4()) * value1;
            double value16 = bridgeextension11.method5() + (bridgeextension11.bridge$getPosZ() - bridgeextension11.method5()) * value1;
            double value18 = bridgeextension11.bridge$getPreviousRotationYaw() + (bridgeextension11.bridge$getRotationYaw() - bridgeextension11.bridge$getPreviousRotationYaw()) * value1;
            double value20 = bridgeextension11 instanceof EntityLivingBridge bridgeextension2_522
               ? bridgeextension2_522.bridge$getPrevRotationYawHead() + (bridgeextension2_522.bridge$getRotationYawHead() - bridgeextension2_522.bridge$getPrevRotationYawHead()) * value1
               : value18;
            double value44 = bridgeextension11.bridge$getPreviousRotationPitch() + (bridgeextension11.bridge$getRotationPitch() - bridgeextension11.bridge$getPreviousRotationPitch()) * value1;
            if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POSITION) {
               value6 = value12 + value6;
               value7 = value14 + value7;
               value8 = value16 + value8;
            } else {
               boolean flag24 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POS_ROT_HEAD;
               double value25 = Math.toRadians(flag24 ? value20 : value18);
               double value27 = Math.toRadians(flag24 ? value44 : 0.0);
               double value29 = value6;
               double value31 = value7 * Math.cos(value27) - value8 * Math.sin(value27);
               double value33 = value7 * Math.sin(value27) + value8 * Math.cos(value27);
               double value35 = value29 * Math.cos(value25) - value33 * Math.sin(value25);
               double value37 = value31;
               double value39 = value29 * Math.sin(value25) + value33 * Math.cos(value25);
               value6 = value12 + value35;
               value7 = value14 + value37;
               value8 = value16 + value39;
               if (flag24) {
                  value9 = value20 + value9;
                  value10 = value44 + value10;
               } else {
                  value9 = value18 + value9;
               }
            }
         }
      }

      this.field6.bridge$setPosX(value6);
      this.field6.bridge$setPosY(value7);
      this.field6.bridge$setPosZ(value8);
      this.field6.bridge$setPreviousPosX(this.field6.bridge$getPosX());
      this.field6.bridge$setPreviousPosY(this.field6.bridge$getPosY());
      this.field6.bridge$setPreviousPosZ(this.field6.bridge$getPosZ());
      this.field6.bridge$setLastTickX(this.field6.bridge$getPosX());
      this.field6.bridge$setLastTickY(this.field6.bridge$getPosY());
      this.field6.bridge$setLastTickZ(this.field6.bridge$getPosZ());
      if (LunarConstants.field24.nextInt(10) == 0) {
         value9 = value9 + LunarConstants.field24.nextDouble() * 1.0E-5;
         value10 = value10 + LunarConstants.field24.nextDouble() * 1.0E-5;
      }

      this.field6.bridge$setRotationYawHead(value9.floatValue());
      this.field6.bridge$setRotationYaw(value9.floatValue());
      this.field6.bridge$setRotationPitch(value10.floatValue());
      this.field6.bridge$setPrevRotationYawHead(value9.floatValue());
      this.field6.bridge$setPreviousRotationYaw(value9.floatValue());
      this.field6.bridge$setPreviousRotationPitch(value10.floatValue());
      int number42 = (int)Math.floor(value6) >> 4;
      int number43 = (int)Math.floor(value8) >> 4;
      this.field6.bridge$setChunkX(number42);
      this.field6.bridge$setChunkY(value7.intValue());
      this.field6.bridge$setChunkZ(number43);
      this.field6.bridge$setPreviousRotationYaw((float)this.field6.bridge$getRotationYaw());
      this.field6.bridge$setPrevRotationYawHead((float)this.field6.bridge$getRotationYaw());
      this.field6.bridge$setRotationYawHead((float)this.field6.bridge$getPreviousRotationYaw());
   }

   protected void method5(BridgeExtension bridgeextension1, float value2, float value3, float value4, float value5, float value6) {
      ArrayList list7 = new ArrayList((Collection)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get());
      boolean flag8 = false;
      float value9 = value3 * value3 + value2 * value2;
      if (value9 >= 1.0E-4F) {
         value9 = (float)Math.max(Math.sqrt(value9), 1.0);
         if (value9 < 1.0F) {
            value9 = 1.0F;
         }

         value9 = value5 / value9;
         value3 *= value9;
         value2 *= value9;
         double value10 = bridgeextension1.bridge$getRotationYaw();
         if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() == CameraMode.FOLLOW) {
            BridgeExtension bridgeextension12 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
            if (bridgeextension12 != null) {
               double value13 = Ref.method3().bridge$getTimer().method1();
               double value15 = bridgeextension12.bridge$getPreviousRotationYaw() + (bridgeextension12.bridge$getRotationYaw() - bridgeextension12.bridge$getPreviousRotationYaw()) * value13;
               if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POS_ROT_HEAD) {
                  value10 -= bridgeextension12 instanceof EntityLivingBridge bridgeextension2_517
                     ? bridgeextension2_517.bridge$getPrevRotationYawHead() + (bridgeextension2_517.bridge$getRotationYawHead() - bridgeextension2_517.bridge$getPrevRotationYawHead()) * value13
                     : value15;
               } else if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POS_ROT_BODY) {
                  value10 -= value15;
               }
            }
         }

         float value22 = (float)Math.sin(value10 * (float) Math.PI / 180.0);
         float value23 = (float)Math.cos(value10 * (float) Math.PI / 180.0);
         list7.set(0, (Double)list7.get(0) + (double)(value3 * value23 - value2 * value22) * value6);
         list7.set(2, (Double)list7.get(2) + (double)(value2 * value23 + value3 * value22) * value6);
         flag8 = true;
      }

      if (value4 * value4 >= 1.0E-4F) {
         list7.set(1, (Double)list7.get(1) + value4 * value5 * value6);
         flag8 = true;
      }

      if (flag8) {
         this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list7);
      }
   }

   public void method6() {
      ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method6().method16();
      ReplayTimeline highlight_31 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method4();
      highlight_31.setPaused(true);
      BridgeExtension bridgeextension2 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
      LocalPlayerContext nameplate33 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method7();
      ArrayList list4 = new ArrayList((Collection)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get());
      if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() == CameraMode.FOLLOW) {
         list4.set(0, 0.0);
         list4.set(1, 0.0);
         list4.set(2, -2.0);
         this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list4);
         if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method24().get() == CameraFollowMode.POSITION) {
            if (bridgeextension2 == null) {
               this.method7(nameplate33.getYaw(), nameplate33.getPitch());
            } else {
               this.method7(bridgeextension2.bridge$getRotationYaw(), bridgeextension2.bridge$getRotationPitch());
            }
         } else {
            this.method7(0.0, 0.0);
         }
      } else if (bridgeextension2 == null) {
         list4.set(0, nameplate33.getX());
         list4.set(1, nameplate33.getY());
         list4.set(2, nameplate33.getZ());
         this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list4);
         this.method7(nameplate33.getYaw(), nameplate33.getPitch());
      } else {
         float value5 = 0.0F;
         if (Ref.MC_VERSION <= 0) {
            value5 = this.field6.bridge$getHeight() - this.field6.bridge$getEyeHeight();
         }

         list4.set(0, bridgeextension2.bridge$getPosX());
         list4.set(1, bridgeextension2.bridge$getPosY() - value5);
         list4.set(2, bridgeextension2.bridge$getPosZ());
         this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list4);
         this.method7(bridgeextension2.bridge$getRotationYaw(), bridgeextension2.bridge$getRotationPitch());
      }
   }

   protected void method7(double value1, double value3) {
      ArrayList list5 = new ArrayList((Collection)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get());
      list5.set(3, AngleMath.wrapDegrees(value1));
      list5.set(4, AngleMath.wrapDegrees(value3));
      this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list5);
   }

   private void method8(BridgeExtension bridgeextension1, float value2, float value3) {
      if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() != CameraMode.FOLLOW) {
         double value4 = AngleMath.wrapDegrees(bridgeextension1.bridge$getRotationPitch() + value2);
         double value6 = AngleMath.wrapDegrees(bridgeextension1.bridge$getRotationYaw() + value3);
         this.method7(value6, value4);
         Vector3d vector3d8 = this.method9(value4, value6);
         ArrayList list9 = new ArrayList((Collection)this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().get());
         list9.set(0, this.field4.x + vector3d8.x * this.distance);
         list9.set(1, this.field4.y + vector3d8.y * this.distance);
         list9.set(2, this.field4.z + vector3d8.z * this.distance);
         this.CCIORHCRHORCHHROHCIHCOIROHROHI.method16().OIRHOOIICOCIOOHICRRRICORIHHIHC(list9);
      }
   }

   private Vector3d method9(double value1, double value3) {
      double value5 = Math.toRadians(value1);
      double value7 = Math.toRadians(-value3);
      double value9 = Math.cos(value7);
      double value11 = Math.sin(value7);
      double value13 = Math.cos(value5) * -1.0;
      double value15 = Math.sin(value5);
      return new Vector3d(value11 * value13, value15, value9 * value13);
   }
}
