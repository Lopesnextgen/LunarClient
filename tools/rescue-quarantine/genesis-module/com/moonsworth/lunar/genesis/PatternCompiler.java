package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
interface PatternCompiler {
   CommonPattern method1(String text1);

   boolean isPcreLike();
}
