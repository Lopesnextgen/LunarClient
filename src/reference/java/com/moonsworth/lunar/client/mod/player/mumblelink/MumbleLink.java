package com.moonsworth.lunar.client.mod.player.mumblelink;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.network.mumble.LinkData;
import com.moonsworth.lunar.client.network.mumble.MumbleVec;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Locale;

public final class MumbleLink extends AbstractFeature {
   private static final String field8 = "mumbleLinkEnabledPopup";
   private static final String field9 = "mumbleLinkDisabledPopup";
   private static final String field10 = "mumbleLinkUnsupportedPopup";
   private static final String field11 = "mumbleLinkErrorPopup";
   private com.moonsworth.lunar.client.network.mumble.MumbleLink field12;
   private boolean field13 = false;
   private boolean field14 = false;
   private boolean field15 = false;
   private final JsonObject field16 = this.method14();
   private final JsonObject field17 = new JsonObject();

   public MumbleLink() {
      super(false);
      this.handle(EventServerJoin.class, arg1 -> {
         if (this.field13 && Ref.method8() != null && Ref.method8().bridge$isRemote()) {
            Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkEnabledPopup", new Object[0]));
         }
      });
      this.handle(
         EventTick.class,
         arg1 -> {
            if (this.field12 != null && Ref.method8() != null && Ref.method8().bridge$isRemote()) {
               if (!this.field13 && !this.field14) {
                  this.method15();
               } else {
                  if (this.field14) {
                     return;
                  }

                  Bridge5Extension_5 bridge5extension_52 = Ref.method7();
                  float value3 = 1.0F;
                  float value4 = 1.0F;
                  float value5 = 1.0F;
                  float value6 = 1.0F;
                  float value7 = 1.0F;
                  float value8 = 1.0F;
                  float value9 = 1.0F;
                  float value10 = 1.0F;
                  float value11 = 1.0F;
                  float value12 = 1.0F;
                  float value13 = 1.0F;
                  float value14 = 1.0F;
                  MumbleVec mumblevec15 = new MumbleVec(
                     bridge5extension_52.bridge$getLastReportedLookAngle().bridge$xCoord(),
                     bridge5extension_52.bridge$getLastReportedLookAngle().bridge$yCoord(),
                     bridge5extension_52.bridge$getLastReportedLookAngle().bridge$zCoord()
                  );
                  MumbleVec mumblevec16 = this.method3(bridge5extension_52);
                  MumbleVec mumblevec17 = new MumbleVec(bridge5extension_52.bridge$getPosX(), (float)bridge5extension_52.bridge$getPosZ(), (float)bridge5extension_52.bridge$getPosY());
                  MumbleVec mumblevec18 = new MumbleVec(mumblevec15.xCoord * value3, (float)mumblevec15.zCoord * value5, (float)mumblevec15.yCoord * value4);
                  MumbleVec mumblevec19 = new MumbleVec(mumblevec16.xCoord * value9, (float)mumblevec16.zCoord * value11, (float)mumblevec16.yCoord * value10);
                  MumbleVec mumblevec20 = new MumbleVec(bridge5extension_52.bridge$getPosX(), (float)bridge5extension_52.bridge$getPosZ(), (float)bridge5extension_52.bridge$getPosY());
                  MumbleVec mumblevec21 = new MumbleVec(mumblevec15.xCoord * value6, (float)mumblevec15.zCoord * value8, (float)mumblevec15.yCoord * value7);
                  MumbleVec mumblevec22 = new MumbleVec(mumblevec16.xCoord * value12, (float)mumblevec16.zCoord * value14, (float)mumblevec16.yCoord * value13);
                  LinkData linkdata23 = new LinkData(this.method13().toString(), this.field16.toString(), mumblevec18, mumblevec19, mumblevec17, mumblevec20, mumblevec21, mumblevec22);
                  this.field12.update(linkdata23);
               }
            }
         }
      );
   }

   public String getId() {
      return "MUMBLE_LINK";
   }

   private JsonObject method13() {
      JsonArray array1 = new JsonArray();
      array1.add(new JsonPrimitive(Ref.method8().bridge$getWorldInfo().bridge$getSpawnX()));
      array1.add(new JsonPrimitive(Ref.method8().bridge$getWorldInfo().bridge$getSpawnY()));
      array1.add(new JsonPrimitive(Ref.method8().bridge$getWorldInfo().bridge$getSpawnZ()));
      this.field17.addProperty("name", Ref.method7().bridge$getName());
      this.field17.add("worldSpawn", array1);
      if (Bridge.method8().getMinecraftVersion().method19()) {
         this.field17.addProperty("dimension", Ref.method7().bridge$getDimensionName());
      } else {
         this.field17.addProperty("dimension", Ref.method7().bridge$getDimension());
      }

      return this.field17;
   }

   private JsonObject method14() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("domain", "AllTalk");
      return json1;
   }

   private MumbleVec method3(Bridge5Extension_5 bridge5extension_51) {
      float value2 = MathHelperBridge.method1(-bridge5extension_51.bridge$getRotationYaw() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float value3 = MathHelperBridge.method2(-bridge5extension_51.bridge$getRotationYaw() * (float) (Math.PI / 180.0) - (float) Math.PI);
      float value4 = -MathHelperBridge.method1((-bridge5extension_51.bridge$getRotationPitch() + 90.0) * (float) (Math.PI / 180.0));
      float value5 = MathHelperBridge.method2((-bridge5extension_51.bridge$getRotationPitch() + 90.0) * (float) (Math.PI / 180.0));
      return new MumbleVec(value3 * value4, value5, value2 * value4);
   }

   public void method3(boolean flag1) {
      boolean flag2 = this.field15 != flag1;
      this.field15 = flag1;
      if (!LunarConstants.field1.toLowerCase(Locale.ROOT).contains("win")) {
         if (flag2 && flag1) {
            Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkUnsupportedPopup", new Object[0]));
         }

         ((SettingIntercept)this.method7(ModTraits.field4)).method1(this, OverrideSource.SERVER, false);
      } else if (flag2) {
         if (flag1) {
            if (this.field12 == null) {
               this.field12 = new com.moonsworth.lunar.client.network.mumble.MumbleLink();
            }

            if (this.field12.isLoaded()) {
               this.method15();
            } else {
               this.field14 = true;
               Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkErrorPopup", new Object[0]));
            }
         } else {
            Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkDisabledPopup", new Object[0]));
         }
      }
   }

   public void method15() {
      if (!this.field13) {
         try {
            this.field13 = this.field12.init() >= 0;
         } catch (Throwable exception2) {
            this.field14 = true;
            this.field13 = false;
            Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkErrorPopup", new Object[0]));
            exception2.printStackTrace();
         }
      }

      if (this.field13) {
         Ref.method4().method69().method3(NotificationManager.method15("mumbleLinkEnabledPopup", new Object[0]));
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   public boolean method7() {
      return false;
   }
}
