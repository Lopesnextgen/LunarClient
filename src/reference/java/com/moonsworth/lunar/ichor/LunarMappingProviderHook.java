package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import org.cadixdev.lorenz.MappingSet;

@Annotation3
public interface LunarMappingProviderHook {
   @Annotation8(
      OORRORHHCIICCICOOOIOOHRRHCOIHI = true,
      RHRRICIHIHHROIRCHHROOOHHOCHORH = true,
      COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true,
      HIHCIHIOOCIORRCIOROCCCRCOOICCR = true
   )
   default MappingSet method1(URLClassLoader var1) {
      IchorPipeline var2 = var1.method1();
      return var2.method10(Files.Data2.field12)
         .orElseThrow(
            () -> new FatalIchorError("Can't find mappings intermediary2lunar_${mcVer}.kin for " + Config.method36(var1.method1().method34().method6()).getId())
         );
   }
}
