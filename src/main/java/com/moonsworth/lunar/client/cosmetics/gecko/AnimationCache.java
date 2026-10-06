package com.moonsworth.lunar.client.cosmetics.gecko;

import java.util.Collection;
import java.util.HashMap;
import software.bernie.geckolib3.core.builder.Animation;

public class AnimationCache {
   private HashMap<String, Animation> field1 = new HashMap<>();

   public AnimationCache() {
   }

   public Animation method1(String text1) {
      return this.field1.get(text1);
   }

   public void method2(String text1, Animation animation2) {
      this.field1.put(text1, animation2);
   }

   public Collection<Animation> method3() {
      return this.field1.values();
   }
}
