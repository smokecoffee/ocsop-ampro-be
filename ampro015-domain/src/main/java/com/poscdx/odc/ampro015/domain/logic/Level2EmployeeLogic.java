package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            pme00AllLevel2EmployeeResponse.setMessage("Employee is exits");
        }else {
            try{
                M00Employee employee = new M00Employee();
                employee.setEmpId(newEmployee.getEmpId());
                employee.setSiteCode(newEmployee.getSite());
                employee.setAvatar(newEmployee.getAvatar());
                employee.setName(newEmployee.getName());
                employee.setPassword(passwordToMd5Hex);
                employee.setBirthday(newEmployee.getBirthDate());
                employee.setJoinDate(newEmployee.getJoinDate());
                employee.setMail(newEmployee.getEmail());
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

            } catch (Exception e){
//            e.printStackTrace();
                pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                pme00AllLevel2EmployeeResponse.setMessage("This employee has been created");
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
                                                        List<Pme00Employee> pme00EmployeeList){
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        if(pme00EmployeeList.isEmpty()){
            pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
            pme00AllLevel2EmployeeResponse.setMessage("This employee could not be found");
        }else {
            String empId = pme00EmployeeList.get(0).getEmpId();
            Pme00Employee pme00EmployeeEdit = pme00EmployeeList.get(0);
            M00Employee checkEmployee = serviceLifecycle.requestM00EmployeeService()
                    .find(pme00EmployeeList.get(0).getEmpId());
            if(checkEmployee==null){
                pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                pme00AllLevel2EmployeeResponse.setMessage("This employee could not be found");
            }else {
                List<Pme00RoleUser> editRoleUsers = pme00EmployeeList.get(0).getListRoleUser();
                List<Pme00RoleUser> checkRoleUsers = serviceLifecycle.requestPme00RoleUserService()
                        .findRoleUserByEmpId(empId);
                for (Pme00RoleUser checkRoleUser : checkRoleUsers) {
                    if (empId.equals(checkRoleUser.getEmpId())) {
                        int roleUserId = checkRoleUser.getId();
                        serviceLifecycle.requestPme00RoleUserService().remove(roleUserId);
                    }
                }
                for(int i=0; i<editRoleUsers.size(); i++){
                    editRoleUsers.get(i).setRoleId(pme00EmployeeEdit.getListRoleUser().get(i).getRoleId());
                }
                checkEmployee.setEmpId(pme00EmployeeEdit.getEmpId());
                if(pme00EmployeeEdit.getSiteCode().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee SiteCode could not match");
                }else if(pme00EmployeeEdit.getAvatar().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee Avatar could not match");
                }else if(pme00EmployeeEdit.getName().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee name could not match");
                }else if(pme00EmployeeEdit.getBirthDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee birthDate could not match");
                }else if(pme00EmployeeEdit.getJoinDate().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee joinDate could not match");
                }else if(pme00EmployeeEdit.getEmail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee email could not match");
                }else if(pme00EmployeeEdit.getPersonalMail().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee personalMail could not match");
                }else if(pme00EmployeeEdit.getMobile().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee mobile could not match");
                }else if(pme00EmployeeEdit.getAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee address could not match");
                }else if(pme00EmployeeEdit.getStatus().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee status could not match");
                }else if(pme00EmployeeEdit.getGender().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee gender could not match");
                }else if(pme00EmployeeEdit.getIpAddress().isEmpty()) {
                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    pme00AllLevel2EmployeeResponse.setMessage("This employee ipAddress could not match");
                }else {
                    //set gia tri
                    checkEmployee.setSiteCode(pme00EmployeeEdit.getSiteCode());
                    checkEmployee.setAvatar(pme00EmployeeEdit.getAvatar());
                    checkEmployee.setName(pme00EmployeeEdit.getName());
                    if(pme00EmployeeEdit.getPassword().isEmpty()){
                        checkEmployee.setPassword(checkEmployee.getPassword());
                    }else {
                        String passwordToMd5Hex = DigestUtils
                                .md5Hex(pme00EmployeeEdit.getPassword());
                        checkEmployee.setPassword(passwordToMd5Hex);
                    }
                    checkEmployee.setPassword(checkEmployee.getPassword());
                    checkEmployee.setIpAddress(pme00EmployeeEdit.getIpAddress());
                    checkEmployee.setBirthday(pme00EmployeeEdit.getBirthDate());
                    checkEmployee.setJoinDate(pme00EmployeeEdit.getJoinDate());
                    checkEmployee.setMail(pme00EmployeeEdit.getEmail());
                    checkEmployee.setPersonalMail(pme00EmployeeEdit.getPersonalMail());
                    checkEmployee.setIpAddress(pme00EmployeeEdit.getIpAddress());
                    checkEmployee.setMobile(pme00EmployeeEdit.getMobile());
                    checkEmployee.setAddress(pme00EmployeeEdit.getAddress());
                    checkEmployee.setEmpStatus(pme00EmployeeEdit.getStatus());
                    checkEmployee.setGender(pme00EmployeeEdit.getGender());
                    checkEmployee.setRole("ADMIN");
                    //xu ly
                    serviceLifecycle.requestM00EmployeeService().modify(checkEmployee);
                    serviceLifecycle.requestPme00RoleUserService().modify(editRoleUsers);

                    pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
                    pme00AllLevel2EmployeeResponse.setListData(pme00EmployeeList);
                    pme00AllLevel2EmployeeResponse.setMessage("Edit employee successfully");

                }
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
