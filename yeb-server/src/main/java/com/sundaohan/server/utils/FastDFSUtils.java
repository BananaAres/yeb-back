package com.sundaohan.server.utils;

import org.apache.poi.ss.formula.functions.T;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.utils
 * @Title FastDFSUtils
 * @Description FastDFS工具类
 * @Date 2021/8/5 下午10:35
 */
public class FastDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * @Title static initializer
     * @Description 初始化客户端，
     * ClientGlobal.init 读取配置文件，并初始化对应属性
     * @Author sundaohan
     * @Params
     * @return
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("初始化FastDFS失败",e.getMessage());
        }

    }

    /**
     * @Title upload
     * @Description 上传文件
     * @Author sundaohan
     * @Params [file]
     * @return java.lang.String[]
     */
    public static String[] upload(MultipartFile file){
        String name = file.getOriginalFilename();
        logger.info("文件名：", name);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try{
            //获取storage客户端
            storageClient = getStorageClient();
            //上传
            uploadResults = storageClient.upload_file(file.getBytes(),name.substring(name.lastIndexOf(".")+1),null);
        }catch (Exception e){
            logger.error("上传文件失败",e.getMessage());
        }
        if(uploadResults == null){
            logger.error("上传失败", storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * @Title getFileInfo
     * @Description 获取文件信息
     * @Author sundaohan
     * @Params [groupName, remoteFileName]
     * @return org.csource.fastdfs.FileInfo
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        }catch (Exception e){
            logger.error("文件信息获取失败",e.getMessage());
        }
        return null;
    }

    /**
     * @Title downFile
     * @Description 下载文件
     * @Author sundaohan
     * @Params []
     * @return java.io.InputStream
     */
    public static InputStream downFile(String groupName, String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
           InputStream inputStream = new ByteArrayInputStream(fileByte);
           return inputStream;
        }catch (Exception e){
            logger.error("文件下载失败",e.getMessage());
        }
        return null;
    }

    /**
     * @Title deleteFile
     * @Description 删除文件
     * @Author sundaohan
     * @Params [groupName, remoteFileName]
     * @return void
     */
    public static void deleteFile(String groupName, String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName,remoteFileName);
        }catch (Exception e){
            logger.error("文件删除失败",e.getMessage());
        }

    }
    
    /**
     * @Title getStorageClient
     * @Description 生成storage客户端
     * @Author sundaohan
     * @Params []
     * @return org.csource.fastdfs.StorageClient
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return  storageClient;
    }
    
    
    /**
     * @Title getTrackerServer
     * @Description 生成tracker服务器
     * @Author sundaohan
     * @Params []
     * @return org.csource.fastdfs.TrackerServer
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    /**
     * @Title getTrackerUrl
     * @Description 获取文件路径
     * @Author sundaohan
     * @Params []
     * @return java.lang.String
     */
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("文件路径获取失败",e.getMessage());
        }
        return "http://"+storageServer.getInetSocketAddress().getHostString()+":80/";
    }
}
