package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.JarUtils;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class MixinSourceArchive {
   private final IchorStage field1;
   private final Map<String, byte[]> field2;
   private final Predicate<String> field3;

   public MixinSourceArchive(IchorStage ichor41, Map<String, byte[]> map, Predicate<String> predicate3) {
      this.field1 = ichor41;
      this.field2 = map;
      this.field3 = predicate3;
   }

   public Optional<MixinClassSource> method1(Collection<String> list) {
      for (String text3 : list) {
         String text4 = text3.replace("/", ".");
         if (this.field3.test(text4)) {
            byte[] items5 = this.field2.get(text4);
            if (items5 != null) {
               return Optional.of(new MixinClassSource(this.field1, text4, items5));
            }
         }
      }

      return Optional.empty();
   }

   public static MixinSourceArchive method2(IchorStage ichor40, Path path1, Predicate<String> predicate2) {
      Map map3 = JarUtils.method7(path1, predicate2);
      return new MixinSourceArchive(ichor40, map3, predicate2);
   }

   public IchorStage method3() {
      return this.field1;
   }

   public Map<String, byte[]> method4() {
      return this.field2;
   }

   public Predicate<String> method5() {
      return this.field3;
   }
}
