package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.EffectTrack;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.HashMapImpl;
import java.util.HashMap;
import java.util.Map;

public class TrackRegistry {
   private static final Map<String, Class<?>> field1 = new HashMap<>();

   public TrackRegistry() {
   }

   private static void method1(Track<?> gui_20) {
      field1.put(gui_20.type(), gui_20.getClass());
   }

   public static Class<?> getType(String text) {
      return field1.get(text);
   }

   static {
      method1(new EffectTrack(null, new HashMapImpl()));
   }
}
