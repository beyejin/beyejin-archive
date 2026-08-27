package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.basic.SessionValue;
import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.schedule.repository.ScheduleRepository;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import com.example.scheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; //레파지토리 가져옴
    private final UserRepository userRepository;
    private final UserService userService;

    // 저장
    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request, SessionValue sessionValue) {

        User savedUser = userRepository.findById(sessionValue.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                savedUser,
                request.getTitle(),
                request.getContent()
        );
        Schedule savedschedule = scheduleRepository.save(schedule);

        return ScheduleCreateResponse.from(savedschedule);
    }

    // 다 건 조회 v1

//    public List<ScheduleGetResponse> findAll() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        return schedules.stream().map(ScheduleGetResponse::from).toList();
//    }

    /**
     * 회원 목록 조회 서비스
     * 1. 조회하기
     * 2. Dto 만들기
     * 3. dto 변환
     * 4. 반환
     */
    @Transactional(readOnly = true)
    public ScheduleGetListResponse getScheduleList() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleGetListResponse.ScheduleGetDto> scheduleGetList = schedules.stream()
                .map(ScheduleGetListResponse.ScheduleGetDto::from)
                .collect(Collectors.toList());

        return new ScheduleGetListResponse(scheduleGetList);
    }


    // 단 건 조회
    @Transactional(readOnly = true)
    public ScheduleGetListResponse.ScheduleGetDto getByScheduleId(Long scheduleId) {
        Schedule savedSchedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return ScheduleGetListResponse.ScheduleGetDto.from(savedSchedule);

    }

    // 수정
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request, SessionValue sessionValue) {

        Schedule foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        if (!foundSchedule.getUser().getId().equals(sessionValue.getUserId())) {
            throw new IllegalStateException("작성한 유저만 수정할 수 있습니다.");
        }
        foundSchedule.update(request);
        return ScheduleUpdateResponse.from(foundSchedule);
    }

    // 삭제
    @Transactional
    public void delete(Long scheduleId,SessionValue sessionValue) {
        Schedule deletedSchedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (!deletedSchedule.getUser().getId().equals(sessionValue.getUserId())) {
            throw new IllegalStateException("작성한 유저만 삭제할 수 있습니다.");
        }
        scheduleRepository.delete(deletedSchedule);
    }


}
