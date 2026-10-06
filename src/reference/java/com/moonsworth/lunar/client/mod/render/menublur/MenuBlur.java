package com.moonsworth.lunar.client.mod.render.menublur;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.render.shader.LunarPostEffect;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.menublur.MenuBlurShader;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventPostProcess;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;

public class MenuBlur extends AbstractFeature {
   private boolean animationStarted = true;
   private final HoverAnimation blurAnimation = new HoverAnimation(125L);
   private final IntegerOption blurStrength = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "blurStrength"
            )
            .method4(4))
         .method7(0, 20))
      .method31();
   private final MenuBlur.Data lunarScreen = new MenuBlur.Data("Lunar", 0);
   private final MenuBlur.Data inventoryScreen = new MenuBlur.Data("Inventory", 1862270976);
   private final MenuBlur.Data pauseScreen = new MenuBlur.Data("Pause", 1862270976);
   private LunarPostEffect shader = null;

   public MenuBlur() {
      super(true);
      this.handle(EventScreenChange.class, arg1 -> {
         if (arg1.method1() == null) {
            this.stopBlurAnimation();
         }
      });
      this.handle(EventPostProcess.class, arg1 -> {
         if (this.blurAnimation.isActive()) {
            if (this.shader == null) {
               this.shader = Ref.method4().method99().method2("menu_blur", new MenuBlurShader());
            }

            arg1.method1();
            Bridge3_24 bridge3_242 = arg1.method3();
            float value3 = 1.0F / bridge3_242.bridge$framebufferWidth();
            float value4 = 1.0F / bridge3_242.bridge$framebufferHeight();
            float value5 = ((Integer)this.blurStrength.get()).intValue();
            Bridge3_24 bridge3_246 = this.shader.method1(bridge3_242);

            for (int index7 = 0; index7 < 2; index7++) {
               this.shader.method3(arg1.method2(), bridge3_242, bridge3_246, arg4x -> {
                  arg4x.bridge$getShaderUniform("Progress").bridge$set(this.blurAnimation.method1());
                  arg4x.bridge$getShaderUniform("Radius").bridge$set(value5);
                  arg4x.bridge$getShaderUniform("BlurDir").bridge$set(1.0F, 0.0F);
                  arg4x.bridge$getShaderUniform("OneTexel").bridge$set(value3, value4);
               });
               this.shader.method3(arg1.method2(), bridge3_246, bridge3_242, arg4x -> {
                  arg4x.bridge$getShaderUniform("Progress").bridge$set(this.blurAnimation.method1());
                  arg4x.bridge$getShaderUniform("Radius").bridge$set(value5);
                  arg4x.bridge$getShaderUniform("BlurDir").bridge$set(0.0F, 1.0F);
                  arg4x.bridge$getShaderUniform("OneTexel").bridge$set(value3, value4);
               });
            }
         }
      });
   }

   public String getId() {
      return "MENU_BLUR";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{
            this.blurStrength, this.lunarScreen.field1, this.lunarScreen.field2, this.inventoryScreen.field1, this.inventoryScreen.field2, this.pauseScreen.field1, this.pauseScreen.field2
         }
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method3(new String[]{"tterrag1098"}).method11(this);
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.stopBlurAnimation();
         if (this.shader != null) {
            this.shader.delete();
            this.shader = null;
         }
      }
   }

   public void startBlurAnimation() {
      if (this.animationStarted) {
         this.blurAnimation.start();
      }

      this.animationStarted = false;
   }

   public void ensureBlurStarted() {
      if (this.animationStarted) {
         this.startBlurAnimation();
      }
   }

   public void stopBlurAnimation() {
      this.blurAnimation.stop();
      this.animationStarted = true;
   }

   public int applyFade(int number1) {
      int number2 = number1 >>> 24;
      int number3 = number1 >> 16 & 0xFF;
      int number4 = number1 >> 8 & 0xFF;
      int number5 = number1 & 0xFF;
      float value6;
      if (this.blurAnimation.method7()) {
         value6 = Math.max(Math.min(this.blurAnimation.method1(), 0.75F), 0.0F);
      } else {
         value6 = 0.75F;
      }

      number2 = (int)(number2 * value6);
      number3 = (int)(number3 * value6);
      number5 = (int)(number5 * value6);
      number4 = (int)(number4 * value6);
      return number2 << 24 | number3 << 16 | number4 << 8 | number5;
   }

   public Optional<Integer> getScreenBackgroundColor(GuiScreenBridge bridge5extension61) {
      if (this.lunarScreen.method2(bridge5extension61)) {
         return Optional.of(this.lunarScreen.method1());
      } else if (this.inventoryScreen.method2(bridge5extension61)) {
         return Optional.of(this.inventoryScreen.method1());
      } else {
         return this.pauseScreen.method2(bridge5extension61) ? Optional.of(this.pauseScreen.method1()) : Optional.empty();
      }
   }

   public boolean shouldBlurScreen(GuiScreenBridge bridge5extension61) {
      return this.lunarScreen.method2(bridge5extension61) || this.inventoryScreen.method2(bridge5extension61) || this.pauseScreen.method2(bridge5extension61);
   }

   @Generated
   public boolean isAnimationStarted() {
      return this.animationStarted;
   }

   @Generated
   public HoverAnimation method17() {
      return this.blurAnimation;
   }

   @Generated
   public IntegerOption getBlurStrength() {
      return this.blurStrength;
   }

   @Generated
   public MenuBlur.Data getLunarScreenData() {
      return this.lunarScreen;
   }

   @Generated
   public MenuBlur.Data getInventoryScreenData() {
      return this.inventoryScreen;
   }

   @Generated
   public MenuBlur.Data getPauseScreenData() {
      return this.pauseScreen;
   }

   @Generated
   public LunarPostEffect getShader() {
      return this.shader;
   }

   @Generated
   public void method17(boolean flag1) {
      this.animationStarted = flag1;
   }

   public class Data {
      private final ToggleOption field1;
      private final ColorOption field2;
      private final Set<Class<?>> field3 = new HashSet<>();

      public Data(String text2, int number3) {
         this.field1 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blur" + text2 + "Toggle")
               .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
            .method31();
         if ("lunar".equals(text2)) {
            this.field1.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
               if (!arg1x) {
                  MenuBlur.this.stopBlurAnimation();
               }
            });
         }

         this.field2 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
                     "blur" + text2 + "Background"
                  )
                  .method4(number3))
               .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !(Boolean)this.field1.get()))
            .method31();
      }

      public int method1() {
         float value1 = this.field2.getAlpha() / 255.0F;
         if (value1 > 0.75F) {
            this.field2.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(0.75F);
         }

         return MenuBlur.this.applyFade(this.field2.method14(0.0F));
      }

      public boolean method2(GuiScreenBridge bridge5extension61) {
         if (MenuBlur.this.isEnabled() && (Boolean)this.field1.get()) {
            for (Class clazz2 = bridge5extension61.getClass(); clazz2 != Object.class; clazz2 = clazz2.getSuperclass()) {
               if (this.field3.contains(clazz2)) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }

      @Generated
      public ToggleOption method3() {
         return this.field1;
      }

      @Generated
      public ColorOption method4() {
         return this.field2;
      }

      @Generated
      public Set<Class<?>> method5() {
         return this.field3;
      }
   }
}
