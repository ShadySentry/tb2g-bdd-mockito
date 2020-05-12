package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        //given
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);
        //when
        Set<Visit> foundVisits = service.findAll();

        //then
        then(visitRepository).should(times(1)).findAll();
        assertThat(foundVisits).hasSize(1);

    }

    @DisplayName("Test Find By Id")
    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        Visit foundVisit = service.findById(1L);

        //then
        assertThat(foundVisit).isNotNull();
        then(visitRepository).should().findById(anyLong());
    }

    @DisplayName("Test save")
    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = service.save(new Visit());

        //then
        then(visitRepository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test delete")
    @Test
    void delete() {
        //when
        Visit visit = new Visit();
        service.delete(visit);
        //then
        then(visitRepository).should().delete(any(Visit.class));

    }

    @DisplayName("Test Delete By ID")
    @Test
    void deleteById() {
        //when
        service.deleteById(1L);
        //then
        then(visitRepository).should().deleteById(1L);
    }
}