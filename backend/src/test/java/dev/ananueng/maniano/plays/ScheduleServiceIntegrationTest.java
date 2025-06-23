// package dev.ananueng.maniano.schedule;
//
// import dev.ananueng.maniano.project.Project;
// import dev.ananueng.maniano.project.ProjectRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;
//
// import java.time.LocalDateTime;
// import java.util.Optional;
//
// import static org.assertj.core.api.Assertions.assertThat;
//
// @SpringBootTest
// @Transactional
// class ScheduleServiceIntegrationTest {
//
//     @Autowired
//     private ScheduleRepository scheduleRepository;
//
//     @Autowired
//     private ProjectRepository projectRepository;
//
//     @Autowired
//     private ScheduleService scheduleService;
//
//     @BeforeEach
//     void setUp() {
//         // Set up initial data for the tests
//         Project project = new Project();
//         project.setTitle("Integration Test Project");
//         projectRepository.save(project);
//     }
//
//     @Test
//     void testCreateSchedule() {
//         // GIVEN
//         Project project = projectRepository.findAll().getFirst();
//         ScheduleRequestDto requestDto = new ScheduleRequestDto("Integration Test Schedule", project.getId(), LocalDateTime.now().plusDays(1));
//
//         // WHEN
//         scheduleService.create(requestDto);
//
//         // THEN
//         Optional<Schedule> savedSchedule = scheduleRepository.findAll().stream().findFirst();
//         assertThat(savedSchedule).isPresent();
//         assertThat(savedSchedule.get().getName()).isEqualTo("Integration Test Schedule");
//         assertThat(savedSchedule.get().getProject().getId()).isEqualTo(project.getId());
//
//         Project mappedProject = projectRepository.findAll().getFirst();
//
//         // This fails because the one-to-many mapping is not visible?
//         // assertThat(mappedProject.getSchedules()).contains(savedSchedule.get());
//     }
//
//     @Test
//     void testPatchSchedule() {
//         // GIVEN
//         Project project = projectRepository.findAll().getFirst();
//         Schedule schedule = new Schedule();
//         schedule.setName("Original Title");
//         schedule.setDueDate(LocalDateTime.now().plusDays(2));
//         schedule.setProject(project);
//         scheduleRepository.save(schedule);
//
//         ScheduleRequestDto requestDto = new ScheduleRequestDto("Updated Title", project.getId(), LocalDateTime.now().plusDays(3));
//
//         // WHEN
//         scheduleService.patch(requestDto, schedule.getId());
//
//         // THEN
//         Optional<Schedule> updatedSchedule = scheduleRepository.findById(schedule.getId());
//         assertThat(updatedSchedule).isPresent();
//         assertThat(updatedSchedule.get().getName()).isEqualTo("Updated Title");
//         assertThat(updatedSchedule.get().getDueDate()).isEqualTo(requestDto.dueDate());
//     }
//
//     @Test
//     void testDeleteSchedule() {
//         // GIVEN
//         Project project = projectRepository.findAll().getFirst();
//         Schedule schedule = new Schedule();
//         schedule.setName("To be deleted");
//         schedule.setDueDate(LocalDateTime.now().plusDays(2));
//         schedule.setProject(project);
//         scheduleRepository.save(schedule);
//
//         // WHEN
//         scheduleService.delete(schedule.getId());
//
//         // THEN
//         Optional<Schedule> deletedSchedule = scheduleRepository.findById(schedule.getId());
//         assertThat(deletedSchedule).isPresent();
//         assertThat(deletedSchedule.get().isDeleted()).isTrue();
//         assertThat(deletedSchedule.get().getDeletedAt()).isNotNull();
//     }
//
//     // TODO: test delete schedule cascade
// }