package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.basic.SessionValue;
import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;


    /**
     * 회원 생성 API
     * 0. 클라이언트에게 데이터를 전달받기
     * 1. 서비스한테 회원 생성 처리 부탁
     * 2. 처리 결과 받기
     * 3. http 반환 객체 만들기
     * 4. 반환
     */
    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> createSchedule(
            @Valid @RequestBody ScheduleCreateRequest request,
            @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue) {
        if (sessionValue == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }

        ScheduleCreateResponse result = scheduleService.save(request, sessionValue);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 회원 목록 조회 API
     * 1. 서비스한테 로직 처리 부탁하기
     * 2. 결과 반환 받기
     * 3. http 반환 객체 만들기
     * 4. 반환
     */
    @GetMapping
    public ResponseEntity<ScheduleGetListResponse> getAll() {
        ScheduleGetListResponse response = scheduleService.getScheduleList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 단 건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetListResponse.ScheduleGetDto> getByScheduleId(@PathVariable Long scheduleId) {
        ScheduleGetListResponse.ScheduleGetDto result = scheduleService.getByScheduleId(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> Update(
            @PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequest request,
            @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue
    ) {
        if (sessionValue == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }

        ScheduleUpdateResponse result = scheduleService.update(scheduleId, request, sessionValue);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deletAll(@PathVariable Long scheduleId,
                                         @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue) {
        if (sessionValue == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }

        scheduleService.delete(scheduleId, sessionValue);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
