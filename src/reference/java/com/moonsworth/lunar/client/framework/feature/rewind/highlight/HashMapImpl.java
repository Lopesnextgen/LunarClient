package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class HashMapImpl extends HashMap<UUID, Entry<Range<Integer>, RewindIterator<?>>> {
}
