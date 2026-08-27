package com.example.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    @NotBlank(message = "일정 제목은 필수값입니다.")
    @Size(max = 50, message = "일정 제목은 50글자 이내여야 합니다.")
    private String title;
    @NotBlank(message = "스케줄 내용은 필수값입니다.")
    @Size(max = 200, message = "일정 내용은 200글자 이내여야 합니다.")
    private String content;

}
