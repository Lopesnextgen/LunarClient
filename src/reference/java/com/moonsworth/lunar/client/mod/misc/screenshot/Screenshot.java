package com.moonsworth.lunar.client.mod.misc.screenshot;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotCapture;
import com.moonsworth.lunar.client.framework.feature.screenshot.ScreenshotShareThread;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.EventCommand.CommandInput;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.Generated;

public class Screenshot extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("copyAutomatically").method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("uploadOption").method4(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("tweetOption").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("copyOption").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("deleteOption").method4(true))
      .method31();
   private final String field13 = "/lc_upload_screenshot";
   private final EnumOption<Screenshot.Type> field14 = (EnumOption<Screenshot.Type>)OptionFactory.method10(
         "screenshotPrivacy", Screenshot.Type.UNLISTED
      )
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("worldDetails").method4(true))
      .method31();
   private final ScreenshotCapture field16 = new ScreenshotCapture();
   private static final DateFormat field17 = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

   public Screenshot() {
      super(true);
      this.handle(EventKeybind.class, this::method4);
      this.handle(EventMouseButton.class, this::method5);
      this.handle(EventCommand.class, this::method2);
      this.handle(CommandInput.class, this::method1);
   }

   public String getId() {
      return "SCREENSHOT";
   }

   private void method1(CommandInput data1) {
      if (data1.getCommand().startsWith("/lc_upload_screenshot")) {
         data1.method2();
      }
   }

   private void method2(EventCommand highlightimpl41) {
      if (highlightimpl41.getCommand().startsWith("/lc_upload_screenshot")) {
         highlightimpl41.cancel();
         boolean flag2 = highlightimpl41.method1("/lc_upload_screenshot" + "_tweet");
         boolean flag3 = highlightimpl41.method1("/lc_upload_screenshot" + "_copy");
         boolean flag4 = highlightimpl41.method1("/lc_upload_screenshot" + "_delete");
         String text5 = highlightimpl41.get(0);
         String text6 = highlightimpl41.get(1);
         if (text5.isEmpty()) {
            return;
         }

         UUID uuid7 = !text6.isEmpty() ? UUID.fromString(text6) : null;
         Path path8 = Path.of(this.mc.bridge$getMcDataDir().getAbsolutePath(), "screenshots").normalize();
         Path path9 = path8.resolve(text5).normalize();
         if (path9.equals(path8) || !path9.startsWith(path8)) {
            return;
         }

         if (Files.exists(path9)) {
            if (flag4) {
               this.method3(path9);
            } else if (flag3) {
               new ScreenshotShareThread(path9, uuid7, flag2, flag3, "", null, null, null).start();
            } else {
               DriverViewportLegacy.method50()
                  .method17(
                     DriverRouteRegistry.field19,
                     new com.moonsworth.lunar.client.driver.bridge.ScreenshotUploadBridge.Data(
                        Ref.method7() == null ? text5 : Ref.method7().bridge$getName() + "'s screenshot",
                        text5,
                        path9.toString(),
                        text6,
                        flag2,
                        ((Screenshot.Type)this.field14.get()).id(),
                        (Boolean)this.field15.get()
                     )
                  );
            }
         }
      }
   }

   private void method3(Path path1) {
      boolean flag2;
      try {
         Files.delete(path1);
         flag2 = true;
      } catch (IOException exception4) {
         LunarLogger.warn("Couldn't delete screenshot", exception4);
         flag2 = false;
      }

      String text3 = this.method1(flag2 ? "deletedScreenshot" : "deleteFailed", new Object[0]);
      this.mc.bridge$submit(() -> this.mc.bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(Bridge.method8().method13(text3)));
   }

   private void method4(EventKeybind highlightimpl1) {
      if (highlightimpl1.method11() == InputAction.DOWN) {
         this.method6(highlightimpl1, highlightimpl1.method10(), highlightimpl1.method12());
      }
   }

   private void method5(EventMouseButton highlightimpl31) {
      if (highlightimpl31.method2() >= 0) {
         if (highlightimpl31.method4() == InputAction.DOWN) {
            this.method6(highlightimpl31, KeyCode.fromMouse(highlightimpl31.method2()), false);
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.CancellableEvent highlightimpl1, KeyCode bridgetype_82, boolean flag3) {
      if (!Client.method109().method40().method64().method13()
         && !Ref.method4().method40().method85().method19()
         && bridgetype_82 == this.mc.bridge$getGameSettings().bridge$getScreenshotKey()) {
         highlightimpl1.cancel();
         if (!flag3) {
            this.field16.method2(this, this.mc.bridge$getMcDataDir());
            LunarLogger.method1("Screenshot taken", new Object[0]);
         }
      }
   }

   public static File method7(File file0) {
      String text1 = field17.format(new Date()).toString();
      int index2 = 1;

      while (true) {
         File file3 = new File(file0, text1 + (index2 == 1 ? "" : "_" + index2) + ".png");
         if (!file3.exists()) {
            return file3;
         }

         index2++;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1("screenshotOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
      lightingextension231.method1(
         "screenshotChatOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field9, this.field10, this.field11, this.field12})
      );
      lightingextension231.method1(
         "screenshotUploadOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field14, this.field15})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   public boolean method7() {
      return false;
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }

   @Generated
   public ToggleOption method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field10;
   }

   @Generated
   public ToggleOption method16() {
      return this.field11;
   }

   @Generated
   public ToggleOption method17() {
      return this.field12;
   }

   @Generated
   public String method19() {
      return "/lc_upload_screenshot";
   }

   @Generated
   public EnumOption<Screenshot.Type> method21() {
      return this.field14;
   }

   @Generated
   public ToggleOption method22() {
      return this.field15;
   }

   public enum Type implements OptionEnumValue, Translatable {
      PUBLIC("public", "privacyPublic"),
      UNLISTED("unlisted", "privacyUnlisted"),
      PRIVATE("private", "privacyPrivate");

      private final String id;
      private final String description;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      public String description() {
         return this.method51(this.description, new Object[0]);
      }

      public String getLanguagePath() {
         return "settings";
      }

      @Generated
      Type(String text3, String text4) {
         this.id = text3;
         this.description = text4;
      }
   }
}
