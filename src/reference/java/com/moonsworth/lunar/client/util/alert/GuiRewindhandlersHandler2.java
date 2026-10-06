package com.moonsworth.lunar.client.util.alert;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.SlayerType;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private Map<String, GuiRewindhandlersHandler2.Data6> connectedTextures = null;

   private GuiRewindhandlersHandler2() {
      this.method7(AlertUpdateEvent.class, var1 -> var1.getFuture().thenRun(() -> ThreadModuleDump63.method3().bridge$submit(this::update)), Integer.MAX_VALUE);
   }

   public Optional<GuiRewindhandlersHandler2.Data6> getConnectedTexture(Bridge4_8 var1) {
      return this.connectedTextures == null ? Optional.empty() : Optional.ofNullable(this.connectedTextures.get(var1.bridge$getName()));
   }

   public Optional<GuiRewindhandlersHandler2.Data6> getConnectedTexture(String var1) {
      return this.connectedTextures == null ? Optional.empty() : Optional.ofNullable(this.connectedTextures.get(var1));
   }

   @Override
   protected void onEnable() {
      this.connectedTextures = new HashMap<>();
      this.update();
   }

   @Override
   protected void onDisable() {
      if (this.connectedTextures != null) {
         this.connectedTextures.clear();
         this.connectedTextures = null;
      }
   }

   @Override
   protected boolean isEnabled() {
      return Bridge.method5().isPresent();
   }

   private void update() {
      if (this.connectedTextures != null) {
         this.connectedTextures.clear();
         Optional var1 = Bridge.method5();
         if (ThreadModuleDump63.MC_VERSION < 1 || ((Slayer2)var1.get()).getConfig().hasConnectedTextures()) {
            HashMap var2 = new HashMap();
            Slayer[][] var3 = ((Slayer2)var1.get()).getConnectedTextures().getTileProperties();
            if (var3 != null) {
               for (Slayer[] var7 : var3) {
                  if (var7 != null) {
                     this.addConnectedProperties(var7, var2);
                  }
               }
            }

            Slayer[][] var9 = ((Slayer2)var1.get()).getConnectedTextures().getBlockProperties();
            if (var9 != null) {
               for (Slayer[] var8 : var9) {
                  if (var8 != null) {
                     this.addConnectedProperties(var8, var2);
                  }
               }
            }
         }
      }
   }

   private void addConnectedProperties(Slayer[] var1, Map<Slayer, GuiRewindhandlersHandler2.Data5> var2) {
      for (Slayer var6 : var1) {
         if (var6 != null) {
            GuiRewindhandlersHandler2.Data5 var7 = var2.computeIfAbsent(var6, var0 -> new GuiRewindhandlersHandler2.Data5());
            Set var8 = var7.field2;
            if (var8 == null) {
               var8 = var7.field2 = new HashSet<>();
               this.addTextureNames(var6.bridge$getMatchTiles(), var8, null);
               this.addTextureNames(var6.bridge$parseMatchBlocks(), var8, null);
            }

            for (String var10 : var8) {
               LinkedHashSet var11 = var7.field1;
               if (var11 == null) {
                  var11 = var7.field1 = new LinkedHashSet<>();
                  this.addTextureNames(
                     var6.bridge$getTiles(),
                     var11,
                     var0 -> {
                        var0 = var0.toLowerCase(Locale.ROOT);
                        return !var0.endsWith("<default>")
                           && !var0.endsWith("<skip>")
                           && var0.contains("ctm/")
                           && (var0.contains("optifine") || var0.contains("mcpatcher"));
                     }
                  );
               }

               if (!var11.isEmpty()) {
                  GuiRewindhandlersHandler2.Data6 var12 = this.connectedTextures.computeIfAbsent(var10, var0 -> new GuiRewindhandlersHandler2.Data6());
                  int var13 = 0;

                  for (String var15 : var11) {
                     if (!var12.field1.containsKey(var15)) {
                        var12.field1.put(var15, new GuiRewindhandlersHandler2.Data6.Data(var6.bridge$getMethod(), var13));
                     }

                     var13++;
                  }
               }
            }
         }
      }
   }

   private void addTextureNames(String[] var1, Set<String> var2, @Nullable Predicate<String> var3) {
      if (var1 != null) {
         for (String var7 : var1) {
            if (var7 != null && (var3 == null || var3.test(var7))) {
               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  ResourceLocationBridge var8 = ResourceLocationBridge.create(var7);
                  String var9 = var8.bridge$getPath();
                  if (var9.startsWith("textures/")) {
                     var9 = var9.substring("textures/".length());
                  }

                  if (!var9.contains("/")) {
                     var9 = (ThreadModuleDump63.MC_VERSION > 5 ? "block/" : "blocks/") + var9;
                  }

                  if (var9.endsWith(".png")) {
                     var9 = var9.substring(0, var9.length() - ".png".length());
                  }

                  var7 = var8.bridge$getDomain() + ":" + var9;
               }

               var2.add(var7);
            }
         }
      }
   }

   private static class Data5 {
      private LinkedHashSet<String> field1 = null;
      private Set<String> field2 = null;
   }

   public static class Data6 {
      private final Map<String, GuiRewindhandlersHandler2.Data6.Data> field1 = new HashMap<>();

      public SlayerType getConnectedTexture(String var1) {
         GuiRewindhandlersHandler2.Data6.Data var2 = this.field1.get(var1);
         return var2 == null ? SlayerType.NONE : var2.field1;
      }

      public boolean getConnectedTexture(String var1) {
         return this.field1.containsKey(var1);
      }

      public int addConnectedProperties(String var1) {
         GuiRewindhandlersHandler2.Data6.Data var2 = this.field1.get(var1);
         return var2 == null ? -1 : var2.field2;
      }

      public Set<String> addTextureNames() {
         return this.field1.keySet();
      }

      private class Data {
         private final SlayerType field1;
         private final int field2;

         private Data(SlayerType var1, int var2) {
            this.field1 = var1;
            this.field2 = var2;
         }

         public SlayerType getConnectedTexture() {
            return this.field1;
         }

         public int getConnectedTexture() {
            return this.field2;
         }
      }
   }
}
