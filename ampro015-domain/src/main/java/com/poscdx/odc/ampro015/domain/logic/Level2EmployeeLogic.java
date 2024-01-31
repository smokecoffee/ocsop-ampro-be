package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Level2EmployeeLogic implements Level2EmployeeService {
    @Override
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(ServiceLifecycle serviceLifecycle,
                                                            String site,
                                                            String status,
                                                            String name,
                                                            String empId,
                                                            String joinDateFrom,
                                                            String joinDateTo,
                                                            String gender){
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();

        List<Object[]> listPme00Employee = serviceLifecycle.requestM00EmployeeService()
                .searchPmeEmployee(site, status, name, empId, joinDateFrom, joinDateTo, gender);

        List<Pme00RoleUser> pme00RoleUsers = serviceLifecycle.requestPme00RoleUserService().findRoleUserByEmpId(empId);
        List<Pme00Employee> pme00Employees1 = new ArrayList<>();
        for(Object[] object : listPme00Employee){
            Pme00Employee employee = new Pme00Employee(object);
//            EmployeeDto employeeDto = new EmployeeDto(object);
            employee.setListRoleUser(pme00RoleUsers);
            pme00Employees1.add(employee);
        }
        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
        pme00AllLevel2EmployeeResponse.setListData(pme00Employees1);
        pme00AllLevel2EmployeeResponse.setMessage("Get all employee successfully");
        return pme00AllLevel2EmployeeResponse;
    }

    @Override
    public PmeSiteResponse findSiteEmp(ServiceLifecycle serviceLifecycle){
        PmeSiteResponse pmeSiteResponse = new PmeSiteResponse();
        final int cdTpId1 = 51;
        List<M00Codes030> m00Codes030s = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(cdTpId1);
        List<Pme00Site> pme00Sites = new ArrayList<>();
        for(M00Codes030 m00Codes030 : m00Codes030s){
            Pme00Site pme00Site = new Pme00Site();
            pme00Site.setCD_V(m00Codes030.getCdV());
            pme00Site.setCD_V_MEANING(m00Codes030.getCdvMeaning());
            pme00Sites.add(pme00Site);
        }
        pmeSiteResponse.setStatus(HttpStatus.OK.value());
        pmeSiteResponse.setListData(pme00Sites);
        pmeSiteResponse.setMessage("Get all site successfully");
        return pmeSiteResponse;
    }
    @Override
    public PmeStatusResponse findStatus(ServiceLifecycle serviceLifecycle){
        PmeStatusResponse pmeStatusResponse = new PmeStatusResponse();
        final int cdTpId2 = 99;
        List<M00Codes030> m00Codes030s = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(cdTpId2);
        List<Pme00Status> pme00Statuses = new ArrayList<>();
        for(M00Codes030 m00Codes030 : m00Codes030s){
            Pme00Status pme00Status = new Pme00Status();
            pme00Status.setCD_V(m00Codes030.getCdV());
            pme00Status.setCD_V_MEANING(m00Codes030.getCdvMeaning());
            pme00Statuses.add(pme00Status);
        }
        pmeStatusResponse.setStatus(HttpStatus.OK.value());
        pmeStatusResponse.setListData(pme00Statuses);
        pmeStatusResponse.setMessage("Get all status successfully");
        return pmeStatusResponse;
    }
    @Override
    public Pme00AllLevel2EmployeeResponse addEmployee(ServiceLifecycle serviceLifecycle, Pme00Employee newEmployee){
        String passwordToMd5Hex = DigestUtils
                .md5Hex(newEmployee.getPassword());
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        M00Employee checkEmployee = serviceLifecycle.requestM00EmployeeService().find(newEmployee.getEmpId());
        if(checkEmployee!=null){
            pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
            pme00AllLevel2EmployeeResponse.setMessage("Employee exist");
        }else {
            try{

                if(newEmployee.getSiteCode().isEmpty()){
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee SiteCode could not match");
                }else if(newEmployee.getAvatar().isEmpty()){
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Avatar could not match");
                }else if(newEmployee.getName().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Name could not match");
                }else if(newEmployee.getPassword().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee getPassword could not match");
                }else if(newEmployee.getBirthDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee BirthDate could not match");
                }else if(newEmployee.getJoinDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee JoinDate could not match");
                }else if(newEmployee.getMail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Mail could not match");
                }else if(newEmployee.getPersonalMail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee PersonalMail could not match");
                }else if(newEmployee.getMobile().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Mobile could not match");
                }else if(newEmployee.getAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Address could not match");
                }else if(newEmployee.getStatus().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Status could not match");
                }else if(newEmployee.getCreateBy().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee CreateBy could not match");
                }else if(newEmployee.getGender().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Gender could not match");
                }else if(newEmployee.getIpAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee getIpAddress could not match");
                }else {
                    M00Employee employee = new M00Employee();
                    employee.setEmpId(newEmployee.getEmpId());
                    employee.setSiteCode(newEmployee.getSite());
                    employee.setAvatar(newEmployee.getAvatar());
                    employee.setName(newEmployee.getName());
                    employee.setPassword(passwordToMd5Hex);
                    employee.setBirthDate(newEmployee.getBirthDate());
                    employee.setJoinDate(newEmployee.getJoinDate());
                    employee.setMail(newEmployee.getMail());
                    employee.setPersonalMail(newEmployee.getPersonalMail());
                    employee.setMobile(newEmployee.getMobile());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setEmpStatus(newEmployee.getStatus());
                    employee.setCreateBy(newEmployee.getCreateBy());
                    employee.setGender(newEmployee.getGender());
                    employee.setIpAddress(newEmployee.getIpAddress());
                    employee.setRole("ADMIN");
                    M00Employee m00Employee = serviceLifecycle.requestM00EmployeeService().register(employee);

                    List<Pme00RoleUser> listRoleUser = newEmployee.getListRoleUser();
                    Set<Integer> setId = listRoleUser.stream()
                            .map(Pme00RoleUser::getRoleId)
                            .collect(Collectors.toSet());

                    for (Pme00RoleUser pme00RoleUser : listRoleUser) {
                        if (setId.contains(pme00RoleUser.getRoleId())) {
                            serviceLifecycle.requestPme00RoleUserService()
                                    .register(pme00RoleUser);
                            setId.remove(pme00RoleUser.getRoleId());
                        }
                    }
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
                    pme00AllLevel2EmployeeResponse.setMessage("Employee has been created successfully");
                }

            } catch (Exception e){
            e.printStackTrace();
                pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                pme00AllLevel2EmployeeResponse.setMessage(e.getMessage());
            }
        }
        return pme00AllLevel2EmployeeResponse;
    }
    @Override
    public PmeRoleResponse findAllRole(ServiceLifecycle serviceLifecycle){
        PmeRoleResponse pmeRoleResponse = new PmeRoleResponse();
        List<Pme00Role> pme00Roles = serviceLifecycle.requestPme00RoleService().findAll();
        pmeRoleResponse.setStatus(HttpStatus.OK.value());
        pmeRoleResponse.setListData(pme00Roles);
        pmeRoleResponse.setMessage("Get all role successfully");
        return pmeRoleResponse;
    }


    @Override
    public Pme00AllLevel2EmployeeResponse editEmployee(ServiceLifecycle serviceLifecycle,
                                                        Pme00Employee pme00Employee){
//        List<Pme00RoleUser> pme00RoleUsers = pme00Employee.getListRoleUser();
////        pme00RoleUsers.add()
////                .stream()
////                .flatMap(i -> i.getListRoleUser()
////                .stream()).collect(Collectors.toList());
//        //Update for List<Pme00Employee>
//        serviceLifecycle.requestM00EmployeeService().modifyByList2(pme00Employee);
//        //Update for List<Pme00RoleUser>
//        serviceLifecycle.requestPme00RoleUserService().modify(pme00RoleUsers);
//        //set response
//        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
//        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
//        pme00AllLevel2EmployeeResponse.setMessage("Update successfully!");
//
//        return pme00AllLevel2EmployeeResponse;

        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse =new Pme00AllLevel2EmployeeResponse();
            String empId = pme00Employee.getEmpId();
            M00Employee checkEmployee = serviceLifecycle.requestM00EmployeeService()
                    .find(pme00Employee.getEmpId());
            if(checkEmployee==null){
                pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                pme00AllLevel2EmployeeResponse.setMessage("This employee could not be found");
            }else {
                List<Pme00RoleUser> editRoleUsers = pme00Employee.getListRoleUser();
                List<Pme00RoleUser> checkRoleUsers = serviceLifecycle.requestPme00RoleUserService()
                        .findRoleUserByEmpId(empId);
                for (Pme00RoleUser checkRoleUser : checkRoleUsers) {
                    if (empId.equals(checkRoleUser.getEmpId())) {
                        int roleUserId = checkRoleUser.getId();
                        serviceLifecycle.requestPme00RoleUserService().remove(roleUserId);
                    }
                }
                for(int i=0; i<editRoleUsers.size(); i++){
                    editRoleUsers.get(i).setRoleId(pme00Employee.getListRoleUser().get(i).getRoleId());
                }
                checkEmployee.setEmpId(pme00Employee.getEmpId());
                if(pme00Employee.getSiteCode().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee SiteCode could not match");
                }else if(pme00Employee.getAvatar().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Avatar could not match");
                }else if(pme00Employee.getName().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee name could not match");
                }else if(pme00Employee.getBirthDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee birthDate could not match");
                }else if(pme00Employee.getJoinDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee joinDate could not match");
                }else if(pme00Employee.getMail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee email could not match");
                }else if(pme00Employee.getPersonalMail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee personalMail could not match");
                }else if(pme00Employee.getMobile().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee mobile could not match");
                }else if(pme00Employee.getAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee address could not match");
                }else if(pme00Employee.getStatus().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee status could not match");
                }else if(pme00Employee.getGender().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee gender could not match");
                }else if(pme00Employee.getIpAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee ipAddress could not match");
                }else {
                    //set gia tri
                    checkEmployee.setSiteCode(pme00Employee.getSiteCode());
                    checkEmployee.setAvatar(pme00Employee.getAvatar());
                    checkEmployee.setName(pme00Employee.getName());
                    if(pme00Employee.getPassword().isEmpty()){
                        checkEmployee.setPassword(checkEmployee.getPassword());
                    }else {
                        String passwordToMd5Hex = DigestUtils
                                .md5Hex(pme00Employee.getPassword());
                        checkEmployee.setPassword(passwordToMd5Hex);
                    }
                    checkEmployee.setPassword(checkEmployee.getPassword());
                    checkEmployee.setIpAddress(pme00Employee.getIpAddress());
                    checkEmployee.setBirthDate(pme00Employee.getBirthDate());
                    checkEmployee.setJoinDate(pme00Employee.getJoinDate());
                    checkEmployee.setMail(pme00Employee.getMail());
                    checkEmployee.setPersonalMail(pme00Employee.getPersonalMail());
                    checkEmployee.setIpAddress(pme00Employee.getIpAddress());
                    checkEmployee.setMobile(pme00Employee.getMobile());
                    checkEmployee.setAddress(pme00Employee.getAddress());
                    checkEmployee.setEmpStatus(pme00Employee.getStatus());
                    checkEmployee.setGender(pme00Employee.getGender());
                    checkEmployee.setRole("ADMIN");
                    //xu ly
                    serviceLifecycle.requestM00EmployeeService().modify(checkEmployee);
                    serviceLifecycle.requestPme00RoleUserService().modify(editRoleUsers);

                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
                    pme00AllLevel2EmployeeResponse.setMessage("Edit employee successfully");

                }
            }
        return pme00AllLevel2EmployeeResponse;
    }

    public Pme00GenderResponse findGender(ServiceLifecycle serviceLifecycle){
        Pme00GenderResponse pme00GenderResponse = new Pme00GenderResponse();
        final int cdTpId = 68;
        List<M00Codes030> m00Codes030s = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(cdTpId);
        List<Pme00Gender> pme00Genders = new ArrayList<>();
        for (M00Codes030 m00Codes030 : m00Codes030s){
            Pme00Gender pme00Gender = new Pme00Gender();
            pme00Gender.setCdV(m00Codes030.getCdV());
            pme00Gender.setCdvMeaning(m00Codes030.getCdvMeaning());
            pme00Genders.add(pme00Gender);
        }
        pme00GenderResponse.setStatus(HttpStatus.OK.value());
        pme00GenderResponse.setListData(pme00Genders);
        pme00GenderResponse.setMessage("Get all gender successfully");
        return pme00GenderResponse;
    }

}
