package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.event.mixin.holograms.EventOptionsReloadBase.EventOptionsReload;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SettingsOverrideDebug extends AbstractFeature {
   @ConstantName
   private static final String field8 = "SETTINGS_OVERRIDE_DEBUG";
   private final EnumMap<SettingsOverrideDebug.Type, Map<String, Pair<ClientOption<?>, SettingsOverrideDebug.Data<?>>>> field9 = new EnumMap<SettingsOverrideDebug.Type, Map<String, Pair<ClientOption<?>, SettingsOverrideDebug.Data<?>>>>(
      SettingsOverrideDebug.Type.class
   ) {
      {
         for (SettingsOverrideDebug.Type type6 : SettingsOverrideDebug.Type.values()) {
            this.put(type6, new HashMap<>());
         }
      }
   };
   private MultiSelectOption field10;
   private MultiSelectOption field11;
   private Consumer<EventOptionsReload> field12 = arg1 -> this.method5();
   private boolean field13 = true;

   public SettingsOverrideDebug() {
      super(true);
      LunarEventBus.method29().method2(EventOptionsReload.class, this.field12);
      this.method12(EventServerChange.class, arg1 -> this.method7(true), 50);
   }

   public String getId() {
      return "SETTINGS_OVERRIDE_DEBUG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      if (this.field13) {
         this.field13 = false;
      } else {
         LunarEventBus.method29().method6(EventOptionsReload.class, this.field12);
         this.field12 = null;
         if (this.field10 == null) {
            this.field10 = (MultiSelectOption)((MultiSelectOption)OptionFactory.method27("modOverrides").method3(this.method13()).method5(arg1x -> {
               if (!this.field10.contains(arg1x)) {
                  Map map2 = this.field9.get(SettingsOverrideDebug.Type.MOD);

                  for (Entry entry4 : SettingsOverrideDebug.Type.MOD.getOptionIds().entrySet()) {
                     Pair pair5 = (Pair)map2.get(entry4.getKey());
                     if (pair5 != null) {
                        SettingsOverrideDebug.Data data6 = (SettingsOverrideDebug.Data)pair5.value();
                        SettingIntercept alert27 = this.method4((SettingIntercept<?, ?>)entry4.getValue());
                        alert27.method1(this.method5((ClientOption<?>)pair5.key()), data6.method1(), data6.method2().orElse(null));
                     }
                  }
               }

               this.method5();
               LcuiScreen.method145();
            }).method31()).HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
               this.method5();
               this.method7(true);
            });
         }

         if (this.field11 == null) {
            this.field11 = (MultiSelectOption)((MultiSelectOption)OptionFactory.method27("optionOverrides").method3(this.method14()).method5(arg1x -> {
               if (!this.field11.contains(arg1x)) {
                  for (SettingsOverrideDebug.Type type5 : SettingsOverrideDebug.Type.values()) {
                     if (type5 != SettingsOverrideDebug.Type.MOD) {
                        Map map6 = this.field9.get(type5);

                        for (Entry entry8 : type5.getOptionIds().entrySet()) {
                           Pair pair9 = (Pair)map6.get(entry8.getKey());
                           if (pair9 != null) {
                              SettingsOverrideDebug.Data data10 = (SettingsOverrideDebug.Data)pair9.value();
                              SettingIntercept alert211 = this.method4((SettingIntercept<?, ?>)entry8.getValue());
                              alert211.method1(this.method5((ClientOption<?>)pair9.key()), data10.method1(), data10.method2().orElse(null));
                           }
                        }
                     }
                  }
               }

               this.method5();
               LcuiScreen.method145();
            }).method31()).HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
               this.method5();
               this.method7(true);
            });
         }

         for (Map map3 : this.field9.values()) {
            map3.clear();
         }

         lightingextension231.method1("modOverrides", arg1x -> {
            Set set2 = Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH();

            for (String text4 : (Set)this.field10.get()) {
               set2.stream().filter(arg1xx -> arg1xx.getId().equals(text4)).findFirst().map(arg1xx -> {
                  SettingIntercept alert22x = (SettingIntercept)arg1xx.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
                  if (alert22x == null) {
                     return null;
                  }

                  ToggleOption lightingextension4433x = this.method12(arg1xx, alert22x);
                  this.field9.get(SettingsOverrideDebug.Type.MOD).put(arg1xx.getId(), Pair.of(lightingextension4433x, new SettingsOverrideDebug.Data(alert22x)));
                  return lightingextension4433x;
               }).ifPresent(arg1xx -> arg1x.method9(new ClientOption[]{arg1xx}));
            }
         });
         lightingextension231.method1("optionOverrides", arg1x -> {
            Set set2 = Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
            Collection list3x = Ref.method4().method41().method2().values();

            label54:
            for (String text5 : (Set)this.field11.get()) {
               for (Framework7Extension framework7extension7 : set2) {
                  OptionContainer framework58 = (OptionContainer)framework7extension7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
                  if (framework58 != null) {
                     for (ClientOption lightingextension10 : framework58.method2()) {
                        if (lightingextension10.getId().equals(text5)) {
                           this.method6(arg1x, SettingsOverrideDebug.Type.OPTION, lightingextension10);
                           continue label54;
                        }
                     }
                  }
               }

               for (ItemSetHandler foghandler12 : list3x) {
                  for (ClientOption lightingextension14 : foghandler12.method13()) {
                     if (lightingextension14.getId().equals(text5)) {
                        this.method6(arg1x, SettingsOverrideDebug.Type.MANAGER, lightingextension14);
                        continue label54;
                     }
                  }
               }
            }
         });
         lightingextension231.method1("overrides", arg1x -> arg1x.method9(new ClientOption[]{this.field10, this.field11}));
      }
   }

   private Set<String> method13() {
      return Ref.method4()
         .method40()
         .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         .stream()
         .filter(arg0 -> arg0.method3(ModTraits.field4).flatMap(SettingIntercept::method3).isPresent())
         .<String>map(Framework7Extension::getId)
         .collect(Collectors.toSet());
   }

   private Set<String> method14() {
      Set set1 = Ref.method4()
         .method40()
         .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         .stream()
         .flatMap(arg0 -> {
            OptionContainer framework51x = (OptionContainer)arg0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            return framework51x == null ? Stream.empty() : framework51x.method2().stream();
         })
         .filter(arg0 -> arg0.method3(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent())
         .map(ClientOption::getId)
         .collect(Collectors.toSet());
      set1.addAll(
         Ref.method4()
            .method41()
            .method2()
            .values()
            .stream()
            .flatMap(arg0 -> arg0.method13().stream())
            .filter(arg0 -> arg0.method3(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent())
            .map(ClientOption::getId)
            .collect(Collectors.toSet())
      );
      return set1;
   }

   private SettingIntercept<?, ?> method4(SettingIntercept<?, ?> alert21) {
      return alert21 instanceof ClientOption lightingextension2 && lightingextension2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field14) instanceof SettingIntercept alert23 ? alert23 : alert21;
   }

   private Object method5(ClientOption<?> lightingextension1) {
      OptionFeatureLink nameplate32 = (OptionFeatureLink)lightingextension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field8);
      return nameplate32 != null ? nameplate32.getFeature() : lightingextension1;
   }

   private void method6(
      com.moonsworth.lunar.client.config.option.RootSettingsBuilder.Data data1, @Nullable SettingsOverrideDebug.Type type2, ClientOption<?> lightingextension3
   ) {
      String text4 = lightingextension3.getName();
      boolean flag6 = false;
      Object obj7 = lightingextension3.get();
      Object obj5;
      if (obj7 instanceof Boolean flag8) {
         ToggleOption lightingextension44315 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7(lightingextension3.getId()).OOOIROIIOCOOHICRIRHHHRROHHHHIO(flag8))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         lightingextension44315.HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg1x -> lightingextension3.method3(OptionTraits.field5).ifPresent(arg2x -> arg2x.method1(lightingextension3, OverrideSource.SERVER, arg1x))
         );
         obj5 = lightingextension44315;
      } else if (obj7 instanceof Integer number9) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate16 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                        lightingextension3.getId()
                     )
                     .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(number9))
                  .method7((Integer)nameplate16.getMin(), (Integer)nameplate16.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else if (obj7 instanceof Float value10) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate17 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                        lightingextension3.getId()
                     )
                     .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(value10))
                  .HRRCROICHIIROIHRCOIHRRHCCRIIRH((Float)nameplate17.getMin(), (Float)nameplate17.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else if (obj7 instanceof Double value11) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate18 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                        lightingextension3.getId()
                     )
                     .OIRHOOIICOCIOOHICRRRICORIHHIHC(value11))
                  .RIIIOHCCHRRRORICCHIIHHOORIIOIR((Double)nameplate18.getMin(), (Double)nameplate18.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else if (obj7 instanceof Long number12) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate19 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.LongOption.Data)((com.moonsworth.lunar.client.config.option.LongOption.Data)((com.moonsworth.lunar.client.config.option.LongOption.Data)OptionFactory.method3(
                        lightingextension3.getId()
                     )
                     .HRHCHRICROCCHOHOROROIRIICHCRHH(number12))
                  .HRICOROOOCCOCOROCRHHCRRIRCOICO((Long)nameplate19.getMin(), (Long)nameplate19.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else if (obj7 instanceof Short number13) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate20 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.ShortOption.Data)((com.moonsworth.lunar.client.config.option.ShortOption.Data)((com.moonsworth.lunar.client.config.option.ShortOption.Data)OptionFactory.method5(
                        lightingextension3.getId()
                     )
                     .HRICOROOOCCOCOROCRHHCRRIRCOICO(number13))
                  .HORHROIOIOICIRHIOCOICHHHIHCIIO((Short)nameplate20.getMin(), (Short)nameplate20.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else if (obj7 instanceof Byte number14) {
         com.moonsworth.lunar.client.config.option.NumberRule nameplate21 = (com.moonsworth.lunar.client.config.option.NumberRule)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         obj5 = ((com.moonsworth.lunar.client.config.option.ByteOption.Data)((com.moonsworth.lunar.client.config.option.ByteOption.Data)((com.moonsworth.lunar.client.config.option.ByteOption.Data)OptionFactory.method6(
                        lightingextension3.getId()
                     )
                     .HRICOROOOCCOCOROCRHHCRRIRCOICO(number14))
                  .HORHROIOIOICIRHIOCOICHHHIHCIIO((Byte)nameplate21.getMin(), (Byte)nameplate21.getMin()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(text4))
            .method31();
         flag6 = true;
      } else {
         obj5 = null;
      }

      if (flag6) {
         obj5.method8(
            arg1x -> lightingextension3.method3(OptionTraits.field5).ifPresent(arg2x -> arg2x.method1(lightingextension3, OverrideSource.SERVER, (Number)arg1x))
         );
      }

      if (obj5 != null) {
         data1.HORHROIOIOICIRHIOCOICHHHIHCIIO((ClientOption)obj5, arg2x -> {
            for (ClientOption lightingextension4x : lightingextension3.getChildren()) {
               this.method6(arg2x, null, lightingextension4x);
            }
         });
         if (type2 != null) {
            this.field9
               .get(type2)
               .put(lightingextension3.getId(), Pair.of(obj5, new SettingsOverrideDebug.Data((SettingIntercept)lightingextension3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(OptionTraits.field5))));
         }
      }
   }

   private void method7(boolean flag1) {
      if (flag1) {
         for (SettingsOverrideDebug.Type type5 : SettingsOverrideDebug.Type.values()) {
            Map map6 = this.field9.get(type5);

            for (Entry entry8 : type5.getOptionIds().entrySet()) {
               Pair pair9 = (Pair)map6.get(entry8.getKey());
               if (pair9 != null) {
                  SettingIntercept alert210 = this.method4((SettingIntercept<?, ?>)entry8.getValue());
                  alert210.method1(this.method5((ClientOption<?>)pair9.key()), OverrideSource.SERVER, ((ClientOption)pair9.key()).get());
               }
            }
         }

         this.method15();
      } else {
         for (SettingsOverrideDebug.Type type15 : SettingsOverrideDebug.Type.values()) {
            Map map16 = this.field9.get(type15);

            for (Entry entry18 : type15.getOptionIds().entrySet()) {
               Pair pair19 = (Pair)map16.get(entry18.getKey());
               if (pair19 != null) {
                  SettingIntercept alert220 = this.method4((SettingIntercept<?, ?>)entry18.getValue());
                  SettingsOverrideDebug.Data data11 = (SettingsOverrideDebug.Data)pair19.value();
                  if (data11 != null) {
                     alert220.method1(this.method5((ClientOption<?>)pair19.key()), data11.method1(), data11.method2().orElse(null));
                  } else {
                     alert220.method1(this.method5((ClientOption<?>)pair19.key()), OverrideSource.SERVER, null);
                  }
               }
            }
         }
      }
   }

   private void method15() {
      this.field10.method7().clear();
      this.field10.method7().addAll(this.method13());
      this.field11.method7().clear();
      this.field11.method7().addAll(this.method14());
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   public void method1(JsonObject json1) {
   }

   public void method3(boolean flag1) {
      if (!this.field13
         && Ref.method4().method40() != null
         && Ref.method4().method41() != null
         && this.field10 != null
         && this.field11 != null) {
         this.method7(flag1);
      }
   }

   private ToggleOption method12(Framework7Extension framework7extension1, SettingIntercept<Framework7Extension, Boolean> alert22) {
      ModDetails framework83 = (ModDetails)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
      ToggleOption lightingextension4434 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7(framework7extension1.getId()).OOOIROIIOCOOHICRIRHHHRROHHHHIO(framework7extension1.isEnabled()))
               .ROICHOCCIOCHCIHOIHIHICCORIROCC(framework83 != null ? framework83.getName() : framework7extension1.getId()))
            .method18(framework7extension1))
         .method31();
      lightingextension4434.method16(OptionTraits.field14, alert22);
      lightingextension4434.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg2x -> alert22.method1(framework7extension1, OverrideSource.SERVER, arg2x));
      return lightingextension4434;
   }

   private class Data<V> {
      private final OverrideSource field1;
      private final Optional<V> field2;

      public Data(SettingIntercept<?, V> alert21) {
         this(alert21.method2(), alert21.method3());
      }

      private Data(OverrideSource alerttype1, Optional<V> optional2) {
         this.field1 = alerttype1;
         this.field2 = optional2;
      }

      public OverrideSource method1() {
         return this.field1;
      }

      public Optional<V> method2() {
         return this.field2;
      }
   }

   private enum Type {
      MOD {
         @Override
         public Map<String, SettingIntercept> getOptionIds() {
            HashMap map1 = new HashMap();

            for (Framework7Extension framework7extension3 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
               if (!framework7extension3.getId().equals("SETTINGS_OVERRIDE_DEBUG")) {
                  SettingIntercept alert24 = (SettingIntercept)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
                  if (alert24 != null) {
                     map1.put(framework7extension3.getId(), alert24);
                  }
               }
            }

            return map1;
         }
      },
      OPTION {
         @Override
         public Map<String, SettingIntercept> getOptionIds() {
            HashMap map1 = new HashMap();

            for (Framework7Extension framework7extension3 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
               if (!framework7extension3.getId().equals("SETTINGS_OVERRIDE_DEBUG")) {
                  OptionContainer framework54 = (OptionContainer)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
                  if (framework54 != null) {
                     for (ClientOption lightingextension6 : framework54.method2()) {
                        SettingIntercept alert27 = (SettingIntercept)lightingextension6.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field5);
                        if (alert27 != null) {
                           map1.put(lightingextension6.getId(), alert27);
                        }
                     }
                  }
               }
            }

            return map1;
         }
      },
      MANAGER {
         @Override
         public Map<String, SettingIntercept> getOptionIds() {
            HashMap map1 = new HashMap();

            for (ItemSetHandler foghandler3 : Ref.method4().method41().method2().values()) {
               for (ClientOption lightingextension5 : foghandler3.method13()) {
                  SettingIntercept alert26 = (SettingIntercept)lightingextension5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field5);
                  if (alert26 != null) {
                     map1.put(lightingextension5.getId(), alert26);
                  }
               }
            }

            return map1;
         }
      };

      public abstract Map<String, SettingIntercept> getOptionIds();

      @Generated
      Type() {
      }
   }
}
