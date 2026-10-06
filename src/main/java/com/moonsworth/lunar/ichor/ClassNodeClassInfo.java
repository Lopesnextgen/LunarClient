package com.moonsworth.lunar.ichor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cadixdev.bombe.analysis.InheritanceProvider;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo.Impl;

class ClassNodeClassInfo extends Impl {
   ClassNodeClassInfo(String text, boolean flag, String text2, List list, Map map, Map map2, Map map3) {
      super(text, flag, text2, list, map, map2, map3);
   }

   public synchronized Set<ClassInfo> provideParents(InheritanceProvider provider) {
      return super.provideParents(provider);
   }
}
