package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.skyblock.chocolatefactory.SkyblockChocolateFactory;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class RabbitCollectionStore {
   private static final File field1 = new File(LunarConstants.field25 + File.separator + "skyblock_rabbit_collection.json");
   public static final ResourceLocationBridge field2 = ResourceLocationBridge.create("entity.experience_orb.pickup");
   private final SkyblockChocolateFactory field3;
   private long field4;

   public RabbitCollectionStore(SkyblockChocolateFactory skyblockchocolatefactory1) {
      this.field3 = skyblockchocolatefactory1;
   }

   public void method1(SkyblockProfileEvents.SkyblockProfileChangeEvent data151) {
      this.field3.method66(new RabbitCollection());
      if (field1.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field1));
            if (!element3.isJsonObject()) {
               return;
            }

            JsonObject json4 = element3.getAsJsonObject();
            String text5 = Ref.method3().bridge$getSession().bridge$getProfile().getId().toString();
            if (!json4.has(text5)) {
               return;
            }

            JsonObject json6 = json4.getAsJsonObject(text5);
            if (!json6.has(text2)) {
               return;
            }

            this.field3.method66((RabbitCollection)LunarConstants.field22.fromJson(json6.getAsJsonObject(text2), RabbitCollection.class));
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Loading SkyBlock Hoppity Rabbit Collection");
         }
      }
   }

   public void method2(String text1) {
      if (text1 != null) {
         try {
            Object obj2;
            if (field1.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field1));
               } catch (JsonParseException jsonparseexception12) {
                  obj2 = new JsonObject();
               }
            } else {
               obj2 = new JsonObject();
            }

            if (!obj2.isJsonObject()) {
               return;
            }

            String text3 = Ref.method3().bridge$getSession().bridge$getProfile().getId().toString();
            JsonObject json4 = obj2.getAsJsonObject();
            JsonObject json5 = json4.getAsJsonObject(text3);
            if (json5 == null) {
               json5 = new JsonObject();
            }

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field3.method55()).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field1)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Hoppity Rabbit Collection");
         }

         this.field3.method55().setDirty(false);
      }
   }

   public TextComponent method3(long number1, long number3) {
      if (this.field3.method61() <= 0.0) {
         return (TextComponent)Component.text("Never").style(Style.style(NamedTextColor.RED));
      }

      Style style5 = TextComponentFactory.styleOf(this.field3.method54());
      if (number3 >= number1) {
         return (TextComponent)Component.text("Now").style(style5);
      }

      long number6 = (long)((number1 - number3) / this.field3.method61()) * 1000L;
      return (TextComponent)Component.text(TimeFormatting.method1(number6)).style(style5);
   }

   public TextComponent method4(long number1) {
      long number3 = this.field3.method57() + this.field3.method74();
      return this.method3(number1, number3);
   }

   public void method5(ResourceLocationBridge horsestats141, int number2) {
      long number3 = Ref.method3().bridge$getSystemTime();
      if (number3 - this.field4 > number2) {
         this.field4 = number3;
         Ref.method3().bridge$getSoundHandler().method1(horsestats141);
      }
   }
}
