package com.example.scheduledevelop.schedule.dto;

import com.example.scheduledevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleCreateResponse {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleCreateResponse(Long id, String username, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /* public static CreateScheduleResponse from (Schedule schedule) {
        return new CreateScheduleResponse(schedule);
    }*/

    public static ScheduleCreateResponse from(Schedule schedule) {
        return new ScheduleCreateResponse(
                schedule.getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
