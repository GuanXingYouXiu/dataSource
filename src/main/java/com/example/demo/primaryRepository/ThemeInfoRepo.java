package com.example.demo.primaryRepository;

import com.example.demo.primaryEntity.ThemeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeInfoRepo extends JpaRepository<ThemeInfo, Long> {

}
