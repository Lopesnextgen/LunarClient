package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.shader.DevShaderEditor;
import com.moonsworth.lunar.client.render.shader.ShaderKey.Data2;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.ShaderPreviewRenderer;
import com.moonsworth.lunar.client.framework.mod.UnlockableFeature;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.shader.ShaderDefinition;
import java.io.IOException;
import java.util.UUID;
import lombok.Generated;

public class ShaderDebugMod extends AbstractFeature {
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("reloadWornShader")
               .method2(new KeyBind(false, false, true, KeyCode.KEY_P)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field9 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("openShaderCloakEditor")
            .method2(new KeyBind(false, true, false, KeyCode.KEY_P)))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("screenshotTexture")
               .method2(new KeyBind(true, false, false, KeyCode.KEY_P)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private ShaderCloakRenderer field11 = null;
   private ResourceLocationBridge field12 = null;
   private long field13 = 0L;
   private Shaderdebugmod field14;
   private final GuiIterator field15 = new GuiIterator();
   private final ShaderPreviewRenderer field16 = new ShaderPreviewRenderer();
   private boolean valid = false;
   private Long field17;

   public ShaderDebugMod() {
      super(false);
   }

   public String getId() {
      return "SHADER_DEBUG_MOD";
   }

   protected String method18() {
      return "Shader Debug";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"Fish"}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field10});
   }

   protected void method1(boolean flag1) {
      this.method6(ModTraits.field6, new UnlockableFeature(ModEnabledState.method6(flag1)));
      this.method6(ModTraits.field9, ModSearchIndex.method7());
   }

   private void method13() {
      if (this.field11 != null && !this.field11.method23()) {
         this.field11.method15();
      }
   }

   private void tick() {
      if (this.field14 != null && !this.field16.isInitialized()) {
         this.field16.init();
      }

      if (this.field14 != null) {
         this.field14.method1();
      }

      if (this.field11 != null) {
         long number1 = System.currentTimeMillis() - this.field13;
         if (number1 > 30000L) {
            this.method14();
            this.method15();
         } else if (this.field11.method22()) {
            this.field11.method6();
            Bridge5Extension_5 bridge5extension_53 = Ref.method7();
            if (bridge5extension_53 != null) {
               this.field11.method2(bridge5extension_53);
               if (this.field11.method23()) {
                  this.field11.method16();
               }

               if (this.field16.isValid() && this.field14 != null && this.field14.method42()) {
                  this.field16.method1(this.field11.method40(), this.field14.method44());
               }

               if (this.field14 != null && this.field14.method42()) {
                  this.field14.method5();
               }
            }
         }
      }
   }

   public void method6(ResourceLocationBridge horsestats141, ShaderDefinition alert62, Bridge8Extension3 bridge8extension33, UUID uuid4) {
      if (horsestats141 != null) {
         if (uuid4 == null || Ref.method7() == null || uuid4.equals(Ref.method7().bridge$getUniqueID())) {
            this.field13 = System.currentTimeMillis();
            if (!horsestats141.equals(this.field12)) {
               this.field12 = horsestats141;
               this.method14();
               this.field11 = new ShaderCloakRenderer(alert62);
               if (this.field17 != null) {
                  this.field11.setStartTime(this.field17);
                  this.field17 = null;
               }

               if (this.field14 != null) {
                  this.field11.method42(this.field14);
               }

               Bridge5Extension_5 bridge5extension_55 = Ref.method7();
               if (bridge5extension_55 != null) {
                  try {
                     this.field11.method7(bridge8extension33);
                     this.field11.method6();
                  } catch (IOException exception7) {
                     DevShaderEditor.method9("Unable to load Shaders! " + exception7.getMessage());
                     LunarLogger.warn("Unable to load Dev Shader Cloak! %s", exception7);
                  }
               }
            }
         }
      }
   }

   public void method14() {
      if (this.field11 != null) {
         this.field11.method42(null);
         this.field11.cleanup();
         this.field11 = null;
      }
   }

   public void method15() {
      this.field12 = null;
   }

   public void method16() {
      boolean flag1 = !this.valid;
      this.valid = true;
      if (flag1) {
         LunarEventBus.method29().method1(EventTick.class, this::tick);
         LunarEventBus.method29().method1(EventRenderTickStart.class, this::method13);
         this.field9.method3(this::method21);
         this.field10.method3(this::method19);
         this.field8.method3(() -> {
            Bridge5Extension_5 bridge5extension_51x = Ref.method7();
            if (bridge5extension_51x != null) {
               CosmeticMetadata gui2handler32 = Ref.method4().method53().method15(bridge5extension_51x.bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
               if (gui2handler32 != null) {
                  ResourceLocationBridge horsestats143 = gui2handler32.method4().method4(bridge5extension_51x);
                  ShaderDefinition alert64 = (ShaderDefinition)Ref.method4().method53().method73().get(horsestats143);
                  if (alert64 != null) {
                     if (alert64.method5() instanceof Data2 data25) {
                        Ref.method4().method102().method6(data25.getLocation());
                     }

                     if (alert64.method6() instanceof Data2 data27) {
                        Ref.method4().method102().method6(data27.getLocation());
                     }

                     this.method14();
                     this.field12 = null;
                  }
               }
            }
         });
      }
   }

   public void method17() {
      if (this.field11 != null) {
         this.field13 = System.currentTimeMillis();
      }
   }

   private void method19() {
      if (this.field11 != null) {
         this.field11.method24(arg1 -> {
            if (this.field11.method25(arg1)) {
               Ref.method4().method69().method3("Successfully saved screenshot to your screenshots folder!");
            } else {
               Ref.method4().method69().method3("Unable to make screenshot!");
            }
         });
      }
   }

   private void method21() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         CosmeticManager holograms122 = Ref.method4().method53();
         CosmeticMetadata gui2handler33 = holograms122.getProvider(bridge5extension_51.bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
         if (gui2handler33 == null) {
            Ref.method4().method69().method3("You must be wearing a cloak to open this menu!");
         } else {
            ResourceLocationBridge horsestats144 = gui2handler33.method4().method3();
            if (JitPaths.method1(horsestats144)) {
               Ref.method4().method69().method3("You must be wearing a dev cosmetics cloak to open this menu!");
            } else {
               boolean flag5 = false;
               if (this.field14 == null) {
                  flag5 = true;
               } else if (!this.field14.method6(horsestats144)) {
                  this.field14.method3(false);
                  flag5 = true;
               }

               if (flag5) {
                  if (this.field14 != null) {
                     this.field14.cleanup();
                  }

                  this.field14 = new Shaderdebugmod(horsestats144);
                  this.field14.method28();
                  this.field14.method5();
               }

               if (this.field11 == null) {
                  Ref.method4().method53().method37(horsestats144, bridge5extension_51.bridge$getUniqueID());
               }

               if (this.field11 != null) {
                  this.field11.method42(this.field14);
               }

               this.field14.method21(this.field15, (int)gui2handler33.method4().method9());
               DriverViewportLegacy.method50().method16(DriverRouteRegistry.field24);
            }
         }
      }
   }

   public void method22() {
      if (this.field14 != null) {
         this.field14.cleanup();
         this.field14 = null;
      }
   }

   public void method23() {
      if (this.field11 != null) {
         this.field17 = this.field11.getStartTime();
      }
   }

   @Generated
   public ShaderCloakRenderer method24() {
      return this.field11;
   }

   @Generated
   public ResourceLocationBridge method25() {
      return this.field12;
   }

   @Generated
   public Shaderdebugmod method26() {
      return this.field14;
   }

   @Generated
   public GuiIterator method27() {
      return this.field15;
   }

   @Generated
   public ShaderPreviewRenderer method28() {
      return this.field16;
   }

   @Generated
   public boolean isValid() {
      return this.valid;
   }
}
