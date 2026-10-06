package com.moonsworth.lunar.genesis;

import java.util.List;
import com.google.common.io.LineProcessor;
import com.google.common.collect.Lists;

final class Resources$1 implements LineProcessor<List<String>> {
   final List<String> field1 = Lists.newArrayList();

   Resources$1() {
   }

   public boolean processLine(String text1) {
      this.field1.add(text1);
      return true;
   }

   public List<String> getResult() {
      return this.field1;
   }
}
