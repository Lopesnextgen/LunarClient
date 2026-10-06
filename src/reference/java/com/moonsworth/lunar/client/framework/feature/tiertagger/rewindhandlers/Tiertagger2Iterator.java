package com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger2$Data;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger2_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger3_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger4;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger5;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerType;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Tiertagger2Iterator extends Tiertagger2_2 {
   private static final Tiertagger4 field2 = new Tiertagger4(Optional.empty(), OptionalInt.empty(), OptionalInt.empty(), Collections.emptyMap());
   private final Tiertagger2$Data field3;
   private List<Tiertagger_2> field4 = null;

   public Tiertagger2Iterator() {
      List var1 = Tiertagger6Iterator.method7()
         .method9()
         .values()
         .stream()
         .map(var0 -> new Tiertagger5(var0.tier(), var0.method1()))
         .sorted(Comparator.comparingInt(Tiertagger5::method1))
         .collect(Collectors.toCollection(ArrayList::new));
      if (var1.isEmpty()) {
         for (int var2 = 1; var2 <= 5; var2++) {
            var1.add(new Tiertagger5(var2, 0));
            var1.add(new Tiertagger5(var2, 1));
         }
      }

      this.field3 = new Tiertagger2$Data(Tiertagger6Iterator.method7().method10(), var1, true, false);
   }

   @NotNull
   @Override
   public Tiertagger2.Tiertagger2$Data method1() {
      return this.field3;
   }

   @NotNull
   @Override
   public List<Tiertagger_2> method3() {
      Tiertagger6Iterator var1 = Tiertagger6Iterator.method7();
      if (!ThreadModuleDump63.field1 && this.field4 == null && var1.isLoaded()) {
         this.field4 = new CopyOnWriteArrayList<>();
         String var2 = ThreadModuleDump63.MC_VERSION > 1 ? var1.method12() : var1.method11();
         this.method10(this.field3.method2() + var2, true)
            .thenAccept(
               var2x -> {
                  if (var2x.isEmpty()) {
                     Slayer.method5("Unable to fetch tiertests gamemodes!");
                  } else {
                     JsonElement var3 = (JsonElement)var2x.get();
                     if (!var3.isJsonNull() && var3.isJsonObject()) {
                        JsonObject var4 = var3.getAsJsonObject();
                        if (!var4.has("data")) {
                           Slayer.method5("No tiertests gamemodes array!");
                        } else {
                           JsonArray var5 = var4.getAsJsonArray("data");
                           ArrayList var6 = new ArrayList();

                           for (JsonElement var8 : var5) {
                              if (var8.isJsonObject()) {
                                 JsonObject var9 = var8.getAsJsonObject();
                                 if (var9.has("name")) {
                                    String var10 = var9.get("name").getAsString();
                                    var6.add(
                                       new Tiertagger_2(
                                          var10,
                                          this.method5(var9, var10),
                                          Optional.ofNullable(this.method4(var9, var10, var1)),
                                          this.method3(var9, var10, var1)
                                       )
                                    );
                                 }
                              }
                           }

                           this.field4.clear();
                           this.field4.addAll(var6);
                           Slayer.method3("Got %s tiertests gamemodes", var6.size());
                        }
                     } else {
                        Slayer.method5("Unable to fetch tiertests gamemodes (invalid json)!");
                     }
                  }
               }
            );
      }

      return this.field4 != null && !this.field4.isEmpty() ? this.field4 : var1.method8();
   }

   private int method3(JsonObject var1, String var2, Tiertagger6Iterator var3) {
      if (var1.has("colorHex")) {
         String var4 = var1.get("colorHex").getAsString();

         try {
            return Color.decode(var4).getRGB();
         } catch (Exception var6) {
         }
      }

      return var3.method3(var2);
   }

   @Nullable
   private String method4(JsonObject var1, String var2, Tiertagger6Iterator var3) {
      String var4 = var3.method2(var2);
      if (var4 != null) {
         return var4;
      } else {
         return var1.has("unicode") ? var1.get("unicode").getAsString() : null;
      }
   }

   private String method5(JsonObject var1, String var2) {
      if (!var1.has("beautifiedName")) {
         return this.capitalize(var2);
      }

      String var3 = var1.get("beautifiedName").getAsString();
      return var3.trim().isEmpty() ? this.capitalize(var2) : var3;
   }

   private String capitalize(String var1) {
      return var1.isEmpty() ? "" : Character.toUpperCase(var1.charAt(0)) + var1.substring(1).toLowerCase(Locale.ROOT);
   }

   @Override
   protected Tiertagger4 method2(UUID var1) {
      if (var1 == null) {
         return null;
      }

      Tiertagger6Iterator var2 = Tiertagger6Iterator.method7();
      String var3 = String.format(this.field3.method2() + var2.method13(), var1);
      Optional var4 = (Optional)this.method10(var3, false).get();
      if (var4.isEmpty()) {
         throw new Exception("Error while fetching");
      }

      JsonElement var5 = (JsonElement)var4.get();
      if (!var5.isJsonNull() && var5.isJsonObject()) {
         JsonObject var6 = var5.getAsJsonObject();
         JsonArray var7 = ThreadModuleDump9.findJsonArray(var6, "data").orElse(null);
         if (var7 != null && !var7.isEmpty()) {
            HashMap var8 = new HashMap();
            TiertaggerType var9 = null;
            int var10 = -1;
            int var11 = -1;

            for (JsonElement var13 : var7) {
               JsonObject var14 = var13.getAsJsonObject();
               Tiertagger_2 var15 = this.method7(var14);
               if (var15 != null) {
                  if (var9 == null) {
                     var9 = this.method8(var14);
                  }

                  if (var10 == -1 || var11 == -1) {
                     String var16 = ThreadModuleDump63.MC_VERSION > 1 ? "rankModern" : "rankLegacy";
                     JsonObject var17 = ThreadModuleDump9.findJsonObject(var14, var16).orElse(null);
                     if (var17 != null) {
                        var10 = ThreadModuleDump9.findInt(var17, "points").orElse(-1);
                        var11 = ThreadModuleDump9.findInt(var17, "rank").orElse(-1);
                     }
                  }

                  Tiertagger6Iterator.Data var18 = var2.method1(var14.get("tier").getAsString().toUpperCase(Locale.ROOT));
                  if (var18 == null) {
                     Slayer.method5("[TierTests] Found unsupported tier (%s), skipping (!!)", var14.get("tier").getAsString());
                  } else {
                     Tiertagger3_2 var19 = new Tiertagger3_2(var15, new Tiertagger5(var18.tier(), var18.method1()), Optional.empty(), Optional.empty());
                     var8.put(var15, var19);
                  }
               }
            }

            return var8.isEmpty()
               ? field2
               : new Tiertagger4(
                  Optional.ofNullable(var9),
                  var10 == -1 ? OptionalInt.empty() : OptionalInt.of(var10),
                  var11 == -1 ? OptionalInt.empty() : OptionalInt.of(var11),
                  var8
               );
         } else {
            return null;
         }
      } else {
         Slayer.method5("Unable to fetch tiertests ranking for %s (%s)!", var1, var5.toString());
         return null;
      }
   }

   private Tiertagger_2 method7(JsonObject var1) {
      String var2 = ThreadModuleDump9.findJsonObject(var1, "gamemode").flatMap(var0 -> ThreadModuleDump9.findString(var0, "name")).orElse(null);
      if (var2 == null) {
         return null;
      }

      Tiertagger_2 var3 = (Tiertagger_2)this.method9(var2).orElse(null);
      if (var3 == null) {
         Slayer.method3("[TierTests] Found unsupported gamemode (%s), skipping", var2);
      }

      return var3;
   }

   private TiertaggerType method8(JsonObject var1) {
      String var2 = ThreadModuleDump9.findJsonObject(var1, "user").flatMap(var0 -> ThreadModuleDump9.findString(var0, "region")).orElse(null);
      if (var2 == null) {
         return null;
      }

      try {
         return TiertaggerType.fromString(var2);
      } catch (IllegalArgumentException var4) {
         Slayer.method5("Unable to parse tiertests region %s", var2);
         return null;
      }
   }
}
