package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import mchorse.emoticons.morph.Morph;
import mchorse.emoticons.morph.MorphConfiguration;
import mchorse.emoticons.morph.MorphEntry;

public class MorphTimeline {
   private final int field1;
   private final List<Morph> morphs;
   private final Map<Morph, MorphRenderer> field2;
   private int time;

   public MorphTimeline(MorphConfiguration morphconfiguration1, String text, int value) {
      MorphEntry morphentry4 = (MorphEntry)morphconfiguration1.morphs().get(text);
      if (morphentry4 == null) {
         throw new IllegalArgumentException("No morph is available with name " + text + " in the morph configuration.");
      }

      this.field1 = value;
      this.morphs = Collections.unmodifiableList(morphentry4.getMorphs());
      this.field2 = new HashMap<>();
   }

   private void method1(int value, EntityLivingBridge bridgeextension2_52) {
      if (this.morphs == null) {
         this.field2.clear();
      } else {
         for (Morph morph4 : this.morphs) {
            Objects.requireNonNull(morph4);
            boolean flag5 = value >= morph4.getStart() && value < morph4.getLength() + morph4.getStart();
            if (!flag5) {
               MorphRenderer holograms86 = this.field2.get(morph4);
               if (holograms86 != null) {
                  holograms86.method9();
                  if (!holograms86.method12()) {
                     this.field2.remove(morph4);
                  }
               }
            } else {
               this.field2.computeIfAbsent(morph4, arg2x -> Objects.requireNonNull(MorphRenderer.method12(morph4, bridgeextension2_52)));
            }
         }
      }
   }

   public void method2(Consumer<MorphRenderer> consumer1) {
      this.field2.values().forEach(consumer1);
   }

   public boolean method3() {
      return !this.field2.isEmpty();
   }

   public void update(EntityLivingBridge bridgeextension2_51) {
      this.method1(this.time++, bridgeextension2_51);
      this.time = this.time % this.field1;
   }
}
