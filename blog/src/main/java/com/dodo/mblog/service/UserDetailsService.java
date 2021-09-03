package com.dodo.mblog.service;

import com.dodo.mblog.entity.UserDetails;
import com.dodo.mblog.mapper.UserDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserDetailsMapper userDetailsMapper;

    @Value("${app.config.uploadPath}")
    private String uploadDiskPath;

    public UserDetails findByEmail(String email){
        return userDetailsMapper.findByEmail(email);
    }

    public UserDetails findByUserName(String username){
        return userDetailsMapper.findByUserName(username);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void addUserDetails(UserDetails user){
        userDetailsMapper.insertUserDetails(user);
    }


    @Transactional(noRollbackFor = Exception.class)
    public void updateUser(UserDetails user) {
        userDetailsMapper.updateUser(user);
    }

    public List<UserDetails> getUsers() {
        return userDetailsMapper.getUsers();
    }

    public void deleteUser(String email) {
        userDetailsMapper.deleteUser(email);
    }

    /**
     * 验证上传的文件
     * @param file
     * @return
     */
    private boolean validedUploadFile(MultipartFile file)  {
        String valideImageSuffix = ".jpg.jpeg.png.bmp";

        if (file.isEmpty()) {
            logger.error("上传的文件为空！");
            return false;
        }else {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));  // 文件后缀名
            return valideImageSuffix.contains(suffix);
        }
    }

    /**
     * 生成上传文件名称，按照当时时间命名
     * @return
     */
    private String generteFileName()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return "pic_" + sdf.format(new Date());
    }

    public String saveFile(MultipartFile file) throws IOException {
        if(validedUploadFile(file)){
            String name = generteFileName();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));  // 文件后缀名
            String fileUrl = uploadDiskPath + name + suffix;
            FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(fileUrl));
            logger.debug("file save success! " + fileUrl);
            return fileUrl;
        }else {
            logger.error("文件校验失败，请检查文件格式！");
        }

        return null;
    }
}
