package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import java.util.List;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   MP4("mp4", true, List.of(Gui2Extension2.H264, Gui2Extension2.HEVC, Gui2Extension2.VP9, Gui2Extension2.AV1)),
   MKV("mkv", true, List.of(Gui2Extension2.H264, Gui2Extension2.HEVC, Gui2Extension2.AV1)),
   MOV("mov", true, List.of(Gui2Extension2.H264, Gui2Extension2.HEVC, Gui2Extension2.AV1, Gui2Extension2.WEBP)),
   AVI("avi", true, List.of(Gui2Extension2.H264)),
   WEBP("webp", false, List.of(Gui2Extension2.WEBP)),
   WEBM("webm", true, List.of(Gui2Extension2.AV1));

   private final String id;
   private final boolean supportsAudio;
   private final List<Gui2Extension2> codecs;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public boolean isSupportsAudio() {
      return this.supportsAudio;
   }

   @Generated
   public List<Gui2Extension2> getCodecs() {
      return this.codecs;
   }

   @Generated
   Gui2Extension(String text, boolean flag, List<Gui2Extension2> list) {
      this.id = text;
      this.supportsAudio = flag;
      this.codecs = list;
   }
}
