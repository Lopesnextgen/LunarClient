package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.util.math.MathUtils;
import com.replaymod.replay.camera.ClassicCameraController;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClassicCameraController.class)
public class ClassicCameraControllerV1_8Mixin {
   @Shadow
   private double MAX_SPEED;
   @Final
   @Shadow
   private static double LOWER_SPEED;
   @Final
   @Shadow
   private static double UPPER_SPEED;
   @Shadow
   private double THRESHOLD;
   @Shadow
   private double DECAY;

   public ClassicCameraControllerV1_8Mixin() {
   }

   @Overwrite
   public void setCameraMaximumSpeed(double value) {
      this.MAX_SPEED = MathUtils.method2(value, LOWER_SPEED, UPPER_SPEED);
      this.THRESHOLD = this.MAX_SPEED / 20.0;
      this.DECAY = 5.0;
   }
}
