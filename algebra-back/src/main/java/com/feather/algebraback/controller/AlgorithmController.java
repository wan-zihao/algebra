package com.feather.algebraback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Algorithm;
import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.service.impl.AlgorithmServiceImpl;
import com.feather.algebraback.service.impl.UserServiceImpl;
import com.feather.algebraback.utils.InputStreamUtil;
import com.feather.algebraback.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@RestController
public class AlgorithmController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private AlgorithmServiceImpl algorithmService;

    /**
     * 用户上传算法
     *
     * @param file
     * @param principal
     * @return
     */
    @Transactional
    @RequestMapping("/upload")
    public RespBean upload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (principal == null) {
            return RespBean.error("尚未登录,请先登录",null);
        }
        //获得用户id
        Integer userId = userService.getUserByUserName(principal.getName()).getUserId();
        try {
            //获得文件保存路径
            File tempPath = new File(ResourceUtils.getURL("classpath:").getPath());
            String path = tempPath.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator +"resources" + File.separator + "upload" + File.separator;
            if (file.isEmpty()) {
                log.info("上传的文件为空");
                return RespBean.error("上传失败,请重新上传");
            }
            //获得文件原来的名称
            String filename = file.getOriginalFilename();
            //获得uuid
            String uuid= UuidUtil.getUuid();
            assert filename != null;
            //获得后缀
            String suffix=filename.substring(filename.lastIndexOf("."));
            String resultName = path + uuid + suffix;
            //字节流转为字符串
            String content = InputStreamUtil.getInputStreamString(file.getInputStream());
            file.transferTo(new File(resultName));
            //保存文件上传的数据
            algorithmService.save(new Algorithm(uuid,userId,content));
            //添加日志
            MessageController.messageService.save(new Message("id为" + userId + "的用户上传了文件" + filename));
        } catch (Exception e) {
            e.printStackTrace();
            log.info("出现异常");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error("上传失败，请重新上传");
        }
        return RespBean.success("上传成功");
    }

    /**
     * 文件下载
     *
     * @param uuid
     * @return
     */
    @RequestMapping("/download")
    public RespBean download(@RequestParam("uuid") String uuid,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try {
            ServletContext servletContext = request.getSession().getServletContext();
            //获得文件保存路径
            File tempPath = new File(ResourceUtils.getURL("classpath:").getPath());
            String path = tempPath.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator +"resources" + File.separator + "upload" + File.separator;
            //获得文件路径
            String fileName = path + uuid + ".txt";
            //用字节流关联文件
            fis = new FileInputStream(fileName);
            //获得mime类型
            String mimeType = servletContext.getMimeType(fileName);
            //设置响应头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename=" + uuid + ".txt");
            //将输入流的数据写入到输出流
            sos = response.getOutputStream();
            int len=0;
            byte[] buffer = new byte[1024 * 8];
            while((len=fis.read(buffer)) > -1){
                sos.write(buffer,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            assert sos != null;
            sos.close();
            fis.close();
        }
        return null;
    }

    /**
     * 分页查询算法文件
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/algorithm")
    public RespBean selectAlgorithmPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                        @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        return RespBean.success(null, algorithmService.selectAlgorithmPage(new Page<>(currentPage,pageSize)));
    }

    @DeleteMapping("/algorithm/{id}")
    public RespBean deleteAlgorithmById(@PathVariable("id") Integer algorithmId,
                                        @RequestParam("uuid") String uuid,
                                        HttpServletRequest request) throws FileNotFoundException {
        //获得文件路径
        ServletContext servletContext = request.getSession().getServletContext();
        File tempPath = new File(ResourceUtils.getURL("classpath:").getPath());
        String path = tempPath.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator +"resources" + File.separator + "upload" + File.separator;
        String fileName = path + uuid + ".txt";
        if(algorithmService.removeById(algorithmId)){
            MessageController.messageService.save(new Message("id为"+algorithmId+"的算法文件被删除了"));
            new File(fileName).delete();
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
