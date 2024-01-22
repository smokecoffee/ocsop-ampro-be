package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2EmployeeService;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Level2EmployeeLogic implements Level2EmployeeService {
    @Override
    public Pme00AllLevel2EmployeeResponse getListPmeEmployee(ServiceLifecycle serviceLifecycle) {
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        List<Object[]> pme00Employees = serviceLifecycle.requestM00EmployeeService().findAllEmployee();
        List<Pme00Employee> pme00Employees1 = new ArrayList<>();
        for(Object[] object : pme00Employees){
            Pme00Employee employee = new Pme00Employee(object);
            pme00Employees1.add(employee);
        }
        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
        pme00AllLevel2EmployeeResponse.setListData(pme00Employees1);
        pme00AllLevel2EmployeeResponse.setMessage("Get all employee successfully");
        return pme00AllLevel2EmployeeResponse;
    }

    @Override
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(ServiceLifecycle serviceLifecycle,
                                                            String site, String status, String name, String empId, String joinDateFrom, String joinDateTo){
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        List<Object[]> listPme00Employee = serviceLifecycle.requestM00EmployeeService()
                .searchPmeEmployee(site, status, name, empId, joinDateFrom, joinDateTo);
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
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        try{
            M00Employee employee = new M00Employee();
            employee.setEmpId(newEmployee.getEmpId());
            employee.setSiteCode(newEmployee.getSite());
            employee.setAvatar(newEmployee.getAvatar());
            employee.setName(newEmployee.getName());
            employee.setPassword(newEmployee.getPassword());
            employee.setBirthday(newEmployee.getBirthDate());
            employee.setJoinDate(newEmployee.getJoinDate());
            employee.setMail(newEmployee.getEmail());
            employee.setPersonalMail(newEmployee.getPersonalMail());
            employee.setMobile(newEmployee.getMobile());
            employee.setAddress(newEmployee.getAddress());
            employee.setEmpStatus(newEmployee.getStatus());
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
            pme00AllLevel2EmployeeResponse.setMessage("This meeting has been created");
        }
//        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
//        pme00AllLevel2EmployeeResponse.setMessage("Add employee successfully");
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
        String  empId = pme00EmployeeList.get(0).getEmpId();
        Pme00AllLevel2EmployeeResponse findEmployeeById = serviceLifecycle.requestLevel2EmployeeService()
                .searchPmeEmployee(serviceLifecycle, "", "", "", empId, "", "");
        if(findEmployeeById==null){
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
                editRoleUsers.get(i).setRoleId(pme00EmployeeList.get(0).getListRoleUser().get(i).getRoleId());
            }
            M00Employee employee = new M00Employee();
            employee.setEmpId(pme00EmployeeList.get(0).getEmpId());
            employee.setSiteCode(pme00EmployeeList.get(0).getSite());
            employee.setAvatar(pme00EmployeeList.get(0).getAvatar());
            employee.setName(pme00EmployeeList.get(0).getName());
            employee.setPassword(pme00EmployeeList.get(0).getPassword());
            employee.setBirthday(pme00EmployeeList.get(0).getBirthDate());
            employee.setJoinDate(pme00EmployeeList.get(0).getJoinDate());
            employee.setMail(pme00EmployeeList.get(0).getEmail());
            employee.setPersonalMail(pme00EmployeeList.get(0).getPersonalMail());
            employee.setMobile(pme00EmployeeList.get(0).getMobile());
            employee.setAddress(pme00EmployeeList.get(0).getAddress());
            employee.setEmpStatus(pme00EmployeeList.get(0).getStatus());
            employee.setRole("ADMIN");

            serviceLifecycle.requestM00EmployeeService().modify(employee);
            serviceLifecycle.requestPme00RoleUserService().modify(editRoleUsers);

            pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
            pme00AllLevel2EmployeeResponse.setListData(pme00EmployeeList);
            pme00AllLevel2EmployeeResponse.setMessage("Edit employee successfully");
        }
        return pme00AllLevel2EmployeeResponse;
    }

}
