package com.moonsworth.lunar.client.ui.hud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.util.Annotation6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModDetails;

public abstract class HudElementBase implements MixinCore9Extension {
   private final FloatOption field1 = (FloatOption)this.method1(
         (FloatOption.Data)((FloatOption.Data)OptionFactory.method2("scale").method4(1.0F))
            .method8(this.OHHHRHRRIOCCHRCOHIOCOORHROIORC(), this.HRRRRCOROCOIHHOIRRCCCROCIOHRCO())
      )
      .method31();
   private final GuiIterator field2 = new GuiIterator();
   private final float field3;
   private final float field4;
   @NotNull
   private final HudAnchor field5;
   @NotNull
   private HudAnchor field6;
   private double field7;
   private double field8;
   private float x;
   private float y;
   private float width;
   private float height;

   public HudElementBase(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) @NotNull HudAnchor var3
   ) {
      this.field3 = var1;
      this.field4 = var2;
      this.x = var1;
      this.y = var2;
      this.field5 = var3;
      this.field6 = var3;
   }

   @Contract("_->param1")
   protected FloatOption.Data method1(FloatOption.Data var1) {
      return var1;
   }

   @MustBeInvokedByOverriders
   @Override
   public void method1(RootSettingsAssembler var1) {
      var1.method11(new ClientOption[]{this.field1});
   }

   @Override
   public float method1() {
      return (float)this.field7;
   }

   @Override
   public float method2() {
      return (float)this.field8;
   }

   @Override
   public void setScale(float var1) {
      this.field1.method1(var1);
   }

   @Override
   public float getScale() {
      return this.field1.get() / this.IRHROCORHCRRORRCICOOOOHCIOOCHR();
   }

   @Override
   public FloatOption method9() {
      return this.field1;
   }

   @Override
   public double method13() {
      return this.field7 * this.getScale();
   }

   @Override
   public double method14() {
      return this.field8 * this.getScale();
   }

   @Override
   public void method15(MarkerModel.Data2 var1) {
      this.field7 = LcuiScreen.method138(
         this.OCRORCCHCRIOOIRHOHRRHCRRHRCIHO(var1.HHHCHORHIHRCOHIOICICICHCRRICCI()), this.method21() ? 1.0 : LcuiScreen.method17()
      );
      this.field8 = LcuiScreen.method138(
         this.CORRCOHCRHOHHOIHOIOICORROHOOOO(var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO()), this.method21() ? 1.0 : LcuiScreen.method17()
      );
   }

   @Override
   public void method16(float var1, float var2) {
      this.width = var1;
      this.height = var2;
   }

   @Override
   public void method17(float var1, float var2) {
      this.x = var1;
      this.y = var2;
   }

   @Override
   public void method18() {
      this.x = this.field3;
      this.y = this.field4;
      this.field6 = this.field5;
   }

   @Override
   public double method20(HudAnchor var1, double var2) {
      return HudAnchor.anchorOriginX(var1, var2 / this.getScale(), this.getWidth()) + this.x / this.getScale();
   }

   @Override
   public double method22(HudAnchor var1, double var2) {
      return HudAnchor.anchorOriginY(var1, var2 / this.getScale(), this.getHeight()) + this.y / this.getScale();
   }

   @Override
   public HudAnchor method25() {
      return this.field5;
   }

   @NotNull
   @Override
   public HudAnchor method26() {
      return this.field6;
   }

   @Override
   public void method27(@NotNull HudAnchor var1) {
      this.field6 = var1;
   }

   @Annotation("properties")
   @Override
   public JsonElement method4() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("x", this.getX() * 2.0F);
      var1.addProperty("y", this.getY() * 2.0F);
      var1.addProperty("scale", this.getScale());
      var1.addProperty("position", this.method26().id());
      return var1;
   }

   @Override
   public void method1(JsonObject var1) {
      if (this.x != this.field3) {
         var1.addProperty("x", this.x);
      }

      if (this.y != this.field4) {
         var1.addProperty("y", this.y);
      }

      if (this.x != this.field3 || this.y != this.field4 || !this.field6.id().equalsIgnoreCase(this.field5.id())) {
         var1.addProperty("position", this.field6.id());
      }
   }

   @Override
   public void load(JsonObject var1) {
      if (var1.has("position") && !var1.get("position").isJsonNull()) {
         this.field6 = Objects.requireNonNullElse(HudAnchor.fromId(var1.get("position").getAsString()), this.field5);
      } else {
         this.field6 = this.field5;
      }

      float var2 = var1.has("x") && !var1.get("x").isJsonNull() ? var1.get("x").getAsFloat() : this.field3;
      float var3 = var1.has("y") && !var1.get("y").isJsonNull() ? var1.get("y").getAsFloat() : this.field4;
      this.method17(var2, var3);
   }

   protected boolean method19(MarkerModel.Data2 var1) {
      if (ThreadModuleDump63.method11() == null) {
         return false;
      }

      double var2 = this.method13();
      double var4 = this.method14();
      return var1.HHHCHORHIHRCOHIOICICICHCRRICCI() > var2
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > var4
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < var2 + this.getWidth()
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < var4 + this.getHeight();
   }

   private static void method20(HudBaseRenderEvent var0) {
      boolean var1 = Client.method109().method40().method95().isEnabled();
      boolean var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() && !Client.method109().method41().method6().method24().get();
      if (var1 || !var2) {
         if (!Client.method109().method56().method25()) {
            if (!ThreadModuleDump63.method41() || ThreadModuleDump63.method4().method41().method6().method25().get()) {
               boolean var3 = method21();
               if (!Client.method109().method40().method64().method13() || var3) {
                  boolean var4 = !(var0 instanceof HudBaseRenderEvent.Data4);
                  boolean var5 = ThreadModuleDump63.method4().method40().method85().method17(var0x -> !var0x.method53().method24());

                  for (Framework7Extension var7 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
                     if ((!var1 || !var2 || var7 instanceof F3Display) && var7.isEnabled()) {
                        if (var5) {
                           ModDetails var8 = (ModDetails)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
                           if (var8 == null || !var8.isVanilla()) {
                              continue;
                           }
                        }

                        AlertExtension var10 = (AlertExtension)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
                        if (var10 != null) {
                           var10.method3(var3x -> {
                              if (!var3x.isEnabled()) {
                                 return false;
                              }

                              MixinCore9Extension var4x = (MixinCore9Extension)var3x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
                              if (var4x == null) {
                                 return true;
                              }

                              if ((!var0.method4() || var4 == var4x.method31()) && var4x.method5(var3, var3x)) {
                                 method22(var0, var4x, var3);
                              }

                              return true;
                           });
                        }

                        MixinCore9Extension var9 = (MixinCore9Extension)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
                        if (var9 != null && (!var0.method4() || var4 == var9.method31()) && var9.method5(var3, var7)) {
                           method22(var0, var9, var3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static boolean method21() {
      return ThreadModuleDump63.method3().bridge$getWorld() == null
         || DriverViewportLegacy.method50().method61() == DriverRouteRegistryLegacy.field5
         || DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field5
         || ThreadModuleDump63.method11() == com.moonsworth.lunar.client.ui.hud.HudEditorScreen.class
         || ThreadModuleDump63.method11() == com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen.class;
   }

   private static void method22(HudBaseRenderEvent var0, MixinCore9Extension var1, boolean var2) {
      LegacyGuiGraphicsBridge var3 = var0.method1();
      float var4 = var1.getScale();
      var3.push();
      var3.method40(var4, var4);
      var1.method15(var0.method3().method5());
      var1.method3(var0, var1.ICRIHRIORRCRCOOCCCHHRIRICCHHII(), var1.RIIIOCHHCIHOIOROOOHRIRICCCCHHC(), var2);
      var3.pop();
   }

   @Annotation("metadata")
   @Generated
   @Override
   public GuiIterator method3() {
      return this.field2;
   }

   @Generated
   @Override
   public float getX() {
      return this.x;
   }

   @Generated
   @Override
   public float getY() {
      return this.y;
   }

   @Generated
   @Override
   public float getWidth() {
      return this.width;
   }

   @Generated
   @Override
   public float getHeight() {
      return this.height;
   }

   static {
      ClientEventBus.method29().method2(EventRenderHudLegacy.class, HudElementBase::method20);
      ClientEventBus.method29().method2(EventRenderHudLegacy.Data.class, HudElementBase::method20);
   }
}
