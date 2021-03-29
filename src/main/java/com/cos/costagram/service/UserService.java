package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;
import com.cos.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
   
   private final UserRepository userRepository;
   
   @Transactional(readOnly = true) //스프링 프레임워크꺼 javax ㄴ
   public UserProfileRespDto 회원프로필(int userId, int principalId) {
      UserProfileRespDto userProfileRespDto = new UserProfileRespDto();

      User userEntity = userRepository.findById(userId).orElseThrow(()->{
         return new IllegalArgumentException();
      });
      
      userProfileRespDto.setFollowState(false);
      userProfileRespDto.setImageCount(userId);
      userProfileRespDto.setFollowCount(0);
      userProfileRespDto.setUser(userEntity);
      
      return userProfileRespDto;
   }
}