package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2ProjectService;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Level2ProjectLogic implements Level2ProjectService {

    /**
     * Insert infor project
     *
     * @param serviceLifecycle
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public boolean registerProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto) throws SQLException {

        // Check project code exists
        if(checkExistsM00Codes030(serviceLifecycle, ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID, dto.getM00Codes030().getCdV())
                && checkExistsPme00ProjectInfo(serviceLifecycle, dto.getPme00ProjectInfo().getCdV())){
            return  false;
        }

        // Insert data M00Codes030
        M00Codes030 entityCodes030 = new M00Codes030();

        int seq = serviceLifecycle.requestM00Codes030Service().getMaxSeqInquiry(ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID) + 1;

        entityCodes030.setCdTpId(ConstantUtil.CD_TP_ID);
        entityCodes030.setCategoryGroupId(ConstantUtil.CATEGORY_GROUP_ID);
        entityCodes030.setCdV(dto.getM00Codes030().getCdV());
        entityCodes030.setCdvMeaning(dto.getM00Codes030().getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getM00Codes030().getCdVExplain());
        entityCodes030.setCdVInquirySeq(seq);
        entityCodes030.setCreatedProgramId(dto.getM00Codes030().getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getM00Codes030().getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getM00Codes030().getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getM00Codes030().getLastUpdateTimestamp());

        serviceLifecycle.requestM00Codes030Service().register(entityCodes030);

        // Insert data Pme00ProjectInfo
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getPme00ProjectInfo().getCdV());
        entityInfo.setPeriod(dto.getPme00ProjectInfo().getPeriod());
        entityInfo.setStartDate(dto.getPme00ProjectInfo().getStartDate());
        entityInfo.setEndDate(dto.getPme00ProjectInfo().getEndDate());
        entityInfo.setKoreaPm(dto.getPme00ProjectInfo().getKoreaPm());
        entityInfo.setVietnamPl(dto.getPme00ProjectInfo().getVietnamPl());
        entityInfo.setStatus(dto.getPme00ProjectInfo().getStatus());
        entityInfo.setFramework(dto.getPme00ProjectInfo().getFramework());

        serviceLifecycle.requestPme00ProjectInfoService().register(entityInfo);

        // Insert data Pme00Member
        Pme00Member entityMember;
        for (Pme00Member member : dto.getLstMember()) {
            entityMember = new Pme00Member();
            entityMember.setCdVId(member.getCdVId());
            entityMember.setEmpId(member.getEmpId());
            entityMember.setEmpName(member.getEmpName());

            serviceLifecycle.requestPme00MemberService().register(entityMember);
        }

        return true;
    }

    /**
     * Modify project info
     *
     * @param serviceLifecycle
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public boolean modifyProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto) throws SQLException {

        // Check project code exists
        if(!checkExistsM00Codes030(serviceLifecycle, ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID, dto.getM00Codes030().getCdV())
                || !checkExistsPme00ProjectInfo(serviceLifecycle, dto.getPme00ProjectInfo().getCdV())){
            return  false;
        }

        // Update data M00Codes030
        List<M00Codes030> lstCodes030 = new ArrayList<>();
        M00Codes030 entityCodes030 = new M00Codes030();

        entityCodes030.setCdTpId(ConstantUtil.CD_TP_ID);
        entityCodes030.setCategoryGroupId(ConstantUtil.CATEGORY_GROUP_ID);
        entityCodes030.setCdV(dto.getM00Codes030().getCdV());
        entityCodes030.setCdvMeaning(dto.getM00Codes030().getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getM00Codes030().getCdVExplain());
        entityCodes030.setCdVInquirySeq(dto.getM00Codes030().getCdVInquirySeq());
        entityCodes030.setCreatedProgramId(dto.getM00Codes030().getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getM00Codes030().getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getM00Codes030().getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getM00Codes030().getLastUpdateTimestamp());
        lstCodes030.add(entityCodes030);

        serviceLifecycle.requestM00Codes030Service().modify(lstCodes030);

        // Update data Pme00ProjectInfo
        List<Pme00ProjectInfo> lstProjectInfo = new ArrayList<>();
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getPme00ProjectInfo().getCdV());
        entityInfo.setPeriod(dto.getPme00ProjectInfo().getPeriod());
        entityInfo.setStartDate(dto.getPme00ProjectInfo().getStartDate());
        entityInfo.setEndDate(dto.getPme00ProjectInfo().getEndDate());
        entityInfo.setKoreaPm(dto.getPme00ProjectInfo().getKoreaPm());
        entityInfo.setVietnamPl(dto.getPme00ProjectInfo().getVietnamPl());
        entityInfo.setStatus(dto.getPme00ProjectInfo().getStatus());
        entityInfo.setFramework(dto.getPme00ProjectInfo().getFramework());
        lstProjectInfo.add(entityInfo);

        serviceLifecycle.requestPme00ProjectInfoService().modify(lstProjectInfo);

        // Update data Pme00Member
        // Delete old Pme00Member
        serviceLifecycle.requestPme00MemberService().deleteMemberById(dto.getPme00ProjectInfo().getCdV(), null);

        // Insert new Pme00Member
        Pme00Member entityMember;
        for (Pme00Member member : dto.getLstMember()) {
            entityMember = new Pme00Member();
            entityMember.setCdVId(member.getCdVId());
            entityMember.setEmpId(member.getEmpId());
            entityMember.setEmpName(member.getEmpName());

            serviceLifecycle.requestPme00MemberService().register(entityMember);
        }

        return true;
    }

    /**
     * Delete project
     *
     * @param serviceLifecycle
     * @param id
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public void deleteProject(ServiceLifecycle serviceLifecycle, M00Codes030Id id) throws SQLException {

        // Delete member in Pme00Member
        serviceLifecycle.requestPme00MemberService().deleteMemberById(id.getCdV(), null);

        // Delete project Pme00ProjectInfo
        serviceLifecycle.requestPme00ProjectInfoService().remove(id.getCdV());

        // Delete project M00Codes030
        serviceLifecycle.requestM00Codes030Service().remove(id);

        // TODO
        // Delete tasks
    }

    @Override
    public List<ProjectManagementDto> getProjectList(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto) {
        //return this.store.getProjectList(dto);
        List<ProjectManagementDto>  projectList = new ArrayList<>();
        List<M00Codes030> m00Codes030List =
                serviceLifecycle.requestM00Codes030Service()
                        .findM00Codes030(dto.getM00Codes030().getCdV(), dto.getM00Codes030().getCdvMeaning());

        List<Pme00ProjectInfo> pme00ProjectInfoList =
                serviceLifecycle.requestPme00ProjectInfoService()
                        .findProjectInfo(dto.getPme00ProjectInfo().getCdV(), dto.getPme00ProjectInfo().getPeriod()
                                , dto.getPme00ProjectInfo().getKoreaPm(), dto.getPme00ProjectInfo().getVietnamPl()
                                , dto.getPme00ProjectInfo().getFramework(), dto.getPme00ProjectInfo().getStatus()
                                , dto.getPme00ProjectInfo().getStartDate(), dto.getPme00ProjectInfo().getEndDate());


        for (M00Codes030 project : m00Codes030List) {
            for (Pme00ProjectInfo projectInfo : pme00ProjectInfoList) {

                ProjectManagementDto rsDto = new ProjectManagementDto();
                if (project.getCdV().equals(projectInfo.getCdV())){
                    rsDto.setM00Codes030(project);
                    rsDto.setPme00ProjectInfo(projectInfo);

                    List<Pme00Member> lstMember = new ArrayList<>();
                    lstMember = serviceLifecycle.requestPme00MemberService().getListMemberByCdVId(project.getCdV());
                    rsDto.setLstMember(lstMember);

                    projectList.add(rsDto);
                }
            }

        }
        return projectList;
    }

    /**
     * Get project info
     *
     * @param serviceLifecycle
     * @return
     */
    @Override
    public List<ProjectManagementDto> getProjectList(ServiceLifecycle serviceLifecycle) {

        List<ProjectManagementDto> result = new ArrayList<>();

        //Get project list
        List<Pme00ProjectInfo> projectList = serviceLifecycle.requestPme00ProjectInfoService().findProjectInfo(null,
                0, null, null, null, null, null, null);

        //Sort projects by status
        Collections.sort(projectList, Comparator.comparing(Pme00ProjectInfo::getStatus));

        if (!projectList.isEmpty()) {
            for (Pme00ProjectInfo pme00ProjectInfo : projectList) {
                ProjectManagementDto newObject = new ProjectManagementDto();

                //Set project info
                newObject.setPme00ProjectInfo(pme00ProjectInfo);

                //Get task list
                List<M00TaskDto> taskList = serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle,pme00ProjectInfo.getCdV());
                newObject.setLstTask(taskList);
                //Set member list
                List<Pme00Member> listMember = serviceLifecycle.requestPme00MemberService().getListMemberByCdVId(pme00ProjectInfo.getCdV());
                newObject.setLstMember(listMember);

                //Set project name
                M00Codes030Id m00Codes030Id = new M00Codes030Id(ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID, pme00ProjectInfo.getCdV());
                String projectName = serviceLifecycle.requestM00Codes030Service().find(m00Codes030Id).getCdvMeaning();
                M00Codes030 m00Codes030 = new M00Codes030();
                m00Codes030.setCdvMeaning(projectName);
                newObject.setM00Codes030(m00Codes030);

                //Set project progress
                long completedTasks = taskList.stream()
                        .filter(item -> "O".equals(item.getTask().getStatus()))
                        .count();
                double completionPercentage = (completedTasks * 100.0) / taskList.size();
                int progress = (int) completionPercentage;
                newObject.setProgress(progress);
                System.out.println("Completion Percentage: " + completionPercentage);

                result.add(newObject);
            }
        }

        return result;
    }

    /**
     * Check exists project code
     * @param serviceLifecycle
     * @param cdTpId
     * @param cateGroupId
     * @param cdV
     * @return
     */
    private boolean checkExistsM00Codes030(ServiceLifecycle serviceLifecycle, int cdTpId, int cateGroupId, String cdV){

        M00Codes030Id codes030Id = new M00Codes030Id();
        codes030Id.setCdTpId(cdTpId);
        codes030Id.setCategoryGroupId(cateGroupId);
        codes030Id.setCdV(cdV);

        M00Codes030 codes030 = serviceLifecycle.requestM00Codes030Service().find(codes030Id);

        return codes030 != null;
    }

    /**
     * Check exists project info code
     * @param serviceLifecycle
     * @param cdV
     * @return
     */
    private boolean checkExistsPme00ProjectInfo(ServiceLifecycle serviceLifecycle, String cdV){
        Pme00ProjectInfo projectInfo = new Pme00ProjectInfo();

        projectInfo = serviceLifecycle.requestPme00ProjectInfoService().find(cdV);

        return projectInfo != null;
    }
}
