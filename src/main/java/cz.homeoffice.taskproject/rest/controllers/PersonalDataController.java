package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.services.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PersonalDataController {

    private final PersonalDataService personalDataService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private PersonalDataDto addPersonalData(@RequestBody PersonalDataDto personalDataRest) {
        return personalDataService.addPersonalData(personalDataRest);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    private List<PersonalDataDto> getAllPersonalData() {
        return personalDataService.getAllPersonalData();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    private PersonalDataDto getPersonalDataById(@PathVariable("id") Long id) {
        return personalDataService.getPersonalDataById(id);
    }

    @RequestMapping(value = "/delete-data/{id}", method = RequestMethod.DELETE)
    private void deletePersonalDataById(@PathVariable("id") Long id) {
        personalDataService.deletePersonalDataById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    private PersonalDataDto updatePersonalDataById(@RequestBody PersonalDataDto personalDataDto) {
        return personalDataService.updatePersonalDataById(personalDataDto);
    }
}