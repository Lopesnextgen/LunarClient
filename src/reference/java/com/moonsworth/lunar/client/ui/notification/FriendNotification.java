package com.moonsworth.lunar.client.ui.notification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.network.friend.FriendStatus;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.HeadTextureCache;
import java.util.UUID;
import lombok.Generated;
import com.moonsworth.lunar.client.gui.notification.FormattedNotification;

public class FriendNotification extends FormattedNotification {
   private final UUID field18;
   private final Memory field19;
   private boolean field20;

   public FriendNotification(UUID uuid1, Memory memory2, String text3) {
      super(text3, "friendsHint");
      this.field19 = memory2;
      this.field18 = uuid1;
   }

   @Override
   public void method2(AbstractRenderContext bridgeextension_91, float value2, float value3) {
      if (Ref.method4().method31().method16() != FriendStatus.BUSY) {
         super.method2(bridgeextension_91, value2, value3);
      }
   }

   @Override
   public boolean hasIcon() {
      return true;
   }

   @Override
   public ResourceLocationBridge getIcon() {
      return HeadTextureCache.method1(this.field18);
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = (JsonObject)super.provide();
      json1.addProperty("uuid", this.field18.toString());
      json1.addProperty("user", this.field19.getName());
      json1.addProperty("chat", this.field20);
      return json1;
   }

   @Generated
   public void method2(boolean flag1) {
      this.field20 = flag1;
   }
}
