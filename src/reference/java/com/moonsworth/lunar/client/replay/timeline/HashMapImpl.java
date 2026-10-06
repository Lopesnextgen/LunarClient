package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class HashMapImpl extends HashMap<UUID, Entry<Range<Integer>, RewindIterator<?>>> {
   public HashMapImpl() {
   }
}
