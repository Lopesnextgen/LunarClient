package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateShardBridge;
import lombok.Generated;

public class RunnableRenderStateShard implements RenderStateShardBridge {
   private final Runnable field1;
   private final Runnable field2;

   public void bridge$setupState() {
      this.field1.run();
   }

   public void bridge$clearState() {
      this.field2.run();
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof RunnableRenderStateShard bridge19task2)) {
         return false;
      } else if (!bridge19task2.canEqual(this)) {
         return false;
      } else {
         Runnable runnable3 = this.field1;
         Runnable runnable4 = bridge19task2.field1;
         if (runnable3 == null ? runnable4 == null : runnable3.equals(runnable4)) {
            Runnable runnable5 = this.field2;
            Runnable runnable6 = bridge19task2.field2;
            return runnable5 == null ? runnable6 == null : runnable5.equals(runnable6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof RunnableRenderStateShard;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      Runnable runnable3 = this.field1;
      number2 = number2 * 59 + (runnable3 == null ? 43 : runnable3.hashCode());
      Runnable runnable4 = this.field2;
      return number2 * 59 + (runnable4 == null ? 43 : runnable4.hashCode());
   }

   @Generated
   public RunnableRenderStateShard(Runnable runnable1, Runnable runnable2) {
      this.field1 = runnable1;
      this.field2 = runnable2;
   }
}
