package com.gxa.modules.oss.factory;

import com.gxa.common.utils.SpringContextUtils;
import com.gxa.modules.oss.config.StorageConfig;
import com.gxa.modules.oss.service.AbstractStorageService;
import com.gxa.modules.oss.service.impl.*;
import com.gxa.modules.oss.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文件上传Factory
 *
 */
public final class OSSFactory {

//    @Autowired
    //获取云存储配置信息
//    private static StorageConfig storageConfig;

    public static AbstractStorageService build(){

        StorageConfig storageConfig = (StorageConfig) SpringContextUtils.getBean("storageConfig");


        if(storageConfig.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(storageConfig);
        }else if(storageConfig.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(storageConfig);
        }else if(storageConfig.getType() == Constant.CloudService.TCLOUD.getValue()){
            return new TcloudCloudStorageService(storageConfig);
        }else if(storageConfig.getType() == Constant.CloudService.FASTDFS.getValue()){
            return new FastDFSCloudStorageService(storageConfig);
        }else if(storageConfig.getType() == Constant.CloudService.LOCAL.getValue()){
            return new LocalStorageService(storageConfig);
        }else if(storageConfig.getType() == Constant.CloudService.MINIO.getValue()){
            return new MinioCloudStorageService(storageConfig);
        }

        return null;
    }

}
 
 
