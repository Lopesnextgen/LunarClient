package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import java.util.UUID;
import org.apache.commons.lang3.Range;

public class ThumbnailRequest {
   private final GameplaySegment field1;
   private final Range<Integer> field2;
   private final UUID field3;
   private final int field4;

   public ThumbnailRequest(GameplaySegment gameplaySegment, Range<Integer> range2, UUID uuid3, int value) {
      this.field1 = gameplaySegment;
      this.field2 = range2;
      this.field3 = uuid3;
      this.field4 = value;
   }

   public GameplaySegment method1() {
      return this.field1;
   }

   public Range<Integer> method2() {
      return this.field2;
   }

   public UUID method3() {
      return this.field3;
   }

   public int method4() {
      return this.field4;
   }
}
