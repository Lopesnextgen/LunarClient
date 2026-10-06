package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.bridge.optifine.ConnectedTextureMethod;
import com.moonsworth.lunar.client.event.mixin.highlight.EventAlertUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ConnectedTexturesListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private Map<String, ConnectedTexturesListener.ConnectedTexture> field7 = null;

   private ConnectedTexturesListener() {
      this.method7(EventAlertUpdate.class, arg1 -> arg1.getFuture().thenRun(() -> Ref.method3().bridge$submit(this::update)), Integer.MAX_VALUE);
   }

   public Optional<ConnectedTexturesListener.ConnectedTexture> method1(Bridge4_8 bridge4_81) {
      return this.field7 == null ? Optional.empty() : Optional.ofNullable(this.field7.get(bridge4_81.bridge$getName()));
   }

   public Optional<ConnectedTexturesListener.ConnectedTexture> method2(String text1) {
      return this.field7 == null ? Optional.empty() : Optional.ofNullable(this.field7.get(text1));
   }

   protected void onEnable() {
      this.field7 = new HashMap<>();
      this.update();
   }

   protected void onDisable() {
      if (this.field7 != null) {
         this.field7.clear();
         this.field7 = null;
      }
   }

   protected boolean isEnabled() {
      return Bridge.method5().isPresent();
   }

   private void update() {
      if (this.field7 != null) {
         this.field7.clear();
         Optional optional1 = Bridge.method5();
         if (Ref.MC_VERSION < 1 || ((OptifineBridge)optional1.get()).getConfig().hasConnectedTextures()) {
            HashMap map2 = new HashMap();
            ConnectedProperties[][] items3 = ((OptifineBridge)optional1.get()).getConnectedTextures().getTileProperties();
            if (items3 != null) {
               for (ConnectedProperties[] items7 : items3) {
                  if (items7 != null) {
                     this.method3(items7, map2);
                  }
               }
            }

            ConnectedProperties[][] items9 = ((OptifineBridge)optional1.get()).getConnectedTextures().getBlockProperties();
            if (items9 != null) {
               for (ConnectedProperties[] items8 : items9) {
                  if (items8 != null) {
                     this.method3(items8, map2);
                  }
               }
            }
         }
      }
   }

   private void method3(ConnectedProperties[] items1, Map<ConnectedProperties, ConnectedTexturesListener.TileVariants> map2) {
      for (ConnectedProperties slayer6 : items1) {
         if (slayer6 != null) {
            ConnectedTexturesListener.TileVariants data57 = map2.computeIfAbsent(slayer6, arg0 -> new ConnectedTexturesListener.TileVariants());
            Set set8 = data57.field2;
            if (set8 == null) {
               set8 = data57.field2 = new HashSet<>();
               this.method4(slayer6.bridge$getMatchTiles(), set8, null);
               this.method4(slayer6.bridge$parseMatchBlocks(), set8, null);
            }

            for (String text10 : set8) {
               LinkedHashSet set11 = data57.field1;
               if (set11 == null) {
                  set11 = data57.field1 = new LinkedHashSet<>();
                  this.method4(
                     slayer6.bridge$getTiles(),
                     set11,
                     arg0 -> {
                        arg0 = arg0.toLowerCase(Locale.ROOT);
                        return !arg0.endsWith("<default>")
                           && !arg0.endsWith("<skip>")
                           && arg0.contains("ctm/")
                           && (arg0.contains("optifine") || arg0.contains("mcpatcher"));
                     }
                  );
               }

               if (!set11.isEmpty()) {
                  ConnectedTexturesListener.ConnectedTexture data612 = this.field7.computeIfAbsent(text10, arg0 -> new ConnectedTexturesListener.ConnectedTexture());
                  int index13 = 0;

                  for (String text15 : set11) {
                     if (!data612.field1.containsKey(text15)) {
                        data612.field1.put(text15, new ConnectedTexturesListener.ConnectedTexture.Data(slayer6.bridge$getMethod(), index13));
                     }

                     index13++;
                  }
               }
            }
         }
      }
   }

   private void method4(String[] items1, Set<String> set2, @Nullable Predicate<String> predicate3) {
      if (items1 != null) {
         for (String text7 : items1) {
            if (text7 != null && (predicate3 == null || predicate3.test(text7))) {
               if (Ref.MC_VERSION >= 1) {
                  ResourceLocationBridge horsestats148 = ResourceLocationBridge.create(text7);
                  String text9 = horsestats148.bridge$getPath();
                  if (text9.startsWith("textures/")) {
                     text9 = text9.substring("textures/".length());
                  }

                  if (!text9.contains("/")) {
                     text9 = (Ref.MC_VERSION > 5 ? "block/" : "blocks/") + text9;
                  }

                  if (text9.endsWith(".png")) {
                     text9 = text9.substring(0, text9.length() - ".png".length());
                  }

                  text7 = horsestats148.bridge$getDomain() + ":" + text9;
               }

               set2.add(text7);
            }
         }
      }
   }

   private static class TileVariants {
      private LinkedHashSet<String> field1 = null;
      private Set<String> field2 = null;

      private TileVariants() {
      }
   }

   public static class ConnectedTexture {
      private final Map<String, ConnectedTexturesListener.ConnectedTexture.Data> field1 = new HashMap<>();

      public ConnectedTexture() {
      }

      public ConnectedTextureMethod method1(String text1) {
         ConnectedTexturesListener.ConnectedTexture.Data data2 = this.field1.get(text1);
         return data2 == null ? ConnectedTextureMethod.NONE : data2.field1;
      }

      public boolean method2(String text1) {
         return this.field1.containsKey(text1);
      }

      public int method3(String text1) {
         ConnectedTexturesListener.ConnectedTexture.Data data2 = this.field1.get(text1);
         return data2 == null ? -1 : data2.field2;
      }

      public Set<String> method4() {
         return this.field1.keySet();
      }

      private class Data {
         private final ConnectedTextureMethod field1;
         private final int field2;

         private Data(ConnectedTextureMethod slayertype1, int number2) {
            this.field1 = slayertype1;
            this.field2 = number2;
         }

         public ConnectedTextureMethod method1() {
            return this.field1;
         }

         public int method2() {
            return this.field2;
         }
      }
   }
}
