package com.cos.costagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.image.ImageRepository;
import com.cos.costagram.domain.tag.Tag;
import com.cos.costagram.domain.tag.TagRepository;
import com.cos.costagram.utils.TagUtils;
import com.cos.costagram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	public List<Image> 피드이미지(int principalId){
		
		// 1. principalId 로 내가 팔로우하고 있는 사용자를 찾아야 됨. (한개이거나 컬렉션이거나)
		// select * from image where userId in (select toUserId from follow where fromUserId = 1);
		
		return imageRepository.mFeed(principalId);
	}
	
	@Transactional
	public void 사진업로드(ImageReqDto imageReDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageReDto.getFile().getOriginalFilename();
		//System.out.println("파일명 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		//System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, imageReDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 참고 :  Image 엔티티에 Tag는 주인이 아니다. Image 엔티티로 통해서 Tag를 save할 수 없다.
		
		// 1. Image 저장
		Image image = imageReDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		// 2. Tag 저장
		List<Tag> tags = TagUtils.parsingToTagObject(imageReDto.getTags(), imageEntity);
		tagRepository.saveAll(tags);
		
	}
}
