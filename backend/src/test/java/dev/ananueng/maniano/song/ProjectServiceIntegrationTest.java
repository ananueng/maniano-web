// package dev.ananueng.maniano.project;
//
// import dev.ananueng.maniano.schedule.*;
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
// class ProjectServiceIntegrationTest {
//
//     @Autowired
//     private ProjectService projectService;
//     @Autowired
//     private ScheduleService scheduleService;
//     @Autowired
//     private ProjectRepository projectRepository;
//     @Autowired
//     private ScheduleRepository scheduleRepository;
//
//     @BeforeEach
//     void setUp() {
//         // Set up initial data for the tests
//     }
//
//     @Test
//     void testCreateProject() {
//         // GIVEN
//         ProjectRequestDto requestDto = new ProjectRequestDto("Integration Test Project", "Description");
//
//         // WHEN
//         projectService.create(requestDto);
//
//         // THEN
//         Optional<ProjectResponseDto> savedProject = Optional.ofNullable(projectService.findById(1));
//         assertThat(savedProject).isPresent();
//         assertThat(savedProject.get().title()).isEqualTo("Integration Test Project");
//         assertThat(savedProject.get().description()).isEqualTo("Description");
//     }
//
//     @Test
//     void testPatchProject() {
//         // GIVEN
//         ProjectRequestDto createRequestDto = new ProjectRequestDto("Original Title", "Description");
//         projectService.create(createRequestDto);
//
//         ProjectRequestDto requestDto = new ProjectRequestDto("Updated Title", "Updated Description");
//
//         // WHEN
//         assertThat(projectService.findById(1)).isNotNull();
//         projectService.patch(requestDto, 1);
//
//         // THEN
//         ProjectResponseDto updatedProject = projectService.findById(1);
//         assertThat(updatedProject.title()).isEqualTo("Updated Title");
//         assertThat(updatedProject.description()).isEqualTo("Updated Description");
//     }
//
//     @Test
//     void testDeleteProject() {
//         // GIVEN
//         ProjectRequestDto projectRequestDto = new ProjectRequestDto("To be deleted", "Description");
//         projectService.create(projectRequestDto);
//
//         // WHEN
//         // This fails because the one-to-many mapping is not visible?
//         projectService.delete(1);
//
//         // THEN
//         Optional<Project> deletedProject = projectRepository.findById(1);
//         assertThat(deletedProject).isPresent();
//         assertThat(deletedProject.get().isDeleted()).isTrue();
//         assertThat(deletedProject.get().getDeletedAt()).isNotNull();
//     }
//
//     @Test
//     void testDeleteProjectCascade() {
//         // GIVEN
//         // a project to be deleted (id = 1)
//         ProjectRequestDto projectRequestDto = new ProjectRequestDto("To be deleted", "Description");
//         projectService.create(projectRequestDto);
//         Project project1 = projectRepository.findAll().stream().findFirst().get();
//
//         // a project to not be deleted (id = 2)
//         ProjectRequestDto unrelatedProjectRequestDto = new ProjectRequestDto("Unrelated Project", "Description");
//         projectService.create(unrelatedProjectRequestDto);
//         Project project2 = projectRepository.findAll().stream().filter(p -> !p.getId().equals(project1.getId())).findFirst().get();
//
//         // schedules that belong to the project to be deleted
//         ScheduleRequestDto scheduleRequestDto1 = new ScheduleRequestDto("Schedule 1", project1.getId(), LocalDateTime.now().plusDays(1));
//         scheduleService.create(scheduleRequestDto1);
//         Schedule schedule1 = scheduleRepository.findAll().stream().filter(s -> s.getProject().getId().equals(project1.getId())).findFirst().get();
//
//         ScheduleRequestDto scheduleRequestDto2 = new ScheduleRequestDto("Schedule 2", project1.getId(), LocalDateTime.now().plusDays(2));
//         scheduleService.create(scheduleRequestDto2);
//
//         // This fails because the one-to-many mapping is not visible?
//         Project UpdatedProject1 = projectRepository.findAll().stream().findFirst().get();
//
//         // a schedule that belongs to the project that won't be deleted
//         ScheduleRequestDto unrelatedScheduleRequestDto = new ScheduleRequestDto("Unrelated Schedule", project2.getId(), LocalDateTime.now().plusDays(3));
//         scheduleService.create(unrelatedScheduleRequestDto);
//
//         // WHEN
//         projectService.delete(project1.getId());
//
//         // THEN
//         Optional<Project> deletedProject = projectRepository.findById(project1.getId());
//         assertThat(deletedProject).isPresent();
//         assertThat(deletedProject.get().isDeleted()).isTrue();
//         assertThat(deletedProject.get().getDeletedAt()).isNotNull();
//
//         Optional<Schedule> deletedSchedule1 = scheduleRepository.findById(schedule1.getId());
//         assertThat(deletedSchedule1).isPresent();
//         assertThat(deletedSchedule1.get().isDeleted()).isTrue();
//         assertThat(deletedSchedule1.get().getDeletedAt()).isNotNull();
//
//         Optional<Schedule> deletedSchedule2 = scheduleRepository.findById(schedule1.getId() + 1);
//         assertThat(deletedSchedule2).isPresent();
//         assertThat(deletedSchedule2.get().isDeleted()).isTrue();
//         assertThat(deletedSchedule2.get().getDeletedAt()).isNotNull();
//
//         // schedule that belonged to the project that wasn't deleted
//         Optional<Schedule> unrelatedSchedule = scheduleRepository.findById(schedule1.getId() + 2);
//         assertThat(unrelatedSchedule).isPresent();
//         assertThat(unrelatedSchedule.get().isDeleted()).isFalse();
//         assertThat(unrelatedSchedule.get().getDeletedAt()).isNull();
//     }
// }