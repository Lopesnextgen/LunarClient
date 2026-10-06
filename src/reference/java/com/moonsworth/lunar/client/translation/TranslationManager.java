package com.moonsworth.lunar.client.translation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.LanguageEntry;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.calculator.Calculator;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.calculator.CalculatorType;
import com.moonsworth.lunar.client.calculator.CalculatorType2;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.holograms.mixin.IchorHandlersLoadedEvent;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.Nullable;

public class TranslationManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, CalculatorType> implements EventRegistrar {
   private static final Pattern field2 = Pattern.compile("\\$([{]?([a-zA-Z0-9]*)[}]?)");
   private final GuiIterator field3 = new GuiIterator();
   private CalculatorType field4;
   private String field5;
   private JsonObject field6 = new JsonObject();
   private JsonObject field7 = new JsonObject();
   @Nullable
   private JsonObject field8 = null;
   private final Map<String, List<String>> field9 = new HashMap<>();
   private final List<String> field10 = new ArrayList<>();
   private JsonObject field11 = new JsonObject();

   public TranslationManager() {
      this.handle(IchorHandlersLoadedEvent.class, var1 -> {
         this.field10.addAll(var1.method1().keySet());
         this.method13();
      });
      this.method13();
      this.method18();
   }

   @Override
   protected Map<String, CalculatorType> method3() {
      return ImmutableMap.copyOf(Arrays.stream(CalculatorType.values()).collect(Collectors.toMap(CalculatorType::getFileName, Function.identity())));
   }

   public String method2(String var1, String var2, Object... var3) {
      return this.method4(this.method10(var1), var2, var3);
   }

   public String method3(Calculator2 var1, String var2, Object... var3) {
      return this.method2(var1.getLanguagePath(), var2, var3);
   }

   public String method4(List<String> var1, String var2, Object... var3) {
      Optional var4 = this.method11(var1, var2, this.field6).or(() -> this.method11(var1, var2, this.field7)).or(() -> this.method11(var1, var2, this.field11));
      if (!LunarBuildData.field4 && this.field8 != null) {
         var4 = var4.or(() -> this.method11(var1, var2, this.field8));
      }

      return var4.<String>map(var1x -> ThreadModuleDump56.method1(var1x, var3)).orElse(var2);
   }

   public boolean method5(String var1, String var2) {
      return this.method6(this.method10(var1), var2);
   }

   public boolean method6(List<String> var1, String var2) {
      return this.method11(var1, var2, this.field6)
         .or(() -> this.method11(var1, var2, this.field7))
         .or(() -> this.method11(var1, var2, this.field11))
         .isPresent();
   }

   public Component method7(TranslatableComponent var1) {
      String[] var2 = var1.key().split(Pattern.quote("."));
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < var2.length - 1; var4++) {
         String var5 = var2[var4];
         if (!var5.isEmpty()) {
            var3.add(var5);
         }
      }

