package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Tiertagger2Iterator_3 extends Tiertagger2_2 {
   protected static final List<Tiertagger5> field2;
   private List<Tiertagger_2> field3 = null;

   protected abstract boolean method2();

   protected abstract List<Tiertagger_2> method4();

   @Nullable
   protected abstract String method3(String var1);

   protected abstract int method4(String var1);

   @NotNull
   @Override
   public List<Tiertagger_2> method3() {
      if (!ThreadModuleDump63.field1 && this.field3 == null && this.method2()) {
         this.field3 = new CopyOnWriteArrayList<>();
         this.method10(this.method1().method2() + "tierlists", true).thenAccept(var1 -> {
            if (var1.isEmpty()) {
               Slayer.method5("Unable to fetch %s gamemodes!", new Object[]{this.method8()});
            } else {
               JsonElement var2 = (JsonElement)var1.get();
               if (!var2.isJsonNull() && var2.isJsonObject()) {
                  JsonObject var3 = var2.getAsJsonObject();
                  ArrayList var4 = new ArrayList();

                  for (String var6 : var3.keySet()) {
                     JsonObject var7 = var3.getAsJsonObject(var6);
                     String var8 = ThreadModuleDump9.findString(var7, "title").orElseGet(() -> StringUtils.capitalize(var6));
                     var4.add(new Tiertagger_2(var6, var8, Optional.ofNullable(this.method3(var6)), this.method4(var6)));
                  }

                  this.field3.clear();
                  this.field3.addAll(var4);
                  Slayer.method3("Got %s %s gamemodes", new Object[]{var4.size(), this.method8()});
               } else {
                  Slayer.method5("Unable to fetch %s gamemodes (invalid json)!", new Object[]{this.method8()});
               }
            }
         });
      }

      return this.field3 != null && !this.field3.isEmpty() ? this.field3 : this.method4();
   }

   @Override
   protected Tiertagger4 method2(UUID var1) {
      if (var1 == null) {
         return null;
      }

      String var2 = this.method1().method2() + "profile/" + var1.toString().replace("-", "");
      Optional var3 = (Optional)this.method10(var2, false).get();
      if (var3.isEmpty()) {
         throw new Exception("Error while fetching");
      }

      JsonElement var4 = (JsonElement)var3.get();
      if (!var4.isJsonNull() && var4.isJsonObject()) {
         JsonObject var5 = var4.getAsJsonObject();
         if (var5.isEmpty()) {
            return null;
         }

         TiertaggerType var6 = null;

         try {
            var6 = ThreadModuleDump9.findString(var5, "region").map(TiertaggerType::fromString).orElse(null);
         } catch (Exception var8) {
         }

         return new Tiertagger4(
            Optional.ofNullable(var6), ThreadModuleDump9.findInt(var5, "overall"), ThreadModuleDump9.findInt(var5, "points"), this.method7(var5)
         );
      } else {
         return null;
      }
   }

   private Map<Tiertagger_2, Tiertagger3_2> method7(JsonObject var1) {
      JsonObject var2 = (JsonObject)ThreadModuleDump9.findJsonObject(var1, "rankings").orElse(null);
      if (var2 == null) {
         return Collections.emptyMap();
      }

      String var3 = this.method8();
      HashMap var4 = new HashMap();

      for (String var6 : var2.keySet()) {
         Optional var7 = this.method9(var6);
         if (var7.isEmpty()) {
            Slayer.method5("Invalid " + var3 + " gamemode %s", new Object[]{var6});
         } else if (!var2.get(var6).isJsonObject()) {
            Slayer.method5("Malformed ranking object " + var3, new Object[0]);
         } else {
            JsonObject var8 = var2.get(var6).getAsJsonObject();
            OptionalInt var9 = ThreadModuleDump9.findInt(var8, "tier");
            OptionalInt var10 = ThreadModuleDump9.findInt(var8, "pos");
            if (!var9.isEmpty() && !var10.isEmpty()) {
               OptionalInt var11 = ThreadModuleDump9.findInt(var8, "peak_tier");
               OptionalInt var12 = ThreadModuleDump9.findInt(var8, "peak_pos");
               Tiertagger5 var13 = null;
               if (var11.isPresent() && var12.isPresent()) {
                  var13 = new Tiertagger5(var11.getAsInt(), var12.getAsInt());
               }

               Tiertagger3_2 var14 = new Tiertagger3_2(
                  (Tiertagger_2)var7.get(),
                  new Tiertagger5(var9.getAsInt(), var10.getAsInt()),
                  Optional.ofNullable(var13),
                  ThreadModuleDump9.findBoolean(var8, "retired")
               );
               var4.put((Tiertagger_2)var7.get(), var14);
            } else {
               Slayer.method5("Malformed ranking " + var3, new Object[0]);
            }
         }
      }

      return var4;
   }

   static {
      ArrayList var0 = new ArrayList();

      for (int var1 = 1; var1 <= 5; var1++) {
         var0.add(new Tiertagger5(var1, 0));
         var0.add(new Tiertagger5(var1, 1));
      }

      field2 = var0;
   }
}
