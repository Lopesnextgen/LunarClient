package com.moonsworth.lunar.client.account;

import com.google.gson.JsonArray;
import com.lunarclient.common.v1.UserSocialPlatform;
import com.lunarclient.websocket.socials.v1.LinkedSocial;
import com.moonsworth.lunar.client.framework.ItemMapHandler;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class SocialLinksManager extends ItemMapHandler<UserSocialPlatform, LinkedAccount> implements Extension {
   private final GuiIterator field2 = new GuiIterator();

   public SocialLinksManager() {
      this.method5(false);
   }

   public void method1(List<LinkedSocial> var1) {
      for (LinkedSocial var3 : var1) {
         UserSocialPlatform var4 = var3.getSocial().getPlatform();
         LinkedAccount var5 = (LinkedAccount)this.method3().get(var4);
         if (var5 != null) {
            var5.method1(var3);
         }
      }

      this.method5(true);
   }

   public void method4() {
      for (LinkedAccount var2 : this.method3().values()) {
         var2.method1(null);
      }

      this.method5(false);
   }

   @Override
   protected Map<UserSocialPlatform, LinkedAccount> method3() {
      LinkedHashMap var1 = new LinkedHashMap();
      var1.put(
         UserSocialPlatform.USER_SOCIAL_PLATFORM_TWITTER,
         new LinkedAccount("twitter", "X (Twitter)", UserSocialPlatform.USER_SOCIAL_PLATFORM_TWITTER, PhosphorIconLegacy.PI_XCOM_STROKE)
      );
      var1.put(
         UserSocialPlatform.USER_SOCIAL_PLATFORM_DISCORD,
         new LinkedAccount("discord", "Discord", UserSocialPlatform.USER_SOCIAL_PLATFORM_DISCORD, PhosphorIconLegacy.PI_DISCORD_STROKE, List.of(5596, 5594, 5650))
      );
      var1.put(
         UserSocialPlatform.USER_SOCIAL_PLATFORM_TWITCH,
         new LinkedAccount("twitch", "Twitch", UserSocialPlatform.USER_SOCIAL_PLATFORM_TWITCH, PhosphorIconLegacy.PI_TWITCH_STROKE)
      );
      var1.put(
         UserSocialPlatform.USER_SOCIAL_PLATFORM_YOUTUBE,
         new LinkedAccount("youtube", "YouTube", UserSocialPlatform.USER_SOCIAL_PLATFORM_YOUTUBE, PhosphorIconLegacy.PI_YOUTUBE_STROKE)
      );
      return var1;
   }

   public JsonArray method5() {
      JsonArray var1 = new JsonArray();
      ArrayList var2 = new ArrayList(this.method3().values());
      var2.sort(Comparator.comparing(LinkedAccount::getName));

      for (LinkedAccount var4 : var2) {
         var1.add(var4.provide());
      }

      return var1;
   }

   public void method5(boolean var1) {
      this.field2.method3("socials", this.method5());
      this.field2.method3("connected", var1);
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field2;
   }
}
