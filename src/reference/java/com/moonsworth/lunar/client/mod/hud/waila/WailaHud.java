package com.moonsworth.lunar.client.mod.hud.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.EntityAgeableBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityItemFrameBridge;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.horsestats.SprayHitResult;
import com.moonsworth.lunar.bridge.horsestats.ClientEntityHitResult;
import com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler;
import com.moonsworth.lunar.client.framework.feature.waila.CosmeticWailaComponent;
import com.moonsworth.lunar.client.framework.feature.waila.StringWailaComponent;
import com.moonsworth.lunar.client.framework.feature.waila.SpacerWailaComponent;
import com.moonsworth.lunar.client.framework.feature.waila.ItemWailaComponent;
import com.moonsworth.lunar.client.framework.feature.waila.TextureWailaComponent;
import com.moonsworth.lunar.client.framework.feature.waila.WailaIterator;
import com.moonsworth.lunar.client.framework.feature.waila.VerticalWailaGroup;
import com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaLayout;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import com.moonsworth.lunar.client.util.raytrace.CosmeticRaycastContext;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.jspecify.annotations.Nullable;

public class WailaHud extends AbstractFeature {
   private static final int MAX_ENTITY_HOVER_TICKS = 10;
   private final ToggleOption showBlockCoords = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showBlockCoords"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption blockCoordsColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "blockCoordsColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showCorrectTool = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showCorrectTool"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption correctToolColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "correctToolColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showBreakTime = (ToggleOption)OptionFactory.method7("showBreakTime").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption breakTimeColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "breakTimeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showLightLevel = (ToggleOption)OptionFactory.method7("showLightLevel").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption lightLevelColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lightLevelColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption alwaysShow = (ToggleOption)OptionFactory.method7("alwaysShow").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showEntities = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showEntities"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showCosmetics = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showCosmetics"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showSprays = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showSprays"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption showCompanions = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showCompanions"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption background = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption textShadow = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption verticalSpacing = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "verticalSpacing"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final IntegerOption horizontalSpacing = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "horizontalSpacing"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private int entityHoverTicks;
   private WailaHud.WailaRenderContext cachedRenderContext;
   private WailaLayout currentLayout;

   public WailaHud() {
      super(false);
      this.method24(ModTraits.field1, new WailaHud.Data());
      this.handle(EventTick.class, this::method1);
      this.handle(EventRewindTick.class, this::method3);
   }

   public String getId() {
      return "WAILA";
   }

