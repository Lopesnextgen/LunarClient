package com.moonsworth.lunar.client.mod.skyblock.blazeslayer;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge4_3;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityWitherSkeletonBridge;
import com.moonsworth.lunar.bridge.EntityPigZombieBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent.SlayerBossSpawnEvent;
import com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockBlazeSlayer extends AbstractFeature {
   private final LocalPlayerNameListener field8 = (LocalPlayerNameListener)this.method3(LocalPlayerNameListener.class);
   private final AlertDisplayListener field9 = (AlertDisplayListener)this.method3(AlertDisplayListener.class);
   private static final Pattern field10 = Pattern.compile("^(?<mobTypes>[^ ]+ )?☠ Inferno Demonlord [IV]+ (.+)$");
   private static final Pattern field11 = Pattern.compile("^Spawned by: (.+)$");
   private static final Pattern field12 = Pattern.compile("^(ASHEN|SPIRIT|AURIC|CRYSTAL) ♨\\d .+$");
   private static final Pattern field13 = Pattern.compile("^\\ds \\d hits$");
   private static final String field14 = "eyJ0aW1lc3RhbXAiOjE0NzkxODY4NTAxNDQsInByb2ZpbGVJZCI6ImQzMGRjYzE3NzlmOTRlYTdhYTdiMTg4ZGU1N2E0M2FkIiwicHJvZmlsZU5hbWUiOiJoYW9oYW5rbGxpdSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWMyZTlkODM5NWNhY2Q5OTIyODY5YzE1MzczY2Y3Y2IxNmRhMGE1Y2U1ZjNjNjMyYjE5Y2ViMzkyOWM5YTExIn19fQ==";
   private static final ResourceLocationBridge field15 = ResourceLocationBridge.create("lunar", "mobs/blaze.png");
   private static final List<ParticleType> field16 = List.of(
      ParticleType.DUST,
      ParticleType.FLAME,
      ParticleType.SMOKE,
      ParticleType.WITCH,
      ParticleType.LARGE_SMOKE,
      ParticleType.EXPLOSION,
      ParticleType.AMBIENT_ENTITY_EFFECT,
      ParticleType.ENTITY_EFFECT,
      ParticleType.EXPLOSION_EMITTER
   );
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("blazeHpHUD").method4(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("attunementHUD").method4(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("firePillarHUD").method4(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("firePillarAlert").method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("colorAttunement").method4(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideBlazeBossParticles").method4(true))
      .method31();
   private final ObjectOpenHashSet<BridgeExtension> field23 = new ObjectOpenHashSet();
   private boolean field24;
   private BridgeExtension field25;
   private BridgeExtension field26;
   private BridgeExtension field27;
   private Component field28;
   private BridgeExtension field29;
   private BridgeExtension field30;
   private BridgeExtension field31;

   public SkyblockBlazeSlayer(Skyblock skyblock1) {
      super(false);
      this.method8(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method8(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockBlazeSlayer.Data()));
      this.method8(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method8(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method14(this::reset);
      this.handle(SlayerBossSpawnEvent.class, this::method1);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent.Data.class, arg1x -> this.reset());
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent.SlayerQuestFailedEvent.class, arg1x -> this.reset());
      this.handle(EventEntitySpawn.class, arg1x -> {
         this.method2(arg1x);
         this.method5(arg1x);
         this.method3(arg1x);
         this.method4(arg1x);
      });
      this.handle(EventEntityRemove.class, this::method6);
      this.handle(EventSpawnParticle.class, this::method7);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange.class, arg1x -> this.reset());
   }

   private void method1(SlayerBossSpawnEvent data31) {
      this.field24 = data31.method1().startsWith("Inferno Demonlord");
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if (this.field24) {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         if (itemcounter6extension2 != null) {
            if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge) {
               Component component3 = highlightimpl6_21.field1.bridge$getCustomName();
               if (component3 != null) {
                  String text4 = TextBridge.getTextContent(component3);
                  if (field10.matcher(text4).matches()) {
                     BridgeExtension bridgeextension5 = null;
                     BridgeExtension bridgeextension6 = null;
                     SkyblockBlazeSlayer.Type type7 = null;
                     boolean flag8 = false;

                     for (BridgeExtension bridgeextension11 : itemcounter6extension2.bridge$getEntities(
                        highlightimpl6_21.field1.bridge$getBoundingBox().method12(0.5, 1.0, 0.5), arg0 -> arg0 instanceof EntityArmorStandBridge || arg0 instanceof Bridge4_3
                     )) {
                        if (bridgeextension11 instanceof EntityArmorStandBridge) {
                           Component component12 = bridgeextension11.bridge$getCustomName();
                           if (component12 != null) {
                              String text13 = TextBridge.getTextContent(component12);
                              Matcher matcher14 = field12.matcher(text13);
                              if (matcher14.matches()) {
                                 bridgeextension6 = bridgeextension11;
                                 type7 = SkyblockBlazeSlayer.Type.valueOf(matcher14.group(1));
                              } else if (!flag8) {
                                 Matcher matcher15 = field11.matcher(text13);
                                 if (matcher15.matches()) {
                                    String text16 = matcher15.group(1);
                                    String text17 = this.field8.method5();
                                    if (text16.equals(text17)) {
                                       flag8 = true;
                                    }
                                 }
                              }
                           }
                        } else if (bridgeextension11 instanceof Bridge4_3) {
                           bridgeextension5 = bridgeextension11;
                        }
                     }

                     if (flag8 && bridgeextension5 != null) {
                        this.field25 = bridgeextension5;
                        this.field28 = highlightimpl6_21.field1.bridge$getCustomName();
                        if ((Boolean)this.field21.get()) {
                           this.field29 = bridgeextension6;
                           this.method8(this.field25, type7);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(EventEntitySpawn highlightimpl6_21) {
      if (this.field24 && this.field25 != null) {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         if (itemcounter6extension2 != null) {
            BridgeExtension bridgeextension3 = highlightimpl6_21.field1;
            if (bridgeextension3 instanceof EntityArmorStandBridge) {
               Component component4 = highlightimpl6_21.field1.bridge$getCustomName();
               if (component4 != null) {
                  String text5 = TextBridge.getTextContent(component4);
                  Matcher matcher6 = field12.matcher(text5);
                  if (matcher6.matches()) {
                     SkyblockBlazeSlayer.Type type7 = SkyblockBlazeSlayer.Type.valueOf(matcher6.group(1));
                     if (this.field25 != null && bridgeextension3.method13(this.field25) < 9.0) {
                        this.method8(this.field25, type7);
                        this.field29 = highlightimpl6_21.field1;
                     } else if (this.field26 != null && bridgeextension3.method13(this.field26) < 9.0 || this.field27 != null && bridgeextension3.method13(this.field27) < 9.0) {
                        this.field29 = highlightimpl6_21.field1;
                        if (type7 != SkyblockBlazeSlayer.Type.SPIRIT && type7 != SkyblockBlazeSlayer.Type.CRYSTAL) {
                           if (this.field26 != null) {
                              this.method8(this.field26, type7);
                           }
                        } else if (this.field27 != null) {
                           this.method8(this.field27, type7);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventEntitySpawn highlightimpl6_21) {
      if (this.field24 && this.field25 != null) {
         if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
            ItemStackBridge bridgeextension_47 = bridgeextension_22.bridge$getMainHand();
            if ((bridgeextension_47 == null || bridgeextension_47.bridge$isEmpty()) && this.field30 != null) {
               Component component8 = bridgeextension_22.bridge$getCustomName();
               if (component8 == null) {
                  return;
               }

               String text5 = TextBridge.getTextContent(component8);
               if (!field13.matcher(text5).matches()) {
                  return;
               }

               if (bridgeextension_22.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field30) > 16.0) {
                  return;
               }

               this.field31 = highlightimpl6_21.field1;
               if ((Boolean)this.field20.get()) {
                  TextComponent text6 = Component.text(this.method6("firePillar", new Object[0]), NamedTextColor.RED);
                  this.field9
                     .method2(
                        ComparableImpl.method2()
                           .method1("FIRE_PILLAR")
                           .method2(text6)
                           .method3(2000L)
                           .method4(com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type.HIGH)
                           .method6()
                     );
               }
            } else if (bridgeextension_47 != null) {
               Optional optional4 = SkyblockItemUtil.method11(bridgeextension_47);
               if (optional4.isEmpty()
                  || !((String)optional4.get())
                     .equals(
                        "eyJ0aW1lc3RhbXAiOjE0NzkxODY4NTAxNDQsInByb2ZpbGVJZCI6ImQzMGRjYzE3NzlmOTRlYTdhYTdiMTg4ZGU1N2E0M2FkIiwicHJvZmlsZU5hbWUiOiJoYW9oYW5rbGxpdSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWMyZTlkODM5NWNhY2Q5OTIyODY5YzE1MzczY2Y3Y2IxNmRhMGE1Y2U1ZjNjNjMyYjE5Y2ViMzkyOWM5YTExIn19fQ=="
                     )) {
                  return;
               }

               if (bridgeextension_22.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field25) > 16.0) {
                  return;
               }

               this.field30 = highlightimpl6_21.field1;
            }
         }
      }
   }

   private void method5(EventEntitySpawn highlightimpl6_21) {
      if (this.field24 && this.field25 != null) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         if (bridgeextension2 instanceof EntityWitherSkeletonBridge) {
            if (bridgeextension2.method13(this.field25) > 16.0) {
               return;
            }

            this.field27 = bridgeextension2;
         } else if (bridgeextension2 instanceof EntityPigZombieBridge) {
            if (bridgeextension2.method13(this.field25) > 16.0) {
               return;
            }

            this.field26 = bridgeextension2;
         }
      }
   }

   private void method6(EventEntityRemove highlightimpl121) {
      if (this.field24) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         if (bridgeextension2 == this.field25) {
            this.field25 = null;
         } else if (bridgeextension2 == this.field30) {
            this.field30 = null;
         } else if (bridgeextension2 == this.field31) {
            this.field30 = null;
            this.field31 = null;
         } else if (bridgeextension2 == this.field29) {
            this.field29 = null;
         } else if (bridgeextension2 == this.field28) {
            this.field28 = null;
         } else if (bridgeextension2 == this.field26) {
            this.field26 = null;
         } else if (bridgeextension2 == this.field27) {
            this.field27 = null;
         }
      }
   }

   private void method7(EventSpawnParticle highlightimpl151) {
      if ((Boolean)this.field22.get()) {
         if (this.field24) {
            if (this.field25 != null) {
               if (!(this.field25.method15(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ()) > 16.0)) {
                  ParticleType horsestatstype22 = highlightimpl151.method2();
                  if (field16.contains(horsestatstype22)) {
                     highlightimpl151.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private void method8(BridgeExtension bridgeextension1, SkyblockBlazeSlayer.Type type2) {
      if (bridgeextension1 != null && type2 != null) {
         Color color3 = switch (type2) {
            case ASHEN -> Color.GRAY;
            case AURIC -> Color.YELLOW;
            case SPIRIT -> Color.WHITE;
            case CRYSTAL -> Color.CYAN;
         };
         bridgeextension1.bridge$setGlowing(true);
         bridgeextension1.bridge$setGlowingColor(color3.getRGB());
         this.field23.add(bridgeextension1);
      }
   }

   private void reset() {
      this.field25 = null;
      this.field28 = null;
      this.field29 = null;
      this.field30 = null;
      this.field31 = null;
      this.field24 = false;
      ObjectIterator objectiterator1 = this.field23.iterator();

      while (objectiterator1.hasNext()) {
         BridgeExtension bridgeextension2 = (BridgeExtension)objectiterator1.next();
         bridgeextension2.bridge$setGlowing(false);
      }

      this.field23.clear();
   }

   private boolean method13() {
      return (Boolean)this.field19.get() || (Boolean)this.field18.get() || (Boolean)this.field17.get();
   }

   public String getId() {
      return "SKYBLOCK_BLAZE_SLAYER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field17, this.field18, this.field19, this.field20, this.field21, this.field22});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_CENTER, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 20, 100, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.method3(
               Component.text("☠ Inferno Demonlord IV 16.2M❤", NamedTextColor.RED),
               Component.text("CRYSTAL ♨5", NamedTextColor.AQUA),
               Component.text("3s 2 hits", NamedTextColor.RED)
            )
            : this.method3(
               SkyblockBlazeSlayer.this.field28,
               SkyblockBlazeSlayer.this.field29 == null ? null : SkyblockBlazeSlayer.this.field29.bridge$getCustomName(),
               SkyblockBlazeSlayer.this.field31 == null ? null : SkyblockBlazeSlayer.this.field31.bridge$getCustomName()
            );
      }

      private List<HudLine> method3(@Nullable Component component1, @Nullable Component component2, @Nullable Component component3) {
         ArrayList list4 = new ArrayList();
         if ((Boolean)SkyblockBlazeSlayer.this.field17.get() && component1 != null) {
            list4.add(new HudLine(SkyblockBlazeSlayer.field15, component1));
         }

         if ((Boolean)SkyblockBlazeSlayer.this.field18.get() && component2 != null) {
            list4.add(new HudLine(Bridge.method28().method18(), component2));
         }

         if ((Boolean)SkyblockBlazeSlayer.this.field19.get() && component3 != null) {
            list4.add(new HudLine(Bridge.method28().method31(), component3));
         }

         return list4;
      }

      public boolean method4(boolean flag1) {
         return !SkyblockBlazeSlayer.this.method13() ? false : super.method4(flag1);
      }

      public boolean method30() {
         return !SkyblockBlazeSlayer.this.method13() ? false : super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }

   private enum Type {
      SPIRIT,
      CRYSTAL,
      ASHEN,
      AURIC;

      Type() {
      }
   }
}
