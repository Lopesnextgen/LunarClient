package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.hash.Funnel;

interface NumberBase2$Extension extends Serializable {
   <T> boolean put(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4);

   <T> boolean mightContain(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4);

   int ordinal();
}
