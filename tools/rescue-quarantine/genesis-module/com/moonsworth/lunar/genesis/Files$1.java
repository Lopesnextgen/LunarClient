package com.moonsworth.lunar.genesis;

import java.util.List;
import com.google.common.io.LineProcessor;
import com.google.common.collect.Lists;

final class Files$1 implements LineProcessor<List<String>> {
   final List<String> field1 = Lists.newArrayList();

   Files$1() {
   }

   @Override
   public boolean processLine(String text1) {
      this.field1.add(text1);
      return true;
   }

   public List<String> getResult() {
      return this.field1;
   }
}
