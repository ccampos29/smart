package com.solutionsystems.smart.service;

import com.solutionsystems.smart.jpa.entity.UserEntity;

public interface IUserService {

    UserEntity findByEmail(String email);

}
