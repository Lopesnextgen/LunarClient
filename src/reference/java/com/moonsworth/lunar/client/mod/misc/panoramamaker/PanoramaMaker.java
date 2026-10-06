package com.moonsworth.lunar.client.mod.misc.panoramamaker;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotCapture;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Generated;

public class PanoramaMaker extends AbstractFeature {
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("makePanorama").method18(this))
         .method2(KeyBind.method2(KeyCode.KEY_P)))
      .method31();
   private final ModifierKeybindOption field9 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("screenshotMainMenu")
            .method5(KeyCode.KEY_MINUS)
            .method18(this))
         .method11()
         .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
      .method31();
   private static final DateFormat field10 = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private String field11;
   public static boolean field12;
   private long field13;

   public PanoramaMaker(boolean flag1) {
      super(flag1);
      this.method9(ModTraits.field18, arg0 -> arg0.method11(Config.field9));
      this.handle(EventTick.class, this::method1);
   }

   public String getId() {
      return "PANORAMA_MAKER";
   }

   private void method1(EventTick highlightimpl21) {
      if (Ref.method14() - this.field13 > 50L && field12) {
         this.field11 = field10.format(new Date());
         File file2 = new File(this.mc.bridge$getMcDataDir() + File.separator + "screenshots" + File.separator);
         ScreenshotCapture screenshot23 = new ScreenshotCapture();
         screenshot23.method4(
            null,
            new File(file2 + File.separator + this.field11 + ".png"),
            this.mc.bridge$displayWidth(),
            this.mc.bridge$displayHeight(),
            this.mc.bridge$getMainRenderTarget()
         );
         Bridge.method8().method64(file2.toURI());
         field12 = false;
      }
   }

   private File method13() {
      File file1 = new File(this.mc.bridge$getMcDataDir() + File.separator + "panoramas" + File.separator);
      if (!file1.exists()) {
         file1.mkdir();
      }

      File file2 = new File(file1, this.field11);
      if (!file2.exists()) {
         file2.mkdir();
      }

      return file2;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9});
      this.field8.method5(arg1x -> {
         if (Ref.method7() != null && Ref.method8() != null) {
            this.field11 = field10.format(new Date());
            if (Ref.MC_VERSION >= 8) {
               Ref.method3().bridge$makePanorama(this.method13(), 512, 512);
            }

            Bridge.method8().method64(this.method13().toURI());
         } else {
            Ref.method4().method69().method3("You must be in a game to take a panorama!");
         }
      });
      this.field9.method5(arg1x -> {
         field12 = true;
         this.field13 = Ref.method14();
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   @Generated
   public ModifierKeybindOption method14() {
      return this.field8;
   }

   @Generated
   public ModifierKeybindOption method15() {
      return this.field9;
   }
}
