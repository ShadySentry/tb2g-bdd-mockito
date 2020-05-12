package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.springdatajpa.OwnerSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerSDJpaService ownerService;
    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Test
    void processCreationFormTest() {
        //given
        final String redirect = "redirect:/owners/5";
        Owner owner = new Owner(5L,"Daniel","The Fifth");

        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any(Owner.class))).willReturn(owner);
        //when
        String viewName = ownerController.processCreationForm(owner,bindingResult);

        //then
        System.out.println(bindingResult);
        assertThat(viewName).isEqualToIgnoringCase(redirect);
    }

    @Test
    void processCreationTestWithErrorTest(){
        //given
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        Owner owner = new Owner(1L,"Marc","The First");
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String viewName =  ownerController.processCreationForm(owner,bindingResult);
        //then
        assertThat(viewName).isEqualToIgnoringCase(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
    }
}