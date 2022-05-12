package com.ssssheep.test.work4.dao;

import com.ssssheep.test.work4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/5/12 23:20 星期四
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
