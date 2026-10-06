package com.moonsworth.lunar.client.cosmetics.gecko;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationJsonParser;
import io.sentry.Attachment;
import io.sentry.Sentry;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import software.bernie.geckolib3.core.builder.Animation;

public class AnimationFileLoader {
   public AnimationFileLoader() {
   }

   public AnimationCache method1(ResourceLocationBridge horsestats141, Bridge11_2 bridge11_22) {
      AnimationCache nameplate23 = new AnimationCache();
      JsonObject json4 = this.method2(horsestats141, bridge11_22);
      if (json4 == null) {
         return nameplate23;
      }

      for (Entry entry7 : AnimationJsonParser.method1(json4)) {
         String text8 = (String)entry7.getKey();

         try {
            Animation animation9 = AnimationJsonParser.method12(AnimationJsonParser.method10(json4, text8), horsestats141);
            nameplate23.method2(text8, animation9);
         } catch (JSONException jsonexception11) {
            LunarLogger.method7("Could not load animation: {}", new Object[]{text8, jsonexception11});
            throw new RuntimeException(jsonexception11);
         }
      }

      return nameplate23;
   }

   private JsonObject method2(ResourceLocationBridge horsestats141, Bridge11_2 bridge11_22) {
      String text3 = method3(horsestats141, bridge11_22);

      try {
         JsonReader jsonreader4 = new JsonReader(new StringReader(text3));
         return (JsonObject)new Gson().getAdapter(JsonObject.class).read(jsonreader4);
      } catch (Exception exception7) {
         if (text3 != null) {
            byte[] items5 = text3.getBytes(StandardCharsets.UTF_8);
            Attachment attachment6 = new Attachment(items5, "animation-file-loader.json");
            Sentry.configureScope(arg1x -> arg1x.addAttachment(attachment6));
         }

         throw exception7;
      }
   }

   public static String method3(ResourceLocationBridge horsestats140, Bridge11_2 bridge11_21) {
      ResourceBridge bridge152 = bridge11_21.bridge$getResource(horsestats140);
      if (bridge152 == null) {
         LunarLogger.method5("Couldn't find: " + horsestats140, new Object[0]);
         throw new RuntimeException(new FileNotFoundException(horsestats140.toString()));
      }

      try (InputStream input3 = bridge152.bridge$getInputStream()) {
         return IOUtils.toString(input3, StandardCharsets.UTF_8);
      } catch (Exception exception8) {
         LunarLogger.warn("Couldn't load: " + horsestats140, exception8);
         throw new RuntimeException("Failed to load " + horsestats140.toString(), exception8);
      }
   }
}