      return (Component)(var3.isEmpty() ? var1 : this.method9(var3, var2[var2.length - 1], var1.args()));
   }

   public Component method8(String var1, String var2, List<Component> var3) {
      return this.method9(this.method10(var1), var2, var3);
   }

   public Component method9(List<String> var1, String var2, List<Component> var3) {
      Optional var4 = this.method11(var1, var2, this.field6).or(() -> this.method11(var1, var2, this.field7)).or(() -> this.method11(var1, var2, this.field11));
      if (!LunarBuildData.field4 && this.field8 != null) {
         var4 = var4.or(() -> this.method11(var1, var2, this.field8));
      }

      return var4.<Component>map(
            var1x -> (Component)(var1x.contains("$")
               ? Component.text(var1x).replaceText((TextReplacementConfig)TextReplacementConfig.builder().match(field2).replacement((var1xx, var2x) -> {
                  String var3x = var1xx.group(1);
                  String var4x = var1xx.group(2);
                  boolean var5 = !var3x.equals(var4x);
                  if (var5) {
                     return Component.text(Objects.requireNonNull(CalculatorType2.getReplacement(var4x)));
                  }

                  int var6 = Integer.parseInt(var3x);
                  return (ComponentLike)var3.get(var6);
               }).build())
               : Component.text(var1x))
         )
         .orElse(Component.text(var2));
   }

   private synchronized List<String> method10(String var1) {
      return this.field9.computeIfAbsent(var1, var0 -> {
         Builder var1x = ImmutableList.builder();

         for (String var5 : var0.split(Pattern.quote("."))) {
            if (!var5.isEmpty()) {
               var1x.add(var5);
            }
         }

         return var1x.build();
      });
   }

   private Optional<String> method11(List<String> var1, String var2, JsonObject var3) {
      String var4 = null;

      try {
         JsonObject var5 = var3;

         for (String var7 : var1) {
            JsonObject var8 = var5.getAsJsonObject(var7);
            if (var8 != null) {
               var5 = var8;
            }
         }

         JsonElement var10 = var5.get(var2);
         if (var10 != null) {
            var4 = var10.getAsString();
         }
      } catch (Exception var9) {
      }

      return var4 != null && !var4.isEmpty() ? Optional.of(var4) : Optional.empty();
   }

   public void method12(CalculatorType var1) {
      this.field4 = var1;
      this.method13();
   }

   public void setLanguage(String var1) {
      this.field5 = var1;
      this.method14();
      CalculatorType var2 = this.method2().getOrDefault(var1, CalculatorType.ENGLISH);
      if (this.field4 == null || !this.field4.equals(var2)) {
         this.method12(var2);
      }
   }

   private void method13() {
      if (!LunarBuildData.field4 && this.field8 == null) {
         this.field8 = this.method15("lang/lunar/dev-langs", false);
      }

      if (this.field4 == null) {
         Slayer.method4("Lang", "Couldn't get a valid language so we're setting it to ENGLISH.", new Object[0]);
         this.method12(CalculatorType.ENGLISH);
      } else {
         Slayer.method4("Lang", "Language set as %s (%s).", new Object[]{this.field4.name(), this.field4.getFileName()});
         this.field6 = this.method15("lang/lunar/" + this.field4.getFileName(), true);
         this.field7 = this.method15("lang/lunar/en_US", true);

         for (String var2 : this.field10) {
            JsonObject var3 = this.method15("assets/" + var2 + "/lang/" + this.field4.getFileName(), false);
            if (var3.keySet().isEmpty()) {
               var3 = this.method15("assets/" + var2 + "/lang/en_US", false);
            }

            if (!var3.keySet().isEmpty()) {
               this.field6.add(var2, var3);
               this.field7.add(var2, var3);
            }
         }

         if (ThreadModuleDump63.method4().method67() != null) {
            Calculator.method1();

            for (com.moonsworth.lunar.client.framework.feature.tps.Gui2Extension var4 : com.moonsworth.lunar.client.framework.feature.tps.Gui2Extension.values()) {
               var4.updateNametagPrefixLang();
            }
         }

         if (ThreadModuleDump63.method4().method40() != null) {
            ThreadModuleDump63.method4().method40().method1().forEach(Framework7Extension::method6);
         }

         this.method14();
      }
   }

   public void method14() {
      this.field3.method3("active", this.field5);
      this.field3.method3("default", ThreadModuleDump80.language);
      this.field3.method3("lang", this.field6);
      this.field3.method3("defaultLang", this.field7);
      this.field3.method3("forceUnicode", ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isForceUnicode());
   }

   private JsonObject method15(String var1, boolean var2) {
      JsonObject var3 = new JsonObject();

      try {
         InputStream var4 = this.method17(var1 + ".json", var1.toLowerCase() + ".json", var1 + ".lang", var1.toLowerCase() + ".lang");
         if (var4 != null) {
            if (var2) {
               Slayer.method4("Lang", "Language file found: %s.", new Object[]{var1});
            }

            ByteArrayOutputStream var5 = new ByteArrayOutputStream();
            byte[] var6 = new byte[1024];

            int var7;
            while ((var7 = var4.read(var6)) != -1) {
               var5.write(var6, 0, var7);
            }

            String var8 = var5.toString(StandardCharsets.UTF_8);

            try {
               var3 = new JsonParser().parse(var8).getAsJsonObject();
            } catch (Exception var10) {
               var3 = this.method16(var8);
            }

            var4.close();
         } else if (var2) {
            Slayer.method8("Lang", "Language file not found %s.", new Object[]{var1});
         }
      } catch (JsonParseException | IllegalStateException | IOException var11) {
         var11.printStackTrace();
      }

      return var3;
   }

   private JsonObject method16(String var1) {
      JsonObject var2 = new JsonObject();

      for (String var6 : var1.split("\\r?\\n")) {
         if (!var6.isEmpty() && !var6.startsWith("#")) {
            String[] var7 = var6.split("=", 2);
            if (var7.length == 2) {
               var2.addProperty(var7[0], var7[1]);
            }
         }
      }

      return var2;
   }

   private InputStream method17(String... var1) {
      for (String var5 : var1) {
         InputStream var6 = this.getClass().getClassLoader().getResourceAsStream(var5);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public void init() {
      super.init();
      this.method13();
      this.method18();
   }

   public void method18() {
      JsonArray var1 = new JsonArray();
      List var2 = ThreadModuleDump63.method3().bridge$getLanguageManager().bridge$getLanguages();
      var2.sort(Comparator.comparing(LanguageEntry::name));

      for (LanguageEntry var4 : var2) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("code", var4.method1());
         var5.addProperty("region", var4.method2());
         var5.addProperty("name", var4.name());
         var5.addProperty("lunarSupported", var4.method3());
         var1.add(var5);
      }

      this.field3.method3("available", var1);
   }

   public String method19(String var1, Object[] var2, boolean var3) {
      boolean var5 = var3 && var1.startsWith("key.");
      String var4;
      if (var5) {
         var4 = var1.substring("key.".length());
      } else {
         var4 = var1;
      }

      Optional var6 = Arrays.stream(var4.split("\\.")).findFirst();
      if (var6.isPresent()) {
         String var7 = var4;
         if (var5) {
            if (((String)var6.get()).equals("replaymod")) {
               var7 = "replaymod.input." + var7.substring("replaymod.".length());
            } else {
               var7 = "key." + var7;
               String var8 = Client.method109().method67().method4(ImmutableList.of((String)var6.get()), var7, var2);
               if (!var8.equals(var7)) {
                  return var8;
               }

               var7 = var4;
            }
         }

         String var10 = Client.method109().method67().method4(ImmutableList.of((String)var6.get()), var7, var2);
         if (!var10.equals(var7)) {
            return var10;
         }
      }

      return !var3 ? this.method19(var1, var2, true) : null;
   }

   @Generated
   public GuiIterator method20() {
      return this.field3;
   }

   @Generated
   public CalculatorType method21() {
      return this.field4;
   }

   @Generated
   public void method22(JsonObject var1) {
      this.field11 = var1;
   }
}
