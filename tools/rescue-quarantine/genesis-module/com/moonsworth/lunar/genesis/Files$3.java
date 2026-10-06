package com.moonsworth.lunar.genesis;

import java.io.File;
import com.google.common.graph.PredecessorsFunction;

final class Files$3 implements PredecessorsFunction<File> {
   Files$3() {
   }

   public Iterable<File> successors(File file1) {
      return MixinHelper16.access$200(file1);
   }
}
