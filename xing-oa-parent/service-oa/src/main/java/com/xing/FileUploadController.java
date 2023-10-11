package com.xing;

import com.xing.common.result.Result;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: Wang Xing
 * @Date: 14:43 2023/8/31
 */
@RestController
public class FileUploadController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");

    @PostMapping("/upload")
    public Result fileUplad(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String filename = file.getOriginalFilename();       // 文件名
        String suffix = FilenameUtils.getExtension(filename);   // 文件后缀
        if (!filename.endsWith(".pdf")) {
            return Result.fail().message("文件类型不正确");
        }
        String format = simpleDateFormat.format(new Date());
        String realPath = request.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = UUID.randomUUID() + ".pdf";
        try {
            file.transferTo(new File(folder, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + format + newName;
            result.put("url", url);
            return Result.ok(result);
        } catch (IOException e) {
            return Result.fail(e.getMessage());
        }
    }

}
