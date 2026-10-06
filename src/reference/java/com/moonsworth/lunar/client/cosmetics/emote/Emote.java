package com.moonsworth.lunar.client.cosmetics.emote;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.AnimationTimer;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import mchorse.emoticons.common.emotes.Emotes;

public abstract class Emote implements EmoteModelRenderer, JsonProvider {
   protected final String field1;
   protected final String field2;
   protected final AnimationTimer field3;
   private final StyngrSong field4;
   private final int field5;
   private final EmoteGift field6;
   private ResourceLocationBridge field7;

   public Emote(int number1, EmoteGift fov2_42, String text3, String text, AnimationTimer holograms_25, StyngrSong chest6) {
      this.field5 = number1;
      this.field6 = fov2_42;
      this.field1 = text3;
      this.field2 = text;
      this.field4 = chest6;
      this.field3 = holograms_25;
      if (this.field3 != null) {
         this.field3.start();
      }

      try {
         this.field7 = ResourceLocationBridge.create("lunar", "emotes/icons/" + number1 + ".webp");
      } catch (Exception exception8) {
         exception8.printStackTrace();
         this.field7 = null;
      }
   }

   public String method2() {
      return this.field1.toLowerCase().replace(" ", "_");
   }

   public boolean method3() {
      return this.field3.method6();
   }

   public void method3(Bridge6_10 bridge6_101) {
      if (bridge6_101 != null) {
         if (bridge6_101 == Ref.method7() && Client.method109().method45().field9) {
            if (!Ref.method4().method40().method31().isActive()) {
               Ref.method3().bridge$getGameSettings().bridge$setThirdPersonView(0);
            }

            Client.method109().method45().field9 = false;
         }
      }
   }

   public boolean method4() {
      return false;
   }

   public int getMetadata() {
      return Emotes.get(this.method2()).<Integer>map(mchorse.emoticons.common.emotes.Emote::getMetadata).orElse(-1);
   }

   private float method5(long number1) {
      return Math.round((float)number1 / 1000.0F * 100.0F) / 100.0F;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field5);
      json1.addProperty("name", this.field1);
      json1.addProperty("resource", this.field7.bridge$getPath());
      json1.addProperty("animator", this.field2);
      TranslationManager foghandler282 = Client.method109().method67();
      if (this.field6 != null) {
         if (this.field6.method5() > 0L) {
            String text3;
            if (this.field6.method5() < System.currentTimeMillis()) {
               json1.addProperty("expireTime", this.field6.method5());
               int number4 = Math.max(1, (int)((System.currentTimeMillis() - this.field6.method5()) / 86400000L));
               text3 = foghandler282.method2("gui.emotes", number4 > 1 ? "expired_plural" : "expired_singular", new Object[]{number4});
            } else {
               json1.addProperty("expireTime", 0);
               int number8 = Math.max(1, (int)((this.field6.method5() - System.currentTimeMillis()) / 86400000L));
               text3 = foghandler282.method2("gui.emotes", number8 > 1 ? "expires_in_plural" : "expires_in_singular", new Object[]{number8});
            }

            json1.addProperty("expiresIn", text3);
         }

         if (this.field6.method1() != null) {
            json1.addProperty("grantedAt", this.field6.method1().getEpochSecond());
            json1.addProperty("grantedAtReadable", this.field6.method3());
         } else {
            json1.addProperty("grantedAt", 0);
            json1.addProperty("grantedAtReadable", "Unknown");
         }

         if (this.field6.method2() != null) {
            json1.add("gifter", this.field6.method2().provide());
         }

         json1.add("recommendedJams", LunarConstants.field22.toJsonTree(this.field6.method4()));
      }

      mchorse.emoticons.common.emotes.Emote emote7 = (mchorse.emoticons.common.emotes.Emote)Emotes.EMOTES.get(this.field1.toLowerCase().replace(" ", "_"));
      if (emote7 != null) {
         String text9 = foghandler282.method2("gui.emotes", emote7.looping ? "yes" : "no", new Object[0]);
         String text5 = foghandler282.method2("gui.emotes", "loops", new Object[]{text9});
         String text6 = foghandler282.method2("gui.emotes", "duration", new Object[]{this.method5(emote7.duration * 50L)});
         json1.addProperty("loops", text5);
         json1.addProperty("duration", text6);
      }

      return json1;
   }

   @Generated
   public String getName() {
      return this.field1;
   }

   @Generated
   public String method6() {
      return this.field2;
   }

   @Generated
   public AnimationTimer method7() {
      return this.field3;
   }

   @Generated
   public StyngrSong method8() {
      return this.field4;
   }

   @Generated
   public int getId() {
      return this.field5;
   }

   @Generated
   public EmoteGift method9() {
      return this.field6;
   }

   @Generated
   public ResourceLocationBridge getResource() {
      return this.field7;
   }
}
