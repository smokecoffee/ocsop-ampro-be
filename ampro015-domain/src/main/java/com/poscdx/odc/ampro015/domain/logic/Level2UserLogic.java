package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;
import com.poscdx.odc.ampro015.domain.entity.payload.response.UserResponse;
import com.poscdx.odc.ampro015.domain.spec.Level2UserService;
import com.poscdx.odc.ampro015.domain.store.ExcanRoleStore;
import com.poscdx.odc.ampro015.domain.store.ExcanUserStore;
import com.poscdx.odc.ampro015.domain.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
public class Level2UserLogic implements Level2UserService {

    @Autowired
    private ExcanUserStore excanUserStore;
    @Autowired
    private ExcanRoleStore excanRoleStore;

    @Override
    public Boolean existsByName(String name) {
        return excanUserStore.existsByName(name);
    }

    @Override
    public UserResponse addUser(ExcanUser excanUser) {
        UserResponse result = new UserResponse();
        if(excanUserStore.findById(excanUser.getId()) != null){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("User Id " + excanUser.getId() + " already exist!");
        }else if(excanRoleStore.findById(excanUser.getRoleId()) == null){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("Role Id " + excanUser.getRoleId() + " not exist!");
        } else if(excanUserStore.existsByEmail(excanUser.getEmail())){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("Email " + excanUser.getEmail() + " already exist, chose different email pls!");
        } else if(excanUserStore.existsByName(excanUser.getName())){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("User name " + excanUser.getName() + " already exist, chose different email pls!");
        } else {
            excanUserStore.create(excanUser);
            result.setCode(HttpStatus.SC_CREATED);
            List<ExcanUser> data = new ArrayList<>();
            data.add(excanUser);
            result.setData(data);
            result.setMessage("Create user successfully!");
        }
        return result;
    }
    @Override
    public ExcanUser findById(String id) {
        return excanUserStore.findById(id);
    }

    @Override
    public UserResponse getUserResponseById(String id) {
        UserResponse result = new UserResponse();
        //set response
        List<ExcanUser> data = new ArrayList<>();
        data.add(this.findById(id));
        result.setData(data);
        result.setCode(HttpStatus.SC_OK);
        result.setMessage("Get all user successfully!");
        return result;
    }

    @Override
    public UserResponse getUserByCondition(String userName, String roleId, String statusId) {
        UserResponse result = new UserResponse();
        List<ExcanUser> searchUsers = excanUserStore.findAll();

        searchUsers = searchUsers.stream().filter(i -> StringUtils.containsIgnoreCase(i.getName(), userName)).collect(Collectors.toList());
        if(!roleId.equalsIgnoreCase("all")){
            searchUsers = searchUsers.stream().filter(i -> i.getRoleId() == Integer.parseInt(roleId)).collect(Collectors.toList());
        }
        if(!statusId.equalsIgnoreCase("all")){
            searchUsers = searchUsers.stream().filter(i -> i.getStatus() == Integer.parseInt(statusId)).collect(Collectors.toList());
        }

        result.setData(searchUsers);
        result.setCode(HttpStatus.SC_OK);
        result.setMessage("Get user by condition successfully!");

        return result;
    }

    @Override
    public UserResponse update(ExcanUser excanUser) {
        UserResponse result = new UserResponse();
        if(excanUserStore.findById(excanUser.getId()) != null){

            if(excanRoleStore.findById(excanUser.getRoleId()) == null){
                result.setCode(HttpStatus.SC_BAD_REQUEST);
                result.setMessage("Role Id " + excanUser.getRoleId() + " not exist!");
            } else if(existEmailWithOutCurrentEmail(excanUser.getId(), excanUser.getEmail())){
                result.setCode(HttpStatus.SC_BAD_REQUEST);
                result.setMessage("Email " + excanUser.getEmail() + " already exist, chose different email pls!");
            } else if(existNameWithOutCurrentName(excanUser.getId(), excanUser.getName())){
                result.setCode(HttpStatus.SC_BAD_REQUEST);
                result.setMessage("User name " + excanUser.getName() + " already exist, chose different email pls!");
            } else {
                excanUserStore.update(excanUser);
                result.setCode(HttpStatus.SC_OK);
                List<ExcanUser> data = new ArrayList<>();
                data.add(excanUser);
                result.setData(data);
                result.setMessage("Update user successfully!");
            }
        } else {
            result.setCode(HttpStatus.SC_OK);
            result.setMessage("User Id " + excanUser.getId() + " not exits!");
        }
        return result;
    }

    public boolean existEmailWithOutCurrentEmail(String id, String email){
        ExcanUser currentUser = excanUserStore.findById(id);
        if(currentUser.getEmail().equals(email)){
            return false;
        } else {
            List<ExcanUser> allUser = excanUserStore.findAll();
            List<String> allEmail = allUser.stream().map(ExcanUser::getEmail).collect(Collectors.toList());
            return allEmail.contains(email);
        }
    }

    public boolean existNameWithOutCurrentName(String id, String name){
        ExcanUser currentUser = excanUserStore.findById(id);
        if(currentUser.getName().equals(name)){
            return false;
        } else {
            List<ExcanUser> allUser = excanUserStore.findAll();
            List<String> allName = allUser.stream().map(ExcanUser::getName).collect(Collectors.toList());
            return allName.contains(name);
        }
    }

    @Override
    public UserResponse findAll() {
        UserResponse result = new UserResponse();
        result.setCode(HttpStatus.SC_OK);
        List<ExcanUser> data = excanUserStore.findAll();
        result.setData(data);
        result.setMessage("Get all user successfully!");
        return result;
    }

    @Override
    public String deleteUserById(String id) {
        String result = "";
        ExcanUser existUser = this.findById(id);
        if(existUser != null){
            excanUserStore.delete(id);
            result = "User with Id " + id + " deleted successfully!";
        } else {
            result = "User with Id " + id + " not exist!";
        }
        return result;
    }

    @Override
    public String uploadFile(String folderAssetName, MultipartFile image) {
        String fileName = image.getOriginalFilename();
        String resultString = "";
        Path root = Paths.get(Constants.UPLOAD_STATIC_USER_FOLDER_PATH + "\\");

        try {
            Path folderPath = root.resolve(folderAssetName);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            } else {
                // Delete all files in the folder
                Files.walk(folderPath)
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                Files.delete(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }

            Path file = folderPath.resolve(image.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            resultString = fileName;
        } catch (Exception e) {
            //logger.error("Failed to upload file: {}", e.getMessage(), e);
            resultString = "Upload unsuccessfully!";
        }
        return resultString;
    }
}
