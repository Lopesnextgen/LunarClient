package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;
import com.google.common.base.Ascii;

enum CaseFormat$5 {
   ;
   CaseFormat$5(CharMatcher predicateextension23, String text4) {
   }

   String normalizeWord(String text1) {
      return Ascii.toUpperCase(text1);
   }

   String convert(MixinHelperType_5 mixinhelpertype_51, String text2) {
      if (mixinhelpertype_51 == LOWER_HYPHEN) {
         return Ascii.toLowerCase(text2.replace('_', '-'));
      } else {
         return mixinhelpertype_51 == LOWER_UNDERSCORE ? Ascii.toLowerCase(text2) : super.convert(mixinhelpertype_51, text2);
      }
   }
}
