//package cz.homeoffice.funtaskproject.services.implementations;
//
//import cz.homeoffice.funtaskproject.convertors.PersonalDataConvertor;
//import cz.homeoffice.funtaskproject.entity.PersonalData;
//import cz.homeoffice.funtaskproject.entity.User;
//import cz.homeoffice.funtaskproject.repositories.PersonalDataRepository;
//import cz.homeoffice.funtaskproject.repositories.UserRepository;
//import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
//import cz.homeoffice.funtaskproject.services.exceptions.PersonalDataServiceException;
//import org.easymock.EasyMockRunner;
//import org.easymock.Mock;
//import org.easymock.TestSubject;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.easymock.EasyMock.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(EasyMockRunner.class)
//public class PersonalDataServiceTest {
//
//    @Rule
//    public ExpectedException expectedEx = ExpectedException.none();
//
//    @Mock
//    public UserRepository userRepository;
//
//    @Mock
//    private PersonalDataRepository personalDataRepository;
//
//    @Mock
//    private PersonalDataConvertor personalDataConvertor;
//
//    @TestSubject
//    private final PersonalDataServiceImpl personalDataService = new PersonalDataServiceImpl();
//
//    @Test
//    public void addPersonalData() {
//        PersonalDataRest rest = new PersonalDataRest();
//        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        rest.setDateOfBirthday(LocalDate.now());
//        rest.setDateOfCreation(LocalDate.now());
//        rest.setPhoneNumber("+420 777 777 777");
//
//        PersonalData dao = new PersonalData();
//        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        dao.setDateOfBirthday(LocalDate.now());
//        dao.setDateOfCreation(LocalDate.now());
//        dao.setPhoneNumber("+420 777 777 777");
//
//        expect(personalDataConvertor.toDao(rest)).andReturn(dao);
//        expect(personalDataRepository.save(dao)).andReturn(dao);
//        expect(personalDataConvertor.toRest(dao)).andReturn(rest);
//
//        replay(personalDataConvertor);
//        replay(personalDataRepository);
//
//        PersonalDataRest personalDataRest = personalDataService.addPersonalData(rest);
//
//        verify(personalDataConvertor);
//        verify(personalDataRepository);
//
//        assertEquals(dao.getAddress(), personalDataRest.getAddress());
//        assertEquals(dao.getDateOfBirthday(), personalDataRest.getDateOfBirthday());
//        assertEquals(dao.getPhoneNumber(), personalDataRest.getPhoneNumber());
//        assertEquals(dao.getDateOfCreation(), personalDataRest.getDateOfCreation());
//    }
//
//    @Test
//    public void getAllPersonalData() {
//        PersonalData dao = new PersonalData();
//        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        dao.setDateOfBirthday(LocalDate.now());
//        dao.setDateOfCreation(LocalDate.now());
//        dao.setPhoneNumber("+420 777 777 777");
//
//        List<PersonalData> personalDataList = new ArrayList<>();
//        personalDataList.add(dao);
//        personalDataList.add(dao);
//
//        PersonalDataRest rest = new PersonalDataRest();
//        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        rest.setDateOfBirthday(LocalDate.now());
//        rest.setDateOfCreation(LocalDate.now());
//        rest.setPhoneNumber("+420 777 777 777");
//
//        List<PersonalDataRest> personalDataRestList = new ArrayList<>();
//        personalDataRestList.add(rest);
//        personalDataRestList.add(rest);
//
//        expect(personalDataConvertor.toRest(personalDataList)).andReturn(personalDataRestList);
//        expect(personalDataRepository.findAll()).andReturn(personalDataList);
//
//        replay(personalDataConvertor);
//        replay(personalDataRepository);
//
//        List<PersonalDataRest> allPersonalData = personalDataService.getAllPersonalData();
//
//        verify(personalDataConvertor);
//        verify(personalDataRepository);
//
//        assertEquals(2, allPersonalData.size());
//        assertEquals(allPersonalData.size(), personalDataList.size());
//
//        assertTrue(allPersonalData.size() > 0);
//
//        for (int i = 0; i < allPersonalData.size(); i++) {
//            assertEquals(personalDataList.get(i).getPhoneNumber(), allPersonalData.get(i).getPhoneNumber());
//            assertEquals(personalDataList.get(i).getAddress(), allPersonalData.get(i).getAddress());
//            assertEquals(personalDataList.get(i).getDateOfBirthday(), allPersonalData.get(i).getDateOfBirthday());
//            assertEquals(personalDataList.get(i).getDateOfBirthday(), allPersonalData.get(i).getDateOfBirthday());
//        }
//    }
//
//    @Test
//    public void getPersonalDataById() {
//        PersonalData dao = new PersonalData();
//        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        dao.setDateOfBirthday(LocalDate.now());
//        dao.setDateOfCreation(LocalDate.now());
//        dao.setPhoneNumber("+420 777 777 777");
//
//        PersonalDataRest rest = new PersonalDataRest();
//        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        rest.setDateOfBirthday(LocalDate.now());
//        rest.setDateOfCreation(LocalDate.now());
//        rest.setPhoneNumber("+420 777 777 777");
//
//        expect(personalDataRepository.findById(anyInt())).andReturn(Optional.of(dao));
//        expect(personalDataConvertor.toRest(dao)).andReturn(rest);
//
//        replay(personalDataRepository);
//        replay(personalDataConvertor);
//
//        PersonalDataRest personalDataById = personalDataService.getPersonalDataById(anyInt());
//
//        verify(personalDataRepository);
//        verify(personalDataConvertor);
//
//        assertEquals(dao.getAddress(), personalDataById.getAddress());
//        assertEquals(dao.getDateOfBirthday(), personalDataById.getDateOfBirthday());
//        assertEquals(dao.getPhoneNumber(), personalDataById.getPhoneNumber());
//        assertEquals(dao.getDateOfCreation(), personalDataById.getDateOfCreation());
//    }
//
//    @Test
//    public void should_throw_exception_when_getPersonalDataById() {
//        Optional<PersonalData> personalDataById = Optional.empty();
//
//        expect(personalDataRepository.findById(anyInt())).andReturn(personalDataById);
//        expectedEx.expect(PersonalDataServiceException.class);
//        expectedEx.expectMessage("The id number isn't found");
//
//        replay(personalDataRepository);
//
//        personalDataService.getPersonalDataById(anyInt());
//    }
//
//    @Test
//    public void deletePersonalDataById() {
//        PersonalData dao = new PersonalData();
//        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        dao.setDateOfBirthday(LocalDate.now());
//        dao.setDateOfCreation(LocalDate.now());
//        dao.setPhoneNumber("+420 777 777 777");
//
//        expect(personalDataRepository.findById(anyInt())).andReturn(Optional.of(dao));
//
//        personalDataRepository.deleteById(anyInt());
//        expectLastCall();
//
//        replay(personalDataRepository);
//
//        personalDataService.deletePersonalDataById(1);
//
//        verify(personalDataRepository);
//    }
//
//    @Test
//    public void should_throw_exception_when_deletePersonalDataById() {
//        Optional<PersonalData> personalDataById = Optional.empty();
//
//        expect(personalDataRepository.findById(anyInt())).andReturn(personalDataById);
//        expectedEx.expect(PersonalDataServiceException.class);
//        expectedEx.expectMessage("The id number isn't found");
//
//        replay(personalDataRepository);
//
//        personalDataService.deletePersonalDataById(anyInt());
//    }
//
//    @Test
//    public void updatePersonalDataById() {
//        PersonalData dao = new PersonalData();
//        dao.setId(1);
//        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
//        dao.setDateOfBirthday(LocalDate.now());
//        dao.setDateOfCreation(LocalDate.now());
//        dao.setPhoneNumber("+420 777 777 777");
//
//        PersonalDataRest rest = new PersonalDataRest();
//        rest.setId(1);
//        rest.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
//        rest.setDateOfBirthday(LocalDate.now());
//        rest.setDateOfCreation(LocalDate.now());
//        rest.setPhoneNumber("+420 999 777 777");
//
//        PersonalData dao1 = new PersonalData();
//        dao1.setId(1);
//        dao1.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
//        dao1.setDateOfBirthday(LocalDate.now());
//        dao1.setDateOfCreation(LocalDate.now());
//        dao1.setPhoneNumber("+420 999 777 777");
//
//        PersonalDataRest rest1 = new PersonalDataRest();
//        rest1.setId(1);
//        rest1.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
//        rest1.setDateOfBirthday(LocalDate.now());
//        rest1.setDateOfCreation(LocalDate.now());
//        rest1.setPhoneNumber("+420 999 777 777");
//
//
//        expect(personalDataRepository.findById(1)).andReturn(Optional.of(dao));
//        expect(personalDataConvertor.toDao(1, rest)).andReturn(dao);
//        expect(personalDataRepository.save(dao)).andReturn(dao1);
//        expect(personalDataConvertor.toRest(dao1)).andReturn(rest1);
//
//        replay(personalDataConvertor);
//        replay(personalDataRepository);
//
//        PersonalDataRest personalDataRest = personalDataService.updatePersonalDataById(1, rest);
//
//        verify(personalDataRepository);
//        verify(personalDataConvertor);
//
//        assertEquals(dao1.getId(), personalDataRest.getId());
//        assertEquals(dao1.getAddress(), personalDataRest.getAddress());
//        assertEquals(dao1.getDateOfBirthday(), personalDataRest.getDateOfBirthday());
//        assertEquals(dao1.getPhoneNumber(), personalDataRest.getPhoneNumber());
//        assertEquals(dao1.getDateOfCreation(), personalDataRest.getDateOfCreation());
//    }
//
//    @Test
//    public void getUserPersonalDataByAccessToken() {
//        PersonalData personalData = new PersonalData();
//        personalData.setId(1);
//        personalData.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
//        personalData.setDateOfBirthday(LocalDate.now());
//        personalData.setDateOfCreation(LocalDate.now());
//        personalData.setPhoneNumber("+420 777 777 777");
//
//        PersonalDataRest rest1 = new PersonalDataRest();
//        rest1.setId(1);
//        rest1.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
//        rest1.setDateOfBirthday(LocalDate.now());
//        rest1.setDateOfCreation(LocalDate.now());
//        rest1.setPhoneNumber("+420 777 777 777");
//
//        User dao = new User();
//        dao.setId(1);
//        dao.setUserName("Jana");
//        dao.setEmail("bomba@bubu.cz");
//        dao.setAccessToken(UUID.randomUUID().toString());
//        dao.setPassword("123");
//        dao.setPersonalData(personalData);
//
//        expect(userRepository.findByAccessToken(anyObject())).andReturn(Optional.of(dao));
//        expect(personalDataRepository.findById(1)).andReturn(Optional.of(personalData));
//        expect(personalDataConvertor.toRest(personalData)).andReturn(rest1);
//
//        replay(userRepository);
//        replay(personalDataRepository);
//        replay(personalDataConvertor);
//
//        PersonalDataRest personalDataById = personalDataService.getPersonalDataById(1);
//
//        verify(personalDataRepository);
//        verify(personalDataConvertor);
//
//        assertEquals(personalData.getId(), personalDataById.getId());
//        assertEquals(personalData.getAddress(), personalDataById.getAddress());
//        assertEquals(personalData.getDateOfBirthday(), personalDataById.getDateOfBirthday());
//        assertEquals(personalData.getPhoneNumber(), personalDataById.getPhoneNumber());
//        assertEquals(personalData.getDateOfCreation(), personalDataById.getDateOfCreation());
//
//
//    }
//}