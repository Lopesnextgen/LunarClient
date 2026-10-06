package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge2_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeType2_7;
import com.moonsworth.lunar.bridge.SoundAttenuationType;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import org.joml.Vector2i;
import org.joml.Vector3d;

public class CreeperJumpscareFilter implements TurboEntityFilter<BridgeExtension> {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create(ThreadModuleDump63.MC_VERSION <= 1 ? "creeper.primed" : "entity.creeper.primed");
   private static final int field2 = 20;
   private int field3 = -1;

   public boolean method1(TurboEntityManager var1, Itemcounter6Extension var2, Bridge5Extension_5 var3, BridgeExtension var4) {
      if (this.field3 == -1) {
         this.method2(var2, var3, var4);
      } else {
         if (this.field3 == 0) {
            return true;
         }

         this.field3--;
         com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method2(var4, var3.bridge$getEyePosition(), false);
         if (var4 instanceof Bridge2_4 var5) {
            int var6 = var5.bridge$getSwell();
            var5.bridge$setOldSwell(var6);
            var5.bridge$setSwell(var6 + 1);
         }
      }

      return false;
   }

   private void method2(Itemcounter6Extension var1, Bridge5Extension_5 var2, BridgeExtension var3) {
      Vector3d var4 = var2.bridge$getLastReportedLookAngle().method6().mul(-1.0);
      Vec3Bridge var5 = var2.bridge$getEyePosition();
      Vector3d var6 = new Vector3d(var5.bridge$xCoord(), var5.bridge$yCoord() - 1.0, var5.bridge$zCoord());
      Vector2i var7 = new Vector2i((int)Math.floor(var6.x()), (int)Math.floor(var6.z()));
      ((MissResult)SExtension.builder(SImpl.BLOCK_AT)
            .method14((var0, var1x) -> var1x.bridge$isAir())
            .method16(
               (var1x, var2x, var3x, var4x) -> var3x.bridge$getX() == var7.x() && var3x.bridge$getZ() == var7.y() ? null : MissResult.method8(var3x)
            )
            .method5(var6, var4, 2.0)
            .method18()
            .method8(var1))
         .method5(
            var4x -> {
               Vector3iBridge var5x = var4x.method6();
               boolean var6x = com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method3(
                  var1, var5x.bridge$getX(), var5x.bridge$getY(), var5x.bridge$getZ(), var3
               );
               if (var6x) {
                  var3.bridge$setPosX(var5x.bridge$getX() + 0.5);
                  var3.bridge$setPosY(var5x.bridge$getY() - (ThreadModuleDump63.MC_VERSION <= 0 ? 0.2F : 0.0F));
                  var3.bridge$setPosZ(var5x.bridge$getZ() + 0.5);
                  com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method2(var3, var2.bridge$getEyePosition(), false);
                  if (var3 instanceof Bridge2_4 var7x) {
                     ThreadModuleDump63.method3()
                        .bridge$getSoundHandler()
                        .bridge$play(
                           field1,
                           BridgeType2_7.HOSTILE,
                           1.0F,
                           1.0F,
                           false,
                           0,
                           SoundAttenuationType.NONE,
                           var3.bridge$getPosX(),
                           var3.bridge$getPosY(),
                           var3.bridge$getPosZ()
                        );
                     ThreadModuleDump63.method4().method86().method2("event:lunar.jumpscare", 1);
                     this.field3 = var7x.bridge$getMaxSwell();
                     if (ThreadLocalRandom.current().nextFloat() > 0.9) {
                        var7x.bridge$setPowered(true);
                     }

                     var7x.bridge$setIgnited(true);
                  } else {
                     this.field3 = 20;
                  }
               }
            }
         );
   }
}
