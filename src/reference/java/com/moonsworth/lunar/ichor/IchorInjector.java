package com.moonsworth.lunar.ichor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.objectweb.asm.tree.ClassNode;

public interface IchorInjector {
   ClassNode transformClassNode(IchorPipeline ichor71, String text2, String text3, @Nullable ClassNode node4);

   void registerMixins(List<String> list1);

   void setMixinDecorations(List<String> list1, Map<String, Object> map2);

   void gotoDefaultPhase();

   Set<String> getMixinAndTargetClasses();

   Set<String> getSyntheticClasses();

   void audit(ClassLoader classloader1);

   Map<String, byte[]> getExtraClassDefinitions();
}
