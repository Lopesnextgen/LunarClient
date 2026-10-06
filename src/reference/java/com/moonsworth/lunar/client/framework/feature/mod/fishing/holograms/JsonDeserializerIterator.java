package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType2;
import com.moonsworth.lunar.client.mod.skyblock.dungeontimer.SkyblockDungeonTimer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class JsonDeserializerIterator implements JsonDeserializer<Holograms11> {
   private SkyblockDungeonTimer field1 = null;
   private Holograms4_4 field2 = null;
   private Holograms4_4 field3 = null;
   private Holograms4_4 field4 = null;
   private Holograms4_4 field5 = null;
   private Holograms4_4 field6 = null;

   public Holograms11 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      this.field1 = ThreadModuleDump63.method4().method40().method82().method144();
      JsonObject var5 = var4.getAsJsonObject("catacombs");
      JsonObject var6 = var5.getAsJsonObject("basic");
      String var7 = var6.get("finalPattern").getAsString();
      JsonObject var8 = var6.getAsJsonObject("bloodOpen");
      JsonObject var9 = var6.getAsJsonObject("bloodClear");
      this.field2 = this.method2(var8, var7);
      this.field3 = this.method2(var9, var7);
      HashMap var10 = new HashMap();

      for (Entry var12 : var5.entrySet()) {
         HighlightType var13;
         try {
            var13 = HighlightType.valueOf((String)var12.getKey());
         } catch (IllegalArgumentException var27) {
            continue;
         }

         ArrayList var14 = new ArrayList();
         JsonObject var15 = ((JsonElement)var12.getValue()).getAsJsonObject();
         String var16 = var15.get("bossName").getAsString();
         NamedTextColor var17 = (NamedTextColor)NamedTextColor.NAMES.valueOrThrow(var15.get("color").getAsString());
         JsonArray var18 = var15.getAsJsonArray("uniqueSplits");
         this.method3(var16, var17, var14);

         for (JsonElement var20 : var18) {
            JsonObject var21 = var20.getAsJsonObject();
            var14.add(this.method2(var21, var7));
         }

         var10.put(var13, var14);
      }

      JsonObject var28 = var4.getAsJsonObject("kuudra");
      JsonObject var29 = var28.getAsJsonObject("basic");
      String var30 = var29.get("finalPattern").getAsString();
      JsonObject var31 = var29.getAsJsonObject("supplies");
      JsonObject var32 = var29.getAsJsonObject("build");
      JsonObject var33 = var29.getAsJsonObject("stun");
      this.field4 = this.method2(var31, var30);
      this.field5 = this.method2(var32, var30);
      this.field6 = this.method2(var33, var30);
      HashMap var34 = new HashMap();

      for (Entry var36 : var28.entrySet()) {
         HighlightType2 var37;
         try {
            var37 = HighlightType2.valueOf((String)var36.getKey());
         } catch (IllegalArgumentException var26) {
            continue;
         }

         ArrayList var38 = new ArrayList();
         JsonArray var22 = ((JsonElement)var36.getValue()).getAsJsonObject().getAsJsonArray("uniqueSplits");
         this.method5(var38);

         for (JsonElement var24 : var22) {
            JsonObject var25 = var24.getAsJsonObject();
            var38.add(this.method2(var25, var30));
         }

         var34.put(var37, var38);
      }

      return new Holograms11(var10, var34);
   }

   private Holograms4_4 method2(JsonObject var1, String var2) {
      String var3;
      if (var1.has("name")) {
         var3 = var1.get("name").getAsString();
      } else {
         if (!var1.has("translatableName")) {
            throw new JsonParseException("Timer has no name or translatable name");
         }

         var3 = this.field1.method33(var1.get("translatableName").getAsString(), new Object[0]);
      }

      NamedTextColor var4 = (NamedTextColor)NamedTextColor.NAMES.valueOrThrow(var1.get("color").getAsString());
      TextComponent var5 = (TextComponent)Component.text(var3).color(var4);
      if (var1.has("final") && var1.get("final").getAsBoolean()) {
         return new Holograms4_4(var5, var2);
      }

      String var6 = var1.get("pattern").getAsString();
      return new Holograms4_4(var5, var6);
   }

   private void method3(String var1, NamedTextColor var2, List<Holograms4_4> var3) {
      this.method4(var1, (TextComponent)Component.text(this.field1.method33("bossEntry", new Object[]{var1})).color(var2), var3);
   }

   private void method4(String var1, TextComponent var2, List<Holograms4_4> var3) {
      var3.add(new Holograms4_4(this.field2));
      var3.add(new Holograms4_4(this.field3));
      var3.add(new Holograms4_4(var2, "\\[BOSS\\] " + var1 + ": .*"));
   }

   private void method5(List<Holograms4_4> var1) {
      var1.add(new Holograms4_4(this.field4));
      var1.add(new Holograms4_4(this.field5));
      var1.add(new Holograms4_4(this.field6));
   }
}
