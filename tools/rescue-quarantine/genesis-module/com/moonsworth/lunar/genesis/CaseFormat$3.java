package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;
import com.google.common.base.Ascii;

enum CaseFormat$3 {
   ;
   CaseFormat$3(CharMatcher predicateextension23, String text4) {
   }

   String normalizeWord(String text1) {
      return MixinHelperType_5.access$100(text1);
   }

   String normalizeFirstWord(String text1) {
      return Ascii.toLowerCase(text1);
   }
}
