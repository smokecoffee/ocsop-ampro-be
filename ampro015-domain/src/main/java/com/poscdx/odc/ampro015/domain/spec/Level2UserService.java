package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;
import com.poscdx.odc.ampro015.domain.entity.payload.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface Level2UserService {
    Boolean existsByName(String name);
    UserResponse addUser(ExcanUser excanUser);
    ExcanUser findById(String id);
    UserResponse getUserResponseById(String id);
    UserResponse getUserByCondition(String userName, String roleId, String statusId);
    UserResponse update(ExcanUser excanUser);
    UserResponse findAll();
    String deleteUserById(String id);
    String uploadFile(String folderAssetName, MultipartFile image);

}
