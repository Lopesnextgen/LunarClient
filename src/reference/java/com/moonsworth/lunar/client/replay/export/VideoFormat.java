package com.moonsworth.lunar.client.replay.export;

import java.util.List;
import lombok.Generated;

public enum VideoFormat implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   MP4("mp4", true, List.of(VideoCodec.H264, VideoCodec.HEVC, VideoCodec.VP9, VideoCodec.AV1)),
   MKV("mkv", true, List.of(VideoCodec.H264, VideoCodec.HEVC, VideoCodec.AV1)),
   MOV("mov", true, List.of(VideoCodec.H264, VideoCodec.HEVC, VideoCodec.AV1, VideoCodec.WEBP)),
   AVI("avi", true, List.of(VideoCodec.H264)),
   WEBP("webp", false, List.of(VideoCodec.WEBP)),
   WEBM("webm", true, List.of(VideoCodec.AV1));

   private final String id;
   private final boolean supportsAudio;
   private final List<VideoCodec> codecs;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
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
   public List<VideoCodec> getCodecs() {
      return this.codecs;
   }

   @Generated
   VideoFormat(String text3, boolean flag4, List<VideoCodec> list5) {
      this.id = text3;
      this.supportsAudio = flag4;
      this.codecs = list5;
   }
}
