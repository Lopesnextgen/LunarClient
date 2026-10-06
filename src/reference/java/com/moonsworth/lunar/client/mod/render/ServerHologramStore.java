package com.moonsworth.lunar.client.mod.render;

import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHologramStore extends com.moonsworth.lunar.client.framework.ItemMapHandler<String, Serverholograms> {
   @Override
   protected Map<String, Serverholograms> method3() {
      return new ConcurrentHashMap<>();
   }
}
