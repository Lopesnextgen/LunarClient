package com.moonsworth.lunar.client.framework.feature.rewind.holograms;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader2;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader3;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader32;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader4;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader5;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader52;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate3Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate3Iterator3;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Generated;

public class Holograms {
   private final Map<String, Supplier<Fishing2Iterator>> properties = new HashMap<>();
   private final Map<String, List<String>> field1 = new HashMap<>();

   public Holograms(RewindHandlers var1, Nameplate2 var2) {
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "camera",
            () -> List.of(
               new Fishing2Loader2(var2, var1.method45().method22()),
               new Fishing2Loader2(var2, var1.method45().method23()),
               new Fishing2Loader2(var2, var1.method45().method24()),
               new Fishing2Loader52(var2, var1.method45().method25()),
               new Fishing2Loader52(var2, var1.method45().method26()),
               new Fishing2Loader2(var2, var1.method45().method35()),
               new Fishing2Loader2(var2, var1.method45().method36()),
               new Fishing2Loader2(var2, var1.method45().method37())
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "speed",
            () -> List.of(
               new Fishing2Loader3(var2, var1.method41().method15()).OIOIHROIRRIOOORIRORRHCCRRRCIII(), new Fishing2Loader2(var2, var1.method41().method17())
            )
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "cameraShake",
            () -> List.of(new Fishing2Loader3(var2, var1.method45().method28()), new Fishing2Loader3(var2, var1.method45().method29())),
            var1.method45().method27()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
               var2,
               List.of("gameplay", "effect"),
               "cameraFov",
               () -> List.of(new Fishing2Loader3(var2, var1.method45().method34())),
               var1.method45().method30()
            )
            .CHIOOICRRCOIHHHROHOIHORRICHRCC()
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "sounds",
            () -> List.of(new Fishing2Loader2(var2, var1.method54().method17()), new Fishing2Loader3(var2, var1.method54().method19())),
            var1.method54().method16()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2, List.of(), "decode", () -> List.of(new Fishing2Loader2(var2, OptionFactory.method12("key").method31())), null
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("text"),
            "text",
            () -> List.of(
               new Fishing2Loader2(var2, ((Data)OptionFactory.method12("text").HIIIOHRRROCICIOIORRRIRCRCHHIII("Text")).method31()),
               new Fishing2Loader2(
                  var2,
                  ((com.moonsworth.lunar.client.config.option.DynamicDropdownOption.Data)OptionFactory.method19("font").HIIIOHRRROCICIOIORRRIRCRCHHIII("Arial"))
                     .method4(() -> {
                        String[] var0xx = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                        return new ArrayList<>(Arrays.asList(var0xx));
                     })
                     .method31()
               ),
               new Fishing2Loader5(
                  var2,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("position", new Double[]{0.0, 0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .method31()
               ),
               new Fishing2Loader5(
                  var2,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("rotation", new Double[]{0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .method31()
               ),
               new Fishing2Loader3(
                  var2,
                  ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                              "size"
                           )
                           .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(100))
                        .method7(0, 1000))
                     .method31()
               ),
               new Fishing2Loader4(
                  var2,
                  (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("color")
                        .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
                     .method31()
               )
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("image"),
            "image",
            () -> List.of(
               new Fishing2Loader5(
                  var2,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("position", new Double[]{0.0, 0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .method31()
               ),
               new Fishing2Loader5(
                  var2,
                  (MultiNumberOption)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22("rotation", new Double[]{0.0})
                        .method8(Codec.DOUBLE.listOf()))
                     .method31()
               ),
               new Fishing2Loader3(
                  var2,
                  ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                              "size"
                           )
                           .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(100))
                        .method7(0, 500))
                     .method31()
               ),
               new Fishing2Loader3(
                  var2,
                  ((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                              "opacity"
                           )
                           .OIRHOOIICOCIOOHICRRRICORIHHIHC(1.0))
                        .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.0, 1.0))
                     .method31()
               )
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("audio"),
            "audio",
            () -> List.of(
               new Fishing2Loader3(
                  var2,
                  ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                              "volume"
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(100.0F))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 500.0F))
                     .method31()
               ),
               new Fishing2Loader3(
                     var2,
                     ((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
                                 "speed"
                              )
                              .OIRHOOIICOCIOOHICRRRICORIHHIHC(1.0))
                           .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.1, 4.0))
                        .method31()
                  )
                  .OIOIHROIRRIOOORIRORRHCCRRRCIII()
            ),
            false,
            false,
            null
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "packs",
            () -> List.of(
               new Fishing2Loader2(var2, var1.method47().method19()),
               new Fishing2Loader2(var2, var1.method47().method21()),
               new Fishing2Loader2(var2, var1.method47().method22())
            ),
            var1.method47().method17()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2, List.of("gameplay", "effect"), "shader", () -> List.of(new Fishing2Loader2(var2, var1.method47().method29())), var1.method47().method28()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "chromaKeying",
            () -> List.of(
               new Fishing2Loader4(var2, var1.method52().method26()),
               new Fishing2Loader2(var2, var1.method52().method28()),
               new Fishing2Loader2(var2, var1.method52().method27())
            ),
            var1.method52().method15()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "worldRendering",
            () -> List.of(
               new Fishing2Loader2(var2, var1.method52().method16()),
               new Fishing2Loader2(var2, var1.method52().method17()),
               new Fishing2Loader2(var2, var1.method52().method19()),
               new Fishing2Loader2(var2, var1.method52().method21()),
               new Fishing2Loader2(var2, var1.method52().method23()),
               new Fishing2Loader2(var2, var1.method52().method22()),
               new Fishing2Loader2(var2, var1.method52().method24()),
               new Fishing2Loader2(var2, var1.method52().method25())
            ),
            var1.method52().method14()
         )
      );
      this.method1(
         () -> new Fishing2Iterator3(
            var2,
            List.of("gameplay", "effect"),
            "uiRendering",
            () -> List.of(
               new Fishing2Loader2(var2, var1.method53().method27()),
               new Fishing2Loader2(var2, var1.method53().method28()),
               new Fishing2Loader2(var2, var1.method53().method29()),
               new Fishing2Loader2(var2, var1.method53().method30()),
               new Fishing2Loader2(var2, var1.method53().method34()),
               new Fishing2Loader2(var2, var1.method53().method35()),
               new Fishing2Loader2(var2, var1.method53().method36()),
               new Fishing2Loader2(var2, var1.method53().method37()),
               new Fishing2Loader2(var2, var1.method53().method38()),
               new Fishing2Loader2(var2, var1.method53().method39()),
               new Fishing2Loader2(var2, var1.method53().method40())
            ),
            var1.method53().method26()
         )
      );
      this.method1(() -> new Nameplate2_2(var2, List.of("gameplay", "effect"), "entityOverrides"));
      this.method1(() -> new Nameplate3Iterator2(var2, List.of("entityOverrides"), "player", var1.method43()));
      this.method1(() -> new Nameplate3Iterator3(var2, List.of("entityOverrides"), "entity", var1.method43()));
   }

   private void method1(Supplier<Fishing2Iterator> var1) {
      Fishing2Iterator var2 = (Fishing2Iterator)var1.get();
      this.properties.put(var2.type(), var1);
      this.field1.put(var2.type(), var2.method10());
   }

   public Fishing2Iterator method2(Nameplate2 var1, String var2) {
      String var3 = var2.split("#")[0];
      Fishing2Iterator var4 = null;
      if (this.properties.containsKey(var3)) {
         var4 = this.properties.get(var3).get();
      } else {
         for (Framework7Extension var6 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (var6.getId().equals(var3)) {
               var4 = this.method3(var6, var1);
               break;
            }
         }
      }

      if (var4 != null) {
         if (var4 instanceof Fishing2Iterator3 var7) {
            var7.setType(var2);
         }

         var4.method19(true);
      }

      return var4;
   }

   private Fishing2Iterator method3(Framework7Extension var1, Nameplate2 var2) {
      return new Fishing2Iterator2(
         var2,
         var1,
         () -> {
            ArrayList var3 = new ArrayList();
            AlertExtension var4 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var4 != null) {
               var4.HROOOICICRCOCIROHIRICCCOCCIORH(var3x -> {
                  Fishing2Iterator var4x = this.method3(var3x, var2);
                  if (!var4x.method12().isEmpty()) {
                     var3.add(var4x);
                  }
               });
            }

            return var3;
         },
         () -> {
            ArrayList var2x = new ArrayList();
            MixinCore9Extension var3 = (MixinCore9Extension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
            if (var3 != null) {
               short var4 = 1920;
               short var5 = 1080;
               var2x.add(
                  new Fishing2Loader32(
                     var2,
                     ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                                 "x"
                              )
                              .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO((float)var3.method19(var4) / var4 * 100.0F))
                           .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 100.0F))
                        .method31(),
                     var3,
                     true
                  )
               );
               var2x.add(
                  new Fishing2Loader32(
                     var2,
                     ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                                 "y"
                              )
                              .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO((float)var3.method21(var5) / var5 * 100.0F))
                           .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 100.0F))
                        .method31(),
                     var3,
                     false
                  )
               );
            }

            Framework5 var8 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
            if (var8 != null) {
               for (ClientOption var6 : var8.method2()) {
                  if (!(var6 instanceof AbstractKeybindOption)) {
                     if (var6 instanceof ColorOption var7) {
                        var2x.add(new Fishing2Loader4(var2, var7));
                     } else if (var6.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7)) {
                        var2x.add(new Fishing2Loader3(var2, var6));
                     } else {
                        var2x.add(new Fishing2Loader2(var2, var6));
                     }
                  }
               }
            }

            return var2x;
         }
      );
   }

   @Generated
   public Map<String, List<String>> method4() {
      return this.field1;
   }
}
