package com.example.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {
    private String title;
    @NotBlank(message = "수정 내용은 필수값입니다.")
    private String content;

}
