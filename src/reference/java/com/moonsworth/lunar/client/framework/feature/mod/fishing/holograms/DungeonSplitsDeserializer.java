package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.KuudraTier;
import com.moonsworth.lunar.client.mod.skyblock.dungeontimer.SkyblockDungeonTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class DungeonSplitsDeserializer implements JsonDeserializer<DungeonSplits> {
   private SkyblockDungeonTimer field1 = null;
   private DungeonSplit field2 = null;
   private DungeonSplit field3 = null;
   private DungeonSplit field4 = null;
   private DungeonSplit field5 = null;
   private DungeonSplit field6 = null;

   public DungeonSplitsDeserializer() {
   }

   public DungeonSplits method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      this.field1 = Ref.method4().method40().method82().method144();
      JsonObject json5 = json4.getAsJsonObject("catacombs");
      JsonObject json6 = json5.getAsJsonObject("basic");
      String text7 = json6.get("finalPattern").getAsString();
      JsonObject json8 = json6.getAsJsonObject("bloodOpen");
      JsonObject json9 = json6.getAsJsonObject("bloodClear");
      this.field2 = this.method2(json8, text7);
      this.field3 = this.method2(json9, text7);
      HashMap map10 = new HashMap();

      for (Entry entry12 : json5.entrySet()) {
         DungeonFloor highlighttype13;
         try {
            highlighttype13 = DungeonFloor.valueOf((String)entry12.getKey());
         } catch (IllegalArgumentException illegalargumentexception27) {
            continue;
         }

         ArrayList list14 = new ArrayList();
         JsonObject json15 = ((JsonElement)entry12.getValue()).getAsJsonObject();
         String text16 = json15.get("bossName").getAsString();
         NamedTextColor namedtextcolor17 = (NamedTextColor)NamedTextColor.NAMES.valueOrThrow(json15.get("color").getAsString());
         JsonArray array18 = json15.getAsJsonArray("uniqueSplits");
         this.method3(text16, namedtextcolor17, list14);

         for (JsonElement element20 : array18) {
            JsonObject json21 = element20.getAsJsonObject();
            list14.add(this.method2(json21, text7));
         }

         map10.put(highlighttype13, list14);
      }

      JsonObject json28 = json4.getAsJsonObject("kuudra");
      JsonObject json29 = json28.getAsJsonObject("basic");
      String text30 = json29.get("finalPattern").getAsString();
      JsonObject json31 = json29.getAsJsonObject("supplies");
      JsonObject json32 = json29.getAsJsonObject("build");
      JsonObject json33 = json29.getAsJsonObject("stun");
      this.field4 = this.method2(json31, text30);
      this.field5 = this.method2(json32, text30);
      this.field6 = this.method2(json33, text30);
      HashMap map34 = new HashMap();

      for (Entry entry36 : json28.entrySet()) {
         KuudraTier highlighttype237;
         try {
            highlighttype237 = KuudraTier.valueOf((String)entry36.getKey());
         } catch (IllegalArgumentException illegalargumentexception26) {
            continue;
         }

         ArrayList list38 = new ArrayList();
         JsonArray array22 = ((JsonElement)entry36.getValue()).getAsJsonObject().getAsJsonArray("uniqueSplits");
         this.method5(list38);

         for (JsonElement element24 : array22) {
            JsonObject json25 = element24.getAsJsonObject();
            list38.add(this.method2(json25, text30));
         }

         map34.put(highlighttype237, list38);
      }

      return new DungeonSplits(map10, map34);
   }

   private DungeonSplit method2(JsonObject json1, String text2) {
      String text3;
      if (json1.has("name")) {
         text3 = json1.get("name").getAsString();
      } else {
         if (!json1.has("translatableName")) {
            throw new JsonParseException("Timer has no name or translatable name");
         }

         text3 = this.field1.method33(json1.get("translatableName").getAsString(), new Object[0]);
      }

      NamedTextColor namedtextcolor4 = (NamedTextColor)NamedTextColor.NAMES.valueOrThrow(json1.get("color").getAsString());
      TextComponent text5 = (TextComponent)Component.text(text3).color(namedtextcolor4);
      if (json1.has("final") && json1.get("final").getAsBoolean()) {
         return new DungeonSplit(text5, text2);
      }

      String text6 = json1.get("pattern").getAsString();
      return new DungeonSplit(text5, text6);
   }

   private void method3(String text1, NamedTextColor namedtextcolor2, List<DungeonSplit> list3) {
      this.method4(text1, (TextComponent)Component.text(this.field1.method33("bossEntry", new Object[]{text1})).color(namedtextcolor2), list3);
   }

   private void method4(String text1, TextComponent text2, List<DungeonSplit> list3) {
      list3.add(new DungeonSplit(this.field2));
      list3.add(new DungeonSplit(this.field3));
      list3.add(new DungeonSplit(text2, "\\[BOSS\\] " + text1 + ": .*"));
   }

   private void method5(List<DungeonSplit> list1) {
      list1.add(new DungeonSplit(this.field4));
      list1.add(new DungeonSplit(this.field5));
      list1.add(new DungeonSplit(this.field6));
   }
}
