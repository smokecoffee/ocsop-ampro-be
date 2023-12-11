package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pme00ProjectInfoLogic implements Pme00ProjectInfoService {
    private final Pme00ProjectInfoStore store;

    public Pme00ProjectInfoLogic(Pme00ProjectInfoStore store) {
        this.store = store;
    }

    @Override
    public Pme00ProjectInfo find(String cdVId) {
        return this.store.retrieve(cdVId);
    }

    @Override
    public List<Pme00ProjectInfo> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00ProjectInfo> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00ProjectInfo register(Pme00ProjectInfo entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(String cdVId) {
        this.store.delete(cdVId);
    }

    @Override
    public List<EmployeeDto> getActiveEmployee() {
        List<Object[]> resultList = this.store.getActiveEmployee();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            employeeDtoList.add(new EmployeeDto(obj));
        }
        return employeeDtoList;
    }

    /**
     * Insert infor project
     *
     * @param serviceLifecycle
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public Pme00ProjectListDto registerProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto) throws SQLException {

        // Check project code exists
        if(checkExistsM00Codes030(serviceLifecycle, ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID, dto.getCdV())
                && checkExistsPme00ProjectInfo(serviceLifecycle, dto.getCdV())){
            return  null;
        }

        // Insert data M00Codes030
        M00Codes030 entityCodes030 = new M00Codes030();

        int seq = serviceLifecycle.requestM00Codes030Service().getMaxSeqInquiry(ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID) + 1;

        entityCodes030.setCdTpId(dto.getCdTpId());
        entityCodes030.setCategoryGroupId(dto.getCategoryGroupId());
        entityCodes030.setCdV(dto.getCdV());
        entityCodes030.setCdvMeaning(dto.getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getCdVExplain());
        entityCodes030.setCdVInquirySeq(seq);
        entityCodes030.setCreatedProgramId(dto.getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getLastUpdateTimestamp());

        serviceLifecycle.requestM00Codes030Service().register(entityCodes030);

        // Insert data Pme00ProjectInfo
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getCdV());
        entityInfo.setPeriod(dto.getPeriod());
        entityInfo.setStartDate(dto.getStartDate());
        entityInfo.setEndDate(dto.getEndDate());
        entityInfo.setKoreaPm(dto.getKoreaPm());
        entityInfo.setVietnamPl(dto.getVietNamPl());
        entityInfo.setStatus(dto.getStatus());
        entityInfo.setFramework(dto.getFramework());

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

        return dto;
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
    public Pme00ProjectListDto modifyProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto) throws SQLException {

        // Check project code exists
        if(!checkExistsM00Codes030(serviceLifecycle, ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID, dto.getCdV())
                || !checkExistsPme00ProjectInfo(serviceLifecycle, dto.getCdV())){
            return  null;
        }

        // Update data M00Codes030
        List<M00Codes030> lstCodes030 = new ArrayList<>();
        M00Codes030 entityCodes030 = new M00Codes030();

        entityCodes030.setCdTpId(dto.getCdTpId());
        entityCodes030.setCategoryGroupId(dto.getCategoryGroupId());
        entityCodes030.setCdV(dto.getCdV());
        entityCodes030.setCdvMeaning(dto.getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getCdVExplain());
        entityCodes030.setCdVInquirySeq(dto.getCdVInquirySeq());
        entityCodes030.setCreatedProgramId(dto.getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getLastUpdateTimestamp());
        lstCodes030.add(entityCodes030);

        serviceLifecycle.requestM00Codes030Service().modify(lstCodes030);

        // Update data Pme00ProjectInfo
        List<Pme00ProjectInfo> lstProjectInfo = new ArrayList<>();
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getCdV());
        entityInfo.setPeriod(dto.getPeriod());
        entityInfo.setStartDate(dto.getStartDate());
        entityInfo.setEndDate(dto.getEndDate());
        entityInfo.setKoreaPm(dto.getKoreaPm());
        entityInfo.setVietnamPl(dto.getVietNamPl());
        entityInfo.setStatus(dto.getStatus());
        entityInfo.setFramework(dto.getFramework());
        lstProjectInfo.add(entityInfo);

        serviceLifecycle.requestPme00ProjectInfoService().modify(lstProjectInfo);

        // Update data Pme00Member
        // Delete old Pme00Member
        serviceLifecycle.requestPme00MemberService().deleteMemberById(dto.getCdV(), null);

        // Insert new Pme00Member
        Pme00Member entityMember;
        for (Pme00Member member : dto.getLstMember()) {
            entityMember = new Pme00Member();
            entityMember.setCdVId(member.getCdVId());
            entityMember.setEmpId(member.getEmpId());
            entityMember.setEmpName(member.getEmpName());

            serviceLifecycle.requestPme00MemberService().register(entityMember);
        }

        return dto;
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

    }

    @Override
    public List<Pme00ProjectListDto> getProjectList(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto) {
        List<Pme00ProjectListDto> ProjectList = this.store.getProjectList(dto);
        for (Pme00ProjectListDto rsDto : ProjectList) {
            //ProjectList.add(new Pme00ProjectListDto(obj));
        }
        return ProjectList;
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
