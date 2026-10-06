package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Tiertagger2Iterator extends Tiertagger2_2 {
   private static final Tiertagger2$Data field2;
   private static final int field3 = 99999;

   @NotNull
   @Override
   public Tiertagger2.Tiertagger2$Data method1() {
      return field2;
   }

   @NotNull
   @Override
   public List<Tiertagger_2> method3() {
      return Tiertagger6Impl_2.method3().method4();
   }

   @Override
   protected Tiertagger4 method2(UUID var1) {
      if (var1 == null) {
         return null;
      }

      Optional var2 = (Optional)this.method10(this.method1().method2() + var1, false).get();
      if (var2.isEmpty()) {
         throw new Exception("Error while fetching");
      }

      JsonElement var3 = (JsonElement)var2.get();
      if (!var3.isJsonNull() && var3.isJsonObject()) {
         JsonObject var4 = var3.getAsJsonObject();
         if (var4.isEmpty()) {
            return null;
         }

         int var5 = ThreadModuleDump9.findInt(var4, "globalPosition").orElse(-1);
         return new Tiertagger4(
            Optional.empty(), var5 > 0 && var5 != 99999 ? OptionalInt.of(var5) : OptionalInt.empty(), OptionalInt.empty(), this.method4(var4)
         );
      } else {
         return null;
      }
   }

   private Map<Tiertagger_2, Tiertagger3_2> method4(JsonObject var1) {
      JsonObject var2 = ThreadModuleDump9.findJsonObject(var1, "data").orElse(null);
      if (var2 == null) {
         return Collections.emptyMap();
      }

      HashMap var3 = new HashMap();

      for (String var5 : var2.keySet()) {
         Optional var6 = this.method9(var5);
         if (var6.isEmpty()) {
            Slayer.method5("Invalid pvphq ladder %s", var5);
         } else if (!var2.get(var5).isJsonObject()) {
            Slayer.method5("Malformed pvphq ladder object");
         } else {
            JsonObject var7 = var2.getAsJsonObject(var5);
            String var8 = ThreadModuleDump9.findString(var7, "grantedTier").orElse(null);
            if (var8 == null) {
               Slayer.method5("Malformed pvphq ladder");
            } else if (!this.method5(var7)) {
               Tiertagger5 var9 = this.method6(var8);
               if (var9 == null) {
                  Slayer.method5("Found unsupported pvphq tier (%s), skipping", var8);
               } else {
                  Tiertagger3_2 var10 = new Tiertagger3_2((Tiertagger_2)var6.get(), var9, Optional.empty(), Optional.empty());
                  var3.put((Tiertagger_2)var6.get(), var10);
               }
            }
         }
      }

      return var3;
   }

   private boolean method5(JsonObject var1) {
      OptionalInt var2 = ThreadModuleDump9.findInt(var1, "placementGames");
      OptionalInt var3 = ThreadModuleDump9.findInt(var1, "placementTarget");
      return !var2.isEmpty() && !var3.isEmpty() ? var2.getAsInt() < var3.getAsInt() : false;
   }

   @Nullable
   private Tiertagger5 method6(String var1) {
      String var2 = var1.toUpperCase(Locale.ROOT);
      if (var2.length() == 3 && var2.charAt(1) == 'T') {
         byte var3 = switch (var2.charAt(0)) {
            case 'H' -> 0;
            case 'L' -> 1;
            case 'M' -> 2;
            default -> -1;
         };
         int var4 = var2.charAt(2) - '0';
         if (var3 != -1 && var4 >= 1 && var4 <= 5) {
            return new Tiertagger5(var4, var3);
         }

         Slayer.method5("Invalid PvpHQ tier (2): " + var1);
         return null;
      } else {
         Slayer.method5("Invalid PvpHQ tier (1): " + var1);
         return null;
      }
   }

   static {
      ArrayList var0 = new ArrayList();

      for (int var1 = 1; var1 <= 5; var1++) {
         var0.add(new Tiertagger5(var1, 0));
         var0.add(new Tiertagger5(var1, 2));
         var0.add(new Tiertagger5(var1, 1));
      }

      field2 = new Tiertagger2$Data(ServiceEndpoints.method4() + "/tier-tagger/pvphq/", var0, false, false);
   }
}
