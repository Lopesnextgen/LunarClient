package com.moonsworth.lunar.client.mod.hud;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.Horsestats30;
import com.moonsworth.lunar.client.click.Nameplate;
import com.moonsworth.lunar.client.click.holograms.HologramsImpl3;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.Framework8;
import com.moonsworth.lunar.client.framework.Gui2Extension2;
import com.moonsworth.lunar.client.framework.MixinCore9;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display2;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display_3;
import com.moonsworth.lunar.client.framework.feature.f3display.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display.Data3;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display.Data4;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension4912;
import com.moonsworth.lunar.client.lighting.LightingExtension4915;
import com.moonsworth.lunar.client.lighting.LightingExtension497;
import com.moonsworth.lunar.client.lighting.Lighting_7;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.lighting.nameplate.ThreadModuleDump43Extension22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class F3ModuleChildMod extends Framework7Extension2 {
   private final F3ModuleChildMod.Data field8 = new F3ModuleChildMod.Data(0.0F, 0.0F, Gui2Extension2.TOP_LEFT);
   protected final LightingExtension4915 field9 = (LightingExtension4915)((com.moonsworth.lunar.client.lighting.LightingExtension4915.Data)Lighting.method12(
            "name"
         )
         .HIIIOHRRROCICIOIORRRIRCRCHHIII("New Module"))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field10 = (LightingExtension443)Lighting.method7("alwaysShow").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension4912 field11 = (LightingExtension4912)Lighting.method27("elements").method3(Gui2Extension.ids()).method6(var0 -> {
      Gui2Extension var1x = F3Display.field14.get(var0);
      return var1x == null ? var0 : var1x.getDisplay();
   }).method5(var1x -> this.field8.method20()).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field12 = (LightingExtension443)((Data2)Lighting.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension497<com.moonsworth.lunar.client.hitbox.Gui2Extension> field13 = (LightingExtension497<com.moonsworth.lunar.client.hitbox.Gui2Extension>)Lighting.method10(
         "alignment", com.moonsworth.lunar.client.hitbox.Gui2Extension.LEFT
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field14 = (LightingExtension443)((Data2)Lighting.method7("overrideDisplayOptions").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field15 = (LightingExtension443)Lighting.method7("showClientBrand").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field16 = (LightingExtension443)((Data2)Lighting.method7("showWorldName").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field17 = (LightingExtension443)Lighting.method7("showRegionFile").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field18 = (LightingExtension443)((Data2)Lighting.method7("showProperties").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field19 = (LightingExtension443)Lighting.method7("showTags").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field20 = (LightingExtension443)((Data2)Lighting.method7("showProperties").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field21 = (LightingExtension443)Lighting.method7("showTags").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field22 = (LightingExtension443)Lighting.method7("showEmpty").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field23 = (LightingExtension443)((Data2)Lighting.method7("showSimulationDistance").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field24 = (LightingExtension443)Lighting.method7("showMood").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field25 = (LightingExtension443)Lighting.method7("showAllocationRate").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field26 = (LightingExtension443)((Data2)Lighting.method7("showGlobalPercent").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field27 = (LightingExtension443)Lighting.method7("useCustomColor").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field28 = (LightingExtension443)((Data2)Lighting.method7("alignToMaxRam").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field29 = (LightingExtension443)((Data2)Lighting.method7("showLunarResources").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field30 = (LightingExtension443)((Data2)Lighting.method7("showLunarTurbo").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final LightingExtension443 field31 = (LightingExtension443)((Data2)Lighting.method7("showLunarJIT").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final F3Display.Data field32 = new F3Display.Data();
   private final F3Display.Data field33;

   protected F3ModuleChildMod(F3Display var1) {
      super(true);
      this.field33 = var1.field18;
      this.method6(Framework.field16, Framework4.method5(null, var1));
      this.method6(Framework.field1, this.field8);
   }

   public F3Display.Data method13() {
      return this.field14.get() ? this.field32 : this.field33;
   }

   public void method2(LightingExtension23 var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field9, this.field12, this.field10});
      ((ThreadModuleDump43Extension22)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field13})).method2(this.field12::get);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, this.field32::method1);
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new Lighting_7[]{this.field11, Lighting.method14("remove").method4(() -> {
         F3Display var1x = (F3Display)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var1x.method4(this);
      })});
      this.field13.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.field8.method22());
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.field8.method22());
      this.method3(Gui2Extension.MINECRAFT_VERSION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field15}));
      this.method3(Gui2Extension.POSITION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field16}));
      this.method3(Gui2Extension.CHUNK_POSITION, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17}));
      this.method3(Gui2Extension.TARGET_BLOCK, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field18, this.field19}));
      this.method3(
         Gui2Extension.TARGET_FLUID, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field20, this.field21, this.field22})
      );
      this.method3(Gui2Extension.RENDER_DISTANCE, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field23}));
      this.method3(Gui2Extension.SOUNDS, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field24}));
      this.method3(Gui2Extension.MEMORY_USAGE, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field25}));
      this.method3(Gui2Extension.PIE_CHART, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field26, this.field27}));
      this.method3(Gui2Extension.RAM_CHART, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field28}));
      this.method3(
         Gui2Extension.LUNAR_DEBUG, var1, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field29, this.field30, this.field31})
      );
   }

   private void method3(Gui2Extension var1, LightingExtension23 var2, Consumer<com.moonsworth.lunar.client.lighting.LightingExtension23.Data> var3) {
      ((ThreadModuleDump43Extension22)var2.HORHROIOIOICIRHIOCOICHHHIHCIIO(var1::getDisplay, var3)).method2(() -> !this.field8.field10.contains(var1));
   }

   public String getId() {
      throw new IllegalStateException("F3ModuleChildMod must be created using F3ModuleChildMod.create()!");
   }

   protected Framework8 method20() {
      return Framework8.method7().method4(this.field9::get).method8().method11(this);
   }

   public void load(JsonObject var1) {
      super.load(var1);
      this.field8.method20();
      this.field8.method22();
   }

   public void method3(boolean var1) {
      if (!var1) {
         this.field8.field14 = false;
      }
   }

   public static F3ModuleChildMod method6(F3Display var0) {
      String var1 = "F3_MODULE_" + System.currentTimeMillis();
      F3ModuleChildMod var2 = method7(var0, var1);
      float var3 = new Random(var1.hashCode()).nextFloat(1.0F);
      Color var4 = Color.getHSBColor(var3, 0.8F, 0.85F);
      Color var5 = Color.getHSBColor(var3 + 0.03F, 0.5F, 1.0F);
      var2.field32.field4.method1(var4.getRGB());
      var2.field32.field5.method1(var5.getRGB());
      return var2;
   }

   public static F3ModuleChildMod method7(F3Display var0, final String var1) {
      return new F3ModuleChildMod(var0) {
         @Override
         public String getId() {
            return var1;
         }
      };
   }

   public boolean method8(Gui2Extension var1) {
      return this.field8.field10.contains(var1) && this.field8.field13;
   }

   @Generated
   public F3ModuleChildMod.Data method14() {
      return this.field8;
   }

   @Generated
   public LightingExtension443 method15() {
      return this.field18;
   }

   @Generated
   public LightingExtension443 method16() {
      return this.field19;
   }

   @Generated
   public LightingExtension443 method17() {
      return this.field20;
   }

   @Generated
   public LightingExtension443 method19() {
      return this.field21;
   }

   @Generated
   public LightingExtension443 method21() {
      return this.field22;
   }

   @Generated
   public LightingExtension443 method22() {
      return this.field26;
   }

   public class Data extends MixinCore9 {
      private com.moonsworth.lunar.client.hitbox.Gui2Extension field9 = com.moonsworth.lunar.client.hitbox.Gui2Extension.LEFT;
      private Set<Gui2Extension> field10 = EnumSet.noneOf(Gui2Extension.class);
      private final HologramsImpl3 field11 = new HologramsImpl3(0L);
      private float field12 = 0.0F;
      private boolean field13 = false;
      private boolean field14 = false;
      private boolean field15 = false;
      private F3display2 field16;
      private boolean dirty = false;
      private float field17 = 1.0F;
      private double field18 = 0.0;
      private double field19 = 0.0;

      public Data(float var2, float var3, @NotNull Gui2Extension2 var4) {
         super(var2, var3, var4);
      }

      public void method27(@NotNull Gui2Extension2 var1) {
         super.method27(var1);
         this.method22();
      }

      public void method18() {
      }

      public void method3(HighlightImpl var1, float var2, float var3, boolean var4) {
      }

      public boolean method4(HighlightImpl var1) {
         if (!this.field14) {
            return false;
         }

         this.field17 = this.getScale();
         this.field18 = var1.method3().method10();
         this.field19 = var1.method3().method11();
         this.method27(var1.method3().method5());
         float var2 = this.ICRIHRIORRCRCOOCCCHHRIRICCHHII();
         float var3 = this.RIIIOCHHCIHOIOROOOHRIRICCCCHHC();
         float var4 = var2;
         float var5 = var3;
         if (this.field11.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            double var6 = this.field11.HRIOICORHOROOICOOHIOIOCOHRROHR();
            Gui2Extension2 var8 = this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH();
            if (var8.getHorizontal() == com.moonsworth.lunar.client.framework.Gui2Extension.MIDDLE) {
               if (var8.getVertical() == com.moonsworth.lunar.client.framework.Gui2Extension.TOP) {
                  var5 = -this.field12;
               } else {
                  var5 = (float)this.field19 / this.getScale();
               }

               var5 = (float)Horsestats30.lerp(var6, var5, var3);
            } else {
               if (var8.getHorizontal() == com.moonsworth.lunar.client.framework.Gui2Extension.LEFT) {
                  var4 = -this.field12;
               } else {
                  var4 = (float)this.field18 / this.getScale();
               }

               var4 = (float)Horsestats30.lerp(var6, var4, var2);
            }
         } else if (!this.method14(this.field13, this.field15)) {
            return false;
         }

         MixinHelper_4 var11 = var1.method2();
         this.field16 = new F3display2(var11, F3ModuleChildMod.this, var4, var5, this.field15);
         return true;
      }

      public void method5() {
         if (this.field16 != null) {
            Data3 var1 = F3display_3.method10(this.field16);
            this.method17();

            try {
               this.method9(var1);
            } finally {
               this.pop();
            }
         }
      }

      public void method15() {
         if (this.field16 != null) {
            this.field16.method11();
            com.moonsworth.lunar.client.framework.feature.f3display.F3display.Data2 var1 = F3display_3.method8(this.field16);
            this.method17();

            try {
               this.method9(var1);
            } finally {
               this.pop();
            }
         }
      }

      public void method16() {
         if (this.field16 != null) {
            this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(this.field16.method10(), this.field16.method9());
         }

         this.field16 = null;
      }

      private void method17() {
         MixinHelper_4 var1 = this.field16.method13();
         var1.push();
         var1.scale(this.field17, this.field17, 1.0F);
      }

      private void pop() {
         this.field16.method13().pop();
      }

      private void method9(F3display_3 var1) {
         boolean var2 = var1.method7();
         boolean var3 = Nameplate.method1();

         for (Gui2Extension var5 : this.field10) {
            if (!var5.isAllowed(var3, var2)) {
               if (var2) {
                  var1.method2(var5.getDisplay() + ": ", "Reduced");
               }
            } else {
               BiConsumer var6 = F3Display.field13.get(var5);
               if (var6 != null) {
                  var6.accept(var1, F3ModuleChildMod.this);
               }
            }
         }
      }

      protected void method10(boolean var1) {
         Data4 var2 = F3display_3.method9(var1);

         for (Gui2Extension var4 : this.field10) {
            BiConsumer var5 = F3Display.field13.get(var4);
            if (var5 != null) {
               var5.accept(var2, F3ModuleChildMod.this);
            }
         }

         this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(var2.getWidth(), var2.getHeight());
      }

      public boolean method31() {
         return false;
      }

      public boolean method30() {
         boolean var1 = (Boolean)ThreadModuleDump63.method4().method40().method95().field17.get();
         if (!var1) {
            return true;
         } else {
            return F3ModuleChildMod.this.field10.get() ? true : ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo();
         }
      }

      public boolean method4(boolean var1) {
         boolean var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() || (Boolean)F3ModuleChildMod.this.field10.get();
         if (this.field13 != var2) {
            this.field13 = var2;
            this.dirty = true;
            this.field11.stop();
            F3Display var3 = ThreadModuleDump63.method4().method40().method95();
            double var4 = this.field13 ? (Double)var3.field15.get() : (Double)var3.field16.get();
            if (var4 > 0.0) {
               this.field11.setDurationMs((long)(var4 * 1000.0));
               this.field11.ICRIOHCOCRHIHHHOOIIHHOIRRIHIHC(!this.field13);
               this.field11.start();
               this.method10(var1);
               if (this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().getHorizontal() == com.moonsworth.lunar.client.framework.Gui2Extension.MIDDLE) {
                  this.field12 = this.getHeight();
               } else {
                  this.field12 = this.getWidth();
               }
            }
         }

         this.field16 = null;
         this.field14 = this.method14(var2, var1);
         this.field15 = var1;
         return false;
      }

      private boolean method14(boolean var1, boolean var2) {
         if (var1 || this.field11.CRCCOHOHCCRHRIHCROCCRICIRRICRH()) {
            return true;
         } else if (var2) {
            boolean var3 = (Boolean)ThreadModuleDump63.method4().method40().method95().field17.get();
            return !var3;
         } else {
            return false;
         }
      }

      public boolean method19() {
         boolean var1 = this.dirty;
         this.dirty = false;
         return var1;
      }

      public void method20() {
         EnumSet var1 = EnumSet.noneOf(Gui2Extension.class);
         var1.addAll(((Set)F3ModuleChildMod.this.field11.get()).stream().<Gui2Extension>map(Gui2Extension::getFromId).filter(Objects::nonNull).toList());
         this.field10 = var1;
         ThreadModuleDump63.method4().method40().method95().method14();
      }

      public void method22() {
         if ((Boolean)F3ModuleChildMod.this.field12.get()) {
            com.moonsworth.lunar.client.framework.Gui2Extension var1 = this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().getHorizontal();
            if (var1 == com.moonsworth.lunar.client.framework.Gui2Extension.RIGHT) {
               this.field9 = com.moonsworth.lunar.client.hitbox.Gui2Extension.RIGHT;
            } else if (var1 == com.moonsworth.lunar.client.framework.Gui2Extension.MIDDLE) {
               this.field9 = com.moonsworth.lunar.client.hitbox.Gui2Extension.CENTER;
            } else {
               this.field9 = com.moonsworth.lunar.client.hitbox.Gui2Extension.LEFT;
            }
         } else {
            this.field9 = (com.moonsworth.lunar.client.hitbox.Gui2Extension)F3ModuleChildMod.this.field13.get();
         }
      }

      @Generated
      public com.moonsworth.lunar.client.hitbox.Gui2Extension method23() {
         return this.field9;
      }
   }
}
