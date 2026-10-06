package com.moonsworth.lunar.client.ui.notification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class Notification implements JsonProvider, Translatable {
   public static final float field1 = 200.0F;
   public static final float field2 = 125.0F;
   public static final float field3 = 20.0F;
   protected static final long field4 = 2500L;
   protected NotificationAnchor field5 = NotificationAnchor.TOP_RIGHT;
   private final ColorAnimation field6;
   protected long field7;
   protected long durationMs;
   private final ResourceLocationBridge field8;
   private final NotificationType field9;
   private boolean field10;
   private final String field11;
   protected final List<String> field12;
   private final String field13;
   protected float width;
   protected float height;
   private int textColor = -1;
   private int field14 = -1358954496;
   private int field15 = 1076176165;
   private int field16 = 553648127;

   public Notification(String text1) {
      this(null, null, null, text1);
   }

   public Notification(String text1, String text2) {
      this(null, null, text1, text2);
   }

   public Notification(ResourceLocationBridge horsestats141, String text2) {
      this(null, horsestats141, null, text2);
   }

   public Notification(NotificationType chesttype1, String text2) {
      this(chesttype1, chesttype1.getIcon(), null, text2);
   }

   public Notification(ResourceLocationBridge horsestats141, String text2, String text3) {
      this(null, horsestats141, text2, text3);
   }

   public Notification(NotificationType chesttype1, ResourceLocationBridge horsestats142, String text3, String text4) {
      this.field9 = chesttype1;
      this.field11 = text3;
      this.field8 = horsestats142;
      this.field6 = new ColorAnimation(250L);
      this.durationMs = 2500L;
      float value5 = this.hasIcon() ? 22.0F : 6.0F;
      float value6 = 200.0F - value5 - 8.0F;
      this.field13 = text4;
      this.field12 = FontRegistry.method10().method25(text4, value6);
      if (Ref.method4().method41() != null) {
         this.method9((NotificationAnchor)Ref.method4().method41().method6().method22().get());
      }

      this.updateWidth();
      this.method6();
   }

   public boolean hasIcon() {
      return this.field8 != null;
   }

   public boolean method2() {
      return this.field11 != null && !this.field11.isEmpty();
   }

   public void method2(AbstractRenderContext bridgeextension_91, float value2, float value3) {
      LcuiScreen.method55(bridgeextension_91, value2, value3, this.getWidth(), this.getHeight(), 4.0F, this.field15, this.field16, this.field14);
      this.method5(bridgeextension_91, value2, value3);
      this.method4(bridgeextension_91, value2, value3);
      byte number4 = 0;

      for (String text6 : this.field12) {
         float value7 = value2 + (this.hasIcon() ? 22.0F : 6.0F);
         float value8 = value3 + 6.0F + (this.method2() ? 8 : 0) + number4;
         FontRegistry.method10().method5(bridgeextension_91, text6, value7, value8, this.textColor);
         number4 += 8;
      }
   }

   protected float method3() {
      if (this.field7 == 0L) {
         this.field7 = System.currentTimeMillis();
      }

      if (this.method8() <= 250L && !this.field6.method5()) {
         this.field6.start();
      } else if (this.method7() <= 250L && !this.field6.method7() && !this.isDead()) {
         this.field6.start();
      }

      if (this.field6.method7()) {
         return this.method7() <= 250L
            ? this.field6.method9() * (10.0F + this.width)
            : 10.0F + this.width - this.field6.method9() * (10.0F + this.width);
      } else {
         return 0.0F;
      }
   }

   private void method4(AbstractRenderContext bridgeextension_91, float value2, float value3) {
      if (this.method2()) {
         float value4 = value2 + (this.hasIcon() ? 22.0F : 6.0F);
         FontRegistry.method9().method5(bridgeextension_91, this.field11, value4, value3 + 6.0F, -1);
      }
   }

   protected void method5(AbstractRenderContext bridgeextension_91, float value2, float value3) {
      if (this.hasIcon()) {
         float value4 = this.height / 2.0F - 8.0F;
         LcuiScreen.method38(bridgeextension_91, this.getIcon(), 8.0F, value2 + 4.0F, value3 + value4, -1);
         if (this.field10) {
            LcuiScreen.method57(bridgeextension_91, value2 + 4.0F, value3 + value4, 16.0F, 16.0F, 4.0F, -4210753);
         }
      }
   }

   protected void updateWidth() {
      float value1 = this.hasIcon() ? 22.0F : 6.0F;
      float value2 = 125.0F;

      for (String text4 : this.field12) {
         float value5 = FontRegistry.method10().method4(text4) + value1 + 6.0F;
         if (value5 > value2) {
            value2 = value5;
         }
      }

      this.width = value2;
   }

   protected void method6() {
      float value1 = this.field12.size() * 8 + 10 + (this.method2() ? 8 : 0);
      this.height = Math.max(20.0F, value1);
   }

   public long method7() {
      return this.field7 + this.durationMs - System.currentTimeMillis();
   }

   public long method8() {
      return System.currentTimeMillis() - this.field7;
   }

   public boolean isDead() {
      return this.field7 != 0L && this.method8() >= this.durationMs;
   }

   public Notification method9(NotificationAnchor gui2extension1) {
      this.field5 = gui2extension1;
      return this;
   }

   public Notification method10(long number1) {
      this.durationMs = number1;
      return this;
   }

   public String getLanguagePath() {
      return "popups";
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      if (this.field9 != null) {
         json1.addProperty("type", this.field9.name());
      }

      json1.addProperty("anchor", this.field5.id());
      json1.addProperty("durationMs", this.durationMs);
      json1.addProperty("title", this.field11);
      json1.addProperty("message", this.field13);
      if (this.field8 != null) {
         json1.addProperty("icon", this.field8.bridge$getPath());
      }

      return json1;
   }

   @Generated
   public NotificationAnchor method11() {
      return this.field5;
   }

   @Generated
   public ColorAnimation method12() {
      return this.field6;
   }

   @Generated
   public long method13() {
      return this.field7;
   }

   @Generated
   public long getDurationMs() {
      return this.durationMs;
   }

   @Generated
   public boolean method14() {
      return this.field10;
   }

   @Generated
   public String getTitle() {
      return this.field11;
   }

   @Generated
   public List<String> method15() {
      return this.field12;
   }

   @Generated
   public String method16() {
      return this.field13;
   }

   @Generated
   public int getTextColor() {
      return this.textColor;
   }

   @Generated
   public int method18() {
      return this.field14;
   }

   @Generated
   public int method19() {
      return this.field15;
   }

   @Generated
   public int method20() {
      return this.field16;
   }

   @Generated
   public ResourceLocationBridge getIcon() {
      return this.field8;
   }

   @Generated
   public NotificationType method21() {
      return this.field9;
   }

   @Generated
   public void method22(boolean flag1) {
      this.field10 = flag1;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public void setTextColor(int number1) {
      this.textColor = number1;
   }

   @Generated
   public void method24(int number1) {
      this.field14 = number1;
   }

   @Generated
   public void method25(int number1) {
      this.field15 = number1;
   }

   @Generated
   public void method26(int number1) {
      this.field16 = number1;
   }
}
