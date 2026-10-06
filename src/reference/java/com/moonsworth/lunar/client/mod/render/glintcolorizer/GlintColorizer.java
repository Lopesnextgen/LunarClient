package com.moonsworth.lunar.client.mod.render.glintcolorizer;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.DepthFunction;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.highlight.EventAlertUpdate;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.IntConsumer;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class GlintColorizer extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create(
      Ref.MC_VERSION >= 16 ? "textures/misc/enchanted_glint_item.png" : "textures/misc/enchanted_item_glint.png"
   );
   private static boolean field9;
   private static ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar:dynamic/alpha_glint_texture");
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showGlint")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("useLunarEquation")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("overrideItemGlint")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field14 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("itemGlintLunarColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-865854977))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("itemGlintVanillaColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8372020))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("overrideArmorGlint")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field17 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("armorGlintLunar")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-865854977))
      .method31();
   private final ColorOption field18 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("armorGlintVanilla")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8372020))
      .method31();
   private final ColorOption field19 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("glintColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8372020))
      .method31();

   public GlintColorizer() {
      super(false);
      this.method11(EventRenderItemGlint.class, this::method6, 115);
      if (Ref.MC_VERSION <= 5) {
         this.handle(EventAlertUpdate.class, arg0 -> field9 = false);
      }
   }

   public String getId() {
      return "GLINT_COLORIZER";
   }

   public boolean method13() {
      return (Boolean)this.field13.get();
   }

   public boolean method14() {
      return (Boolean)this.field12.get();
   }

   public boolean method3(EventRenderItemGlint highlightimpl31) {
      return this.method4(highlightimpl31);
   }

   public boolean method4(EventRenderItemGlint highlightimpl31) {
      return this.isEnabled() && (Boolean)this.field13.get() && (highlightimpl31.method2() == GlintTarget.GUI || highlightimpl31.method2() == GlintTarget.ITEM);
   }

   public boolean method5(EventRenderItemGlint highlightimpl31) {
      return this.isEnabled() && (Boolean)this.field16.get() && highlightimpl31.method2() == GlintTarget.EQUIPPED_ARMOR && highlightimpl31.method5() instanceof Bridge6_10;
   }

   public void method6(EventRenderItemGlint highlightimpl31) {
      if (!highlightimpl31.isCancelled()) {
         if (!(Boolean)this.field11.get()) {
            highlightimpl31.setCancelled(true);
         } else {
            AbstractRenderContext bridgeextension_92 = highlightimpl31.method7();
            if (!bridgeextension_92.method38()) {
               if (highlightimpl31.method3() != null) {
                  if (this.method4(highlightimpl31)) {
                     this.method10(bridgeextension_92, highlightimpl31.method3());
                     highlightimpl31.setCancelled(true);
                  } else if (this.method5(highlightimpl31)) {
                     this.method11(bridgeextension_92, () -> highlightimpl31.method3().accept(-1), highlightimpl31.method5().method2() + bridgeextension_92.method28());
                     highlightimpl31.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   public int method7(boolean flag1) {
      return flag1 ? this.field14.method14(0.0F) : this.field15.method14(0.0F);
   }

   public void method8(AbstractRenderContext bridgeextension_91, IntConsumer intconsumer2, int number3) {
      this.method12(bridgeextension_91, intconsumer2, number3);
   }

   public void method9(AbstractRenderContext bridgeextension_91, Runnable runnable2, float value3, int number4) {
      this.method13(bridgeextension_91, runnable2, value3, number4);
   }

   private void method10(AbstractRenderContext bridgeextension_91, IntConsumer intconsumer2) {
      if ((Boolean)this.field12.get() && method19() != null) {
         this.method12(bridgeextension_91, intconsumer2, this.field14.method14(0.0F));
      } else {
         this.method14(bridgeextension_91, intconsumer2, this.field15.method14(0.0F));
      }
   }

   private void method11(AbstractRenderContext bridgeextension_91, Runnable runnable2, float value3) {
      if ((Boolean)this.field12.get() && method19() != null) {
         this.method13(bridgeextension_91, runnable2, value3, this.field17.method14(0.0F));
      } else {
         this.method15(bridgeextension_91, runnable2, value3, this.field18.method14(0.0F));
      }
   }

   private void method12(AbstractRenderContext bridgeextension_91, IntConsumer intconsumer2, int number3) {
      this.mc.bridge$getTextureManager().bridge$bindTexture(method19());
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      float[] items4 = Color.RGBtoHSB(number3 >> 16 & 0xFF, number3 >> 8 & 0xFF, number3 & 0xFF, null);
      GL11.glPushMatrix();
      bridgeextension_91.method4(GlBlendFactor.GL_ZERO, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA, GlBlendFactor.GL_ZERO, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
      int number5 = (int)((1.0F - items4[2] * items4[2]) * 255.0F) * (number3 >> 24 & 0xFF) / 255;
      if (number5 > 10) {
         this.method17(bridgeextension_91, intconsumer2, number5 << 24);
      }

      if (Bridge.getMinecraftVersion() == Config.field1) {
         this.mc.bridge$getTextureManager().bridge$bindTexture(field8);
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE);
         this.method17(bridgeextension_91, intconsumer2, (number3 >> 24 & 0xFF) / 2 << 24 | number3 & 16777215);
      } else {
         bridgeextension_91.method4(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE);
         this.method17(bridgeextension_91, intconsumer2, number3);
      }

      GL11.glPopMatrix();
   }

   private void method13(AbstractRenderContext bridgeextension_91, Runnable runnable2, float value3, int number4) {
      this.mc.bridge$getTextureManager().bridge$bindTexture(method19());
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      float[] items5 = Color.RGBtoHSB(number4 >> 16 & 0xFF, number4 >> 8 & 0xFF, number4 & 0xFF, null);
      GL11.glPushMatrix();
      bridgeextension_91.method4(GlBlendFactor.GL_ZERO, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA, GlBlendFactor.GL_ZERO, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
      int number6 = (int)((1.0F - items5[2] * items5[2]) * 255.0F) * (number4 >> 24 & 0xFF) / 255;
      if (number6 > 10) {
         this.method16(bridgeextension_91, runnable2, value3, number6 << 24);
      }

      if (Bridge.getMinecraftVersion() == Config.field1) {
         this.mc.bridge$getTextureManager().bridge$bindTexture(field8);
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE);
         this.method16(bridgeextension_91, runnable2, value3, (number4 >> 24 & 0xFF) / 2 << 24 | number4 & 16777215);
      } else {
         bridgeextension_91.method4(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE);
         this.method16(bridgeextension_91, runnable2, value3, number4);
      }

      GL11.glPopMatrix();
   }

   private void method14(AbstractRenderContext bridgeextension_91, IntConsumer intconsumer2, int number3) {
      this.mc.bridge$getTextureManager().bridge$bindTexture(field8);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      if (Bridge.getMinecraftVersion() == Config.field1) {
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE);
         this.method17(bridgeextension_91, intconsumer2, (number3 >> 24 & 0xFF) / 2 << 24 | number3 & 16777215);
      } else {
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_COLOR, GlBlendFactor.GL_ONE);
         this.method17(bridgeextension_91, intconsumer2, number3);
      }
   }

   private void method15(AbstractRenderContext bridgeextension_91, Runnable runnable2, float value3, int number4) {
      this.mc.bridge$getTextureManager().bridge$bindTexture(field8);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      if (Bridge.getMinecraftVersion() == Config.field1) {
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE);
         this.method16(bridgeextension_91, runnable2, value3, (number4 >> 24 & 0xFF) / 2 << 24 | number4 & 16777215);
      } else {
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_COLOR, GlBlendFactor.GL_ONE);
         this.method16(bridgeextension_91, runnable2, value3, number4);
      }
   }

   private void method16(AbstractRenderContext bridgeextension_91, Runnable runnable2, float value3, int number4) {
      bridgeextension_91.method14();
      bridgeextension_91.method7(DepthFunction.GL_EQUAL);
      bridgeextension_91.method6(false);

      for (int index5 = 0; index5 < 2; index5++) {
         bridgeextension_91.method11();
         float value6 = 0.76F;
         int number7 = number4;
         float value8 = (number7 >> 24 & 0xFF) / 255.0F;
         float value9 = (number7 >> 16 & 0xFF) / 255.0F;
         float value10 = (number7 >> 8 & 0xFF) / 255.0F;
         float value11 = (number7 & 0xFF) / 255.0F;
         bridgeextension_91.method25(value9 * value6, value10 * value6, value11 * value6, value8);
         bridgeextension_91.method8(GlMatrixMode.GL_TEXTURE);
         bridgeextension_91.method36();
         float value12 = 0.33333334F;
         bridgeextension_91.scale(value12, value12, value12);
         bridgeextension_91.method4(30.0F - index5 * 60.0F, 0.0F, 0.0F, 1.0F);
         bridgeextension_91.translate(0.0, value3 * (0.001F + index5 * 0.003F) * 20.0F, 0.0);
         bridgeextension_91.method8(GlMatrixMode.GL_MODELVIEW);
         runnable2.run();
         bridgeextension_91.method33();
      }

      bridgeextension_91.method8(GlMatrixMode.GL_TEXTURE);
      bridgeextension_91.method36();
      bridgeextension_91.method8(GlMatrixMode.GL_MODELVIEW);
      bridgeextension_91.method10();
      bridgeextension_91.method6(true);
      bridgeextension_91.method7(DepthFunction.GL_LEQUAL);
      bridgeextension_91.method15();
   }

   private void method17(AbstractRenderContext bridgeextension_91, IntConsumer intconsumer2, int number3) {
      bridgeextension_91.method18();
      bridgeextension_91.method14();
      bridgeextension_91.method6(false);
      bridgeextension_91.method7(DepthFunction.GL_EQUAL);
      bridgeextension_91.method11();
      bridgeextension_91.method8(GlMatrixMode.GL_TEXTURE);
      bridgeextension_91.push();
      float value4 = Bridge.getMinecraftVersion() == Config.field1 ? 0.125F : 8.0F;
      bridgeextension_91.scale(value4, value4, value4);
      float value5 = (float)(this.mc.bridge$getSystemTime() % 3000L) / 3000.0F / value4;
      bridgeextension_91.translate(value5, 0.0, 0.0);
      bridgeextension_91.method4(-50.0F, 0.0F, 0.0F, 1.0F);
      intconsumer2.accept(number3);
      bridgeextension_91.pop();
      bridgeextension_91.push();
      bridgeextension_91.scale(value4, value4, value4);
      float value6 = (float)(this.mc.bridge$getSystemTime() % 4873L) / 4873.0F / value4;
      bridgeextension_91.translate(-value6, 0.0, 0.0);
      bridgeextension_91.method4(10.0F, 0.0F, 0.0F, 1.0F);
      intconsumer2.accept(number3);
      bridgeextension_91.pop();
      bridgeextension_91.method8(GlMatrixMode.GL_MODELVIEW);
      bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
      bridgeextension_91.method10();
      bridgeextension_91.method7(DepthFunction.GL_LEQUAL);
      bridgeextension_91.method6(true);
   }

   public static ResourceLocationBridge method19() {
      if (!field9) {
         field9 = true;
         ResourceBridge bridge150 = Ref.method3().bridge$getResourceManager().bridge$getResource(field8);
         if (bridge150 == null) {
            LunarLogger.method5("Couldn't find item glint resource: " + field8, new Object[0]);
            field10 = null;
            return null;
         }

         BufferedImage bufferedimage1;
         try {
            bufferedimage1 = ImageIO.read(bridge150.bridge$getInputStream());
         } catch (Exception exception8) {
            exception8.printStackTrace();
            field10 = null;
            return null;
         }

         BufferedImage bufferedimage2 = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), 2);

         for (int index3 = 0; index3 < bufferedimage1.getWidth(); index3++) {
            for (int index4 = 0; index4 < bufferedimage1.getHeight(); index4++) {
               int number5 = bufferedimage1.getRGB(index3, index4);
               int number6 = ((number5 >> 16 & 0xFF) + (number5 >> 8 & 0xFF) + (number5 & 0xFF)) / 3 & 0xFF;
               int number7;
               if (Bridge.getMinecraftVersion() == Config.field1) {
                  number7 = number6 << 24 | 16777215;
               } else {
                  number7 = number6 << 24 | number6 << 16 | number6 << 8 | number6;
               }

               bufferedimage2.setRGB(index3, index4, number7);
            }
         }

         Ref.method3().bridge$getTextureManager().bridge$loadTexture(field10, Bridge.method8().method22(bufferedimage2));
      }

      return field10;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field11});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field12}))
         .method2(() -> !(Boolean)this.field11.get())
         .OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
      ((SettingsSectionImpl)lightingextension231.method1("glintColourOptions", arg1x -> {
         arg1x.method7(this.field13, arg1xx -> {
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14}).method3(() -> !(Boolean)this.field12.get());
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}).method3(this.field12::get);
         }).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
         arg1x.method7(this.field16, arg1xx -> {
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17}).method3(() -> !(Boolean)this.field12.get());
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18}).method3(this.field12::get);
         }).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field19}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
      })).method2(() -> !(Boolean)this.field11.get());
   }

   protected ModDetails method20() {
      return ModDetails.method7().method3(new String[]{"Powns"}).method11(this);
   }

   @Generated
   public ColorOption method21() {
      return this.field19;
   }
}
