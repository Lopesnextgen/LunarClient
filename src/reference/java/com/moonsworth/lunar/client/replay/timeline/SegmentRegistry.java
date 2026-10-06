package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.ImageSegment;
import com.moonsworth.lunar.client.replay.timeline.TextSegment;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.AudioSegment;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.EffectSegment;
import java.util.HashMap;
import java.util.Map;

public class SegmentRegistry {
   private static final Map<String, Class<?>> field1 = new HashMap<>();

   public SegmentRegistry() {
   }

   private static void method1(RewindIterator<?> iterator) {
      field1.put(iterator.type(), iterator.getClass());
   }

   public static Class<?> getType(String text) {
      return field1.get(text);
   }

   static {
      method1(new GameplaySegment(null, null));
      method1(new EffectSegment(null));
      method1(new TextSegment(null));
      method1(new ImageSegment(null));
      method1(new AudioSegment(null));
   }
}
