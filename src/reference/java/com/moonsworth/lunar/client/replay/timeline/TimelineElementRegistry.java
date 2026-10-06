package com.moonsworth.lunar.client.replay.timeline;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.replay.timeline.BooleanProperty;
import com.moonsworth.lunar.client.replay.timeline.NumberProperty;
import com.moonsworth.lunar.client.replay.timeline.HudPositionProperty;
import com.moonsworth.lunar.client.replay.timeline.ColorProperty;
import com.moonsworth.lunar.client.replay.timeline.ListProperty;
import com.moonsworth.lunar.client.replay.timeline.TransformProperty;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.ModPropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.AnimatedPropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.EntityOverridesCategory;
import com.moonsworth.lunar.client.replay.timeline.PlayerOverrideGroup;
import com.moonsworth.lunar.client.replay.timeline.EntityIdOverrideGroup;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Generated;

public class TimelineElementRegistry {
   private final Map<String, Supplier<PropertyGroup>> properties = new HashMap<>();
   private final Map<String, List<String>> field1 = new HashMap<>();

   public TimelineElementRegistry(RewindHandlers rewindhandlers1, UndoRedoManager nameplate22) {
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "camera",
            () -> List.of(
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method22()),
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method23()),
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method24()),
               new TransformProperty(nameplate22, rewindhandlers1.method45().method25()),
               new TransformProperty(nameplate22, rewindhandlers1.method45().method26()),
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method35()),
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method36()),
               new BooleanProperty(nameplate22, rewindhandlers1.method45().method37())
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "speed",
            () -> List.of(
               new NumberProperty(nameplate22, rewindhandlers1.method41().method15()).OIOIHROIRRIOOORIRORRHCCRRRCIII(), new BooleanProperty(nameplate22, rewindhandlers1.method41().method17())
            )
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "cameraShake",
            () -> List.of(new NumberProperty(nameplate22, rewindhandlers1.method45().method28()), new NumberProperty(nameplate22, rewindhandlers1.method45().method29())),
            rewindhandlers1.method45().method27()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
               nameplate22,
               List.of("gameplay", "effect"),
               "cameraFov",
               () -> List.of(new NumberProperty(nameplate22, rewindhandlers1.method45().method34())),
               rewindhandlers1.method45().method30()
            )
            .CHIOOICRRCOIHHHROHOIHORRICHRCC()
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "sounds",
            () -> List.of(new BooleanProperty(nameplate22, rewindhandlers1.method54().method17()), new NumberProperty(nameplate22, rewindhandlers1.method54().method19())),
            rewindhandlers1.method54().method16()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22, List.of(), "decode", () -> List.of(new BooleanProperty(nameplate22, OptionFactory.method12("key").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH())), null
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("text"),
            "text",
            () -> List.of(
               new BooleanProperty(nameplate22, ((Data)OptionFactory.method12("text").HIIIOHRRROCICIOIORRRIRCRCHHIII("Text")).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()),
               new BooleanProperty(
                  nameplate22,
                  ((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19("font").HIIIOHRRROCICIOIORRRIRCRCHHIII("Arial"))
                     .method4(() -> {
                        String[] items0xx = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                        return new ArrayList<>(Arrays.asList(items0xx));
                     })
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new ListProperty(
                  nameplate22,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("position", new Double[]{0.0, 0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new ListProperty(
                  nameplate22,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("rotation", new Double[]{0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new NumberProperty(
                  nameplate22,
                  ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                              "size"
                           )
                           .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(100))
                        .OCRRICRIORICCCRHIOHORCICIHHICO(0, 1000))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new ColorProperty(
                  nameplate22,
                  (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("color")
                        .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               )
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("image"),
            "image",
            () -> List.of(
               new ListProperty(
                  nameplate22,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("position", new Double[]{0.0, 0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new ListProperty(
                  nameplate22,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("rotation", new Double[]{0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new NumberProperty(
                  nameplate22,
                  ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                              "size"
                           )
                           .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(100))
                        .OCRRICRIORICCCRHIOHORCICIHHICO(0, 500))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new NumberProperty(
                  nameplate22,
                  ((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                              "opacity"
                           )
                           .OIRHOOIICOCIOOHICRRRICORIHHIHC(1.0))
                        .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.0, 1.0))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               )
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("audio"),
            "audio",
            () -> List.of(
               new NumberProperty(
                  nameplate22,
                  ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "volume"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(100.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 500.0F))
                     .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
               ),
               new NumberProperty(
                     nameplate22,
                     ((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                                 "speed"
                              )
                              .OIRHOOIICOCIOOHICRRRICORIHHIHC(1.0))
                           .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.1, 4.0))
                        .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH()
                  )
                  .OIOIHROIRRIOOORIRORRHCCRRRCIII()
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "packs",
            () -> List.of(
               new BooleanProperty(nameplate22, rewindhandlers1.method47().method19()),
               new BooleanProperty(nameplate22, rewindhandlers1.method47().method21()),
               new BooleanProperty(nameplate22, rewindhandlers1.method47().method22())
            ),
            rewindhandlers1.method47().method17()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22, List.of("gameplay", "effect"), "shader", () -> List.of(new BooleanProperty(nameplate22, rewindhandlers1.method47().method29())), rewindhandlers1.method47().method28()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "chromaKeying",
            () -> List.of(
               new ColorProperty(nameplate22, rewindhandlers1.method52().method26()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method28()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method27())
            ),
            rewindhandlers1.method52().method15()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "worldRendering",
            () -> List.of(
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method16()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method17()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method19()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method21()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method23()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method22()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method24()),
               new BooleanProperty(nameplate22, rewindhandlers1.method52().method25())
            ),
            rewindhandlers1.method52().method14()
         )
      );
      this.method1(
         () -> new AnimatedPropertyGroup(
            nameplate22,
            List.of("gameplay", "effect"),
            "uiRendering",
            () -> List.of(
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method27()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method28()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method29()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method30()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method34()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method35()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method36()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method37()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method38()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method39()),
               new BooleanProperty(nameplate22, rewindhandlers1.method53().method40())
            ),
            rewindhandlers1.method53().method26()
         )
      );
      this.method1(() -> new EntityOverridesCategory(nameplate22, List.of("gameplay", "effect"), "entityOverrides"));
      this.method1(() -> new PlayerOverrideGroup(nameplate22, List.of("entityOverrides"), "player", rewindhandlers1.method43()));
      this.method1(() -> new EntityIdOverrideGroup(nameplate22, List.of("entityOverrides"), "entity", rewindhandlers1.method43()));
   }

   private void method1(Supplier<PropertyGroup> supplier1) {
      PropertyGroup fishing2iterator2 = (PropertyGroup)supplier1.get();
      this.properties.put(fishing2iterator2.type(), supplier1);
      this.field1.put(fishing2iterator2.type(), fishing2iterator2.method10());
   }

   public PropertyGroup method2(UndoRedoManager nameplate21, String text2) {
      String text3 = text2.split("#")[0];
      PropertyGroup fishing2iterator4 = null;
      if (this.properties.containsKey(text3)) {
         fishing2iterator4 = this.properties.get(text3).get();
      } else {
         for (Framework7Extension framework7extension6 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (framework7extension6.getId().equals(text3)) {
               fishing2iterator4 = this.method3(framework7extension6, nameplate21);
               break;
            }
         }
      }

      if (fishing2iterator4 != null) {
         if (fishing2iterator4 instanceof AnimatedPropertyGroup fishing2iterator37) {
            fishing2iterator37.setType(text2);
         }

         fishing2iterator4.method19(true);
      }

      return fishing2iterator4;
   }

   private PropertyGroup method3(Framework7Extension framework7extension1, UndoRedoManager nameplate22) {
      return new ModPropertyGroup(
         nameplate22,
         framework7extension1,
         () -> {
            ArrayList list3 = new ArrayList();
            ModChildren alertextension4 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
            if (alertextension4 != null) {
               alertextension4.method2(arg3x -> {
                  PropertyGroup fishing2iterator4x = this.method3(arg3x, nameplate22);
                  if (!fishing2iterator4x.method12().isEmpty()) {
                     list3.add(fishing2iterator4x);
                  }
               });
            }

            return list3;
         },
         () -> {
            ArrayList list2x = new ArrayList();
            MixinCore9Extension mixincore9extension3 = (MixinCore9Extension)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1);
            if (mixincore9extension3 != null) {
               short number4 = 1920;
               short number5 = 1080;
               list2x.add(
                  new HudPositionProperty(
                     nameplate22,
                     ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                                 "x"
                              )
                              .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO((float)mixincore9extension3.method19(number4) / number4 * 100.0F))
                           .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 100.0F))
                        .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH(),
                     mixincore9extension3,
                     true
                  )
               );
               list2x.add(
                  new HudPositionProperty(
                     nameplate22,
                     ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                                 "y"
                              )
                              .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO((float)mixincore9extension3.method21(number5) / number5 * 100.0F))
                           .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 100.0F))
                        .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH(),
                     mixincore9extension3,
                     false
                  )
               );
            }

            OptionContainer framework58 = (OptionContainer)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            if (framework58 != null) {
               for (ClientOption lightingextension6 : framework58.method2()) {
                  if (!(lightingextension6 instanceof AbstractKeybindOption)) {
                     if (lightingextension6 instanceof ColorOption lightingextension42227) {
                        list2x.add(new ColorProperty(nameplate22, lightingextension42227));
                     } else if (lightingextension6.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7)) {
                        list2x.add(new NumberProperty(nameplate22, lightingextension6));
                     } else {
                        list2x.add(new BooleanProperty(nameplate22, lightingextension6));
                     }
                  }
               }
            }

            return list2x;
         }
      );
   }

   @Generated
   public Map<String, List<String>> method4() {
      return this.field1;
   }
}
