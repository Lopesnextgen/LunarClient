package com.moonsworth.lunar.client.mod.misc.debug;

import com.eliotlash.molang.variables.ExecutionContext;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.RenderPlayerBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformVec3fBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.f3display.F3DebugInfo;
import com.moonsworth.lunar.client.framework.mod.UnlockableFeature;
import com.moonsworth.lunar.client.event.render.EventRenderEntityModel;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.ui.hud.HudRowLayout;
import com.moonsworth.lunar.client.ui.hud.HudRow;
import com.moonsworth.lunar.client.cosmetics.molang.MolangRuntime;
import com.moonsworth.lunar.client.cosmetics.molang.QueryDefaults;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.Flag;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3f;

public class GeckolibDebugMod extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("debugBoundingBoxes").method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("border").method31();
   private final FloatOption field11 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<HudRowAlignment> field13 = (EnumOption<HudRowAlignment>)OptionFactory.method10("alignment", HudRowAlignment.LEFT)
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "molangColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final DoubleOption field17 = (DoubleOption)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
               "velocitySmoothingCoefficient"
            )
            .OIRHOOIICOCIOOHICRRRICORIHHIHC(0.1))
         .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.0, 1.0))
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
                  "hotReloadCosmetics"
               )
               .method2(KeyBind.method2(KeyCode.KEY_Y)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field19 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
                  "reloadCosmetics"
               )
               .method2(KeyBind.method2(KeyCode.KEY_EQUALS)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("hidePlayerModel").method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("useShortQuery").method31();
   public final FloatOption field22 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "translateX"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-0.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-2.0F, 2.0F))
      .method31();
   public final FloatOption field23 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "translateX"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-0.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-2.0F, 2.0F))
      .method31();
   public final FloatOption field24 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "translateX"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-0.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-2.0F, 2.0F))
      .method31();
   public final FloatOption field25 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "rotateX"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-146.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
         .method6(3))
      .method31();
   public final FloatOption field26 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "rotateX"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-8.67F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
         .method6(3))
      .method31();
   public final FloatOption field27 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "rotateX"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(-12.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
         .method6(3))
      .method31();
   public final ToggleOption field28 = (ToggleOption)OptionFactory.method7("flipX").method31();
   public final ToggleOption field29 = (ToggleOption)OptionFactory.method7("flipX").method31();
   public final ToggleOption field30 = (ToggleOption)OptionFactory.method7("flipX").method31();
   public final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("geckoCompute").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("logErrors").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field33 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "prevMolangModel"
            )
            .method2(KeyBind.method2(KeyCode.KEY_LEFT)))
         .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
      .method31();
   private final ModifierKeybindOption field34 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "nextMolangModel"
            )
            .method2(KeyBind.method2(KeyCode.KEY_RIGHT)))
         .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
      .method31();
   public final CompanionDebug field35 = new CompanionDebug(this);
   private MolangRuntime field36;
   private MolangRuntime field37;
   public final ItemTransformVec3fBridge field38 = new ItemTransformVec3fBridge(new Vector3f(0.0F), new Vector3f(0.0F), new Vector3f(1.0F));
   private List<String> field39 = new ArrayList<>();
   private String field40 = "";
   private CosmeticMetadata field41 = null;
   private boolean valid = false;

   public GeckolibDebugMod() {
      super(false);
      this.handle(EventRenderEntityModel.class, arg1 -> {
         if ((Boolean)this.field20.get()) {
            arg1.cancel();
         }
      });
   }

   public String getId() {
      return "GECKOLIB_DEBUG_MOD";
   }

   protected void method1(boolean flag1) {
      this.method2(ModTraits.field6, new UnlockableFeature(ModEnabledState.method6(flag1)));
      this.method2(ModTraits.field9, ModSearchIndex.method7());
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field35);
   }

   public MixinCore9Extension method13() {
      return new GeckolibDebugMod.Data();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "hudDisplayOptions",
         arg1x -> {
            arg1x.method9(
               new ClientOption[]{
                  this.field31,
                  this.field8,
                  this.field22,
                  this.field23,
                  this.field24,
                  this.field25,
                  this.field26,
                  this.field27,
                  this.field28,
                  this.field29,
                  this.field30
               }
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field10, arg1xx -> arg1xx.method9(new ClientOption[]{this.field11, this.field15})
            );
            arg1x.method9(new ClientOption[]{this.field12});
            arg1x.method9(new ClientOption[]{this.field13}).method3(this.field12::get);
            arg1x.method9(new ClientOption[]{this.field16, this.field17, this.field18, this.field19, this.field20, this.field32});
         }
      );
      lightingextension231.method1(
         "itemTransformOptions",
         arg1x -> arg1x.method9(
            new ClientOption[]{
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemRotX"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field2.x = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemRotY"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field2.y = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemRotZ"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-180.0F, 180.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field2.z = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemTranslateX"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-5.0F, 5.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field3.x = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemTranslateY"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-5.0F, 5.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field3.y = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemTranslateZ"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(-5.0F, 5.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(arg1xx -> this.field38.field3.z = arg1xx),
               ((FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "itemScale"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.1F, 5.0F))
                     .method31())
                  .CICORRHIOIIOORRRICCORIOIOCIHII(this.field38.field4::set)
            }
         )
      );
      QueryDefaults.method1();
      ArrayList list2 = new ArrayList();
      QueryDefaults.method2()
         .forEach(
            (arg1x, arg2x) -> {
               ToggleOption lightingextension4433 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(arg1x).OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
                  .method31();
               lightingextension4433.method16(com.moonsworth.lunar.client.config.option.OptionTraits.field14, Flag.INSTANCE);
               list2.add(lightingextension4433);
            }
         );
      list2.sort(Comparator.comparing(ClientOption::getName));
      lightingextension231.method1(
         "molangHudOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field33, this.field34, this.field21})
      );
      lightingextension231.method1("molangQueryOptions", arg1x -> arg1x.OHOOORICRHIIIIRHCICICOCHROICRC(list2));
   }

   protected String method18() {
      return "[Geckolib]";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field7})
         .method3(new String[]{"Alexandre totally made this and Gecko has nothing to do with it (legit legit)"})
         .method11(this);
   }

   private void method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         List list2 = Ref.method4().method53().method19(bridge5extension_51.bridge$getUniqueID());
         this.field39.clear();
         this.field39.add("");
         CosmeticMetadata gui2handler33 = null;

         for (CosmeticMetadata gui2handler35 : list2) {
            if (gui2handler35.method4() instanceof EmoteModel gui2iterator6) {
               this.field39.add(gui2iterator6.getName());
               if (gui2iterator6.getName().equals(this.field40)) {
                  gui2handler33 = gui2handler35;
               }
            }
         }

         if (gui2handler33 == null) {
            this.field40 = "";
            this.field37 = this.field36;
         }

         this.field41 = gui2handler33;
      }
   }

   public void method8(String text1, MolangRuntime highlight2) {
      if (this.field40.equals(text1)) {
         this.field37 = highlight2;
      }
   }

   public void method15() {
      boolean flag1 = !this.valid;
      this.valid = true;
      if (this.field36 == null) {
         EvaluatorImpl evaluatorimpl2 = new EvaluatorImpl();
         this.field36 = new MolangRuntime(new ExecutionContext(evaluatorimpl2), evaluatorimpl2);
         if (this.field37 == null) {
            this.field37 = this.field36;
         }
      }

      if (flag1) {
         this.handle(EventTick.class, arg1x -> this.method14());
         this.field18.method3(() -> {
            Ref.method4().method96().method7().clear();
            Ref.method4().method76().method1();
            Ref.method4().method46().method8();
            Ref.method4().method69().method3("Reloaded cosmetic JIT assets!");
            LunarLogger.method3("Reloaded JIT resources.", new Object[0]);
         });
         this.field19.method3(() -> Ref.method4().method53().method1(true));
         this.field33.method3(() -> {
            if (!this.field39.isEmpty()) {
               int index1x = this.field39.indexOf(this.field40);
               if (index1x == -1) {
                  index1x = 0;
               } else if (--index1x < 0) {
                  index1x = this.field39.size() - 1;
               }

               this.field40 = this.field39.get(index1x);
            }
         });
         this.field34.method3(() -> {
            if (!this.field39.isEmpty()) {
               int index1x = this.field39.indexOf(this.field40);
               if (index1x == -1) {
                  index1x = 0;
               } else if (++index1x >= this.field39.size()) {
                  index1x = 0;
               }

               this.field40 = this.field39.get(index1x);
            }
         });
      }
   }

   @Generated
   public ToggleOption method16() {
      return this.field8;
   }

   @Generated
   public DoubleOption method17() {
      return this.field17;
   }

   @Generated
   public CompanionDebug method19() {
      return this.field35;
   }

   @Generated
   public boolean isValid() {
      return this.valid;
   }

   private class Data extends HudRowElement {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, List<HudRow> list5) {
         this.method2(
            mixinhelper_41,
            value2,
            value3,
            (Boolean)GeckolibDebugMod.this.field12.get(),
            (HudRowAlignment)GeckolibDebugMod.this.field13.get(),
            (Boolean)GeckolibDebugMod.this.field9.get(),
            GeckolibDebugMod.this.field14,
            (Boolean)GeckolibDebugMod.this.field10.get(),
            (Float)GeckolibDebugMod.this.field11.get(),
            GeckolibDebugMod.this.field15
         );
      }

      protected List<HudRow> method5(boolean flag1) {
         ArrayList list2 = new ArrayList();
         if (GeckolibDebugMod.this.field37 == null) {
            return list2;
         }

         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridge5extension_53 != null) {
            RenderPlayerBridge mixinhelper_64 = (RenderPlayerBridge)Bridge.method9()
               .bridge$getEntityRenderDispatcher()
               .bridge$getSkinMap()
               .get(Ref.method7().bridge$getSkinType());
            ModelPlayerBridge bridgeextension2_75 = mixinhelper_64.bridge$getMainModel();
            RenderContext fov106 = RenderContext.method7(bridge5extension_53, bridgeextension2_75);
            fov106.method24(GeckolibDebugMod.this.field41);
            fov106.method20(bridge5extension_53.bridge$getHeldItem());
            GeckolibDebugMod.this.field37.method6(bridge5extension_53, fov106);
         }

         MolangScope fps1111 = GeckolibDebugMod.this.field37.method10();
         OptionContainer framework512 = (OptionContainer)GeckolibDebugMod.this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
         if (framework512 == null) {
            return list2;
         }

         boolean flag13 = (Boolean)GeckolibDebugMod.this.field21.get();

         for (ClientOption lightingextension8 : framework512.method2()) {
            if (lightingextension8.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field14) == Flag.INSTANCE
               && lightingextension8.get() instanceof Boolean flag9
               && flag9) {
               MolangVariable fps7handler15 = fps1111.method5(lightingextension8.getId());
               if (fps7handler15 != null) {
                  list2.add(
                     HudRowLayout.method5(
                        (flag13 ? lightingextension8.getId().replaceFirst("query[.]", "q.") : lightingextension8.getId()) + ": " + F3DebugInfo.method33(fps7handler15.value, 2),
                        GeckolibDebugMod.this.field16,
                        false
                     )
                  );
               }
            }
         }

         if (!list2.isEmpty()) {
            String text14 = GeckolibDebugMod.this.field40.isEmpty() ? "None (Debug Mod)" : GeckolibDebugMod.this.field40;
            list2.add(0, HudRowLayout.method5("Viewing Model: " + text14, GeckolibDebugMod.this.field16, false));
            list2.add(
               1,
               HudRowLayout.method5(
                  "< " + GeckolibDebugMod.this.field33 + " Previous > " + GeckolibDebugMod.this.field34 + " Next", GeckolibDebugMod.this.field16, false
               )
            );
         }

         return list2;
      }
   }
}
