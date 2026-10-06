package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import javax.vecmath.Matrix4f;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.common.emotes.Emote;
import mchorse.emoticons.morph.Morph;
import mchorse.emoticons.morph.MorphConfiguration;
import mchorse.emoticons.morph.MorphEntry;

public class EmoteAnimator {
   private final MorphConfiguration field1;
   private MorphEntry field2;
   private BedrockScheme field3;
   private int field4;
   private List<Morph> morphs;
   private final Map<Morph, MorphRenderer> field5;
   private final List<MorphRenderer> field6;
   private final List<MorphRenderer> field7;

   public EmoteAnimator(MorphConfiguration morphconfiguration1) {
      this.field1 = morphconfiguration1;
      this.field2 = null;
      this.field3 = null;
      this.field4 = Integer.MAX_VALUE;
      this.morphs = null;
      this.field5 = new HashMap<>();
      this.field6 = new ArrayList<>();
      this.field7 = new ArrayList<>();
   }

   private void reset() {
      this.morphs = null;
      this.field5.clear();
      this.field6.forEach(MorphRenderer::method9);
      this.field7.forEach(MorphRenderer::method9);
      this.field7.clear();
   }

   private void method1() {
      this.field5.values().forEach(arg1 -> {
         arg1.method9();
         this.field7.add(arg1);
      });
      this.field5.clear();
      this.field6.forEach(arg1 -> {
         arg1.method9();
         this.field7.add(arg1);
      });
      this.field6.clear();
      this.morphs = null;
   }

   private void method2(int number1, EntityLivingBridge bridgeextension2_52) {
      this.field7.removeIf(arg0 -> !arg0.method12());
      this.field6.removeIf(arg0 -> !arg0.method12());
      if (this.field3 == null) {
         this.field6.clear();
      } else if (this.field4 != Integer.MAX_VALUE) {
         HologramParticleEmitter glintcolorizer5iterator3 = new HologramParticleEmitter();
         glintcolorizer5iterator3.method3(this.field3);
         glintcolorizer5iterator3.running = true;
         glintcolorizer5iterator3.method5(bridgeextension2_52);
         glintcolorizer5iterator3.field8.set(bridgeextension2_52.bridge$getPosX(), bridgeextension2_52.bridge$getPosY(), bridgeextension2_52.bridge$getPosZ());
         this.field6.add(glintcolorizer5iterator3);
      }

      if (this.morphs == null) {
         this.field5.clear();
      } else {
         for (Morph morph4 : this.morphs) {
            Objects.requireNonNull(morph4);
            boolean flag5 = number1 >= morph4.getStart() && number1 < morph4.getLength() + morph4.getStart();
            if (!flag5) {
               MorphRenderer holograms86 = this.field5.get(morph4);
               if (holograms86 != null) {
                  holograms86.method9();
                  if (!holograms86.method12()) {
                     this.field5.remove(morph4);
                  }
               }
            } else {
               this.field5.computeIfAbsent(morph4, arg2x -> Objects.requireNonNull(MorphRenderer.method12(morph4, bridgeextension2_52)));
            }
         }
      }
   }

   public void method3(Consumer<MorphRenderer> consumer1) {
      this.field5.values().forEach(consumer1);
      this.field6.forEach(consumer1);
      this.field7.forEach(consumer1);
   }

   public boolean method4() {
      return !this.field5.isEmpty() || !this.field6.isEmpty() || !this.field7.isEmpty();
   }

   public void method5(EmoteController emotecontroller1, EntityLivingBridge bridgeextension2_52) {
      Emote emote3 = emotecontroller1.getEmote();
      if (!(Boolean)Ref.method4().method41().method6().method38().get()) {
         emote3 = null;
      }

      if (emote3 == null) {
         this.field4 = Integer.MAX_VALUE;
         this.field6.forEach(MorphRenderer::method9);
         this.method2(Integer.MAX_VALUE, bridgeextension2_52);
         if (this.field6.isEmpty() && this.field5.isEmpty() && this.field7.isEmpty()) {
            this.morphs = null;
            this.field2 = null;
            this.field3 = null;
         }
      } else {
         int number4 = emote3.looping && emote3.duration > 0 ? emotecontroller1.emoteTimer % emote3.duration : emotecontroller1.emoteTimer;
         int number5 = emote3.looping && emote3.duration > 0 && this.field4 != Integer.MAX_VALUE ? this.field4 % emote3.duration : this.field4;
         if (emotecontroller1.emoteTimer < this.field4) {
            this.field2 = null;
            this.field3 = null;
            this.reset();
         } else if (number4 < number5) {
            this.field2 = null;
            this.field3 = null;
            this.method1();
         }

         this.field4 = emotecontroller1.emoteTimer;
         MorphEntry morphentry6 = (MorphEntry)this.field1.morphs().get(emote3.morph);
         BedrockScheme glintcolorizer3_27 = (BedrockScheme)Ref.method4().method70().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(emote3.particleEffect);
         if (morphentry6 != this.field2 || glintcolorizer3_27 != this.field3) {
            this.field2 = morphentry6;
            this.field3 = glintcolorizer3_27;
            if (this.field2 == null) {
               this.morphs = Collections.emptyList();
            } else {
               this.morphs = Collections.unmodifiableList(this.field2.getMorphs());
            }
         }

         this.method2(number4, bridgeextension2_52);
      }
   }

   public void method6(EntityPlayerBridge bridgeextension2221, Matrix4f matrix4f2, IBoneRenderer holograms23, float value4) {
      this.method3(arg4x -> {
         Matrix4f matrix4f5 = new Matrix4f(matrix4f2);
         Ref.method4().method70().method9(matrix4f5, bridgeextension2221, holograms23, arg4x, value4);
      });
   }
}
