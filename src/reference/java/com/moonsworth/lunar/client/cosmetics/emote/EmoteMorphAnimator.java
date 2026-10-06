package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import java.util.function.Consumer;
import javax.vecmath.Matrix4f;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.morph.MorphConfiguration;

public class EmoteMorphAnimator {
   private final MorphTracker field1;
   private final EmoteAnimator field2;

   public EmoteMorphAnimator(MorphConfiguration morphconfiguration1) {
      this.field1 = new MorphTracker(morphconfiguration1);
      this.field2 = new EmoteAnimator(morphconfiguration1);
   }

   public void method1(Consumer<MorphRenderer> consumer1) {
      this.field1.method1(consumer1);
      this.field2.method3(consumer1);
   }

   public boolean method2() {
      return this.field1.method2() || this.field2.method4();
   }

   public void method3(EmoteController emotecontroller1, EntityLivingBridge bridgeextension2_52) {
      this.field1.update(bridgeextension2_52);
      this.field2.method5(emotecontroller1, bridgeextension2_52);
   }

   public void method4(EntityPlayerBridge entity, Matrix4f matrix4f2, IBoneRenderer holograms23, float value) {
      this.field1.method3(entity, matrix4f2, holograms23, value);
      this.field2.method6(entity, matrix4f2, holograms23, value);
   }
}
