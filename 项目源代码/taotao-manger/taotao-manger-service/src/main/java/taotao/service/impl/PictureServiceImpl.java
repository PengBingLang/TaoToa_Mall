package taotao.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import taotao.common.utils.IDUtils;
import taotao.service.PictureService;

/**
 * 图片上传服务
 * 
 * @author 彭秉浪
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${file_upload_root_path}")
	private String path;// 服务器端存储文件的物理路径
	@Value("${tomcat_virtual_path}")
	private String vPath;

	@Override
	public Map uploadPicture(MultipartFile imgFile) {
		Map result = new HashMap<>();
		try {
			// 获取上传文件的原文件名
			String oldName = imgFile.getOriginalFilename();
			// 构建新图片文件名
			String newName = IDUtils.genImageName();
			String time = new DateTime().toString("yyyy-MM-dd-");// 生成日期名
			newName = time + newName + oldName.substring(oldName.lastIndexOf("."));

			// 图片的全路径
			String path_name = path + "\\" + newName;

			// 将文件写入磁盘
			File newFile = new File(path_name);
			imgFile.transferTo(newFile);
			// 将新图片的全路径存入数据库
			String url = vPath + "/" + newName;
			boolean b = true;

			if (!b) {
				result.put("error", 1);
				result.put("message", "文件上传失败！");
				return result;
			}
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "文件上传发生异常！");
			return result;
		}
	}
}
