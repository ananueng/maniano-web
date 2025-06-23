package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.song.Song;
import dev.ananueng.maniano.song.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayServiceUnitTest {

    @Mock
    private PlayRepository playRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PlayMapper playMapper;

    @InjectMocks
    private PlayService playService;

    // @Test
    // void testFindAll() {
    //     Project project = new Project();
    //     project.setId(1);
    //
    //     Schedule schedule1 = new Schedule();
    //     schedule1.setProject(project);
    //
    //     Schedule schedule2 = new Schedule();
    //     schedule2.setProject(project);
    //
    //     List<Schedule> schedules = List.of(schedule1, schedule2);
    //
    //     when(scheduleRepository.findAll()).thenReturn(schedules);
    //     when(scheduleMapper.scheduleToResponseDto(any(Schedule.class))).thenAnswer(invocation -> {
    //         Schedule schedule = invocation.getArgument(0);
    //         return new ScheduleResponseDto(schedule.getTitle(), schedule.getProjectId(), schedule.getDueDate(), schedule.getCreatedAt(), schedule.getLastModifiedBy(), schedule.getLastModifiedAt());
    //     });
    //
    //     List<ScheduleResponseDto> result = scheduleService.findAll();
    //
    //     assertEquals(2, result.size());
    //     verify(scheduleRepository, times(1)).findAll();
    // }

    // temp
    // @Test
    // void testCreateFromDto() {
    //     // GIVEN
    //     Project project = new Project();
    //     project.setId(1);
    //     project.setTitle("Test Project");
    //
    //     ScheduleRequestDto requestDto = new ScheduleRequestDto("Test Schedule", 1, LocalDateTime.now().plusDays(1));
    //     Schedule schedule = new Schedule();
    //     schedule.setName(requestDto.name());
    //     schedule.setDueDate(requestDto.dueDate());
    //     schedule.setProject(project);
    //
    //     // capture the Schedule object that is passed to the save method (after mapping the DTO to the Service object)
    //     ArgumentCaptor<Schedule> scheduleCaptor = ArgumentCaptor.forClass(Schedule.class);
    //
    //     // return the mock object when calling the helper method rather than using a database
    //     given(projectRepository.findById(requestDto.projectId())).willReturn(Optional.of(project));
    //     given(scheduleMapper.requestDtoToSchedule(requestDto)).willReturn(schedule);
    //
    //     // WHEN
    //     scheduleService.create(requestDto);
    //
    //     // THEN
    //     // check that the save method was called once and capture the Schedule object
    //     verify(scheduleRepository, times(1)).save(scheduleCaptor.capture());
    //
    //     // verify the captured object's properties
    //     Schedule savedSchedule = scheduleCaptor.getValue();
    //     assertThat(savedSchedule.getProject()).isEqualTo(project);
    //     assertThat(savedSchedule.getName()).isEqualTo("Test Schedule");
    //     assertThat(savedSchedule.getDueDate()).isEqualTo(requestDto.dueDate());
    //     assertThat(savedSchedule.getCreatedAt()).isNotNull();
    //     assertThat(savedSchedule.getLastModifiedAt()).isNotNull();
    //
    //     // verify the referenced project's properties
    //     assertThat(savedSchedule.getProject().getTitle()).isEqualTo("Test Project");
    // }

    @Test
    void testPatch() {
        // GIVEN
        PlayRequestDto dto = new PlayRequestDto("Updated Title", 1, LocalDateTime.now());
        Play play = new Play();
        Song song = new Song();
        song.setId(1);

        play.setSong(song);

        // capture the Schedule object that is passed to the save method
        ArgumentCaptor<Play> scheduleCaptor = ArgumentCaptor.forClass(Play.class);

        // return the mock objects when calling the helper methods rather than using a database
        when(playRepository.findById(1)).thenReturn(Optional.of(play));
        when(songRepository.findById(1)).thenReturn(Optional.of(song));

        // WHEN
        playService.patch(dto, 1);

        // THEN
        // check that the save method was called once and capture the Schedule object
        verify(playRepository, times(1)).save(scheduleCaptor.capture());

        // verify the captured object's properties
        Play savedPlay = scheduleCaptor.getValue();
        assertThat(savedPlay.getSong()).isEqualTo(song);
        assertThat(savedPlay.getName()).isEqualTo("Updated Title");
        // assertThat(savedSchedule.getName()).isEqualTo("FALSE");
        assertThat(savedPlay.getDueDate()).isEqualTo(dto.dueDate());
    }
    // temp
    // @Test
    // void testDelete() {
    //     Schedule schedule = new Schedule();
    //     when(scheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
    //
    //     scheduleService.delete(1);
    //
    //     assertNotNull(schedule.getDeletedAt());
    //     assertTrue(schedule.isDeleted());
    // }
}