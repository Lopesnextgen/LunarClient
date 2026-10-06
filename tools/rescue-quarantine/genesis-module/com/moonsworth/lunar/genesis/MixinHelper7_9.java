package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.DoNotMock;
import com.google.common.graph.Graph;

@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@Annotation2
public interface MixinHelper7_9<N> {
   Iterable<? extends N> predecessors(N var1);
}
