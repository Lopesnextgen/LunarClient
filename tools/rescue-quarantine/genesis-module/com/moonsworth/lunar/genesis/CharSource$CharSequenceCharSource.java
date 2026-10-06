package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.stream.Stream;
import com.google.common.collect.Streams;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Optional;
import com.google.common.io.LineProcessor;
import com.google.common.base.Splitter;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;

class CharSource$CharSequenceCharSource extends MixinHelper9 {
   private static final Splitter field1 = Splitter.method6("\r\n|\n|\r");
   protected final CharSequence field2;

   protected CharSource$CharSequenceCharSource(CharSequence text1) {
      this.field2 = (CharSequence)Preconditions.checkNotNull(text1);
   }

   public java.io.Reader openStream() {
      return new CharSequenceReader(this.field2);
   }

   public String read() {
      return this.field2.toString();
   }

   public boolean isEmpty() {
      return this.field2.length() == 0;
   }

   public long length() {
      return this.field2.length();
   }

   public Optional<Long> method2() {
      return Optional.method2((long)this.field2.length());
   }

   private Iterator<String> linesIterator() {
      return new Data5$1(this);
   }

   public Stream<String> lines() {
      return Streams.stream(this.linesIterator());
   }

   public String readFirstLine() {
      Iterator iterator1 = this.linesIterator();
      return iterator1.hasNext() ? (String)iterator1.next() : null;
   }

   public ImmutableList<String> method4() {
      return ImmutableList.method16(this.linesIterator());
   }

   public <T> T method5(LineProcessor<T> mixinhelper8_61) {
      Iterator iterator2 = this.linesIterator();

      while (iterator2.hasNext() && mixinhelper8_61.processLine((String)iterator2.next())) {
      }

      return (T)mixinhelper8_61.getResult();
   }

   public String toString() {
      return "CharSource.wrap(" + Ascii.truncate(this.field2, 30, "...") + ")";
   }
}
