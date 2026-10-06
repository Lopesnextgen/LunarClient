package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
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

public abstract class TierApiProvider extends Tiertagger2_2 {
   protected static final List<TierPlacement> field2;
   private List<TierGameMode> field3 = null;

   public TierApiProvider() {
   }

   protected abstract boolean method2();

   protected abstract List<TierGameMode> method4();

   @Nullable
   protected abstract String method3(String text1);

   protected abstract int method4(String text1);

   @NotNull
   @Override
   public List<TierGameMode> method3() {
      if (!Ref.field1 && this.field3 == null && this.method2()) {
         this.field3 = new CopyOnWriteArrayList<>();
         this.method10(this.method1().method2() + "tierlists", true).thenAccept(arg1 -> {
            if (arg1.isEmpty()) {
               LunarLogger.method5("Unable to fetch %s gamemodes!", new Object[]{this.method8()});
            } else {
               JsonElement element2 = (JsonElement)arg1.get();
               if (!element2.isJsonNull() && element2.isJsonObject()) {
                  JsonObject json3 = element2.getAsJsonObject();
                  ArrayList list4 = new ArrayList();

                  for (String text6 : json3.keySet()) {
                     JsonObject json7 = json3.getAsJsonObject(text6);
                     String text8 = ThreadModuleDump9.findString(json7, "title").orElseGet(() -> StringUtils.capitalize(text6));
                     list4.add(new TierGameMode(text6, text8, Optional.ofNullable(this.method3(text6)), this.method4(text6)));
                  }

                  this.field3.clear();
                  this.field3.addAll(list4);
                  LunarLogger.method3("Got %s %s gamemodes", new Object[]{list4.size(), this.method8()});
               } else {
                  LunarLogger.method5("Unable to fetch %s gamemodes (invalid json)!", new Object[]{this.method8()});
               }
            }
         });
      }

      return this.field3 != null && !this.field3.isEmpty() ? this.field3 : this.method4();
   }

   @Override
   protected TierPlayerProfile method2(UUID uuid1) {
      if (uuid1 == null) {
         return null;
      }

      String text2 = this.method1().method2() + "profile/" + uuid1.toString().replace("-", "");
      Optional optional3 = (Optional)this.method10(text2, false).get();
      if (optional3.isEmpty()) {
         throw new Exception("Error while fetching");
      }

      JsonElement element4 = (JsonElement)optional3.get();
      if (!element4.isJsonNull() && element4.isJsonObject()) {
         JsonObject json5 = element4.getAsJsonObject();
         if (json5.isEmpty()) {
            return null;
         }

         TiertaggerType tiertaggertype6 = null;

         try {
            tiertaggertype6 = ThreadModuleDump9.findString(json5, "region").map(TiertaggerType::fromString).orElse(null);
         } catch (Exception exception8) {
         }

         return new TierPlayerProfile(
            Optional.ofNullable(tiertaggertype6), ThreadModuleDump9.findInt(json5, "overall"), ThreadModuleDump9.findInt(json5, "points"), this.method7(json5)
         );
      } else {
         return null;
      }
   }

   private Map<TierGameMode, TierRanking> method7(JsonObject json1) {
      JsonObject json2 = (JsonObject)ThreadModuleDump9.findJsonObject(json1, "rankings").orElse(null);
      if (json2 == null) {
         return Collections.emptyMap();
      }

      String text3 = this.method8();
      HashMap map4 = new HashMap();

      for (String text6 : json2.keySet()) {
         Optional optional7 = this.method9(text6);
         if (optional7.isEmpty()) {
            LunarLogger.method5("Invalid " + text3 + " gamemode %s", new Object[]{text6});
         } else if (!json2.get(text6).isJsonObject()) {
            LunarLogger.method5("Malformed ranking object " + text3, new Object[0]);
         } else {
            JsonObject json8 = json2.get(text6).getAsJsonObject();
            OptionalInt optionalint9 = ThreadModuleDump9.findInt(json8, "tier");
            OptionalInt optionalint10 = ThreadModuleDump9.findInt(json8, "pos");
            if (!optionalint9.isEmpty() && !optionalint10.isEmpty()) {
               OptionalInt optionalint11 = ThreadModuleDump9.findInt(json8, "peak_tier");
               OptionalInt optionalint12 = ThreadModuleDump9.findInt(json8, "peak_pos");
               TierPlacement tiertagger513 = null;
               if (optionalint11.isPresent() && optionalint12.isPresent()) {
                  tiertagger513 = new TierPlacement(optionalint11.getAsInt(), optionalint12.getAsInt());
               }

               TierRanking tiertagger3_214 = new TierRanking(
                  (TierGameMode)optional7.get(),
                  new TierPlacement(optionalint9.getAsInt(), optionalint10.getAsInt()),
                  Optional.ofNullable(tiertagger513),
                  ThreadModuleDump9.findBoolean(json8, "retired")
               );
               map4.put((TierGameMode)optional7.get(), tiertagger3_214);
            } else {
               LunarLogger.method5("Malformed ranking " + text3, new Object[0]);
            }
         }
      }

      return map4;
   }

   static {
      ArrayList list0 = new ArrayList();

      for (int index1 = 1; index1 <= 5; index1++) {
         list0.add(new TierPlacement(index1, 0));
         list0.add(new TierPlacement(index1, 1));
      }

      field2 = list0;
   }
}
