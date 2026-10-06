package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.client.util.collection.CollectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginChannelRegistry {
   public static final List<String> field1 = (List<String>)CollectionUtils.make(new ArrayList(), arg0 -> {
      arg0.add("lunar:apollo");
      arg0.add("apollo:json");
      arg0.add("lunarclient:pm");
      arg0.add("badlion:timers");
   });
   public static final Map<String, List<String>> field2 = (Map<String, List<String>>)CollectionUtils.make(
      new HashMap(), arg0 -> arg0.put("worldedit:cui", List.of("worldeditcui"))
   );

   public PluginChannelRegistry() {
   }
}
