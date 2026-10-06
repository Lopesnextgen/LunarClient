package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import java.util.List;
import lombok.Generated;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class AnimationEventImpl extends AnimationEvent<EmoteModel> {
   private RenderContext field1;

   public AnimationEventImpl(EmoteModel iterator, float value, float value2, float value3, boolean flag, List<Object> list, RenderContext fov107) {
      super(iterator, value, value2, value3, flag, list);
      this.field1 = fov107;
   }

   @Generated
   public RenderContext method1() {
      return this.field1;
   }
}
