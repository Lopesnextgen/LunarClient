package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventMouseOverAttack extends Highlight {
   private final MovingObjectPositionHitResult field1;

   @Generated
   public MovingObjectPositionHitResult method1() {
      return this.field1;
   }

   @Generated
   public EventMouseOverAttack(MovingObjectPositionHitResult movingObjectPositionHitResult) {
      this.field1 = movingObjectPositionHitResult;
   }
}
