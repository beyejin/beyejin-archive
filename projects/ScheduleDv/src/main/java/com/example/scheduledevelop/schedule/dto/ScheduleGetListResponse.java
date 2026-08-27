package com.example.scheduledevelop.schedule.dto;

import com.example.scheduledevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleGetListResponse {

    // 속성
    private List<ScheduleGetDto> scheduleGetResponsesList;

    // 생성자
    public ScheduleGetListResponse(List<ScheduleGetDto> scheduleGetResponsesList) {
        this.scheduleGetResponsesList =scheduleGetResponsesList;
    }

    // 기능
    public List<ScheduleGetDto> getScheduleGetResponsesList() {
        return scheduleGetResponsesList;
    }

    // 중첩클래스 & 내부클래스
    @Getter
    public static class ScheduleGetDto {
        // 속성
        private final Long id;
        private final String username;
        private final String title;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        // 생성자
        private ScheduleGetDto(Long id, String username, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.username = username;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }

        // 기능

        public static ScheduleGetDto from (Schedule schedule) {
            return new ScheduleGetDto(
                    schedule.getId(),
                    schedule.getUser().getUsername(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }

    }

}
