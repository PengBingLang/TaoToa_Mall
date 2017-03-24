package taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import taotao.service.PictureService;

/**
 * 上传图片处理
 * 
 * @author 彭秉浪
 */
@Controller
public class PrctureController {

	@Autowired
	private PictureService pictureService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map pictrueUpload(MultipartFile uploadFile) {
		Map aMap = pictureService.uploadPicture(uploadFile);
		return aMap;
	}
}
