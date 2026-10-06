package com.moonsworth.lunar.client.mod.hud.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_20;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemFrameEntityBridge;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.SprayHitResult;
import com.moonsworth.lunar.bridge.horsestats.ClientEntityHitResult;
import com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler2;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler3;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler5;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler6;
import com.moonsworth.lunar.client.framework.feature.waila.WailaHandler7;
import com.moonsworth.lunar.client.framework.feature.waila.WailaIterator;
import com.moonsworth.lunar.client.framework.feature.waila.WailaIterator2;
import com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTickEnd;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler;
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

public class Waila extends AbstractFeature {
   private static final int MAX_ENTITY_HOVER_TICKS = 10;
   private final ToggleOption showBlockCoords = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBlockCoords")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption blockCoordsColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("blockCoordsColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption showCorrectTool = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showCorrectTool")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption correctToolColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("correctToolColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption showBreakTime = (ToggleOption)OptionFactory.method7("showBreakTime").method31();
   private final ColorOption breakTimeColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("breakTimeColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption showLightLevel = (ToggleOption)OptionFactory.method7("showLightLevel").method31();
   private final ColorOption lightLevelColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("lightLevelColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption alwaysShow = (ToggleOption)OptionFactory.method7("alwaysShow").method31();
   private final ToggleOption showEntities = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEntities")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showCosmetics = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showCosmetics")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showSprays = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showSprays")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showCompanions = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showCompanions")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("background")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("backgroundColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .method31();
   private final ColorOption textColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("textColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption textShadow = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("textShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final ColorOption borderColor = (ColorOption)((ColorOption.Data)OptionFactory.method8("borderColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final IntegerOption verticalSpacing = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "verticalSpacing"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .method31();
   private final IntegerOption horizontalSpacing = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "horizontalSpacing"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .method31();
   private int entityHoverTicks;
   private Waila.Data2 cachedEntityData;
   private Waila2 currentComponents;

   public Waila() {
      super(false);
      this.method24(Framework.field1, new Waila.Data());
      this.handle(EventClientTick.class, this::method1);
      this.handle(EventTickEnd.class, this::method3);
   }

   @Override
   public String getId() {
      return "WAILA";
   }

   private void method1(EventClientTick var1) {
      this.currentComponents = null;
      if (ThreadModuleDump63.method11() == HudEditorScreen.class) {
         ItemStackBridge var22 = Bridge.method8().method39(Bridge.method34().method4());
         this.setComponents(
            new Waila2(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                  new WailaHandler5(this.horizontalSpacing.get(), 16),
                  new WailaHandler6(var22),
                  new WailaHandler5(3),
                  new WailaHandler3("WAILA"),
                  new WailaHandler5(this.horizontalSpacing.get(), 16)
               )
            )
         );
      } else {
         Itemcounter6Extension var2 = ThreadModuleDump63.method8();
         if (var2 != null) {
            MovingObjectPositionHitResult var3 = ThreadModuleDump63.method3().bridge$getObjectMouseOver();
            if (var3 != null && var3.bridge$isTypeOfHit(MovingObjectHitType.BLOCK)) {
               this.decayEntityHover();
               com.moonsworth.lunar.client.framework.feature.waila.Waila var24 = this.buildCosmeticWaila();
               if (var24 != null) {
                  this.setComponents(
                     new Waila2(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                           new WailaHandler5(this.horizontalSpacing.get()), var24, new WailaHandler5(this.horizontalSpacing.get())
                        )
                     )
                  );
                  return;
               }

               Horsestats20Extension2 var25 = var3.bridge$getBlockPosition();
               Bridge3_23 var27 = var2.method4(var3.bridge$getBlockPosition());
               ItemStackBridge var31 = var27.bridge$getStack(var25);
               boolean var35 = this.showBlockCoords.get();
               String var36 = "(" + var25.bridge$getX() + ", " + var25.bridge$getY() + ", " + var25.bridge$getZ() + ")";
               boolean var11 = var31 == null || var31.bridge$getItem() == null;
               com.moonsworth.lunar.client.framework.feature.waila.Waila var10;
               if (!var11 && !var31.bridge$isEmpty() && !var31.bridge$getDisplayName().equalsIgnoreCase("air")) {
                  TranslationManager var37 = ThreadModuleDump63.method4().method67();
                  String var39 = !this.showCorrectTool.get() ? null : var37.method2("gui.waila", "tool", this.getCorrectToolName(var27));
                  String var14 = !this.showBreakTime.get() ? null : var37.method2("gui.waila", "breakTime", this.method4(var27));
                  String var15 = !this.showLightLevel.get() ? null : var37.method2("gui.waila", "light", this.getLightLevelAt(var25));
                  WailaIterator var16 = new WailaIterator();
                  var16.method1(
                     new WailaHandler6(var31),
                     new WailaHandler5(3),
                     new WailaIterator2(
                        new WailaHandler(var31.bridge$getDisplayName()).method1(),
                        !var35 ? null : new WailaHandler3(var36).method1().method2(this.blockCoordsColor),
                        var39 == null ? null : new WailaHandler3(var39).method1().method2(this.correctToolColor),
                        var14 == null ? null : new WailaHandler3(var14).method1().method2(this.breakTimeColor),
                        var15 == null ? null : new WailaHandler3(var15).method1().method2(this.lightLevelColor)
                     )
                  );
                  var10 = var16;
               } else {
                  WailaHandler var12;
                  if (var11) {
                     var12 = new WailaHandler(var27.bridge$getName()).method1();
                  } else {
                     var12 = new WailaHandler(var31.bridge$getDisplayName()).method1();
                  }

                  WailaIterator var13 = new WailaIterator();
                  var13.method1(
                     new WailaIterator2(
                        new WailaHandler5(0, 4), var12, !var35 ? null : new WailaHandler3(var36).method1().method2(this.blockCoordsColor), new WailaHandler5(0, 3)
                     )
                  );
                  var10 = var13;
               }

               if (this.showSprays.get() && ThreadModuleDump63.method4().method41().method6().method35().get()) {
                  float var38 = ThreadModuleDump63.method3().bridge$getTimer().method1();
                  ArrayList var40 = new ArrayList();

                  for (List var43 : ThreadModuleDump63.method4().method46().method43().values()) {
                     var40.addAll(var43);
                  }

                  SprayHitResult var42 = (SprayHitResult)SExtension.builder(SImpl.SPRAY)
                     .method14((var0, var1x) -> true)
                     .method8(ThreadModuleDump63.method7(), 5.0, var38)
                     .method18()
                     .method8(var40);
                  Optional var44 = var42.method3();
                  if (var44.isPresent()) {
                     SprayHitResult.Data var45 = (SprayHitResult.Data)var44.get();
                     if (var45.method7() instanceof SprayPlacement var17) {
                        Component var46 = null;

                        for (Entry var20 : ThreadModuleDump63.method4().method46().method43().entrySet()) {
                           if (((LinkedList)var20.getValue()).contains(var17)) {
                              Optional var21 = var2.bridge$getPlayerByUniqueId((UUID)var20.getKey());
                              if (var21.isPresent()) {
                                 var46 = ((Bridge6_10)var21.get()).bridge$getDisplayNameComponent();
                              }
                              break;
                           }
                        }

                        String var47 = ThreadModuleDump63.method4().method67().method2("gui.waila", "placedBy");
                        WailaIterator var48 = new WailaIterator();
                        var48.method1(
                           new WailaHandler7(16, 0, var17.method1().method4()),
                           new WailaHandler5(3),
                           new WailaIterator2(
                              new WailaHandler(var17.method1().getName()).method1(),
                              var46 == null ? null : new WailaHandler(Component.text(var47).append(var46)).method1()
                           )
                        );
                        var10 = new WailaIterator2(var10, new WailaHandler5(0, 3), var48);
                     }
                  }
               }

               this.setComponents(
                  new Waila2(
                     new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                        new WailaHandler5(this.horizontalSpacing.get()), var10, new WailaHandler5(this.horizontalSpacing.get())
                     )
                  )
               );
            } else if (var3 != null && this.showEntities.get() && var3.bridge$isTypeOfHit(MovingObjectHitType.ENTITY)) {
               BridgeExtension var23 = var3.bridge$getEntityHit();
               if (var23 instanceof BridgeExtension2_5 var5) {
                  if (!var5.bridge$isInvisible()) {
                     if (var23 instanceof Bridge5_11 var6) {
                        boolean var7 = ThreadModuleDump22.method2(var6, Highlight3Iterator.method8(KeystrokesType.HYPIXEL));
                        if (!var7 && this.showCosmetics.get()) {
                           if (this.entityHoverTicks <= -20) {
                              this.entityHoverTicks = 0;
                           }

                           this.entityHoverTicks++;
                           if (this.entityHoverTicks > 10) {
                              this.entityHoverTicks = 0;
                           } else if (this.entityHoverTicks == 10) {
                              float var8 = ThreadModuleDump63.method3().bridge$getTimer().method1();
                              ((CosmeticHitResult)SExtension.builder(SImpl.COSMETIC)
                                    .method1(new ChestHandler(true))
                                    .method14((var0, var1x) -> true)
                                    .method8(ThreadModuleDump63.method7(), 5.0, var8)
                                    .method18()
                                    .method8(var6))
                                 .method2(var2x -> this.updateEntityRenderContext(var6, (CosmeticMetadata)var2x.method7(), var2x.method8()), () -> {
                                    this.cachedEntityData = null;
                                    this.entityHoverTicks = 0;
                                 });
                           }
                        }

                        com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila var32 = new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                           new WailaHandler5(this.horizontalSpacing.get(), 16),
                           new WailaHandler((Component)(var7 ? Component.text("NPC") : var6.bridge$getDisplayNameComponent())),
                           new WailaHandler5(this.horizontalSpacing.get(), 16)
                        );
                        if (this.cachedEntityData != null) {
                           if (LunarBuildData.field4) {
                              this.setComponents(new Waila2(var32, this.cachedEntityData.method1()));
                           } else {
                              String var9 = this.cachedEntityData.method2().method10().getDisplayName();
                              if (this.cachedEntityData.method2().method19()) {
                                 var9 = var9 + " (Geckolib)";
                              }

                              this.setComponents(
                                 new Waila2(
                                    var32,
                                    this.cachedEntityData.method1(),
                                    new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                                       new WailaHandler5(this.horizontalSpacing.get(), 16), new WailaHandler3(var9), new WailaHandler5(this.horizontalSpacing.get(), 16)
                                    )
                                 )
                              );
                           }
                        } else {
                           this.setComponents(new Waila2(var32));
                        }
                     } else {
                        this.decayEntityHover();
                        LinkedList var28 = new LinkedList();
                        ItemStackBridge var33 = var23.bridge$getPickResult();
                        if (var33 != null && Bridge.getMinecraftVersion() != Config.field1) {
                           var28.add(
                              new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                                 new WailaHandler5(this.horizontalSpacing.get(), 16),
                                 new WailaHandler6(var33),
                                 new WailaHandler5(6),
                                 new WailaHandler(getEntityDisplayName((BridgeExtension2_5)var23)),
                                 new WailaHandler5(this.horizontalSpacing.get(), 16)
                              )
                           );
                        } else {
                           var28.add(
                              new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                                 new WailaHandler5(this.horizontalSpacing.get(), 16),
                                 new WailaHandler(getEntityDisplayName((BridgeExtension2_5)var23)),
                                 new WailaHandler5(this.horizontalSpacing.get(), 16)
                              )
                           );
                        }

                        this.setComponents(new Waila2(var28));
                     }
                  } else {
                     this.decayEntityHover();
                  }
               } else if (!(var23 instanceof ItemEntityBridge)) {
                  this.decayEntityHover();
                  LinkedList var26 = new LinkedList();
                  ItemStackBridge var29 = var23.bridge$getPickResult();
                  if (var29 != null) {
                     var26.add(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                           new WailaHandler5(this.horizontalSpacing.get(), 16),
                           new WailaHandler6(var29),
                           new WailaHandler5(8),
                           new WailaHandler(var29.bridge$getDisplayName()),
                           new WailaHandler5(this.horizontalSpacing.get(), 16)
                        )
                     );
                  } else {
                     var26.add(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                           new WailaHandler5(this.horizontalSpacing.get(), 16), new WailaHandler(var23.bridge$getTypeName()), new WailaHandler5(this.horizontalSpacing.get(), 16)
                        )
                     );
                  }

