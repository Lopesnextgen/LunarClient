package com.moonsworth.lunar.genesis;

import java.nio.file.Path;
import com.google.common.graph.PredecessorsFunction;

final class MoreFiles$1 implements PredecessorsFunction<Path> {
   MoreFiles$1() {
   }

   public Iterable<Path> successors(Path path1) {
      return MixinHelper18.access$300(path1);
   }
}
