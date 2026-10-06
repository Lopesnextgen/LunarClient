package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   MINECRAFT_VERSION("minecraft_version", "Minecraft Version", true),
   FPS("fps", "FPS", true),
   POSITION("position", "Position"),
   CHUNK_POSITION("chunk_position", "Chunk Position"),
   BIOME("biome", "Biome"),
   ENTITIES("entities", "Entity Count", true),
   CHUNKS("chunks", "Chunk Count", true),
   PARTICLES("particles", "Particle Count", true),
   DAY("day", "Day"),
   LIGHT_LEVEL("light_level", "Light Level"),
   SOUNDS("sounds", "Sound Count"),
   BANDWIDTH("bandwidth", "Bandwidth", true),
   SERVER_INFO("server_info", "Server Info", true),
   POST_EFFECT("post_effect", "Post Effect"),
   TARGET_BLOCK("target_block", "Targeted Block"),
   TARGET_FLUID("target_fluid", "Targeted Fluid"),
   TARGET_ENTITY("target_entity", "Targeted Entity"),
   RENDER_DISTANCE("render_distance", "Render Distance", true),
   GPU_UTILIZATION("gpu_utilization", "GPU Utilization", true),
   MEMORY_USAGE("memory_usage", "Memory Usage", true),
   HARDWARE_INFO("hardware_info", "System Specs", true),
   LUNAR_DEBUG("lunar_debug", "Lunar Debug", true),
   PIE_CHART("pie_chart", "Profiler Pie Chart"),
   PING_CHART("ping_chart", "Ping Chart", true),
   RAM_CHART("ram_chart", "RAM Chart", true),
   FPS_CHART("fps_chart", "FPS Chart", true),
   GPU_CHART("gpu_chart", "GPU Chart", true),
   BANDWIDTH_CHART("bandwidth_chart", "Bandwidth Chart", true);

   private final String id;
   private final String display;
   private final boolean allowedInReducedInfo;

   Gui2Extension(String var3, String var4) {
      this(var3, var4, false);
   }

   public String id() {
      return this.id;
   }

   public static Gui2Extension getFromId(String var0) {
      return (Gui2Extension)F3Display.extensionById.get(var0);
   }

   public static List<String> ids() {
      return Arrays.stream(values()).map(Gui2Extension::id).toList();
   }

   public boolean isAllowed(boolean var1, boolean var2) {
      if (!var1) {
         return true;
      } else {
         return this == PIE_CHART && var2 ? true : this.allowedInReducedInfo;
      }
   }

   @Generated
   Gui2Extension(String var3, String var4, boolean var5) {
      this.id = var3;
      this.display = var4;
      this.allowedInReducedInfo = var5;
   }

   @Generated
   public String getDisplay() {
      return this.display;
   }
}