                  if (var23 instanceof ItemFrameEntityBridge var34) {
                     var29 = var34.bridge$getItemStack();
                     if (var29 != null && !var29.bridge$isEmpty() && !var29.bridge$getDisplayName().equalsIgnoreCase("air")) {
                        var26.add(
                           new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                              0.7F,
                              new WailaHandler5(this.horizontalSpacing.get() + 6),
                              new WailaHandler6(var29),
                              new WailaHandler5(12),
                              new WailaHandler3(var29.bridge$getDisplayName()),
                              new WailaHandler5(this.horizontalSpacing.get())
                           )
                        );
                     }
                  }

                  this.setComponents(new Waila2(var26));
               } else {
                  this.decayEntityHover();
               }
            } else {
               com.moonsworth.lunar.client.framework.feature.waila.Waila var4 = this.buildCosmeticWaila();
               if (var4 != null) {
                  this.decayEntityHover();
                  this.setComponents(
                     new Waila2(
                        new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                           new WailaHandler5(this.horizontalSpacing.get()), var4, new WailaHandler5(this.horizontalSpacing.get())
                        )
                     )
                  );
               }
            }
         }
      }
   }

   private @Nullable Waila buildCosmeticWaila() {
      if (this.showCompanions.get() && CosmeticCategoryType.COMPANION.canShowCosmetic()) {
         Set var1 = ThreadModuleDump63.method4().method88().method24().keySet();
         if (var1.isEmpty()) {
            return null;
         }

         ClientEntityHitResult var2 = (ClientEntityHitResult)SExtension.builder(SImpl.CLIENT_ENTITY)
            .method14((var0, var1x) -> var0.method5() == null || !var0.method5().bridge$isInvisible())
            .method8(ThreadModuleDump63.method7(), 5.0, ThreadModuleDump63.method3().bridge$getTimer().method1())
            .method18()
            .method8(var1);
         Optional var3 = var2.method3();
         if (var3.isPresent()) {
            ClientEntityHitResult.Data var4 = (ClientEntityHitResult.Data)var3.get();
            if (var4.method5() instanceof EmoteDefinition var5) {
               Component var10 = var5.method29().bridge$getDisplayNameComponent();
               String var11 = ThreadModuleDump63.method4().method67().method2("gui.waila", "owner");
               WailaIterator var8 = new WailaIterator();
               var8.method1(
                  new WailaHandler5(4),
                  new WailaHandler2(var5.method29(), var5.method27(), null, 40),
                  new WailaHandler5(14),
                  new WailaIterator2(
                     new WailaHandler(var5.method28().getName()).method1(),
                     var10 == null ? null : new WailaHandler(Component.text(var11).append(var10)).method1()
                  )
               );
               return var8;
            }

            if (var4.method5() != null) {
               Component var9 = var4.method5().bridge$getCustomName();
               String var7 = ThreadModuleDump63.method4().method67().method2("gui.waila", "lunarEntity");
               if (var9 != null) {
                  return new WailaIterator2(new WailaHandler(var7).method1(), new WailaHandler(var9).method1());
               }

               return new WailaHandler(var7).method1();
            }

            return null;
         }
      }

      return null;
   }

   private void method3(EventTickEnd var1) {
      if (this.alwaysShow.get() && this.currentComponents == null) {
         ItemStackBridge var2 = Bridge.method8().method39(ThreadModuleDump63.MC_VERSION == 0 ? Bridge.method34().method7() : Bridge.method34().method64());
         this.setComponents(
            new Waila2(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                  new WailaHandler5(this.horizontalSpacing.get()),
                  new WailaHandler6(var2),
                  new WailaHandler5(3),
                  new WailaHandler3("No target"),
                  new WailaHandler5(this.horizontalSpacing.get())
               )
            )
         );
      }
   }

   private String method4(Bridge3_23 var1) {
      float var2 = (int)Math.ceil(var1.bridge$getDestroySpeed() * 20.0F) / 20.0F;
      if (var2 == 0.0F || ThreadModuleDump63.method3().bridge$getPlayer().bridge$getPlayerCapabilities().bridge$isCreativeMode()) {
         return ThreadModuleDump63.method4().method67().method2("gui.waila", "instant");
      } else {
         return var2 < 0.0F ? ThreadModuleDump63.method4().method67().method2("gui.waila", "unbreakable") : var2 + "s";
      }
   }

   private String getCorrectToolName(Bridge3_23 var1) {
      TranslationManager var2 = ThreadModuleDump63.method4().method67();
      if (var1.bridge$getDestroySpeed() < 0.0F) {
         return var2.method2("gui.components", "none");
      } else if (var1.bridge$breaksByHoe()) {
         return var2.method2("gui.waila", "hoe");
      } else if (var1.bridge$breaksByShears()) {
         return var2.method2("gui.waila", "shears");
      } else if (var1.bridge$breaksByAxe()) {
         return var2.method2("gui.waila", "axe");
      } else if (var1.bridge$breaksByShovel()) {
         return var2.method2("gui.waila", "shovel");
      } else {
         return var1.bridge$breaksByPickaxe() ? var2.method2("gui.waila", "pickaxe") : var2.method2("gui.waila", "hand");
      }
   }

   private int getLightLevelAt(Horsestats20Extension2 var1) {
      return ThreadModuleDump63.method8().bridge$getLightLevel(var1.bridge$above(), true, true);
   }

   private void decayEntityHover() {
      if (this.entityHoverTicks <= -20) {
         this.cachedEntityData = null;
      } else if (this.entityHoverTicks <= 0) {
         this.entityHoverTicks--;
      } else {
         this.entityHoverTicks = 0;
      }
   }

   private void updateEntityRenderContext(Bridge5_11 var1, CosmeticMetadata var2, AxisAlignedBBBridge var3) {
      OwnedCosmetic var4 = var2.method4();
      if (this.cachedEntityData == null || !this.cachedEntityData.method2().equals(var4)) {
         if (var4.method10() == CosmeticCategoryType.CLOAK) {
            Object var5;
            ResourceLocationBridge var6;
            if (var4.getName().contains("Optifine")) {
               var6 = ResourceLocationBridge.create("lunar", "icons/waila/optifine-32x32.png");
               var5 = Component.text(var4.getName());
            } else {
               Optional var7 = this.field4.method94().method1(var4.method3());
               if (var7.isPresent()) {
                  var6 = ResourceLocationBridge.create("lunar", "icons/icon_32x32.png");
                  var5 = (Component)var7.get();
               } else {
                  var6 = ResourceLocationBridge.create("lunar", "logo/logo-32x32.png");
                  var5 = Component.text(var4.getName());
               }
            }

            this.cachedEntityData = new Waila.Data2(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                  new WailaHandler5(this.horizontalSpacing.get(), 16),
                  new WailaHandler2(var1, var2, var3),
                  new WailaHandler5(8),
                  new WailaHandler7(16, 4, var6),
                  new WailaHandler((Component)var5),
                  new WailaHandler5(this.horizontalSpacing.get(), 16)
               ),
               var4,
               var3
            );
         } else {
            this.cachedEntityData = new Waila.Data2(
               new com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila(
                  new WailaHandler5(this.horizontalSpacing.get(), 16),
                  new WailaHandler2(var1, var2, var3),
                  new WailaHandler5(8),
                  new WailaHandler(var4.getName()),
                  new WailaHandler5(this.horizontalSpacing.get(), 16)
               ),
               var4,
               var3
            );
         }
      }
   }

   private static Component getEntityDisplayName(BridgeExtension2_5 var0) {
      return var0 instanceof Bridge5_20 && ((Bridge5_20)var0).bridge$isBaby()
         ? ThreadModuleDump63.method4()
            .method67()
            .method7(
               (TranslatableComponent)Component.translatable().key("gui.waila.baby").args(var0.bridge$getDisplayNameComponent()).fallback("Baby $0").build()
            )
         : var0.bridge$getDisplayNameComponent();
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method45(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> var1x.method9(
            new ClientOption[]{
               this.alwaysShow, this.showEntities, this.showCosmetics, this.showSprays, this.showCompanions, this.textShadow, this.background, this.border, this.borderThickness
            }
         )
      );
      var1.method1("extraOptions", var1x -> {
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBlockCoords, var1xx -> var1xx.method9(new ClientOption[]{this.blockCoordsColor}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showCorrectTool, var1xx -> var1xx.method9(new ClientOption[]{this.correctToolColor}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showBreakTime, var1xx -> var1xx.method9(new ClientOption[]{this.breakTimeColor}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showLightLevel, var1xx -> var1xx.method9(new ClientOption[]{this.lightLevelColor}));
      });
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.COLOR, var1x -> var1x.method9(new ClientOption[]{this.textColor, this.backgroundColor, this.borderColor})
      );
      var1.method1("sizeOptions", var1x -> var1x.method9(new ClientOption[]{this.verticalSpacing, this.horizontalSpacing}));
   }

   public void setComponents(Waila2 var1) {
      this.currentComponents = var1;
      ((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1)).method16(var1.getWidth(), var1.getHeight());
   }

   public int getVerticalSpacing() {
      return this.verticalSpacing.get();
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
   public Waila2 getCurrentComponents() {
      return this.currentComponents;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      @Override
      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         if (Waila.this.background.get()) {
            Waila.this.backgroundColor.method11(var1.method2(), var2, var3, this.getWidth(), this.getHeight());
         }

         if (Waila.this.border.get()) {
            Waila.this.borderColor.method11(var1.method2(), this, var2, var3, this.getWidth(), this.getHeight(), Waila.this.borderThickness.get());
         }

         int var5 = (int)var2;
         int var6 = (int)var3;
         Waila.this.currentComponents.method1(var1.method2(), Waila.this, var5, var6);
      }

      @Override
      public boolean method4(boolean var1) {
         return Waila.this.currentComponents != null;
      }
   }

   public class Data2 {
      private final com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila components;
      private final OwnedCosmetic cosmetic;
      private final AxisAlignedBBBridge bounds;

      public Data2(com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila var1, OwnedCosmetic var2, AxisAlignedBBBridge var3) {
         this.components = var1;
         this.cosmetic = var2;
         this.bounds = var3;
      }

      public com.moonsworth.lunar.client.framework.feature.waila.mixin.Waila method1() {
         return this.components;
      }

      public OwnedCosmetic method2() {
         return this.cosmetic;
      }

      public AxisAlignedBBBridge method3() {
         return this.bounds;
      }
   }
}