   private void method1(EventTick highlightimpl21) {
      this.currentLayout = null;
      if (Ref.method11() == HudEditorScreen.class) {
         ItemStackBridge bridgeextension_422 = Bridge.method8().method39(Bridge.method34().method4());
         this.setLayout(
            new WailaLayout(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                  new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                     new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                        new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                        new ItemWailaComponent(bridgeextension_422),
                        new SpacerWailaComponent(3),
                        new StringWailaComponent("WAILA"),
                        new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                     }
                  )
               }
            )
         );
      } else {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         if (itemcounter6extension2 != null) {
            MovingObjectPositionBridge horsestats213 = Ref.method3().bridge$getObjectMouseOver();
            if (horsestats213 != null && horsestats213.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
               this.decayEntityHover();
               com.moonsworth.lunar.client.framework.feature.waila.WailaComponent waila24 = this.buildCosmeticComponent();
               if (waila24 != null) {
                  this.setLayout(
                     new WailaLayout(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                           new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                              new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                 new SpacerWailaComponent((Integer)this.horizontalSpacing.get()), waila24, new SpacerWailaComponent((Integer)this.horizontalSpacing.get())
                              }
                           )
                        }
                     )
                  );
                  return;
               }

               Horsestats20Extension2 horsestats20extension225 = horsestats213.bridge$getBlockPosition();
               Bridge3_23 bridge3_2327 = itemcounter6extension2.method4(horsestats213.bridge$getBlockPosition());
               ItemStackBridge bridgeextension_431 = bridge3_2327.bridge$getStack(horsestats20extension225);
               boolean flag35 = (Boolean)this.showBlockCoords.get();
               String text36 = "(" + horsestats20extension225.bridge$getX() + ", " + horsestats20extension225.bridge$getY() + ", " + horsestats20extension225.bridge$getZ() + ")";
               boolean flag11 = bridgeextension_431 == null || bridgeextension_431.bridge$getItem() == null;
               Object obj10;
               if (!flag11 && !bridgeextension_431.bridge$isEmpty() && !bridgeextension_431.bridge$getDisplayName().equalsIgnoreCase("air")) {
                  TranslationManager foghandler2837 = Ref.method4().method67();
                  String text39 = !this.showCorrectTool.get() ? null : foghandler2837.method2("gui.waila", "tool", new Object[]{this.getCorrectToolName(bridge3_2327)});
                  String text14 = !this.showBreakTime.get() ? null : foghandler2837.method2("gui.waila", "breakTime", new Object[]{this.method4(bridge3_2327)});
                  String text15 = !this.showLightLevel.get() ? null : foghandler2837.method2("gui.waila", "light", new Object[]{this.getLightLevelAt(horsestats20extension225)});
                  WailaIterator wailaiterator16 = new WailaIterator(new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[0]);
                  wailaiterator16.method1(
                     new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                        new ItemWailaComponent(bridgeextension_431),
                        new SpacerWailaComponent(3),
                        new VerticalWailaGroup(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new WailaHandler(bridgeextension_431.bridge$getDisplayName()).method1(),
                              !flag35 ? null : new StringWailaComponent(text36).method1().method2(this.blockCoordsColor),
                              text39 == null ? null : new StringWailaComponent(text39).method1().method2(this.correctToolColor),
                              text14 == null ? null : new StringWailaComponent(text14).method1().method2(this.breakTimeColor),
                              text15 == null ? null : new StringWailaComponent(text15).method1().method2(this.lightLevelColor)
                           }
                        )
                     }
                  );
                  obj10 = wailaiterator16;
               } else {
                  WailaHandler wailahandler12;
                  if (flag11) {
                     wailahandler12 = new WailaHandler(bridge3_2327.bridge$getName()).method1();
                  } else {
                     wailahandler12 = new WailaHandler(bridgeextension_431.bridge$getDisplayName()).method1();
                  }

                  WailaIterator wailaiterator13 = new WailaIterator(new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[0]);
                  wailaiterator13.method1(
                     new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                        new VerticalWailaGroup(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new SpacerWailaComponent(0, 4), wailahandler12, !flag35 ? null : new StringWailaComponent(text36).method1().method2(this.blockCoordsColor), new SpacerWailaComponent(0, 3)
                           }
                        )
                     }
                  );
                  obj10 = wailaiterator13;
               }

               if ((Boolean)this.showSprays.get() && (Boolean)Ref.method4().method41().method6().method35().get()) {
                  float value38 = Ref.method3().bridge$getTimer().method1();
                  ArrayList list40 = new ArrayList();

                  for (List list43 : Ref.method4().method46().method43().values()) {
                     list40.addAll(list43);
                  }

                  SprayHitResult horsestatshandler442 = (SprayHitResult)Ray.method9(Raycaster.field12)
                     .method14((arg0, arg1x) -> true)
                     .method8(Ref.method7(), 5.0, value38)
                     .method18()
                     .method8(list40);
                  Optional optional44 = horsestatshandler442.IIRRICRIRHCOIOCIIOHOORIIHCIOHC();
                  if (optional44.isPresent()) {
                     com.moonsworth.lunar.bridge.horsestats.SprayHitResult.Data data45 = (com.moonsworth.lunar.bridge.horsestats.SprayHitResult.Data)optional44.get();
                     if (data45.method7() instanceof SprayPlacement click17) {
                        Component component46 = null;

                        for (Entry entry20 : Ref.method4().method46().method43().entrySet()) {
                           if (((LinkedList)entry20.getValue()).contains(click17)) {
                              Optional optional21 = itemcounter6extension2.bridge$getPlayerByUniqueId((UUID)entry20.getKey());
                              if (optional21.isPresent()) {
                                 component46 = ((Bridge6_10)optional21.get()).bridge$getDisplayNameComponent();
                              }
                              break;
                           }
                        }

                        String text47 = Ref.method4().method67().method2("gui.waila", "placedBy", new Object[0]);
                        WailaIterator wailaiterator48 = new WailaIterator(new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[0]);
                        wailaiterator48.method1(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new TextureWailaComponent(16, 0, click17.method1().method4()),
                              new SpacerWailaComponent(3),
                              new VerticalWailaGroup(
                                 new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                    new WailaHandler(click17.method1().getName()).method1(),
                                    component46 == null ? null : new WailaHandler(Component.text(text47).append(component46)).method1()
                                 }
                              )
                           }
                        );
                        obj10 = new VerticalWailaGroup(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              (com.moonsworth.lunar.client.framework.feature.waila.WailaComponent)obj10, new SpacerWailaComponent(0, 3), wailaiterator48
                           }
                        );
                     }
                  }
               }

               this.setLayout(
                  new WailaLayout(
                     new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get()),
                              (com.moonsworth.lunar.client.framework.feature.waila.WailaComponent)obj10,
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get())
                           }
                        )
                     }
                  )
               );
            } else if (horsestats213 != null && (Boolean)this.showEntities.get() && horsestats213.bridge$isTypeOfHit(MovingObjectTypeBridge.ENTITY)) {
               BridgeExtension bridgeextension23 = horsestats213.bridge$getEntityHit();
               if (bridgeextension23 instanceof EntityLivingBridge bridgeextension2_55) {
                  if (!bridgeextension2_55.bridge$isInvisible()) {
                     if (bridgeextension23 instanceof Bridge5_11 bridge5_116) {
                        boolean flag7 = NpcUtils.method2(bridge5_116, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL));
                        if (!flag7 && (Boolean)this.showCosmetics.get()) {
                           if (this.entityHoverTicks <= -20) {
                              this.entityHoverTicks = 0;
                           }

                           this.entityHoverTicks++;
                           if (this.entityHoverTicks > 10) {
                              this.entityHoverTicks = 0;
                           } else if (this.entityHoverTicks == 10) {
                              float value8 = Ref.method3().bridge$getTimer().method1();
                              ((CosmeticHitResult)Ray.method9(Raycaster.field11)
                                    .method1(new CosmeticRaycastContext(true))
                                    .method14((arg0, arg1x) -> true)
                                    .method8(Ref.method7(), 5.0, value8)
                                    .method18()
                                    .method8(bridge5_116))
                                 .method2(arg2x -> this.updateEntityRenderContext(bridge5_116, (CosmeticMetadata)arg2x.method7(), arg2x.method8()), () -> {
                                    this.cachedRenderContext = null;
                                    this.entityHoverTicks = 0;
                                 });
                           }
                        }

                        com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow waila32 = new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                              new WailaHandler((Component)(flag7 ? Component.text("NPC") : bridge5_116.bridge$getDisplayNameComponent())),
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                           }
                        );
                        if (this.cachedRenderContext != null) {
                           if (LunarBuildData.field4) {
                              this.setLayout(new WailaLayout(new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{waila32, this.cachedRenderContext.method1()}));
                           } else {
                              String text9 = this.cachedRenderContext.method2().method10().getDisplayName();
                              if (this.cachedRenderContext.method2().method19()) {
                                 text9 = text9 + " (Geckolib)";
                              }

                              this.setLayout(
                                 new WailaLayout(
                                    new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                                       waila32,
                                       this.cachedRenderContext.method1(),
                                       new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                                          new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                             new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                                             new StringWailaComponent(text9),
                                             new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                                          }
                                       )
                                    }
                                 )
                              );
                           }
                        } else {
                           this.setLayout(new WailaLayout(new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{waila32}));
                        }
                     } else {
                        this.decayEntityHover();
                        LinkedList list28 = new LinkedList();
                        ItemStackBridge bridgeextension_433 = bridgeextension23.bridge$getPickResult();
                        if (bridgeextension_433 != null && Bridge.getMinecraftVersion() != Config.field1) {
                           list28.add(
                              new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                                 new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                    new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                                    new ItemWailaComponent(bridgeextension_433),
                                    new SpacerWailaComponent(6),
                                    new WailaHandler(getEntityDisplayName((EntityLivingBridge)bridgeextension23)),
                                    new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                                 }
                              )
                           );
                        } else {
                           list28.add(
                              new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                                 new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                    new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                                    new WailaHandler(getEntityDisplayName((EntityLivingBridge)bridgeextension23)),
                                    new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                                 }
                              )
                           );
                        }

                        this.setLayout(new WailaLayout(list28));
                     }
                  } else {
                     this.decayEntityHover();
                  }
               } else if (!(bridgeextension23 instanceof EntityItemBridge)) {
                  this.decayEntityHover();
                  LinkedList list26 = new LinkedList();
                  ItemStackBridge bridgeextension_429 = bridgeextension23.bridge$getPickResult();
                  if (bridgeextension_429 != null) {
                     list26.add(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                              new ItemWailaComponent(bridgeextension_429),
                              new SpacerWailaComponent(8),
                              new WailaHandler(bridgeextension_429.bridge$getDisplayName()),
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                           }
                        )
                     );
                  } else {
                     list26.add(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                           new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                              new WailaHandler(bridgeextension23.bridge$getTypeName()),
                              new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                           }
                        )
                     );
                  }

                  if (bridgeextension23 instanceof EntityItemFrameBridge bridgeextension534) {
                     bridgeextension_429 = bridgeextension534.bridge$getItemStack();
                     if (bridgeextension_429 != null && !bridgeextension_429.bridge$isEmpty() && !bridgeextension_429.bridge$getDisplayName().equalsIgnoreCase("air")) {
                        list26.add(
                           new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                              0.7F,
                              new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                 new SpacerWailaComponent((Integer)this.horizontalSpacing.get() + 6),
                                 new ItemWailaComponent(bridgeextension_429),
                                 new SpacerWailaComponent(12),
                                 new StringWailaComponent(bridgeextension_429.bridge$getDisplayName()),
                                 new SpacerWailaComponent((Integer)this.horizontalSpacing.get())
                              }
                           )
                        );
                     }
                  }

                  this.setLayout(new WailaLayout(list26));
               } else {
                  this.decayEntityHover();
               }
            } else {
               com.moonsworth.lunar.client.framework.feature.waila.WailaComponent waila4 = this.buildCosmeticComponent();
               if (waila4 != null) {
                  this.decayEntityHover();
                  this.setLayout(
                     new WailaLayout(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                           new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                              new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                                 new SpacerWailaComponent((Integer)this.horizontalSpacing.get()), waila4, new SpacerWailaComponent((Integer)this.horizontalSpacing.get())
                              }
                           )
                        }
                     )
                  );
               }
            }
         }
      }
   }

   private @Nullable WailaHud buildCosmeticComponent() {
      if ((Boolean)this.showCompanions.get() && CosmeticCategoryType.COMPANION.canShowCosmetic()) {
         Set set1 = Ref.method4().method88().method24().keySet();
         if (set1.isEmpty()) {
            return null;
         }

         ClientEntityHitResult horsestatshandler52 = (ClientEntityHitResult)Ray.method9(Raycaster.field13)
            .method14((arg0, arg1x) -> arg0.method5() == null || !arg0.method5().bridge$isInvisible())
            .method8(Ref.method7(), 5.0, Ref.method3().bridge$getTimer().method1())
            .method18()
            .method8(set1);
         Optional optional3 = horsestatshandler52.IIRRICRIRHCOIOCIIOHOORIIHCIOHC();
         if (optional3.isPresent()) {
            com.moonsworth.lunar.bridge.horsestats.ClientEntityHitResult.Data data4 = (com.moonsworth.lunar.bridge.horsestats.ClientEntityHitResult.Data)optional3.get();
            if (data4.method5() instanceof EmoteDefinition inactive35) {
               Component component10 = inactive35.method29().bridge$getDisplayNameComponent();
               String text11 = Ref.method4().method67().method2("gui.waila", "owner", new Object[0]);
               WailaIterator wailaiterator8 = new WailaIterator(new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[0]);
               wailaiterator8.method1(
                  new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                     new SpacerWailaComponent(4),
                     new CosmeticWailaComponent(inactive35.method29(), inactive35.method27(), null, 40),
                     new SpacerWailaComponent(14),
                     new VerticalWailaGroup(
                        new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                           new WailaHandler(inactive35.method28().getName()).method1(),
                           component10 == null ? null : new WailaHandler(Component.text(text11).append(component10)).method1()
                        }
                     )
                  }
               );
               return wailaiterator8;
            }

            if (data4.method5() != null) {
               Component component9 = data4.method5().bridge$getCustomName();
               String text7 = Ref.method4().method67().method2("gui.waila", "lunarEntity", new Object[0]);
               if (component9 != null) {
                  return new VerticalWailaGroup(
                     new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{new WailaHandler(text7).method1(), new WailaHandler(component9).method1()}
                  );
               }

               return new WailaHandler(text7).method1();
            }

            return null;
         }
      }

      return null;
   }

   private void method3(EventRewindTick highlightimpl31) {
      if ((Boolean)this.alwaysShow.get() && this.currentLayout == null) {
         ItemStackBridge bridgeextension_42 = Bridge.method8().method39(Ref.MC_VERSION == 0 ? Bridge.method34().method7() : Bridge.method34().method64());
         this.setLayout(
            new WailaLayout(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow[]{
                  new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                     new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                        new SpacerWailaComponent((Integer)this.horizontalSpacing.get()),
                        new ItemWailaComponent(bridgeextension_42),
                        new SpacerWailaComponent(3),
                        new StringWailaComponent("No target"),
                        new SpacerWailaComponent((Integer)this.horizontalSpacing.get())
                     }
                  )
               }
            )
         );
      }
   }

   private String method4(Bridge3_23 bridge3_231) {
      float value2 = (int)Math.ceil(bridge3_231.bridge$getDestroySpeed() * 20.0F) / 20.0F;
      if (value2 == 0.0F || Ref.method3().bridge$getPlayer().bridge$getPlayerCapabilities().bridge$isCreativeMode()) {
         return Ref.method4().method67().method2("gui.waila", "instant", new Object[0]);
      } else {
         return value2 < 0.0F ? Ref.method4().method67().method2("gui.waila", "unbreakable", new Object[0]) : value2 + "s";
      }
   }

   private String getCorrectToolName(Bridge3_23 bridge3_231) {
      TranslationManager foghandler282 = Ref.method4().method67();
      if (bridge3_231.bridge$getDestroySpeed() < 0.0F) {
         return foghandler282.method2("gui.components", "none", new Object[0]);
      } else if (bridge3_231.bridge$breaksByHoe()) {
         return foghandler282.method2("gui.waila", "hoe", new Object[0]);
      } else if (bridge3_231.bridge$breaksByShears()) {
         return foghandler282.method2("gui.waila", "shears", new Object[0]);
      } else if (bridge3_231.bridge$breaksByAxe()) {
         return foghandler282.method2("gui.waila", "axe", new Object[0]);
      } else if (bridge3_231.bridge$breaksByShovel()) {
         return foghandler282.method2("gui.waila", "shovel", new Object[0]);
      } else {
         return bridge3_231.bridge$breaksByPickaxe() ? foghandler282.method2("gui.waila", "pickaxe", new Object[0]) : foghandler282.method2("gui.waila", "hand", new Object[0]);
      }
   }

   private int getLightLevelAt(Horsestats20Extension2 horsestats20extension21) {
      return Ref.method8().bridge$getLightLevel(horsestats20extension21.bridge$above(), true, true);
   }

   private void decayEntityHover() {
      if (this.entityHoverTicks <= -20) {
         this.cachedRenderContext = null;
      } else if (this.entityHoverTicks <= 0) {
         this.entityHoverTicks--;
      } else {
         this.entityHoverTicks = 0;
      }
   }

   private void updateEntityRenderContext(Bridge5_11 bridge5_111, CosmeticMetadata gui2handler32, AxisAlignedBBBridge horsestats123) {
      OwnedCosmetic gui2handler4 = gui2handler32.method4();
      if (this.cachedRenderContext == null || !this.cachedRenderContext.method2().equals(gui2handler4)) {
         if (gui2handler4.method10() == CosmeticCategoryType.CLOAK) {
            Object obj5;
            ResourceLocationBridge horsestats146;
            if (gui2handler4.getName().contains("Optifine")) {
               horsestats146 = ResourceLocationBridge.create("lunar", "icons/waila/optifine-32x32.png");
               obj5 = Component.text(gui2handler4.getName());
            } else {
               Optional optional7 = this.CROIICHHOOCCOCRIRCRIHHRHICICCO.method94().method1(gui2handler4.method3());
               if (optional7.isPresent()) {
                  horsestats146 = ResourceLocationBridge.create("lunar", "icons/icon_32x32.png");
                  obj5 = (Component)optional7.get();
               } else {
                  horsestats146 = ResourceLocationBridge.create("lunar", "logo/logo-32x32.png");
                  obj5 = Component.text(gui2handler4.getName());
               }
            }

            this.cachedRenderContext = new WailaHud.WailaRenderContext(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                  new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                     new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                     new CosmeticWailaComponent(bridge5_111, gui2handler32, horsestats123),
                     new SpacerWailaComponent(8),
                     new TextureWailaComponent(16, 4, horsestats146),
                     new WailaHandler((Component)obj5),
                     new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                  }
               ),
               gui2handler4,
               horsestats123
            );
         } else {
            this.cachedRenderContext = new WailaHud.WailaRenderContext(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow(
                  new com.moonsworth.lunar.client.framework.feature.waila.WailaComponent[]{
                     new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16),
                     new CosmeticWailaComponent(bridge5_111, gui2handler32, horsestats123),
                     new SpacerWailaComponent(8),
                     new WailaHandler(gui2handler4.getName()),
                     new SpacerWailaComponent((Integer)this.horizontalSpacing.get(), 16)
                  }
               ),
               gui2handler4,
               horsestats123
            );
         }
      }
   }

   private static Component getEntityDisplayName(EntityLivingBridge bridgeextension2_50) {
      return bridgeextension2_50 instanceof EntityAgeableBridge && ((EntityAgeableBridge)bridgeextension2_50).bridge$isBaby()
         ? Ref.method4()
            .method67()
            .method7(
               (TranslatableComponent)Component.translatable().key("gui.waila.baby").args(bridgeextension2_50.bridge$getDisplayNameComponent()).fallback("Baby $0").build()
            )
         : bridgeextension2_50.bridge$getDisplayNameComponent();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.alwaysShow, this.showEntities, this.showCosmetics, this.showSprays, this.showCompanions, this.textShadow, this.background, this.border, this.borderThickness
            }
         )
      );
      lightingextension231.method1("extraOptions", arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBlockCoords, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.blockCoordsColor}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showCorrectTool, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.correctToolColor}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBreakTime, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.breakTimeColor}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showLightLevel, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.lightLevelColor}));
      });
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textColor, this.backgroundColor, this.borderColor})
      );
      lightingextension231.method1("sizeOptions", arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.verticalSpacing, this.horizontalSpacing}));
   }

   public void setLayout(WailaLayout waila21) {
      this.currentLayout = waila21;
      ((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method16(waila21.getWidth(), waila21.getHeight());
   }

   public int getVerticalSpacing() {
      return (Integer)this.verticalSpacing.get();
   }

   @Generated
   public ColorOption getTextColor() {
      return this.textColor;
   }

   @Generated
   public ToggleOption getTextShadow() {
      return this.textShadow;
   }

   @Generated
   public WailaLayout getCurrentLayout() {
      return this.currentLayout;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if ((Boolean)WailaHud.this.background.get()) {
            WailaHud.this.backgroundColor.method11(highlightimpl1.method2(), value2, value3, this.getWidth(), this.getHeight());
         }

         if ((Boolean)WailaHud.this.border.get()) {
            WailaHud.this.borderColor.method11(highlightimpl1.method2(), this, value2, value3, this.getWidth(), this.getHeight(), (Float)WailaHud.this.borderThickness.get());
         }

         int number5 = (int)value2;
         int number6 = (int)value3;
         WailaHud.this.currentLayout.method1(highlightimpl1.method2(), WailaHud.this, number5, number6);
      }

      public boolean method4(boolean flag1) {
         return WailaHud.this.currentLayout != null;
      }
   }

   public class WailaRenderContext {
      private final com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow row;
      private final OwnedCosmetic cosmetic;
      private final AxisAlignedBBBridge bounds;

      public WailaRenderContext(com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow waila1, OwnedCosmetic gui2handler2, AxisAlignedBBBridge horsestats123) {
         this.row = waila1;
         this.cosmetic = gui2handler2;
         this.bounds = horsestats123;
      }

      public com.moonsworth.lunar.client.framework.feature.waila.mixin.WailaRow method1() {
         return this.row;
      }

      public OwnedCosmetic method2() {
         return this.cosmetic;
      }

      public AxisAlignedBBBridge method3() {
         return this.bounds;
      }
   }
}
